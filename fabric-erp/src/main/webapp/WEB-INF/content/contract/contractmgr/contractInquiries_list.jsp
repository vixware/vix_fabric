<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
var name = "";
var code = "";
var operator ="";
var approval ="";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function loadCode(){
	code = $('#contract_code').val();
	code=Url.encode(code);
	code=Url.encode(code);
}
function loadOperator(){
	operator = $('#contract_operator').val();
	operator=Url.encode(operator);
	operator=Url.encode(operator);
}
function loadApproval(){
	approval = $('#contract_approval').val();
	approval=Url.encode(approval);
	approval=Url.encode(approval);
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
		$("#contract_code").val("");
		$("#contract_operator").val("");
		$("#contract_approval").val("");
	}
}

$('#numBtn').click(function(){
	$('#numBtn').parent("li").toggleClass("current");
	$('#number').animate({height: 'toggle', opacity: 'toggle'},500,function(){$('#number').css('overflow','visible');});
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

function deleteById(id){
	$.ajax({
		  url:'${vix}/contract/contractDraftingAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='cmn_message'/>",function(action){
				var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
				if(null != treeNode){
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes(treeNode, "refresh");
				pager("start","${vix}/contract/contractDraftingAction!goSingleList.action?name="+name,'category');
			});
		  }
	});
}

function saveOrUpdate(id,type,pageNo){
	if(type == "1"){
		saveOrUpdate1(id,pageNo);	
	}
	else if(type == "2"){
		saveOrUpdate2(id,pageNo);
	}
	else if(type == "3"){
		saveOrUpdate3(id,pageNo);
	}
	else if(type == "4"){
		saveOrUpdate4(id,pageNo);
	}
	else if(type == "5"){
		saveOrUpdate5(id,pageNo);
	} 
	}
