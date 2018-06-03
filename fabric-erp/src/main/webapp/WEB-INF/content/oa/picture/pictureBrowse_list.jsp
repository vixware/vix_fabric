<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
/* 搜索功能 */
var name = "";
var keyword = "";
var fileName ="";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function loadKeyword(){
	keyword = $('#public_keyword').val();
	keyword = Url.encode(keyword);
	keyword = Url.encode(keyword);
}
function loadFileName(){
	fileName = $('#public_fileName').val();
	fileName = Url.encode(fileName);
	fileName = Url.encode(fileName);
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
		$("#public_keyword").val("");
		$("#public_fileName").val("");
	}
}

/*搜索*/
function searchForContent(i){
	loadName();
	loadKeyword();
	loadFileName();
	if(i == 0){
		pager("start","${vix}/oa/pictureBrowseAction!goSearchList.action?i="+i+"&fileName="+name,'pictureBrowse');
	}
	else{
		pager("start","${vix}/oa/pictureBrowseAction!goSearchList.action?i="+i+"&keyword="+keyword+"&fileName="+fileName,'pictureBrowse');
	}
}

/*改变搜索按钮的显示*/
 function changeDisplay(){
	var divText = $("#lb_search_advanced").text();
	if(divText == "高级搜索"){
		$("#nameS").attr({disabled:'true'});
		$("#simpleSearch").hide();
		$("#simpleReset").hide();
	}
	else{
		$("#nameS").removeAttr("disabled");
		$("#simpleSearch").show();
		$("#simpleReset").show();
	}
} 


 //最近使用
 function leastRecentlyUsed(createTime){
 	pager("start","${vix}/oa/pictureBrowseAction!goSingleList.action?createTime="+createTime,'pictureBrowse');
 }



//面包屑
 if($('.sub_menu').length){
 	$("#breadCrumb").jBreadCrumb();
 }
 loadMenuContent('${vix}/oa/main/oaMainAction!goMenuContent.action');

function saveOrUpdate(id,parentId){
	$.ajax({
		  url:'${vix}/oa/pictureBrowseAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 860,
					height : 380,
					title:"图片浏览",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							$("#pictureBrowseForm").ajaxSubmit({
							     type: "post",
							     url: "${vix}/oa/pictureBrowseAction!saveOrUpdate.action",
							     dataType: "text",
							     success: function(result){
							    	showMessage(result);
									setTimeout("", 1000);
									loadContent('${vix}/oa/pictureBrowseAction!goList.action');
							     }
							 });
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};
function saveOrUpdateKnowledgeBaseCategory(id,parentId){
	$.ajax({
		  url:'${vix}/oa/pictureBrowseAction!goSaveOrUpdateKnowledgeBaseCategory.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 660,
					height : 380,
					title:"图片分类",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							$("#pictureBrowseCategoryForm").ajaxSubmit({
							     type: "post",
							     url: "${vix}/oa/pictureBrowseAction!saveOrUpdateKnowledgeBaseCategory.action",
							     dataType: "text",
							     success: function(result){
							    	showMessage(result);
									setTimeout("", 1000);
									loadContent('${vix}/oa/pictureBrowseAction!goList.action');
							     }
							 });
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};
function searchForRightContent(){
	loadName();
	pager("start","${vix}/oa/pictureBrowseAction!goSingleList.action?name="+name,'pictureBrowse');
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/oa/pictureBrowseAction!goSingleList.action?name="+name,'pictureBrowse');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/oa/pictureBrowseAction!goSingleList.action?name=",'pictureBrowse');
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
	pager("start","${vix}/oa/pictureBrowseAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'pictureBrowse');
}
bindSearch();
bindSwitch();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/oa_file_cabinet_archive.png" alt="" /> <s:text name="oa_xtbg" /></a></li>
				<li><a href="#"><s:text name="知识管理" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/pictureBrowseAction!goList.action?pageNo=${pageNo}');"><s:text name="图片浏览" /></a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdateKnowledgeBaseCategory(0);"><span>新增分类 </span> </a> <a href="#" onclick="saveOrUpdate(0);"><span>添加图片 </span> </a>
		</p>
	</div>
</div>

<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /> </b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> <b>帮助</b>
				</strong>
				<p>Launched in January 1998, People's Daily Online is a website built by People's Daily, the official newspaper of the Communist Party of China. Containing</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
			<li class="fly"><a href="#"><img alt="" src="img/leastRecentlyUsed.png"> <s:text name="最近完成" /> </a>
				<ul>
					<li><a href="#" onclick="leastRecentlyUsed('THREEDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('SEVENDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('ONEMONTH');"><img alt="" src="img/time_go.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('THREEMONTHS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div>
			<label>文件名称:<input id="nameS" name="" type="text" class="int" /></label> <label> <input id="simpleSearch" name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent(0);" /> <input id="simpleReset" name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent(0);" />
			</label> <strong id="lb_search_advanced" onclick="changeDisplay();"><s:text name='cmn_advance_search' /></strong>
		</div>
		<div class="search_advanced">
			<label>姓名:<input id="public_keyword" name="" type="text" class="int" /></label> <label>文件名称:<input id="public_fileName" name="" type="text" class="int" /></label> <label> <input name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent(1);" /> <input name="" type="button" class="btn"
				value="<s:text name='cmn_reset'/>" onclick="resetForContent(1);" />
			</label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="pictureBrowseList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());"><span style="display: none;">${c.chineseFirstLetter}</span>${c.fileName}</a></li>
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
						url:"${vix}/oa/pictureBrowseAction!findKnowledgeBaseCategoryTree.action",
						autoParam:["id"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					pager("start","${vix}/oa/pictureBrowseAction!goSingleList.action?parentId="+treeNode.id,"pictureBrowse");
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
					<li class="current"><img src="img/mail.png" alt="" /> <s:text name="图片列表" /></li>
				</ul>
			</div>
			<div class="right_content" id="a1">

				<div id="pictureBrowse" class="table"></div>

			</div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>