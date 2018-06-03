<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/pur_tender.png" alt="" /> <s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#"><s:text name="pur_purchase_manage" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/purchase/purchaseTenderAction!goList.action');">招标管理</a></li>
				<li><a href="#">创建招标</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${purchaseTender.id }" />
<div class="content">
	<form id="tenderForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /></a> <a href="#" onclick="loadContent('${vix}/purchase/purchaseTenderAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong> <b>新增项目包招标</b> <i></i>
					</strong>
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
											<td align="right">编码：</td>
											<td><input id="tenderCode" name="tenderCode" value="${purchaseTender.tenderCode }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">主题：</td>
											<td><input id="title" name="title" value="${purchaseTender.title }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">类型：</td>
											<td><select id="type" name="type" style="width: 50" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">类型1</option>
													<option value="2">类型2</option>
											</select><span style="color: red;">*</span></td>
											<td align="right">竞标地点：</td>
											<td><input id="tenderAgent" name="tenderAgent" value="${purchaseTender.tenderAgent }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">联系人：</td>
											<td><input id="contact" name="contact" value="${purchaseTender.contact }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">联系电话：</td>
											<td><input id="telephone" name="telephone" value="${purchaseTender.telephone }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">开始日期：</td>
											<td><input id="startTime" name="startTime" value="<fmt:formatDate value='${purchaseTender.startTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('startTime','yyyy-MM-dd')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">结束日期：</td>
											<td><input id="endTime" name="endTime" value="<fmt:formatDate value='${purchaseTender.endTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('endTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif"
												width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">简介：</td>
											<td colspan="3"><textarea id="introduction" name="introduction" class="example" rows="2" style="width: 700px">${purchaseTender.introduction }</textarea></td>
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
							<li class="current"><a onclick="javascript:tab(6,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />子项目组</a></li>
							<li><a onclick="javascript:$('#a2').attr('style','');tab(8,2,'a',event)"><img src="${vix}/common/img/attachment.png" alt="" />附件</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="dlLineItem" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlLineItemTb',url: '${vix}/purchase/purchaseTenderAction!getTenderDetailsJson.action?id=${purchaseTender.id}',onClickRow: onDlClickRow1">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:120">编号</th>
										<th data-options="field:'title',align:'center',width:120">主题</th>
										<th data-options="field:'type',align:'center',width:120">类型</th>
										<th data-options="field:'tenderAgent',align:'center',width:120">竞标地点</th>
										<th data-options="field:'contact',align:'center',width:120">联系人</th>
										<th data-options="field:'telephone',align:'center',width:120">联系电话</th>
										<th data-options="field:'startTime',align:'center',width:120,formatter:formatDatebox">开始时间</th>
										<th data-options="field:'endTime',align:'center',width:120,formatter:formatDatebox">结束时间</th>
									</tr>
								</thead>
							</table>
							<div id="dlLineItemTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addTenderDetails();"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-edit',plain:true" onclick="editTenderDetails();"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">修改</span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick=""><span class="l-btn-left"><span
										class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span></a>
							</div>
							<script type="text/javascript">
								$('#dlLineItem').datagrid();
								var editIndexDlLineItem = undefined;
								function endDlEditing1() {
									if (editIndexDlLineItem == undefined) {
										return true;
									}
									if ($('#dlLineItem').datagrid('validateRow', editIndexDlLineItem)) {
										$('#dlLineItem').datagrid('endEdit', editIndexDlLineItem);
										editIndexDlLineItem = undefined;
										return true;
									} else {
										return false;
									}
								}
								function onDlClickRow1(index) {
									if (editIndexDlLineItem != index) {
										if (endDlEditing1()) {
											$('#dlLineItem').datagrid('selectRow', index).datagrid('beginEdit', index);
											editIndexDlLineItem = index;
										} else {
											$('#dlLineItem').datagrid('selectRow', editIndexDlLineItem);
										}
									}
								}
								function removeDlLineItem() {
									if (editIndexDlLineItem == undefined) {
										return;
									}
									$('#dlLineItem').datagrid('cancelEdit', editIndexDlLineItem).datagrid('deleteRow', editIndexDlLineItem);
									editIndexDlLineItem = undefined;
								}
								function addTenderDetails() {
									var ptId = '${purchaseTender.id }';
									if (null != ptId && "" != ptId) {
										$.ajax({
										url : '${vix}/purchase/purchaseTenderAction!goSaveOrUpdate2.action?isS=0&pId=' + ptId,
										cache : false,
										success : function(html) {
											$("#mainContent").html(html);
										}
										});
									} else {
										alert("请先保存主项目!");
									}
								}
								function editTenderDetails() {
									var ptId = '${purchaseTender.id }';
									if (null != ptId && "" != ptId) {
										var row = $('#dlLineItem').datagrid("getSelected");
										if (null != row) {
											$.ajax({
											url : '${vix}/purchase/purchaseTenderAction!goSaveOrUpdate2.action?isS=0&pId=' + ptId + '&id=' + row.id,
											cache : false,
											success : function(html) {
												$("#mainContent").html(html);
											}
											});
										} else {
											alert("请选择需要修改的行!");
										}
									} else {
										alert("请先保存主项目!");
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
											format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k])
													.substr(("" + o[k]).length));
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
							$('#soAttach')
									.datagrid({
									url : '${vix}/purchase/purchaseTenderAction!getAttachmentsJson.action?id=${purchaseTender.id}',
									title : '订单附件',
									width : 900,
									height : 'auto',
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
										$
												.ajax({
												url : '${vix}/purchase/purchaseTenderAction!addAttachments.action',
												cache : false,
												success : function(html) {
													asyncbox
															.open({
															modal : true,
															width : 364,
															height : 160,
															title : "上传附件",
															html : html,
															callback : function(action, returnValue) {
																if (action == 'ok') {
																	uploadFile('${vix}/purchase/purchaseTenderAction!uploadAttachments.action?id=${purchaseTender.id}', 'fileToUpload');
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
											url : '${vix}/purchase/purchaseTenderAction!deleteAttachments.action?afId=' + rows[i].id,
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
		</div>
		</dl>
</div>
</form>
</div>
