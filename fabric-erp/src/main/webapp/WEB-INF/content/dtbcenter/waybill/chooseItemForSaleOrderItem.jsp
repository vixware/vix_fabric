<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#orderItemForm").validationEngine();
    function chooseItem() {
	    $.ajax({
	    url : '${vix}/mdm/item/itemAction!goChooseItem.action',
	    cache : false,
	    success : function(html) {
		    asyncbox.open({
		    modal : true,
		    width : 960,
		    height : 580,
		    title : "选择${vv:varView("vix_mdm_item")}",
		    html : html,
		    callback : function(action,returnValue) {
			    if (action == 'ok') {
				    if (returnValue != '') {
					    $.ajax({
					    url : '${vix}/mdm/item/itemAction!getItemEntityJson.action?itemIdSpecId=' + returnValue,
					    cache : false,
					    success : function(json) {
						    var obj = eval(json);
						    $("#itemCode").val(obj.code);
						    $("#unitcost").val(obj.price);
						    $("#producedate").val(getDate(obj.produceDate));
						    $("#massdate").val(obj.qualityPeriod);
						    $("#batchcode").val(obj.batchCode);
						    $("#specification").val(obj.specification);
						    $("#unit").val(obj.purchaseUnit);
					    }
					    });
				    } else {
					    asyncbox.success("请选择${vv:varView("vix_mdm_item")}!", "<s:text name='vix_message'/>");
					    return false;
				    }
			    }
		    },
		    btnsbar : $.btn.OKCANCEL
		    });
	    }
	    });
    }
    function getDate(val) {
	    if (val != null && val != "") {
		    var d = new Date(val);
		    return format(d);
	    } else
		    return "";
    }
    function format(date) {
	    var y = date.getFullYear();
	    var m = date.getMonth() + 1;
	    var d = date.getDate();
	    return y + '-' + m + '-' + d;
    }
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="orderItemForm">
		<s:hidden id="oiId" name="oiId" value="%{saleOrderItem.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">${vv:varView("vix_mdm_item")}编码:&nbsp;</th>
					<td><input id="itemCode" name="itemCode" value="${saleOrderItem.itemCode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span><input class="btn" type="button" value="选择" onclick="chooseItem();" /></td>
					<th align="right">规格型号:&nbsp;</th>
					<td><input id="specification" name="specification" value="${saleOrderItem.specification}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">${vv:varView("vix_mdm_item")}价格:&nbsp;</th>
					<td><input id="price" name="price" value="${saleOrderItem.price}" type="text" class="validate[required,custom[integer]] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">单位:&nbsp;</th>
					<td><input id="unit" name="unit" value="${saleOrderItem.unit}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">数量:&nbsp;</th>
					<td><input id="amount" name="amount" value="${saleOrderItem.amount}" type="text" class="validate[required,custom[integer]] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">金额:&nbsp;</th>
					<td><input id="netTotal" name="netTotal" value="${saleOrderItem.netTotal}" type="text" class="validate[required,custom[integer]] text-input" /> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>