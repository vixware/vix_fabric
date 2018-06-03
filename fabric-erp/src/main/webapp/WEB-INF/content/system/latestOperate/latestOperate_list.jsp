<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<s:iterator value="latestOperateEntityList" var="latestOperateEntity" status="s">
	<a href="#">${latestOperateEntity.objectType}</a>
</s:iterator>