<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<li class="fly"><a href="#" onclick="loadContent('${vix}/chain/chainAction!goList.action','bg_02');">连锁经营管理</a>
	<ul>
		<li class="fly"><a href="#" onclick="loadContent('${vix}/chain/chainConfigAction!goConfig.action','bg_02');">设置</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/chain/chainConfigAction!goConfig.action','bg_02');">参数配置</a></li>
				<li><a href="">提醒设置</a></li>
			</ul></li>
		<li class="fly"><a href="#" onclick="loadContent('${vix}/chain/storesManagelistsAction!goList.action','bg_02');">门店管理</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/drp/storeInformationAction!goList.action?source=chain','bg_02');">门店信息</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/storePersonnelinformationAction!goList.action?source=chain','bg_02');">门店人员信息</a>
				<li><a href="#" onclick="loadContent('${vix}/drp/accountManagementAction!goList.action?source=chain','bg_02');">门店账号管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/storeEnquiryrequestAction!goList.action?source=chain','bg_02');">门店要货请求</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/storeSalesrecordAction!goList.action?source=chain','bg_02');">门店销售记录</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/storeReceivingrecordsAction!goList.action?source=chain','bg_02');">门店收货记录</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/storeReturnRecordsAction!goList.action?source=chain','bg_02');">门店退货记录</a></li>
				<li class="fly"><a href="#">政策下达与反馈</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drp/policyFeedbackAction!goList.action','bg_02');">政策下达</a></li>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drp/storesFeedbackAction!goList.action','bg_02');">政策反馈</a></li>
					</ul></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/storeVisitscoreAction!goList.action','bg_02');">门店拜访及评分</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/businessNeedsAction!goList.action','bg_02');">文商需求</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/storesTextDemandAction!goList.action','bg_02');">门店短信需求</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/ridesAction!goList.action','bg_02');">游乐设施管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/common/vixLogAction!goList.action','bg_02');">操作日志</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drp/productAcquisitionAction!goList.action','bg_02');">本品竞品采集</a></li>
			</ul></li>
		<li class="fly"><a href="#">会员管理</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/drp/membershipInformationAction!goList.action','bg_02');">会员信息</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/membershipCardmanagementAction!goList.action','bg_02');">会员卡管理</a></li>
				<li class="fly"><a href="#">会员积分信息</a>
					<ul>
						<li><a href="#" onclick="loadContent('${vix}/drp/membershipPointsregistrationAction!goList.action','bg_02');">会员积分记录</a></li>
						<li><a href="#" onclick="loadContent('${vix}/drp/expiredIntegralAction!goList.action','bg_02');">过期积分记录</a></li>
					</ul></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/memberPointsforAction!goList.action','bg_02');">会员积分兑换</a></li>
				<li><a href="#">会员门店退货</a></li>
			</ul></li>
		<li><a href="#" onclick="loadContent('${vix}/drp/distributororderManagementAction!goList.action','bg_02');">订单管理</a></li>
		<li class="fly"><a href="#">积分管理</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/drp/integralRulesSetAction!goList.action','bg_02');">积分规则设置</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/reDeemAction!goList.action','bg_02');">积分兑换</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/setRedeemGoodsAction!goList.action?source=drp','bg_02');">设置可积分兑换商品</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/integralManagementsubsidiaryAction!goList.action','bg_02');">积分管理明细</a></li>
			</ul></li>
		<li class="fly"><a href="#">优惠券管理</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/chain/couponManagementAction!goList.action','bg_02');">优惠券创建</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/chain/couponSendAction!goList.action','bg_02');">优惠券发放</a></li>
			</ul></li>
		<li class="fly"><a href="#">百货与卖场经营管理</a>
			<ul>
				<li class="fly"><a href="#">卖场管理</a>
					<ul>
						<li class="fly"><a href="#">楼层区域管理</a>
							<ul>
								<li><a href="#">新增</a></li>
							</ul></li>
					</ul></li>
				<li><a href="#">专柜管理</a></li>
				<li class="fly"><a href="#">租户管理</a>
					<ul>
						<li><a href="#">商铺管理</a></li>
						<li><a href="#">租约管理</a></li>
						<li><a href="#">租户商品管理</a></li>
						<li><a href="#">广告位管理</a></li>
						<li><a href="#">结算维护</a></li>
					</ul></li>
				<li><a href="#">协议与合同管理</a></li>
				<li><a href="#">收款管理</a></li>
			</ul></li>
		<li><a href="#" onclick="loadContent('${vix}/drp/webPOSAction!goList.action','bg_02');">POS终端销售</a></li>
		<li><a href="#">采购管理</a></li>
		<li><a href="#">库存管理</a></li>
		<li><a href="#" onclick="loadContent('${vix}/chain/automaticOrderManagementAction!goList.action','bg_02');">自动订货管理</a></li>
		<li class="fly"><a href="#">促销活动管理</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drp/marketingCampaignAction!goList.action','bg_02');">营销活动列表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/activityPlanAction!goList.action','bg_02');">营销活动计划</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/marketingExecutiveAction!goList.action','bg_02');">营销执行管理</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drp/marketingCampaignTrackingAction!goList.action','bg_02');">营销活动追踪</a></li>
			</ul></li>
		<li class="fly"><a href="#">结算管理</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/chain/productViewAction!goList.action','bg_02');">商品视图</a></li>
				<li><a href="#" onclick="loadContent('${vix}/chain/suppliersViewAction!goList.action','bg_02');">供应商视图</a></li>
			</ul></li>
		<li><a href="#" onclick="loadContent('${vix}/inventory/salesReportAction!goList.action','bg_02');">门店销售统计</a></li>
		<li class="fly_end"><a href="#" onclick="loadContent('${vix}/chain/playgroundManagementStatisticsAction!goList.action','bg_02');">游乐场运营管理统计</a></li>
	</ul></li>

