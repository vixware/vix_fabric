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
	//上一条数据
	function goBeforeWimAdjustpvouch(id) {
		$.ajax({
		url : '${vix}/inventory/positionAdjustmentAction!goBeforeWimAdjustpvouch.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	//下一条
	function goAfterWimAdjustpvouch(id) {
		$.ajax({
		url : '${vix}/inventory/positionAdjustmentAction!goAfterWimAdjustpvouch.action?id=' + id,
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
				<li><a href="#"><img src="${vix}/common/img/inv_takestock.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="wim_stock_control" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/positionAdjustmentAction!goList.action?pageNo=${pageNo}');"><s:text name="wim_positionadjustment" /> </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${wimAdjustpvouch.id}" />
<div class="content">
	<form id="stockRecordsForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a href="#"><img width="22" height="22" title="禁用" src="${vix}/common/img/dt_disable.png" /> </a> <a href="#"><img width="22" height="22" title="锁定" src="${vix}/common/img/dt_locked.png" /> </a><a href="#"><img width="22" height="22" title="删除" src="${vix}/common/img/dt_delete.png" /> </a> <a href="#"><img
							width="22" height="22" title="审批通过" src="${vix}/common/img/dt_aprroval.png" /> </a> <a href="#"><img width="22" height="22" title="驳回" src="${vix}/common/img/dt_reject.png"> </a> <a href="#" onclick="goBeforeWimAdjustpvouch('${wimAdjustpvouch.id}');"><img width="22" height="22" title="上一条" src="${vix}/common/img/dt_before.png">
					</a> <a href="#" onclick="goAfterWimAdjustpvouch('${wimAdjustpvouch.id}');"><img width="22" height="22" title="下一条" src="${vix}/common/img/dt_next.png"> </a> <a href="#"><img width="22" height="22" title="打印" src="${vix}/common/img/dt_print.png"> </a> <a href="#"><img width="22" height="22" title="导出"
							src="${vix}/common/img/dt_export.png"> </a> <a href="#" onclick="loadContent('${vix}/inventory/positionAdjustmentAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a></span> <strong><b>货位调整单 </b><i></i> </strong>
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
											<th>调整单号：</th>
											<td><input id="code" name="code" value="${wimAdjustpvouch.code }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>主题：</th>
											<td><input id="name" name="name" value="${wimAdjustpvouch.name}" class="validate[required] text-input" type="text" size="30" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>仓库：</th>
											<td><input type="hidden" id="warehouseId" name="warehouseId" value="${wimAdjustpvouch.invWarehouse.id }" /><input type="text" name="warehouseName" id="warehouseName" value="${wimAdjustpvouch.invWarehouse.name }" class="validate[required] text-input" size="30" /><span style="color: red;">*</span><input class="btn" type="button"
												value="选择" onclick="chooseWarehouse();" /></td>
											<th>调整日期：</th>
											<td><input id="createTime" name="createTime" value="<s:date name="%{wimAdjustpvouch.createTime}" format="yyyy-MM-dd HH:mm:ss"/>" type="text" class="validate[required] text-input" readonly="readonly" /> <img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>业务员：</th>
											<td><input id="personcode" name="personcode" value="${wimAdjustpvouch.personcode }" type="text" size="30"></td>
										</tr>
										<tr>
											<td align="right">备注：</td>
											<td colspan="3"><textarea id="memo" name="memo" style="width: 550px; height: 100px; margin-top: 5px;">${wimAdjustpvouch.memo }</textarea></td>
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
							<li class="current"><a onclick="javascript:$('#a1').attr('style','');tab(4,2,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />明细</a></li>
						</ul>
					</div>
					<div id="a1" class="right_content" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
							function saveDeliveryAddress(id) {
								$.ajax({
								url : '${vix}/inventory/positionAdjustmentAction!goInventoryCurrentStockList.action?id=' + id + "&wimAdjustpvouchId=" + $('#wimAdjustpvouchId').val(),
								cache : false,
								success : function(html) {
									asyncbox.open({
									title : '商品明细',
									modal : true,
									width : 900,
									height : 525,
									html : html,
									callback : function(action, returnValue) {
									},
									btnsbar : $.btn.OKCANCEL
									});
								}
								});
							}
							$('#stockrecordlines').datagrid({
							url : '${vix}/inventory/positionAdjustmentAction!getWimAdjustpvouchItemJson.action?id=${wimAdjustpvouch.id}',
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
							/* fitColumns : true, */
							idField : 'id',
							frozenColumns : [ [ {
							field : 'id',
							title : '编号',
							width : 60,
							hidden : true,
							align : 'right'
							}, {
							field : 'itemcode',
							title : '商品编码',
							width : 150,
							align : 'right'
							}, {
							field : 'itemname',
							title : '商品名称',
							width : 350,
							align : 'right'
							} ] ],
							columns : [ [ {
							field : 'cbposcode',
							title : '调整前货位',
							width : 150,
							align : 'right',
							required : 'true'
							}, {
							field : 'caposcode',
							title : '调整后货位',
							width : 150,
							align : 'right',
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
								var row = $('#stockrecordlines').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveDeliveryAddress('${vix}/inventory/positionAdjustmentAction!goInventoryCurrentStockList.action?id=' + row.id);
								}
							}
							}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								var rows = $('#stockrecordlines').datagrid("getSelections"); //获取你选择的所有行	
								//循环所选的行
								for (var i = 0; i < rows.length; i++) {
									var index = $('#stockrecordlines').datagrid('getRowIndex', rows[i]);//获取某行的行号
									$.ajax({
									url : '${vix}/inventory/positionAdjustmentAction!deleteWimStockrecordlinesById.action?id=' + rows[i].id,
									cache : false
									});
									$('#stockrecordlines').datagrid('deleteRow', index); //通过行号移除该行
								}
							}
							} ]
							});
						</script>
						<div style="padding: 8px;">
							<table id="stockrecordlines"></table>
						</div>
					</div>
				</div>
			</dl>
		</div>
	</form>
</div>