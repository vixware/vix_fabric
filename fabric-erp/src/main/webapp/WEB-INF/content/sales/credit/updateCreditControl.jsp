<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript">
<!--
	$(".newvoucher dt b").click(function(){
		$(this).toggleClass("downup");
		$(this).parent("dt").next("dd").toggle();
	});
	/** input 获取焦点input效果绑定  */
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});
 
$("#creditControlForm").validationEngine();
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
function saveOrUpdateSaleCredit(){
	if($('#creditControlForm').validationEngine('validate')){
		$.post('${vix}/sales/credit/creditControlAction!saveOrUpdate.action',
			{'creditControl.id':$("#id").val(),
			  'creditControl.customerAccount.id':$("#customerAccountId").val(),
			  'creditControl.ccName':$("#ccName").val(),
			  'creditControl.billType':$("#billType").val(),
			  'creditControl.bizType':$("#bizType").val(),
			  'creditControl.saleOrg.id':$("#saleOrgId").val()
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/sales/credit/creditControlAction!goList.action');
			}
		);
	}
}

function chooseCustomerAccount(){
	$.ajax({
		  url:'${vix}/mdm/crm/customerAccountAction!goChooseCustomerAccount.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 960,
					height : 500,
					title:"选择客户",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(":");
								$("#customerAccountId").val(result[0]);
								$("#customerName").val(result[1]);
							}else{
								$("#customerAccountId").val("");
								$("#customerName").val("");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

function chooseSaleOrg(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action?canCheckComp=0',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择销售组织",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var resObj = $.parseJSON(returnValue);
								var uId = resObj[0].realId;
								$("#saleOrgId").val(uId);
								$("#saleOrgName").val(resObj[0].name);
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
//-->
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/credit.png" alt="" />
					<s:text name="cmn_home" /></a></li>
				<li><a href="#">销售管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/credit/creditControlAction!goList.action');">信用管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${creditControl.id}" />
<div class="content">
	<form id="creditControlForm">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateSaleCredit();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/sales/credit/creditControlAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="creditControl.id > 0">
							${creditControl.customerAccount.name}
						</s:if> <s:else>
							新增信用规则
						</s:else>
					</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<table>
							<tr>
								<td width="15%" align="right">客户:</td>
								<td width="35%"><input id="customerName" name="salesOrder.customerAccount.name" value="${creditControl.customerAccount.name}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span> <input type="hidden" id="customerAccountId" name="customerAccountId" value="${creditControl.customerAccount.id}" />
									<span><a class="abtn" href="#" onclick="chooseCustomerAccount();"><span>选择</span></a></span></td>
								<td width="10%" align="right">信用控制名称:</td>
								<td width="40%"><input id="ccName" name="creditControl.ccName" value="${creditControl.ccName}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr>
								<td align="right">单据类型:</td>
								<td><input id="billType" name="creditControl.billType" value="${creditControl.billType}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								<td align="right">业务类型:</td>
								<td><input id="bizType" name="creditControl.bizType" value="${creditControl.bizType}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr>
								<td align="right">销售组织:</td>
								<td colspan="3"><input type="hidden" id="saleOrgId" value="${creditControl.saleOrg.id}" /> <input id="saleOrgName" name="creditControl.saleOrg.fullName" value="${creditControl.saleOrg.fullName}" type="text" size="30" class="validate[required] text-input" /> <a class="abtn" href="#" onclick="chooseSaleOrg();"><span>选择</span></a></td>
							</tr>
						</table>
					</div>
				</dd>
				<div class="clearfix" style="background: #FFF; position: relative;">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:$('#a1').attr('style','');tab(5,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />信用管理明细</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="creditControlItem" class="easyui-datagrid" style="height: 380px; margin: 6px;" data-options="fitColumns:true,singleSelect: true,toolbar: '#creditControlItemTb',url: '${vix}/sales/credit/creditControlItemAction!getCreditControlItemJson.action?id=${creditControl.id}'">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:60">编号</th>
										<th data-options="field:'ccSubject',width:150,align:'center'">控制主体</th>
										<th data-options="field:'ccType',width:120,align:'center'">控制类型</th>
										<th data-options="field:'creditLevel',width:120,align:'center'">风险等级</th>
										<th data-options="field:'creditValue',width:120,align:'center'">控制值</th>
										<th data-options="field:'creditMethod',width:120,align:'center'">控制方法</th>
										<th data-options="field:'isValid',width:120,align:'center'">是否生效</th>
									</tr>
								</thead>
							</table>
							<div id="creditControlItemTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addItem(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-edit',plain:true" onclick="updateItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left"><s:text name="cmn_update" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeItem()"><span
									class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
							$('#creditControlItem').datagrid();
							function removeItem(){
								var row = $('#creditControlItem').datagrid('getSelected');
								if(row){
									asyncbox.confirm('是否删除该信用明细?','提示信息',function(action){
										if(action == 'ok'){
											var index = $('#creditControlItem').datagrid('getRowIndex',row);
											$('#creditControlItem').datagrid('deleteRow', index);
											$.ajax({
												  url:'${vix}/sales/credit/creditControlItemAction!deleteById.action?id='+row.id,
												  cache: false,
												  success: function(html){
													  showMessage(html);
												  }
											});
										}
									});
								}else{
									showMessage("请选择一条数据!");
								}
							}
							function updateItem(){
								var row = $('#creditControlItem').datagrid('getSelected');
								if(row){
									addItem(row.id);
								}else{
									showMessage("请选择一条数据!");
								}
							}
							function addItem(id){
								$.ajax({
									  url:'${vix}/sales/credit/creditControlItemAction!goSaveOrUpdate.action?id='+id,
									  cache: false,
									  success: function(html){
										  asyncbox.open({
											 	modal:true,
											 	width : 740,
												height : 240,
												title:"添加控制条件",
												html:html,
												callback : function(action){
													if(action == 'ok'){
														if($('#creditControlItemForm').validationEngine('validate')){
															$.post('${vix}/sales/credit/creditControlItemAction!saveOrUpdate.action',
																	{'creditControlItem.id':$("#cciId").val(),
																	  'creditControlItem.ccSubject':$("#ccSubject").val(),
																	  'creditControlItem.ccType':$("#ccType").val(),
																	  'creditControlItem.creditLevel':$("#creditLevel").val(),
																	  'creditControlItem.creditLevel':$("#creditLevel").val(),
																	  'creditControlItem.creditValue':$("#creditValue").val(),
																	  'creditControlItem.creditMethod':$("#creditMethod").val(),
																	  'creditControlItem.isValid':$(":radio[name=isValid][checked]").val(),
																	  'creditControlItem.creditControl.id':$("#id").val()
																	},
																	function(result){
																		showMessage(result);
																		$('#creditControlItem').datagrid("reload");
																	}
																);
														}else{
										  					return false;
										  				}
													}
												},
												btnsbar : $.btn.OKCANCEL
											});
									  }
								});
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
