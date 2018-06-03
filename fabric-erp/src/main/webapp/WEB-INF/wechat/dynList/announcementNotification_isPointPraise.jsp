<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/wechatcommon/page/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<h2>
	<c:choose>
		<c:when test="${wxpTrendsAndEmployee.isPointPraise  eq 1}">
			<a href="#" class="cur" onclick="cancelPointPraise('${announcementNotification.id }');"></a>
		</c:when>
		<c:otherwise>
			<a href="#" onclick="pointPraise('${announcementNotification.id }');"></a>
		</c:otherwise>
	</c:choose>
	<span>已有<b id="pointpraisenumsspanid">${announcementNotification.pointPraiseNums }</b>人点赞
	</span>
</h2>
<dl>
	<s:if test="wxpTrendsAndEmployeeList.size > 0">
		<dt>已点赞</dt>
		<dd>
			<s:iterator value="wxpTrendsAndEmployeeList" var="entity" status="s">
				${entity.employeeName }
				</s:iterator>
		</dd>
	</s:if>
</dl>
<!-- <h3>
	<a href="#">分享到外部</a>
</h3> -->
