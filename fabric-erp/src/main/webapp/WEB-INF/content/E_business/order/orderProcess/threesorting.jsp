<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#logisticsForm").validationEngine();
</script>
<input type="hidden" id="orderId" name="orderId" value="${orderId}" />
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="logisticsForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">物流公司:&nbsp;</td>
					<td><s:select id="logisticsId" headerKey="-1" headerValue="--请选择--" list="%{logisticsList}" listValue="name" listKey="id" multiple="false" theme="simple">
						</s:select><span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>