<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
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
	asyncbox.confirm('确定要删除该公告通知么?','<s:text name='公告通知'/>',function(action){
		if(action == 'ok'){
			deleteById(id);
		}
	});
}
function chooseAll(chk){
	if(null == chk){
		$("input[name='chkId']").attr("checked", true);
	}else{
		if($(chk).attr('checked')){
			$("input[name='chkId']").attr("checked", true);
		}else{
			$("input[name='chkId']").attr("checked", false);
		}
	}
	selectCount();
}

function selectCount(){
	var selectCount = 0;
	$.each($("input[name='chkId']"), function(i, n){
		if($(n).attr('checked')){
			selectCount++;
		}
	});
	$("#selectCount1").html(selectCount);
	$("#selectCount2").html(selectCount);
	if(selectCount == 0){
		$("input[name='chkIds']").attr("checked", false);
	}else{
		$("input[name='chkIds']").attr("checked", true);
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

/* 查看公告通知 */
function popNews(id){
	$.ajax({
		  url:"${vix}/oa/announcementAction!goSeenoticenotice.action?id="+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 780,
					height :460,
					title:"查看公告通知",
					html:html,
					callback : function(action){
						if(action == 'ok'){
								}
							} ,
							btnsbar : [{
								text :'关闭',
								action :'cancel'
							}]
				});
		  }
	});
};

/* 查看人员 */
function viewers(id){
	$.ajax({
		  url:"${vix}/oa/announcementNotificationAction!goViewers.action?id="+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 780,
					height :460,
					title:"查看人员",
					html:html,
					callback : function(action){
						if(action == 'ok'){
								}
							} ,
							btnsbar : [{
								text :'关闭',
								action :'cancel'
							}]
				});
		  }
	});
};

/** 载入数据列排序图标 */
loadOrderByImage("${vix}","announcement");
</script>
<input type="hidden" id="announcementTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="announcementPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="announcementTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="announcementOrderField" value="${pager.orderField}" />
<input type="hidden" id="announcementOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="announcementInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="180">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkIds" onchange="chooseAll(this)"></label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name="cmn_all" /></a></li>
							<li><a href="#"><s:text name="cmn_other" /></a></li>
							<li><a href="#"><s:text name="cmn_close" /></a></li>
						</ul>
					</div>
				</div>
			</th>
			<%-- <th width="10%" style="cursor: pointer;" onclick="orderBy('id');">
				<s:text name="cmn_id"/>&nbsp;<img id="trends_id" src="${vix}/common/img/arrow.gif">
			</th> --%>
			<th width="7%" style="cursor: pointer;" onclick="orderBy('uploadPersonName');">发布人&nbsp;<img id="announcementImg_uploadPersonName" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="7%" style="cursor: pointer;" onclick="orderBy('bulletinType');">类型&nbsp;<img id="announcementImg_bulletinType" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="17%" style="cursor: pointer;" onclick="orderBy('pubNames');">发布范围&nbsp;<img id="announcementImg_pubNames" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="20%" style="cursor: pointer;" onclick="orderBy('title');">标题&nbsp;<img id="announcementImg_title" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="8%" style="cursor: pointer;" onclick="orderBy('activeStartDate');">生效时间&nbsp;<img id="announcementImg_activeStartDate" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="8%" style="cursor: pointer;" onclick="orderBy('activeEndDate');">终止时间&nbsp;<img id="announcementImg_activeEndDate" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="8%" style="cursor: pointer;" onclick="orderBy('readTimes');">阅读次数&nbsp;<img id="announcementImg_readTimes" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="6%" style="cursor: pointer;" onclick="orderBy('isPublish');">状态&nbsp;<img id="announcementImg_isPublish" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="13%"><s:text name="cmn_operate" /></th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<%-- <td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td> --%>
				<td><span style="color: gray;">${entity.uploadPersonName }</span></td>
				<td><a href="#" style="color: gray;"> <s:if test="%{#entity.bulletinType == 0}">全体公告</s:if> <s:elseif test="%{#entity.bulletinType == 1}">部门公告</s:elseif> <s:elseif test="%{#entity.bulletinType == 2}">行政公告</s:elseif> <s:elseif test="%{#entity.bulletinType == 3}">通知</s:elseif>
				</a></td>
				<td><span style="color: gray;">${entity.pubNames }</span></td>
				<td><a href="#" style="color: gray;" onclick="popNews('${entity.id}');">${entity.title}</a>
				<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.activeStartDate}" type="both" pattern="yyyy-MM-dd" /></a></td>
				<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.activeEndDate}" type="both" pattern="yyyy-MM-dd" /></a></td>
				<td style="text-align: center"><span style="color: gray; text-align: center">${entity.readTimes}</span></td>
				<td id="isPublish_${entity.id}"><s:if test="%{#entity.isPublish == 1}">
						<span style="color: green;">发布</span>
					</s:if> <s:elseif test="%{#entity.isPublish == 0}">
						<span style="color: red;">终止</span>
					</s:elseif></td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<s:if test="%{#entity.isPublish==0 }">
							<a href="#" id="ip_link_${entity.id}" onclick="updateIsPublish('${entity.id}',${entity.isPublish})"> <span id="entity_${entity.id}"><span style="color: green;">发布</span></span>
							</a>
						</s:if>
						<s:elseif test="%{#entity.isPublish==1 }">
							<a href="#" id="ip_link_${entity.id}" onclick="updateIsPublish('${entity.id}',${entity.isPublish})"> <span id="entity_${entity.id}"><span style="color: red;">终止</span></span>
							</a>
						</s:elseif>
					</div>
					<div class="untitled" style="position: static; display: inline;">
						<a href="#" onclick="viewers('${entity.id}');"><span style="color: blue;">查看</span></a>
					</div> <%-- <div class="untitled" style="position: static;display: inline;">
						<s:if test="#entity.isPublish ==0" >
							<a href="#" onclick="saveOrUpdate('${entity.id}');">修改</a>
						</s:if>
					</div> --%>
					<div class="untitled" style="position: static; display: inline;">
						<a href="#" onclick="deleteEntity('${entity.id}');"><span style="color: #8B0000;">删除</span></a>
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
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>