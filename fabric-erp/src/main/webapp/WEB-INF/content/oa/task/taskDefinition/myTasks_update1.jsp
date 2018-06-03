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


 $("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
	 $("table tr:even").addClass("alt");
	 //list_check
	 $(".list_check").bind('mouseover',function(){
	 	$(".list_check").addClass('posr');
	 	$(".list_check ul").css('display','block');
	 }).bind('mouseleave',function(){
	 	$(".list_check").removeClass('posr');
	 	$(".list_check ul").css('display','none');
	 });
	    

	 function chooseAll(chk){
	 	if(null == chk){
	 		$("input[name='chkCategoryId']").attr("checked", true);
	 	}else{
	 		if($(chk).attr('checked')){
	 			$("input[name='chkCategoryId']").attr("checked", true);
	 		}else{
	 			$("input[name='chkCategoryId']").attr("checked", false);
	 		}
	 	}
	 	selectCount();
	 }

	 function selectCount(){
	 	var selectCount = 0;
	 	$.each($("input[name='chkCategoryId']"), function(i, n){
	 		if($(n).attr('checked')){
	 			selectCount++;
	 		}
	 	});
	 	$("#selectCategoryCount1").html(selectCount);
	 	$("#selectCategoryCount2").html(selectCount);
	 	if(selectCount == 0){
	 		$("input[name='chkCategoryIds']").attr("checked", false);
	 	}else{
	 		$("input[name='chkCategoryIds']").attr("checked", true);
	 	}
	 }

	 $(".untitled").hover(
	 	function () {
	 		$(this).css({ "position": "relative"});
	 		$(this).children('.popup').css({ "display": "block"});
	 		var bh = $("body").height(); 
	 		var pt = $(this).offset();
	 		if(( bh - pt.top) < 165){$(this).children('.popup').css({ "bottom": "0"});}else{$(this).children('.popup').css({ "top": "-7px"});};
	   	},
	 	function () {
	 		$(this).css({ "position": "static"});
	 		$(this).children('.popup').css({ "display": "none"});
	  	}
	 );
	 $(".close").click(
	 	function () {
	 		$(this).parent().parent().css({ "display": "none"});
	 	}
	 );
</script>
<input type="hidden" id="categoryTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="categoryPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="categoryTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="categoryOrderField" value="${pager.orderField}" />
<input type="hidden" id="categoryOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="categoryInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/oa_task.png" alt="" /> 协同办公</a></li>
				<li><a href="#">任务管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/myTasksAction!goList.action?pageNo=${pageNo}');">我的任务</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/myTasksAction!goList.action?pageNo=${pageNo}');">查看我的任务 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${executionFeedback.id}" />
<div class="content">
	<form id="orderForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<strong><b><s:text name="查看任务" /></b></strong>
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
					<table class="list">
						<tbody>
							<tr class="alt">
								<th width="50">
									<div class="list_check">
										<div class="drop">
											<label><input type="checkbox" name="chkCategoryIds" onchange="chooseAll(this)"></label>
											<ul>
												<li><a href="#" onclick="chooseAll();">所有</a></li>
												<li><a href="#">其他</a></li>
												<li><a href="#">式样</a></li>
												<li><a href="#">关闭</a></li>
											</ul>
										</div>
									</div>
								</th>
								<th>作者</th>
								<th width="50%">反馈内容</th>
								<th>反馈时间</th>
								<th>进度百分比</th>
								<!-- <th><s:text name="oa_operating"/></th> -->
							</tr>
							<% int count =0; %>
							<s:iterator value="pager.resultList" var="entity" status="s">
								<% count++; %>
								<tr>
									<td><input id="chkCategoryId" name="chkCategoryId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
									<td><a href="#" style="color: gray;">${entity.uploadPersonName}</a></td>
									<td><a href="#" style="color: gray;">${entity.executionFeedback}</a></td>
									<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.feedbackTime}" type="both" pattern="yyyy-MM-dd" /></a></td>
									<td id="name_${entity.id}"><s:if test="%{#entity.name <= 50}">
											<span style="color: red;">${entity.name}%</span>
										</s:if> <s:elseif test="%{#entity.name >= 51 && #entity.name <=80 }">
											<span style="color: orange;">${entity.name}%</span>
										</s:elseif> <s:elseif test="%{#entity.name >= 81 && #entity.name <=99 }">
											<span style="color: blue;">${entity.name}%</span>
										</s:elseif> <s:elseif test="%{#entity.name == 100}">
											<span style="color: green;">${entity.name}%</span>
										</s:elseif></td>
									<!-- <td>					
										<div class="untitled" style="position: static;display: inline;">
											<s:if test="tag != 'choose'">
												<a href="#" onclick="saveOrUpdate('${entity.id}');">修改</a>
											</s:if>
											<s:if test="tag != 'choose'">
												<a href="#" onclick="deleteEntity('${entity.id}');">删除</a>
											</s:if>
										</div>
									</td> -->
								</tr>
							</s:iterator>
							<%	/** 用于页面数据行数显示不全时，输出空行，使页面保存美观! */
								com.vix.core.web.Pager pager = (com.vix.core.web.Pager)request.getAttribute("pager");
								count = pager.getPageSize() - count;
								request.setAttribute("count",count);
							%>
							<c:forEach begin="1" end="${count}">
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</dd>
			</dl>
		</div>
	</form>
</div>
<!-- content -->
<script type="text/javascript">
$(function(){
	$.fn.fix = function(options){
		var defaults = {
			'advance' : 0,
			'top' : 0
		}
		options = $.extend(defaults, options);
		return this.each(function(){
			var $_this = $(this);
			$_this.wrap('<div></div>');
			var wp = $_this.parent('div');
			wp.height(wp.height());
			var ofst = wp.offset();
			
			if(!$_this.is(':visible') && $(window).scrollTop() + options.advance > $_this.offset().top){
				$_this.css({'position':'fixed','z-index':9999,'top': options.top,'width':$_this.width()});
			}
			$(window).scroll(function(){
				if(!$_this.is(':visible')){
					return ;
				}
				
				if($(window).scrollTop() + options.advance > wp.offset().top){
					$_this.css({'position':'fixed','z-index':9999,'top': options.top,'width':$_this.width()});
				}else{
					$_this.css({'position':'static','z-index':'auto','top': 'auto','width':'auto'});
				}
			});
		});
	}
	$('#a1 .right_btn,#a6 .right_btn').fix({'advance':38,'top':38});
});

	function tabs(title,content,style){
		$(title).click(function(){
			$(title).removeClass(style);
			$(this).addClass(style);
			$(content).hide();
			$(content+':eq('+$(title).index($(this))+')').show();
		});
	}
	

</script>