<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<link href="${vix}/common/css/newservice.css" rel="stylesheet" />
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script type="text/javascript">
<!--
	/** input 获取焦点input效果绑定  */
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});
 
/** 保存订单 */
function saveOrUpdateOrder(){
	if($('#expensesForm').validationEngine('validate')){
		$.post('${vix}/sales/payment/expensesAction!saveOrUpdate.action',
			{'expenses.id':$("#id").val(),
			  'expenses.expensesDate':$("#expensesDate").val(),
			  'expenses.expensesBillNumber':$("#expensesBillNumber").val(),
			  'expenses.invoice':$("#invoice").val(),
			  'expenses.customerAccount.id':$("#customerAccountId").val(),
			  'expenses.saleOrg':$("#saleOrg").val(),
			  'expenses.employee.id':$("#employeeId").val(),
			  'expenses.currencyType.id':$("#currencyTypeId").val(),
			  'expenses.memo':$("#memo").val()
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/sales/payment/expensesAction!goList.action?menuId=menuOrder');
			}
		);
	}
}
$("#expensesForm").validationEngine();
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
var updateField = "";
function expensesFieldChanged(input){
	updateField+= $(input).attr("id")+",";
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
$(".newvoucher dt b").click(function(){
	$(this).toggleClass("downup");
	$(this).parent("dt").next("dd").toggle();
});

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
				<li><a href="#"><img src="${vix}/common/img/sale/disbursementCost.png" alt="" />
					<s:text name="cmn_home" /></a></li>
				<li><a href="#">供应链</a></li>
				<li><a href="#">销售管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/payment/expensesAction!goList.action');">费用支出</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${expenses.id}" />
<div class="content">
	<form id="expensesForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/sales/payment/expensesAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="expenses.code != null ">
							${expenses.code}
						</s:if> <s:else>新增费用支出</s:else>
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
									<table class="addtable_c">
										<tr>
											<td align="right" width="15%">支出日期:</td>
											<td width="35%"><input id="expensesDate" name="expensesDate" value="<s:property value='formatDateToString(expenses.expensesDate)'/>" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span><img onclick="showTime('expensesDate','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif"
												width="16" height="22" align="absmiddle"></td>
											<td align="right" width="10%">支出单号:</td>
											<td width="40%"><input id="expensesBillNumber" name="expensesBillNumber" value="${expenses.expensesBillNumber}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">发票号:</td>
											<td><input id="invoice" name="invoice" value="${expenses.invoice}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right" width="10%">客户名称:</td>
											<td width="40%"><input id="customerName" name="expenses.customerAccount.name" value="${expenses.customerAccount.name}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span> <input type="hidden" id="customerAccountId" name="customerAccountId" value="${expenses.customerAccount.id}" /> <span><a
													class="abtn" href="#" onclick="chooseCustomerAccount();"><span>选择</span></a></span></td>
										</tr>
										<tr>
											<td align="right">销售部门:</td>
											<td><input id="saleOrg" name="saleOrg" value="${expenses.saleOrg}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">业务员:</td>
											<td><input type="hidden" id="employeeId" value="${expenses.employee.id}" /> <input id="employeeName" name="employeeName" value="${expenses.employee.name}" type="text" size="30" /> <span><a class="abtn" href="#" onclick="chooseEmployee();"><span>选择</span></a></span></td>
										</tr>
										<tr>
											<td align="right">币种:</td>
											<td><s:select id="currencyTypeId" headerKey="-1" headerValue="--请选择--" list="%{currencyTypeList}" listValue="name" listKey="id" value="%{disbursementCost.currencyType.id}" multiple="false" theme="simple"></s:select></td>
											<td align="right">备注:</td>
											<td><input id="memo" name="memo" value="${expenses.memo}" type="text" size="30" /></td>
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
							<li class="current"><a href="#"><img src="${vix}/common/img/mail.png" alt="" />明细</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="expensesDetail" class="easyui-datagrid" style="height: 380px; margin: 6px;" data-options="fitColumns:true,singleSelect: true,toolbar: '#expensesDetailTb',url: '${vix}/sales/payment/expensesAction!getExpensesDetailJson.action?id=${expenses.id}'">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:60">编号</th>
										<th data-options="field:'expensesItem',width:150,align:'center'">费用项目</th>
										<th data-options="field:'amount',width:120,align:'center'">代垫金额</th>
										<th data-options="field:'goodsName',width:120,align:'center'">存货名称</th>
										<th data-options="field:'specification',width:120,align:'center'">规格</th>
									</tr>
								</thead>
							</table>
							<div id="expensesDetailTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addItem(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-edit',plain:true" onclick="updateItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left"><s:text name="cmn_update" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeItem()"><span
									class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
							$('#expensesDetail').datagrid();
							function removeItem(){
								var row = $('#expensesDetail').datagrid('getSelected');
								if(row){
									asyncbox.confirm('是否删除该代垫明细?','提示信息',function(action){
										if(action == 'ok'){
											var index = $('#expensesDetail').datagrid('getRowIndex',row);
											$('#expensesDetail').datagrid('deleteRow', index);
											$.ajax({
												  url:'${vix}/sales/payment/expensesAction!deleteById.action?id='+row.id,
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
								var row = $('#expensesDetail').datagrid('getSelected');
								if(row){
									addItem(row.id);
								}else{
									showMessage("请选择一条数据!");
								}
							}
							function addItem(id){
								$.ajax({
									  url:'${vix}/sales/payment/expensesDetailAction!goSaveOrUpdate.action?id='+id,
									  cache: false,
									  success: function(html){
										  asyncbox.open({
											 	modal:true,
												width : 660,
												height : 360,
												title:"添加明细",
												html:html,
												callback : function(action){
													if(action == 'ok'){
														if($('#expensesDetailForm').validationEngine('validate')){
															$.post('${vix}/sales/payment/expensesDetailAction!saveOrUpdate.action',
																	{'expensesDetail.id':$("#dcdId").val(),
																	  'expensesDetail.expensesItem':$("#expensesItem").val(),
																	  'expensesDetail.goodsName':$("#goodsName").val(),
																	  'expensesDetail.amount':$("#amount").val(),
																	  'expensesDetail.specification':$("#specification").val(),
																	  'expensesDetail.expenses.id':$("#id").val()
																	},
																	function(result){
																		showMessage(result);
																		setTimeout("",1000);
																		$('#expensesDetail').datagrid("reload");
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
		<!--oder-->
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
		$('#a1 .right_btn,#a3 .right_btn').fix({'advance':38,'top':38});
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