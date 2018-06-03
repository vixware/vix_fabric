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
				<li><a href="#">销售管理</a></li>
				<li><a href="#">设置</a></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/common/salesAction!goDictionaryEdit.action');">字典管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<div id="c_head" class="drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>销售字典管理</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left">
			<div id="dic">
				<ul style="margin-left: 15px;">
					<hr style="margin-left: -10px;" />
					<p style="font-size: 14px; margin-left: -6px; color: #606060;">销售发货单</p>
					<li><span id="ibc" style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/sales/common/salesAction!goList.action');"> 发货单-业务类型 </span></li>
					<li><span id="ibc" style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/sales/common/salesAction!goBillTypeList.action');"> 发货单-单据类型 </span></li>
					<hr style="margin-left: -10px;" />
					<p style="font-size: 14px; margin-left: -6px; color: #606060;">销售退换货单</p>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/sales/common/salesAction!goReturnGoodsList.action');"> 退换货单-类型 </span></li>
					<hr style="margin-left: -10px;" />
					<p style="font-size: 14px; margin-left: -6px; color: #606060;">销售业绩</p>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/sales/common/salesAction!goSalesPerformanceEvaluationMethodList.action');"> 考评方式 </span></li>
					<%-- <hr style="margin-left: -10px;"/>
					<p style="font-size:14px;margin-left: -6px;color:#606060;">销售机会相关</p>
					<li><span style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/crm/base/saleChanceStatusAction!goList.action');">
						销售机会-状态
					</span></li> --%>
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