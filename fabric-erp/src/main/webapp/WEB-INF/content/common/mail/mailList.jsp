<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$(function() {
		$('.mail_link li a').click(function() {
			$("a.current").removeClass();
			$(this).addClass("current");
		})
	});
	$(".mail_menu").hover(function() {
		$(this).addClass("sub_menu_hover");
		$("ul", this).stop().slideDown(200);
	}, function() {
		$(this).removeClass("sub_menu_hover");
		$("ul", this).stop().slideUp(200);
	});

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
	pager("start", "${vix}/common/mailAction!goSingleList.action?name=" + name, 'mailFile');

	function orderBy(orderField) {
		loadName();
		var orderBy = $("#mailInfoOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/common/mailAction!goSingleList.action?orderField=" + orderField + "&orderBy=" + orderBy + "&name=" + name, 'mailFile');
	}

	bindSearch();
	function currentPager(tag) {
		loadName();
		pager(tag, "${vix}/common/mailAction!goSingleList.action?name=" + name, 'mailFile');
	}

	function categoryTab(num, befor, id, e, entity) {
		var el = e.target ? e.target.parentNode : e.srcElement.parentNode;
		var pa = el.parentNode.getElementsByTagName("li");
		for (var i = 0; i < pa.length; i++) {
			pa[i].className = "";
		}
		el.className = "current";
		for (i = 1; i <= num; i++) {
			try {
				if (i == befor) {
					document.getElementById(id + i).style.display = "block";
				} else {
					document.getElementById(id + i).style.display = "none";
				}
			} catch (e) {
			}
		}
		if (entity != undefined) {
			categoryPager('start', entity);
		}
	}
	function categoryPager(tag, entity) {
		if (entity == 'mailFile') {
			pager(tag, "${vix}/common/mailAction!goSingleList.action?1=1", entity);
		}
		if (entity == 'directory') {
			pager(tag, "${vix}/common/mailAction!goDirectoryList.action?1=1", entity);
		}
		if (entity == 'asteriskmail') {
			pager(tag, "${vix}/common/mailAction!goAsteriskMailList.action?1=1", entity);
		}
		if (entity == 'draftbox') {
			pager(tag, "${vix}/common/mailAction!goRoughCopyList.action?1=1", entity);
		}
		if (entity == 'hasBeenSent') {
			pager(tag, "${vix}/common/mailAction!goHasBeenSentList.action?1=1", entity);
		}
		if (entity == 'hasdelete') {
			pager(tag, "${vix}/common/mailAction!goHasDeleteList.action?1=1", entity);
		}
		if (entity == 'dustbin') {
			pager(tag, "${vix}/common/mailAction!goDustbinList.action?1=1", entity);
		}
	}

	$(".recently .commont_list_more a").click(function() {
		$(".recently ul").height("auto");
		$(".recently .commont_list_more").hide();
	});
	$(".commont_tree .commont_fl_btn a").click(function() {
		$(this).toggleClass("open");
		$(this).parents(".commont_tree").find("ul").toggle();
	});
	$(".clearRecently").click(function() {
		$(".recently").remove();
	});
	$("#a1 .checkmail").click(function() {
		$("#toMail").val($("#toMail").val() + $(this).attr("rel") + ";");
	});
	$("#a2 .checkmail").click(function() {
		$("#check2").val($("#check2").val() + $(this).attr("rel") + ";");
	});
	$(".mail_menu").hover(function() {
		$(this).addClass("sub_menu_hover");
		$("ul", this).stop().slideDown(200);
	}, function() {
		$(this).removeClass("sub_menu_hover");
		$("ul", this).stop().slideUp(200);
	});

	function saveOrUpdateMailInfo() {
		$("#mailInfoForm").ajaxSubmit({
		type : "post",
		url : "${vix}/common/mailAction!saveOrUpdate.action",
		dataType : "text",
		success : function(result) {
			showMessage(result);
			setTimeout("", 1000);
			categoryPager("start", "mailFile");
		}
		});
	}
	$("#mailInfoForm").validationEngine();
