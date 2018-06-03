<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var code = "";
function loadCode(){
	code = $('#codeS').val();
	code=Url.encode(code);
	code=Url.encode(code);
}
$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});
function saveOrUpdate(id,pageNo){
	$.ajax({
		  url:'${vix}/sales/payment/expensesAction!goSaveOrUpdate.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
};

function deleteById(id){
	$.ajax({
		  url:'${vix}/sales/payment/expensesAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"提示信息",function(action){
				pager("start","${vix}/sales/payment/expensesAction!goListContent.action?code="+code,'expenses');
			});
		  }
	});
}

function showOrder(id){
	$.ajax({
		  url:'${vix}/sales/payment/expensesAction!showOrder.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
}

function searchForContent(){
	loadCode();
	pager("start","${vix}/sales/payment/expensesAction!goListContent.action?code="+code,'expenses');
}
 
loadCode();
//载入分页列表数据
pager("start","${vix}/sales/payment/expensesAction!goListContent.action?code="+code+"&pageNo=${pageNo}",'expenses');
//排序 
function orderBy(orderField){
	loadCode();
	var orderBy = $("#expensesOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/sales/payment/expensesAction!goListContent.action?orderField="+orderField+"&orderBy="+orderBy+"&code="+code,'expenses');
}

bindSearch();
var orderStatus = '';
function currentPager(tag){
	loadCode();
	pager(tag,"${vix}/sales/payment/expensesAction!goListContent.action?code="+code+'&orderStatus='+orderStatus,'expenses');
}
$(".drop>ul>li").bind('mouseover',function(){
	$(this).children('ul').delay(400).slideDown('fast');
}).bind('mouseleave',function(){
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast');
});

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
	asyncbox.confirm('确定要删除选中的费用支出信息么?','提示信息',function(action){
		if(action == 'ok'){
			$.ajax({
				  url:'${vix}/sales/payment/expensesAction!deleteByIds.action?ids='+ids,
				  cache: false,
				  success: function(html){
					asyncbox.success(html,"提示信息",function(action){
						pager("current","${vix}/sales/payment/expensesAction!goListContent.action?code=",'expenses');
					});
				  }
			});
		}
	});
}
function saleOrderStatus(status){
	orderStatus = status;
	pager("start","${vix}/sales/payment/expensesAction!goListContent.action?code="+status,'expenses');
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
								pager('start',"${vix}/sales/payment/expensesAction!goListContent.action?name="+name+'&companyCode='+result[0],'expenses');
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
		<i> <a href="#"><img alt="" src="${vix}/common/img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="${vix}/common/img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/sale/disbursementCost.png" alt="" />供应链</a></li>
				<li><a href="#">销售管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/payment/expensesAction!goList.action');">费用支出</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0);"><span><s:text name="cmn_add" /></span></a>
		</p>
	</div>
</div>
<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img alt="" src="${vix}/common/img/icon_11.png"></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>费用支出</b>
				</strong>
				<p></p>
			</div>
		</div>
		<ul>
			<li><span id="companyName" style="height: 30px; width: 220px; padding-top: 8px;"></span><input type="hidden" id="companyCode" /></li>
			<li><a href="###" onclick="chooseCompany()"><img src="${vix}/common/img/icon_10.png">选择公司</a></li>
		</ul>
		<div>
			<label>编码<input type="text" class="int" id="codeS"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" /></label> <strong id="lb_search_advanced"><s:text name="cmn_advance_search" /></strong>
		</div>
		<div class="search_advanced">
			<label><s:text name="cmn_name" /><input type="text" class="int" name=""></label> <label><s:text name="cmn_name" /><input type="text" class="int" name=""></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name=""><input type="button" value="<s:text name='cmn_reset'/>" class="btn"
				name=""></label>
		</div>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="expensesInfo"></b> <s:text name="cmn_to" /> <b class="expensesTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="expenses" class="table" style="height: 468px;"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="expensesInfo"></b> <s:text name="cmn_to" /> <b class="expensesTotalCount"></b>)
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