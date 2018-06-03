<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<option value="-1">--------请选择--------</option>
<s:iterator value="measureUnitList" var="mu">
	<s:if test="#mu.name != ''">
		<s:if test="#mu.id == projectQuotationTemplateItem.assitMeasureUnit.id ">
			<option selected="selected" value="${mu.id}">${mu.name}</option>
		</s:if>
		<s:else>
			<option value="${mu.id}">${mu.name}</option>
		</s:else>
	</s:if>
</s:iterator>
