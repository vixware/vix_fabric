<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#calendarsform").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="calendarsform">
		<s:hidden id="id" name="id" value="%{calendars.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td>主题：</td>
					<td><span>${calendars.scheduleName }</span></td>
				</tr>
				<tr height="40">
					<td>内容：</td>
					<td><textarea name="schedulecontent" id="schedulecontent" cols="" rows="" style="width: 730px; height: 176px; margin-top: 5px; padding-top: 5px; border-top-width: 1px;" class="ipt" readonly="readonly"> ${calendars.calendarsContent}</textarea></td>
				</tr>
			</table>
		</div>
	</form>
</div>