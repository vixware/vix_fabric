<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<li class="fly"><a href="#">分销运营监控</a>
	<ul>
		<li class="fly"><a href="#">订单过程查询</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drpom/waitDeliveryAction!goList.action','bg_02');">待发货商品一览表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/plannedDeliveryAction!goList.action','bg_02');">计划发货商品一览表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/customerDeliveryStatisticsAction!goList.action','bg_02');">客户发货统计表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/salesListAction!goList.action','bg_02');">销售出库明细</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/commoditySalesSummaryAction!goList.action','bg_02');">商品销售汇总</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/summarizeTheSalesAction!goList.action','bg_02');">客户商品销售汇总</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/summarizesTheSalesAction!goList.action','bg_02');">客户商品类别销售汇总</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/brandMerchandiseSalesListAction!goList.action','bg_02');">商品品牌销售明细表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/brandMerchandiseSalesSummaryAction!goList.action','bg_02');">商品品牌销售汇总表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/dailySalesReportsAction!goList.action','bg_02');">日销售进度报表</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drpom/salesTaxSummaryTableAction!goList.action','bg_02');">销售税金汇总表</a></li>
			</ul></li>
		<li class="fly"><a href="#">退换货过程查询</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drpom/goodsReturnSummaryAction!goList.action','bg_02');">商品退货汇总</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/thyyhzbAction!goList.action','bg_02');">退货原因汇总表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/commodityExchangeSummaryTableAction!goList.action','bg_02');">商品换货汇总表</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drpom/commodityExchangeListAction!goList.action','bg_02');">商品换货明细表</a></li>
			</ul></li>
		<li class="fly"><a href="#">销售排名</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drpom/customerSalesRankingTableAction!goList.action','bg_02');">客户销售排名表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/salesmanSalesRankingAction!goList.action','bg_02');">业务员销售排名</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/departmentSalesRankingAction!goList.action','bg_02');">部门销售排名</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/areaSalesRankingAction!goList.action','bg_02');">地区销售排名</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drpom/productCategorySalesRankingsAction!goList.action','bg_02');">商品类别销售排名</a></li>
			</ul></li>
		<li class="fly"><a href="#">销售趋势查询</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drpom/customerSalesTrendsAction!goList.action','bg_02');">客户销售趋势表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/salesmanSalesTrendsAction!goList.action','bg_02');">业务员销售趋势表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/departmentSalesTrendsAction!goList.action','bg_02');">部门销售趋势表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/salesTrendAreaTableAction!goList.action','bg_02');">地区销售趋势表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/commoditySalesTrendsAction!goList.action','bg_02');">商品销售趋势表</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drpom/productCategorySalesTrendsAction!goList.action','bg_02');">商品类别销售趋势表</a></li>
			</ul></li>
		<li class="fly"><a href="#">代销商品销售查询</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drpom/sellLibraryListAction!goList.action','bg_02');">代销出库明细表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/consignmentCommoditySummaryTableAction!goList.action','bg_02');">代销商品汇总表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/consignmentCustomerSummaryTableAction!goList.action','bg_02');">代销客户汇总表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/consignmentMerchandiseLedgerAction!goList.action','bg_02');">代销商品明细账</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/productAssemblesSummaryTableAction!goList.action','bg_02');">商品拆装汇总表</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drpom/inventorySalesAnalysisAction!goList.action','bg_02');">库存销售分析表</a></li>
			</ul></li>
		<li class="fly"><a href="#">采购过程查询</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drpom/receivedGoodsListAction!goList.action','bg_02');">计划收货商品一览表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/warehouseOrderQuantityQueryAction!goList.action','bg_02');">仓库订货量查询</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/commodityPurchasingSummaryAction!goList.action','bg_02');">商品采购汇总</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/supplierProcurementSummaryAction!goList.action','bg_02');">供应商商品采购汇总</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/goodsReturnSummaryAction!goList.action','bg_02');">商品退货汇总</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/commodityPurchasingTrendsAction!goList.action','bg_02');">商品采购趋势</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/supplierSourcingTrendAction!goList.action','bg_02');">供应商采购趋势</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drpom/purchaseTaxSummaryTableAction!goList.action','bg_02');">采购税金汇总表</a></li>
			</ul></li>
		<li class="fly"><a href="#">应收应付查询</a>
			<ul>
				<li class="fly"><a href="#">应收业务查询</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drpom/orderCollectionTableAction!goList.action','bg_02');">订单回款表</a>
						<li><a href="#" onclick="loadContent('${vix}/drpom/shouldProceedsTableAction!goList.action','bg_02');">应收汇总表</a>
						<li><a href="#" onclick="loadContent('${vix}/drpom/scheduleAccountsReceivableAction!goList.action','bg_02');">应收明细表</a>
						<li><a href="#" onclick="loadContent('${vix}/drpom/accountsReceivableAgingScheduleAction!goList.action','bg_02');">应收账龄分析表</a>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drpom/salesmanPaymentSummaryTableAction!goList.action','bg_02');">业务员收款汇总表</a>
					</ul></li>
				<li class="fly"><a href="#">应付业务查询</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drpom/copeSummaryTableAction!goList.action','bg_02');">应付汇总表</a>
						<li><a href="#" onclick="loadContent('${vix}/drpom/copeWithListAction!goList.action','bg_02');">应付明细表</a>
						<li><a href="#" onclick="loadContent('${vix}/drpom/receivableAgingScheduleAction!goList.action','bg_02');">应收账龄分析表</a>
						<li><a href="#" onclick="loadContent('${vix}/drpom/accountListAction!goList.action','bg_02');">账户明细表</a>
						<li><a href="#" onclick="loadContent('${vix}/drpom/transactionsListAction!goList.action','bg_02');">往来款项明细表</a>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drpom/accountsPayableSummaryTableAction!goList.action','bg_02');">往来款项汇总表</a>
					</ul></li>
			</ul></li>
		<li class="fly"><a href="#">资金在途查询</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drpom/moneyTransitScheduleAction!goList.action','bg_02');">在途资金明细表</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drpom/transitFundAnalysisAction!goList.action','bg_02');">在途资金分析</a></li>
			</ul></li>
		<li class="fly"><a href="#">费用管理查询</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drpom/budgetAnalysisTableAction!goList.action','bg_02');">预算分析表</a></li>
				<li><a href="#" href="#" onclick="loadContent('${vix}/drpom/budgetSummaryAction!goList.action','bg_02');">预算汇总表</a></li>
				<li><a href="#" href="#" onclick="loadContent('${vix}/drpom/feeScheduleAction!goList.action','bg_02');">费用明细表</a></li>
				<li><a href="#" href="#" onclick="loadContent('${vix}/drpom/costSummaryAction!goList.action','bg_02');">费用汇总表</a></li>
				<li><a href="#" href="#" onclick="loadContent('${vix}/drpom/departmentCostSummaryAction!goList.action','bg_02');">部门费用汇总表</a></li>
				<li class="fly_end"><a href="#" href="#" onclick="loadContent('${vix}/drpom/personnelCostSummaryAction!goList.action','bg_02');">人员费用汇总表</a></li>
			</ul></li>
		<li class="fly"><a href="#">返点返利查询</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drpom/rebateSummaryAction!goList.action','bg_02');">返点汇总表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/rebateSummaryDetailAction!goList.action','bg_02');">返点明细表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/theRebateSummaryAction!goList.action','bg_02');">返款汇总表</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drpom/theRebateSummaryDetailAction!goList.action','bg_02');">返款明细表</a></li>
			</ul></li>
		<li class="fly"><a href="#">价格管理处理查询</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drpom/merchandiseDiscountDetailInquiryAction!goList.action','bg_02');">商品折扣明细查询</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/merchandiseDiscountInformationQueryAction!goList.action','bg_02');">商品折扣汇总查询</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/changeTrendPriceGoodsAction!goList.action','bg_02');">商品报价变动趋势</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/changeTrendCommodityPricesAction!goList.action','bg_02');">商品价格变动趋势</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drpom/commodityPriceSummaryAction!goList.action','bg_02');">商品价格指标汇总</a></li>
			</ul></li>
		<li class="fly"><a href="#">库存管理体系</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drpom/invoicingMerchandiseSummaryAction!goList.action','bg_02');">商品进销存汇总表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/merchandiseInventoryListAction!goList.action','bg_02');">库存商品明细表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/goodsCostBreakdownAction!goList.action','bg_02');">商品成本明细表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/commodityWarehouseDistributionTableAction!goList.action','bg_02');">商品仓库分布表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/goodsSummaryTableAction!goList.action','bg_02');">商品来源去向汇总表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/goodsOverdueTableAction!goList.action','bg_02');">商品超储短缺表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/commoditiesAction!goList.action','bg_02');">商品保质期预警表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/guaranteePeriodAction!goList.action','bg_02');">商品现存量查询</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/serialNumberQueryAction!goList.action','bg_02');">序列号查询</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/inventoryGoodsSummaryTableAction!goList.action','bg_02');">盘盈盘亏商品汇总表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/shelfLifeProductsAction!goList.action','bg_02');">商品保质期分析</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/storageLocationStorageAmountAction!goList.action','bg_02');">货位存放量</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/commodityStorageSpaceDistributionTableAction!goList.action','bg_02');">商品货位分布表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/commodityDisassemblyAnalysisTableAction!goList.action','bg_02');">商品拆装分析表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/commodityDetachingRotatingSummaryAction!goList.action','bg_02');">商品拆转汇总表</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drpom/inventorySalesAnalysisAction!goList.action','bg_02');">库存销售分析表</a></li>
			</ul></li>
		<li class="fly"><a href="#">价格处理过程查询</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drpom/merchandiseDiscountDetailAction!goList.action','bg_02');">商品折扣明细查询</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/merchandiseDiscountSummaryQueryAction!goList.action','bg_02');">商品折扣汇总查询</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/commodityTrajectoryAction!goList.action','bg_02');">商品报价变动趋势</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drpom/commodityPricesTrajectoryAction!goList.action','bg_02');">商品价格变动趋势</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drpom/commodityPriceIndexSummaryAction!goList.action','bg_02');">商品价格指标汇总</a></li>
			</ul></li>
		<li class="fly"><a href="#">营销中心查询</a>
			<ul>
				<li class="fly"><a href="#">库存分析</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drpom/marketingCenterSummaryInvoicingAction!goList.action','bg_02');">营销中心单品进销存汇总表</a>
						<li><a href="#" onclick="loadContent('${vix}/drpom/marketingCenterCategoriesGoodsInvoicingSummaryAction!goList.action','bg_02');">营销中心商品类别进销存汇总表</a>
						<li><a href="#" onclick="loadContent('${vix}/drpom/marketingCenterProductWarningQueryAction!goList.action','bg_02');">营销中心商品保质期预警查询</a>
						<li><a href="#" onclick="loadContent('${vix}/drpom/marketingCenterCategoriesGoodsWarningQueryAction!goList.action','bg_02');">营销中心商品类别保质期预警查询</a>
						<li><a href="#" onclick="loadContent('${vix}/drpom/marketingMindInventoryGoodsDistributionTableAction!goList.action','bg_02');">营销心中库存商品分布表</a>
						<li><a href="#" onclick="loadContent('${vix}/drpom/marketingCenterGoodsOverstockedShortageFormAction!goList.action','bg_02');">营销中心商品超储短缺表</a>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drpom/marketingCenterInventorySalesAnalysisAction!goList.action','bg_02');">营销中心库存销售分析表</a>
					</ul></li>
				<li class="fly"><a href="#">价格费用分享</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drpom/agencyCommoditySalesPriceTrendAction!goList.action','bg_02');">机构商品销售价格变动趋势表</a>
						<li><a href="#" onclick="loadContent('${vix}/drpom/branchesCommoditySalePriceTrendTableAction!goList.action','bg_02');">分支机构商品销售价格变动趋势表</a>
						<li><a href="#" onclick="loadContent('${vix}/drpom/agencyCostBudgetAnalysisTableAction!goList.action','bg_02');">机构费用预算分析表</a>
						<li><a href="#" onclick="loadContent('${vix}/drpom/branchesCostBudgetAnalysisTableAction!goList.action','bg_02');">分支机构费用预算分析表</a>
						<li><a href="#" onclick="loadContent('${vix}/drpom/organizationExpensesBudgetSummaryAction!goList.action','bg_02');">机构费用预算汇总表</a>
						<li><a href="#" onclick="loadContent('${vix}/drpom/branchOfficeExpensesBudgetSummaryAction!goList.action','bg_02');">分支机构费用预算汇总表</a>
						<li><a href="#" onclick="loadContent('${vix}/drpom/marketingCenterCostSummaryAction!goList.action','bg_02');">营销中心费用汇总表</a>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drpom/agencyCostSummaryAction!goList.action','bg_02');">机构费用汇总表</a>
					</ul></li>
				<li class="fly"><a href="#">计划分享</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drpom/mechanismPlanImplementationAction!goList.action','bg_02');">机构长期销售计划执行情况表</a>
					</ul></li>
			</ul></li>
	</ul></li>