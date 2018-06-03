<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var buyerNick = "";
function loadBuyerNick(){
	buyerNick = $('#buyerNick').val();
	buyerNick=Url.encode(buyerNick);
	buyerNick=Url.encode(buyerNick);
}
var payTypeCn = "";
function loadPayTypeCn(){
	payTypeCn = $('#payTypeCn').val();
	payTypeCn=Url.encode(payTypeCn);
	payTypeCn=Url.encode(payTypeCn);
}
var orderCode = "";
function loadOrderCode(){
	orderCode = $('#orderCode').val();
	orderCode=Url.encode(orderCode);
	orderCode=Url.encode(orderCode);
}
var receiverMobile = "";
function loadReceiverMobile(){
	receiverMobile = $('#receiverMobile').val();
	receiverMobile=Url.encode(receiverMobile);
	receiverMobile=Url.encode(receiverMobile);
}
function saveOrUpdate(id,pageNo){
	$.ajax({
	  url:'${vix}/business/orderProcessAction!goSaveOrUpdate.action?id='+id+"&pageNo="+pageNo,
	  cache: false,
	  success: function(html){
	  	$("#mainContent").html(html);
	  }
	});
};

function goPrintPickingList(){
	$.ajax({
	  url:'${vix}/business/printPickingListAction!goList.action',
	  cache: false,
	  success: function(html){
	  	$("#mainContent").html(html);
	  }
});
};
/* 发货单 */
function goPrintDeliveryList(){
	$.ajax({
	  url:'${vix}/business/printDeliveryListAction!goList.action',
	  cache: false,
	  success: function(html){
	  	$("#mainContent").html(html);
	  }
});
};
/* 快递单 */
function goPrintExpressList(){
	$.ajax({
	  url:'${vix}/business/printExpressListAction!goList.action',
	  cache: false,
	  success: function(html){
	  	$("#mainContent").html(html);
	  }
});
};
function downloadOrder(channelId){
	loadContent('${vix}/business/orderDownLoadLogAction!goList.action');
	$.ajax({
	  url:'${vix}/business/orderProcessAction!goDownloadOrder.action?channelId='+channelId,
	  cache: false,
	  success: function(){
	    setTimeout("", 1000);
	    loadContent('${vix}/business/orderProcessAction!goList.action');
	  }
});
};

