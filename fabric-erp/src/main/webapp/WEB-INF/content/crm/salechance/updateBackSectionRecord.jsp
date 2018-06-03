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
 
$("#backSectionRecordForm").validationEngine();
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
function saveOrUpdateBackSectionRecord(){
	if($('#backSectionRecordForm').validationEngine('validate')){
		$.post('${vix}/crm/salechance/backSectionRecordAction!saveOrUpdate.action',
			{'backSectionRecord.id':$("#id").val(),
			  'backSectionRecord.customerAccount.id':$("#customerAccountId").val(),
			  'backSectionRecord.backSectionDate':$("#backSectionDate").val(),
			  'backSectionRecord.findDate':$("#findDate").val(),
			  'backSectionRecord.amount':$("#amount").val(),
			  'backSectionRecord.stageNumber':$("#stageNumber").val(),
			  'backSectionRecord.foreignCurrencyMemo':$("#foreignCurrencyMemo").val(),
			  'backSectionRecord.isBilling':$(":radio[name=isBilling][checked]").val(),
			  'backSectionRecord.owner.id':$("#ownerId").val(),
			  'backSectionRecord.paymentType.id':$("#paymentTypeId").val(),
			  'backSectionRecord.paymentCategory.id':$("#paymentCategoryId").val(),
			  'backSectionRecord.crmProject.id':$("#crmProjectId").val(),
			  'backSectionRecord.saleOrderId':$("#saleOrderId").val(),
			  'backSectionRecord.saleOrderTitle':$("#saleOrderName").val(),
			  'backSectionRecord.memo':$("#memo").val()
			},
			function(result){
				showMessage(result);
				loadContent('${vix}/crm/salechance/backSectionRecordAction!goList.action');
			}
		);
	}
}
function chooseCrmProject(){
	$.ajax({
		  url:'${vix}/crm/project/crmProjectAction!goChooseCrmProject.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
				 	width : 960,
					height : 500,
					title:"选择项目",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(":");
								$("#crmProjectId").val(result[0]);
								$("#crmProjectName").val(result[1]);
							}else{
								$("#crmProjectId").val("");
								$("#crmProjectName").val("");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function chooseSaleOrder(){
	$.ajax({
		  url:'${vix}/sales/order/salesOrderAction!goChooseSalesOrder.action?chooseType=single',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 960,
					height : 500,
					title:"选择订单",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(":");
								$("#saleOrderId").val(result[0]);
								$("#saleOrderName").val(result[1]);
							}else{
								$("#saleOrderId").val("");
								$("#saleOrderName").val("");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function chooseOwner(){
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
								$("#ownerId").val(result[0].replace(",",""));
								$("#ownerName").val(result[1].replace(",",""));
							}else{
								$("#ownerId").val("");
								$("#ownerName").val("");
								asyncbox.success("<s:text name='pleaseChooseEmployee'/>","<s:text name='vix_message'/>");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
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
								$("#customerCode").val(result[2]);
							}else{
								$("#customerAccountId").val("");
								$("#customerName").val("");
								$("#customerCode").val("");
								asyncbox.success("<s:text name='pleaseChooseCustomerAccount'/>","<s:text name='vix_message'/>");
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
				<li><a href="#"><img src="${vix}/common/img/crm/backSectionRecord.png" alt="" />
					<s:text name="cmn_home" /></a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/salechance/saleChanceAction!goList.action');">销售机会</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/salechance/backSectionRecordAction!goList.action');">回款记录</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${backSectionRecord.id}" />
<div class="content">
	<form id="backSectionRecordForm">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateBackSectionRecord();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/crm/salechance/backSectionRecordAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="backSectionRecord.id > 0">
							${backSectionRecord.customerAccount.name}
						</s:if> <s:else>
							新增回款记录
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
									<table class="addtable_c">
										<tr>
											<td width="15%" align="right">回款日期:</td>
											<td width="35%"><input id="backSectionDate" name="backSectionRecord.backSectionDate" value="<s:property value='formatDateToString(backSectionRecord.backSectionDate)'/>" type="text" /> <img onclick="showTime('backSectionDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td width="10%" align="right">客户名称:</td>
											<td width="40%"><input id="customerName" name="backSectionRecord.customerAccount.name" value="${backSectionRecord.customerAccount.name}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <input type="hidden" id="customerAccountId" name="customerAccountId"
												value="${backSectionRecord.customerAccount.id}" /> <a class="abtn" href="#" onclick="chooseCustomerAccount();"><span>选择</span></a></td>
										</tr>
										<tr>
											<td align="right">项目:</td>
											<td><input id="crmProjectName" name="backSectionRecord.crmProject.subject" value="${backSectionRecord.crmProject.subject}" type="text" /> <input type="hidden" id="crmProjectId" name="crmProjectId" value="${backSectionRecord.crmProject.id}" /> <a class="abtn" href="#" onclick="chooseCrmProject();"><span>选择</span></a></td>
											<td align="right">销售订单:</td>
											<td><input id="saleOrderName" name="backSectionRecord.saleOrderTitle" value="${backSectionRecord.saleOrderTitle}" type="text" /> <input type="hidden" id="saleOrderId" name="saleOrderId" value="${backSectionRecord.saleOrderId}" /> <a class="abtn" href="#" onclick="chooseSaleOrder();"><span>选择</span></a></td>
										</tr>
										<tr>
											<td align="right">期次:</td>
											<td><input id="stageNumber" name="backSectionRecord.stageNumber" value="${backSectionRecord.stageNumber}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">金额:</td>
											<td><input id="amount" name="backSectionRecord.amount" value="${backSectionRecord.amount}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <script type="text/javascript">
												$(function(){
													$('#amount').priceFormat({
													    prefix: '',
													    centsLimit: 1
													});
												});
											</script></td>
										</tr>
										<tr>
											<td align="right">外币备注:</td>
											<td><input id="foreignCurrencyMemo" name="backSectionRecord.foreignCurrencyMemo" value="${backSectionRecord.foreignCurrencyMemo}" type="text" /></td>
											<td align="right">开票状态:</td>
											<td><s:if test="backSectionRecord.isBilling == 0">
													<input type="radio" id="isBilling1" name="isBilling" value="0" checked="checked" />未开
												<input type="radio" id="isBilling1" name="isBilling" value="1" />已开
												<input type="radio" id="isBilling2" name="isBilling" value="2" />不开
											</s:if> <s:elseif test="backSectionRecord.isBilling == 1">
													<input type="radio" id="isBilling1" name="isBilling" value="0" />未开
												<input type="radio" id="isBilling1" name="isBilling" value="1" checked="checked" />已开
												<input type="radio" id="isBilling2" name="isBilling" value="2" />不开
											</s:elseif> <s:elseif test="backSectionRecord.isBilling == 2">
													<input type="radio" id="isBilling1" name="isBilling" value="0" />未开
												<input type="radio" id="isBilling1" name="isBilling" value="1" />已开
												<input type="radio" id="isBilling2" name="isBilling" value="2" checked="checked" />不开
											</s:elseif> <s:else>
													<input type="radio" id="isBilling1" name="isBilling" value="0" />未开
												<input type="radio" id="isBilling1" name="isBilling" value="1" />已开
												<input type="radio" id="isBilling2" name="isBilling" value="2" />不开
											</s:else></td>
										</tr>
										<tr>
											<td align="right">所有者:</td>
											<td><input id="ownerName" name="backSectionPlan.owner.name" value="${backSectionRecord.owner.name}" type="text" /> <input type="hidden" id="ownerId" name="ownerId" value="${backSectionRecord.owner.id}" /> <a class="abtn" href="#" onclick="chooseOwner();"><span>选择</span></a></td>
											<td align="right">付款方式:</td>
											<td><s:select id="paymentTypeId" headerKey="" headerValue="--请选择--" list="%{paymentTypeList}" listValue="name" listKey="id" value="%{backSectionRecord.paymentType.id}" multiple="false" theme="simple"></s:select></td>
										</tr>
										<tr>
											<td align="right">付款分类:</td>
											<td><s:select id="paymentCategoryId" headerKey="" headerValue="--请选择--" list="%{paymentCategoryList}" listValue="name" listKey="id" value="%{backSectionRecord.paymentCategory.id}" multiple="false" theme="simple"></s:select></td>
											<td align="right">备注:</td>
											<td><input id="memo" name="backSectionRecord.memo" value="${backSectionRecord.memo}" type="text" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>
<!-- content -->
