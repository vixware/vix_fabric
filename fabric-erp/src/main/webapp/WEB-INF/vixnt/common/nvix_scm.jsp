<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<li><a id="" href="#" onclick="loadContent('purchase_purchaseStatistics','${nvix}/nvixnt/purchase/vixntPurchaseStatisticsAction!goSourceView.action');"><i class="fa fa-lg fa-fw fa-shopping-cart"></i> <span class="menu-item-parent">采购管理</span></a>
	<ul>
		<li><a id="" href="#"><i class="fa fa-fw icon-iconfont-config"></i>基础数据管理</a>
			<ul>
				<li><a href="#" id="" onclick="loadContent('purchase_bizType','${nvix}/nvixnt/purchase/vixntPurchaseSetUpAction!goList.action');">采购设置</a></li>
				<li><a href="#" id="" onclick="loadContent('purchase_bizType','${nvix}/nvixnt/purchase/vixntPurchaseBizTypeAction!goList.action');">业务类型管理</a></li>
				<li><a href="#" id="" onclick="loadContent('purchase_orderType','${nvix}/nvixnt/purchase/vixntPurchaseOrderTypeAction!goList.action');">单据类型管理</a></li>
				<li><a href="#" id="" onclick="loadContent('purchase_orderType','${nvix}/nvixnt/purchase/vixntPurchasePermissibleErrorAction!goList.action');">误差规则</a></li>
			</ul></li>
		<li><a href="#" id="purchase_purchasePlan" onclick="loadContent('purchase_purchasePlan','${nvix}/nvixnt/purchase/vixntPurchasePlanAction!goList.action');">采购计划 </a></li>
		<li><a href="#" id="purchase_purchaseApply" onclick="loadContent('purchase_purchaseApply','${nvix}/nvixnt/purchase/vixntPurchaseApplyAction!goList.action');">采购申请 </a></li>
		<li><a href="#" id="purchase_purchaseInquiry" onclick="loadContent('purchase_purchaseInquiry','${nvix}/nvixnt/purchase/vixntPurchaseInquireAction!goList.action');">采购询价 </a></li>
		<li><a href="#" id="purchase_purchaseorder" onclick="loadContent('purchase_purchaseorder','${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goList.action');">采购订单</a></li>
		<li><a href="#" id="purchase_purchaseArrival" onclick="loadContent('purchase_purchaseArrival','${nvix}/nvixnt/purchase/vixntPurchaseArrivalAction!goList.action');">采购到货</a></li>
		<li><a href="#" id="purchase_purchaseReturn" onclick="loadContent('purchase_purchaseReturn','${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!goList.action');">采购退货</a></li>
		<li><a id="" href="#"><i class="fa fa-lg fa-fw fa-bar-chart-o"></i><span class="menu-item-parent">采购智能分析</span></a>
	<ul>
		<li><a href="#" id="purchase_purchaseArrivalDetailed" onclick="loadContent('purchase_purchaseArrivalDetailed','${nvix}/nvixnt/purchase/vixntPurchaseDetailedListAction!goArrivalDetailedView.action');">到货明细</a></li>
		<li><a href="#" id="purchase_purchaseDetailed" onclick="loadContent('purchase_purchaseDetailed','${nvix}/nvixnt/purchase/vixntPurchaseDetailedListAction!goDetailedView.action');">采购明细</a></li>
		<li><a href="#" id="purchase_purchaseStorageDetailed" onclick="loadContent('purchase_purchaseStorageDetailed','${nvix}/nvixnt/purchase/vixntPurchaseDetailedListAction!goStorageDetailedView.action');">入库明细</a></li>
		<li><a href="#" id="purchase_purchaseCostAnalysis" onclick="loadContent('purchase_purchaseCostAnalysis','${nvix}/nvixnt/purchase/vixntPurchaseDetailedListAction!goCostAnalysisView.action');">采购成本分析</a></li>
		<li><a href="#" id="purchase_purchaseStructureAnalysis" onclick="loadContent('purchase_purchaseStructureAnalysis','${nvix}/nvixnt/purchase/vixntPurchaseStatisticsAction!goStructureAnalysisView.action');">类型结构分析</a></li>
		<li><a href="#" id="purchase_purchaseProportionAnalysis" onclick="loadContent('purchase_purchaseProportionAnalysis','${nvix}/nvixnt/purchase/vixntPurchaseDetailedListAction!goCostAnalysisView.action?page=2');">资金比重分析</a></li>
		<li><a href="#" id="purchase_purchaseStatistics" onclick="loadContent('purchase_purchaseStatistics','${nvix}/nvixnt/purchase/vixntPurchaseStatisticsAction!goStatisticsView.action');">采购仪表盘</a></li>
	</ul></li>
	</ul></li>
	
