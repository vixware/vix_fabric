<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">

$(function(){
	_add01_bindButtonClassEvent($('#detail_info_btn a'), 'over', 'active');

	$('#detail_info_btn a').first().trigger('click');
});

function nav_loadContentStep(id)
{
	var url;
	if(id=='gdxx')
	{
		url = "${vix}/eam/workOrdersAction!gdGdxx.action";
	}
	else if(id=='rwmx')
	{
		url = "${vix}/eam/workOrdersAction!gdRwmx.action";
	}
	else if(id=='zyry')
	{
		url = "${vix}/eam/workOrdersAction!gdZyry.action";
	}
	else if(id=='wlxx')
	{
		url = "${vix}/eam/workOrdersAction!gdWlxx.action";
	}
	else if(id=='aqxx')
	{
		url = "${vix}/eam/workOrdersAction!gdAqxx.action";
	}
	else if(id=='cbgs')
	{
		url = "${vix}/eam/workOrdersAction!gdCbgs.action";
	}
	
	if($('#woId').val()>0)
		url += "?id=" + $('#woId').val();

	$('#breadcrumb li a').removeClass('show');
	$('#'+id).addClass('show');

	$.ajax({
		url:url,
		cache:false,
		success:function(html){
			$("#navStepContent").html(html);
		}
	});
}
</script>

<input type="hidden" id="woId" name="woId" value="<s:property value="id" />" />

<div class="tab_submenu">
	<h2 class="detail_title">
		<ul id="detail_info_btn">
			<li><a id='gdxx' onclick="javascript:nav_loadContentStep('gdxx');">工单信息</a></li>
			<li><a id='rwmx' onclick="javascript:nav_loadContentStep('rwmx');" <s:if test="id==null || id==0"> style="display:none;"</s:if>>任务明细</a></li>
			<li><a id='zyry' onclick="javascript:nav_loadContentStep('zyry');" <s:if test="id==null || id==0"> style="display:none;"</s:if>>作业人员</a></li>
			<li><a id='wlxx' onclick="javascript:nav_loadContentStep('wlxx');" <s:if test="id==null || id==0"> style="display:none;"</s:if>>${vv:varView("vix_mdm_item")}信息</a></li>
			<!-- 		  
		  <li><a id='aqxx' onclick="javascript:nav_loadContentStep('aqxx');"<s:if test="id==null || id==0"> style="display:none;"</s:if>>安全信息</a></li>
		  <li><a id='cbgs' onclick="javascript:nav_loadContentStep('cbgs');"<s:if test="id==null || id==0"> style="display:none;"</s:if>>成本估算</a></li>
 -->
		</ul>
	</h2>
</div>
<div class="content" id="navStepContent"></div>

<script type="text/javascript"> 
<!--
//-->
</script>