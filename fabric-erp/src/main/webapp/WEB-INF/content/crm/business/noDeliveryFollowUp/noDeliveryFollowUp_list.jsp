<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//载入分页列表数据
	pager("start", "${vix}/crm/business/noDeliveryFollowUpAction!goSingleList.action?1=1", 'salesOrder');

	function currentPager(tag) {
		pager(tag, "${vix}/crm/business/noDeliveryFollowUpAction!goSingleList.action?1=1", 'salesOrder');
	}

	function saveOrUpdateExpediting(salesOrderId) {
		$.ajax({
		url : '${vix}/crm/business/noDeliveryFollowUpAction!goSaveOrUpdate.action?salesOrderId=' + salesOrderId,
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 750,
			height : 355,
			title : "关怀",
			html : html,
			callback : function(action) {
				if (action == 'ok') {
					if ($('#messageLogForm').validationEngine('validate')) {
						$.post('${vix}/crm/business/noDeliveryFollowUpAction!saveOrUpdateMessage.action', {
							'messageLog.id' : $("#messageLogId").val(),
							'messageLog.messageContent' : $("#messageContent").val(),
							'messageLog.mobilePhone' : $("#mobilePhone").val(),
							'salesOrderId' : $("#salesOrderId").val()
						}, function(result) {
							showMessage(result);
							setTimeout("", 1000);
							loadContent('${vix}/crm/business/noDeliveryFollowUpAction!goList.action');
						});
					} else {
						return false;
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	};
	function saveOrUpdate(salesOrderId) {
		$.ajax({
		url : '${vix}/crm/business/noDeliveryFollowUpAction!goSaveOrUpdateFollowing.action?salesOrderId=' + salesOrderId,
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 750,
			height : 400,
			title : "跟进",
			html : html,
			callback : function(action) {
				if (action == 'ok') {
					if ($('#myAffairsForm').validationEngine('validate')) {
						$.post('${vix}/crm/business/noDeliveryFollowUpAction!saveOrUpdateTransactions.action', {
							'myAffairs.id' : $("#myAffairsId").val(),
							'myAffairs.name' : $("#name").val(),
							'myAffairs.affairsType' : $("#affairsType").val(),
							'myAffairs.importance' : $("#importance").val(),
							'myAffairs.creator' : $("#creator").val(),
							'myAffairs.processor' : $("#processor").val(),
							'myAffairs.memo' : $("#memo").val(),
							'salesOrderId' : $("#salesOrderId").val()
						}, function(result) {
							showMessage(result);
							setTimeout("", 1000);
							loadContent('${vix}/crm/business/noDeliveryFollowUpAction!goList.action');
						});
					} else {
						return false;
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	};
	$("#myAffairsForm").validationEngine();
	$("#messageLogForm").validationEngine();
	bindSearch();
</script>
<style>
.goods_info {
	padding: 10px;
	width: 350px;
}

.goods_info dt {
	float: left;
	padding-right: 10px;
}

.goods_info dd {
	overflow: hidden;
	line-height: 18px;
	text-align: left;
}
</style>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/remind.png" alt="" />供应链</a></li>
				<li><a href="#">会员交互管理</a></li>
				<li><a href="#">客服中心</a></li>
				<li><a href="#">事务跟进</a></li>
				<li><a href="#">未发货跟进</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop"></div>
</div>
<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img alt="" src="${vix}/common/img/icon_11.png"></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_mode" /> </a>
				<ul>
					<li><a href="#" onclick="saleOrderStatus('0')"><img alt="" src="img/uncommitted.png"> <s:text name="cmn_uncommitted" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('1')"><img alt="" src="img/unaudited.png"> <s:text name="cmn_unapproved" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('2')"><img alt="" src="img/verifying.png"> <s:text name="cmn_approval_in" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('3')"><img alt="" src="img/approved.png"> <s:text name="cmn_approval" /> </a></li>
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
			<label><s:text name="cmn_content" />:<input type="text" class="int" id="nameS"> </label> <label><input id="simpleSearch" type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent(0);"> <input id="simpleReset" type="button" value="<s:text name='cmn_reset'/>" class="btn"
				onclick="resetForContent(0)"> </label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="itemList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});"><span style="display: none;">${c.name}</span>${c.name}</a></li>
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
					$("#selectTreeType").val(treeNode.treeType);
					pager("start","${vix}/crm/business/changePostAndPriceAction!goSingleList.action?parentId="+treeNode.id+"&treeType="+treeNode.treeType,"salesOrder");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
				</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
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
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="salesOrderInfo"></b> <s:text name='cmn_to' /> <b class="salesOrderTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="salesOrder" class="table"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="salesOrderInfo"></b> <s:text name='cmn_to' /> <b class="salesOrderTotalCount"></b>)
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