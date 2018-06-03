<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var quoteSubject = "";
function loadQuoteSubject(){
	quoteSubject = $('#quoteSubjectS').val();
	quoteSubject=Url.encode(quoteSubject);
	quoteSubject=Url.encode(quoteSubject);
}
function saveOrUpdate(id,tag){
	if(tag == 'new' || tag == 'update'){
		$.ajax({
			  url:'${vix}/sales/quotes/projectQuotationSchemeAction!goSaveOrUpdate.action?id='+id,
			  cache: false,
			  success: function(html){
				$("#mainContent").html(html);
			  }
		});
	}else{
		$.ajax({
			url:'${vix}/sales/quotes/projectQuotationTemplateAction!goChooseProjectQuotationTemplate.action',
			cache: false,
			success: function(html){
				asyncbox.open({
					 	modal:true,
						width : 960,
						height : 500,
						title:"选择项目式报价单模板",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if (returnValue != '') {
				                    $.ajax({
				                    	url : '${vix}/sales/quotes/projectQuotationSchemeAction!convertProjectQuotationTemplateToProjectQuotationScheme.action?projectQuotationTemplateIds='+returnValue,
				                    	cache : false,
				                    	success : function(html) {
				                    		 $("#mainContent").html(html);
				                    	}
				                	});
			                    }
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
};
$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});

function deleteById(id){
	$.ajax({
		  url:'${vix}/sales/quotes/projectQuotationSchemeAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"提示信息",function(action){
				pager("start","${vix}/sales/quotes/projectQuotationSchemeAction!goListContent.action?quoteSubject="+quoteSubject,'projectQuotationScheme');
			});
		  }
	});
}

function searchForContent(){
	loadQuoteSubject();
	pager("start","${vix}/sales/quotes/projectQuotationSchemeAction!goListContent.action?quoteSubject="+quoteSubject,'projectQuotationScheme');
}
 
loadQuoteSubject();
//载入分页列表数据
pager("start","${vix}/sales/quotes/projectQuotationSchemeAction!goListContent.action?quoteSubject="+quoteSubject,'projectQuotationScheme');
//排序 
function orderBy(orderField){
	loadQuoteSubject();
	var orderBy = $("#projectQuotationSchemeOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/sales/quotes/projectQuotationSchemeAction!goListContent.action?orderField="+orderField+"&orderBy="+orderBy+"&quoteSubject="+quoteSubject,'projectQuotationScheme');
}

bindSearch();
function currentPager(tag){
	loadQuoteSubject();
	pager(tag,"${vix}/sales/quotes/projectQuotationSchemeAction!goListContent.action?quoteSubject="+quoteSubject,'projectQuotationScheme');
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
								pager('start',"${vix}/sales/quotes/projectQuotationSchemeAction!goListContent.action?name="+name+'&companyCode='+result[0],'projectQuotationScheme');
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
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/sale/saleQuotes.png" alt="" />供应链</a></li>
				<li><a href="#">销售管理</a></li>
				<li><a href="#">销售报价</a></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/quotes/projectQuotationSchemeAction!goList.action');">项目式报价</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<ul>
			<li><a href="#"><span>单据来源</span></a>
				<ul style="display: none; width: 135px;">
					<li><a href="###" onclick="saveOrUpdate(0,'new');">新增项目式报价单</a></li>
					<li><a href="###" onclick="saveOrUpdate(0,'chooseProjectQuotationSchemeTemplate');">来自项目式报价单模板 </a></li>
				</ul></li>
		</ul>
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
			<li><span id="companyName" style="height: 30px; width: 220px; padding-top: 8px;"></span><input type="hidden" id="companyCode" /></li>
			<li><a href="###" onclick="chooseCompany()"><img src="${vix}/common/img/icon_10.png">选择公司</a></li>
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
			<label>主题<input type="text" class="int" id="quoteSubjectS"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" /></label> <strong id="lb_search_advanced"><s:text name="cmn_advance_search" /></strong>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="projectQuotationSchemeInfo"></b> <s:text name='cmn_to' /> <b class="projectQuotationSchemeTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="projectQuotationScheme" class="table"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="projectQuotationSchemeInfo"></b> <s:text name='cmn_to' /> <b class="projectQuotationSchemeTotalCount"></b>)
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