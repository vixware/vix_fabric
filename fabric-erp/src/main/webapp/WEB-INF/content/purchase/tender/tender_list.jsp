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
var searchName = "";
function loadSearchName(){
	searchName = $('#searchName').val(); 
	searchName=Url.encode(searchName);
	searchName=Url.encode(searchName);
}
function saveOrUpdate(id,pageNo){
	$.ajax({
		  url:'${vix}/purchase/purchaseTenderAction!goSaveOrUpdate.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
}
function saveOrUpdate2(id,pageNo){
	$.ajax({
		  url:'${vix}/purchase/purchaseTenderAction!goSaveOrUpdate2.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
}


function deleteByIds(){
	var ids = '';
	$("[name='chkId']").each(function(){  
		if($(this).attr('checked')){
			ids+=$(this).val()+",";  
		}
	});
	asyncbox.success(ids,"选中的id");
}
//删除
function deleteById(id){
	$.ajax({
		  url:'${vix}/purchase/purchaseTenderAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='cmn_message'/>",function(action){
				pager("start","${vix}/purchase/purchaseTenderAction!goSingleList.action?name="+name,'purchaseTender');
			});
		  }
	});
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/purchase/purchaseTenderAction!goSingleList.action?name="+name+"&pageNo=${pageNo}",'purchaseTender');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#brandTenderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/purchase/purchaseTenderAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name,'purchaseTender');
}

function currentPager(tag){
	loadName();
	pager(tag,"${vix}/purchase/purchaseTenderAction!goSingleList.action?name="+name,'purchaseTender');
}

/*搜索*/
function searchForContent(i){
	loadName();
	loadCode();
	loadSearchName();
	if(i == 0){
		pager("start","${vix}/purchase/purchaseTenderAction!goSearchList.action?i="+i+"&searchContent="+name,'purchaseTender');
	}
	else{
		pager("start","${vix}/purchase/purchaseTenderAction!goSearchList.action?i="+i+"&code="+code+"&searchName="+searchName,'purchaseTender');
	}
}
function purStatus(status){
	pager("start","${vix}/purchase/purchaseTenderAction!goSingleList.action?status="+status,'purchaseTender');
}
//最近使用
function purRecent(rencentDate){
	pager("start","${vix}/purchase/purchaseTenderAction!goSingleList.action?updateTime="+rencentDate,'purchaseTender');
}
function goSearch() {
	$.ajax({
	url : '${vix}/dtbcenter/vehiclePlanAction!goSearch.action',
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
				loadcode();
				loadselectname();
				pager("start", "${vix}/purchase/purchaseTenderAction!goSingleList.action", 'purchaseTender');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/pur_tender.png" alt="" /> <s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#"><s:text name="pur_purchase_manage" /></a></li>
				<li><a href="#">招标管理</a></li>
				<li><a href="#">招标列表</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
		<ul>
			<li><a href="#"><span>新增招标选项</span></a>
				<ul>
					<li><a href="#" onclick="saveOrUpdate(0,$('#selectId').val());">项目包</a></li>
					<li><a href="#" onclick="saveOrUpdate2(0,$('#selectId').val());">普通项目</a></li>
				</ul></li>
		</ul>
		<a href="#"><span>上报</span></a> <a href="#"><span>审批</span></a> <a href="#"><span>流标</span></a>
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
			<s:iterator value="purchaseTenderList" var="p">
				<li><a href="#" onclick="saveOrUpdate(${p.id});">${p.title}</a></li>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="purchaseTenderInfo"></b> <s:text name='cmn_to' /> <b class="purchaseTenderTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="purchaseTender" class="table" style="overflow-x: auto; width: 100%;"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="purchaseTenderInfo"></b> <s:text name='cmn_to' /> <b class="purchaseTenderTotalCount"></b>)
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