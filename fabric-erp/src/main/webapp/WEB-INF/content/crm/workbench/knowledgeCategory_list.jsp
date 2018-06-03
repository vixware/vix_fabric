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
		  url:'${vix}/crm/workbench/knowledgeCategoryAction!goSaveOrUpdate.action?id='+id+"&parentId="+$("#selectId").val(),
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 880,
					height : 420,
					title:"${vv:varView('vix_mdm_item')}分类",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#knowledgeCategoryForm').validationEngine('validate')){
								$.post('${vix}/crm/workbench/knowledgeCategoryAction!saveOrUpdate.action',
									 {'knowledgeCategory.id':$("#id").val(),
									  'knowledgeCategory.code':$("#code").val(),
									  'knowledgeCategory.parentKnowledgeCategory.id':$("#parentKnowledgeCategoryId").val(),
									  'knowledgeCategory.name':$("#name").val(),
									  'knowledgeCategory.preExamineStandard':$("#preExamineStandard").val()
									},
									function(result){
										asyncbox.success(result,"<s:text name='vix_message'/>",function(action){
											var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
											if(null != treeNode){
												treeNode.isParent = true;
											} 
											zTree.reAsyncChildNodes(treeNode, "refresh");
											pager("current","${vix}/crm/workbench/knowledgeCategoryAction!goListContent.action?name="+name+"&parentId="+$("#selectId").val(),'knowledgeCategory');
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
		  url:'${vix}/crm/workbench/knowledgeCategoryAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='vix_message'/>",function(action){
				zTree.reAsyncChildNodes(zTree.getNodeByTId($("#selectIdTreeId").val()), "refresh");
				pager("current","${vix}/crm/workbench/knowledgeCategoryAction!goListContent.action?name="+name,'knowledgeCategory');
			});
		  }
	});
}
function searchForRightContent(){
	loadName();
	pager("start","${vix}/crm/workbench/knowledgeCategoryAction!goListContent.action?name="+name+"&id="+$("#selectId").val(),'knowledgeCategory');
}
loadName();
//载入分页列表数据
pager("start","${vix}/crm/workbench/knowledgeCategoryAction!goListContent.action?name="+name,'knowledgeCategory');
function loadRoot(){
	$('#nameS').val("");
	$('#selectId').val("");
	$("#selectIdTreeId").val("");
	zTree.refresh();
	pager("start","${vix}/crm/workbench/knowledgeCategoryAction!goListContent.action?name=",'knowledgeCategory');
}
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#knowledgeCategoryOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("current","${vix}/crm/workbench/knowledgeCategoryAction!goListContent.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&id="+$("#selectId").val()+'&companyCode='+$("#companyCode").val(),'knowledgeCategory');
}

bindSwitch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/crm/workbench/knowledgeCategoryAction!goListContent.action?name="+name+'&companyCode='+$("#companyCode").val(),'knowledgeCategory');
}

function currentPagerClick(input,event){
	loadName();
	pagerClick(input,event,"${vix}/crm/workbench/knowledgeCategoryAction!goListContent.action?name="+name,'knowledgeCategory');
}
function reset(){
	$("#nameS").val("");
}
//选择公司
function chooseCompany(){
	$.ajax({
		  url:'${vix}/common/org/organizationAction!goChooseOrganization.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择公司",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
								$("#companyCode").val(result[0]);
								$("#companyName").html(result[1]);
								loadName();
								pager('start',"${vix}/crm/workbench/knowledgeCategoryAction!goListContent.action?name="+name+'&companyCode='+result[0],'knowledgeCategory');
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
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
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="${vix}/common/img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="${vix}/common/img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/mdm/knowledgeCategory.png" alt="" />系统管理</a></li>
				<li><a href="#">基础数据管理</a></li>
				<li><a href="#">物料管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/workbench/knowledgeCategoryAction!goList.action');">分类管理</a></li>
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
			<label><s:text name="cmn_name" /><input type="text" class="int" id="nameS"></label> <label> <input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForRightContent();" /> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" onclick="reset();" />
			</label>
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
						url:"${vix}/crm/workbench/knowledgeCategoryAction!findTreeToJson.action",
						autoParam:["id", "name=n", "level=lv"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdTreeId").val(treeNode.tId);
					pager("start","${vix}/crm/workbench/knowledgeCategoryAction!goListContent.action?parentId="+treeNode.id,'knowledgeCategory');
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
				//-->
			</script>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><img src="${vix}/common/img/mail.png" alt="" />${vv:varView('vix_mdm_item')}分类</li>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="knowledgeCategoryInfo"></b> <s:text name='cmn_to' /> <b class="knowledgeCategoryTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="knowledgeCategory" style="overflow-x: auto; overflow-y: hidden; width: 100%;"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="knowledgeCategoryInfo"></b> <s:text name='cmn_to' /> <b class="knowledgeCategoryTotalCount"></b>)
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