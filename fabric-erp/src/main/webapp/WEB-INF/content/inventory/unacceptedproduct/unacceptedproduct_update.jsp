<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdateNonconformingProduct() {
		if ($('#nonconformingProductform').validationEngine('validate')) {
			$.post('${vix}/inventory/unAcceptedProductAction!saveOrUpdate.action', {
			'nonconformingProduct.id' : $("#nonconformingProductId").val(),
			'nonconformingProduct.code' : $("#code").val(),
			'nonconformingProduct.name' : $("#name").val(),
			'nonconformingProduct.createTime' : $("#createTime").val(),
			'nonconformingProduct.biztype' : $("#biztype").val(),
			'nonconformingProduct.creator' : $("#creator").val(),
			'updateField' : updateField,
			'nonconformingProduct.memo' : $("#memo").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/inventory/unAcceptedProductAction!goList.action');
			});
		}
	}
	function saveAndNew() {
		if ($('#nonconformingProductform').validationEngine('validate')) {
			$.post('${vix}/inventory/unAcceptedProductAction!saveOrUpdate.action', {
			'nonconformingProduct.id' : $("#nonconformingProductId").val(),
			'nonconformingProduct.code' : $("#code").val(),
			'nonconformingProduct.name' : $("#name").val(),
			'nonconformingProduct.createTime' : $("#createTime").val(),
			'nonconformingProduct.biztype' : $("#biztype").val(),
			'nonconformingProduct.creator' : $("#creator").val(),
			'updateField' : updateField,
			'nonconformingProduct.memo' : $("#memo").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				saveOrUpdate(0);
			});
		}
	}
	$("#nonconformingProductform").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/inv_beginDefectiveItem.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="wim_stock_control" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/unAcceptedProductAction!goList.action?pageNo=${pageNo}');"><s:text name="wim_unacceptedproduct" /> </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="nonconformingProductId" name="nonconformingProductId" value="${nonconformingProduct.id}" />
<div class="content">
	<form id="nonconformingProductform">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateNonconformingProduct()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#" onclick="saveAndNew()"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22"
							height="22" title="保存并提交审批" src="${vix}/common/img/dt_submit.png" /> </a> <a href="#" onclick="loadContent('${vix}/inventory/unAcceptedProductAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>不合格品</b> </strong>
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
											<th>单据编码：</th>
											<td><input id="code" name="code" value="${nonconformingProduct.code }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>主题：</th>
											<td><input id="name" name="name" value="${nonconformingProduct.name }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>入库日期：</th>
											<td><input id="createTime" name="createTime" value="<s:date name='%{nonconformingProduct.createTime}' format='yyyy-MM-dd HH:mm:ss'/>" type="text" onchange="fieldChanged(this);" class="validate[required] text-input" readonly="readonly" /> <img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span style="color: red;">*</span></td>
											<th>不合格类型：</th>
											<td><select id="biztype" name="biztype" class="validate[required] text-input" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<option value="1">质量问题</option>
													<option value="2" selected="selected">残次品</option>
													<option value="3">其他</option>
											</select><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>操作人：</th>
											<td><input id="creator" name="creator" value="${nonconformingProduct.creator }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>备注：</th>
											<td colspan="3"><input id="memo" name="memo" value="${nonconformingProduct.memo }" type="text" size="50" onchange="fieldChanged(this);"></td>
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
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />明细</a></li>
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
									title : '商品明细',
									modal : true,
									width : 950,
									height : 550,
									html : html,
									callback : function(action) {
									},
									btnsbar : [ {
									text : '关闭',
									action : 'cancel'
									} ]
									});
								}
								});
							}
							$('#nonconformingProductDetails').datagrid({
							url : '${vix}/inventory/unAcceptedProductAction!getNonconformingProductDetailsJson.action?id=${nonconformingProduct.id}',
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
							fitColumns : true,
							idField : 'id',
							frozenColumns : [ [ {
							field : 'id',
							title : '编号',
							width : 60,
							hidden : true,
							align : 'center'
							}, {
							field : 'itemcode',
							title : '商品编码',
							width : 200,
							align : 'center'
							}, {
							field : 'itemname',
							title : '商品名称',
							width : 350,
							align : 'center'
							} ] ],
							columns : [ [ {
							field : 'specification',
							title : '规格型号',
							width : 200,
							align : 'center'
							}, {
							field : 'skuCode',
							title : 'skuCode',
							width : 100,
							align : 'center'
							}, {
							field : 'unit',
							title : '单位',
							width : 100,
							align : 'center'
							}, {
							field : 'unitcost',
							title : '单价',
							width : 100,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'quantity',
							title : '数量',
							width : 100,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
							} ] ],
							toolbar : [ {
							id : 'da2Btnadd',
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								saveDeliveryAddress('${vix}/inventory/unAcceptedProductAction!goListItem.action?=${nonconformingProduct.id}');
							}
							}, '-', {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#nonconformingProductDetails').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveDeliveryAddress('${vix}/inventory/unAcceptedProductAction!goListItem.action?id=' + row.id);
								}
							}
							}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								var rows = $('#nonconformingProductDetails').datagrid("getSelections"); //获取你选择的所有行	
								//循环所选的行
								for (var i = 0; i < rows.length; i++) {
									var index = $('#nonconformingProductDetails').datagrid('getRowIndex', rows[i]);//获取某行的行号
									$.ajax({
									url : '${vix}/inventory/unAcceptedProductAction!deleteNonconformingProductDetailsById.action?id=' + rows[i].id,
									cache : false
									});
									$('#nonconformingProductDetails').datagrid('deleteRow', index); //通过行号移除该行
								}
							}
							} ]
							});
						</script>
						<div style="padding: 8px;">
							<table id="nonconformingProductDetails"></table>
						</div>
					</div>
				</div>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>