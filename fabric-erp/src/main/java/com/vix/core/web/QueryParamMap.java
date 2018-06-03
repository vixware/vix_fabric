package com.vix.core.web;

import java.util.HashMap;

public class QueryParamMap<K, V> extends HashMap<K, V> {
	private OrderBy orderBy;
	
	public OrderBy setOrderBy(String orderByColumn){
		this.orderBy = new OrderBy(orderByColumn);
		return this.orderBy;
	}
	
	public String getOrderBy(){
		if(this.orderBy==null)
			return null;
		return this.orderBy.orderByHql();
	}
	
	

	public class OrderBy{
		String orderBy;
		String orderByMethod = "asc";
		
		public OrderBy(String orderByColumn){
			this.orderBy = orderByColumn;
		}
		
		public void Asc(){
			this.orderByMethod = "asc";
		}
		
		public void Desc(){
			this.orderByMethod = "desc";
		}
		
		public String orderByHql(){
			return this.orderBy + " " + this.orderByMethod;
		}
	}
}

