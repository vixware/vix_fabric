<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<ul class="notification-body">
	<s:if test="calendarsList.size > 0">
		<s:iterator value="calendarsList" var="entity" status="s">
			<li><span class="unread"> <a href="javascript:void(0);" class="msg"> <img src="${nvix}/vixntcommon/base/img/avatars/4.png" alt="" class="air air-top-left margin-top-5" width="40" height="40" /> <span class="from">${entity.scheduleName } <i class="icon-paperclip"></i></span> <time>${entity.createTimeTimeStr }</time> <span
						class="subject">${entity.calendarsContent } </span>
				</a>
			</span></li>
		</s:iterator>
	</s:if>
</ul>