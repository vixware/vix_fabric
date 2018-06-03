<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
var name = "";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}

$('#numBtn').click(function(){
	$('#numBtn').parent("li").toggleClass("current");
	$('#number').animate({height: 'toggle', opacity: 'toggle'},500,function(){$('#number').css('overflow','visible');});
	return false;
});

$(function(){
	if($('#numBox').length) $('#numBox').listmenu({menuWidth: '100%', cols:{ count:6, gutter:0 }});
});

$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});

function saveOrUpdate(id){
	$.ajax({
		  url:'${vix}/oa/meetingDeviceAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
}  
 

function deleteById(id){
	$.ajax({
		  url:'${vix}/oa/meetingDeviceAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='cmn_message'/>",function(action){
				var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
				if(null != treeNode){
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes(treeNode, "refresh");
				pager("start","${vix}/oa/meetingDeviceAction!goSingleList.action?name="+name,'category');
			});
		  }
	});
}

function searchForRightContent(){
	loadName();
	pager("start","${vix}/oa/meetingDeviceAction!goSingleList.action?name="+name,'category');
}
 
 
loadName();
//载入分页列表数据
pager("start","${vix}/oa/meetingDeviceAction!goSingleList.action?name="+name,'category');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/oa/meetingDeviceAction!goSingleList.action?name=",'category');
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
	pager("start","${vix}/oa/meetingDeviceAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'category');
}

bindSearch();
bindSwitch();
var orderStatus = '';
function currentPager(tag){
	loadCode();
	pager(tag,"${vix}/oa/meetingDeviceAction!goSingleList.action?name="+name+'&orderStatus='+orderStatus,'category');
}
$(".drop>ul>li").bind('mouseover',function(){
	$(this).children('ul').delay(400).slideDown('fast');
}).bind('mouseleave',function(){
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast');
});

//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
loadMenuContent('${vix}/oa/main/oaMainAction!goMenuContent.action');
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/mdm_meetingRequest.png" alt="" /> 协同办公</a></li>
				<li><a href="#">行政办公</a></li>
				<li><a href="#">会议申请安排</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/meetingDeviceAction!goList.action?pageNo=${pageNo}');">会议设备管理</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0);"><span><s:text name='cmn_add' /></span></a>
		</p>
	</div>
</div>

<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<!-- c_head -->
		<div class="box">
			<div id="left" class="ui-resizable switch" style="height: 500px; width: 7px;">
				<div id="switch_box" class="switch_btn off"></div>
				<div class="left_content current" style="height: 500px;">
					<div style="padding-left: 4px;">
						<img src="${vix}/common/img/file.png" style="padding-right: 5px;" /><a href="#" onclick="loadRoot();"><s:text name='cmn_rootDirectory' /></a>
					</div>
					<div id="tree_root" class="ztree" style="padding: 0;"></div>
				</div>
				<script type="text/javascript">
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/oa/meetingDeviceAction!findTreeToJson.action",
						autoParam:["id", "name=n", "level=lv"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdTreeId").val(treeNode.tId);
					pager("start","${vix}/oa/meetingDeviceAction!goSingleList.action?parentId="+treeNode.id,"category");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
			</script>
				<div class="ui-resizable-handle ui-resizable-e"></div>
			</div>
			<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
			<!-- left -->
			<div id="right">
				<div class="right_menu">
					<ul>
						<li class="current"><img src="img/common_listx16.png" alt="" />
						<s:text name="会议室设备列表" /></li>
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
									<li><a href="#">Actions</a></li>
									<li><a href="#">Actions</a></li>
								</ul></li>
						</ul>
						<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount1">0</span></strong>
						<div>
							<span><a class="start" onclick="categoryPager('start','category');"></a></span> <span><a class="previous" onclick="categoryPager('previous','category');"></a></span> <em>(<b class="categoryInfo"></b> <s:text name='cmn_to' /> <b class="categoryTotalCount"></b>)
							</em> <span><a class="next" onclick="categoryPager('next','category');"></a></span> <span><a class="end" onclick="categoryPager('end','category');"></a></span>
						</div>
					</div>
					<div id="category" class="table"></div>
					<div class="pagelist drop">
						<ul>
							<li class="ed"><a href="#"><s:text name='cmn_choose' /></a>
								<ul>
									<li><a href="#"><s:text name='cmn_delete' /></a></li>
									<li><a href="#"><s:text name='cmn_mail' /></a></li>
									<li><a href="#"><s:text name="cmn_merger" /></a></li>
									<li><a href="#"><s:text name="cmn_export" /></a></li>
								</ul></li>
						</ul>
						<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount2">0</span></strong>
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