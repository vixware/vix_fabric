<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
loadMenuContent('${vix}/contract/top/contractMainAction!goMenuContent.action');

/* 搜索功能 */
var name = "";
var contractCode ="";
var contractName ="";
var beneficiaryName ="";
var paymentPeople ="";
var aprPerson ="";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function loadContractCode(){
	contractCode = $('#ht_contractCode').val();
	contractCode = Url.encode(contractCode);
	contractCode = Url.encode(contractCode);
}
function loadContractName(){
	contractName = $('#ht_contractName').val();
	contractName = Url.encode(contractName);
	contractName = Url.encode(contractName);
}
function loadBeneficiaryName(){
	beneficiaryName = $('#ht_beneficiaryName').val();
	beneficiaryName = Url.encode(beneficiaryName);
	beneficiaryName = Url.encode(beneficiaryName);
}
function loadPaymentPeople(){
	paymentPeople = $('#ht_paymentPeople').val();
	paymentPeople = Url.encode(paymentPeople);
	paymentPeople = Url.encode(paymentPeople);
}
function loadAprPerson(){
	aprPerson = $('#ht_aprPerson').val();
	aprPerson = Url.encode(aprPerson);
	aprPerson = Url.encode(aprPerson);
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
		$("#ht_contractCode").val("");
		$("#ht_contractName").val("");
		$("#ht_beneficiaryName").val("");
		$("#ht_paymentPeople").val("");
		$("#ht_aprPerson").val("");
	}
}

/*搜索*/
function searchForContent(i){
	loadName();
	pager("start","${vix}/contract/contractPaymentsAction!goSearchList.action?i="+i+"&contractName="+name,'brand');
}

//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/contract/contractPaymentsAction!goSearch.action',
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
				pager("start", "${vix}/contract/contractPaymentsAction!!goSearchList.action?contractCode=" + $('#contractCode').val() 
						+ "&contractName=" + $('#contractName').val()
						+ "&beneficiaryName=" + $('#beneficiaryName').val()
						+ "&paymentPeople=" + $('#paymentPeople').val()
						+ "&aprPerson=" + $('#aprPerson').val(), 'brand');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};

//最近使用
function leastRecentlyUsed(paymentTime){
	pager("start","${vix}/contract/contractPaymentsAction!goSingleList.action?paymentTime="+paymentTime,'brand');
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

function saveOrUpdate(id){
	$.ajax({
		  url:"${vix}/contract/contractPaymentsAction!goSaveOrUpdate.action?id="+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 760,
					height : 360,
					title:"合同付款",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#brandForm').validationEngine('validate')){
								$.post('${vix}/contract/contractPaymentsAction!saveOrUpdate.action',
									 {'contractPaymentPlan.id':$("#id").val(),
									  'contractPaymentPlan.contractCode':$("#contractCode").val(),
									  'contractPaymentPlan.contractName':$("#contractName").val(),
									  'contractPaymentPlan.beneficiaryName':$("#beneficiaryName").val(),
									  'contractPaymentPlan.beneficiaryAccount':$("#beneficiaryAccount").val(),
									  'contractPaymentPlan.paymentAmount':$("#paymentAmount").val(),
									  'contractPaymentPlan.paymentTime':$("#paymentTime").val(),
									  'contractPaymentPlan.paymentPeople':$("#paymentPeople").val(),
									  'contractPaymentPlan.amount':$("#amount").val(),
									  'contractPaymentPlan.aprPerson':$("#aprPerson").val(),
									  'contractPaymentPlan.aprTime':$("#aprTime").val(),
									  'contractPaymentPlan.status':$("#status").val()						  
									},
									function(result){
										asyncbox.open({
											modal:true,
											width : 320,
											height : 50,
											title:"<s:text name='合同付款'/>",
											html:result,
											callback : function(action){
												pager("current","${vix}/contract/contractPaymentsAction!goSingleList.action?name="+name,'brand');
											},
											btnsbar : $.btn.OKCANCEL
										});
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

$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});

function deleteById(id){
	$.ajax({
		  url:'${vix}/contract/contractPaymentsAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='合同付款'/>",function(action){
				var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
				if(null != treeNode){
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes(treeNode, "refresh");
				pager("start","${vix}/contract/contractPaymentsAction!goSingleList.action?name="+name,'brand');
			});
		  }
	});
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/contract/contractPaymentsAction!goSingleList.action?name="+name,'brand');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#brandOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/contract/contractPaymentsAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name,'brand');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/contract/contractPaymentsAction!goSingleList.action?name="+name,'brand');
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
loadMenuContent('${vix}/contract/top/contractMainAction!goMenuContent.action');
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/ctm_contract.png" alt="" /> <s:text name="cmn_supplyChain" /> </a></li>
				<li><a href="#"><s:text name="ctm_agreement" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/contract/contractPaymentsAction!goList.action?pageNo=${pageNo}');"><s:text name="ctm_contract_payments" /></a></li>
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
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
			<li class="fly"><a href="#"><img alt="" src="img/leastRecentlyUsed.png"> <s:text name="最近付款合同" /> </a>
				<ul>
					<li><a href="#" onclick="leastRecentlyUsed('THREEDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('SEVENDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('ONEMONTH');"><img alt="" src="img/time_go.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('THREEMONTHS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div>
			<label>合同名称: <input type="text" class="int" id="nameS" style="width: 150px;"> <input type="button" value="搜索" class="btn" onclick="searchForContent(0);" />
			</label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" />
			</label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="contractPaymentPlanList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());"><span style="display: none;">${c.chineseFirstLetter}</span>${c.contractName}</a></li>
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
				<div id="brand" class="table"></div>
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
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>