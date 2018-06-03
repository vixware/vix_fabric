<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<form id="returnRuleItemForm">
		<div class="box order_table" style="line-height: normal;">
			<s:hidden id="returnRuleItemId" name="returnRuleItem.id" value="%{returnRuleItem.id}" theme="simple" />
			<table class="table-padding020">
				<tr height="30">
					<td align="right">从:&nbsp;</td>
					<td><input type="text" id="from" name="returnRuleItem.from" value="${returnRuleItem.from}" class="validate[custom[number]] text-input" /></td>
					<td align="right">到:&nbsp;</td>
					<td><input type="text" id="to" name="returnRuleItem.to" value="${returnRuleItem.to}" class="validate[custom[number]] text-input" /></td>
				</tr>
				<tr height="30">
					<td align="right">返款比率:&nbsp;</td>
					<td><input type="text" id="ratio" name="returnRuleItem.ratio" value="${returnRuleItem.ratio}" />%</td>
					<td align="right">计量单位:&nbsp;</td>
					<td><input type="text" id="unit" name="returnRuleItem.unit" value="${returnRuleItem.unit}" /></td>
				</tr>
				<tr height="30">
					<td align="right">币种:&nbsp;</td>
					<td colspan="3"><s:select id="currencyTypeId" headerKey="-1" headerValue="--请选择--" list="%{currencyTypeList}" listValue="name" listKey="id" value="%{returnRuleItem.currencyType.id}" multiple="false" theme="simple"></s:select></td>
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
	$("#returnRuleItemForm").validationEngine();
</script>