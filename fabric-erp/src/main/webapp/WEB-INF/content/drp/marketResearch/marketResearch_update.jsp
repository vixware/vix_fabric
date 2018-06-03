<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function saveOrUpdateChannelDistributor() {
		if ($('#channelDistributorForm').validationEngine('validate')) {
			$.post('${vix}/drp/marketResearchAction!saveOrUpdate.action', {
			'channelDistributor.id' : $("#id").val(),
			'organizationId' : $("#parentId").val(),
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
				loadContent('${vix}/drp/marketResearchAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#channelDistributorForm").validationEngine();
	function chooseParentOrganization() {
		$.ajax({
		url : '${vix}/drp/marketResearchAction!goChooseOrganization.action',
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
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_channel.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#"><s:text name="drp_market_research_management" /> </a></li>
				<li><a href="#">竞争信息管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/marketResearchAction!goList.action');">市场活动</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="channelDistributor.id" value="${channelDistributor.id}" />
<div class="content">
	<form id="channelDistributorForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateChannelDistributor()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/drp/marketResearchAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>新增 </b></strong>
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
											<th>编码：</th>
											<td><input id="code" name="code" value="${competingProducts.code }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>主题：</th>
											<td><input id="name" name="name" value="${competingProducts.name}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>竞争者名称：</th>
											<td><input id="gernalName" name="gernalName" value="${competingProducts.gernalName}" type="text" size="30" /></td>
											<th>市场活动类型：</th>
											<td><select id="itemType" name="itemType" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="类型1" selected="selected">类型1</option>
													<option value="类型2">类型2</option>
											</select><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>活动开始日期：</th>
											<td><input id="specification" name="specification" value="${competingProducts.specification}" type="text" size="30" /></td>
											<th>活动结束日期：</th>
											<td><input id="price" name="price" value="${competingProducts.price}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>促销产品名称：</th>
											<td><input id="eanupc" name="eanupc" value="${competingProducts.eanupc }" type="text" size="30"></td>
											<th>推广的新产品名称：</th>
											<td><input id="xcode" name="xcode" value="${competingProducts.xcode}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>参与的媒体种类：</th>
											<td><select id="itemType" name="itemType" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="类型1" selected="selected">类型1</option>
													<option value="类型2">类型2</option>
											</select><span style="color: red;">*</span></td>
											<th>参与的媒体数量：</th>
											<td><input id="industry" name="industry" value="${competingProducts.industry}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>主要传播媒体名称或频道：</th>
											<td><input id="industry" name="industry" value="${competingProducts.industry}" type="text" size="30" /></td>
											<th>辅助传播媒体1名称：</th>
											<td><input id="masterUnit" name="masterUnit" value="${competingProducts.masterUnit}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>辅助传播媒体2名称：</th>
											<td><input id="industry" name="industry" value="${competingProducts.industry}" type="text" size="30" /></td>
											<th>辅助传播媒体3名称：</th>
											<td><input id="masterUnit" name="masterUnit" value="${competingProducts.masterUnit}" type="text" size="30" /></td>
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
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />活动明细</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
							function saveDeliveryAddress(url) {
								$.ajax({
								url : url,
								cache : false,
								success : function(html) {
									asyncbox.open({
									title : '活动明细',
									modal : true,
									width : 724,
									height : 400,
									html : html,
									callback : function(action, returnValue) {
										if (action == 'ok') {
											if ($('#orderItemForm').validationEngine('validate')) {
												$.post('${vix}/inventory/inboundWarehouseAction!saveOrUpdateWimStockRecordLines.action', {
												'id' : $("#id").val(),
												'wimStockrecordlines.id' : $("#oiId").val(),
												'wimStockrecordlines.itemcode' : $("#itemcode").val(),
												'wimStockrecordlines.itemname' : $("#itemname").val(),
												'wimStockrecordlines.specification' : $("#specification").val(),
												'wimStockrecordlines.unitcost' : $("#unitcost").val(),
												'wimStockrecordlines.quantity' : $("#quantity").val(),
												'wimStockrecordlines.suppliercode' : $("#suppliercode").val(),
												'wimStockrecordlines.purchaseorderitemcode' : $("#purchaseorderitemcode").val(),
												'wimStockrecordlines.unit' : $("#unit").val(),
												'wimStockrecordlines.producedate' : $("#producedate").val(),
												'wimStockrecordlines.massdate' : $("#massdate").val(),
												'wimStockrecordlines.price' : $("#price").val()
												}, function(result) {
													showMessage(result);
													setTimeout("", 1000);
													$('#dlAddress2').datagrid("reload");
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
							$('#dlAddress2').datagrid({
							url : '${vix}/inventory/inboundWarehouseAction!getOrderItemJson.action?id=${wimStockrecords.id}',
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
							}, {
							field : 'itemname',
							title : '所在区域',
							width : 100,
							align : 'center'
							}, {
							field : 'itemcode',
							title : '统计时间区间',
							width : 100,
							align : 'center'
							} ] ],
							columns : [ [ {
							field : 'unitcost',
							title : '竞争者名称',
							width : 100,
							align : 'right',
							required : 'true'
							}, {
							field : 'unitcost',
							title : '电视投放次数',
							width : 100,
							align : 'right',
							required : 'true'
							}, {
							field : 'unitcost',
							title : '电视累计投放时间',
							width : 100,
							align : 'right',
							required : 'true'
							}, {
							field : 'unitcost',
							title : '电台投放次数',
							width : 100,
							align : 'right',
							required : 'true'
							}, {
							field : 'price',
							title : '电台累计投放时间',
							width : 100,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'massdate',
							title : '报纸投放次数',
							width : 100,
							align : 'center'
							}, {
							field : 'massdate',
							title : '报纸投放版面',
							width : 100,
							align : 'center'
							}, {
							field : 'massdate',
							title : '促销行动次数',
							width : 100,
							align : 'center'
							}, {
							field : 'massdate',
							title : '促销行动周期',
							width : 100,
							align : 'center'
							} ] ],
							toolbar : [ {
							id : 'da2Btnadd',
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								saveDeliveryAddress('${vix}/inventory/inboundWarehouseAction!goAddSaleOrderItem.action?=${wimStockrecords.id}');
							}
							}, '-', {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#dlAddress2').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveDeliveryAddress('${vix}/inventory/inboundWarehouseAction!getOrderItemJson.action?id=' + row.id);
								}
							}
							}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								var rows = $('#dlAddress2').datagrid("getSelections"); //获取你选择的所有行	
								//循环所选的行
								for (var i = 0; i < rows.length; i++) {
									var index = $('#dlAddress2').datagrid('getRowIndex', rows[i]);//获取某行的行号
									$('#dlAddress2').datagrid('deleteRow', index); //通过行号移除该行
									$.ajax({
									url : '${vix}/inventory/inboundWarehouseAction!deleteWimStockrecordlinesById.action?id=' + rows[i].id,
									cache : false
									});
								}
							}
							} ]
							});
						</script>
						<div style="padding: 8px;">
							<table id="dlAddress2"></table>
						</div>
					</div>
				</div>
			</dl>
		</div>
	</form>
</div>