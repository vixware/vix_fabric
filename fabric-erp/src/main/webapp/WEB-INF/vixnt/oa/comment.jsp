<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<s:if test="evaluationReviewsList.size > 0">
	<s:iterator value="evaluationReviewsList" var="entity" status="s">
		<li class="message message-reply"><img src="${entity.employee.headImgUrl}"> <span class="message-text"> <a href="javascript:void(0);" class="username">${entity.employee.name}</a> ${entity.evaluationContent} <i class="fa fa-smile-o txt-color-orange"></i>
		</span>
			<ul class="list-inline font-xs">
				<li><a href="javascript:void(0);" class="text-muted">${entity.evaluationTime}</a></li>
			</ul></li>
	</s:iterator>
</s:if>