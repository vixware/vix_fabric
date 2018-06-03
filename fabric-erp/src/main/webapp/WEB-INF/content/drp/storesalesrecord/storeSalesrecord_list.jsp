<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	_pad_page_refresh_main_content = true;
	
	$(function(){
		//载入tab数据
		_load_tab_page_content();
		loadMenuContent('${vix}/drp/drpMainAction!goMenuContent.action');
	});
	function saveOrUpdate(id,pageNo){
		$.ajax({
			  url:'${vix}/drp/storeSalesrecordAction!goSaveOrUpdate.action?id='+id+"&pageNo="+pageNo+"&parentId="+$("#selectId").val()+"&treeType="+$("#selectType").val(),
			  cache: false,
			  success: function(html){
			  	$("#mainContent").html(html);
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
		$.ajax({
			url : '${vix}/drp/storeSalesrecordAction!deleteByIds.action?ids=' + ids,
			cache : false,
			success : function(html) {
				asyncbox.success(html, "提示信息", function(action) {
					pager("start", "${vix}/drp/storeSalesrecordAction!goSingleList.action?1=1", 'salesOrder');
				});
			}
		});
	}
	//排序 
	function orderBy(orderField){
		var orderBy = $("#salesOrderOrderBy").val();
		if(orderBy == 'desc'){
			orderBy = "asc";
		}else{
			orderBy = 'desc';
		}
		pager("start","${vix}/drp/storeSalesrecordAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy,'salesOrder');
	}
	
	function currentPager(tag){
		pager(tag,"${vix}/drp/storeSalesrecordAction!goSingleList.action?1=1",'salesOrder');
	}
	/** 状态 */
	function listbystatus(status) {
		pager("start", "${vix}/drp/storeSalesrecordAction!goSingleList.action?status=" + status, 'salesOrder');
	}
	/* 最近使用 */
	function leastRecentlyUsed(days) {
		pager("start", "${vix}/drp/storeSalesrecordAction!goSingleList.action?days=" + days, 'salesOrder');
	}
	
	pager("start","${vix}/drp/storeSalesrecordAction!goSingleList.action?1=1",'salesOrder');
	function goSearch() {
		$.ajax({
		url : '${vix}/dtbcenter/vehiclePlanAction!goSearch.action',
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
					loadcode();
					loadselectname();
					pager("start", "${vix}/dtbcenter/vehiclePlanAction!goSingleList.action?code=" + code + "&name=" + selectname, 'salesOrder');
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
				<s:if test="source =='drp' ">
					<li><a href="#"><img src="${vix}/common/img/drp_shop.png" alt="" /> <s:text name="supplyChain" /> </a></li>
					<li><a href="#" onclick="loadContent('${vix}/drp/drpAction!goList.action','bg_02');"><s:text name="drp_management" /> </a></li>
					<li><a href="#"><s:text name="drp_store_management" /> </a></li>
					<li><a href="#"><s:text name="drp_store_sales_record" /> </a></li>
				</s:if>
				<s:else>
					<li><a href="#"><img src="${vix}/common/img/drp_shop.png" alt="" /> <s:text name="supplyChain" /> </a></li>
					<li><a href="#" onclick="loadContent('${vix}/drp/drpAction!goList.action','bg_02');">连锁经营管理 </a></li>
					<li><a href="#">门店管理 </a></li>
					<li><a href="#">门店销售记录 </a></li>
				</s:else>
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
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_mode" /> </a>
				<ul>
					<li><a href="#" onclick="listbystatus('0')"><img alt="" src="img/uncommitted.png"> 未提交 </a></li>
					<li><a href="#" onclick="listbystatus('1')"><img alt="" src="img/unaudited.png"> 待审批 </a></li>
					<li><a href="#" onclick="listbystatus('2')"><img alt="" src="img/verifying.png"> 审批中 </a></li>
					<li><a href="#" onclick="listbystatus('3')"><img alt="" src="img/approved.png"> 已完成 </a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/leastRecentlyUsed.png"> <s:text name="cmn_recently_used" /> </a>
				<ul>
					<li><a href="#" onclick="leastRecentlyUsed('THREEDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('SEVENDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('ONEMONTH');"><img alt="" src="img/time_go.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('THREEMONTHS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div search_page_id="tab_home" class="grid_search search_simple">
			<label><input type="text" name="code" class="int more" placeholder="编码"></label> <input type="button" class="btn search" value="<s:text name='cmn_search'/>" /><input type="button" class="btn reset" value="<s:text name='cmn_reset'/>" /> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="salesOrderList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});"><span style="display: none;">${c.chineseCharacter}</span>${c.name}</a></li>
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
					$("#selectType").val(treeNode.treeType);
					pager("start","${vix}/drp/storeSalesrecordAction!goSingleList.action?id="+treeNode.id+"&treeType="+treeNode.treeType,"salesOrder");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
				</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectType" name="selectType" value="${treeType}" />
		<!-- left -->
		<div id="right">
			<div id="tab_content" class="tabbable addable_tab">
				<ul id="myTab" class="right_menu nav nav-tabs">
					<li pageId="tab_home" class="current"><a href="#tab_home" page="${vix}/drp/storeSalesrecordAction!goSingleList.action"> <img src="img/mail.png" alt="" /> 门店销售记录
					</a></li>
				</ul>
				<div id="tab_content_page" class="right_content">
					<div id="tab_home" class="table"></div>
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