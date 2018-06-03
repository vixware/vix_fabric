<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<li class="fly"><a href="#" onclick="loadContent('${vix}/business/businessAction!goList.action','bg_02');">网店管理</a>
	<ul>
		<li class="fly_top"><a href="#" onclick="loadContent('${vix}/business/shopOptionAction!goList.action','bg_02');">选项</a></li>
		<li><a href="#" onclick="loadContent('${vix}/business/onlineStoreSetAction!goList.action','bg_02');">网店设置</a></li>
		<li><a href="#" onclick="loadContent('${vix}/business/itemcatsDownloadAction!goList.action','bg_02');">类目管理</a>
		<li class="fly"><a href="#">商品管理</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/business/itemDownLoadAction!goList.action','bg_02');">商品列表</a>
				<li><a href="#" onclick="loadContent('${vix}/business/itemDownLoadAction!goList.action','bg_02');">商品创建</a>
				<li><a href="#" onclick="loadContent('${vix}/inventory/groupInventoryCurrentStockAction!goList.action','bg_02');">组合商品管理</a>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/business/itemDownLoadAction!goList.action','bg_02');">商品发布</a>
			</ul></li>
		<li class="fly"><a href="#">订单管理</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/business/orderProcessAction!goList.action','bg_02');">订单同步管理</a>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/business/outBoundProcessAction!goList.action','bg_02');">订单出库处理</a>
			</ul></li>
		<li class="fly"><a href="#">物流管理</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/business/deliveryAreaAction!goList.action','bg_02');">区域设置</a>
				<li><a href="#" onclick="loadContent('${vix}/business/expressFeeRulesAction!goList.action','bg_02');">规则设置</a>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/business/postageRecordsAction!goList.action','bg_02');">邮费记录</a>
			</ul></li>
		<li><a href="#" onclick="loadContent('${vix}/business/statisticalAnalysisAction!goList.action?flag=flag');">统计分析</a></li>
		<li class="fly_end"><a href="#" onclick="loadContent('${vix}/business/orderDownLoadLogAction!goList.action?flag=flag');">日志管理</a></li>
	</ul></li>