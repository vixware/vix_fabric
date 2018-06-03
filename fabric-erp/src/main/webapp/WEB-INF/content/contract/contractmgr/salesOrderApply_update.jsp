<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script src="${vix}/common/core/js/all.js" type="text/javascript"></script>
<script type="text/javascript">
	/** input 获取焦点input效果绑定  */
	$(".order_table input[type='text']").focusin(function() {
		$(this).css({
		'border' : '1px solid #f00',
		'background' : '#f5f5f5'
		});
	});
	$(".order_table  input[type='text']").focusout(function() {
		$(this).css({
		'border' : '1px solid #ccc',
		'background' : '#fff'
		});
	});
	var updateField = "";
	function salesOrderFieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}

	//选择客户
	function chooseRadioSupplier1() {
		$.ajax({
		url : '${vix}/contract/contractDraftingAction!goSubRadioSingleList1.action',
		cache : false,
		success : function(html) {
			$(".ab_outer .list td input[type='checkbox']").css("margin-left", 10);
			$(".ab_c .content").css("margin-bottom", "0");
			$('.ab_c .content').parent().css('position', 'relative');
			asyncbox.open({
			modal : true,
			width : 1000,
			height : 500,
			title : "选择客户信息",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var rv = returnValue.split(",");
						$("#secondPartyId").val(rv[0]);
						$("#secondParty").val(rv[1]);
					} else {
						$("#secondParty").val("");
						asyncbox.success("请选择客户信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
	//选择项目名称
	function chooseProjectName() {
		$.ajax({
		url : '${vix}/contract/contractDraftingAction!goProjectNameList.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 1000,
			height : 500,
			title : "选择项目名称",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var rv = returnValue.split(",");
						$("#id").val(rv[0]);
						$("#firstParty").val(rv[1]);
					} else {
						$("#firstParty").val("");
						asyncbox.success("请选择分类信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}

	/**
	 * 选择部门
	 */
	function chooseParentOrganization() {
		$.ajax({
		url : '${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action',
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
						//alert(returnValue);
						var selectIds = "";
						var selectNames = "";

						var resObj = $.parseJSON(returnValue);
						var pubIdTmp = $("#pubIds").val();

						pubIdTmp = pubIdTmp + ",";
						for (var i = 0; i < resObj.length; i++) {
							if (resObj[i].treeType != "O") {
								asyncbox.alert("只能选择部门信息！", "提示");
								return;
							}
							if (!pubIdTmp.contains(resObj[i].treeId + ",")) {
								selectIds += "," + resObj[i].treeId;
								selectNames += "," + resObj[i].name;
							}
						}
						selectIds = $("#pubIds").val() + selectIds;
						selectNames = $("#departmentName").val() + selectNames;

						$("#pubIds").val(selectIds);
						selectNames = selectNames.substring(1, selectNames.length);
						$("#departmentName").val(selectNames);
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}

	/** 快速新建供应商 */
	function createSupplier() {
		$.ajax({
		url : '${vix}/contract/contractDraftingAction!goAddQuicklySupplier1.action?',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 700,
			height : 400,
			title : "快速客户信息",
			html : html,
			callback : function(action) {
				if (action == 'ok') {
					if ($('#supplierForm').validationEngine('validate')) {
						$.post('${vix}/contract/contractDraftingAction!saveOrUpdateSupplier1.action', {
						'customerAccount.id' : $("#srmId").val(),
						'customerAccount.code' : $("#code").val(),
						'customerAccount.name' : $("#name").val(),
						'customerAccount.msnAccount' : $("#msnAccount").val()
						}, function(result) {
							var rt = result.split(",");
							showMessage(rt[0] + rt[3]);
							setTimeout("", 1000);
							$("#code").val(rt[1]);
							$("#name").val(rt[2]);
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

	/** 保存合同 */
	function saveOrUpdateOrder() {
		/**合同预警时间  */
		var warnningTime = $("#warnningTime").val();
		/**合同项  */
		var dlData = $("#dlLineItem").datagrid("getRows");
		var dlJson = JSON.stringify(dlData);
		/** 合同预警 */
		var dpData = $("#dlDeliveryPlan").datagrid("getRows");
		var dpJson = JSON.stringify(dpData);
		/** 合同审批 */
		var spData = $("#dlInvoice").datagrid("getRows");
		var spJson = JSON.stringify(spData);
		/** 价格条件 */
		var jgData = $("#dlInvoice1").datagrid("getRows");
		var jgJson = JSON.stringify(jgData);

		if ($('#orderForm').validationEngine('validate')) {
			$.post('${vix}/contract/contractDraftingAction!saveOrUpdate.action', {
			'contract.id' : $("#id").val(),
			'contract.contractCode' : $("#contractCode").val(),
			'contract.contractName' : $("#contractName").val(),
			'contract.projectCode' : $("#projectCode").val(),
			'contract.projectName' : $("#projectName").val(),
			'contract.departmentName' : $("#departmentName").val(),
			'contract.email' : $("#email").val(),
			'contract.signDate' : $("#signDate").val(),
			'contract.contractStartTime' : $("#contractStartTime").val(),
			'contract.contractEndTime' : $("#contractEndTime").val(),
			'contract.operator' : $("#operator").val(),
			'contract.negotiator' : $("#negotiator").val(),
			'contract.approval' : $("#approval").val(),
			'contract.prepaymentAmount' : $("#prepaymentAmount").val(),
			'contract.outstandingAmounts' : $("#outstandingAmounts").val(),
			'contract.partyUnitName' : $("#partyUnitName").val(),
			'contract.partyCAddress' : $("#partyCAddress").val(),
			'contract.partyCUnitName' : $("#partyCUnitName").val(),
			'contract.partyCContact' : $("#partyCContact").val(),
			'contract.partyCPhone' : $("#partyCPhone").val(),
			'contract.partyCEmail' : $("#partyCEmail").val(),
			'contract.masterContractCoding' : $("#masterContractCoding").val(),
			'contract.contractExecutionMoney' : $("#contractExecutionMoney").val(),
			'contract.retentionCalculation' : $("#retentionCalculation").val(),
			'contract.retentionsStartDate' : $("#retentionsStartDate").val(),
			'contract.retentionEndDate' : $("#retentionEndDate").val(),
			'contract.retentionRatio' : $("#retentionRatio").val(),
			'contract.warrantyAmount' : $("#warrantyAmount").val(),
			'contract.warranty' : $("#warranty").val(),
			'contract.agingcontrol' : $("#agingcontrol").val(),
			'contract.agingControllink' : $("#agingControllink").val(),
			'contract.businessType' : $("#businessType").val(),
			'contract.contractTotalQuantity' : $("#contractTotalQuantity").val(),
			'contract.numberContractExecution' : $("#numberContractExecution").val(),
			'contract.enableStage' : $("#enableStage").val(),
			'contract.contractStageGroup' : $("#contractStageGroup").val(),
			'contract.approvalDate' : $("#approvalDate").val(),
			'contract.firstParty' : $("#firstParty").val(),
			'contract.firstPartyId' : $("#firstPartyId").val(),
			'contract.secondParty' : $("#secondParty").val(),
			'contract.secondPartyId' : $("#secondPartyId").val(),
			'contract.thirdParty' : $("#thirdParty").val(),
			'contract.firstPartyAddress' : $("#firstPartyAddress").val(),
			'contract.firstPartyContact' : $("#firstPartyContact").val(),
			'contract.firstPartyPhone' : $("#firstPartyPhone").val(),
			'contract.firstPartyEmail' : $("#firstPartyEmail").val(),
			'contract.totalAmount' : $("#totalAmount").val(),
			'contract.mode' : $("#mode").val(),
			'contract.isbid' : $("#isbid").val(),
			'contract.mainContent' : contents.html(),
			'contract.remark' : $("#remark").val(),
			'contract.baddress' : $("#bAddress").val(),
			'contract.bunitName' : $("#bUnitName").val(),
			'contract.bcontact' : $("#bContact").val(),
			'contract.bphone' : $("#bPhone").val(),
			'contract.contractType' : $("#contractType").val(),
			'warnningTime' : warnningTime,
			//'contract.contractGroupType.id' : $("#contractGroupTypeId").val(),
			//'contract.contractTypeCombine.id' : $("#contractTypeCombineId").val(),
			//'contract.contractNatureType.id' : $("#contractNatureTypeId").val(),
			//'contract.viewIndustryType.id' : $("#viewIndustryTypeId").val(),
			//'contract.modeType.id' : $("#modeTypeId").val(),
			//'contract.enableStageType.id' : $("#enableStageTypeId").val(),
			//'contract.contractStageGroupType.id' : $("#contractStageGroupTypeId").val(),
			//'contract.currencyType.id' : $("#currencyTypeId").val(),
			//'contract.exchangeRate.id' : $("#exchangeRateId").val(),
			'dlJson' : dlJson,
			'dpJson' : dpJson,
			'spJson' : spJson,
			'jgJson' : jgJson
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/contract/contractDraftingAction!goList.action');
			});
		}
	}

	$("#orderForm").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/ctm_contract.png" alt="" /> <s:text name="cmn_supplyChain" /> </a></li>
				<li><a href="#"><s:text name="ctm_agreement" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/contract/contractDraftingAction!goList.action?pageNo=${pageNo}');"><s:text name="合同查询" /> </a></li>
				<li><a href="#"><s:text name="新增销售合同" /> </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${contract.id}" />
<input type="hidden" id="contractType" name="contractType" value="${contract.contractType}" />
<div class="content">
	<form id="orderForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder();"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <%-- <a href="#"><img width="22" height="22" title="保存并提交审批" src="${vix}/common/img/dt_submit.png" /></a>  --%>
						<a href="#" onclick="loadContent('${vix}/contract/contractDraftingAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="新增销售合同" /> </b><i></i> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /> </a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /> </a> <a href="#"><img
											src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /> </a>
									</span> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th align="right">主合同编码：</th>
											<td><input id="masterContractCoding" name="masterContractCoding" value="${contract.masterContractCoding}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<th align="right">所属合同组：</th>
											<td><s:select id="contractGroupTypeId" headerKey="-1" headerValue="--请选择--" list="%{contractGroupTypeList}" listValue="name" listKey="id" value="%{contract.contractGroupType.id}" multiple="false" theme="simple">
												</s:select></td>
										</tr>
										<tr>
											<th align="right">合同编码：</th>
											<td><input id="contractCode" name="contractCode" value="${contract.contractCode}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<th align="right">合同名称：</th>
											<td><input id="contractName" name="contractName" value="${contract.contractName}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th align="right">项目编号：</th>
											<td><input id="projectCode" name="projectCode" value="${contract.projectCode}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<th align="right">项目名称：</th>
											<td><input id="projectName" name="projectName" value="${contract.projectName}" type="text" size="18" class="validate[required] text-input" /> <span style="color: red;">*</span> <input type="hidden" id="projectName" name="projectName" value="${contract.id}" /> <input class="btn" type="button" value="选择"
												onclick="chooseProjectName();" /></td>
										</tr>

										<tr>
											<td align="right">类型：</td>
											<td><s:select id="contractTypeCombineId" headerKey="-1" headerValue="--请选择--" list="%{contractTypeCombineList}" listValue="name" listKey="id" value="%{contract.contractTypeCombine.id}" multiple="false" theme="simple">
												</s:select><span style="color: red;">*</span></td>
											<th align="right">合同性质：</th>
											<td><s:select id="contractNatureTypeId" headerKey="-1" headerValue="--请选择--" list="%{contractNatureTypeList}" listValue="name" listKey="id" value="%{contract.contractNatureType.id}" multiple="false" theme="simple">
												</s:select><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th align="right">所属行业：</th>
											<td><s:select id="viewIndustryTypeId" headerKey="-1" headerValue="--请选择--" list="%{viewIndustryList}" listValue="name" listKey="id" value="%{contract.viewIndustryType.id}" multiple="false" theme="simple">
												</s:select><span style="color: red;">*</span></td>
											<th align="right">合同签订日期：</th>
											<td><input id="signDate" name="signDate" value="<fmt:formatDate value='${contract.signDate}' type='text' pattern='yyyy-MM-dd' />" type="text" size="30" /> <img onclick="showTime('signDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th align="right">合同开始日期：</th>
											<td><input id="contractStartTime" name="contractStartTime" value="<fmt:formatDate value='${contract.contractStartTime}' type='text' pattern='yyyy-MM-dd' />" type="text" size="30" /> <img onclick="showTime('contractStartTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<th align="right">合同终止日期：</th>
											<td><input id="contractEndTime" name="contractEndTime" value="<fmt:formatDate value='${contract.contractEndTime}' type='text' pattern='yyyy-MM-dd' />" type="text" size="30" /> <img onclick="showTime('contractEndTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th align="right">经办人：</th>
											<td><input id="operator" name="operator" value="${contract.operator}" type="text" size="30" /></td>
											<th align="right">谈判人：</th>
											<td><input id="negotiator" name="negotiator" value="${contract.negotiator}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th align="right">审批人：</th>
											<td><input id="approval" name="approval" value="${contract.approval}" type="text" size="30" /></td>
											<th align="right">审批日期：</th>
											<td><input id="approvalDate" name="contractEndTime" value="<fmt:formatDate value='${contract.approvalDate}' type='text' pattern='yyyy-MM-dd' />" type="text" size="30" /> <img onclick="showTime('approvalDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th align="right">合同履行状态：</th>
											<td><s:select id="modeTypeId" headerKey="-1" headerValue="--请选择--" list="%{modeTypeList}" listValue="name" listKey="id" value="%{contract.modeType.id}" multiple="false" theme="simple">
												</s:select><span style="color: red;">*</span></td>
											<th align="right">所属部门：</th>
											<td colspan="3"><input type="hidden" id="pubIds" name="pubIds" value="${pubIds}" /> <input type="hidden" id="treeType" name="treeType" value="${treeType}" /> <input type="text" id="departmentName" name="departmentName" readonly="readonly" value="${contract.departmentName}" /> <input class="btn" type="button" value="选择"
												onclick="chooseParentOrganization();" /></td>
										</tr>
										<tr>
											<td align="right">合同总数量：</td>
											<td><input id="contractTotalQuantity" name="contractTotalQuantity" value="${contract.contractTotalQuantity}" type="text" size="30" /></td>
											<td align="right">合同执行数量：</td>
											<td><input id="numberContractExecution" name="numberContractExecution" value="${contract.numberContractExecution}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th align="right">启用阶段：</th>
											<td><s:select id="enableStageTypeId" headerKey="-1" headerValue="--请选择--" list="%{enableStageTypeList}" listValue="name" listKey="id" value="%{contract.enableStageType.id}" multiple="false" theme="simple">
												</s:select></td>
											<th align="right">合同阶段组：</th>
											<td><s:select id="contractStageGroupTypeId" headerKey="-1" headerValue="--请选择--" list="%{contractStageGroupTypeList}" listValue="name" listKey="id" value="%{contract.contractStageGroupType.id}" multiple="false" theme="simple">
												</s:select></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /> </a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /> </a> <a href="#"><img
											src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /></a>
									</span> <strong>合同方</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">甲方：</td>
											<td><input id="firstParty" name="firstParty" value="${contract.firstParty}" type="text" size="30" /></td>
											<td align="right">乙方：</td>
											<td><input id="secondParty" name="secondParty" value="${contract.secondParty}" type="text" size="8" class="validate[required] text-input" /> <span style="color: red;">*</span> <input type="hidden" id="secondPartyId" name="secondPartyId" value="${contract.secondPartyId}" /> <input class="btn" type="button" value="选择信息"
												onclick="chooseRadioSupplier1();" /> <input class="btn" type="button" value="快速新建" onclick="createSupplier();" /></td>
										</tr>
										<tr>
											<th align="right">甲方单位名称：</th>
											<td><input id="partyUnitName" name="partyUnitName" value="${contract.partyUnitName}" type="text" size="30" /></td>
											<th align="right">乙方单位名称：</th>
											<td><input id="bUnitName" name="bUnitName" value="${contract.bunitName}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th align="right">甲方单位地址：</th>
											<td><input id="firstPartyAddress" name="firstPartyAddress" value="${contract.firstPartyAddress}" type="text" size="30" /></td>
											<td align="right">乙方单位地址：</td>
											<td><input id="bAddress" name="bAddress" value="${contract.baddress}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th align="right">甲方联系人：</th>
											<td><input id="firstPartyContact" name="firstPartyContact" value="${contract.firstPartyContact}" type="text" size="30" /></td>
											<th align="right">乙方联系人：</th>
											<td><input id="bContact" name="bContact" value="${contract.bcontact}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th align="right">甲方电话：</th>
											<td><input id="firstPartyPhone" name="firstPartyPhone" value="${contract.firstPartyPhone}" type="text" size="30" class="validate[required,custom[integer]] text-input" /><span style="color: red;">*</span></td>
											<th align="right">乙方电话：</th>
											<td><input id="bPhone" name="bPhone" value="${contract.bphone}" type="text" size="30" class="validate[required,custom[integer]] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th align="right">甲方邮件：</th>
											<td><input id="firstPartyEmail" name="firstPartyEmail" value="${contract.firstPartyEmail}" type="text" size="30" class="validate[required,custom[email]] text-input" /><span style="color: red;">*</span></td>
											<th align="right">乙方邮件：</th>
											<td><input id="email" name="email" value="${contract.email}" type="text" size="30" class="validate[required,custom[email]] text-input" /><span style="color: red;">*</span></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a>
									</span> <strong>金额</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th align="right">币种：</th>
											<td><s:select id="currencyTypeId" headerKey="-1" headerValue="--请选择--" list="%{currencyTypeList}" listValue="name" listKey="id" value="%{contract.currencyType.id}" multiple="false" theme="simple">
												</s:select><span style="color: red;">*</span></td>
											<th align="right">汇率：</th>
											<td><s:select id="exchangeRateId" headerKey="-1" headerValue="--请选择--" list="%{exchangeRateList}" listValue="name" listKey="id" value="%{contract.exchangeRate.id}" multiple="false" theme="simple">
												</s:select><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">合同总金额：</td>
											<td><input class="easyui-numberbox" id="totalAmount" name="totalAmount" value="${contract.totalAmount}" type="text" size="30" data-options="precision:2,groupSeparator:',',decimalSeparator:'.',prefix:'$'"></input></td>
											<td align="right">合同执行金额：</td>
											<td><input class="easyui-numberbox" id="contractExecutionMoney" name="contractExecutionMoney" value="${contract.contractExecutionMoney}" type="text" size="30" data-options="precision:2,groupSeparator:',',decimalSeparator:'.',prefix:'$'"></input></td>
										</tr>
										<tr>
											<th align="right">预付款金额：</th>
											<td><input class="easyui-numberbox" id="prepaymentAmount" name="prepaymentAmount" value="${contract.prepaymentAmount}" type="text" size="30" data-options="precision:2,groupSeparator:',',decimalSeparator:'.',prefix:'$'"></input></td>
											<th align="right">未结金额：</th>
											<td><input class="easyui-numberbox" id="outstandingAmounts" name="outstandingAmounts" value="${contract.outstandingAmounts}" type="text" size="30" data-options="precision:2,groupSeparator:',',decimalSeparator:'.',prefix:'$'"></input></td>
										</tr>
										<tr>
											<td align="right">质保金计算方式：</td>
											<td><input id="retentionCalculation" name="retentionCalculation" value="${contract.retentionCalculation}" type="text" size="30" /></td>
											<th align="right">质保金开始日期：</th>
											<td><input id="retentionsStartDate" name="retentionsStartDate" value="<fmt:formatDate value='${contract.retentionsStartDate}' type='text' pattern='yyyy-MM-dd' />" type="text" size="30" /> <img onclick="showTime('retentionsStartDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th align="right">质保金结束日期：</th>
											<td><input id="retentionEndDate" name="retentionEndDate" value="<fmt:formatDate value='${contract.retentionEndDate}' type='text' pattern='yyyy-MM-dd' />" type="text" size="30" /> <img onclick="showTime('retentionEndDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">质保金比例（%）：</td>
											<td><input id="retentionRatio" name="retentionRatio" value="${contract.retentionRatio}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">质保金额度：</td>
											<td><input class="easyui-numberbox" id="warrantyAmount" name="warrantyAmount" value="${contract.warrantyAmount}" type="text" size="30" data-options="precision:2,groupSeparator:',',decimalSeparator:'.',prefix:'$'"></input></td>
											<td align="right">保修期：</td>
											<td><input id="warranty" name="warranty" value="${contract.warranty}" type="text" size="30" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a>
									</span> <strong>内容</strong>
								</dt>

								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th align="right">合同内容：</th>
											<td colspan="3"><textarea id="mainContent" name="mainContent">${contract.mainContent}</textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
												var contents = KindEditor.create('textarea[name="mainContent"]', {
												basePath : '${vix}/plugin/KindEditor/',
												width : 950,
												height : 200,
												cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
												uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
												fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
												allowFileManager : true
												});
											</script></td>
										</tr>
										<tr>
											<th align="right">备注：</th>
											<td colspan="3"><textarea id="remark" class="example" rows="1" style="width: 950px; margin-top: 15px;">${contract.remark}</textarea></td>
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
							<li class="current"><a onclick="javascript:tab(6,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />合同项</a></li>
							<li><a onclick="javascript:$('#a2').attr('style','');tab(6,2,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />标的</a></li>
							<li><a onclick="javascript:$('#a3').attr('style','');tab(6,3,'a',event);"><img src="${vix}/common/img/attachment.png" alt="" />附件</a></li>
							<li><a onclick="javascript:$('#a4').attr('style','');tab(6,4,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />预警</a></li>
							<li><a onclick="javascript:$('#a5').attr('style','');tab(6,5,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />审批</a></li>
							<li><a onclick="javascript:$('#a6').attr('style','');tab(6,6,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />价格条件</a></li>
						</ul>

					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="dlLineItem" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlLineItemTb',url: '${vix}/contract/contractDraftingAction!getContract1.action?id=${contract.id}',onClickRow: onDlClickRow">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:120">序号</th>
										<th data-options="field:'theContract',align:'center',width:570,editor:'text'">内容</th>
										<th data-options="field:'contractType',width:120,align:'center',editor:'text'">类型</th>
										<th data-options="field:'contractStatus',width:120,align:'center',editor:'text'">状态</th>
									</tr>
								</thead>
							</table>
							<div id="dlLineItemTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="appendDlLineItem()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-edit',plain:true" onclick="removeDlLineItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true"
									onclick="saveDlLineItem()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_save" /></span></span></a>
							</div>
							<script type="text/javascript">
								$('#dlLineItem').datagrid();
								var editIndexDlLineItem = undefined;
								function endDlEditing() {
									if (editIndexDlLineItem == undefined) {
										return true;
									}
									if ($('#dlLineItem').datagrid('validateRow', editIndexDlLineItem)) {
										$('#dlLineItem').datagrid('endEdit', editIndexDlLineItem);
										editIndexDlLineItem = undefined;
										return true;
									} else {
										return false;
									}
								}
								function onDlClickRow(index) {
									if (editIndexDlLineItem != index) {
										if (endDlEditing()) {
											$('#dlLineItem').datagrid('selectRow', index).datagrid('beginEdit', index);
											editIndexDlLineItem = index;
										} else {
											$('#dlLineItem').datagrid('selectRow', editIndexDlLineItem);
										}
									}
								}
								function appendDlLineItem() {
									if (endDlEditing()) {
										$('#dlLineItem').datagrid('appendRow', {
											status : 'P'
										});
										editIndexDlLineItem = $('#dlLineItem').datagrid('getRows').length - 1;
										$('#dlLineItem').datagrid('selectRow', editIndexDlLineItem).datagrid('beginEdit', editIndexDlLineItem);
									}
								}
								function removeDlLineItem() {
									if (editIndexDlLineItem == undefined) {
										return;
									}
									var row = $('#dlLineItem').datagrid('getSelected');
									$.ajax({
									url : '${vix}/contract/contractDraftingAction!deleteContractLineById.action?id=' + row.id,
									cache : false
									});
									$('#dlLineItem').datagrid('cancelEdit', editIndexDlLineItem).datagrid('deleteRow', editIndexDlLineItem);
									editIndexDlLineItem = undefined;
								}
								function saveDlLineItem() {
									if (endDlEditing()) {
										$('#dlLineItem').datagrid('acceptChanges');
									}
								}
							</script>
						</div>
					</div>
					<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<script type="text/javascript">
							function saveDeliveryAddress(url) {
								$.ajax({
								url : url,
								cache : false,
								success : function(html) {
									asyncbox.open({
									modal : true,
									width : 724,
									height : 340,
									title : "添加合同标的明细",
									html : html,
									callback : function(action, returnValue) {
										if (action == 'ok') {
											$.post('${vix}/contract/contractDraftingAction!saveOrUpdateContractSubjectDetails.action', {
											'id' : $("#id").val(),
											'contractSubject.id' : $("#daId").val(),
											'contractSubject.subjectCode' : $("#subjectCode").val(),
											'contractSubject.subjectName' : $("#subjectName").val(),
											'contractSubject.source' : $("#source").val(),
											'contractSubject.stockClassificationCode' : $("#stockClassificationCode").val(),
											'contractSubject.correspondingInventoryCode' : $("#correspondingInventoryCode").val(),
											'contractSubject.projectCatalog' : $("#projectCatalog").val(),
											'contractSubject.inventoriesSpecification' : $("#inventoriesSpecification").val(),
											'contractSubject.nnTOCurrencyPrice' : $("#nnTOCurrencyPrice").val(),
											'contractSubject.ttIOriginalCurrencyPrice' : $("#ttIOriginalCurrencyPrice").val(),
											'contractSubject.nnTaxAmountOriginalCurrency' : $("#nnTaxAmountOriginalCurrency").val(),
											'contractSubject.ttATOriginalCurrency' : $("#ttATOriginalCurrency").val(),
											'contractSubject.executionQuantity' : $("#executionQuantity").val(),
											'contractSubject.eeTAOriginalCurrency' : $("#eeTAOriginalCurrency").val(),
											'contractSubject.eeTAIncLriginalCurrency' : $("#eeTAIncLriginalCurrency").val(),
											'contractSubject.startTime' : $("#startTime").val(),
											'contractSubject.endTime' : $("#endTime").val()
											}, function(result) {
												showMessage(result);
												setTimeout("", 1000);
												$('#dlAddress2').datagrid("reload");
											});
										}
									},
									btnsbar : $.btn.OKCANCEL
									});
								}
								});
							}
							$('#dlAddress2').datagrid({
							url : '${vix}/contract/contractDraftingAction!getContractSubjectJson.action?id=${contract.id}',
							title : '合同标的明细',
							width : 'auto',
							height : '450',
							iconCls : 'icon-save',
							fitColumns : true,
							nowrap : true,
							autoRowHeight : false,
							striped : true,
							collapsible : true,

							sortName : 'code',
							sortOrder : 'desc',
							remoteSort : false,
							idField : 'code',
							frozenColumns : [ [ {
							field : 'ck',
							checkbox : true
							}, {
							field : 'id',
							title : '编号',
							width : 80,
							align : 'center'
							}, {
							field : 'subjectCode',
							title : '标的编码',
							width : 80,
							align : 'center'
							}, {
							field : 'subjectName',
							title : '标的名称',
							width : 80,
							align : 'center'
							}, {
							field : 'source',
							title : '来源',
							width : 100,
							align : 'center'
							}, ] ],
							columns : [ [ {
							field : 'stockClassificationCode',
							title : '存货分类编码',
							width : 98,
							align : 'center'
							}, {
							field : 'correspondingInventoryCode',
							title : '对应存货编码',
							width : 98,
							align : 'center'
							}, {
							field : 'projectCatalog',
							title : '项目大类',
							width : 98,
							align : 'center'
							}, {
							field : 'inventoriesSpecification',
							title : '存货规格型号',
							width : 98,
							align : 'center'
							}, {
							field : 'nnTOCurrencyPrice',
							title : '无税原币单价',
							width : 98,
							align : 'center'
							}, {
							field : 'ttIOriginalCurrencyPrice',
							title : '含税原币单价',
							width : 98,
							align : 'center'
							}, {
							field : 'nnTaxAmountOriginalCurrency',
							title : '无税原币金额',
							width : 100,
							align : 'center'
							}, {
							field : 'ttATOriginalCurrency',
							title : '含税原币金额',
							width : 100,
							align : 'center'
							}, {
							field : 'executionQuantity',
							title : '执行数量',
							width : 98,
							align : 'center'
							}, {
							field : 'eeTAOriginalCurrency',
							title : '执行无税金额原币',
							width : 98,
							align : 'center'
							}, {
							field : 'eeTAIncLriginalCurrency',
							title : '执行含税金额原币',
							width : 98,
							align : 'center'
							}, {
							field : 'startTime',
							title : '开始时间',
							width : 120,
							align : 'center',
							editor : 'datebox',
							formatter : formatDatebox
							}, {
							field : 'endTime',
							title : '结束时间',
							width : 120,
							align : 'center',
							editor : 'datebox',
							formatter : formatDatebox
							}, ] ],
							toolbar : [ {
							id : 'da2Btnadd',
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								saveDeliveryAddress('${vix}/contract/contractDraftingAction!goAddContractSubject.action?id=0');
							}
							}, '-', {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#dlAddress2').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveDeliveryAddress('${vix}/contract/contractDraftingAction!goAddContractSubject.action?id=' + row.id);
								}
							}
							}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								var rows = $('#dlAddress2').datagrid("getSelections"); //获取你选择的所有行	
								//循环所选的行
								for (var i = 0; i < rows.length; i++) {
									var index = $('#dlAddress2').datagrid('getRowIndex', rows[i]);//获取某行的行号										
									$.ajax({
									url : '${vix}/contract/contractDraftingAction!deleteContractSubjectById.action?id=' + rows[i].id,
									cache : false
									});
									$('#dlAddress2').datagrid('deleteRow', index); //通过行号移除该行
								}
							}
							} ]
							});
							//为原始Date类型拓展format一个方法，用于日期显示的格式化
							Date.prototype.format = function(format) {
								var o = {
								"M+" : this.getMonth() + 1, //month 
								"d+" : this.getDate(), //day 
								"h+" : this.getHours(), //hour 
								"m+" : this.getMinutes(), //minute 
								"s+" : this.getSeconds(), //second 
								"q+" : Math.floor((this.getMonth() + 3) / 3), //quarter 
								"S" : this.getMilliseconds()
								//millisecond 
								}
								if (/(y+)/.test(format))
									format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
								for ( var k in o)
									if (new RegExp("(" + k + ")").test(format))
										format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
								return format;
							}

							//格式化日期
							function formatDatebox(value) {
								if (value == null || value == '') {
									return '';
								}
								var dt;
								if (value instanceof Date) {
									dt = value;
								} else {
									dt = new Date(value);
									if (isNaN(dt)) {
										value = value.replace(/\/Date\((-?\d+)\)\//, '$1'); //标红的这段是关键代码，将那个长字符串的日期值转换成正常的JS日期格式
										dt = new Date();
										dt.setTime(value);
									}
								}
								return dt.format("yyyy-MM-dd"); //这里用到一个javascript的Date类型的拓展方法，这个是自己添加的拓展方法，在后面的步骤3定义
							}
						</script>
						<div style="padding: 8px;">
							<table id="dlAddress2"></table>
						</div>
					</div>
					<div id="a3" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<script type="text/javascript">
							$('#soAttach').datagrid({
							url : '${vix}/contract/contractDraftingAction!getContractTemplate.action?id=${contract.id}',
							title : '合同附件',
							width : 'auto',
							height : '450',
							fitColumns : true,
							columns : [ [ {
							field : 'id',
							title : '序号',
							width : 80
							}, {
							field : 'name',
							title : '主题',
							width : 80
							}, {
							field : 'path',
							title : '路径',
							width : 80
							}, {
							field : 'createTime',
							title : '创建时间',
							width : 80
							} ] ],
							toolbar : [ {
							id : 'saBtnadd',
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								$.ajax({
								url : '${vix}/contract/contractDraftingAction!addAttachFile.action',
								cache : false,
								success : function(html) {
									asyncbox.open({
									modal : true,
									width : 364,
									height : 160,
									title : "上传附件",
									html : html,
									callback : function(action, returnValue) {
										if (action == 'ok') {
											uploadFile('${vix}/contract/contractDraftingAction!uploadAttachment.action?id=${contract.id}', 'fileToUpload');
											$('#soAttach').datagrid("reload");
										}
									},
									btnsbar : $.btn.OKCANCEL
									});
								}
								});
							}
							}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								var rows = $('#soAttach').datagrid("getSelections"); //获取你选择的所有行	
								//循环所选的行
								for (var i = 0; i < rows.length; i++) {
									var index = $('#soAttach').datagrid('getRowIndex', rows[i]);//获取某行的行号
									$('#soAttach').datagrid('deleteRow', index); //通过行号移除该行
									$.ajax({
									url : '${vix}/contract/contractDraftingAction!!deleteAttachFile.action?afId=' + rows[i].id,
									cache : false
									});
								}
							}
							} ]
							});
						</script>
						<div style="padding: 8px;">
							<table id="soAttach"></table>
						</div>
					</div>
					<div id="a4" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="dlDeliveryPlan" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlDeliveryPlanTb',url: '${vix}/contract/contractDraftingAction!getContract2.action?id=${contract.id}',onClickRow: onDlClickRow">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:120">序号</th>
										<th data-options="field:'warnningContent',width:420,align:'center',editor:'text'">预警信息</th>
										<th data-options="field:'warnningTime',width:120,align:'center',editor:'datebox'">预警时间</th>
										<th data-options="field:'warnningType',width:120,align:'center',editor:'text'">类型</th>
									</tr>
								</thead>
							</table>
							<div id="dlDeliveryPlanTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="appendDlDeliveryPlan()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)"
									class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeDlDeliveryPlan()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-save',plain:true" onclick="saveDlDeliveryPlan()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span></a>
							</div>
							<script type="text/javascript">
								$('#dlDeliveryPlan').datagrid();
								var editIndexDlDeliveryPlan = undefined;
								function endDlEditing() {
									if (editIndexDlDeliveryPlan == undefined) {
										return true;
									}
									if ($('#dlDeliveryPlan').datagrid('validateRow', editIndexDlDeliveryPlan)) {
										$('#dlDeliveryPlan').datagrid('endEdit', editIndexDlDeliveryPlan);
										editIndexDlDeliveryPlan = undefined;
										return true;
									} else {
										return false;
									}
								}
								function onDlClickRow(index) {
									if (editIndexDlDeliveryPlan != index) {
										if (endDlEditing()) {
											$('#dlDeliveryPlan').datagrid('selectRow', index).datagrid('beginEdit', index);
											editIndexDlDeliveryPlan = index;
										} else {
											$('#dlDeliveryPlan').datagrid('selectRow', editIndexDlDeliveryPlan);
										}
									}
								}
								function appendDlDeliveryPlan() {
									if (endDlEditing()) {
										$('#dlDeliveryPlan').datagrid('appendRow', {
											status : 'P'
										});
										editIndexDlDeliveryPlan = $('#dlDeliveryPlan').datagrid('getRows').length - 1;
										$('#dlDeliveryPlan').datagrid('selectRow', editIndexDlDeliveryPlan).datagrid('beginEdit', editIndexDlDeliveryPlan);
									}
								}
								function removeDlDeliveryPlan() {
									if (editIndexDlDeliveryPlan == undefined) {
										return;
									}
									var row = $('#dlDeliveryPlan').datagrid("getSelected");
									$.ajax({
									url : '${vix}/contract/contractDraftingAction!deleteContractWarningById.action?id=' + row.id,
									cache : false
									});
									$('#dlDeliveryPlan').datagrid('cancelEdit', editIndexDlDeliveryPlan).datagrid('deleteRow', editIndexDlDeliveryPlan);
									editIndexDlDeliveryPlan = undefined;
								}
								function saveDlDeliveryPlan() {
									if (endDlEditing()) {
										$('#dlDeliveryPlan').datagrid('acceptChanges');
									}
								}
							</script>
						</div>
					</div>
					<div id="a5" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="dlInvoice" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlInvoiceTb',url: '${vix}/contract/contractDraftingAction!getContract3.action?id=${contract.id}',onClickRow: onDlClickRow">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:120">序号</th>
										<th data-options="field:'underTakePerson',width:405,align:'center',editor:'text'">审批人</th>
										<th data-options="field:'underTakeDate',width:405,align:'center',editor:'datebox'">审批时间</th>
									</tr>
								</thead>
							</table>
							<div id="dlInvoiceTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="appendDlInvoice()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-remove',plain:true" onclick="removeDlInvoice()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-save',plain:true"
									onclick="saveDlInvoice()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span></a>
							</div>
							<script type="text/javascript">
								$('#dlInvoice').datagrid();
								var editIndexDlInvoice = undefined;
								function endDlEditing() {
									if (editIndexDlInvoice == undefined) {
										return true;
									}
									if ($('#dlInvoice').datagrid('validateRow', editIndexDlInvoice)) {
										$('#dlInvoice').datagrid('endEdit', editIndexDlInvoice);
										editIndexDlInvoice = undefined;
										return true;
									} else {
										return false;
									}
								}
								function onDlClickRow(index) {
									if (editIndexDlInvoice != index) {
										if (endDlEditing()) {
											$('#dlInvoice').datagrid('selectRow', index).datagrid('beginEdit', index);
											editIndexDlInvoice = index;
										} else {
											$('#dlInvoice').datagrid('selectRow', editIndexDlInvoice);
										}
									}
								}
								function appendDlInvoice() {
									if (endDlEditing()) {
										$('#dlInvoice').datagrid('appendRow', {
											status : 'P'
										});
										editIndexDlInvoice = $('#dlInvoice').datagrid('getRows').length - 1;
										$('#dlInvoice').datagrid('selectRow', editIndexDlInvoice).datagrid('beginEdit', editIndexDlInvoice);
									}
								}
								function removeDlInvoice() {
									if (editIndexDlInvoice == undefined) {
										return;
									}
									var row = $('#dlInvoice').datagrid("getSelected");
									$.ajax({
									url : '${vix}/contract/contractDraftingAction!deleteApplicationFormById.action?id=' + row.id,
									cache : false
									});
									$('#dlInvoice').datagrid('cancelEdit', editIndexDlInvoice).datagrid('deleteRow', editIndexDlInvoice);
									editIndexDlInvoice = undefined;
								}
								function saveDlInvoice() {
									if (endDlEditing()) {
										$('#dlInvoice').datagrid('acceptChanges');
									}
								}
							</script>
						</div>
					</div>
					<div id="a6" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="dlInvoice1" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlInvoiceTb1',url: '${vix}/contract/contractDraftingAction!getContract4.action?id=${contract.id}',onClickRow: onDlClickRow">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:120">序号</th>
										<th data-options="field:'startQuantity',width:150,align:'center',editor:'text'">开始数量</th>
										<th data-options="field:'cutoffQuantity',width:150,align:'center',editor:'text'">截止数量</th>
										<th data-options="field:'discount',width:150,align:'center',editor:'text'">折扣</th>
										<th data-options="field:'numberFrom',width:150,align:'center',editor:'text'">数量（从）</th>
										<th data-options="field:'numberto',width:150,align:'center',editor:'text'">数量（到）</th>
										<th data-options="field:'sellpriceCondition',width:400,align:'center',editor:'text'">销售价格条件</th>
									</tr>
								</thead>
							</table>
							<div id="dlInvoiceTb1" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="appendDlInvoice1()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-remove',plain:true" onclick="removeDlInvoice1()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-save',plain:true"
									onclick="saveDlInvoice1()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span></a>
							</div>
							<script type="text/javascript">
								$('#dlInvoice1').datagrid();
								var editIndexDlInvoice1 = undefined;
								function endDlEditing() {
									if (editIndexDlInvoice1 == undefined) {
										return true;
									}
									if ($('#dlInvoice1').datagrid('validateRow', editIndexDlInvoice1)) {
										$('#dlInvoice1').datagrid('endEdit', editIndexDlInvoice1);
										editIndexDlInvoice1 = undefined;
										return true;
									} else {
										return false;
									}
								}
								function onDlClickRow(index) {
									if (editIndexDlInvoice1 != index) {
										if (endDlEditing()) {
											$('#dlInvoice1').datagrid('selectRow', index).datagrid('beginEdit', index);
											editIndexDlInvoice1 = index;
										} else {
											$('#dlInvoice1').datagrid('selectRow', editIndexDlInvoice1);
										}
									}
								}
								function appendDlInvoice1() {
									if (endDlEditing()) {
										$('#dlInvoice1').datagrid('appendRow', {
											status : 'P'
										});
										editIndexDlInvoice1 = $('#dlInvoice1').datagrid('getRows').length - 1;
										$('#dlInvoice1').datagrid('selectRow', editIndexDlInvoice1).datagrid('beginEdit', editIndexDlInvoice1);
									}
								}
								function removeDlInvoice1() {
									if (editIndexDlInvoice1 == undefined) {
										return;
									}
									var row = $('#dlInvoice1').datagrid("getSelected");
									$.ajax({
									url : '${vix}/contract/contractDraftingAction!deleteApplicationFormById.action?id=' + row.id,
									cache : false
									});
									$('#dlInvoice1').datagrid('cancelEdit', editIndexDlInvoice1).datagrid('deleteRow', editIndexDlInvoice1);
									editIndexDlInvoice1 = undefined;
								}
								function saveDlInvoice1() {
									if (endDlEditing()) {
										$('#dlInvoice1').datagrid('acceptChanges');
									}
								}
							</script>
						</div>
					</div>
				</div>
			</dl>
		</div>
	</form>
</div>