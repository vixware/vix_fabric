<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
loadMenuContent('${vix}/contract/top/contractMainAction!goMenuContent.action');

/* 搜索功能 */
var name = "";
var masterContractCoding = "";
var contractCode ="";
var contractName ="";
var projectCode ="";
var projectName ="";
var departmentName ="";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function loadMasterContractCoding(){
	masterContractCoding = $('#ht_masterContractCoding').val();
	masterContractCoding = Url.encode(masterContractCoding);
	masterContractCoding = Url.encode(masterContractCoding);
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
function loadProjectCode(){
	projectCode = $('#ht_projectCode').val();
	projectCode = Url.encode(projectCode);
	projectCode = Url.encode(projectCode);
}
function loadProjectName(){
	projectName = $('#ht_projectName').val();
	projectName = Url.encode(projectName);
	projectName = Url.encode(projectName);
}
function loadDepartmentName(){
	departmentName = $('#ht_departmentName').val();
	departmentName = Url.encode(departmentName);
	departmentName = Url.encode(departmentName);
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
		$("#ht_masterContractCoding").val("");
		$("#ht_contractCode").val("");
		$("#ht_contractName").val("");
		$("#ht_projectCode").val("");
		$("#ht_projectName").val("");
		$("#ht_departmentName").val("");
	}
}

/*搜索*/
function searchForContent(i){
	loadName();
	pager("start","${vix}/contract/todoContractsAction!goSearchList.action?i="+i+"&contractName="+name,'category');
}

//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/contract/todoContractsAction!goSearch.action',
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
				pager("start", "${vix}/contract/todoContractsAction!!goSearchList.action?masterContractCoding=" + $('#masterContractCoding').val() 
						+ "&contractCode=" + $('#contractCode').val()
						+ "&contractName=" + $('#contractName').val()
						+ "&projectCode=" + $('#projectCode').val()
						+ "&projectName=" + $('#projectName').val()
						+ "&departmentName=" + $('#departmentName').val(), 'category');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};

