<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
var name = "";
var purchaseName = "";
var purchasePerson = "";
function loadName(){
	name = $('#nameS').val(); 
	name=Url.encode(name);
	name=Url.encode(name);
}
function loadPurchaseName(){
	purchaseName = $('#purchaseName').val(); 
	purchaseName=Url.encode(purchaseName);
	purchaseName=Url.encode(purchaseName);
}
function loadPurchasePerson(){
	purchasePerson = $('#purchasePerson').val(); 
	purchasePerson=Url.encode(purchasePerson);
	purchasePerson=Url.encode(purchasePerson);
}
$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});
function chooseCondition(){
	$.ajax({
		url:'${vix}/sales/commission/commissionResultAction!goChooseCondition.action',
		cache: false,
		success: function(html){
			asyncbox.open({
			 	modal:true,
				width : 640,
				height : 200,
				title:"选择佣金条件",
				html:html,
				callback : function(action,returnValue){
					if(action == 'ok'){
						if($('#ccfcForm').validationEngine('validate')){
							$.ajax({
								url:'${vix}/sales/commission/commissionResultAction!calculateSaleReturnBill.action?customerAccountId='+$("#customerAccountId").val()+"&startTime="+$("#startTime").val()+"&endTime="+$("#endTime").val(),
								cache: false,
								success: function(html){
									showMessage(result);
									setTimeout("",1000);
									loadContent('${vix}/sales/commission/commissionResultAction!goList.action');
								}
							});
						}
					}
				},
				btnsbar : $.btn.OKCANCEL
			});
		}
	});
};

//删除
function deleteById(id){
	$.ajax({
		  url:'${vix}/sales/commission/commissionResultAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='cmn_message'/>",function(action){
				var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
				if(null != treeNode){
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes(treeNode, "refresh");
				pager("start","${vix}/sales/commission/commissionResultAction!goListContent.action?name="+name,'category');
			});
		  }
	});
}

function searchForRightContent(){
	loadName();
	pager("start","${vix}/sales/commission/commissionResultAction!goListContent.action?name="+name,'category');
}

loadName();
//载入分页列表数据
pager("start","${vix}/sales/commission/commissionResultAction!goListContent.action?name="+name,'category');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/sales/commission/commissionResultAction!goListContent.action?name=",'category');
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
	pager("start","${vix}/sales/commission/commissionResultAction!goListContent.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'category');
}

function categoryPager(tag,entity){
	loadName();
	if(entity == 'category'){
		pager(tag,"${vix}/sales/commission/commissionResultAction!goListContent.action?name="+name+'&parentId='+$('#selectId').val(),entity);
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
/*搜索*/
function searchForContent(i){
	loadName();
	loadPurchaseName();
	loadPurchasePerson();
	if(i == 0){
		pager("start","${vix}/sales/commission/commissionResultAction!goSearchList.action?i="+i+"&searchContent="+name,'category');
	}
	else{
		pager("start","${vix}/sales/commission/commissionResultAction!goSearchList.action?i="+i+"&purchasePerson="+purchasePerson+"&purchasePlanName="+purchaseName,'category');
	}
}
/*重置搜索*/
function resetForContent(i){
	if(i == 0){
		$("#nameS").val("");
	}
	else{
		$("#purchasePerson").val("");
		$("#purchaseName").val("");
	}
}
/*改变搜索按钮的显示*/
function changeDisplay(){
	var divText = $("#lb_search_advanced").text();
	if(divText == "高级搜索"){
		$("#nameS").attr({disabled:'true'});
		$("#simpleSerach").hide();
		$("#simpleReset").hide();
	}
	else{
		$("#nameS").removeAttr("disabled");
		$("#simpleSerach").show();
		$("#simpleReset").show();
	}
}
//状态
function purStatus(status){
	pager("start","${vix}/sales/commission/commissionResultAction!goListContent.action?status="+status,'category');
}
//最近使用
function purRecent(rencentDate){
	pager("start","${vix}/sales/commission/commissionResultAction!goListContent.action?updateTime="+rencentDate,'category');
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
				<li><a href="#"><img src="img/pur_plan.png" alt="" /> <s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#">销售管理</a></li>
				<li><a href="#">销售佣金管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/commission/commissionResultAction!goList.action');">佣金计算</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="chooseCondition();"><span><s:text name="cmn_add" /></span></a>
		</p>
	</div>
</div>

<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b> </b>
				</strong>
				<p></p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/icon_10.png" alt="" />
				<s:text name="cmn_index" /></a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png">
				<s:text name="cmn_mode" /></a>
				<ul>
					<li><a href="#" onclick="purStatus('S1')"><img alt="" src="${vix}/common/img/icon_10.png">待确认</a></li>
					<li><a href="#" onclick="purStatus('S2')"><img alt="" src="${vix}/common/img/icon_10.png">审批中</a></li>
					<li><a href="#" onclick="purStatus('S3')"><img alt="" src="${vix}/common/img/icon_10.png">已发货</a></li>
					<li><a href="#" onclick="purStatus('S4')"><img alt="" src="${vix}/common/img/icon_10.png">已完成</a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png">
				<s:text name="cmn_recently_used" /></a>
				<ul>
					<li><a href="#" onclick="purRecent('T1')"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_three_days" /></a></li>
					<li><a href="#" onclick="purRecent('T2')"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_seven_days" /></a></li>
					<li><a href="#" onclick="purRecent('T3')"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_month" /></a></li>
					<li><a href="#" onclick="purRecent('T4')"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_three_months" /></a></li>
				</ul></li>
		</ul>
		<div>
			<label>搜索内容:<input id="nameS" name="" type="text" class="int" /></label> <label> <input id="simpleSearch" name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent(0);" /> <input id="simpleReset" name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent(0);" />
			</label> <strong id="lb_search_advanced" onclick="changeDisplay();"><s:text name='cmn_advance_search' /></strong>
		</div>
		<div class="search_advanced">
			<label>主题:<input id="purchaseName" name="" type="text" class="int" /></label> <label>业务员:<input id="purchasePerson" name="" type="text" class="int" /></label> <label> <input name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent(1);" /> <input name="" type="button" class="btn"
				value="<s:text name='cmn_reset'/>" onclick="resetForContent(1);" />
			</label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<div class="left_content" style="height: 500px;">
				<div style="padding-left: 4px;">
					<img src="${vix}/common/img/file.png" style="padding-right: 5px;" /><input type="checkbox" /> <a href="#" onclick="loadRoot();"><s:text name='cmn_rootDirectory' /></a>
				</div>
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/sales/commission/commissionResultAction!findTreeToJson.action",
						autoParam:["id", "name=n", "level=lv"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdTreeId").val(treeNode.tId);
					pager("start","${vix}/sales/commission/commissionResultAction!goCalculationList.action?parentId="+treeNode.id,"category");
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
					<li class="current"><a onclick="categoryTab(3,1,'a',event,'category')"><img src="img/mail.png" alt="" />计算列表</a></li>
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