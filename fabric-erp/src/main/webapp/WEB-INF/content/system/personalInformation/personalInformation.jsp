<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function updateUserAccount() {
		var pwd1 = $("#newpassword").val();
		var pwd2 = $("#passwordConfirm").val();
		var pwd3 = $("#passwordhidden").val();
		var pwd4 = $("#password").val();

		if (pwd1 != pwd2) {
			asyncbox.success("两次新密码不一致，请重新输入！", "提示信息");
			$("#newpassword").val("");
			$("#passwordConfirm").val("");
			return false;
		} else if (pwd3 != pwd4) {
			asyncbox.success("原始密码不正确，请重新输入！", "提示信息");
			$("#password").val("");
			$("#newpassword").val("");
			$("#passwordConfirm").val("");
			return false;
		} else {
			if ($('#userAccountForm').validationEngine('validate')) {
				$.post('${vix}/system/personalInformationAction!saveOrUpdateUserAccount.action', {
					'userAccount.password' : $("#newpassword").val()
				}, function(result) {
					showMessage(result);
					setTimeout("", 1000);
				});
			}
		}
	}
	$("#userAccountForm").validationEngine();
	$("#employeeForm").validationEngine();
	function saveOrUpdateEmployee() {
		if ($('#employeeForm').validationEngine('validate')) {
			$.post('${vix}/system/personalInformationAction!saveOrUpdateEmployee.action', {
			'employee.id' : $("#employeeId").val(),
			'employee.name' : $("#name").val(),
			'employee.idNumber' : $("#idNumber").val(),
			'employee.gender' : $("#gender").val(),
			'employee.birthday' : $("#birthday").val(),
			'employee.bloodType' : $("#bloodType").val(),
			'employee.national' : $("#national").val(),
			'employee.currentResidence' : $("#currentResidence").val(),
			'employee.pictureurl' : $("#pictureurl").val(),
			'employee.telephone' : $("#telephone").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/system/personalInformationAction!goList.action');
			});
		}
	}
	$(function() {
		$("#gender").val('${employee.gender}');
		$("#bloodType").val('${employee.bloodType}');
	});

	$(document).ready(function() {
		$('#personalimg').hover(function() {
			$(this).addClass('otherhover');
			$('#fileToUploadId').removeAttr('style');
		}, function() {
			$(this).removeClass('otherhover');
			$('#fileToUploadId').attr('style', 'display: none;');
		});
	});

	function uploadpic() {
		$("#employeeForm").ajaxSubmit({
		type : "post",
		url : "${vix}/system/personalInformationAction!savePic.action",
		dataType : "text",
		success : function(result) {
			showPic(result);
			$("#pictureurl").val(result);
		}
		});
	}
	function showPic(path) {
		$('#pictureid').attr('src', path).next('span').css('visibility', 'visible');
	}

	
	
	var uadp3 = {
			initDgProxyApply : function(){
				$('#userAccountProxyApplyDg').datagrid({
					title:'申请',
					width :'auto',  
		            height :400, 
					singleSelect: true,
					nowrap: true,
					//autoRowHeight: false,
					//fit:true, vix不能用
					fitColumns:true,
					striped: true,
					collapsible:true,
					url:'${vix}/common/security/userAccountProxyAction!goApplySingleList.action',
					remoteSort: false,
					idField:'id',
					columns:[[
						{field:'ck',checkbox:true},
						{field:'id',hidden:true},
						{field:'userAccount',title:'受理账号',width:80},
						{field:'empName',title:'受理人姓名',width:80},
						{field:'isEnable',title:'状态',width:80,
							formatter:function(value,row,index){
	            				if(value=='Y'){
	            					return '启用';
	            				}else if(value=='N'){
	            					return '禁用'
	            				}
	            				return '';
	            			}
						},
						{field:'memo',title:'说明',width:300}				
					]],
					pagination:true,
					pageSize:12,
					pageList:[12,20,50],
					rownumbers:true,
					toolbar:[{
						id:'btnaddA1',
						text:'增加',
						iconCls:'icon-add',
						handler:function(){
							uadp3.addUserAccountProxyApply('');
						}
					},
					{
						id:'btneditA1',
						text:'修改',
						iconCls:'icon-edit',
						handler:function(){
							debugger;
							var rowId = uadp3.getSelectRowId();
							if(rowId!=null){
								uadp3.addUserAccountProxyApply(rowId);
							}
						}
					},
					{
						id:'btncutA1',
						text:'删除',
						iconCls:'icon-remove',
						handler:function(){
							//$('#btnsave').linkbutton('enable');
							//alert('cut')
							var rowId = uadp3.getSelectRowId();
							if(rowId!=null && confirm("确认要删除申请代理信息吗?") ){
								uadp3.deleteUserAccountProxy(rowId,"userAccountProxyApplyDg");
							}
						}
					}],
					onLoadSuccess:function(data){
					}
				});
				//$('#userAccountProxyApplyDg').datagrid('reload');
			},
			getSelectRowId : function(){
				var rowsTmp = $('#userAccountProxyApplyDg').datagrid("getSelected");
				if(rowsTmp!=null){
					return rowsTmp.id;
				}
				return null;
			},
			addUserAccountProxyApply :function (id){
				$.ajax({
					  url:'${vix}/common/security/userAccountProxyAction!goSaveOrUpdateApply.action?id='+id,
					  cache: false,
					  success: function(html){
						  asyncbox.open({
							 	modal:true,
								width : 560,
								height : 280,
								title:"代理申请",
								html:html,
								callback : function(action){
									if(action == 'ok'){
										if($("#userAccountProxyApplyForm").validationEngine('validate')){
											//showErrorMessage("密码和确认密码不一致!");
											//setTimeout("", 1000);
											//return false;
											if(confirm("确认要保存申请代理信息吗?")){
												var queryString = $('#userAccountProxyApplyForm').formSerialize(); 
												$.post('${vix}/common/security/userAccountProxyAction!saveOrUpdateApply.action',
													queryString,
													function(result){
														showMessage(result);
														setTimeout("", 1000);
														$('#userAccountProxyApplyDg').datagrid('reload');
													}
												 );
											}
										}else {
											return false;
										}
										
									}
								},
								btnsbar : $.btn.OKCANCEL
							});
					  }
				});
			},
			save4Add :function (){
				
			},
			
			deleteUserAccountProxy :function (id,dgId){
				$.ajax({
					  url:'${vix}/common/security/userAccountProxyAction!deleteById.action?id='+id,
					  cache: false,
					  success: function(result){
						  showMessage(result);
						  setTimeout("", 1000);
						  
						  $('#'+dgId).datagrid('reload');
					  }
				});
			}
	};
	
	//代理信息
	var uadp4 = {
			initDgProxyAccept : function(){
				$('#userAccountProxyAcceptDg').datagrid({
					title:'代理',
					width :'auto',  
		            height :400, 
					singleSelect: true,
					nowrap: true,
					//autoRowHeight: false,
					//fit:true, vix不能用
					fitColumns:true,
					striped: true,
					collapsible:true,
					url:'${vix}/common/security/userAccountProxyAction!goAcceptSingleList.action',
					remoteSort: false,
					idField:'id',
					columns:[[
						{field:'ck',checkbox:true},
						{field:'id',hidden:true},
						{field:'userAccount',title:'申请账号',width:80},
						{field:'empName',title:'申请人姓名',width:80},
						{field:'isEnable',title:'状态',width:80,
							formatter:function(value,row,index){
	            				if(value=='Y'){
	            					return '启用';
	            				}else if(value=='N'){
	            					return '禁用'
	            				}
	            				return '';
	            			}
						},
						{field:'memo',title:'说明',width:300}				
					]],
					pagination:true,
					pageSize:12,
					pageList:[12,20,50],
					rownumbers:true,
					
					toolbar:[
					{
						id:'btnloginA1',
						text:'代理登录',
						iconCls:'icon-redo',
						handler:function(){
							var rowsTmp = $('#userAccountProxyAcceptDg').datagrid("getSelected");
							if(rowsTmp.isEnable=='N'){
								showMessage("代理申请未启用！");
								setTimeout("", 1000);
								return;
							}
							var rowId = rowsTmp.id;
							if(rowId!=null && confirm("确认要使用所选账号进行代理登录吗?")){
								uadp4.proxyLogin(rowId);
							}
						}
					}
					/*{
						id:'btnaddA1',
						text:'增加',
						iconCls:'icon-add',
						handler:function(){
							//uadp4.addUserAccountProxyAccept('');
						}
					},
					{
						id:'btneditA1',
						text:'修改',
						iconCls:'icon-edit',
						handler:function(){
							debugger;
							var rowId = uadp4.getSelectRowId();
							if(rowId!=null){
								uadp4.addUserAccountProxyAccept(rowId);
							}
						}
					}					
					*/
					],
					onLoadSuccess:function(data){
					}
				});
				//$('#userAccountProxyApplyDg').datagrid('reload');
			},
			getSelectRowId : function(){
				var rowsTmp = $('#userAccountProxyAcceptDg').datagrid("getSelected");
				if(rowsTmp!=null){
					return rowsTmp.id;
				}
				return null;
			},
			proxyLogin : function (id){
				$.ajax({
					  url:'${vix}/common/security/userAccountProxyAction!proxyLoginById.action?id='+id,
					  cache: false,
					  dataType :"json",
					  async : false,
					  success: function(result){
						  if(result.isSuccess){
						  	showMessage("正在进行代理登录......");
							setTimeout("", 1000);
						  	window.location.href = "${vix}/common/vixAction!goIndex.action";
						  }else{
						  	showMessage(result.msg);
							setTimeout("", 1000);
							return;
						  }
					  }
				});
			}
			
	};
	
	$(function() {
		if (document.getElementById("birthday").value == "") {
			var myDate = new Date();
			$("#birthday").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate());
		}
		
		uadp3.initDgProxyApply();
		uadp4.initDgProxyAccept();
	});
