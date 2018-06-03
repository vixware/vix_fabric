<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
loadMenuContent('${vix}/inventory/inventoryMainAction!goMenuContent.action');
/* 内容 */
var name = "";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function saveOrUpdate(id,pageNo){
	$.ajax({
		  url:'${vix}/crm/business/membershipSubdivisionAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
};

function goCustomerList(id,name){
	$.ajax({
		  url:'${vix}/crm/business/membershipSubdivisionAction!goCustomerList.action?id='+id+"&name="+name,
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
};
function searchForContent(){
	loadName();
    pager("start","${vix}/crm/business/membershipSubdivisionAction!goSingleList.action?name="+name,'membershipSubdivision');
}

function resetForContent(){
	$("#nameS").val("");
}
//载入分页列表数据
pager("start","${vix}/crm/business/membershipSubdivisionAction!goSingleList.action?1=1",'membershipSubdivision');
//排序 
function orderBy(orderField){
	var orderBy = $("#membershipSubdivisionOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/crm/business/membershipSubdivisionAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy,'membershipSubdivision');
}

function currentPager(tag,entity){
	pager(tag,"${vix}/crm/business/membershipSubdivisionAction!goSingleList.action?1=1",entity);
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/customer.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">会员交互管理 </a></li>
				<li><a href="#">营销中心 </a></li>
				<li><a href="#">智能营销 </a></li>
				<li><a href="#">会员细分管理 </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0);"><span><s:text name='cmn_add' /> </span> </a>
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
			<li><a href="#" id="numBtn"><img src="img/icon_10.png" alt="" /> <s:text name="cmn_index" /></a></li>
		</ul>
		<div>
			<label>主题:<input type="text" class="int" id="nameS"></label> <label><input id="simpleSearch" type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();"> <input id="simpleReset" type="button" value="<s:text name='cmn_reset'/>" class="btn" onclick="resetForContent()"> </label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="wimTransvouchList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});">${c.tvcode}</a></li>
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
						url:"${vix}/business/itemDownLoadAction!findStoreTypeTreeToJson.action",
						autoParam:["id","treeType"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectTreeType").val(treeNode.treeType);
					pager("start","${vix}/crm/business/membershipSubdivisionAction!goSingleList.action?parentId="+treeNode.id+"&treeType="+treeNode.treeType,"membershipSubdivision");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
				</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
			<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectTreeType" name="selectTreeType" value="${treeType}" />
		</div>
		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><a onclick="categoryTab(5,1,'a',event,'membershipSubdivision')"><img src="img/holidaysam.png" alt="" />客户细分列表</a></li>
				</ul>
			</div>
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /></a>
							<ul>
								<li><a href="#" onclick="deleteEntitys();"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectBrandCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start','membershipSubdivision');"></a></span> <span><a class="previous" onclick="currentPager('previous','membershipSubdivision');"></a></span> <em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name='cmn_to' /> <b
							class="membershipSubdivisionTotalCount">${pager.totalCount}</b>)
						</em> <span><a class="next" onclick="currentPager('next','membershipSubdivision');"></a></span> <span><a class="end" onclick="currentPager('end','membershipSubdivision');"></a></span>
					</div>
				</div>
				<div id="membershipSubdivision" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name="cmn_choose" /></a>
							<ul>
								<li><a href="#"><s:text name="cmn_delete" /></a></li>
								<li><a href="#">邮件</a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectBrandCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start','membershipSubdivision');"></a></span> <span><a class="previous" onclick="currentPager('previous','membershipSubdivision');"></a></span> <em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name="cmn_to" /> <b
							class="membershipSubdivisionTotalCount">${pager.totalCount}</b>)
						</em> <span><a class="next" onclick="currentPager('next','membershipSubdivision');"></a></span> <span><a class="end" onclick="currentPager('end','membershipSubdivision');"></a></span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>