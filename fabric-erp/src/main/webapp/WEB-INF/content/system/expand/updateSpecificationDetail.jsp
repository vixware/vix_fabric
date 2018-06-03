<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#specificationDetailForm").validationEngine();
</script>
<form id="specificationDetailForm">
	<div style="padding: 5px;">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<s:hidden id="detailId" name="specificationDetail.id" value="%{specificationDetail.id}" theme="simple" />
				<input id="detailCode" name="specificationDetail.code" value="${specificationDetail.code}" type="hidden" />
				<tr height="30">
					<td align="right">名称:&nbsp;</td>
					<td><input id="detailName" name="specificationDetail.name" value="${specificationDetail.name}" /></td>
					<td align="right">备注:&nbsp;</td>
					<td colspan="3"><input id="detailMemo" name="specificationDetail.memo" value="${specificationDetail.memo}" /></td>
				</tr>
			</table>
		</div>
	</div>
</form>