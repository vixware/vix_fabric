<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
<!--
/* 
function addUserAccountRole(){
	$.ajax({
		  url:'${vix}/common/security/userAccountAction!goChooseRole.action?tag=choose',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择角色",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							var roleIds = "";
							var roleNames = "";
							var result = returnValue.split(",");
							for (var i=0; i<result.length; i++){
								if(result[i].length>1){
									var v = result[i].split(":");
									roleIds += "," + v[0];
									roleNames += "," + v[1];
								}
							}
							$("#addUserAccountRoleIds").val(roleIds);
							$("#addUserAccountRoleNames").html(roleNames);
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function deleteUserAccountRole(row,id){
	$(row).parent().parent().remove();
	$("#deleteUserAccountRoleIds").val($("#deleteUserAccountRoleIds").val()+","+id);
} */

function brandList(){
	$("#brandSub").css({"display":""});
	$("#dimensionSub").css({"display":"none"});
}
$(function(){
	$("#roleForm").validationEngine();
});
//-->
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form action="" id="roleForm" name="roleForm">
		<s:hidden id="id" name="entityForm.id" value="%{entity.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="10">
					<td align="right" colspan="4"></td>
				</tr>
				<tr height="40">
					<td align="right">角色名:&nbsp;</td>
					<td><input type="text" id="roleName" name="entityForm.name" value="${entity.name}" class="validate[required] text-input" /></td>
					<td align="right" style="display: none">角色编码:&nbsp;</td>
					<td><input type="text" id="roleCode" style="display: none" name="entityForm.roleCode" value="${entity.roleCode}" <s:if test="%{entity.id!=null && entity.id>0}">readonly="readonly"</s:if> class="validate[required] text-input" /></td>
				</tr>
				<tr height="40">
					<td align="right">开始时间：</td>
					<td><input type="text" name="entityForm.startTime" value="<s:date name='entity.startTime' format='yyyy-MM-dd'/>" class="time_aoto_input ipt" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'blue'})"></td>
					<td align="right">截止时间：</td>
					<td><input type="text" name="entityForm.endTime" value="<s:date name='entity.endTime' format='yyyy-MM-dd'/>" class="time_aoto_input ipt" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'blue'})"></td>
				</tr>
				<tr height="40">
					<td align="right">备注说明:&nbsp;</td>
					<td><input type="text" id="memo" name="entityForm.memo" value="${entity.memo}" /></td>
				</tr>

			</table>
			<input type="hidden" id="addUserAccountRoleIds" name="addUserAccountRoleIds" value="" /> <input type="hidden" id="deleteUserAccountRoleIds" name="deleteUserAccountRoleIds" value="" />

		</div>
	</form>
</div>
<script src="${vix}/common/js/jtip.js" type="text/javascript"></script>
<script type="text/javascript">
<!--
//提示
if($('input.sweet-tooltip').length){
	$('input.sweet-tooltip').focus(function() {
		tooltip				= $(this);
		tooltipText 		= tooltip.attr('data-text-tooltip');
		tooltipClassName	= 'tooltip';
		tooltipClass		= '.' + tooltipClassName;
		
		if(tooltip.hasClass('showed-tooltip')) return false;
		
		tooltip.addClass('showed-tooltip')
				 .after('<div class="'+tooltipClassName+'"><div class="tooltip_l"></div><div class="tooltip_r"></div><div class="tooltip_x">'+tooltipText+'</div><div class="tooltip_b"></div></div>');
		tooltipPosTop 	= tooltip.position().top - $(tooltipClass).outerHeight() - 10;
		tooltipPosLeft = tooltip.position().left;
		tooltipPosLeft = tooltipPosLeft - (($(tooltipClass).outerWidth()/2) - tooltip.outerWidth()/2);
		$(tooltipClass).css({ 'left': tooltipPosLeft, 'top': tooltipPosTop }).animate({'opacity':'1', 'marginTop':'0'}, 500);
	}).focusout(function() {
		$(tooltipClass).animate({'opacity':'0', 'marginTop':'-10px'}, 500, function() {
			$(this).remove();
			tooltip.removeClass('showed-tooltip');
				
		});
	});
}
//-->
</script>