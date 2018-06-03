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
$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});
$(function(){
	if($('#numBox').length) $('#numBox').listmenu({menuWidth: '100%', cols:{ count:6, gutter:0 }});
});

/* 新增分类 */
function saveOrUpdateBillsCategory(id,parentId){
	$.ajax({
		  url:'${vix}/sales/config/saleBillTypeAction!goSaveOrUpdateBillsCategory.action?id='+id+"&parentId="+parentId,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 650,
					height : 475,
					title:"单据分类",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#billsCategoryForm').validationEngine('validate')){
							$.post('${vix}/sales/config/saleBillTypeAction!saveOrUpdateBillsCategory.action',
									 {
									  'billsCategory.id':$("#billsCategoryId").val(),
									  'billsCategory.categoryCode':$("#billsCategoryDictionaryCategoryCode").val(),
									  'billsCategory.companyCode':$("#companyCode").val(),
									  /* 'billsCategory.categoryName':$("#categoryName").val(), */
									  'billsCategory.categoryDescription':$("#categoryDescription").val()
									},
									function(result){
									    showMessage(result);
									    setTimeout("", 1000);
									    loadContent('${vix}/sales/config/saleBillTypeAction!goList.action');
									}
								 );
							}else {
								return false;
							}
						}
						},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};

/* 新增类型 */
function saveOrUpdateBillsType(id,billsPropertyId){
	$.ajax({
		  url:'${vix}/sales/config/saleBillTypeAction!goSaveOrUpdate.action?id='+id+"&billsPropertyId="+billsPropertyId,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 850,
					height : 575,
					title:"单据类型",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#billsTypeForm').validationEngine('validate')){
							$.post('${vix}/sales/config/saleBillTypeAction!saveOrUpdate.action',
								 {
								  'billsType.id':$("#billsTypeId").val(),
								  'billsType.billsProperty.id':$("#billsPropertyId").val(),
								  'billsType.typeCode':$("#typeCode").val(),
								  'billsType.typeName':$("#typeName").val(),
								  'billsType.typeDescription':$("#typeDescription").val()
								},
								function(result){
								    showMessage(result);
								    setTimeout("", 1000);
								    loadContent('${vix}/sales/config/saleBillTypeAction!goList.action');
								}
							 );
							}else {
								return false;
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};

function searchForRightContent(){
	loadName();
	pager("start","${vix}/sales/config/saleBillTypeAction!goSingleList.action?name="+name,'saleBillType');
}
loadName();
//载入分页列表数据
pager("start","${vix}/sales/config/saleBillTypeAction!goSingleList.action?name="+name,'saleBillType');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/sales/config/saleBillTypeAction!goSingleList.action?name=",'saleBillType');
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
	pager("start","${vix}/sales/config/saleBillTypeAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'saleBillType');
}
bindSearch();
bindSwitch();
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
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/sales/config/saleBillTypeAction!goSingleList.action?name="+name,'saleBillType');
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/site.png" alt="" />供应链</a></li>
				<li><a href="#">供应链</a></li>
				<li><a href="#">销售管理</a></li>
				<li><a href="#">设置</a></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/config/saleBillTypeAction!goList.action');">销售单据类型</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdateBillsType(0,$('#selectId').val());"><span>新增</span></a>
		</p>
	</div>
</div>
<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /> </b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> <b>帮助</b>
				</strong>
				<p>Launched in January 1998, People's Daily Online is a website built by People's Daily, the official newspaper of the Communist Party of China. Containing</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_mode" /> </a>
				<ul>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_unapproved_plan" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_approval_by_plan" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_approval_in" /> </a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_recently_used" /> </a>
				<ul>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div>
			<label><s:text name="cmn_content" /><input type="text" class="int" id="nameS"> </label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();"><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name=""> </label> <label> <input type="button"
				value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
		<div class="search_advanced">
			<label><s:text name="drp_company_code1" /><input type="text" class="int" name=""> </label><label><s:text name="drp_company_name1" /><input type="text" class="int" name=""> </label><label><s:text name="drp_dealers_name1" /><input type="text" class="int" name=""> </label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<s:iterator value="saleBillTypeList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});">${c.code}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<div class="left_content" style="height: 500px;">
				<%-- <div style="padding-left: 4px;">
					<img src="${vix}/common/img/file.png" style="padding-right: 5px;" /><a href="#"
						onclick="loadRoot();">单据管理</a>
				</div> --%>
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">	
			    var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/sales/config/saleBillTypeAction!findOrgAndUnitTreeToJson.action",
						autoParam:["id","treeType"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdType").val(treeNode.treeType);
					$("#selectIdTreeId").val(treeNode.tId);
					pager("start","${vix}/sales/config/saleBillTypeAction!goSingleList.action?id="+treeNode.id+"&treeType="+treeNode.treeType,"saleBillType");
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
					<li class="current"><img src="img/mail.png" alt="" />单据类型</li>
				</ul>
			</div>
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_add' /> </a></li>
								<li><a href="#"><s:text name='cmn_update' /> </a></li>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount1">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="saleBillTypeInfo"></b> <s:text name='cmn_to' /> <b class="saleBillTypeTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="saleBillType" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="saleBillTypeInfo"></b> <s:text name='cmn_to' /> <b class="saleBillTypeTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
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