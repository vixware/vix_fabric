<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link rel="shortcut icon" type="image/x-icon" href="${vix}/common/img/vix.ico" media="screen" />
<title><s:text name="vix_name" /></title>
<!-- common resource start -->
<s:include value="resource.jsp" />
</head>
<body>
	<input type="hidden" id="cmn_simple_search" name="cmn_simple_search" value="<s:text name='cmn_simple_search'/>" />
	<input type="hidden" id="cmn_advance_search" name="cmn_advance_search" value="<s:text name='cmn_advance_search'/>" />
	<s:include value="top.jsp" />
	<!-- top -->
	<%-- <s:include value="head.jsp" /> --%>
	<s:include value="head.jsp" />
	<!-- head -->
	<div id="main_content_delegate">
		<div id="mainContent">
			<s:if test="#session.menuId == null">
				<s:include value="content.jsp" />
			</s:if>
		</div>
	</div>
	<!-- content -->
	<s:include value="foot.jsp" />
	<!-- foot -->
	<s:include value="menu.jsp" />
	<div id="asyncBoxWindow" style="display: none;"></div>
	<div id="page_content_loading_mask">
		<div class="open_m">
			<div class="img_box">
				<img border="0" src="${vix}/common/img/page_content_loading.gif" />
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			/** 刷新恢复页面操作 */
			if ($("#menuId").val() != '') {
				var menuId = $("#menuId").val();
				$("#" + menuId).click();
			}
		});
	</script>
</body>
</html>