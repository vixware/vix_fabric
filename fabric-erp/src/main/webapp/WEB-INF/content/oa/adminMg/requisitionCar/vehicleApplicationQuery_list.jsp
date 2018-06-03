<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
/* 搜索功能 */
var name = "";
var carName = "";
var reasons ="";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}

$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});
 
 
loadName();
//载入分页列表数据
pager("start","${vix}/oa/vehicleApplicationQueryAction!goSingleList.action?name="+name,'trends');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#trendsOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/oa/vehicleApplicationQueryAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name,'trends');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/oa/vehicleApplicationQueryAction!goSingleList.action?name="+name,'trends');
}

$(".drop>ul>li").bind('mouseover',function(){
	$(this).children('ul').delay(400).slideDown('fast');
}).bind('mouseleave',function(){
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast');
});
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
loadMenuContent('${vix}/oa/main/oaMainAction!goMenuContent.action');
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/mdm_vehicleRequest.png" alt="" /> 协同办公</a></li>
				<li><a href="#">行政办公</a></li>
				<li><a href="#">车辆申请安排</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/vehicleApplicationQueryAction!goList.action?pageNo=${pageNo}');">车辆查询管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div id="trends" class="table"></div>
			</div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>