<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	/** 保存合同 */
	function saveOrUpdateOrder() {

		if ($('#orderForm').validationEngine('validate')) {
			$.post('${vix}/contract/protocolTempleteAction!saveOrUpdate.action', {
			'protocolTemplete.id' : $("#id").val(),
			'protocolTemplete.partyName' : $("#partyName").val(),
			'protocolTemplete.bsquarename' : $("#bsquarename").val(),
			'protocolTemplete.agreementContent' : $("#agreementContent").val(),
			'protocolTemplete.partysignature' : $("#partysignature").val(),
			'protocolTemplete.bsquareSignature' : $("#bsquareSignature").val(),
			'protocolTemplete.idName' : $("#idName").val(),
			'protocolTemplete.postalCoding' : $("#postalCoding").val(),
			'protocolTemplete.contractSignedLocation' : $("#contractSignedLocation").val(),
			'protocolTemplete.contractSignedTime' : $("#contractSignedTime").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/contract/protocolTempleteAction!goList.action?menuId=menuContract');
			});
		}
	}

	$("#orderForm").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/ctm_contract.png" alt="" /> <s:text name="supplyChain" /></a></li>
				<li><a href="#"><s:text name="分销管理" /></a></li>
				<li><a href="#"><s:text name="经销商管理" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/contract/protocolTempleteAction!goList.action?pageNo=${pageNo}');"><s:text name="合同协议" /></a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${protocolTemplete.id}" />
<div class="content">
	<form id="orderForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder();"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/contract/protocolTempleteAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="新增" /> </b><i></i> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /> </a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /> </a> <a href="#"><img
											src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /> </a>
									</span> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th align="right">甲方（姓名）：</th>
											<td><input id="partyName" name="partyName" value="${protocolTemplete.partyName}" type="text" size="30" class="validate[required] text-input" /></td>
											<th align="right">乙方（姓名）：</th>
											<td><input id="bsquarename" name="bsquarename" value="${protocolTemplete.bsquarename}" type="text" size="30" class="validate[required] text-input" /></td>
										</tr>
										<tr>
											<th align="right">协议内容：</th>
											<td colspan="3"><textarea id="agreementContent" class="agreementContent" rows="1" style="width: 880px">${protocolTemplete.agreementContent}</textarea></td>
										</tr>
										<tr>
											<th align="right">甲方（签章）：</th>
											<td><input id="partysignature" name="partysignature" value="${protocolTemplete.partysignature}" type="text" size="30" class="validate[required] text-input" /></td>
											<th align="right">乙方（签章）：</th>
											<td><input id="bsquareSignature" name="bsquareSignature" value="${protocolTemplete.bsquareSignature}" type="text" size="30" class="validate[required] text-input" /></td>
										</tr>
										<tr>
											<th align="right">身份证：</th>
											<td><input id="idName" name="idName" value="${protocolTemplete.idName}" type="text" size="30" class="validate[required] text-input" /></td>
											<th align="right">地址：</th>
											<td><input id="postalCoding" name="postalCoding" value="${protocolTemplete.postalCoding}" type="text" size="30" class="validate[required] text-input" /></td>
										</tr>
										<tr>
											<th align="right">签订地址：</th>
											<td><input id="contractSignedLocation" name="contractSignedLocation" value="${protocolTemplete.contractSignedLocation}" type="text" size="30" class="validate[required] text-input" /></td>
											<th align="right">签订时间：</th>
											<td><input id="contractSignedTime" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd" name="contractSignedTime" value="<fmt:formatDate value='${protocolTemplete.contractSignedTime}' type='both' pattern='yyyy-MM-dd' />" type="text" size="30" /> <img onclick="showTime('contractSignedTime','yyyy-MM-dd')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
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
							<li class="current"><a onclick="javascript:tab(5,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />协议模板</a></li>
						</ul>

					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="soAttach"></table>

							<script type="text/javascript">
								$('#soAttach').datagrid({
								url : '${vix}/contract/protocolTempleteAction!getContractTemplate.action?id=${protocolTemplete.id}',
								width : 'auto',
								height : '450',
								fitColumns : true,
								columns : [ [ {
								field : 'id',
								title : '序号',
								width : 80
								}, {
								field : 'saleOrderCode',
								title : '主题',
								width : 80
								}, {
								field : 'detail',
								title : '路径',
								width : 80
								}, {
								field : 'deliveryTime',
								title : '创建时间',
								width : 80
								}, {
								field : 'itemCode',
								title : '下载',
								width : 80
								}, ] ],
								toolbar : [ {
								id : 'saBtnadd',
								text : '新增',
								iconCls : 'icon-add',
								handler : function() {
									$('#btnsave').linkbutton('enable');
									$.ajax({
									url : '${vix}/contract/protocolTempleteAction!addAttachFile1.action',
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
												uploadFile('${vix}/contract/protocolTempleteAction!uploadAttachment.action?id=${protocolTemplete.id}', 'fileToUpload');
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
										url : '${vix}/contract/protocolTempleteAction!deleteAttachFile.action?afId=' + rows[i].id,
										cache : false
										});
									}
								}
								} ]
								});
							</script>
						</div>
					</div>
				</div>
			</dl>
		</div>
	</form>
</div>