</script>
<style>
.otherhover {
	cursor: pointer;
}
</style>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="img/icon_14.gif" alt="" />打印</a> <a href="#" id="help"><img src="img/icon_15.gif" alt="" />帮助</a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/hr_emp.png" alt="" /> 工作台</a></li>
				<li><a href="#">个人设置</a></li>
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
			<h2>个人信息</h2>
		</div>
		<div id="por_left" class="np_l_l">
			<div class="np_right_box">
				<div class="nprb_title">个人信息配置</div>
				<ul class="datelist">
					<li id="lnp_btn1" class="clearfix on_lnpbtn" onclick="javascript:tabBig(4,1,'lnp','lnp_btn','on_lnpbtn')"><img src="img/system_basic_information.png" /> 基本信息</li>
					<li id="lnp_btn2" class="clearfix" onclick="javascript:tabBig(4,2,'lnp','lnp_btn','on_lnpbtn')"><img src="img/personal_Settings_interface_settings.png" /> 界面设置</li>
					<li id="lnp_btn3" class="clearfix" onclick="javascript:tabBig(4,3,'lnp','lnp_btn','on_lnpbtn')"><img src="img/personal_accounts_and_security_settings.png" /> 账号与安全</li>
				</ul>
				<br>
				<form id="employeeForm" method="post" theme="simple" enctype="multipart/form-data">
					<div id="personalimg">
						<img id="pictureid" height="175" width="175" src="${employee.pictureurl }">
						<div id="fileToUploadId" style="display: none;">
							<input type="file" id="fileToUpload" name="fileToUpload" onchange="uploadpic();" style="width: 175px;" />
						</div>
					</div>
				</form>
			</div>
		</div>
		<div id="por_right" class="np_l_r clearfix" style="position: relative;">
			<div id="por_switch"></div>
			<div id="lnp1">
				<ul class="np_tab clearfix">
					<li class="current"><a href="javascript:;" onclick="javascript:tab(6,1,'np',event)">个人信息</a></li>
					<!-- <li><a href="javascript:;" onclick="javascript:tab(6,2,'np',event)">联系方式</a></li> -->
					<li><a href="javascript:;" onclick="javascript:tab(6,3,'np',event);showData();">个人常用邮箱设置</a></li>
				</ul>
				<div class="np_clist" id="np1">
					<form id="employeeForm">
						<input type="hidden" id="employeeId" name="employeeId" value="${employee.id}" /> <input type="hidden" id="pictureurl" name="pictureurl" value="${employee.pictureurl}" />
						<table width="100%">
							<tbody>
								<tr height="40">
									<td align="center">姓名：</td>
									<td><input id="name" name="name" value="${employee.name}" type="text" class="ipt"></td>
								</tr>
								<tr height="40">
									<td align="center">身份证号：</td>
									<td><input id="idNumber" name="idNumber" value="${employee.idNumber}" class="ipt" type="text"></td>
								</tr>
								<tr height="40">
									<td align="center">性别：</td>
									<td><select id="gender" name="gender">
											<option value="男">男</option>
											<option value="女">女</option>
									</select></td>
								</tr>
								<tr height="40">
									<td align="center">生日：</td>
									<td><input id="birthday" name="birthday" value="<s:date format="yyyy-MM-dd" name="%{#employee.birthday}" />" type="text" class="ipt" /><img onclick="showTime('birthday','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
								</tr>
								<tr height="40">
									<td align="center">血型：</td>
									<td><select id="bloodType" class="bloodType">
											<option value="A">A</option>
											<option value="B">B</option>
											<option value="O">O</option>
											<option value="AB">AB</option>
											<option value="其它">其它</option>
									</select></td>
								</tr>
								<tr height="40">
									<td align="center">民族：</td>
									<td><input id="national" name="national" value="${employee.national}" class="ipt" type="text"></td>
								</tr>
								<tr height="40">
									<td align="center">地址：</td>
									<td><input id="currentResidence" name="currentResidence" value="${employee.currentResidence}" class="ipt wb80" type="text"></td>
								</tr>
								<tr height="40">
									<td align="center">联系电话：</td>
									<td><input id="telephone" name="telephone" value="${employee.telephone}" class="ipt" type="text"></td>
								</tr>
								<tr>
									<td colspan="2" class="tc" style="padding: 10px 0;"><input name="" type="button" onclick="saveOrUpdateEmployee();" value="保存" class="btn" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="button" value="取消" class="btn" /></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
				<div class="np_clist" id="np3" style="display: none; height: 450px;">
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
				<div class="np_clist" id="np21"></div>
				<div class="np_clist" id="np22" style="display: none;"></div>
				<div class="np_clist" id="np23" style="display: none;"></div>
			</div>
			<div id="lnp3" style="display: none;">
				<ul class="np_tab">
					<li class="current"><a href="javascript:;" onclick="javascript:tab(6,1,'np3',event)">账号</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,2,'np3',event)">安全</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,3,'np3',event)">账号申请代理</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,4,'np3',event)">账号代理登录</a></li>
				</ul>
				<div class="np_clist" id="np31">
					<form id="userAccountForm">
						<input type="hidden" id="userAccountId" name="userAccountId" value="${userAccount.id}" /> <input type="hidden" id="passwordhidden" name="passwordhidden" value="${userAccount.password}" />
						<table width="100%">
							<tbody>
								<tr height="40">
									<td align="center">账户:</td>
									<td><input type="text" id="account" name="account" value="${userAccount.account}" class="ipt" /></td>
								</tr>
								<tr height="40">
									<td align="center">当前密码:</td>
									<td><input type="password" id="password" name="password" class="ipt" /></td>
								</tr>
								<tr height="40">
									<td align="center">新密码:</td>
									<td><input type="password" id="newpassword" name="newpassword" class="ipt" /></td>
								</tr>
								<tr height="40">
									<td align="center">确认新密码:</td>
									<td><input type="password" id="passwordConfirm" name="passwordConfirm" class="ipt" /></td>
								</tr>
								<tr>
									<td colspan="2" class="tc" style="padding: 10px 0;"><input name="" type="button" onclick="updateUserAccount();" value="保存" class="btn" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="button" value="取消" class="btn" /></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
				<div class="np_clist" id="np32" style="display: none;"></div>

				<!-- 账号代理申请 -->
				<div class="np_clist" id="np33" style="display: none; height: 450px;">
					<div style="padding: 8px;">
						<%-- --%>
						<table id="userAccountProxyApplyDg" style="width: 900px; height: 450px; margin: 6px;" />
						<%-- <table id="userAccountProxyApplyDg" class="easyui-datagrid" style="width:900px;height: 450px; margin: 6px;"
							data-options="iconCls: 'icon-edit',singleSelect: true,fitColumns: true,rownumbers : true, toolbar: '#userAccountProxyApplyDgTb',url:'${vix}/common/security/userAccountProxyAction!goApplySingleList.action'">
							<thead>
								<tr>
									<th data-options="field:'id',width:150,align:'left'">受理账号A</th>
									<th data-options="field:'userAccount',width:150,align:'left'">受理账号A</th>
									<th data-options="field:'empName',width:150,align:'left'">受理人姓名</th>
									<th data-options="field:'memo',width:250,align:'left'">说明</th>
								</tr>
							</thead>
						</table>
						<div id="userAccountProxyApplyDgTb" style="height: auto">
							<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="addProxyApply()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a>
							<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="removeProxyApply()"> <span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
						</div> --%>
						<script type="text/javascript">
							/*
							$('#userAccountProxyApplyDg').datagrid({
								url:'${vix}/common/security/userAccountProxyAction!goApplySingleList.action',
	    						width :'auto'		
	    					});
							*/
							
						</script>
					</div>
				</div>

				<!-- 账号代理登录 -->
				<div class="np_clist" id="np34" style="display: none;">
					<table id="userAccountProxyAcceptDg" style="width: 900px; height: 450px; margin: 6px;" />

				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>