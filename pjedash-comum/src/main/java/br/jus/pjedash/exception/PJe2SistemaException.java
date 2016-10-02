package br.jus.pjedash.exception;

import java.util.List;

import javax.ejb.ApplicationException;

/**
 * Esta exceção deve ser utilizada para encapsular todas as exceções do sistema
 * que não forem originadas das regras de negócio.
 *
 */
@ApplicationException(rollback = true, inherited = true)
public class PJe2SistemaException extends AbstractException {

	private static final long serialVersionUID = 1L;

	public PJe2SistemaException(String message) {
		super(message);
	}

	public PJe2SistemaException(String message, Throwable cause) {
		super(message, cause);
	}

	public PJe2SistemaException(Throwable cause) {
		super(cause);
	}

	public PJe2SistemaException(List<String> messages) {
		super(messages);
	}

}