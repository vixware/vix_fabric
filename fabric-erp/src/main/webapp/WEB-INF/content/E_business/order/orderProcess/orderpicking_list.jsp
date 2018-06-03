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

var orderId = "";
function chkChange (chk , id){
	if (chk.checked){
		orderId = orderId + "," + id;
	}
}
function saveOrUpdateOneSorting(id){
	$.ajax({
		  url:'${vix}/business/orderProcessAction!goOneSorting.action?id='+id+"&orderId="+orderId,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 650,
					height : 200,
					title:"分拣条件",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#distributionCenterForm').validationEngine('validate')){
							$.post('${vix}/business/orderProcessAction!saveOrUpdateOneSorting.action',
									 {
									  'orderId':orderId,
									  'distributionCenterId':$("#distributionCenterId").val()
									},
									function(result){
										showMessage(result);
										setTimeout("", 1000);
										loadContent('${vix}/business/orderProcessAction!goOrderPicking.action');
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
function saveOrUpdateTwoSorting(id){
	$.ajax({
		  url:'${vix}/business/orderProcessAction!goTwoSorting.action?id='+id+"&orderId="+orderId,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 650,
					height : 200,
					title:"分拣条件",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#warehouseform').validationEngine('validate')){
							$.post('${vix}/business/orderProcessAction!saveOrUpdateTwoSorting.action',
									 {
									  'orderId':$("#orderId").val(),
									  'warehouseId':$("#warehouseId").val()
									},
									function(result){
										showMessage(result);
										setTimeout("", 1000);
										loadContent('${vix}/business/orderProcessAction!goOrderPicking.action');
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
function saveOrUpdateThreeSorting(id){
	$.ajax({
		  url:'${vix}/business/orderProcessAction!goThreeSorting.action?id='+id+"&orderId="+orderId,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 650,
					height : 200,
					title:"分拣条件",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#logisticsForm').validationEngine('validate')){
							$.post('${vix}/business/orderProcessAction!saveOrUpdateThreeSorting.action',
									 {
							     	 'orderId':$("#orderId").val(),
								     'logisticsId':$("#logisticsId").val()
									},
									function(result){
										showMessage(result);
										setTimeout("", 1000);
										loadContent('${vix}/business/orderProcessAction!goOrderPicking.action');
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
function deleteByIds(){
	var ids = '';
	$("[name='chkId']").each(function(){  
		if($(this).attr('checked')){
			ids+=$(this).val()+",";  
		}
	});
	asyncbox.success(ids,"选中的id");
}

function deleteById(id){
	$.ajax({
		  url:'${vix}/business/orderProcessAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"提示信息",function(action){
				pager("start","${vix}/business/orderProcessAction!goSingleList.action?name="+name,'salesOrder');
			});
		  }
	});
}
loadName();
//载入分页列表数据
pager("start","${vix}/business/orderProcessAction!goOrderPickingList.action?orderIds="+$("#orderIds").val(),'salesOrder');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#brandOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/business/orderProcessAction!goOrderPickingList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name,'salesOrder');
}

function currentPager(tag){
	loadName();
	pager(tag,"${vix}/business/orderProcessAction!goOrderPickingList.action?name="+name,'salesOrder');
}

</script>
<input type="hidden" id="orderIds" name="orderIds" value="${orderIds}" />
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/inv_takestock.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">网店管理 </a></li>
				<li><a href="#">订单管理</a></li>
				<li><a href="#">订单分仓</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdateTwoSorting(0);"><span>按仓库分拣 </span> </a> <a href="#" onclick="saveOrUpdateThreeSorting(0);"><span>按物流分拣 </span> </a>
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
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_unapproved_plan" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_approval_by_plan" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_approval_in" /> </a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_recently_used" /> </a>
				<ul>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div>
			<label><s:text name="cmn_content" /><input type="text" class="int" id="nameS"> </label> <label> <input id="simpleSearch" type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent(0);"> <input id="simpleReset" type="button" value="<s:text name='cmn_reset'/>" class="btn"
				onclick="resetForContent(0)">
			</label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
		<div class="search_advanced">
			<label><s:text name="wim_inventory_list_number1" /><input type="text" class="int" name="" id="stocktakingcode1"> </label> <label><s:text name="wim_counting_outbound" /><input type="text" class="int" name="" id="warehousecode1"> </label> <label><s:text name="wim_count_people" /><input type="text" class="int" name=""
				id="personcode1"> </label> <label> <input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent(1);"> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(1)">
			</label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="orderList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});">${c.outerId}</a></li>
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
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span> </strong>
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
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span> </strong>
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