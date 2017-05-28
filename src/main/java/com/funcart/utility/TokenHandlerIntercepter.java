/*package com.funcart.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.funcart.controller.CustomerController;

@Component
public class TokenHandlerIntercepter implements HandlerInterceptor{
	
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
		boolean flag = true;
		JWTTokenGenerator jwt = new JWTTokenGenerator();
		String token = "";
		try {
			token = jwt.generateJWTToken("sandi10.96@gmail.com");
		} catch (IllegalArgumentException | JWTCreationException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(jwt.parseJWT(token, "sandi10.96@gmail.com")){
			System.out.println("It's valid");
		}
		jwt.refreshJWT(token,"sandi10.96@gmail.com");
		jwt.refreshJWT(token,"sandi10.96@gmail.com");
		return flag;
	}
	
	public void postHandle(HttpServletRequest request,HttpServletResponse response,Object handler,ModelAndView modelAndView)throws Exception{
	    //if (!(((HandlerMethod)handler).getBean() instanceof CustomerController))
		
	    if(request.getPathInfo().equals("/login")){
	    	String requestBody = getBody(request);
		    
	    	ObjectMapper mapper = new ObjectMapper();
	        JsonNode actualObj = mapper.readTree(requestBody);
	    	response.addHeader("token",jwtTokenGenerator.generateJWTToken(actualObj.get("emailOrPhoneNumber").toString(),"funcart"));
	    	response.addHeader("issuer","funcart");
	    }else{
	    	jwtTokenGenerator.refreshJWT();
	    }
	}

	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler, Exception ex)throws Exception{
		System.out.println("After completetion");
	}
	
	public String getBody(HttpServletRequest request) throws IOException {

	    String body = null;
	    StringBuilder stringBuilder = new StringBuilder();
	    BufferedReader bufferedReader = null;

	    try {
	        InputStream inputStream = request.getInputStream();
	        if (inputStream != null) {
	            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	            char[] charBuffer = new char[128];
	            int bytesRead = -1;
	            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
	                stringBuilder.append(charBuffer, 0, bytesRead);
	            }
	        } else {
	            stringBuilder.append("");
	        }
	    } catch (IOException ex) {
	        throw ex;
	    } finally {
	        if (bufferedReader != null) {
	            try {
	                bufferedReader.close();
	            } catch (IOException ex) {
	                throw ex;
	            }
	        }
	    }
	    body = stringBuilder.toString();
	    return body;
	}
}
*/