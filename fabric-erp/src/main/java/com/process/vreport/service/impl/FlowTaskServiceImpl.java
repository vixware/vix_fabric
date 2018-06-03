package com.process.vreport.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.process.vreport.model.FlowTask;
import com.process.vreport.model.FlowTaskRouterBuilder;
import com.process.vreport.model.Link;
import com.process.vreport.model.Node;
import com.process.vreport.service.FlowTaskService;

@Service
public class FlowTaskServiceImpl implements FlowTaskService {

	@Autowired
	private CamelContext context;

	@Override
	public void startFlowTask(FlowTask flowTask) {
		RoutesBuilder builder = new FlowTaskRouterBuilder(flowTask);
		try {
			context.addRoutes(builder);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stopFLowTask(String id) {
		try {
			context.stopRoute(id);
			context.removeRoute(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ServiceStatus getFlowTaskStatus(String id) {
		return context.getRouteStatus(id);
	}

	@Override
	public FlowTask parseFlowTask(String src) {
		FlowTask flowTask = new FlowTask();
		Map<String, String> nodeLink = new HashMap<String, String>();
		flowTask.setNodeLink(nodeLink);
		JSONObject object = JSON.parseObject(src);
		flowTask.setId(object.getString("id"));
		JSONObject nodeObject = object.getJSONObject("node");
		Iterator<String> keySet = nodeObject.keySet().iterator();
		Map<String, Node> nodeMap = new HashMap<String, Node>();
		flowTask.setNodeMap(nodeMap);
		while (keySet.hasNext()) {
			String nodeId = keySet.next();
			Node node = nodeObject.getObject(nodeId, Node.class);
			if (node.getType().equalsIgnoreCase("start")) {
				flowTask.setStartNodeId(nodeId);
			}
			nodeMap.put(nodeId, node);
		}
		JSONObject linkObject = object.getJSONObject("link");
		keySet = linkObject.keySet().iterator();
		Map<String, Link> linkMap = new HashMap<String, Link>();
		flowTask.setLinkMap(linkMap);
		while (keySet.hasNext()) {
			String linkId = keySet.next();
			Link link = linkObject.getObject(linkId, Link.class);
			String fromId = link.getFrom();
			nodeLink.put(fromId, linkId);
			linkMap.put(linkId, link);
		}
		return flowTask;
	}

	@Override
	public FlowTask parseFlowTask(String id, String src) {
		FlowTask flowTask = new FlowTask();
		Map<String, String> nodeLink = new HashMap<String, String>();
		flowTask.setNodeLink(nodeLink);
		JSONObject object = JSON.parseObject(src);
		flowTask.setId(id);
		JSONObject nodeObject = object.getJSONObject("node");
		Iterator<String> keySet = nodeObject.keySet().iterator();
		Map<String, Node> nodeMap = new HashMap<String, Node>();
		flowTask.setNodeMap(nodeMap);
		while (keySet.hasNext()) {
			String nodeId = keySet.next();
			Node node = nodeObject.getObject(nodeId, Node.class);
			if (node.getType().equalsIgnoreCase("start")) {
				flowTask.setStartNodeId(nodeId);
			}
			nodeMap.put(nodeId, node);
		}
		JSONObject linkObject = object.getJSONObject("link");
		keySet = linkObject.keySet().iterator();
		Map<String, Link> linkMap = new HashMap<String, Link>();
		flowTask.setLinkMap(linkMap);
		while (keySet.hasNext()) {
			String linkId = keySet.next();
			Link link = linkObject.getObject(linkId, Link.class);
			String fromId = link.getFrom();
			nodeLink.put(fromId, linkId);
			linkMap.put(linkId, link);
		}
		return flowTask;
	}
}
