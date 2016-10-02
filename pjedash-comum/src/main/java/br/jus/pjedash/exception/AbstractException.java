package br.jus.pjedash.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.ApplicationException;

/**
 * Esta exceção não deve ser utilizada. Ela é apenas a classe raiz das demais
 * exceções.
 *
 */
@ApplicationException(rollback = true, inherited = true)
public abstract class AbstractException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private List<String> listMessages;

	public AbstractException(String message) {
		
		super(message);
		this.initializeListMessage();
		this.listMessages.add(this.getMessage());
	}

	public AbstractException(String message, Throwable cause) {
		
		super(message, cause);
		this.initializeListMessage();
		this.listMessages.add(this.getMessage());
	}

	public AbstractException(Throwable cause) {
		
		super(cause);
		this.initializeListMessage();
		this.listMessages.add(this.getMessage());
	}

	public AbstractException(List<String> messages) {
		
		super(messages == null ? null : messages.toString());
		this.listMessages = messages;
	}

	public List<String> getListMessages() {
		
		this.initializeListMessage();
		return Collections.unmodifiableList(this.listMessages);
	}

	private void initializeListMessage() {
		
		if (this.listMessages == null) {
			this.listMessages = new ArrayList<String>();
		}
	}

}