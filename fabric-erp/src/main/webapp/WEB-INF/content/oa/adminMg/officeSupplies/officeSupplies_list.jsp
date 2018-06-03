<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
/* 搜索功能 */
var name = "";
var code = "";
var officeSuppliesName ="";
var officeSuppliesCode ="";
var currentInventory ="";
var lowestVigilance ="";
var maximumVigilance ="";
var supplier ="";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function loadModel(){
	model = $('#office_model').val();
	model = Url.encode(model);
	model = Url.encode(model);
}
function loadOfficeSuppliesName(){
	officeSuppliesName = $('#office_officeSuppliesName').val();
	officeSuppliesName = Url.encode(officeSuppliesName);
	officeSuppliesName = Url.encode(officeSuppliesName);
}
function loadOfficeSuppliesCode(){
	officeSuppliesCode = $('#office_officeSuppliesCode').val();
	officeSuppliesCode = Url.encode(officeSuppliesCode);
	officeSuppliesCode = Url.encode(officeSuppliesCode);
}
function loadCurrentInventory(){
	currentInventory = $('#office_currentInventory').val();
	currentInventory = Url.encode(currentInventory);
	currentInventory = Url.encode(currentInventory);
}
function loadLowestVigilance(){
	lowestVigilance = $('#office_lowestVigilance').val();
	lowestVigilance = Url.encode(lowestVigilance);
	lowestVigilance = Url.encode(lowestVigilance);
}
function loadMaximumVigilance(){
	maximumVigilance = $('#office_maximumVigilance').val();
	maximumVigilance = Url.encode(maximumVigilance);
	maximumVigilance = Url.encode(maximumVigilance);
}
function loadSupplier(){
	supplier = $('#office_supplier').val();
	supplier = Url.encode(supplier);
	supplier = Url.encode(supplier);
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
		$("#office_model").val("");
		$("#office_officeSuppliesName").val("");
		$("#office_officeSuppliesCode").val("");
		$("#office_currentInventory").val("");
		$("#office_lowestVigilance").val("");
		$("#office_maximumVigilance").val("");
		$("#office_supplier").val("");
	}
}

/*搜索*/
function searchForContent(i){
	loadName();
	if(i == 0){
		pager("start","${vix}/oa/officeSuppliesAction!goSearchList.action?i="+i+"&model="+name,'category');
	}
}

//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/oa/officeSuppliesAction!goSearch.action',
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
				pager("start", "${vix}/oa/officeSuppliesAction!goSearchList.action?model=" + $('#model').val() 
						+ "&officeSuppliesName=" + $('#officeSuppliesName').val()
						+ "&officeSuppliesCode=" + $('#officeSuppliesCode').val()
						+ "&currentInventory=" + $('#currentInventory').val()
						+ "&lowestVigilance=" + $('#lowestVigilance').val()
						+ "&maximumVigilance=" + $('#maximumVigilance').val()
						+ "&supplier=" + $('#supplier').val(), 'category');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};

 //最近使用
 function leastRecentlyUsed(startTime){
 	pager("start","${vix}/oa/officeSuppliesAction!goSingleList.action?startTime="+startTime,'category');
 }


$('#numBtn').click(function(){
	$('#numBtn').parent("li").toggleClass("current");
	$('#number').stop().animate({height: 'toggle', opacity: 'toggle'},500,function(){
		$('#number').css('overflow','visible');
	}); 
	return false;
});

$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});

$(function(){
	if($('#numBox').length) $('#numBox').listmenu({menuWidth: '100%', cols:{ count:6, gutter:0 }});
});


//办公用品录入
function saveOrUpdate(id,parentId){
	$.ajax({
		  url:'${vix}/oa/officeSuppliesAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 700,
					height : 380,
					title:"办公用品录入",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							$("#officeSuppliesForm").ajaxSubmit({
							     type: "post",
							     url: "${vix}/oa/officeSuppliesAction!saveOrUpdate.action",
							     dataType: "text",
							     success: function(result){
							    	showMessage(result);
									setTimeout("", 1000);
									loadContent('${vix}/oa/officeSuppliesAction!goList.action');
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
		  url:'${vix}/oa/officeSuppliesAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='cmn_message'/>",function(action){
				var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
				if(null != treeNode){
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes(treeNode, "refresh");
				pager("start","${vix}/oa/officeSuppliesAction!goSingleList.action?name="+name,'category');
			});
		  }
	});
}

function searchForRightContent(){
	loadName();
	pager("start","${vix}/oa/officeSuppliesAction!goSingleList.action?name="+name,'category');
}
 

 
loadName();
//载入分页列表数据
pager("start","${vix}/oa/officeSuppliesAction!goSingleList.action?name="+name,'category');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/oa/officeSuppliesAction!goSingleList.action?name=",'category');
}

//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#categoryOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/oa/officeSuppliesAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'category');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/oa/officeSuppliesAction!goSingleList.action?name="+name,'category');
}

$(".drop>ul>li").bind('mouseover',function(){
	$(this).children('ul').delay(400).slideDown('fast');
}).bind('mouseleave',function(){
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast');
});



function showOrder(id){
	$.ajax({
		  url:'${vix}/oa/officeSuppliesAction!showOrder.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
}

//新增图书分类
function saveOrUpdateCategory(id,parentId){
	$.ajax({
		  url:'${vix}/oa/officeSuppliesAction!goSaveOrUpdateCategory.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 660,
					height : 380,
					title:"新增办公用品分类",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							$("#bookCategoryForm").ajaxSubmit({
							     type: "post",
							     url: "${vix}/oa/officeSuppliesAction!saveOrUpdateCategory.action",
							     dataType: "text",
							     success: function(result){
							    	showMessage(result);
									setTimeout("", 1000);
									loadContent('${vix}/oa/officeSuppliesAction!goList.action');
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
				<li><a href="#"><img src="img/oa_office_Supplies.png" alt="" /> 协同办公</a></li>
				<li><a href="#">行政办公</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/officeSuppliesAction!goList.action?pageNo=${pageNo}');">办公用品管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/officeSuppliesAction!goList.action?pageNo=${pageNo}');">办公用品台帐</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdateCategory(0);"><span>新增分类 </span> </a> <a href="#" onclick="saveOrUpdate(0);"><span>新增办公用品</span> </a>
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
			<label>编码: <input type="text" class="int" id="nameS" style="width: 150px;"> <input type="button" value="搜索" class="btn" onclick="searchForContent(0);" />
			</label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" />
			</label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="officeSuppliesList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());"><span style="display: none;">${c.chineseFirstLetter}</span>${c.officeSuppliesName}</a></li>
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
						url:"${vix}/oa/officeSuppliesAction!findBookCategoryTree.action",
						autoParam:["id"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					pager("start","${vix}/oa/officeSuppliesAction!goSingleList.action?parentId="+treeNode.id,"category");
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
						<span><a class="start" onclick="categoryPager('start','category');"></a></span> <span><a class="previous" onclick="categoryPager('previous','category');"></a></span> <em>(<b class="categoryInfo"></b> <s:text name='cmn_to' /> <b class="categoryTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next','category');"></a></span> <span><a class="end" onclick="categoryPager('end','category');"></a></span>
					</div>
				</div>
				<div id="category" class="table"></div>
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
						<span><a class="start" onclick="categoryPager('start','category');"></a></span> <span><a class="previous" onclick="categoryPager('previous','category');"></a></span> <em>(<b class="categoryInfo"></b> <s:text name='cmn_to' /> <b class="categoryTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next','category');"></a></span> <span><a class="end" onclick="categoryPager('end','category');"></a></span>
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