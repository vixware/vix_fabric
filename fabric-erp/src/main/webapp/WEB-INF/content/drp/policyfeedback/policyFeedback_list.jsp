<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	_pad_page_refresh_main_content = true;
	
	$(function(){
		//载入tab数据
		_load_tab_page_content();
		loadMenuContent('${vix}/drp/drpMainAction!goMenuContent.action');
	});
	function saveOrUpdate(id,pageNo){
		$.ajax({
			  url:'${vix}/drp/policyFeedbackAction!goSaveOrUpdate.action?id='+id+"&pageNo="+pageNo,
			  cache: false,
			  success: function(html){
			  	$("#mainContent").html(html);
			  }
		});}
	//批量删除
	function deleteEntitys() {
		var ids = '';
		$("[name='chkId']").each(function() {
			if ($(this).attr('checked')) {
				ids += $(this).val() + ",";
			}
		});
		$.ajax({
			url : '${vix}/drp/policyFeedbackAction!deleteByIds.action?ids=' + ids,
			cache : false,
			success : function(html) {
				asyncbox.success(html, "提示信息", function(action) {
					pager("start", "${vix}/drp/policyFeedbackAction!goSingleList.action?1=1", 'policyInformation');
				});
			}
		});
	}
	
	//排序 
	function orderBy(orderField){
		var orderBy = $("#policyInformationOrderBy").val();
		if(orderBy == 'desc'){
			orderBy = "asc";
		}else{
			orderBy = 'desc';
		}
		pager("start","${vix}/drp/policyFeedbackAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy,'policyInformation');
	}
	
	function currentPager(tag){
		pager(tag,"${vix}/drp/policyFeedbackAction!goSingleList.action?1=1",'policyInformation');
	}
	
	function saveOrUpdatePoliciesIssued(id){
		$.ajax({
			  url:'${vix}/drp/policyFeedbackAction!goPoliciesIssued.action?id='+id,
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 650,
						height : 200,
						title: "下达",
						html:html,
						callback : function(action){
							if(action == 'ok'){
								if($('#policyInformationForm').validationEngine('validate')){
								$.post('${vix}/drp/policyFeedbackAction!saveOrUpdatePoliciesIssued.action',
										 {
										  'policyInformation.id':$("#policyInformationId").val(),
										  'channelDistributorId':$("#channelDistributorId").val()
										},
										function(result){
											showMessage(result);
											setTimeout("", 1000);
											loadContent('${vix}/drp/policyFeedbackAction!goList.action');
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
	function listbystatus(status) {
		pager("start", "${vix}/drp/policyFeedbackAction!goSingleList.action?status=" + status, 'policyInformation');
	}
	/* 最近使用 */
	function leastRecentlyUsed(days) {
		pager("start", "${vix}/drp/policyFeedbackAction!goSingleList.action?days=" + days, 'policyInformation');
	}
	function goSearch() {
		$.ajax({
		url : '${vix}/drp/policyFeedbackAction!goSearch.action',
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
					pager("start", "${vix}/drp/policyFeedbackAction!goSingleList.action?code=" + code + "&name=" + selectname, 'policyInformation');
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
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_shop.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/drpAction!goList.action','bg_02');"><s:text name="drp_management" /> </a></li>
				<li><a href="#"><s:text name="drp_store_management" /> </a></li>
				<li><a href="#">政策下达与反馈 </a></li>
				<li><a href="#">政策下达 </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0);"><span>新增</span></a>
		</p>
	</div>
</div>
<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img alt="" src="img/icon_11.png"> </b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
			<li class="fly"><a href="#"><img alt="" src="img/leastRecentlyUsed.png"> <s:text name="cmn_recently_used" /> </a>
				<ul>
					<li><a href="#" onclick="leastRecentlyUsed('THREEDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('SEVENDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('ONEMONTH');"><img alt="" src="img/time_go.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('THREEMONTHS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div search_page_id="tab_home" class="grid_search search_simple">
			<label><input type="text" name="name" class="int more" placeholder="主题 "></label> <input type="button" class="btn search" value="<s:text name='cmn_search'/>" /><label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<s:iterator value="policyInformationList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});"><span style="display: none;">${c.chineseCharacter}</span>${c.name}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="right">
			<div id="tab_content" class="tabbable addable_tab">
				<ul id="myTab" class="right_menu nav nav-tabs">
					<li pageId="tab_home" class="current"><a href="#tab_home" page="${vix}/drp/policyFeedbackAction!goSingleList.action"> <img src="img/mail.png" alt="" />政策列表
					</a></li>
				</ul>
				<div id="tab_content_page" class="right_content">
					<div id="tab_home" class="table"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>