//载入分页列表数据
pager("start","${vix}/business/orderProcessAction!goSingleList.action?1=1",'salesOrder');
//排序 
function orderBy(orderField){
	var orderBy = $("#salesOrderOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/business/orderProcessAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy,'salesOrder');
}

function currentPager(tag){
	pager(tag,"${vix}/business/orderProcessAction!goSingleList.action?1=1",'salesOrder');
}

function pophtml(id){
	$.ajax({
		  url:'${vix}/business/orderProcessAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 950,
					height : 600,
					title:"订单信息",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if ($('#order').validationEngine('validate')) {
								$.post('${vix}/business/orderProcessAction!updateOrderDetail.action', {
								'order.id' : $("#orderId").val(),
								'order.sellerMemo' : $("#sellerMemo").val()
								}, function(result) {
									showMessage(result);
									setTimeout("", 1000);
									loadContent('${vix}/business/orderProcessAction!goList.action');
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
var businussOrderId = "";
function chkChange (chk , id){
	if (chk.checked){
		businussOrderId = businussOrderId + "," + id;
	}
}
$("#order").validationEngine();
function goOrderPicking(){
	$.ajax({
	  url:'${vix}/business/orderProcessAction!goOrderPicking.action?orderIds='+businussOrderId,
	  cache: false,
	  success: function(html){
	  	$("#mainContent").html(html);
	  }
});
};

function categoryTab(num,befor,id,e,entity){
	var el=e.target?e.target.parentNode:e.srcElement.parentNode;
	var pa=el.parentNode.getElementsByTagName("li");
	for(var i=0;i<pa.length;i++){
		pa[i].className="";
	}
	el.className="current";
	for(i=1;i<=num;i++){
		try{
			if(i==befor){
				document.getElementById(id+i).style.display="block";
			}else{
				document.getElementById(id+i).style.display="none";
			}
		}catch(e){ }
	}
	if(entity != undefined){
		categoryPager('start',entity);
	}
}
function categoryPager(tag,entity){
	if(entity == 'salesOrder'){
		pager(tag,"${vix}/business/orderProcessAction!goSingleList.action?1=1",entity);
	}
	if(entity == 'salesOrder1'){
		pager(tag,"${vix}/business/orderProcessAction!goOutStandingOrdersList.action?1=1",entity);
	}
	if(entity == 'salesOrder2'){
		pager(tag,"${vix}/business/orderProcessAction!goSalesOrder2List.action?1=1",entity);
	}
	if(entity == 'salesOrder3'){
		pager(tag,"${vix}/business/orderProcessAction!goSalesOrder3List.action?1=1",entity);
	}
	if(entity == 'salesOrder4'){
		pager(tag,"${vix}/business/orderProcessAction!goSalesOrder4List.action?1=1",entity);
	}
}

function searchOrder(){
	$.ajax({
		  url:'${vix}/business/orderProcessAction!goSearchOrder.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 650,
					height : 315,
					title:"高级查询",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							pager("start","${vix}/business/orderProcessAction!searchOrder.action?1=1",'salesOrder');
						}
						},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};
/* 分仓库 */
function saveOrUpdateTwoSorting(id){
	$.ajax({
		  url:'${vix}/business/orderProcessAction!goTwoSorting.action?id='+id+"&orderId="+businussOrderId,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 550,
					height : 350,
					title:"选择仓库",
					html:html,
					callback : function(action, returnValue) {
						if (action == 'ok') {
							if (returnValue != undefined) {
								var result = returnValue.split(",");
								$.post('${vix}/business/orderProcessAction!saveOrUpdateTwoSorting.action',
										 {
										  'orderId':$("#orderId").val(),
										  'warehouseId':result[0]
										},
										function(result){
											showMessage(result);
											setTimeout("", 1000);
											loadContent('${vix}/business/orderProcessAction!goList.action');
										}
									 );
							} else {
								asyncbox.success("请选择仓库信息!", "提示信息");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};
/* 分物流 */
function saveOrUpdateLogistics(id){
	$.ajax({
		  url:'${vix}/business/orderProcessAction!goThreeSorting.action?id='+id+"&orderId="+businussOrderId,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 650,
					height : 200,
					title:"选择物流",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#logisticsForm').validationEngine('validate')){
							$.post('${vix}/business/orderProcessAction!saveOrUpdateLogistics.action',
									 {
							     	 'orderId':$("#orderId").val(),
								     'logisticsId':$("#logisticsId").val()
									},
									function(result){
										showMessage(result);
										setTimeout("", 1000);
										loadContent('${vix}/business/orderProcessAction!goList.action');
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
$(function(){
	if($('.fixed_header').length > 0 && $('#c_head').length > 0){
		var fixed_t = $('.content #c_head').offset().top -40;
		$(window).scroll(function(){
			if($(window).scrollTop() > fixed_t){
				$('.fixed_header').show();
			}else{
				$('.fixed_header').hide();
				}
		})
	}
});

function searchForContent(){
	loadBuyerNick();
	loadPayTypeCn();
	loadOrderCode();
	loadReceiverMobile();
    pager("start","${vix}/business/orderProcessAction!goSearchList.action?orderCode="+orderCode+"&buyerNick="+buyerNick+"&receiverMobile="+$("#receiverMobile").val()+"&payTypeCn="+payTypeCn,'salesOrder');
    resetForContent();
}

function resetForContent(){
	$("#orderCode").val("");
	$("#buyerNick").val("");
	$("#receiverMobile").val("");
	$("#payTypeCn").val("");
}


//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/business/orderProcessAction!goSearch.action',
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
				 pager("start","${vix}/business/orderProcessAction!goSearchList.action?outerId="+$('#outerId').val()+"&createTime="+$('#createTime').val()+"&channelName="+$('#channelName').val()+"&receiverName="+$('#receiverName').val()+"&receiverMobile="+$('#receiverMobile').val()+"&num="+$('#num').val()+"&payTypeCn="+$('#payTypeCn').val()+"&receiverAddress="+$('#receiverAddress').val(),'salesOrder');
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
				<li><a href="#"><img src="${vix}/common/img/sale/saleOrder.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">网店管理</a></li>
				<li><a href="#">订单管理 </a></li>
				<li><a href="#">订单处理 </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<ul>
			<li><a href="#"><span>订单同步</span> </a>
				<ul>
					<li><a href="#" onclick="downloadOrder(0);">全部 </a></li>
					<s:if test="channelDistributorList.size > 0">
						<s:iterator value="channelDistributorList" var="entity" status="s">
							<li><a href="javascript:;" onclick="downloadOrder('${entity.id}');">${entity.name}</a></li>
						</s:iterator>
					</s:if>
				</ul></li>
		</ul>
		<a href="#" onclick="saveOrUpdateTwoSorting(0);"><span>分仓库</span> </a> <a href="#" onclick="saveOrUpdateLogistics(0);"><span>分物流 </span> </a>
	</div>
</div>
<div class="content">
	<div id="c_head" class=" drop">
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
			<label>订单编码:<input type="text" class="int" id="orderCode"></label> <label><input id="simpleSearch" type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();"> <input id="simpleReset" type="button" value="<s:text name='cmn_reset'/>" class="btn" onclick="resetForContent()"> </label> <label>
				<input type="button" value="高级搜索" class="btn" onclick="goSearch();" />
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
					pager("start","${vix}/business/orderProcessAction!goSingleList.action?parentId="+treeNode.id+"&treeType="+treeNode.treeType,"salesOrder");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
				</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectTreeType" name="selectTreeType" value="${treeType}" />
		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><a onclick="categoryTab(5,1,'a',event,'salesOrder')"><img src="img/pending.png" alt="" />未处理</a></li>
					<li><a onclick="categoryTab(5,2,'a',event,'salesOrder1')"><img src="img/hasbeendivided.png" alt="" />已分仓</a></li>
					<!-- <li><a onclick="categoryTab(5,3,'a',event,'salesOrder3')"><img src="img/tobeconfirmed.png" alt="" />待确认</a></li>
					<li><a onclick="categoryTab(5,4,'a',event,'salesOrder4')"><img src="img/finish.png" alt="" />完成</a></li> -->
				</ul>
			</div>
			<div class="right_content" id="a1">
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
			<div class="right_content" id="a2" style="display: none;">
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
					<strong><s:text name="cmn_selected" />:<span id="selectBrandCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','salesOrder1');"></a></span> <span><a class="previous" onclick="categoryPager('previous','salesOrder1');"></a></span> <em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name='cmn_to' /> <b class="salesOrder1TotalCount">${pager.totalCount}</b>)
						</em> <span><a class="next" onclick="categoryPager('next','salesOrder1');"></a></span> <span><a class="end" onclick="categoryPager('end','salesOrder1');"></a></span>
					</div>
				</div>
				<div id="salesOrder1" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name="cmn_choose" /></a>
							<ul>
								<li><a href="#"><s:text name="cmn_delete" /></a></li>
								<li><a href="#"><s:text name="cmn_mail" /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectBrandCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','salesOrder1');"></a></span> <span><a class="previous" onclick="categoryPager('previous','salesOrder1');"></a></span> <em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name="cmn_to" /> <b class="salesOrder1TotalCount">${pager.totalCount}</b>)
						</em> <span><a class="next" onclick="categoryPager('next','salesOrder1');"></a></span> <span><a class="end" onclick="categoryPager('end','salesOrder1');"></a></span>
					</div>
				</div>
			</div>
			<div class="right_content" id="a3" style="display: none;">
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
					<strong><s:text name="cmn_selected" />:<span id="selectBrandCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','salesOrder3');"></a></span> <span><a class="previous" onclick="categoryPager('previous','salesOrder3');"></a></span> <em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name='cmn_to' /> <b class="salesOrder3TotalCount">${pager.totalCount}</b>)
						</em> <span><a class="next" onclick="categoryPager('next','salesOrder3');"></a></span> <span><a class="end" onclick="categoryPager('end','salesOrder3');"></a></span>
					</div>
				</div>
				<div id="salesOrder3" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name="cmn_choose" /></a>
							<ul>
								<li><a href="#"><s:text name="cmn_delete" /></a></li>
								<li><a href="#"><s:text name="cmn_mail" /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectBrandCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','salesOrder3');"></a></span> <span><a class="previous" onclick="categoryPager('previous','salesOrder3');"></a></span> <em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name="cmn_to" /> <b class="salesOrder3TotalCount">${pager.totalCount}</b>)
						</em> <span><a class="next" onclick="categoryPager('next','salesOrder3');"></a></span> <span><a class="end" onclick="categoryPager('end','salesOrder3');"></a></span>
					</div>
				</div>
			</div>
			<div class="right_content" id="a4" style="display: none;">
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
					<strong><s:text name="cmn_selected" />:<span id="selectBrandCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','salesOrder4');"></a></span> <span><a class="previous" onclick="categoryPager('previous','salesOrder4');"></a></span> <em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name='cmn_to' /> <b class="salesOrder4TotalCount">${pager.totalCount}</b>)
						</em> <span><a class="next" onclick="categoryPager('next','salesOrder4');"></a></span> <span><a class="end" onclick="categoryPager('end','salesOrder4');"></a></span>
					</div>
				</div>
				<div id="salesOrder4" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name="cmn_choose" /></a>
							<ul>
								<li><a href="#"><s:text name="cmn_delete" /></a></li>
								<li><a href="#"><s:text name="cmn_mail" /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectBrandCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','salesOrder4');"></a></span> <span><a class="previous" onclick="categoryPager('previous','salesOrder4');"></a></span> <em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name="cmn_to" /> <b class="salesOrder4TotalCount">${pager.totalCount}</b>)
						</em> <span><a class="next" onclick="categoryPager('next','salesOrder4');"></a></span> <span><a class="end" onclick="categoryPager('end','salesOrder4');"></a></span>
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