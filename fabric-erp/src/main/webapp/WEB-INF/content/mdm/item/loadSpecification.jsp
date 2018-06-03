<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<table>
	<s:iterator var="specification" value="specificationList">
		<tr height="30">
			<td width="10%"></td>
			<td align="right" width="10%">${specification.name}&nbsp;</td>
			<td width="80%"><s:iterator var="specificationDetail" value="#specification.specificationDetails">
					<input type="checkbox" id="specification_${specification.id}" name="specificationDetail_${specification.id}_${specificationDetail.id}" value="${specificationDetail.id}" checked="" />${specificationDetail.name}&nbsp;
				</s:iterator></td>
		</tr>
	</s:iterator>
	<tr>
		<td></td>
		<td align="right"><a class="abtn" href="#" onclick="generateSpecTable();"><span>生成规格</span></a></td>
		<td></td>
	</tr>
</table>
<div id="specTable"></div>
<script type="text/javascript">

</script>