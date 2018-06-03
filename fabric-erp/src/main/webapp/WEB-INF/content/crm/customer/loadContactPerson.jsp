<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<option value="">--------请选择------</option>
<s:iterator value="contactPersonList" var="sn">
	<s:if test="#sn.id == memorialDay.contactPerson.id ">
		<option selected="selected" value="${sn.id}">${sn.name}</option>
	</s:if>
	<s:else>
		<option value="${sn.id}">${sn.name}</option>
	</s:else>
</s:iterator>