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
$(document).ready(function(){
	if($('#numBox').length) $('#numBox').listmenu({menuWidth: '100%', cols:{ count:6, gutter:0 }});
});
$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});
function saveOrUpdate(id,tag){
	var pageNo = $("#customerAccountPageNoHidden").val();
	$.ajax({
		  url:'${vix}/crm/customer/crmCustomerAccountAdvanceSearchAction!goSaveOrUpdate.action?source=${source}&id='+id+"&customerAccountType="+tag+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
};
function goCustomerView(id,tag){
	var pageNo = $("#customerAccountPageNoHidden").val();
	$.ajax({
		  url:'${vix}/crm/customer/crmCustomerAccountAdvanceSearchAction!goCustomerView.action?source=${source}&id='+id+"&customerAccountType="+tag+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
};

function deleteById(id){
	$.ajax({
		  url:'${vix}/crm/customer/crmCustomerAccount!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='cmn_message'/>",function(action){
				pager("start","${vix}/crm/customer/crmCustomerAccountAdvanceSearchAction!goListContent.action?name="+name,'customerAccount');
			});
		  }
	});
}

function searchForRightContent(){
	loadName();
	pager("start","${vix}/crm/customer/crmCustomerAccountAdvanceSearchAction!goListContent.action?name="+name,'customerAccount');
}

loadName();
//载入分页列表数据
pager("start","${vix}/crm/customer/crmCustomerAccountAdvanceSearchAction!goListContent.action?name="+name+"&pageNo=${pageNo}",'customerAccount');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/crm/customer/crmCustomerAccountAdvanceSearchAction!goListContent.action?name=",'customerAccount');
}
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#customerAccountOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/crm/customer/crmCustomerAccountAdvanceSearchAction!goListContent.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'customerAccount');
}

function customerAccountPager(tag){
	loadName();
	pager(tag,"${vix}/crm/customer/crmCustomerAccountAdvanceSearchAction!goListContent.action?name="+name+'&parentId='+$('#selectId').val(),'customerAccount');
}
function shareCustomerAccount(){
	var ids = '';
	$("[name='chkCategoryId']").each(function(){  
		if($(this).attr('checked')){
			ids+=$(this).val()+",";  
		}
	});
	if(ids == ''){
		showMessage('请选择客户!');
		return;
	}
	$.ajax({
		  url:'${vix}/crm/customer/customerAccountShareAction!goCustomerAccountShare.action?type=share',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
				 	width : 880,
					height : 420,
					title:"批量共享",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							var empRead = "";
							var empWrite = "";
							var userGroupRead = "";
							var userGroupWrite = "";
							$("input[type=checkbox][id^='diyChkRead_']").each(function(){
								if($(this).attr("checked")){empRead += $(this).val() + ":1,";}else{empRead += $(this).val() + ":0,";}
							});
							$("input[type=checkbox][id^='diyChkWrite_']").each(function(){
								if($(this).attr("checked")){empWrite += $(this).val() + ":1,";}else{empWrite = $(this).val() + ":0,";}
							});
							$("input[type=checkbox][id^='userGroupChkRead_']").each(function(){
								if($(this).attr("checked")){userGroupRead = $(this).val() + ":1,";}else{userGroupRead = $(this).val() + ":0,";}
							});
							$("input[type=checkbox][id^='userGroupChkWrite_']").each(function(){
								if($(this).attr("checked")){userGroupWrite = $(this).val() + ":1,";}else{empRead = $(this).val() + ":0,";}
							});
							$.post('${vix}/crm/customer/customerAccountShareAction!batchShare.action',
								{'empRead':empRead,
								  'empWrite':empWrite,
								  'userGroupRead':userGroupRead,
								  'userGroupWrite':userGroupWrite,
								  'ids':ids
								},
								function(result){
									showMessage(result);
								}
							);
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function moveCustomerAccount(){
	var ids = '';
	$("[name='chkCategoryId']").each(function(){  
		if($(this).attr('checked')){
			ids+=$(this).val()+",";  
		}
	});
	if(ids == ''){
		showMessage('<div>请选择客户!</div>');
		return;
	}
	$.ajax({
		  url:'${vix}/crm/customer/customerAccountShareAction!goCustomerAccountShare.action?type=move',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 880,
					height : 420,
					title:"批量转移",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							var empRead = "";
							var empWrite = "";
							var userGroupRead = "";
							var userGroupWrite = "";
							$("input[type=checkbox][id^='diyChkRead_']").each(function(){
								if($(this).attr("checked")){empRead += $(this).val() + ":1,";}else{empRead += $(this).val() + ":0,";}
							});
							$("input[type=checkbox][id^='diyChkWrite_']").each(function(){
								if($(this).attr("checked")){empWrite += $(this).val() + ":1,";}else{empWrite = $(this).val() + ":0,";}
							});
							$("input[type=checkbox][id^='userGroupChkRead_']").each(function(){
								if($(this).attr("checked")){userGroupRead = $(this).val() + ":1,";}else{userGroupRead = $(this).val() + ":0,";}
							});
							$("input[type=checkbox][id^='userGroupChkWrite_']").each(function(){
								if($(this).attr("checked")){userGroupWrite = $(this).val() + ":1,";}else{empRead = $(this).val() + ":0,";}
							});	
							$.post('${vix}/crm/customer/customerAccountShareAction!batchMove.action',
								{'empRead':empRead,
								  'empWrite':empWrite,
								  'userGroupRead':userGroupRead,
								  'userGroupWrite':userGroupWrite,
								  'ids':ids
								},
								function(result){
									showMessage(result);
								}
							);
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
bindSearch();
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
bindSwitch();
$("#switch_box").click();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/customer.png" alt="" />供应链</a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#">客户管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/customer/crmCustomerAccountAdvanceSearchAction!goCustomerAccountShare.action');">客户共享和转移</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="shareCustomerAccount();"><span>批量共享</span></a> <a href="#" onclick="moveCustomerAccount();"><span>批量转移</span></a>
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
			<label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForRightContent();"><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name=""></label> <strong id="lb_search_advanced"><s:text name="cmn_advance_search" /></strong>
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
						url:"${vix}/crm/customer/crmCustomerAccountCategoryAction!findTreeToJson.action",
						autoParam:["id", "name=n", "level=lv"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					pager("start","${vix}/crm/customer/crmCustomerAccountAdvanceSearchAction!goListContent.action?id="+treeNode.id,'customerAccount');
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
				//-->
			</script>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" />
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /></a>
							<ul>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="customerAccountPager('start','account');"></a></span> <span><a class="previous" onclick="customerAccountPager('previous','account');"></a></span> <em>(<b class="customerAccountInfo"></b> <s:text name='cmn_to' /> <b class="customerAccountTotalCount"></b>)
						</em> <span><a class="next" onclick="customerAccountPager('next','account');"></a></span> <span><a class="end" onclick="customerAccountPager('end','account');"></a></span>
					</div>
				</div>
				<div id="customerAccount" class="table"></div>
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
						<span><a class="start" onclick="customerAccountPager('start','account');"></a></span> <span><a class="previous" onclick="customerAccountPager('previous','account');"></a></span> <em>(<b class="customerAccountInfo"></b> <s:text name='cmn_to' /> <b class="customerAccountTotalCount"></b>)
						</em> <span><a class="next" onclick="customerAccountPager('next','account');"></a></span> <span><a class="end" onclick="customerAccountPager('end','account');"></a></span>
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