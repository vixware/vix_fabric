<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#organizationForm").validationEngine();

	$(function() {
		setReadOnlyForCompTenantId();
	});

	/**
	 * 设定只读
	 */
	function setReadOnlyForCompTenantId() {
		var compId = "${entity.id}";
		var parentCompId = "${entity.parentOrganization.id}";
		if (compId != "" || parentCompId != "") {
			$("#tenantId").attr("readonly", "readonly");
		}
		setDisplayForCompTenantId(parentCompId);
	}

	/**
	 * 设置隐藏显示
	 */
	function setDisplayForCompTenantId(parent) {
		if (parent != "") {
			$(".compdisplay").hide();
		} else {
			$(".compdisplay").show();
		}
	}

	function chooseParentOrganization() {
		$.ajax({
		url : '${vix}/system/companyOperationAction!goChooseOrganization.action',
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
						if (result[0] == $("#id").val()) {
							showErrorMessage("不允许引用本公司为父公司!");
							setTimeout("", 1000);
							return false;
						} else {
							$("#parentId").val(result[0]);
							//$("#organizationName").html(result[1]);
							$("#organizationName").val(result[1]);
						}
					} else {
						//$("#parentId").val("");
						//$("#organizationName").val("");
						showErrorMessage("请选择上级公司信息!");
						setTimeout("", 1000);
						return false;
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}

	/**
	 * 选择行业模块
	 */
	function chooseIndustryModule() {
		$.ajax({
		url : '${vix}/common/select/commonSelectModuleAction!goChooseIndustryModule.action',
		data : {
		chkStyle : "checkbox",
		checkedObjIds : $('#industryManagementModuleIds').val()
		},
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 260,
			height : 320,
			title : "选择模块",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						//alert(returnValue);
						var selectIds = "";
						var selectNames = "";

						var resObj = $.parseJSON(returnValue);

						for (var i = 0; i < resObj.length; i++) {
							selectIds += "," + resObj[i].treeId;
							selectNames += "," + resObj[i].fullName;
						}

						selectIds = selectIds.substring(1, selectIds.length);
						selectNames = selectNames.substring(1, selectNames.length);

						//alert(selectIds+"--"+selectNames);
						if (selectIds != "") {
							$("#industryManagementModuleIds").val(selectIds);
							$("#industryManagementModuleNames").val(selectNames);
						}

						//selectNames = selectNames.substring(1,selectNames.length);
						//$("#industryName").val(selectNames);
					}

				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}

	function brandList() {
		$("#brandSub").css({
			"display" : ""
		});
		$("#dimensionSub").css({
			"display" : "none"
		});
	}
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<form action="" id="organizationForm" name="organizationForm">
			<s:hidden id="id" name="entityForm.id" value="%{entity.id}" theme="simple" />
			<table>
				<tr>
					<td align="right">上级公司机构:&nbsp;</td>
					<td colspan="3"><input type="hidden" id="parentId" name="entityForm.parentId" value="${entity.parentOrganization.id}" onchange="setDisplayForCompTenantId(this.value);" /> <%-- <span id="organizationName"><s:property value="entity.parentOrganization.orgName"/></span> --%> <input type="text" id="organizationName" name="organizationName"
						readonly="readonly" value="${entity.parentOrganization.orgName}" /> <!-- <input class="btn" type="button" value="选择" onclick="chooseParentOrganization();"/> --></td>
				</tr>
				<tr height="40">
					<td width="15%" align="right">机构类型:&nbsp;</td>
					<td width="35%"><s:select list="#{'jtgs':'集团公司','gs':'公司'}" name="entityForm.orgType" value="%{entity.orgType}" theme="simple"></s:select></td>
					<td width="15%" align="right"><div class="compdisplay">承租户编码:&nbsp;</div></td>
					<td width="35%">
						<div class="compdisplay">
							<input type="text" id="tenantId" name="entityForm.tenantId" value="${entity.tenantId}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" />
						</div>
					</td>
				</tr>
				<tr height="40">
					<td width="15%" align="right">名称:&nbsp;</td>
					<td width="35%"><input type="text" id="orgName" name="entityForm.orgName" value="${entity.orgName}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /> <span style="color: red;">*</span></td>
					<td width="15%" align="right">简称:&nbsp;</td>
					<td width="35%"><input type="text" id="briefName" name="entityForm.briefName" value="${entity.briefName}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">拼音缩写:&nbsp;</td>
					<td><input type="text" id="shortName" name="entityForm.shortName" value="${entity.shortName}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
					<td align="right">英文名称:&nbsp;</td>
					<td><input type="text" id="enName" name="entityForm.enName" value="${entity.enName}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
				</tr>
				<tr height="40">
					<td align="right">地址:&nbsp;</td>
					<td><input type="text" id="address" name="entityForm.address" value="${entity.address}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
					<td align="right">所属国家:&nbsp;</td>
					<td><input type="text" id="country" name="entityForm.country" value="${entity.country}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
				</tr>
				<tr height="40">
					<td align="right">地区:&nbsp;</td>
					<td><input type="text" id="area" name="entityForm.area" value="${entity.area}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
					<td align="right">使用语言:&nbsp;</td>
					<td><input type="text" id="countryLanguage" name="entityForm.countryLanguage" value="${entity.countryLanguage}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
				</tr>
				<tr height="40">
					<td align="right">编码:&nbsp;</td>
					<td><input type="text" id="companyCode" name="entityForm.companyCode" value="${entity.companyCode}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
					<td align="right">成立时间:&nbsp;</td>
					<td><input type="text" id="companyCreateDate" name="entityForm.companyCreateDate" value="<s:date name='%{entity.companyCreateDate}' format='yyyy-MM-dd'/>" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /><img
						onclick="showTime('companyCreateDate','yyyy-MM-dd')" src="img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="40">
					<td align="right">公司管理员账号:&nbsp;
					<input type="hidden" id="userAccountId" name="entityForm.userAccountId" value="${entity.compSuperAdmin.id}" /></td>
					<td><input type="text" id="account" name="entityForm.account" value="${entity.compSuperAdmin.account}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /> <span style="color: red;">*</span></td>
					<td align="right">&nbsp;</td>
					<td align="right">&nbsp;</td>
				</tr>
				<tr height="40">
					<td align="right">密码:&nbsp;</td>
					<td><input type="password" id="password" name="entityForm.password" value="${entity.compSuperAdmin.password}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /> <span style="color: red;">*</span></td>
					<td align="right">确认密码:&nbsp;</td>
					<td><input type="password" id="passwordConfirm" name="entityForm.passwordConfirm" value="<s:date name='%{entity.compSuperAdmin.password}' format='yyyy-MM-dd'/>" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /> <span
						style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">数据查看:&nbsp;</td>
					<td><s:select list="#{'P':'本公司数据','N':'不使用'}" name="entityForm.orgDataFilterType" value="%{entity.orgDataFilterType}" theme="simple"></s:select></td>
					<td align="right">&nbsp;</td>
					<td align="right">&nbsp;</td>
				</tr>
				<tr height="40">
					<td align="right">期间数:&nbsp;</td>
					<td><input type="text" id="numberOfPeriods" name="entityForm.numberOfPeriods" value="${entity.numberOfPeriods}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
					<td align="right">本位币 :&nbsp;</td>
					<td><input type="text" id="currency" name="entityForm.currency" value="${entity.currency}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
				</tr>
				<tr height="40">
					<td align="right">当前总帐期间:&nbsp;</td>
					<td><input type="text" id="currentPeriod" name="entityForm.currentPeriod" value="${entity.currentPeriod}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
					<td align="right">财务年度开始日期:&nbsp;</td>
					<td><input type="text" id="beginningOfFiscalYear" name="entityForm.beginningOfFiscalYear" value="<s:date name='%{entity.beginningOfFiscalYear}' format='yyyy-MM-dd'/>" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /><img
						onclick="showTime('beginningOfFiscalYear','yyyy-MM-dd')" src="img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="40">
					<td align="right">应付会计期间:&nbsp;</td>
					<td><input type="text" id="accountPayableCurrentPeroid" name="entityForm.accountPayableCurrentPeroid" value="${entity.accountPayableCurrentPeroid}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
					<td align="right">应付会计年度起始日期:&nbsp;</td>
					<td><input type="text" id="accountPaybaleBeginningOfFisaclYear" name="entityForm.accountPaybaleBeginningOfFisaclYear" value="<s:date name='%{entity.accountPaybaleBeginningOfFisaclYear}' format='yyyy-MM-dd'/>" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /><img
						onclick="showTime('accountPaybaleBeginningOfFisaclYear','yyyy-MM-dd')" src="img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="40">
					<td align="right">应收会计期间:&nbsp;</td>
					<td><input type="text" id="accountReceivableCurrentPeriod" name="entityForm.accountReceivableCurrentPeriod" value="${entity.accountReceivableCurrentPeriod}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
					<td align="right">应收会计年度起始日期:&nbsp;</td>
					<td><input type="text" id="accountReceivableBeginningOfFiscalYear" name="entityForm.accountReceivableBeginningOfFiscalYear" value="<s:date name='%{entity.accountReceivableBeginningOfFiscalYear}' format='yyyy-MM-dd'/>" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /><img
						onclick="showTime('accountReceivableBeginningOfFiscalYear','yyyy-MM-dd')" src="img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="40">
					<td align="right">会计期间日历:&nbsp;</td>
					<td><input type="text" id="fiscalCalendar" name="entityForm.fiscalCalendar" value="${entity.fiscalCalendar}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
				</tr>
			</table>
		</form>
	</div>
</div>
<script src="${vix}/common/js/jtip.js" type="text/javascript"></script>