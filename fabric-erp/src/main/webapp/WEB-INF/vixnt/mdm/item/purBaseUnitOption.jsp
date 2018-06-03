<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<select id="purBaseUnitId" name="" data-prompt-position="topLeft" class="form-control">
	<option value="">------请选择------</option>
	<c:forEach items="${measureUnitList}" var="entity">
		<s:if test="#entity.id == itemPurchaseProperties.purBaseUnit.id">
			<option selected="selected" value="${entity.id}">${entity.name}</option>
		</s:if>
		<s:else>
			<option value="${entity.id}">${entity.name}</option>
		</s:else>
	</c:forEach>
</select>
