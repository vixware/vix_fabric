<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<li class="fly"><a href="#">存货核算</a>
	<ul>
		<li class="fly_top"><a href="#" onclick="loadContent('${vix}/inventoryAccounting/inventoryAccountingConfigAction!goConfig.action','bg_02');">初始设置</a></li>
		<li class="fly"><a href="#">日常业务</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/inventory/inboundWarehouseAction!goSaveOrUpdate.action','bg_02');">采购入库单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/inboundWarehouseAction!goAddFinishedGoodsInbound.action','bg_02');">成品入库单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/inboundWarehouseAction!goList.action','bg_02');">其他入库单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/outBoundAction!goList.action','bg_02');">销售出库单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/outBoundAction!goList.action','bg_02');">材料出库单</a></li>
				<li><a href="#">假退料单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/outBoundAction!goList.action','bg_02');">其他出库单</a></li>
				<li><a href="#">入库调整单</a></li>
				<li><a href="#">出库调整单</a></li>
				<li><a href="#">系统调整单</a></li>
				<li><a href="#">计划价调整单</a></li>
				<li class="fly_end"><a href="#">单据列表</a></li>
			</ul></li>
		<li class="fly"><a href="#">业务核算</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/inventoryAccounting/normalDocumentEntryAction!goList.action','bg_02');">正常单据记账</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventoryAccounting/goodsSentOutAction!goList.action','bg_02');">发出商品记账</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventoryAccounting/directSalesAction!goList.action','bg_02');">直运销售记账</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventoryAccounting/specialDocumentsAction!goList.action','bg_02');">特殊单据记账</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventoryAccounting/restoringAccountingAction!goList.action','bg_02');">恢复记账</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventoryAccounting/estimatedCostAction!goList.action','bg_02');">暂估成本录入</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventoryAccounting/costSettlementAction!goList.action','bg_02');">结算成本处理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventoryAccounting/finishedProductCostAllocationAction!goList.action','bg_02');">产成品成本分配</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventoryAccounting/averageUnitPriceAction!goList.action','bg_02');">平均单价计算</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventoryAccounting/differenceRateCalculationAction!goList.action','bg_02');">差异率计算</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventoryAccounting/finalProcessingAction!goList.action','bg_02');">期末处理</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/inventoryAccounting/monthEndCloseAction!goList.action','bg_02');">月末结账</a></li>
			</ul></li>
		<li class="fly"><a href="#">财务核算</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/inventoryAccounting/createVoucherAction!goList.action','bg_02');">生成凭证</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventoryAccounting/documentListAction!goList.action','bg_02');">凭证列表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventoryAccounting/withTheGeneralLedgerReconciliationAction!goList.action','bg_02');">与总账对账</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/inventoryAccounting/commodityIsReconciledWithGeneralLedgerAction!goList.action','bg_02');">发出商品与总账对账</a></li>
			</ul></li>
		<li class="fly"><a href="#">跌价准备</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/inventoryAccounting/provisionSetAction!goConfig.action','bg_02');">跌价准备设置</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventoryAccounting/provisionPeriodAction!goConfig.action','bg_02');">跌价准备期初</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventoryAccounting/initialAllowanceListAction!goConfig.action','bg_02');">期初跌价准备列表</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/inventoryAccounting/provisionListAction!goConfig.action','bg_02');">跌价准备列表</a></li>
			</ul></li>
		<li class="fly_end"><a href="#">报表</a></li>
	</ul></li>