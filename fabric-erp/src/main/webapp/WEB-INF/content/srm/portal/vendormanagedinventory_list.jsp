<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var name = "";
var itemName = "";
var warehouse = "";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
} 
function loadItemName(){
	itemName = $('#itemName').val(); 
	itemName=Url.encode(itemName);
	itemName=Url.encode(itemName);
}
function loadWarehouse(){
	warehouse = $('#warehouse').val(); 
	warehouse=Url.encode(warehouse);
	warehouse=Url.encode(warehouse);
}
function saveOrUpdate(id){
    
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
		  url:'${vix}/srm/supplierVMIAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"提示信息",function(action){
				pager("start","${vix}/srm/supplierVMIAction!goVMIList.action?name="+name,'brand');
			});
		  }
	});
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/srm/supplierVMIAction!goVMIList.action?name="+name,'brand');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#brandOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/srm/supplierVMIAction!goVMIList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name,'brand');
}

function currentPager(tag){
	loadName();
	pager(tag,"${vix}/srm/supplierVMIAction!goVMIList.action?name="+name,'brand');
}

/*搜索*/
function searchForContent(i){
	loadName();
	loadItemName();
	loadWarehouse();
	if(i == 0){
		pager("start","${vix}/srm/supplierVMIAction!goSearchList.action?i="+i+"&searchContent="+name,'brand');
	}
	else{
		pager("start","${vix}/srm/supplierVMIAction!goSearchList.action?i="+i+"&warehouse="+warehouse+"&itemName="+itemName,'brand');
	}
}
//状态
function srmStatus(status){
	pager("start","${vix}/srm/supplierVMIAction!goVMIList.action?status="+status,'brand');
}
//最近使用
function srmRecent(rencentDate){
	pager("start","${vix}/srm/supplierVMIAction!goVMIList.action?updateTime="+rencentDate,'brand');
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
				pager("start", "${vix}/srm/supplierVMIAction!goSingleList.action", 'brand');
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
				<li><a href="#"><img src="img/srm_product.png" alt="" /> <s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#"><s:text name="srm_supp_rela_manage" /></a></li>
				<li><a href="#"><s:text name='srm_supplier_portal' /></a></li>
				<li><a href="#"><s:text name='srm_vendor_managed_inventory' /></a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0);"><span><s:text name='cmn_add' /></span></a>
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
					<li><a href="#" onclick="srmStatus('S1')"><img alt="" src="${vix}/common/img/icon_10.png">待确认</a></li>
					<li><a href="#" onclick="srmStatus('S2')"><img alt="" src="${vix}/common/img/icon_10.png">审批中</a></li>
					<li><a href="#" onclick="srmStatus('S3')"><img alt="" src="${vix}/common/img/icon_10.png">已发货</a></li>
					<li><a href="#" onclick="srmStatus('S4')"><img alt="" src="${vix}/common/img/icon_10.png">已完成</a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_recently_used" /></a>
				<ul>
					<li><a href="#" onclick="srmRecent('T1')"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_days" /></a></li>
					<li><a href="#" onclick="srmRecent('T2')"><img alt="" src="img/icon_10.png"> <s:text name="cmn_seven_days" /></a></li>
					<li><a href="#" onclick="srmRecent('T3')"><img alt="" src="img/icon_10.png"> <s:text name="cmn_month" /></a></li>
					<li><a href="#" onclick="srmRecent('T4')"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_months" /></a></li>
				</ul></li>
		</ul>
		<div>
			<label>搜索内容：<input type="text" class="int" id="nameS"></label> <label> <input id="simpleSearch" name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent(0);" /> <input id="simpleReset" name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent(0);" />
			</label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="supplierVMIList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});">${c.itemName}</a></li>
			</s:iterator>
		</ul>
	</div>
	<div class="box">
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