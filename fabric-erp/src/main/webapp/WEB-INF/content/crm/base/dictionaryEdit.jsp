<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function loadDicContent(span,url){
	$("div[id='dic'] li").each(function(){
		$(this).attr("style","");
	});
	$(span).parent().attr("style","background-color:#d0d0d0;");
	$.ajax({
		  url:url,
		  cache: false,
		  success: function(html){
			  $("#dictionary").html(html);
			  bindSwitch();
		  }
	});
}
$("#ibc").click();
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
loadMenuContent('${vix}/crm/project/crmProjectAction!goMenuContent.action');

</script>
<style>
#dic ul {
	margin-right: 10px;
	padding-bottom: 10px;
	color: #000;
}

#dic ul li {
	margin-left: 15px;
	font-size: 12px;
	height: 18px;
	line-height: 25px;
}

#dic ul li a {
	color: #000;
}
</style>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/site.png" alt="" />供应链</a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#">设置</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/base/crmDictionaryAction!goDictionaryEdit.action');">字典管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<div id="c_head" class="drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>CRM字典管理</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left">
			<div id="dic">
				<ul style="margin-left: 15px;">
					<hr style="margin-left: -10px;" />
					<p style="font-size: 14px; margin-left: -6px; color: #606060;">客户与联系人相关</p>
					<li><span id="ibc" style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/base/customerSourceAction!goList.action');"> 客户-客户来源 </span></li>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/base/hotLevelAction!goList.action');"> 客户-热度等级 </span></li>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/base/industryAction!goList.action');"> 客户-行业 </span></li>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/base/relationshipClassAction!goList.action');"> 客户-关系等级 </span></li>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/base/staffScaleAction!goList.action');"> 客户-人员规模 </span></li>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/base/customerTypeAction!goList.action');"> 客户-客户种类 </span></li>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/base/customerStageAction!goList.action');"> 客户-客户阶段 </span></li>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/base/contactPersonTypeAction!goList.action');"> 客户-联系人类型 </span></li>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/base/memorialDayTypeAction!goList.action');"> 客户-纪念日类型 </span></li>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/base/credentialTypeAction!goList.action');"> 客户-证件类型 </span></li>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/base/crmContactTypeAction!goList.action');"> 联系人-类型 </span></li>
					<hr style="margin-left: -10px;" />
					<p style="font-size: 14px; margin-left: -6px; color: #606060;">销售机会相关</p>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/base/saleChanceStatusAction!goList.action');"> 销售机会-状态 </span></li>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/base/saleStageAction!goList.action');"> 销售机会-阶段 </span></li>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/base/saleStageAction!goSaleTypeList.action');"> 销售机会-类型 </span></li>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/base/saleActivityAction!goList.action');"> 销售活动-类型 </span></li>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/workbench/innerBulletinCategoryAction!goList.action');"> 内部公告分类 </span></li>
					<hr style="margin-left: -10px;" />
					<p style="font-size: 14px; margin-left: -6px; color: #606060;">客户服务</p>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/base/serviceModeAction!goList.action');"> 客服-服务方式 </span></li>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/base/serviceTypeAction!goList.action');"> 客服-服务类型 </span></li>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/base/customerServiceStatusAction!goList.action');"> 客服-客服记录状态 </span></li>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/base/emergencyLevelTypeAction!goList.action');"> 客服-紧急程度 </span></li>
					<hr style="margin-left: -10px;" />
					<p style="font-size: 14px; margin-left: -6px; color: #606060;">项目相关</p>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/base/collaboratorTypeAction!goList.action');"> 项目-协作方类型 </span></li>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/base/competitiveAction!goList.action');"> 项目-对手竞争力 </span></li>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/base/complaintTypeAction!goList.action');"> 项目-投诉类型 </span></li>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/base/consumeTimeAction!goList.action');"> 项目-花费时间 </span></li>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/base/dealResultAction!goList.action');"> 项目-处理结果 </span></li>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/base/projectCostTypeAction!goList.action');"> 项目-费用类型 </span></li>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/base/projectRequireImportantAction!goList.action');"> 项目-需求重要程度 </span></li>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/base/projectStageAction!goList.action');"> 项目-阶段 </span></li>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/base/projectSalePreviousStageAction!goList.action');"> 项目-售前阶段 </span></li>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/base/projectStatusAction!goList.action');"> 项目-状态 </span></li>
				</ul>
			</div>
		</div>
		<!-- left -->
		<div id="right">
			<div class="right_content" style="height: 620px;">
				<div id="dictionary"></div>
			</div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>