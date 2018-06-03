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
 
$("#printedRequisitionForm").validationEngine();
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
function saveOrUpdatePrintedRequisition(){
	if($('#printedRequisitionForm').validationEngine('validate')){
		$.post('${vix}/crm/market/printedRequisitionAction!saveOrUpdate.action',
			{'printedRequisition.id':$("#id").val(),
			  'printedRequisition.count':$("#count").val(),
			  'printedRequisition.use':$("#use").val(),
			  'printedRequisition.printedMatter.id':$("#printedMatterId").val(),
			  'printedRequisition.employee.id':$("#employeeId").val()
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/crm/market/printedRequisitionAction!goList.action');
			}
		);
	}
}
function choosePrintedMatter(){
	$.ajax({
		  url:'${vix}/crm/market/printedMatterAction!goChoosePrintedMatter.action',
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
								$("#printedMatterId").val(result[0]);
								$("#printedMatterName").val(result[1]);
							}else{
								$("#printedMatterId").val("");
								$("#printedMatterName").val("");
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
								asyncbox.success("<s:text name='pleaseChooseEmployee'/>","<s:text name='vix_message'/>");
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
				<li><a href="#" onclick="loadContent('${vix}/crm/market/printedRequisitionAction!goList.action');">印刷品领用</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${printedRequisition.id}" />
<input type="hidden" id="employeeId" value="${printedRequisition.employee.id}" />
<input type="hidden" id="printedMatterId" value="${printedRequisition.printedMatter.id}" />
<div class="content">
	<form id="printedRequisitionForm">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdatePrintedRequisition();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/crm/market/printedRequisitionAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="printedRequisition.id > 0">
							${printedRequisition.count}
						</s:if> <s:else>
							领用印刷品
						</s:else>
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
									<s:hidden id="employeeId" name="printedRequisition.employee.id" value="%{printedRequisition.employee.id}" theme="simple" />
									<table class="addtable_c">
										<tr>
											<td align="right">对应印刷品:</td>
											<td colspan="3"><input id="printedMatterName" name="printedRequisition.printedMatter.name" value="${printedRequisition.printedMatter.name}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span> <a class="abtn" href="#" onclick="choosePrintedMatter();"><span>选择</span></a></td>
										</tr>
										<tr>
											<td align="right" width="15%">数量:</td>
											<td width="35%"><input id="count" name="printedRequisition.count" value="${printedRequisition.count}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right" width="10%">领用人:</td>
											<td width="40%"><input id="employeeName" name="printedRequisition.employee.name" value="${printedRequisition.employee.name}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span> <a class="abtn" href="#" onclick="chooseEmployee();"><span>选择</span></a></td>
										</tr>
										<tr>
											<td align="right">用途:</td>
											<td colspan="3"><textarea id="use" style="width: 320px; height: 80px;" rows="5">${printedRequisition.use}</textarea></td>
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
