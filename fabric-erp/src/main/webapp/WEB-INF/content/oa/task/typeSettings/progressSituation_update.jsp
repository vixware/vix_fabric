<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#progressSituationForm").validationEngine();
//-->
</script>
<form id="progressSituationForm">
	<div style="padding: 5px;">
		<s:hidden id="id" name="progressSituation.id" value="%{progressSituation.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="30">
					<td align="right">编码:&nbsp;</td>
					<td><input id="code" name="progressSituation.code" value="${progressSituation.code}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">进度情况:&nbsp;</td>
					<td><input id="name" name="progressSituation.name" value="${progressSituation.name}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">是否启用:&nbsp;</td>
					<td><s:if test="progressSituation.disabled == 0">
							<input type="radio" id="disabled1" name="disabled" value="0" checked="checked" />否
							<input type="radio" id="disabled1" name="disabled" value="1" />是
						</s:if> <s:elseif test="progressSituation.isBaseCurrency == 1">
							<input type="radio" id="disabled1" name="disabled" value="0" />否
							<input type="radio" id="disabled1" name="disabled" value="1" checked="checked" />是
						</s:elseif> <s:else>
							<input type="radio" id="disabled1" name="disabled" value="0" />否
							<input type="radio" id="disabled2" name="disabled" value="1" />是
						</s:else></td>
				</tr>
			</table>
		</div>
	</div>
</form>