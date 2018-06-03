<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<c:forEach items="${memberTagList}" var="entity">
	<div class="btn-group">
		<button class="btn btn-sm btn-info" type="button" onclick="check('${entity.id}')">
			${entity.name}
		</button>
	</div>
</c:forEach>