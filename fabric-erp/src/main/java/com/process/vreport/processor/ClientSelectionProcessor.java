package com.process.vreport.processor;

import java.util.Date;

import org.apache.camel.Exchange;

import com.e6soft.core.util.ApplicationContextUtil;
import com.process.vreport.dao.NodeMonitorDao;
import com.process.vreport.model.NodeMonitor;
import com.process.vreport.model.NodeProcessor;
import com.vix.common.id.VixUUID;

/**
 * 
 * com.process.vreport.processor.ClientSelectionProcessor
 *
 * @author bjitzhang
 *
 * @date 2015年5月27日
 */
public class ClientSelectionProcessor extends NodeProcessor {

	@Override
	public void process(Exchange exchange) throws Exception {
		NodeMonitorDao nodeMonitorDao = ApplicationContextUtil.getContext().getBean(NodeMonitorDao.class);
		String routeId = exchange.getFromRouteId();
		String nodeText = this.getNode().getText();
		System.out.println("客户筛选" + nodeText);
		NodeMonitor nM = new NodeMonitor();
		nM.setId(VixUUID.getUUID());
		nM.setRouteId(routeId);
		nM.setExchangeId(exchange.getExchangeId());
		nM.setFormNodeId(this.getFromNode().getId());
		nM.setNodeId(this.getNode().getId());
		nM.setLinkId(this.getFormLink().getId());
		nM.setStatus(1);
		nM.setTime(new Date());
		nodeMonitorDao.update(nM);
	}
}
