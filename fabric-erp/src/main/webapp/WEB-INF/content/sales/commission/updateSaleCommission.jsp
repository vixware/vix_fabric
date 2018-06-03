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
 
$("#saleCommissionForm").validationEngine();
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
function saveOrUpdateSaleCommission(){
	if($('#saleCommissionForm').validationEngine('validate')){
		$.post('${vix}/sales/commission/saleCommissionAction!saveOrUpdate.action',
			{'saleCommission.id':$("#id").val(),
			  'saleCommission.salesMan.id':$("#salesManId").val(),
			  'saleCommission.orderCode':$("#orderCode").val(),
			  'saleCommission.orderAmount':$("#orderAmount").val(),
			  'saleCommission.orderTime':$("#orderTime").val(),
			  'saleCommission.conmmission':$("#conmmission").val(),
			  'saleCommission.commissionRate':$("#commissionRate").val()
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/sales/commission/saleCommissionAction!goList.action');
			}
		);
	}
}
function chooseEmployee(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectEmpAction!goList.action?tag=choose',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 960,
					height : 500,
					title:"选择职员",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(":");
								$("#saleManId").val(result[0]);
								$("#saleManName").val(result[1]);
							}else{
								$("#saleManId").val("");
								$("#saleManName").val("");
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
				<li><a href="#"><img src="img/drp_channel.png" alt="" />
					<s:text name="cmn_home" /></a></li>
				<li><a href="#">销售管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/commission/saleCommissionAction!goList.action');">佣金管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${saleCommission.id}" />
<div class="content">
	<form id="saleCommissionForm">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateSaleCommission();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/sales/commission/saleCommissionAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="saleCommission.id > 0">
							${saleCommission.name}
						</s:if> <s:else>
							新增佣金信息
						</s:else>
					</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<table>
							<tr>
								<td width="15%" align="right">销售员:</td>
								<td><input type="hidden" id="saleManId" name="saleManId" value="${saleCommission.saleMan.id}" /> <input id="saleManName" name="saleCommission.saleMan.name" value="${saleCommission.saleMan.name}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span> <a class="abtn" href="#"
									onclick="chooseEmployee();"><span>选择</span></a></td>
								<td width="10%" align="right">订单编码:</td>
								<td width="40%"><input id="orderCode" name="saleCommission.orderCode" value="${saleCommission.orderCode}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr>
								<td align="right">订单金额:</td>
								<td><input id="orderAmount" name="saleCommission.orderAmount" value="${saleCommission.orderAmount}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								<td align="right">订单时间:</td>
								<td><input id="orderTime" name="saleCommission.orderTime" value="${saleCommission.orderTime}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr>
								<td align="right">佣金:</td>
								<td><input id="conmmission" name="saleCommission.conmmission" value="${saleCommission.conmmission}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								<td align="right">佣金比例:</td>
								<td><input id="commissionRate" name="saleCommission.commissionRate" value="${saleCommission.commissionRate}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
						</table>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>
<!-- content -->
