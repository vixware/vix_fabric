<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script src="${vix}/common/core/js/all.js" type="text/javascript"></script>
<script type="text/javascript">
/** input 获取焦点input效果绑定  */
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});
	var updateField = "";
	function salesOrderFieldChanged(input){
		updateField+= $(input).attr("id")+",";
	}
	

$(".newvoucher dt b").click(function(){
	$(this).toggleClass("downup");
	$(this).parent("dt").next("dd").toggle();
});

$(".addtable .addtable_t").click(function(){
	$(this).toggleClass("addtable_td");
	$(this).next(".addtable_c").toggle(); 
});
$(".newvoucher dt b").click(function(){
	$(this).toggleClass("downup");
	$(this).parent("dt").next("dd").toggle();
});
$(".order_table input[type='text']").focusin( function() {
   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
});
$(".order_table  input[type='text']").focusout( function() {
   $(this).css({'border':'1px solid #ccc','background':'#fff'});
});

$("#orderForm").validationEngine();
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


function saveOrUpdate(id){
	$.ajax({
		  url:"${vix}/oa/evaluationReviewAction!goSaveOrUpdate.action?id="+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 760,
					height : 410,
					title:"评价评估",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#brandForm').validationEngine('validate')){
								var str="";   
								$("input[name='allowedTime']:checkbox:checked").each(function(){   
									str+=$(this).val()+",";   
								});   
								//alert(str);  
								$.post('${vix}/oa/evaluationReviewAction!saveOrUpdate.action',
									 {'executionFeedback.executionFeedback.id':id,
									  'evaluationReview.evaluationTime':$("#evaluationTime").val(),
									  'evaluationReview.evaluationContent':evaluationContent.html()
									},
									function(result){
										showMessage(result);
										setTimeout("",1000);
										pager("start","${vix}/oa/taskDefineAction!goSaveOrUpdate1.action?name="+name,'brand');
									}
								 ); 
			  				}else{
			  					return false;
			  				}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};


function saveOrUpdate1(id){
	$.ajax({
		  url:"${vix}/oa/evaluationReviewAction!goSaveOrUpdate1.action?id="+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 780,
					height :460,
					title:"查看评价评估",
					html:html,
					btnsbar : $.btn.CANCEL
				});
		  }
	});
};
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/oa_task.png" alt="" /> 协同办公</a></li>
				<li><a href="#">任务管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/taskDefineAction!goList.action?pageNo=${pageNo}');">任务列表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/taskDefineAction!goList.action?pageNo=${pageNo}');">反馈与评价查看</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${taskDefinition.id}" />
