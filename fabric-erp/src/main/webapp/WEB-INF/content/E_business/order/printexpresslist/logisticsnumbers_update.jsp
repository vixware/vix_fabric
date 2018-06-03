<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#logisticsForm").validationEngine();
</script>
<input type="hidden" id="orderBatchId" name="orderBatchId" value="${orderBatchId}" />
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="logisticsForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">快递公司：</td>
					<td><s:select id="logisticsId" headerKey="-1" headerValue="--请选择--" list="%{logisticsList}" listValue="name" listKey="id" multiple="false" theme="simple" class="validate[required] text-input">
						</s:select> <span style="color: red;">*</span></td>
					<td align="right">快递单号:&nbsp;</td>
					<td><input id="outSid" name="outSid" type="text" class="validate[required] text-input"><span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>