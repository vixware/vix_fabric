<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
$("#treeSingleGridForm").validationEngine();
function chooseParentCategory(){
	$.ajax({
		  url:'${vix}/oa/adminMg/requisitionCar/requisitionCarAction!goChooseCategory.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择父分类",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
								if(result[0] == $("#id").val()){
									asyncbox.success("不允许引用自身为父分类!","提示信息");
								}else{
									$("#parentId").val(result[0]);
									$("#categoryName").html(result[1]);
								}
							}else{
								$("#parentId").val("");
								$("#categoryName").html("");
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
	<form id="treeSingleGridForm">
		<s:hidden id="id" name="category.id" value="%{category.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr>
					<td align="right">父分类:&nbsp;</td>
					<td><input type="hidden" id="parentId" name="parentId" value="${vehicleRequest.parentCategory.id}" /> <span id="categoryName"><s:property value="vehicleRequest.parentCategory.model" /></span> <span class="btn"><a href="#" onclick="chooseParentCategory();">选择</a></span></td>
					<td align="right">型号:&nbsp;</td>
					<td><input type="text" id="model" name="category.model" value="${vehicleRequest.model}" /></td>
				</tr>
				<tr height="40">
					<th align="right">车辆类型：</th>
					<td><s:select id="vehicleTypeId" headerKey="-1" headerValue="--请选择--" list="%{vehicleTypeList}" listValue="name" listKey="id" value="%{vehicleRequest.vehicleType.id}" multiple="false" theme="simple">
						</s:select></td>
					<td align="right">是否禁用:&nbsp;</td>
					<td><s:if test="category.status == 0">
							<input type="radio" id="status1" name="status" value="1" />是
							<input type="radio" id="status2" name="status" value="0" checked="checked" />否
						</s:if> <s:elseif test="category.status == 1">
							<input type="radio" id="status1" name="status" value="1" checked="checked" />是
							<input type="radio" id="status2" name="status" value="0" />否
						</s:elseif> <s:else>
							<input type="radio" id="status1" name="status" value="1" />是
							<input type="radio" id="status2" name="status" value="0" checked="checked" />否
						</s:else></td>
				</tr>
				<tr height="40">
					<td align="right">车牌号:&nbsp;</td>
					<td><input type="text" id="plateNumber" name="vehicleRequest.plateNumber" value="${vehicleRequest.plateNumber}" /></td>
					<td align="right">引擎号:&nbsp;</td>
					<td><input type="text" id="engineNumber" name="vehicleRequest.engineNumber" value="${vehicleRequest.engineNumber}" /></td>
				</tr>
				<tr height="40">
					<td align="right">购买价:&nbsp;</td>
					<td><input type="text" id="price" name="vehicleRequest.price" value="${vehicleRequest.price}" /></td>
					<td align="right">购置日期:&nbsp;</td>
					<td><input id="acquiredDate" name="acquiredDate" type="text" class="ipt" onclick="showTime('acquiredDate','yyyy-MM-dd')" value="<fmt:formatDate value='${vehicleRequest.acquiredDate }' type='both' pattern='yyyy-MM-dd' />" /> <a><img onclick="showTime('acquiredDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16"
							height="22" align="absmiddle"></a></td>
				</tr>
				<tr height="40">
					<th align="right">车辆颜色：</th>
					<td><s:select id="vehicleColorId" headerKey="-1" headerValue="--请选择--" list="%{vehicleColorList}" listValue="name" listKey="id" value="%{vehicleRequest.vehicleColor.id}" multiple="false" theme="simple">
						</s:select></td>
					<th align="right">变速器类型：</th>
					<td><s:select id="transmissionTypeId" headerKey="-1" headerValue="--请选择--" list="%{transmissionTypeList}" listValue="name" listKey="id" value="%{vehicleRequest.transmissionType.id}" multiple="false" theme="simple">
						</s:select></td>
				</tr>
				<tr height="40">
					<th align="right">引擎类型：</th>
					<td><s:select id="engineTypeId" headerKey="-1" headerValue="--请选择--" list="%{engineTypeList}" listValue="name" listKey="id" value="%{vehicleRequest.engineType.id}" multiple="false" theme="simple">
						</s:select></td>
					<th align="right">排量类型：</th>
					<td><s:select id="displacementTypeId" headerKey="-1" headerValue="--请选择--" list="%{displacementTypeList}" listValue="name" listKey="id" value="%{vehicleRequest.displacementType.id}" multiple="false" theme="simple">
						</s:select></td>
				</tr>
				<tr>
					<th>创建日期:&nbsp;</th>
					<td><input id="createTime" name="createTime" type="text" class="ipt" onclick="showTime('createTime','yyyy-MM-dd')" value="<fmt:formatDate value='${vehicleRequest.createTime}' type='both' pattern='yyyy-MM-dd'/>" /> <a><img onclick="showTime('createTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
							align="absmiddle"></a></td>
				</tr>
				<tr height="40">
					<th class="tr">备注：</th>
					<td colspan="2"><textarea id="memo" name="memo" class="example" rows="2" style="width: 400px">${vehicleRequest.memo }</textarea></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script src="${vix}/common/js/jtip.js" type="text/javascript"></script>
