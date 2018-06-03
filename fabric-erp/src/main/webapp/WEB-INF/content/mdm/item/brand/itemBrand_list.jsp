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
		  url:'${vix}/mdm/item/itemBrandAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 820,
					height : 320,
					title:"${vv:varView('vix_mdm_item')}品牌",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#itemBrandForm').validationEngine('validate')){
								$.post('${vix}/mdm/item/itemBrandAction!saveOrUpdate.action',
									{'itemBrand.id':$("#id").val(),
										'itemBrand.name':$("#name").val(),
										'itemBrand.code':$("#code").val(),
										'itemBrand.amount':$("#amount").val(),
										'itemBrand.brandCompany':$("#brandCompany").val(),
										'itemBrand.companyAddress':$("#companyAddress").val(),
										'itemBrand.orderBy':$("#orderBy").val(),
										'itemBrand.memo':$("#memo").val()
									},
									function(result){
										asyncbox.success(result,"<s:text name='vix_message'/>",function(action){
											pager("current","${vix}/mdm/item/itemBrandAction!goListContent.action?name="+name,'itemBrand');
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
		  url:'${vix}/mdm/item/itemBrandAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='vix_message'/>",function(action){
				zTree.reAsyncChildNodes(zTree.getNodeByTId($("#selectIdTreeId").val()), "refresh");
				pager("current","${vix}/mdm/item/itemBrandAction!goListContent.action?name="+name,'itemBrand');
			});
		  }
	});
}
function searchForRightContent(){
	loadName();
	pager("start","${vix}/mdm/item/itemBrandAction!goListContent.action?name="+name+"&id="+$("#selectId").val(),'itemBrand');
}
loadName();
//载入分页列表数据
pager("start","${vix}/mdm/item/itemBrandAction!goListContent.action?name="+name,'itemBrand');
function loadRoot(){
	$('#nameS').val("");
	$('#selectId').val("");
	$("#selectIdTreeId").val("");
	zTree.refresh();
	pager("start","${vix}/mdm/item/itemBrandAction!goListContent.action?name=",'itemBrand');
}
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#itemBrandOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("current","${vix}/mdm/item/itemBrandAction!goListContent.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&id="+$("#selectId").val(),'itemBrand');
}

bindSwitch();
bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/mdm/item/itemBrandAction!goListContent.action?name="+name,'itemBrand');
}

function currentPagerClick(input,event){
	loadName();
	pagerClick(input,event,"${vix}/mdm/item/itemBrandAction!goListContent.action?name="+name,'itemBrand');
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
loadMenuContent('${vix}/sales/salesAction!goMenuContent.action');
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="${vix}/common/img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="${vix}/common/img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/sys_settings.png" alt="" />系统管理</a></li>
				<li><a href="#">${vv:varView('vix_mdm_item')}管理</a></li>
				<li><a href="#">设置</a></li>
				<li><a href="#" onclick="loadContent('${vix}/mdm/item/itemBrandAction!goList.action');">${vv:varView('vix_mdm_item')}品牌</a></li>
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
		<div>
			<label><s:text name="cmn_name" /><input type="text" class="int" id="nameS"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForRightContent();" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" /></label> <strong id="lb_search_advanced"><s:text
					name="cmn_advance_search" /></strong>
		</div>
		<div class="search_advanced">
			<label><s:text name="cmn_name" /><input type="text" class="int" name=""></label> <label><s:text name="cmn_name" /><input type="text" class="int" name=""></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name=""><input type="button" value="<s:text name='cmn_reset'/>" class="btn"
				name=""></label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="right">
			<div class="right_content">
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="itemBrandInfo"></b> <s:text name='cmn_to' /> <b class="itemBrandTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="itemBrand" style="overflow-x: auto; overflow-y: hidden; width: 100%;"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="itemBrandInfo"></b> <s:text name='cmn_to' /> <b class="itemBrandTotalCount"></b>)
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