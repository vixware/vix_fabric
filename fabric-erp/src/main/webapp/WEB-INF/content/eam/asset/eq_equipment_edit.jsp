<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$(function(){
	_add01_bindButtonClassEvent($('#detail_info_btn a'), 'over', 'active');

	$('#detail_info_btn a').first().trigger('click');
});


function eq_loadContentStep(id)
{
	var url;
	if(id=='jbxx')
	{
		url = "${vix}/eam/accountManageAction!eqJbxx.action";
	}
	else if(id=='jscs')
	{
		url = "${vix}/eam/accountManageAction!eqJscs.action";
	}
	else if(id=='bjxx')
	{
		url = "${vix}/eam/accountManageAction!eqBjxx.action";
	}
	else if(id=='jcxx')
	{
		url = "${vix}/eam/accountManageAction!eqJcxx.action";
	}
	else if(id=='bxxx')
	{
		url = "${vix}/eam/accountManageAction!eqBxxx.action";
	}
	else if(id=='sbwd')
	{
		url = "${vix}/eam/accountManageAction!eqSbwd.action";
	}
	
	if($('#eqId').val()>0)
		url += "?eqId=" + $('#eqId').val();
		
	$.ajax({
		url:url,
		cache:false,
		success:function(html){
			$("#eqStepContent").html(html);
		}
	});
}

</script>
<!-- top -->
<!-- head -->
<input type="hidden" name="eqId" id="eqId" value="<s:property value="id" />" />

<div class="tab_submenu">
	<h2 class="detail_title">
		<ul id="detail_info_btn">
			<li><a id='jbxx' onclick="eq_loadContentStep('jbxx');">基本信息</a></li>
			<!-- 
		  <li><a id='jscs' onclick="eq_loadContentStep('jscs');"<s:if test="id==null || id==0"> style="display:none;"</s:if>>技术参数</a></li>
 -->
			<li><a id='bjxx' onclick="eq_loadContentStep('bjxx');" <s:if test="id==null || id==0"> style="display:none;"</s:if>>备件信息</a></li>
			<li><a id='jcxx' onclick="eq_loadContentStep('jcxx');" <s:if test="id==null || id==0"> style="display:none;"</s:if>>监测信息</a></li>
			<li><a id='bxxx' onclick="eq_loadContentStep('bxxx');" <s:if test="id==null || id==0"> style="display:none;"</s:if>>保修信息</a></li>
			<li><a id='sbwd' onclick="eq_loadContentStep('sbwd');" <s:if test="id==null || id==0"> style="display:none;"</s:if>>设备文档</a></li>
		</ul>
	</h2>
</div>

<div class="content" id="eqStepContent"></div>
<!-- content -->