<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var rbTitle = "";
function loadQuoteSubject(){
	rbTitle = $('#rbTitleS').val();
	rbTitle=Url.encode(rbTitle);
	rbTitle=Url.encode(rbTitle);
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
function saveOrUpdate(id){
	$.ajax({
		url:'${vix}/sales/credit/customerCreditInfoAction!goSaveOrUpdate.action?id='+id,
		cache: false,
		success: function(html){
			asyncbox.open({
			 	modal:true,
				width : 840,
				height : 400,
				title:"授信管理",
				html:html,
				callback : function(action,returnValue){
					if(action == 'ok'){
						if($('#customerCreditInfoForm').validationEngine('validate')){
							$.post('${vix}/sales/credit/customerCreditInfoAction!saveOrUpdate.action',
								{'customerCreditInfo.id':$("#id").val(),
								  'customerCreditInfo.creditAmount':$("#creditAmount").val(),
								  'customerCreditInfo.creditManagerCode':$("#creditManagerCode").val(),
								  'customerCreditInfo.creditManagerName':$("#creditManagerName").val(),
								  'customerCreditInfo.customerAccount.id':$("#customerAccountId").val(),
								  'customerCreditInfo.usedCreditAmount':$("#usedCreditAmount").val(),
								  'customerCreditInfo.isCreditControl':$("#isCreditControl").val(),
								  'customerCreditInfo.riskType':$("#riskType").val(),
								  'customerCreditInfo.isFreezeCredit':$("#isFreezeCredit").val(),
								  'customerCreditInfo.maxOfBill':$("#maxOfBill").val(),
								  'customerCreditInfo.maxTimeOfArrears':$("#maxTimeOfArrears").val(),
								  'customerCreditInfo.arrearsLevel':$("#arrearsLevel").val(),
								  'customerCreditInfo.arrearsMaxLevel':$("#arrearsMaxLevel").val(),
								  'customerCreditInfo.dso':$("#dso").val(),
								  'customerCreditInfo.creditChecker.id':$("#creditCheckerId").val(),
								  'customerCreditInfo.creditCheckTime':$("#creditCheckTime").val(),
								  'customerCreditInfo.creditLevel':$("#creditLevel").val(),
								  'customerCreditInfo.expirationDate':$("#expirationDate").val(),
								  'customerCreditInfo.currentYearBillAmount':$("#currentYearBillAmount").val(),
								  'customerCreditInfo.beforeYearBillAmount':$("#beforeYearBillAmount").val()
								},
								function(result){
									showMessage(result);
									setTimeout("",1000);
									loadContent('${vix}/sales/credit/customerCreditInfoAction!goList.action');
								}
							);
						}else{
							return false;
						}
					}
				},
				btnsbar : $.btn.OKCANCEL
			});
		}
	});
};
$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});
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
		  url:'${vix}/sales/credit/customerCreditInfoAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"提示信息",function(action){
				pager("start","${vix}/sales/credit/customerCreditInfoAction!goListContent.action?rbTitle="+rbTitle,'customerCreditInfo');
			});
		  }
	});
}

function searchForContent(){
	loadQuoteSubject();
	pager("start","${vix}/sales/credit/customerCreditInfoAction!goListContent.action?rbTitle="+rbTitle,'customerCreditInfo');
}
 
loadQuoteSubject();
//载入分页列表数据
pager("start","${vix}/sales/credit/customerCreditInfoAction!goListContent.action?rbTitle="+rbTitle,'customerCreditInfo');
//排序 
function orderBy(orderField){
	loadQuoteSubject();
	var orderBy = $("#customerCreditInfoOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/sales/credit/customerCreditInfoAction!goListContent.action?orderField="+orderField+"&orderBy="+orderBy+"&rbTitle="+rbTitle,'customerCreditInfo');
}

bindSearch();
function currentPager(tag){
	loadQuoteSubject();
	pager(tag,"${vix}/sales/credit/customerCreditInfoAction!goListContent.action?rbTitle="+rbTitle,'customerCreditInfo');
}

$(".drop>ul>li").bind('mouseover',function(){
	$(this).children('ul').delay(400).slideDown('fast');
}).bind('mouseleave',function(){
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast');
});
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
loadMenuContent('${vix}/sales/salesAction!goMenuContent.action');
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="###"><img src="${vix}/common/img/sale/saleQuotes.png" alt="" />供应链</a></li>
				<li><a href="###">销售管理</a></li>
				<li><a href="###">信用管理</a></li>
				<li><a href="###" onclick="loadContent('${vix}/sales/credit/customerCreditInfoAction!goList.action');">授信管理</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0);"><span><s:text name='cmn_add' /></span></a> <a href="#" onclick="deleteByIds();"><span><s:text name='cmn_delete' /></span></a>
		</p>
	</div>
</div>
<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img alt="" src="img/icon_11.png"></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/icon_10.png" alt="" />
				<s:text name="cmn_index" /></a></li>
			<li><a href="#"><img alt="" src="img/icon_10.png">
				<s:text name="cmn_category" /></a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png">
				<s:text name="cmn_category" /></a>
				<ul>
					<li><a href="#"><img alt="" src="img/icon_10.png">
						<s:text name="information" /></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png">
						<s:text name="information" /></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png">
						<s:text name="information" /></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png">
						<s:text name="information" /></a></li>
				</ul></li>
		</ul>
		<div>
			<label>主题<input type="text" class="int" id="rbTitleS"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" /></label> <strong id="lb_search_advanced"><s:text name="cmn_advance_search" /></strong>
		</div>
		<div class="search_advanced">
			<label><s:text name="cmn_name" /><input type="text" class="int" name=""></label> <label><s:text name="cmn_name" /><input type="text" class="int" name=""></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name=""><input type="button" value="<s:text name='cmn_reset'/>" class="btn"
				name=""></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<li><a href="#">a11</a></li>
			<li><a href="#">v11</a></li>
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="employeeList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());">${c.name}</a></li>
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
						<li class="tp"><a href="#"><s:text name='cmn_choose' /></a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /></a></li>
								<li><a href="#"><s:text name='cmn_email' /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="customerCreditInfoInfo"></b> <s:text name='cmn_to' /> <b class="customerCreditInfoTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="customerCreditInfo" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /></a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /></a></li>
								<li><a href="#"><s:text name='cmn_email' /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="customerCreditInfoInfo"></b> <s:text name='cmn_to' /> <b class="customerCreditInfoTotalCount"></b>)
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