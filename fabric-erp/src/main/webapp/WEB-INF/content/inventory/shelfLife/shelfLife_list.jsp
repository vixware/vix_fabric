<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	loadMenuContent('${vix}/inventory/inventoryMainAction!goMenuContent.action');
	/* 内容 */
	var searchContent = "";
	function loadSearchContent() {
		searchContent = $('#nameS').val();
		searchContent = Url.encode(searchContent);
		searchContent = Url.encode(searchContent);
	}
	var goodsCode = "";
	function loadGoodsCode() {
		goodsCode = $('#goodsCode').val();
		goodsCode = Url.encode(goodsCode);
		goodsCode = Url.encode(goodsCode);
	}
	var goodsName = "";
	function loadGoodsName() {
		goodsName = $('#goodsName').val();
		goodsName = Url.encode(goodsName);
		goodsName = Url.encode(goodsName);
	}
	//载入分页列表数据
	pager("start", "${vix}/inventory/shelfLifeAction!goSingleList.action?1=1", 'inventoryCurrentStock');
	//排序 
	function orderBy(orderField) {
		var orderBy = $("#inventoryCurrentStockOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/inventory/shelfLifeAction!goSingleList.action?orderField=" + orderField + "&orderBy=" + orderBy, 'inventoryCurrentStock');
	}
	function currentPager(tag) {
		pager(tag, "${vix}/inventory/shelfLifeAction!goSingleList.action?1=1", 'inventoryCurrentStock');
	}
	/* 搜索 */
	function searchForContent() {
		loadSearchContent();
		loadGoodsCode();
		loadGoodsName();
		pager("start", "${vix}/inventory/shelfLifeAction!goSingleList.action?searchContent=" + searchContent + "&goodsCode=" + goodsCode + "&goodsName=" + goodsName, 'inventoryCurrentStock');
	}
	function resetForContent() {
		$("#nameS").val("");
		$("#goodsCode").val("");
		$("#goodsName").val("");
	}
	bindSearch();
	function goSearch() {
		$
				.ajax({
				url : '${vix}/inventory/shelfLifeAction!goSearch.action',
				cache : false,
				success : function(html) {
					asyncbox
							.open({
							modal : true,
							width : 650,
							height : 300,
							title : "查询条件",
							html : html,
							callback : function(action) {
								if (action == 'ok') {
									pager("start", "${vix}/inventory/shelfLifeAction!goSingleList.action?goodsCode=" + $('#goodsCode').val() + "&goodsName=" + $('#goodsName')
											.val(), 'inventoryCurrentStock');
								}
							},
							btnsbar : $.btn.OKCANCEL
							});
				}
				});
	};
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/inv_shelfLife.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">库存管理 </a></li>
				<li><a href="#">初始设置 </a></li>
				<li><a href="#">保质期管理</a></li>
				<li><a href="#">保质期查询</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img alt="" src="img/icon_11.png"> </b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> <b>帮助</b>
				</strong>
				<p>保质期查询</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/icon_10.png" alt="" /> <s:text name="cmn_index" /></a></li>
		</ul>
		<div>
			<label>内容:<input type="text" class="int" id="nameS" placeholder="编码"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent(0);" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" /></label> <label> <input
				type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
		<%-- <div class="search_advanced">
			<label>编码:<input type="text" class="int" name="" id="goodsCode" placeholder="编码"></label> <label>名称:<input type="text" class="int" name="" id="goodsName" placeholder="名称"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name="" onclick="searchForContent();"><input type="button"
				value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent();"></label>
		</div> --%>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<s:iterator value="inventoryCurrentStockList" var="c">
				<li><a href="#">${c.itemcode}</a></li>
			</s:iterator>
		</ul>
	</div>
	<div class="box">
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
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
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
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
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>