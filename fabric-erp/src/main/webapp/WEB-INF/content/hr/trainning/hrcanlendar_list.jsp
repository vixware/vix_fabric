<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
var name = "";
function loadName(){}
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
	$.ajax({
		  url:'${vix}/hr/hrCanlendarAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 660,
					height : 380,
					title:"<s:text name='category'/>",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							$.post('${vix}/hr/hrCanlendarAction!saveOrUpdate.action',
								 {'category.id':$("#id").val(),
								  'category.parentCategory.id':$("#parentId").val(),
								  'category.name':$("#name").val(),
								  'category.status': $(":radio[name=status][checked]").val(),
								  'category.memo':$("#memo").val(),
								  'addCategoryBrandIds':$("#addCategoryBrandIds").val(),
								  'deleteCategoryBrandIds':$("#deleteCategoryBrandIds").val(),
								  'addCategoryDimensionIds':$("#addCategoryDimensionIds").val(),
								  'deleteCategoryDimensionIds':$("#deleteCategoryDimensionIds").val()
								},
								function(result){
									asyncbox.success(result,"<s:text name='message'/>",function(action){
										var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
										if(null != treeNode){
											treeNode.isParent = true;
										}
										zTree.reAsyncChildNodes(treeNode, "refresh");
										pager("start","${vix}/hr/hrCanlendarAction!goSingleList.action?name="+name+"&id="+$("#selectId").val(),'category');
									});
								}
							 );
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};

function deleteById(id){
	$.ajax({
		  url:'${vix}/hr/hrCanlendarAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='message'/>",function(action){
				var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
				if(null != treeNode){
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes(treeNode, "refresh");
				pager("start","${vix}/hr/hrCanlendarAction!goSingleList.action?name="+name,'category');
			});
		  }
	});
}

function searchForRightContent(){
	loadName();
	pager("start","${vix}/hr/hrCanlendarAction!goSingleList.action?name="+name,'category');
}

loadName();
//载入分页列表数据
pager("start","${vix}/hr/hrCanlendarAction!goSingleList.action?name="+name,'category');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/hr/hrCanlendarAction!goSingleList.action?name=",'category');
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
	pager("start","${vix}/hr/hrCanlendarAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'category');
}

