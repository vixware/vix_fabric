<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
/* 搜索功能 */
var name = "";
var proposalTitle = "";
var bizOrgNames ="";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function loadProposalTitle(){
	proposalTitle = $('#project_proposalTitle').val();
	proposalTitle = Url.encode(proposalTitle);
	proposalTitle = Url.encode(proposalTitle);
}
function loadBizOrgNames1(){
	bizOrgNames = $('#project_bizOrgNames').val();
	bizOrgNames = Url.encode(bizOrgNames);
	bizOrgNames = Url.encode(bizOrgNames);
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
		$("#project_proposalTitle").val("");
		$("#project_bizOrgNames").val("");
	}
}

/*搜索*/
function searchForContent(i){
	loadName();
	if(i == 0){
		pager("start","${vix}/oa/projectManagementAction!goSearchList.action?i="+i+"&proposalTitle="+name,'project');
	}
	else{
		pager("start","${vix}/oa/projectManagementAction!goSearchList.action?i="+i+"&proposalTitle="+proposalTitle+"&bizOrgNames="+bizOrgNames,'project');
	}
}

//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/oa/projectManagementAction!goSearch.action',
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
				pager("start", "${vix}/oa/projectManagementAction!goSearchList.action?proposalTitle=" + $('#proposalTitle').val() + "&bizOrgNames=" + $('#bizOrgNames').val(), 'project');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};

//计划类别
 function saleOrderStatus(workPlan){
 	pager("start","${vix}/oa/projectManagementAction!goSingleList.action?workPlan="+workPlan,'project');
 }
 //最近使用
 function leastRecentlyUsed(initiateTime){
 	pager("start","${vix}/oa/projectManagementAction!goSingleList.action?initiateTime="+initiateTime,'project');
 }

$('#numBtn').click(function(){
	$('#numBtn').parent("li").toggleClass("current");
	$('#number').stop().animate({height: 'toggle', opacity: 'toggle'},500,function(){
		$('#number').css('overflow','visible');
	}); 
	return false;
});
$(function(){
	if($('#numBox').length) $('#numBox').listmenu({menuWidth: '100%', cols:{ count:6, gutter:0 }});
});
$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});

/*保存工作计划  */
function saveOrUpdate(id){
	$.ajax({
		  url:'${vix}/oa/projectManagementAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
} 

/* 查看工作计划与批注 */
function lookPostilOrPlan(id){
	$.ajax({
		  url:"${vix}/oa/workPlanAction!goLookPostilOrPlan.action?id="+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 780,
					height :460,
					title:"查看工作计划与批注",
					html:html,
					callback : function(action){
						if(action == 'ok'){
								}
							} ,
							btnsbar : [{
								text :'关闭',
								action :'cancel'
							}]
				});
		  }
	});
};

/* 批注 */
function postilOrPlan(id){
	$.ajax({
		  url:"${vix}/oa/projectManagementAction!goPostilOrPlan.action?id="+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 760,
					height : 370,
					title:"批注",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#projectForm').validationEngine('validate')){
								var str="";   
								$("input[name='allowedTime']:checkbox:checked").each(function(){   
									str+=$(this).val()+",";   
								});   
								//alert(str);  
								$.post('${vix}/oa/projectManagementAction!postilOrPlan.action',
									 {
									  'postil.projectManagement.id':"id",
									  'postil.planContent':planContents.html()
									},
									function(result){
										showMessage(result);
										setTimeout("",1000);
										pager("start","${vix}/oa/projectManagementAction!goSingleList.action?name="+name,'project');
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

/* 设置继续或者暂停 */
function updateIsPublish(id,isPublish){
	var param={'id':id};  
	$.ajax({ 
		url:"${vix}/oa/projectManagementAction!updateIsPublish.action",
		data:param,
		dataType:"text",
		success:function(data){
			/* alert('修改成功'); */
			loadName();
			pager("start","${vix}/oa/projectManagementAction!goSingleList.action?name="+name,'project');
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

function deleteById(id){
	$.ajax({
		  url:'${vix}/oa/projectManagementAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"提示信息",function(action){
				pager("start","${vix}/oa/projectManagementAction!goSingleList.action?name="+name,'project');
			});
		  }
	});
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/oa/projectManagementAction!goSingleList.action?name="+name,'project');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#projectOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/oa/projectManagementAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name,'project');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/oa/projectManagementAction!goSingleList.action?name="+name,'project');
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
loadMenuContent('${vix}/oa/main/oaMainAction!goMenuContent.action');
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/oa/oa_planned.png" alt="" /> 协同办公</a></li>
				<li><a href="#"><s:text name="oa_administrative_civil" /></a></li>
				<li><a href="#"><s:text name="工作计划" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/projectManagementAction!goList.action?pageNo=${pageNo}');"><s:text name="工作计划管理" /></a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0,$('#selectId').val());"><span><s:text name='新增工作计划' /></span></a>
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
			<%-- <li><a class="btn" type="button" onclick="chooseEmployees();"><img src="img/find.png" alt="" /> <s:text name="选择人员" /></a></li> --%>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="计划类别" /> </a>
				<ul>
					<li><a href="#" onclick="saleOrderStatus('0')"><img alt="" src="img/uncommitted.png"> <s:text name="今日计划" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('1')"><img alt="" src="img/unaudited.png"> <s:text name="本周计划" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('2')"><img alt="" src="img/unaudited.png"> <s:text name="本月计划" /> </a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/leastRecentlyUsed.png"> <s:text name="cmn_recently_used" /> </a>
				<ul>
					<li><a href="#" onclick="leastRecentlyUsed('THREEDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('SEVENDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('ONEMONTH');"><img alt="" src="img/time_go.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('THREEMONTHS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div>
			<label>计划名称: <input type="text" class="int" id="nameS" style="width: 150px;"> <input type="button" value="搜索" class="btn" onclick="searchForContent(0);" />
			</label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" />
			</label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="projectManagementList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());"><span style="display: none;">${c.chineseFirstLetter}</span>${c.proposalTitle}</a></li>
			</s:iterator>
		</ul>
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
								<li><a href="#"><s:text name='cmn_mail'/></a></li>
								<li><a href="#"><s:text name="cmn_merger"/></a></li>
								<li><a href="#"><s:text name="cmn_export"/></a></li>
							</ul>
						</li>
					</ul>
					<strong><s:text name="cmn_selected"/>:<span id="selectCount1">0</span></strong> --%>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="projectInfo"></b> <s:text name='cmn_to' /> <b class="projectTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="project" class="table" style="overflow-x: auto; width: 100%;"></div>
				<div class="pagelist drop">
					<%-- <ul>
						<li class="ed">
							<a href="#"><s:text name='cmn_choose'/></a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete'/></a></li>
								<li><a href="#"><s:text name='cmn_mail'/></a></li>
								<li><a href="#"><s:text name="cmn_merger"/></a></li>
								<li><a href="#"><s:text name="cmn_export"/></a></li>
							</ul>
						</li>
					</ul>
					<strong><s:text name="cmn_selected"/>:<span id="selectCount2">0</span></strong> --%>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="projectInfo"></b> <s:text name='cmn_to' /> <b class="projectTotalCount"></b>)
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