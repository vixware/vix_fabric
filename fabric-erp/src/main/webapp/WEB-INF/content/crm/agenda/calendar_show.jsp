<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$ ("#calendarsform").validationEngine ();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="calendarsform">
		<s:hidden id="id" name="id" value="%{calendars.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">主题:</td>
					<td><b>${calendars.name}</b></td>
				</tr>
				<tr height="40">
					<th align="right">内容：</th>
					<td><b>${calendars.content}</b></td>
				</tr>
			</table>
		</div>
	</form>
</div>