<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdateEmployee() {
		var orderItemStr = "";
		if ($('#distributorEmployeeForm').validationEngine('validate')) {
			$.post('${vix}/drp/employeeManagementAction!saveOrUpdate.action', {
			'employee.id' : $("#id").val(),
			'employee.channelDistributor.id' : $("#channelDistributorId").val(),
			'employee.code' : $("#code").val(),
			'employee.name' : $("#name").val(),
			'employee.oldName' : $("#oldName").val(),
			'employee.fsName' : $("#fsName").val(),
			'employee.idNumber' : $("#idNumber").val(),
			'employee.birthDate' : $("#birthDate").val(),
			'employee.bloodType' : $("#bloodType").val(),
			'employee.gender' : $("#gender").val(),
			'employee.qualificationsCode' : $("#qualificationsCode").val(),
			'employee.departmentCode' : $("#departmentCode").val(),
			'employee.staffJobNumber' : $("#staffJobNumber").val(),
			'employee.residenceAddress' : $("#residenceAddress").val(),
			'employee.birthplace' : $("#birthplace").val(),
			'employee.isDemission' : $(":radio[name=isDemission][checked]").val(),
			'employee.entityTime' : $("#entityTime").val(),
			'updateField' : updateField,
			'employee.telephone' : $("#telephone").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/employeeManagementAction!goList.action');
			});
		}
	}
	$("#distributorEmployeeForm").validationEngine();
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
						$("#channelDistributorId").val(result[0]);
						$("#channelDistributorName").val(result[1]);
					} else {
						$("#channelDistributorId").val("");
						$("#channelDistributorName").val("");
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
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_market.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#">设置 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/employeeManagementAction!goList.action');">员工管理 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${employee.id}" />
<input type="hidden" id="channelDistributorId" name="id" value="${employee.channelDistributor.id}" />
<div class="content">
	<form id="distributorEmployeeForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateEmployee()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/drp/employeeManagementAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>员工信息</b></strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">渠道/经销商：</td>
											<td><input id="channelDistributorName" name="channelDistributorName" value="${employee.channelDistributor.name }" onchange="fieldChanged(this);" type="text" size="30" class="validate[required] text-input" /><input type="hidden" id="channelDistributorId" name="channelDistributorId" value="${employee.channelDistributor.id}" /><span
												style="color: red;">*</span> <input class="btn" type="button" value="选择" onclick="chooseParentOrganization();" /></td>
										</tr>
										<tr>
											<td align="right">员工编码：</td>
											<td><input id="code" name="code" type="text" size="30" value="${employee.code}" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td align="right">姓名：</td>
											<td><input id="name" name="name" type="text" size="30" value="${employee.name}" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">身份证号：</td>
											<td><input id="idNumber" name="idNumber" type="text" size="30" value="${employee.idNumber}" onchange="fieldChanged(this);" /></td>
											<td align="right">出生日期：</td>
											<td><input id="birthDate" name="birthDate" value="<s:date name="%{employee.birthDate}" format="yyyy-MM-dd"/>" type="text" onchange="fieldChanged(this);" /> <img onclick="showTime('birthDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="middle"></td>
										</tr>
										<tr>
											<td align="right">员工职号：</td>
											<td><input id="staffJobNumber" name="staffJobNumber" type="text" size="30" value="${employee.staffJobNumber}" onchange="fieldChanged(this);" /></td>
											<td align="right">联系电话：</td>
											<td><input id="telephone" name="telephone" type="text" size="30" value="${employee.telephone}" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">曾用名：</td>
											<td><input id="oldName" name="oldName" type="text" size="30" value="${employee.oldName}" onchange="fieldChanged(this);" /></td>
											<td align="right">姓名缩写</td>
											<td><input id="fsName" name="fsName" type="text" size="30" value="${employee.fsName}" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">性别：</td>
											<td><s:select list="#{'1':'男','0':'女'}" id="gender" name="gender" value="%{employee.gender}" theme="simple" onchange="fieldChanged(this);"></s:select></td>
											<td align="right">学历：</td>
											<td><s:select list="#{'专科':'专科','本科':'本科','博士':'博士'}" id="qualificationsCode" name="qualificationsCode" value="%{employee.qualificationsCode}" theme="simple" onchange="fieldChanged(this);"></s:select></td>
										</tr>
										<tr>
											<td align="right">科系：</td>
											<td><input id="departmentCode" name="departmentCode" type="text" size="30" value="${employee.departmentCode}" onchange="fieldChanged(this);" /></td>
											<td align="right">血型：</td>
											<td><s:select list="#{'A':'A','B':'B','AB':'AB','O':'O'}" id="bloodType" name="bloodType" value="%{employee.bloodType}" theme="simple" onchange="fieldChanged(this);"></s:select></td>
										</tr>
										<tr>
											<td align="right">出生地：</td>
											<td><input id="birthplace" name="birthplace" type="text" size="30" value="${employee.birthplace}" onchange="fieldChanged(this);" /></td>
											<td align="right">户籍地址：</td>
											<td><input id="residenceAddress" name="residenceAddress" type="text" size="30" value="${employee.residenceAddress}" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">入职日期：</td>
											<td><input id="entityTime" name="entityTime" value="<s:date name="%{employee.entityTime}" format="yyyy-MM-dd"/>" type="text" onchange="fieldChanged(this);" /> <img onclick="showTime('entityTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="middle"></td>
											<td align="right">是否离职:&nbsp;</td>
											<td><s:if test='employee.isDemission=="1"'>
													<s:radio list="#{\"1\":\"是\",\"0\":\"否\"}" name="isDemission" listKey="key" listValue="value" value="1" theme="simple" onchange="fieldChanged(this);"></s:radio>
												</s:if> <s:elseif test='employee.isDemission=="0"'>
													<s:radio list="#{\"1\":\"是\",\"0\":\"否\"}" name="isDemission" listKey="key" listValue="value" value="0" theme="simple" onchange="fieldChanged(this);"></s:radio>
												</s:elseif> <s:else>
													<s:radio list="#{\"1\":\"是\",\"0\":\"否\"}" name="isDemission" listKey="key" listValue="value" value="0" theme="simple" onchange="fieldChanged(this);"></s:radio>
												</s:else><span style="color: red;">*</span></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>