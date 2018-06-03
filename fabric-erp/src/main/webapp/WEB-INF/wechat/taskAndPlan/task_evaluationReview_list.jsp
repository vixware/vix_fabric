<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/wechatcommon/page/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<h1>
	<span>回复（${evaluationReviewNum }）</span>
	<!-- <a href="#">只看评论</a> -->
</h1>
<s:if test="evaluationReviewList.size > 0">
	<s:iterator value="evaluationReviewList" var="entity" status="s">
		<dl>
			<dt>
				<a href="#"><img src="${entity.employee.headImgUrl }"></a>
			</dt>
			<dd>
				<span>${entity.employee.name }<b>${fn:substring(entity.evaluationTime, 0,19)}</b></span> <strong>${entity.evaluationContent }</strong>
			</dd>
		</dl>
	</s:iterator>
</s:if>
<s:else>
	<p>暂无回复</p>
</s:else>
