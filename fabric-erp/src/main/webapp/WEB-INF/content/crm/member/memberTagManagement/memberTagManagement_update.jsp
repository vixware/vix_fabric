<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#memberTagForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<form id="memberTagForm">
		<div class="box order_table" style="line-height: normal;">
			<input id="id" name="memberTag.id" value="${memberTag.id}" type="hidden"> <input id="customerAccountId" name="customerAccount.id" value="${customerAccount.id}" type="hidden">
			<table>
				<tr height="30">
					<td align="right">标签:&nbsp;</td>
					<td><input id="memberTagName" name="memberTagName" value="${memberTag.name }" type="text" class="ipt w88per underline" size="45"></td>
				</tr>
			</table>
		</div>
	</form>
</div>