<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
<!--

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
}

function brandList(){
	$("#brandSub").css({"display":""});
	$("#dimensionSub").css({"display":"none"});
}
//-->
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form action="" id="userAccountForm">
		<s:hidden id="id" name="entityForm.id" value="%{entity.id}" theme="simple" />
		<input type="hidden" name="entityForm.employee.id" value="${employeeId}">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">账户:&nbsp;</td>
					<td><input type="text" id="account" name="entityForm.account" value="${entity.account}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
					<td align="right">是否禁用:&nbsp;</td>
					<td><s:radio list="#{'0':'是','1':'否'}" name="entityForm.enable" value="%{entity.enable}" theme="simple"></s:radio></td>
					<td align="right">职员:&nbsp;</td>
					<td>普通职员</td>
				</tr>
				<tr height="40">
					<td align="right">密码:&nbsp;</td>
					<td><input type="password" id="password" name="entityForm.password" value="${entity.password}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
					<td align="right">确认密码:&nbsp;</td>
					<td><input type="password" id="passwordConfirm" name="entityForm.passwordConfirm" value="${entity.password}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
				</tr>
				<tr height="30">
					<td align="right">分配角色:</td>
					<td><a id="addUserAccountRole" href="###" onclick="addUserAccountRole();" style="color: #000000; text-decoration: none;">添加</a></td>
					<td align="right">角色:</td>
					<td colspan="3"><span id="addUserAccountRoleNames"></span></td>
				</tr>
			</table>
			<hr />
			<input type="hidden" id="addUserAccountRoleIds" name="addUserAccountRoleIds" value="" /> <input type="hidden" id="deleteUserAccountRoleIds" name="deleteUserAccountRoleIds" value="" />
			<div class="table" style="margin: 5px;">
				<p id="table_top" style="border: 1px solid #D6D6D6; color: #CDCDCD; padding: 5px;">
					<img src="${vix}/common/img/icon_22.gif" /><a id="bradn" href="#" onclick="brandList()" style="color: #000000; text-decoration: none;">角色列表</a>
				</p>
				<table id="brandSub" class="list">
					<tr>
						<th width="10%">编号</th>
						<th width="80%">名称</th>
						<th width="10%">操作</th>
					</tr>
					<s:iterator value="entity.roles" var="role" status="s">
						<tr>
							<td align="right">${s.count}</td>
							<td align="right">${role.name}</td>
							<td align="center"><a href="#" onclick="deleteUserAccountRole(this,${role.id});" title="删除"> <img src="${vix}/common/img/icon_12.png" />
							</a></td>
						</tr>
					</s:iterator>
				</table>
			</div>
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