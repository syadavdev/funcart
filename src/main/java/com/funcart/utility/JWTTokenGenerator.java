package com.funcart.utility;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.auth0.jwt.exceptions.JWTCreationException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTTokenGenerator{
	
	public static final Logger log = LoggerFactory.getLogger(JWTTokenGenerator.class);
	private JwtBuilder builder;
	
	public String generateJWTToken(String secret) throws IllegalArgumentException, JWTCreationException, UnsupportedEncodingException{
		
		long ttlMillis = 216*1000*1000*10;	//6 hours
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		String id = UUID.randomUUID().toString().replace("-", "");
		
		Date now = new Date(System.currentTimeMillis());
			 
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
		SecretKeySpec signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		
		builder = Jwts.builder().setId(id)
		                        .setIssuedAt(now)
		                        .setIssuer("funcart")
		                        .signWith(signatureAlgorithm, signingKey);
		if (ttlMillis >= 0) {
		    Date expDate = new Date(System.currentTimeMillis() + ttlMillis);
		    builder.setExpiration(expDate);
		}
	
		return builder.compact();
	}
	
	public boolean parseJWT(String token,String secret){
		boolean flag = false;
		try{	
			Claims claims = Jwts.parser()        
						 		.setSigningKey(DatatypeConverter.parseBase64Binary(secret))
						 		.parseClaimsJws(token)
						 		.getBody();
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}		
	
/*	public void refreshJWT(String token,String secret){
		
		try{
			String claims = Jwts.parser()
								.setSigningKey(DatatypeConverter.parseBase64Binary(secret))
								.parseClaimsJws(token)
								.getBody()
								.setExpiration(expDate)
								.getSubject();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println(Jwts.parser()
				.setSigningKey(DatatypeConverter.parseBase64Binary(secret))
				.parseClaimsJws(token)
				.getBody().getExpiration());
	}*/
}