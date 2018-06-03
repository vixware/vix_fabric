<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">

function brandList(){
	$("#brandSub").css({"display":""});
	$("#dimensionSub").css({"display":"none"});
}

swichContent("essentialinformation");
function toSwithEmpTab(tabId){
	var test1 = $("#"+tabId);
	var currentClass = $("#"+tabId).attr("class");
	if(typeof currentClass!='undefined' && currentClass=='current'){
		return;
	}
	var employeeId = $("#employeeId").val();
	if(employeeId!=""){
		//能够切换
		var t1 = test1.parent().parent().find("li > a").each(function (){
			$(this).removeClass("current");
		});
		$("#"+tabId).addClass("current");
		swichContent(tabId);
	}else{
		//不能切换
		asyncbox.alert("请先保存职员的基本信息！","提示");
		return;
	}
}

function swichContent(tabId){
	var employeeId = $("#employeeId").val();
	var urlVal;
	if(tabId=="essentialinformation"){
		urlVal = '${vix}/hr/hrmgr/employeessAction!goSaveOrUpdateEss.action?id='+employeeId+"&parentId=${parentId}";
	}else if(tabId=="personalskills"){
		urlVal = '${vix}/hr/hrmgr/employeessAction!goSaveOrUpdatePer.action?id='+employeeId;
	}else if(tabId=="relationship"){
		urlVal = '${vix}/hr/hrmgr/employeessAction!goSaveOrUpdateRel.action?id='+employeeId;
	}else if(tabId=="workingsituation"){
		urlVal = '${vix}/hr/hrmgr/employeessAction!goSaveOrUpdateWork.action?id='+employeeId;
	}
	$.ajax({
		  url:urlVal,
		  cache: false,
		  success: function(html){
			  $("#order_table").html(html);
		  }
	});
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/hr_emp.png" alt="" />
					<s:text name="hr_humanr_resources" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/hrmgr/employeessAction!goList.action');">员工管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/hrmgr/employeessAction!goSaveOrUpdate.action');">后备人才管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<div class="order">
		<dl>
			<dt id="orderTieleFixd">
				<span class="no_line"> <a onclick="saveOrUpdateEss();"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#" onclick="loadContent('${vix}/hr/hrmgr/employeessAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
				</span> <strong><b><s:text name="新增职员" /> </b><i></i> </strong>
			</dt>

			<dd class="clearfix">
				<ul class="clearfix jd jd4">
					<li><a id="essentialinformation" href="javascript:void(0);" class="current" onclick="toSwithEmpTab('essentialinformation');">1 基本信息<span></span></a></li>
					<li><a id="personalskills" href="javascript:void(0);" onclick="toSwithEmpTab('personalskills');">2个人技能<span></span></a></li>
					<li><a id="relationship" href="javascript:void(0);" onclick="toSwithEmpTab('relationship');">3个人关系<span></span></a></li>
					<li><a id="workingsituation" href="javascript:void(0);" onclick="toSwithEmpTab('workingsituation');">4工作情况</a></li>
				</ul>
				<input type="hidden" id="employeeId" value="${id}" />
			</dd>
		</dl>
	</div>
	<div id="order_table" class=""></div>
</div>
