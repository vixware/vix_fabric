package com.rest.ebusiness;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.rest.core.base.BaseRestController;
import com.vix.oa.task.taskDefinition.entity.VixTask;

@Controller
@RequestMapping(value = "restService/updateOrder")
public class UpdateStatusController extends BaseRestController {

	public static void updateOrderStatus() {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> urlVariables = new HashMap<String, Object>();
		//String jsonData = "{\"currencyType\":\"CT_001,%E4%BA%BA%E6%B0%91%E5%B8%81\",\"channelDistributorCode\":\"ECSHOP-001\",\"invoiceContent\":\"%E6%B0%B4%E6%9E%9C\",\"dispatchType\":\"SF\",\"code\":\"20151116145758757\",\"consignee\":\"z%E2%80%86l,%E6%B9%96%E5%8C%97%E7%9C%81-%E8%A5%84%E6%A8%8A%E5%B8%82-%E8%A5%84%E9%98%B3%E5%8C%BA,18310707792\",\"totalPrice\":0.01,\"freight\":0,\"validateCode\":\"37a403ea18d7d3d2a86fe6cce84ec906\",\"dealStatus\":\"EO_DS_HASCONFIRMED\",\"logisticStatus\":\"EO_LS_WAITDELIVERGOODS\",\"onlineStoreCode\":\"ECSHOP-001\",\"freeFreight\":0,\"ecCustomer\":\"d84a9832-c370-48a7-8769-75a5ff67c95a,%E8%87%AA%E5%8A%A8%E6%B3%A8%E5%86%8C\",\"phoneConfirm\":\"1\",\"payType\":\"PAY_1006,%E8%B4%A7%E5%88%B0%E4%BB%98%E6%AC%BE\",\"totalGiveIntegral\":0,\"ecOrderItem\":[{\"itemType\":\"buy\",\"itemName\":\"%E5%B0%9A%E5%93%81%E9%87%91%E4%B8%9D%E7%BA%A24%E7%B2%92%E8%A3%85\",\"coinExchange\":0,\"price\":0.0128,\"giveMCoin\":0,\"count\":1,\"sku\":\"A104\"}],\"invoiceType\":\"1\",\"totalTakeIntegral\":0,\"totalProductPrice\":0.0128,\"deliveryDate\":\"2\",\"paymentStatus\":\"EO_PS_CASHONDELIVERY\",\"createDate\":\"2015-11-16\"}";
		//urlVariables.put("id", "a9fec7a0-50dc-1b73-8150-dc3056080001");
		restTemplate.delete("http://localhost:8080/vixnt-erp/restService/app/task/findTaskList.rs", urlVariables);
	}

	/*	public static void updateOrderStatus() {
			RestTemplate restTemplate = new RestTemplate();
			Map<String, Object> urlVariables = new HashMap<String, Object>();
			String jsonData = "{\"currencyType\":\"CT_001,%E4%BA%BA%E6%B0%91%E5%B8%81\",\"channelDistributorCode\":\"ECSHOP-001\",\"invoiceContent\":\"%E6%B0%B4%E6%9E%9C\",\"dispatchType\":\"SF\",\"code\":\"20151116145758757\",\"consignee\":\"z%E2%80%86l,%E6%B9%96%E5%8C%97%E7%9C%81-%E8%A5%84%E6%A8%8A%E5%B8%82-%E8%A5%84%E9%98%B3%E5%8C%BA,18310707792\",\"totalPrice\":0.01,\"freight\":0,\"validateCode\":\"37a403ea18d7d3d2a86fe6cce84ec906\",\"dealStatus\":\"EO_DS_HASCONFIRMED\",\"logisticStatus\":\"EO_LS_WAITDELIVERGOODS\",\"onlineStoreCode\":\"ECSHOP-001\",\"freeFreight\":0,\"ecCustomer\":\"d84a9832-c370-48a7-8769-75a5ff67c95a,%E8%87%AA%E5%8A%A8%E6%B3%A8%E5%86%8C\",\"phoneConfirm\":\"1\",\"payType\":\"PAY_1006,%E8%B4%A7%E5%88%B0%E4%BB%98%E6%AC%BE\",\"totalGiveIntegral\":0,\"ecOrderItem\":[{\"itemType\":\"buy\",\"itemName\":\"%E5%B0%9A%E5%93%81%E9%87%91%E4%B8%9D%E7%BA%A24%E7%B2%92%E8%A3%85\",\"coinExchange\":0,\"price\":0.0128,\"giveMCoin\":0,\"count\":1,\"sku\":\"A104\"}],\"invoiceType\":\"1\",\"totalTakeIntegral\":0,\"totalProductPrice\":0.0128,\"deliveryDate\":\"2\",\"paymentStatus\":\"EO_PS_CASHONDELIVERY\",\"createDate\":\"2015-11-16\"}";
			urlVariables.put("jsonData", jsonData);
			String message = restTemplate.postForObject("http://localhost:8080/vixnt-erp/restService/orderDown/addOrder.rs?jsonData={jsonData}", String.class, String.class, urlVariables);
			System.out.println(message);
		}
	*/
	public static void main(String args[]) {
		uploadfruitdata();
	}

