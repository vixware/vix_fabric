<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
/* 内容 */
var searchContent = "";
function loadSearchContent(){
	searchContent = $('#nameS').val();
	searchContent=Url.encode(searchContent);
	searchContent=Url.encode(searchContent);
}
var goodsCode = "";
function loadGoodsCode(){
	goodsCode = $('#goodsCode').val();
	goodsCode=Url.encode(goodsCode);
	goodsCode=Url.encode(goodsCode);
}
var goodsName = "";
function loadGoodsName(){
	goodsName = $('#goodsName').val();
	goodsName=Url.encode(goodsName);
	goodsName=Url.encode(goodsName);
}

function downloadItem(channelId){
	$.ajax({
	  url:'${vix}/business/itemDownLoadAction!goDownloadItem.action?channelId='+channelId,
	  cache: false,
	  success: function(){
	    loadContent('${vix}/business/itemDownLoadAction!goList.action');
	  }
});
};
function saveOrUpdate(id,pageNo){
	$.ajax({
	  url:'${vix}/business/itemDownLoadAction!goSaveOrUpdate.action?id='+id+"&pageNo="+pageNo,
	  cache: false,
	  success: function(html){
	  	$("#mainContent").html(html);
	  }
});
};

//载入分页列表数据
pager("start","${vix}/business/itemDownLoadAction!goSingleList.action?name="+name,'inventoryCurrentStock');
//排序 
function orderBy(orderField){
	var orderBy = $("#inventoryCurrentStockOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/business/itemDownLoadAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy,'inventoryCurrentStock');
}

function currentPager(tag){
	pager(tag,"${vix}/business/itemDownLoadAction!goSingleList.action?name="+name,'inventoryCurrentStock');
}

/* 搜索 */
function searchForContent(tag){
	loadSearchContent();
	loadGoodsCode();
	loadGoodsName();
	if(tag==0){
     	pager("start","${vix}/business/itemDownLoadAction!goSingleList.action?searchContent="+searchContent,'inventoryCurrentStock');
	}else {
	    pager("start","${vix}/business/itemDownLoadAction!goSingleList.action?goodsCode="+goodsCode+"&goodsName="+goodsName,'inventoryCurrentStock');
	}
}
function resetForContent(tag){
	if(tag == 0){
		$("#nameS").val("");
	}
	else{
		$("#goodsCode").val("");
		$("#goodsName").val("");
	}
}
function saveOrUpdateUpLoadItem(id){
	$.ajax({
		  url:'${vix}/business/itemDownLoadAction!goUpLoadItem.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 650,
					height : 200,
					title:"商品上传",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#warehouseform').validationEngine('validate')){
							$.post('${vix}/business/itemDownLoadAction!saveOrUpdateWarehouse.action',
									 {
									  'invWarehouse.id':$("#invWarehouseid").val()
									},
									function(result){
										showMessage(result);
										setTimeout("", 1000);
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

function pophtml(id){
	$.ajax({
		  url:'${vix}/business/itemDownLoadAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 900,
					height : 450,
					title:"商品信息",
					html:html,
					callback : function(action){
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};
function goSearch() {
	$.ajax({
	url : '${vix}/business/itemDownLoadAction!goSearch.action',
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
				pager("start","${vix}/business/itemDownLoadAction!goSingleList.action?goodsCode="+$('#goodsCode').val()+"&goodsName="+$('#goodsName').val(),'inventoryCurrentStock');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};
function deleteById(id) {
	$.ajax({
	url : '${vix}/business/itemDownLoadAction!deleteById.action?id=' + id,
	cache : false,
	success : function(html) {
		asyncbox.success(html, "提示信息", function(action) {
			pager("start", "${vix}/business/itemDownLoadAction!goSingleList.action?1=1", 'inventoryCurrentStock');
		});
	}
});
}
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
		url : '${vix}/business/itemDownLoadAction!deleteByIds.action?ids=' + ids,
		cache : false,
		success : function(html) {
			asyncbox.success(html, "提示信息", function(action) {
				pager("start", "${vix}/business/itemDownLoadAction!goSingleList.action?1=1", 'inventoryCurrentStock');
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
				<li><a href="#"><img src="${vix}/common/img/mdm/item.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">网店管理</a></li>
				<li><a href="#">商品管理 </a></li>
				<li><a href="#">商品列表 </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<ul>
			<li><a href="#"><span>商品下载</span> </a>
				<ul>
					<li><a href="#" onclick="downloadItem(0);">全部 </a></li>
					<s:if test="channelDistributorList.size > 0">
						<s:iterator value="channelDistributorList" var="entity" status="s">
							<li><a href="javascript:;" onclick="downloadItem('${entity.id}');">${entity.name}</a></li>
						</s:iterator>
					</s:if>
				</ul></li>
		</ul>
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
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
		</ul>
		<div>
			<label>内容:<input type="text" class="int" id="nameS" placeholder="编码"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent(0);" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" /></label><label> <input
				type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<s:iterator value="goodsList" var="c">
				<li><a href="#" onclick="pophtml(${c.id});"><span style="display: none;">${c.chineseCharacter}</span>${c.goodName}</a></li>
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
					async: {
						enable: true,
						url:"${vix}/business/itemDownLoadAction!findStoreTypeTreeToJson.action",
						autoParam:["id","treeType"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdType").val(treeNode.treeType);
					pager("start","${vix}/business/itemDownLoadAction!goSingleList.action?id="+treeNode.id+"&treeType="+treeNode.treeType,"inventoryCurrentStock");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
				</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" />
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