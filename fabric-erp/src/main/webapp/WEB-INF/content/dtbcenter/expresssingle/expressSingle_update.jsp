<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdateExpressSingle() {
		if ($('#expressSingleform').validationEngine('validate')) {
			$.post('${vix}/dtbcenter/expressSingleAction!saveOrUpdate.action', {
			'expressSingle.id' : $("#id").val(),
			'expressSingle.senderCustomerAccountNo' : $("#senderCustomerAccountNo").val(),
			'expressSingle.senderFromCompany' : $("#senderFromCompany").val(),
			'expressSingle.senderContactPerson' : $("#senderContactPerson").val(),
			'expressSingle.senderAddress' : $("#senderAddress").val(),
			'expressSingle.senderAreaCode' : $("#senderAreaCode").val(),
			'expressSingle.senderTel' : $("#senderTel").val(),
			'expressSingle.senderMsg' : $("#senderMsg").val(),
			'expressSingle.receiverCustomerAccountNo' : $("#receiverCustomerAccountNo").val(),
			'expressSingle.receiverToCompany' : $("#receiverToCompany").val(),
			'expressSingle.receiverContactPerson' : $("#receiverContactPerson").val(),
			'expressSingle.receiverAddress' : $("#receiverAddress").val(),
			'expressSingle.receiverAreaCode' : $("#receiverAreaCode").val(),
			'expressSingle.receiverTel' : $("#receiverTel").val(),
			'expressSingle.goodsDescription' : $("#goodsDescription").val(),
			'expressSingle.goodsQuantity' : $("#goodsQuantity").val(),
			'expressSingle.goodsLength' : $("#goodsLength").val(),
			'expressSingle.goodsWidth' : $("#goodsWidth").val(),
			'expressSingle.goodsHeight' : $("#goodsHeight").val(),
			'expressSingle.creditCardNo' : $("#creditCardNo").val(),
			'expressSingle.amount' : $("#amount").val(),
			'expressSingle.returnReceiptService' : $("#returnReceiptService").val(),
			'expressSingle.returnTracking' : $("#returnTracking").val(),
			'expressSingle.fuelSurcharge' : $("#fuelSurcharge").val(),
			'expressSingle.eveningCollectionCharge' : $("#eveningCollectionCharge").val(),
			'expressSingle.authorizationPickupService' : $("#authorizationPickupService").val(),
			'expressSingle.deliveryUponNotice' : $("#deliveryUponNotice").val(),
			'expressSingle.packageCharge' : $("#packageCharge").val(),
			'expressSingle.specialoffer' : $("#specialoffer").val(),
			'expressSingle.specialDelivery' : $("#specialDelivery").val(),
			'expressSingle.otherPersonalizedServices' : $("#otherPersonalizedServices").val(),
			'expressSingle.declaredValue' : $("#declaredValue").val(),
			'expressSingle.chargeValue' : $("#chargeValue").val(),
			'expressSingle.amountOfPieces' : $("#amountOfPieces").val(),
			'expressSingle.chargedWeight' : $("#chargedWeight").val(),
			'expressSingle.freight' : $("#freight").val(),
			'expressSingle.actualWeight' : $("#actualWeight").val(),
			'expressSingle.totalCharge' : $("#totalCharge").val(),
			'expressSingle.origin' : $("#origin").val(),
			'expressSingle.des' : $("#des").val(),
			'expressSingle.selfPickup' : $("#selfPickup").val(),
			'expressSingle.selfSend' : $("#selfSend").val(),
			'expressSingle.shipper' : $("#shipper").val(),
			'expressSingle.consignee' : $("#consignee").val(),
			'expressSingle.thirdParty' : $("#thirdParty").val(),
			'expressSingle.monthlyPaymentNo' : $("#monthlyPaymentNo").val(),
			'expressSingle.thirdPartyAreaCode' : $("#thirdPartyAreaCode").val(),
			'expressSingle.pickupBy' : $("#pickupBy").val(),
			'expressSingle.deliveredBy' : $("#deliveredBy").val(),
			'expressSingle.mailingDate' : $("#mailingDate").val(),
			'expressSingle.signature' : $("#signature").val(),
			'expressSingle.receivedDate' : $("#receivedDate").val(),
			'expressSingle.sameDay' : $("#sameDay").val(),
			'expressSingle.cargo' : $("#cargo").val(),
			'expressSingle.fourDay' : $("#fourDay").val(),
			'expressSingle.alabel' : $("#alabel").val(),
			'updateField' : updateField,
			'expressSingle.groundDelivery' : $("#groundDelivery").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/dtbcenter/expressSingleAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#expressSingleform").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/receiveorders.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="ldm_dtbcenter_management" /> </a></li>
				<li><a href="#"><s:text name="ldm_order_receiving" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/expressSingleAction!goList.action');">快递单管理 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${expressSingle.id}" />
<div class="content">
	<form id="expressSingleform">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateExpressSingle()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /> </a> <a href="#" onclick="loadContent('${vix}/dtbcenter/expressSingleAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>快递单信息</b> </strong>
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
											<th width="15%">寄件人编码：</th>
											<td width="35%"><input id="senderCustomerAccountNo" name="senderCustomerAccountNo" value="${expressSingle.senderCustomerAccountNo }" type="text" size="30" onchange="fieldChanged(this);"></td>
											<th width="15%">寄件公司：</th>
											<td width="35%"><input id="senderFromCompany" name="senderFromCompany" value="${expressSingle.senderFromCompany }" type="text" size="30" onchange="fieldChanged(this);"></td>
										</tr>
										<tr>
											<th>寄件联络人：</th>
											<td><input id="senderContactPerson" name="senderContactPerson" value="${expressSingle.senderContactPerson}" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<th>寄件地址：</th>
											<td><input id="senderAddress" name="senderAddress" value="${expressSingle.senderAddress}" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<th>区号：</th>
											<td><input id="senderAreaCode" name="senderAreaCode" value="${expressSingle.senderAreaCode}" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">寄件人联系电话：</td>
											<td><input id="senderTel" name="senderTel" type="text" value="${expressSingle.senderTel }" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">签收短信通知：</td>
											<td><input id="senderMsg" name="senderMsg" type="text" value="${expressSingle.senderMsg }" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">收件人编码：</td>
											<td><input id="receiverCustomerAccountNo" name="receiverCustomerAccountNo" type="text" value="${expressSingle.receiverCustomerAccountNo }" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">收件公司：</td>
											<td><input id="receiverToCompany" name="receiverToCompany" type="text" value="${expressSingle.receiverToCompany }" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">收件人：</td>
											<td><input id="receiverContactPerson" name="receiverContactPerson" type="text" value="${expressSingle.receiverContactPerson }" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">收件人地址：</td>
											<td><input id="receiverAddress" name="receiverAddress" type="text" value="${expressSingle.receiverAddress }" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">收件人区号：</td>
											<td><input id="receiverAreaCode" name="receiverAreaCode" type="text" value="${expressSingle.receiverAreaCode }" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">收件人固定电话：</td>
											<td><input id="receiverTel" name="receiverTel" type="text" value="${expressSingle.receiverTel }" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">件数：</td>
											<td><input id="amountOfPieces" name="amountOfPieces" type="text" value="${expressSingle.amountOfPieces }" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">计费重量：</td>
											<td><input id="chargedWeight" name="chargedWeight" type="text" value="${expressSingle.chargedWeight }" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">运费：</td>
											<td><input id="freight" name="freight" type="text" value="${expressSingle.freight }" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">实际重量：</td>
											<td><input id="actualWeight" name="actualWeight" type="text" value="${expressSingle.actualWeight }" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">费用合计：</td>
											<td><input id="totalCharge" name="totalCharge" type="text" value="${expressSingle.totalCharge }" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">原寄地：</td>
											<td><input id="origin" name="origin" type="text" value="${expressSingle.origin }" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">目的地：</td>
											<td><input id="des" name="des" type="text" value="${expressSingle.des }" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">自取件：</td>
											<td><input id="selfPickup" name="selfPickup" type="text" value="${expressSingle.selfPickup }" size="30" onchange="fieldChanged(this);" /></td>
										</tr>

										<tr>
											<td align="right">收件员：</td>
											<td><input id="pickupBy" name="pickupBy" type="text" value="${expressSingle.pickupBy }" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">派件员：</td>
											<td><input id="deliveredBy" name="deliveredBy" type="text" value="${expressSingle.deliveredBy }" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">寄件日期：</td>
											<td><input id="mailingDate" name="mailingDate" value="${expressSingle.mailingDate}" type="text" readonly="readonly" onchange="fieldChanged(this);" /> <img onclick="showTime('mailingDate','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">收件人签名：</td>
											<td><input id="signature" name="signature" type="text" value="${expressSingle.signature }" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">收件日期：</td>
											<td><input id="receivedDate" name="receivedDate" value="${expressSingle.receivedDate}" type="text" readonly="readonly" /> <img onclick="showTime('receivedDate','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <span style="display: none;"> </span> <strong>附加业务类型</strong>
								</dt>
								<dd style="display: none;">
									<table style="border: none;">
										<tr>
											<td align="right" width="15%">代收贷款卡号：</td>
											<td width="35%"><input id="creditCardNo" name="creditCardNo" type="text" value="${expressSingle.creditCardNo }" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right" width="15%">金额：</td>
											<td width="35%"><input id="amount" name="amount" type="text" value="${expressSingle.amount }" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">签回单：</td>
											<td><input id="returnReceiptService" name="returnReceiptService" type="text" value="${expressSingle.returnReceiptService }" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">回单号:</td>
											<td><input id="returnTracking" name="returnTracking" type="text" value="${expressSingle.returnTracking }" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">燃油附加费：</td>
											<td><input id="fuelSurcharge" name="fuelSurcharge" type="text" value="${expressSingle.fuelSurcharge }" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">夜晚收件费用：</td>
											<td><input id="eveningCollectionCharge" name="eveningCollectionCharge" type="text" value="${expressSingle.eveningCollectionCharge }" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">委托件费：</td>
											<td><input id="authorizationPickupService" name="authorizationPickupService" type="text" value="${expressSingle.authorizationPickupService }" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">等通知派送费用：</td>
											<td><input id="deliveryUponNotice" name="deliveryUponNotice" type="text" value="${expressSingle.deliveryUponNotice }" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">包装费：</td>
											<td><input id="packageCharge" name="packageCharge" type="text" value="${expressSingle.packageCharge }" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">特殊保价费用：</td>
											<td><input id="specialoffer" name="specialoffer" type="text" value="${expressSingle.specialoffer }" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">特殊配送：</td>
											<td><input id="specialDelivery" name="specialDelivery" type="text" value="${expressSingle.specialDelivery }" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">其他个性化服务费用：</td>
											<td><input id="otherPersonalizedServices" name="otherPersonalizedServices" type="text" value="${expressSingle.otherPersonalizedServices }" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">声明保价物品价值：</td>
											<td><input id="declaredValue" name="declaredValue" type="text" value="${expressSingle.declaredValue }" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">普通保价费用：</td>
											<td><input id="chargeValue" name="chargeValue" type="text" value="${expressSingle.chargeValue }" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <span style="display: none;"> </span> <strong>其他信息</strong>
								</dt>
								<dd style="display: none;">
									<table style="border: none;">
										<tr>
											<td align="right" width="15%">自寄件：</td>
											<td width="35%"><input id="selfSend" name="selfSend" type="text" value="${expressSingle.selfSend }" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right" width="15%">寄方付：</td>
											<td width="35%"><input id="shipper" name="shipper" type="text" value="${expressSingle.shipper }" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">收方付：</td>
											<td><input id="consignee" name="consignee" type="text" value="${expressSingle.consignee }" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">第三方付：</td>
											<td><input id="thirdParty" name="thirdParty" type="text" value="${expressSingle.thirdParty }" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">月结账号：</td>
											<td><input id="monthlyPaymentNo" name="monthlyPaymentNo" type="text" value="${expressSingle.monthlyPaymentNo }" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">第三方付款地区：</td>
											<td><input id="thirdPartyAreaCode" name="thirdPartyAreaCode" type="text" value="${expressSingle.thirdPartyAreaCode }" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">普货：</td>
											<td><input id="cargo" name="cargo" type="text" value="${expressSingle.cargo }" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">四日件：</td>
											<td><input id="fourDay" name="fourDay" type="text" value="${expressSingle.fourDay }" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">A标：</td>
											<td><input id="alabel" name="alabel" type="text" value="${expressSingle.alabel }" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">陆运：</td>
											<td><input id="groundDelivery" name="groundDelivery" type="text" value="${expressSingle.groundDelivery }" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">即日到：</td>
											<td><input id="sameDay" name="sameDay" type="text" value="${expressSingle.sameDay }" size="30" onchange="fieldChanged(this);" /></td>
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
									title : '明细',
									modal : true,
									width : 724,
									height : 400,
									html : html,
									callback : function(action, returnValue) {
										if (action == 'ok') {
											if ($('#orderItemForm').validationEngine('validate')) {
												$.post('${vix}/dtbcenter/expressSingleAction!saveOrUpdateExpressSingleDetail.action', {
												'id' : $("#id").val(),
												'wayBillItem.id' : $("#oiId").val(),
												'wayBillItem.itemCode' : $("#itemCode").val(),
												'wayBillItem.specification' : $("#specification").val(),
												'wayBillItem.price' : $("#price").val(),
												'wayBillItem.amount' : $("#amount").val(),
												'wayBillItem.unit' : $("#unit").val()
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
							$('#dlAddress2')
									.datagrid({
									url : '${vix}/dtbcenter/expressSingleAction!getExpressSingleDetailJson.action?id=${expressSingle.id}',
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
									title : '编码',
									width : 100,
									align : 'center'
									}, {
									field : 'itemCode',
									title : '名称',
									width : 100,
									align : 'center'
									} ] ],
									columns : [ [ {
									field : 'specification',
									title : '规格型号',
									width : 100,
									align : 'center'
									}, {
									field : 'unit',
									title : '单位',
									width : 100,
									align : 'center'
									}, {
									field : 'amount',
									title : '数量',
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
									field : 'netTotal',
									title : '金额',
									width : 100,
									align : 'right',
									editor : 'numberbox',
									required : 'true'
									} ] ],
									toolbar : [ {
									id : 'da2Btnadd',
									text : '添加',
									iconCls : 'icon-add',
									handler : function() {
										$('#btnsave').linkbutton('enable');
										saveDeliveryAddress('${vix}/dtbcenter/expressSingleAction!goSaveOrUpdateExpressSingleDetail.action');
									}
									}, '-', {
									id : 'btnedit',
									text : '修改',
									iconCls : 'icon-edit',
									handler : function() {
										var row = $('#dlAddress2').datagrid("getSelected"); //获取你选择的所有行
										if (row) {
											saveDeliveryAddress('${vix}/dtbcenter/expressSingleAction!goSaveOrUpdateExpressSingleDetail.action?id=' + row.id);
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
											url : '${vix}/dtbcenter/expressSingleAction!deleteExpressSingleDetailById.action?id=' + rows[i].id,
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