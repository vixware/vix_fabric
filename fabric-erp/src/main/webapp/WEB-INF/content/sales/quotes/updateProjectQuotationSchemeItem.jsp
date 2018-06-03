<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<form id="projectQuotationSchemeItemForm">
		<div class="box order_table" style="line-height: normal;">
			<s:hidden id="projectQuotationSchemeItemId" name="projectQuotationSchemeItem.id" value="%{projectQuotationSchemeItem.id}" theme="simple" />
			<s:hidden id="itemProjectQuotationSchemeItemId" name="projectQuotationSchemeItem.item.id" value="%{projectQuotationSchemeItem.item.id}" theme="simple" />
			<s:hidden id="parentProjectQuotationSchemeItemId" name="projectQuotationSchemeItem.parentProjectQuotationSchemeItem.id" value="%{projectQuotationSchemeItem.parentProjectQuotationSchemeItem.id}" theme="simple" />
			<table class="table-padding020">
				<tr height="30">
					<td align="right">上级报价明细 :&nbsp;</td>
					<td><input type="text" id="parentProjectQuotationSchemeItemName" name="projectQuotationSchemeItem.parentProjectQuotationSchemeItem.name" value="${projectQuotationSchemeItem.parentProjectQuotationSchemeItem.name}" readonly="readonly" /> <span class="abtn"><a href="###" onclick="chooseProjectQuotationSchemeItem();"><span>选择</span></a></span>
					</td>
					<td align="right">名称</td>
					<td><input type="text" id="projectQuotationSchemeItemName" name="projectQuotationSchemeItem.name" value="${projectQuotationSchemeItem.name}" /></td>
				</tr>
				<tr height="30">
					<td align="right">报价负责人 :&nbsp;</td>
					<td><s:hidden id="quotationChargerId" name="projectQuotationSchemeItem.quotationCharger.id" value="%{projectQuotationSchemeItem.quotationCharger.id}" /> <input type="text" id="quotationChargerName" name="projectQuotationSchemeItem.quotationCharger.name" value="${projectQuotationSchemeItem.quotationCharger.name}" readonly="readonly" /> <span
						class="abtn"><a href="###" onclick="choosePqsiEmployee();"><span>选择</span></a></span></td>
					<td align="right">${vv:varView('vix_mdm_item')}:&nbsp;</td>
					<td><input type="text" id="itemProjectQuotationSchemeItemName" name="projectQuotationSchemeItem.item.name" value="${projectQuotationSchemeItem.item.name}" readonly="readonly" /> <span class="abtn"><a href="###" onclick="chooseItemForProjectQuotationSchemeItem();"><span>选择</span></a></span></td>
				</tr>
				<tr height="30">
					<td align="right">报价负责部门:&nbsp;</td>
					<td><input type="text" id="quotationDepartment" name="projectQuotationSchemeItem.quotationDepartment" value="${projectQuotationSchemeItem.quotationDepartment}" /></td>
					<td align="right">数量</td>
					<td><input type="text" id="pqsAmount" name="projectQuotationSchemeItem.amount" value="${projectQuotationSchemeItem.amount}" /></td>
				</tr>
				<tr height="30">
					<td align="right">辅计算量:&nbsp;</td>
					<td><input type="text" id="assitAmount" name="projectQuotationSchemeItem.assitAmount" value="${projectQuotationSchemeItem.assitAmount}" /></td>
					<td align="right">计量单位</td>
					<td><input type="text" id="unit" name="projectQuotationSchemeItem.unit" value="${projectQuotationSchemeItem.unit}" /></td>
				</tr>
				<tr height="30">
					<td align="right">辅助计量单位:&nbsp;</td>
					<td><input type="text" id="assitUnit" name="projectQuotationSchemeItem.assitUnit" value="${projectQuotationSchemeItem.assitUnit}" /></td>
					<td align="right">主辅计量单位换算率</td>
					<td><input type="text" id="unitExchange" name="projectQuotationSchemeItem.unitExchange" value="${projectQuotationSchemeItem.unitExchange}" />% 范围(1-100)</td>
				</tr>
				<tr height="30">
					<td align="right">税率:&nbsp;</td>
					<td><input type="text" id="tax" name="projectQuotationSchemeItem.tax" value="${projectQuotationSchemeItem.tax}" />% 范围(1-100)</td>
					<td align="right">折扣</td>
					<td><input type="text" id="discount" name="projectQuotationSchemeItem.discount" value="${projectQuotationSchemeItem.discount}" /></td>
				</tr>
				<tr height="30">
					<td align="right">单价:&nbsp;</td>
					<td colspan="3"><input type="text" id="price" name="projectQuotationSchemeItem.price" value="${projectQuotationSchemeItem.price}" class="validate[required] text-input" /> <span style="color: red;">*</span> <span class="abtn" style="cursor: pointer;" onclick="fixedPQSPrice();"><span>定价</span></span></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
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
	$("#projectQuotationSchemeItemForm").validationEngine();
	function chooseProjectQuotationSchemeItem(){
		$.ajax({
			  url:'${vix}/sales/quotes/projectQuotationSchemeItemAction!goChooseQuotationSchemeItem.action?projectQuotationSchemeId='+$("#id").val(),
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
					 	width : 660,
						height : 380,
						title:"选择上级报价明细",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != ''){
									var i = returnValue.split(",");
									$("#parentProjectQuotationSchemeItemId").val(i[0]);
									$("#parentProjectQuotationSchemeItemName").val(i[1]);
								}
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	function chooseItemForProjectQuotationSchemeItem(){
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
									var i = returnValue.split(",");
									$("#itemProjectQuotationSchemeItemId").val(i[0]);
									$("#itemProjectQuotationSchemeItemName").val(i[2]);
									$("#price").val(i[3]);
								}else{
									asyncbox.success("请选择${vv:varView('vix_mdm_item')}!","<s:text name='vix_message'/>");
									return false;
								}
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	function choosePqsiEmployee(){
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
									returnValue = returnValue.replace(',','');
									var result = returnValue.split(":");
									$("#quotationChargerId").val(result[0]);
									$("#quotationChargerName").val(result[1]);
								}
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	
	function fixedPQSPrice(){
		if($("#itemProjectQuotationSchemeItemId").val() == ''){
			asyncbox.success("请选择${vv:varView('vix_mdm_item')}!","<s:text name='vix_message'/>");
			return false;	
		}
		if($("#pqsAmount").val() == ''){
			asyncbox.success("请输入${vv:varView('vix_mdm_item')}数量!","<s:text name='vix_message'/>");
			return false;
		}
		var bdString = $("#bdString").val();
		var bcdDate =  $("#billDate").val() + " " + bdString.split(" ")[1];
		$.ajax({
			  url:'${vix}/mdm/item/saleItemPriceAction!goFixedPrice.action?id='+$("#itemProjectQuotationSchemeItemId").val()+"&count="+$("#pqsAmount").val()+"&billCreateDate="+bcdDate+"&customerAccountId="+$("#customerAccountId").val()+"&regionalId="+$("#regionalId").val(),
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 960,
						height : 580,
						title:"定价",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != ''){
									var rv = returnValue.split(":");
									$("#price").val(rv[0]);
								}
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
</script>