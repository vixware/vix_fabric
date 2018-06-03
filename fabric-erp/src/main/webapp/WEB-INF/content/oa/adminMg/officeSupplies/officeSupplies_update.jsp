<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#officeSuppliesForm").validationEngine();
	function bookBaseCategory() {
		$.ajax({
		url : '${vix}/oa/officeSuppliesAction!goBookCategory.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 560,
			height : 340,
			title : "选择分类",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						$("#officeCategoryId").val(result[0]);
						$("#officeCategoryName").val(result[1]);
					} else {
						$("#officeCategoryId").val("");
						$("#officeCategoryName").val("");
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
	<form id="officeSuppliesForm" method="post" theme="simple" enctype="multipart/form-data">
		<s:hidden id="officeSuppliesId" name="officeSupplies.id" value="%{officeSupplies.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">分类:&nbsp;</td>
					<td><s:hidden id="officeCategoryId" name="officeSupplies.officeCategory.id" value="%{officeSupplies.officeCategory.id}" theme="simple" /> <input type="text" id="officeCategoryName" name="officeSupplies.officeCategory.name" value="${officeSupplies.officeCategory.name}" readonly="readonly" /> <input class="btn" type="button" value="选择"
						onclick="bookBaseCategory();" /></td>
				</tr>
				<tr height="40">
					<td align="right">办公用品名称:&nbsp;</td>
					<td><input type="text" id="officeSuppliesName" name="officeSupplies.officeSuppliesName" class="validate[required] text-input" value="${officeSupplies.officeSuppliesName}" /><span style="color: red;">*</span></td>
					<td align="right">规格/型号:&nbsp;</td>
					<td><input type="text" id="model" name="officeSupplies.model" class="validate[required] text-input" value="${officeSupplies.model}" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">计量单位:&nbsp;</td>
					<td><input type="text" id="prickle" name="officeSupplies.prickle" class="validate[required] text-input" value="${officeSupplies.prickle}" /><span style="color: red;">*</span></td>
					<td align="right">单价:&nbsp;</td>
					<td><input type="text" id="unitPrice" name="officeSupplies.unitPrice" class="validate[required] text-input" value="${officeSupplies.unitPrice}" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">办公用品编码:&nbsp;</td>
					<td><input type="text" id="officeSuppliesCode" name="officeSupplies.officeSuppliesCode" class="validate[required] text-input" value="${officeSupplies.officeSuppliesCode}" /><span style="color: red;">*</span></td>
					<td align="right">当前库存:&nbsp;</td>
					<td><input type="text" id="currentInventory" name="officeSupplies.currentInventory" class="validate[required] text-input" value="${officeSupplies.currentInventory}" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">最低警戒库存:&nbsp;</td>
					<td><input type="text" id="lowestVigilance" name="officeSupplies.lowestVigilance" class="validate[required] text-input" value="${officeSupplies.lowestVigilance}" /><span style="color: red;">*</span></td>
					<td align="right">最高警戒库存:&nbsp;</td>
					<td><input type="text" id="maximumVigilance" name="officeSupplies.maximumVigilance" class="validate[required] text-input" value="${officeSupplies.maximumVigilance}" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">供应商:&nbsp;</td>
					<td><input type="text" id="supplier" name="officeSupplies.supplier" class="validate[required] text-input" value="${officeSupplies.supplier}" /><span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>