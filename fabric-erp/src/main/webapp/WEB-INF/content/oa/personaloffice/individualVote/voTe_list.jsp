<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
/* 查看 */
function saveOrUpdate(id){
	$.ajax({
		  url:"${vix}/oa/voTeAction!goSaveOrUpdate.action?id="+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 780,
					height :460,
					title:"查看投票",
					html:html,
					callback : function(action){
						if(action == 'ok'){
								}
							} ,
							btnsbar : [{
								text :'关闭',
								action :'cancel'
							}]
				});
		  }
	});
};

/* 投票 */
function saveOrUpdate1(id){
	$.ajax({
		  url:'${vix}/oa/voTeAction!goSaveOrUpdate1.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
} 

/* 搜索功能 */
var name = "";
var title = "";
var voteDescribe = "";
var title = "";
var uploadPersonName ="";
var pubNames ="";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function loadTitle(){
	title = $('#vote_title').val();
	title = Url.encode(title);
	title = Url.encode(title);
}
function loadVoteDescribe(){
	voteDescribe = $('#vote_voteDescribe').val();
	voteDescribe = Url.encode(voteDescribe);
	voteDescribe = Url.encode(voteDescribe);
}
function loadRemarks(){
	remarks = $('#vote_remarks').val();
	remarks = Url.encode(remarks);
	remarks = Url.encode(remarks);
}
function loadUploadPersonName(){
	uploadPersonName = $('#vote_uploadPersonName').val();
	uploadPersonName = Url.encode(uploadPersonName);
	uploadPersonName = Url.encode(uploadPersonName);
}
function loadPubNames(){
	pubNames = $('#vote_pubNames').val();
	pubNames = Url.encode(pubNames);
	pubNames = Url.encode(pubNames);
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
		$("#vote_title").val("");
		$("#vote_voteDescribe").val("");
		$("#vote_remarks").val("");
		$("#vote_uploadPersonName").val("");
		$("#vote_pubNames").val("");
	}
}

/*搜索*/
function searchForContent(i){
	loadName();
	if(i == 0){
		pager("start","${vix}/oa/voTeAction!goSearchList.action?i="+i+"&title="+name,'voTe');
	}
}

//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/oa/voTeAction!goSearch.action',
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
				pager("start", "${vix}/oa/voTeAction!goSearchList.action?title=" + $('#title').val()
						+ "&uploadPersonName=" + $('#uploadPersonName').val()
						+ "&voteDescribe=" + $('#voteDescribe').val()
						+ "&remarks=" + $('#remarks').val()
						+ "&pubNames=" + $('#pubNames').val(), 'voting');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};

//状态
function saleOrderStatus(publishType){
	pager("start","${vix}/oa/voTeAction!goSingleList.action?publishType="+publishType,'voTe');
}
//最近使用
function leastRecentlyUsed(startTime){
	pager("start","${vix}/oa/voTeAction!goSingleList.action?startTime="+startTime,'voTe');
}

$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});


loadName();
//载入分页列表数据
pager("start","${vix}/oa/voTeAction!goSingleList.action?name="+name,'voTe');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#voTeOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/oa/voTeAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name,'voTe');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/oa/voTeAction!goSingleList.action?name="+name,'voTe');
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
				<li><a href="#"><img src="${vix}/common/img/oa/oa_votes.png" alt="" /> <s:text name="oa_xtbg" /> </a></li>
				<li><a href="#"><s:text name="oa_grbg" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/voTeAction!goList.action?pageNo=${pageNo}');"><s:text name="oa_vote" /> </a></li>
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
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="类型" /> </a>
				<ul>
					<li><a href="#" onclick="saleOrderStatus('0')"><img alt="" src="img/uncommitted.png"> <s:text name="待发布" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('1')"><img alt="" src="img/unaudited.png"> <s:text name="发布" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('2')"><img alt="" src="img/unaudited.png"> <s:text name="终止" /> </a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/leastRecentlyUsed.png"> <s:text name="cmn_recently_used" /> </a>
				<ul>
					<li><a href="#" onclick="leastRecentlyUsed('THREEDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('SEVENDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('ONEMONTH');"><img alt="" src="img/time_go.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('THREEMONTHS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div>
			<label>标题: <input type="text" class="int" id="nameS" style="width: 150px;"> <input type="button" value="搜索" class="btn" onclick="searchForContent(0);" />
			</label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" />
			</label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="votingManagementList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());"><span style="display: none;">${c.chineseFirstLetter}</span>${c.title}</a></li>
			</s:iterator>
		</ul>
	</div>
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
					<strong><s:text name="cmn_selected"/>:<span id="selectCount1">0</span></strong> --%>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="voTeInfo"></b> <s:text name='cmn_to' /> <b class="voTeTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="voTe" class="table"></div>
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
					<strong><s:text name="cmn_selected"/>:<span id="selectCount2">0</span></strong> --%>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="voTeInfo"></b> <s:text name='cmn_to' /> <b class="voTeTotalCount"></b>)
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