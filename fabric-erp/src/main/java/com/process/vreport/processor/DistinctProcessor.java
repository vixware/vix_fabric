package com.process.vreport.processor;

import java.util.Date;

import org.apache.camel.Exchange;

import com.e6soft.core.util.ApplicationContextUtil;
import com.process.vreport.dao.NodeMonitorDao;
import com.process.vreport.model.NodeMonitor;
import com.process.vreport.model.NodeProcessor;
import com.vix.common.id.VixUUID;

/**
 * 排重 com.process.vreport.processor.DistinctProcessor
 *
 * @author bjitzhang
 *
 * @date 2015年5月27日
 */
public class DistinctProcessor extends NodeProcessor {

	@Override
	public void process(Exchange exchange) throws Exception {
		NodeMonitorDao nodeMonitorDao = ApplicationContextUtil.getContext().getBean(NodeMonitorDao.class);
		String routeId = exchange.getFromRouteId();
		System.out.println("排重" + this.getNode().getText());
		NodeMonitor nM = new NodeMonitor();
		nM.setId(VixUUID.getUUID());
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