</script>
<style>
#mail_link_1 li a {
	display: block;
	line-height: 28px;
	font-size: 14px;
	font-weight: bold;
	color: #000;
}
</style>
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
				<ul class="mail_link" id="mail_link_1">
					<li class="compose"><a href="#" onclick="javascript:tab(15,1,'a',event);"><img alt="" src="img/mail_1.gif" />&nbsp;&nbsp;写信</a></li>
					<li class="check"><a href="#" onclick="javascript:categoryTab(15,2,'a',event,'mailFileFile');"><img alt="" src="img/mail_2.gif" />&nbsp;&nbsp;收信</a></li>
					<li class="addr"><a href="#" onclick="javascript:categoryTab(15,3,'a',event,'directory');"><img alt="" src="img/mail_3.gif" />&nbsp;&nbsp;通讯录</a></li>
				</ul>
				<ul class="mail_link">
					<li><a href="#" onclick="javascript:categoryTab(15,2,'a',event,'mailFile');">收件箱</a></li>
					<li><a href="#" onclick="javascript:categoryTab(15,4,'a',event,'asteriskmail');">星标邮件</a></li>
					<li><a href="#" onclick="javascript:categoryTab(15,5,'a',event,'draftbox');">草稿箱</a></li>
					<li><a href="#" onclick="javascript:categoryTab(15,6,'a',event,'hasBeenSent');">已发送</a></li>
					<li><a href="#" onclick="javascript:categoryTab(15,7,'a',event,'hasdelete');">已删除</a></li>
					<li><a href="#" onclick="javascript:categoryTab(15,8,'a',event,'dustbin');">垃圾箱</a></li>
				</ul>
				<ul class="mail_link">
					<li><a href="#">其他邮件</a></li>
				</ul>
			</div>
		</div>
		<div id="right">
			<div class="right_content" style="border: 0;" id="a1">
				<form id="mailInfoForm" method="post" theme="simple" enctype="multipart/form-data">
					<input type="hidden" id="id" name="mailInfo.id" value="${mailInfo.id}" />
					<div class="commont_box">
						<div class="commont">
							<div class="commont_title">联系人</div>
							<div class="commont_search">
								<input type="button" class="lm_sopen" />
								<div>
									<input type="text" value="" class="txt" />
								</div>
							</div>
							<div class="commont_list">
								<div class="commont_list_t">
									<a href="#" class="r clearRecently">[清空]</a>最近联系人
								</div>
								<div class="commont_fl recently">
									<ul>
										<s:iterator value="wabList" var="entity" status="s">
											<li><a class="checkmail" href="javascript:;" rel="${entity.email }">${entity.name }</a></li>
										</s:iterator>
									</ul>
									<div class="commont_list_more">
										<a href="javascript:;">显示全部↓</a>
									</div>
								</div>
								<div class="commont_list_t">邮箱联系人</div>
								<div class="commont_fl recently">
									<ul>
										<s:iterator value="wabList" var="entity" status="s">
											<li><a class="checkmail" href="javascript:;" rel="${entity.email }">${entity.name }</a></li>
										</s:iterator>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="mail_box">
						<table class="mail_edit">
							<tr>
								<td width="60"></td>
								<td></td>
							</tr>
							<tr>
								<td colspan="2">
									<div class="pagelist drop clearfix mail_tool">
										<div class="l">
											<a href="javascript:;" onclick="saveOrUpdateMailInfo();" class="abtn"><span>发送</span></a> <a href="javascript:;" class="abtn"><span>存草稿</span></a> <a href="javascript:;" class="abtn"><span>定时发送</span></a> <a href="javascript:;" class="abtn"><span>关闭</span></a>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td width="60" class="tr">收件人：</td>
								<td><input id="toMail" name="mailInfo.toMail" value="${mailInfo.toMail }" type="text" size="30" class="ipt"></td>
							</tr>
							<tr>
								<td width="60" class="tr">抄送：</td>
								<td><input name="" type="text" class="ipt" /></td>
							</tr>
							<tr>
								<td width="60" class="tr">密送：</td>
								<td><input name="" type="text" class="ipt" /></td>
							</tr>
							<tr>
								<td width="60" class="tr">主题：</td>
								<td><input id="subject" name="mailInfo.subject" value="${mailInfo.subject }" type="text" size="30" class="ipt"></td>
							</tr>
							<tr>
								<td width="60" class="tr">附件：</td>
								<td><input type="file" id="fileToUpload" name="fileToUpload" style="width: 175px;" /></td>
							</tr>
							<tr>
								<td width="60" class="tr">正文：</td>
								<td><textarea name="mailInfo.textMsg" id="textMsg" cols="" rows="" style="width: 100%; height: 200px;" class="ipt"></textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
									var textMsg = KindEditor.create('textarea[name="textMsg"]', {
									basePath : '${vix}/plugin/KindEditor/',
									height : 200,
									cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
									uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
									fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
									allowFileManager : true
									});
								</script></td>
							</tr>
							<tr>
								<td></td>
								<td>
									<div class="mail_edit_check">
										<label><input name="" type="checkbox" value="" checked="checked" />保存到"已发送"</label> <label><input name="" type="checkbox" value="" />紧急</label> <label><input name="" type="checkbox" value="" />需要回执纯文本</label> <label><input name="" type="checkbox" value="" />使用信纸</label> <label><input name="" type="checkbox" value="" />短信提醒我</label> <label><input
											name="" type="checkbox" value="" />对邮件加密</label>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div class="pagelist drop clearfix mail_tool">
										<div class="l">
											<a href="javascript:;" class="abtn"><span>发送</span></a> <a href="javascript:;" class="abtn"><span>存草稿</span></a> <a href="javascript:;" class="abtn"><span>关闭</span></a>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</form>
			</div>
			<div class="right_content" id="a2" style="border: 0; display: none;">
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
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="mailFileInfo"></b> <s:text name='cmn_to' /> <b class="mailFileTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="mailFile" class="table"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="mailFileInfo"></b> <s:text name='cmn_to' /> <b class="mailFileTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
			</div>
			<div class="right_content" id="a3" style="border: 0; display: none;">
				<div class="mail_title">通讯录</div>
				<div class="pagelist drop clearfix mail_tool">
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="directoryInfo"></b> <s:text name='cmn_to' /> <b class="directoryTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="directory" class="table"></div>
			</div>
			<div class="right_content" id="a4" style="border: 0; display: none;">
				<div class="mail_title">
					星标邮件<span class="gray">（共${pager.totalCount}封）</span>
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
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="asteriskmailInfo"></b> <s:text name='cmn_to' /> <b class="asteriskmailTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="asteriskmail" class="table"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="asteriskmailInfo"></b> <s:text name='cmn_to' /> <b class="asteriskmailTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
			</div>
			<div class="right_content" id="a5" style="border: 0; display: none;">
				<div class="mail_title">草稿箱</div>
				<div class="pagelist drop clearfix mail_tool">
					<div class="l">
						<a href="javascript:;" class="abtn"><span>删除草稿</span></a> <a href="javascript:;" class="abtn"><span>转发</span></a> <a href="javascript:;" class="abtn"><span>全部标记为已读</span></a>
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
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="draftboxInfo"></b> <s:text name='cmn_to' /> <b class="draftboxTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="draftbox" class="table"></div>
				<div class="pagelist drop clearfix mail_tool">
					<div class="l">
						<a href="javascript:;" class="abtn"><span>删除草稿</span></a> <a href="javascript:;" class="abtn"><span>转发</span></a> <a href="javascript:;" class="abtn"><span>全部标记为已读</span></a>
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
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="draftboxInfo"></b> <s:text name='cmn_to' /> <b class="draftboxTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
			</div>
			<div class="right_content" id="a6" style="border: 0; display: none;">
				<div class="mail_title">已发送</div>
				<div class="pagelist drop clearfix mail_tool">
					<div class="l">
						<a href="javascript:;" class="abtn"><span>转发</span></a> <a href="javascript:;" class="abtn"><span>全部标记为已读</span></a>
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
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="hasBeenSentInfo"></b> <s:text name='cmn_to' /> <b class="hasBeenSentTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="hasBeenSent" class="table"></div>
				<div class="pagelist drop clearfix mail_tool">
					<div class="l">
						<a href="javascript:;" class="abtn"><span>转发</span></a> <a href="javascript:;" class="abtn"><span>全部标记为已读</span></a>
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
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="hasBeenSentInfo"></b> <s:text name='cmn_to' /> <b class="hasBeenSentTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
			</div>
			<div class="right_content" id="a7" style="border: 0; display: none;">
				<div class="mail_title">已删除</div>
				<div class="pagelist drop clearfix mail_tool">
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
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="hasdeleteInfo"></b> <s:text name='cmn_to' /> <b class="hasdeleteTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="hasdelete" class="table"></div>
				<div class="pagelist drop clearfix mail_tool">
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
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="hasdeleteInfo"></b> <s:text name='cmn_to' /> <b class="hasdeleteTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
			</div>
			<div class="right_content" id="a8" style="border: 0; display: none;">
				<div class="mail_title">垃圾箱</div>
				<div class="pagelist drop clearfix mail_tool">
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
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="dustbinInfo"></b> <s:text name='cmn_to' /> <b class="dustbinTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="dustbin" class="table"></div>
				<div class="pagelist drop clearfix mail_tool">
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
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="dustbinInfo"></b> <s:text name='cmn_to' /> <b class="dustbinTotalCount"></b>)
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