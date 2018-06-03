
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<link href="${vix}/common/css/easyui/themes/gray/easyui.css" rel="stylesheet" />
<link href="${vix}/common/css/easyui/themes/icon.css" rel="stylesheet" />
<script src="${vix}/common/js/jquery.easyui.min.js" type="text/javascript"></script>

<script type="text/javascript">
/** 页面加载 */
$(function(){
	//设置业务类型选中
	$("#bizType").val('${salesInvoice.bizType}');
	//设置单据类型选中
	$("#formType").val('${salesInvoice.formType}');
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
 
function resize(){
	$('#test').datagrid('resize', {
		width:700,
		height:400
	});
}
function getSelected(){
	var selected = $('#test').datagrid('getSelected');
	if (selected){
		alert(selected.code+":"+selected.name+":"+selected.addr+":"+selected.col4);
	}
}
function getSelections(){
	var ids = [];
	var rows = $('#test').datagrid('getSelections');
	for(var i=0;i<rows.length;i++){
		ids.push(rows[i].code);
	}
	alert(ids.join(':'));
}
function clearSelections(){
	$('#test').datagrid('clearSelections');
}
function selectRow(){
	$('#test').datagrid('selectRow',2);
}
function selectRecord(){
	$('#test').datagrid('selectRecord','002');
}
function unselectRow(){
	$('#test').datagrid('unselectRow',2);
}
function mergeCells(){
	$('#test').datagrid('mergeCells',{
		index:2,
		field:'addr',
		rowspan:2,
		colspan:2
	});
} 
//提示
if($('input.sweet-tooltip').length){
	$('input.sweet-tooltip').focus(function() {
		tooltip				= $(this);
		tooltipText 		= tooltip.attr('data-text-tooltip');
		tooltipClassName	= 'tooltip';
		tooltipClass		= '.' + tooltipClassName;
		
		if(tooltip.hasClass('showed-tooltip')) return false;
		
		tooltip.addClass('showed-tooltip')
				 .after('<div class="'+tooltipClassName+'"><div class="tooltip_l"></div><div class="tooltip_r"></div><div class="tooltip_x">'+tooltipText+'</div><div class="tooltip_b"></div></div>');

		
		tooltipPosTop 	= tooltip.position().top - $(tooltipClass).outerHeight() - 10;
		tooltipPosLeft = tooltip.position().left;
		tooltipPosLeft = tooltipPosLeft - (($(tooltipClass).outerWidth()/2) - tooltip.outerWidth()/2);
		
		$(tooltipClass).css({ 'left': tooltipPosLeft, 'top': tooltipPosTop })
							.animate({'opacity':'1', 'marginTop':'0'}, 500);
		
	}).focusout(function() {
		
		$(tooltipClass).animate({'opacity':'0', 'marginTop':'-10px'}, 500, function() {
				
			$(this).remove();
			tooltip.removeClass('showed-tooltip');
				
		});
	});
	JT_init();
}
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
 
pager("start","${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id="+$("#id").val(),'orderUpdate');
function currentPager(tag){
	pager(tag,"${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id="+$("#id").val(),'orderUpdate');
}
/** 保存销售发票 */
function saveOrUpdateOrder(){
	var orderItemStr = "";
	/** 明细 */
	var iiData = $("#dlLineItem").datagrid("getRows");
	var iiJson = JSON.stringify(iiData);
	if($('#salesInvoiceForm').validationEngine('validate')){
		$.post('${vix}/sales/invoice/salesInvoiceAction!saveOrUpdate.action',
				{
					'salesInvoice.id':$("#id").val(),
					'salesInvoice.code':$("#code").val(),
					'salesInvoice.name':$("#name").val(),
					'salesInvoice.formType':$("#formType").val(),
					'salesInvoice.bizType':$("#bizType").val(),
					'salesInvoice.customerName':$("#customerName").val(),
					'salesInvoice.payCondition':$("#payCondition").val(),
					'salesInvoice.amount':$("#amount").val(),
					'salesInvoice.tax':$("#tax").val(),
					'salesInvoice.deliveryTime':$("#deliveryTime").val(),
					'salesInvoice.createTime':$("#createTime").val(),
					'orderItemStr':orderItemStr,
					'iiJson':iiJson
				},
				function(result){
					showMessage(result);
					setTimeout("",1000);
					loadContent('${vix}/sales/invoice/salesInvoiceAction!goList.action?menuId=menuOrder');
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
$("#salesInvoiceForm").validationEngine();
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
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/drp_channel.png" alt="" />
					<s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#"><s:text name="sa_salesmanage" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/invoice/salesInvoiceAction!goList.action');">销售发票</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${salesInvoice.id }" />
<div class="content">
	<form id="salesInvoiceForm">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateOrder();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_savenew.png" /></a> <a href="#"
						onclick="loadContent('${vix}/sales/invoice/salesInvoiceAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="salesInvoice.name != null ">${salesInvoice.name}</s:if> <s:else>新增销售普通发票</s:else>
					</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
										<s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
										<s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
										<s:text name="calculator" /></a>
									</span> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">发票编码：</td>
											<td><input id="code" name="code" value="${salesInvoice.code }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">发票主题：</td>
											<td><input id="name" name="name" value="${salesInvoice.code }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">单据类型：</td>
											<td><select id="formType" name="formType" style="width: 50" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">类型1</option>
													<option value="2">类型2</option>
											</select><span style="color: red;">*</span></td>
											<td align="right">业务类型：</td>
											<td><select id="bizType" name="bizType" style="width: 50">
													<option value="">请选择</option>
													<option value="1">类型1</option>
													<option value="2">类型2</option>
											</select></td>
										</tr>
										<tr>
											<td align="right">客户名称：</td>
											<td><input id="customerName" name="salesInvoice.customerAccount.name" value="${salesInvoice.customerAccount.name}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span> <input type="hidden" id="customerAccountId" name="customerAccountId" value="${salesInvoice.customerAccount.id}" /> <span><a
													class="abtn" href="#" onclick="chooseCustomerAccount();"><span>选择</span></a></span></td>
											<td align="right">付款条件：</td>
											<td><input id="payCondition" name="payCondition" value="${salesInvoice.payCondition }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">金额：</td>
											<td><input id="amount" name="amount" value="${salesInvoice.amount }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">税率：</td>
											<td><input id="tax" name="tax" value="${salesInvoice.tax }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">交货日期：</td>
											<td><input id="deliveryTime" name="deliveryTime" value="<fmt:formatDate value='${salesInvoice.deliveryTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('deliveryTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">单据日期：</td>
											<td><input id="createTime" name="createTime" value="<fmt:formatDate value='${salesInvoice.createTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" class="validate[required] text-input" /> <img onclick="showTime('createTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"><span style="color: red;">*</span></td>
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
							<li class="current"><a onclick="javascript:tab(8,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />发票明细</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div class="right_content">
							<script type="text/javascript">
	          				function saveOrUpdateSalesInvoiceItem(id){
								$.ajax({
									  url:'${vix}/sales/refund/salesInvoiceItemAction!goSaveOrUpdate.action?id='+id,
									  cache: false,
									  success: function(html){
											asyncbox.open({
											 	modal:true,width : 720,height : 340,title:"返款规则明细",html:html,
												callback : function(action){
													if(action == 'ok'){
														if($('#salesInvoiceItemForm').validationEngine('validate')){
															$.post('${vix}/sales/refund/salesInvoiceItemAction!saveOrUpdate.action',
																 {'salesInvoiceItem.id':$("#salesInvoiceItemId").val(),
																  'salesInvoiceItem.salesInvoice.id':$("#id").val(),
															      'salesInvoiceItem.from':$("#from").val(),
																  'salesInvoiceItem.to':$("#to").val(),
																  'salesInvoiceItem.unit':$("#unit").val(),
																  'salesInvoiceItem.ratio':$("#ratio").val(),
																  'salesInvoiceItem.currencyType.id':$("#currencyTypeId").val(),
																  'salesInvoiceItem.ratioType':$("#ratioType").val()
																},
																function(result){
																	asyncbox.success(result,"<s:text name='vix_message'/>",function(){
																		pager("start","${vix}/sales/refund/salesInvoiceItemAction!goListContent.action?salesInvoiceId="+$("#id").val(),"salesInvoiceItem");
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
	          				function salesInvoiceItemOrderBy(orderField){
								loadQuoteSubject();
								var orderBy = $("#salesInvoiceItemOrderBy").val();
								if(orderBy == 'desc'){
									orderBy = "asc";
								}else{
									orderBy = 'desc';
								}
								pager("start","${vix}/sales/refund/salesInvoiceItemAction!goListContent.action?salesInvoiceId="+$("#id").val()+"&orderField="+orderField+"&orderBy="+orderBy ,'salesInvoiceItem');
							}
							function salesInvoiceItemPager(tag){
								pager(tag,"${vix}/sales/refund/salesInvoiceItemAction!goListContent.action?salesInvoiceId="+$("#id").val(),'salesInvoiceItem');
							}
							pager("start","${vix}/sales/refund/salesInvoiceItemAction!goListContent.action?salesInvoiceId="+$("#id").val(),"salesInvoiceItem");
						</script>
							<div class="right_btn">
								<span><a onclick="saveOrUpdateSalesInvoiceItem(0);" href="###"><img src="img/address_book.png" alt=""></a></span>
							</div>
							<div class="pagelist drop clearfix">
								<strong><s:text name="cmn_selected" />:<span id="selectSalesInvoiceItemCount2">0</span></strong>
								<div>
									<span><a class="start" onclick="salesInvoiceItemPager('start');"></a></span> <span><a class="previous" onclick="salesInvoiceItemPager('previous');"></a></span> <em>(<b class="salesInvoiceItemInfo"></b> <s:text name='cmn_to' /> <b class="salesInvoiceItemTotalCount"></b>)
									</em> <span><a class="next" onclick="salesInvoiceItemPager('next');"></a></span> <span><a class="end" onclick="salesInvoiceItemPager('end');"></a></span>
								</div>
							</div>
							<div id="salesInvoiceItem" class="table"></div>
							<div class="pagelist drop clearfix">
								<strong><s:text name="cmn_selected" />:<span id="selectSalesInvoiceItemCount2">0</span></strong>
								<div>
									<span><a class="start" onclick="salesInvoiceItemPager('start');"></a></span> <span><a class="previous" onclick="salesInvoiceItemPager('previous');"></a></span> <em>(<b class="salesInvoiceItemInfo"></b> <s:text name='cmn_to' /> <b class="salesInvoiceItemTotalCount"></b>)
									</em> <span><a class="next" onclick="salesInvoiceItemPager('next');"></a></span> <span><a class="end" onclick="salesInvoiceItemPager('end');"></a></span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</dl>
		</div>
	</form>
</div>
<!-- content -->