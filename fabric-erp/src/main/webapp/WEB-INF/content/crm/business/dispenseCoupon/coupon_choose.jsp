<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#logisticsForm").validationEngine();
</script>
<input type="hidden" id="customerAccountId" name="customerAccountId" value="${customerAccountId}" />
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="logisticsForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">优惠券:&nbsp;</td>
					<td><s:select id="couponId" headerKey="" headerValue="--请选择--" list="%{couponList}" listValue="name" listKey="id" multiple="false" theme="simple">
						</s:select><span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>