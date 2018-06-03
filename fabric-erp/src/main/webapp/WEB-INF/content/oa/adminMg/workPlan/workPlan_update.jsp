<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" href="${vix}/common/css/aristo/jquery-ui-1.8.5.custom.css" type="text/css" media="screen" title="no title" charset="utf-8">
<script src="${vix}/common/js/jquery.tokeninput.js" type="text/javascript"></script>
<script src="${vix}/common/js/jquery.jnotify.js" type="text/javascript"></script>
<script src="${vix}/common/js/underscore-min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/backbone-min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.tmpl.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/ba-debug.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/ba-tinyPubSub.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.mousewheel.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.ui.ipad.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.global.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/nicEdit.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
function tabs(title,content,style){
		$(title).click(function(){
			$(title).removeClass(style);
			$(this).addClass(style);
			$(content).hide();
			$(content+':eq('+$(title).index($(this))+')').show();
		});
	}
	
	
	 /* if('${null != meetingRequest.department }'){
		var chkTime = '${meetingRequest.allowedTime }'.split(",");
		for (var i=0;i<chkTime.length-1;i++){
			if ($("#allowedTime"+chkTime[i]).val() == chkTime[i]) {  
	            $("#allowedTime"+chkTime[i]).attr("checked", "checked");  
	        }
		}
	}  */
	
	/** 日志进度 */
	function saveOrUpdateOrder(){
		
		if($('#orderForm').validationEngine('validate')){
			$.post('${vix}/oa/workPlanAction!saveOrUpdate.action',
				{'progressLog.id':$("#id").val(),
				 'progressLog.currentTime':$("#currentTime").val(),
				 'progressLog.schedule':$("#schedule").val(),	
				 'progressLog.percent':$("#percent").val()
				},
				function(result){
					showMessage(result);
					setTimeout("",1000);
					loadContent('${vix}/oa/workPlanAction!goList.action?menuId=menuContract');
				} 
			);
		}
	}
	
	function saveOrUpdate1(id){
		$.ajax({
			  url:'${vix}/oa/workPlanAction!goSaveOrUpdate1.action?id='+id,
			  cache: false,
			  success: function(html){
				  $("#mainContent").html(html);
			  }
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
	    
	 function deleteEntity(id){
			asyncbox.confirm('确定要删除该日志进度么?','<s:text name='日志进度'/>',function(action){
				if(action == 'ok'){
					deleteById(id);
				}
			});
		}

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
		<i> <a href="#"><img src="${vix}/common/img/icon_14.gif" alt="" />
			<s:text name="print" /></a> <a href="#" id="help"><img src="${vix}/common/img/icon_15.gif" alt="" />
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/pm_task.png" alt="" /> 协同办公</a></li>
				<li><a href="#">行政办公</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/workPlanAction!goList.action?pageNo=${pageNo}');">工作计划 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/workPlanAction!goList.action?pageNo=${pageNo}');">工作计划查询 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/workPlanAction!goList.action?pageNo=${pageNo}');">进度日志 </a></li>
			</ul>
		</div>
	</h2>
</div>
<!-- sub menu -->
<div class="content">
	<form id="orderForm">
		<div class="order">
			<dl>
				<dt id="saveOrUpdateOrder">
					<span class="no_line"> <a onclick="saveOrUpdateOrder();"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /></a> <a href="#" onclick="loadContent('${vix}/oa/workPlanAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="进度日志详情" /> </b><i></i> </strong>
				</dt>
				<dd class="clearfix">
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
								<th width="50%">内容</th>
								<th>附件</th>
								<th>日志时间</th>
								<th>进度百分比</th>
								<th><s:text name="oa_operating" /></th>
							</tr>
							<% int count =0; %>
							<s:iterator value="pager.resultList" var="entity" status="s">
								<% count++; %>
								<tr>
									<td><input id="chkCategoryId" name="chkCategoryId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
									<td>张三</td>
									<td><a href="#" style="color: gray;">${entity.schedule}</a></td>
									<td>无</td>
									<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.currentTime}" type="both" pattern="yyyy-MM-dd" /></a></td>
									<td><a href="#" style="color: gray;">${entity.percent}</a></td>
									<td>
										<div class="untitled" style="position: static; display: inline;">
											<s:if test="tag != 'choose'">
												<a href="#" onclick="saveOrUpdate1(0,$('#selectId').val());">修改</a>
											</s:if>
											<s:if test="tag != 'choose'">
												<a href="#" onclick="deleteEntity('${entity.id}');">删除</a>
											</s:if>
										</div>
									</td>
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
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th align="right">反馈时间：</th>
											<td><input id="currentTime" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd" name="currentTime" value="<fmt:formatDate value='${progressLog.currentTime}' type='both' pattern='yyyy-MM-dd' />" type="text" size="26" /> <img onclick="showTime('currentTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16"
												height="22" align="absmiddle"></td>
											<th align="right">完成百分比：</th>
											<td><input id="percent" name="percent" value="${progressLog.percent}" type="text" size="26" class="validate[required] text-input" />% <span style="color: red;">(注：估计完成与总量的百分比)</span></td>
										</tr>
										<tr>
											<th align="right">进度详细情：</th>
											<td colspan="3"><textarea id="schedule" name="enclosure" class="example" rows="2" style="width: 1024px; height: 110px;">${progressLog.enclosure }</textarea></td>
										</tr>
										<tr>
											<th align="right">附件选择：</th>
											<td><input id="" name="" value="" type="text" size="26" /><span><a href="#"><img src="img/icon_25.gif" />添加附件</a> <a href="#"><img src="img/icon_25.gif" />从文件柜和网络硬盘选择附件</a> </span></td>
											<th align="right">提醒：</th>
											<td><label> <input name="" type="checkbox" value="" class="checkbox" /> 发送事务提醒消息
											</label></td>
										</tr>
										<tr>
											<th align="right">是否写入工作日志：</th>
											<td><label> <input name="" type="checkbox" value="" class="checkbox" /> <span style="color: red;">(注意：勾选会将进度详情写入工作日志中)</span>
											</label></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
			</dl>
		</div>
	</form>
</div>







