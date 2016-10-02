package br.jus.pjedash.exception;

import java.util.List;

import javax.ejb.ApplicationException;

/**
 * Esta exceção deve ser utilizada para as regras de negócio do sistema. Ela faz
 * com que a transação seja interrompida e o container realize um rollback
 * automaticamente.
 *
 */
@ApplicationException(rollback = true, inherited = true)
public class PJe2RNsException extends AbstractException {

	private static final long serialVersionUID = 1L;

	public PJe2RNsException(String message) {
		super(message);
	}

	public PJe2RNsException(String message, Throwable cause) {
		super(message, cause);
	}

	public PJe2RNsException(Throwable cause) {
		super(cause);
	}

	public PJe2RNsException(List<String> messages) {
		super(messages);
	}

}