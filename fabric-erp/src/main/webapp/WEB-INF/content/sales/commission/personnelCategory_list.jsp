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
		  url:'${vix}/sales/commission/personnelCategoryAction!goSaveOrUpdate.action?id='+id+"&parentId="+$("#selectId").val(),
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 420,
					height : 220,
					title:"销售人员分类",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#personnelCategoryForm').validationEngine('validate')){
								$.post('${vix}/sales/commission/personnelCategoryAction!saveOrUpdate.action',
									{'personnelCategory.id':$("#id").val(),
										'personnelCategory.parentPersonnelCategory.id':$("#parentPersonnelCategoryId").val(),
										'personnelCategory.name':$("#name").val(),
										'personnelCategory.memo':$("#memo").val()
									},
									function(result){
										asyncbox.success(result,"<s:text name='vix_message'/>",function(action){
											var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
											if(null != treeNode){
												treeNode.isParent = true;
											} 
											zTree.reAsyncChildNodes(treeNode, "refresh");
											pager("current","${vix}/sales/commission/personnelCategoryAction!goListContent.action?name="+name+"&parentId="+$("#selectId").val(),'personnelCategory');
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
$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});
function deleteById(id){
	$.ajax({
		  url:'${vix}/sales/commission/personnelCategoryAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='vix_message'/>",function(action){
				zTree.reAsyncChildNodes(zTree.getNodeByTId($("#selectIdTreeId").val()), "refresh");
				pager("current","${vix}/sales/commission/personnelCategoryAction!goListContent.action?name="+name,'personnelCategory');
			});
		  }
	});
}
function searchForRightContent(){
	loadName();
	pager("start","${vix}/sales/commission/personnelCategoryAction!goListContent.action?name="+name+"&id="+$("#selectId").val(),'personnelCategory');
}
loadName();
//载入分页列表数据
pager("start","${vix}/sales/commission/personnelCategoryAction!goListContent.action?name="+name,'personnelCategory');
function loadRoot(){
	$('#nameS').val("");
	$('#selectId').val("");
	$("#selectIdTreeId").val("");
	zTree.refresh();
	pager("start","${vix}/sales/commission/personnelCategoryAction!goListContent.action?name=",'personnelCategory');
}
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#personnelCategoryOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("current","${vix}/sales/commission/personnelCategoryAction!goListContent.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&id="+$("#selectId").val(),'personnelCategory');
}

bindSwitch();
bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/sales/commission/personnelCategoryAction!goListContent.action?name="+name,'personnelCategory');
}

function currentPagerClick(input,event){
	loadName();
	pagerClick(input,event,"${vix}/sales/commission/personnelCategoryAction!goListContent.action?name="+name,'personnelCategory');
}
$(".drop>ul>li").bind('mouseover',function(){
	$(this).children('ul').delay(400).slideDown('fast');
}).bind('mouseleave',function(){
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast');
});
//面包屑
if($('.sub_menu').length)
{
	$("#breadCrumb").jBreadCrumb();
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
				<li><a href="#">销售佣金管理</a></li>
				<li><a href="#">设置</a></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/commission/personnelCategoryAction!goList.action');">销售人员分类</a></li>
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
			<li><a href="#"><img src="${vix}/common/img/icon_10.png" />
				<s:text name="cmn_category" /></a></li>
			<li class="fly_th"><a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />
				<s:text name="cmn_category" /></a>
				<ul>
					<li>
						<dl>
							<dt>
								<s:text name="cmn_category" />
							</dt>
							<dd>
								<a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />
								<s:text name="cmn_category" /></a> <a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />
								<s:text name="cmn_category" /></a> <a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />
								<s:text name="cmn_category" /></a> <a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />
								<s:text name="cmn_category" /></a> <a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />
								<s:text name="cmn_category" /></a>
							</dd>
						</dl>
					</li>
					<li>
						<dl>
							<dt>
								<s:text name="cmn_category" />
							</dt>
							<dd>
								<a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />
								<s:text name="cmn_category" /></a> <a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />
								<s:text name="cmn_category" /></a> <a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />
								<s:text name="cmn_category" /></a> <a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />
								<s:text name="cmn_category" /></a> <a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />
								<s:text name="cmn_category" /></a>
							</dd>
						</dl>
					</li>
					<li>
						<dl>
							<dt>
								<s:text name="cmn_category" />
							</dt>
							<dd>
								<a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />
								<s:text name="cmn_category" /></a> <a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />
								<s:text name="cmn_category" /></a> <a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />
								<s:text name="cmn_category" /></a> <a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />
								<s:text name="cmn_category" /></a> <a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />
								<s:text name="cmn_category" /></a>
							</dd>
						</dl>
					</li>
				</ul></li>
		</ul>
		<div>
			<label><s:text name="cmn_name" /><input type="text" class="int" id="nameS"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForRightContent();" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" /></label> <strong id="lb_search_advanced"><s:text
					name="cmn_advance_search" /></strong>
		</div>
		<div class="search_advanced">
			<label><s:text name="cmn_name" /><input type="text" class="int" name=""></label> <label><s:text name="cmn_name" /><input type="text" class="int" name=""></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name=""><input type="button" value="<s:text name='cmn_reset'/>" class="btn"
				name=""></label>
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
						url:"${vix}/sales/commission/personnelCategoryAction!findTreeToJson.action",
						autoParam:["id", "name=n", "level=lv"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdTreeId").val(treeNode.tId);
					pager("start","${vix}/sales/commission/personnelCategoryAction!goListContent.action?parentId="+treeNode.id,'personnelCategory');
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="personnelCategoryInfo"></b> <s:text name='cmn_to' /> <b class="personnelCategoryTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="personnelCategory" style="overflow-x: auto; overflow-y: hidden; width: 100%;"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="personnelCategoryInfo"></b> <s:text name='cmn_to' /> <b class="personnelCategoryTotalCount"></b>)
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