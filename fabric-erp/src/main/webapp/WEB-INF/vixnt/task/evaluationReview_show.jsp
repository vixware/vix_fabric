<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<ul>
	<s:if test="evaluationReviewList.size > 0">
		<s:iterator value="evaluationReviewList" var="entity" status="s">
			<li class="message">
				<s:if test="entity.employee.headImgUrl !=null">
					<img src="${entity.employee.headImgUrl }" alt="" height="50px" width="50px">
				</s:if> <s:else>
					<img src="${nvix}/vixntcommon/base/images/touxiang.png" alt="" height="50px" width="50px">
				</s:else>
				<div class="message-text">
					<time>${entity.evaluationTimeTimeStr}</time>
					<a href="javascript:void(0);" class="username">${entity.employee.name }</a> ${entity.evaluationContent }
				</div></li>
		</s:iterator>
	</s:if>
</ul