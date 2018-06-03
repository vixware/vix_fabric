<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
/* 搜索功能 */
var name = "";
var bookName = "";
var bookCode = "";
var ISBN = "";
var author ="";
var press ="";
var folio ="";
var rev ="";
var impression ="";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function loadBookCode(){
	bookCode = $('#book_bookCode').val();
	bookCode = Url.encode(bookCode);
	bookCode = Url.encode(bookCode);
}
function loadBookname(){
	bookName = $('#book_bookName').val();
	bookName = Url.encode(bookName);
	bookName = Url.encode(bookName);
}
function loadISBN(){
	ISBN = $('#book_ISBN').val();
	ISBN = Url.encode(ISBN);
	ISBN = Url.encode(ISBN);
}
function loadBookAuthor(){
	author = $('#book_author').val();
	author = Url.encode(author);
	author = Url.encode(author);
}
function loadPress(){
	press = $('#book_press').val();
	press = Url.encode(press);
	press = Url.encode(press);
}
function loadFolio(){
	folio = $('#book_folio').val();
	folio = Url.encode(folio);
	folio = Url.encode(folio);
}
function loadRev(){
	rev = $('#book_rev').val();
	rev = Url.encode(rev);
	rev = Url.encode(rev);
}
function loadImpression(){
	impression = $('#book_impression').val();
	impression = Url.encode(impression);
	impression = Url.encode(impression);
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
		$("#book_bookName").val("");
		$("#book_bookCode").val("");
		$("#book_ISBN").val("");
		$("#book_author").val("");
		$("#book_press").val("");
		$("#book_folio").val("");
		$("#book_rev").val("");
		$("#book_impression").val("");
	}
}

/*搜索*/
function searchForContent(i){
	loadName();
	if(i == 0){
		pager("start","${vix}/oa/bookEntryAction!goSearchList.action?i="+i+"&bookName="+name,'bookEntry');
	}
}

//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/oa/bookEntryAction!goSearch.action',
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
				pager("start", "${vix}/oa/bookEntryAction!goSearchList.action?bookCode=" + $('#bookCode').val() 
						+ "&bookName=" + $('#bookName').val()
						+ "&ISBN=" + $('#ISBN').val()
						+ "&author=" + $('#author').val()
						+ "&press=" + $('#press').val()
						+ "&folio=" + $('#folio').val()
						+ "&rev=" + $('#rev').val()
						+ "&impression=" + $('#impression').val(), 'bookEntry');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};

$('#numBtn').click(function(){
	$('#numBtn').parent("li").toggleClass("current");
	$('#number').animate({height: 'toggle', opacity: 'toggle'},500,function(){$('#number').css('overflow','visible');});
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

//图书录入
function saveOrUpdate(id,parentId){
	$.ajax({
		  url:'${vix}/oa/bookEntryAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 660,
					height : 380,
					title:"图书录入",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							$("#bookCabinetForm").ajaxSubmit({
							     type: "post",
							     url: "${vix}/oa/bookEntryAction!saveOrUpdate.action",
							     dataType: "text",
							     success: function(result){
							    	showMessage(result);
									setTimeout("", 1000);
									loadContent('${vix}/oa/bookEntryAction!goList.action');
							     }
							 });
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};
	
function deleteById(id){
	$.ajax({
		  url:'${vix}/oa/bookEntryAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='cmn_message'/>",function(action){
				var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
				if(null != treeNode){
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes(treeNode, "refresh");
				pager("start","${vix}/oa/bookEntryAction!goSingleList.action?bookName="+bookName,'bookEntry');
			});
		  }
	});
}

function searchForRightContent(){
	loadName();
	pager("start","${vix}/oa/bookEntryAction!goSingleList.action?name="+name,'bookEntry');
}
 

 
loadName();
//载入分页列表数据
pager("start","${vix}/oa/bookEntryAction!goSingleList.action?name="+name,'bookEntry');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/oa/bookEntryAction!goSingleList.action?name=",'bookEntry');
}

//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#bookEntryOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/oa/bookEntryAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'bookEntry');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/oa/bookEntryAction!goSingleList.action?name="+name,'bookEntry');
}