//采购订单
function saveOrUpdate1(id,pageNo){
	$.ajax({
		  url:'${vix}/contract/contractDraftingAction!goSaveOrUpdate.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
}

//销售订单
 function saveOrUpdate2(id,pageNo){
	$.ajax({
		  url:'${vix}/contract/contractDraftingAction!goSaveOrUpdateApply.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
} 

//采购框架协议
 function saveOrUpdate3(id,pageNo){
	$.ajax({
		  url:'${vix}/contract/contractDraftingAction!goPurchaseAgreement.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
} 

//销售框架协议
 function saveOrUpdate4(id,pageNo){
	$.ajax({
		  url:'${vix}/contract/contractDraftingAction!goSalesAgreement.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
} 

//项目合同
function saveOrUpdate5(id,pageNo){
	$.ajax({
		  url:'${vix}/contract/contractDraftingAction!goPmContract.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
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

function searchForRightContent(){
	loadName();
	pager("start","${vix}/contract/contractDraftingAction!goSingleList.action?name="+name,'category');
}
 
/*搜索*/
function searchForContent(i){
	loadName();
	loadCode();
	loadOperator();
	loadApproval();
	if(i == 0){
		pager("start","${vix}/contract/contractDraftingAction!goSearchList.action?i="+i+"&searchContent="+name,'category');
	}
	else{
		pager("start","${vix}/contract/contractDraftingAction!goSearchList.action?i="+i+"&code="+code+"&approval="+approval+"&operator="+operator,'category');
	}
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/contract/contractDraftingAction!goSingleList.action?name="+name+"&pageNo=${pageNo}",'category');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/contract/contractDraftingAction!goSingleList.action?name=",'category');
}

//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#categoryOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/contract/contractDraftingAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'category');
}

bindSearch();
bindSwitch();
var orderStatus = '';
function currentPager(tag){
	loadCode();
	pager(tag,"${vix}/contract/contractDraftingAction!goSingleList.action?name="+name+'&mode='+mode,'category');
}

$(".drop>ul>li").bind('mouseover',function(){
	$(this).children('ul').delay(400).slideDown('fast');
}).bind('mouseleave',function(){
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast');
});

/** 状态 */
function saleOrderStatus(mode){
	pager("start","${vix}/contract/contractDraftingAction!goSingleList.action?mode="+mode,'category');
}

/*最近使用  */
function leastRecentlyUsed(rencentDate){
	pager("start","${vix}/contract/contractDraftingAction!goSingleList.action?rencentDate="+rencentDate,'category');
}

function showOrder(id){
	$.ajax({
		  url:'${vix}/contract/contractDraftingAction!showOrder.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
}

//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
loadMenuContent('${vix}/contract/top/contractMainAction!goMenuContent.action');
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/ctm_contract.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="ctm_agreement" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/contract/contractDraftingAction!goList.action?pageNo=${pageNo}');"><s:text name="ctm_contract_inquiries" /> </a></li>
			</ul>
		</div>
	</h2>
	<%-- <div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate;"><span><s:text name="cmn_add"/></span></a>
			<a href="#" onclick="deleteById(0,$('#selectId').val());"><span><s:text name='cmn_delete'/></span></a>
			<a href="#" onclick="saveOrUpdate(0);"><span><s:text name='ctm_report'/></span></a>
			<a href="#" onclick="deleteByIds();"><span><s:text name='ctm_examination_and_approval'/></span></a>
			<a href="#" onclick="saveOrUpdate(0);"><span><s:text name='ctm_batch_approval'/></span></a>
			<a href="#" onclick="deleteByIds();"><span><s:text name='ctm_filing'/></span></a>
		</p>
	</div> --%>
	<div class="drop">
		<ul>
			<li><a href="#"><span>新增</span> </a>
				<ul>
					<li><a href="#" onclick="loadContent('${vix}/contract/contractDraftingAction!goSaveOrUpdate.action?contractType=1&pageNo=${pageNo}');">采购合同</a></li>
					<li><a href="#" onclick="loadContent('${vix}/contract/contractDraftingAction!goPurchaseAgreement.action?pageNo=${pageNo}');">采购框架协议</a></li>
					<li><a href="#" onclick="loadContent('${vix}/contract/contractDraftingAction!goSaveOrUpdateApply.action?contractType=3&pageNo=${pageNo}');">销售合同</a></li>
					<li><a href="#" onclick="loadContent('${vix}/contract/contractDraftingAction!goSalesAgreement.action?pageNo=${pageNo}');">销售框架协议</a></li>
					<li><a href="#" onclick="loadContent('${vix}/contract/contractDraftingAction!goPmContract.action?pageNo=${pageNo}');">项目合同</a></li>
				</ul></li>
		</ul>
	</div>
</div>

<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /> </b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> <b>帮助</b>
				</strong>
				<p>Launched in January 1998, People's Daily Online is a website built by People's Daily, the official newspaper of the Communist Party of China. Containing</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_mode" /> </a>
				<ul>
					<li><a href="#" onclick="saleOrderStatus('0')"><img alt="" src="img/uncommitted.png"> <s:text name="cmn_uncommitted" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('1')"><img alt="" src="img/unaudited.png"> <s:text name="cmn_unapproved" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('2')"><img alt="" src="img/verifying.png"> <s:text name="cmn_approval_in" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('3')"><img alt="" src="img/approved.png"> <s:text name="cmn_approval" /> </a></li>
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
			<label>填写内容:<input id="nameS" name="" type="text" class="int" /></label> <label> <input id="simpleSearch" name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent(0);" /> <input id="simpleReset" name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent(0);" />
			</label> <strong id="lb_search_advanced" onclick="changeDisplay();"><s:text name='cmn_advance_search' /> </strong>
		</div>
		<div class="search_advanced">
			<label><s:text name='ctm_theme' />:<input id="contract_code" name="" type="text" class="int" /> </label> <label><s:text name='ctm_agent' />:<input id="contract_operator" name="" type="text" class="int" /> </label> <label><s:text name='ctm_for' />:<input id="contract_approval" name="" type="text" class="int" /> </label> <label> <input
				name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent(1);" /> <input name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent(1);" />
			</label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="contractList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});">${c.contractName}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable switch" style="height: 500px; width: 7px;">
			<div id="switch_box" class="switch_btn off"></div>
			<div class="left_content current" style="height: 500px;">
				<div style="padding-left: 4px;">
					<img src="${vix}/common/img/file.png" style="padding-right: 5px;" /><a href="#" onclick="loadRoot();"><s:text name='cmn_rootDirectory' /> </a>
				</div>
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">				
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/contract/contractDraftingAction!findTreeToJson.action",
						autoParam:["id", "name=n", "level=lv"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdTreeId").val(treeNode.tId);
					pager("start","${vix}/contract/contractDraftingAction!goSingleList.action?parentId="+treeNode.id,"category");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
				</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><img src="img/mail.png" alt="" /> <s:text name="ctm_contract_inquiries" /></li>
				</ul>
			</div>
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_add' /> </a></li>
								<li><a href="#"><s:text name='cmn_update' /> </a></li>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount01">0</span> </strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','category');"></a> </span> <span><a class="previous" onclick="categoryPager('previous','category');"></a> </span> <em>(<b class="categoryInfo"></b> <s:text name='cmn_to' /> <b class="categoryTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next','category');"></a> </span> <span><a class="end" onclick="categoryPager('end','category');"></a> </span>
					</div>
				</div>
				<div id="category" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','category');"></a> </span> <span><a class="previous" onclick="categoryPager('previous','category');"></a> </span> <em>(<b class="categoryInfo"></b> <s:text name='cmn_to' /> <b class="categoryTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next','category');"></a> </span> <span><a class="end" onclick="categoryPager('end','category');"></a> </span>
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