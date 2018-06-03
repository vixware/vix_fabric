/**
 * 
 */
package com.vix.restService.workbench;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.vix.common.security.entity.Module;

/**
 * com.vix.restService.workbench.Testrest
 *
 * @author bjitzhang
 *
 * @date 2015年4月7日
 */
@Controller("testrest")
@Scope("prototype")
public class Testrest {
	@Autowired
	private RestOperations restTemplate;

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		Module favUser = restTemplate.getForObject("http://192.168.0.101:8080/vixnt/restService/{id}", Module.class);
		System.out.println("userId:    " + favUser.getCode());
		System.out.println("userName:    " + favUser.getName());
		System.out.println("userAge:    " + favUser.getCompanyInnerCode());
	}

}
