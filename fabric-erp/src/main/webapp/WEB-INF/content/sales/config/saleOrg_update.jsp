<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function chooseParentOrganization(){
	$.ajax({
		  url:'${vix}/common/org/organizationAction!goChooseOrganization.action',
		  cache: false,
		  success: function(html){
			  //debugger;
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择父公司",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
								if(result[0] == $("#id").val()){
									asyncbox.success("不允许引用本公司为父公司!","提示信息");
								}else{
									$("#parentId").val(result[0]);
									$("#parentTreeName").val(result[1]);
								}
							}else{
								$("#parentId").val("");
								$("#parentTreeName").val("");
								asyncbox.success("请选择公司信息!","提示信息");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<form action="" id="organizationUnitForm" name="organizationUnitForm">
			<s:hidden id="id" name="entityForm.id" value="%{entity.id}" theme="simple" />
			<table>
				<tr>
					<td align="right">上级机构:&nbsp;</td>
					<td><input type="hidden" id="parentId" name="parentId" value="${parentId}" /> <input type="hidden" id="parentTreeType" name="parentTreeType" value="${parentTreeType}" /> <%-- <span id="parentTreeName"><s:property value="parentTreeName"/></span> --%> <input type="text" id="parentTreeName" name="parentTreeName" readonly="readonly"
						value="${parentTreeName}" /> <input class="btn" type="button" value="选择" onclick="chooseParentOrganization();" /></td>
					<td align="right">编码:&nbsp;</td>
					<td><input id="orgCode" name="entityForm.orgCode" type="text" value="${entity.orgCode}" /></td>
				</tr>
				<tr height="40">
					<td align="right">全称:&nbsp;</td>
					<td><input id="fullName" name="entityForm.fullName" value="${entity.fullName}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">简称:&nbsp;</td>
					<td><input id="fs" name="entityForm.fs" value="${entity.fs}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">组织单元类型:&nbsp;</td>
					<td><s:select list="#{\"JZBM\":\"基准部门\",\"XSZZ\":\"销售组织\",\"XSBGS\":\"销售办公室\",\"XSZ\":\"销售组\"}" name="entityForm.unitType" value="%{entity.unitType}" theme="simple"></s:select></td>
					<td align="right">业务类型:&nbsp;</td>
					<td><input type="text" id="bizUnitType" name="entityForm.bizUnitType" value="${entity.bizUnitType}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
				</tr>
				<tr height="40">
					<td align="right">描述:&nbsp;</td>
					<td colspan="3"><input id="description" name="entityForm.description" type="text" value="${entity.description}" /></td>
				</tr>
				<tr height="40">
					<td align="right">启用时间:&nbsp;</td>
					<td><input type="text" id="startUsingDate" name="entityForm.startUsingDate" value="<s:date name='%{entity.startUsingDate}' format='yyyy-MM-dd'/>" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /><img onclick="showTime('startUsingDate','yyyy-MM-dd')" src="img/datePicker.gif"
						width="16" height="22" align="absmiddle"></td>
					<td align="right">停用时间:&nbsp;</td>
					<td><input type="text" id="stopUsingDate" name="entityForm.stopUsingDate" value="<s:date name='%{entity.stopUsingDate}' format='yyyy-MM-dd'/>" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /><img onclick="showTime('stopUsingDate','yyyy-MM-dd')" src="img/datePicker.gif" width="16"
						height="22" align="absmiddle"></td>
				</tr>
				<tr height="40">
					<td align="right">备注:&nbsp;</td>
					<td colspan="3"><textarea id="memo" rows="4" cols="60" style="font-size: 12px;">${entity.memo}</textarea></td>
				</tr>
			</table>
			<!-- <hr/> -->
			<input type="hidden" id="addCategoryBrandIds" name="addCategoryBrandIds" value="" /> <input type="hidden" id="deleteCategoryBrandIds" name="deleteCategoryBrandIds" value="" />

		</form>

	</div>
</div>