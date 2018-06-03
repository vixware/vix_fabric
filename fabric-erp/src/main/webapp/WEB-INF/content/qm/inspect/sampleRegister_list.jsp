<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
/* 搜索功能 */
var name = "";
var specificationsModel = "";
var productName ="";
var sampleCycle ="";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function loadProductName(){
	productName = $('#sample_productName').val();
	productName = Url.encode(productName);
	productName = Url.encode(productName);
}
function loadSpecificationsModel(){
	specificationsModel = $('#sample_specificationsModel').val();
	specificationsModel = Url.encode(specificationsModel);
	specificationsModel = Url.encode(specificationsModel);
}
function loadSampleCycle(){
	sampleCycle = $('#sample_sampleCycle').val();
	sampleCycle = Url.encode(sampleCycle);
	sampleCycle = Url.encode(sampleCycle);
}


/*判断搜索内容是否为空*/
function validateSearch(temp){
	if(null == temp || "" == temp){
		return false;
	}
	return true;
}

/*重置搜索*/
function resetForContent(i){
	if(i == 0){
		$("#nameS").val("");
	}
	else{
		$("#sample_productName").val("");
		$("#sample_specificationsModel").val("");
		$("#sample_sampleCycle").val("");
	}
}

/*搜索*/
function searchForContent(i){
	loadName();
	loadProductName();
	loadSpecificationsModel();
	loadSampleCycle();
	if(i == 0){
		pager("start","${vix}/qm/sampleRegisterAction!goSearchList.action?i="+i+"&productName="+name,'sample');
	}
	else{
		pager("start","${vix}/qm/sampleRegisterAction!goSearchList.action?i="+i+"&productName="+productName+"&specificationsModel="+specificationsModel+"&sampleCycle="+sampleCycle,'sample');
	}
}

/*改变搜索按钮的显示*/
 function changeDisplay(){
	var divText = $("#lb_search_advanced").text();
	if(divText == "高级搜索"){
		$("#nameS").attr({disabled:'true'});
		$("#simpleSearch").hide();
		$("#simpleReset").hide();
	}
	else{
		$("#nameS").removeAttr("disabled");
		$("#simpleSearch").show();
		$("#simpleReset").show();
	}
} 

 //最近使用
 function leastRecentlyUsed(samplesDate){
 	pager("start","${vix}/qm/sampleRegisterAction!goSingleList.action?samplesDate="+samplesDate,'sample');
 }

function saveOrUpdate(id){
	$.ajax({
		  url:'${vix}/qm/sampleRegisterAction!goSaveOrUpdate.action?id='+id,
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
		  url:'${vix}/qm/sampleRegisterAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"提示信息",function(action){
				pager("start","${vix}/qm/sampleRegisterAction!goSingleList.action?name="+name,'sample');
			});
		  }
	});
}

 
loadName();
//载入分页列表数据
pager("start","${vix}/qm/sampleRegisterAction!goSingleList.action?name="+name,'sample');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#sampleOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/qm/sampleRegisterAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name,'sample');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/qm/sampleRegisterAction!goSingleList.action?name="+name,'sample');
}

//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
loadMenuContent('${vix}/qm/pmMainAction!goMenuContent.action');

</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/qm_quality_management.png" alt="" /> 供应链</a></li>
				<li><a href="#">质量管理</a></li>
				<li><a href="#">样品管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/qm/sampleRegisterAction!goList.action?pageNo=${pageNo}');">样品登记</a></li>
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
			<li class="fly"><a href="#"><img alt="" src="img/leastRecentlyUsed.png"> <s:text name="最近留样时间" /> </a>
				<ul>
					<li><a href="#" onclick="leastRecentlyUsed('THREEDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('SEVENDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('ONEMONTH');"><img alt="" src="img/time_go.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('THREEMONTHS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div>
			<label>名称:<input id="nameS" name="" type="text" class="int" /></label> <label> <input id="simpleSearch" name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent(0);" /> <input id="simpleReset" name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent(0);" />
			</label> <strong id="lb_search_advanced" onclick="changeDisplay();"><s:text name='cmn_advance_search' /></strong>
		</div>
		<div class="search_advanced">
			<label>名称:<input id="sample_productName" name="" type="text" class="int" /></label> <label>规格型号:<input id="sample_specificationsModel" name="" type="text" class="int" /></label> <label>留样周期:<input id="sample_sampleCycle" name="" type="text" class="int" /></label> <label> <input name="" type="button" class="btn"
				value="<s:text name='cmn_search'/>" onclick="searchForContent(1);" /> <input name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent(1);" />
			</label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<%-- <ul>
						<li class="tp">
							<a href="#"><s:text name='cmn_choose'/></a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete'/></a></li>
								<li><a href="#"><s:text name='cmn_email'/></a></li>
								<li><a href="#"><s:text name="merger"/></a></li>
								<li><a href="#"><s:text name="export"/></a></li>
							</ul>
						</li>
					</ul>
					<strong><s:text name="cmn_selected"/>:<span id="selectCount1">0</span></strong> --%>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="sampleInfo"></b> <s:text name='cmn_to' /> <b class="sampleTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="sample" class="table"></div>
				<div class="pagelist drop">
					<%-- <ul>
						<li class="ed">
							<a href="#"><s:text name='cmn_choose'/></a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete'/></a></li>
								<li><a href="#"><s:text name='cmn_email'/></a></li>
								<li><a href="#"><s:text name="merger"/></a></li>
								<li><a href="#"><s:text name="export"/></a></li>
							</ul>
						</li>
					</ul>
					<strong><s:text name="cmn_selected"/>:<span id="selectCount2">0</span></strong> --%>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="sampleInfo"></b> <s:text name='cmn_to' /> <b class="sampleTotalCount"></b>)
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