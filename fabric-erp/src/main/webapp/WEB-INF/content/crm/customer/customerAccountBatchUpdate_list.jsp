<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});

function searchForRightContent(){
	pager("start","${vix}/crm/customer/crmCustomerAccountBatchAction!goBatchUpdateContent.action?name=",'batchUpdateCustomerAccount');
}

//载入分页列表数据
pager("start","${vix}/crm/customer/crmCustomerAccountBatchAction!goBatchUpdateContent.action?name="+"&pageNo=${pageNo}",'batchUpdateCustomerAccount');

function accountPager(tag){
	pager(tag,"${vix}/crm/customer/crmCustomerAccountBatchAction!goBatchUpdateContent.action?name="+'&parentId='+$('#selectId').val(),'batchUpdateCustomerAccount');
}
function batchUpdateCustomerAccount(){
	$("input[type=text][id='code']").each(function(){
		if($(this).val() != ""){
			var i = $(this).parent().parent().attr("id");
			var id = $($($("#"+i).children("td")[0]).children("input")[0]).val();
			var code = $(this).val();
			var name = $($($("#"+i).children("td")[2]).children("input")[0]).val();
			var shorterName = $($($("#"+i).children("td")[3]).children("input")[0]).val();
			var hotLevelId = $($($("#"+i).children("td")[4]).find("select")[0]).val();
			var customerTypeId = $($($("#"+i).children("td")[5]).find("select")[0]).val();
			var relationshipClassId = $($($("#"+i).children("td")[6]).find("select")[0]).val();
			var customerSourceId = $($($("#"+i).children("td")[7]).find("select")[0]).val();
			var customerStageId = $($($("#"+i).children("td")[8]).find("select")[0]).val();
			var customerType = $($($("#"+i).children("td")[9]).find("select")[0]).val();
			$.post('${vix}/crm/customer/crmCustomerAccountBatchAction!batchUpdate.action',
					{'customerAccount.id':id,
					  'customerAccount.code':code,
					  'customerAccount.name':name,
					  'customerAccount.type':customerType,
					  'customerAccount.shorterName':shorterName,
					  'customerAccount.customerType.id':customerTypeId,
					  'customerAccount.relationshipClass.id':relationshipClassId,
					  'customerAccount.hotLevel.id':hotLevelId,
					  'customerAccount.customerSource.id':customerSourceId,
					  'customerAccount.customerStage.id':customerStageId
					},
					function(result){
						showMessage(result);
					}
				 );
		}else{
			return;
		}
	});
	pager("start","${vix}/crm/customer/crmCustomerAccountBatchAction!goBatchUpdateContent.action?name="+"&pageNo=${pageNo}",'batchUpdateCustomerAccount');
}
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
				<li><a href="#"><img src="${vix}/common/img/crm/customer.png" alt="" />供应链</a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#">客户管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/customer/crmCustomerAccountBatchAction!goBatchUpdateList.action');">批量编辑客户</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_head -->
	<div class="box">
		<div class="fllist" style="padding: 10px 10px 20px;">
			<p>
				<strong><img class="icon" src="img/list_icon_24.gif">数据分类：</strong><a href="#">·全部数据</a><a href="#">·潜在</a><a href="#">·普通</a><a href="#">·VIP</a><a href="#">·代理</a><a href="#">·合作</a><a href="#">·失效</a><a href="#">·热点客户</a><a href="#">·历史</a><a href="#">·15天未跟踪</a><a href="#">·30天未跟踪</a><a href="#">·60天未跟踪</a><a href="#">·100天未跟踪</a><br />&nbsp;
			</p>
			<p>
				<strong><img class="icon" src="img/list_icon_24.gif">快速查询：</strong>&nbsp;客户名称：<input name="" type="text" class="ipt" value="" /> <input name="" type="button" class="btn" value="查询" /> 客户编号：<input name="" type="text" class="ipt" value="" /> <input name="" type="button" class="btn" value="查询" />
			</p>
		</div>
		<div class="addtitle">
			<span class="l"><img width="16" height="16" style="vertical-align: middle" src="img/address_book.png">&nbsp;客户基本信息</span>
		</div>
		<div id="batchUpdateCustomerAccount" class="table"></div>
		<div class="pagelist drop">
			<div>
				<span><a class="start" onclick="accountPager('start');"></a></span> <span><a class="previous" onclick="accountPager('previous');"></a></span> <em>(<b class="batchUpdateCustomerAccountInfo"></b> <s:text name='cmn_to' /> <b class="batchUpdateCustomerAccountTotalCount"></b>)
				</em> <span><a class="next" onclick="accountPager('next');"></a></span> <span><a class="end" onclick="accountPager('end');"></a></span>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>