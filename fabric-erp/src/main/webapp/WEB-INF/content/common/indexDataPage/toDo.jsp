<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function chosedatabydate(onchangedate){
		$.ajax({
			  url:'${vix}/common/vixIndexDataAction!goToDo.action?onchangedate='+onchangedate,
			  cache: false,
			  success: function(html){
			  	$("#mainContent").html(html);
			  }
		});
   }
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="img/icon_14.gif" alt="" />打印</a> <a href="#" id="help"><img src="img/icon_15.gif" alt="" />帮助</a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/sys_jobtodo.png" alt="" />工作台</a></li>
				<li><a href="#">待办事宜</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop"></div>
</div>
<!-- sub menu -->
<div class="content">
	<div id="c_head" class="drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>待办事宜帮助信息</p>
			</div>
		</div>
		<ul class="c_head_tabbtn">
			<li class="current"><a href="javascript:void(0);" onclick="javascript:tab(8,1,'newtab',event)"><img alt="" src="img/icon_10.png">待办事宜</a></li>
			<li><a href="javascript:void(0);" onclick="javascript:tab(8,2,'newtab',event)"><img alt="" src="img/icon_10.png">待办总览</a></li>
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
						WdatePicker ({
						eCont : 'date1' ,
						dateFmt : 'yyyy-MM-dd HH:mm:ss' ,
						skin : 'blue',onpicked:function(dp){
							chosedatabydate(dp.cal.getDateStr());
                		}
						});
					</script>
				</div>
				<div class="addright" id="jobtodo">
					<div class="daily_box">
						<h4 class="blue">待办</h4>
						<table class="list">
							<tbody>
								<s:if test="jobTodoList.size > 0">
									<s:iterator value="jobTodoList" var="entity" status="s">
										<tr>
											<td><a href="javascript:;" onclick="saveOrUpdate('${entity.id}');">${entity.jobName}</a> <s:date format="yyyy-MM-dd HH:mm:ss" name="%{#entity.createTime}" /></td>
										</tr>
									</s:iterator>
								</s:if>
								<s:else>
									<c:forEach begin="1" end="20">
										<tr>
											<td>&nbsp;</td>
										</tr>
									</c:forEach>
								</s:else>
							</tbody>
						</table>
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
						WdatePicker ({
						eCont : 'date2' ,
						dateFmt : 'yyyy-MM-dd HH:mm:ss' ,
						skin : 'blue'
						});
					</script>
				</div>
				<div class="addright">
					<p class="drt clearfix">
						<span class="gray">注意：没有待办任务的日期不再下面显示</span>
					</p>
					<s:if test="jobTodoList.size > 0">
						<s:iterator value="jobTodoList" var="entity" status="s">
							<div class="addbox">
								<div class="addtitle">${entity.jobName }</div>
								<div class="addbox_content">
									<p class="gray">${entity.jobContent }</p>
								</div>
							</div>
						</s:iterator>
					</s:if>
				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>