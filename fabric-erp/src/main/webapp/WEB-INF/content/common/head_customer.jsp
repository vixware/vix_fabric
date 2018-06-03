<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<li class="fly"><a href="#" onclick="loadContent('${vix}/crm/business/crmBusinessAction!goList.action','bg_02');">会员交互管理</a>
	<ul>
		<li class="fly_end"><a href="#" onclick="loadContent('${vix}/crm/business/membershipIntegrationAction!goList.action','bg_02');">会员整合管理</a></li>
		<li class="fly"><a href="#">会员管理</a>
			<ul>
				<li class="fly"><a href="#">设置</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/crm/member/memberLevelAction!goList.action','bg_02');">会员等级</a></li>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/crm/member/memberTagAction!goList.action','bg_02');">会员标签</a></li>
					</ul></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/business/memberShipAction!goList.action','bg_02');">会员列表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/business/memberCareAction!goList.action','bg_02');">会员关怀</a></li>
				<li class="fly"><a href="#">忠诚度管理</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/crm/member/memberLevelManagementAction!goList.action','bg_02');">会员等级管理</a></li>
						<li><a href="###" onclick="loadContent('${vix}/crm/member/customerBlackListAction!goList.action','bg_02');">黑名单管理</a></li>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drp/pointRecordAction!goList.action','bg_02');">会员积分管理</a></li>
					</ul></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/member/memberTagManagementAction!goList.action','bg_02');">会员标签视图</a></li>
			</ul></li>
		<li><a href="#" onclick="loadContent('${vix}/crm/business/membershipSubdivisionAction!goList.action','bg_02');">会员细分管理</a></li>
		<li class="fly"><a href="#">优惠券管理</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/crm/business/couponAction!goList.action','bg_02');">优惠券创建</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/business/dispenseCouponAction!goList.action','bg_02');">优惠券发放</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/business/customerCouponAction!goList.action','bg_02');">会员优惠券管理</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/crm/business/couponUseConditionAction!goList.action','bg_02');">优惠券使用汇总</a></li>
			</ul></li>
		<li class="fly"><a href="#">营销中心</a>
			<ul>
				<li class="fly"><a href="#">设置</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/crm/business/messageTemplateTypeSetAction!goMessageTemplateTypeList.action','bg_02');">短信模板类型设置</a></li>
						<li><a href="#" onclick="loadContent('${vix}/crm/business/businessSetAction!goMessageList.action','bg_02');">短信模板</a></li>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/crm/business/businessSetAction!goEmailList.action','bg_02');">邮件模板</a></li>
					</ul></li>
				<li class="fly"><a href="#">智能营销</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/crm/business/marketingProcessManagementAction!goList.action','bg_02');">营销流程管理</a></li>
						<li><a href="#" onclick="loadContent('${vix}/crm/business/marketingActivitiesAction!goList.action','bg_02');">营销活动</a></li>
						<li><a href="#" onclick="loadContent('${vix}/crm/business/marketingAutomationProcessAction!goList.action','bg_02');">营销自动化流程监控</a></li>
						<%-- <li><a href="#" onclick="loadContent('${vix}/crm/business/allMarketingProcessExecuteSummaryAction!goList.action','bg_02');">营销流程执行汇总</a></li> --%>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/crm/business/anyMarketingProcessExecuteSummaryAction!goList.action','bg_02');">营销流程执行汇总</a></li>
					</ul></li>
				<li class="fly"><a href="#">促销管理</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="">促销规则</a></li>
					</ul></li>
				<li class="fly"><a href="#">短信管理</a>
					<ul>
						<!-- <li class="fly_top"><a href="#">创建短信模板</a></li> -->
						<li><a href="#" onclick="loadContent('${vix}/crm/business/businessSetAction!goMessageList.action','bg_02');">短信模板管理</a></li>
						<li><a href="#">发送短信</a></li>
						<li class="fly_end"><a href="#">短信批次管理</a></li>
					</ul></li>
				<!-- <li class="fly"><a href="#">EDM管理</a>
					<ul>
						<li class="fly_top"><a href="#">创建EDM模板</a></li>
						<li><a href="#">EDM模板管理</a></li>
						<li><a href="#">发送EDM</a></li>
						<li><a href="#">EDM批次管理</a></li>
						<li class="fly_end"><a href="#">EDM素材管理</a></li>
					</ul></li>
				<li class="fly_end"><a href="#">微营销管理</a></li> -->
			</ul></li>
		<li class="fly"><a href="#">订单中心</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/crm/business/orderSupervisoryControlAction!goList.action','bg_02');">订单监控</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/business/orderExpeditingAction!goList.action','bg_02');">订单催付</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/business/orderCareAction!goList.action','bg_02');">订单关怀</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/business/sentLogAction!goList.action','bg_02');">发送记录</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/crm/business/abnormalAlarmAction!goList.action','bg_02');">异常告警</a></li>
			</ul></li>
		<li class="fly"><a href="#">客服中心</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/crm/business/orderManagementAction!goList.action','bg_02');">询单管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/business/changePostAndPriceAction!goList.action','bg_02');">改邮改价</a></li>
				<li class="fly"><a href="#">事务跟进</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/crm/business/followPaymentAction!goList.action','bg_02');">未付款跟进</a></li>
						<li><a href="#" onclick="loadContent('${vix}/crm/business/noDeliveryFollowUpAction!goList.action','bg_02');">未发货跟进</a></li>
						<li><a href="#" onclick="loadContent('${vix}/crm/business/logisticsFollowUpAction!goList.action','bg_02');">物流跟进</a></li>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/crm/business/followUpEvaluationAction!goList.action','bg_02');">评价跟进</a></li>
					</ul></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/business/myAffairsAction!goList.action','bg_02');">我的事务</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/crm/business/customServiceMonitoringAction!goList.action','bg_02');">客服监控</a></li>
			</ul></li>
		<li class="fly"><a href="#">商业智能与决策</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/crm/business/purchasingBehaviorAction!goList.action','bg_02');">购买行为分析</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/analyse/rfmAnalyseAction!showAnalyseData.action','bg_02');">RFM模型分析</a></li>
				<li class="fly"><a href="#">会员结构分析</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/crm/business/membershipStructureAnalysisAction!goList.action','bg_02');">会员层次分析</a></li>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/crm/business/salesAnalysisAction!goList.action','bg_02');">销售分析</a></li>
					</ul></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/crm/business/categoryAnalysisAction!goList.action','bg_02');">品类分析</a></li>
			</ul></li>
	</ul></li>