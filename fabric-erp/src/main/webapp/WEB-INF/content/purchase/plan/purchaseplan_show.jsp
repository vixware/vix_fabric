<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	if ($(".ms").length > 0) {
		$(".ms").hover(function() {
			$(">ul", this).stop().slideDown(100);
		}, function() {
			$(">ul", this).stop().slideUp(100);
		});
		$(".ms ul li").hover(function() {
			$(">a", this).addClass("hover");
			$(">ul", this).stop().slideDown(100);
		}, function() {
			$(">a", this).removeClass("hover");
			$(">ul", this).stop().slideUp(100);
		});
	}
	function goShowBeforeAndAfterPurchaseOrder(id, str) {
		$.ajax({
		url : '${vix}/purchase/purchaseOrderAction!goShowBeforeAndAfterPurchaseOrder.action?id=' + id + "&str=" + str,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/inv_inbound.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">采购管理 </a></li>
				<li><a href="#" onclick="">采购计划 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${purchaseOrder.id }" />
<input type="hidden" id="groupCode" name="groupCode" value="${purchaseOrder.groupCode }" />
<div class="content">
	<form id="stockRecordsForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a href="#"><img width="22" height="22" title="禁用" src="${vix}/common/img/dt_disable.png" /> </a> <a href="#"><img width="22" height="22" title="锁定" src="${vix}/common/img/dt_locked.png" /> </a><a href="#"><img width="22" height="22" title="删除" src="${vix}/common/img/dt_delete.png" /> </a> <a href="#"><img
							width="22" height="22" title="审批通过" src="${vix}/common/img/dt_aprroval.png" /> </a> <a href="#"><img width="22" height="22" title="驳回" src="${vix}/common/img/dt_reject.png"> </a> <a href="#" onclick="goShowBeforeAndAfterPurchaseOrder('${purchaseOrder.id}','before');"><img width="22" height="22" title="上一条"
							src="${vix}/common/img/dt_before.png"> </a> <a href="#" onclick="goShowBeforeAndAfterPurchaseOrder('${purchaseOrder.id}','after');"><img width="22" height="22" title="下一条" src="${vix}/common/img/dt_next.png"> </a> <a href="#" onclick="goPrintPurchaseOrder('${purchaseOrder.id}');"><img width="22" height="22" title="打印"
							src="${vix}/common/img/dt_print.png"> </a>
						<div class="ms" style="float: none; display: inline;">
							<a href="#"><img width="22" height="22" alt="关联单据" src="${vix}/common/img/dt_report.png"> </a>
							<ul style="display: none; overflow: hidden; height: 124px; margin-top: 0px; margin-bottom: 0px; padding-top: 0px; padding-bottom: 0px;">
								<li><a href="#" onclick=""><img src="${vix}/common/img/icon_10.png" alt="">引用单据</a></li>
							</ul>
						</div> <a href="#"><img width="22" height="22" title="导出" src="${vix}/common/img/dt_export.png"> </a> <a href="#" onclick="loadContent('${vix}/purchase/purchaseOrderAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a></span> <strong><b>采购计划</b><i></i> </strong>
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
											<td><input id="purchasePlanCode" name="purchasePlanCode" value="${purchasePlan.purchasePlanCode }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">名称：</td>
											<td><input id="purchasePlanName" name="purchasePlanName" value="${purchasePlan.purchasePlanName }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">类型：</td>
											<td><select id="bizStyle" name="style" style="width: 50" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">采购</option>
													<option value="2">代销</option>
											</select><span style="color: red;">*</span></td>
											<td align="right">供应商名称：</td>
											<td><input id="supplierName" name="supplierName" value="${purchasePlan.supplierName }" type="text" size="30" /><input type="hidden" id="supplierCode" name="supplierCode" value="${purchasePlan.supplierCode }" /> <input class="btn" type="button" value="选择" onclick="chooseRadioSupplier();" /></td>
										</tr>
										<tr>
											<td align="right">创建人：</td>
											<td><input id="creator" name="creator" value="${purchasePlan.creator }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">创建时间：</td>
											<td><input id="createTime" name="createTime" value="<fmt:formatDate value='${purchasePlan.createTime }' type='both' pattern='yyyy-MM-dd' />" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('createTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16"
												height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">说明：</td>
											<td colspan="3"><textarea id="description" name="description" class="example" rows="1" style="width: 600px; height: 128px;">${purchasePlan.description }</textarea></td>
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
							<li class="current"><a onclick="javascript:tab(6,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />明细</a></li>
							<li><a onclick="javascript:$('#a2').attr('style','');tab(6,2,'a',event);"><img src="${vix}/common/img/attachment.png" alt="" />附件</a></li>
						</ul>
					</div>
					<div id="a1" class="right_content" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
							function saveDeliveryAddress(id) {
								$.ajax({
								url : '${vix}/purchase/purchasePlanAction!goSaveOrUpdatePurchasePlanItems.action?id=' + id,
								cache : false,
								success : function(html) {
									asyncbox.open({
									modal : true,
									width : 1000,
									height : 650,
									title : "明细",
									html : html,
									callback : function(action) {
										if (action == 'cancel' || action == 'close') {
											$('#dlAddress2').datagrid("reload");
										}
									},
									btnsbar : [ {
									text : '关闭',
									action : 'cancel'
									} ]
									});
								}
								});
							}
							$('#dlAddress2').datagrid({
							url : '${vix}/purchase/purchasePlanAction!getPurchasePlanItemsJson.action?id=${purchasePlan.id}',
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
							field : 'itemCode',
							title : '商品编码',
							width : 200,
							align : 'center'
							}, {
							field : 'itemName',
							title : '商品名称',
							width : 200,
							align : 'center'
							} ] ],
							columns : [ [ {
							field : 'specification',
							title : '规格型号',
							width : 200,
							align : 'center'
							}, {
							field : 'measureUnit',
							title : '单位',
							width : 200,
							align : 'center',
							formatter : function(value, rec, index) {
								var measureUnitName = '';
								if (value != null) {
									measureUnitName = value.name;
								}
								return measureUnitName;
							}
							}, {
							field : 'unitcost',
							title : '单价',
							width : 200,
							align : 'center',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'amount',
							title : '数量',
							width : 200,
							align : 'center',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'price',
							title : '金额',
							width : 200,
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
								saveDeliveryAddress(0);
							}
							}, '-', {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#dlAddress2').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveDeliveryAddress(row.id);
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
									url : '${vix}/purchase/purchasePlanAction!deletePurchasePlanItemsById.action?id=' + rows[i].id,
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
					<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
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
											//$('#soAttach').datagrid({ url:"${vix}/purchase/purchasePlanAction!getAttachmentsJson.action?id=${purchasePlan.id}",method:"post"});
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