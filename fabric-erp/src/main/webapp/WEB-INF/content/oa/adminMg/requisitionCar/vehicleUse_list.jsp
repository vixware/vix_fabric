<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
/*  申请使用车辆 */
function saveOrUpdate(id){
	$.ajax({
		  url:'${vix}/oa/vehicleUseAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
} 


function updateBookingSituation(id,bookingSituation){
	var param={'id':id,'bookingSituation':bookingSituation};  
	$.ajax({ 
		url:"${vix}/oa/vehicleUseAction!updateBookingSituation.action",
		data:param,
		dataType:"text",
		success:function(data){
			/* alert(bookingSituation); */
			loadName();
			pager("start","${vix}/oa/vehicleUseAction!goSingleList.action?name="+name,'trends');
		}
		});
}

/* 搜索功能 */
var name = "";
var carName = "";
var theme = "";
var reasons ="";
var pubNames ="";
var startTime ="";
var endTime ="";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}

function loadCarName(){
	carName = $('#carUse_carName').val();
	carName = Url.encode(carName);
	carName = Url.encode(carName);
}

function loadTheme(){
	theme = $('#carUse_theme').val();
	theme = Url.encode(theme);
	theme = Url.encode(theme);
}

function loadReasons(){
	reasons = $('#carUse_reasons').val();
	reasons = Url.encode(reasons);
	reasons = Url.encode(reasons);
}
function loadPubNames(){
	pubNames = $('#carUse_pubNames').val();
	pubNames = Url.encode(pubNames);
	pubNames = Url.encode(pubNames);
}
function loadStartTime(){
	startTime = $('#carUse_startTime').val();
	startTime = Url.encode(startTime);
	startTime = Url.encode(startTime);
}
function loadEndTime(){
	endTime = $('#carUse_endTime').val();
	endTime = Url.encode(endTime);
	endTime = Url.encode(endTime);
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
		$("#carUse_carName").val("");
		$("#carUse_theme").val("");
		$("#carUse_reasons").val("");
		$("#carUse_pubNames").val("");
		$("#carUse_startTime").val("");
		$("#carUse_endTime").val("");
	}
}

/*搜索*/
function searchForContent(i){
	loadName();
	if(i == 0){
		pager("start","${vix}/oa/vehicleUseAction!goSearchList.action?i="+i+"&carName="+name,'trends');
	}
}

//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/oa/vehicleUseAction!goSearch.action',
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
				pager("start", "${vix}/oa/vehicleUseAction!goSearchList.action?carName=" + $('#carName').val() + "&theme=" + $('#theme').val() + "&reasons=" + $('#reasons').val() + "&pubNames=" + $('#pubNames').val() + "&startTime=" + $('#startTime').val() + "&endTime=" + $('#endTime').val(), 'trends');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
}; 

//状态
 function saleOrderStatus(bookingSituation){
 	pager("start","${vix}/oa/vehicleUseAction!goSingleList.action?bookingSituation="+bookingSituation,'trends');
 }
 //最近使用
 function leastRecentlyUsed(startTime){
 	pager("start","${vix}/oa/vehicleUseAction!goSingleList.action?createTime="+startTime,'trends');
 }



$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});

function deleteById(id){
	$.ajax({
		  url:'${vix}/oa/vehicleUseAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='车辆使用管理'/>",function(action){
				var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
				if(null != treeNode){
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes(treeNode, "refresh");
				pager("start","${vix}/oa/vehicleUseAction!goSingleList.action?name="+name,'trends');
			});
		  }
	});
}
 
 
loadName();
//载入分页列表数据
pager("start","${vix}/oa/vehicleUseAction!goSingleList.action?name="+name,'trends');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#trendsOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/oa/vehicleUseAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name,'trends');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/oa/vehicleUseAction!goSingleList.action?name="+name,'trends');
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
				<li><a href="#" onclick="loadContent('${vix}/oa/vehicleUseAction!goList.action?pageNo=${pageNo}');">车辆使用管理</a></li>
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
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="状态" /> </a>
				<ul>
					<li><a href="#" onclick="saleOrderStatus('0')"><img alt="" src="img/uncommitted.png"> <s:text name="待批准" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('1')"><img alt="" src="img/unaudited.png"> <s:text name="已批准" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('2')"><img alt="" src="img/unaudited.png"> <s:text name="被驳回" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('3')"><img alt="" src="img/unaudited.png"> <s:text name="完成" /> </a></li>
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
			<label>用车人: <input type="text" class="int" id="nameS" style="width: 150px;"> <input type="button" value="搜索" class="btn" onclick="searchForContent(0);" />
			</label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" />
			</label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="carUseList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());"><span style="display: none;">${c.chineseFirstLetter}</span>${c.reasons}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_choose' /></a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /></a></li>
								<li><a href="#"><s:text name='cmn_mail' /></a></li>
								<li><a href="#"><s:text name="cmn_merger" /></a></li>
								<li><a href="#"><s:text name="cmn_export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="trendsInfo"></b> <s:text name='cmn_to' /> <b class="trendsTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="trends" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /></a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /></a></li>
								<li><a href="#"><s:text name='cmn_mail' /></a></li>
								<li><a href="#"><s:text name="cmn_merger" /></a></li>
								<li><a href="#"><s:text name="cmn_export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="trendsInfo"></b> <s:text name='cmn_to' /> <b class="trendsTotalCount"></b>)
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