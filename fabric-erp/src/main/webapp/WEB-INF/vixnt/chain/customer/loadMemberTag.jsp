<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<c:forEach items="${customerAccounMmemberTagList}" var="customerEntity">
	<div class="btn-group">
		<button class="btn btn-sm btn-warning" type="button" onclick="checked('${customerEntity.id}')">
			${customerEntity.name}&nbsp;&nbsp;&nbsp;<i class="fa fa-times" style="position: absolute;right: 2px;top: 2px;"></i>
		</button>
	</div>
</c:forEach>