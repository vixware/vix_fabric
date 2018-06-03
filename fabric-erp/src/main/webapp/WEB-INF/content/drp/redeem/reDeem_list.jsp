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
function saveOrUpdate(id){
	$.ajax({
		  url:'${vix}/drp/reDeemAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 660,
					height : 380,
					title:"商品信息",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							$.post('${vix}/drp/reDeemAction!saveOrUpdate.action',
								 {
								'memberShipCard.id':$("#id").val(),
								'saleOrderItem.id':$("#saleOrderItemId").val(),
								'saleOrderItem.itemCode':$("#itemcode").val(),
								'saleOrderItem.unit':$("#unit").val(),
								'saleOrderItem.price':$("#price").val(),
								'updateField' : updateField,
								'requiredPoint':$("#requiredPoint").val()
								},
								function(result){
									showMessage(result);
									setTimeout("", 1000);
									loadContent('${vix}/drp/reDeemAction!goList.action');
								}
							 );
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
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
		url : '${vix}/drp/reDeemAction!deleteByIds.action?ids=' + ids,
		cache : false,
		success : function(html) {
			asyncbox.success(html, "提示信息", function(action) {
				pager("start", "${vix}/drp/reDeemAction!goSingleList.action?1=1", 'memberShipCard');
			});
		}
	});
}

function searchForRightContent(){
	loadName();
	pager("start","${vix}/drp/reDeemAction!goSingleList.action?name="+name,'memberShipCard');
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/drp/reDeemAction!goSingleList.action?name="+name,'memberShipCard');
//排序 
function orderBy(orderField){
	var orderBy = $("#memberShipCardOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/drp/reDeemAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy,'memberShipCard');
}
bindSearch();
/** 状态 */
function listbystatus(status) {
	pager("start", "${vix}/drp/reDeemAction!goSingleList.action?status=" + status, 'memberShipCard');
}
/* 最近使用 */
function leastRecentlyUsed(days) {
	pager("start", "${vix}/drp/reDeemAction!goSingleList.action?days=" + days, 'memberShipCard');
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
				<li><a href="#"><s:text name="cbm_points_management" /> </a></li>
				<li><a href="#"><s:text name="drp_redeem" /> </a></li>
			</ul>
		</div>
	</h2>
</div>

<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /> </b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_mode" /> </a>
				<ul>
					<li><a href="#" onclick="listbystatus('0')"><img alt="" src="img/uncommitted.png"> 未提交 </a></li>
					<li><a href="#" onclick="listbystatus('1')"><img alt="" src="img/unaudited.png"> 待审批 </a></li>
					<li><a href="#" onclick="listbystatus('2')"><img alt="" src="img/verifying.png"> 审批中 </a></li>
					<li><a href="#" onclick="listbystatus('3')"><img alt="" src="img/approved.png"> 已完成 </a></li>
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
			<label>内容:<input type="text" class="int" id="nameS" placeholder="名称"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent(0);" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent();" /></label> <label> <input
				type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
		<div class="search_advanced">
			<label>编码:<input type="text" class="int" name="" id="code" placeholder="编码"></label> <label>姓名:<input type="text" class="int" name="" id="selectName" placeholder="姓名"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name="" onclick="searchForContent();"><input type="button"
				value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent();"></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<li><a href="#">111</a></li>
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="categoryList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});">${c.name}</a></li>
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
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><img src="img/mail.png" alt="" /> <s:text name="drp_redeem" /></li>
				</ul>
			</div>
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_add' /> </a></li>
								<li><a href="#"><s:text name='cmn_update' /> </a></li>
								<li><a href="#" onclick="deleteEntitys();"><s:text name='cmn_delete' /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount1">0</span> </strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','memberShipCard');"></a> </span> <span><a class="previous" onclick="categoryPager('previous','memberShipCard');"></a> </span> <em>(<b class="memberShipCardInfo"></b> <s:text name='cmn_to' /> <b class="memberShipCardTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next','memberShipCard');"></a> </span> <span><a class="end" onclick="categoryPager('end','memberShipCard');"></a> </span>
					</div>
				</div>
				<div id="memberShipCard" class="table"></div>
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
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','memberShipCard');"></a> </span> <span><a class="previous" onclick="categoryPager('previous','memberShipCard');"></a> </span> <em>(<b class="memberShipCardInfo"></b> <s:text name='cmn_to' /> <b class="memberShipCardTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next','memberShipCard');"></a> </span> <span><a class="end" onclick="categoryPager('end','memberShipCard');"></a> </span>
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