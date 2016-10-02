package br.jus.pjedash.servico.util;

import net.minidev.json.JSONObject;

public class ResponseMessageJson {
	
	
	public static String getJsonString(String messageValue) {
		return getJsonString("message", messageValue);
	}
	
	public static String getJsonString(String messaeKey, String messageValue) {
		JSONObject messageJson = new JSONObject();
		messageJson.put(messaeKey, messageValue);
		
		return messageJson.toJSONString();
	}

}
