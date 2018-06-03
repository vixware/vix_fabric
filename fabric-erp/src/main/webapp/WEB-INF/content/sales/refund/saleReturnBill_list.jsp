<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var rbTitle = "";
function loadQuoteSubject(){
	rbTitle = $('#rbTitleS').val();
	rbTitle=Url.encode(rbTitle);
	rbTitle=Url.encode(rbTitle);
}
function chooseCondition(){
	$.ajax({
		url:'${vix}/sales/refund/saleReturnBillAction!goChooseCondition.action',
		cache: false,
		success: function(html){
			asyncbox.open({
			 	modal:true,
				width : 780,
				height : 240,
				title:"选择返款条件",
				html:html,
				callback : function(action,returnValue){
					if(action == 'ok'){
						if($('#ccfrbForm').validationEngine('validate')){
							$.ajax({
								url:'${vix}/sales/refund/saleReturnBillAction!calculateSaleReturnBill.action?customerAccountId='+$("#customerAccountId").val()+"&itemId="+$("#itemId").val()+"&startTime="+$("#startTime").val()+"&endTime="+$("#endTime").val(),
								cache: false,
								success: function(html){
									showMessage(html);
									setTimeout("",1000);
									loadContent('${vix}/sales/refund/saleReturnBillAction!goList.action');
								}
							});
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
function saveOrUpdate(id){
	$.ajax({
		url:'${vix}/sales/refund/saleReturnBillAction!goSaveOrUpdate.action?id='+id,
		cache: false,
		success: function(html){
			asyncbox.open({
			 	modal:true,
				width : 740,
				height : 300,
				title:"返款单管理",
				html:html,
				callback : function(action,returnValue){
					if(action == 'ok'){
						if($('#saleReturnBillForm').validationEngine('validate')){
							$.post('${vix}/sales/refund/saleReturnBillAction!saveOrUpdate.action',
								{'saleReturnBill.id':$("#id").val(),
								  'saleReturnBill.rbCode':$("#rbCode").val(),
								  'saleReturnBill.rbTitle':$("#rbTitle").val(),
								  'saleReturnBill.customerAccount.id':$("#customerAccountId").val(),
								  'saleReturnBill.item.id':$("#itemId").val(),
								  'saleReturnBill.returnAmount':$("#returnAmount").val(),
								  'saleReturnBill.currencyType.id':$("#currencyTypeId").val(),
								  'saleReturnBill.rbDate':$("#rbDate").val()
								},
								function(result){
									showMessage(result);
									setTimeout("",1000);
									pager("start","${vix}/sales/refund/saleReturnBillAction!goListContent.action?rbTitle=",'saleReturnBill');
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
		  url:'${vix}/sales/refund/saleReturnBillAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"提示信息",function(action){
				pager("start","${vix}/sales/refund/saleReturnBillAction!goListContent.action?rbTitle="+rbTitle,'saleReturnBill');
			});
		  }
	});
}

function searchForContent(){
	loadQuoteSubject();
	pager("start","${vix}/sales/refund/saleReturnBillAction!goListContent.action?rbTitle="+rbTitle,'saleReturnBill');
}
 
loadQuoteSubject();
//载入分页列表数据
pager("start","${vix}/sales/refund/saleReturnBillAction!goListContent.action?rbTitle="+rbTitle,'saleReturnBill');
//排序 
function orderBy(orderField){
	loadQuoteSubject();
	var orderBy = $("#saleReturnBillOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/sales/refund/saleReturnBillAction!goListContent.action?orderField="+orderField+"&orderBy="+orderBy+"&rbTitle="+rbTitle,'saleReturnBill');
}

bindSearch();
function currentPager(tag){
	loadQuoteSubject();
	pager(tag,"${vix}/sales/refund/saleReturnBillAction!goListContent.action?rbTitle="+rbTitle,'saleReturnBill');
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
				<li><a href="###">返款</a></li>
				<li><a href="###" onclick="loadContent('${vix}/sales/refund/saleReturnBillAction!goList.action');">返款单</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="chooseCondition();"><span><s:text name='cmn_add' /></span></a> <a href="#" onclick="deleteByIds();"><span><s:text name='cmn_delete' /></span></a>
		</p>
	</div>
</div>
<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img alt="" src="img/icon_11.png"></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b> </b>
				</strong>
				<p></p>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="saleReturnBillInfo"></b> <s:text name='cmn_to' /> <b class="saleReturnBillTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="saleReturnBill" class="table"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="saleReturnBillInfo"></b> <s:text name='cmn_to' /> <b class="saleReturnBillTotalCount"></b>)
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