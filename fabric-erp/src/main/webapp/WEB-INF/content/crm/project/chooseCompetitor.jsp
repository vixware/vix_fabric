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
 
$('input.btn[type="button"]').hover(function(){$(this).addClass("btnhover");},function(){$(this).removeClass("btnhover");});

function searchForContent(){
	loadCompanyName();
	pager("start","${vix}/crm/project/competitorAction!goSubListContent.action?companyName="+companyName+"&product="+product,'chooseCompetitor');
}
 
loadCompanyName();
//载入分页列表数据
pager("start","${vix}/crm/project/competitorAction!goSubListContent.action?companyName="+companyName+"&product="+product,'chooseCompetitor');
//排序 
function orderBy(orderField){
	loadCompanyName();
	var orderBy = $("#chooseCompetitorOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/crm/project/competitorAction!goSubListContent.action?orderField="+orderField+"&orderBy="+orderBy+"&companyName="+companyName+"&product="+product,'chooseCompetitor');
}

function currentPager(tag){
	loadCompanyName();
	pager(tag,"${vix}/crm/project/competitorAction!goSubListContent.action?companyName="+companyName+"&product="+product,'chooseCompetitor');
}
function reset(){
	$('#companyNameS').val('');$('#productS').val('');
}
</script>
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
		<div>
			<label>公司名称<input type="text" class="int" id="companyNameS"></label> <label>竞争产品<input type="text" class="int" id="productS"></label> <label> <input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" /> <input type="button" value="<s:text name='cmn_reset'/>" class="btn"
				onclick="reset();" />
			</label>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="chooseCompetitorInfo"></b> <s:text name='cmn_to' /> <b class="chooseCompetitorTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="chooseCompetitor" class="table"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="chooseCompetitorInfo"></b> <s:text name='cmn_to' /> <b class="chooseCompetitorTotalCount"></b>)
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