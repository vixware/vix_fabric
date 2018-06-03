<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script type="text/javascript"> 
<!-- 
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
/** 保存采购询价单 */
function saveOrUpdateCommissionTerm(){
	if($('#commissionTermForm').validationEngine('validate')){
		$.post('${vix}/sales/commission/commissionTermAction!saveOrUpdate.action',
				{
					'commissionTerm.id':$("#id").val(),
					'commissionTerm.crCode':$("#crCode").val(),
					'commissionTerm.crName':$("#crName").val(),
					'commissionTerm.type':$("#type").val(),
					'commissionTerm.commissionPlanItem.id':$("#commissionPlanItemId").val(),
					'commissionTerm.amountUnit':$("#amountUnit").val(),
					'commissionTerm.sumUnit':$("#sumUnit").val(),
					'commissionTerm.from':$("#from").val(),
					'commissionTerm.to':$("#to").val(),
					'commissionTerm.returnTerm':$("#returnTerm").val(),
					'commissionTerm.returnAmount':$("#returnAmount").val()
				},
				function(result){
					showMessage(result);
					setTimeout("",1000);
					loadContent('${vix}/sales/commission/commissionTermAction!goList.action');
				}
			 );
	}
}
$("#commissionTermForm").validationEngine();
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
function chooseCommissionPlanItem(){
	$.ajax({
		  url:'${vix}/sales/commission/commissionPlanItemAction!goChooseCommissionPlanItem.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 960,
					height : 500,
					title:"佣金计划明细",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
								$("#commissionPlanItemId").val(result[0]);
								$("#commissionPlanItemName").val(result[1]);
							}else{
								$("#commissionPlanItemId").val("");
								$("#commissionPlanItemName").val("");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
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
				<li><a href="#"><img src="img/pur_inquire.png" alt="" /> <s:text name="supplyChain" /></a></li>
				<li><a href="#">销售管理</a></li>
				<li><a href="#">销售佣金管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/commission/commissionTermAction!goList.action');">佣金条件</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${commissionTerm.id }" />
<div class="content">
	<form id="commissionTermForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateCommissionTerm();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/sales/commission/commissionTermAction!goList.action');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b>新增佣金方案</b> <i></i>
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
											<td align="right">编码：</td>
											<td><input id="crCode" name="commissionTerm.crCode" value="${commissionTerm.crCode}" type="text" size="30" /></td>
											<td align="right">名称：</td>
											<td><input id="crName" name="commissionTerm.crName" value="${commissionTerm.crName}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">类型：</td>
											<td><input id="type" name="commissionTerm.type" value="${commissionTerm.type}" type="text" size="30" /></td>
											<td align="right">佣金计划明细：</td>
											<td><input id="commissionPlanItemName" name="commissionTerm.commissionPlanItem.name" value="${commissionTerm.commissionPlanItem.name}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span> <input type="hidden" id="commissionPlanItemId" name="commissionTerm.commissionPlanItem.id"
												value="${commissionTerm.commissionPlanItem.id}" /> <span><a class="abtn" href="#" onclick="chooseCommissionPlanItem();"><span>选择</span></a></span></td>
										</tr>
										<tr>
											<td align="right">数量单位：</td>
											<td><input id="amountUnit" name="commissionTerm.amountUnit" value="${commissionTerm.amountUnit}" type="text" size="30" /></td>
											<td align="right">金额单位：</td>
											<td><input id="sumUnit" name="commissionTerm.sumUnit" value="${commissionTerm.sumUnit}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">从：</td>
											<td><input id="from" name="commissionTerm.from" value="${commissionTerm.from}" type="text" size="30" /></td>
											<td align="right">到：</td>
											<td><input id="to" name="commissionTerm.to" value="${commissionTerm.to}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">返款比率：</td>
											<td><input id="returnTerm" name="commissionTerm.returnTerm" value="${commissionTerm.returnTerm}" type="text" size="30" /></td>
											<td align="right">返款金额：</td>
											<td><input id="returnAmount" name="commissionTerm.returnAmount" value="${commissionTerm.returnAmount}" type="text" size="30" /></td>
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