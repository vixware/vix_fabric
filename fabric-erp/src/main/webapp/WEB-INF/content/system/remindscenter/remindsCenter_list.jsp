<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function categoryPager(tag, entity) {
		if (entity == 'tasklist') {
			$.ajax({
			url : '${vix}/system/remindsCenterAction!goList.action',
			cache : false,
			success : function(html) {
			}
			});
		}
	}
	function deleteById(id) {
		asyncbox.confirm('确定要删除该提醒吗?', '提示信息', function(action) {
			if (action == 'ok') {
				$.ajax({
				url : '${vix}/system/remindsCenterAction!deleteAlertNoticeById.action?id=' + id,
				cache : false,
				success : function(html) {
					asyncbox.success(html, "<s:text name='cmn_message'/>", function(action) {
						$.ajax({
						url : '${vix}/system/remindsCenterAction!goList.action',
						cache : false,
						success : function(html) {
							$("#mainContent").html(html);
						}
						});
					});
				}
				});
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
				<li><a href="#"><img src="${vix}/common/img/Alert_11.png" alt="" />系统管理 </a></li>
				<li><a href="#">预警中心 </a></li>
				<li><a href="#">提醒中心 </a></li>
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
		<div id="left">
			<div class="taskmenu" style="margin-top: 5px;">
				<div class="addtitle taskmenutitle">
					<span class="l"><img src="img/exclamation.png" style="vertical-align: middle;" /> 提醒中心</span>
				</div>
				<ul class="taskmenulist clearfix">
					<li><a href="javascript:;" onclick="javascript:tab(6,1,'t',event,'')"><img src="img/today.png" />今日</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,2,'t',event,'tasklist')"><img src="img/datePicker.gif" />提醒列表</a></li>
				</ul>
			</div>
		</div>
		<div id="right" style="margin-top: 5px;">
			<div class="right_content" style="border: 0">
				<div id="t1">
					<div class="addtitle taskmenutitle clearfix">
						<span class="l"><img src="img/today.png" style="vertical-align: middle;" /> 今日</span>
					</div>
					<div class="task">
						<s:if test="vixTaskList.size > 0">
							<s:iterator value="vixTaskList" var="entity" status="s">
								<div class="taskitem">
									<table>
										<tbody>
											<tr>
												<td width="30" class="tc"><img src="img/d-16.png" /></td>
												<td>${entity.questName}</td>
												<td width="50" class="tc"><a href="#"><img src="img/task03.png" width="24" height="24" /><br /> 延期</a></td>
												<td width="50" class="tc"><a href="#"><img src="img/task04.png" width="24" height="24" /><br /> 删除</a></td>
											</tr>
										</tbody>
									</table>
								</div>
							</s:iterator>
						</s:if>
					</div>
				</div>
				<div id="t2" style="display: none;">
					<div class="addtitle taskmenutitle clearfix">
						<span class="l"><img src="img/task_list.png" style="vertical-align: middle;" /> 提醒列表</span>
					</div>
					<div class="task">
						<s:if test="alertNoticeList.size > 0">
							<s:iterator value="alertNoticeList" var="entity" status="s">
								<div class="taskitem">
									<table>
										<tbody>
											<tr>
												<td width="30" class="tc"><img src="img/d-16.png" /></td>
												<td><a href="#" onclick="loadContent('${vix}/purchase/purchasePlanAction!goList.action','bg_02');">${entity.content}</a></td>
												<td width="50" class="tc"><a href="#"><img src="img/task03.png" width="24" height="24" /><br /> 延期</a></td>
												<td width="50" class="tc"><a href="#" onclick="deleteById('${entity.id}');"><img src="img/task04.png" width="24" height="24" /><br /> 删除</a></td>
											</tr>
										</tbody>
									</table>
								</div>
							</s:iterator>
						</s:if>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>