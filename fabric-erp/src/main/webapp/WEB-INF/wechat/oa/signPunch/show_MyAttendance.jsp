<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
	<head>
		<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" name="viewport">
		<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1" media="(device-height: 568px)" name="viewport">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${vix}/wechatcommon/css/result.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${vix}/wechatcommon/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${vix}/wechatcommon/js/js.js"></script>
		<title>我的考勤</title>
		<script type="text/javascript">
			function myAttendance(id) {
				window.location.href = "${vix}/wechat/signPunchAction!goMyAttendance.action?attTimes=" + attTimes;
			};
		</script>
	</head>
	<body>
		<section>
			<div class="subJob">
				<s:if test="punList.size > 0">
					<s:iterator value="punList" var="entity" status="s">
						<dl>
							<dt>
								<span style="height: 28px;"></span>
								<strong>
									<input type="hidden" id="attClockId" name="attClock.id" value="${entity.id }" />
									<a href="#" onclick="myAttendance('${entity.id}');">${entity.recordDateStr}</a>
									<%-- <b>${entity.wxCode}</b> --%>
								</strong>
							</dt>
						</dl>
					</s:iterator>
				</s:if>
				<s:else>
					<dt>
						<dl align="center">
							<strong>
								<a href="#">无数据</a>
							</strong>
						</dl>
					</dt>
				</s:else>
			</div>
		</section>
	</body>
</html>