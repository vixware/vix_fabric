<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<li><a href="#"><i class="fa fa-lg fa-fw fa-globe"></i><span class="menu-item-parent">会员营销</span></a>
	<ul>
		<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntMembershipSubdivisionAction!goList.action');">会员细分管理</a></li>
		<li><a href="#">智能营销</a>
			<ul>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/nvixntPromotionRuleAction!goList.action');">促销活动</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntMarketingProcessManagementAction!goList.action');">营销流程管理</a></li>
				<%-- <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntMarketingActivitiesAction!goList.action');">营销活动</a> --%>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntMarketingAutomationProcessAction!goList.action');">营销流程监控</a>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntAllMarketingProcessExecuteSummaryAction!goList.action');">营销流程执行汇总</a>
			</ul></li>
		<li><a href="#">优惠券管理</a>
			<ul>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/nvixCouponManagementAction!goList.action');">优惠券方案设计</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/nvixCouponManagementAction!goCouponDetailList.action');">优惠券</a></li>
				<%-- <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntDispenseCouponAction!goList.action');">优惠券发放</a></li> --%>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntCouponUseConditionAction!goList.action');">优惠券使用汇总</a>
			</ul></li>
		<li><a href="#">短息管理</a>
			<ul>
				<li><a href="javascript:void(0);" onclick="loadContent('','${nvix}/nvixnt/messageApiSetAction!goList.action');">接口配置</a></li>
				<li><a href="javascript:void(0);" onclick="loadContent('','${nvix}/nvixnt/messageCostRulesAction!goList.action');">短信计费规则管理</a></li>
				<li><a href="javascript:void(0);" onclick="loadContent('','${nvix}/message/messageTemplateAction!goList.action');">短信模板配置</a></li>
				<li><a href="javascript:void(0);" onclick="loadContent('','${nvix}/message/messageSendRecordAction!goList.action');">短信发送记录</a></li>
			</ul></li>
		<li><a href="#" id="" onclick="">大转盘</a></li>
		<li><a href="#" id="" onclick=""><i class="fa fa-lg fa-fw fa-magic"></i>数据魔方</a></li>
	</ul></li>
	<li><a href="#"><i class="fa fa-lg fa-fw fa-bar-chart-o"></i><span class="menu-item-parent">智能分析</span></a>
	<ul>
		<li><a href="#" id="AnalysisSrwopPageA" onclick="loadContent('AnalysisSrwopPageA','${nvix}/nvixnt/vixntPurchasingBehaviorAction!goList.action');">会员量分析</a></li>
		<li><a href="#" id="AnalysisSrwopPageB" onclick="loadContent('AnalysisSrwopPageB','${nvix}/nvixnt/vixntMembershipStructureAnalysisAction!goList.action');">客户分析</a></li>
		<li><a href="#" id="AnalysisSrwopPageC" onclick="loadContent('AnalysisSrwopPageC','${nvix}/nvixnt/vixntSalesAnalysisAction!goList.action');">会员消费分析</a></li>
		<li><a href="#" id="AnalysisSrwopPageD" onclick="loadContent('AnalysisSrwopPageD','${nvix}/nvixnt/vixntCategoryAnalysisAction!goList.action');">会员画像</a>
	</ul>
</li>