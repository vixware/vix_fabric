<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#orderItemForm").validationEngine();
//选择分类
function chooseSupplierCategory(){
	$.ajax({
		  url:'${vix}/mm/pmordersAction!goChooseSupplierCategory.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择所属分类",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
									$("#newScId").val(result[0]);
									$("#newScName").val(result[1]);
							}else{
								asyncbox.success("请选择所属分类信息!","提示信息");
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
		<s:hidden id="newId" name="supplierCategory.id" value="%{supplierCategory.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">工单类别:&nbsp;</th>
					<td><input id="shortName" name="BO_SPR_CMNM_shortName" type="text" size="30" /></td>
					<th align="right">产品编码:&nbsp;</th>
					<td><input id="shortName" name="BO_SPR_CMNM_shortName" type="text" size="30" /></td>
				</tr>
				<tr height="40">
					<th align="right">工单单号:&nbsp;</th>
					<td><input id="newCode" name="supplierCategory.code" value="${supplierCategory.code}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">产品名称:&nbsp;</th>
					<td><input id="newName" name="supplierCategory.name" value="${supplierCategory.name}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">开单日期:&nbsp;</th>
					<td><input id="shortName" name="BO_SPR_CMNM_shortName" type="text" size="30" /></td>
					<th align="right">状态码:&nbsp;</th>
					<td><input id="shortName" name="BO_SPR_CMNM_shortName" type="text" size="30" /></td>
				</tr>
				<tr height="40">
					<th align="right">规格:&nbsp;</th>
					<td><input id="shortName" name="BO_SPR_CMNM_shortName" type="text" size="30" /></td>
					<th align="right">性质:&nbsp;</th>
					<td><input id="shortName" name="BO_SPR_CMNM_shortName" type="text" size="30" /></td>
				</tr>
				<tr height="40">
					<th align="right">预计产量:&nbsp;</th>
					<td><input id="shortName" name="BO_SPR_CMNM_shortName" type="text" size="30" /></td>
					<th align="right">已生产量:&nbsp;</th>
					<td><input id="shortName" name="BO_SPR_CMNM_shortName" type="text" size="30" /></td>
				</tr>
				<tr height="40">
					<th align="right">预计开工时间:&nbsp;</th>
					<td><input id="shortName" name="BO_SPR_CMNM_shortName" type="text" size="30" /></td>
					<th align="right">预计完工时间:&nbsp;</th>
					<td><input id="shortName" name="BO_SPR_CMNM_shortName" type="text" size="30" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>