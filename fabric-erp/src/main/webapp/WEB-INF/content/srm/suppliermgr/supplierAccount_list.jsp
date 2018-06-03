<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
loadMenuContent('${vix}/purchase/purchaseMainAction!goMenuContent.action');
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
function saveOrUpdate(id,pageNo){
	$.ajax({
		  url:'${vix}/srm/supplierAccountAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 800,
					height : 400,
					title:"新增供应商帐号",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#supplierAccount').validationEngine('validate')){
								$.post('${vix}/srm/supplierAccountAction!saveOrUpdate.action', {
										'supplierAccount.id' : $("#newId").val(),
										'supplierAccount.account' : $("#newAccount").val(),
										'supplierAccount.password' : $("#newPassword").val(),
										'supplierAccount.startTime' : $("#newStartTime").val(),
										'supplierAccount.endTime' : $("#newEndTime").val(),
										'supplierAccount.enable' : $("#newEnable").val(),
										'supplierAccount.employee.id' : $("#newEId").val()
										}, function(result){
											showMessage(result);
											setTimeout("",1000);
											//载入分页列表数据
											//pager("start","${vix}/srm/supplierAccountAction!goSingleList.action?code="+code+"&pageNo=${pageNo}",'category');
											loadContent('${vix}/srm/supplierAccountAction!goList.action');
										});
							}else{
			  					return false;
			  				}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

function deleteByIds(){
	var ids = '';
	$("[name='chkId']").each(function(){  
		if($(this).attr('checked')){
			ids+=$(this).val()+",";  
		}
	});
	asyncbox.success(ids,"选中的id");
}

//删除
function deleteById(id){
	$.ajax({
		  url:'${vix}/srm/supplierAccountAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='cmn_message'/>",function(action){
				pager("start","${vix}/srm/supplierAccountAction!goSingleList.action?name="+name,'brand');
			});
		  }
	});
}

function searchForContent(){
	loadName();
	pager("start","${vix}/srm/supplierAccountAction!goSingleList.action?name="+name,'brand');
}
/*搜索*/
function searchForContent(i){
	loadName();
	loadPurchaseName();
	loadPurchasePerson();
	if(i == 0){
		pager("start","${vix}/srm/supplierAccountAction!goSearchList.action?i="+i+"&searchContent="+name,'brand');
	}
	else{
		pager("start","${vix}/srm/supplierAccountAction!goSearchList.action?i="+i+"&purchasePerson="+purchasePerson+"&name="+purchaseName,'brand');
	}
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
//状态
function purStatus(status){
	pager("start","${vix}/srm/supplierAccountAction!goSingleList.action?status="+status,'brand');
}
//最近使用
function purRecent(rencentDate){
	pager("start","${vix}/srm/supplierAccountAction!goSingleList.action?updateTime="+rencentDate,'brand');
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/srm/supplierAccountAction!goSingleList.action?name="+name,'brand');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#brandOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/srm/supplierAccountAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name,'brand');
}

function currentPager(tag){
	loadName();
	pager(tag,"${vix}/srm/supplierAccountAction!goSingleList.action?name="+name,'brand');
}

</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/srm_supplier.png" alt="" /> <s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#"><s:text name="srm_supp_rela_manage" /></a></li>
				<li><a href="#"><s:text name='srm_supp_manage' /></a></li>
				<li><a href="#">供应商帐号</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0,$('#selectId').val());"><span><s:text name='cmn_add' /></span></a>
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
			<label>搜索内容:<input id="nameS" name="" type="text" class="int" /></label> <label> <input id="simpleSearch" name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent(0);" /> <input id="simpleReset" name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent(0);" />
			</label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="purchaseInquireList" var="p">
				<li><a href="#" onclick="saveOrUpdate(${p.id});">${p.name}</a></li>
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
								<li><a href="#"><s:text name='email' /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="brandInfo"></b> <s:text name='cmn_to' /> <b class="brandTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="brand" class="table" style="overflow-x: auto; width: 100%;"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='choose' /></a>
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