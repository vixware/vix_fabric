package com.process.vreport.model;

import java.util.List;

import org.apache.camel.Processor;

public abstract class NodeProcessor implements Processor {

	protected Node node;
	protected Link formLink;
	protected Node fromNode;
	protected List<Node> fromNodeList;

	public Node getFromNode() {
		return fromNode;
	}

	public void setFromNode(Node fromNode) {
		this.fromNode = fromNode;
	}

	public void setFormLink(Link formLink) {
		this.formLink = formLink;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public Link getFormLink() {
		return formLink;
	}

	public Node getNode() {
		return node;
	}

	public List<Node> getFromNodeList() {
		return fromNodeList;
	}

	public void setFromNodeList(List<Node> fromNodeList) {
		this.fromNodeList = fromNodeList;
	}

}
