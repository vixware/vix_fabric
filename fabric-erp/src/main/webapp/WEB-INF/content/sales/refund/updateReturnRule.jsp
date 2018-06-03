<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
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
 
$("#returnRuleForm").validationEngine();
function saveOrUpdateReturnRule(){
	if($('#returnRuleForm').validationEngine('validate')){
		$.post('${vix}/sales/refund/returnRuleAction!saveOrUpdate.action',
			{'returnRule.id':$("#id").val(),
			  'returnRule.rrType':$(":radio[name=rrType][checked]").val(),
			  'returnRule.customerAccount.id':$("#customerAccountId").val(),
			  'returnRule.item.id':$("#itemId").val(),
			  'returnRule.lowerSaleCount':$("#lowerSaleCount").val(),
			  'returnRule.ratio':$("#ratio").val(),
			  'returnRule.detailType':$(":radio[name=detailType][checked]").val()
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/sales/refund/returnRuleAction!goList.action');
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
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function chooseItem(){
	$.ajax({
		  url:'${vix}/mdm/item/itemAction!goChooseItem.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 960,
					height : 580,
					title:"选择${vv:varView('vix_mdm_item')}",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != ''){
								var r = returnValue.split(",");
								$("#itemId").val(r[0]);
								$("#itemCode").val(r[1]);
								$("#itemName").val(r[2]);
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

function rrTypeChange(){
	if($(":radio[name=rrType][checked]").val() != ''){
		if($(":radio[name=rrType][checked]").val() == 'customerAccount'){
			$("#customerBtn").attr("style","");
			$("#customerName").removeAttr("disabled");
			$("#itemBtn").attr("style","visibility:hidden");
			$("#itemName").attr("disabled","disabled");
			return;
		}else{
			$("#customerBtn").attr("style","visibility:hidden");
			$("#customerName").attr("disabled","disabled");
			$("#itemBtn").attr("style","");
			$("#itemName").removeAttr("disabled");
		}
	}
}

//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
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
				<li><a href="###"><img src="${vix}/common/img/sale/saleQuotes.png" alt="" />
					<s:text name="cmn_home" /></a></li>
				<li><a href="###">销售管理</a></li>
				<li><a href="###">返款</a></li>
				<li><a href="###" onclick="loadContent('${vix}/sales/refund/returnRuleAction!goList.action');">返款规则</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${returnRule.id}" />
<div class="content">
	<form id="returnRuleForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateReturnRule();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/sales/refund/returnRuleAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="returnRule.code != null ">${returnRule.rrName}</s:if> <s:else>新增返款规则</s:else>
					</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table>
										<tr>
											<td align="right" width="15%">规则类型:</td>
											<td width="35%"><s:if test="returnRule.rrType == 'customerAccount'">
													<input type="radio" id="rrType1" name="rrType" onchange="rrTypeChange();" value="item" />${vv:varView('vix_mdm_item')}
												<input type="radio" id="rrType2" name="rrType" onchange="rrTypeChange();" value="customerAccount" class="validate[required] text-input" checked="checked" />客户
											</s:if> <s:elseif test="returnRule.rrType == 'item'">
													<input type="radio" id="rrType1" name="rrType" onchange="rrTypeChange();" value="item" class="validate[required] text-input" checked="checked" />${vv:varView('vix_mdm_item')}
												<input type="radio" id="rrType2" name="rrType" onchange="rrTypeChange();" value="customerAccount" class="validate[required] text-input" />客户
											</s:elseif> <s:else>
													<input type="radio" id="rrType1" name="rrType" onchange="rrTypeChange();" value="item" class="validate[required] text-input" />${vv:varView('vix_mdm_item')}
												<input type="radio" id="rrType2" name="rrType" onchange="rrTypeChange();" value="customerAccount" class="validate[required] text-input" />客户
											</s:else> <span style="color: red;">*</span></td>
											<td align="right" width="10%">明细计算类型:</td>
											<td width="40%"><s:if test="returnRule.detailType == 'money'">
													<input type="radio" id="detailType1" name="detailType" value="count" />数量
												<input type="radio" id="detailType2" name="detailType" value="money" class="validate[required] text-input" checked="checked" />金额
											</s:if> <s:elseif test="returnRule.detailType == 'count'">
													<input type="radio" id="detailType1" name="detailType" value="count" class="validate[required] text-input" checked="checked" />数量
												<input type="radio" id="detailType2" name="detailType" value="money" class="validate[required] text-input" />金额
											</s:elseif> <s:else>
													<input type="radio" id="detailType1" name="detailType" value="count" class="validate[required] text-input" />数量
												<input type="radio" id="detailType2" name="detailType" value="money" class="validate[required] text-input" />金额
											</s:else> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">客户名称:</td>
											<td><input id="customerName" name="returnRule.customerAccount.name" value="${returnRule.customerAccount.name}" type="text" size="30" /> <input id="customerAccountId" name="customerAccountId" value="${returnRule.customerAccount.id}" type="hidden" /> <span><a id="customerBtn" class="abtn" href="#"
													onclick="chooseCustomerAccount();"><span>选择</span></a></span></td>
											<td align="right">${vv:varView('vix_mdm_item')}:&nbsp;</td>
											<td><input id="itemName" value="${returnRule.item.name}" type="text" size="30" /> <input id="itemId" type="hidden" value="${returnRule.item.id}" /> <span><a id="itemBtn" class="abtn" style="cursor: pointer;" onclick="chooseItem();"><span>选择</span></a></span></td>
										</tr>
										<tr>
											<td align="right">最低销售数量:</td>
											<td><input id="lowerSaleCount" name="returnRule.lowerSaleCount" value="${returnRule.lowerSaleCount}" type="text" size="30" /></td>
											<td align="right">返款比率:</td>
											<td><input id="ratio" name="returnRule.ratio" value="${returnRule.ratio}" type="text" size="30" />%</td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
					<div class="right_menu">
						<ul>
							<li class="current"><a href="###"><img alt="" src="img/mail.png">返款规则明细</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; height: 300px; background: #FFF; z-index: 1;">
						<div style="padding: 8px;">
							<table id="returnRuleItem" class="easyui-datagrid" style="height: auto; margin: 2px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#returnRuleItemTb',url: '${vix}/sales/refund/returnRuleItemAction!getReturnRuleItemJson.action?id=${returnRule.id}'">
								<thead>
									<tr>
										<th data-options="field:'id',width:80,align:'center'">编号</th>
										<th data-options="field:'from',width:180,align:'center',editor:'numberbox'">从</th>
										<th data-options="field:'to',width:180,align:'center',editor:'numberbox'">到</th>
										<th data-options="field:'ratio',width:140,align:'center',editor:'numberbox'">返款比率</th>
										<th data-options="field:'unit',width:140,align:'center',editor:'text'">计量单位</th>
									</tr>
								</thead>
							</table>
							<div id="returnRuleItemTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addReturnRuleItem(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-edit',plain:true" onclick="updateReturnRuleItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left"><s:text name="cmn_update" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true"
									onclick="removeReturnRuleItem()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
								$('#returnRuleItem').datagrid();
								function removeReturnRuleItem(){
									var row = $('#returnRuleItem').datagrid('getSelected');
									if(row){
										asyncbox.confirm('是否删除该明细信息?','提示信息',function(action){
											if(action == 'ok'){
												var index = $('#returnRuleItem').datagrid('getRowIndex',row);
												$('#returnRuleItem').datagrid('deleteRow', index);
												$.ajax({
													  url:'${vix}/sales/refund/returnRuleItemAction!deleteById.action?id='+row.id,
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
								function updateReturnRuleItem(){
									var row = $('#returnRuleItem').datagrid('getSelected');
									if(row){
										addReturnRuleItem(row.id);
									}else{
										showMessage("请选择一条数据!");
									}
								}
								function addReturnRuleItem(id){
									$.ajax({
										  url:'${vix}/sales/refund/returnRuleItemAction!goSaveOrUpdate.action?id='+id,
										  cache: false,
										  success: function(html){
												asyncbox.open({
												 	modal:true,width : 720,height : 240,title:"返款规则明细",html:html,
													callback : function(action){
														if(action == 'ok'){
															if($('#returnRuleItemForm').validationEngine('validate')){
																$.post('${vix}/sales/refund/returnRuleItemAction!saveOrUpdate.action',
																	 {'returnRuleItem.id':$("#returnRuleItemId").val(),
																	  'returnRuleItem.returnRule.id':$("#id").val(),
																      'returnRuleItem.from':$("#from").val(),
																	  'returnRuleItem.to':$("#to").val(),
																	  'returnRuleItem.unit':$("#unit").val(),
																	  'returnRuleItem.ratio':$("#ratio").val(),
																	  'returnRuleItem.currencyType.id':$("#currencyTypeId").val(),
																	  'returnRuleItem.ratioType':$("#ratioType").val()
																	},
																	function(result){
																		asyncbox.success(result,"<s:text name='vix_message'/>",function(){
																			$('#returnRuleItem').datagrid("load");
																		});
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
				</dd>
			</dl>
		</div>
	</form>
</div>
<!-- content -->
