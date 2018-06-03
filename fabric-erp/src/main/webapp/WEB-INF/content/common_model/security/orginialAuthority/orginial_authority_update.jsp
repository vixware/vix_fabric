<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
<!--
function chooseParentAuthority(){
	$.ajax({
		  url:'${vix}/common/security/authority/orginialAuthorityAction!goChooseAuthority.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择父权限",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
								if(result[0] == $("#id").val()){
									showErrorMessage("不允许引用本权限为父权限!");
									setTimeout("", 1000);
									return false;
								}else{
									$("#parentTreeId").val(result[0]);
									$("#parentTreeName").val(result[1]);
								}
							}else{
								$("#parentTreeId").val("");
								$("#parentTreeName").val("");
								showErrorMessage("请选择权限信息!");
								setTimeout("", 1000);
								return false;
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

function addResource(){
	$.ajax({
		  url:'${vix}/common/security/orginialResourceAction!goChooseResource.action?tag=choose',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择资源",
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
							$("#addResourceIds").val(roleIds);
							
							roleNames = roleNames.substring(1,roleNames.length);
							$("#addResourceNames").html(roleNames);
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function deleteResource(row,id){
	$(row).parent().parent().remove();
	$("#deleteResourceIds").val($("#deleteResourceIds").val()+","+id);
}

function brandList(){
	$("#brandSub").css({"display":""});
	$("#dimensionSub").css({"display":"none"});
}

$(function(){
	$("#authorityForm").validationEngine();
});
//-->
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<form action="" id="authorityForm" name="authorityForm">
			<s:hidden id="id" name="entityForm.id" value="%{entity.id}" theme="simple" />
			<table>
				<tr>
					<td align="right">上级权限:&nbsp;</td>
					<td colspan="3"><input type="hidden" id="parentTreeId" name="parentTreeId" value="${parentTreeId}" /> <input type="text" id="parentTreeName" name="parentTreeName" readonly="readonly" value="${parentTreeName}" /> <input class="btn" type="button" value="选择"
						onclick="chooseParentAuthority();" /></td>
				</tr>
				<tr height="40">
					<td align="right">名称:&nbsp;</td>
					<td><input type="text" id="name" name="entityForm.name" value="${entity.name}" class="validate[required] text-input" /></td>
					<td align="right">资源类型:&nbsp;</td>
					<td><s:select list="#{'M':'菜单','F':'功能'}" id="authType" name="entityForm.authType" value="%{entity.authType}" theme="simple"></s:select></td>

					<%-- <td align="right">是否禁用:&nbsp;</td>
				<td>
					<s:if test="category.status == 0">
						<input type="radio" id="status1" name="status" value="1"/>是
						<input type="radio" id="status2" name="status" value="0" checked="checked"/>否
					</s:if>
					<s:elseif test="category.status == 1">
						<input type="radio" id="status1" name="status" value="1" checked="checked"/>是
						<input type="radio" id="status2" name="status" value="0"/>否
					</s:elseif>
					<s:else>
						<input type="radio" id="status1" name="status" value="1"/>是
						<input type="radio" id="status2" name="status" value="0"/>否
					</s:else>
				</td>
				<td align="right">备注:&nbsp;</td>
				<td><s:textfield id="memo" name = "category.memo" value="%{category.memo}" theme="simple"/></td> --%>
				</tr>
				<!-- <tr height="30">
				<td align="right">新增品牌:</td>
				<td><a id="addCategoryBrand" href="#" onclick="addCategoryBrand();" style="color: #000000; text-decoration: none;">添加</a></td>
				<td align="right">品牌:</td>
				<td colspan="3"><span id="addCategoryBrandNames"></span></td>
			</tr> -->
				<tr height="40">
					<td align="right">显示名称:&nbsp;</td>
					<td><input type="text" id="displayName" name="entityForm.displayName" value="${entity.displayName}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
					<td align="right">编码:&nbsp;</td>
					<td><input type="text" id="authorityCode" name="entityForm.authorityCode" value="${entity.authorityCode}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
				</tr>
				<tr height="40">
					<td align="right">链接:&nbsp;</td>
					<td><input type="text" id="menuHrefUrl" name="entityForm.menuHrefUrl" value="${entity.menuHrefUrl}" /></td>
					<td align="right">顺序号:&nbsp;</td>
					<td><input type="text" id="sortOrder" name="entityForm.sortOrder" value="${entity.sortOrder}" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
				</tr>
				<tr height="40">
					<%-- <td align="right">类型:&nbsp;</td>
				<td>
					<s:select id="bizType" name="entityForm.bizType" list="#request.authorityTypeMap" listKey="key" listValue="value" value="%{entity.bizType}" theme="simple"></s:select>
				</td> --%>
					<td align="right">显示位置:&nbsp;</td>
					<td><s:select list="#{'C':'正常','U':'一级'}" id="viewPos" name="entityForm.viewPos" value="%{entity.viewPos}" theme="simple"></s:select></td>
				</tr>
				<tr height="40">
					<td align="right">备注:&nbsp;</td>
					<td><input type="text" id="memo" name="entityForm.memo" value="${entity.memo}" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
				</tr>
				<tr height="40">
					<td align="right">分配资源:</td>
					<td><a id="addResource" href="#" onclick="addResource();" style="color: #000000; text-decoration: none;">添加</a></td>
					<td align="right">资源:</td>
					<td colspan="3"><span id="addResourceNames"></span></td>
				</tr>
			</table>
			<!-- <hr/> -->
			<input type="hidden" id="addResourceIds" name="addResourceIds" value="" /> <input type="hidden" id="deleteResourceIds" name="deleteResourceIds" value="" />

		</form>
		<%--  --%>
		<div class="table" style="margin: 5px;">
			<p id="table_top" style="border: 1px solid #D6D6D6; color: #CDCDCD; padding: 5px;">
				<img src="${vix}/common/img/icon_22.gif" /><a id="bradn" href="#" onclick="brandList()" style="color: #000000; text-decoration: none;">资源列表</a>
			</p>
			<table id="brandSub" class="list">
				<tr>
					<th width="10%">编号</th>
					<th width="40%">名称</th>
					<!-- <th width="30%" align="left" >描述</th> -->
					<th width="40%" align="left">控制URL</th>
					<th width="10%">操作</th>
				</tr>
				<s:iterator value="entity.resources" var="resource" status="s">
					<tr>
						<td align="right">${s.count}</td>
						<td align="right">${resource.displayName}</td>
						<%-- <td align="right">${resource.memo}</td> --%>
						<td align="right">${resource.url}</td>
						<td align="center"><a href="#" onclick="deleteResource(this,${resource.id});" title="删除"> <img src="${vix}/common/img/icon_12.png" />
						</a></td>
					</tr>
				</s:iterator>
			</table>
		</div>
	</div>
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