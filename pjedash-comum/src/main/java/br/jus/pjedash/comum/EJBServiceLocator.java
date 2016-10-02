package br.jus.pjedash.comum;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EJBServiceLocator {

	@SuppressWarnings("unchecked")
	public static <T> T locateEJBStateless(Class<T> clazz){
		if(clazz == null){
			return null;
		}
		String jndi = "";
		String ejbName = clazz.getSimpleName().replaceFirst("I", "").replace("Remote", "");
		String fqn = clazz.getCanonicalName();

		jndi = "ejb:pjedash-ear/pjedash-negocio/"+ ejbName+ "!" + fqn;
		final Properties jndiProperties = new Properties();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		Context context;
		try {
			context = new InitialContext(jndiProperties);
			return (T) context.lookup(jndi);
		} catch (NamingException e) {
			return null;
		}
	}
}
