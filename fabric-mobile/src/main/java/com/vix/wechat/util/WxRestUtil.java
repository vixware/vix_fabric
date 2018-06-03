package com.vix.wechat.util;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;


public class WxRestUtil {
	
	public static String postForObject(RestTemplate restTemplate,String url,String stringEntity){
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
        //headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(stringEntity,headers);
        String response =  restTemplate.postForObject(url, entity, String.class);
		return response;
	}
	
	public static String getForObject(RestTemplate restTemplate,String url){
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
        //headers.setContentType(MediaType.APPLICATION_JSON);
        String response =  restTemplate.getForObject(url, String.class);
		return response;
	}
}
