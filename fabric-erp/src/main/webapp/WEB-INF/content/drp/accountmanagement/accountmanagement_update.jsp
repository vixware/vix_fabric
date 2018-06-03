<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function addUserAccountRole(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectRoleAction!goList.action?tag=choose',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 860,
					height : 540,
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
							roleNames = roleNames.substring(1,roleNames.length);
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
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form action="" id="userAccountForm">
		<s:hidden id="id" name="entityForm.id" value="%{entity.id}" theme="simple" />
		<s:hidden id="parentId" name="parentId" value="%{parentId}" theme="simple" />
		<input type="hidden" id="employeeId" name="employeeId" value="${entityForm.employee.id}" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">账户:&nbsp;</td>
					<td><input type="text" id="account" name="entityForm.account" value="${entity.account}" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
					<td align="right">是否禁用:&nbsp;</td>
					<td><s:if test='entity.enable=="1"'>
							<s:radio list="#{\"0\":\"是\",\"1\":\"否\"}" name="entityForm.enable" listKey="key" listValue="value" value="1" theme="simple"></s:radio>
						</s:if> <s:elseif test='entity.enable=="0"'>
							<s:radio list="#{\"0\":\"是\",\"1\":\"否\"}" name="entityForm.enable" listKey="key" listValue="value" value="0" theme="simple"></s:radio>
						</s:elseif> <s:else>
							<s:radio list="#{\"0\":\"是\",\"1\":\"否\"}" name="entityForm.enable" listKey="key" listValue="value" value="1" theme="simple"></s:radio>
						</s:else><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">密码:&nbsp;</td>
					<td><input type="password" id="password" name="entityForm.password" value="${entity.password}" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
					<td align="right">确认密码:&nbsp;</td>
					<td><input type="password" id="passwordConfirm" name="entityForm.passwordConfirm" value="${entity.password}" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
				</tr>
				<tr height="40">
					<td align="right">开始时间:&nbsp;</td>
					<td><input id="startTime" name="entityForm.startTime" value="<s:date name='%{entity.startTime}' format='yyyy-MM-dd'/>" type="text" /><img onclick="showTime('startTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<td align="right">结束时间:&nbsp;</td>
					<td><input id="endTime" name="entityForm.endTime" value="<s:date name='%{entity.endTime}' format='yyyy-MM-dd'/>" type="text" /><img onclick="showTime('endTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="40">
					<td align="right">分配角色:</td>
					<td><a id="addUserAccountRole" href="#" onclick="addUserAccountRole();" style="color: #000000; text-decoration: none;">添加</a></td>
					<td align="right">角色:</td>
					<td><span id="addUserAccountRoleNames"></span></td>
				</tr>
			</table>
			<hr />
			<input type="hidden" id="addUserAccountRoleIds" name="addUserAccountRoleIds" value="" /> <input type="hidden" id="deleteUserAccountRoleIds" name="deleteUserAccountRoleIds" value="" />
			<div class="table" style="margin: 5px;">
				<p id="table_top" style="border: 1px solid #D6D6D6; color: #CDCDCD; padding: 5px;">
					<img src="${vix}/common/img/user.png" /> <a id="bradn" href="#" onclick="brandList()" style="color: #000000; text-decoration: none;">角色列表</a>
				</p>
				<table id="brandSub" class="list">
					<tr>
						<th>编号</th>
						<th>名称</th>
						<th>操作</th>
					</tr>
					<s:iterator value="entity.roles" var="role" status="s">
						<tr>
							<td>${s.count}</td>
							<td>${role.name}</td>
							<td><a href="#" onclick="deleteUserAccountRole(this,${role.id});" title="删除"> <img src="${vix}/common/img/icon_12.png" />
							</a></td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>
	</form>
</div>
