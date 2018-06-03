<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<ul>
	<s:if test="evaluationReviewList.size > 0">
		<s:iterator value="evaluationReviewList" var="entity" status="s">
			<li class="message"><img src="${entity.employee.headImgUrl }" alt="" height="50px" width="50px">
				<div class="message-text">
					<time>
						<s:date name="%{entity.evaluationTime}" format="yyyy-MM-dd HH:mm:ss" />
					</time>
					<a href="javascript:void(0);" class="username">${entity.employee.name }</a> ${entity.evaluationContent }
				</div></li>
		</s:iterator>
	</s:if>
</ul>