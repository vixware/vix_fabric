<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/wechatcommon/page/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="doAttendanceOut" class="proDet proDet_b">
	<input type="hidden" id="isEarly" value="${isEarly}">
	<input type="hidden" id="doOutAddress" value="${doOutAddress}">
	<input type="hidden" id="endTimes" value="${endTimes}">
	<h2>
		<span>
			签退地址:<c:out value="${doOutAddress}"/>
			<br/>
			<b>签退时间: ${endTimes}</b>
			<br/>
			<b>签退状态:
				<c:if test="${isEarly == 0 }"><b style="color: blue;font-size: 18px;">已签退</b></c:if>
				<c:if test="${isEarly == 1 }"><b style="color: red;font-size: 18px;">早退</b></c:if>
			</b>
		</span>
	</h2>
</div>