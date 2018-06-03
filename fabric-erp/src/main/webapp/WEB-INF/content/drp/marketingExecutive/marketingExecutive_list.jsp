<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
loadMenuContent('${vix}/drp/drpMainAction!goMenuContent.action');
var name = "";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
var feedbackContent = "";
function loadFeedbackContent(){
	feedbackContent = $('#feedbackContent').val();
	feedbackContent=Url.encode(feedbackContent);
	feedbackContent=Url.encode(feedbackContent);
}
var selectContent = "";
function loadSelectContent(){
	selectContent = $('#selectContent').val();
	selectContent=Url.encode(selectContent);
	selectContent=Url.encode(selectContent);
}
function searchForContent(){
	loadName();
	loadFeedbackContent();
	loadSelectContent();
	pager("start","${vix}/drp/marketingExecutiveAction!goSingleList.action?name="+name+"&feedbackContent="+feedbackContent+"&selectContent="+selectContent,'marketingCampaign');
}
 function resetForContent(){
	 $('#nameS').val('');
	 $('#feedbackContent').val('');
	 $('#selectContent').val('');
 }
loadName();
//载入分页列表数据
pager("start","${vix}/drp/marketingExecutiveAction!goSingleList.action?name="+name,'marketingCampaign');
//排序 
function orderBy(orderField){
	var orderBy = $("#marketingCampaignOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/drp/marketingExecutiveAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy,'marketingCampaign');
}
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/drp/marketingExecutiveAction!goSingleList.action?name="+name,'marketingCampaign');
}
/**
 * 活动反馈
 */
function marketingCampaignTask(id){
	$.ajax({
		  url:'${vix}/drp/marketingExecutiveAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 850,
					height : 375,
					title: "反馈",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#marketingCampaignTaskForm').validationEngine('validate')){
							$.post('${vix}/drp/marketingExecutiveAction!saveOrUpdate.action',
									 {
									  'marketingCampaignTask.id':$("#marketingCampaignTaskId").val(),
									  'marketingCampaignTask.feedbackContent': feedbackContent.html()
									},
									function(result){
										showMessage(result);
										setTimeout("", 1000);
										loadContent('${vix}/drp/marketingExecutiveAction!goList.action');
									}
								 );
							}else {
								return false;
							}
						}
						},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};
bindSearch();
/** 状态 */
function listbystatus(status) {
	pager("start", "${vix}/drp/marketingExecutiveAction!goSingleList.action?status=" + status, 'marketingCampaign');
}
/* 最近使用 */
function leastRecentlyUsed(days) {
	pager("start", "${vix}/drp/marketingExecutiveAction!goSingleList.action?days=" + days, 'marketingCampaign');
}
//批量删除
function deleteEntitys() {
	var ids = '';
	$("[name='chkId']").each(function() {
		if ($(this).attr('checked')) {
			ids += $(this).val() + ",";
		}
	});
	$.ajax({
		url : '${vix}/drp/marketingExecutiveAction!deleteByIds.action?ids=' + ids,
		cache : false,
		success : function(html) {
			asyncbox.success(html, "提示信息", function(action) {
				pager("start", "${vix}/drp/marketingExecutiveAction!goSingleList.action?1=1", 'marketingCampaign');
			});
		}
	});
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_marketing_activity.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#">营销组合管理 </a></li>
				<li><a href="#">营销执行管理</a></li>
			</ul>
		</div>
	</h2>
</div>

<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /> </b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_mode" /> </a>
				<ul>
					<li><a href="#" onclick="listbystatus('0')"><img alt="" src="img/uncommitted.png"> 未提交 </a></li>
					<li><a href="#" onclick="listbystatus('1')"><img alt="" src="img/unaudited.png"> 待审批 </a></li>
					<li><a href="#" onclick="listbystatus('2')"><img alt="" src="img/verifying.png"> 审批中 </a></li>
					<li><a href="#" onclick="listbystatus('3')"><img alt="" src="img/approved.png"> 已完成 </a></li>
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
			<label>内容:<input type="text" class="int" id="nameS" placeholder="主题"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent();" /></label> <label> <input
				type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
		<div class="search_advanced">
			<label>主题:<input type="text" class="int" name="selectContent" id="selectContent" placeholder="主题"></label> <label>反馈内容:<input type="text" class="int" name="feedbackContent" id="feedbackContent" placeholder="反馈内容"></label><label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name=""
				onclick="searchForContent();"><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent();"></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<s:iterator value="marketingCampaignTaskList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});"><span style="display: none;">${c.chineseCharacter}</span>${c.name}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="right">
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_add' /> </a></li>
								<li><a href="#"><s:text name='cmn_update' /> </a></li>
								<li><a href="#" onclick="deleteEntitys();"><s:text name='cmn_delete' /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount1">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="marketingCampaignInfo"></b> <s:text name='cmn_to' /> <b class="marketingCampaignTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="marketingCampaign" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#" onclick="deleteEntitys();"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="marketingCampaignInfo"></b> <s:text name='cmn_to' /> <b class="marketingCampaignTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
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