function categoryPager(tag,entity){
	loadName();
	if(entity == 'category'){
		pager(tag,"${vix}/hr/hrCanlendarAction!goSingleList.action?name="+name+'&parentId='+$('#selectId').val(),entity);
	}
	if(entity == 'brand'){
		pager(tag,"${vix}/hr/hrCanlendarAction!goSubSingleList.action?tag=list&categoryId="+$('#selectId').val(),entity);
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
				<li><a href="#"><img src="img/hr_empma.png" alt="" /> <s:text name="hr_humanr_resources" /> </a></li>
				<li><a href="#">基础设置</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/hrCanlendarAction!goList.action');">公共日历</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0,$('#selectId').val());"><span><s:text name="cmn_add" /></span></a> <a href="#" onclick="loadContent('${vix}/common/canlendarAction!goCanlendar.action');" onclick="saveOrUpdate(0,$('#selectId').val());"><span>跳转日历</span></a>
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
		<ul>
			<li class="fly"><a id="recordYear" href="#">请选择年份</a>
				<ul>
					<li><a href="#" onclick="changeYear(this)">2010</a></li>
					<li><a href="#" onclick="changeYear(this)">2011</a></li>
					<li><a href="#" onclick="changeYear(this)">2012</a></li>
					<li><a href="#" onclick="changeYear(this)">2013</a></li>
					<li><a href="#" onclick="changeYear(this)">2014</a></li>
				</ul></li>
			<li class="fly"><a id="recordMonth" href="#">请选择月份</a>
				<ul>
					<li><a href="#" onclick="changeMonth(this)">01</a></li>
					<li><a href="#" onclick="changeMonth(this)">02</a></li>
					<li><a href="#" onclick="changeMonth(this)">03</a></li>
					<li><a href="#" onclick="changeMonth(this)">04</a></li>
					<li><a href="#" onclick="changeMonth(this)">05</a></li>
					<li><a href="#" onclick="changeMonth(this)">06</a></li>
					<li><a href="#" onclick="changeMonth(this)">07</a></li>
					<li><a href="#" onclick="changeMonth(this)">08</a></li>
					<li><a href="#" onclick="changeMonth(this)">09</a></li>
					<li><a href="#" onclick="changeMonth(this)">10</a></li>
					<li><a href="#" onclick="changeMonth(this)">11</a></li>
					<li><a href="#" onclick="changeMonth(this)">12</a></li>
				</ul></li>
			<li><a href="#" onclick="recordSearch();">开始查询</a></li>
		</ul>
		<div>
			<label><s:text name="cmn_name" /><input type="text" class="int" id="codeS"></label> <label><s:text name="cmn_my_item" /><input type="checkbox" value="" name=""></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" /><input type="button"
				value="<s:text name='cmn_reset'/>" class="btn" name="" /></label> <strong id="lb_search_advanced"><s:text name="cmn_advance_search" /></strong>
		</div>
		<div class="search_advanced">
			<label><s:text name="cmn_name" /><input type="text" class="int" name=""></label> <label><s:text name="cmn_my_item" /><input type="checkbox" value="" name=""></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name=""><input type="button" value="<s:text name='cmn_reset'/>"
				class="btn" name=""></label> <label><s:text name="cmn_name" /><input type="text" class="int" name=""></label> <label><s:text name="cmn_my_item" /><input type="checkbox" value="" name=""></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name=""><input type="button"
				value="<s:text name='cmn_reset'/>" class="btn" name=""></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<li><a href="#">111</a></li>
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="categoryList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());">${c.name}</a></li>
			</s:iterator>
		</ul>
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
			<!--
			var zTree;			
			var setting = {
				async: {
					enable: true,
					url:"${vix}/hr/attendanceRecordAction!findOrgAndUnitTreeToJson.action",
					autoParam:["id","treeType"]
				},
				callback: {
					onClick: onClick
				}
			};
			function onClick(event, treeId, treeNode, clickFlag) {
				$("#selectId").val(treeNode.id);
				$("#selectIdTreeId").val(treeNode.tId);
				pager("start","${vix}/hr/attendanceRecordAction!goRecordList.action?parentId="+treeNode.id,"category");
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
					<li class="current"><a onclick="categoryTab(3,1,'a',event,'category')"><img src="img/holidaysam.png" alt="" />法定假日</a></li>
					<li><a onclick="categoryTab(3,2,'a',event,'brand')"><img src="img/adjust_view_day.png" alt="" />公休日调整</a></li>
				</ul>
			</div>
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /></a>
							<ul>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
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
			<div class="right_content" id="a2" style="display: none;">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /></a>
							<ul>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectBrandCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','brand');"></a></span> <span><a class="previous" onclick="categoryPager('previous','brand');"></a></span> <em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name='cmn_to' /> <b class="brandTotalCount">${pager.totalCount}</b>)
						</em> <span><a class="next" onclick="categoryPager('next','brand');"></a></span> <span><a class="end" onclick="categoryPager('end','brand');"></a></span>
					</div>
				</div>
				<div id="brand" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name="cmn_choose" /></a>
							<ul>
								<li><a href="#"><s:text name="cmn_delete" /></a></li>
								<li><a href="#"><s:text name="cmn_email" /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectBrandCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','brand');"></a></span> <span><a class="previous" onclick="categoryPager('previous','brand');"></a></span> <em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name="cmn_to" /> <b class="brandTotalCount">${pager.totalCount}</b>)
						</em> <span><a class="next" onclick="categoryPager('next','brand');"></a></span> <span><a class="end" onclick="categoryPager('end','brand');"></a></span>
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