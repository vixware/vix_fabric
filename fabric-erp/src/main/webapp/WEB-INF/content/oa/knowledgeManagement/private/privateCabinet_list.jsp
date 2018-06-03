<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
/* 搜索功能 */
var name = "";
var code = "";
var keyword = "";
var fileName ="";
var memo ="";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function loadcode(){
	code = $('#public_code').val();
	code = Url.encode(code);
	code = Url.encode(code);
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
function loadmemo(){
	memo = $('#public_memo').val();
	memo = Url.encode(memo);
	memo = Url.encode(memo);
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
		$("#public_code").val("");
		$("#public_keyword").val("");
		$("#public_fileName").val("");
		$("#public_memo").val("");
	}
}

/*搜索*/
function searchForContent(i){
	loadName();
	if(i == 0){
		pager("start","${vix}/oa/privateCabinetAction!goSearchList.action?i="+i+"&fileName="+name,'privateCabinet');
	}
}

//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/oa/privateCabinetAction!goSearch.action',
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
				pager("start", "${vix}/oa/privateCabinetAction!goSearchList.action?code=" + $('#code').val() 
						+ "&keyword=" + $('#keyword').val()
						+ "&fileName=" + $('#fileName').val()
						+ "&memo=" + $('#memo').val(), 'privateCabinet');
				}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};

 //最近使用
 function leastRecentlyUsed(createTime){
 	pager("start","${vix}/oa/privateCabinetAction!goSingleList.action?createTime="+createTime,'privateCabinet');
 }

//面包屑
 if($('.sub_menu').length){
 	$("#breadCrumb").jBreadCrumb();
 }
 loadMenuContent('${vix}/oa/main/oaMainAction!goMenuContent.action');

function saveOrUpdate(id,parentId){
	$.ajax({
		  url:'${vix}/oa/privateCabinetAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 660,
					height : 380,
					title:"个人文件",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							$("#privateCabinetForm").ajaxSubmit({
							     type: "post",
							     url: "${vix}/oa/privateCabinetAction!saveOrUpdate.action",
							     dataType: "text",
							     success: function(result){
							    	showMessage(result);
									setTimeout("", 1000);
									loadContent('${vix}/oa/privateCabinetAction!goList.action');
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
		  url:'${vix}/oa/privateCabinetAction!goSaveOrUpdateKnowledgeBaseCategory.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 660,
					height : 380,
					title:"个人文件分类",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							$("#privateCabinetCategoryForm").ajaxSubmit({
							     type: "post",
							     url: "${vix}/oa/privateCabinetAction!saveOrUpdateKnowledgeBaseCategory.action",
							     dataType: "text",
							     success: function(result){
							    	showMessage(result);
									setTimeout("", 1000);
									loadContent('${vix}/oa/privateCabinetAction!goList.action');
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
	pager("start","${vix}/oa/privateCabinetAction!goSingleList.action?name="+name,'privateCabinet');
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/oa/privateCabinetAction!goSingleList.action?name="+name,'privateCabinet');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/oa/privateCabinetAction!goSingleList.action?name=",'privateCabinet');
}
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#privateCabinetOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/oa/privateCabinetAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'privateCabinet');
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
				<li><a href="#"><s:text name="oa_grbg" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/privateCabinetAction!goList.action?pageNo=${pageNo}');"><s:text name="oa_personal_file_cabinet" /></a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdateKnowledgeBaseCategory(0);"><span>新增分类 </span> </a> <a href="#" onclick="saveOrUpdate(0);"><span>新增文件</span> </a>
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
			<label>文件名称: <input type="text" class="int" id="nameS" style="width: 150px;"> <input type="button" value="搜索" class="btn" onclick="searchForContent(0);" />
			</label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" />
			</label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="privateCabinetList" var="c">
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
						url:"${vix}/oa/privateCabinetAction!findKnowledgeBaseCategoryTree.action",
						autoParam:["id"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					pager("start","${vix}/oa/privateCabinetAction!goSingleList.action?parentId="+treeNode.id,"privateCabinet");
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
					<li class="current"><img src="img/mail.png" alt="" /> <s:text name="个人文件柜" /></li>
				</ul>
			</div>
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<%-- <ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_add' /> </a></li>
								<li><a href="#"><s:text name='cmn_update' /> </a></li>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount1">0</span> </strong> --%>
					<div>
						<span><a class="start" onclick="privateCabinetPager('start','privateCabinet');"></a> </span> <span><a class="previous" onclick="privateCabinetPager('previous','privateCabinet');"></a> </span> <em>(<b class="privateCabinetInfo"></b> <s:text name='cmn_to' /> <b class="privateCabinetTotalCount"></b>)
						</em> <span><a class="next" onclick="privateCabinetPager('next','privateCabinet');"></a> </span> <span><a class="end" onclick="privateCabinetPager('end','privateCabinet');"></a> </span>
					</div>
				</div>
				<div id="privateCabinet" class="table"></div>
				<div class="pagelist drop">
					<%-- <ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount2">0</span> </strong> --%>
					<div>
						<span><a class="start" onclick="privateCabinetPager('start','privateCabinet');"></a> </span> <span><a class="previous" onclick="privateCabinetPager('previous','privateCabinet');"></a> </span> <em>(<b class="privateCabinetInfo"></b> <s:text name='cmn_to' /> <b class="privateCabinetTotalCount"></b>)
						</em> <span><a class="next" onclick="privateCabinetPager('next','privateCabinet');"></a> </span> <span><a class="end" onclick="privateCabinetPager('end','privateCabinet');"></a> </span>
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