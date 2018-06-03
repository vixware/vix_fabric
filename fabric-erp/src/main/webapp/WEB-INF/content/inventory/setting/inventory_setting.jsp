<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
loadMenuContent('${vix}/inventory/inventoryMainAction!goMenuContent.action');
	function loadDicContent(span,url) {
	    $("div[id='dic'] li").each(function() {
		    $(this).attr("style", "");
	    });
	    $(span).parent().attr("style", "background-color:#d0d0d0;");
	    $.ajax({
	    url : url,
	    cache : false,
	    success : function(html) {
		    $("#dictionary").html(html);
		    bindSwitch();
	    }
	    });
    }
    $("#ibc").click();
</script>
<style>
#dic ul {
	margin-right: 10px;
	padding-bottom: 10px;
	color: #000;
}

#dic ul li {
	margin-left: 15px;
	font-size: 12px;
	height: 18px;
	line-height: 25px;
}

#dic ul li a {
	color: #000;
}
</style>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/site.png" alt="" />供应链</a></li>
				<li><a href="#">库存管理</a></li>
				<li><a href="#">初始设置</a></li>
				<li><a href="#" onclick="${vix}/inventory/inventoryTypeAction!goList.action');">字典管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<div id="c_head" class="drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>字典管理</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left">
			<div id="dic">
				<ul style="margin-left: 15px;">
					<hr style="margin-left: -10px;" />
					<p style="font-size: 14px; margin-left: -6px; color: #606060;">入库管理</p>
					<li><span id="ibc" style="cursor: pointer;" onclick="loadDicContent(this,'${vix}/inventory/inventoryTypeAction!goList.action');"> 入库类别 </span></li>
				</ul>
			</div>
		</div>
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div id="dictionary"></div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>