<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#billsCategoryForm").validationEngine();
	function changeDisplay(a) {
		$('#categoryName').val(a.value);
		billsPropertyDictionary(a.value);
	}
	function chooseParentOrganization() {
		$.ajax({
		url : '${vix}/drp/distributionSystemRelationShipAction!goChooseOrganization.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 560,
			height : 340,
			title : "选择父公司",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						$("#companyCode").val(result[3]);
					} else {
						$("#companyCode").val("");
						asyncbox.success("请选择公司信息!", "提示信息");
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
	<s:hidden id="billsCategoryId" name="billsCategoryId" value="%{billsCategory.id}" theme="simple" />
	<form id="billsCategoryForm">
		<div class="order">
			<dl>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dd style="display: block;">
									<table>
										<tr height="40">
											<td align="right">公司编码:&nbsp;</td>
											<td><input type="text" id="companyCode" name="companyCode" value="${billsCategory.companyCode}" readonly="readonly" /><input class="btn" type="button" value="选择" onclick="chooseParentOrganization();" /></td>
										</tr>
										<tr height="40">
											<td align="right">分类编码:&nbsp;</td>
											<td><s:select list="billsCategoryDictionaryList" id="billsCategoryDictionaryCategoryCode" name="categoryCode" listKey="categoryCode" listValue="categoryCode" headerKey="" headerValue="--请选择分类--" theme="simple" value="categoryName" onchange="javascript:changeDisplay(this);" /> <span style="color: red;">*</span></td>
											<td align="right">分类名称:&nbsp;</td>
											<td><s:select id="categoryName" headerKey="-1" headerValue="--请选择--" list="billsCategoryDictionaryList" listValue="categoryName" listKey="categoryCode" multiple="false" theme="simple" disabled="true">
												</s:select></td>
										</tr>
										<tr height="40">
											<td align="right">分类描述:&nbsp;</td>
											<td colspan="3"><input type="text" id="categoryDescription" name="categoryDescription" size="45" value="${billsCategory.categoryDescription}" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
				<div class="clearfix" style="background: #FFF; position: relative;">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />单据性质</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
							$(function() {
								billsPropertyDictionary($('#billsCategoryDictionaryCategoryCode').val);
							});
							function billsPropertyDictionary(categoryCode) {
								$('#dlAddress2').datagrid({
								url : '${vix}/system/billTypeManagementAction!getBillsPropertyDictionaryJson.action?billsCategoryDictionaryCategoryCode=' + categoryCode,
								width : 'auto',
								height : 250,
								pagination : true,
								rownumbers : true,
								fitColumns : true,
								sortOrder : 'desc',
								striped : true,
								columns : [ [ {
								field : 'id',
								title : '编号',
								width : 60,
								hidden : true,
								align : 'center'
								}, {
								field : 'propertyCode',
								title : '编码',
								width : 100,
								align : 'center'
								}, {
								field : 'propertyName',
								title : '名称',
								width : 100,
								align : 'center'
								}, {
								field : 'propertyDescription',
								title : '描述',
								width : 100,
								align : 'center'
								} ] ]
								});
							}
						</script>
						<div style="padding: 8px;">
							<table id="dlAddress2"></table>
						</div>
					</div>
				</div>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>
