package com.process.vreport.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.ProcessorDefinition;

import com.process.vreport.processor.NodeMonitorProcessor;

public class FlowTaskRouterBuilder extends RouteBuilder {

	private FlowTask flowTask;

	public FlowTaskRouterBuilder(FlowTask flowTask) {
		this.flowTask = flowTask;
	}

	@Override
	public void configure() throws Exception {
		final Node startNode = flowTask.getNodeMap().get(flowTask.getStartNodeId());
		Link link = flowTask.getLinkMap().get(flowTask.getNodeLink().get(startNode.getId()));
		if (link != null) {
			@SuppressWarnings("rawtypes")
			ProcessorDefinition def = from(startNode.getValue()).routeId(flowTask.getId());
			List<LinkTarget> linkTarget = link.getTo();
			if (link.getIsMul() != null && link.getIsMul().intValue() == 1) {
				def.multicast().parallelProcessing();
			}
			for (LinkTarget t : linkTarget) {
				String targetId = t.getTargetId();
				evalNode(def, link, t.getCondition(), this.flowTask.getNodeMap().get(targetId), startNode);
			}
		}
	}

	private void evalNode(@SuppressWarnings("rawtypes") ProcessorDefinition def, Link formLink, String condition, Node node, Node fromNode) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Link link = flowTask.getLinkMap().get(flowTask.getNodeLink().get(node.getId()));
		if (node.getType().equalsIgnoreCase("process")) {
			Class<?> c = Class.forName(node.getValue());
			NodeProcessor p = (NodeProcessor) c.newInstance();
			p.setFormLink(formLink);
			p.setNode(node);
			//后加
			p.setFromNode(fromNode);
			if (p.getFromNodeList() == null) {
				List<Node> fromNodeList = new ArrayList<Node>();
				fromNodeList.add(fromNode);
				p.setFromNodeList(fromNodeList);
			} else {
				List<Node> fromNodeList = p.getFromNodeList();
				fromNodeList.add(fromNode);
				p.setFromNodeList(fromNodeList);
			}
			//后加
			def.process(createNodeMonitor(node, formLink, fromNode)).process(p);
		}
		if (node.getType().equalsIgnoreCase("delay")) {
			def.process(createNodeMonitor(node, formLink, fromNode)).delay(Long.valueOf(node.getValue()));
		}
		if (link != null) {

			List<LinkTarget> linkTarget = link.getTo();
			if (link.getIsMul() != null && link.getIsMul().intValue() == 1) {//全部当做并行处理也可以
				String[] strArray = new String[linkTarget.size()];
				int i = 0;
				for (LinkTarget t : linkTarget) {
					String targetId = t.getTargetId();
					strArray[i++] = ("direct:" + targetId);
					evalNode(from("direct:" + targetId), link, t.getCondition(), this.flowTask.getNodeMap().get(targetId), node);
				}
				def.multicast().parallelProcessing().to(strArray);
			} else {//只有一个linktarget （也可以当做多个并行处理）
				for (LinkTarget t : linkTarget) {
					String targetId = t.getTargetId();
					evalNode(def, link, t.getCondition(), this.flowTask.getNodeMap().get(targetId), node);
				}
			}

		}
	}

	private NodeMonitorProcessor createNodeMonitor(Node node, Link formLink, Node fromNode) {
		NodeMonitorProcessor t = new NodeMonitorProcessor();
		t.setNode(node);
		t.setFormLink(formLink);
		t.setFromNode(fromNode);
		return t;
	}
}
