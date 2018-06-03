<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
$("attendance tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("attendance tr:even").addClass("alt");
//list_check
$(".list_check").bind('mouseover',function(){
	$(".list_check").addClass('posr');
	$(".list_check ul").css('display','block');
}).bind('mouseleave',function(){
	$(".list_check").removeClass('posr');
	$(".list_check ul").css('display','none');
});
   
function deleteAttend(id,a){
	asyncbox.confirm('确定要删除考勤记录么?','提示信息',function(action){
		if(action == 'ok'){
			deleteAttendance(id,a);
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


////////////
$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
		$(this).addClass("btnhover");
	},function(){
		$(this).removeClass("btnhover");
	});


//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#newtab5OrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/oa/personalAttendanceAction!goAttendanceList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name,'newtab5');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/oa/personalAttendanceAction!goAttendanceList.action?name="+name,'newtab5');
}

$(".drop>ul>li").bind('mouseover',function(){
	$(this).children('ul').delay(400).slideDown('fast');
}).bind('mouseleave',function(){
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast');
});
/** 载入数据列排序图标 */
loadOrderByImage("${vix}","newtab5");
</script>
<input type="hidden" id="newtab5TotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="newtab5PageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="newtab5TotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="newtab5OrderField" value="${pager.orderField}" />
<input type="hidden" id="newtab5OrderBy" value="${pager.orderBy}" />
<input type="hidden" id="newtab5InfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<div class="right_content">
	<div class="pagelist drop">
		<div>
			<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="newtab5Info"></b> <s:text name='cmn_to' /> <b class="newtab5TotalCount"></b>)
			</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
		</div>
	</div>
	<table id="attendance" class="list">
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
				<th>姓名</th>
				<th>记录日期</th>
				<th>登记时间</th>
				<th>状态</th>
			</tr>
			<% int count =0; %>
			<s:iterator value="pager.resultList" var="entity" status="s">
				<% count++; %>
				<tr>
					<td><input id="chkCategoryId" name="chkCategoryId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
					<td><a href="#" style="color: gray;">${entity.uploadPersonName}</a></td>
					<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.loginDate}" type="both" pattern="yyyy-MM-dd" /></a></td>
					<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.startTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></a></td>
					<td><s:if test="%{#entity.reason == 1}">
							<span style="color: red;">签离</span>
						</s:if> <s:elseif test="%{#entity.reason == 0}">
							<span style="color: green;">签到</span>
						</s:elseif></td>
					<%-- <td>					
					<div class="untitled" style="position: static;display: inline;">
						<s:if test="tag != 'choose'">
						<a href="#" onclick="saveOrAttendance(${entity.id},$('#parentId').val());">修改</a>
						</s:if>
						<a href="#" onclick="deleteAttend(${entity.id},this);">删除</a>
					</div>
				</td> --%>
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
	<div class="pagelist drop">
		<div>
			<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="newtab5Info"></b> <s:text name='cmn_to' /> <b class="newtab5TotalCount"></b>)
			</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
		</div>
	</div>
</div>