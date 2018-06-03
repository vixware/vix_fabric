<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function saveOrUpdateMeasureUnitGroup() {
		if ($('#measureUnitGroupForm').validationEngine('validate')) {
			$.post('${vix}/common/measureUnitGroupAction!saveOrUpdate.action', {
			'measureUnitGroup.id' : $("#id").val(),
			'measureUnitGroup.organization.id' : $("#organizationId").val(),
			'measureUnitGroup.code' : $("#code").val(),
			'measureUnitGroup.name' : $("#name").val(),
			'measureUnitGroup.type' : $("#type").val(),
			'measureUnitGroup.status' : $("#status").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/common/measureUnitGroupAction!goList.action');
			});
		}
	}
	$("#measureUnitGroupForm").validationEngine();
	$(function() {
		if (document.getElementById("type").value == "") {
			$("#type").val('${measureUnitGroup.type}');
		}
		if (document.getElementById("status").value == "") {
			$("#status").val('${measureUnitGroup.status}');
		}
		if (document.getElementById("organizationId").value == "") {
			$("#organizationId").val('${measureUnitGroup.organization.id}');
		}
	});
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/sys_settings.png" alt="" />系统管理</a></li>
				<li><a href="#">基础设置</a></li>
				<li><a href="#">字典管理</a></li>
				<li><a href="#">计量单位</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${measureUnitGroup.id}" />
<div class="content">
	<form id="measureUnitGroupForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateMeasureUnitGroup()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"
						onclick="loadContent('${vix}/common/measureUnitGroupAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>计量单位组</b> </strong>
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
										<tr height="40">
											<td align="right">公司:&nbsp;</td>
											<td><s:select id="organizationId" headerKey="-1" headerValue="--请选择--" list="%{organizationList}" listValue="orgName" listKey="id" value="%{measureUnitGroup.organization.id}" multiple="false" theme="simple">
												</s:select></td>
										</tr>
										<tr>
											<th>编码：</th>
											<td><input id="code" name="code" value="${measureUnitGroup.code }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>名称：</th>
											<td><input id="name" name="name" value="${measureUnitGroup.name }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>类别：</th>
											<td><select id="type" name="type">
													<option value="">请选择</option>
													<option value="1">无换算率</option>
													<option value="2">固定换算率</option>
													<option value="3">浮动换算率</option>
											</select></td>
											<th>状态：</th>
											<td><select id="status" name="status">
													<option value="">请选择</option>
													<option value="启用" selected="selected">启用</option>
													<option value="禁用">禁用</option>
											</select></td>
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
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />计量单位组明细</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;" class="right_content">
						<script type="text/javascript">
							function updateProductInformation(url) {
								$.ajax({
								url : url,
								cache : false,
								success : function(html) {
									asyncbox.open({
									title : '计量单位',
									modal : true,
									width : 750,
									height : 300,
									html : html,
									callback : function(action, returnValue) {
										if (action == 'ok') {
											if ($('#measureUnitGroupDetailForm').validationEngine('validate')) {
												$.post('${vix}/common/measureUnitGroupAction!saveOrUpdateMeasureUnitGroupDetail.action', {
												'id' : $("#id").val(),
												'measureUnit.id' : $("#measureUnitId").val(),
												'measureUnit.code' : $("#measureUnitCode").val(),
												'measureUnit.name' : $("#measureUnitName").val(),
												'measureUnit.englishCode' : $("#measureUnitEnglishCode").val(),
												'measureUnit.barCode' : $("#barCode").val(),
												'measureUnit.isMeasurementUnit' : $(":radio[name=isMeasurementUnit][checked]").val(),
												'measureUnit.rate' : $("#rate").val()
												}, function(result) {
													showMessage(result);
													setTimeout("", 1000);
													$('#measureUnitGroupDetail').datagrid("reload");
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
							$('#measureUnitGroupDetail').datagrid({
								url : '${vix}/common/measureUnitGroupAction!getMeasureUnitGroupDetailJson.action?id=${measureUnitGroup.id}',
								width : 'auto',
								height : 450,
								pagination : true,
								rownumbers : true,
								sortOrder : 'desc',
								striped : true,
								fitColumns : true,
								frozenColumns : [[{field : 'id',title : '编号',width : 60,hidden : true,align : 'center'}]],
								columns : [
								    [{field : 'code',title : '编码',width : 100,align : 'center'}, {field : 'name',title : '名称',width : 150,align : 'center'},
								     {field : 'isMeasurementUnit',title : '是否主计量单位',width : 100,align : 'center',required : 'true'}, {field : 'rate',title : '换算率',width : 100,align : 'center',required : 'true'}]
								],
								toolbar : [
									{id : 'da2Btnadd',text : '新增',iconCls : 'icon-add',handler : function() {
											$('#btnsave').linkbutton('enable');
											updateProductInformation('${vix}/common/measureUnitGroupAction!goSaveOrUpdateMeasureUnitGroupDetail.action');
										}
									}, '-', 
									{id : 'btnedit',text : '修改',iconCls : 'icon-edit',handler : function() {
											var row = $('#measureUnitGroupDetail').datagrid("getSelected"); //获取你选择的所有行
											if (row) {
												updateProductInformation('${vix}/common/measureUnitGroupAction!goSaveOrUpdateMeasureUnitGroupDetail.action?id=' + row.id);
											}
										}
									}, '-', 
									{text : '删除',iconCls : 'icon-remove',handler : function() {
										var rows = $('#measureUnitGroupDetail').datagrid("getSelections"); //获取你选择的所有行	
										//循环所选的行
										for (var i = 0; i < rows.length; i++) {
											var index = $('#measureUnitGroupDetail').datagrid('getRowIndex', rows[i]);//获取某行的行号
											$('#measureUnitGroupDetail').datagrid('deleteRow', index); //通过行号移除该行
											$.ajax({
												url : '${vix}/common/measureUnitGroupAction!deleteMeasureUnitGroupDetailById.action?id=' + rows[i].id,
												cache : false
											});
										}}
									}
								]
							});
						</script>
						<div style="padding: 8px;">
							<table id="measureUnitGroupDetail"></table>
						</div>
					</div>
				</div>
			</dl>
		</div>
	</form>
</div>