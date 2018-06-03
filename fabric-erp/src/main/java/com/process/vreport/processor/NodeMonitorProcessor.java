package com.process.vreport.processor;

import java.util.Date;

import org.apache.camel.Exchange;

import com.e6soft.core.util.ApplicationContextUtil;
import com.process.vreport.dao.NodeMonitorDao;
import com.process.vreport.model.NodeMonitor;
import com.process.vreport.model.NodeProcessor;
import com.vix.common.id.VixUUID;
import com.vix.crm.business.entity.NodesLog;

public class NodeMonitorProcessor extends NodeProcessor {

	@Override
	public void process(Exchange exchange) throws Exception {

		NodeMonitorDao nodeMonitorDao = ApplicationContextUtil.getContext().getBean(NodeMonitorDao.class);
		String routeId = exchange.getFromRouteId();
		String nodeText = this.getNode().getText();
		System.out.println("进入节点 【" + nodeText + "】");
		NodesLog nodesLog = new NodesLog();
		nodesLog.setId(VixUUID.getUUID());
		nodesLog.setCreateTime(new Date());
		nodesLog.setLogContent("进入节点 【" + nodeText + "】");
		nodeMonitorDao.save(nodesLog);
		NodeMonitor nM = nodeMonitorDao.getById(routeId);
		if (nM != null) {
			nM.setExchangeId(exchange.getExchangeId());
			nM.setFormNodeId(this.getFromNode().getId());
			nM.setNodeId(this.getNode().getId());
			nM.setLinkId(this.getFormLink().getId());
			nM.setStatus(1);
			nM.setTime(new Date());
			nodeMonitorDao.update(nM);
		} else {
			nM = new NodeMonitor();
			nM.setId(routeId);
			nM.setRouteId(routeId);
			nM.setExchangeId(exchange.getExchangeId());
			nM.setFormNodeId(this.getFromNode().getId());
			nM.setNodeId(this.getNode().getId());
			nM.setLinkId(this.getFormLink().getId());
			nM.setStatus(1);
			nM.setTime(new Date());
			nodeMonitorDao.save(nM);
		}
	}
}
