<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
var name = "";
var returnOrderCode = "";
var customerName = "";
function loadName(){
	name = $('#nameS').val(); 
	name=Url.encode(name);
	name=Url.encode(name);
}
function loadReturnOrderCode(){
	returnOrderCode = $('#returnOrderCode').val(); 
	returnOrderCode=Url.encode(returnOrderCode);
	returnOrderCode=Url.encode(returnOrderCode);
}
function loadCustomerName(){
	customerName = $('#customerName').val(); 
	customerName=Url.encode(customerName);
	customerName=Url.encode(customerName);
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
function saveOrUpdate(id,pageNo){
	$.ajax({
		  url:'${vix}/sales/delivery/saleReturnFormAction!goSaveOrUpdate.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
};

function deleteById(id){
	$.ajax({
		  url:'${vix}/sales/delivery/saleReturnFormAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='cmn_message'/>",function(action){
				pager("start","${vix}/sales/delivery/saleReturnFormAction!goReturnList.action?name="+name,'saleReturnForm');
			});
		  }
	});
}

function searchForRightContent(){
	loadName();
	pager("start","${vix}/sales/delivery/saleReturnFormAction!goReturnList.action?name="+name,'saleReturnForm');
}

loadName();
//载入分页列表数据
pager("start","${vix}/sales/delivery/saleReturnFormAction!goReturnList.action?name="+name,'saleReturnForm');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/sales/delivery/saleReturnFormAction!goReturnList.action?name=",'saleReturnForm');
}
/*搜索*/
function searchForContent(i){
	loadName();
	loadReturnOrderCode();
	loadCustomerName();
	if(i == 0){
		pager("start","${vix}/sales/delivery/saleReturnFormAction!goSearchList.action?i="+i+"&searchContent="+name,'saleReturnForm');
	}
	else{
		pager("start","${vix}/sales/delivery/saleReturnFormAction!goSearchList.action?i="+i+"&returnOrderCode="+returnOrderCode+"&customerName="+customerName,'saleReturnForm');
	}
}

