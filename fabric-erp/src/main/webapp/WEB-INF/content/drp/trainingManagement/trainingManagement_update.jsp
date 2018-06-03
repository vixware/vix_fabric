<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script type="text/javascript">
	function saveOrUpdateChannelDistributor() {
		if ($('#trainingManagementForm').validationEngine('validate')) {
			$.post('${vix}/drp/trainingManagementAction!saveOrUpdate.action', {
			'trainingManagement.id' : $("#id").val(),
			'trainingManagement.name' : $("#name").val(),
			'trainingManagement.trainingObjects' : $("#trainingObjects").val(),
			'trainingManagement.trainingTime' : $("#trainingTime").val(),
			'trainingManagement.trainingLocation' : $("#trainingLocation").val(),
			'trainingManagement.trainingContent' : trainingContent.html()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/trainingManagementAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#trainingManagementForm").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/hr_training.png" alt="" />供应链</a></li>
				<li><a href="#">分销管理</a></li>
				<li><a href="#">经销商管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/trainingManagementAction!goList.action');">培训管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${trainingManagement.id }" />
<div class="content">
	<form id="trainingManagementForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateChannelDistributor()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/drp/trainingManagementAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>新增培训活动</b></strong>
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
											<td align="right">主题：</td>
											<td><input id="name" name="name" type="text" size="30" value="${trainingManagement.name }" /></td>
											<td align="right">培训对象：</td>
											<td><input id="trainingObjects" name="trainingObjects" type="text" size="30" value="${trainingManagement.trainingObjects }" /></td>
										</tr>
										<tr>
											<td align="right">培训时间：</td>
											<td><input id="trainingTime" name="trainingTime" type="text" value="${trainingManagement.trainingTime }" /> <img onclick="showTime('trainingTime','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="middle"></td>
											<td align="right">培训地点：</td>
											<td><input id="trainingLocation" name="trainingLocation" type="text" size="30" value="${trainingManagement.trainingLocation }" /></td>
										</tr>
										<tr>
											<td align="right">培训内容：</td>
											<td colspan="3"><textarea id="trainingContent" name="trainingContent">${trainingManagement.trainingContent }</textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
												var trainingContent = KindEditor.create('textarea[name="trainingContent"]', {
												basePath : '${vix}/plugin/KindEditor/',
												width : 830,
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
				<div class="clearfix" style="background: #FFF; position: relative;">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(6,1,'a',event)"><img src="${vix}/common/img/attachment.png" alt="" />附件</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
							$('#soAttach').datagrid({
							url : '${vix}/drp/trainingManagementAction!getAttachmentsJson.action?id=${trainingManagement.id}',
							title : '附件',
							height : '450',
							fitColumns : true,
							columns : [ [ {
							field : 'id',
							title : '编号',
							width : 80
							}, {
							field : 'name',
							title : '名称',
							width : 180
							}, ] ],
							toolbar : [ {
							id : 'saBtnadd',
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								$.ajax({
								url : '${vix}/drp/trainingManagementAction!addAttachments.action',
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
											uploadFile('${vix}/drp/trainingManagementAction!uploadAttachments.action?id=${trainingManagement.id}', 'fileToUpload');
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
									url : '${vix}/drp/trainingManagementAction!deleteAttachments.action?afId=' + rows[i].id,
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
				</div>
			</dl>
			<!--submenu-->
	</form>
</div>