<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<li class="fly"><a href="#" onclick="loadContent('${vix}/drp/drpAction!goList.action','bg_02');">分销管理</a>
	<ul>
		<li class="fly"><a href="#">设置</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drp/distributionOptionsAction!goList.action','bg_02');">分销选项</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/distributionSystemRelationShipAction!goList.action','bg_02');">分销体系管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/drpWarehouseAction!goList.action','bg_02');">分销仓库管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/salwableProductAction!goList.action','bg_02');">分销产品管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/employeeManagementAction!goList.action','bg_02');">分销员工管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/accountManagementAction!goList.action?source=drp','bg_02');">分销账号管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/channelPriceAction!goList.action','bg_02');">分销价格管理</a></li>
				<%-- <li class="fly_end"><a href="#" onclick="loadContent('${vix}/drp/swaohAction!goList.action','bg_02');">分销点管理</a></li>
				<li class="fly"><a href="#">价格管理</a>
					<ul>
						<li class="fly_top"><a href="#">变价处理</a></li>
						<li><a href="#">地区价格管理</a></li>
						<li><a href="#">经销商价格管理</a></li>
						<li><a href="#">批量优惠管理</a></li>
						<li><a href="#">现付优惠管理</a></li>
						<li><a href="#">促销优惠管理</a></li>
						<li><a href="#">期货优惠管理</a></li>
						<li class="fly_end"><a href="#">降价保护管理</a></li>
					</ul></li> --%>
			</ul></li>
		<li class="fly"><a href="#">市场管理</a>
			<ul>
				<li class="fly"><a href="#">市场调查</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drp/retailPriceSurveyAction!goList.action','bg_02');">零售价格调查</a></li>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drp/channelPriceSurveyAction!goList.action','bg_02');">渠道价格调查</a></li>
					</ul></li>
				<li class="fly"><a href="#">渠道与经销商管理</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drp/competitionChannelAction!goSaveOrUpdate.action','bg_02');">新增</a></li>
						<li><a href="#" onclick="loadContent('${vix}/drp/competitionChannelAction!goList.action','bg_02');">列表</a></li>
						<li class="fly"><a href="#" onclick="loadContent('${vix}/drp/competitionChannelAction!goList.action','bg_02');">数据采集</a>
							<ul>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drp/branchOrganizationAction!goSaveOrUpdate.action','bg_02');">分支机构管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/drp/customerAndSalesDistributionAction!goSaveOrUpdate.action','bg_02');">客户与销售分布</a></li>
								<li><a href="#" onclick="loadContent('${vix}/drp/productInformationAction!goSaveOrUpdate.action','bg_02');">产品投放信息采集</a></li>
								<li><a href="#" onclick="loadContent('${vix}/drp/marketingActivityAction!goSaveOrUpdate.action','bg_02');">市场活动信息采集</a></li>
								<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drp/advertisingCampaignAction!goSaveOrUpdate.action','bg_02');">广告活动信息采集</a></li>
							</ul></li>
						<!-- <li><a href="#">评估</a></li>
						<li class="fly_end"><a href="#">统计分析</a></li> -->
					</ul></li>
				<li class="fly"><a href="#">竞争信息管理</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drp/competitorInformationAction!goList.action','bg_02');">竞争者管理</a></li>
						<li><a href="#" onclick="loadContent('${vix}/drp/competitiveProductsAction!goList.action','bg_02');">竞争产品管理</a></li>
						<li><a href="#" onclick="loadContent('${vix}/drp/newProductLaunchAction!goList.action','bg_02');">新产品投放</a></li>
						<li><a href="#" onclick="loadContent('${vix}/drp/marketResearchAction!goList.action','bg_02');">市场活动</a></li>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drp/advertisingManagementAction!goList.action','bg_02');">媒体广告</a></li>
					</ul></li>
				<li class="fly"><a href="#">媒体活动</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drp/printMediaManagementAction!goList.action','bg_02');">平面媒体管理</a></li>
						<li><a href="#" onclick="loadContent('${vix}/drp/tvMediaManagementAction!goList.action','bg_02');">电视媒体管理</a></li>
						<li><a href="#" onclick="loadContent('${vix}/drp/newMediaManagementAction!goList.action','bg_02');">新媒体管理</a></li>
						<li><a href="#" onclick="loadContent('${vix}/drp/mediaDeliveryPlanAction!goList.action','bg_02');">媒体组合投放计划</a></li>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drp/putInEffectAction!goList.action','bg_02');">投放效果跟踪</a></li>
					</ul></li>
			</ul></li>
		<li class="fly"><a href="#" onclick="loadContent('${vix}/drp/channelAction!goList.action','bg_02');">渠道管理</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drp/channelAction!goSaveOrUpdate.action','bg_02');">新增渠道</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/channelAction!goList.action','bg_02');">渠道列表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/channelPriceAction!goList.action','bg_02');">渠道价格</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/operationalIndicatorAction!goList.action','bg_02');">经营指标</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drp/operatingPlanAction!goList.action','bg_02');">经营计划</a></li>
			</ul></li>
		<li class="fly"><a href="#" onclick="loadContent('${vix}/drp/distributerManagementAction!goList.action','bg_02');">经销商管理</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drp/distributerManagementAction!goSaveOrUpdate.action','bg_02');">新建</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/distributerManagementAction!goList.action','bg_02');">列表</a></li>
				<!-- <li><a href="#">审批</a></li> -->
				<li><a href="#" onclick="loadContent('${vix}/drp/projectFilingManagementAction!goList.action','bg_02');">项目备案管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/trainingManagementAction!goList.action','bg_02');">培训管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/contract/protocolTempleteAction!goList.action','bg_02');">合同协议</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/channelPriceAction!goList.action','bg_02');">价格管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/tocargoPlanAction!goList.action','bg_02');">要货计划</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/demandOrderAction!goList.action','bg_02');">要货单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/distributororderManagementAction!goList.action','bg_02');">订单管理</a></li>
				<li class="fly"><a href="#">返点管理</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drp/refundRuleAction!goList.action','bg_02');">规则设定</a></li>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drp/rebatesManagementAction!goList.action','bg_02');">返款单</a></li>
					</ul></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/collectManagementAction!goList.action','bg_02');">收款管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/receivableAction!goList.action','bg_02');">应收管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/reimbursedExpensesAction!goList.action','bg_02');">代垫费用</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drp/bankstatementAction!goList.action','bg_02');">对账单</a></li>
			</ul></li>
		<li class="fly"><a href="#">门店管理</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/drp/storeInformationAction!goList.action?source=drp');">门店信息</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/storePersonnelinformationAction!goList.action?source=drp');">门店人员信息</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/storeEnquiryrequestAction!goList.action?source=drp');">门店要货请求</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/webPOSAction!goList.action?source=drp');">WebPOS</a></li>
				<%-- <li><a href="#"
											onclick="loadContent('${vix}/drp/storesIntegralAction!goList.action','bg_02');">门店积分管理</a></li> --%>
				<li><a href="#" onclick="loadContent('${vix}/drp/storeSalesrecordAction!goList.action?source=drp');">门店销售记录</a></li>
				<%-- <li><a href="#"
											onclick="loadContent('${vix}/drp/memberStorereturnAction!goList.action','bg_02');">会员门店退货</a>
										</li> --%>
				<li><a href="#" onclick="loadContent('${vix}/drp/storeReceivingrecordsAction!goList.action?source=drp');">门店收货记录</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/storeReturnRecordsAction!goList.action?source=drp');">门店退货记录</a></li>
				<li class="fly"><a href="#">政策下达与反馈</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drp/policyFeedbackAction!goList.action','bg_02');">政策下达</a></li>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drp/storesFeedbackAction!goList.action','bg_02');">政策反馈</a></li>
					</ul></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/storeVisitscoreAction!goList.action?source=drp');">门店拜访及评分</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/businessNeedsAction!goList.action?source=drp');">文商需求</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/storesTextDemandAction!goList.action?source=drp');">门店短信需求</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drp/productAcquisitionAction!goList.action?source=drp');">本品竞品采集</a></li>
			</ul></li>
		<li class="fly"><a href="#">经销商门户</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/contract/protocolTempleteAction!goList.action?source=drp');">合同协议管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/discountInformationAction!goList.action?source=drp');">折扣信息</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/tocargoPlanAction!goList.action?source=drp');">要货计划</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/distributororderManagementAction!goList.action?source=drp');">订单管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/apManagementAction!goList.action?source=drp');">应付管理</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drp/bankstatementAction!goList.action?source=drp');">对账单</a></li>
			</ul></li>
		<li class="fly"><a href="#">营销组合管理</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drp/marketingCampaignAction!goList.action','bg_02');">营销活动列表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/activityPlanAction!goList.action','bg_02');">营销活动计划</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/marketingExecutiveAction!goList.action','bg_02');">营销执行管理</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drp/marketingCampaignTrackingAction!goList.action','bg_02');">营销活动追踪</a></li>
				<%-- <li><a href="#" onclick="loadContent('${vix}/drp/statisticAnalysisAction!goList.action','bg_02');">统计分析</a> --%>
			</ul></li>
		<li class="fly"><a href="#">业务功能</a>
			<ul>
				<li class="fly"><a href="#">销售订单管理</a>
					<ul>
						<li><a href="#" onclick="loadContent('${vix}/drp/orderEntryAction!goList.action','bg_02');">订单录入</a></li>
						<li><a href="#" onclick="loadContent('${vix}/drp/ordersAuditAction!goList.action','bg_02');">订单审核</a></li>
						<li><a href="#" onclick="loadContent('${vix}/drp/arrearsAgreementAction!goList.action','bg_02');">欠款协议</a></li>
						<li><a href="#" onclick="loadContent('${vix}/drp/ordersShippedAction!goList.action','bg_02');">订单发货</a></li>
						<li><a href="#">多批次发货</a></li>
						<li><a href="#">面向多连锁店发货</a></li>
						<li><a href="#">面向多仓库发货</a></li>
						<li><a href="#">期货订单处理</a></li>
						<li class="fly_end"><a href="#">订单转采购</a></li>
					</ul></li>
				<li class="fly"><a href="#">代销商品管理</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drp/consignmentOrderAction!goList.action','bg_02');">代销订单</a></li>
						<li><a href="#" onclick="loadContent('${vix}/drp/consignmentOrderAction!goList.action','bg_02');">代销订单审核</a></li>
						<li><a href="#" onclick="loadContent('${vix}/drp/consignmentOrderDeliveryAction!goList.action','bg_02');">代销订单发货</a></li>
						<li><a href="#" onclick="loadContent('${vix}/drp/generationConsignmentSalesOrderAction!goList.action','bg_02');">生成代销销售订单</a></li>
						<li><a href="#" onclick="loadContent('${vix}/drp/monthlyMerchandiseConsignmentStatementAction!goList.action','bg_02');">每月代销商品结算单</a></li>
						<li><a href="#" onclick="loadContent('${vix}/drp/salesGoodsConsignmentWarehouseAction!goList.action','bg_02');">代销销售商品入库</a></li>
						<li><a href="#" onclick="loadContent('${vix}/drp/consignmentReturnsAction!goList.action','bg_02');">代销退货</a></li>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drp/affiliateSalesSettlementAction!goList.action','bg_02');">代销商销售结算</a></li>
					</ul></li>
				<li class="fly"><a href="#">日配销售管理</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drp/dayWithSalesOrderAction!goList.action','bg_02');">日配销售订单</a></li>
						<%-- <li><a href="#" onclick="loadContent('${vix}/drp/dayWithSalesOrderAction!goList.action','bg_02');">日配销售订单审核</a></li>
												<li><a href="#" onclick="loadContent('${vix}/drp/dayWithSalesOrderAction!goList.action','bg_02');">日配销售订单汇总</a></li> --%>
						<li><a href="#" onclick="loadContent('${vix}/drp/singleDaySalesDistributionAction!goList.action','bg_02');">日配销售配送单生成</a></li>
						<li><a href="#" onclick="loadContent('${vix}/drp/salesProductionPlanningAction!goList.action','bg_02');">日配销售生产计划单</a></li>
						<li><a href="#" onclick="loadContent('${vix}/drp/modifyOrdersReceivedNumberAction!goList.action','bg_02');">修改日配订单实收数</a></li>
						<li><a href="#" onclick="loadContent('${vix}/drp/dayWithSalesAction!goList.action','bg_02');">日配销售出库</a></li>
						<li><a href="#" onclick="loadContent('${vix}/drp/salesStorageAction!goList.action','bg_02');">日配销售入库</a></li>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drp/salesSettlementAction!goList.action','bg_02');">日配销售结算</a></li>
					</ul></li>
				<li class="fly"><a href="#">安装服务管理</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drp/installationAction!goList.action','bg_02');">安装管理</a></li>
						<li><a href="#" onclick="loadContent('${vix}/drp/maintenanceAction!goList.action','bg_02');">维修管理</a></li>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drp/sparePartsAction!goList.action','bg_02');">配件管理</a></li>
					</ul></li>
				<!-- <li class="fly"><a href="#">退货管理</a>
					<ul>
						<li class="fly"><a href="#">退货单处理</a>
							<ul>
								<li><a href="#">质量召回退货</a></li>
								<li><a href="#">一般退货</a></li>
								<li><a href="#">代销退货</a></li>
								<li><a href="#">残次品退货</a></li>
								<li><a href="#">折价退货</a></li>
							</ul></li>
						<li><a href="#">退货单验收与审核</a></li>
						<li><a href="#">退货单终止</a></li>
						<li><a href="#">退货单结算</a></li>
						<li class="fly_end"><a href="#">退货单入库</a></li>
					</ul></li>
				<li class="fly"><a href="#">专卖店管理</a>
					<ul>
						<li class="fly_top"><a href="#">销售过程管理</a></li>
						<li><a href="#">销售采购管理</a></li>
						<li><a href="#">商品核算</a></li>
						<li><a href="#">独立核算店核算处理</a></li>
						<li><a href="#">信息交换</a></li>
						<li class="fly_end"><a href="#">专卖店报表</a></li>
					</ul></li>
				<li class="fly"><a href="#">销售计划管理</a>
					<ul>
						<li class="fly_top"><a href="#">客户计划</a></li>
						<li><a href="#">业务员计划</a></li>
						<li><a href="#">部门计划</a></li>
						<li><a href="#">公司计划</a></li>
						<li class="fly_end"><a href="#">计划执行控制</a></li>
					</ul></li>
				<li class="fly"><a href="#">采购过程管理</a>
					<ul>
						<li class="fly_top"><a href="#">采购订单</a></li>
						<li><a href="#">采购到货</a></li>
						<li><a href="#">采购退货</a></li>
						<li class="fly_end"><a href="#">采购计划</a></li>
					</ul></li> -->
				<!-- <li class="fly"><a href="#">库存管理</a>
					<ul>
						<li class="fly"><a href="#">入库管理</a>
							<ul>
								<li class="fly_top"><a href="#">出库单确认</a></li>
								<li><a href="#">出库验货后提货</a></li>
								<li><a href="#">代销出库验货后提货</a></li>
								<li><a href="#">采购确认后的入库</a></li>
								<li class="fly_end"><a href="#">中途损失的出入库处理</a></li>
							</ul></li>
						<li><a href="#">出库管理</a></li>
						<li class="fly"><a href="#">调拨管理</a>
							<ul>
								<li class="fly_top"><a href="#">日常调拨</a></li>
								<li class="fly_end"><a href="#">有价调拨</a></li>
							</ul></li>
						<li class="fly"><a href="#">经营用品借出管理</a>
							<ul>
								<li class="fly_top"><a href="#">借出出库单录入</a></li>
								<li><a href="#">借出出库单审核</a></li>
								<li><a href="#">作废借出出库单</a></li>
								<li><a href="#">借出商品出库验收</a></li>
								<li><a href="#">借出入库单录入</a></li>
								<li><a href="#">借出入库单审核</a></li>
								<li><a href="#">借出入库验收</a></li>
								<li class="fly_end"><a href="#">借出商品核算</a></li>
							</ul></li>
						<li class="fly"><a href="#">库存盘点处理</a>
							<ul>
								<li class="fly_top"><a href="#">盘点表生成</a></li>
								<li><a href="#">盘点数录入盘点单</a></li>
								<li><a href="#">盘点单确认</a></li>
								<li><a href="#">盘盈处理</a></li>
								<li class="fly_end"><a href="#">盘亏处理</a></li>
							</ul></li>
						<li class="fly"><a href="#">库存损益处理</a>
							<ul>
								<li class="fly_top"><a href="#">损益单录入</a></li>
								<li><a href="#">损益单审核</a></li>
								<li class="fly_end"><a href="#">损益单记账</a></li>
							</ul></li>
						<li class="fly"><a href="#">库存核算</a>
							<ul>
								<li class="fly_top"><a href="#">核算方式选择</a></li>
								<li class="fly_end"><a href="#">核算处理与商品账结算</a></li>
							</ul></li>
						<li class="fly"><a href="#">产成品入库处理</a>
							<ul>
								<li class="fly_top"><a href="#">产成品入库单录入</a></li>
								<li><a href="#">产成品入库单中止</a></li>
								<li><a href="#">产成品入库单审核</a></li>
								<li class="fly_end"><a href="#">产成品入库单作废</a></li>
							</ul></li>
					</ul></li> -->
				<!-- <li class="fly"><a href="#">运输管理</a>
					<ul>
						<li class="fly_top"><a href="#">运输路线与路径</a></li>
						<li><a href="#">运输商交易信息管理</a></li>
						<li><a href="#">运输商费用管理</a></li>
						<li><a href="#">运输状态跟踪管理</a></li>
						<li><a href="#">运输结算统计</a></li>
						<li class="fly"><a href="#">日配路线与人员管理</a>
							<ul>
								<li class="fly_top"><a href="#">日配车辆与客户关系维护</a></li>
								<li><a href="#">日配车辆日配信息归档</a></li>
								<li class="fly_end"><a href="#">日配车辆各种统计</a></li>
							</ul></li>
					</ul></li>
				<li class="fly"><a href="#">应收应付处理</a>
					<ul>
						<li class="fly_top"><a href="#">收款处理</a></li>
						<li><a href="#">冲应收处理</a></li>
						<li><a href="#">采购付款处理</a></li>
						<li><a href="#">采购退款处理</a></li>
						<li class="fly_end"><a href="#">应收计息处理</a></li>
					</ul></li>
				<li class="fly"><a href="#">费用管理</a>
					<ul>
						<li class="fly"><a href="#">营销中心费用预算</a>
							<ul>
								<li class="fly_top"><a href="#">营销中心年度费用预算编制</a></li>
								<li class="fly_end"><a href="#">审核年度费用预算</a></li>
							</ul></li>
						<li class="fly"><a href="#">分支机构费用预算</a>
							<ul>
								<li class="fly_top"><a href="#">分支机构年度费用预算</a></li>
								<li class="fly_end"><a href="#">审核分支机构年度费用预算</a></li>
							</ul></li>
						<li class="fly"><a href="#">日常费用预算管理</a>
							<ul>
								<li class="fly_top"><a href="#">每月费用拨付</a></li>
								<li><a href="#">费用使用申请单</a></li>
								<li><a href="#">费用使用报销</a></li>
								<li class="fly_end"><a href="#">每月费用使用状况统计</a></li>
							</ul></li>
					</ul></li>
				<li class="fly"><a href="#">结算规则管理</a>
					<ul>
						<li class="fly_top"><a href="#">近点管理</a></li>
						<li class="fly_end"><a href="#">返款管理</a></li>
					</ul></li>
				<li class="fly"><a href="#">期末核算</a>
					<ul>
						<li class="fly_top"><a href="#">商品核算</a></li>
						<li><a href="#">商品账结转</a></li>
						<li><a href="#">保管账结转</a></li>
						<li class="fly_end"><a href="#">在途商品账结转</a></li>
					</ul></li> -->
			</ul></li>
		<li class="fly"><a href="#">会员管理</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/drp/membershipInformationAction!goList.action?source=drp');">会员信息</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/membershipCardmanagementAction!goList.action?source=drp');">会员卡管理</a></li>
				<li class="fly"><a href="#">会员积分信息</a>
					<ul>
						<li><a href="#" onclick="loadContent('${vix}/drp/membershipPointsregistrationAction!goList.action?source=drp');">会员积分记录</a></li>
						<li><a href="#" onclick="loadContent('${vix}/drp/expiredIntegralAction!goList.action?source=drp');">过期积分记录</a></li>
					</ul></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/memberPointsforAction!goList.action?source=drp');">会员积分兑换</a></li>
				<!-- <li><a href="#">会员门店退货</a></li> -->
			</ul></li>
		<li class="fly"><a href="#">积分管理</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/drp/integralRulesSetAction!goList.action?source=drp');">积分规则设置</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/reDeemAction!goList.action?source=drp');">积分兑换</a></li>
				<li><a href="#">积分活动</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/integralManagementsubsidiaryAction!goList.action?source=drp');">积分管理明细</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/setRedeemGoodsAction!goList.action?source=drp');">设置可积分兑换商品</a></li>
				<!-- <li><a href="#">经销商积分兑换</a></li> -->
				<!-- <li class="fly_end"><a href="#">Portal积分领取</a></li> -->
			</ul></li>
		<li class="fly"><a href="#">客户服务与关怀</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/drp/customerFeedbackmanagementAction!goList.action?source=drp');">客户反馈管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/serviceCalendarmanagementAction!goCanlendar.action?source=drp');">服务日历管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/knowledgeBasemanagementAction!goList.action?source=drp');">知识库管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/customerSurveyAction!goList.action?source=drp');">客户调查管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/customerCareremindAction!goList.action?source=drp');">客户关怀提醒</a></li>
			</ul></li>
		<li><a href="#">统计分析</a></li>
		<li class="fly"><a href="#">商业智能分析与展现</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drp/customerConsumptionAnalysisAction!goList.action?source=drp');">客户消费分析</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/customerValueAnalysisAction!goList.action?source=drp');">客户价值分析</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/customerPreferenceAnalysisAction!goList.action?source=drp');">客户偏好分析</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/channelStoresAnalysisAction!goList.action?source=drp');">渠道门店分析</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/productSeriesAnalysisAction!goList.action?source=drp');">产品系列分析</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drp/businessAnalysisAction!goList.action?source=drp');">业务运营分析</a></li>
			</ul></li>
	</ul></li>
