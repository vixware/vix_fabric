<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	function saveOrUpdateChannelDistributor() {
		if ($('#channelDistributorForm').validationEngine('validate')) {
			$.post('${vix}/drp/branchOrganizationAction!saveOrUpdate.action', {
			'channelDistributor.id' : $("#id").val(),
			'channelDistributorId' : $("#parentId").val(),
			'channelDistributor.code' : $("#code").val(),
			'channelDistributor.name' : $("#name").val(),
			'channelDistributor.shortName' : $("#shortName").val(),
			'channelDistributor.status' : $("#status").val(),
			'channelDistributor.telephone' : $("#telephone").val(),
			'channelDistributor.industry' : $("#industry").val(),
			'channelDistributor.employeeCounts' : $("#employeeCounts").val(),
			'channelDistributor.email' : $("#email").val(),
			'channelDistributor.catalog' : $("#catalog").val(),
			'channelDistributor.level' : $("#level").val(),
			'channelDistributor.artificialPerson' : $("#artificialPerson").val(),
			'channelDistributor.registeredCapital' : $("#registeredCapital").val(),
			'channelDistributor.currency' : $("#currency").val(),
			'channelDistributor.openingBank' : $("#openingBank").val(),
			'channelDistributor.bankAccount' : $("#bankAccount").val(),
			'channelDistributor.taxCode' : $("#taxCode").val(),
			'channelDistributor.region' : $("#region").val(),
			'channelDistributor.address' : $("#address").val(),
			'channelDistributor.permit' : $("#permit").val(),
			'channelDistributor.maplink' : $("#maplink").val(),
			'channelDistributor.picture' : $("#picture").val(),
			'channelDistributor.accessory' : $("#accessory").val(),
			'channelDistributor.memo' : memo.html()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/competitionChannelAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#channelDistributorForm").validationEngine();
	function chooseParentOrganization() {
		$.ajax({
		url : '${vix}/drp/channelAction!goChooseOrganization.action',
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
							$("#parentId").val(result[0]);
							$("#organizationName").val(result[1]);
						}
					} else {
						$("#parentId").val("");
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
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/sys_precisionControl.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#">市场管理</a></li>
				<li><a href="#">市场调查</a></li>
				<li><a href="#">数据采集</a></li>
				<li><a href="#">分支机构管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${channelDistributor.id }" />
<div class="content">
	<form id="channelDistributorForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateChannelDistributor()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/drp/branchOrganizationAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>分支机构 </b></strong>
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
											<td width="35%"><input id="code" name="code" value="${channelDistributor.code }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td width="15%" align="right">名称：</td>
											<td width="35%"><input id="name" name="name" value="${channelDistributor.name }" type="text" size="30" class="validate[required] text-input"><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">简称：</td>
											<td><input id="shortName" name="shortName" value="${channelDistributor.shortName }" type="text" size="30" /></td>
											<td align="right">法人：</td>
											<td><input id="artificialPerson" name="artificialPerson" value="${channelDistributor.artificialPerson }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">电话：</td>
											<td><input id="telephone" name="telephone" value="${channelDistributor.telephone }" type="text" size="30" /></td>
											<td align="right">邮箱：</td>
											<td><input id="email" name="email" value="${channelDistributor.email }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">营业执照：</td>
											<td><input id="permit" name="permit" value="${channelDistributor.permit }" type="text" size="30" /></td>
											<td align="right">税号：</td>
											<td><input id="taxCode" name="taxCode" value="${channelDistributor.taxCode }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">所属地区：</td>
											<td><input id="region" name="region" value="${channelDistributor.region }" type="text" size="30" /></td>
											<td align="right">地址：</td>
											<td><input id="address" name="address" value="${channelDistributor.address }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">备注：</td>
											<td colspan="3"><textarea id="memo" name="memo">${channelDistributor.memo }</textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
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
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b><strong>其他信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td width="15%" align="right">开户银行：</td>
											<td width="35%"><input id="openingBank" name="openingBank" value="${channelDistributor.openingBank }" type="text" size="30" /></td>
											<td width="15%" align="right">银行帐号：</td>
											<td width="35%"><input id="bankAccount" name="bankAccount" value="${channelDistributor.bankAccount }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">所属行业：</td>
											<td><select id="industry" name="industry">
													<option value="">请选择</option>
											</select></td>
											<td align="right">员工人数：</td>
											<td><input id="employeeCounts" name="employeeCounts" value="${channelDistributor.employeeCounts }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">类型：</td>
											<td><select id="type" name="type" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">渠道</option>
													<option value="2">经销商</option>
													<option value="3">代理销售商</option>
													<option value="4">门店</option>
											</select><span style="color: red;">*</span></td>
											<td align="right">所属公司：</td>
											<td><input type="text" id="organizationName" name="organizationName" readonly="readonly" value="${channelDistributor.organization.orgName}" class="validate[required] text-input" size="30" /><input class="btn" type="button" value="选择" onclick="chooseParentOrganization();" /> <span style="color: red;">*</span> <input type="hidden"
												id="organizationId" name="organizationId" value="${channelDistributor.organization.id}" />
										</tr>
										<tr>
											<td align="right">等级：</td>
											<td><select id="level" name="level">
													<option value="">请选择</option>
											</select></td>
											<td align="right">上级代理：</td>
											<td><input id="channelDistributorName" name="channelDistributorName" value="${channelDistributor.parentChannelDistributor.name }" type="text" size="30" readonly="readonly" /><input type="hidden" id="channelDistributorId" name="channelDistributorId" value="${channelDistributor.parentChannelDistributor.id}" /><input class="btn"
												type="button" value="选择" onclick="chooseParentChannelDistributor();" /></td>
										</tr>
										<tr>
											<td align="right">地图链接：</td>
											<td><input id="maplink" name="maplink" value="${channelDistributor.maplink }" type="text" size="30" /></td>
											<td align="right">状态：</td>
											<td><select id="status" name="status">
													<option value="">请选择</option>
													<option value="0">营业</option>
													<option value="1">暂停营业</option>
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
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />经营状况</a></li>
							<li><a onclick="javascript:$('#a2').attr('style','');tab(2,2,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />性质规模</a></li>
						</ul>
					</div>
					<div id="a1" class="right_content" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
							function saveOrUpdateOperatingConditions(url) {
								$.ajax({
								url : url,
								cache : false,
								success : function(html) {
									asyncbox.open({
									title : '经营状况',
									modal : true,
									width : 724,
									height : 400,
									html : html,
									callback : function(action, returnValue) {
										if (action == 'ok') {
											if ($('#operatingConditionsForm').validationEngine('validate')) {
												$.post('${vix}/drp/branchOrganizationAction!saveOrUpdateOperatingConditions.action', {
												'id' : $("#id").val(),
												'operatingConditions.id' : $("#operatingConditionsId").val(),
												'operatingConditions.customerNumbers' : $("#customerNumbers").val(),
												'operatingConditions.warehouseScale' : $("#warehouseScale").val(),
												'operatingConditions.distributionMode' : $("#distributionMode").val(),
												'operatingConditions.vehicleNumber' : $("#vehicleNumber").val(),
												'operatingConditions.salesAmount' : $("#salesAmount").val(),
												'operatingConditions.salesProfit' : $("#salesProfit").val(),
												'operatingConditions.year' : $("#year").val()
												}, function(result) {
													showMessage(result);
													setTimeout("", 1000);
													$('#operatingConditions').datagrid("reload");
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
							$('#operatingConditions').datagrid({
							url : '${vix}/drp/branchOrganizationAction!getOperatingConditionsJson.action?id=${channelDistributor.id}',
							width : 'auto',
							height : 450,
							pagination : true,
							rownumbers : true,
							sortOrder : 'desc',
							striped : true,
							frozenColumns : [ [ {
							field : 'id',
							title : '编号',
							width : 60,
							hidden : true,
							align : 'center'
							} ] ],
							columns : [ [ {
							field : 'customerNumbers',
							title : '客户数量',
							width : 150,
							editor : 'numberbox',
							align : 'center'
							}, {
							field : 'warehouseScale',
							title : '仓库规模',
							width : 150,
							align : 'center'
							}, {
							field : 'distributionMode',
							title : '配送解决模式',
							width : 100,
							align : 'center',
							required : 'true'
							}, {
							field : 'vehicleNumber',
							title : '车辆',
							width : 100,
							align : 'center',
							required : 'true'
							}, {
							field : 'year',
							title : '年度',
							width : 100,
							align : 'center',
							required : 'true'
							}, {
							field : 'salesAmount',
							title : '销售额',
							width : 100,
							align : 'center',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'salesProfit',
							title : '利润',
							width : 100,
							editor : 'numberbox',
							align : 'center'
							} ] ],
							toolbar : [ {
							id : 'da2Btnadd',
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								saveOrUpdateOperatingConditions('${vix}/drp/branchOrganizationAction!goSaveOrUpdateOperatingConditions.action');
							}
							}, '-', {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#operatingConditions').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveOrUpdateOperatingConditions('${vix}/drp/branchOrganizationAction!goSaveOrUpdateOperatingConditions.action?id=' + row.id);
								}
							}
							}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								var rows = $('#operatingConditions').datagrid("getSelections"); //获取你选择的所有行	
								//循环所选的行
								for (var i = 0; i < rows.length; i++) {
									var index = $('#operatingConditions').datagrid('getRowIndex', rows[i]);//获取某行的行号
									$('#operatingConditions').datagrid('deleteRow', index); //通过行号移除该行
									$.ajax({
									url : '${vix}/drp/branchOrganizationAction!deleteOperatingConditionsById.action?id=' + rows[i].id,
									cache : false
									});
								}
							}
							} ]
							});
						</script>
						<div style="padding: 8px;">
							<table id="operatingConditions"></table>
						</div>
					</div>
					<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<script type="text/javascript">
							function saveOrUpdatePropertiesScale(url) {
								$.ajax({
								url : url,
								cache : false,
								success : function(html) {
									asyncbox.open({
									title : '性质规模',
									modal : true,
									width : 724,
									height : 400,
									html : html,
									callback : function(action, returnValue) {
										if (action == 'ok') {
											if ($('#propertiesScaleForm').validationEngine('validate')) {
												$.post('${vix}/drp/branchOrganizationAction!saveOrUpdatePropertiesScale.action', {
												'id' : $("#id").val(),
												'propertiesScale.id' : $("#propertiesScaleId").val(),
												'propertiesScale.allProperties' : $("#allProperties").val(),
												'propertiesScale.bmic' : $("#bmic").val(),
												'propertiesScale.workerScale' : $("#workerScale").val(),
												'propertiesScale.salesmanScale' : $("#salesmanScale").val()
												}, function(result) {
													showMessage(result);
													setTimeout("", 1000);
													$('#propertiesScale').datagrid("reload");
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
							$('#propertiesScale').datagrid({
							url : '${vix}/drp/branchOrganizationAction!getPropertiesScaleJson.action?id=${channelDistributor.id}',
							width : "100%",
							height : 450,
							nowrap : true,
							autoRowHeight : false,
							striped : true,
							collapsible : true,
							sortName : 'id',
							sortOrder : 'desc',
							remoteSort : true,
							fitColumns : true,
							pagination : true,
							rownumbers : true,
							showFooter : true,
							idField : 'id',
							columns : [ [ {
							field : 'allProperties',
							title : '所有性质',
							width : 100,
							align : 'center'
							}, {
							field : 'bmic',
							title : '业务管理信息化状况',
							width : 150,
							align : 'center'
							}, {
							field : 'workerScale',
							title : '人员规模',
							width : 100,
							align : 'center'
							}, {
							field : 'salesmanScale',
							title : '销售人员规模',
							width : 100,
							align : 'center'
							} ] ],
							toolbar : [ {
							id : 'da2Btnadd',
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								saveOrUpdatePropertiesScale('${vix}/drp/branchOrganizationAction!goSaveOrUpdatePropertiesScale.action');
							}
							}, '-', {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#propertiesScale').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveOrUpdatePropertiesScale('${vix}/drp/branchOrganizationAction!goSaveOrUpdatePropertiesScale.action?id=' + row.id);
								}
							}
							}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								var rows = $('#propertiesScale').datagrid("getSelections"); //获取你选择的所有行	
								//循环所选的行
								for (var i = 0; i < rows.length; i++) {
									var index = $('#propertiesScale').datagrid('getRowIndex', rows[i]);//获取某行的行号
									$('#propertiesScale').datagrid('deleteRow', index); //通过行号移除该行
									$.ajax({
									url : '${vix}/drp/branchOrganizationAction!deletePropertiesScaleById.action?id=' + rows[i].id,
									cache : false
									});
								}
							}
							} ]
							});
						</script>
						<div style="padding: 8px;">
							<table id="propertiesScale"></table>
						</div>
					</div>
				</div>
			</dl>
		</div>
	</form>
</div>