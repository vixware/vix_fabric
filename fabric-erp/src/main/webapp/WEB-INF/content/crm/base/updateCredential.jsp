<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#credentialForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="credentialForm">
		<div id="updateCredential">
			<s:hidden id="id" name="credential.id" value="%{credential.id}" theme="simple" />
			<div class="box order_table" style="line-height: normal;">
				<table>
					<tr height="30">
						<td align="right">证件号:&nbsp;</td>
						<td><input id="credentialCode" name="credential.credentialCode" value="${credential.credentialCode}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
						<td align="right">证件分类:&nbsp;</td>
						<td><s:select id="credentialTypeId" headerKey="" headerValue="--请选择--" list="%{credentialTypeList}" listValue="name" listKey="id" value="%{credential.credentialType.id}" multiple="false" theme="simple"></s:select></td>
					</tr>
					<tr height="30">
						<td align="right">备注:&nbsp;</td>
						<td colspan="3"><s:textfield id="memo" name="credential.memo" value="%{credential.memo}" theme="simple" /></td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</div>