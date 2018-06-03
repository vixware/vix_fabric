<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<s:iterator value="pager.resultList" var="entity" status="s">
	<a href="###" onclick="saveOrUpdateSalesQuotation('${entity.id}');"> ${entity.quoteSubject} </a>
	<s:property value='formatDateToString(entity.billDate)' />
	<br />
</s:iterator>