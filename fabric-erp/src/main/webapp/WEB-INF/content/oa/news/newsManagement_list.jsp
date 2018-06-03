<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
function popNews(id){
	$.ajax({
		  url:"${vix}/oa/newsManagementAction!goSeenoticenotice.action?id="+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 780,
					height :460,
					title:"查看新闻",
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


function saveOrUpdate(id){
	$.ajax({
		  url:"${vix}/oa/newsManagementAction!goSaveOrUpdate.action?id="+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 760,
					height : 370,
					title:"发表评论",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#trendsForm').validationEngine('validate')){
								var str="";   
								$("input[name='allowedTime']:checkbox:checked").each(function(){   
									str+=$(this).val()+",";   
								});   
								//alert(str);  
								$.post('${vix}/oa/newsManagementAction!saveOrUpdate.action',
									 {'comments.trends.id':id,
									  'comments.commentscontent':commentscontents.html()
									},
									function(result){
										showMessage(result);
										setTimeout("",1000);
										pager("start","${vix}/oa/newsManagementAction!goSingleList.action?name="+name,'trends');
									}
								 ); 
			  				}else{
			  					return false;
			  				}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};


/* 搜索功能 */
var name = "";
var title = "";
var keywords = "";
var subtitle = "";
var uploadPersonName ="";
var bizOrgNames ="";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function loadTitle(){
	title = $('#trends_title').val();
	title = Url.encode(title);
	title = Url.encode(title);
}
function loadKeywords(){
	keywords = $('#trends_keywords').val();
	keywords = Url.encode(keywords);
	keywords = Url.encode(keywords);
}
function loadSubtitle(){
	subtitle = $('#trends_subtitle').val();
	subtitle = Url.encode(subtitle);
	subtitle = Url.encode(subtitle);
}
function loadUploadPersonName(){
	uploadPersonName = $('#trends_uploadPersonName').val();
	uploadPersonName = Url.encode(uploadPersonName);
	uploadPersonName = Url.encode(uploadPersonName);
}
function loadBizOrgNames(){
	bizOrgNames = $('#trends_bizOrgNames').val();
	bizOrgNames = Url.encode(bizOrgNames);
	bizOrgNames = Url.encode(bizOrgNames);
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
		$("#trends_title").val("");
		$("#trends_uploadPersonName").val("");
		$("#trends_bizOrgNames").val("");
		$("#trends_keywords").val("");
		$("#trends_subtitle").val("");
	}
}

/*搜索*/
function searchForContent(i){
	loadName();
	if(i == 0){
		pager("start","${vix}/oa/newsManagementAction!goSearchList.action?i="+i+"&title="+name,'trends');
	}
}

//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/oa/newsManagementAction!goSearch.action',
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
				pager("start", "${vix}/oa/newsManagementAction!goSearchList.action?title=" + $('#title').val()
						+ "&uploadPersonName=" + $('#uploadPersonName').val()
						+ "&keywords=" + $('#keywords').val()
						+ "&subtitle=" + $('#subtitle').val()
						+ "&pubNames=" + $('#pubNames').val(), 'announcement');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};

//状态
 function saleOrderStatus(newsType){
 	pager("start","${vix}/oa/newsManagementAction!goSingleList.action?newsType="+newsType,'trends');
 }
 //最近使用
 function leastRecentlyUsed(createTime){
 	pager("start","${vix}/oa/newsManagementAction!goSingleList.action?createTime="+createTime,'trends');
 }




$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});

function deleteById(id){
	$.ajax({
		  url:'${vix}/oa/newsManagementAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='cmn_message'/>",function(action){
				var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
				if(null != treeNode){
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes(treeNode, "refresh");
				pager("start","${vix}/oa/newsManagementAction!goSingleList.action?name="+name,'trends');
			});
		  }
	});
}
 
 
loadName();
//载入分页列表数据
pager("start","${vix}/oa/newsManagementAction!goSingleList.action?name="+name,'trends');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#trendsOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/oa/newsManagementAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name,'trends');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/oa/newsManagementAction!goSingleList.action?name="+name,'trends');
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
				<li><a href="#"><img src="${vix}/common/img/oa/oa_news.png" alt="" /> <s:text name="协同办公" /> </a></li>
				<li><a href="#"><s:text name="个人公办" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/newsManagementAction!goList.action?pageNo=${pageNo}');"><s:text name="新闻" /></a></li>
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
					<li><a href="#" onclick="saleOrderStatus('0')"><img alt="" src="img/uncommitted.png"> <s:text name="文本" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('1')"><img alt="" src="img/unaudited.png"> <s:text name="图片" /> </a></li>
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
			<s:iterator value="trendsList" var="c">
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