<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function saveOrUpdateTravelExpense(tag) {
		if ($('#travelExpenseForm').validationEngine('validate')) {
			$.post('${vix}/oa/travelClaimsAction!saveOrUpdate.action', {
			'travelExpense.id' : $("#travelExpenseId").val(),
			'travelExpense.organizationUnit.id' : $("#organizationUnitId").val(),
			'travelExpense.name' : $("#name").val(),
			'travelExpense.employee.id' : $("#employeeId").val(),
			'travelExpense.createTime' : $("#createTime").val(),
			'travelExpense.creator' : $("#creator").val(),
			'travelExpense.memo' : $("#memo").val(),
			'tag' : tag
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/oa/travelClaimsAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#travelExpenseForm").validationEngine();
	function saveOrUpdateTravelExpenseDetail(id, priceType) {
		var url = '';
		var title = '';
		if (priceType == 'transportCosts') {
			url = '${vix}/oa/transportCostsAction!goSaveOrUpdate.action?id=' + id;
			title = '交通费用';
		} else if (priceType == 'accommodationFee') {
			url = '${vix}/oa/accommodationFeeAction!goSaveOrUpdate.action?id=' + id;
			title = '住宿费用';
		} else {
			url = '${vix}/oa/currentExpenseDetailAction!goSaveOrUpdate.action?id=' + id;
			title = '日常费用';
		}
		$.ajax({
		url : url,
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 880,
			height : 440,
			title : title,
			html : html,
			callback : function(action) {
				if (action == 'ok') {
					if (priceType == 'transportCosts') {
						$.post('${vix}/oa/transportCostsAction!saveOrUpdate.action', {
						'transportCosts.id' : $("#transportCostsId").val(),
						'transportCosts.travelExpense.id' : $("#travelExpenseId").val(),
						'transportCosts.orgPosition.id' : $("#parentPosId").val(),
						'transportCosts.areaLevel.id' : $("#areaLevelId").val(),
						'transportCosts.transport.id' : $("#transportId").val(),
						'transportCosts.city' : $("#city").val(),
						'transportCosts.type' : $("#type").val(),
						'transportCosts.fromPlace' : $("#fromPlace").val(),
						'transportCosts.toPlace' : $("#toPlace").val(),
						'transportCosts.vehicle' : $("#vehicle").val(),
						'transportCosts.usingTime' : $("#usingTime").val(),
						'transportCosts.cost' : $("#transportCost").val()
						}, function(result) {
							loadTravelExpenseDetail('transportCosts');
						});
					} else if (priceType == 'accommodationFee') {
						$.post('${vix}/oa/accommodationFeeAction!saveOrUpdate.action', {
						'accommodationFee.id' : $("#accommodationFeeId").val(),
						'accommodationFee.travelExpense.id' : $("#travelExpenseId").val(),
						'accommodationFee.areaLevel.id' : $("#areaLevelId").val(),
						'accommodationFee.orgPosition.id' : $("#parentPosId").val(),
						'accommodationFee.name' : $("#name").val(),
						'accommodationFee.department' : $("#department").val(),
						'accommodationFee.lodgingFee' : $("#lodgingFee").val(),
						'accommodationFee.housePrices' : $("#housePrices").val(),
						'accommodationFee.days' : $("#days").val(),
						'accommodationFee.startAccomodationsTime' : $("#startAccomodationsTime").val(),
						'accommodationFee.endAccomodationsTime' : $("#endAccomodationsTime").val(),
						'accommodationFee.accomodationsPlaces' : $("#accomodationsPlaces").val(),
						'accommodationFee.cost' : $("#accommodationCost").val()
						}, function(result) {
							loadTravelExpenseDetail('accommodationFee');
						});
					} else {
						$.post('${vix}/oa/currentExpenseDetailAction!saveOrUpdate.action', {
						'currentExpenseDetail.id' : $("#currentExpenseDetailId").val(),
						'currentExpenseDetail.travelExpense.id' : $("#travelExpenseId").val(),
						'currentExpenseDetail.expenseAccount.id' : $("#expenseAccountId").val(),
						'currentExpenseDetail.orgPosition.id' : $("#parentPosId").val(),
						'currentExpenseDetail.areaLevel.id' : $("#areaLevelId").val(),
						'currentExpenseDetail.expensesDate' : $("#expensesDate").val(),
						'currentExpenseDetail.expensesStandard' : $("#expensesStandard").val(),
						'currentExpenseDetail.expensesMonth' : $("#expensesMonth").val(),
						'currentExpenseDetail.budgetAmount' : $("#budgetAmount").val(),
						'currentExpenseDetail.availableBudget' : $("#availableBudget").val(),
						'currentExpenseDetail.expensesAmountDetail' : $("#expensesAmountDetail").val(),
						'currentExpenseDetail.invoiceNumberDetail' : $("#invoiceNumberDetail").val(),
						'currentExpenseDetail.memo' : $("#currentExpenseDetailMemo").val()
						}, function(result) {
							loadTravelExpenseDetail('currentExpense');
						});
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
	function deleteTravelExpenseDetail(id, priceType) {
		var url = '';
		if (priceType == 'transportCosts') {
			url = '${vix}/oa/transportCostsAction!deleteById.action?id=' + id;
		} else if (priceType == 'accommodationFee') {
			url = '${vix}/oa/accommodationFeeAction!deleteById.action?id=' + id;
		} else {
			url = '${vix}/oa/currentExpenseDetailAction!deleteById.action?id=' + id;
		}
		asyncbox.confirm('确定要删除该明细么?', '<s:text name='vix_message'/>', function(action) {
			if (action == 'ok') {
				$.ajax({
				url : url,
				cache : false,
				success : function(html) {
					asyncbox.success(html, "提示信息", function(action) {
						loadTravelExpenseDetail(priceType);
					});
				}
				});
			}
		});
	}
	function loadTravelExpenseDetail(priceType) {
		var url = '';
		if (priceType == 'transportCosts') {
			url = '${vix}/oa/transportCostsAction!goListContent.action?id=' + $("#travelExpenseId").val();
		} else if (priceType == 'accommodationFee') {
			url = '${vix}/oa/accommodationFeeAction!goListContent.action?id=' + $("#travelExpenseId").val();
		} else {
			url = '${vix}/oa/currentExpenseDetailAction!goListContent.action?id=' + $("#travelExpenseId").val();
		}
		$.ajax({
		url : url,
		cache : false,
		success : function(html) {
			if (priceType == 'transportCosts') {
				$("#transportCosts").html(html);
			} else if (priceType == 'accommodationFee') {
				$("#accommodationFee").html(html);
			} else {
				$("#currentExpense").html(html);
			}
		}
		});
	};
	loadTravelExpenseDetail('accommodationFee');

	function chooseEmployees() {
		$.ajax({
		url : '${vix}/common/select/commonSelectEmpAction!goList.action',
		data : {
			chkStyle : "checkbox"
		},
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 800,
			height : 600,
			title : "选择人员",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var selectIds = "";
						var selectNames = "";
						var result = returnValue.split(",");
						for (var i = 0; i < result.length; i++) {
							if (result[i].length > 1) {
								var v = result[i].split(":");
								selectIds += "," + v[0];
								selectNames = v[1];
								$("#employeeId").val(v[0]);
								$("#employeeName").val(v[1]);
							}
						}
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}

	$(function() {
		//加载创建时间(新增)
		if (document.getElementById("createTime").value == "") {
			var myDate = new Date();
			$("#createTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate() + " " + myDate.getHours() + ":" + myDate.getMinutes() + ":" + myDate.getSeconds());
		}
	});
	/**
	 * 选择部门
	 */
	function chooseBulletinOrgUnit() {
		$.ajax({
		url : '${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action',
		data : {
		chkStyle : "checkbox",
		canCheckComp : 0
		},
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 560,
			height : 340,
			title : "选择部门",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						alert(returnValue);
						var selectIds = "";
						var selectNames = "";

						var resObj = $.parseJSON(returnValue);

						for (var i = 0; i < resObj.length; i++) {
							selectIds += "," + resObj[i].treeId;
							selectNames += "," + resObj[i].name;
						}

						if (selectIds != '') {
							alert(selectIds);
							selectIds = selectIds.substring(1, selectIds.length - 1);
							selectNames = selectNames.substring(1);
							alert(selectIds)
							$("#organizationUnitId").val(selectIds);
							$("#organizationUnitName").val(selectNames);
						}
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
				<li><a href="#" onclick="loadContent('${vix}/oa/travelClaimsAction!goList.action');">报销管理 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="travelExpenseId" name="travelExpenseId" value="${travelExpense.id}" />
<div class="content">
	<form id="travelExpenseForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="goAudit('${taskId}');" href="###"><img width="22" height="22" title="提交审批" src="${vix}/common/img/dt_submit.png" /></a><a href="#" onclick="loadContent('${vix}/common/model/jobTodoAction!goList.action?pageNo=${pageNo}');"> <img width="22" height="22" title="返回"
							src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b>报销单</b></strong>
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
											<td align="right">主题：</td>
											<td><input id="name" name="name" value="${travelExpense.name }" type="text" size="20" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<td align="right">报销人：</td>
											<td><input id="employeeName" name="employeeName" value="${travelExpense.employee.name }" type="text" size="30"><input class="btn" type="button" value="选择" onclick="chooseEmployees();" /> <s:hidden id="employeeId" name="employeeId" value="%{travelExpense.employee.id}" theme="simple" /></td>
										</tr>
										<tr>
											<td align="right">部门：</td>
											<td><input type="hidden" id="organizationUnitId" name="organizationUnitId" value="${travelExpense.organizationUnit.id}" /> <input type="text" id="organizationUnitName" name="organizationUnitName" readonly="readonly" value="${travelExpense.organizationUnit.fs}" /> <input class="btn" type="button" value="选择"
												onclick="chooseBulletinOrgUnit();" /></td>
											<td align="right">报销日期：</td>
											<td><input id="createTime" name="createTime" value="${travelExpense.createTime}" type="text" class="validate[required] text-input" readonly="readonly" /> <img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th align="right">备注：</th>
											<td colspan="3"><textarea id="memo" name="memo" style="width: 525px; height: 100px; margin-top: 5px;">${travelExpense.memo }</textarea></td>
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
							<li class="current"><a onclick="javascript:tab(3,1,'a',event);loadTravelExpenseDetail('accommodationFee');"><img alt="" src="img/mail.png">住宿费用</a></li>
							<li class=""><a onclick="javascript:tab(3,2,'a',event);loadTravelExpenseDetail('transportCosts');"><img alt="" src="img/mail.png">交通费用</a></li>
							<li class=""><a onclick="javascript:tab(3,3,'a',event);loadTravelExpenseDetail('currentExpense');"><img alt="" src="img/mail.png">日常费用</a></li>
						</ul>
					</div>
					<div id="a1" class="right_content" style="display: block;">
						<div class="list_table" style="margin: 0; width: 100%">
							<label> <input class="btn" type="button" value="添加明细" onclick="saveOrUpdateTravelExpenseDetail(0,'accommodationFee');" style="width: 65px; margin-top: 10px; margin-left: 15px;" /></label>
						</div>
						<div style="padding: 8px;">
							<div id="accommodationFee" class="list_table" style="margin: 0; width: 100%"></div>
						</div>
					</div>
					<div id="a2" class="right_content" style="display: none;">
						<div class="list_table" style="margin: 0; width: 100%">
							<label> <input class="btn" type="button" value="添加明细" onclick="saveOrUpdateTravelExpenseDetail(0,'transportCosts');" style="width: 65px; margin-top: 10px; margin-left: 15px;" /></label>
						</div>
						<div style="padding: 8px;">
							<div id="transportCosts" class="list_table" style="margin: 0; width: 100%"></div>
						</div>
					</div>
					<div id="a3" class="right_content" style="display: none;">
						<div class="list_table" style="margin: 0; width: 100%">
							<label> <input class="btn" type="button" value="添加明细" onclick="saveOrUpdateTravelExpenseDetail(0,'currentExpense');" style="width: 65px; margin-top: 10px; margin-left: 15px;" /></label>
						</div>
						<div style="padding: 8px;">
							<div id="currentExpense" class="list_table" style="margin: 0; width: 100%"></div>
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
											<td align="right">制单人：</td>
											<td><input id="creator" name="creator" value="${travelExpense.creator }" type="text" size="30" /></td>
											<td align="right">合计：</td>
											<td><input id="amount" name="amount" value="${travelExpense.amount }" type="text" size="20" /></td>
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
