<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
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
</script>
<input type="hidden" id="radioTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="radioPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="radioTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="radioOrderField" value="${pager.orderField}" />
<input type="hidden" id="radioOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="radioInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />

<div class="right_content">
	<div class="pagelist drop">
		<div>
			<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="radioInfo"></b>到 <b class="radioTotalCount"></b>)
			</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
		</div>
	</div>
	<div class="table">
		<table class="list">
			<tr class="alt">
				<th width="50">选择</th>
				<th onclick="orderBy('id');">编号</th>
				<th onclick="orderBy('goalOfTraining');">培训目标</th>
				<th onclick="orderBy('trainingContent');">培训内容</th>
				<th onclick="orderBy('trainingCourse');">培训课程</th>
				<th onclick="orderBy('trainingSite');">培训地点</th>
			</tr>
			<s:iterator value="pager.resultList" var="entity" status="s">
				<tr>
					<td><input id="chkBrandId" name="chkBrandId" value="${entity.goalOfTraining}" type="radio" onclick="chkChange(this,'${entity.id}',${entity.goalOfTraining},'${entity.trainingContent}','${entity.trainingCourse}','${entity.trainingSite}')" /></td>
					<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
					<td>${entity.goalOfTraining}</td>
					<td>${entity.trainingContent}</td>
					<td>${entity.trainingCourse}</td>
					<td>${entity.trainingSite}</td>
				</tr>
			</s:iterator>
		</table>
	</div>
	<div class="pagelist drop">
		<div>
			<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="radioInfo"></b>到 <b class="radioTotalCount"></b>)
			</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
		</div>
	</div>
</div>
