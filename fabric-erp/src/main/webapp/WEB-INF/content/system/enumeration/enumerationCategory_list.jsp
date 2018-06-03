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
		  url:'${vix}/system/enumerationCategoryAction!goSaveOrUpdate.action?id='+id+'&parentId='+$("#selectId").val(),
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 420,
					height : 220,
					title:"分类管理",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#enumerationCategoryForm').validationEngine('validate')){
								$.post('${vix}/system/enumerationCategoryAction!saveOrUpdate.action',
									 {'enumerationCategory.id':$("#id").val(),
									  'enumerationCategory.code':$("#code").val(),
									  'enumerationCategory.name':$("#name").val(),
									  'enumerationCategory.parentEnumerationCategory.id':$("#parentEnumCategoryId").val()
									},
									function(result){
										asyncbox.success(result,"<s:text name='vix_message'/>",function(action){
											var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
											if(null != treeNode){
												treeNode.isParent = true;
											}
											zTree.reAsyncChildNodes(treeNode, "refresh");
											pager("current","${vix}/system/enumerationCategoryAction!goSingleList.action?name="+name+"&parentId="+$("#selectId").val(),"enumerationCategory");
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
		  url:'${vix}/system/enumerationCategoryAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='vix_message'/>",function(action){
				zTree.reAsyncChildNodes(zTree.getNodeByTId($("#selectIdTreeId").val()), "refresh");
				pager("current","${vix}/system/enumerationCategoryAction!goSingleList.action?name="+name,"enumerationCategory");
			});
		  }
	});
}

function searchForRightContent(){
	loadName();
	pager("start","${vix}/system/enumerationCategoryAction!goSingleList.action?name="+name,"enumerationCategory");
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/system/enumerationCategoryAction!goSingleList.action?name="+name,"enumerationCategory");
function loadRoot(){
	$('#nameS').val("");
	$('#selectId').val("");
	pager("start","${vix}/system/enumerationCategoryAction!goSingleList.action?name=&id=","enumerationCategory");
}
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#enumerationCategoryOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	var parentId = $("#selectId").val();
	if(parentId == undefined){
		parentId = 0;
	}
	pager("current","${vix}/system/enumerationCategoryAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+parentId,"enumerationCategory");
}

function currentPager(tag){
	loadName();
	pager(tag,"${vix}/system/enumerationCategoryAction!goSingleList.action?name="+name,'enumerationCategory');
}

function currentPagerClick(input,event){
	loadName();
	pagerClick(input,event,"${vix}/system/enumerationCategoryAction!goSingleList.action?name="+name,'enumerationCategory');
}
//面包屑
if($('.sub_menu').length)
{
	$("#breadCrumb").jBreadCrumb();
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="${vix}/common/img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="${vix}/common/img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/mdm/itemCatalog.png" alt="" />系统管理</a></li>
				<li><a href="#">基础设置</a></li>
				<li><a href="#">对象类型</a></li>
				<li><a href="#" onclick="loadContent('${vix}/system/enumerationCategoryAction!goList.action');">字典分类</a></li>
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
		<ul>
			<li><span id="companyName" style="height: 30px; width: 220px; padding-top: 8px;"></span><input type="hidden" id="companyCode" /></li>
			<li><a href="###" onclick="chooseCompany()"><img src="${vix}/common/img/icon_10.png">选择公司</a></li>
		</ul>
		<div>
			<label><s:text name="cmn_name" /><input type="text" class="int" id="nameS"></label> <label><s:text name="cmn_my_item" /><input type="checkbox" value="" name=""></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForRightContent();" /><input type="button"
				value="<s:text name='cmn_reset'/>" class="btn" name="" /></label> <strong id="lb_search_advanced"><s:text name="cmn_advance_search" /></strong>
		</div>
		<div class="search_advanced">
			<label><s:text name="cmn_name" /><input type="text" class="int" name=""></label> <label><s:text name="cmn_my_item" /><input type="checkbox" value="" name=""></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name=""><input type="button" value="<s:text name='cmn_reset'/>"
				class="btn" name=""></label> <label><s:text name="cmn_name" /><input type="text" class="int" name=""></label> <label><s:text name="cmn_my_item" /><input type="checkbox" value="" name=""></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name=""><input type="button"
				value="<s:text name='cmn_reset'/>" class="btn" name=""></label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<div class="left_content" style="height: 500px;">
				<div style="padding-left: 4px;">
					<img src="${vix}/common/img/file.png" style="padding-right: 5px;" /><a href="#" onclick="loadRoot();">根目录</a>
				</div>
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">
				<!--
					var zTree;			
					var setting = {
						async: {
							enable: true,
							url:"${vix}/system/enumerationCategoryAction!findTreeToJson.action",
							autoParam:["id", "name=n", "level=lv"]
						},
						callback: {
							onClick: onClick
						}
					};
					function onClick(event, treeId, treeNode, clickFlag) {
						$("#selectId").val(treeNode.id);
						$("#selectIdTreeId").val(treeNode.tId);
						pager("start","${vix}/system/enumerationCategoryAction!goSingleList.action?parentId="+treeNode.id,"enumerationCategory");
					}
					zTree = $.fn.zTree.init($("#tree_root"), setting);
				//-->
			</script>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
		<!-- left -->
		<div id="right">
			<div class="right_content">
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="enumerationCategoryInfo"></b> <s:text name='cmn_to' /> <b class="enumerationCategoryTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="enumerationCategory" style="overflow-x: auto; overflow-y: hidden; width: 100%;"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="enumerationCategoryInfo"></b> <s:text name='cmn_to' /> <b class="enumerationCategoryTotalCount"></b>)
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