<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
/* 搜索功能 */
var name = "";
var code = "";
var polName ="";
var fileName ="";
var uploadPersonName ="";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function loadCode(){
	code = $('#public_code').val();
	code = Url.encode(code);
	code = Url.encode(code);
}
function loadPolName(){
	polName = $('#public_polName').val();
	polName = Url.encode(polName);
	polName = Url.encode(polName);
}
function loadFileName(){
	fileName = $('#public_fileName').val();
	fileName = Url.encode(fileName);
	fileName = Url.encode(fileName);
}
function loadUploadPersonName(){
	uploadPersonName = $('#public_uploadPersonName').val();
	uploadPersonName = Url.encode(uploadPersonName);
	uploadPersonName = Url.encode(uploadPersonName);
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
		$("#public_polName").val("");
		$("#public_fileName").val("");
		$("#public_uploadPersonName").val("");
	}
}

/*搜索*/
function searchForContent(i){
	loadName();
	pager("start","${vix}/hr/policiesAction!goSearchList.action?i="+i+"&code="+name,'policies');
}

//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/hr/policiesAction!goSearch.action',
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
				pager("start", "${vix}/hr/policiesAction!goSearchList.action?code=" + $('#code').val() + "&polName=" + $('#polName').val() + "&fileName=" + $('#fileName').val() + "&uploadPersonName=" + $('#uploadPersonName').val(), 'policies');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};

//状态
function saleOrderStatus(states){
	pager("start","${vix}/hr/policiesAction!goSingleList.action?states="+states,'policies');
}

 //最近使用
 function leastRecentlyUsed(createTime){
 	pager("start","${vix}/hr/policiesAction!goSingleList.action?createTime="+createTime,'policies');
 }

//面包屑
 if($('.sub_menu').length){
 	$("#breadCrumb").jBreadCrumb();
 }
 /* 加载顶部工具栏 */
 loadTopDynamicMenuContent('${vix}/hr/policiesAction!goTopDynamicMenuContent.action');


function saveOrUpdate(id,parentId){
	$.ajax({
		  url:'${vix}/hr/policiesAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 660,
					height : 380,
					title:"政策制度管理",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							$("#policiesForm").ajaxSubmit({
							     type: "post",
							     url: "${vix}/hr/policiesAction!saveOrUpdate.action",
							     dataType: "text",
							     success: function(result){
							    	showMessage(result);
									setTimeout("", 1000);
									loadContent('${vix}/hr/policiesAction!goList.action');
							     }
							 });
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};
function saveOrUpdateCategory(id,parentId){
	$.ajax({
		  url:'${vix}/hr/policiesAction!goSaveOrUpdateCategory.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 660,
					height : 380,
					title:"政策制度管理分类",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							$("#policiesCategoryForm").ajaxSubmit({
							     type: "post",
							     url: "${vix}/hr/policiesAction!saveOrUpdateCategory.action",
							     dataType: "text",
							     success: function(result){
							    	showMessage(result);
									setTimeout("", 1000);
									loadContent('${vix}/hr/policiesAction!goList.action');
							     }
							 });
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};
 
loadName();
//载入分页列表数据
pager("start","${vix}/hr/policiesAction!goSingleList.action?name="+name,'policies');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/hr/policiesAction!goSingleList.action?name=",'policies');
}
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#policiesOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/hr/policiesAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'policies');
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
				<li><a href="#" onclick="loadContent('${vix}/hr/policiesAction!goList.action?pageNo=${pageNo}');"><img src="img/hr_empma.png" alt="" />
					<s:text name="hr_humanr_resources" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/policiesAction!goList.action?pageNo=${pageNo}');"><s:text name="hr_staff_manage" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/policiesAction!goList.action?pageNo=${pageNo}');"><s:text name='hr_policy_system_manage' /></a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdateCategory(0);"><span>新增分类 </span> </a> <a href="#" onclick="saveOrUpdate(0);"><span>新增文件</span> </a>
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
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="状态" /> </a>
				<ul>
					<li><a href="#" onclick="saleOrderStatus('1')"><img alt="" src="img/uncommitted.png"> <s:text name="办公用品管理制度" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('2')"><img alt="" src="img/unaudited.png"> <s:text name="会议管理制度" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('3')"><img alt="" src="img/unaudited.png"> <s:text name="档案管理制度" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('4')"><img alt="" src="img/unaudited.png"> <s:text name="保密制度" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('5')"><img alt="" src="img/unaudited.png"> <s:text name="电脑使用管理规定" /> </a></li>
				</ul></li>
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
			<s:iterator value="polSysManageList" var="c">
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
						url:"${vix}/hr/policiesAction!findKnowledgeBaseCategoryTree.action",
						autoParam:["id"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					pager("start","${vix}/hr/policiesAction!goSingleList.action?parentId="+treeNode.id,"polSysManage");
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
					<li class="current"><img src="img/mail.png" alt="" /> <s:text name="政策制度管理" /></li>
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
						<span><a class="start" onclick="policiesPager('start','policies');"></a></span> <span><a class="previous" onclick="policiesPager('previous','policies');"></a></span> <em>(<b class="policiesInfo"></b> <s:text name='cmn_to' /> <b class="policiesTotalCount"></b>)
						</em> <span><a class="next" onclick="policiesPager('next','policies');"></a></span> <span><a class="end" onclick="policiesPager('end','policies');"></a></span>
					</div>
				</div>
				<div id="policies" class="table"></div>
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
						<span><a class="start" onclick="policiesPager('start','policies');"></a></span> <span><a class="previous" onclick="policiesPager('previous','policies');"></a></span> <em>(<b class="policiesInfo"></b> <s:text name='cmn_to' /> <b class="policiesTotalCount"></b>)
						</em> <span><a class="next" onclick="policiesPager('next','policies');"></a></span> <span><a class="end" onclick="policiesPager('end','policies');"></a></span>
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