<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script src="${vix}/common/core/js/all.js" type="text/javascript"></script>
<script type="text/javascript">
/** input 获取焦点input效果绑定  */
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});
	var updateField = "";
	function salesOrderFieldChanged(input){
		updateField+= $(input).attr("id")+",";
	}

	$(".newvoucher dt b").click(function(){
		$(this).toggleClass("downup");
		$(this).parent("dt").next("dd").toggle();
	});
	
	$(".addtable .addtable_t").click(function(){
		$(this).toggleClass("addtable_td");
		$(this).next(".addtable_c").toggle(); 
	});
	$(".newvoucher dt b").click(function(){
		$(this).toggleClass("downup");
		$(this).parent("dt").next("dd").toggle();
	});
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});
	
	$("#orderForm").validationEngine();
	if($(".ms").length>0){
		$(".ms").hover(function(){
			$(">ul",this).stop().slideDown(100);
		},function(){
			$(">ul",this).stop().slideUp(100);
		});
		$(".ms ul li").hover(function(){
			$(">a",this).addClass("hover");
			$(">ul",this).stop().slideDown(100);
		},function(){
			$(">a",this).removeClass("hover");
			$(">ul",this).stop().slideUp(100);
		});
	}
	
	/** 保存来料检验单 */
	function saveOrUpdateOrder(){
		/**检验项目  */
		var dlData = $("#dlLineItem").datagrid("getRows");
		var dlJson = JSON.stringify(dlData);
		/** 来料检验审批 */
		var spData = $("#dlInvoice").datagrid("getRows");
		var spJson = JSON.stringify(spData);
		
		if($('#orderForm').validationEngine('validate')){
			$.post('${vix}/qm/defectiveProductsAction!saveOrUpdate.action',
				{
				'defectiveProducts.id' : $("#id").val(),
				'defectiveProducts.receiptNumber':$("#receiptNumber").val(),
				'defectiveProducts.scrappedHandle':$("#scrappedHandle").val(),
				'defectiveProducts.returnProcessing':$("#returnProcessing").val(),
				'defectiveProducts.operator':$("#operator").val(),
				'defectiveProducts.inspectionDate':$("#inspectionDate").val(),
				'defectiveProducts.itemId':$("#itemId").val(),
				'defectiveProducts.itemName':$("#itemName").val(),
				'defectiveProducts.itemCode':$("#itemCode").val(),
				'defectiveProducts.participant':$("#participant").val(),
				'defectiveProducts.departmentName':$("#departmentName").val(),
				'defectiveProducts.testMethods.id':$("#testMethodsId").val(),
				'defectiveProducts.inspectionCharacteristics.id':$("#inspectionCharacteristicsId").val(),
				'defectiveProducts.inspectionStatus.id':$("#inspectionStatusId").val(),
				'dlJson' : dlJson,	
				'spJson' : spJson
				},
				function(result){
					showMessage(result);
					setTimeout("",1000);
					loadContent('${vix}/qm/defectiveProductsAction!goList.action?menuId=menuContract');
				}
			);
		}
	}
	
	/**选择${vv:varView("vix_mdm_item")}*/
	function chooseItem(){
		$.ajax({
			  url:'${vix}/mdm/item/itemAction!goChooseItem.action',
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
					 	width : 960,
						height : 580,
						title:"选择${vv:varView("vix_mdm_item")}",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != ''){
									var i = returnValue.split(",");
									$("#itemId").val(i[0]);
									$("#itemCode").val(i[1]);
									$("#itemName").val(i[2]);
								}else{
									asyncbox.success("请选择${vv:varView("vix_mdm_item")}!","<s:text name='vix_message'/>");
									return false;
								}
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	
	
	/** 选择部门*/
	 function chooseParentOrganization(){
		 $.ajax({
			  url:'${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action',
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 560,
						height : 340,
						title:"选择部门",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									//alert(returnValue);
									var selectIds = "";
									var selectNames = "";
								
									var resObj = $.parseJSON(returnValue);
									var pubIdTmp = $("#pubIds").val();
									
									pubIdTmp = pubIdTmp + ",";
									
									/* if(resObj.length == 0 ){
										return;
									} */
									//debugger;
									for(var i=0;i<resObj.length;i++){
										//alert(resObj[i].treeId+"--"+resObj[i].name);
										if(resObj[i].treeType!="O"){
											asyncbox.alert("只能选择部门信息！","提示");
											return;
										} 
										if(!pubIdTmp.contains(resObj[i].treeId+",")){
											selectIds += "," + resObj[i].treeId;
											selectNames += "," + resObj[i].name;
										}
									}
									selectIds = $("#pubIds").val()+selectIds;
									selectNames = $("#departmentName").val()+selectNames;
									
									$("#pubIds").val(selectIds);
									selectNames = selectNames.substring(1,selectNames.length);
									$("#departmentName").val(selectNames);
								}
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	
	/**报检人*/
	 function chooseEmployees(){
			$.ajax({
				  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
				  data:{chkStyle:"checkbox"},
				  cache: false,
				  success: function(html){
					  asyncbox.open({
						 	modal:true,
							width : 800,
							height : 600,
							title:"报检人",
							html:html,
							callback : function(action,returnValue){
								if(action == 'ok'){
									if(returnValue != undefined){
										var selectIds = "";
										var selectNames = "";
										var result = returnValue.split(",");
										for (var i=0; i<result.length; i++){
											if(result[i].length>1){
												var v = result[i].split(":");
												
												selectIds += "," + v[0];
												selectNames = v[1];
											}
										}
										$("#participant").val(selectNames);
									}
									
								}
							},
							btnsbar : $.btn.OKCANCEL
						});
				  }
			});
		}
	
	/**操作人*/
	 function chooseEmployees1(){
			$.ajax({
				  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
				  data:{chkStyle:"checkbox"},
				  cache: false,
				  success: function(html){
					  asyncbox.open({
						 	modal:true,
							width : 800,
							height : 600,
							title:"操作人",
							html:html,
							callback : function(action,returnValue){
								if(action == 'ok'){
									if(returnValue != undefined){
										var selectIds = "";
										var selectNames = "";
										var result = returnValue.split(",");
										for (var i=0; i<result.length; i++){
											if(result[i].length>1){
												var v = result[i].split(":");
												
												selectIds += "," + v[0];
												selectNames = v[1];
											}
										}
										$("#operator").val(selectNames);
									}
									
								}
							},
							btnsbar : $.btn.OKCANCEL
						});
				  }
			});
		}
	 
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/qm_quality_management.png" alt="" /> <s:text name="supplyChain" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/qm/defectiveProductsAction!goList.action?pageNo=${pageNo}');"><s:text name="qm_quality_management" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/qm/defectiveProductsAction!goList.action?pageNo=${pageNo}');"><s:text name="qm_incoming_inspection" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/qm/defectiveProductsAction!goList.action?pageNo=${pageNo}');"><s:text name="来料不良品处理单" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/qm/defectiveProductsAction!goList.action?pageNo=${pageNo}');"><s:text name="add" /></a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${defectiveProducts.id}" />
<div class="content">
	<form id="orderForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder();"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <%-- <a href="#"><img width="22" height="22" title="保存并提交审批" src="${vix}/common/img/dt_submit.png" /></a> 
							 --%> <a href="#" onclick="loadContent('${vix}/qm/defectiveProductsAction!goList.action?pageNo=${pageNo}');"> <img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="新增来料报检单" /> </b><i></i> </strong>
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
										<tr height="40">
											<th align="right">${vv:varView("vix_mdm_item")}编码:&nbsp;</th>
											<td><input id="itemCode" name="defectiveProducts.itemCode" value="${defectiveProducts.itemCode}" type="text" class="validate[required] text-input" /> <input id="itemId" type="hidden" /> <span style="color: red;">*</span> <input class="btn" type="button" value="选择" onclick="chooseItem();" /></td>
											<th align="right">${vv:varView("vix_mdm_item")}名称:&nbsp;</th>
											<td><input id="itemName" name="defectiveProducts.itemName" value="${defectiveProducts.itemName}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
										</tr>
										<td align="right">单据编号：</td>
										<td><input id="receiptNumber" name="receiptNumber" value="${defectiveProducts.receiptNumber}" type="text" size="30" class="validate[required] text-input" /></td>
										<td align="right">抽样标准：</td>
										<td><s:select id="testMethodsId" headerKey="-1" headerValue="--请选择--" list="%{testMethodsList}" listValue="criterion" listKey="id" value="%{defectiveProducts.testMethods.id}" multiple="false" theme="simple">
											</s:select></td>
										</tr>
										<tr>
											<td align="right">报废处理：</td>
											<td><input id="scrappedHandle" name="scrappedHandle" value="${defectiveProducts.scrappedHandle}" type="text" size="30" class="validate[required] text-input" /></td>
											<td align="right">退货处理：</td>
											<td><input id="returnProcessing" name="returnProcessing" value="${defectiveProducts.returnProcessing}" type="text" size="30" class="validate[required] text-input" /></td>
										</tr>
										<tr>
											<td align="right">报检人：</td>
											<td><input id="participant" name="parentId" value="${defectiveProducts.participant}" type="text" class="validate[required] text-input" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseEmployees();" /></td>
											<td align="right">检验方法：</td>
											<td><s:select id="testMethodsId" headerKey="-1" headerValue="--请选择--" list="%{testMethodsList}" listValue="name" listKey="id" value="%{defectiveProducts.testMethods.id}" multiple="false" theme="simple">
												</s:select></td>
										</tr>
										<tr>
											<td align="right">检验部门：</td>
											<td><input type="hidden" id="pubIds" name="pubIds" value="${pubIds}" /> <input type="hidden" id="treeType" name="treeType" value="${treeType}" /> <input type="text" id="departmentName" name="departmentName" readonly="readonly" value="${defectiveProducts.departmentName}" /> <input class="btn" type="button" value="选择"
												onclick="chooseParentOrganization();" /></td>
											<td align="right">样品等级：</td>
											<td><s:select id="inspectionCharacteristicsId" headerKey="-1" headerValue="--请选择--" list="%{inspectionCharacteristicsList}" listValue="name" listKey="id" value="%{defectiveProducts.inspectionCharacteristics.id}" multiple="false" theme="simple">
												</s:select></td>
										</tr>
										<tr>
											<td align="right">检验状态：</td>
											<td><s:select id="inspectionStatusId" headerKey="-1" headerValue="--请选择--" list="%{inspectionStatusList}" listValue="name" listKey="id" value="%{defectiveProducts.inspectionStatus.id}" multiple="false" theme="simple">
												</s:select></td>
											<td align="right">日期：</td>
											<td><input id="inspectionDate" name="defectiveProducts.inspectionDate" value="${defectiveProducts.inspectionDate}" onclick="showTime('inspectionDate','yyyy-MM-dd')" onchange="salesOrderFieldChanged(this);" type="text" size="30" class="validate[required] text-input" /> <img onclick="showTime('inspectionDate','yyyy-MM-dd')"
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
							<li class="current"><a onclick="javascript:tab(3,1,'a',event)"> <img src="${vix}/common/img/mail.png" alt="" />检验项目
							</a></li>
							<li><a onclick="javascript:$('#a2').attr('style','');tab(3,2,'a',event);"> <img src="${vix}/common/img/attachment.png" alt="" />附件
							</a></li>
							<li><a onclick="javascript:$('#a3').attr('style','');tab(3,3,'a',event);"> <img src="${vix}/common/img/mail.png" alt="" />审批
							</a></li>
						</ul>

					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="dlLineItem" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlLineItemTb',url: '${vix}/qm/defectiveProductsAction!getIncoming.action?id=${defectiveProducts.id}',onClickRow: onDlClickRow">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:120">序号</th>
										<th data-options="field:'inspectionPm',align:'center',width:250,editor:'text'">检验项目</th>
										<th data-options="field:'receiptNumber',width:420,align:'center',editor:'text'">技术要求</th>
										<th data-options="field:'inspectionResult',width:120,align:'center',editor:'text'">检验结果</th>
										<th data-options="field:'remark',width:120,align:'center',editor:'text'">备注</th>
										<!-- <th data-options="field:'inspectionDate',width:120,align:'center',editor:'datebox'">检验日期</th> -->
									</tr>
								</thead>
							</table>
							<div id="dlLineItemTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="appendDlLineItem()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-edit',plain:true" onclick="removeDlLineItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true"
									onclick="saveDlLineItem()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_save" /></span></span></a>
							</div>
							<script type="text/javascript">
							$('#dlLineItem').datagrid();
							var editIndexDlLineItem = undefined;
							function endDlEditing(){
								if (editIndexDlLineItem == undefined){return true;}
								if ($('#dlLineItem').datagrid('validateRow', editIndexDlLineItem)){
									$('#dlLineItem').datagrid('endEdit', editIndexDlLineItem);
									editIndexDlLineItem = undefined;
									return true;
								} else {
									return false;
								}
							}
							function onDlClickRow(index){
								if (editIndexDlLineItem != index){
									if (endDlEditing()){
										$('#dlLineItem').datagrid('selectRow', index).datagrid('beginEdit', index);
										editIndexDlLineItem = index;
									} else {
										$('#dlLineItem').datagrid('selectRow', editIndexDlLineItem);
									}
								}
							}
							function appendDlLineItem(){
								if (endDlEditing()){
									$('#dlLineItem').datagrid('appendRow',{status:'P'});
									editIndexDlLineItem = $('#dlLineItem').datagrid('getRows').length-1;
									$('#dlLineItem').datagrid('selectRow', editIndexDlLineItem).datagrid('beginEdit', editIndexDlLineItem);
								}
							}
							function removeDlLineItem(){
								if (editIndexDlLineItem == undefined){return;}
								var row = $('#dlLineItem').datagrid('getSelected');
								$.ajax({url:'${vix}/qm/defectiveProductsAction!deleteIncomingLineById.action?id='+row.id,cache: false});
								$('#dlLineItem').datagrid('cancelEdit', editIndexDlLineItem)
										.datagrid('deleteRow', editIndexDlLineItem);
								editIndexDlLineItem = undefined;
							}
							function saveDlLineItem(){
								if (endDlEditing()){
									$('#dlLineItem').datagrid('acceptChanges');
								}
							}
					</script>
						</div>
					</div>

					<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<script type="text/javascript">
						$('#soAttach').datagrid({
							url: '${vix}/qm/defectiveProductsAction!getIncomingTemplate.action?id=${defectiveProducts.id}',
							title: '报检单附件',
							width: 'auto',
							height: '450',
							fitColumns: true,
							columns:[[
								{field:'id',title:'序号',width:80},
								{field:'saleOrderCode',title:'主题',width:80},
								{field:'detail',title:'路径',width:80},
								{field:'deliveryTime',title:'创建时间',width:80},
								{field:'itemCode',title:'下载',width:80},
							]],
							toolbar:[{
								id:'saBtnadd',
								text:'新增',
								iconCls:'icon-add',
								handler:function(){
									$('#btnsave').linkbutton('enable');
									$.ajax({
										  url:'${vix}/qm/defectiveProductsAction!addAttachFile.action',
										  cache: false,
										  success: function(html){
											  asyncbox.open({
												 	modal:true,
													width : 364,
													height : 160,
													title:"上传附件",
													html:html,
													callback : function(action,returnValue){
														if(action == 'ok'){
															uploadFile('${vix}/qm/defectiveProductsAction!uploadAttachment.action?id=${defectiveProducts.id}','fileToUpload');
															$('#soAttach').datagrid("reload");
														}
													},
													btnsbar : $.btn.OKCANCEL
												});
										  }
									});
								}
							},'-',{
								text:'删除',
								iconCls:'icon-remove',
								handler:function(){
									var rows = $('#soAttach').datagrid("getSelections");	//获取你选择的所有行	
									//循环所选的行
									for(var i =0;i<rows.length;i++){
										var index = $('#soAttach').datagrid('getRowIndex',rows[i]);//获取某行的行号
										$('#soAttach').datagrid('deleteRow',index);	//通过行号移除该行
										$.ajax({url:'${vix}/contract/contractDraftingAction!!deleteAttachFile.action?afId='+rows[i].id,cache: false});
									}
								}
							}]
						});
					</script>
						<div style="padding: 8px;">
							<table id="soAttach"></table>
						</div>
					</div>
					<div id="a3" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="dlInvoice" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlInvoiceTb',url: '${vix}/qm/defectiveProductsAction!getIncoming1.action?id=${defectiveProducts.id}',onClickRow: onDlClickRow">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:120">序号</th>
										<th data-options="field:'pmName',width:405,align:'center',editor:'text'">项目名称</th>
										<th data-options="field:'approvalPeople',width:150,align:'center',editor:'text'">审批人</th>
										<!-- <th data-options="field:'approvalDate',width:150,align:'center',editor:'datebox'">审批时间</th> -->
										<th data-options="field:'approvalResult',width:120,align:'center',editor:'text'">审批结果</th>
									</tr>
								</thead>
							</table>
							<div id="dlInvoiceTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="appendDlInvoice()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-remove',plain:true" onclick="removeDlInvoice()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-save',plain:true"
									onclick="saveDlInvoice()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span></a>
							</div>
							<script type="text/javascript">
							$('#dlInvoice').datagrid();
							var editIndexDlInvoice = undefined;
							function endDlEditing(){
								if (editIndexDlInvoice == undefined){return true;}
								if ($('#dlInvoice').datagrid('validateRow', editIndexDlInvoice)){
									$('#dlInvoice').datagrid('endEdit', editIndexDlInvoice);
									editIndexDlInvoice = undefined;
									return true;
								} else {
									return false;
								}
							}
							function onDlClickRow(index){
								if (editIndexDlInvoice != index){
									if (endDlEditing()){
										$('#dlInvoice').datagrid('selectRow', index).datagrid('beginEdit', index);
										editIndexDlInvoice = index;
									} else {
										$('#dlInvoice').datagrid('selectRow', editIndexDlInvoice);
									}
								}
							}
							function appendDlInvoice(){
								if (endDlEditing()){
									$('#dlInvoice').datagrid('appendRow',{status:'P'});
									editIndexDlInvoice = $('#dlInvoice').datagrid('getRows').length-1;
									$('#dlInvoice').datagrid('selectRow', editIndexDlInvoice).datagrid('beginEdit', editIndexDlInvoice);
								}
							}
							function removeDlInvoice(){
								if (editIndexDlInvoice == undefined){return;}
								var row = $('#dlInvoice').datagrid("getSelected");
								$.ajax({url:'${vix}/qm/defectiveProductsAction!deleteApplicationFormById.action?id='+row.id,cache: false});
								$('#dlInvoice').datagrid('cancelEdit', editIndexDlInvoice)
										.datagrid('deleteRow', editIndexDlInvoice);
								editIndexDlInvoice = undefined;
							}
							function saveDlInvoice(){
								if (endDlEditing()){
									$('#dlInvoice').datagrid('acceptChanges');
								}
							}
					</script>
						</div>
					</div>
				</div>
			</dl>
		</div>
	</form>
</div>
<!-- content -->
<script type="text/javascript">
$(function(){
	$.fn.fix = function(options){
		var defaults = {
			'advance' : 0,
			'top' : 0
		}
		options = $.extend(defaults, options);
		return this.each(function(){
			var $_this = $(this);
			$_this.wrap('<div></div>');
			var wp = $_this.parent('div');
			wp.height(wp.height());
			var ofst = wp.offset();
			
			if(!$_this.is(':visible') && $(window).scrollTop() + options.advance > $_this.offset().top){
				$_this.css({'position':'fixed','z-index':9999,'top': options.top,'width':$_this.width()});
			}
			$(window).scroll(function(){
				if(!$_this.is(':visible')){
					return ;
				}
				
				if($(window).scrollTop() + options.advance > wp.offset().top){
					$_this.css({'position':'fixed','z-index':9999,'top': options.top,'width':$_this.width()});
				}else{
					$_this.css({'position':'static','z-index':'auto','top': 'auto','width':'auto'});
				}
			});
		});
	}
	$('#a1 .right_btn,#a6 .right_btn').fix({'advance':38,'top':38});
});

	function tabs(title,content,style){
		$(title).click(function(){
			$(title).removeClass(style);
			$(this).addClass(style);
			$(content).hide();
			$(content+':eq('+$(title).index($(this))+')').show();
		});
	}
	
	$(window).scroll(function(){
		if($('#orderTitleFixd').parent('dl').offset().top - 43 < $(window).scrollTop()){
			if(!$('#orderTitleFixd').hasClass('fixed')){
				$('#orderTitleFixd').addClass('fixed');
				$('#orderTitleFixd').parent('dl').css('padding-top',30);
			}
		}else{
			$('#orderTitleFixd').removeClass('fixed');
			$('#orderTitleFixd').parent('dl').css('padding-top',0);
		}
	});

</script>