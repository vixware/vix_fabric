<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var subject = "";
function loadSubject(){
	subject = $('#subjectS').val();
	subject=Url.encode(subject);
	subject=Url.encode(subject);
}

function saveOrUpdate(id){
	$.ajax({
		  url:'${vix}/crm/salechance/saleChanceAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
};

function deleteById(id){
	$.ajax({
		  url:'${vix}/crm/salechance/saleChanceAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"提示信息",function(action){
				pager("start","${vix}/crm/salechance/saleChanceAction!goListContent.action?subject="+subject+'&companyCode='+$("#companyCode").val(),'salsubjectnce');
			});
		  }
	});
}

function searchForContent(){
	loadSubject();
	pager("start","${vix}/crm/salechance/saleChanceAction!goListContent.action?subject="+subject+'&companyCode='+$("#companyCode").val(),'saleChance');
}
 
loadSubject();
//载入分页列表数据
pager("start","${vix}/crm/salechance/saleChanceAction!goListContent.action?subject="+subject+'&companyCode='+$("#companyCode").val(),'saleChance');
//排序 
function orderBy(orderField){
	loadSubject();
	var orderBy = $("#saleChanceOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/crm/salechance/saleChanceAction!goListContent.action?orderField="+orderField+"&orderBy="+orderBy+"&subject="+subject+'&companyCode='+$("#companyCode").val(),'saleChance');
}

bindSearch();
function currentPager(tag){
	loadSubject();
	pager(tag,"${vix}/crm/salechance/saleChanceAction!goListContent.action?subject="+subject+'&companyCode='+$("#companyCode").val(),'saleChance');
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
								loadTitle();
								pager('start',"${vix}/crm/salechance/saleChanceAction!goListContent.action?title="+title+'&companyCode='+result[0],'saleChance');
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
loadMenuContent('${vix}/crm/project/crmProjectAction!goMenuContent.action');

//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/crm/salechance/saleChanceAction!goSearch.action',
	cache : false,
	success : function(html) {
		asyncbox.open({
		modal : true,
		width : 650,
		height : 300,
		title : "查询条件",
		html : html,
		callback : function(action) {
			loadConditions();
			if (action == 'ok') {
				pager("start", "${vix}/crm/salechance/saleChanceAction!goListContent.action?customerName=" + customerName + "&charger=" + charger + "&subject=" + subject, 'saleChance');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};

function reset(){
	$('#subjectS').val('');
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/saleChance.png" alt="" />供应链</a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/salechance/saleChanceAction!goList.action');">销售机会</a></li>
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
			<li><span id="companyName" style="height: 30px; width: 220px; padding-top: 8px;"></span><input type="hidden" id="companyCode" /></li>
			<li><a href="###" onclick="chooseCompany()"><img src="${vix}/common/img/icon_10.png">选择公司</a></li>
			<li><a href="#" id="numBtn"><img src="img/icon_10.png" alt="" />
				<s:text name="cmn_index" /></a></li>
		</ul>
		<div>
			<label><s:text name="主题" /><input type="text" class="int" id="subjectS"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="reset();"></label> <label> <input type="button"
				value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="indexList" var="index">
				<li><a href="###" onclick="saveOrUpdate(${index.id});"><span style="display: none;">${index.chineseFirstLetter}</span>${index.subject}</a></li>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="saleChanceInfo"></b> <s:text name='cmn_to' /> <b class="saleChanceTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="saleChance" class="table"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="saleChanceInfo"></b> <s:text name='cmn_to' /> <b class="saleChanceTotalCount"></b>)
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