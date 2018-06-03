<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
loadMenuContent('${vix}/hr/hrMainAction!goMenuContent.action');
var name = "";
var code = "";
var oldName = "";
var idNumber = "";
var staffJobNumber = "";
function loadName(){
	name = $("#empName").val();
	name = encodeURI(name);
}
function loadCode(){
	code = $('#hr_code').val();
	code = Url.encode(code);
	code = Url.encode(code);
}
function loadOldName(){
	oldName = $('#hr_oldName').val();
	oldName = Url.encode(oldName);
	oldName = Url.encode(oldName);
}
function loadIdNumber(){
	idNumber = $('#hr_idNumber').val();
	idNumber = Url.encode(idNumber);
	idNumber = Url.encode(idNumber);
}
function loadStaffJobNumber(){
	staffJobNumber = $('#hr_staffJobNumber').val();
	staffJobNumber = Url.encode(staffJobNumber);
	staffJobNumber = Url.encode(staffJobNumber);
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
		$("#empName").val("");
	}
	else{
		$("#hr_code").val("");
		$("#hr_oldName").val("");
		$("#hr_idNumber").val("");
		$("#hr_staffJobNumber").val("");
	}
}

/*搜索*/
function searchForContent(){
	loadName();
	pager("start","${vix}/hr/hrmgr/employeessAction!goSearchList.action?empName="+name,'category');
}

//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/hr/hrmgr/employeessAction!goSearch.action',
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
				pager("start", "${vix}/hr/hrmgr/employeessAction!goSearchList.action?empName=" + $('#empName').val() + "&code=" + $('#code').val() + "&oldName=" + $('#oldName').val() + "&idNumber=" + $('#idNumber').val() + "&staffJobNumber=" + $('#staffJobNumber').val(), 'category');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};

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
});
$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});
function saveOrUpdate(id,parentId){
	if(id==0){
		if(parentId.substring(parentId.length-1,parentId.length)=='O'){
			$.ajax({
				  url:'${vix}/hr/hrmgr/employeessAction!goSaveOrUpdate.action?id='+id+'&parentId='+parentId,
				  cache: false,
				  success: function(html){
					  $("#mainContent").html(html);
				  }
			});
		}else{
			asyncbox.alert("非部门组织不能创建职员！","提示");
			return;
		}
	}else{
		$.ajax({
			  url:'${vix}/hr/hrmgr/employeessAction!goSaveOrUpdate.action?id='+id,
			  cache: false,
			  success: function(html){
				  $("#mainContent").html(html);
			  }
		});
	}
};

function deleteById(id){
	$.ajax({
		  url:'${vix}/hr/hrmgr/employeessAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='message'/>",function(action){
				var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
				if(null != treeNode){
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes(treeNode, "refresh");
				pager("start","${vix}/hr/hrmgr/employeessAction!goSingleList.action?id="+treeNode.id+"&treeType="+treeNode.treeType,"category");
			});
		  }
	});
}

loadName();
//载入分页列表数据
pager("start","${vix}/hr/hrmgr/employeessAction!goSingleList.action?empName="+name+"&orderField=id&orderBy=desc",'category');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/hr/hrmgr/employeessAction!goSingleList.action?empName=",'category');
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
	pager("start","${vix}/hr/hrmgr/employeessAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&empName="+name+"&id="+$("#selectId").val()+"&treeType="+$('#treeType').val(),'category');
}

function categoryPager(tag,entity){
	loadName();
	if(entity == 'category'){
		pager(tag,"${vix}/hr/hrmgr/employeessAction!goSingleList.action?empName="+name+'&id='+$('#selectId').val()+"&treeType="+$('#treeType').val() ,entity);
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
loadTopDynamicMenuContent('${vix}/hr/employeessAction!goTopDynamicMenuContent.action');
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/hr_emp.png" alt="" />
					<s:text name="hr_humanr_resources" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/hrmgr/employeessAction!goList.action');">员工管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/hrmgr/employeessAction!goList.action');">后备人才管理</a></li>
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
			<b><img src="img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<div>
			<label>姓名: <input type="text" class="int" id="empName" style="width: 150px;"> <input type="button" value="搜索" class="btn" onclick="searchForContent(0);" />
			</label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" />
			</label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<div class="left_content" style="height: 500px;">
				<%-- <div style="padding-left:4px;"><img src="${vix}/common/img/file.png" style="padding-right:5px;"/><a href="#" onclick="loadRoot();"><s:text name='rootDirectory'/></a></div> --%>
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/hr/hrmgr/employeessAction!findOrgAndUnitTreeToJson.action",
						autoParam:["id","treeType"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					//debugger;
					$("#selectId").val(treeNode.id);
					$("#selectIdTreeId").val(treeNode.tId);
					$("#treeType").val(treeNode.treeType);
					var name = $("#empName").val();
					if(name!=null && name!=""){
						name = encodeURI(name);
					}
					pager("start","${vix}/hr/hrmgr/employeessAction!goSingleList.action?empName="+name+"&id="+treeNode.id+"&treeType="+treeNode.treeType,"category");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
			</script>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" /> <input type="hidden" id="treeType" name="treeType" />
		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><a onclick="categoryTab(3,1,'a',event,'category')"><img src="img/common_listx16.png" alt="" />后备人才管理</a></li>
				</ul>
			</div>
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /></a>
							<ul>
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
								<li><a href="#"><s:text name='cmn_email' /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
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