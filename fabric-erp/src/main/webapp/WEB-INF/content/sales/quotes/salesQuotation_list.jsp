<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var quoteSubject = "";
function loadQuoteSubject(){
	quoteSubject = $('#quoteSubjectS').val();
	quoteSubject=Url.encode(quoteSubject);
	quoteSubject=Url.encode(quoteSubject);
}
//展示
function showOrder(id){
	$.ajax({
		  url:'${vix}/sales/quotes/salesQuotationAction!showSalesQuotation.action?id=' + id,
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
};

function saveOrUpdate(id,tag){
	if(tag == 'new'){
		$.ajax({
			  url:'${vix}/sales/quotes/salesQuotationAction!goSaveOrUpdate.action?id='+id,
			  cache: false,
			  success: function(html){
				$("#mainContent").html(html);
			  }
		});
	}else{
		$.ajax({
			url:'${vix}/sales/quotes/salesQuotationTemplateAction!goChooseSalesQuotationTemplate.action',
			cache: false,
			success: function(html){
				asyncbox.open({
					 	modal:true,
						width : 960,
						height : 500,
						title:"选择销售报价单模板",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if (returnValue != '') {
				                    $.ajax({
				                    	url : '${vix}/sales/quotes/salesQuotationAction!convertSalesQuotationTemplateToSalesQuotation.action?salesQuotationTemplateIds='+returnValue,
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

function deleteById(id){
	$.ajax({
		  url:'${vix}/sales/quotes/salesQuotationAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"提示信息",function(action){
				pager("start","${vix}/sales/quotes/salesQuotationAction!goListContent.action?quoteSubject="+quoteSubject,'salesQuotation');
			});
		  }
	});
}

function searchForContent(){
	loadQuoteSubject();
	pager("start","${vix}/sales/quotes/salesQuotationAction!goListContent.action?quoteSubject="+quoteSubject,'salesQuotation');
}
 
loadQuoteSubject();
//载入分页列表数据
pager("start","${vix}/sales/quotes/salesQuotationAction!goListContent.action?quoteSubject="+quoteSubject,'salesQuotation');
//排序 
function orderBy(orderField){
	loadQuoteSubject();
	var orderBy = $("#salesQuotationOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/sales/quotes/salesQuotationAction!goListContent.action?orderField="+orderField+"&orderBy="+orderBy+"&quoteSubject="+quoteSubject,'salesQuotation');
}

bindSearch();
function currentPager(tag){
	loadQuoteSubject();
	pager(tag,"${vix}/sales/quotes/salesQuotationAction!goListContent.action?quoteSubject="+quoteSubject,'salesQuotation');
}

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
								pager('start',"${vix}/sales/quotes/salesQuotationAction!goListContent.action?name="+name+'&companyCode='+result[0],'salesQuotation');
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
	url : '${vix}/sales/quotes/salesQuotationAction!goShowBeforeAndAfter.action?id=' + id + "&str=" + str,
	cache : false,
	success : function(html) {
		$("#mainContent").html(html);
	}
	});
};

function reset(){
	$("#quoteSubjectS").val('');
}

//高级搜索 
function goSearch() {
	$.ajax({
	url : '${vix}/sales/quotes/salesQuotationAction!goSearch.action',
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
				pager("start","${vix}/sales/quotes/salesQuotationAction!goListContent.action?quoteSubject=" + quoteSubject + "&startTime=" + startTime + "&endTime=" + endTime + "&customerAccount=" + customerAccount,'salesQuotation');
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
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/sale/saleQuotes.png" alt="" />供应链</a></li>
				<li><a href="#">销售管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/quotes/salesQuotationAction!goList.action');">销售报价</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<ul>
			<li><a href="#"><span>单据来源</span></a>
				<ul style="display: none;">
					<li><a href="###" onclick="saveOrUpdate(0,'new');">新增销售报价单</a></li>
					<li><a href="###" onclick="saveOrUpdate(0,'chooseSalesQuotationTemplate');">来自销售报价单模板 </a></li>
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
			<label>主题<input type="text" class="int" id="quoteSubjectS"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="reset();" /></label> <label> <input type="button" value="高级搜索"
				class="btn" onclick="goSearch();" /></label>
		</div>
		<div class="search_advanced">
			<label><s:text name="cmn_name" /><input type="text" class="int" name=""></label> <label><s:text name="cmn_name" /><input type="text" class="int" name=""></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name=""><input type="button" value="<s:text name='cmn_reset'/>" class="btn"
				name=""></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="sqList" var="c">
				<li><a href="###" onclick="saveOrUpdate(${c.id});"><span style="display: none;">${c.chineseFirstLetter}</span>${c.quoteSubject}</a></li>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="salesQuotationInfo"></b> <s:text name='cmn_to' /> <b class="salesQuotationTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="salesQuotation" class="table"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="salesQuotationInfo"></b> <s:text name='cmn_to' /> <b class="salesQuotationTotalCount"></b>)
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