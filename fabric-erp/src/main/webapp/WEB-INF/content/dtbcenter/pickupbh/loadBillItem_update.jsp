<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$ ("#orderItemForm").validationEngine ();
	function chooseItem (){
		$.ajax ({
		url : '${vix}/dtbcenter/pickupBusinessHandlingAction!goChooseItem.action' ,
		cache : false ,
		success : function (html){
			asyncbox.open ({
			modal : true ,
			width : 960 ,
			height : 580 ,
			title : "选择${vv:varView("vix_mdm_item")}" ,
			html : html ,
			callback : function (action , returnValue){
				if (action == 'ok'){
					if (returnValue != ''){
						$.ajax ({
						url : '${vix}/dtbcenter/pickupBusinessHandlingAction!getItemEntityJson.action?itemId=' + returnValue ,
						cache : false ,
						success : function (json){
							var obj = eval (json);
							$ ("#itemCode").val (obj.code);
							$ ("#itemName").val (obj.name);
							$ ("#unit").val (obj.masterUnit);
							$ ("#price").val (obj.price);
							$ ("#tax").val (obj.saleTax);
						}
						});
					}else{
						asyncbox.success ("请选择${vv:varView("vix_mdm_item")}!" , "<s:text name='vix_message'/>");
						return false;
					}
				}
			} ,
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
	function getDate (val){
		if (val != null && val != ""){
			var d = new Date (val);
			return format (d);
		}else
			return "";
	}
	function format (date){
		var y = date.getFullYear ();
		var m = date.getMonth () + 1;
		var d = date.getDate ();
		return y + '-' + m + '-' + d;
	}
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="orderItemForm">
		<s:hidden id="oiId" name="loadBillItem.id" value="%{loadBillItem.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">${vv:varView("vix_mdm_item")}编码:&nbsp;</th>
					<td><input id="itemCode" name="itemCode" value="${loadBillItem.itemCode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">${vv:varView("vix_mdm_item")}名称:&nbsp;</th>
					<td><input id="itemName" name="itemName" value="${loadBillItem.itemName}" type="text" /><input class="btn" type="button" value="选择" onclick="chooseItem();" /></td>
				</tr>
				<tr height="40">
					<th align="right">计量单位:&nbsp;</th>
					<td><input id="unit" name="unit" value="${loadBillItem.unit}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">单价:&nbsp;</th>
					<td><input id="price" name="price" value="${loadBillItem.price}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">需求日期:&nbsp;</th>
					<td><input id="requireDate" name="requireDate" class="validate[required] text-input" value="${loadBillItem.requireDate}" type="text" /><img onclick="showTime('requireDate','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span style="color: red;">*</span></td>
					<th align="right">税率:&nbsp;</th>
					<td><input id="tax" name="tax" value="${loadBillItem.tax}" type="text" class="validate[required,custom[integer]] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">销售数量:&nbsp;</th>
					<td><input id="amount" name="amount" value="${loadBillItem.amount}" type="text" class="validate[required,custom[integer]] text-input" /> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>