//高级搜索 
function goSearch() {
	$.ajax({
	url : '${vix}/sales/delivery/saleReturnFormAction!goSearch.action',
	cache : false,
	success : function(html) {
		asyncbox.open({
		modal : true,
		width : 650,
		height : 300,
		title : "查询条件",
		html : html,
		callback : function(action) {
			loadConditions();
			if (action == 'ok') {
				pager("start", "${vix}/sales/delivery/saleReturnFormAction!goSearchList.action?returnOrderCode=" + returnOrderCode + "&customerName=" + customerName, 'saleReturnForm');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};

/*重置搜索*/
function resetForContent(i){
	if(i == 0){
		$("#nameS").val("");
	}
	else{
		$("#customerName").val("");
		$("#returnOrderCode").val("");
	}
}

//展示
function showSaleReturnForm(id){
	$.ajax({
		  url:'${vix}/sales/delivery/saleReturnFormAction!showSaleReturnForm.action?id=' + id,
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
};


/*改变搜索按钮的显示*/
function changeDisplay(){
	var divText = $("#lb_search_advanced").text();
	if(divText == "高级搜索"){
		$("#nameS").attr({disabled:'true'});
		$("#simpleSerach").hide();
		$("#simpleReset").hide();
	}
	else{
		$("#nameS").removeAttr("disabled");
		$("#simpleSerach").show();
		$("#simpleReset").show();
	}
}
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#saleReturnFormOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/sales/delivery/saleReturnFormAction!goReturnList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'saleReturnForm');
}

function saleReturnFormPager(tag,entity){
	loadName();
	if(entity == 'saleReturnForm'){
		pager(tag,"${vix}/sales/delivery/saleReturnFormAction!goReturnList.action?name="+name+'&parentId='+$('#selectId').val(),entity);
	}
}

bindSearch();
bindSwitch();
function saleReturnFormTab(num,befor,id,e,entity){
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
		saleReturnFormPager('start',entity);
	}
}
$(".drop>ul>li").bind('mouseover',function(){
	$(this).children('ul').delay(400).slideDown('fast');
}).bind('mouseleave',function(){
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast');
});
//面包屑
if($('.sub_menu').length)
{
	$("#breadCrumb").jBreadCrumb();
}
//状态
function purStatus(status){
	pager("start","${vix}/sales/delivery/saleReturnFormAction!goSingleList.action?status="+status,'saleReturnForm');
}
//最近使用
function purRecent(rencentDate){
	pager("start","${vix}/sales/delivery/saleReturnFormAction!goSingleList.action?updateTime="+rencentDate,'saleReturnForm');
}

function chooseSalesOrder(){
	$.ajax({
		  url:'${vix}/sales/order/salesOrderAction!goChooseSalesOrder.action?chooseType=multi',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 960,
					height : 500,
					title:"选择订单",
					html:html,
					callback : function(action,returnValue){
						alert(returnValue);
						if(action == 'ok'){
							if (returnValue != '') {
			                    $.ajax({
			                    	url : '${vix}/sales/delivery/saleReturnFormAction!convertSalesOrderToSaleReturnForm.action?salesOrderIds='+returnValue,
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
								pager('start',"${vix}/sales/delivery/saleReturnFormAction!goSingleList.action?name="+name+'&companyCode='+result[0],'saleReturnForm');
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
loadMenuContent('${vix}/sales/salesAction!goMenuContent.action');
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/sale/saleReturn.png" alt="" />
					<s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#"><s:text name="sa_salesmanage" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/delivery/saleReturnFormAction!goList.action');">销售退货单</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<ul>
			<li><a href="#"><span>单据来源</span></a>
				<ul style="display: none;">
					<li><a href="###" onclick="saveOrUpdate(0);">新增退货单</a></li>
					<li><a href="###" onclick="chooseSalesOrder()">来自销售订单 </a></li>
				</ul></li>
		</ul>
	</div>
</div>

<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><span id="companyName" style="height: 30px; width: 220px; padding-top: 8px;"></span><input type="hidden" id="companyCode" /></li>
			<li><a href="###" onclick="chooseCompany()"><img src="${vix}/common/img/icon_10.png">选择公司</a></li>
			<li><a href="#" id="numBtn"><img src="img/icon_10.png" alt="" />
				<s:text name="cmn_index" /></a></li>
			<!-- 菜单栏->状态 -->
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png">
				<s:text name="cmn_mode" /></a>
				<ul>
					<li><a href="#" onclick="salesStatus('S1')"><img alt="" src="${vix}/common/img/icon_10.png">待确认</a></li>
					<li><a href="#" onclick="salesStatus('S2')"><img alt="" src="${vix}/common/img/icon_10.png">审批中</a></li>
					<li><a href="#" onclick="salesStatus('S3')"><img alt="" src="${vix}/common/img/icon_10.png">已发货</a></li>
					<li><a href="#" onclick="salesStatus('S4')"><img alt="" src="${vix}/common/img/icon_10.png">已完成</a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png">
				<s:text name="cmn_recently_used" /></a>
				<ul>
					<li><a href="#" onclick="salesRecent('T1')"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_three_days" /></a></li>
					<li><a href="#" onclick="salesRecent('T2')"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_seven_days" /></a></li>
					<li><a href="#" onclick="salesRecent('T3')"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_month" /></a></li>
					<li><a href="#" onclick="salesRecent('T4')"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_three_months" /></a></li>
				</ul></li>
		</ul>
		<!-- 页面左上角按钮部分 -->
		<div>
			<label>搜索内容:<input id="nameS" name="" type="text" class="int" /></label> <label> <input id="simpleSearch" name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent(0);" /> <input id="simpleReset" name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent(0);" />
			</label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
		<div class="search_advanced">
			<label>退货单号:<input id="returnOrderCode" name="" type="text" class="int" /></label> <label>客户名称:<input id="customerName" name="" type="text" class="int" /></label> <label> <input name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent(1);" /> <input name="" type="button" class="btn"
				value="<s:text name='cmn_reset'/>" onclick="resetForContent(1);" />
			</label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="saleReturnFormList" var="c">
				<li><a href="###" onclick="saveOrUpdate('${c.id}');"><span style="display: none;">${c.chineseFirstLetter}</span>${c.returnOrderCode}</a></li>
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
						<li class="tp"><a href="#"><s:text name='cmn_operate' /></a>
							<ul>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="saleReturnFormPager('start','saleReturnForm');"></a></span> <span><a class="previous" onclick="saleReturnFormPager('previous','saleReturnForm');"></a></span> <em>(<b class="saleReturnFormInfo"></b> <s:text name='cmn_to' /> <b class="saleReturnFormTotalCount"></b>)
						</em> <span><a class="next" onclick="saleReturnFormPager('next','saleReturnForm');"></a></span> <span><a class="end" onclick="saleReturnFormPager('end','saleReturnForm');"></a></span>
					</div>
				</div>
				<div id="saleReturnForm" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /></a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /></a></li>
								<li><a href="#"><s:text name='cmn_mail' /></a></li>
								<li><a href="#"><s:text name="cmn_merger" /></a></li>
								<li><a href="#"><s:text name="cmn_export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="saleReturnFormPager('start','saleReturnForm');"></a></span> <span><a class="previous" onclick="saleReturnFormPager('previous','saleReturnForm');"></a></span> <em>(<b class="saleReturnFormInfo"></b> <s:text name='cmn_to' /> <b class="saleReturnFormTotalCount"></b>)
						</em> <span><a class="next" onclick="saleReturnFormPager('next','saleReturnForm');"></a></span> <span><a class="end" onclick="saleReturnFormPager('end','saleReturnForm');"></a></span>
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