	/*public static void updateOrderStatus() {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> urlVariables = new HashMap<String, Object>();
		String jsonData = "{\"validateCode\":\"validateCode\",\"channelDistributorCode\":\"ECSHOP-001\",\"orderCode\":\"2015110917551277\",\"createDate\":\"2015-10-11 11:23:12\",\"companyCode\":\"1001001\",\"code\":\"1112232\",\"ecOrderCode\":\"20151030123040367\",\"returnGoodsAmount\":116.0,\"amountStatus\":\"1\",\"status\":\"1\",\"memo\":\"1\",\"returnGoodsItemBills\":[{\"createDate\":\"2015-10-11 11:23:12\",\"companyCode\":\"1001001\",\"sku\":\"2323221\",\"ecProductCode\":\"EP_20151027155347536\",\"returnAmount\":116.0,\"takeOffPointValue\":11,\"returnGoodsBillCode\":\"1112232\"}]}";
		urlVariables.put("jsonData", jsonData);
		String message = restTemplate.postForObject("http://localhost:8080/vixnt-erp/restService/orderReturn/returnOrder.rs?jsonData={jsonData}", String.class, String.class, urlVariables);
		System.out.println(message);
	}*/

	/*public static void uploadfruitdata() {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> urlVariables = new HashMap<String, Object>();
		String fruitData = "{\"tablename\":\"fruit\",\"tabledata\":{\"serialnumber\":\"321321\",\"dateregistration\":\"2015/09/22\",\"fruitsize\":\"3213213\",\"fruitclassificationid\":\"213213\",\"farmersid\":\"213213\",\"fruitdiameterid\":\"213213\",\"note\":\"21321321\"}}";
		urlVariables.put("fruitData", fruitData);
		String message = restTemplate.postForObject("http://localhost:8080/vixnt-erp/restService/dataSelect/select.rs?fruitData={fruitData}", String.class, String.class, urlVariables);
		System.out.println(message);
	}*/

	/*public static void uploadfruitdata() {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> urlVariables = new HashMap<String, Object>();
		String codeData = "{\"productcode\":\"00000000000101-00000000000111\",\"packagecode\":\"00100000000111\",\"itemcode\":\"3213213213213\"}";
		urlVariables.put("codeData", codeData);
		String message = restTemplate.postForObject("http://localhost:8080/vixnt-erp/restService/uploadCode/upload.rs?codeData={codeData}", String.class, String.class, urlVariables);
		System.out.println(message);
	}*/

	/*public static void uploadfruitdata() {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> urlVariables = new HashMap<String, Object>();
		String fruitData = "{\"tablename\":\"datacodesource\",\"tabledata\":{\"packagecode\":\"00100000000060\"}}";
		urlVariables.put("fruitData", fruitData);
		String message = restTemplate.postForObject("http://localhost:8080/vixnt-erp/restService/dataSelect/select.rs?fruitData={fruitData}", String.class, String.class, urlVariables);
		System.out.println(message);
	}*/

