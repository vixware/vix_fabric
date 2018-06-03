<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#supplierForm").validationEngine();

	//选择分类
	function chooseSupplierCategory() {
		$.ajax({
		url : '${vix}/srm/managementBusinessPartnerAction!goChooseSupplierCategory.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 560,
			height : 340,
			title : "选择所属分类",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						$("#newParentId").val(result[0]);
						$("#newCatalog").val(result[1]);
					} else {
						$("#newParentId").val("");
						$("#newCatalog").val("");
						asyncbox.success("请选择所属分类信息!", "提示信息");
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
	<form id="supplierForm">
		<s:hidden id="srmId" name="supplier.id" value="%{supplier.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right"><font color="red">状态</font>:&nbsp;</th>
					<td colspan="3"><select disabled="disabled" style="width: 227px">
							<option>已通过</option>
					</select></td>
				</tr>
				<tr height="40">
					<th align="right">编码:&nbsp;</th>
					<td><input id="newSupplierCode" name="supplier.code" size="30px" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">名称:&nbsp;</th>
					<td><input id="newSupplierName" name="supplier.name" size="30px" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">简称:&nbsp;</th>
					<td><input id="newShortName" name="supplier.shortName" size="30px" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">法人:&nbsp;</th>
					<td><input id="newArtificialPerson" name="supplier.artificialPerson" size="30px" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<!-- <tr height="40">
					<th align="right">类型:&nbsp;</th>
					<td><select id="newType" name="type" style="width: 227px" class="validate[required] text-input">
							<option value="">请选择</option>
							<option value="1">制造</option>
							<option value="2">物流</option>
					</select> <span style="color: red;">*</span></td>
					<th align="right">所属分类:&nbsp;</th>
					<td><input type="hidden" id="newParentId" /> <input id="newCatalog" type="text" size="30px" readonly="readonly" /> <input class="btn" type="button" value="选择" onclick="chooseSupplierCategory();" /> <span style="color: red;">*</span></td>
				</tr> -->
				<tr height="40">
					<th align="right">开户银行:&nbsp;</th>
					<td><input id="newOpeningBank" name="supplier.openingBank" size="30px" type="text" /></td>
					<th align="right">银行帐号:&nbsp;</th>
					<td><input id="newBankAccount" name="supplier.bankAccount" size="30px" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">联系人:&nbsp;</th>
					<td><input id="newContacts" name="supplier.contacts" size="30px" type="text" /></td>
					<th align="right">联系电话:&nbsp;</th>
					<td><input id="newTelephone" name="supplier.telephone" size="30px" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">传真:&nbsp;</th>
					<td><input id="newPortraiture" name="supplier.portraiture" size="30px" type="text" /></td>
					<th align="right">邮件:&nbsp;</th>
					<td><input id="newEmail" name="supplier.email" size="30px" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">地址:&nbsp;</th>
					<td colspan="3"><textarea id="newDeliveryAddress" rows="2" style="width: 500px"></textarea></td>
				</tr>
			</table>
		</div>
	</form>
</div>