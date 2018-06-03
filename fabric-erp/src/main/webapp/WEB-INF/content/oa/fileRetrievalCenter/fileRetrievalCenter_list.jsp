<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="${vix}/common/css/switch.css" rel="stylesheet" type="text/css" />
<link href="${vix}/common/search/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${vix}/common/js/switch.js"></script>
<script type="text/javascript">
function saveOrUpdate(id){
	$.ajax({
		  url:'${vix}/oa/treegridEuTreegrid/fileRetrievalCenterAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
};
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="img/icon_14.gif" alt="" />打印</a> <a href="#" id="help"><img src="img/icon_15.gif" alt="" />帮助</a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/hr_staff.png" class="png" alt="" width="26" height="26" />&nbsp;协同办公</a></li>
				<li><a href="#">知识管理</a></li>
				<a href="#">文档检索中心</a>
			</ul>
		</div>
	</h2>
</div>
<!-- sub menu -->
<div class="content">
	<!-- c_head -->
	<div class="npcontent clearfix" id="onresizeBox" style="background-image: url(../common/search/images/65.jpg);">

		<!--DEMO start-->
		<div style="width: 850px; height: 28%; margin: 0 auto; margin-top: 5cm; overflow: hidden;">
			<div style="height: 15%; padding-top: 15px;">
				<!-- 弹性高度 -->
				<!-- 人员搜索 -->
				<nav class="mainNavs">
					<a href="#">新闻</a> <a href="#">网页</a> <a href="#">贴吧</a> <a href="#">知道</a> <a href="#">音乐</a> <a href="#">图片</a> <a href="#">视频</a> <a href="#">地图</a> <a href="#">百科</a> <a href="#">文库</a> <a href="#">更多&gt;&gt;</a>
				</nav>
				<div class="searchBox">
					<form action="">
						<input type="text" class="searchIpt f14" name="wd" maxlength="100" autocomplete="off" />
						<!-- <a href="#" class="btn1 cp" onclick="saveOrUpdate(0);">搜索一下</a> -->
						<input type="submit" onclick="saveOrUpdate(0)" class="btn1 cp" value="搜索一下" />

					</form>
				</div>
			</div>
		</div>
		<!--DEMO end-->
	</div>
</div>