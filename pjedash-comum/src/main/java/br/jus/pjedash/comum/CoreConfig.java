package br.jus.pjedash.comum;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import br.jus.pjedash.mensagem.Messages;

/**
 * Esta classe encapsula o arquivo de propriedades que contém os parâmetros e configurações da aplicação.
 * 
 */
public enum CoreConfig implements Messages {
	
	USER_PASSWORD_HASH_ALGORITHM("user.password.hashalgorithm"),
	USER_PASSWORD_HASH_ENCODING("user.password.hashencoding"),

	LOGGER_FILE_BACKUPS("logger.file.backups"),
	LOGGER_FILE_NAME("logger.file.name"),
	LOGGER_FILE_SIZE("logger.file.size"),
	LOGGER_LEVEL("logger.level"),
	LOGGER_USE_PARENT_HANDLERS("logger.useparenthandlers"),

	CHARSET_DEFAULT("charset.default"),
	
	;

	private String key;

	private static ResourceBundle messages = ResourceBundle.getBundle("core_configurations");

	private CoreConfig(String key) {
		this.key = key;
	}

	public String getMessage(Object... params) {
		return MessageFormat.format(CoreConfig.messages.getString(this.key), params);
	}

	public static String getAnotherMessage(String anotherKey, Object... params) {
		return MessageFormat.format(CoreConfig.messages.getString(anotherKey), params);
	}
	
}