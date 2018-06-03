<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function saveOrUpdate(id) {
		$.ajax({
		url : '${vix}/oa/task/taskDefinition/taskDefineAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_shop.png" alt="" />工作台 </a></li>
				<li><a href="#">任务中心 </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop"></div>
</div>

<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img alt="" src="img/icon_11.png"> </b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_mode" /> </a>
				<ul>
					<li><a href="#" onclick="saleOrderStatus('0')"><img alt="" src="img/uncommitted.png"> <s:text name="cmn_uncommitted" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('1')"><img alt="" src="img/unaudited.png"> <s:text name="cmn_unapproved" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('2')"><img alt="" src="img/verifying.png"> <s:text name="cmn_approval_in" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('3')"><img alt="" src="img/approved.png"> <s:text name="cmn_approval" /> </a></li>
				</ul></li>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="por_left" class="np_l_l mail_left">
			<div class="addtitle " style="border: 1px solid #B4B4B4; border-bottom: 0;">
				<span class="l"><img src="img/task.png" style="vertical-align: middle;" /> 任务中心</span>
			</div>
			<ul class="datelist">
				<li id="lnp_btn1" class="clearfix on_lnpbtn" onclick="javascript:tabBig(5,1,'lnp','lnp_btn','on_lnpbtn')"><img src="img/today.png" />今日</li>
				<li id="lnp_btn2" class="clearfix" onclick="javascript:tabBig(5,2,'lnp','lnp_btn','on_lnpbtn')"><img src="img/task_list.png" />任务列表</li>
				<li id="lnp_btn3" class="clearfix" onclick="javascript:tabBig(5,3,'lnp','lnp_btn','on_lnpbtn')"><img src="img/project_task.png" />项目任务</li>
				<li id="lnp_btn4" class="clearfix" onclick="javascript:tabBig(5,4,'lnp','lnp_btn','on_lnpbtn')"><img src="img/shortcut.png" />接下来</li>
				<li id="lnp_btn5" class="clearfix" onclick="javascript:tabBig(5,5,'lnp','lnp_btn','on_lnpbtn')"><img src="img/clock.png" />倒计时</li>
			</ul>
		</div>
		<div id="por_right" class="np_l_r clearfix" style="position: relative;">
			<div id="lnp1">
				<div class="addtitle taskmenutitle clearfix">
					<span class="l"><img src="img/today.png" style="vertical-align: middle;" /> 今日（${tasksize }）</span>
				</div>
				<div class="task">
					<s:if test="vixTaskList.size > 0">
						<s:iterator value="vixTaskList" var="entity" status="s">
							<div class="taskitem">
								<table>
									<tr>
										<td width="30" class="tc"><img src="img/d-16.png" /></td>
										<td><a href="#" onclick="saveOrUpdate('${entity.id}');"><p>${entity.questName }</p>
												<p class="gray">${entity.taskDescription }</p></a></td>
										<td width="50" class="tc"><a href="#" onclick="saveOrUpdate('${entity.id}');"><img src="img/task03.png" width="24" height="24" /><br /> 查看</a></td>
										<td width="50" class="tc"><a href="#"><img src="img/task04.png" width="24" height="24" /><br /> 删除</a></td>
									</tr>
								</table>
							</div>
						</s:iterator>
					</s:if>
				</div>
			</div>
			<div id="lnp2" style="display: none;">
				<div class="addtitle taskmenutitle clearfix">
					<span class="l"><img src="img/task_list.png" style="vertical-align: middle;" /> 任务列表（${allTasksize }）</span>
				</div>
				<div class="task">
					<s:if test="allVixTaskList.size > 0">
						<s:iterator value="allVixTaskList" var="entity" status="s">
							<div class="taskitem">
								<table>
									<tr>
										<td width="30" class="tc"><img src="img/d-16.png" /></td>
										<td><a href="#" onclick="saveOrUpdate('${entity.id}');"><p>${entity.questName }</p>
												<p class="gray">${entity.taskDescription }</p></a></td>
										<td width="50" class="tc"><a href="#" onclick="saveOrUpdate('${entity.id}');"><img src="img/task03.png" width="24" height="24" /><br /> 查看</a></td>
										<td width="50" class="tc"><a href="#"><img src="img/task04.png" width="24" height="24" /><br /> 删除</a></td>
									</tr>
								</table>
							</div>
						</s:iterator>
					</s:if>
				</div>
			</div>
			<div id="lnp3" style="display: none;">
				<div class="addtitle taskmenutitle clearfix">
					<span class="l"><img src="img/project_task.png" style="vertical-align: middle;" />项目任务</span>
				</div>
				<ul class="fnlist">
					<li><a href="#"><img src="img/fn_01.png" width="70" height="70" />功能文字</a></li>
					<li><a href="#"><img src="img/fn_02.png" width="70" height="70" />功能文字</a></li>
					<li><a href="#"><img src="img/fn_03.png" width="70" height="70" />功能文字</a></li>
					<li><a href="#"><img src="img/fn_04.png" width="70" height="70" />功能文字</a></li>
					<li><a href="#"><img src="img/fn_05.png" width="70" height="70" />功能文字</a></li>
					<li><a href="#"><img src="img/fn_06.png" width="70" height="70" />功能文字</a></li>
					<li><a href="#"><img src="img/fn_07.png" width="70" height="70" />功能文字</a></li>
					<li><a href="#"><img src="img/fn_08.png" width="70" height="70" />功能文字</a></li>
					<li><a href="#"><img src="img/fn_09.png" width="70" height="70" />功能文字</a></li>
					<li><a href="#"><img src="img/fn_10.png" width="70" height="70" />功能文字</a></li>
					<li><a href="#"><img src="img/fn_11.png" width="70" height="70" />功能文字</a></li>
				</ul>
			</div>
			<div id="lnp4" style="display: none;">
				<div class="addtitle taskmenutitle clearfix">
					<span class="l"><img src="img/shortcut.png" style="vertical-align: middle;" />接下来</span>
				</div>
				<ul class="fnlist">
					<li><a href="#"><img src="img/fn_01.png" width="70" height="70" />功能文字</a></li>
					<li><a href="#"><img src="img/fn_02.png" width="70" height="70" />功能文字</a></li>
					<li><a href="#"><img src="img/fn_03.png" width="70" height="70" />功能文字</a></li>
					<li><a href="#"><img src="img/fn_04.png" width="70" height="70" />功能文字</a></li>
					<li><a href="#"><img src="img/fn_05.png" width="70" height="70" />功能文字</a></li>
					<li><a href="#"><img src="img/fn_06.png" width="70" height="70" />功能文字</a></li>
					<li><a href="#"><img src="img/fn_07.png" width="70" height="70" />功能文字</a></li>
					<li><a href="#"><img src="img/fn_08.png" width="70" height="70" />功能文字</a></li>
					<li><a href="#"><img src="img/fn_09.png" width="70" height="70" />功能文字</a></li>
					<li><a href="#"><img src="img/fn_10.png" width="70" height="70" />功能文字</a></li>
					<li><a href="#"><img src="img/fn_11.png" width="70" height="70" />功能文字</a></li>
				</ul>
			</div>
			<div id="lnp5" style="display: none;">
				<div class="addtitle taskmenutitle clearfix">
					<span class="l"><img src="img/clock.png" style="vertical-align: middle;" /> 倒计时</span>
				</div>
				<ul class="tasktime clearfix">
					<li>
						<div class="tasktimebox">
							<div class="tasktimetitle">
								<span class="r">还有</span><span class="l">距离</span><b>建团62周年庆祝活动</b>
							</div>
							<div class="taskdate">
								<strong>123</strong>天
							</div>
							<span class="taskhour"><img src="img/list_icon_10.gif" />23小时22分19秒</span>
							<div class="taskendtime">结束日：2012年11月02日 星期五</div>
						</div>
					</li>
					<li>
						<div class="tasktimebox">
							<div class="tasktimetitle">
								<span class="r">还有</span><span class="l">距离</span><b>建团62周年庆祝活动</b>
							</div>
							<div class="taskdate">
								<strong>123</strong>天
							</div>
							<span class="taskhour"><img src="img/list_icon_10.gif" />23小时22分19秒</span>
							<div class="taskendtime">结束日：2012年11月02日 星期五</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>