<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<li><a id="mid_customerAccount" href="#"><i class="fa fa-lg fa-fw fa-book"></i> <span class="menu-item-parent">合同管理</span></a>
	<ul>
		<li><a id="" href="#">初始设置</a>
			<ul>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/contract/vixntContractGroupTypeAction!goList.action');">所属合同组</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/contract/vixntContractTypeCombineAction!goList.action');">合同类型</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/contract/vixntContractNatureTypeAction!goList.action');">合同性质</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/contract/vixntViewIndustryTypeAction!goList.action');">所属行业</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/contract/vixntModeTypeAction!goList.action');">履行状态</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/contract/vixntEnableStageTypeAction!goList.action');">启用阶段</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/contract/vixntContractStageGroupTypeAction!goList.action');">合同阶段组</a></li>
			</ul></li>
			<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/contract/vixntLaborContractAction!goList.action');">劳动合同</a></li>
			<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/contract/vixntContractAction!goList.action');">合同列表</a></li>
			<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/contract/vixntExamineContractAction!goList.action');">合同审批</a></li>
			<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/contract/vixntContractWarningAction!goList.action');">合同预警</a></li>
	</ul>
</li>
