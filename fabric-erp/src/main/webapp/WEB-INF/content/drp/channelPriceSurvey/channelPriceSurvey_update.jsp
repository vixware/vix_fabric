<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	function saveOrUpdateChannelDistributor() {
		if ($('#purchasePlanForm').validationEngine('validate')) {
			$.post('${vix}/drp/channelPriceSurveyAction!saveOrUpdate.action', {
			'channelDistributor.id' : $("#id").val(),
			'channelDistributor.code' : $("#code").val(),
			'channelDistributor.name' : $("#name").val(),
			'channelDistributor.type' : $("#type").val(),
			'channelDistributor.artificialPerson' : $("#artificialPerson").val(),
			'channelDistributor.telephone' : $("#telephone").val(),
			'channelDistributor.email' : $("#email").val(),
			'channelDistributor.status' : $("#status").val(),
			'channelDistributor.memo' : memo.html()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/channelPriceSurveyAction!goList.action');
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
				<li><a href="#"><img src="img/sys_precisionControl.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#">市场管理</a></li>
				<li><a href="#">市场调查</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/channelPriceSurveyAction!goList.action?pageNo=${pageNo}');">渠道价格调查</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${channelDistributor.id }" />
<div class="content">
	<form id="purchasePlanForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateChannelDistributor()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/drp/channelPriceSurveyAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>渠道价格信息</b></strong>
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
											<td align="right">企业编码：</td>
											<td><input id="code" name="code" value="${channelDistributor.code }" type="text" size="30" /></td>
											<td align="right">企业名称：</td>
											<td><input id="name" name="name" value="${channelDistributor.name }" type="text" size="30" class="validate[required] text-input" /></td>
										</tr>
										<tr>
											<td align="right">类型：</td>
											<td><input id="type" name="type" value="${channelDistributor.type }" type="text" size="30" /></td>
											<td align="right">联系人：</td>
											<td><input id="artificialPerson" name="artificialPerson" value="${channelDistributor.artificialPerson }" type="text" size="30" class="validate[required] text-input" /></td>
										</tr>
										<tr>
											<td align="right">电话：</td>
											<td><input id="telephone" name="telephone" value="${channelDistributor.telephone }" type="text" size="30" /></td>
											<td align="right">邮箱：</td>
											<td><input id="email" name="email" value="${channelDistributor.email }" type="text" size="30" class="validate[required] text-input" /></td>
										</tr>
										<tr>
											<td align="right">状态：</td>
											<td><input id="status" name="status" value="${channelDistributor.status }" type="text" size="30" /></td>
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
				<div class="clearfix" style="background: #FFF; position: relative;">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />明细</a></li>
						</ul>
					</div>
					<div id="a1" class="right_content" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
							function saveDeliveryAddress(url) {
								$.ajax({
								url : url,
								cache : false,
								success : function(html) {
									asyncbox.open({
									title : '价格明细',
									modal : true,
									width : 724,
									height : 400,
									html : html,
									callback : function(action, returnValue) {
										if (action == 'ok') {
											if ($('#retailPriceSurveyDetailForm').validationEngine('validate')) {
												$.post('${vix}/drp/channelPriceSurveyAction!saveOrUpdateChannelPrice.action', {
												'id' : $("#id").val(),
												'channelPrice.id' : $("#oiId").val(),
												'channelPrice.itemName' : $("#itemName").val(),
												'channelPrice.channelPrice' : $("#channelPrice").val(),
												'channelPrice.promotionalPrice' : $("#promotionalPrice").val(),
												'channelPrice.layoutRebate' : $("#layoutRebate").val(),
												'channelPrice.marketAccess' : $("#marketAccess").val(),
												'channelPrice.paymentTerm' : $("#paymentTerm").val(),
												'channelPrice.cashDiscount' : $("#cashDiscount").val(),
												'channelPrice.premiumRate' : $("#premiumRate").val(),
												'channelPrice.bulkDiscount' : $("#bulkDiscount").val(),
												'channelPrice.timePeriod' : $("#timePeriod").val(),
												'channelPrice.yearEndRebate' : $("#yearEndRebate").val()
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
							url : '${vix}/drp/channelPriceSurveyAction!getRetailPriceSurveyDetailJson.action?id=${channelDistributor.id}',
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
							field : 'itemName',
							title : '商品名称',
							width : 100,
							align : 'center'
							} ] ],
							columns : [ [ {
							field : 'channelPrice',
							title : '渠道价格',
							width : 100,
							align : 'center'
							}, {
							field : 'promotionalPrice',
							title : '促销价格',
							width : 100,
							align : 'center'
							}, {
							field : 'layoutRebate',
							title : '摆放返点',
							width : 100,
							align : 'center'
							}, {
							field : 'marketAccess',
							title : '市场准入',
							width : 100,
							align : 'right',
							required : 'true'
							}, {
							field : 'paymentTerm',
							title : '账期',
							width : 100,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'cashDiscount',
							title : '现付贴现',
							width : 100,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'premiumRate',
							title : '赠品率',
							width : 100,
							align : 'center'
							}, {
							field : 'bulkDiscount',
							title : '批量折扣',
							width : 100,
							align : 'center'
							}, {
							field : 'timePeriod',
							title : '时段返点',
							width : 100,
							align : 'center'
							}, {
							field : 'yearEndRebate',
							title : '年终返点',
							width : 100,
							align : 'center'
							} ] ],
							toolbar : [ {
							id : 'da2Btnadd',
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								saveDeliveryAddress('${vix}/drp/channelPriceSurveyAction!goSaveOrUpdateChannelPrice.action');
							}
							}, '-', {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#dlAddress2').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveDeliveryAddress('${vix}/drp/channelPriceSurveyAction!goSaveOrUpdateChannelPrice.action?id=' + row.id);
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
									url : '${vix}/drp/channelPriceSurveyAction!deleteChannelPriceById.action?id=' + rows[i].id,
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