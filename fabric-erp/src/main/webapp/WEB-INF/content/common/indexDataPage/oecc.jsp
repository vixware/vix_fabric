<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function chosedatabydate(onchangedate) {
		$.ajax({
		url : '${vix}/common/vixIndexDataAction!goOECC.action?onchangedate=' + onchangedate,
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
				<li><a href="#"><img src="img/sys_oecc.png" alt="" />工作台</a></li>
				<li><a href="#">日清管理</a></li>
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
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul class="c_head_tabbtn">
			<li class="current"><a href="javascript:void(0);" onclick="javascript:tab(8,1,'newtab',event)"><img alt="" src="img/icon_10.png">工作总结</a></li>
			<li><a href="javascript:void(0);" onclick="javascript:tab(8,2,'newtab',event)"><img alt="" src="img/icon_10.png">数据参考</a></li>
		</ul>
		<div>
			<label>搜索内容:<input name="" type="text" class="int" /></label> <label><input name="" type="button" class="btn" value="搜索" /><input name="" type="button" class="btn" value="重置" /></label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="right">
			<div id="newtab1">
				<div class="addleft">
					<div class="addbtn">
						<p>
							<img src="img/sys_oecc.png" />日清管理
						</p>
					</div>
					<div id="date1" class="date_box"></div>
					<script type="text/javascript">
						WdatePicker({
						eCont : 'date1',
						dateFmt : 'yyyy-MM-dd HH:mm:ss',
						skin : 'blue',
						onpicked : function(dp) {
							chosedatabydate(dp.cal.getDateStr());
						}
						});
					</script>
				</div>
				<div class="addright">
					<div class="daily_box">
						<h4 class="blue">任务</h4>
						<table class="list tc">
							<tbody>
								<tr>
									<th class="tl">任务主题</th>
									<th>今日创建</th>
									<th>需今日结束</th>
									<th>完成活推进</th>
									<th>结束</th>
								</tr>
								<s:if test="vixTaskList.size > 0">
									<s:iterator value="vixTaskList" var="entity" status="s">
										<tr>
											<td class="tl"><a href="#">${entity.questName}</a></td>
											<td><img alt="" src="img/uncommitted.png"></td>
											<td><img alt="" src="img/unaudited.png"></td>
											<td><img alt="" src="img/verifying.png"></td>
											<td><img alt="" src="img/approved.png"></td>
										</tr>
									</s:iterator>
								</s:if>
								<s:else>
									<c:forEach begin="1" end="10">
										<tr>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
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
							<img src="img/sys_oecc.png" />日清管理
						</p>
					</div>
					<div id="date2" class="date_box"></div>
					<script type="text/javascript">
						WdatePicker({
						eCont : 'date2',
						dateFmt : 'yyyy-MM-dd',
						skin : 'blue'
						});
					</script>
				</div>
				<div class="addright">
					<p class="drt clearfix">
						<span class="gray">注意：未写日报的日期不再下面显示</span>
					</p>
					<div class="addbox">
						<div class="addtitle">2009-10-28[星期三]</div>
						<div class="addbox_content">
							<p class="gray">上午注册了一个QQ和MSN并加入了同事的联系方式，另外看了咱们公司的资料，感觉CRM软件内容很多，想搞清除需要花费一番功夫。</p>
							<p class="gray">下午接收培训，通过培训对公司的基本概况有一个简要了解，例如公司名称及网址、公司Logo、公司产品、公司服务方式及服务对象、公司成长历史及在业界的地位等。有些都有助于我们在日后为客户服务过程中介绍公司。</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>