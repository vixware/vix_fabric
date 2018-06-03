<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<p class="drt clearfix">
	<em>调至：<a href="#" onclick="loadDailyContent($('#wpDate').val()+',05');">5</a>号 <a href="#" onclick="loadDailyContent($('#wpDate').val()+',10');">10</a>号 <a href="#" onclick="loadDailyContent($('#wpDate').val()+',20');">20</a>号 <a href="#" onclick="loadDailyContent($('#wpDate').val()+',25');">25</a>号 <a href="#"
		onclick="loadDailyContent($('#wpDate').val()+',30');">30</a>号
	</em>
</p>
<h2>${today}- ${employee.name}-${daily.name}</h2>
<p class="drt clearfix">
	<span class="gray">注意：未写日报的日期不再下面显示</span>
</p>
<div class="addbox">
	<div class="addtitle">${today}</div>
	<div class="addbox_content">
		<p class="gray">
			<b>今日计划(由昨日拟定)</b>
		</p>
		<p class="gray">${yesterdayDaily.tomorrowPlan}</p>
		<p class="gray">
			<b>今日总结</b>
		</p>
		<p class="gray">${daily.todaySummary}</p>
		<p class="gray">
			<b>明日计划</b>
		</p>
		<p class="gray">${daily.tomorrowPlan}</p>
		<s:if test="daily.repeat != null">
			<p class="gray">
				<b>批示和回复</b>
			</p>
			<p class="gray">${daily.repeat}</p>
		</s:if>
	</div>
</div>
<s:if test="daily.repeat == null">
	<script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script>
	<h4 style="padding-bottom: 5px; margin-left: 15px;">写回复</h4>
	<form action="" method="get" style="margin-left: 15px;">
		<textarea style="height: 140px;" name="dailyRepeat"></textarea>
		<br /> <input type="button" value="确认回复" class="btn" onclick="saveRepeat()" />
	</form>
	<input type="hidden" id="dailyId" value="${daily.id}" />
	<script>
		var repeat = KindEditor.create('textarea[name="dailyRepeat"]',{basePath:'${vix}/plugin/KindEditor/',width:'99%',height:'100%'});
		function saveRepeat(){
			if(repeat.html() != ''){
				$.post('${vix}/crm/agenda/dailyAction!saveRepeat.action',
						{'daily.id': $("#dailyId").val(),
						  'daily.repeat':repeat.html()
						},
						function(result){
							showMessage(result);
							loadContent('${vix}/crm/agenda/dailyAction!goList.action');
						}
					);
			}
		}
	</script>
</s:if>
