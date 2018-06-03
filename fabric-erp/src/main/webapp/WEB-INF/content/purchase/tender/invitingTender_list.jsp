<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
loadMenuContent('${vix}/purchase/purchaseMainAction!goMenuContent.action');
var name = "";
function loadName(){
	name = $('#nameS').val(); 
	name=Url.encode(name);
	name=Url.encode(name);
}
var code = "";
function loadCode(){
	code = $('#code').val(); 
	code=Url.encode(code);
	code=Url.encode(code);
}
var searchName = "";
function loadSearchName(){
	searchName = $('#searchName').val(); 
	searchName=Url.encode(searchName);
	searchName=Url.encode(searchName);
}


function deleteById(id){
	$.ajax({
		  url:'${vix}/purchase/invitingTenderAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='cmn_message'/>",function(action){
				var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
				if(null != treeNode){
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes(treeNode, "refresh");
				pager("start","${vix}/purchase/invitingTenderAction!goInvitingTenderList.action?name="+name,'purchaseTender');
			});
		  }
	});
}

function searchForRightContent(){
	loadName();
	pager("start","${vix}/purchase/invitingTenderAction!goInvitingTenderList.action?name="+name,'purchaseTender');
}

loadName();
//载入分页列表数据
pager("start","${vix}/purchase/invitingTenderAction!goInvitingTenderList.action?name="+name,'purchaseTender');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/purchase/invitingTenderAction!goInvitingTenderList.action?name=",'purchaseTender');
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
	pager("start","${vix}/purchase/invitingTenderAction!goInvitingTenderList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'purchaseTender');
}

function categoryPager(tag,entity){
	loadName();
	if(entity == 'purchaseTender'){
		pager(tag,"${vix}/purchase/invitingTenderAction!goInvitingTenderList.action?name="+name+'&parentId='+$('#selectId').val(),entity);
	}
}

bindSearch();
/*搜索*/
function searchForContent(i){
	loadName();
	loadCode();
	loadSearchName();
	if(i == 0){
		pager("start","${vix}/purchase/invitingTenderAction!goSearchList.action?i="+i+"&searchContent="+name,'purchaseTender');
	}
	else{
		pager("start","${vix}/purchase/invitingTenderAction!goSearchList.action?i="+i+"&code="+code+"&searchName="+searchName,'purchaseTender');
	}
}
//状态
function purStatus(status){
	pager("start","${vix}/purchase/invitingTenderAction!goInvitingTenderList.action?status="+status,'purchaseTender');
}
//最近使用
function purRecent(rencentDate){
	pager("start","${vix}/purchase/invitingTenderAction!goInvitingTenderList.action?updateTime="+rencentDate,'purchaseTender');
}
//发布邀请
function goInvitingTender(ptID){ 
	$.ajax({
		  url:'${vix}/purchase/invitingTenderAction!goChooseChkSupplier.action?ptID='+ptID,
		  cache: false,
		  success: function(html){
			  $(".ab_outer .list td input[type='checkbox']").css("margin-left",10);
			  $(".ab_c .content").css("margin-bottom","0");
			  $('.ab_c .content').parent().css('position','relative');
			  asyncbox.open({
				 	modal:true,
					width : 1000,
					height : 550,
					title:"选择供应商",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								returnValue = Url.encode(returnValue);
								returnValue = Url.encode(returnValue);
								$.ajax({
									  url:'${vix}/purchase/invitingTenderAction!saveInvitingTender.action?returnValue='+returnValue+"&ptID="+ptID,
									  cache: false,
									  success: function(html){
										  //$("#mainContent").html(html);
										  asyncbox.success("发布邀请成功!","提示信息");
									  }
								});
							}else{
								//asyncbox.success("发布邀请失败!","提示信息");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
//查看已邀请供应商
function showInvitingSuppliers(ptID){
	$.ajax({
		  url:'${vix}/purchase/invitingTenderAction!goInvitingSuppliers.action?ptID='+ptID,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 800,
					height : 550,
					title:"查看已邀请供应商",
					html:html,
					btnsbar : $.btn.OK
				});
		  }
	});
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/pur_tender.png" alt="" /> <s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#">采购管理</a></li>
				<li><a href="#">招标管理</a></li>
				<li><a href="#">发布招标邀请</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop" style="display: none;">
		<p>
			<a href="#" onclick="goInvitingTender(0,$('#selectId').val());"><span>发布邀请</span></a>
		</p>
	</div>
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
			<li><a href="#" id="numBtn"><img src="img/icon_10.png" alt="" /> <s:text name="cmn_index" /></a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_recently_used" /></a>
				<ul>
					<li><a href="#" onclick="purRecent('T1')"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_days" /></a></li>
					<li><a href="#" onclick="purRecent('T2')"><img alt="" src="img/icon_10.png"> <s:text name="cmn_seven_days" /></a></li>
					<li><a href="#" onclick="purRecent('T3')"><img alt="" src="img/icon_10.png"> <s:text name="cmn_month" /></a></li>
					<li><a href="#" onclick="purRecent('T4')"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_months" /></a></li>
				</ul></li>
		</ul>
		<div>
			<label>内容:<input id="nameS" name="" type="text" class="int" /></label> <label> <input id="simpleSearch" name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent(0);" /> <input id="simpleReset" name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent(0);" />
			</label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="purchaseApplyList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());">${c.name}</a></li>
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
						url:"${vix}/purchase/invitingTenderAction!findTreeToJson.action",
						autoParam:["id", "name=n", "level=lv"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdTreeId").val(treeNode.tId);
					pager("start","${vix}/purchase/invitingTenderAction!goInvitingTenderList.action?parentId="+treeNode.id,"purchaseTender");
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
					<li class="current"><a onclick="categoryTab(3,1,'a',event,'purchaseTender')"><img src="img/mail.png" alt="" />招标项目</a></li>
				</ul>
			</div>
			<div class="right_content" id="a1">
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
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','purchaseTender');"></a></span> <span><a class="previous" onclick="categoryPager('previous','purchaseTender');"></a></span> <em>(<b class="purchaseTenderInfo"></b> <s:text name='cmn_to' /> <b class="purchaseTenderTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next','purchaseTender');"></a></span> <span><a class="end" onclick="categoryPager('end','purchaseTender');"></a></span>
					</div>
				</div>
				<div id="purchaseTender" class="table"></div>
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
						<span><a class="start" onclick="categoryPager('start','purchaseTender');"></a></span> <span><a class="previous" onclick="categoryPager('previous','purchaseTender');"></a></span> <em>(<b class="purchaseTenderInfo"></b> <s:text name='cmn_to' /> <b class="purchaseTenderTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next','purchaseTender');"></a></span> <span><a class="end" onclick="categoryPager('end','purchaseTender');"></a></span>
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