	/*	public static void uploadfruitdata() {
			RestTemplate restTemplate = new RestTemplate();
			Map<String, Object> urlVariables = new HashMap<String, Object>();
			try {
				String updatecode = "{\"productcode\":\"00000000000050\",\"packagecode\":\"00000000000050\"}";
				urlVariables.put("updatecode", updatecode);
				String message = restTemplate.postForObject("http://localhost:8080/vixnt-erp/restService/uploadCode/update.rs?updatecode={updatecode}", String.class, String.class, urlVariables);
				System.out.println(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/

	//上传水果
	/*public static void uploadfruitdata() {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> urlVariables = new HashMap<String, Object>();
		String fruitData = "{\"tablename\":\"fruit\",\"tabledata\":{\"serialnumber\":\"321321\",\"dateregistration\":\"2015/09/22\",\"fruitsize\":\"3213213\",\"fruitclassificationid\":\"213213\",\"farmersid\":\"213213\",\"fruitdiameterid\":\"213213\",\"note\":\"21321321\"}}";
		urlVariables.put("fruitData", fruitData);
		String message = restTemplate.postForObject("http://localhost:8080/vixnt-erp/restService/fruitDataUpload/upload.rs?fruitData={fruitData}", String.class, String.class, urlVariables);
		System.out.println(message);
	}*/
	private static String TEST_ACCOUNT = "testlg";
	private static String TEST_PASSWORD = "202cb962ac59075b964b07152d234b70";
	//测试任务保存
	public static void uploadfruitdata() {
		try {
			Map<String, Object> formData = new HashMap<String, Object>();
			VixTask taskDefinition=new VixTask();
			taskDefinition.setCode("213213");
			taskDefinition.setName("343243");
			String url = "http://localhost:8080/vixnt-erp/restService/app/task/saveTask.rs";
			//String response = postData(formData, url);
			HttpResponse<JsonNode> jsonResponse = Unirest.post(url).basicAuth(TEST_ACCOUNT, TEST_PASSWORD).fields(formData).asJson();
			System.out.println(jsonResponse); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String postData(MultiValueMap<String, Object> formData, String url) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Accept-Charset", "utf-8");
		requestHeaders.setContentType(org.springframework.http.MediaType.MULTIPART_FORM_DATA);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(formData, requestHeaders);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class, formData);
		return response.getBody();
	}

	/*	public static void uploadfruitdata() {
			RestTemplate restTemplate = new RestTemplate();
			Map<String, Object> urlVariables = new HashMap<String, Object>();
			try {
				urlVariables.put("name", "name");
				urlVariables.put("code", "code");
				String message = restTemplate.postForObject("http://localhost:8080/vixnt-erp/restService/app/taskAndPlan/deleteTaskDefinition.rs?fruitData={fruitData}", String.class, String.class, urlVariables);
				System.out.println(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	*///果经录入
	/*public static void uploadfruitdata() {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> urlVariables = new HashMap<String, Object>();
		try {
			JSONObject json = new JSONObject();
			json.put("tablename", "fruitdiameter");
			JSONObject json1 = new JSONObject();
			json1.put("fruitdiameter", URLEncoder.encode("果经", "UTF-8"));
			json1.put("note", URLEncoder.encode("备注", "UTF-8"));
			json.put("tabledata", json1);
			urlVariables.put("fruitData", json.toCompactString());
			String message = restTemplate.postForObject("http://localhost:8080/vixnt-erp/restService/fruitDataUpload/upload.rs?fruitData={fruitData}", String.class, String.class, urlVariables);
			System.out.println(message);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}*/
	//农户
	/*	public static void uploadfruitdata() {
			RestTemplate restTemplate = new RestTemplate();
			Map<String, Object> urlVariables = new HashMap<String, Object>();
			try {
				JSONObject json = new JSONObject();
				json.put("tablename", "farmers");
				JSONObject json1 = new JSONObject();
				json1.put("name", URLEncoder.encode("名称", "UTF-8"));
				json1.put("sex", URLEncoder.encode("性别", "UTF-8"));
				json1.put("leveleducation", URLEncoder.encode("文化程度", "UTF-8"));
				json1.put("politicallandscape", URLEncoder.encode("政治面貌", "UTF-8"));
				json1.put("idnumber", URLEncoder.encode("身份证号", "UTF-8"));
				json1.put("health", URLEncoder.encode("健康状况", "UTF-8"));
				json1.put("groundinformation", URLEncoder.encode("宗地信息", "UTF-8"));
				json1.put("homeaddress", URLEncoder.encode("家庭住址", "UTF-8"));
				json1.put("contact", URLEncoder.encode("联系方式", "UTF-8"));
				json1.put("contact", URLEncoder.encode("联系方式", "UTF-8"));
				json1.put("note", URLEncoder.encode("备注", "UTF-8"));
				json.put("tabledata", json1);
				urlVariables.put("fruitData", json.toCompactString());
				String message = restTemplate.postForObject("http://localhost:8080/vixnt-erp/restService/fruitDataUpload/upload.rs?fruitData={fruitData}", String.class, String.class, urlVariables);
				System.out.println(message);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}*/

