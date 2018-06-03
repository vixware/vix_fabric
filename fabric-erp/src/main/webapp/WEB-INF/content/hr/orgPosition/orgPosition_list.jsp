<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
var name = "";
function loadName(){
	name = $("#orgUnitName").val();
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
});
$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});
function saveOrUpdate(id,parentId){
	if(id==''){
		if(parentId.substring(parentId.length-1,parentId.length)=='O'){
			$.ajax({
				  url:'${vix}/hr/position/orgPositionAction!goSaveOrUpdate.action?id='+id+'&treeId='+parentId,
				  cache: false,
				  success: function(html){
					  $("#mainContent").html(html);
				  }
			});
		}else{
			showErrorMessage("非部门组织不能创建岗位！");
			setTimeout("", 1000);
			return;
		}
	}else{
		$.ajax({
			  url:'${vix}/hr/position/orgPositionAction!goSaveOrUpdate.action?id='+id,
			  cache: false,
			  success: function(html){
				  $("#mainContent").html(html);
			  }
		});
	}
	
};

function saveOrUpdateOrgPosition(){
	if($("#orgPositionForm").validationEngine('validate')){
		asyncbox.confirm('确定要保存么?','提示信息',function(action){
			if(action == 'ok'){
				var queryString = $('#orgPositionForm').formSerialize(); 
				$.post('${vix}/hr/position/orgPositionAction!saveOrUpdate.action',
					queryString,
					function(result){
						showMessage(result);
						setTimeout("", 1000);
						loadContent('${vix}/hr/position/orgPositionAction!goList.action');
					}
				 );
			}
		});
	}else {
		return false;
	}
};


function deleteById(id){
	$.ajax({
		  url:'${vix}/hr/position/orgPositionAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(result){
			  	showMessage(result);
			  	setTimeout("", 1000);
			  
				var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
				if(null != treeNode){
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes(treeNode, "refresh");
				pager("start","${vix}/hr/position/orgPositionAction!goSingleList.action?treeId="+treeNode.treeId+"&treeType="+treeNode.treeType,"category");
		  }
	});
}

function showPosition(id,pageNo){
	$.ajax({
		  url:'${vix}/hr/position/orgPositionAction!goShowPosition.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
}
function searchForRightContent(){
	loadName();
	pager("start","${vix}/hr/position/orgPositionAction!goSingleList.action?posName="+name,'category');
}

loadName();
//载入分页列表数据
pager("start","${vix}/hr/position/orgPositionAction!goSingleList.action?posName="+name,'category');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/hr/position/orgPositionAction!goSingleList.action?posName=",'category');
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
	pager("start","${vix}/hr/position/orgPositionAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&posName="+name+"&treeId="+$("#selectId").val(),'category');
}

function categoryPager(tag,entity){
	loadName();
	if(entity == 'category'){
		pager(tag,"${vix}/hr/position/orgPositionAction!goSingleList.action?posName="+name+'&treeId='+$('#selectId').val(),entity);
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
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<s:if test="%{fromKey == 'system'}">
					<ul>
						<li><img src="${vix}/common/img/hr_org.png" alt="" /><a href="#"><s:text name="system_control" /></a></li>
						<li><a href="#"><s:text name="system_control_org" /></a></li>
						<li><a href="#"><s:text name="system_control_org_sys" /></a></li>
						<li><a href="#">岗位管理</a></li>
					</ul>
				</s:if>
				<s:else>
					<li><a href="#"><img src="img/hr_poisition.png" alt="" />
						<s:text name="hr_humanr_resources" /></a></li>
					<li><a href="#" onclick="loadContent('${vix}/hr/position/orgPositionAction!goList.action');">组织管理</a></li>
					<li><a href="#" onclick="loadContent('${vix}/hr/position/orgPositionAction!goList.action');"> 岗位管理</a></li>
				</s:else>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate('',$('#selectId').val());"><span><s:text name="cmn_add" /></span></a>
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
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /><s:text name="cmn_index"/></a></li>
			<li>
				<a href="#"><img src="img/icon_10.png" /><s:text name="cmn_category"/></a>
			</li>
		</ul> --%>
		<div>
			<label><s:text name="cmn_name" /><input type="text" class="int" id="orgUnitName" value=""></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForRightContent();" /> </label>
		</div>
		<%-- <div class="search_advanced">
			<label><s:text name="cmn_name"/><input type="text" class="int" name=""></label>
			<label><s:text name="cmn_my_item"/><input type="checkbox" value="" name=""></label>
			<label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name=""><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name=""></label>
			<label><s:text name="cmn_name"/><input type="text" class="int" name=""></label>
			<label><s:text name="cmn_my_item"/><input type="checkbox" value="" name=""></label>
			<label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name=""><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name=""></label>
		</div> --%>
	</div>

	<%--   <div id="number">
		<span class="num_left_bg"></span>
		<span class="num_right_bg"></span>
        <ul id="numBox" class="numBox">
       		<li><a href="#">111</a></li>
        	<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
        	<s:iterator value="categoryList" var="c">
        		<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());">${c.name}</a></li>
        	</s:iterator>
        </ul>
    </div> --%>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<div class="left_content" style="height: 500px;">
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">
				<!--
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/hr/position/orgPositionAction!findOrgAndUnitTreeToJson.action",
						autoParam:["treeId","treeType"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.treeId);
					$("#selectIdTreeId").val(treeNode.tId);
					//alert(treeNode.tId+ "--" + treeNode.treeType );
					pager("start","${vix}/hr/position/orgPositionAction!goSingleList.action?treeId="+treeNode.treeId+"&treeType="+treeNode.treeType,"category");
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
					<li class="current"><a onclick="categoryTab(3,1,'a',event,'category')"><img src="img/positionsam.png" alt="" />岗位</a></li>
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