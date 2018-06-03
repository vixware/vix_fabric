<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var title = "";
function loadTitle(){
	title = $('#titleS').val();
	title=Url.encode(title);
	title=Url.encode(title);
}
$('#numBtn').click(function(){
	$('#numBtn').parent("li").toggleClass("current");
	$('#number').stop().animate({height: 'toggle', opacity: 'toggle'},500,function(){
		$('#number').css('overflow','visible');
	});
	return false;
});
$(function(){
	if($('#numBox').length) $('#numBox').listmenu({menuWidth: '100%', cols:{ count:6, gutter:0 }});
});
$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});
function saveOrUpdate(id){
	$.ajax({
		  url:'${vix}/sales/order/salesOrderAction!goSaveOrUpdate.action?id=' + id + "&pageNo="+$("#orderPageNoHidden").val(),
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
};
function showOrder(id){
	$.ajax({
		  url:'${vix}/sales/order/salesOrderAction!showOrder.action?id=' + id,
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
};
function chooseProjectQuotes(){
	$.ajax({
		url:'${vix}/sales/quotes/projectQuotationSchemeAction!goChooseProjectQuotationScheme.action',
		cache: false,
		success: function(html){
			asyncbox.open({
				 	modal:true,
					width : 960,
					height : 500,
					title:"选择项目式报价单",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if (returnValue != '') {
			                    $.ajax({
			                    	url : '${vix}/sales/order/salesOrderAction!convertProjectQuotationSchemeToSalesOrder.action?projectQuotationSchemeIds='+returnValue,
			                    	cache : false,
			                    	success : function(html) {
			                    		 $("#mainContent").html(html);
			                    	}
			                	});
		                    } else {
			                    asyncbox.success("请选择报价单!", "<s:text name='vix_message'/>");
			                    return false;
		                    }
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

function chooseQuotes(){
	$.ajax({
		url:'${vix}/sales/quotes/salesQuotationAction!goChooseSalesQuotation.action',
		cache: false,
		success: function(html){
			asyncbox.open({
				 	modal:true,
					width : 960,
					height : 500,
					title:"选择报价单",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if (returnValue != '') {
			                    $.ajax({
			                    	url : '${vix}/sales/order/salesOrderAction!convertSalesQuotationToSalesOrder.action?salesQuotationIds='+returnValue,
			                    	cache : false,
			                    	success : function(html) {
			                    		 $("#mainContent").html(html);
			                    	}
			                	});
		                    } else {
			                    asyncbox.success("请选择报价单!", "<s:text name='vix_message'/>");
			                    return false;
		                    }
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

function deleteById(id){
	loadTitle();	
	$.ajax({
		  url:'${vix}/sales/order/salesOrderAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"提示信息",function(action){
				pager("start","${vix}/sales/order/salesOrderAction!goSingleList.action?title="+title+'&companyCode='+$("#companyCode").val(),'order');
			});
		  }
	});
}

function showOrder(id){
	$.ajax({
		  url:'${vix}/sales/order/salesOrderAction!showSalesOrder.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
}

function searchForContent(){
	loadTitle();
	pager("start","${vix}/sales/order/salesOrderAction!goSingleList.action?title="+title+'&companyCode='+$("#companyCode").val(),'order');
}
 
loadTitle();
//载入分页列表数据
pager("start","${vix}/sales/order/salesOrderAction!goSingleList.action?title="+title+"&pageNo=${pageNo}&companyCode="+$("#companyCode").val(),'order');
//排序 
function orderBy(orderField){
	loadTitle();
	var orderBy = $("#orderOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/sales/order/salesOrderAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&title="+title+'&companyCode='+$("#companyCode").val(),'order');
}

bindSearch();
var orderStatus = '';
function currentPager(tag){
	loadCode();
	pager(tag,"${vix}/sales/order/salesOrderAction!goSingleList.action?title="+title+'&orderStatus='+orderStatus+'&companyCode='+$("#companyCode").val(),'order');
}

function deleteChoose(){
	var ids = '';
	$("[name='chkId']").each(function(){  
		if($(this).attr('checked')){
			ids+=$(this).val()+",";  
		}
	});
	if(ids == ''){
		asyncbox.confirm('请选择需要删除的订单!','提示信息');
		return;
	}
	asyncbox.confirm('确定要删除选中的订单么?','提示信息',function(action){
		if(action == 'ok'){
			$.ajax({
				  url:'${vix}/sales/order/salesOrderAction!deleteByIds.action?ids='+ids,
				  cache: false,
				  success: function(html){
					asyncbox.success(html,"提示信息",function(action){
						pager("current","${vix}/sales/order/salesOrderAction!goSingleList.action?companyCode="+$("#companyCode").val(),'order');
					});
				  }
			});
		}
	});
}
function saleOrderStatus(status){
	orderStatus = status;
	pager("start","${vix}/sales/order/salesOrderAction!goSingleList.action?title="+status,'order');
}
function chooseCompany(){
	$.ajax({
		  url:'${vix}/common/org/organizationAction!goChooseOrganization.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择公司",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
								$("#companyCode").val(result[0]);
								$("#companyName").html(result[1]);
								loadName();
								pager('start',"${vix}/sales/order/salesOrderAction!goSingleList.action?name="+name+'&companyCode='+result[0],'order');
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

//上一条，下一条
function goShowBeforeAndAfter(id, str) {
	$.ajax({
	url : '${vix}/sales/order/salesOrderAction!goShowBeforeAndAfter.action?id=' + id + "&str=" + str,
	cache : false,
	success : function(html) {
		$("#mainContent").html(html);
	}
	});
};

function reset(){
	$("#titleS").val('');
}

//高级搜索 
function goSearch() {
	$.ajax({
	url : '${vix}/sales/order/salesOrderAction!goSearch.action',
	cache : false,
	success : function(html) {
		asyncbox.open({
		modal : true,
		width : 650,
		height : 400,
		title : "查询条件",
		html : html,
		callback : function(action) {
			loadConditions();
			if (action == 'ok') {
				pager("start","${vix}/sales/order/salesOrderAction!goSingleList.action?salePerson=" + salePerson + "&title=" + title + "&customerAccount=" + customerAccount,'order');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};

loadMenuContent('${vix}/sales/salesAction!goMenuContent.action');
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="${vix}/common/img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="${vix}/common/img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/sale/saleOrder.png" />供应链</a></li>
				<li><a href="#">供应链</a></li>
				<li><a href="#">销售管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/order/salesOrderAction!goList.action');">销售订单</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<ul>
			<li><a href="#"><span>单据来源</span></a>
				<ul style="display: none;">
					<li><a href="###" onclick="saveOrUpdate(0);">新增订单</a></li>
					<li><a href="###" onclick="chooseProjectQuotes()">来自项目式报价单 </a></li>
					<li><a href="###" onclick="chooseQuotes()">来自普通报价单</a></li>
				</ul></li>
		</ul>
	</div>
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
			<li><span id="companyName" style="height: 30px; width: 220px; padding-top: 8px;"></span><input type="hidden" id="companyCode" /></li>
			<li><a href="###" onclick="chooseCompany()"><img src="${vix}/common/img/icon_10.png">选择公司</a></li>
			<li><a href="#" id="numBtn"><img src="${vix}/common/img/icon_10.png" alt="" />
				<s:text name="cmn_index" /></a></li>
			<li><a href="#"><img alt="" src="${vix}/common/img/icon_10.png">
				<s:text name="cmn_category" /></a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png">
				<s:text name="订单状态" /></a>
				<ul>
					<li><a href="#" onclick="saleOrderStatus('1')"><img alt="" src="${vix}/common/img/icon_10.png">待确认</a></li>
					<li><a href="#" onclick="saleOrderStatus('q')"><img alt="" src="${vix}/common/img/icon_10.png">审批中</a></li>
					<li><a href="#" onclick="saleOrderStatus('s')"><img alt="" src="${vix}/common/img/icon_10.png">已发货</a></li>
					<li><a href="#" onclick="saleOrderStatus('f')"><img alt="" src="${vix}/common/img/icon_10.png">已完成</a></li>
				</ul></li>
		</ul>
		<div>
			<label>主题<input type="text" class="int" id="titleS"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="reset();" /></label> <label> <input type="button" value="高级搜索" class="btn"
				onclick="goSearch();" /></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="salesOrderList" var="c">
				<li><a href="###" onclick="saveOrUpdate(${c.id});"><span style="display: none;">${c.chineseFirstLetter}</span>${c.title}</a></li>
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
						<li class="tp"><a href="#"><s:text name="cmn_choose" /></a>
							<ul>
								<li><a href="#" onclick="deleteChoose();"><s:text name="cmn_delete" /></a></li>
								<li><a href="#"><s:text name="email" /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="orderInfo"></b> <s:text name="cmn_to" /> <b class="orderTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="order" class="table" style="height: 468px;"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name="cmn_choose" /></a>
							<ul>
								<li><a href="#"><s:text name="cmn_delete" /></a></li>
								<li><a href="#"><s:text name="cmn_email" /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="orderInfo"></b> <s:text name="cmn_to" /> <b class="orderTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
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