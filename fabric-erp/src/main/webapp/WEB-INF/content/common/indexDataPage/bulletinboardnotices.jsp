<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">

function searchForContent() {
	$.ajax({
	url : '${vix}/common/vixIndexDataAction!goBulletInboardNotices.action?searchContent=' + $("#searchContent").val(),
	cache : false,
	success : function(html) {
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
				<li><a href="#"><img src="img/bulletinboardnotices.png" alt="" />工作台</a></li>
				<li><a href="#">通知公告</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop"></div>
</div>
<!-- sub menu -->
<div class="content">
	<div id="c_head" class="drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>
			<label>搜索内容:<input name="searchContent" type="text" id="searchContent" class="int" /></label> <label><input name="" type="button" class="btn" value="搜索" onclick="searchForContent();" /><input name="" type="button" class="btn" value="重置" /></label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="right">
			<div id="newtab1">
				<div class="addleft">
					<div class="addbtn">
						<p>
							<img src="img/bulletinboardnotices.png" /> 通知公告
						</p>
					</div>
					<div id="date1" class="date_box"></div>
					<script type="text/javascript">
						WdatePicker({
                        eCont : 'date1',
                        dateFmt : 'yyyy-MM-dd',
                        skin : 'blue'
                        });
					</script>
				</div>
				<div class="addright" id="jobtodo">
					<div class="daily_box">
						<h4 class="blue">通知</h4>
						<table class="list">
							<tbody>
								<s:if test="announcementNotificationList.size > 0">
									<s:iterator value="announcementNotificationList" var="entity" status="s">
										<tr>
											<td><a href="#" onclick="saveOrUpdate('${entity.id}');">${entity.title}</a> <s:date format="yyyy-MM-dd HH:mm:ss" name="%{#entity.createTime}" /></td>
										</tr>
									</s:iterator>
								</s:if>
								<s:else>
									<c:forEach begin="1" end="5">
										<tr>
											<td>&nbsp;</td>
										</tr>
									</c:forEach>
								</s:else>
							</tbody>
						</table>
						<h4 class="blue">公告</h4>
						<table class="list">
							<tbody>
								<s:if test="announcementNotificationList.size > 0">
									<s:iterator value="announcementNotificationList" var="announcementNotification" status="s">
										<tr>
											<td><a href="#">${announcementNotification.title}</a> <s:date format="yyyy-MM-dd HH:mm:ss" name="%{#announcementNotification.createTime}" /></td>
										</tr>
									</s:iterator>
								</s:if>
								<s:else>
									<c:forEach begin="1" end="5">
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
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>
<!-- content -->