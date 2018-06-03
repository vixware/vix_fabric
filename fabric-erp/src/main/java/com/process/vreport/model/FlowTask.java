package com.process.vreport.model;

import java.util.Map;

public class FlowTask {

	private String id;
	
	private Map<String,Node> nodeMap;
	
	private Map<String,Link> linkMap;
	
	private Map<String,String> nodeLink;
	
	private String startNodeId;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Map<String, Link> getLinkMap() {
		return linkMap;
	}
	
	public Map<String, Node> getNodeMap() {
		return nodeMap;
	}
	
	public void setLinkMap(Map<String, Link> linkMap) {
		this.linkMap = linkMap;
	}
	
	public void setNodeMap(Map<String, Node> nodeMap) {
		this.nodeMap = nodeMap;
	}
	
	public String getStartNodeId() {
		return startNodeId;
	}
	
	public void setStartNodeId(String startNodeId) {
		this.startNodeId = startNodeId;
	}
	
	public Map<String, String> getNodeLink() {
		return nodeLink;
	}
	
	public void setNodeLink(Map<String, String> nodeLink) {
		this.nodeLink = nodeLink;
	}
}
