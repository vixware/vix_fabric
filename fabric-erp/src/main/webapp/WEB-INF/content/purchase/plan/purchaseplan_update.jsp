<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	
	$("#purchasePlanForm").validationEngine();
	
	/** 保存采购计划 */
	function saveOrUpdatePlan(tag){
		if($('#purchasePlanForm').validationEngine('validate')){
			$.post('${vix}/purchase/purchasePlanAction!saveOrUpdate.action',
			{
				'purchasePlan.id':$("#id").val(),
				'purchasePlan.supplier.id':$("#supplierId").val(),
				'purchasePlan.purchasePlanPackage.id':$("#purchasePlanPackageId").val(),
				'purchasePlan.purchasePlanCode':$("#purchasePlanCode").val(),
				'purchasePlan.purchasePlanName':$("#purchasePlanName").val(),
				'purchasePlan.style':$("#bizStyle").val(),
				'purchasePlan.status':$("#status").val(),
				'purchasePlan.creator':$("#creator").val(),
				'purchasePlan.planTime':$("#planTime").val(),
				'purchasePlan.createTime':$("#createTime").val(),
				'purchasePlan.description':$("#description").val(),
				'purchasePlan.amount':$("#amount").val(),
				'purchasePlan.price':$("#price").val(),
				'updateField' : updateField
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				if (tag == 1) {
				loadContent('${vix}/purchase/purchasePlanAction!goList.action');
				} else {
					saveOrUpdate(0);
				}
			}
		 );
		}
	};
	
	/** 选择单选供应商 */
	function chooseRadioSupplier(){
		$.ajax({
			  url:'${vix}/purchase/purchasePlanAction!goChooseRadioSupplier.action',
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 1000,
						height : 500,
						title:"选择供应商",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									var rv = returnValue.split(",");
									$("#supplierId").val(rv[0]);
									$("#supplierName").val(rv[1]);
								}else{
									$("#supplierName").val(rv[1]);
									asyncbox.success("请选择分类信息!","提示信息");
								}
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	};
	var map = {"1":"d1", "2":"d2", "3":"d3"};
	
	$("#bizStyle").bind("change", function(){
		var divId = map[this.value];
		$("#"+divId).show().siblings().hide();
	}); 
	
	function initdata(){
		
		//设置单据类型选中(修改)
		$("#bizStyle").val('${purchasePlan.style}');
		if(${null == purchasePlan.createTime }){
			var myDate = new Date();
			$("#createTime").val(myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate());
		}
		
		if('${purchasePlan.style}' == "1"){
			$("#d1").show().siblings().hide();
			$('#planTime').val('${purchasePlan.planTime}');
		}else if ('${purchasePlan.style}' == "2"){
			$("#d2").show().siblings().hide();  
			$('#planTime').val('${purchasePlan.planTime}');
		}else if ('${purchasePlan.style}' == "3"){
			$("#d3").show().siblings().hide();  
			$('#planTime').val('${purchasePlan.planTime}');
		}
	};
	
	initdata();
	
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/pur_plan.png" alt="" /> <s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#"><s:text name="pur_purchase_manage" /></a></li>
				<a href="#" onclick="loadContent('${vix}/purchase/purchasePlanAction!goList.action');"><s:text name='pur_purchase_plan' /></a>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${purchasePlan.id }" />
<input type="hidden" id="status" name="status" value="${purchasePlan.status }" />
<input type="hidden" id="purchasePlanPackageId" name="purchasePlanPackageId" value="${purchasePlan.purchasePlanPackage.id }" />
<div class="content">
	<form id="purchasePlanForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdatePlan(1);" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#" onclick="saveOrUpdatePlan(2);"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"><img width="22" height="22"
							title="保存并提交审批" src="${vix}/common/img/dt_submit.png" /></a> <a onclick="loadContent('${vix}/purchase/purchasePlanAction!goList.action?pageNo=${pageNo}');" href="#"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong> <b>新增采购计划</b> <i></i>
					</strong>
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
											<td align="right">编码：</td>
											<td><input id="purchasePlanCode" name="purchasePlanCode" value="${purchasePlan.purchasePlanCode }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td align="right">名称：</td>
											<td><input id="purchasePlanName" name="purchasePlanName" value="${purchasePlan.purchasePlanName }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">计划类型：</td>
											<td><select id="bizStyle" name="style" style="width: 50" class="validate[required] text-input" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<option value="1">年计划</option>
													<option value="2">季计划</option>
													<option value="3">月计划</option>
											</select><span style="color: red;">*</span></td>
											<td align="right">计划时间：</td>
											<td><div id="d1">
													<input id="planTime" name="planTime" type="text" style="width: 200px;" onClick="WdatePicker({dateFmt:'yyyy'})" value="<s:date format="yyyy-MM-dd" name="%{purchasePlan.planTime}" />" />
												</div>
												<div id="d2" style="display: none;">
													<input id="planTime" name="planTime" type="text" style="width: 200px;" onClick="WdatePicker({dateFmt:'yyyy年M季度', isQuarter:true, isShowOK:false,disabledDates:['....-0[5-9]-..','....-1[0-2]-..'], startDate:'%y-01-01' })" value="<s:date format="yyyy-MM-dd" name="%{purchasePlan.planTime}" />" />
												</div>
												<div id="d3" style="display: none;">
													<input id="planTime" name="planTime" type="text" style="width: 200px;" onClick="WdatePicker({dateFmt:'yyyy-MM'})" value="<s:date format="yyyy-MM-dd" name="%{purchasePlan.planTime}" />" />
												</div></td>
										</tr>
										<tr>
											<td align="right">供应商名称：</td>
											<td><input id="supplierName" name="supplierName" value="${purchasePlan.supplier.name  }" type="text" size="30" onchange="fieldChanged(this);" /><input type="hidden" id="supplierId" name="supplierId" value="${purchasePlan.supplier.id }" /> <input class="btn" type="button" value="选择" onclick="chooseRadioSupplier();" /></td>
										</tr>
										<tr>
											<td align="right">说明：</td>
											<td colspan="3"><textarea id="description" name="description" class="example" rows="1" style="width: 600px; height: 128px;" onchange="fieldChanged(this);">${purchasePlan.description }</textarea></td>
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
							$('#soAttach')
									.datagrid({
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
										$
												.ajax({
												url : '${vix}/purchase/purchasePlanAction!addAttachments.action',
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
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td width="15%" align="right">创建人：</td>
											<td width="35%"><input id="creator" name="creator" value="${purchasePlan.creator }" type="text" size="30" class="validate[required] text-input" /onchange="fieldChanged(this);"><span style="color: red;">*</span></td>
											<td width="15%" align="right">创建时间：</td>
											<td width="35%"><input id="createTime" name="createTime" value="<fmt:formatDate value='${purchasePlan.createTime }' type='both' pattern='yyyy-MM-dd hh:mm:ss' />" type="text" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span> <img
												onclick="showTime('createTime','yyyy-MM-dd hh:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th>数量：</th>
											<td><input id="amount" name="amount" value="${purchasePlan.amount }" type="text" /></td>
											<th>采购金额：</th>
											<td><input id="price" name="price" value="${purchasePlan.price }" type="text" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>
