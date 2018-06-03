<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
loadMenuContent('${vix}/inventory/inventoryMainAction!goMenuContent.action');
var searchContent = "";
function loadSearchContent(){
	searchContent = $('#nameS').val();
	searchContent=Url.encode(searchContent);
	searchContent=Url.encode(searchContent);
}
var outerId = "";
function loadOuterId(){
	outerId = $('#outerId').val();
	outerId=Url.encode(outerId);
	outerId=Url.encode(outerId);
}
var shippingNo = "";
function loadShippingNo(){
	shippingNo = $('#shippingNo').val();
	shippingNo=Url.encode(shippingNo);
	shippingNo=Url.encode(shippingNo);
}
var logisticsName = "";
function loadLogisticsName(){
	logisticsName = $('#logisticsName').val();
	logisticsName=Url.encode(logisticsName);
	logisticsName=Url.encode(logisticsName);
}
function searchForContent(tag){
	loadSearchContent();
	loadOuterId();
	loadShippingNo();
	loadLogisticsName();
	if(tag==0){
     	pager("start","${vix}/business/postageRecordsAction!goSingleList.action?searchContent="+searchContent,'postageRecords');
	}else {
	    pager("start","${vix}/business/postageRecordsAction!goSingleList.action?outerId="+outerId+"&shippingNo="+shippingNo+"&logisticsName="+logisticsName,'postageRecords');
	}
}
function resetForContent(tag){
	if(tag == 0){
		$("#nameS").val("");
	}
	else{
		$("#outerId").val("");
		$("#shippingNo").val("");
		$("#logisticsName").val("");
	}
}
//载入分页列表数据
pager("start","${vix}/business/postageRecordsAction!goSingleList.action?1=1",'postageRecords');
/* 点击根节点列表页显示的数据 */
function loadRoot(){
	pager("start","${vix}/business/postageRecordsAction!goSingleList.action?1=1",'postageRecords');
}
//排序 
function orderBy(orderField){
	var orderBy = $("#postageRecordsOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/business/postageRecordsAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy,'postageRecords');
}

function expressFeeRulesPager(tag){
	pager(tag,"${vix}/business/postageRecordsAction!goSingleList.action?1=1",'postageRecords');
}
bindSearch();
//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/business/postageRecordsAction!goSearch.action',
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
				 pager("start","${vix}/business/postageRecordsAction!goSingleList.action?outerId="+$("#outerId").val()+"&shippingNo="+$("#shippingNo").val()+"&logisticsName="+$("#logisticsName").val(),'postageRecords');
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
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/inv_inbound.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">网店管理 </a></li>
				<li><a href="#">物流管理 </a></li>
				<li><a href="#">邮费记录 </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop"></div>
</div>
<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /> </b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> <b>帮助</b>
				</strong>
				<p>邮费记录</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
		</ul>
		<div>
			<label>搜索内容:<input type="text" class="int" id="nameS" placeholder="订单编码"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent(0);" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" /></label> <label> <input
				type="button" value="高级搜索" class="btn" onclick="goSearch();" />
		</div>
		<%-- <div class="search_advanced">
			<label>订单编码:<input type="text" class="int" name="" id="outerId" placeholder="订单编码"></label> <label>快递单编码:<input type="text" class="int" name="" id="shippingNo" placeholder="快递单编码"></label> <label>快递公司:<input type="text" class="int" name="" id="logisticsName" placeholder="快递公司"></label> <label><input type="button"
				value="<s:text name='cmn_search'/>" class="btn" name="" onclick="searchForContent();"><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent();"></label>
		</div> --%>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<s:iterator value="postageRecordsList" var="c">
				<li><a href="#" onclick="goSaveOrUpdate(${c.id});">${c.id}</a></li>
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
					url:"${vix}/business/postageRecordsAction!findLogisticsTreeToJson.action",
					autoParam:["id","treeType"]
				},
				callback: {
					onClick: onClick
				}
			};
			function onClick(event, treeId, treeNode, clickFlag) {
				$("#selectId").val(treeNode.id);
				pager("start","${vix}/business/postageRecordsAction!goSingleList.action?parentId="+treeNode.id,"postageRecords");
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
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="merger" /> </a></li>
								<li><a href="#"><s:text name="export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span> </strong>
					<div>
						<span><a class="start" onclick="expressFeeRulesPager('start');"></a> </span> <span><a class="previous" onclick="expressFeeRulesPager('previous');"></a> </span> <em>(<b class="postageRecordsInfo"></b> <s:text name='cmn_to' /> <b class="postageRecordsTotalCount"></b>)
						</em> <span><a class="next" onclick="expressFeeRulesPager('next');"></a> </span> <span><a class="end" onclick="expressFeeRulesPager('end');"></a> </span>
					</div>
				</div>
				<div id="postageRecords" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="merger" /> </a></li>
								<li><a href="#"><s:text name="export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="expressFeeRulesPager('start');"></a> </span> <span><a class="previous" onclick="expressFeeRulesPager('previous');"></a> </span> <em>(<b class="postageRecordsInfo"></b> <s:text name='cmn_to' /> <b class="postageRecordsTotalCount"></b>)
						</em> <span><a class="next" onclick="expressFeeRulesPager('next');"></a> </span> <span><a class="end" onclick="expressFeeRulesPager('end');"></a> </span>
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