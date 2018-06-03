<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<li class="fly"><a href="#" onclick="loadContent('${vix}/purchase/purchaseManagementAction!goList.action','bg_02');">采购管理</a>
	<ul>
		<li class="fly"><a href="#">设置</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/srm/tempAction!goProcedures.action','bg_02');">采购设置</a></li>
				<li class="fly"><a href="#" onclick="loadContent('${vix}/purchase/purchasingOptionsAction!goListContent.action','bg_02');">采购选项</a>
					<ul>
						<li class="fly_top"><a href="#">批号管理</a></li>
						<li><a href="#">序号管理</a></li>
						<li class="fly_end"><a href="#">条形码管理</a></li>
					</ul></li>
				<li class="fly"><a href="#" onclick="loadContent('${vix}/srm/tempAction!goBeginSetting.action','bg_02');">期初设置</a>
					<ul>
						<li class="fly_top"><a href="#">期初入库单</a></li>
						<li class="fly_end"><a href="#">期初发票</a></li>
					</ul></li>
				<li><a href="#" onclick="loadContent('${vix}/purchase/orderTypeAction!goList.action','bg_02');">采购单据类型</a></li>
				<li><a href="#" onclick="loadContent('${vix}/purchase/bizTypeAction!goList.action','bg_02');">采购业务类型</a></li>
				<li><a href="#">采购订单模版</a></li>
				<li class="fly"><a href="#">采购会计指令</a>
					<ul>
						<li class="fly_top"><a href="#">采购入库指令</a></li>
						<li><a href="#">采购发票指令</a></li>
						<li class="fly_end"><a href="#">采购结算指令</a></li>
					</ul></li>
			</ul></li>
		<li class="fly"><a href="#" onclick="loadContent('${vix}/purchase/purchasePlanAction!goList.action','bg_02');">采购计划</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/purchase/purchasePlanAction!goList.action','bg_02');">采购计划管理</a>
				<li><a href="#" onclick="loadContent('${vix}/purchase/purchaseApproveAction!goList.action','bg_02');">计划汇总</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/purchase/purchasePlanFinalizedAction!goList.action','bg_02');">计划定稿</a></li>
			</ul></li>
		<li icon="${vix}/common/img/pur_apply.png" class="fly"><a href="#" onclick="loadContent('${vix}/purchase/purchaseApplyAction!goList.action','bg_02');">采购申请</a>
			<ul>
				<li class="fly_top"><a href="#" id="qk_pur_apply_add" onclick="loadContent('${vix}/purchase/purchaseApplyAction!goSaveOrUpdate.action','bg_02');">新增</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/purchase/purchaseApplyAction!goList.action','bg_02');">列表</a></li>
			</ul></li>
		<li class="fly" icon="${vix}/common/img/pur_inquire.png"><a href="#" onclick="loadContent('${vix}/purchase/purchaseInquireAction!goList.action','bg_02');">采购询价</a>
			<ul>
				<li class="fly_top"><a href="#" id="qk_pur_inquire_add" onclick="loadContent('${vix}/purchase/purchaseInquireAction!goSaveOrUpdate.action','bg_02');">新增</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/purchase/purchaseInquireAction!goList.action','bg_02');">列表</a></li>
			</ul></li>
		<li class="fly"><a href="#" onclick="loadContent('${vix}/purchase/purchaseTenderAction!goList.action','bg_02');">招标管理</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/purchase/purchaseTenderAction!goSaveOrUpdate2.action','bg_02');">创建普通招标</a></li>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/purchase/purchaseTenderAction!goSaveOrUpdate.action','bg_02');">创建项目包招标</a></li>
				<li><a href="#" onclick="loadContent('${vix}/purchase/invitingTenderAction!goList.action','bg_02');">发布招标邀请</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/purchase/quoteMonitorAction!goList.action','bg_02');">供应商实时报价监控</a></li>
			</ul></li>
		<li icon="${vix}/common/img/pur_order.png" class="fly"><a href="#" onclick="loadContent('${vix}/purchase/purchaseOrderAction!goList.action','bg_02');">采购订单</a>
			<ul>
				<li class="fly_top"><a href="#" id="qk_pur_order_add" onclick="loadContent('${vix}/purchase/purchaseOrderAction!goSaveOrUpdate.action','bg_02');">新增</a></li>
				<li icon="${vix}/common/img/pur_order.png"><a href="#" onclick="loadContent('${vix}/purchase/purchaseOrderAction!goList.action','bg_02');">列表</a></li>
				<li><a href="#">计划批量转单</a></li>
				<li><a href="#">请购比价转单</a></li>
				<li><a href="#">执行统计表</a></li>
				<li class="fly_end"><a href="#">预警</a></li>
			</ul></li>
		<li class="fly"><a href="#" onclick="loadContent('${vix}/srm/tempAction!goApprovalList.action','bg_02');">采购审批</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/purchase/purchaseRequAppAction!goList.action','bg_02');">采购申请管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/purchase/purchaseOrderAppAction!goList.action','bg_02');">采购订单管理</a></li>
			</ul></li>
		<li class="fly"><a href="#" onclick="loadContent('${vix}/purchase/contractManagementAction!goList.action','bg_02');">合同管理</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/contract/contractInquiryAction!goSalesContract.action','bg_02');">新增</a></li>
				<li><a href="#">审批</a></li>
				<li><a href="#" onclick="loadContent('${vix}/contract/contractInquiryAction!goSingleList.action','bg_02');">列表</a></li>
				<li><a href="#">合同付款</a></li>
				<li class="fly_end"><a href="#">模版</a></li>
			</ul></li>
		<li icon="${vix}/common/img/pur_arrival.png" class="fly"><a href="#" onclick="loadContent('${vix}/purchase/purchaseArrivalAction!goList.action','bg_02');">到货管理</a>
			<ul>
				<li class="fly_top"><a href="#" id="qk_pur_arrival_add" onclick="loadContent('${vix}/purchase/purchaseArrivalAction!goSaveOrUpdate.action','bg_02');">新增</a></li>
				<li><a href="#" onclick="loadContent('${vix}/purchase/purchaseArrivalAction!goList.action','bg_02');">列表</a></li>
				<li class="fly_end"><a href="#">到货退回单</a></li>
			</ul></li>
		<li icon="${vix}/common/img/pur_inbound.png" class="fly"><a href="#" onclick="loadContent('${vix}/purchase/purchaseInboundAction!goList.action','bg_02');">采购入库</a>
			<ul>
				<li class="fly_top"><a href="#" id="qk_pur_inbound_add" onclick="loadContent('${vix}/purchase/purchaseInboundAction!goSaveOrUpdate.action','bg_02');">新增</a></li>
				<li><a href="#" onclick="loadContent('${vix}/purchase/purchaseInboundAction!goList.action','bg_02');">列表</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/inventory/inboundWarehouseAction!goScarletLetterInbound.action','bg_02');">红字入库单</a></li>
			</ul></li>
		<li icon="${vix}/common/img/pur_return.png" class="fly"><a href="#" onclick="loadContent('${vix}/purchase/purchaseReturnAction!goList.action','bg_02');">采购退货</a>
			<ul>
				<li class="fly_top"><a href="#" id="qk_pur_return_add" onclick="loadContent('${vix}/purchase/purchaseReturnAction!goSaveOrUpdate.action','bg_02');">新增</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/purchase/purchaseReturnAction!goList.action','bg_02');">列表</a></li>
			</ul></li>
		<li><a href="#" onclick="loadContent('${vix}/srm/tempAction!goImportList.action','bg_02');">进口管理</a></li>
		<li class="fly"><a href="#">发票管理</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/purchase/purchaseInvoiceAction!goList.action','bg_02');">采购发票列表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/purchase/purchaseInvoiceAction!goSaveOrUpdate.action','bg_02');">普通采购发票</a></li>
				<li><a href="#" onclick="loadContent('${vix}/purchase/freightInvoiceAction!goSaveOrUpdate.action','bg_02');">运费发票</a></li>
				<li><a href="#">红字专用采购发票</a></li>
				<li><a href="#">红字普通采购发票</a></li>
				<li><a href="#">红字运费采购发票</a></li>
				<li class="fly_end"><a href="#">专用采购发票</a></li>
			</ul></li>
		<li class="fly"><a href="#" onclick="loadContent('${vix}/purchase/purchasingSettlementAction!goList.action','bg_02');">采购结算</a>
			<ul>
				<li class="fly_top"><a href="#">自动结算</a></li>
				<li><a href="#">手工结算</a></li>
				<li><a href="#"> 费用折扣结算</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/purchase/purchasingSettlementAction!goList.action','bg_02');">结算单列表</a></li>
			</ul></li>
		<li><a href="#" onclick="loadContent('${vix}/inventory/standingBookAction!goList.action','bg_02');">现存量查询</a></li>
		<li><a href="#" onclick="loadContent('${vix}/purchase/checkoutAction!goList.action','bg_02');">月末结账</a></li>
		<li class="fly"><a href="/vix/content/scm/pm/rf.jsp">报表</a>
			<ul>
				<li class=""><a href="#" onclick="loadContent('${vix}/purchase/purchaseChartsAction!tjChartsBase.action','bg_02');">我的报表</a></li>
				<li class="fly"><a href="#">统计表</a>
					<ul>
						<li><a href="#">到货明细表</a></li>
						<li><a href="#">采购明细表</a></li>
						<li><a href="#">入库明细表</a></li>
						<li><a href="#">结算明细表</a></li>
						<li><a href="#">未完成业务明细表</a></li>
						<li><a href="#">费用明细表</a></li>
						<li><a href="#">增值税发票处理状态明细表</a></li>
						<li><a href="#">采购综合统计表</a></li>
						<li><a href="#">采购计划综合统计表</a></li>
					</ul></li>
				<li class="fly"><a href="#">采购账簿</a>
					<ul>
						<li><a href="#">在途货物余额表</a></li>
						<li><a href="#">暂估入库余额表</a></li>
					</ul></li>
				<li class="fly"><a href="#">采购分析</a>
					<ul>
						<li><a href="#">采购成本分析</a></li>
						<li><a href="#">采购类型结构分析</a></li>
						<li><a href="#">采购资金比重分析</a></li>
						<li><a href="#">采购费用分析</a></li>
						<li><a href="#">采购货龄综合分析</a></li>
					</ul></li>
			</ul></li>
	</ul></li>
