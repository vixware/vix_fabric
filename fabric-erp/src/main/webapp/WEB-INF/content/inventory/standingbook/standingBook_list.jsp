<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	loadMenuContent('${vix}/inventory/inventoryMainAction!goMenuContent.action');
	var name = "";
	function loadName() {
		name = $('#nameS').val();
		name = Url.encode(name);
		name = Url.encode(name);
	}
	function standingbookPager(tag) {
		pager(tag, "${vix}/inventory/standingBookAction!goSingleList.action?name=" + name, 'standingbook');
	}
	function searchForContent() {
		loadName();
		pager("start", "${vix}/inventory/standingBookAction!goSingleList.action?name=" + name, 'standingbook');
	}
	function resetForContent() {
		$('#nameS').val('');
		$('#itemcode').val('');
		$('#itemname').val('');
	}
	loadName();
	//载入分页列表数据
	pager("start", "${vix}/inventory/standingBookAction!goSingleList.action?name=" + name, 'standingbook');
	//排序 
	function orderBy(orderField) {
		loadName();
		var orderBy = $("#standingbookOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/inventory/standingBookAction!goSingleList.action?orderField=" + orderField + "&orderBy=" + orderBy, 'standingbook');
	}
	bindSearch();
	/** 状态 */
	function saleOrderStatus(status) {
		pager("start", "${vix}/inventory/standingBookAction!goSingleList.action?status=" + status, 'standingbook');
	}
	/* 最近使用 */
	function leastRecentlyUsed(days) {
		pager("start", "${vix}/inventory/standingBookAction!goSingleList.action?days=" + days, 'standingbook');
	}
	//高级搜索
	function goSearch() {
		$.ajax({
		url : '${vix}/inventory/standingBookAction!goSearch.action',
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
					pager("start", "${vix}/inventory/standingBookAction!goSingleList.action?name=" + $('#nameS').val() + "&itemcode=" + $('#itemcode').val() + "&itemname=" + $('#itemname').val(), 'standingbook');
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	};

	function importXlsFile() {
		$.ajaxFileUpload({
		url : '${vix}/inventory/standingBookAction!importFile.action',//用于文件上传的服务器端请求地址
		secureuri : true,//是否安全提交,设置为true;设置为false，则出现乱码
		fileElementId : 'fileToUpload',//文件上传空间的id属性  <input type="file" id="file" name="file" />
		dataType : 'text',//返回值类型 ,可以使xml、text、json、html
		success : function(data, status) { //服务器成功响应处理函数
			var data = $.parseJSON(data);
			if (data.success == '1') {
				showMessage(data.msg);
				setTimeout("", 1000);
				loadContent('${vix}/inventory/standingBookAction!goList.action');
			} else {
				if (typeof (data.error) != 'undefined') {
					if (data.error != '') {
						showErrorMessage(data.error);
						setTimeout("", 1000);
					} else {
						showMessage(data.msg);
						setTimeout("", 1000);
					}
				}
			}
		},
		error : function(data, status, e) {//服务器响应失败处理函数
			showErrorMessage("上传错误!");
			setTimeout("", 1000);
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
				<li><a href="#"><img src="${vix}/common/img/inv_standingbook.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="wim_stock_control" /> </a></li>
				<li><a href="#"><s:text name="wim_standing_book" /> </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="loadContent('${vix}/inventory/initInventoryAction!goList.action');"><span>期初录入</span> </a> <a href="#" onclick="loadContent('${vix}/inventory/shelfLifeAction!goList.action');"><span>快到期商品查询</span> </a> <a href="#" onclick="$('#fileToUpload').click()"><span>导入价格</span></a>
		</p>
	</div>
</div>
<input type="file" id="fileToUpload" name="fileToUpload" onchange="importXlsFile();" style="display: none;" />
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
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_mode" /> </a>
				<ul>
					<li><a href="#" onclick="saleOrderStatus('0')"><img alt="" src="img/uncommitted.png"> 未提交 </a></li>
					<li><a href="#" onclick="saleOrderStatus('1')"><img alt="" src="img/unaudited.png"> 待审批 </a></li>
					<li><a href="#" onclick="saleOrderStatus('2')"><img alt="" src="img/verifying.png"> 审批中 </a></li>
					<li><a href="#" onclick="saleOrderStatus('3')"><img alt="" src="img/approved.png"> 已完成 </a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/leastRecentlyUsed.png"> <s:text name="cmn_recently_used" /> </a>
				<ul>
					<li><a href="#" onclick="leastRecentlyUsed('THREEDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('SEVENDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('ONEMONTH');"><img alt="" src="img/time_go.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('THREEMONTHS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div>
			<label>内容:<input type="text" class="int" id="nameS" placeholder="商品名称" style="width: 300px;"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent(0);" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" /></label> <label>
				<input type="button" value="高级搜索" class="btn" onclick="goSearch();" />
			</label>
		</div>
		<%-- <div class="search_advanced">
			<label>编码:<input type="text" class="int" name="itemcode" id="itemcode" placeholder="编码"></label> <label>商品名称:<input type="text" class="int" name="itemname" id="itemname" placeholder="商品名称" style="width: 300px;"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name=""
				onclick="searchForContent();"><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent();"></label>
		</div> --%>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="inventoryCurrentStockList" var="c">
				<li><a href="#"><span style="display: none;">${c.chineseCharacter}</span>${c.itemname}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<div class="left_content" style="height: 500px;">
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">
				var zTree;
				var setting = {
				async : {
				enable : true,
				url : "${vix}/inventory/warehouseAction!findInvWarehouseTreeToJson.action",
				autoParam : [ "id", "treeType" ]
				},
				callback : {
					onClick : onClick
				}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					pager("start", "${vix}/inventory/standingBookAction!goSingleList.action?parentId=" + treeNode.id + "&treeType=" + treeNode.treeType, "standingbook");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
			</script>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" />
		<!-- left -->
		<div id="right">
			<div class="right_content">
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
						<span><a class="start" onclick="standingbookPager('start');"></a> </span> <span><a class="previous" onclick="standingbookPager('previous');"></a> </span> <em>(<b class="standingbookInfo"></b> <s:text name='cmn_to' /> <b class="standingbookTotalCount"></b>)
						</em> <span><a class="next" onclick="standingbookPager('next');"></a> </span> <span><a class="end" onclick="standingbookPager('end');"></a> </span>
					</div>
				</div>
				<div id="standingbook" class="table"></div>
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
						<span><a class="start" onclick="standingbookPager('start');"></a> </span> <span><a class="previous" onclick="standingbookPager('previous');"></a> </span> <em>(<b class="standingbookInfo"></b> <s:text name='cmn_to' /> <b class="standingbookTotalCount"></b>)
						</em> <span><a class="next" onclick="standingbookPager('next');"></a> </span> <span><a class="end" onclick="standingbookPager('end');"></a> </span>
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