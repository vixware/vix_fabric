package com.vix.system.config.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @ClassName: ConfigServer
 * @Description: TODO
 * @author wangmingchen
 * @date 2016年1月9日 下午5:03:34
 */
public class ServiceConfigServer extends BaseEntity {

	/** IP地址 
	 * http://127.0.0.1:8080
	 * */
	private String serverIpAndPort;

	/**
	 * BizOcConstant.OC_SERVICE_TNT_TYPE
	 */
	private String serviceType;
	
	/**
	 * 是否开启
	 */
	private String status;

	public String getServerIpAndPort() {
		return serverIpAndPort;
	}

	public void setServerIpAndPort(String serverIpAndPort) {
		this.serverIpAndPort = serverIpAndPort;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}
	
}