<div class="content">
	<form id="orderForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<strong><b><s:text name="任务与反馈、评价内容查看" /> </b> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
										<s:text name="calculator" /> </a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /> </a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /> </a>
									</span> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th align="right">任务名称：</th>
											<td><div style="width: 300px; float: clear;">${taskDefinition.questName}</div></td>
											<th align="right">任务来源：</th>
											<td><div style="width: 300px; float: clear;">${taskDefinition.taskSourceType.name}</div></td>
										</tr>
										<tr>
											<th align="right">任务类型：</th>
											<td><div style="width: 300px; float: clear;">${taskDefinition.taskType.name}</div></td>
											<th align="right">难度系数：</th>
											<td><div style="width: 300px; float: clear;">${taskDefinition.difficultyCoefficient.name}</div></td>
										</tr>
										<tr>
											<th align="right">任务层级：</th>
											<td><div style="width: 300px; float: clear;">${taskDefinition.taskLevel.name}</div></td>
											<th align="right">完成标志：</th>
											<td><div style="width: 300px; float: clear;">${taskDefinition.completionMark.name}</div></td>
										</tr>
										<tr>
											<th align="right">开始时间：</th>
											<td><a href="#" style="color: gray;"><fmt:formatDate value="${taskDefinition.startTime}" type="both" pattern="yyyy-MM-dd" /> </a></td>
											<th align="right">完成时间：</th>
											<td><a href="#" style="color: gray;"><fmt:formatDate value="${taskDefinition.endTime}" type="both" pattern="yyyy-MM-dd" /> </a></td>
										</tr>
										<tr>
											<th align="right">有效期：</th>
											<td><div style="width: 300px; float: clear;">${taskDefinition.validity}</div></td>
											<th align="right">任务权重：</th>
											<td><div style="width: 300px; float: clear;">${taskDefinition.taskWeights}</div></td>
										</tr>
										<tr>
											<th class="tr">执行部门/人员：</th>
											<td><div style="width: 300px; float: clear;">${taskDefinition.executiveAgency}</div></td>
											<th class="tr">考核部门/人员：</th>
											<td><div style="width: 300px; float: clear;">${taskDefinition.assessDepartment}</div></td>
										</tr>
										<tr>
											<th class="tr">审核部门/人员：</th>
											<td><div style="width: 300px; float: clear;">${taskDefinition.reviewDivision}</div></td>
											<th align="right">更新时间：</th>
											<td><a href="#" style="color: gray;"><fmt:formatDate value="${taskDefinition.updateTime}" type="both" pattern="yyyy-MM-dd" /> </a></td>
										</tr>
										<tr>
											<th align="right">任务描述：</th>
											<td><div style="width: 300px; float: clear;">${taskDefinition.taskDescription}</div></td>
											<th class="tr">创建人：</th>
											<td><div style="width: 300px; float: clear;">${taskDefinition.uploadPersonName}</div></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
				<div class="clearfix">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />任务附件</a></li>
						</ul>
					</div>
					<table class="list">
						<tbody>
							<tr class="alt">
								<th>编号</th>
								<th>文档名称</th>
								<th>文档类型</th>
								<th>上传时间</th>
								<th>上传者</th>
								<th>操作</th>
							</tr>
							<s:iterator value="uploaderList" var="entity" status="s">
								<tr>
									<td>${entity.id}</td>
									<td>${entity.title}</td>
									<td>${entity.fileType}</td>
									<td>${entity.uploadTime}</td>
									<td>${entity.uploadPersonName}</td>
									<td>
										<div class="untitled" style="position: static; display: inline;">
											<a href="${vix}/oa/taskDefineAction!downloadEqDocument.action?id=${entity.id}">下载</a>
										</div>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<div class="clearfix">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />任务反馈附件</a></li>
						</ul>
					</div>
					<table class="list">
						<tbody>
							<tr class="alt">
								<th>编号</th>
								<th>文档名称</th>
								<th>文档类型</th>
								<th>上传时间</th>
								<th>上传者</th>
								<th>操作</th>
							</tr>
							<s:iterator value="feedbackUploaderList" var="entity" status="s">
								<tr>
									<td>${entity.id}</td>
									<td>${entity.title}</td>
									<td>${entity.fileType}</td>
									<td>${entity.uploadTime}</td>
									<td>${entity.uploadPersonName}</td>
									<td>
										<div class="untitled" style="position: static; display: inline;">
											<a href="${vix}/oa/feedbackAction!downloadEqDocument.action?id=${entity.id}">下载</a>
										</div>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<div class="clearfix">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />任务反馈内容</a></li>
						</ul>
					</div>
					<table class="list">
						<tbody>
							<tr class="alt">
								<th>作者</th>
								<th width="50%">反馈内容</th>
								<th>反馈时间</th>
								<th>进度百分比</th>
								<th>操作</th>
							</tr>
							<s:iterator value="pager.resultList" var="entity" status="s">
								<tr>
									<td><a href="#" style="color: gray;">${entity.uploadPersonName}</a></td>
									<td><a href="#" style="color: gray;">${entity.executionFeedback}</a></td>
									<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.feedbackTime}" type="both" pattern="yyyy-MM-dd" /> </a></td>
									<td id="name_${entity.id}"><s:if test="%{#entity.name <= 50}">
											<span style="color: red;">${entity.name}%</span>
										</s:if> <s:elseif test="%{#entity.name >= 51 && #entity.name <=80 }">
											<span style="color: orange;">${entity.name}%</span>
										</s:elseif> <s:elseif test="%{#entity.name >= 81 && #entity.name <=99 }">
											<span style="color: blue;">${entity.name}%</span>
										</s:elseif> <s:elseif test="%{#entity.name == 100}">
											<span style="color: green;">${entity.name}%</span>
										</s:elseif></td>
									<td>
										<div class="untitled" style="position: static; display: inline;">
											<a href="#" onclick="saveOrUpdate1('${entity.id}');">查看评价评估</a> <a href="#" onclick="saveOrUpdate(0);">评价评估</a>
										</div>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</dl>
		</div>
	</form>
</div>