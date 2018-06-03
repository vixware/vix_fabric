<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var buyerNick = "";
function loadBuyerNick(){
	buyerNick = $('#buyerNick').val();
	buyerNick=Url.encode(buyerNick);
	buyerNick=Url.encode(buyerNick);
}
var receiverMobile = "";
function loadReceiverMobile(){
	receiverMobile = $('#receiverMobile').val();
	receiverMobile=Url.encode(receiverMobile);
	receiverMobile=Url.encode(receiverMobile);
}
//载入分页列表数据
pager("start","${vix}/crm/business/membershipIntegrationAction!goSingleList.action?1=1",'customer');
//排序 
function orderBy(orderField){
	var orderBy = $("#customerOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/crm/business/membershipIntegrationAction!goSingleList1.action?orderField="+orderField+"&orderBy="+orderBy,'customer');
}

function currentPager(tag){
	pager(tag,"${vix}/crm/business/membershipIntegrationAction!goSingleList1.action?1=1",'customer');
}
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
function categoryPager(tag,entity){
	if(entity == 'customer'){
		pager(tag,"${vix}/crm/business/membershipIntegrationAction!goSingleList.action?1=1",entity);
	}
	if(entity == 'customer1'){
		pager(tag,"${vix}/crm/business/membershipIntegrationAction!goSingleList2.action?1=1",entity);
	}
	if(entity == 'latestOperateEntity'){
		pager(tag,"${vix}/crm/business/membershipIntegrationAction!goSingleList1.action?1=1",entity);
	}
}

function searchForContent(){
	loadBuyerNick();
	loadReceiverMobile();
    pager("start","${vix}/crm/business/membershipIntegrationAction!goSingleList2.action?buyerNick="+buyerNick+"&receiverMobile="+$("#receiverMobile").val(),'customer1');
}

function resetForContent(){
	$("#buyerNick").val("");
	$("#receiverMobile").val("");
}

var businussOrderId = "";
function chkChange(chk, id) {
	if (chk.checked) {
		businussOrderId = businussOrderId + "," + id;
	}
} 
//会员同步
function goSyncCustomerAccount(){
	$.ajax({
	  url:'${vix}/crm/business/membershipIntegrationAction!goSyncCustomerAccount.action',
	  cache: false,
	  success: function(result){
		  showMessage(result);
		  setTimeout("", 1000);
		  loadContent('${vix}/crm/business/membershipIntegrationAction!goList.action');
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
				<li><a href="#"><img src="${vix}/common/img/crm/customer.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">会员交互管理</a></li>
				<li><a href="#">会员管理 </a></li>
				<li><a href="#">会员整合管理 </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<a href="#" onclick="goSyncCustomerAccount(0);"><span>会员整合 </span> </a>
	</div>
</div>
<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img alt="" src="img/icon_11.png"> </b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> <b>帮助</b>
				</strong>
				<p>会员整合</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
		</ul>
		<div>
			<label>客户姓名:<input type="text" class="int" id="buyerNick"></label> <label>手机号码:<input type="text" class="int" id="receiverMobile"></label> <label><input id="simpleSearch" type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();"> <input id="simpleReset" type="button"
				value="<s:text name='cmn_reset'/>" class="btn" onclick="resetForContent()"> </label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="salesOrderList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});">${c.code}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><a onclick="categoryTab(5,1,'a',event,'customer')"><img src="img/holidaysam.png" alt="" />待整合</a></li>
					<li><a onclick="categoryTab(5,2,'a',event,'customer1')"><img src="img/adjust_view_day.png" alt="" />会员列表</a></li>
					<li><a onclick="categoryTab(5,3,'a',event,'latestOperateEntity')"><img src="img/adjust_view_day.png" alt="" />整合日志</a></li>
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
						<span><a class="start" onclick="categoryPager('start','customer');"></a></span> <span><a class="previous" onclick="categoryPager('previous','customer');"></a></span> <em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name='cmn_to' /> <b class="customerTotalCount">${pager.totalCount}</b>)
						</em> <span><a class="next" onclick="categoryPager('next','customer');"></a></span> <span><a class="end" onclick="categoryPager('end','customer');"></a></span>
					</div>
				</div>
				<div id="customer" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name="cmn_choose" /></a>
							<ul>
								<li><a href="#"><s:text name="cmn_delete" /></a></li>
								<li><a href="#"><s:text name="cmn_email" /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectBrandCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','customer');"></a></span> <span><a class="previous" onclick="categoryPager('previous','customer');"></a></span> <em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name="cmn_to" /> <b class="customerTotalCount">${pager.totalCount}</b>)
						</em> <span><a class="next" onclick="categoryPager('next','customer');"></a></span> <span><a class="end" onclick="categoryPager('end','customer');"></a></span>
					</div>
				</div>
			</div>
			<div class="right_content" id="a2" style="display: none;">
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
					<strong><s:text name="cmn_selected" />:<span id="selectBrandCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','customer1');"></a></span> <span><a class="previous" onclick="categoryPager('previous','customer1');"></a></span> <em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name='cmn_to' /> <b class="customer1TotalCount">${pager.totalCount}</b>)
						</em> <span><a class="next" onclick="categoryPager('next','customer1');"></a></span> <span><a class="end" onclick="categoryPager('end','customer1');"></a></span>
					</div>
				</div>
				<div id="customer1" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name="cmn_choose" /></a>
							<ul>
								<li><a href="#"><s:text name="cmn_delete" /></a></li>
								<li><a href="#"><s:text name="cmn_email" /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectBrandCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','customer1');"></a></span> <span><a class="previous" onclick="categoryPager('previous','customer1');"></a></span> <em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name="cmn_to" /> <b class="customer1TotalCount">${pager.totalCount}</b>)
						</em> <span><a class="next" onclick="categoryPager('next','customer1');"></a></span> <span><a class="end" onclick="categoryPager('end','customer1');"></a></span>
					</div>
				</div>
			</div>
			<div class="right_content" id="a3" style="display: none;">
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
					<strong><s:text name="cmn_selected" />:<span id="selectBrandCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','latestOperateEntity');"></a></span> <span><a class="previous" onclick="categoryPager('previous','latestOperateEntity');"></a></span> <em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name='cmn_to' /> <b
							class="latestOperateEntityTotalCount">${pager.totalCount}</b>)
						</em> <span><a class="next" onclick="categoryPager('next','latestOperateEntity');"></a></span> <span><a class="end" onclick="categoryPager('end','latestOperateEntity');"></a></span>
					</div>
				</div>
				<div id="latestOperateEntity" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name="cmn_choose" /></a>
							<ul>
								<li><a href="#"><s:text name="cmn_delete" /></a></li>
								<li><a href="#"><s:text name="cmn_email" /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectBrandCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','latestOperateEntity');"></a></span> <span><a class="previous" onclick="categoryPager('previous','latestOperateEntity');"></a></span> <em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name="cmn_to" /> <b
							class="latestOperateEntityTotalCount">${pager.totalCount}</b>)
						</em> <span><a class="next" onclick="categoryPager('next','latestOperateEntity');"></a></span> <span><a class="end" onclick="categoryPager('end','latestOperateEntity');"></a></span>
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
