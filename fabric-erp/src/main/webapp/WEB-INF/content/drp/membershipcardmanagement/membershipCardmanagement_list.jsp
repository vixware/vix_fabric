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
function saveOrUpdate(id,pageNo){
	$.ajax({
		  url:'${vix}/drp/membershipCardmanagementAction!goSaveOrUpdate.action?id='+id+"&pageNo="+pageNo+"&parentId="+$("#selectId").val()+"&treeType="+$("#selectTreeType").val(),
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
};
function batchSaveOrUpdate(id,pageNo){
	$.ajax({
		  url:'${vix}/drp/membershipCardmanagementAction!goBatchSaveOrUpdate.action?id='+id+"&pageNo="+pageNo+"&parentId="+$("#selectId").val()+"&treeType="+$("#selectTreeType").val(),
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
};
function changeCard(id,pageNo){
	$.ajax({
		  url:'${vix}/drp/membershipCardmanagementAction!goChangeCard.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
};
//批量删除
function deleteEntitys() {
	var ids = '';
	$("[name='chkId']").each(function() {
		if ($(this).attr('checked')) {
			ids += $(this).val() + ",";
		}
	});
	$.ajax({
		url : '${vix}/drp/membershipCardmanagementAction!deleteByIds.action?ids=' + ids,
		cache : false,
		success : function(html) {
			asyncbox.success(html, "提示信息", function(action) {
				pager("start", "${vix}/drp/membershipCardmanagementAction!goSingleList.action?1=1", 'shipCard');
			});
		}
	});
}

function searchForContent(){
	loadName();
	pager("start","${vix}/drp/membershipCardmanagementAction!goSingleList.action?name="+name,'shipCard');
}
/**重置搜索*/
function resetForContent(){
	$("#nameS").val("");
}
loadName();
//载入分页列表数据
pager("start","${vix}/drp/membershipCardmanagementAction!goSingleList.action?name="+name,'shipCard');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#shipCardOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/drp/membershipCardmanagementAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy,'shipCard');
}

function currentPager(tag){
	loadName();
	pager(tag,"${vix}/drp/membershipCardmanagementAction!goSingleList.action?name="+name,'shipCard');
}
/* 续费 */
function memberShipCardFeesForRenewal(id){
	$.ajax({
		  url:'${vix}/drp/membershipCardmanagementAction!goMemberShipCardFeesForRenewal.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 650,
					height : 455,
					title:"会员卡",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#memberShipCardForm').validationEngine('validate')){
							$.post('${vix}/drp/membershipCardmanagementAction!memberShipCardFeesForRenewal.action',
									 {
									  'memberShipCard.id':$("#memberShipCardId").val(),
									  'otherMemberShipCardId':$("#otherMemberShipCardId").val(),
									  'balanceAmount':$("#balanceAmount").val()
									},
									function(result){
										showMessage(result);
										setTimeout("", 1000);
										loadContent('${vix}/drp/membershipCardmanagementAction!goList.action');
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
function memberShipCardForbidden(id){
	asyncbox.confirm('确定要禁用该会员卡么?','禁用',function(action){
		if(action == 'ok'){
			$.ajax({
				  url:'${vix}/drp/membershipCardmanagementAction!memberShipCardDisable.action?id='+id,
				  cache: false,
				  success: function(html){
					asyncbox.success(html,"提示信息",function(action){
						loadContent('${vix}/drp/membershipCardmanagementAction!goList.action');
					});
				  }
			});
		}
	});
};
function memberShipCardForbiddenUp(id){
	asyncbox.confirm('确定要启用该会员卡么?','启用',function(action){
		if(action == 'ok'){
			$.ajax({
				  url:'${vix}/drp/membershipCardmanagementAction!memberShipCardEnable.action?id='+id,
				  cache: false,
				  success: function(html){
					asyncbox.success(html,"提示信息",function(action){
						loadContent('${vix}/drp/membershipCardmanagementAction!goList.action');
					});
				  }
			});
		}
	});
};
function saveOrUpdateMemberShipCardTransfers(id){
	$.ajax({
		  url:'${vix}/drp/membershipCardmanagementAction!goMemberShipCardTransfers.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 650,
					height : 225,
					title:"转账",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#memberShipCardForm').validationEngine('validate')){
							$.post('${vix}/drp/membershipCardmanagementAction!saveOrUpdateMemberShipCardTransfers.action',
									 {
									  'memberShipCard.id':$("#memberShipCardId").val(),
									  'otherMemberShipCardId':$("#otherMemberShipCardId").val(),
									  'transfersOutAmount':$("#transfersOutAmount").val()
									},
									function(result){
										showMessage(result);
										setTimeout("", 1000);
										loadContent('${vix}/drp/membershipCardmanagementAction!goList.action');
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
/** 状态 */
function listbystatus(status) {
	pager("start", "${vix}/drp/membershipCardmanagementAction!goSingleList.action?status=" + status, 'shipCard');
}
/* 最近使用 */
function leastRecentlyUsed(days) {
	pager("start", "${vix}/drp/membershipCardmanagementAction!goSingleList.action?days=" + days, 'shipCard');
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_distribution_management.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#"><s:text name="cbm_membership_management" /> </a></li>
				<li><a href="#"><s:text name="drp_membership_card_management" /> </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<a href="#" onclick="saveOrUpdate(0);"><span><s:text name='cmn_add' /> </span> </a> <a href="#" onclick="batchSaveOrUpdate(0);"><span>批量开卡</span> </a>
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
		<div>
			<label>卡号:<input type="text" class="int" id="nameS" placeholder="卡号"></label><label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent(0);" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" /></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="memberShipCardList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});">${c.vipcardid}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<div class="left_content" style="height: 500px;">
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">	
			    var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/drp/distributionSystemRelationShipAction!findOrgAndUnitTreeToJson.action",
						autoParam:["id","treeType"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectTreeType").val(treeNode.treeType);
					pager("start","${vix}/drp/membershipCardmanagementAction!goSingleList.action?id="+treeNode.id+"&treeType="+treeNode.treeType+"&companyCode="+treeNode.code,"shipCard");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
				</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectTreeType" name="selectTreeType" value="${treeType}" />
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#" onclick="deleteEntitys();"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="shipCardInfo"></b> <s:text name='cmn_to' /> <b class="shipCardTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="shipCard" class="table"></div>
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
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="shipCardInfo"></b> <s:text name='cmn_to' /> <b class="shipCardTotalCount"></b>)
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