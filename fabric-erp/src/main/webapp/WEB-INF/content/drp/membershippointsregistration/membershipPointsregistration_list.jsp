<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
loadMenuContent('${vix}/drp/drpMainAction!goMenuContent.action');
function saveOrUpdate(id,pageNo){
	$.ajax({
		  url:'${vix}/drp/membershipPointsregistrationAction!goSaveOrUpdate.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
};
pager("start", "${vix}/drp/membershipPointsregistrationAction!goSingleList.action", 'zoVipCardLog');
//批量删除
function deleteEntitys() {
	var ids = '';
	$("[name='chkId']").each(function() {
		if ($(this).attr('checked')) {
			ids += $(this).val() + ",";
		}
	});
	$.ajax({
		url : '${vix}/drp/membershipPointsregistrationAction!deleteByIds.action?ids=' + ids,
		cache : false,
		success : function(html) {
			asyncbox.success(html, "提示信息", function(action) {
				pager("start", "${vix}/drp/membershipPointsregistrationAction!goSingleList.action?1=1", 'zoVipCardLog');
			});
		}
	});
}
//排序 
function orderBy(orderField){
	var orderBy = $("#zoVipCardLogOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/drp/membershipPointsregistrationAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy,'zoVipCardLog');
}

function currentPager(tag){
	pager(tag,"${vix}/drp/membershipPointsregistrationAction!goSingleList.action?1=1",'zoVipCardLog');
}
/** 状态 */
function listbystatus(status) {
	pager("start", "${vix}/drp/membershipPointsregistrationAction!goSingleList.action?status=" + status, 'zoVipCardLog');
}
/* 最近使用 */
function leastRecentlyUsed(days) {
	pager("start", "${vix}/drp/membershipPointsregistrationAction!goSingleList.action?days=" + days, 'zoVipCardLog');
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_distribution_management.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#"><s:text name="cbm_membership_management" /> </a></li>
				<li><a href="#">会员积分信息</a></li>
				<li><a href="#">会员积分记录</a></li>
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
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
			<li class="fly"><a href="#"><img alt="" src="img/leastRecentlyUsed.png"> <s:text name="cmn_recently_used" /> </a>
				<ul>
					<li><a href="#" onclick="leastRecentlyUsed('THREEDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('SEVENDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('ONEMONTH');"><img alt="" src="img/time_go.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('THREEMONTHS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div search_page_id="tab_home" class="grid_search search_simple">
			<label><input type="text" name="memberShipCard.vipcardid" class="int more" placeholder="卡号"></label> <input type="button" class="btn search" value="<s:text name='cmn_search'/>" /><input type="button" class="btn reset" value="<s:text name='cmn_reset'/>" />
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="zoVipCardLogList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});">${c.memberShipCard.vipcardid}</a></li>
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
						url:"${vix}/drp/distributionSystemRelationShipAction!findOrgAndUnitTreeToJson.action",
						autoParam:["id","treeType"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdType").val(treeNode.treeType);
					pager("start","${vix}/drp/membershipPointsregistrationAction!goSingleList.action?id="+treeNode.id+"&treeType="+treeNode.treeType,"zoVipCardLog");
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
						<li class="tp"><a href="#"><s:text name='cmn_operate' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_add' /> </a></li>
								<li><a href="#"><s:text name='cmn_update' /> </a></li>
								<li><a href="#" onclick="deleteEntitys();"><s:text name='cmn_delete' /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount1">0</span> </strong>
					<div>
						<span><a class="start" onclick="categoryPager('start');"></a> </span> <span><a class="previous" onclick="categoryPager('previous');"></a> </span> <em>(<b class="zoVipCardLogInfo"></b> <s:text name='cmn_to' /> <b class="zoVipCardLogTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next');"></a> </span> <span><a class="end" onclick="categoryPager('end');"></a> </span>
					</div>
				</div>
				<div id="zoVipCardLog" class="table"></div>
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
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="categoryPager('start');"></a> </span> <span><a class="previous" onclick="categoryPager('previous');"></a> </span> <em>(<b class="zoVipCardLogInfo"></b> <s:text name='cmn_to' /> <b class="zoVipCardLogTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next');"></a> </span> <span><a class="end" onclick="categoryPager('end');"></a> </span>
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