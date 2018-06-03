<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
<!--
$(function(){
	$("#businessViewForm").validationEngine();
});

function chooseParentOrganization(){
	$.ajax({
		  //url:'${vix}/system/companyOperationAction!goChooseOrganization.action',
		  url : "${vix}/common/select/commonSelectOrgAction!goChooseOrgUnit.action",
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择父公司",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var jsonarray = $.parseJSON(returnValue);
								var orgId = jsonarray[0].treeId;
								var orgName = jsonarray[0].name;
								$("#parentOrgId").val(orgId);
								//$("#organizationName").html(result[1]);
								$("#parentOrgName").val(orgName);
							}else{
								//$("#parentId").val("");
								//$("#organizationName").val("");
								//asyncbox.success("请选择上级公司信息!","提示信息");
								showMessage("请选择上级公司信息!");
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
//-->
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form action="" id="businessViewForm" name="businessViewForm">
		<s:hidden id="id" name="entityForm.id" value="%{entity.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr>
					<td align="right">上级公司机构:&nbsp;</td>
					<td colspan="3"><input type="hidden" id="parentOrgId" name="entityForm.organization.id" value="${entity.organization.id}" /> <input type="text" id="parentOrgName" readonly="readonly" value="${entity.organization.orgName}" /> <input class="btn" type="button" value="选择" onclick="chooseParentOrganization();" /></td>
				</tr>
				<tr height="40">
					<td align="right">业务对象标识:&nbsp;</td>
					<td><input type="text" name="entityForm.metaDataCode" value="${entity.metaDataCode}" class="validate[required] text-input" /></td>
					<td align="right">编码:&nbsp;</td>
					<td><input type="text" name="entityForm.code" value="${entity.code}" <s:if test="%{entity.id!=null && entity.id>0}">readonly="readonly"</s:if> class="validate[required] text-input" /></td>
				</tr>
				<tr height="40">
					<td align="right">名称:&nbsp;</td>
					<td><input type="text" name="entityForm.name" value="${entity.name}" class="validate[required] text-input" /></td>
					<td align="right">业务视图类型:&nbsp;</td>
					<td><s:select id="bizViewType" name="entityForm.bizViewType" list="#request.bvType" listKey="key" listValue="value" value="%{entity.bizViewType}" theme="simple"></s:select> <%-- <input type="text" id="bizViewType" name="entityForm.bizViewType" value="${entity.bizViewType}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);"/> --%>
					</td>
				</tr>
				<tr height="40">
					<td align="right">项目标识:&nbsp;</td>
					<td><input type="text" name="entityForm.pmCode" value="${entity.pmCode}" class="validate[required] text-input" /></td>
					<td align="right">是否为矩阵管理结构:&nbsp;</td>
					<td><s:radio list="#{\"1\":\"是\",\"0\":\"否\"}" name="entityForm.isMatrixManagement" value="%{entity.isMatrixManagement}" theme="simple"></s:radio></td>
				</tr>
				<!-- 创建时间    开始时间  截至时间 -->
				<tr>
					<td align="right">创建时间：</td>
					<td><input type="text" name="entityForm.bizViewCreateDate" value="<s:date name='entity.bizViewCreateDate' format='yyyy-MM-dd'/>" class="time_aoto_input ipt" <s:if test="%{entity.id!=null}">readonly="readonly"</s:if> <s:else>
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'blue'})"
					</s:else>></td>
				</tr>
				<tr>
					<td align="right">开始时间：</td>
					<td><input type="text" name="entityForm.startTime" value="<s:date name='entity.startTime' format='yyyy-MM-dd'/>" class="time_aoto_input ipt" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'blue'})"></td>
					<td align="right">截止时间：</td>
					<td><input type="text" name="entityForm.endTime" value="<s:date name='entity.endTime' format='yyyy-MM-dd'/>" class="time_aoto_input ipt" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'blue'})"></td>
				</tr>

				<%-- <tr height="40">
				<td align="right">备注说明:&nbsp;</td>
				<td><input type="text" id="memo" name="entityForm.memo" value="${entity.memo}" /></td>
			</tr> --%>

			</table>
			<!-- <input type="hidden" id="addUserAccountRoleIds" name="addUserAccountRoleIds" value=""/>
		<input type="hidden" id="deleteUserAccountRoleIds" name="deleteUserAccountRoleIds" value=""/> -->

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