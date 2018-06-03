<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
<!--

function addDataRowOwnerRole(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectRoleAction!goList.action',//dataResRowOwnerAction
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 760,
					height : 520,
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
							
							roleNames = roleNames.substring(1,roleNames.length);
							$("#addDataRowOwnerRoleIds").val(roleIds);
							$("#addDataRowOwnerRoleNames").html(roleNames);
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

/**
 * 选择策略
 */
function chooseDataResRowPolicy(){
	$.ajax({
		  url:'${vix}/common/security/dataResRowOwnerAction!goChooseRowPolicy.action',//
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 760,
					height : 520,
					title:"选择策略",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							var rowPolicyIds = "";
							var rowPolicyNames = "";
							var result = returnValue.split(",");
							for (var i=0; i<result.length; i++){
								if(result[i].length>1){
									var v = result[i].split(":");
									rowPolicyIds += "," + v[0];
									rowPolicyNames += "," + v[1];
									break;
								}
							}
							
							rowPolicyNames = rowPolicyNames.substring(1,rowPolicyNames.length);
							
							$("#dataResRowPolicyId").val(rowPolicyIds);
							$("#policyName").val(rowPolicyNames);
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

function deleteDataRowOwnerRole(row,id){
	$(row).parent().parent().remove();
	$("#deleteDataRowOwnerRoleIds").val($("#deleteDataRowOwnerRoleIds").val()+","+id);
}

function brandList(){
	$("#brandSub").css({"display":""});
	$("#dimensionSub").css({"display":"none"});
}
//-->
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form action="" id="dataRowOwnerForm">
		<s:hidden id="id" name="entityForm.id" value="%{entity.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">主体名称:&nbsp;</td>
					<td><input type="text" id="account" name="entityForm.ownerName" value="${entity.ownerName}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
					<td align="right">主体类型:&nbsp;</td>
					<td><s:select list="#{'R':'角色'}" id="ownerType" name="entityForm.ownerType" value="%{entity.ownerType}" theme="simple"></s:select></td>

					<%-- <td align="right">是否禁用:&nbsp;</td>
				<td>
					<s:if test="entity.enable == '0'">
						<input type="radio" id="enable1" name="enable" value="1" checked="checked"/>是
						<input type="radio" id="enable2" name="enable" value="0" />否
					</s:if>
					<s:elseif test="entity.enable == '1'">
						<input type="radio" id="enable1" name="enable" value="1" />是
						<input type="radio" id="enable2" name="enable" value="0" checked="checked"/>否
					</s:elseif>
					<s:else>
						<input type="radio" id="enable1" name="enable" value="1"/>是
						<input type="radio" id="enable2" name="enable" value="0"/>否
					</s:else>
				</td>
				<td align="right">职员:&nbsp;</td>
				<td>普通职员</td> --%>
				</tr>
				<tr height="40">
					<td align="right">权限策略:&nbsp;</td>
					<%-- 
				<td><s:select list="dataResRowPolicyList" listKey="id" listValue="policyName" id="dataResRowPolicyId" name="entityForm.dataResRowPolicy.id" value="%{entity.dataResRowPolicy.id}" theme="simple"></s:select></td>
				 --%>
					<td colspan="3"><input type="hidden" id="dataResRowPolicyId" name="entityForm.dataResRowPolicy.id" value="${entity.dataResRowPolicy.id}" /> <input type="text" id="policyName" name="policyName" readonly="readonly" value="${entity.dataResRowPolicy.policyName}" /> <input class="btn" type="button" value="选择"
						onclick="chooseDataResRowPolicy('');" /></td>
				</tr>
				<tr height="30">
					<td align="right">分配角色:</td>
					<td><a id="addDataRowOwnerRole" href="#" onclick="addDataRowOwnerRole();" style="color: #000000; text-decoration: none;">添加</a></td>
					<td align="right">角色:</td>
					<td colspan="3"><span id="addDataRowOwnerRoleNames"></span></td>
				</tr>
			</table>
			<hr />
			<input type="hidden" id="addDataRowOwnerRoleIds" name="addDataRowOwnerRoleIds" value="" /> <input type="hidden" id="deleteDataRowOwnerRoleIds" name="deleteDataRowOwnerRoleIds" value="" />
			<div class="table" style="margin: 5px;">
				<p id="table_top" style="border: 1px solid #D6D6D6; color: #CDCDCD; padding: 5px;">
					<img src="${vix}/common/img/icon_22.gif" /><a id="bradn" href="#" onclick="brandList()" style="color: #000000; text-decoration: none;">角色列表</a>
				</p>
				<table id="brandSub" class="list">
					<tr>
						<th width="10%" style="text-align: center;">编号</th>
						<th width="80%" style="text-align: center;">名称</th>
						<th width="10%" style="text-align: center;">操作</th>
					</tr>
					<s:iterator value="entity.roles" var="role" status="s">
						<tr>
							<td style="text-align: center;">${s.count}</td>
							<td style="text-align: center;">${role.name}</td>
							<td style="text-align: center;"><a href="#" onclick="deleteDataRowOwnerRole(this,${role.id});" title="删除"> <img src="${vix}/common/img/icon_12.png" />
							</a></td>
						</tr>
					</s:iterator>
				</table>
				<%-- <table id="dimensionSub" class="list" style="display:none;">
				<tr>
					<th width="10%">编号</th>
					<th width="80%">名称</th>
					<th width="10%">操作</th>
				</tr>
				<s:iterator value="category.dimensions" var="entity" status="s">
					<tr>
						<td>${s.count}</td>
						<td>${entity.name}</td>
						<td align="center">
							<a href="#" onclick="deleteUserAccountRoleDimension(this,${entity.id});" title="删除">
								<img src="${vix}/common/img/icon_12.png"/>
							</a>
						</td>
					</tr>
				</s:iterator>
			</table> --%>
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