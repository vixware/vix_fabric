<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function updateUserAccount() {
		var pwd1 = $("#password").val();
		var pwd2 = $("#passwordConfirm").val();

		if (pwd1 != pwd2) {
			asyncbox.success("两次密码不一致，请重新输入！", "提示信息");
			$("#password").val("");
			$("#passwordConfirm").val("");
			return false;
		} else {
			if ($('#userAccountForm').validationEngine('validate')) {
				$.post('${vix}/system/personalInformationAction!saveOrUpdateUserAccount.action', {
					'userAccount.password' : $("#password").val()
				}, function(result) {
					showMessage(result);
					setTimeout("", 1000);
				});
			}
		}
	}
	$("#userAccountForm").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="img/icon_14.gif" alt="" />打印</a> <a href="#" id="help"><img src="img/icon_15.gif" alt="" />帮助</a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/hr_emp.png" alt="" /> 系统管理</a></li>
				<li><a href="#">基础设置</a></li>
				<li><a href="#">运行参数</a></li>
			</ul>
		</div>
	</h2>
</div>
<!-- sub menu -->
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_head -->
	<div class="npcontent clearfix">
		<div class="np_left_title">
			<h2>系统设置</h2>
		</div>
		<div id="por_left" class="np_l_l">
			<div class="np_right_box">
				<div class="nprb_title">系统设置</div>
				<ul class="datelist">
					<li id="lnp_btn1" class="clearfix on_lnpbtn" onclick="javascript:tabBig(10,1,'lnp','lnp_btn','on_lnpbtn')"><img src="img/system_basic_information.png" /> 模块设置</li>
					<li id="lnp_btn2" class="clearfix" onclick="javascript:tabBig(10,2,'lnp','lnp_btn','on_lnpbtn')"><img src="img/personal_Settings_interface_settings.png" /> 功能设置</li>
					<li id="lnp_btn3" class="clearfix" onclick="javascript:tabBig(10,3,'lnp','lnp_btn','on_lnpbtn')"><img src="img/personal_accounts_and_security_settings.png" /> 初始化设置</li>
					<li id="lnp_btn4" class="clearfix" onclick="javascript:tabBig(10,4,'lnp','lnp_btn','on_lnpbtn')"><img src="img/personal_accounts_and_security_settings.png" /> 图片设置</li>
					<li id="lnp_btn5" class="clearfix" onclick="javascript:tabBig(10,5,'lnp','lnp_btn','on_lnpbtn')"><img src="img/personal_accounts_and_security_settings.png" /> LOGO设置</li>
				</ul>
			</div>
		</div>
		<div id="por_right" class="np_l_r clearfix" style="position: relative;">
			<div id="por_switch"></div>
			<div id="lnp1">
				<ul class="np_tab clearfix">
					<li class="current"><a href="javascript:;" onclick="javascript:tab(10,1,'np',event)">公司基本信息</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(10,2,'np',event)">背景图片设置</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(10,3,'np',event)">财年</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(10,4,'np',event)">模块启用控制</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(10,5,'np',event)">全文检索配置</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(10,6,'np',event)">系统控制类参数</a></li>
				</ul>
				<div class="np_clist lineh30" id="np1">
					<table width="100%">
						<tbody>
							<tr height="40">
								<td>姓名：</td>
								<td><input class="ipt" value="" type="text"></td>
							</tr>
							<tr height="40">
								<td>性别：</td>
								<td><select id="sex_select">
										<option value="1">男</option>
										<option value="2">女</option>
								</select></td>
							</tr>
							<tr height="40">
								<td>生日：</td>
								<td><input id="createTime" name="wimStockrecords.createTime" value="${wimStockrecords.createTime}" onchange="salesOrderFieldChanged(this);" type="text" class="validate[required] text-input" /><img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span
									style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<td><label for="">血型：</label></td>
								<td><select id="blood_select" class="logtimeaddselect">
										<option value="1">A</option>
										<option value="2">B</option>
										<option value="3">O</option>
										<option value="4">AB</option>
										<option value="5">其它</option>
								</select></td>
							</tr>
							<tr height="40">
								<td>地址：</td>
								<td>
									<div>
										<select style="width: 120px;" id="addslt_c_1"><option value="0">选择国家</option>
											<option value="1">中国</option>
										</select> <select style="width: 75px;" id="addslt_s_1"><option value="0">选择省</option>
											<option value="34">安徽</option>
										</select> <select style="width: 105px;" id="addslt_cty_1"><option value="0">选择市</option>
											<option value="6">保定</option>
										</select>
									</div>
								</td>
							</tr>
							<tr height="40">
								<td>公司名称：</td>
								<td><input class="ipt wb80" type="text"></td>
							</tr>
							<tr height="40">
								<td>详细地址：</td>
								<td><input class="ipt wb80" type="text"><span class="point" id="cpy_address_count"></span></td>
							</tr>
							<tr>
								<td colspan="5" class="tc" style="padding: 10px 0;"><input name="" type="button" value="保存" class="btn" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="button" value="取消" class="btn" /></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="np_clist lineh30" id="np2" style="display: none; height: 450px;">
					<table width="100%">
						<tbody>
							<tr height="40">
								<td>电话:</td>
								<td><input type="text" class="ipt wb80" value=""></td>
							</tr>
							<tr height="40">
								<td>手机：</td>
								<td><input type="text" class="ipt wb80" value=""></td>
							</tr>
							<tr height="40">
								<td>QQ：</td>
								<td><input type="text" class="ipt wb80" value=""></td>
							</tr>
							<tr height="40">
								<td>Email：</td>
								<td><input type="text" class="ipt wb80" value=""></td>
							</tr>
							<tr>
								<td colspan="5" class="tc" style="padding: 10px 0;"><input name="" type="button" value="保存" class="btn" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="button" value="取消" class="btn" /></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="np_clist lineh30" id="np3" style="display: none; height: 450px;">
					<div style="padding: 8px;">
						<table id="personalEmail" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,fitColumns: true,rownumbers : true, toolbar: '#personalEmailTb',url: '${vix}/system/personalInformationAction!getPersonalEmailJson.action',onClickRow: onDlClickRow">
							<thead>
								<tr>
									<th data-options="field:'emailAddress',width:250,align:'center',editor:'text'">E-mail地址</th>
									<th data-options="field:'emailUserName',width:150,align:'center',editor:'text'">E-mail账号</th>
									<th data-options="field:'emailPassword',width:150,align:'center',editor:'text'">E-mail密码</th>
									<th field="mailServerHost" width="150" formatter="mailServerHostFormatter" editor="{type:'combobox',options:{valueField:'mailServerHost',textField:'name',data:mailServerHostData}}">使用协议</th>
									<th field="type" width="100" formatter="typeFormatter" editor="{type:'combobox',options:{valueField:'type',textField:'name',data:typeData}}">邮箱类型</th>
									<th field="isDefault" width="100" formatter="isDefaultFormatter" editor="{type:'combobox',options:{valueField:'isDefault',textField:'name',data:isDefaultData}}">是否默认</th>
								</tr>
							</thead>
						</table>
						<div id="personalEmailTb" style="height: auto">
							<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="appendDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /> </span> </span> </a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="removeDlAddress()"> <span
								class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /> </span> </span>
							</a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="saveDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /> </span> </span> </a>
						</div>
						<script type="text/javascript">
							var mailServerHostData = [ {
							mailServerHost : 'smtp.126.com',
							name : 'smtp.126.com'
							}, {
							mailServerHost : 'pop.126.com',
							name : 'pop.126.com'
							} ];
							function mailServerHostFormatter(value) {
								for (var i = 0; i < mailServerHostData.length; i++) {
									if (mailServerHostData[i].mailServerHost == value)
										return mailServerHostData[i].name;
								}
								return value;
							}
							var typeData = [ {
							type : 'S',
							name : '发送'
							}, {
							type : 'R',
							name : '接收'
							} ];
							function typeFormatter(value) {
								for (var i = 0; i < typeData.length; i++) {
									if (typeData[i].type == value)
										return typeData[i].name;
								}
								return value;
							}
							var isDefaultData = [ {
							isDefault : '1',
							name : '是'
							}, {
							isDefault : '0',
							name : '否'
							} ];
							function isDefaultFormatter(value) {
								for (var i = 0; i < isDefaultData.length; i++) {
									if (isDefaultData[i].isDefault == value)
										return isDefaultData[i].name;
								}
								return value;
							}
							$('#personalEmail').datagrid();
							var editIndexDlAddress = undefined;
							function endDlEditing() {
								if (editIndexDlAddress == undefined) {
									return true;
								}
								if ($('#personalEmail').datagrid('validateRow', editIndexDlAddress)) {
									$('#personalEmail').datagrid('endEdit', editIndexDlAddress);
									editIndexDlAddress = undefined;
									return true;
								} else {
									return false;
								}
							}
							function onDlClickRow(index) {
								if (editIndexDlAddress != index) {
									if (endDlEditing()) {
										$('#personalEmail').datagrid('selectRow', index).datagrid('beginEdit', index);
										editIndexDlAddress = index;
									} else {
										$('#personalEmail').datagrid('selectRow', editIndexDlAddress);
									}
								}
							}
							function appendDlAddress() {
								if (endDlEditing()) {
									$('#personalEmail').datagrid('appendRow', {});
									editIndexDlAddress = $('#personalEmail').datagrid('getRows').length - 1;
									$('#personalEmail').datagrid('selectRow', editIndexDlAddress).datagrid('beginEdit', editIndexDlAddress);
								}
							}
							function removeDlAddress() {
								if (editIndexDlAddress == undefined) {
									return;
								}
								var rows = $('#personalEmail').datagrid("getSelections"); //获取你选择的所有行	
								//循环所选的行
								for (var i = 0; i < rows.length; i++) {
									var index = $('#personalEmail').datagrid('getRowIndex', rows[i]);//获取某行的行号
									$('#personalEmail').datagrid('deleteRow', index); //通过行号移除该行
									$.ajax({
									url : '${vix}/system/personalInformationAction!deleteById.action?id=' + rows[i].id,
									cache : false
									});
								}
								$('#personalEmail').datagrid('cancelEdit', editIndexDlAddress).datagrid('deleteRow', editIndexDlAddress);
								editIndexDlAddress = undefined;
							}
							function saveDlAddress() {
								if (endDlEditing()) {
									$('#personalEmail').datagrid('acceptChanges');
									saveOrUpdatePersonalEmail();
								}
							}
							function saveOrUpdatePersonalEmail() {
								var personalEmailData = $("#personalEmail").datagrid("getRows");
								var personalEmailJson = JSON.stringify(personalEmailData);
								$.post('${vix}/system/personalInformationAction!saveOrUpdatePersonalEmail.action', {
									'personalEmailJson' : personalEmailJson
								}, function(result) {
									showMessage(result);
									setTimeout("", 1000);
									$('#personalEmail').datagrid("reload");
								});
							}
							function showData() {
								$('#personalEmail').datagrid("load");
							}
						</script>
					</div>
				</div>
			</div>
			<div id="lnp2" style="display: none;">
				<ul class="np_tab">
					<li class="current"><a href="javascript:;" onclick="javascript:tab(6,1,'np2',event)">菜单</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,2,'np2',event)">功能</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,3,'np2',event)">自定义功能</a></li>
				</ul>
				<div class="np_clist" id="np21">
					<table width="100%" cellspacing="0" cellpadding="0" border="0" class="msgtable">
					</table>
				</div>
				<div class="np_clist" id="np22" style="display: none;">
					<div id="projcontent"></div>
				</div>
				<div class="np_clist lineh30" id="np23" style="display: none;">
					<table width="100%">
					</table>
				</div>
			</div>
			<div id="lnp3" style="display: none;">
				<ul class="np_tab">
					<li class="current"><a href="javascript:;" onclick="javascript:tab(6,1,'np3',event)">账号</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,2,'np3',event)">安全</a></li>
				</ul>
				<div class="np_clist" id="np31">
					<form id="userAccountForm">
						<input type="hidden" id="userAccountId" name="userAccountId" value="${userAccount.id}" />
						<table width="100%">
							<tbody>
								<tr height="40">
									<td align="right">账户:&nbsp;</td>
									<td><input type="text" id="account" name="account" value="${userAccount.account}" class="ipt wb80" /></td>
								</tr>
								<tr height="40">
									<td align="right">密码:&nbsp;</td>
									<td><input type="password" id="password" name="password" value="${userAccount.password}" class="ipt wb80" /></td>
								</tr>
								<tr height="40">
									<td align="right">确认密码:&nbsp;</td>
									<td><input type="password" id="passwordConfirm" name="passwordConfirm" class="ipt wb80" /></td>
								</tr>
								<tr>
									<td colspan="5" class="tc" style="padding: 10px 0;"><input name="" type="button" onclick="updateUserAccount();" value="保存" class="btn" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="button" value="取消" class="btn" /></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
				<div class="np_clist lineh30" id="np32" style="display: none;">
					<table width="100%">
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>