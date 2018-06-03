<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdateOrder() {
		var cardData = $("#dlAddress").datagrid("getRows");
		var cardJson = JSON.stringify(cardData);
		if ($('#order').validationEngine('validate')) {
			$.post('${vix}/dtbcenter/driverAction!saveOrUpdate.action', {
			'driver.id' : $("#id").val(),
			'driver.scode' : $("#scode").val(),
			'driver.name' : $("#name").val(),
			'driver.englishName' : $("#englishName").val(),
			'driver.sex' : $(":radio[name=sex][checked]").val(),
			'driver.identityNumber' : $("#identityNumber").val(),
			'driver.educationalBackground' : $("#educationalBackground").val(),
			'driver.department' : $("#department").val(),
			'driver.birthday' : $("#birthday").val(),
			'driver.position' : $("#position").val(),
			'driver.workNumber' : $("#workNumber").val(),
			'driver.mobile' : $("#mobile").val(),
			'driver.telephone' : $("#telephone").val(),
			'driver.email' : $("#email").val(),
			'driver.jobDepartment' : $("#jobDepartment").val(),
			'driver.description' : $("#description").val(),
			'updateField' : updateField,
			'cardJson' : cardJson
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/dtbcenter/driverAction!goList.action');
			});
		}
	}
	$("#order").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/transportationresourcemanagement.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="dtbcenter" /> </a></li>
				<li><a href="#"><s:text name="dtbcenter_transreource" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/driverAction!goList.action');"><s:text name="dtbcenter_personalManagement" /> </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="driver.id" value="${driver.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /> </a> <a href="#" onclick="loadContent('${vix}/dtbcenter/driverAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b><s:text name="dtbcenter_personalinformation" /> </b> </strong>
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
											<th>员工编号：</th>
											<td><input id="scode" name="scode" value="${driver.scode }" type="text" size="20" class="validate[required] text-input" onchange="fieldChanged(this);" /> <span style="color: red;">*</span></td>
											<th>姓名：</th>
											<td><input id="name" name="name" value="${driver.name}" type="text" size="20" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>英文名：</th>
											<td><input id="englishName" name="englishName" value="${driver.englishName }" type="text" size="30" onchange="fieldChanged(this);"></td>
											<th>性别：</th>
											<td><input id="sex1" name="sex" type="radio" value="男" checked="checked" />男 <input id="sex2" name="sex" type="radio" value="女" />女</td>
										</tr>
										<tr>
											<th>身份证号：</th>
											<td><input id="identityNumber" name="identityNumber" value="${driver.identityNumber}" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<th>生日：</th>
											<td><input id="birthday" name="birthday" value="<fmt:formatDate value="${driver.birthday}" pattern="yyyy-MM-dd" />" type="text" readonly="readonly" onchange="fieldChanged(this);" /><img onclick="showTime('birthday','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th>学历：</th>
											<td><select id="educationalBackground" name="educationalBackground" class="validate[required] text-input" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<option value="高中" selected="selected">高中</option>
													<option value="大学">大学</option>
											</select><span style="color: red;">*</span></td>
											<th>所属部门：</th>
											<td><input id="department" name="department" value="${driver.department}" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<th>职务：</th>
											<td><input id="position" name="position" value="${driver.position}" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<th>手机号码：</th>
											<td><input id="mobile" name="mobile" value="${driver.mobile}" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<th>工号：</th>
											<td><input id="workNumber" name="workNumber" value="${driver.workNumber}" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<th>联系电话：</th>
											<td><input id="telephone" name="telephone" value="${driver.telephone}" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<th>E-MAIL：</th>
											<td><input id="email" name="email" value="${driver.email}" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<th>任职部门：</th>
											<td><input id="jobDepartment" name="jobDepartment" value="${driver.jobDepartment}" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<th>描述：</th>
											<td><input id="description" name="description" value="${driver.description}" type="text" size="30" onchange="fieldChanged(this);" /></td>
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
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />证件</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="dlAddress" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,rownumbers : true,toolbar: '#dlAddressTb',url: '${vix}/dtbcenter/driverAction!getCardJson.action?id=${driver.id}',onClickRow: onDlClickRow">
								<thead>
									<tr>
										<th data-options="field:'docCode',width:100,align:'center',editor:'text'">编号</th>
										<th field="docType" width="100" editor="{type:'combobox',options:{valueField:'docType',textField:'name',data:products}}">类型</th>
										<th data-options="field:'belongs',width:100,align:'center',editor:'text'">证件所有人</th>
										<th data-options="field:'status',width:100,align:'center',editor:'text'">状态</th>
										<th data-options="field:'location',width:100,align:'center',editor:'text'">存放地点</th>
										<th data-options="field:'registerTime',width:100,editor:'datebox'">登记时间</th>
										<th data-options="field:'validUntil',width:100,editor:'datebox'">有效期至</th>
									</tr>
								</thead>
							</table>
							<div id="dlAddressTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="appendDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /> </span> </span> </a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-remove',plain:true" onclick="removeDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /> </span> </span> </a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-save',plain:true"
									onclick="saveDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /> </span> </span> </a>
							</div>
							<script type="text/javascript">
								var products = [ {
								docType : 'FI-SW-01',
								name : 'Koi'
								}, {
								docType : 'K9-DL-01',
								name : 'Dalmation'
								}, {
								docType : 'RP-SN-01',
								name : 'Rattlesnake'
								}, {
								docType : 'RP-LI-02',
								name : 'Iguana'
								}, {
								docType : 'FL-DSH-01',
								name : 'Manx'
								}, {
								docType : 'FL-DLH-02',
								name : 'Persian'
								}, {
								docType : 'AV-CB-01',
								name : 'Amazon Parrot'
								} ];
								$('#dlAddress').datagrid();
								var editIndexDlAddress = undefined;
								function endDlEditing() {
									if (editIndexDlAddress == undefined) {
										return true;
									}
									if ($('#dlAddress').datagrid('validateRow', editIndexDlAddress)) {
										$('#dlAddress').datagrid('endEdit', editIndexDlAddress);
										editIndexDlAddress = undefined;
										return true;
									} else {
										return false;
									}
								}
								function onDlClickRow(index) {
									if (editIndexDlAddress != index) {
										if (endDlEditing()) {
											$('#dlAddress').datagrid('selectRow', index).datagrid('beginEdit', index);
											editIndexDlAddress = index;
										} else {
											$('#dlAddress').datagrid('selectRow', editIndexDlAddress);
										}
									}
								}
								function appendDlAddress() {
									if (endDlEditing()) {
										$('#dlAddress').datagrid('appendRow', {});
										editIndexDlAddress = $('#dlAddress').datagrid('getRows').length - 1;
										$('#dlAddress').datagrid('selectRow', editIndexDlAddress).datagrid('beginEdit', editIndexDlAddress);
									}
								}
								function removeDlAddress() {
									if (editIndexDlAddress == undefined) {
										return;
									}
									$('#dlAddress').datagrid('cancelEdit', editIndexDlAddress).datagrid('deleteRow', editIndexDlAddress);
									editIndexDlAddress = undefined;
								}
								function saveDlAddress() {
									if (endDlEditing()) {
										$('#dlAddress').datagrid('acceptChanges');
									}
								}
							</script>
						</div>
					</div>
				</div>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>