/**
 * 
 */
package com.vix.system.billTypeManagement.vo;

import java.util.ArrayList;
import java.util.List;

import com.vix.common.billtype.BillType;

/**
 * 将单据类型编码组装成list
 * 
 * @author zhanghaibing
 * 
 * @date 2013-10-25
 */
public class BillTypeCodeParameterList {

	public static List<BillTypeCodeParameter> getBillTypeCodeParameterList() {
		List<BillTypeCodeParameter> billTypeCodeParameterList = new ArrayList<BillTypeCodeParameter>();

		BillTypeCodeParameter billTypeCodeParameter = new BillTypeCodeParameter();
		billTypeCodeParameter.setCode(BillType.INV_INBOUND);
		billTypeCodeParameter.setName(BillType.INV_INBOUND);
		billTypeCodeParameterList.add(billTypeCodeParameter);

		BillTypeCodeParameter billTypeCodeParameter1 = new BillTypeCodeParameter();
		billTypeCodeParameter1.setCode(BillType.INV_OUTBOUND);
		billTypeCodeParameter1.setName(BillType.INV_OUTBOUND);
		billTypeCodeParameterList.add(billTypeCodeParameter1);
		BillTypeCodeParameter billTypeCodeParameter2 = new BillTypeCodeParameter();
		billTypeCodeParameter2.setCode(BillType.PUR_ORDER);
		billTypeCodeParameter2.setName(BillType.PUR_ORDER);
		billTypeCodeParameterList.add(billTypeCodeParameter2);

		return billTypeCodeParameterList;
	}
}
