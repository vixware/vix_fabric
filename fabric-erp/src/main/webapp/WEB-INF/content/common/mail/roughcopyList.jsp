<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$(".sub_menu .drop>ul>li").hover(function() // sub_menu菜单项的鼠标移入操作 
	{
		$(this).addClass("sub_menu_hover");
	}, function() // sub_menu菜单项的鼠标移出操作 
	{
		$(this).removeClass("sub_menu_hover");
	});
	var name = "";
	function loadName() {
		name = $('#nameS').val();
		name = Url.encode(name);
		name = Url.encode(name);
	}
	pager("start", "${vix}/common/mailAction!goRoughCopyList.action?name=" + name + "&pageNo=${pageNo}", 'mail');

	function orderBy(orderField) {
		loadName();
		var orderBy = $("#mailInfoOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/common/mailAction!goSingleList.action?orderField=" + orderField + "&orderBy=" + orderBy + "&name=" + name, 'mail');
	}

	bindSearch();
	function currentPager(tag) {
		loadName();
		pager(tag, "${vix}/common/mailAction!goSingleList.action?name=" + name, 'mail');
	}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="img/icon_14.gif" alt="" />打印</a> <a href="#" id="help"><img src="img/icon_15.gif" alt="" />帮助</a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/sys_mail.png" class="png" alt="" width="26" height="26" />&nbsp;工作台</a></li>
				<li><a href="#">邮件</a></li>
			</ul>
		</div>
	</h2>
</div>
<!-- sub menu -->
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left">
			<div class="mail_left">
				<ul class="mail_nav">
					<li class="compose" style="vertical-align: middle;"><a href="#" onclick="loadContent('${vix}/common/mailAction!goMailEdit.action');"><img alt="" src="img/mail_1.gif" />&nbsp;&nbsp;写信</a></li>
					<li class="check"><a href="#" onclick="loadContent('${vix}/common/mailAction!goMailList.action');"><img alt="" src="img/mail_2.gif" />&nbsp;&nbsp;收信</a></li>
					<li class="addr"><a href="#" onclick="loadContent('${vix}/common/mailAction!goMailList.action');"><img alt="" src="img/mail_3.gif" />&nbsp;&nbsp;通讯录</a></li>
				</ul>
				<ul class="mail_link">
					<li><a class="current" href="#" onclick="loadContent('${vix}/common/mailAction!goMailList.action');">收件箱</a></li>
					<li><a href="#">星标邮件</a></li>
					<li><a href="#" onclick="loadContent('${vix}/common/mailAction!goRoughCopy.action');">草稿箱</a></li>
					<li><a href="#" onclick="loadContent('${vix}/common/mailAction!goHasBeenSent.action');">已发送</a></li>
					<li><a href="#">已删除</a></li>
					<li><a href="#">垃圾箱</a></li>
				</ul>
				<ul class="mail_link">
					<li><a href="#">其他邮件</a></li>
				</ul>
			</div>
		</div>
		<div id="right">
			<div class="right_content" style="border: none;">
				<div class="mail_title">
					收件箱<span class="gray">（共${pager.totalCount}封）</span>
				</div>
				<div class="pagelist drop clearfix mail_tool">
					<div class="l">
						<a href="javascript:;" class="abtn"><span>删除</span></a> <a href="javascript:;" class="abtn"><span>彻底删除</span></a> <a href="javascript:;" class="abtn"><span>转发</span></a> <a href="javascript:;" class="abtn"><span>举报</span></a> <a href="javascript:;" class="abtn"><span>全部标记为已读</span></a>
					</div>
					<div class="mail_menu">
						<a href="#"><span>标记为</span></a>
						<ul style="display: none;">
							<li><a href="#">已读邮件</a></li>
							<li><a href="#">未读邮件</a></li>
							<li><a href="#">标星邮件</a></li>
							<li><a href="#">取消标星</a></li>
						</ul>
					</div>
					<div class="mail_menu">
						<a href="#"><span>移动到</span></a>
						<ul style="display: none;">
							<li><a href="#">收件箱</a></li>
							<li><a href="#">发件箱</a></li>
							<li><a href="#">草稿箱</a></li>
							<li><a href="#">已删除</a></li>
							<li><a href="#">垃圾箱</a></li>
							<li><a href="#">回收站</a></li>
						</ul>
					</div>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="mailInfo"></b> <s:text name='cmn_to' /> <b class="mailTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="mail" class="table"></div>
				<div class="pagelist drop clearfix mail_tool">
					<div class="l">
						<a href="javascript:;" class="abtn"><span>删除</span></a> <a href="javascript:;" class="abtn"><span>彻底删除</span></a> <a href="javascript:;" class="abtn"><span>转发</span></a> <a href="javascript:;" class="abtn"><span>全部标记为已读</span></a>
					</div>
					<div class="mail_menu mail_menu_bottom">
						<a href="#"><span>标记为</span></a>
						<ul style="display: none;">
							<li><a href="#">已读邮件</a></li>
							<li><a href="#">未读邮件</a></li>
							<li><a href="#">标星邮件</a></li>
							<li><a href="#">取消标星</a></li>
						</ul>
					</div>
					<div class="mail_menu mail_menu_bottom">
						<a href="#"><span>移动到</span></a>
						<ul style="display: none;">
							<li><a href="#">收件箱</a></li>
							<li><a href="#">发件箱</a></li>
							<li><a href="#">草稿箱</a></li>
							<li><a href="#">已删除</a></li>
							<li><a href="#">垃圾箱</a></li>
							<li><a href="#">回收站</a></li>
						</ul>
					</div>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="mailInfo"></b> <s:text name='cmn_to' /> <b class="mailTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
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