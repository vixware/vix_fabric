<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdateChannelDistributor() {
		if ($('#channelDistributorForm').validationEngine('validate')) {
			$("#channelDistributorForm").ajaxSubmit({
			type : "post",
			url : "${vix}/drp/storeInformationAction!saveOrUpdate.action",
			dataType : "text",
			success : function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/storeInformationAction!goList.action');
			}
			});
		} else {
			return false;
		}
	}
	$("#channelDistributorForm").validationEngine();
	function chooseParentOrganization() {
		$.ajax({
		url : '${vix}/drp/storeInformationAction!goChooseOrganization.action',
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
							asyncbox.success("不允许引用本公司为父公司!", "提示信息");
						} else {
							$("#organizationId").val(result[0]);
							$("#organizationName").val(result[1]);
						}
					} else {
						$("#organizationId").val("");
						$("#organizationName").val("");
						asyncbox.success("请选择公司信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
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

	function updateImage() {
		$.ajax({
		url : '${vix}/purchase/purchaseOrderAction!addAttachments.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 364,
			height : 160,
			title : "上传图片",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					uploadpic();
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
	$(function() {
		if (document.getElementById("startTime").value == "") {
			var myDate = new Date();
			$("#startTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate());
		}
	});

	function uploadpic() {
		$("#channelDistributorForm").ajaxSubmit({
		type : "post",
		url : "${vix}/drp/storeInformationAction!savePic.action",
		dataType : "text",
		success : function(result) {
			showPic(result);
			$("#picture").val(result);
		}
		});
	}
	function showPic(path) {
		$('#pictureid').attr('src', path).next('span').css('visibility', 'visible');
	}
</script>

<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<s:if test="source =='drp'">
					<li><a href="#"><img src="${vix}/common/img/drp_shop.png" alt="" /> <s:text name="supplyChain" /> </a></li>
					<li><a href="#"><s:text name="drp_management" /> </a></li>
					<li><a href="#"><s:text name="drp_store_management" /> </a></li>
					<li><a href="#" onclick="loadContent('${vix}/drp/storeInformationAction!goList.action');"><s:text name="drp_store_information" /> </a></li>
				</s:if>
				<s:else>
					<li><a href="#"><img src="${vix}/common/img/drp_shop.png" alt="" /> <s:text name="supplyChain" /> </a></li>
					<li><a href="#">连锁经营管理 </a></li>
					<li><a href="#">门店管理 </a></li>
					<li><a href="#" onclick="loadContent('${vix}/drp/storeInformationAction!goList.action');"><s:text name="drp_store_information" /> </a></li>
				</s:else>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<form id="channelDistributorForm" method="post" theme="simple" enctype="multipart/form-data">
		<s:hidden id="id" name="channelDistributor.id" value="%{channelDistributor.id}" theme="simple" />
		<s:hidden id="picture" name="channelDistributor.picture" value="%{channelDistributor.picture}" theme="simple" />
		<s:hidden id="updateField" name="updateField" value="%{updateField}" theme="simple" />
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateChannelDistributor()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"
						onclick="loadContent('${vix}/drp/storeInformationAction!goList.action?pageNo=${pageNo}&source='+'${source}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b><s:text name="drp_store_information" /> </b> </strong>
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
											<td width="15%" align="right">编码：</td>
											<td width="35%"><input id="code" name="channelDistributor.code" value="${channelDistributor.code }" type="text" style="width: 250px;" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td width="15%" align="right">名称：</td>
											<td width="35%"><input id="name" name="channelDistributor.name" value="${channelDistributor.name }" type="text" style="width: 250px;" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">法人：</td>
											<td><input id="artificialPerson" name="channelDistributor.artificialPerson" value="${channelDistributor.artificialPerson }" type="text" style="width: 250px;" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td align="right">营业执照编号：</td>
											<td><input id="permit" name="channelDistributor.permit" value="${channelDistributor.permit }" type="text" style="width: 250px;" onchange="fieldChanged(this);" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">简称：</td>
											<td><input id="shortName" name="channelDistributor.shortName" value="${channelDistributor.shortName }" type="text" style="width: 250px;" onchange="fieldChanged(this);" /></td>
											<td align="right">负责人：</td>
											<td><input id="employeeName" name="employeeName" value="${channelDistributor.employee.name }" type="text" style="width: 250px;"><input class="btn" type="button" value="选择" onclick="chooseEmployees();" onchange="fieldChanged(this);" /> <s:hidden id="employeeId" name="channelDistributor.employee.id"
													value="%{channelDistributor.employee.id}" theme="simple" /></td>
										</tr>
										<tr>
											<td align="right">电话：</td>
											<td><input id="telephone" name="channelDistributor.telephone" value="${channelDistributor.telephone }" type="text" style="width: 250px;" onchange="fieldChanged(this);" /></td>
											<td align="right">邮箱：</td>
											<td><input id="email" name="channelDistributor.email" value="${channelDistributor.email }" type="text" style="width: 250px;" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">所属公司：</td>
											<td><input type="text" id="organizationName" name="organizationName" readonly="readonly" value="${channelDistributor.organization.orgName }" style="width: 250px;" class="validate[required] text-input" onchange="fieldChanged(this);" /><input class="btn" type="button" value="选择" onclick="chooseParentOrganization();" /> <span
												style="color: red;">*</span> <s:hidden id="organizationId" name="channelDistributor.organization.id" value="%{channelDistributor.organization.id}" theme="simple" />
											<td align="right">地址：</td>
											<td><input id="address" name="channelDistributor.address" value="${channelDistributor.address }" type="text" style="width: 250px;" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<th>开始时间：</th>
											<td><input id="startTime" name="channelDistributor.startTime" onchange="fieldChanged(this);" value="<s:date name='%{channelDistributor.startTime}' format='yyyy-MM-dd'/>" type="text" /><img onclick="showTime('startTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<th>结束时间：</th>
											<td><input id="endTime" name="channelDistributor.endTime" onchange="fieldChanged(this);" value="<s:date name='%{channelDistributor.endTime}' format='yyyy-MM-dd'/>" type="text" /><img onclick="showTime('endTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">备注：</td>
											<td colspan="3"><textarea id="memo" name="channelDistributor.memo">${channelDistributor.memo }</textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
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
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b></span> <strong>其他</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right" width="15%">开户银行：</td>
											<td width="35%"><input id="openingBank" name="channelDistributor.openingBank" value="${channelDistributor.openingBank }" type="text" style="width: 250px;" onchange="fieldChanged(this);" /></td>
											<td align="right" width="15%">银行帐号：</td>
											<td width="35%"><input id="bankAccount" name="channelDistributor.bankAccount" value="${channelDistributor.bankAccount }" type="text" style="width: 250px;" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">注册资金：</td>
											<td><input id="registeredCapital" name="channelDistributor.registeredCapital" value="${channelDistributor.registeredCapital }" type="text" style="width: 250px;" onchange="fieldChanged(this);" /></td>
											<td align="right">币种:&nbsp;</td>
											<td><s:select id="currencyTypeId" name="channelDistributor.currencyType.id" headerKey="-1" headerValue="--请选择--" list="%{currencyTypeList}" listValue="name" listKey="id" value="%{channelDistributor.currencyType.id}" multiple="false" theme="simple" onchange="fieldChanged(this);"></s:select><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">税号：</td>
											<td><input id="taxCode" name="channelDistributor.taxCode" value="${channelDistributor.taxCode }" type="text" style="width: 250px;" onchange="fieldChanged(this);" /></td>
											<td align="right">员工人数：</td>
											<td><input id="employeeCounts" name="channelDistributor.employeeCounts" value="${channelDistributor.employeeCounts }" type="text" style="width: 250px;" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">等级：</td>
											<td><select id="level" name="channelDistributor.level" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<option value="A级">A级</option>
													<option value="B级">B级</option>
													<option value="C级" selected="selected">C级</option>
											</select></td>
											<td align="right">状态：</td>
											<td><select id="status" name="channelDistributor.status" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<option value="1" selected="selected">营业</option>
													<option value="0">暂停营业</option>
											</select></td>
										</tr>
										<tr>
											<td align="right">所属地区：</td>
											<td><input id="region" name="channelDistributor.region" value="${channelDistributor.region }" type="text" style="width: 250px;" onchange="fieldChanged(this);" /></td>
											<td align="right">地图链接：</td>
											<td><input id="maplink" name="channelDistributor.maplink" value="${channelDistributor.maplink }" type="text" style="width: 250px;" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">图片：</td>
											<td><img id="pictureid" height="100" width="100" src="${channelDistributor.picture }"> <input type="file" id="fileToUpload" name="fileToUpload" onchange="uploadpic();" onchange="fieldChanged(this);" /></td>
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