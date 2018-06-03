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
	var orderItemStr = "";
	/** 送货地址 */
	var dlData = $("#dlAddress").datagrid("getRows");
	var dlJson = JSON.stringify(dlData);
	/** 发运计划 */
	var dpData = $("#deliveryPlan").datagrid("getRows");
	var dpJson = JSON.stringify(dpData);
	if($('#order').validationEngine('validate')){
		$.post('${vix}/sales/forecast/forecastAction!saveOrUpdate.action',
			{'forecast.id':$("#id").val(),
			  'forecast.name':$("#name").val(),
			  'forecast.title':$("#title").val(),
			  'forecast.billDate':$("#billDate").val(),
			  'forecast.status':$(":radio[name=status][checked]").val(),
			  'forecast.customerAccount.id':$("#customerAccountId").val(),
			  'forecast.bizType':$("#bizType").val(),
			  'forecast.currency':$("#currency").val(),
			  'forecast.salePerson':$("#salePerson").val(),
			  'forecast.saleOrg':$("#saleOrg").val(),
			  'forecast.deliveryTimeInPlan':$("#deliveryTimeInPlan").val(),
			  'forecast.promiseTime':$("#promiseTime").val(),
			  'forecast.amount':$("#amount").val(),
			  'forecast.deliveryTime':$("#deliveryTime").val(),
			  'orderItemStr':orderItemStr,
			  'updateField':updateField,
			  'dlJson':dlJson,
			  'dpJson':dpJson
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/sales/forecast/forecastAction!goList.action?menuId=menuOrder');
			}
		);
	}
}
$("#order").validationEngine();
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
function forecastFieldChanged(input){
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
				<li><a href="#"><img src="${vix}/common/img/sale/forecast.png" alt="" />
					<s:text name="cmn_home" /></a></li>
				<li><a href="#">供应链</a></li>
				<li><a href="#">销售管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/forecast/forecastAction!goList.action');">预测定义</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${forecast.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"><img width="22" height="22" alt="禁用"
							src="${vix}/common/img/dt_submit.png" /></a> <a href="#"><img width="22" height="22" alt="取消" src="${vix}/common/img/dt_cancelback.png" /></a> <a href="#" onclick="loadContent('${vix}/sales/forecast/forecastModelAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="forecast.name != null ">
							${forecast.name}
						</s:if> <s:else>新增预测定义</s:else>
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
											<td align="right" width="15%">名称:</td>
											<td width="35%"><input id="name" name="name" value="${forecast.name}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right" width="10%">状态:</td>
											<td width="40%"><input id="status" name="status" value="${forecast.status}" onchange="forecastFieldChanged(this);" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">描述:</td>
											<td><input id="description" name="description" value="${forecast.description}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">时间:</td>
											<td><input id="time" name="time" value="${forecast.time}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
			</dl>
		</div>
		<!--oder-->
	</form>
</div>
<!-- content -->
<script type="text/javascript">
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