<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
/* 搜索功能 */
var name = "";
var content = "";
var scheduleName = "";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function loadscheduleName(){
	scheduleName = $('#vote_scheduleName').val();
	scheduleName = Url.encode(scheduleName);
	scheduleName = Url.encode(scheduleName);
}
function loadcontent(){
	calendarsContent = $('#vote_calendarsContent').val();
	calendarsContent = Url.encode(calendarsContent);
	calendarsContent = Url.encode(calendarsContent);
}


/*判断搜索内容是否为空*/
function validateSearch(temp){
	if(null == temp || "" == temp){
		return false;
	}
	return true;
}

/*重置搜索*/
function resetForContent(i){
	if(i == 0){
		$("#nameS").val("");
	}
	else{
		$("#vote_scheduleName").val("");
		$("#vote_calendarsContent").val("");
	}
}

/*搜索*/
function searchForContent(i){
	loadName();
	if(i == 0){
		pager("start","${vix}/oa/schedulesAction!goSearchList.action?i="+i+"&scheduleName="+name,'voting');
	}
	else{
		pager("start","${vix}/oa/schedulesAction!goSearchList.action?i="+i+"&scheduleName="+scheduleName+"&calendarsContent="+calendarsContent,'voting');
	}
}

//状态
 function saleOrderStatus(view){
 	pager("start","${vix}/oa/schedulesAction!goSingleList.action?view="+view,'voting');
 }
 //最近使用
 function leastRecentlyUsed(startTime){
 	pager("start","${vix}/oa/schedulesAction!goSingleList.action?startTime="+startTime,'voting');
 }


$('#numBtn').click(function(){
	$('#numBtn').parent("li").toggleClass("current");
	$('#number').stop().animate({height: 'toggle', opacity: 'toggle'},500,function(){
		$('#number').css('overflow','visible');
	}); 
	return false;
});
$(function(){
	if($('#numBox').length) $('#numBox').listmenu({menuWidth: '100%', cols:{ count:6, gutter:0 }});
});
$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});

 
loadName();
//载入分页列表数据
pager("start","${vix}/oa/schedulesAction!goSingleList.action?name="+name,'voting');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#votingOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/oa/schedulesAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name,'voting');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/oa/schedulesAction!goSingleList.action?name="+name,'voting');
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

//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/oa/schedulesAction!goSearch.action',
	cache : false,
	success : function(html) {
		asyncbox.open({
		modal : true,
		width : 650,
		height : 300,
		title : "查询条件",
		html : html,
		callback : function(action) {
			if (action == 'ok') {
				pager("start", "${vix}/oa/schedulesAction!goSearchList.action?scheduleName=" + $('#scheduleName').val() + "&calendarsContent=" + $('#calendarsContent').val(), 'voting');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/schedule.png" alt="" />
					<s:text name="oa_xtbg" /></a></li>
				<li><a href="#"><s:text name="oa_administrative_civil" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/schedulesAction!goList.action?pageNo=${pageNo}');"><s:text name="oa_schedule_arrangements_query" /></a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img alt="" src="img/icon_11.png"></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<%-- <li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li> --%>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="类型" /> </a>
				<ul>
					<li><a href="#" onclick="saleOrderStatus('month')"><img alt="" src="img/uncommitted.png"> <s:text name="月期事物" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('agendaWeek')"><img alt="" src="img/unaudited.png"> <s:text name="周期事物" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('agendaDay')"><img alt="" src="img/unaudited.png"> <s:text name="日常事物" /> </a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/leastRecentlyUsed.png"> <s:text name="最近开始" /> </a>
				<ul>
					<li><a href="#" onclick="leastRecentlyUsed('THREEDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('SEVENDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('ONEMONTH');"><img alt="" src="img/time_go.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('THREEMONTHS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div>
			<label>主题: <input type="text" class="int" id="nameS" style="width: 150px;"> <input type="button" value="搜索" class="btn" onclick="searchForContent(0);" />
			</label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" />
			</label>
		</div>
	</div>
	<%-- <div id="number">
		<span class="num_left_bg"></span>
		<span class="num_right_bg"></span>
        <ul id="numBox" class="numBox">
        	<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
        	<s:iterator value="calendarsList" var="c">
        		<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());"><span style="display:none;">${c.chineseFirstLetter}</span>${c.title}</a></li>
        	</s:iterator>
        </ul>
    </div> --%>
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<%-- <ul>
						<li class="tp">
							<a href="#"><s:text name='cmn_choose'/></a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete'/></a></li>
								<li><a href="#"><s:text name='cmn_mail'/></a></li>
								<li><a href="#"><s:text name="cmn_merger"/></a></li>
								<li><a href="#"><s:text name="cmn_export"/></a></li>
							</ul>
						</li>
					</ul> 
					<strong><s:text name="cmn_selected"/>:<span id="selectCount1">0</span></strong>--%>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="votingInfo"></b> <s:text name='cmn_to' /> <b class="votingTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="voting" class="table" style="overflow-x: auto; width: 100%;"></div>
				<div class="pagelist drop">
					<%-- <ul>
						<li class="ed">
							<a href="#"><s:text name='cmn_choose'/></a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete'/></a></li>
								<li><a href="#"><s:text name='cmn_mail'/></a></li>
								<li><a href="#"><s:text name="cmn_merger"/></a></li>
								<li><a href="#"><s:text name="cmn_export"/></a></li>
							</ul>
						</li>
					</ul> 
					<strong><s:text name="cmn_selected"/>:<span id="selectCount2">0</span></strong>--%>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="votingInfo"></b> <s:text name='cmn_to' /> <b class="votingTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
			</div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>