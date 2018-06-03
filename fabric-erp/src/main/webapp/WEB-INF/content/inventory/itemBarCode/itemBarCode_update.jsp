<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#itemform").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="itemform">
		<s:hidden id="id" name="id" value="%{item.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">条形码:&nbsp;</td>
					<td><input type="text" id="serialCode" name="serialCode" value="${item.serialCode}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>