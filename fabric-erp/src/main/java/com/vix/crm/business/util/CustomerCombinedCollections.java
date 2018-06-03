package com.vix.crm.business.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerCombinedCollections {

	private Map<String, List<CustomerCombinedModel>> customerCombinedModelMap = new HashMap<String, List<CustomerCombinedModel>>();

	public CustomerCombinedCollections addParam(CustomerCombinedModel value) {
		String name = value.getCustomerName() + "|" + value.getMobile() + "|" + value.getAddr();
		List<CustomerCombinedModel> list = customerCombinedModelMap.get(name);
		if (list == null) {
			list = new ArrayList<CustomerCombinedModel>();
			customerCombinedModelMap.put(name, list);
		}
		list.add(value);
		return this;
	}

	public Map<String, List<CustomerCombinedModel>> getCustomerCombinedModelMap() {
		return customerCombinedModelMap;
	}
}
