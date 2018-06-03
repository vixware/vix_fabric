<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/wechatcommon/page/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="doAttendance" class="proDet proDet_b">
	<input type="hidden" id="isLate" value="${isLate}">
	<input type="hidden" id="doAddress" value="${doAddress}">
	<input type="hidden" id="startTimes" value="${startTimes}">
	<h2>
		<span>
			签到地址:<c:out value="${doAddress}"/>
			<br/>
			<b>签到时间: ${startTimes}</b>
			<br/>
			<b>签到状态:
				<c:if test="${isLate == 0}"><b style="color: blue;font-size: 18px;">已签到</b></c:if>
				<c:if test="${isLate == 1}"><b style="color: red;font-size: 18px;">迟到</b></c:if>
			</b>
		</span>
	</h2>
</div>