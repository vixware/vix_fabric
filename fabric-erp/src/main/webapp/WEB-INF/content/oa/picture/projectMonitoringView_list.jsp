<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="css/reset.css" rel="stylesheet" type="text/css" />
<link href="css/css.css" rel="stylesheet" type="text/css" />
<link href="css/toolbar.css" rel="stylesheet" type="text/css" />
<link href="css/tree.css" rel="stylesheet" type="text/css" />
<link href="css/base/jquery.ui.all.css" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='css/fullcalendar.css' />
<link rel='stylesheet' type='text/css' href='css/fullcalendar.print.css' media='print' />
<link href="css/skin_01.css" type="text/css" id="skin" rel="stylesheet">
<link href="css/font_02.css" type="text/css" id="font" rel="stylesheet">
<link href="css/jquery.lightbox-0.5.css" type="text/css" rel="stylesheet">
<script src="js/jquery.jcarousel.min.js" type="text/javascript"></script>
<script src="js/jquery.easing.1.3.js" type="text/javascript"></script>
<script src="js/jquery.jBreadCrumb.1.1.js" type="text/javascript"></script>
<script src="js/bar.js" type="text/javascript"></script>
<script src="js/all.js" type="text/javascript"></script>
<script src="js/jquery.tree.js" type="text/javascript"></script>
<script src="js/date.js" type="text/javascript"></script>
<script src="js/date.js" type="text/javascript"></script>
<script src="js/jquery.ui.core.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.widget.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.mouse.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.resizable.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.draggable.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.dialog.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.position.min.js" type="text/javascript"></script>
<script src="js/jquery.lightbox-0.5.pack.js" type="text/javascript"></script>
<body>
	<div class="sub_menu">
		<h2>
			<i> <a href="#"><img src="${vix}/common/img/icon_14.gif" alt="" /> <s:text name="print" /></a> <a href="#" id="help"><img src="${vix}/common/img/icon_15.gif" alt="" /> <s:text name="help" /></a>
			</i>
			<div id="breadCrumb" class="breadCrumb module">
				<ul>
					<li><a href="#"><img src="${vix}/common/img/drp_channel.png" alt="" /> <s:text name="supplyChain" /></a></li>
					<li><a href="#"><s:text name="oa_xtbg" /></a></li>
					<li><a href="#">知识管理</a></li>
					<li><a href="#">项目监控视图</a></li>
				</ul>
			</div>
		</h2>
	</div>
	<!-- sub menu -->
	<div class="content">
		<div class="c_head">
			<span class="left_bg"></span> <span class="right_bg"></span>
		</div>
		<!-- c_head -->
		<div class="box">
			<div class="content">
				<div class="imgpmlist">12</div>
				<div>12</div>
				<div>12</div>
				<div>12</div>
			</div>
		</div>
	</div>
	<script src="js/loadtree.js" type="text/javascript"></script>
	<script type="text/javascript">
	//面包屑
	if($('.sub_menu').length){
		$("#breadCrumb").jBreadCrumb();
	}
	loadMenuContent('${vix}/oa/main/oaMainAction!goMenuContent.action');
</script>
</body>