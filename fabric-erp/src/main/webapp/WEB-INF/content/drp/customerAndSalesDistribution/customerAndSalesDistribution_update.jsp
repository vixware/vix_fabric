<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	function saveOrUpdateChannelDistributor() {
		if ($('#channelDistributorForm').validationEngine('validate')) {
			$.post('${vix}/drp/customerAndSalesDistributionAction!saveOrUpdate.action', {
			'channelDistributor.id' : $("#id").val(),
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
			'channelDistributor.accessory' : $("#accessory").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/customerAndSalesDistributionAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#channelDistributorForm").validationEngine();
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
				<li><a href="#">客户与销售分布</a></li>
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
						onclick="loadContent('${vix}/drp/customerAndSalesDistributionAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>客户与销售分布</b></strong>
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
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />客户</a></li>
							<li><a onclick="javascript:$('#a2').attr('style','');tab(2,2,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />销售分布</a></li>
						</ul>
					</div>
					<div id="a1" class="right_content" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
							function saveOrUpdateCustomer(url) {
								$.ajax({
								url : url,
								cache : false,
								success : function(html) {
									asyncbox.open({
									title : '客户',
									modal : true,
									width : 724,
									height : 400,
									html : html,
									callback : function(action, returnValue) {
										if (action == 'ok') {
											if ($('#customerForm').validationEngine('validate')) {
												$.post('${vix}/drp/customerAndSalesDistributionAction!saveOrUpdateCustomer.action', {
												'id' : $("#id").val(),
												'customer.id' : $("#customerId").val(),
												'customer.code' : $("#customerCode").val(),
												'customer.name' : $("#customerName").val(),
												'customer.type' : $("#type").val(),
												'customer.phoneFax' : $("#phoneFax").val(),
												'customer.pointValue' : $("#pointValue").val()
												}, function(result) {
													showMessage(result);
													setTimeout("", 1000);
													$('#customer').datagrid("reload");
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
							$('#customer').datagrid({
							url : '${vix}/drp/customerAndSalesDistributionAction!getCustomerJson.action?id=${channelDistributor.id}',
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
							field : 'code',
							title : '编码',
							width : 150,
							align : 'center'
							}, {
							field : 'name',
							title : '名称',
							width : 150,
							align : 'center'
							}, {
							field : 'type',
							title : '客户类型',
							width : 100,
							align : 'center',
							required : 'true'
							}, {
							field : 'phoneFax',
							title : '电话传真',
							width : 100,
							align : 'center',
							required : 'true'
							}, {
							field : 'pointValue',
							title : '积分值',
							width : 100,
							align : 'center',
							editor : 'numberbox',
							required : 'true'
							} ] ],
							toolbar : [ {
							id : 'da2Btnadd',
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								saveOrUpdateCustomer('${vix}/drp/customerAndSalesDistributionAction!goSaveOrUpdateCustomer.action');
							}
							}, '-', {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#customer').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveOrUpdateCustomer('${vix}/drp/customerAndSalesDistributionAction!goSaveOrUpdateCustomer.action?id=' + row.id);
								}
							}
							}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								var rows = $('#customer').datagrid("getSelections"); //获取你选择的所有行	
								//循环所选的行
								for (var i = 0; i < rows.length; i++) {
									var index = $('#customer').datagrid('getRowIndex', rows[i]);//获取某行的行号
									$('#customer').datagrid('deleteRow', index); //通过行号移除该行
									$.ajax({
									url : '${vix}/drp/customerAndSalesDistributionAction!deleteCustomerById.action?id=' + rows[i].id,
									cache : false
									});
								}
							}
							} ]
							});
						</script>
						<div style="padding: 8px;">
							<table id="customer"></table>
						</div>
					</div>
					<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<script type="text/javascript">
							function saveOrUpdateSalesDistribution(url) {
								$.ajax({
								url : url,
								cache : false,
								success : function(html) {
									asyncbox.open({
									title : '销售分布',
									modal : true,
									width : 724,
									height : 400,
									html : html,
									callback : function(action, returnValue) {
										if (action == 'ok') {
											if ($('#salesDistributionForm').validationEngine('validate')) {
												$.post('${vix}/drp/customerAndSalesDistributionAction!saveOrUpdateSalesDistribution.action', {
												'id' : $("#id").val(),
												'salesDistribution.id' : $("#salesDistributionId").val(),
												'salesDistribution.code' : $("#salesDistributionCode").val(),
												'salesDistribution.name' : $("#salesDistributionName").val(),
												'salesDistribution.salesManager' : $("#salesManager").val(),
												'salesDistribution.officeStaff' : $("#officeStaff").val(),
												'salesDistribution.areaCoverage' : $("#areaCoverage").val()
												}, function(result) {
													showMessage(result);
													setTimeout("", 1000);
													$('#salesDistribution').datagrid("reload");
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
							$('#salesDistribution').datagrid({
							url : '${vix}/drp/customerAndSalesDistributionAction!getSalesDistributionJson.action?id=${channelDistributor.id}',
							width : "100%",
							height : 450,
							nowrap : true,
							autoRowHeight : false,
							striped : true,
							collapsible : true,
							sortName : 'id',
							sortOrder : 'desc',
							remoteSort : true,
							pagination : true,
							rownumbers : true,
							showFooter : true,
							idField : 'id',
							columns : [ [ {
							field : 'code',
							title : '区域编码',
							width : 100,
							align : 'center'
							}, {
							field : 'name',
							title : '区域名称',
							width : 150,
							align : 'center'
							}, {
							field : 'salesManager',
							title : '销售经理',
							width : 100,
							align : 'center'
							}, {
							field : 'officeStaff',
							title : '内勤人员',
							width : 100,
							align : 'center'
							}, {
							field : 'areaCoverage',
							title : '区域范围',
							width : 100,
							align : 'center'
							} ] ],
							toolbar : [ {
							id : 'da2Btnadd',
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								saveOrUpdateSalesDistribution('${vix}/drp/customerAndSalesDistributionAction!goSaveOrUpdateSalesDistribution.action');
							}
							}, '-', {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#salesDistribution').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveOrUpdateSalesDistribution('${vix}/drp/customerAndSalesDistributionAction!goSaveOrUpdateSalesDistribution.action?id=' + row.id);
								}
							}
							}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								var rows = $('#salesDistribution').datagrid("getSelections"); //获取你选择的所有行	
								//循环所选的行
								for (var i = 0; i < rows.length; i++) {
									var index = $('#salesDistribution').datagrid('getRowIndex', rows[i]);//获取某行的行号
									$('#salesDistribution').datagrid('deleteRow', index); //通过行号移除该行
									$.ajax({
									url : '${vix}/drp/customerAndSalesDistributionAction!deleteSalesDistributionById.action?id=' + rows[i].id,
									cache : false
									});
								}
							}
							} ]
							});
						</script>
						<div style="padding: 8px;">
							<table id="salesDistribution"></table>
						</div>
					</div>
				</div>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>