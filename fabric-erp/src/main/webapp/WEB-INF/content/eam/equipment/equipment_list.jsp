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
	$('#number').stop().animate({height: 'toggle', opacity: 'toggle'},500,function(){
		$('#number').css('overflow','visible');
	}); 
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
	alert(id);
	$.ajax({
		  url:'${vix}/eam/equipmentAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
};

function deleteByIds(){
	var ids = '';
	$("[name='chkId']").each(function(){  
		if($(this).attr('checked')){
			ids+=$(this).val()+",";  
		}
	});
	asyncbox.success(ids,"选中的id");
}

function deleteById(id){
	$.ajax({
		  url:'${vix}/eam/equipmentAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"提示信息",function(action){
				pager("start","${vix}/eam/equipmentAction!goListContent.action?eqname="+name,'equipment');
			});
		  }
	});
}

function searchForContent(){
	loadName();
	pager("start","${vix}/eam/equipmentAction!goListContent.action?eqname="+name,'equipment');
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/eam/equipmentAction!goListContent.action?eqname="+name,'equipment');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#brandOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/eam/equipmentAction!goListContent.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name,'equipment');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/eam/equipmentAction!goListContent.action?eqname="+name,'equipment');
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
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/drp_channel.png" alt="" />
					<s:text name="eam_enterpriseassetmanagement" /></a></li>
				<li><a href="#"><s:text name="eam_equipmentinformationmanagement" /></a></li>
				<li><a href="#"><s:text name='eam_accountmanagement' /></a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0,$('#selectId').val());"><span><s:text name="cmn_add" /></span></a> <a href="#" onclick="deleteByIds(0,$('#selectId').val());"><span><s:text name='cmn_delete' /></span></a>
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
			<li><a href="#" id="numBtn"><img src="img/icon_10.png" alt="" />
				<s:text name="cmn_index" /></a></li>
			<li><a href="#"><img alt="" src="img/icon_10.png">
				<s:text name="cmn_mode" /></a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png">
				<s:text name="cmn_recently_used" /></a>
				<ul>
					<li><a href="#"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_three_days" /></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_seven_days" /></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_month" /></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_three_months" /></a></li>
				</ul></li>
		</ul>
		<div>
			<label><s:text name='cmn_content' /><input type="text" class="int" id="nameS"></label> <label><input name="" type="button" class="btn" value="<s:text name='cmn_search'/>" /><input name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" /></label> <strong id="lb_search_advanced"><s:text name='cmn_advance_search' /></strong>
		</div>
		<div class="search_advanced">
			<label><s:text name='cmn_content' /><input name="" type="text" class="int" /></label> <label><input name="" type="button" class="btn" value="<s:text name='cmn_search'/>" /><input name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" /></label> <label><s:text name='cmn_content' /><input name="" type="text" class="int" /></label>
			<label><input name="" type="button" class="btn" value="<s:text name='cmn_search'/>" /><input name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" /></label>
		</div>
	</div>

	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<li><a href="#">111</a></li>
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="equipmentList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());">${c.eqname}</a></li>
			</s:iterator>
		</ul>
	</div>

	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<div class="left_content" style="height: 500px;">
				<div style="padding-left: 4px;">
					<img src="${vix}/common/img/file.png" style="padding-right: 5px;" /><a href="#" onclick="loadRoot();"><s:text name='cmn_rootDirectory' /></a>
				</div>
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">
				<!--
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/eam/equipmentAction!findTreeToJson.action",
						autoParam:["id", "name=n", "level=lv"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdTreeId").val(treeNode.tId);
					pager("start","${vix}/eam/equipmentAction!goListContent.action?categoryId="+treeNode.id,"equipment");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
				//-->
			</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><a onclick="categoryTab(3,1,'a',event,'category')"><img src="img/mail.png" alt="" />
						<s:text name='eam_accountmanagement' /></a></li>
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
						<span><a class="start" onclick="categoryPager('start','equipment');"></a></span> <span><a class="previous" onclick="categoryPager('previous','equipment');"></a></span> <em>(<b class="categoryInfo"></b> <s:text name='cmn_to' /> <b class="categoryTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next','equipment');"></a></span> <span><a class="end" onclick="categoryPager('end','equipment');"></a></span>
					</div>
				</div>
				<div id="equipment" class="table"></div>
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
						<span><a class="start" onclick="categoryPager('start','equipment');"></a></span> <span><a class="previous" onclick="categoryPager('previous','equipment');"></a></span> <em>(<b class="categoryInfo"></b> <s:text name='cmn_to' /> <b class="categoryTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next','equipment');"></a></span> <span><a class="end" onclick="categoryPager('end','equipment');"></a></span>
					</div>
				</div>
			</div>

		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>