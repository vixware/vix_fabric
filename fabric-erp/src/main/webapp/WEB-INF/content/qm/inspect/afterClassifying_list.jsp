<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
/* 搜索功能 */
var name = "";
var afterName = "";
var memo ="";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function loadAfterName(){
	afterName = $('#qm_afterName').val();
	afterName = Url.encode(afterName);
	afterName = Url.encode(afterName);
}
function loadMemo(){
	memo = $('#qm_memo').val();
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
		$("#qm_afterName").val("");
		$("#qm_memo").val("");
	}
}

/*搜索*/
function searchForContent(i){
	loadName();
	loadAfterName();
	loadMemo();
	if(i == 0){
		pager("start","${vix}/qm/afterClassifyingAction!goSearchList.action?i="+i+"&afterName="+name,'category');
	}
	else{
		pager("start","${vix}/qm/afterClassifyingAction!goSearchList.action?i="+i+"&afterName="+afterName+"&memo="+memo,'category');
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

function saveOrUpdate(id){
	$.ajax({
		  url:"${vix}/qm/afterClassifyingAction!goSaveOrUpdate.action?id="+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 660,
					height : 220,
					title:"样品分类",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#brandForm').validationEngine('validate')){
								$.post('${vix}/qm/afterClassifyingAction!saveOrUpdate.action',
									{'afterClassifying.id':$("#id").val(),
									  'afterClassifying.parentCategory.id':$("#parentId").val(),
									  'afterClassifying.afterName':$("#afterName").val(),
									  'afterClassifying.status':$('input:radio[name=status]').val(),
									  'afterClassifying.memo':$("#memo").val()								 
									},
									function(result){
										asyncbox.open({
											modal:true,
											width : 320,
											height : 50,
											title:"<s:text name='样品分类'/>",
											html:result,
											callback : function(action){
												pager("start","${vix}/qm/afterClassifyingAction!goSingleList.action?name="+name,'category');
											},
											btnsbar : $.btn.OKCANCEL
										});
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



function deleteById(id){
	$.ajax({
		  url:'${vix}/qm/afterClassifyingAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='删除'/>",function(action){
				var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
				if(null != treeNode){
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes(treeNode, "refresh");
				pager("start","${vix}/qm/afterClassifyingAction!goSingleList.action?name="+name,'category');
			});
		  }
	});
}

loadName();
//载入分页列表数据
pager("start","${vix}/qm/afterClassifyingAction!goSingleList.action?name="+name,'category');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/qm/afterClassifyingAction!goSingleList.action?name=",'category');
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
	pager("start","${vix}/qm/afterClassifyingAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'category');
}
bindSearch();
bindSwitch();

//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
loadMenuContent('${vix}/qm/pmMainAction!goMenuContent.action');

//加载顶部工具栏
loadTopDynamicMenuContent('${vix}/qm/afterClassifyingAction!goTopDynamicMenuContent.action');
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="${vix}/common/img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="${vix}/common/img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/qm_quality_management.png" alt="" /> 供应链</a></li>
				<li><a href="#">质量管理</a></li>
				<li><a href="#">样品管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/qm/afterClassifyingAction!goList.action?pageNo=${pageNo}');">样品分类</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0,$('#selectId').val());"><span><s:text name="cmn_add" /></span></a>
		</p>
	</div>
</div>

<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="${vix}/common/img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<div>
			<label>名称:<input id="nameS" name="" type="text" class="int" /></label> <label> <input id="simpleSearch" name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent(0);" /> <input id="simpleReset" name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent(0);" /></label> <strong
				id="lb_search_advanced" onclick="changeDisplay();"><s:text name='cmn_advance_search' /></strong>
		</div>
		<div class="search_advanced">
			<label><s:text name='名称' />:<input id="qm_afterName" name="" type="text" class="int" /></label> <label><s:text name='备注' />:<input id="qm_memo" name="" type="text" class="int" /></label> <label> <input name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent(1);" /> <input name="" type="button"
				class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent(1);" />
			</label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<div class="left_content" style="height: 500px;">
				<div style="padding-left: 4px;">
					<img src="${vix}/common/img/file.png" style="padding-right: 5px;" /><a href="#" onclick="loadRoot();"><s:text name="样品名称" /></a>
				</div>
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">
				<!--
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/qm/afterClassifyingAction!findTreeToJson.action",
						autoParam:["id", "name=n", "level=lv"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdTreeId").val(treeNode.tId);
					pager("start","${vix}/qm/afterClassifyingAction!goSingleList.action?parentId="+treeNode.id,"category");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
				//-->
			</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><img src="img/common_listx16.png" alt="" />
					<s:text name="样品分类" /></li>
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
					<strong><s:text name="cmn_selected"/>:<span id="selectCategoryCount1">0</span></strong> --%>
					<div>
						<span><a class="start" onclick="categoryPager('start','category');"></a></span> <span><a class="previous" onclick="categoryPager('previous','category');"></a></span> <em>(<b class="categoryInfo"></b> <s:text name='cmn_to' /> <b class="categoryTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next','category');"></a></span> <span><a class="end" onclick="categoryPager('end','category');"></a></span>
					</div>
				</div>
				<div id="category" class="table" style="overflow-x: auto; width: 100%; height: 492px;"></div>
				<div class="pagelist drop">
					<%-- <ul>
						<li class="ed">
							<a href="#"><s:text name='cmn_choose'/></a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete'/></a></li>
								<li><a href="#"><s:text name='cmn_email'/></a></li>
								<li><a href="#"><s:text name="merger"/></a></li>
								<li><a href="#"><s:text name="export"/></a></li>
							</ul>
						</li>
					</ul>
					<strong><s:text name="cmn_selected"/>:<span id="selectCategoryCount2">0</span></strong> --%>
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