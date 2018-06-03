<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#orginialCurrencyTypeForm").validationEngine();
//-->
</script>
<form id="orginialCurrencyTypeForm">
	<div style="padding: 5px;">
		<s:hidden id="id" name="orginialCurrencyType.id" value="%{orginialCurrencyType.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="30">
					<td align="right">编码:&nbsp;</td>
					<td><input id="code" name="orginialCurrencyType.code" value="${orginialCurrencyType.code}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">名称:&nbsp;</td>
					<td><input id="name" name="orginialCurrencyType.name" value="${orginialCurrencyType.name}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">是否本位币:&nbsp;</td>
					<td><s:if test="orginialCurrencyType.isBaseCurrency == 0">
							<input type="radio" id="isBaseCurrency1" name="isBaseCurrency" value="0" checked="checked" />否
							<input type="radio" id="isBaseCurrency1" name="isBaseCurrency" value="1" />是
						</s:if> <s:elseif test="orginialCurrencyType.isBaseCurrency == 1">
							<input type="radio" id="isBaseCurrency1" name="isBaseCurrency" value="0" />否
							<input type="radio" id="isBaseCurrency1" name="isBaseCurrency" value="1" checked="checked" />是
						</s:elseif> <s:else>
							<input type="radio" id="isBaseCurrency1" name="isBaseCurrency" value="0" checked="checked" />否
							<input type="radio" id="isBaseCurrency2" name="isBaseCurrency" value="1" />是
						</s:else></td>
				</tr>
			</table>
		</div>
	</div>
</form>