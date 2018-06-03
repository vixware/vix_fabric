<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var subject = "";
var subjectAS = "";
function loadSubject(){
	subject = $('#subjectS').val();
	subject=Url.encode(subject);
	subject=Url.encode(subject);
	subjectAS = $('#subjectAS').val();
	subjectAS =Url.encode(subjectAS);
	subjectAS =Url.encode(subjectAS);
}
function saveOrUpdate(id){
	$.ajax({
		  url:'${vix}/mdm/item/salePriceConditionAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
};
$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});
function deleteById(id){
	$.ajax({
		  url:'${vix}/mdm/item/salePriceConditionAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='vix_message'/>",function(action){
				pager("current","${vix}/mdm/item/salePriceConditionAction!goListContent.action?subject="+subject+'&priceType='+$("#ptForSearch").val(),'priceCondition');
			});
		  }
	});
}
function searchForRightContent(tag){
	loadSubject();
	var params = '';
	if(tag == 'simple'){
		params = "subject="+subject+'&companyCode='+$("#companyCode").val();
	}else{
		params = "subject="+subjectAS+'&customerAccountId='+$("#customerAccountIdAS").val()+'&itemId='+$("#itemIdAS").val()+'&companyCode='+$("#companyCode").val();
	}
	pager("start","${vix}/mdm/item/salePriceConditionAction!goListContent.action?"+params,'priceCondition');
}
loadSubject();
//载入分页列表数据
pager("start","${vix}/mdm/item/salePriceConditionAction!goListContent.action?subject="+subject,'priceCondition');
function loadRoot(){
	$('#subjectS').val("");
	$('#selectId').val("");
	pager("start",'${vix}/mdm/item/salePriceConditionAction!goListContent.action?subject='+'&companyCode='+$("#companyCode").val()+'&priceType='+$("#ptForSearch").val(),'priceCondition');
}
//排序 
function orderBy(orderField){
	loadSubject();
	var orderBy = $("#priceConditionOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("current","${vix}/mdm/item/salePriceConditionAction!goListContent.action?orderField="+orderField+"&orderBy="+orderBy+"&subject="+subject+"&id="+$("#selectId").val()+'&companyCode='+$("#companyCode").val()+'&priceType='+$("#ptForSearch").val(),'priceCondition');
}

bindSwitch();
bindSearch();
function currentPager(tag){
	loadSubject();
	pager(tag,"${vix}/mdm/item/salePriceConditionAction!goListContent.action?subject="+subject+'&companyCode='+$("#companyCode").val()+'&priceType='+$("#ptForSearch").val(),'priceCondition');
}

function currentPagerClick(input,event){
	loadSubject();
	pagerClick(input,event,"${vix}/mdm/item/salePriceConditionAction!goListContent.action?subject="+subject+'&companyCode='+$("#companyCode").val()+'&priceType='+$("#ptForSearch").val(),'priceCondition');
}
//选择公司
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
								loadSubject();
								pager('start',"${vix}/mdm/item/salePriceConditionAction!goListContent.action?subject="+subject+'&companyCode='+result[0],'priceCondition');
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
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
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="${vix}/common/img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="${vix}/common/img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/mdm/priceCondition.png" alt="" />系统管理</a></li>
				<li><a href="#">基础数据管理</a></li>
				<li><a href="#">${vv:varView('vix_mdm_item')}管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/mdm/item/salePriceConditionAction!goList.action');">${vv:varView('vix_mdm_item')}销售定价</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0,$('#selectId').val());"><span><s:text name="cmn_add" /></span></a>
		</p>
	</div>
</div>

<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="${vix}/common/img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><span id="companyName" style="height: 30px; width: 220px; padding-top: 8px;"></span><input type="hidden" id="companyCode" /></li>
			<li><a href="###" onclick="chooseCompany()"><img src="${vix}/common/img/icon_10.png">选择公司</a></li>
		</ul>
		<div>
			<label>主题<input type="text" class="int" id="subjectS"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForRightContent('simple');" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" /></label> <strong id="lb_search_advanced"><s:text
					name="cmn_advance_search" /></strong>
		</div>
		<div class="search_advanced">
			<input type="hidden" id="customerAccountIdAS" /> <input type="hidden" id="itemIdAS" /> <label>主题<input type="text" class="int" id="subjectAS"></label> <label>客户<input type="text" class="int" id="customerAccountNameAS"></label> <label>${vv:varView('vix_mdm_item')}<input type="text" class="int" id="itemNameAS"></label> <label><input
				type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForRightContent('advance');"><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name=""></label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="right">
			<div>
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /></a>
							<ul>
								<li><a href="#"><s:text name='cmn_add' /></a></li>
								<li><a href="#"><s:text name='cmn_update' /></a></li>
								<li><a href="#"><s:text name='cmn_delete' /></a></li>
							</ul></li>
					</ul>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="priceConditionInfo"></b> <s:text name='cmn_to' /> <b class="priceConditionTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="priceCondition" style="overflow-x: auto; overflow-y: hidden; width: 100%;"></div>
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
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="priceConditionInfo"></b> <s:text name='cmn_to' /> <b class="priceConditionTotalCount"></b>)
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