$(".drop>ul>li").bind('mouseover',function(){
	$(this).children('ul').delay(400).slideDown('fast');
}).bind('mouseleave',function(){
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast');
});



function showOrder(id){
	$.ajax({
		  url:'${vix}/oa/bookEntryAction!showOrder.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
}

//新增图书分类
function saveOrUpdateCategory(id,parentId){
	$.ajax({
		  url:'${vix}/oa/bookEntryAction!goSaveOrUpdateCategory.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 660,
					height : 380,
					title:"新增图书分类",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							$("#bookCategoryForm").ajaxSubmit({
							     type: "post",
							     url: "${vix}/oa/bookEntryAction!saveOrUpdateCategory.action",
							     dataType: "text",
							     success: function(result){
							    	showMessage(result);
									setTimeout("", 1000);
									loadContent('${vix}/oa/bookEntryAction!goList.action');
							     }
							 });
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};

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
				<li><a href="#"><img src="img/oa/oa_book.png" alt="" /> 协同办公</a></li>
				<li><a href="#">行政办公</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/bookEntryAction!goList.action?pageNo=${pageNo}');">图书管理管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/bookEntryAction!goList.action?pageNo=${pageNo}');">图书录入</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdateCategory(0);"><span>新增分类 </span> </a> <a href="#" onclick="saveOrUpdate(0);"><span>新增图书 </span> </a>
		</p>
	</div>
</div>

<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
			<li class="fly"><a href="#"><img alt="" src="img/leastRecentlyUsed.png"> <s:text name="cmn_recently_used" /> </a>
				<ul>
					<li><a href="#" onclick="leastRecentlyUsed('THREEDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('SEVENDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('ONEMONTH');"><img alt="" src="img/time_go.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('THREEMONTHS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div>
			<label>书名: <input type="text" class="int" id="nameS" style="width: 150px;"> <input type="button" value="搜索" class="btn" onclick="searchForContent(0);" />
			</label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" />
			</label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="bookEntryList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());"><span style="display: none;">${c.chineseFirstLetter}</span>${c.bookName}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<div class="left_content" style="height: 500px;">
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">	
			    var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/oa/bookEntryAction!findBookCategoryTree.action",
						autoParam:["id"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					pager("start","${vix}/oa/bookEntryAction!goSingleList.action?parentId="+treeNode.id,"bookEntry");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
			</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" />
		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><img src="img/common_listx16.png" alt="" />
					<s:text name="图书列表" /></li>
				</ul>
			</div>
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<%-- <ul>
						<li class="tp">
							<a href="#"><s:text name='cmn_operate'/></a>
							<ul>
								<li><a href="#"><s:text name='cmn_add'/></a></li>
								<li><a href="#"><s:text name='cmn_update'/></a></li>
								<li><a href="#"><s:text name='cmn_delete'/></a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
							</ul>
						</li>
					</ul>
					<strong><s:text name="cmn_selected"/>:<span id="selectbookEntryCount1">0</span></strong> --%>
					<div>
						<span><a class="start" onclick="bookEntryPager('start','bookEntry');"></a></span> <span><a class="previous" onclick="bookEntryPager('previous','bookEntry');"></a></span> <em>(<b class="bookEntryInfo"></b> <s:text name='cmn_to' /> <b class="bookEntryTotalCount"></b>)
						</em> <span><a class="next" onclick="bookEntryPager('next','bookEntry');"></a></span> <span><a class="end" onclick="bookEntryPager('end','bookEntry');"></a></span>
					</div>
				</div>
				<div id="bookEntry" class="table"></div>
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
					<strong><s:text name="cmn_selected"/>:<span id="selectbookEntryCount2">0</span></strong> --%>
					<div>
						<span><a class="start" onclick="bookEntryPager('start','bookEntry');"></a></span> <span><a class="previous" onclick="bookEntryPager('previous','bookEntry');"></a></span> <em>(<b class="bookEntryInfo"></b> <s:text name='cmn_to' /> <b class="bookEntryTotalCount"></b>)
						</em> <span><a class="next" onclick="bookEntryPager('next','bookEntry');"></a></span> <span><a class="end" onclick="bookEntryPager('end','bookEntry');"></a></span>
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