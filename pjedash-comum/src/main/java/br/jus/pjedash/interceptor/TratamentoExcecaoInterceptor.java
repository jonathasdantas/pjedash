package br.jus.pjedash.interceptor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;

import javax.ejb.EJBException;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.PersistenceException;
import javax.transaction.RollbackException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import br.jus.pjedash.exception.PJe2RNsException;
import br.jus.pjedash.exception.PJe2SistemaException;
import br.jus.pjedash.mensagem.CoreMessages;
import br.jus.pjedash.util.StringUtils;

@TratamentoExcecao
@Interceptor
public class TratamentoExcecaoInterceptor {

	public TratamentoExcecaoInterceptor() {
		// TODO Auto-generated constructor stub
	}

	@AroundInvoke
	public Object tratarExcecao(InvocationContext ctx) throws Exception {

		try {
			Object retorno = ctx.proceed();
			return retorno;
		} catch (SQLException sqlEx) {
			throw new PJe2SistemaException(sqlEx);
		} catch (EJBException ejbEx) {
			String msgValidacao = tratarConstraint(ejbEx);
			if(msgValidacao == null || msgValidacao.isEmpty()){
				throw new PJe2SistemaException(ejbEx);
			}
			else{
				throw new PJe2SistemaException(msgValidacao);
			}
			
		} catch (PJe2RNsException rnEx) {
			try{
				String resposta = CoreMessages.getAnotherMessage(rnEx.getMessage(), null);
				throw new PJe2RNsException(resposta);
			}
			catch(MissingResourceException e){
				throw rnEx;
			}
		} catch (PJe2SistemaException sistemaEx) {
			throw sistemaEx;
		} catch (ConstraintViolationException cve){
			throw cve;
		}catch (Exception ex) {
			throw new PJe2SistemaException(ex);
		}
		
	}

	private String tratarConstraint(Throwable ejbEx) {
		String ret = null;
		List<String> lista = new ArrayList<String>(0);
		
		if(ejbEx.getCause() instanceof RollbackException){
			ejbEx = ejbEx.getCause();
			while(ejbEx.getCause() instanceof PersistenceException){
				ejbEx = ejbEx.getCause();
			}
		}
				
		if(ejbEx.getCause() instanceof ConstraintViolationException){
			ConstraintViolationException c = (ConstraintViolationException) ejbEx.getCause();
			for(ConstraintViolation constraints : ((ConstraintViolationException) c).getConstraintViolations()){
				String code = null;
				try{
					code = CoreMessages.getAnotherMessage(constraints.getMessage(), null);
				}
				catch(MissingResourceException exc){
					code = constraints.getMessage();
				}
				lista.add(code);
			}
		}
		if(lista.size() > 0){
			ret = StringUtils.lista(lista, ",", true);
		}
		return ret;
	}
}