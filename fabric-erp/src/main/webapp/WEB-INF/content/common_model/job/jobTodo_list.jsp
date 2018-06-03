<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function goBillAudit(id) {
		$.ajax({
		url : '${vix}/common/model/jobTodoAction!goBillAudit.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	//载入分页列表数据
	pager("start", "${vix}/common/model/jobTodoAction!goSingleList.action?1=1", 'jobTodo');
	function currentPager(tag) {
		pager(tag, "${vix}/common/model/jobTodoAction!goSingleList.action?1=1", 'jobTodo');
	}

	function currentPagerClick(input, event) {
		pagerClick(input, event, "${vix}/common/model/jobTodoAction!goSingleList.action?1=1", 'jobTodo');
	}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="img/icon_14.gif" alt="" />打印</a> <a href="#" id="help"><img src="img/icon_15.gif" alt="" />帮助</a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/sys_jobtodo.png" alt="" />工作台</a></li>
				<li><a href="###" onclick="loadContent('${vix}/common/model/jobTodoAction!goList.action');">待办事宜</a></li>
			</ul>
		</div>
	</h2>
</div>

<div class="content">
	<div id="c_head" class="drop">
		<span class="left_bg"></span> <span class="right_bg"></span>

		<ul class="c_head_tabbtn">
			<li class="current"><a href="javascript:void(0);" onclick="javascript:tab(2,1,'newtab',event)"><img alt="" src="img/icon_10.png">待办事宜</a></li>
			<li><a href="javascript:void(0);" onclick="javascript:tab(2,2,'newtab',event)"><img alt="" src="img/icon_10.png">待办总览</a></li>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="right">
			<div id="newtab1">
				<div class="addleft">
					<div class="addbtn">
						<p>
							<img src="img/sys_jobtodo.png" /> 待办事宜
						</p>
					</div>
					<div id="date1" class="date_box"></div>
					<script type="text/javascript">
						function chooseDate(currentDate) {
							pager("start", "${vix}/common/model/jobTodoAction!goSingleList.action?currentDate=" + currentDate, 'jobTodo');
						}
						WdatePicker({
						eCont : 'date1',
						dateFmt : 'yyyy-MM-dd HH:mm:ss',
						skin : 'blue',
						onpicked : function(dp) {
							chooseDate(dp.cal.getDateStr());
						}
						});
					</script>
				</div>
				<div class="addright">
					<div class="daily_box">
						<div class="pagelist drop">
							<div>
								<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="jobTodoInfo"></b> <s:text name='cmn_to' /> <b class="jobTodoTotalCount"></b>)
								</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
							</div>
						</div>
						<div id="jobTodo" style="overflow-x: auto; overflow-y: hidden; width: 100%;"></div>
						<div class="pagelist drop">
							<div>
								<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="jobTodoInfo"></b> <s:text name='cmn_to' /> <b class="jobTodoTotalCount"></b>)
								</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="newtab2" style="display: none;">
				<div class="addleft">
					<div class="addbtn">
						<p>
							<img src="img/sys_jobtodo.png" /> 待办总览
						</p>
					</div>
					<div id="date2" class="date_box"></div>
					<script type="text/javascript">
						WdatePicker({
						eCont : 'date2',
						dateFmt : 'yyyy-MM-dd HH:mm:ss',
						skin : 'blue'
						});
					</script>
				</div>
				<div class="addright">
					<p class="drt clearfix">
						<span class="gray">注意：未写日报的日期不再下面显示</span>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>