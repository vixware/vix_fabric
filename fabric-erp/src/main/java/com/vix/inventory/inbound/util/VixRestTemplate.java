/**
 * 
 */
package com.vix.inventory.inbound.util;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * com.vix.inventory.inbound.util.VixRestTemplate
 *
 * @author bjitzhang
 *
 * @date 2015年12月3日
 */
public class VixRestTemplate extends RestTemplate {
	/*
	 *初始化RestTemplate，RestTemplate会默认添加HttpMessageConverter
	 * 添加的StringHttpMessageConverter非UTF-8
	 * 所以先要移除原有的StringHttpMessageConverter，
	 * 再添加一个字符集为UTF-8的StringHttpMessageConvert
	 * */
	public void reInitMessageConverter(RestTemplate restTemplate) {
		List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
		HttpMessageConverter<?> converterTarget = null;
		for (HttpMessageConverter<?> item : converterList) {
			if (item.getClass() == StringHttpMessageConverter.class) {
				converterTarget = item;
				break;
			}
		}

		if (converterTarget != null) {
			converterList.remove(converterTarget);
		}
		HttpMessageConverter<?> converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
		converterList.add(converter);
	}
}
