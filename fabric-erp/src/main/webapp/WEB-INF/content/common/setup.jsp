<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title><s:text name="vix_name" /></title>
<s:include value="resource.jsp" />
</head>
<body>
	<%-- <s:include value="top.jsp" />
	<!-- top -->
	<s:include value="head.jsp" /> --%>
	<!-- head -->
	<div id="mainContent">
		<s:include value="setup_content.jsp" />
	</div>
	<!-- content -->
	<%-- <s:include value="foot.jsp" />
	<!-- foot -->
	<s:include value="menu.jsp" /> --%>
	<div id="asyncBoxWindow" style="display: none;"></div>
</body>
</html>