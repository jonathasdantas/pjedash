package br.jus.pjedash.util;

import java.util.Properties;

public class ConfigurationProperties {
	private static final String CONFIG_PATH_FILE = "config.properties";
	
	private static ConfigurationProperties instance = null;
	private Properties properties = null;

	private ConfigurationProperties() {
		this.properties = new Properties();
		try {
			properties
					.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_PATH_FILE));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static ConfigurationProperties getInstance() {
		if (instance == null) {
			instance = new ConfigurationProperties();
		}
		return instance;
	}

	public String getProperty(String key) {
		String result = null;
		if (key != null && !key.trim().isEmpty()) {
			result = this.properties.getProperty(key);
		}
		return result;
	}

}
