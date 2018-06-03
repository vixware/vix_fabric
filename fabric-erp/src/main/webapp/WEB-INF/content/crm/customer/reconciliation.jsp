<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
<!--
	$(".newvoucher dt b").click(function(){
		$(this).toggleClass("downup");
		$(this).parent("dt").next("dd").toggle();
	});
	/** input 获取焦点input效果绑定  */
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});
 
if($(".ms").length>0){
	$(".ms").hover(function(){
		$(">ul",this).stop().slideDown(100);
	},function(){
		$(">ul",this).stop().slideUp(100);
	});
	$(".ms ul li").hover(function(){
		$(">a",this).addClass("hover");
		$(">ul",this).stop().slideDown(100);
	},function(){
		$(">a",this).removeClass("hover");
		$(">ul",this).stop().slideUp(100);
	});
}

//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
//-->
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="img/icon_14.gif" alt="" />打印</a> <a href="#" id="help"><img src="img/icon_15.gif" alt="" />帮助</a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/newico.png" class="png" alt="" width="26" height="26" />供应链</a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#">客户管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/customer/reconciliationAction!goList.action');">往来对账</a></li>
			</ul>
		</div>
	</h2>
</div>
<!-- sub menu -->
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_head -->
	<div class="box">
		<div class="ct_title">
			<div class="r ct_title_r">
				<span>客户名称:</span><input type="text" id="customerAccountName" readonly="readonly" value="${customerAccount.name}" /><input type="hidden" id="customerAccountId" value="${customerAccount.id}" /> <input class="btn" type="button" value="选择客户" onclick="chooseCustomerAccountForReconciliation();" style="margin-right: 20px;" />
			</div>
			<script type="text/javascript">
				function chooseCustomerAccountForReconciliation(){
					$.ajax({
						  url:'${vix}/mdm/crm/customerAccountAction!goChooseCustomerAccount.action',
						  cache: false,
						  success: function(html){
							  asyncbox.open({
								 	modal:true,
									width : 960,
									height : 500,
									title:"选择客户",
									html:html,
									callback : function(action,returnValue){
										if(action == 'ok'){
											if(returnValue != undefined){
												var result = returnValue.split(":");
												loadContent('${vix}/crm/customer/reconciliationAction!goList.action?year=${year}&customerAccountId='+result[0]);
											}
										}
									},
									btnsbar : $.btn.OKCANCEL
								});
						  }
					});
				}
			</script>
			<a href="#" onclick="loadContent('${vix}/crm/customer/reconciliationAction!goList.action?year=${preYear}&customerAccountId=${customerAccount.id}');">上一年：${preYear}</a>&nbsp;&nbsp;&nbsp;&nbsp; <strong>${year}年-月度对账表</strong>&nbsp;&nbsp;&nbsp;&nbsp;
			<s:if test="nextYear > 0">
				<a href="#" onclick="loadContent('${vix}/crm/customer/reconciliationAction!goList.action?year=${nextYear}&customerAccountId=${customerAccount.id}');">下一年：${nextYear}</a>
			</s:if>
			<s:else>
				<div style="width: 80px;"></div>
			</s:else>
		</div>
		<div class="table">
			<table class="list">
				<tr>
					<th width="10"></th>
					<th>月</th>
					<th>日期</th>
					<th>上月结转</th>
					<th>回款</th>
					<th>小计</th>
				</tr>
				<s:iterator value="bsrtList" var="entity">
					<tr>
						<td></td>
						<td>${entity.month}</td>
						<td>续上月</td>
						<td>${entity.previousMonthAmount}</td>
						<td><s:if test="#entity.currentMonthAmount > 0">
								<a href="#" onclick="showReconciliationDetail(${entity.month});">${entity.currentMonthAmount}</a>
							</s:if> <s:else>
			         			${entity.currentMonthAmount}
			         		</s:else></td>
						<td>${entity.total}</td>
					</tr>
				</s:iterator>
			</table>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>
<script type="text/javascript">
	function showReconciliationDetail(month){
		$.ajax({
			  url:'${vix}/crm/customer/crmCustomerAccountAction!showReconciliationDetail.action?year=${year}&month='+month+"&customerAccountId="+$("#customerAccountId").val(),
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 960,
						height : 500,
						title:"回款明细",
						html:html,
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
</script>
