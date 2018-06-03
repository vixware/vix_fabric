<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<li><a href="#"><i class="fa fa-lg fa-fw fa-bar-chart-o"></i><span class="menu-item-parent">智能分析</span></a>
	<ul>
		<li><a href="#" id="AnalysisSrwopPageA" onclick="loadContent('AnalysisSrwopPageA','${nvix}/nvixnt/vixntPurchasingBehaviorAction!goList.action');">会员量分析</a></li>
		<li><a href="#" id="AnalysisSrwopPageB" onclick="loadContent('AnalysisSrwopPageB','${nvix}/nvixnt/vixntMembershipStructureAnalysisAction!goList.action');">客户分析</a></li>
		<li><a href="#" id="AnalysisSrwopPageC" onclick="loadContent('AnalysisSrwopPageC','${nvix}/nvixnt/vixntSalesAnalysisAction!goList.action');">会员消费分析</a></li>
		<li><a href="#" id="AnalysisSrwopPageD" onclick="loadContent('AnalysisSrwopPageD','${nvix}/nvixnt/vixntCategoryAnalysisAction!goList.action');">会员画像</a>
	</ul>
</li>