<li><a href="#"><i class="fa fa-lg fa-fw icon-iconfont-settlement"></i> <span class="menu-item-parent">销售管理</span></a>
	<ul>
		<li><a id="" href="#"><i class="fa fa-cogs"></i>基础设置</a>
			<ul>
				<li><a href="#" id="mid_saleBizType" onclick="loadContent('mid_saleBizType','${nvix}/nvixnt/nvixntSaleSetUpAction!goList.action');">销售设置</a></li>
				<li><a href="#" id="mid_saleBizType" onclick="loadContent('mid_saleBizType','${nvix}/nvixnt/nvixntSaleBizTypeAction!goList.action');">业务类型管理</a></li>
				<li><a href="#" id="mid_saleOrderType" onclick="loadContent('mid_saleOrderType','${nvix}/nvixnt/nvixntSaleOrderTypeAction!goList.action');">单据类型管理</a></li>
				</ul></li>
		<li><a id="mid_salePlan" href="#" onclick="">销售计划</a>
			<ul>
				<li><a id="mid_salePlan" href="#" onclick="loadContent('mid_salePlan','${nvix}/nvixnt/nvixntSalePlanAction!goList.action');">销售计划管理</a></li>
				<li><a id="mid_salePlan" href="#" onclick="loadContent('mid_salePlan','${nvix}/nvixnt/nvixntSalePlanAction!goCompList.action');">销售计划汇总</a></li>
				</ul></li>
		</li>
		<li><a id="mid_salesQuotation" href="#" onclick="loadContent('mid_salesQuotation','${nvix}/nvixnt/nvixSalesQuotationAction!goList.action');">报价单</a></li>
		<li><a id="mid_salesOrder" href="#" onclick="loadContent('mid_salesOrder','${nvix}/nvixnt/nvixSalesOrderAction!goList.action');">销售订单</a></li>
		<li><a id="mid_salesDeliveryOrder" href="#" onclick="loadContent('mid_salesDeliveryOrder','${nvix}/nvixnt/nvixntSalesOutBoundAction!goList.action');">销售出库单</a></li>
		<li><a id="mid_saleReturnForm" href="#" onclick="loadContent('mid_saleReturnForm','${nvix}/nvixnt/nvixntDeliveryDocumentAction!goList.action');">销售发货单</a></li>
		<li><a id="mid_saleReturnForm" href="#" onclick="loadContent('mid_saleReturnForm','${nvix}/nvixnt/nvixntSaleReturnFormAction!goList.action');">销售退货单</a></li>
		<li><a id="" href="#">销售返利</a>
			<ul>
				<li><a id="mid_returnRule" href="#" onclick="loadContent('mid_returnRule','${nvix}/nvixnt/nvixntReturnRuleAction!goList.action');">返利规则</a></li>
				<li><a id="mid_returnBill" href="#" onclick="loadContent('mid_returnBill','${nvix}/nvixnt/nvixntSaleReturnBillAction!goList.action');">返利单</a></li>
			</ul></li>
		<li><a id="" href="#">销售佣金</a>
			<ul>
				<li><a id="mid_returnRule" href="#">设置</a>
					<ul>
						<li><a id="mid_returnRule" href="#" onclick="loadContent('mid_returnRule','${nvix}/nvixnt/nvixntPersonnelCategoryAction!goList.action');">销售人员类别</a></li>
						<li><a id="mid_returnBill" href="#" onclick="loadContent('mid_returnBill','${nvix}/nvixnt/nvixntSaleAmountAction!goList.action');">销售定额</a></li>
						<li><a id="mid_returnBill" href="#" onclick="loadContent('mid_returnBill','${nvix}/nvixnt/nvixntSalesPerformanceEvaluationMethodAction!goList.action');">业绩考评方式</a></li>
						</ul></li>
				<li><a id="mid_returnBill" href="#" onclick="loadContent('mid_returnBill','${nvix}/nvixnt/nvixntCommissionPlanAction!goList.action');">佣金方案</a></li>
				<li><a id="mid_returnBill" href="#" onclick="loadContent('mid_returnBill','${nvix}/nvixnt/nvixntCommissionTermAction!goList.action');">佣金条件</a></li>
				<li><a id="mid_returnBill" href="#" onclick="loadContent('mid_returnBill','${nvix}/nvixnt/nvixntCommissionResultAction!goList.action');">佣金结算</a></li>
			</ul></li>
		<li><a id="" href="#"><i class="fa fa-lg fa-fw fa-bar-chart-o"></i> <span class="menu-item-parent">销售智能分析</span></a>
			<ul>
				<li><a href="#" id="purchase_purchaseStatisticsSale" onclick="loadContent('purchase_purchaseStatisticsSale','${nvix}/nvixnt/nvixntSalesStatisticsAction!goStatisticsSaleView.action');">销售仪表盘</a></li>
				<li><a href="#" id="sale_saleSaleDetailed" onclick="loadContent('sale_saleSaleDetailed','${nvix}/nvixnt/nvixntSalesStatisticsAction!goSaleDetailedView.action');">销售明细表</a></li>
				<li><a href="#" id="sale_saleDeliverDetailed" onclick="loadContent('sale_saleDeliverDetailed','${nvix}/nvixnt/nvixntSalesAnalysisAction!goDeliverDetailedView.action');">发货明细表</a></li>
				<li><a href="#" id="sale_saleSaleDetailedBook" onclick="loadContent('sale_saleSaleDetailedBook','${nvix}/nvixnt/nvixntSalesStatisticsAction!goSaleDetailedView.action?page=2');">销售明细账</a></li>
				<li><a href="#" id="sale_saleSaleGrow" onclick="loadContent('sale_saleSaleGrow','${nvix}/nvixnt/nvixntSalesAnalysisAction!goSaleGrowView.action');">销售增长分析</a></li>
				<li><a href="#" id="sale_saleSaleSendMap" onclick="loadContent('sale_saleSaleSendMap','${nvix}/nvixnt/nvixntSalesAnalysisAction!goSaleSendMapView.action');">货物流向分析</a></li>
				<li><a href="#" id="sale_saleSaleStructureAnalysis" onclick="loadContent('sale_saleSaleStructureAnalysis','${nvix}/nvixnt/nvixntSalesAnalysisAction!goSaleStructureAnalysisView.action');">销售结构分析</a></li>
				<li><a href="#" id="sale_saleSaleReturnGoods" onclick="loadContent('sale_saleSaleReturnGoods','${nvix}/nvixnt/nvixntSalesAnalysisAction!goSaleReturnGoodsView.action');">退货订单统计表</a></li>
				<li><a href="#" id="sale_saleSaleGoodsTop" onclick="loadContent('sale_saleSaleGoodsTop','${nvix}/nvixnt/nvixntSalesAnalysisAction!goSaleGoodsTopView.action');">产品销量排行</a></li>
				<li><a href="#" id="sale_saleCustomerBuyGoodsTop" onclick="loadContent('sale_saleCustomerBuyGoodsTop','${nvix}/nvixnt/nvixntSalesAnalysisAction!goCustomerBuyGoodsTopView.action');">客户购买排行</a></li>
				<li><a href="#" id="sale_saleSalesmanSellTop" onclick="loadContent('sale_saleSalesmanSellTop','${nvix}/nvixnt/nvixntSalesAnalysisAction!goSalesmanSellTopView.action');">销售人员业绩排行</a></li>
			</ul></li>	
	</ul></li>
	<li><a href="#"><i class="fa fa-lg fa-fw fa-truck"></i> <span class="menu-item-parent">配送管理</span></a>
	<ul>
		<li><a href="#" id="" onclick="loadContent('dtbcenter_vixntvehicle','${nvix}/nvixnt/vixntVehicleAction!goList.action');">车辆管理</a></li>
		<li><a href="#" id="" onclick="loadContent('dtbcenter_vixntLogistics','${nvix}/nvixnt/vixntLogisticsAction!goList.action');">订单管理</a></li>
	</ul></li>
	<!-- 渠道管理 -->
	<s:include value="nvix_channel.jsp"></s:include>
	<s:include value="nvix_chain.jsp"></s:include>
	<s:include value="nvix_inv.jsp"></s:include>