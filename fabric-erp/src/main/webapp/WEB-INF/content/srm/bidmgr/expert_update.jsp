<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
	function chooseProduct() {
		$.ajax({
		url : '${vix}/template/productAction!goChooseProduct.action?tag=choose',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 1000,
			height : 520,
			title : "选择商品",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					$.ajax({
					url : '${vix}/template/orderAction!saveOrUpdateOrderItem.action?id=' + $("#id").val() + "&productIds=" + returnValue,
					cache : false,
					success : function(result) {
						asyncbox.success(result, "提示信息", function(action) {
							pager("start", "${vix}/template/orderAction!goOrderItemSingleList.action?id=" + $("#id").val(), 'orderUpdate');
						});
					}
					});
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
	pager("start", "${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id=" + $("#id").val(), 'orderUpdate');
	function currentPager(tag) {
		pager(tag, "${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id=" + $("#id").val(), 'orderUpdate');
	}
	/** 保存采购计划 */
	function saveOrUpdatePlan() {
		var orderItemStr = "";
		/** 明细 */
		var dlData = $("#dlLineItem").datagrid("getRows");
		var piJson = JSON.stringify(dlData);
		if ($('#purchasePlanForm').validationEngine('validate')) {
			$.post('${vix}/purchase/purchasePlanAction!saveOrUpdate.action', {
			'purchasePlan.id' : $("#id").val(),
			'purchasePlan.purchasePlanCode' : $("#purchasePlanCode").val(),
			'purchasePlan.purchasePlanName' : $("#purchasePlanName").val(),
			'purchasePlan.style' : $("#bizStyle").val(),
			'purchasePlan.amount' : $("#amount").val(),
			'purchasePlan.supplierName' : $("#supplierName").val(),
			'purchasePlan.createTime' : $("#createTime").val(),
			'purchasePlan.executeDepartment' : $("#executeDepartment").val(),
			'purchasePlan.executePerson' : $("#executePerson").val(),
			'purchasePlan.description' : $("#description").val(),
			'orderItemStr' : orderItemStr,
			'piJson' : piJson
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/purchase/purchasePlanAction!goList.action?menuId=menuOrder');
			});
		}
	}

	$("#purchasePlanForm").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/pur_tender.png" alt="" /> <s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#">供应商关系管理</a></li>
				<li><a href="#">招标管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/srm/expertAction!goList.action');">专家管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${purchasePlan.id }" />
<div class="content">
	<form id="purchasePlanForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdatePlan()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#" onclick="loadContent('${vix}/srm/expertAction!goList.action');"><img
							width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>新增评标</b></strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /></a> <a href="#"><img
											src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /></a>
									</span> <strong>项目信息</strong>
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
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <strong>评标</strong>
								</dt>

								<dd style="display: none;">
									<table style="border: none;">
										<tr>
											<td align="right">评审意见：</td>
											<td><textarea rows="2" style="width: 500px;"></textarea></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<div style="margin: 15px 70px; display: none;">
							<textarea id="content" name="content"></textarea>
							<script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script>
							<script type="text/javascript">
								var editor = KindEditor.create('textarea[name="content"]', {
								basePath : '${vix}/plugin/KindEditor/',
								width : 1100,
								height : 300,
								cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
								uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
								fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
								allowFileManager : true
								});
							</script>
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
							url : '${vix}/purchase/purchasePlanAction!getAttachmentsJson.action?id=${purchasePlan.id}',
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
								url : '${vix}/purchase/purchasePlanAction!addAttachments.action',
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
											uploadFile('${vix}/purchase/purchasePlanAction!uploadAttachments.action?id=${purchasePlan.id}', 'fileToUpload');
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
									url : '${vix}/purchase/purchasePlanAction!deleteAttachments.action?afId=' + rows[i].id,
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