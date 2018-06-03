<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript">
$("#orderItemForm").validationEngine();

//弹出组织树
function chooseParentCategory(tag){
	$.ajax({
		  url:'${vix}/hr/postSysAction!goChooseCategory.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择组织",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
									$("#parentId").val(result[0]);
									
									if(tag==0){
									$("#theDepartment").val(result[1]);
									}
							}else{
								$("#parentId").val("");
								$("#theDepartment").html("");
								asyncbox.success("请选择分类信息!","提示信息");
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
	<form id="supplierCategoryForm">
		<s:hidden id="newId" name="hrPostSys.id" value="%{hrPostSys.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">职务编码:&nbsp;</th>
					<td><input id=postsysCode name="hrPostSys.postsysCode" value="${hrPostSys.postsysCode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">职务名称:&nbsp;</th>
					<td><input id=postsysName name="hrPostSys.postsysName" value="${hrPostSys.postsysName}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">职务类型:&nbsp;</th>
					<td><s:select id="postsysTypesId" headerKey="-1" headerValue="--请选择--" list="%{postsysTypesList}" listValue="name" listKey="id" value="%{hrPostSys.postsysTypes.id}" multiple="false" theme="simple">
						</s:select> <span style="color: red;">*</span></td>
					<th align="right">职级:&nbsp;</th>
					<td><s:select id="rankId" headerKey="-1" headerValue="--请选择--" list="%{rankList}" listValue="name" listKey="id" value="%{hrPostSys.rank.id}" multiple="false" theme="simple">
						</s:select> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">职等:&nbsp;</th>
					<td><s:select id="gradingId" headerKey="-1" headerValue="--请选择--" list="%{gradingList}" listValue="name" listKey="id" value="%{hrPostSys.grading.id}" multiple="false" theme="simple">
						</s:select> <span style="color: red;">*</span></td>
					<th align="right">职权:&nbsp;</th>
					<td><s:select id="powersId" headerKey="-1" headerValue="--请选择--" list="%{powersList}" listValue="name" listKey="id" value="%{hrPostSys.powers.id}" multiple="false" theme="simple">
						</s:select> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">所属部门:&nbsp;</th>
					<td><input id="theDepartment" name="parentId" value="${hrPostSys.theDepartment}" type="text" size="30" class="validate[required] text-input" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseParentCategory(0);" /> <span
						style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">有效开始时间:&nbsp;</th>
					<td><input id="startTime" name="startTime" value="<fmt:formatDate value='${hrPostSys.startTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('newstartTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"> <span style="color: red;">*</span>
					</td>
					<th align="right">有效结束时间:&nbsp;</th>
					<td><input id="endTime" name="endTime" value="<fmt:formatDate value='${hrPostSys.endTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('newendTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>