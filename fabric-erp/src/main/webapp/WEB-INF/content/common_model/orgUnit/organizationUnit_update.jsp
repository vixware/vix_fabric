<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function chooseParentOrganization(){
	$.ajax({
		  //url:'${vix}/common/org/organizationUnitAction!goChooseOrganization.action',
		  url:"${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action",
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择上级",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								//debugger;
								/* var result = returnValue.split(",");
								if(result[0] == $("#id").val()){
									asyncbox.success("不允许引用本部门为父部门!","提示信息");
								}else{
									$("#parentId").val(result[0]);
									$("#parentTreeName").val(result[1]);
									$("#parentTreeType").val(result[2]);
								} */
								var selectId = "";
								var selectName = "";
								var selectType = "";
								var resObj = $.parseJSON(returnValue);
								
								for(var i=0;i<resObj.length;i++){
									selectId = resObj[i].realId;
									selectName = resObj[i].name;
									selectType = resObj[i].treeType;
									break;
								}
								
								if(selectId == $("#id").val()){
									showErrorMessage("不允许引用本部门为父部门!");
									setTimeout("", 1000);
									return false;
								}else{
									$("#parentId").val(selectId);
									$("#parentTreeName").val(selectName);
									$("#parentTreeType").val(selectType);
								}
							}else{
								$("#parentId").val("");
								$("#parentTreeName").val("");
								$("#parentTreeType").val("");
								showErrorMessage("请选择部门信息!");
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


function brandList(){
	$("#brandSub").css({"display":""});
	$("#dimensionSub").css({"display":"none"});
}

/**
 * 负责人选择
 */
function chooseManager(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
		  data:{chkStyle:"radio"},
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 660,
					height : 340,
					title:"选择负责人",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var selectIds = "";
								var selectNames = "";
								var result = returnValue.split(",");
								var resultLength = result.length;
								if(result[resultLength-1].length>1){
									var v = result[resultLength-1].split(":");
									selectIds += "," + v[0];
									selectNames += "," + v[1];
								}
								$("#managerEmpId").val(selectIds.substring(1,selectIds.length));
								selectNames = selectNames.substring(1,selectNames.length);
								$("#managerEmpName").val(selectNames);
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

$(function(){
	$("#organizationUnitForm").validationEngine();
});

</script>
<div class="content" style="margin-top: 15px; overflow: hidden">

	<div class="box order_table" style="line-height: normal;">
		<form action="" id="organizationUnitForm" name="organizationUnitForm">
			<s:hidden id="id" name="entityForm.id" value="%{entity.id}" theme="simple" />
			<table>
				<tr>
					<td align="right">上级机构:&nbsp;</td>
					<td colspan="3"><input type="hidden" id="parentId" name="parentId" value="${parentId}" /> <input type="hidden" id="parentTreeType" name="parentTreeType" value="${parentTreeType}" /> <input type="text" id="parentTreeName" name="parentTreeName" readonly="readonly" value="${parentTreeName}" /> <input class="btn" type="button" value="选择"
						onclick="chooseParentOrganization();" /></td>
				</tr>
				<tr height="40">
					<td align="right">负责人:&nbsp;</td>
					<td colspan="3"><input type="hidden" id="managerEmpId" name="entityForm.manager.id" value="${entity.manager.id}" /> <input type="text" id="managerEmpName" name="managerEmpName" readonly="readonly" value="${entity.manager.name}" /> <input class="btn" type="button" value="选择" onclick="chooseManager();" /></td>
				</tr>
				<tr height="40">
					<td align="right">全称:&nbsp;</td>
					<td><input type="text" id="fullName" name="entityForm.fullName" value="${entity.fullName}" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<td align="right">简称:&nbsp;</td>
					<td><input type="text" id="fs" name="entityForm.fs" value="${entity.fs}" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>

				<tr height="40">
					<td align="right">编码:&nbsp;</td>
					<td><input type="text" id="orgCode" name="entityForm.orgCode" value="${entity.orgCode}" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<td align="right">业务类型:&nbsp;</td>
					<td><input type="text" id="bizUnitType" name="entityForm.bizUnitType" value="${entity.bizUnitType}" /></td>
				</tr>
				<tr height="40">
					<td align="right">组织单元类型:&nbsp;</td>
					<td><s:select list="#{'JZBM':'基准部门','XSBGS':'销售办公室','XSZ':'销售组','XSZZ':'销售组织'}" name="entityForm.unitType" value="%{entity.unitType}" theme="simple"></s:select></td>

					<td align="right">描述:&nbsp;</td>
					<td><input type="text" id="description" name="entityForm.description" value="${entity.description}" /></td>

				</tr>

				<tr height="40">
					<td align="right">启用时间:&nbsp;</td>
					<td><input type="text" id="startUsingDate" name="entityForm.startUsingDate" value="<s:date name='%{entity.startUsingDate}' format='yyyy-MM-dd'/>" class="validate[required] text-input" /><img onclick="showTime('startUsingDate','yyyy-MM-dd')" src="img/datePicker.gif" width="16" height="22" align="absmiddle"> <span style="color: red;">*</span>
					</td>
					<td align="right">停用时间:&nbsp;</td>
					<td><input type="text" id="stopUsingDate" name="entityForm.stopUsingDate" value="<s:date name='%{entity.stopUsingDate}' format='yyyy-MM-dd'/>" class="validate[required] text-input" /><img onclick="showTime('stopUsingDate','yyyy-MM-dd')" src="img/datePicker.gif" width="16" height="22" align="absmiddle"> <span style="color: red;">*</span>
					</td>
				</tr>
			</table>
			<!-- <hr/> -->
			<input type="hidden" id="addCategoryBrandIds" name="addCategoryBrandIds" value="" /> <input type="hidden" id="deleteCategoryBrandIds" name="deleteCategoryBrandIds" value="" />

		</form>
	</div>
</div>
