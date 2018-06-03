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
		  url:'${vix}/drp/collectManagementAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 650,
					height : 300,
					title:"销售订单",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#collectBillForm').validationEngine('validate')){
							$.post('${vix}/drp/collectManagementAction!saveOrUpdate.action',
									 {
									  'collectBill.id':$("#collectBillId").val(),
									  'collectBill.customerAccount.id':$("#customerAccountId").val(),
									  'collectBill.salesOrder.id':$("#salesOrderId").val(),
									  'collectBill.receivable':$("#receivable").val(),
									  'collectBill.payment':$("#payment").val(),
									  'updateField' : updateField,
									  'collectBill.paymentTime':$("#paymentTime").val()
									},
									function(result){
										showMessage(result);
										setTimeout("", 1000);
										loadContent('${vix}/drp/collectManagementAction!goList.action');
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
//批量删除
function deleteEntitys() {
	var ids = '';
	$("[name='chkId']").each(function() {
		if ($(this).attr('checked')) {
			ids += $(this).val() + ",";
		}
	});
	$.ajax({
		url : '${vix}/drp/collectManagementAction!deleteByIds.action?ids=' + ids,
		cache : false,
		success : function(html) {
			asyncbox.success(html, "提示信息", function(action) {
				pager("start", "${vix}/drp/collectManagementAction!goSingleList.action?1=1", 'collectBill');
			});
		}
	});
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/drp/collectManagementAction!goSingleList.action?name="+name,'collectBill');
//排序 
function orderBy(orderField){
	var orderBy = $("#collectBillOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/drp/collectManagementAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy,'collectBill');
}
/*搜索*/
function searchForContent(i){
	loadName();
	pager("start","${vix}/drp/collectManagementAction!goSingleList.action?name="+name,'collectBill');
}
/**重置搜索*/
function resetForContent(i){
	$("#nameS").val("");
}
bindSearch();
function categoryPager(tag,collectBill){
	loadName();
	pager(tag,"${vix}/drp/collectManagementAction!goSingleList.action",collectBill);
}
/** 状态 */
function listbystatus(status) {
	pager("start", "${vix}/drp/collectManagementAction!goSingleList.action?status=" + status, 'collectBill');
}
/* 最近使用 */
function leastRecentlyUsed(days) {
	pager("start", "${vix}/drp/collectManagementAction!goSingleList.action?days=" + days, 'collectBill');
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
				<li><a href="#">经销商管理</a></li>
				<li><a href="#">收款管理</a></li>
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
			<label>内容:<input type="text" class="int" id="nameS" placeholder="编码"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent(0);" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" /></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<s:iterator value="collectBillList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});">${c.salesOrder.outerId}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
		<!-- left -->
		<div id="right">
			<div class="right_content">
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
						<span><a class="start" onclick="categoryPager('start','collectBill');"></a> </span> <span><a class="previous" onclick="categoryPager('previous','collectBill');"></a> </span> <em>(<b class="collectBillInfo"></b> <s:text name='cmn_to' /> <b class="collectBillTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next','collectBill');"></a> </span> <span><a class="end" onclick="categoryPager('end','collectBill');"></a> </span>
					</div>
				</div>
				<div id="collectBill" class="table"></div>
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
						<span><a class="start" onclick="categoryPager('start','collectBill');"></a> </span> <span><a class="previous" onclick="categoryPager('previous','collectBill');"></a> </span> <em>(<b class="collectBillInfo"></b> <s:text name='cmn_to' /> <b class="collectBillTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next','collectBill');"></a> </span> <span><a class="end" onclick="categoryPager('end','collectBill');"></a> </span>
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