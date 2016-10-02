package br.jus.pjedash.servico;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

@Provider
@Produces( MediaType.APPLICATION_JSON )
public class JsonProvider implements ContextResolver<com.fasterxml.jackson.databind.ObjectMapper> {
	
	private final ObjectMapper json;

    public JsonProvider() {
        
    	json = new ObjectMapper()
                .findAndRegisterModules()
                .configure( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false )
                .configure( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false );
    	
    	SimpleModule module = new SimpleModule("PjeDashModule");
		//module.addDeserializer(IPredicate.class, new PredicateDeserializer());
		json.registerModule(module);
		
		Hibernate4Module hibernateModule = new Hibernate4Module();
		hibernateModule.configure(Hibernate4Module.Feature.FORCE_LAZY_LOADING, false);
		hibernateModule.configure(Hibernate4Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS, true);
		hibernateModule.enable(Hibernate4Module.Feature.USE_TRANSIENT_ANNOTATION);
        json.registerModule(hibernateModule);
    }

	public ObjectMapper getContext(Class<?> objeto) {
		return json;
	}
}