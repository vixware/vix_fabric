<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#search_btn").click(function() {
			$("#search").addClass("show");
			$("#s_select").css({
				"display" : "block"
			});
		});
		$("#s_select a").click(function() {
			$("#search").removeClass("show");
			$("#s_select").hide();
		});
		$(document).click(function(event) {
			var target = $(event.target);
			if (target.parents("#search").length == 0) {
				$("#search").removeClass("show");
				$("#s_select").css({
					"display" : "none"
				});
			}
		});
	});
</script>
<div id="top_bar">
	<span><strong id="dcMenuSugarCube"></strong></span>
	<ul id="dynamicMenuContent" class="top_btn">
		<li id="tp_01"><a href="${vix}/common/vixAction!goIndex.action?isHomePage=1"><img src="${vix}/common/img/none.gif" alt="" /><small>首页</small></a></li>
		<li id="tp_03"><a href="#" onclick="loadContent('${vix}/common/canlendarAction!goCanlendar.action');"><img src="${vix}/common/img/none.gif" alt="" /><small>日程安排</small></a></li>
		<li id="tp_06" class="dynamicMenuContent"><a href="#" onclick="loadContent('${vix}/common/vixIndexDataAction!goToDo.action');"><img src="${vix}/common/img/none.gif" alt="" /><small>待办事宜</small></a></li>
		<li id="tp_04"><a href="#" onclick="loadContent('${vix}/common/mailAction!goMailList.action');"><img src="${vix}/common/img/none.gif" alt="" /><small>邮件</small></a></li>
		<li id="tp_05"><a href="#" onclick="loadContent('${vix}/common/contactsAction!goList.action');"><img src="${vix}/common/img/none.gif" alt="" /><small>通讯录</small></a></li>
		<%-- 	<li id="tp_02"><a href="#" onclick="loadContent('${vix}/common/vixIndexDataAction!goToDo.action');"><img src="${vix}/common/img/none.gif" alt="" /><small>待办提醒</small></a></li>
		<li id="tp_07" class="dynamicMenuContent"><a href="#"><img src="${vix}/common/img/none.gif" alt="" /><small>客户</small></a></li>
		<li id="tp_08" class="dynamicMenuContent"><a href="#"><img src="${vix}/common/img/none.gif" alt="" /><small>销售报价</small></a></li>
		<li id="tp_09" class="dynamicMenuContent"><a href="#"><img src="${vix}/common/img/none.gif" alt="" /><small>电话</small></a></li> --%>
		<%-- <li id="feedback"><a href="#"><img src="${vix}/common/img/icon_feedback.png" alt="" /></a></li> --%>
	</ul>
	<div id="search">
		<input name="" type="text" class="sint" /><input name="" type="button" class="btn" id="search_btn" />
		<div id="s_select" style="display: none;">
			<div class="search_txt">
				<p>
					<a href="#">DEMO</a>
				</p>
				<p>
					<a href="#">DEMO</a>
				</p>
				<p>
					<a href="#">DEMO</a>
				</p>
				<p>
					<a href="#">DEMO</a>
				</p>
				<p>
					<a href="#">DEMO</a>
				</p>
				<p>
					<a href="#">DEMO</a>
				</p>
				<p>
					<a href="#">DEMO</a>
				</p>
				<p>
					<a href="#">DEMO</a>
				</p>
				<p>
					<a href="#">DEMO</a>
				</p>
				<p>
					<a href="#">DEMO</a>
				</p>
				<p>
					<a href="#">DEMO</a>
				</p>
				<p>
					<a href="#">DEMO</a>
				</p>
				<p>
					<a href="#">DEMO</a>
				</p>
				<p>
					<a href="#">DEMO</a>
				</p>
				<p>
					<a href="#">DEMO</a>
				</p>
				<p>
					<a href="#">DEMO</a>
				</p>
				<p>
					<a href="#">DEMO</a>
				</p>
				<p>
					<a href="#">DEMO</a>
				</p>
				<p>
					<a href="#">DEMO</a>
				</p>
				<p>
					<a href="#">DEMO</a>
				</p>
				<p>
					<a href="#">DEMO</a>
				</p>
				<p>
					<a href="#">DEMO</a>
				</p>
				<p>
					<a href="#">DEMO</a>
				</p>
				<p>
					<a href="#">DEMO</a>
				</p>
				<p>
					<a href="#">DEMO</a>
				</p>
				<p>
					<a href="#">DEM1O</a>
				</p>
			</div>
			<div class="search_bot"></div>
		</div>
	</div>
	<div class="skin">
		<ul>
			<li id="skin_01" class="this"><a onclick="javascript:changeStyle(1,'skin',6);"><img src="${vix}/common/img/none.gif" alt="" /></a></li>
			<li id="skin_02"><a onclick="javascript:changeStyle(2,'skin',6);"><img src="${vix}/common/img/none.gif" alt="" /></a></li>
			<li id="skin_03"><a onclick="javascript:changeStyle(3,'skin',6);"><img src="${vix}/common/img/none.gif" alt="" /></a></li>
			<li id="skin_04"><a onclick="javascript:changeStyle(4,'skin',6);"><img src="${vix}/common/img/none.gif" alt="" /></a></li>
			<li id="skin_05"><a onclick="javascript:changeStyle(5,'skin',6);"><img src="${vix}/common/img/none.gif" alt="" /></a></li>
			<li id="skin_06"><a onclick="javascript:changeStyle(6,'skin',6);"><img src="${vix}/common/img/none.gif" alt="" /></a></li>
		</ul>
		<ul>
			<li id="font_01"><a onclick="javascript:changeStyle(1,'font',3);"><img src="${vix}/common/img/none.gif" alt="" /></a></li>
			<li id="font_02" class="this"><a onclick="javascript:changeStyle(2,'font',3);"><img src="${vix}/common/img/none.gif" alt="" /></a></li>
			<li id="font_03"><a onclick="javascript:changeStyle(3,'font',3);"><img src="${vix}/common/img/none.gif" alt="" /></a></li>
		</ul>
	</div>
</div>