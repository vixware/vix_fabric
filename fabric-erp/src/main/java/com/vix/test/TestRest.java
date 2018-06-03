package com.vix.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.e6soft.form.model.BusinessFormTemplate;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestRest {

	public static void updateOrderStatus() {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> urlVariables = new HashMap<String, Object>();
		String message = restTemplate.postForObject("http://localhost:8080/vform/restService/vform/getvform.rs", String.class, String.class, urlVariables);
		System.out.println(message);

	}

	public static void updateOrderStatus1() {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> urlVariables = new HashMap<String, Object>();
		/*
		 * JSONObject prop = new JSONObject(); prop.put("templateId",
		 * "16e620f057734d8380eb723c87f4a88e"); prop.put("formDataId",
		 * "2f25555ee1bd40f3a299ccdf619f22e3"); urlVariables.put("jsonData",
		 * prop.toCompactString());
		 */
		String message = restTemplate.postForObject("http://192.168.0.101:8080/vform/form/businessbasetemplate/findAllBusinessFormTemplate", String.class, String.class, urlVariables);

		JSONArray ecOrderItemList = JSONArray.fromObject(message);
		List<BusinessFormTemplate> businessFormTemplateList = new ArrayList<BusinessFormTemplate>();
		for (int i = 0; i < ecOrderItemList.size(); i++) {
			BusinessFormTemplate businessFormTemplate = new BusinessFormTemplate();
			JSONObject o = ecOrderItemList.getJSONObject(i);
			businessFormTemplate.setBusinessFormId(o.getString("businessFormId"));
			businessFormTemplate.setTemplateName(o.getString("templateName"));
			businessFormTemplate.setTemplateCode(o.getString("templateCode"));
			businessFormTemplate.setTempatePath(o.getString("tempatePath"));
			businessFormTemplate.setHtmlElementPath(o.getString("htmlElementPath"));
			businessFormTemplate.setHtmlCode(o.getString("htmlCode"));
			businessFormTemplateList.add(businessFormTemplate);
		}
		System.out.println(businessFormTemplateList.toString());

	}

	public static void test() {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> urlVariables = new HashMap<String, Object>();
		JSONObject prop = new JSONObject();
		prop.put("templateId", "16e620f057734d8380eb723c87f4a88e");
		prop.put("formDataId", "2f25555ee1bd40f3a299ccdf619f22e3");
		urlVariables.put("jsonData", prop.toString());
		String message = restTemplate.postForObject("http://192.168.0.101:8080/vform/restService/vform/getvform.rs?jsonData={jsonData}", String.class, String.class, urlVariables);
		System.out.println(message);

	}

	public static void test1() {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> urlVariables = new HashMap<String, Object>();
		urlVariables.put("businessFormTemplateId", "73662c6de7324776a5f69ef5cd9dd971");
		String message = restTemplate.postForObject("http://192.168.0.101:8080/vform/form/businessbasetemplate/list.rs?businessFormTemplateId={businessFormTemplateId}", String.class, String.class, urlVariables);
		System.out.println(message);

	}

	public static void test2() {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> urlVariables = new HashMap<String, Object>();
		urlVariables.put("businessFormId", "73662c6de7324776a5f69ef5cd9dd971");
		String message = restTemplate.postForObject("http://192.168.0.101:8080/vform/form/businessformtemplate/list?businessFormId={businessFormId}", String.class, String.class, urlVariables);
		System.out.println(message);

	}

	public static void test3() {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> urlVariables = new HashMap<String, Object>();
		String message = restTemplate.postForObject("http://192.168.0.101:8080/vform/form/businessformtype/tree", String.class, String.class, urlVariables);
		System.out.println(message);

	}

	public static void test4() {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> urlVariables = new HashMap<String, Object>();
		String message = restTemplate.postForObject("http://192.168.0.101:8080/vform/form/businessformtype/tree", String.class, String.class, urlVariables);
		System.out.println(message);

	}

	public static void test5() {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> urlVariables = new HashMap<String, Object>();
		String message = restTemplate.postForObject("http://192.168.0.101:8080/vform/form/businessform/findBusinessFormList", String.class, String.class, urlVariables);
		System.out.println(message);

	}

	public static void main(String args[]) {
		// test();
		// updateOrderStatus();
		test5();
		// test1();
	}

}
