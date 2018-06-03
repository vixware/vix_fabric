<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function saveOrUpdateCurrentExpense() {
		if ($('#currentExpenseForm').validationEngine('validate')) {
			$.post('${vix}/oa/expensesSummaryDepartmentAction!saveOrUpdate.action', {
			'currentExpense.id' : $("#currentExpenseId").val(),
			'currentExpense.expensesPerson' : $("#expensesPerson").val(),
			'currentExpense.creator' : $("#creator").val(),
			'currentExpense.department' : $("#department").val(),
			'currentExpense.createTime' : $("#createTime").val(),
			'currentExpense.expensesAmount' : $("#expensesAmount").val(),
			'currentExpense.invoiceNumber' : $("#invoiceNumber").val(),
			'currentExpense.memo' : $("#memo").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/oa/expensesSummaryDepartmentAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#currentExpenseForm").validationEngine();

	function loadCurrentExpenseDetail() {
		$.ajax({
		url : '${vix}/oa/currentExpenseDetailAction!goListContent.action?id=' + $("#currentExpenseId").val(),
		cache : false,
		success : function(html) {
			$("#currentExpenseDetailTable").html(html);
		}
		});
	};
	loadCurrentExpenseDetail();
	function goSaveOrUpdateCurrentExpenseDetail(id, currentExpenseId) {
		$.ajax({
		url : '${vix}/oa/currentExpenseDetailAction!goSaveOrUpdate.action?id=' + id + "&currentExpenseId=" + currentExpenseId,
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 750,
			height : 300,
			title : "",
			html : html,
			callback : function(action) {
				if (action == 'ok') {
					if ($('#currentExpenseDetailForm').validationEngine('validate')) {
						$.post('${vix}/oa/currentExpenseDetailAction!saveOrUpdate.action', {
						'currentExpenseDetail.id' : $("#currentExpenseDetailId").val(),
						'currentExpenseDetail.currentExpense.id' : $("#currentExpenseId").val(),
						'currentExpenseDetail.expenseAccount.id' : $("#expenseAccountId").val(),
						'currentExpenseDetail.expensesDate' : $("#expensesDate").val(),
						'currentExpenseDetail.expensesStandard' : $("#expensesStandard").val(),
						'currentExpenseDetail.expensesMonth' : $("#expensesMonth").val(),
						'currentExpenseDetail.budgetAmount' : $("#budgetAmount").val(),
						'currentExpenseDetail.availableBudget' : $("#availableBudget").val(),
						'currentExpenseDetail.expensesAmountDetail' : $("#expensesAmountDetail").val(),
						'currentExpenseDetail.invoiceNumberDetail' : $("#invoiceNumberDetail").val(),
						'currentExpenseDetail.memo' : $("#currentExpenseDetailMemo").val()
						}, function(result) {
							showMessage(result);
							setTimeout("", 1000);
							loadCurrentExpenseDetail();
						});
					} else {
						return false;
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
				<li><a href="#"><img src="${vix}/common/img/oa_claims_management.png" alt="" /> 协同办公 </a></li>
				<li><a href="#">行政办公 </a></li>
				<li><a href="#">报销管理 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/expensesSummaryDepartmentAction!goList.action');">日常费用报销 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="currentExpenseId" name="currentExpenseId" value="${currentExpense.id}" />
<div class="content">
	<form id="currentExpenseForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateCurrentExpense();"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/oa/expensesSummaryDepartmentAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b>日常费用报销 </b><i></i> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b><strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">报销人：</td>
											<td><input id="expensesPerson" name="expensesPerson" value="${currentExpense.expensesPerson }" type="text" size="20" class="validate[required] text-input"><span style="color: red;">*</span></td>
											<td align="right">制单人：</td>
											<td><input id="creator" name="creator" value="${currentExpense.creator }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">部门：</td>
											<td><input id="department" name="department" value="${currentExpense.department }" type="text" size="30" /></td>
											<td align="right">报销日期：</td>
											<td><input id="createTime" name="createTime" value="${currentExpense.createTime}" type="text" class="validate[required] text-input" readonly="readonly" /><img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">备注：</td>
											<td><input id="memo" name="memo" value="${currentExpense.memo }" type="text" size="30" /></td>
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
							<li class="current"><a onclick="javascript:tab(2,1,'a',event);loadCurrentExpenseDetail();"><img alt="" src="img/mail.png">费用报销明细</a></li>
						</ul>
					</div>
					<div id="a1" class="right_content" style="display: block;">
						<div class="list_table" style="margin: 0; width: 100%">
							<label> <input class="btn" type="button" value="添加明细" onclick="goSaveOrUpdateCurrentExpenseDetail(0,$('#currentExpenseId').val());" style="width: 65px; margin-top: 10px; margin-left: 15px;" /></label>
						</div>
						<div style="padding: 8px;">
							<div id="currentExpenseDetailTable" class="list_table" style="margin: 0; width: 100%"></div>
						</div>
					</div>
				</div>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th align="right">总计：</th>
											<td><input id="expensesAmount" name="expensesAmount" value="${currentExpense.expensesAmount }" type="text" />元</td>
											<th align="right">发票：</th>
											<td><input id="invoiceNumber" name="invoiceNumber" value="${currentExpense.invoiceNumber }" type="text" />张</td>
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