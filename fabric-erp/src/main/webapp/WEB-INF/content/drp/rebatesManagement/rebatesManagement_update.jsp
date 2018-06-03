<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdateReturnBill() {
		if ($('#returnBillForm').validationEngine('validate')) {
			$.post('${vix}/drp/rebatesManagementAction!saveOrUpdate.action', {
			'returnBill.id' : $("#id").val(),
			'returnBill.rbCode' : $("#rbCode").val(),
			'returnBill.refundRule.id' : $("#refundRuleId").val(),
			'returnBill.rbTitle' : $("#rbTitle").val(),
			'returnBill.customerAccount.id' : $("#customerAccountId").val(),
			'returnBill.itemCode' : $("#itemCode").val(),
			'returnBill.itemName' : $("#itemName").val(),
			'returnBill.returnAmount' : $("#returnAmount").val(),
			'returnBill.currencyType.id' : $("#currencyTypeId").val(),
			'updateField' : updateField,
			'returnBill.rbDate' : $("#rbDate").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/rebatesManagementAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#returnBillForm").validationEngine();
	/** 选择返点规则 */
	function chooseSalesOrder() {
		$.ajax({
		url : '${vix}/drp/rebatesManagementAction!goRefundRule.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 750,
			height : 450,
			title : "选择返点规则",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						$("#refundRuleId").val(result[0]);
						$("#refundRuleCode").val(result[1]);
						$.ajax({
						url : '${vix}/drp/rebatesManagementAction!returnPayMentAmount.action?refundRuleId=' + result[0] + '&customerAccountId=' + $("#customerAccountId").val(),
						cache : false,
						success : function(json) {
							$("#returnAmount").val(json);
						}
						});
					} else {
						$("#refundRuleId").val("");
						$("#refundRuleCode").val("");
						asyncbox.success("请选择订单信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
	function chooseCustomerAccount() {
		$.ajax({
		url : '${vix}/drp/membershipCardmanagementAction!goChooseCustomerAccount.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 1000,
			height : 500,
			title : "选择会员",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var rv = returnValue.split(",");
						$("#customerAccountId").val(rv[0]);
						$("#customerAccountName").val(rv[1]);
						$("#customerAccountCode").val(rv[2]);
					} else {
						asyncbox.success("请选择会员信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_distribution_management.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#">经销商管理 </a></li>
				<li><a href="#">返点管理 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/rebatesManagementAction!goList.action');">返款单 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${returnBill.id}" />
<div class="content">
	<form id="returnBillForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateReturnBill()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/drp/rebatesManagementAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>新增</b></strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b><strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table>
										<tr height="30">
											<td align="right">编码:&nbsp;</td>
											<td><input id="rbCode" name="rbCode" value="${returnBill.rbCode}" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">主题:&nbsp;</td>
											<td><input id="rbTitle" name="rbTitle" value="${returnBill.rbTitle}" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr height="30">
											<td align="right">会员名称:&nbsp;</td>
											<td><input id="customerAccountName" name="customerAccountName" value="${returnBill.customerAccount.name }" onchange="fieldChanged(this);" type="text" class="validate[required] text-input" readonly="readonly" /><input type="hidden" id="customerAccountId" name="customerAccountId" value="${returnBill.customerAccount.id}" /> <input
												class="btn" type="button" value="选择" onclick="chooseCustomerAccount();" /> <span style="color: red;">*</span></td>
											<td align="right">规则编码:&nbsp;</td>
											<td><input type="text" id="refundRuleCode" name="refundRuleCode" value="${returnBill.refundRule.code}" readonly="readonly" onchange="fieldChanged(this);" /> <s:hidden id="refundRuleId" name="refundRuleId" value="%{returnBill.refundRule.id}" theme="simple" /><input class="btn" type="button" value="选择" onclick="chooseSalesOrder();" /></td>
										</tr>
										<tr height="40">
											<td align="right">返款金额:&nbsp;</td>
											<td><input id="returnAmount" name="returnAmount" value="${returnBill.returnAmount}" class="validate[required] text-input" size="30" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td align="right">币种:&nbsp;</td>
											<td><s:select id="currencyTypeId" name="returnBill.currencyType.id" headerKey="-1" headerValue="--请选择--" list="%{currencyTypeList}" listValue="name" onchange="fieldChanged(this);" listKey="id" value="%{returnBill.currencyType.id}" multiple="false" theme="simple"></s:select></td>
										</tr>
										<tr height="30">
											<td align="right">返款日期：</td>
											<td><input id="rbDate" name="rbDate" value="<s:date format="yyyy-MM-dd" name="%{returnBill.rbDate}" />" type="text" class="validate[required] text-input" readonly="readonly" size="30" onchange="fieldChanged(this);" /><img onclick="showTime('rbDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"><span style="color: red;">*</span></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>