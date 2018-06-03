package com.process.vreport.model;

import java.io.Serializable;
import java.util.Date;

import com.e6soft.core.model.Model;

//E_VFORM_NODE_MONITOR
public class NodeMonitor implements Serializable, Model<String> {

	private static final long serialVersionUID = 1L;

	//ID
	private String id;

	//ROUTE_ID
	private String routeId;

	//EXCHANGE_ID
	private String exchangeId;

	//TIME
	private Date time;

	//FORM_NODE_ID
	private String formNodeId;

	//NODE_ID
	private String nodeId;

	//LINK_ID
	private String linkId;

	//STATUS
	private Integer status;

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(String exchangeId) {
		this.exchangeId = exchangeId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getFormNodeId() {
		return formNodeId;
	}

	public void setFormNodeId(String formNodeId) {
		this.formNodeId = formNodeId;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

}
