<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<li class="fly"><a href="#">委外管理</a>
	<ul>
		<li class="fly_top"><a href="#">设置</a></li>
		<li class="fly"><a href="#">委外商管理</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/outsourcing/outsourcingAction!goSaveOrUpdate.action','bg_02');">新增</a></li>
				<li><a href="#" onclick="loadContent('${vix}/outsourcing/outsourcingAction!goList.action','bg_02');">列表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/outsourcing/supplierStockChartAction!goList.action','bg_02');">供应商存货对照表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/outsourcing/inventoryPriceListAction!goList.action','bg_02');">供应商存货价格表</a></li>
			</ul></li>
		<li class="fly"><a href="#">委外期初</a>
			<ul>
				<li class="fly_top"><a href="#">期初材料出库单</a></li>
				<li><a href="#">红字期初材料出库单</a></li>
				<li class="fly_end"><a href="#">期初材料出库单列表</a></li>
			</ul></li>
		<li class="fly"><a href="#">委外订单</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/outsourcing/outsourcingOrderAction!goSaveOrUpdate.action','bg_02');">新增委外订单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/outsourcing/outsourcingOrderAction!goList.action','bg_02');">委外订单列表</a></li>
				<li class="fly_end"><a href="#">委外订单统计表</a></li>
			</ul></li>
		<li class="fly"><a href="#">委外到货</a>
			<ul>
				<li><a href="#">到货单</a></li>
				<li><a href="#">到货退回单</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/outsourcing/outsourcingArrivalAction!goList.action','bg_02');">到货单列表</a></li>
			</ul></li>
		<li class="fly"><a href="#">委外发票</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/outsourcing/outsourcingInvoiceAction!goList.action','bg_02');">委外发票列表</a></li>
				<li><a href="#">普通委外发票</a></li>
				<li><a href="#">专用委外发票</a></li>
				<li><a href="#">运费发票</a></li>
				<li><a href="#">红字专用委外发票</a></li>
				<li><a href="#">红字普通委外发票</a></li>
				<li class="fly_end"><a href="#">红字运费发票</a></li>
			</ul></li>
		<li class="fly"><a href="#">委外核销</a>
			<ul>
				<li class="fly_top"><a href="#">手工核销</a></li>
				<li><a href="#">委外核销单</a></li>
				<li class="fly_end"><a href="#">核销单列表</a></li>
			</ul></li>
		<li><a href="#" onclick="loadContent('${vix}/outsourcing/endCheckoutAction!goList.action','bg_02');">月末结账</a></li>
		<li><a href="#">报表</a></li>
	</ul></li>