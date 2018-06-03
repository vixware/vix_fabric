<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
loadMenuContent('${vix}/purchase/purchaseMainAction!goMenuContent.action');
var name = "";
function loadName(){
	name = $('#nameS').val(); 
	name=Url.encode(name);
	name=Url.encode(name);
}
var title = "";
function loadTitle(){
	title = $('#title').val(); 
	title=Url.encode(title);
	title=Url.encode(title);
}
var supplierName = "";
function loadSupplierName(){
	supplierName = $('#supplierName').val(); 
	supplierName=Url.encode(supplierName);
	supplierName=Url.encode(supplierName);
}
function saveOrUpdate(id){
	$.ajax({
		  url:'${vix}/purchase/purchasePlanFinalizedAction!goShowPurchasePlanPackage.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
}



loadName();
//载入分页列表数据
pager("start","${vix}/purchase/purchasePlanFinalizedAction!goSingleList.action?name="+name,'purchasePlan');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#categoryOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/purchase/purchasePlanFinalizedAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'purchasePlan');
}

function categoryPager(tag,entity){
	loadName();
	if(entity == 'purchasePlan'){
		pager(tag,"${vix}/purchase/purchasePlanFinalizedAction!goSingleList.action?name="+name+'&parentId='+$('#selectId').val(),entity);
	}
}

bindSearch();
/*搜索*/
function searchForContent(i){
	loadName();
	loadTitle();
	loadSupplierName();
	if(i == 0){
		pager("start","${vix}/purchase/purchasePlanFinalizedAction!goSingleList.action?i="+i+"&searchContent="+name,'purchasePlan');
	}
	else{
		pager("start","${vix}/purchase/purchasePlanFinalizedAction!goSingleList.action?i="+i+"&title="+title+"&supplierName="+supplierName,'purchasePlan');
	}
}
/*重置搜索*/
function resetForContent(i){
	if(i == 0){
		$("#nameS").val("");
	}
	else{
		$("#purchasePerson").val("");
		$("#purchaseName").val("");
	}
}
//状态
function purStatus(status){
	pager("start","${vix}/purchase/purchasePlanFinalizedAction!goSingleList.action?status="+status,'purchasePlan');
}
//最近使用
function purRecent(rencentDate){
	pager("start","${vix}/purchase/purchasePlanFinalizedAction!goSingleList.action?updateTime="+rencentDate,'purchasePlan');
}

function showPurchasePlanDetail(id){
	$.ajax({
		  url:'${vix}/purchase/purchasePlanFinalizedAction!goPurchasePlanDetail.action?purchasePlanId='+id,
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
};
function goSearch() {
	$.ajax({
	url : '${vix}/purchase/purchasePlanFinalizedAction!goSearch.action',
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
				pager("start","${vix}/purchase/purchasePlanFinalizedAction!goSingleList.action?title="+$('#title').val()+"&supplierName="+$('#supplierName').val(),'tab_content_page');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};

function goShowPurchasePlan(id) {
	$.ajax({
	url : '${vix}/purchase/purchasePlanFinalizedAction!goShowPurchasePlan.action?id=' + id,
	cache : false,
	success : function(html) {
		$("#mainContent").html(html);
	}
	});
}
var purchasePlanIds = "";
function chkChange (chk , id){
	if (chk.checked){
		purchasePlanIds = purchasePlanIds + "," + id;
	}
}
function goPurchasePlanPackage(id) {
	$.ajax({
	url : '${vix}/purchase/purchasePlanFinalizedAction!goPurchasePlanPackage.action?id=' + id ,
	cache : false,
	success : function(html) {
		asyncbox.open({
		modal : true,
		width : 750,
		height : 555,
		title : "汇总",
		html : html,
		callback : function(action) {
			if (action == 'ok') {
				$.post('${vix}/purchase/purchasePlanFinalizedAction!goPack.action', {
				'purchasePlanPackage.id' : $("#purchasePlanPackageId").val(),
				'purchasePlanPackage.packageType' : $("#packageType").val(),
				'purchasePlanIds' : purchasePlanIds
				}, function(result) {
					showMessage(result);
					setTimeout("", 1000);
					loadContent('${vix}/purchase/purchasePlanFinalizedAction!goList.action');
				});
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};
function exportExcel(id) {
	form = document.getElementById("exportMD");
	form.action = "${vix}/purchase/purchasePlanFinalizedAction!exportPurchasePlanExcel.action?id=" + id;
	form.submit();
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/pur_plan.png" alt="" /> <s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#"><s:text name="pur_purchase_manage" /></a></li>
				<li><a href="#">计划定稿</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<!-- <p>
			<a href="#" onclick="goPurchasePlanPackage(0);"><span>审批</span></a>
		</p> -->
	</div>
</div>
<form action="${vix}/purchase/purchasePlanFinalizedAction!exportPurchasePlanExcel.action" method="post" name="exportMD" id="exportMD" target="form_iframe" style="margin: 0"></form>
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
			<li><a href="#" id="numBtn"><img src="img/icon_10.png" alt="" /> <s:text name="cmn_index" /></a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_mode" /></a>
				<ul>
					<li><a href="#" onclick="purStatus('S1')"><img alt="" src="${vix}/common/img/icon_10.png">待确认</a></li>
					<li><a href="#" onclick="purStatus('S2')"><img alt="" src="${vix}/common/img/icon_10.png">审批中</a></li>
					<li><a href="#" onclick="purStatus('S3')"><img alt="" src="${vix}/common/img/icon_10.png">已发货</a></li>
					<li><a href="#" onclick="purStatus('S4')"><img alt="" src="${vix}/common/img/icon_10.png">已完成</a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_recently_used" /></a>
				<ul>
					<li><a href="#" onclick="purRecent('T1')"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_days" /></a></li>
					<li><a href="#" onclick="purRecent('T2')"><img alt="" src="img/icon_10.png"> <s:text name="cmn_seven_days" /></a></li>
					<li><a href="#" onclick="purRecent('T3')"><img alt="" src="img/icon_10.png"> <s:text name="cmn_month" /></a></li>
					<li><a href="#" onclick="purRecent('T4')"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_months" /></a></li>
				</ul></li>
		</ul>
		<div>
			<label>内容:<input id="nameS" name="" type="text" class="int" /></label> <label> <input id="simpleSearch" name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent(0);" /> <input id="simpleReset" name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent(0);" />
			</label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="purchasePlanList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});">${c.purchasePlanCode}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><a onclick="categoryTab(3,1,'a',event,'purchasePlan')"><img src="img/mail.png" alt="" /> <s:text name='pur_program_content' /></a></li>
				</ul>
			</div>
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /></a>
							<ul>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','purchasePlan');"></a></span> <span><a class="previous" onclick="categoryPager('previous','purchasePlan');"></a></span> <em>(<b class="purchasePlanInfo"></b> <s:text name='cmn_to' /> <b class="purchasePlanTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next','purchasePlan');"></a></span> <span><a class="end" onclick="categoryPager('end','purchasePlan');"></a></span>
					</div>
				</div>
				<div id="purchasePlan" class="table"></div>
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
						<span><a class="start" onclick="categoryPager('start','purchasePlan');"></a></span> <span><a class="previous" onclick="categoryPager('previous','purchasePlan');"></a></span> <em>(<b class="purchasePlanInfo"></b> <s:text name='cmn_to' /> <b class="purchasePlanTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next','purchasePlan');"></a></span> <span><a class="end" onclick="categoryPager('end','purchasePlan');"></a></span>
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