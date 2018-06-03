<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<s:iterator value="pager.resultList" var="entity" status="s">
	<a href="###" onclick="saveOrUpdateSalesInvoice('${entity.id}');"> ${entity.amount} </a>
	<s:property value='formatDateToString(entity.createTime)' />
	<br />
</s:iterator>