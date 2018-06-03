package com.vix.common.security.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 数据权限的 拦截方法
 * @author wangmingchen
 *
 */
public class DataResRowMethod extends BaseEntity {

    /** 元数据对象对应的 hqlPorvider的类的全称
     * 必须唯一
     * */
    private String hqlProvider;

    /** 拦截hqlprovider包含哪些方法 */
    private String methodList;

	public String getHqlProvider() {
		return hqlProvider;
	}

	public void setHqlProvider(String hqlProvider) {
		this.hqlProvider = hqlProvider;
	}

	public String getMethodList() {
		return methodList;
	}

	public void setMethodList(String methodList) {
		this.methodList = methodList;
	}

}
