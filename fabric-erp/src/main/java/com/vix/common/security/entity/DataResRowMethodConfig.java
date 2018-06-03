package com.vix.common.security.entity;

import com.vix.common.meta.entity.BaseMetaData;
import com.vix.common.share.entity.BaseEntity;

/**
 * 数据权限的 拦截方法配置
 * @author wangmingchen
 *
 */
public class DataResRowMethodConfig extends BaseEntity {

	 /** 元数据对象 */
    private BaseMetaData baseMetaData;
   
    /** 方法配置 */
    private DataResRowMethod dataResRowMethod;
    
    /** 元数据对象对应的 hqlPorvider的类的全称
     * 必须唯一
     * */
    //private String hqlProvider;

    /** 拦截hqlprovider包含哪些方法 */
    //private String methodList;

	public BaseMetaData getBaseMetaData() {
		return baseMetaData;
	}

	public void setBaseMetaData(BaseMetaData baseMetaData) {
		this.baseMetaData = baseMetaData;
	}

	public DataResRowMethod getDataResRowMethod() {
		return dataResRowMethod;
	}

	public void setDataResRowMethod(DataResRowMethod dataResRowMethod) {
		this.dataResRowMethod = dataResRowMethod;
	}

}
