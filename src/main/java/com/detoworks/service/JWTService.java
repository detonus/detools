package com.detoworks.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.base.Charsets;
import io.jsonwebtoken.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Base64;

@Service
public class JWTService {

	private Logger logger = LogManager.getLogger(JWTService.class);

	private ObjectMapper mapper;

	@PostConstruct
	void init() {
		mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
	}

	public String decoded(String JWTEncoded) {
		String output = "";
		try {
			String json = "{";
			//json += Arrays.stream(JWTEncoded.split("\\.")).limit(2).map(el -> getJson(el)).collect(Collectors.joining(","));
			String[] split = JWTEncoded.split("\\.");
			json += "\"header\":" + getJson(split[0]);
			json += ",\"payload\":" + getJson(split[1]);
			json += "}";
			Object jsonObject = mapper.readValue(json, Object.class);
			output += mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
		} catch (Exception e) {
			logger.error(e);
			output += e.getMessage();
		}
		return output;
	}

	private static String getJson(String strEncoded) {
		byte[] decodedBytes = Base64.getUrlDecoder().decode(strEncoded.getBytes(Charsets.UTF_8));
		try {
			return new String(decodedBytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return e.getMessage();
		}
	}

	public String decoded2(String JWTEncoded) {
	    try {
	    	Key key = new SecretKeySpec("secret".getBytes(), SignatureAlgorithm.HS256.getJcaName());
			Jws<Claims> parsed = Jwts.parser()
					//.setSigningKey(DatatypeConverter.parseBase64Binary("secret"))
					.setSigningKey(key)
					.parseClaimsJws(JWTEncoded);
            return "{" + parsed.getHeader().toString()
					+ "," + parsed.getBody().toString()
					+ "," + parsed.getSignature()
					+ "}";
        } catch (Exception e) {
	        return e.getMessage();
        }

	}

}
