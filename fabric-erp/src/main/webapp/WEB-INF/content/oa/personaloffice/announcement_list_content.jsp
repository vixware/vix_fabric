<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
$("announcement tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("announcement tr:even").addClass("alt");
//list_check
$(".list_check").bind('mouseover',function(){
	$(".list_check").addClass('posr');
	$(".list_check ul").css('display','block');
}).bind('mouseleave',function(){
	$(".list_check").removeClass('posr');
	$(".list_check ul").css('display','none');
});
   
function deleteEntity(id){
	asyncbox.confirm('确定要删除该分类么?','提示信息',function(action){
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


function popNews(id){
	$.ajax({
		  url:"${vix}/oa/announcementAction!goSeenoticenotice.action?id="+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 780,
					height :460,
					title:"公告通知",
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
<table id="announcement" class="list">
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
			<th width="8%" style="cursor: pointer;" onclick="orderBy('uploadPersonName');">发布人&nbsp;<img id="announcementImg_uploadPersonName" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="6%"><s:text name="类型" /></th>
			<th width="30%"><s:text name="标题" /></th>
			<th width="25%"><s:text name="发布范围" /></th>
			<th width="8%"><s:text name="生效日期" /></th>
			<th width="7%"><s:text name="阅读次数" /></th>
			<th width="5%"><s:text name="状态" /></th>
			<th width="5%"><s:text name="cmn_operate" /></th>
		</tr>
		<% int count =0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkCategoryId" name="chkCategoryId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td><span style="color: gray;">${entity.announcementNotification.uploadPersonName }</span></td>
				<td><a href="#" style="color: gray;"> <s:if test="%{#entity.announcementNotification.bulletinType == 0}">
							<span style="color: red;">全体公共</span>
						</s:if> <s:elseif test="%{#entity.announcementNotification.bulletinType == 1}">
							<span style="color: green;">部门公告</span>
						</s:elseif> <s:elseif test="%{#entity.announcementNotification.bulletinType == 2}">
							<span style="color: green;">行政公告</span>
						</s:elseif> <s:elseif test="%{#entity.announcementNotification.bulletinType == 3}">
							<span style="color: green;">通知</span>
						</s:elseif>
				</a></td>
				<td><a href="#" style="color: gray;" onclick="popNews(${entity.announcementNotification.id});">${entity.announcementNotification.title}</a></td>
				<td><span style="color: gray;">${entity.announcementNotification.pubNames }</span></td>
				<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.announcementNotification.activeStartDate}" type="both" pattern="yyyy-MM-dd" /></a></td>
				<td style="text-align: center"><span style="color: gray;">${entity.announcementNotification.readTimes }</span></td>
				<td id="isPublish_${entity.id}"><s:if test="%{#entity.isPublish == 1}">
						<span style="color: green;">已读</span>
					</s:if> <s:elseif test="%{#entity.isPublish == 0}">
						<span style="color: red;">未读</span>
					</s:elseif></td>
				<td>
					<div class="untitled">
						<s:if test="tag != 'choose'">
							<a href="#" onclick="popNews('${entity.announcementNotification.id}');" title="<s:text name='oa_view'/>">查看</a>
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
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>