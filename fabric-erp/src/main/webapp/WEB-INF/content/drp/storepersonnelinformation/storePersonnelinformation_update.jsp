<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdateOrder() {
		if ($('#distributorEmployeeForm').validationEngine('validate')) {
			$.post('${vix}/drp/store/storePersonnelinformationAction!saveOrUpdate.action', {
			'employee.id' : $("#employeeId").val(),
			'employee.channelDistributor.id' : $("#channelDistributorId").val(),
			'employee.code' : $("#code").val(),
			'employee.name' : $("#name").val(),
			'employee.oldName' : $("#oldName").val(),
			'employee.idNumber' : $("#idNumber").val(),
			'employee.birthday' : $("#birthDate").val(),
			'employee.bloodType' : $("#bloodType").val(),
			'employee.gender' : $("#gender").val(),
			'employee.qualificationsCode' : $("#qualificationsCode").val(),
			'employee.departmentCode' : $("#departmentCode").val(),
			'employee.staffJobNumber' : $("#staffJobNumber").val(),
			'employee.residenceAddress' : $("#residenceAddress").val(),
			'employee.birthplace' : $("#birthplace").val(),
			'employee.isDemission' : $(":radio[name=isDemission][checked]").val(),
			'employee.entityTime' : $("#entityTime").val(),
			'employee.national' : $("#national").val(),
			'updateField' : updateField,
			'employee.telephone' : $("#telephone").val(),
			'employee.memo': memo.html()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/store/storePersonnelinformationAction!goList.action');
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
	function initdata() {
		if (document.getElementById("entityTime").value == "") {
			var myDate = new Date();
			$("#entityTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate());
		}
	}
	initdata();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<s:if test="source =='drp' ">
					<li><a href="#"><img src="${vix}/common/img/drp_shop.png" alt="" /> <s:text name="supplyChain" /> </a></li>
					<li><a href="#"><s:text name="drp_management" /> </a></li>
					<li><a href="#"><s:text name="drp_store_management" /> </a></li>
					<li><a href="#" onclick="loadContent('${vix}/drp/storePersonnelinformationAction!goList.action?source=drp');"><s:text name="drp_store_personnel_information" /> </a></li>
				</s:if>
				<s:else>
					<li><a href="#"><img src="${vix}/common/img/drp_shop.png" alt="" /> <s:text name="supplyChain" /> </a></li>
					<li><a href="#">连锁经营管理 </a></li>
					<li><a href="#">门店管理 </a></li>
					<li><a href="#" onclick="loadContent('${vix}/drp/storePersonnelinformationAction!goList.action');">门店人员信息</a></li>
				</s:else>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="employeeId" name="employeeId" value="${employee.id}" />
<input type="hidden" id="code" name="code" value="${employee.code}" />
<div class="content">
	<form id="distributorEmployeeForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/drp/storePersonnelinformationAction!goList.action?pageNo=${pageNo}&source=${source}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>人员信息 </b></strong>
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
											<td align="right" width="15%">姓名：</td>
											<td width="35%"><input id="name" name="name" type="text" size="30" value="${employee.name}" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td align="right" width="15%">曾用名：</td>
											<td width="35%"><input id="oldName" name="oldName" type="text" size="30" value="${employee.oldName}" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">身份证号：</td>
											<td><input id="idNumber" name="idNumber" type="text" size="30" value="${employee.idNumber}" onchange="fieldChanged(this);" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">联系电话：</td>
											<td><input id="telephone" name="telephone" type="text" size="30" value="${employee.telephone}" onchange="fieldChanged(this);" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">员工职号：</td>
											<td><input id="staffJobNumber" name="staffJobNumber" type="text" size="30" value="${employee.staffJobNumber}" onchange="fieldChanged(this);" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">所属门店：</td>
											<td><input id="channelDistributorName" name="channelDistributorName" value="${employee.channelDistributor.name }" type="text" class="validate[required] text-input" size="30" onchange="fieldChanged(this);" /><input type="hidden" id="channelDistributorId" name="channelDistributorId" value="${employee.channelDistributor.id}" /><span
												style="color: red;">*</span> <input class="btn" type="button" value="选择" onclick="chooseParentOrganization();" /></td>
										</tr>
										<tr>
											<td align="right">学历：</td>
											<td><input id="qualificationsCode" name="qualificationsCode" type="text" size="30" value="${employee.qualificationsCode}" onchange="fieldChanged(this);" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">民族 ：</td>
											<td><input id="national" name="national" type="text" size="30" value="${employee.national}" onchange="fieldChanged(this);" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">性别：</td>
											<td><input type="radio" id="gender1" name="gender" value="1" checked="checked" />男 <input type="radio" id="gender2" name="gender" value="0" />女</td>
											<td align="right">出生日期：</td>
											<td><input id="birthDate" name="birthDate" value="<s:date name="%{employee.birthday}" format="yyyy-MM-dd"/>" type="text" onchange="fieldChanged(this);" class="validate[required] text-input" /> <img onclick="showTime('birthDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="middle"><span
												style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">籍贯：</td>
											<td><input id="birthplace" name="birthplace" type="text" size="30" value="${employee.birthplace}" onchange="fieldChanged(this);" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">户籍地址：</td>
											<td><input id="residenceAddress" name="residenceAddress" type="text" size="30" value="${employee.residenceAddress}" onchange="fieldChanged(this);" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">入职日期：</td>
											<td><input id="entityTime" name="entityTime" value="<s:date name="%{employee.entityTime}" format="yyyy-MM-dd"/>" type="text" onchange="fieldChanged(this);" class="validate[required] text-input" /> <img onclick="showTime('entityTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="middle"><span
												style="color: red;">*</span></td>
											<td align="right">是否离职:&nbsp;</td>
											<td><input type="radio" id="isDemission1" name="isDemission" value="1" />是<input type="radio" id="isDemission2" name="isDemission" value="0" checked="checked" />否</td>
										</tr>
										<tr>
											<td align="right">备注：</td>
											<td colspan="3"><textarea id="memo" name="memo">${employee.memo }</textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
												var memo = KindEditor.create('textarea[name="memo"]', {
												basePath : '${vix}/plugin/KindEditor/',
												width : 750,
												height : 200,
												cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
												uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
												fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
												allowFileManager : true
												});
											</script></td>
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