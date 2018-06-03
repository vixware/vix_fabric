<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
/* 内容 */
var name = "";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
var selectCode = "";
function loadSelectCode(){
	selectCode = $('#selectCode').val();
	selectCode=Url.encode(selectCode);
	selectCode=Url.encode(selectCode);
}
var selectName = "";
function loadSelectName(){
	selectName = $('#selectName').val();
	selectName=Url.encode(selectName);
	selectName=Url.encode(selectName);
}
/* 搜索 */
function searchForContent(tag){
	loadName();
	loadSelectCode();
	loadSelectName();
	if(tag==0){
     	pager("start","${vix}/inventory/productDisassemblyAction!goSingleList.action?name="+name,'inventoryCurrentStock');
	}else {
	    pager("start","${vix}/inventory/productDisassemblyAction!goSingleList.action?selectCode="+selectCode+"&selectName="+selectName,'inventoryCurrentStock');
	}
}
/* 重置 */
function resetForContent(){
	$("#nameS").val("");
}
function saveOrUpdate(id,pageNo){
	$.ajax({
		  url:'${vix}/inventory/productDisassemblyAction!goSaveOrUpdate.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
}

function deleteById(id){
	$.ajax({
		  url:'${vix}/inventory/productDisassemblyAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"提示信息",function(action){
				pager("start","${vix}/inventory/productDisassemblyAction!goSingleList.action?name="+name,'inventoryCurrentStock');
			});
		  }
	});
}
loadName();
//载入分页列表数据
pager("start","${vix}/inventory/productDisassemblyAction!goSingleList.action?name="+name,'inventoryCurrentStock');
//排序 
function orderBy(orderField){
	var orderBy = $("#inventoryCurrentStockOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/inventory/productDisassemblyAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy,'inventoryCurrentStock');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/inventory/productDisassemblyAction!goSingleList.action?name="+name,'inventoryCurrentStock');
}
//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/inventory/productDisassemblyAction!goSearch.action',
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
				pager("start", "${vix}/inventory/productDisassemblyAction!goSingleList.action?selectCode=" + $('#selectCode').val() + "&selectName=" + $('#selectName').val(), 'inventoryCurrentStock');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};
//批量删除
function deleteEntitys() {
	var ids = '';
	$("[name='chkId']").each(function() {
		if ($(this).attr('checked')) {
			ids += $(this).val() + ",";
		}
	});
	deleteByIds(ids);
}
function deleteByIds(ids) {
		$.ajax({
		url : '${vix}/inventory/productDisassemblyAction!deleteByIds.action?ids=' + ids,
		cache : false,
		success : function(html) {
			asyncbox.success(html, "提示信息", function(action) {
				pager("start", "${vix}/inventory/productDisassemblyAction!goSingleList.action?1=1", 'inventoryCurrentStock');
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
				<li><a href="#"><img src="${vix}/common/img/inv_productDisassembly.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">库存管理 </a></li>
				<li><a href="#">商品拆装管理业务</a></li>
				<li><a href="#">商品拆装</a></li>
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
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img alt="" src="img/icon_11.png"> </b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> <b>帮助</b>
				</strong>
				<p>商品拆装管理</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/icon_10.png" alt="" /> <s:text name="cmn_index" /></a></li>
		</ul>
		<div>
			<label>内容:<input type="text" class="int" id="nameS" placeholder="名称"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent(0);" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" /></label> <label> <input
				type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
		<%-- <div class="search_advanced">
			<label>编码:<input type="text" class="int" name="selectCode" id="selectCode" placeholder="编码"></label> <label>名称:<input type="text" class="int" name="selectName" id="selectName" placeholder="名称"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name="" onclick="searchForContent();"><input
				type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent();"></label>
		</div> --%>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="inventoryCurrentStockList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});">${c.itemcode}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#" onclick="deleteEntitys();"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="inventoryCurrentStockInfo"></b> <s:text name='cmn_to' /> <b class="inventoryCurrentStockTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="inventoryCurrentStock" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#" onclick="deleteEntitys();"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="inventoryCurrentStockInfo"></b> <s:text name='cmn_to' /> <b class="inventoryCurrentStockTotalCount"></b>)
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