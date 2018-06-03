<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}

	function saveOrUpdateTakeDelivery() {
		if ($('#order').validationEngine('validate')) {
			$.post('${vix}/dtbcenter/pickupBusinessHandlingAction!saveOrUpdate.action', {
			'loadBill.id' : $("#id").val(),
			'loadBill.loadBillCode' : $("#loadBillCode").val(),
			'loadBill.delegateDate' : $("#delegateDate").val(),
			'loadBill.mainTransferType' : $("#mainTransferType").val(),
			'loadBill.shipper' : $("#shipper").val(),
			'loadBill.settlingAccountsMethod' : $("#settlingAccountsMethod").val(),
			'loadBill.emergency' : $("#emergency").val(),
			'loadBill.counterman' : $("#counterman").val(),
			'loadBill.operateRequirement' : $("#operateRequirement").val(),
			'loadBill.transferProperty' : $("#transferProperty").val(),
			'loadBill.status' : $("#status").val(),
			'loadBill.creator' : $("#creator").val(),
			'updateField' : updateField,
			'loadBill.createTime' : $("#createTime").val(),
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/dtbcenter/pickupBusinessHandlingAction!goList.action');
			});
		} else {
			return false;
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
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/pickupBusinessHandlingAction!goList.action?pageNo=${pageNo}');">提货业务受理 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${loadBill.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateTakeDelivery()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /> </a> <a href="#" onclick="loadContent('${vix}/dtbcenter/pickupBusinessHandlingAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>提货单 </b> </strong>
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
											<td><input id="loadBillCode" name="loadBillCode" value="${loadBill.loadBillCode }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>委托日期：</th>
											<td><input id="delegateDate" name="delegateDate" value="${loadBill.delegateDate}" type="text" class="validate[required] text-input" readonly="readonly" onchange="fieldChanged(this);" /> <img onclick="showTime('delegateDate','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span
												style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>主要运输方式：</th>
											<td><select id="mainTransferType" name="mainTransferType" class="validate[required] text-input" style="width: 225px;" onchange="fieldChanged(this);">
													<option value="">请选择</option>
											</select><span style="color: red;">*</span></td>
											<th>托运方：</th>
											<td><input id="shipper" name="shipper" value="${loadBill.shipper}" class="validate[required] text-input" type="text" size="30" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>结算方式：</th>
											<td><select id="settlingAccountsMethod" name="settlingAccountsMethod" class="validate[required] text-input" style="width: 225px;" onchange="fieldChanged(this);">
													<option value="">请选择</option>
											</select><span style="color: red;">*</span></td>
											<th>紧急程度：</th>
											<td><select id="emergency" name="emergency" style="width: 225px;" onchange="fieldChanged(this);">
													<option value="">请选择</option>
											</select></td>
										</tr>
										<tr>
											<th>业务员：</th>
											<td><input id="counterman" name="counterman" value="${loadBill.counterman }" type="text" size="30" onchange="fieldChanged(this);"></td>
											<th>作业要求：</th>
											<td><input id="operateRequirement" name="operateRequirement" value="${loadBill.operateRequirement }" type="text" size="30" onchange="fieldChanged(this);"></td>
										</tr>
										<tr>
											<th>运输性质：</th>
											<td><input id="transferProperty" name="transferProperty" value="${loadBill.transferProperty }" type="text" size="30" onchange="fieldChanged(this);"></td>
											<th>状态：</th>
											<td><select id="status" name="status" style="width: 225px;" onchange="fieldChanged(this);">
													<option value="">请选择</option>
											</select></td>
										</tr>
										<tr>
											<th>制单人：</th>
											<td><input id="creator" name="creator" value="${loadBill.creator }" type="text" size="30" onchange="fieldChanged(this);"></td>
											<th>制单日期：</th>
											<td><input id="createTime" name="createTime" value="${loadBill.createTime}" type="text" readonly="readonly" onchange="fieldChanged(this);" /> <img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
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
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />提货单明细</a></li>
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
									title : '提货单明细',
									modal : true,
									width : 724,
									height : 400,
									html : html,
									callback : function(action, returnValue) {
										if (action == 'ok') {
											if ($('#orderItemForm').validationEngine('validate')) {
												$.post('${vix}/dtbcenter/pickupBusinessHandlingAction!saveOrUpdateLoadBillItem.action', {
												'id' : $("#id").val(),
												'loadBillItem.id' : $("#oiId").val(),
												'loadBillItem.itemCode' : $("#itemCode").val(),
												'loadBillItem.itemName' : $("#itemName").val(),
												'loadBillItem.unit' : $("#unit").val(),
												'loadBillItem.price' : $("#price").val(),
												'loadBillItem.requireDate' : $("#requireDate").val(),
												'loadBillItem.amount' : $("#amount").val(),
												'loadBillItem.tax' : $("#tax").val()
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
							url : '${vix}/dtbcenter/pickupBusinessHandlingAction!getLoadBillItemJson.action?id=${loadBill.id}',
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
							align : 'center',
							hidden : true
							}, {
							field : 'itemCode',
							title : '${vv:varView("vix_mdm_item")}编码',
							width : 100,
							align : 'center'
							} ] ],
							columns : [ [ {
							field : 'itemName',
							title : '${vv:varView("vix_mdm_item")}名称',
							width : 100,
							align : 'center'
							}, {
							field : 'amount',
							title : '销售数量',
							width : 100,
							align : 'center'
							}, {
							field : 'unit',
							title : '计量单位',
							width : 100,
							align : 'center'
							}, {
							field : 'tax',
							title : '税率',
							width : 100,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'price',
							title : '单价',
							width : 100,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'taxTotal',
							title : '金额',
							width : 100,
							align : 'center'
							}, {
							field : 'requireDate',
							title : '需求日期',
							width : 100,
							editor : 'datebox',
							align : 'center',
							formatter : function(val, rec) {
								if (val != null && val != "") {
									var d = new Date(val);
									return format(d);
								} else
									return "";
							}
							} ] ],
							toolbar : [ {
							id : 'da2Btnadd',
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								saveDeliveryAddress('${vix}/dtbcenter/pickupBusinessHandlingAction!goUpdateLoadBillItem.action?=${loadBill.id}');
							}
							}, '-', {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#dlAddress2').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveDeliveryAddress('${vix}/dtbcenter/pickupBusinessHandlingAction!getLoadBillItemJson.action?id=' + row.id);
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
									url : '${vix}/dtbcenter/pickupBusinessHandlingAction!deleteWimStockrecordlinesById.action?id=' + rows[i].id,
									cache : false
									});
								}
							}
							} ]
							});
							function format(date) {
								var y = date.getFullYear();
								var m = date.getMonth() + 1;
								var d = date.getDate();
								return y + '-' + m + '-' + d;
							}
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