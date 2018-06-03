<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/vixntcommon/page/resource_css.jsp"%>
</head>
<body class="desktop-detected  pace-done smart-style-1">
	<header id="header">
		<div id="logo-group">
			<span id="logo"> <img src="${nvix}/vixntcommon/base/img/logo.png"></span>
		</div>
		<div class="pull-right">
			<div id="hide-menu" class="btn-header pull-right">
				<span> <a href="javascript:void(0);" data-action="toggleMenu" title="收起菜单"><i class="fa fa-reorder"></i></a>
				</span>
			</div>
			<div id="logout" class="btn-header transparent pull-right">
				<span> <a href="${nvix}/logout" title="退出" data-action="userLogout" data-logout-msg="确认退出重新登录吗?"><i class="fa fa-sign-out"></i></a>
				</span>
			</div>
			<div id="fullscreen" class="btn-header transparent pull-right">
				<span> <a href="javascript:void(0);" data-action="launchFullscreen" title="全屏"><i class="fa fa-arrows-alt"></i></a>
				</span>
			</div>
		</div>
	</header>


	<%@ include file="/WEB-INF/vixnt/common/nvix_head.jsp"%>
	<div id="shortcut">
		<ul>
			<li><a href="#" class="jarvismetro-tile big-cubes bg-color-blue" onclick="loadContent('mid_projectstask','${nvix}/nvixnt/taskAndPlanAction!goList.action');"> <span class="iconbox"> <i class="fa fa-tasks fa-4x"></i> <span>任务<span class="label pull-right bg-color-darken">${taskNum }</span></span>
				</span>
			</a></li>
			<li><a href="#" class="jarvismetro-tile big-cubes bg-color-orangeDark" onclick="loadContent('mid_schedule','${nvix}/nvixnt/nvixScheduleAction!goList.action');"> <span class="iconbox"> <i class="fa fa-calendar fa-4x"></i> <span>日历</span>
				</span>
			</a></li>
			<li><a href="#" class="jarvismetro-tile big-cubes bg-color-purple" onclick="loadContent('mid_projecttracingmanagement','${nvix}/nvixnt/nvixProjectAction!goProjectTracing.action');"> <span class="iconbox"> <i class="fa fa-puzzle-piece fa-4x"></i> <span>项目</span>
				</span>
			</a></li>
			<li><a href="#" class="jarvismetro-tile big-cubes bg-color-blueDark" onclick="loadContent('mid_file','${nvix}/nvixnt/nvixFileAction!goList.action');"> <span class="iconbox"> <i class="fa fa-file-o fa-4x"></i> <span>文件 <span class="label pull-right bg-color-darken">0</span></span>
				</span>
			</a></li>
		</ul>
	</div>

	<%@ include file="/vixntcommon/page/resource_js.jsp"%>
	<!-- MAIN PANEL -->
	<div id="main" role="main">
		<div id="mainContent">
			<c:if test="${homeTemplateCode=='NVIXNT_CRM'}">
				<s:include value="nvix_content.jsp" />
			</c:if>
			<c:if test="${homeTemplateCode=='NVIXNT_OA'}">
				<s:include value="nvix_content_default.jsp" />
			</c:if>
		</div>
	</div>
	<%@ include file="/WEB-INF/vixnt/common/nvix_footer.jsp"%>
	<script src="${nvix}/vixntcommon/common/nvix_index.js"></script>
	<script type="text/javascript">
		function loadContent(mid, url) {
			$("nav li.active").removeClass("active");
			$("#" + mid).parent().addClass("active");
			$.vixerp.common.loadContent(url);
		};
	</script>
</body>
</html>