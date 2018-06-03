/**
 * 
 */
package com.vix.test;

import com.yhd.YhdClient;
import com.yhd.request.product.GeneralProductsSearchRequest;
import com.yhd.response.product.GeneralProductsSearchResponse;

/**
 * com.vix.test.TestYihaodian
 * @author zhanghaibing
 *
 * @date 2014年9月28日
 */
public class TestYihaodian {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GeneralProductsSearchRequest req = new GeneralProductsSearchRequest();
		req.setCanShow(1);
		req.setCanSale(1);// 上架
		req.setCurPage(1);
		req.setPageRows(100);
		YhdClient yhdClient = new YhdClient("http://openapi.yhd.com/app/api/rest/router", "10220014092800002434", "cf3ac66ba254159b36918837c1e04d97");
		GeneralProductsSearchResponse yihaodianResponse = yhdClient.excute(req, "2c2e9c1bd9ed1be0e55c454fd0221583");
		System.out.println(yihaodianResponse.getBody());

	}

}
