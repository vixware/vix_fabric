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
var code = "";
function loadCode(){
	code = $('#code').val(); 
	code=Url.encode(code);
	code=Url.encode(code);
}
var searchname = "";
function loadSearchname(){
	searchname = $('#searchname').val(); 
	searchname=Url.encode(searchname);
	searchname=Url.encode(searchname);
}
//普通采购发票
function saveOrUpdate(id){
	$.ajax({
		  url:'${vix}/purchase/purchaseInvoiceAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
};
//运费发票
function saveOrUpdate2(id){
	$.ajax({
		  url:'${vix}/purchase/freightInvoiceAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
};

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
		  url:'${vix}/purchase/purchaseInvoiceAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"提示信息",function(action){
				pager("start","${vix}/purchase/purchaseInvoiceAction!goInvoiceList.action?name="+name,'brand');
			});
		  }
	});
}

 
loadName();
//载入分页列表数据
pager("start","${vix}/purchase/purchaseInvoiceAction!goInvoiceList.action?name="+name,'brand');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#brandOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/purchase/purchaseInvoiceAction!goInvoiceList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name,'brand');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/purchase/purchaseInvoiceAction!goInvoiceList.action?name="+name,'brand');
}

/*搜索*/
function searchForContent(i){
	loadName();
	loadCode();
	loadSearchname();
	if(i == 0){
		pager("start","${vix}/purchase/purchaseInvoiceAction!goSearchList.action?i="+i+"&searchContent="+name,'brand');
	}
	else{
		pager("start","${vix}/purchase/purchaseInvoiceAction!goSearchList.action?i="+i+"&code="+code+"&searchname="+searchname,'brand');
	}
}
//状态
function purStatus(status){
	pager("start","${vix}/purchase/purchaseInvoiceAction!goInvoiceList.action?status="+status,'brand');
}
//最近使用
function purRecent(rencentDate){
	pager("start","${vix}/purchase/purchaseInvoiceAction!goSingleList.action?updateTime="+rencentDate,'brand');
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/pur_invoice.png" alt="" /> <s:text name="supplyChain" /></a></li>
				<li><a href="#"><s:text name="pur_purchase_manage" /></a></li>
				<li><a href="#">采购发票列表</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
		<ul>
			<li><a href="#"><span>新增采购发票</span></a>
				<ul>
					<li><a href="#" onclick="saveOrUpdate(0,$('#selectId').val());">普通采购发票</a></li>
					<li><a href="#" onclick="saveOrUpdate2(0,$('#selectId').val());">运费发票</a></li>
					<li><a href="#">红字专用采购发票</a></li>
					<li><a href="#">红字普通采购发票</a></li>
					<li><a href="#">红字运费采购发票</a></li>
					<li><a href="#">专用采购发票</a></li>
				</ul></li>
		</ul>
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
			<s:iterator value="purchaseInvoiceList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});">${c.name}</a></li>
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
								<li><a href="#"><s:text name='cmn_mail' /></a></li>
								<li><a href="#"><s:text name="cmn_merger" /></a></li>
								<li><a href="#"><s:text name="cmn_export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="brandInfo"></b> <s:text name='cmn_to' /> <b class="brandTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="brand" class="table" style="overflow-x: auto; width: 100%;"></div>
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
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="brandInfo"></b> <s:text name='cmn_to' /> <b class="brandTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>