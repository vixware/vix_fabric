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
		  url:'${vix}/sales/quotes/salesQuotationCategoryAction!goSaveOrUpdate.action?id='+id+'&parentId='+$("#selectId").val(),
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 580,
					height : 180,
					title:"报价模板分类管理",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#salesQuotationCategoryForm').validationEngine('validate')){
								$.post('${vix}/sales/quotes/salesQuotationCategoryAction!saveOrUpdate.action',
									 {'salesQuotationCategory.id':$("#id").val(),
									  'salesQuotationCategory.name':$("#name").val(),
									  'salesQuotationCategory.parentSalesQuotationCategory.id':$("#parentSalesQuotationCategoryId").val()
									},
									function(result){
										asyncbox.success(result,"<s:text name='vix_message'/>",function(action){
											var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
											if(null != treeNode){
												treeNode.isParent = true;
											}
											zTree.reAsyncChildNodes(treeNode, "refresh");
											pager("current","${vix}/sales/quotes/salesQuotationCategoryAction!goSingleList.action?name="+name+"&parentId="+$("#selectId").val(),"salesQuotationCategory");
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
		  url:'${vix}/sales/quotes/salesQuotationCategoryAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='vix_message'/>",function(action){
				zTree.reAsyncChildNodes(zTree.getNodeByTId($("#selectIdTreeId").val()), "refresh");
				pager("current","${vix}/sales/quotes/salesQuotationCategoryAction!goSingleList.action?name="+name,"salesQuotationCategory");
			});
		  }
	});
}

function searchForRightContent(){
	loadName();
	pager("start","${vix}/sales/quotes/salesQuotationCategoryAction!goSingleList.action?name="+name,"salesQuotationCategory");
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/sales/quotes/salesQuotationCategoryAction!goSingleList.action?name="+name,"salesQuotationCategory");
function loadRoot(){
	$('#nameS').val("");
	$('#selectId').val("");
	pager("start","${vix}/sales/quotes/salesQuotationCategoryAction!goSingleList.action?name=&id=","salesQuotationCategory");
}
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#salesQuotationCategoryOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	var parentId = $("#selectId").val();
	if(parentId == undefined){
		parentId = 0;
	}
	pager("current","${vix}/sales/quotes/salesQuotationCategoryAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+parentId,"salesQuotationCategory");
}

function currentPager(tag){
	loadName();
	pager(tag,"${vix}/sales/quotes/salesQuotationCategoryAction!goSingleList.action?name="+name,'salesQuotationCategory');
}

function currentPagerClick(input,event){
	loadName();
	pagerClick(input,event,"${vix}/sales/quotes/salesQuotationCategoryAction!goSingleList.action?name="+name,'salesQuotationCategory');
}
loadMenuContent('${vix}/sales/salesAction!goMenuContent.action');
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="${vix}/common/img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="${vix}/common/img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/pur_plan.png" alt="" /> <s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#">销售管理</a></li>
				<li><a href="#">销售报价</a></li>
				<li><a href="#">项目式报价</a></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/quotes/salesQuotationCategoryAction!goList.action');">报价模板分类</a></li>
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
		<div>
			<label><s:text name="cmn_name" /><input type="text" class="int" id="nameS"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForRightContent();" /></label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<div class="left_content" style="height: 500px;">
				<div style="padding-left: 4px;">
					<img src="${vix}/common/img/file.png" style="padding-right: 5px;" /><a href="#" onclick="loadRoot();">${bomStruct.name}</a>
				</div>
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/sales/quotes/salesQuotationCategoryAction!findTreeToJson.action",
						autoParam:["id", "name=n", "level=lv"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.idStr);
					$("#selectIdTreeId").val(treeNode.tId);
					pager("start","${vix}/sales/quotes/salesQuotationCategoryAction!goSingleList.action?parentId="+treeNode.id,'salesQuotationCategory');
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="salesQuotationCategoryInfo"></b> <s:text name='cmn_to' /> <b class="salesQuotationCategoryTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="salesQuotationCategory" style="overflow-x: auto; overflow-y: hidden; width: 100%;"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="salesQuotationCategoryInfo"></b> <s:text name='cmn_to' /> <b class="salesQuotationCategoryTotalCount"></b>)
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