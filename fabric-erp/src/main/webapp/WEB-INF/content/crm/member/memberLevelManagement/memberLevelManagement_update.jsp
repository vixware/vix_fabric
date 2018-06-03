<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#customerAccountForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<form id="customerAccountForm">
		<div class="box order_table" style="line-height: normal;">
			<s:hidden id="customerAccountId" name="customerAccountId" value="%{customerAccount.id}" theme="simple" />
			<table class="table-padding020">
				<tr height="30">
					<td align="right">等级:&nbsp;</td>
					<td><s:select id="memberLevelId" headerKey="-1" headerValue="--请选择--" list="%{memberLevelList}" listValue="name" listKey="id" value="" multiple="false" theme="simple">
						</s:select></td>
				</tr>
			</table>
		</div>
	</form>
</div>