package com.vix.core.config.server;

/**
 * @ClassName: VixServerConfig
 * @Description: 服务器配置
 * @author wangmingchen
 * @date 2015年3月22日 上午8:12:37
 */
public final class VixServerConfig {

	/** 是否是运营中心服务器 */
	private Boolean isMasterServer = false;
	/** 
	 * 如果是运营中心服务器， 则为其服务器地址，包括上下文的rootpath；
	 *  否则为空 */
	private String masterServerURL;
	
	/** 数据是否进行数据同步 */
	private Boolean isSyn = false;
	
	/** 服务器是否处于内网模式
	 * OC中如果处于内网模式，则进行服务购买时，会在保存订单时进行数据同步，否则会在回调中同步
	 *  */
	private Boolean isLanMode = true;

	public Boolean getIsMasterServer() {
		return isMasterServer;
	}

	public void setIsMasterServer(Boolean isMasterServer) {
		this.isMasterServer = isMasterServer;
	}

	public String getMasterServerURL() {
		return masterServerURL;
	}

	public void setMasterServerURL(String masterServerURL) {
		this.masterServerURL = masterServerURL;
	}

	public Boolean getIsSyn() {
		return isSyn;
	}

	public void setIsSyn(Boolean isSyn) {
		this.isSyn = isSyn;
	}

	public Boolean getIsLanMode() {
		return isLanMode;
	}

	public void setIsLanMode(Boolean isLanMode) {
		this.isLanMode = isLanMode;
	}
	
}
