<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
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
function saveOrUpdate(id,pageNo){
	$.ajax({
		  url:'${vix}/crm/accountAction!goSaveOrUpdate.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
};

function deleteById(id){
	$.ajax({
		  url:'${vix}/crm/accountAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='cmn_message'/>",function(action){
				var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
				if(null != treeNode){
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes(treeNode, "refresh");
				pager("start","${vix}/crm/accountAction!goListContent.action?name="+name,'account');
			});
		  }
	});
}

function searchForRightContent(){
	loadName();
	pager("start","${vix}/crm/accountAction!goListContent.action?name="+name,'account');
}

loadName();
//载入分页列表数据
pager("start","${vix}/crm/accountAction!goListContent.action?name="+name+"&pageNo=${pageNo}",'account');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/crm/accountAction!goListContent.action?name=",'account');
}
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#accountOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/crmaccountrAction!goListContent.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'account');
}

function accountPager(tag,entity){
	loadName();
	if(entity == 'account'){
		pager(tag,"${vix}/crm/accountAction!goListContent.action?name="+name+'&parentId='+$('#selectId').val(),entity);
	}
}

bindSearch();
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
loadMenuContent('${vix}/crm/project/crmProjectAction!goMenuContent.action');

</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/customer.png" alt="" />
					<s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#"><s:text name="crm_accountrelationshipmanagement" /></a></li>
				<li><a href="#"><s:text name='crm_accountmanagement' /></a></li>
				<li><a href="#"><s:text name='crm_crmlist' /></a></li>
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
			<!-- 菜单栏->状态 -->
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png">
				<s:text name="cmn_category" /></a>
				<ul>
					<li><a href="#"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_unapproved_plan" /></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_approval_by_plan" /></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_approval_in" /></a></li>
				</ul></li>
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
		<!-- 页面左上角按钮部分 -->
		<div>
			<label><s:text name="cmn_content" /><input type="text" class="int" id="nameS"></label>
			<!--  	<label><s:text name="my_item"/><input type="checkbox" value="" name=""></label>-->
			<label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();"><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name=""></label> <strong id="lb_search_advanced"><s:text name="cmn_advance_search" /></strong>
		</div>
		<div class="search_advanced">
			<label><s:text name="sa_ids" /><input type="text" class="int" name=""></label>
			<!--  	<label><s:text name="my_item"/><input type="checkbox" value="" name=""></label>-->
			<!--    <label><input type="button" value="<s:text name='search'/>" class="btn" name=""><input type="button" value="<s:text name='reset'/>" class="btn" name=""></label> -->
			<label><s:text name="sa_subjects" /><input type="text" class="int" name=""></label>
			<!--  	<label><s:text name="my_item"/><input type="checkbox" value="" name=""></label>-->
			<!--  	<label><input type="button" value="<s:text name='search'/>" class="btn" name=""><input type="button" value="<s:text name='reset'/>" class="btn" name=""></label> -->
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
		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><a onclick="accountTab(3,1,'a',event,'account')"><img src="img/mail.png" alt="" />
						<s:text name='crm_crmlist' /></a></li>
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
						<span><a class="start" onclick="accountPager('start','account');"></a></span> <span><a class="previous" onclick="accountPager('previous','account');"></a></span> <em>(<b class="accountInfo"></b> <s:text name='cmn_to' /> <b class="accountTotalCount"></b>)
						</em> <span><a class="next" onclick="accountPager('next','account');"></a></span> <span><a class="end" onclick="accountPager('end','account');"></a></span>
					</div>
				</div>
				<div id="account" class="table"></div>
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
						<span><a class="start" onclick="accountPager('start','account');"></a></span> <span><a class="previous" onclick="accountPager('previous','account');"></a></span> <em>(<b class="accountInfo"></b> <s:text name='cmn_to' /> <b class="accountTotalCount"></b>)
						</em> <span><a class="next" onclick="accountPager('next','account');"></a></span> <span><a class="end" onclick="accountPager('end','account');"></a></span>
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