<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	var name = "";
	function loadName() {
		name = $('#nameS').val();
		name = Url.encode(name);
		name = Url.encode(name);
	}
	function standingbookPager(tag) {
		pager(tag, "${vix}/common/vixLogAction!goSingleList.action?name=" + name, 'latestOperateEntity');
	}
	function searchForRightContent() {
		loadName();
		pager("start", "${vix}/common/vixLogAction!goSingleList.action?name=" + name, 'latestOperateEntity');
	}
	loadName();
	//载入分页列表数据
	pager("start", "${vix}/common/vixLogAction!goSingleList.action?name=" + name, 'latestOperateEntity');
	function loadRoot() {
		$('#name').val("");
		$('#selectId').val("");
		pager("start", "${vix}/common/vixLogAction!goSingleList.action?name=", 'latestOperateEntity');
	}
	//排序 
	function orderBy(orderField) {
		loadName();
		var orderBy = $("#categoryOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/common/vixLogAction!goSingleList.action?orderField=" + orderField + "&orderBy=" + orderBy + "&name=" + name + "&parentId=" + $("#selectId").val(), 'latestOperateEntity');
	}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/inv_standingbook.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">连锁经营管理 </a></li>
				<li><a href="#">门店管理 </a></li>
				<li><a href="#">操作日志 </a></li>
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
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_recently_used" /> </a>
				<ul>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div>
			<label><s:text name="cmn_content" />: <input type="text" class="int" id="nameS"> </label> <label> <input id="simpleSearch" type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();"> <input id="simpleReset" type="button" value="<s:text name='cmn_reset'/>" class="btn" name=""
				onclick="resetForContent();">
			</label><label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="latestOperateEntityList" var="c">
				<li><a href="#">${c.operate}</a></li>
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
				url : "${vix}/drp/distributionSystemRelationShipAction!findOrgAndUnitTreeToJson.action",
				autoParam : [ "id", "treeType" ]
				},
				callback : {
					onClick : onClick
				}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdType").val(treeNode.treeType);
					$("#selectIdTreeId").val(treeNode.tId);
					pager("start", "${vix}/drp/distributionSystemRelationShipAction!goSingleList.action?id=" + treeNode.id + "&treeType=" + treeNode.treeType, "channelDistributor");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
			</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
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
						<span><a class="start" onclick="standingbookPager('start');"></a> </span> <span><a class="previous" onclick="standingbookPager('previous');"></a> </span> <em>(<b class="latestOperateEntityInfo"></b> <s:text name='cmn_to' /> <b class="latestOperateEntityTotalCount"></b>)
						</em> <span><a class="next" onclick="standingbookPager('next');"></a> </span> <span><a class="end" onclick="standingbookPager('end');"></a> </span>
					</div>
				</div>
				<div id="latestOperateEntity" class="table"></div>
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
						<span><a class="start" onclick="standingbookPager('start');"></a> </span> <span><a class="previous" onclick="standingbookPager('previous');"></a> </span> <em>(<b class="latestOperateEntityInfo"></b> <s:text name='cmn_to' /> <b class="latestOperateEntityTotalCount"></b>)
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