<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdateOrder() {
		var dlData = $("#dlAddress").datagrid("getRows");
		var dlJson = JSON.stringify(dlData);
		if ($('#order').validationEngine('validate')) {
			$.post('${vix}/dtbcenter/pickupBusinessAnalysisAction!saveOrUpdate.action', {
			'stockTaking.id' : $("#id").val(),
			'stockTaking.stocktakingcode' : $("#stocktakingcode").val(),
			'stockTaking.warehousecode' : $("#warehousecode").val(),
			'stockTaking.createTime' : $("#createTime").val(),
			'stockTaking.sttype' : $("#sttype").val(),
			'stockTaking.departmentCode' : $("#departmentCode").val(),
			'stockTaking.itemposition' : $("#itemposition").val(),
			'stockTaking.stbatch' : $("#stbatch").val(),
			'stockTaking.accountdate' : $("#accountdate").val(),
			'stockTaking.personcode' : $("#personcode").val(),
			'updateField' : updateField,
			'dlJson' : dlJson
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/dtbcenter/pickupBusinessAnalysisAction!goList.action');
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
				<li><a href="#"><img src="${vix}/common/img/pickup.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="ldm_dtbcenter_management" /> </a></li>
				<li><a href="#"><s:text name="ldm_pick_up_management" /> </a></li>
				<li><a href="#">提货业务分拆 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="stockTaking.id" value="${stockTaking.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /> </a> <a href="#" onclick="loadContent('${vix}/dtbcenter/pickupBusinessAnalysisAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>提货业务分拆 </b> </strong>
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
											<th>托单编号：</th>
											<td><input id="stocktakingcode" name="stocktakingcode" value="${stockTaking.stocktakingcode }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>委托日期：</th>
											<td><input id="createTime" name="createTime" value="${stockTaking.createTime}" type="text" class="validate[required] text-input" readonly="readonly" onchange="fieldChanged(this);" /> <img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th>主要运输方式：</th>
											<td><input id="warehousecode" name="warehousecode" value="${stockTaking.warehousecode}" class="validate[required] text-input" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<th>托运方：</th>
											<td><input id="departmentCode" name="departmentCode" value="${stockTaking.departmentCode}" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>紧急程度：</th>
											<td><select id="sttype" name="sttype" class="validate[required] text-input" onchange="fieldChanged(this);">
													<option value="">请选择</option>
											</select></td>
											<th>结算方式：</th>
											<td><select id="sttype" name="sttype" class="validate[required] text-input" onchange="fieldChanged(this);">
													<option value="">请选择</option>
											</select></td>
										</tr>
										<tr>
											<th>业务员：</th>
											<td><input id="stbatch" name="stbatch" value="${stockTaking.stbatch }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>作业要求：</th>
											<td><input id="personcode" name="personcode" value="${stockTaking.personcode }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>运输性质：</th>
											<td><input id="stbatch" name="stbatch" value="${stockTaking.stbatch }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>状态：</th>
											<td><input id="personcode" name="personcode" value="${stockTaking.personcode }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>输单人：</th>
											<td><input id="stbatch" name="stbatch" value="${stockTaking.stbatch }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>输单日期：</th>
											<td><input id="accountdate" name="accountdate" value="${stockTaking.accountdate}" type="text" readonly="readonly" onchange="fieldChanged(this);" /> <img onclick="showTime('accountdate','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
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
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />运输信息</a></li>
							<li><a onclick="javascript:$('#a2').attr('style','');tab(4,2,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />货物明细</a></li>
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
									modal : true,
									width : 724,
									height : 340,
									html : html,
									callback : function(action, returnValue) {
										if (action == 'ok') {
											if ($('#daForm').validationEngine('validate')) {
												$.post('${vix}/dtbcenter/pickupBusinessAnalysisAction!saveOrUpdateStockTakingItem.action', {
												'id' : $("#id").val(),
												'wimStockrecordlines.id' : $("#daId").val(),
												'wimStockrecordlines.itemcode' : $("#itemcode").val(),
												'wimStockrecordlines.itemname' : $("#itemname").val(),
												'wimStockrecordlines.itemtype' : $("#itemtype").val(),
												'wimStockrecordlines.specification' : $("#specification").val(),
												'wimStockrecordlines.quantity' : $("#quantity").val(),
												'wimStockrecordlines.unitcost' : $("#unitcost").val(),
												'wimStockrecordlines.price' : $("#price").val()
												}, function(result) {
													showMessage(result);
													setTimeout("", 1000);
													$('#dlAddress').datagrid("reload");
												});
											}
										}
									},
									btnsbar : $.btn.OKCANCEL
									});
								}
								});
							}
							$('#dlAddress')
									.datagrid({
									url : '${vix}/dtbcenter/pickupBusinessAnalysisAction!getStockTakingItemJson.action?id=${wimTransvouch.id}',
									title : '运输信息',
									width : 'auto',
									height : 450,
									fitColumns : false,
									columns : [ [ {
									field : 'id',
									title : '编号',
									width : 100,
									align : 'center'
									}, {
									field : 'itemcode',
									title : '发货方',
									width : 100,
									align : 'center'
									}, {
									field : 'itemname',
									title : '发货站点',
									width : 100,
									align : 'center'
									}, {
									field : 'itemtype',
									title : '要求装货时间',
									width : 100,
									align : 'center'
									}, {
									field : 'specification',
									title : '发货城区',
									width : 100,
									align : 'center'
									}, {
									field : 'quantity',
									title : '联系人',
									width : 100,
									align : 'right'
									}, {
									field : 'unitcost',
									title : '电话',
									width : 100,
									align : 'right'
									}, {
									field : 'price',
									title : '收货方',
									width : 100,
									align : 'right'
									}, {
									field : 'price',
									title : '收货站点',
									width : 100,
									align : 'right'
									}, {
									field : 'price',
									title : '要求到货时间',
									width : 100,
									align : 'right'
									}, {
									field : 'price',
									title : '收货地区',
									width : 100,
									align : 'right'
									}, {
									field : 'price',
									title : '联系人',
									width : 100,
									align : 'right'
									}, {
									field : 'price',
									title : '电话',
									width : 100,
									align : 'right'
									}, {
									field : 'price',
									title : '城际间距离',
									width : 100,
									align : 'right'
									}, {
									field : 'price',
									title : '备注',
									width : 100,
									align : 'right'
									} ] ],
									toolbar : [ {
									id : 'da2Btnadd',
									text : '新增',
									iconCls : 'icon-add',
									handler : function() {
										$('#btnsave').linkbutton('enable');
										saveDeliveryAddress('${vix}/dtbcenter/pickupBusinessAnalysisAction!goSaveOrUpdateStockTakingItem.action?id=0');
									}
									}, '-', {
									id : 'btnedit',
									text : '修改',
									iconCls : 'icon-edit',
									handler : function() {
										var row = $('#dlAddress').datagrid("getSelected"); //获取你选择的所有行
										if (row) {
											saveDeliveryAddress('${vix}/dtbcenter/pickupBusinessAnalysisAction!goSaveOrUpdateStockTakingItem.action?id=' + row.id);
										}
									}
									}, '-', {
									text : '删除',
									iconCls : 'icon-remove',
									handler : function() {
										var rows = $('#dlAddress').datagrid("getSelections"); //获取你选择的所有行	
										//循环所选的行
										for (var i = 0; i < rows.length; i++) {
											var index = $('#dlAddress').datagrid('getRowIndex', rows[i]);//获取某行的行号
											$('#dlAddress').datagrid('deleteRow', index); //通过行号移除该行
											$.ajax({
											url : '${vix}/dtbcenter/pickupBusinessAnalysisAction!deleteStockTakingItemById.action?id=' + rows[i].id,
											cache : false
											});
										}
									}
									} ]
									});
						</script>
						<div style="padding: 8px;">
							<table id="dlAddress"></table>
						</div>
					</div>
					<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<script type="text/javascript">
							function saveDeliveryAddress(url) {
								$.ajax({
								url : url,
								cache : false,
								success : function(html) {
									asyncbox.open({
									modal : true,
									width : 724,
									height : 340,
									html : html,
									callback : function(action, returnValue) {
										if (action == 'ok') {
											if ($('#daForm').validationEngine('validate')) {
												$.post('${vix}/dtbcenter/pickupBusinessAnalysisAction!saveOrUpdateStockTakingItem.action', {
												'id' : $("#id").val(),
												'wimStockrecordlines.id' : $("#daId").val(),
												'wimStockrecordlines.itemcode' : $("#itemcode").val(),
												'wimStockrecordlines.itemname' : $("#itemname").val(),
												'wimStockrecordlines.itemtype' : $("#itemtype").val(),
												'wimStockrecordlines.specification' : $("#specification").val(),
												'wimStockrecordlines.quantity' : $("#quantity").val(),
												'wimStockrecordlines.unitcost' : $("#unitcost").val(),
												'wimStockrecordlines.price' : $("#price").val()
												}, function(result) {
													showMessage(result);
													setTimeout("", 1000);
													$('#dlAddress2').datagrid("reload");
												});
											}
										}
									},
									btnsbar : $.btn.OKCANCEL
									});
								}
								});
							}
							$('#dlAddress2')
									.datagrid({
									url : '${vix}/dtbcenter/pickupBusinessAnalysisAction!getStockTakingItemJson.action?id=${wimTransvouch.id}',
									title : '货物明细',
									width : 'auto',
									height : 450,
									fitColumns : true,
									columns : [ [ {
									field : 'id',
									title : '编号',
									width : 100,
									align : 'center'
									}, {
									field : 'itemcode',
									title : '品名',
									width : 200,
									align : 'center'
									}, {
									field : 'itemname',
									title : '规格',
									width : 200,
									align : 'center'
									}, {
									field : 'itemtype',
									title : '数量',
									width : 200,
									align : 'center'
									}, {
									field : 'specification',
									title : '包装单位',
									width : 200,
									align : 'center'
									}, {
									field : 'quantity',
									title : '重量',
									width : 150,
									align : 'right'
									}, {
									field : 'unitcost',
									title : '净重',
									width : 150,
									align : 'right'
									}, {
									field : 'price',
									title : '体积',
									width : 150,
									align : 'right'
									} ] ],
									toolbar : [ {
									id : 'da2Btnadd',
									text : '新增',
									iconCls : 'icon-add',
									handler : function() {
										$('#btnsave').linkbutton('enable');
										saveDeliveryAddress('${vix}/dtbcenter/pickupBusinessAnalysisAction!goSaveOrUpdateStockTakingItem.action?id=0');
									}
									}, '-', {
									id : 'btnedit',
									text : '修改',
									iconCls : 'icon-edit',
									handler : function() {
										var row = $('#dlAddress2').datagrid("getSelected"); //获取你选择的所有行
										if (row) {
											saveDeliveryAddress('${vix}/dtbcenter/pickupBusinessAnalysisAction!goSaveOrUpdateStockTakingItem.action?id=' + row.id);
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
											url : '${vix}/dtbcenter/pickupBusinessAnalysisAction!deleteStockTakingItemById.action?id=' + rows[i].id,
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
		<!--submenu-->
	</form>
</div>