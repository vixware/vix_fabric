<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#distributionCenterForm").validationEngine();
</script>
<input type="hidden" id="orderId" name="businussOrderId" value="${businussOrderId}" />
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="distributionCenterForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">姓名:&nbsp;</td>
					<td><s:select id="distributionCenterId" headerKey="-1" headerValue="--请选择--" list="%{distributionCenterList}" listValue="name" listKey="id" multiple="false" theme="simple">
						</s:select><span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>