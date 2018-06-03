<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var companyName = "";
var product = "";
function loadCompanyName(){
	companyName = $('#companyNameS').val();
	product = $('#productS').val();
	companyName=Url.encode(companyName);
	companyName=Url.encode(companyName);
	product=Url.encode(product);
	product=Url.encode(product);
}
function saveOrUpdate(id){
	$.ajax({
		  url:'${vix}/crm/project/competitorAction!goSaveOrUpdate.action?id='+id,
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
		  url:'${vix}/crm/project/competitorAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"提示信息",function(action){
				pager("start","${vix}/crm/project/competitorAction!goListContent.action?companyName="+companyName+"&product="+product,'competitor');
			});
		  }
	});
}

function searchForContent(){
	loadCompanyName();
	pager("start","${vix}/crm/project/competitorAction!goListContent.action?companyName="+companyName+"&product="+product,'competitor');
}
 
loadCompanyName();
//载入分页列表数据
pager("start","${vix}/crm/project/competitorAction!goListContent.action?companyName="+companyName+"&product="+product,'competitor');
//排序 
function orderBy(orderField){
	loadCompanyName();
	var orderBy = $("#competitorOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/crm/project/competitorAction!goListContent.action?orderField="+orderField+"&orderBy="+orderBy+"&companyName="+companyName+"&product="+product,'competitor');
}

function currentPager(tag){
	loadCompanyName();
	pager(tag,"${vix}/crm/project/competitorAction!goListContent.action?companyName="+companyName+"&product="+product,'competitor');
}

//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
function reset(){
	$('#companyNameS').val('');$('#productS').val('');
}
loadMenuContent('${vix}/crm/project/crmProjectAction!goMenuContent.action');
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/competitors.png" alt="" />供应链</a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#">基础资料管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/project/competitorAction!goList.action');">竞争对手管理</a></li>
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
			<li><a href="#" id="numBtn"><img src="img/icon_10.png" alt="" />
				<s:text name="cmn_index" /></a></li>
		</ul>
		<div>
			<label>公司名称<input type="text" class="int" id="companyNameS"></label> <label>竞争产品<input type="text" class="int" id="productS"></label> <label> <input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" /> <input type="button" value="<s:text name='cmn_reset'/>" class="btn"
				onclick="reset();" />
			</label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="indexList" var="index">
				<li><a href="###" onclick="saveOrUpdate(${index.id});"><span style="display: none;">${index.chineseFirstLetter}</span>${index.companyName}</a></li>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="competitorInfo"></b> <s:text name='cmn_to' /> <b class="competitorTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="competitor" class="table"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="competitorInfo"></b> <s:text name='cmn_to' /> <b class="competitorTotalCount"></b>)
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