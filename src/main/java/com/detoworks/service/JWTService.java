package com.detoworks.service;

import com.google.common.base.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class JWTService {

	private Logger logger = LogManager.getLogger(JWTService.class);

	public Map<String, String> decoded(String JWTEncoded) {
		Map<String, String> map = new LinkedHashMap<>();
		try {
			String[] split = JWTEncoded.split("\\.");
			map.put("Header", getJson(split[0]));
			map.put("Body", getJson(split[1]));
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		}
		return map;
	}

	private static String getJson(String strEncoded) throws UnsupportedEncodingException {
		byte[] decodedBytes = Base64.getUrlDecoder().decode(strEncoded.getBytes(Charsets.UTF_8));
		return new String(decodedBytes, "UTF-8");
	}

}
