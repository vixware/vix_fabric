<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	$(function() {
		if (document.getElementById("investigationTime").value == "") {
			var myDate = new Date();
			$("#investigationTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate());
		}
	});
	function saveOrUpdateRetailPriceSurvey() {
		if ($('#retailPriceSurveyForm').validationEngine('validate')) {
			$.post('${vix}/drp/retailPriceSurveyAction!saveOrUpdate.action', {
			'retailPriceSurvey.id' : $("#id").val(),
			'retailPriceSurvey.name' : $("#name").val(),
			'retailPriceSurvey.ownedEnterprise' : $("#ownedEnterprise").val(),
			'retailPriceSurvey.enterpriseScale' : $("#enterpriseScale").val(),
			'retailPriceSurvey.formatForm' : $("#formatForm").val(),
			'retailPriceSurvey.investigator' : $("#investigator").val(),
			'retailPriceSurvey.investigationTime' : $("#investigationTime").val(),
			'retailPriceSurvey.memo' : memo.html()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/retailPriceSurveyAction!goList.action');
			});
		}
	}
	$("#retailPriceSurveyForm").validationEngine();
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
				<li><a href="#">零售价格调查</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${retailPriceSurvey.id }" />
<div class="content">
	<form id="retailPriceSurveyForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateRetailPriceSurvey()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/drp/retailPriceSurveyAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>零售价格信息</b></strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">主题：</td>
											<td><input id="name" name="name" value="${retailPriceSurvey.name }" type="text" size="30" /></td>
											<td align="right">企业：</td>
											<td><input id="ownedEnterprise" name="ownedEnterprise" value="${retailPriceSurvey.ownedEnterprise }" type="text" size="30" class="validate[required] text-input" /></td>
										</tr>
										<tr>
											<td align="right">企业规模：</td>
											<td><input id="enterpriseScale" name="enterpriseScale" value="${retailPriceSurvey.enterpriseScale }" type="text" size="30" /></td>
											<td align="right">业态形式：</td>
											<td><input id="formatForm" name="formatForm" value="${retailPriceSurvey.formatForm }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">调查人：</td>
											<td><input id="investigator" name="investigator" value="${retailPriceSurvey.investigator }" type="text" size="30" class="validate[required] text-input" /></td>
											<td align="right">调查时间：</td>
											<td><input id="investigationTime" name="investigationTime" value="<fmt:formatDate value='${retailPriceSurvey.investigationTime }' type='both' pattern='yyyy-MM-dd' />" type="text" class="validate[required] text-input" /> <img onclick="showTime('investigationTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16"
												height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">备注：</td>
											<td colspan="3"><textarea id="memo" name="memo">${retailPriceSurvey.memo }</textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
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
				<div class="clearfix" style="background: #FFF; position: relative;">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />零售价格明细</a></li>
						</ul>
					</div>
					<div id="a1" class="right_content" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
							function saveOrUpdateretailPriceSurveyDetail(url) {
								$.ajax({
								url : url,
								cache : false,
								success : function(html) {
									asyncbox.open({
									title : '零售价格明细',
									modal : true,
									width : 724,
									height : 400,
									html : html,
									callback : function(action, returnValue) {
										if (action == 'ok') {
											if ($('#retailPriceSurveyDetailForm').validationEngine('validate')) {
												$.post('${vix}/drp/retailPriceSurveyAction!saveOrUpdateRetailPriceSurveyDetail.action', {
												'id' : $("#id").val(),
												'retailPriceSurveyDetail.id' : $("#oiId").val(),
												'retailPriceSurveyDetail.itemName' : $("#itemName").val(),
												'retailPriceSurveyDetail.retailStores' : $("#retailStores").val(),
												'retailPriceSurveyDetail.standardRetailPrice' : $("#standardRetailPrice").val(),
												'retailPriceSurveyDetail.discountAmount' : $("#discountAmount").val(),
												'retailPriceSurveyDetail.isBundling' : $("#isBundling").val(),
												'retailPriceSurveyDetail.isMSendNSales' : $("#isMSendNSales").val()
												}, function(result) {
													showMessage(result);
													setTimeout("", 1000);
													$('#retailPriceSurveyDetail').datagrid("reload");
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
							$('#retailPriceSurveyDetail').datagrid({
							url : '${vix}/drp/retailPriceSurveyAction!getRetailPriceSurveyDetailJson.action?id=${retailPriceSurvey.id}',
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
							field : 'itemName',
							title : '产品名称',
							width : 150,
							align : 'center'
							}, {
							field : 'retailStores',
							title : '零售店名称',
							width : 150,
							align : 'center'
							}, {
							field : 'standardRetailPrice',
							title : '标准零售价格',
							width : 100,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'discountAmount',
							title : '金额折扣',
							width : 100,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'isBundling',
							title : '是否捆绑销售',
							width : 100,
							align : 'right',
							required : 'true'
							}, {
							field : 'isMSendNSales',
							title : '是否M送N销售',
							width : 100,
							align : 'center'
							} ] ],
							toolbar : [ {
							id : 'da2Btnadd',
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								saveOrUpdateretailPriceSurveyDetail('${vix}/drp/retailPriceSurveyAction!goSaveOrUpdateRetailPriceSurveyDetail.action');
							}
							}, '-', {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#retailPriceSurveyDetail').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveOrUpdateretailPriceSurveyDetail('${vix}/drp/retailPriceSurveyAction!goSaveOrUpdateRetailPriceSurveyDetail.action?id=' + row.id);
								}
							}
							}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								var rows = $('#retailPriceSurveyDetail').datagrid("getSelections"); //获取你选择的所有行	
								//循环所选的行
								for (var i = 0; i < rows.length; i++) {
									var index = $('#retailPriceSurveyDetail').datagrid('getRowIndex', rows[i]);//获取某行的行号
									$('#retailPriceSurveyDetail').datagrid('deleteRow', index); //通过行号移除该行
									$.ajax({
									url : '${vix}/drp/retailPriceSurveyAction!deleteRetailPriceSurveyDetailById.action?id=' + rows[i].id,
									cache : false
									});
								}
							}
							} ]
							});
						</script>
						<div style="padding: 8px;">
							<table id="retailPriceSurveyDetail"></table>
						</div>
					</div>
				</div>
			</dl>
		</div>
	</form>
</div>
</script>