	/*public static void uploadfruitdata() {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> urlVariables = new HashMap<String, Object>();
		try {
			JSONObject json = new JSONObject();
			json.put("tablename", "fruitdiameter");
			JSONObject json1 = new JSONObject();
			json1.put("fruitdiameter", URLEncoder.encode("果经", "UTF-8"));
			json.put("tabledata", json1);
			urlVariables.put("fruitData", json.toCompactString());
			String message = restTemplate.postForObject("http://localhost:8080/vixnt-erp/restService/dataSelect/select.rs?fruitData={fruitData}", String.class, String.class, urlVariables);
			System.out.println(message);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}*/

	/*public static void uploadfruitdata() {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> urlVariables = new HashMap<String, Object>();
		try {
			JSONObject json = new JSONObject();
			json.put("tablename", "fruit");
			//JSONObject json1 = new JSONObject();
			//json1.put("name", URLEncoder.encode("", "UTF-8"));
			//json.put("tabledata", json1);
			urlVariables.put("fruitData", json.toCompactString());
			String message = restTemplate.postForObject("http://localhost:8080/vixnt-erp/restService/dataSelect/select.rs?fruitData={fruitData}", String.class, String.class, urlVariables);
			System.out.println(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/*public static void uploadfruitdata() {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> urlVariables = new HashMap<String, Object>();
		try {
			JSONObject json = new JSONObject();
			json.put("tablename", "fruitclassification");
			JSONObject json1 = new JSONObject();
			json1.put("name", URLEncoder.encode("名称", "UTF-8"));
			json1.put("uses", URLEncoder.encode("用途", "UTF-8"));
			json.put("tabledata", json1);
			urlVariables.put("fruitData", json.toCompactString());
			String message = restTemplate.postForObject("http://localhost:8080/vixnt-erp/restService/dataSelect/select.rs?fruitData={fruitData}", String.class, String.class, urlVariables);
			System.out.println(message);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}*/
	/*public static void main(String args[]) {
		updateOrderStatus();
	}*/

	/*public static void uploadfruitdata() {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> urlVariables = new HashMap<String, Object>();
		try {
			JSONObject json = new JSONObject();
			json.put("tablename", "fruit");
			JSONObject json1 = new JSONObject();
			json1.put("serialnumber", "1234567890");
			json1.put("dateregistration", "2015/09/22");
			json1.put("fruitsize", "1234567890");
			json1.put("fruitclassificationid", "8afa9a237db5489fad88215c07801ffa");
			json1.put("farmersid", "24e0ae8fec5946a99df7d860813ab1fd");
			json1.put("fruitdiameterid", "b711b78259ef47f2838c13628e262588");
			json1.put("note", URLEncoder.encode("备注", "UTF-8"));
			json.put("tabledata", json1);
			urlVariables.put("fruitData", json.toCompactString());
			String message = restTemplate.postForObject("http://localhost:8080/vixnt-erp/restService/fruitDataUpload/upload.rs?fruitData={fruitData}", String.class, String.class, urlVariables);
			System.out.println(message);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}*/

	/*public static void main(String[] args) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> urlVariables = new HashMap<String, Object>();
		urlVariables.put("code", "20151117104942661");
		urlVariables.put("expressNo", "51008341705200");
		urlVariables.put("expressType", "HTKY");
		String authCode = null;
		if (StrUtils.objectIsNotNull("VIX")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhh");
			Md5Encoder m5 = new Md5EncoderImpl();
			authCode = m5.encodeString("VIX", sdf.format(new Date()));
			System.out.println("authCode:" + authCode);
		}
		urlVariables.put("validateCode", authCode);

		String message = restTemplate.postForObject("http://121.41.36.11/SnowX/restService/ecOrder/updateEcOrderExpressNoByCode.rs?code={code}&validateCode={validateCode}&expressNo={expressNo}&expressType={expressType}", String.class, String.class, urlVariables);
		System.out.println(message);

		String moStr = restTemplate.postForObject("http://121.41.36.11/SnowX/restService/ecOrder/updateEcOrderExpressNoByCode.rs?code={code}&validateCode={validateCode}&expressNo={expressNo}&expressType={expressType}", String.class, String.class, urlVariables);
		System.out.println(moStr);

	}*/
	/*public static void uploadfruitdata() {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> urlVariables = new HashMap<String, Object>();
		String updatecode = "{\"productcode\":\"00000000000012-0000000000015\",\"packagecode\":\"00100000000013\"}";
		urlVariables.put("updatecode", updatecode);
		String message = restTemplate.postForObject("http://localhost:8080/vixnt-erp/restService/uploadCode/onlyOnePackageAndMoreProduct.rs?updatecode={updatecode}", String.class, String.class, urlVariables);
		System.out.println(message);
	}*/
}
