<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#backSectionPlanForm").validationEngine();

//选择供应商信息
function chooseRadioSupplier(){
		$.ajax({
			  url:'${vix}/contract/contractDraftingAction!goSubRadioSingleList.action',
			  cache: false,
			  success: function(html){
				  $(".ab_outer .list td input[type='checkbox']").css("margin-left",10);
				  $(".ab_c .content").css("margin-bottom","0");
				  $('.ab_c .content').parent().css('position','relative');
				  asyncbox.open({
					 	modal:true,
						width : 1000,
						height : 500,
						title:"选择供应商信息",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									var rv = returnValue.split(",");
									$("#firstPartyId").val(rv[0]);
									$("#firstParty").val(rv[1]);
								}else{
									$("#firstParty").val("");
									asyncbox.success("请选择采购信息!","提示信息");
								}
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="backSectionPlanForm">
		<input type="hidden" id="id" name="id" value="${contract.id}" /> <input type="hidden" id="contractType" name="contractType" value="${contract.contractType}" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
				</tr>
				<tr height="40">
					<th align="right">合同编码:&nbsp;</th>
					<td><input id="contractCode" name="{contract.contractCode" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">合同名称:&nbsp;</th>
					<td><input id="contractName" name="contract.contractName" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">甲方:&nbsp;</th>
					<td><input id="firstParty" name="firstParty" value="${contract.firstParty}" type="text" size="8" class="validate[required] text-input" /> <span style="color: red;">*</span> <input type="hidden" id="firstPartyId" name="firstPartyId" value="${contract.firstPartyId}" /> <input class="btn" type="button" value="选择信息"
						onclick="chooseRadioSupplier();" /></td>
					<th align="right">乙方:&nbsp;</th>
					<td><input id="secondParty" name="contract.secondParty" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>