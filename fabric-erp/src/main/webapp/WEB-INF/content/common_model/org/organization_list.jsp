<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
loadMenuContent('${vix}/hr/hrMainAction!goMenuContent.action');
var name = "";
function loadName(){
	name = $("#orgName").val();
	name= Url.encode(name);
	name = encodeURI(name);
}
$('#numBtn').click(function(){
	$('#numBtn').parent("li").toggleClass("current");
	$('#number').stop().animate({height: 'toggle', opacity: 'toggle'},500,function(){
		$('#number').css('overflow','visible');
	});
	return false;
});
$(function(){
	if($('#numBox').length) $('#numBox').listmenu({menuWidth: '100%', cols:{ count:6, gutter:0 }});
	$( "#left" ).resizable({
		maxHeight: 650,
		minHeight: 650,
		maxWidth: 400,
		minWidth: 120,
		handles: 'e'
	});
	
	$("#organizationForm").validationEngine();
});
$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});
function saveOrUpdate(id,parentId){
	if(parentId == ""){
		//asyncbox.alert("请先选择上级（集团）公司！","提示");
		showErrorMessage("请先选择上级（集团）公司！");
		setTimeout("", 1000);
		return;
	}
	$.ajax({
		  //url:'${vix}/common/org/organizationAction!goSaveOrUpdate.action?id='+id +"&parentId="+parentId,
		  url:'${vix}/common/org/organizationAction!goSaveOrUpdate.action?id='+id +"&parentId="+parentId,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 660,
					height : 420,
					title:"新建组织",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($("#organizationForm").validationEngine('validate')){
								if(confirm("确认要保存公司吗?")){
									var queryString = $('#organizationForm').formSerialize(); 
									$.post('${vix}/system/companyOperationAction!saveOrUpdate.action',
										queryString,
										function(result){
											//asyncbox.success(result,"<s:text name='cmn_message'/>",function(action){
												showMessage(result);
												setTimeout("", 1000);
												
												var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
												if(null != treeNode){
													treeNode.isParent = true;
												}
												zTree.reAsyncChildNodes(treeNode, "refresh");
												pager("start","${vix}/common/org/organizationAction!goSingleList.action?orgName="+name+"&id="+$("#selectId").val(),'category');
											//});
										}
									 );
								}
							}
							else{
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
		  url:'${vix}/common/org/organizationAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(result){
			 	showMessage(result);
			  	setTimeout("", 1000);
				var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
				if(null != treeNode){
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes(treeNode, "refresh");
				pager("start","${vix}/common/org/organizationAction!goSingleList.action?orgName="+name,'category');
		  }
	});
}

function showOrganization(id,pageNo){

	$.ajax({
		  url:'${vix}/common/org/organizationAction!goShowOrganization.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 660,
					height : 420,
					title:"查看组织",
					html:html,
					callback : function(action){
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

function searchForContent(){
	loadName();
	pager("start","${vix}/common/org/organizationAction!goSingleList.action?orgName="+name,'category');
}

loadName();
//载入分页列表数据
pager("start","${vix}/common/org/organizationAction!goSingleList.action?orgName="+name,'category');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/common/org/organizationAction!goSingleList.action?orgName=",'category');
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
	pager("start","${vix}/common/org/organizationAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&orgName="+name+"&id="+$("#selectId").val(),'category');
}

function categoryPager(tag,entity){
	loadName();
	if(entity == 'category'){
		pager(tag,"${vix}/common/org/organizationAction!goSingleList.action?orgName="+name+'&id='+$('#selectId').val(),entity);
	}
}

bindSearch();
bindSwitch();
function categoryTab(num,befor,id,e,entity){
	var el=e.target?e.target.parentNode:e.srcElement.parentNode;
	var pa=el.parentNode.getElementsByTagName("li");
	for(var i=0;i<pa.length;i++){
		pa[i].className="";
	}
	el.className="current";
	for(i=1;i<=num;i++){
		try{
			if(i==befor){
				document.getElementById(id+i).style.display="block";
			}else{
				document.getElementById(id+i).style.display="none";
			}
		}catch(e){ }
	}
	if(entity != undefined){
		categoryPager('start',entity);
	}
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
/* 加载顶部工具栏 */
loadTopDynamicMenuContent('${vix}/common/org/organizationAction!goTopDynamicMenuContent.action');
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/hr_org.png" alt="" />
					<s:text name="hr_humanr_resources" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/common/org/organizationAction!goList.action');">组织管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/common/org/organizationAction!goList.action');">组织架构</a></li>
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
		<%-- <div class="untitled">
			<b><img src="img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong>
					<i class="close"><a href="#"></a></i>
					<i><a href="#"></a></i>
					<i><a href="#"></a></i>
					<b>帮助</b>				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_mode" /> </a>
				<ul>
					<li><a href="#" onclick="hrStatus('S1')"><img alt="" src="img/unaudited.png"><s:text name="cmn_unapproved_plan"/></a></li>
					<li><a href="#" onclick="hrStatus('S2')"><img alt="" src="img/verifying.png"><s:text name="cmn_approval_by_plan"/></a></li>
					<li><a href="#" onclick="hrStatus('S3')"><img alt="" src="img/approved.png"><s:text name="cmn_approval_in"/></a></li>
				</ul>
			</li>
			<li class="fly">
				<a href="#"><img alt="" src="img/leastRecentlyUsed.png"><s:text name="cmn_recently_used"/></a>
				<ul>
					<li><a href="#" onclick="srmRecent('T1')"><img alt="" src="img/time_go.png"><s:text name="cmn_three_days"/></a></li>
					<li><a href="#" onclick="srmRecent('T2')"><img alt="" src="img/time_go.png"><s:text name="cmn_seven_days"/></a></li>
					<li><a href="#" onclick="srmRecent('T3')"><img alt="" src="img/time_go.png"><s:text name="cmn_month"/></a></li>
					<li><a href="#" onclick="srmRecent('T4')"><img alt="" src="img/time_go.png"><s:text name="cmn_three_months"/></a></li>
				</ul>
			</li>
		</ul> --%>
		<!-- 页面左上角按钮部分 -->
		<div>
			<label>名称:<input type="text" class="int" id="orgName" value=""></label> <label> <input id="simpleSearch" name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent(0);" />
			</label>
		</div>
		<%-- <div class="search_advanced">
			<label>申请名称：<input id="orgName" type="text" class="int" name=""></label>
			<label>申请部门：<input id="hr_contacts" type="text" class="int" name=""></label>
			<label>
				<input name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent(1);" />
				<input name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent(1);" />
			</label>
		</div> --%>
	</div>
	<%--   <div id="number">
		<span class="num_left_bg"></span>
		<span class="num_right_bg"></span>
        <ul id="numBox" class="numBox">
        	<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
        	<s:iterator value="hrRecruitApplicationList" var="c">
        		<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());">${c.applicationName}</a></li>
        	</s:iterator>
        </ul>
    </div> --%>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>

			<div class="left_content" style="height: 500px;">
				<div style="padding-left: 4px;">
					<img src="${vix}/common/img/file.png" style="padding-right: 5px;" /><a href="#" onclick="loadRoot();">组织机构</a>
				</div>
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/common/org/organizationAction!findTreeToJson.action",
						autoParam:["id"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdTreeId").val(treeNode.tId);
					pager("start","${vix}/common/org/organizationAction!goSingleList.action?id="+treeNode.id,"category");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
			</script>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><a onclick="categoryTab(3,1,'a',event,'category')"><img src="img/chart_organisation.png" alt="" />公司组织机构</a></li>
				</ul>
			</div>
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<%-- <ul>
						<li class="tp">
							<a href="#"><s:text name='cmn_operate'/></a>
							<ul>
								<li><a href="#">Actions</a></li>
							</ul>
						</li>
					</ul>
					<strong><s:text name="cmn_selected"/>:<span id="selectCategoryCount1">0</span></strong> 
					--%>
					<ul>
					</ul>
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
								<li><a href="#"><s:text name='cmn_email'/></a></li>
								<li><a href="#"><s:text name="merger"/></a></li>
								<li><a href="#"><s:text name="export"/></a></li>
							</ul>
						</li>
					</ul>
					<strong><s:text name="cmn_selected"/>:<span id="selectCategoryCount2">0</span></strong>
					 --%>
					<ul>
					</ul>
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