//类型
function saleOrderStatus(contractType){
	pager("start","${vix}/contract/todoContractsAction!goSingleList.action?contractType="+contractType,'category');
}
//最近使用
function leastRecentlyUsed(contractStartTime){
	pager("start","${vix}/contract/todoContractsAction!goSingleList.action?contractStartTime="+contractStartTime,'category');
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
},
function(){
	$(this).removeClass("btnhover");
});


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
function saveOrUpdate1(id,pageNo){
	$.ajax({
		  url:'${vix}/contract/todoContractsAction!goSaveOrUpdate.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
};

function saveOrUpdate2(id,pageNo){
	$.ajax({
		  url:'${vix}/contract/todoContractsAction!goPurchaseFA.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
};

function saveOrUpdate3(id,pageNo){
	$.ajax({
		  url:'${vix}/contract/todoContractsAction!goSalesContract.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
};

function saveOrUpdate4(id,pageNo){
	$.ajax({
		  url:'${vix}/contract/todoContractsAction!goContractMarket.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
};

function saveOrUpdate5(id,pageNo){
	$.ajax({
		  url:'${vix}/contract/todoContractsAction!goPmContract.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
};

/* 控制发布或者终止 */
function updateIsPublish(id,isPublish){
	var param={'id':id};  
	$.ajax({ 
		url:"${vix}/contract/todoContractsAction!updateIsPublish.action",
		data:param,
		dataType:"text",
		success:function(data){
			/* alert('修改成功'); */
			loadName();
			pager("start","${vix}/contract/todoContractsAction!goSingleList.action?name="+name,'category');
		}
		});
}

function searchForRightContent(){
	loadName();
	pager("start","${vix}/contract/todoContractsAction!goSingleList.action?name="+name,'category');
}

loadName();
//载入分页列表数据
pager("start","${vix}/contract/todoContractsAction!goSingleList.action?name="+name+"&orderField=id&orderBy=desc",'category');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/contract/todoContractsAction!goSingleList.action?name=",'category');
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
	pager("start","${vix}/contract/todoContractsAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'category');
}

function categoryPager(tag,entity){
	loadName();
	if(entity == 'category'){
		pager(tag,"${vix}/contract/todoContractsAction!goSingleList.action?name="+name+'&parentId='+$('#selectId').val(),entity);
	}
}

bindSearch();
bindSwitch();
function categoryTab(num,befor,id,e,entity){
	var el=e.target?e.target.parentNode:e.srcElement.parentNode;
	var pa=el.parentNode.getElementsByTagName("li");
	for(var i=0;i<pa.length;i++){
		pa[i].className="";
	}
	el.className="current";
	for(i=1;i<=num;i++){
		try{
			if(i==befor){
				document.getElementById(id+i).style.display="block";
			}else{
				document.getElementById(id+i).style.display="none";
			}
		}catch(e){ }
	}
	if(entity != undefined){
		categoryPager('start',entity);
	}
}
$(".drop>ul>li").bind('mouseover',function(){
	$(this).children('ul').delay(400).slideDown('fast');
}).bind('mouseleave',function(){
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast');
});
//面包屑
if($('.sub_menu').length)
{
	$("#breadCrumb").jBreadCrumb();
}

</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/ctm_contract.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/contract/todoContractsAction!goList.action?pageNo=${pageNo}');"><s:text name="ctm_agreement" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/contract/todoContractsAction!goList.action?pageNo=${pageNo}');"><s:text name="待办合同" /> </a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="类型" /> </a>
				<ul>
					<li><a href="#" onclick="saleOrderStatus('1')"><img alt="" src="img/uncommitted.png"> <s:text name="采购合同" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('2')"><img alt="" src="img/unaudited.png"> <s:text name="销售合同" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('3')"><img alt="" src="img/unaudited.png"> <s:text name="采购框架协议" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('4')"><img alt="" src="img/unaudited.png"> <s:text name="销售框架协议" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('5')"><img alt="" src="img/unaudited.png"> <s:text name="项目合同" /> </a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/leastRecentlyUsed.png"> <s:text name="最近合同" /> </a>
				<ul>
					<li><a href="#" onclick="leastRecentlyUsed('THREEDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('SEVENDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('ONEMONTH');"><img alt="" src="img/time_go.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('THREEMONTHS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<!-- 页面左上角按钮部分 -->
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
			<s:iterator value="contractInquiryList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());"><span style="display: none;">${c.chineseFirstLetter}</span>${c.contractName}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable switch" style="height: 500px; width: 7px;">
			<div id="switch_box" class="switch_btn off"></div>
			<div class="left_content current" style="height: 500px;">
				<div style="padding-left: 4px;">
					<img src="${vix}/common/img/file.png" style="padding-right: 5px;" /><a href="#" onclick="loadRoot();"><s:text name='cmn_rootDirectory' /></a>
				</div>
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/contract/todoContractsAction!findTreeToJson.action",
						autoParam:["id", "name=n", "level=lv"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdTreeId").val(treeNode.tId);
					pager("start","${vix}/contract/todoContractsAction!goSingleList.action?parentId="+treeNode.id,"category");
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
					<li class="current"><a onclick="categoryTab(3,1,'a',event,'category')"><img src="img/common_listx16.png" alt="" />待办合同</a></li>
				</ul>
			</div>
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /></a>
							<ul>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','category');"></a></span> <span><a class="previous" onclick="categoryPager('previous','category');"></a></span> <em>(<b class="categoryInfo"></b> <s:text name='cmn_to' /> <b class="categoryTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next','category');"></a></span> <span><a class="end" onclick="categoryPager('end','category');"></a></span>
					</div>
				</div>
				<div id="category" class="table"></div>
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
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','category');"></a></span> <span><a class="previous" onclick="categoryPager('previous','category');"></a></span> <em>(<b class="categoryInfo"></b> <s:text name='cmn_to' /> <b class="categoryTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next','category');"></a></span> <span><a class="end" onclick="categoryPager('end','category');"></a></span>
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