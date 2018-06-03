/**
 * 
 */
package com.vix.inventory;

import java.util.ArrayList;
import java.util.List;

import com.vix.inventory.config.entity.InvBizTypeParameter;

/**
 * @author zhanghaibing
 * 
 * @date 2013-8-1
 */
public class InventoryPropertyManager {

	public static List<InvBizTypeParameter> getInventoryProperty() {
		/** 库存单据类型/凭证参数 */
		List<InvBizTypeParameter> invBizTypeParameterList = new ArrayList<InvBizTypeParameter>();

		InvBizTypeParameter invBizTypeParameter = new InvBizTypeParameter();

		invBizTypeParameter.setParamKey("1");
		invBizTypeParameter.setParamValue("入库");

		invBizTypeParameterList.add(invBizTypeParameter);

		return invBizTypeParameterList;
	}

	public static void main(String args[]) {
		System.out.println(InventoryPropertyManager.getInventoryProperty().get(0).getParamKey());
		System.out.println(InventoryPropertyManager.getInventoryProperty().get(0).getParamValue());
	}
}
