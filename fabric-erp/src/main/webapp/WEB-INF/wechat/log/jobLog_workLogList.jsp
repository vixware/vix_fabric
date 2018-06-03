<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/wechatcommon/page/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<s:if test="workLogList.size > 0">
	<s:iterator value="workLogList" var="entity" status="s">
		<dl>
			<dt>
				<span name="chkId" value="${entity.id}"></span> <strong><a href="#" onclick="goShowDetails('${entity.id}');"> ${fn:substring(entity.title, 0,20)}</a><b> ${fn:substring(entity.logContent, 0,20)}...</b> </strong>
			</dt>
			<dd>
				<span>${fn:substring(entity.createTime, 11,19)}</span>
			</dd>
		</dl>
	</s:iterator>
</s:if>