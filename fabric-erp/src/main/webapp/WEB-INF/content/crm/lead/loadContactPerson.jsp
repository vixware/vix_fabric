<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<option value="">--------请选择------</option>
<s:iterator value="contactPersonList" var="cp">
	<s:if test="#cp.id == saleLead.contactPerson.id ">
		<option selected="selected" value="${cp.id}">${cp.name}</option>
	</s:if>
	<s:else>
		<option value="${cp.id}">${cp.personName}</option>
	</s:else>
</s:iterator>
