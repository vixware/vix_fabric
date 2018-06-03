<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
var name = "";
function loadName(){
	name = $('#nameS').val();
	name = Url.encode(name);
	name = Url.encode(name);
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
function saveOrUpdate(id,parentId){
	$.ajax({
		  url:'${vix}/sales/invoice/salesInvoiceAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
};

function deleteById(id){
	$.ajax({
		  url:'${vix}/sales/invoice/salesInvoiceAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='cmn_message'/>",function(action){
				pager("start","${vix}/sales/invoice/salesInvoiceAction!goSalesInvoiceList.action?name="+name,'salesInvoice');
			});
		  }
	});
}

function searchForRightContent(){
	loadName();
	pager("start","${vix}/sales/invoice/salesInvoiceAction!goSalesInvoiceList.action?name="+name,'salesInvoice');
}

loadName();
//载入分页列表数据
pager("start","${vix}/sales/invoice/salesInvoiceAction!goSalesInvoiceList.action?name="+name,'salesInvoice');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/sales/invoice/salesInvoiceAction!goSalesInvoiceList.action?name=",'salesInvoice');
}
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#salesInvoiceOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/sales/invoice/salesInvoiceAction!goSalesInvoiceList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'salesInvoice');
}

function salesInvoicePager(tag,entity){
	loadName();
	if(entity == 'salesInvoice'){
		pager(tag,"${vix}/sales/invoice/salesInvoiceAction!goSalesInvoiceList.action?name="+name+'&parentId='+$('#selectId').val(),entity);
	}
}

bindSearch();
bindSwitch();
function salesInvoiceTab(num,befor,id,e,entity){
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
		salesInvoicePager('start',entity);
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
	pager("start","${vix}/sales/invoice/salesInvoiceAction!goInvoiceList.action?status="+status,'salesInvoice');
}
//最近使用
function purRecent(rencentDate){
	pager("start","${vix}/sales/invoice/salesInvoiceAction!goSingleList.action?updateTime="+rencentDate,'salesInvoice');
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
						if(action == 'ok'){
							if (returnValue != '') {
			                    $.ajax({
			                    	url : '${vix}/sales/invoice/salesInvoiceAction!convertSalesOrderToSalesInvoice.action?salesOrderIds='+returnValue,
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
								pager('start',"${vix}/sales/invoice/salesInvoiceAction!goSingleList.action?name="+name+'&companyCode='+result[0],'salesInvoice');
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
				<li><a href="#"><img src="${vix}/common/img/sale/saleInvoice.png" alt="" />
					<s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#"><s:text name="sa_salesmanage" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/invoice/salesInvoiceAction!goList.action');">销售发票</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<ul>
			<li><a href="#"><span>单据来源</span></a>
				<ul style="display: none;">
					<li><a href="###" onclick="saveOrUpdate(0);">新增发票</a></li>
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
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png">
				<s:text name="cmn_mode" /></a>
				<ul>
					<li><a href="#" onclick="purStatus('S1')"><img alt="" src="${vix}/common/img/icon_10.png">待确认</a></li>
					<li><a href="#" onclick="purStatus('S2')"><img alt="" src="${vix}/common/img/icon_10.png">审批中</a></li>
					<li><a href="#" onclick="purStatus('S3')"><img alt="" src="${vix}/common/img/icon_10.png">已发货</a></li>
					<li><a href="#" onclick="purStatus('S4')"><img alt="" src="${vix}/common/img/icon_10.png">已完成</a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png">
				<s:text name="cmn_recently_used" /></a>
				<ul>
					<li><a href="#" onclick="purRecent('T1')"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_three_days" /></a></li>
					<li><a href="#" onclick="purRecent('T2')"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_seven_days" /></a></li>
					<li><a href="#" onclick="purRecent('T3')"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_month" /></a></li>
					<li><a href="#" onclick="purRecent('T4')"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_three_months" /></a></li>
				</ul></li>
		</ul>
		<!-- 页面左上角按钮部分 -->
		<div>
			<label><s:text name="cmn_content" /><input type="text" class="int" id="nameS"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();"><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name=""></label> <strong id="lb_search_advanced"><s:text
					name="cmn_advance_search" /></strong>
		</div>
		<div class="search_advanced">
			<label>发票号：<input type="text" class="int" name=""></label>
			<!--  	<label><s:text name="my_item"/><input type="checkbox" value="" name=""></label>-->
			<!--    <label><input type="button" value="<s:text name='search'/>" class="btn" name=""><input type="button" value="<s:text name='reset'/>" class="btn" name=""></label> -->
			<label>金额:<input type="text" class="int" name=""></label>
			<!--  	<label><s:text name="my_item"/><input type="checkbox" value="" name=""></label>-->
			<!--  	<label><input type="button" value="<s:text name='search'/>" class="btn" name=""><input type="button" value="<s:text name='reset'/>" class="btn" name=""></label> -->
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="salesInvoiceList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());">${c.name}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable switch" style="height: 500px; width: 7px;">
			<div id="switch_box" class="switch_btn off"></div>
			<div class="left_content current" style="height: 500px;">
				<div style="padding-left: 4px;">
					<img src="${vix}/common/img/file.png" style="padding-right: 5px;" /><a href="#" onclick="loadRoot();"><s:text name='cmn_rootDirectory' /></a>
				</div>
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><a onclick="salesInvoiceTab(3,1,'a',event,'salesInvoice')"><img src="img/mail.png" alt="" />
						<s:text name='sa_salesinvoice' /></a></li>
				</ul>
			</div>
			<div class="right_content" id="a1">
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
						<span><a class="start" onclick="salesInvoicePager('start','salesInvoice');"></a></span> <span><a class="previous" onclick="salesInvoicePager('previous','salesInvoice');"></a></span> <em>(<b class="salesInvoiceInfo"></b> <s:text name='cmn_to' /> <b class="salesInvoiceTotalCount"></b>)
						</em> <span><a class="next" onclick="salesInvoicePager('next','salesInvoice');"></a></span> <span><a class="end" onclick="salesInvoicePager('end','salesInvoice');"></a></span>
					</div>
				</div>
				<div id="salesInvoice" class="table"></div>
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
						<span><a class="start" onclick="salesInvoicePager('start','salesInvoice');"></a></span> <span><a class="previous" onclick="salesInvoicePager('previous','salesInvoice');"></a></span> <em>(<b class="salesInvoiceInfo"></b> <s:text name='cmn_to' /> <b class="salesInvoiceTotalCount"></b>)
						</em> <span><a class="next" onclick="salesInvoicePager('next','salesInvoice');"></a></span> <span><a class="end" onclick="salesInvoicePager('end','salesInvoice');"></a></span>
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