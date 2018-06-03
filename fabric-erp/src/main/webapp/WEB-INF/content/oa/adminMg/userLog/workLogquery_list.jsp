<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
/* 搜索功能 */
var name = "";
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
function loadUploadPersonName(){
	uploadPersonName = $('#vote_uploadPersonName').val();
	uploadPersonName = Url.encode(uploadPersonName);
	uploadPersonName = Url.encode(uploadPersonName);
}
function loadLogContent(){
	logContent = $('#vote_logContent').val();
	logContent = Url.encode(logContent);
	logContent = Url.encode(logContent);
}


/*判断搜索内容是否为空*/
function validateSearch(temp){
	if(null == temp || "" == temp){
		return false;
	}
	return true;
}

/* 查看工作日志 */
function lookworkLog(id){
	$.ajax({
		  url:"${vix}/oa/workLogqueryAction!goLookworkLog.action?id="+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 780,
					height :460,
					title:"查看工作日志",
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

/*重置搜索*/
function resetForContent(i){
	if(i == 0){
		$("#nameS").val("");
	}
	else{
		$("#vote_title").val("");
		$("#vote_uploadPersonName").val("");
		$("#vote_logContent").val("");
	}
}

/*搜索*/
function searchForContent(i){
	loadName();
	if(i == 0){
		pager("start","${vix}/oa/workLogqueryAction!goSearchList.action?i="+i+"&uploadPersonName="+name,'workLog');
	}
	else{
		pager("start","${vix}/oa/workLogqueryAction!goSearchList.action?i="+i+"&title="+title+"&uploadPersonName="+uploadPersonName+"&logContent="+logContent,'workLog');
	}
}

//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/oa/workLogqueryAction!goSearch.action',
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
				pager("start", "${vix}/oa/workLogqueryAction!goSearchList.action?title=" + $('#title').val() + "&uploadPersonName=" + $('#uploadPersonName').val()+ "&logContent=" + $('#logContent').val(), 'workLog');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};

//状态
 function saleOrderStatus(logType){
 	pager("start","${vix}/oa/workLogqueryAction!goSingleList.action?logType="+logType,'workLog');
 }
 //最近使用
 function leastRecentlyUsed(logDate){
 	pager("start","${vix}/oa/workLogqueryAction!goSingleList.action?logDate="+logDate,'workLog');
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


function deleteByIds(){
	var ids = '';
	$("[name='chkId']").each(function(){  
		if($(this).attr('checked')){
			ids+=$(this).val()+",";  
		}
	});
	asyncbox.success(ids,"选中的id");
}

function deleteById(id){
	$.ajax({
		  url:'${vix}/oa/workLogqueryAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"提示信息",function(action){
				pager("start","${vix}/oa/workLogqueryAction!goSingleList.action?name="+name,'workLog');
			});
		  }
	});
}

/**发布人*/
function chooseEmployees(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
		  data:{chkStyle:"checkbox"},
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 800,
					height : 600,
					title:"发布人",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var selectIds = "";
								var selectNames = "";
								var result = returnValue.split(",");
								for (var i=0; i<result.length; i++){
									if(result[i].length>1){
										var v = result[i].split(":");
										
										selectIds += "," + v[0];
										selectNames = v[1];
										
									}
								}
								$("#nameS").val(selectNames);
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/oa/workLogqueryAction!goSingleList.action?name="+name,'workLog');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#workLogOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/oa/workLogqueryAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name,'workLog');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/oa/workLogqueryAction!goSingleList.action?name="+name,'workLog');
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
				<li><a href="#"><img src="${vix}/common/img/oa_poll_manager.png" alt="" /> <s:text name="oa_xtbg" /> </a></li>
				<li><a href="#"><s:text name="行政办公" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/workLogqueryAction!goList.action?pageNo=${pageNo}');"><s:text name="工作日志管理" /> </a></li>
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
			<li><a class="btn" type="button" onclick="chooseEmployees();"><img src="img/find.png" alt="" /> <s:text name="选择人员" /></a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="类型" /> </a>
				<ul>
					<li><a href="#" onclick="saleOrderStatus('0')"><img alt="" src="img/uncommitted.png"> <s:text name="工作日志" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('1')"><img alt="" src="img/unaudited.png"> <s:text name="个人日志" /> </a></li>
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
			<label>发布人: <input type="text" class="int" id="nameS" style="width: 150px;"> <input type="button" value="搜索" class="btn" onclick="searchForContent(0);" />
			</label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" />
			</label>
		</div>
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
					<strong><s:text name="cmn_selected"/>:<span id="selectCount1">0</span></strong>--%>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="workLogInfo"></b> <s:text name='cmn_to' /> <b class="workLogTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="workLog" class="table" style="overflow-x: auto; width: 100%;"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="workLogInfo"></b> <s:text name='cmn_to' /> <b class="workLogTotalCount"></b>)
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
</div>