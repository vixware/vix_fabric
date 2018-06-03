<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var name = "";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function saveOrUpdate(id){
	$.ajax({
		  url:'${vix}/crm/workbench/knowledgeAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 820,
					height : 480,
					title:"知识库",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#knowledgeForm').validationEngine('validate')){
								$.post('${vix}/crm/workbench/knowledgeAction!saveOrUpdate.action',
									 {'knowledge.id':$("#id").val(),
									  'knowledge.kmCode':$("#kmCode").val(),
									  'knowledge.knowledgeCategory.id':$("#knowledgeCategoryId").val(),
									  'knowledge.kmQuestion':$("#kmQuestion").val(),
									  'knowledge.kmAnswer':editor.html(),
									  'knowledge.kmType':$("#kmType").val(),
									  'knowledge.keSize':$("#keSize").val(),
									  'knowledge.subject':$("#subject").val(),
									  'knowledge.assignee':$("#assignee").val(),
									  'knowledge.publishTime':$("#publishTime").val()
									},
									function(result){
										asyncbox.success(result,"<s:text name='vix_message'/>",function(action){
											var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
											if(null != treeNode){
												treeNode.isParent = true;
											} 
											zTree.reAsyncChildNodes(treeNode, "refresh");
											pager("current","${vix}/crm/workbench/knowledgeAction!goListContent.action?name="+name+"&id="+$("#selectId").val(),'knowledge');
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
		  url:'${vix}/crm/workbench/knowledgeAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='vix_message'/>",function(action){
				pager("current","${vix}/crm/workbench/knowledgeAction!goListContent.action?name="+name,'knowledge');
			});
		  }
	});
}
function searchForRightContent(){
	loadName();
	pager("start","${vix}/crm/workbench/knowledgeAction!goListContent.action?kmQuestion="+name,'knowledge');
}
loadName();
//载入分页列表数据
pager("start","${vix}/crm/workbench/knowledgeAction!goListContent.action?name="+name,'knowledge');
function loadRoot(){
	$('#nameS').val("");
	$('#selectId').val("");
	pager("start","${vix}/crm/workbench/knowledgeAction!goListContent.action?name=",'knowledge');
}
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#knowledgeOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("current","${vix}/crm/workbench/knowledgeAction!goListContent.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&id="+$("#selectId").val(),'knowledge');
}

bindSwitch();
bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/crm/workbench/knowledgeAction!goListContent.action?name="+name,'knowledge');
}

function reset(){
	$('#nameS').val('');
}

function currentPagerClick(input,event){
	loadName();
	pagerClick(input,event,"${vix}/crm/workbench/knowledgeAction!goListContent.action?kmQuestion="+name,'knowledge');
}

//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}

//高级搜索 
function goSearch() {
	$.ajax({
	url : '${vix}/crm/workbench/knowledgeAction!goSearch.action',
	cache : false,
	success : function(html) {
		asyncbox.open({
		modal : true,
		width : 650,
		height : 300,
		title : "查询条件",
		html : html,
		callback : function(action) {
			loadConditions();
			if (action == 'ok') {
				pager("start","${vix}/crm/workbench/knowledgeAction!goListContent.action?kmQuestion="+kmQuestion +"&knowledgeCategory="+knowledgeCategory+"&subject="+subject,'knowledge');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};

</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="${vix}/common/img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="${vix}/common/img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/knowledge.png" alt="" />供应链</a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#">基础资料管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/workbench/knowledgeAction!goList.action');">知识库管理</a></li>
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
			<label><s:text name="知识点" /><input type="text" class="int" id="nameS"></label> <label> <input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForRightContent();" /> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="reset();" />
			</label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><img src="${vix}/common/img/mail.png" alt="" />知识库</li>
				</ul>
			</div>
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /></a>
							<ul>
								<li><a href="#"><s:text name='cmn_add' /></a></li>
								<li><a href="#"><s:text name='cmn_update' /></a></li>
								<li><a href="#"><s:text name='cmn_delete' /></a></li>
							</ul></li>
					</ul>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="knowledgeInfo"></b> <s:text name='cmn_to' /> <b class="knowledgeTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="knowledge" style="overflow-x: auto; overflow-y: hidden; width: 100%;"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /></a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /></a></li>
								<li><a href="#"><s:text name='cmn_email' /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="knowledgeInfo"></b> <s:text name='cmn_to' /> <b class="knowledgeTotalCount"></b>)
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