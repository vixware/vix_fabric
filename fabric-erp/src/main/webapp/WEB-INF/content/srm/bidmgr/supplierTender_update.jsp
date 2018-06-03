<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
	$(function() {
		//设置单据类型选中(修改)
		$("#type").val('${supplierTender.type}');
	});
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/pur_tender.png" alt="" /> <s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#">供应商关系管理</a></li>
				<a href="#" onclick="loadContent('${vix}/srm/supplierTenderAction!goList.action');">投标管理</a>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${purchaseTender.id }" />
<div class="content">
	<form id="purchaseApplyForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateChannelDistributor()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/srm/supplierTenderAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>新增供应商投标 </b></strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /></a> <a href="#"><img
											src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /></a>
									</span> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td width="15%" align="right">投标编码：</td>
											<td width="35%"><input id="code" name="code" value="${purchaseTender.tenderCode }" readonly="readonly" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td width="15%" align="right">投标主题：</td>
											<td width="35%"><input id="name" name="name" value="${purchaseTender.title }" readonly="readonly" class="validate[required] text-input" type="text" size="30" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">类型：</td>
											<td><select id="type" name="type" style="width: 50" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">类型1</option>
													<option value="2">类型2</option>
											</select><span style="color: red;">*</span></td>
											<td align="right">投标公司：</td>
											<td><input id="tenderName" name="tenderName" value="${supplier.name }" readonly="readonly" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">报价金额：</td>
											<td><input id="quote" name="quote" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">币种：</td>
											<td><select id="currency" name="currency" style="width: 50" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">人民币</option>
													<option value="2">美元</option>
											</select><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">投标日期：</td>
											<td colspan="3"><input id="createTime" name="createTime" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('createTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">内容：</td>
											<td colspan="3"><textarea id="tenderContent" name="tenderContent" class="validate[required] text-input" rows="2" style="width: 600px"></textarea><span style="color: red;">*</span></td>
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
							<li class="current"><a onclick="javascript:tab(6,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />历史投标</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="dlApplyDetails" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlApplyDetailsTb',url: '${vix}/srm/supplierTenderAction!getSupplierTendersJson.action?id=${purchaseTender.id}'">
								<thead>
									<tr>
										<th data-options="field:'code',align:'center',width:200,editor:'text'">编码</th>
										<th data-options="field:'name',width:200,align:'center',editor:'text'">主题</th>
										<th data-options="field:'type',width:200,align:'center',editor:'text'">类型</th>
										<th data-options="field:'tenderName',width:200,align:'center',editor:'text'">投标公司</th>
										<th data-options="field:'quote',width:200,align:'center',editor:'numberbox'">报价金额</th>
										<th data-options="field:'currency',width:200,align:'center',editor:'numberbox'">币种</th>
										<th data-options="field:'createTime',width:200,align:'center',editor:'datebox',formatter:formatDatebox">投标日期</th>
									</tr>
								</thead>
							</table>
							<script type="text/javascript">
								$('#dlApplyDetails').datagrid();
								var editIndexDlApplyDetails = undefined;
								function endDlEditing1() {
									if (editIndexDlApplyDetails == undefined) {
										return true;
									}
									if ($('#dlApplyDetails').datagrid('validateRow', editIndexDlApplyDetails)) {
										$('#dlApplyDetails').datagrid('endEdit', editIndexDlApplyDetails);
										editIndexDlApplyDetails = undefined;
										return true;
									} else {
										return false;
									}
								}
								function onDlClickRow1(index) {
									if (editIndexDlApplyDetails != index) {
										if (endDlEditing1()) {
											$('#dlApplyDetails').datagrid('selectRow', index).datagrid('beginEdit', index);
											editIndexDlApplyDetails = index;
										} else {
											$('#dlApplyDetails').datagrid('selectRow', editIndexDlApplyDetails);
										}
									}
								}
								function appendDlApplyDetails() {
									if (endDlEditing1()) {
										$('#dlApplyDetails').datagrid('appendRow', {
											status : 'P'
										});
										editIndexDlApplyDetails = $('#dlApplyDetails').datagrid('getRows').length - 1;
										$('#dlApplyDetails').datagrid('selectRow', editIndexDlApplyDetails).datagrid('beginEdit', editIndexDlApplyDetails);
									}
								}
								function removeDlApplyDetails() {
									if (editIndexDlApplyDetails == undefined) {
										return;
									}
									$('#dlApplyDetails').datagrid('cancelEdit', editIndexDlApplyDetails).datagrid('deleteRow', editIndexDlApplyDetails);
									editIndexDlApplyDetails = undefined;
								}
								function saveDlApplyDetails() {
									if (endDlEditing1()) {
										$('#dlApplyDetails').datagrid('acceptChanges');
									}
								}

								//为原始Date类型拓展format一个方法，用于日期显示的格式化
								Date.prototype.format = function(format) {
									var o = {
									"M+" : this.getMonth() + 1, //month 
									"d+" : this.getDate(), //day 
									"h+" : this.getHours(), //hour 
									"m+" : this.getMinutes(), //minute 
									"s+" : this.getSeconds(), //second 
									"q+" : Math.floor((this.getMonth() + 3) / 3), //quarter 
									"S" : this.getMilliseconds()
									//millisecond 
									}
									if (/(y+)/.test(format))
										format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
									for ( var k in o)
										if (new RegExp("(" + k + ")").test(format))
											format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
									return format;
								}

								//格式化日期
								function formatDatebox(value) {
									if (value == null || value == '') {
										return '';
									}
									var dt;
									if (value instanceof Date) {
										dt = value;
									} else {
										dt = new Date(value);
										if (isNaN(dt)) {
											value = value.replace(/\/Date\((-?\d+)\)\//, '$1'); //标红的这段是关键代码，将那个长字符串的日期值转换成正常的JS日期格式
											dt = new Date();
											dt.setTime(value);
										}
									}

									return dt.format("yyyy-MM-dd"); //这里用到一个javascript的Date类型的拓展方法，这个是自己添加的拓展方法，在后面的步骤3定义
								}
							</script>
						</div>
					</div>
					<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<script type="text/javascript">
							$('#soAttach').datagrid({
							url : '${vix}/purchase/purchaseApplyAction!getAttachmentsJson.action?id=${purchaseApply.id}',
							title : '订单附件',
							width : 900,
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
							text : '添加',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								$.ajax({
								url : '${vix}/purchase/purchaseApplyAction!addAttachments.action',
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
											uploadFile('${vix}/purchase/purchaseApplyAction!uploadAttachments.action?id=${purchaseApply.id}', 'fileToUpload');
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
									url : '${vix}/purchase/purchaseApplyAction!deleteAttachments.action?afId=' + rows[i].id,
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
		</div>
	</form>
</div>