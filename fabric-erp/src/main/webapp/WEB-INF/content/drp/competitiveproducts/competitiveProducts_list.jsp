<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
loadMenuContent('${vix}/drp/drpMainAction!goMenuContent.action');
var name = "";
var code = "";
var price = "";
var nameOr = "";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function loadCode(){
	code = $('#code').val();
	code = Url.encode(code);
	code = Url.encode(code);
}
function loadPrice(){
	price = $('#price').val();
	price = Url.encode(price);
	price = Url.encode(price);
}
function loadNameOr(){
	nameOr = $('#nameOr').val();
	nameOr = Url.encode(nameOr);
	nameOr = Url.encode(nameOr);
}
function saveOrUpdate(id,pageNo){
	$.ajax({
		  url:'${vix}/drp/competitiveProductsAction!goSaveOrUpdate.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
};
function searchForRightContent(){
	loadName();
	pager("start","${vix}/drp/competitiveProductsAction!goSingleList.action?name="+name,'competingProducts');
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/drp/competitiveProductsAction!goSingleList.action?name="+name,'competingProducts');
//排序 
function orderBy(orderField){
	var orderBy = $("#competingProductsOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/drp/competitiveProductsAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy,'competingProducts');
}
/*搜索*/
function searchForContent(i){
	if(i==0){
		loadName();
		pager("start","${vix}/drp/competitiveProductsAction!goSingleList.action?name="+name,'competingProducts');
	}else{
		loadNameOr();
		loadPerson();
		pager("start","${vix}/drp/competitiveProductsAction!goSingleList.action?name="+nameOr+"&code="+code+"&price="+price,'competingProducts');
	}
}
//最近使用
function srmRecent(rencentDate){
	pager("start","${vix}/drp/competitiveProductsAction!goSingleList.action?updateTime="+rencentDate,'competingProducts');
}
/**重置搜索*/
function resetForContent(i){
	if(i == 0){
		$("#nameS").val("");
	}
	else{
		$("#code").val("");
		$("#nameOr").val("");
		$("#price").val("");
	}
}
bindSearch();
function categoryPager(tag,category){
	loadName();
	pager(tag,"${vix}/drp/competitiveProductsAction!goSingleList.action",category);
}
//批量删除
function deleteEntitys() {
	var ids = '';
	$("[name='chkId']").each(function() {
		if ($(this).attr('checked')) {
			ids += $(this).val() + ",";
		}
	});
	$.ajax({
		url : '${vix}/drp/competitiveProductsAction!deleteByIds.action?ids=' + ids,
		cache : false,
		success : function(html) {
			asyncbox.success(html, "提示信息", function(action) {
				pager("start", "${vix}/drp/competitiveProductsAction!goSingleList.action?1=1", 'competingProducts');
			});
		}
	});
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_channel.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/drpAction!goList.action','bg_02');"><s:text name="drp_management" /> </a></li>
				<li><a href="#"><s:text name="drp_market_research_management" /> </a></li>
				<li><a href="#">竞争信息管理</a></li>
				<li><a href="#">竞争产品管理</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0);"><span><s:text name='cmn_add' /> </span> </a>
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
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_recently_used" /> </a>
				<ul>
					<li><a href="#" onclick="srmRecent('T1')"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#" onclick="srmRecent('T2')"><img alt="" src="img/icon_10.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#" onclick="srmRecent('T3')"><img alt="" src="img/icon_10.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#" onclick="srmRecent('T4')"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div>
			<label><s:text name="cmn_content" /><input type="text" class="int" id="nameS"> </label> <label> <input id="simpleSearch" type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent(0);"> <input id="simpleReset" type="button" value="<s:text name='cmn_reset'/>" class="btn"
				onclick="resetForContent(0);">
			</label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<s:iterator value="competingProductsList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});">${c.name}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><img src="img/mail.png" alt="" /> <s:text name="drp_competitive_products" /></li>
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
						<span><a class="start" onclick="categoryPager('start','competingProducts');"></a> </span> <span><a class="previous" onclick="categoryPager('previous','competingProducts');"></a> </span> <em>(<b class="competingProductsInfo"></b> <s:text name='cmn_to' /> <b class="competingProductsTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next','competingProducts');"></a> </span> <span><a class="end" onclick="categoryPager('end','competingProducts');"></a> </span>
					</div>
				</div>
				<div id="competingProducts" class="table"></div>
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
						<span><a class="start" onclick="categoryPager('start','competingProducts');"></a> </span> <span><a class="previous" onclick="categoryPager('previous','competingProducts');"></a> </span> <em>(<b class="competingProductsInfo"></b> <s:text name='cmn_to' /> <b class="competingProductsTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next','competingProducts');"></a> </span> <span><a class="end" onclick="categoryPager('end','competingProducts');"></a> </span>
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