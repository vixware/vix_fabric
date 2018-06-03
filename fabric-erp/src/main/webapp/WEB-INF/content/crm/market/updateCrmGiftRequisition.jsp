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
 
$("#crmGiftRequisitionForm").validationEngine();
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
function saveOrUpdateCrmGiftRequisition(){
	if($('#crmGiftRequisitionForm').validationEngine('validate')){
		$.post('${vix}/crm/market/crmGiftRequisitionAction!saveOrUpdate.action',
			{'crmGiftRequisition.id':$("#id").val(),
		      'crmGiftRequisition.requisitionDate':$("#requisitionDate").val(),
		      'crmGiftRequisition.crmGift.id':$("#crmGiftId").val(),
		      'crmGiftRequisition.employee.id':$("#employeeId").val(),
			  'crmGiftRequisition.count':$("#count").val(),
			  'crmGiftRequisition.use':$("#use").val()
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/crm/market/crmGiftRequisitionAction!goList.action');
			}
		);
	}
}
function chooseCrmGift(){
	$.ajax({
		  url:'${vix}/crm/market/crmGiftAction!goChooseCrmGift.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 960,
					height : 500,
					title:"选择印刷品",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(":");
								$("#crmGiftId").val(result[0]);
								$("#crmGiftName").val(result[1]);
							}else{
								$("#crmGiftId").val("");
								$("#crmGiftName").val("");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
	
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
								$("#employeeId").val(result[0].replace(",",""));
								$("#employeeName").val(result[1].replace(",",""));
							}else{
								$("#employeeId").val("");
								$("#employeeName").val("");
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
				<li><a href="#"><img src="${vix}/common/img/crm/market.png" alt="" />
					<s:text name="cmn_home" /></a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#">市场管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/market/crmGiftRequisitionAction!goList.action');">礼品领用</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${crmGiftRequisition.id}" />
<input type="hidden" id="employeeId" value="${crmGiftRequisition.employee.id}" />
<input type="hidden" id="crmGiftId" value="${crmGiftRequisition.crmGift.id}" />
<div class="content">
	<form id="crmGiftRequisitionForm">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateCrmGiftRequisition();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/crm/market/crmGiftRequisitionAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="crmGiftRequisition.id > 0">
							${crmGiftRequisition.name}
						</s:if> <s:else>
							新增礼品领用
						</s:else>
					</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<table>
							<tr>
								<td width="15%" align="right">对应礼品:</td>
								<td width="35%"><input id="crmGiftName" name="crmGiftRequisition.crmGift.name" value="${crmGiftRequisition.crmGift.name}" type="text" size="30" size="30" /> <a class="abtn" href="#" onclick="chooseCrmGift();"><span>选择</span></a></td>
								<td width="10%" align="right">领用时间:</td>
								<td width="40%"><input id="requisitionDate" name="requisitionDate" value="${crmGiftRequisition.requisitionDate}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span><img onclick="showTime('requisitionDate','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
									align="absmiddle"></td>
							</tr>
							<tr>
								<td align="right">数量:</td>
								<td><input id="count" name="crmGiftRequisition.count" value="${crmGiftRequisition.count}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								<td align="right">领用人:</td>
								<td><input id="employeeName" name="crmGiftRequisition.employee.name" value="${crmGiftRequisition.employee.name}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span> <a class="abtn" href="#" onclick="chooseEmployee();"><span>选择</span></a></td>
							</tr>
							<tr>
								<td align="right">用途:</td>
								<td colspan="3"><textarea id="use" style="width: 320px; height: 80px;" rows="5">${crmGiftRequisition.use}</textarea></td>
							</tr>
						</table>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>
<!-- content -->
