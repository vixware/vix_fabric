<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
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
	})
	$("#a2 .checkmail").click(function() {
		$("#check2").val($("#check2").val() + $(this).attr("rel") + ";");
	})
	$(".mail_menu").hover(function() {
		$(this).addClass("sub_menu_hover");
		$("ul", this).stop().slideDown(200);
	}, function() {
		$(this).removeClass("sub_menu_hover");
		$("ul", this).stop().slideUp(200);
	});

	function saveOrUpdateMailInfo() {
		if ($('#mailInfoForm').validationEngine('validate')) {
			$.post('${vix}/common/mailAction!saveOrUpdate.action', {
			'mailInfo.id' : $("#id").val(),
			'mailInfo.toMail' : $("#toMail").val(),
			'mailInfo.subject' : $("#subject").val(),
			'mailInfo.textMsg' : $("#textMsg").html()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/common/mailAction!goMailEdit.action');
			});
		}
	}
	$("#mailInfoForm").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="${vix}/common/img/icon_14.gif" alt="" />打印</a> <a href="#" id="help"><img src="${vix}/common/img/icon_15.gif" alt="" />帮助</a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/sys_mail.png" class="png" alt="" width="26" height="26" />&nbsp;工作台</a></li>
				<li><a href="#">邮件</a></li>
			</ul>
		</div>
	</h2>
</div>
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
			<div class="right_menu">
				<ul>
					<li class="current"><a onclick="javascript:tab(2,1,'a',event)"><img alt="" src="${vix}/common/img/mail.png">普通邮件</a></li>
					<li class=""><a onclick="javascript:tab(2,2,'a',event)"><img alt="" src="${vix}/common/img/mail.png">群邮件</a></li>
				</ul>
			</div>
			<div class="right_content" style="border: 0;" id="a1">
				<form id="mailInfoForm">
					<input type="hidden" id="id" name="id" value="${mailInfo.id}" />
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
										<li><a class="checkmail" href="javascript:;" rel="307368425@qq.com">联系人姓名</a></li>
									</ul>
									<div class="commont_list_more">
										<a href="javascript:;">显示全部↓</a>
									</div>
								</div>
								<div class="commont_list_t">邮箱联系人</div>
								<div class="commont_fl commont_tree">
									<div class="commont_fl_btn">
										<a>朋友</a>
									</div>
									<ul>
										<li><a class="checkmail" href="javascript:;" rel="307368425@qq.com">联系人姓名</a></li>
									</ul>
								</div>
								<div class="commont_fl commont_tree">
									<div class="commont_fl_btn">
										<a>同事</a>
									</div>
									<ul>
										<li><a class="checkmail" href="javascript:;" rel="307368425@qq.com">联系人姓名</a></li>
									</ul>
								</div>
								<div class="commont_fl commont_tree">
									<div class="commont_fl_btn">
										<a>同学</a>
									</div>
									<ul>
										<li><a class="checkmail" href="javascript:;" rel="307368425@qq.com">联系人姓名</a></li>
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
								<td><input id="toMail" name="toMail" value="${mailInfo.toMail }" type="text" size="30" class="ipt"></td>
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
								<td><input id="subject" name="subject" value="${mailInfo.subject }" type="text" size="30" class="ipt"></td>
							</tr>
							<tr>
								<td width="60" class="tr">正文：</td>
								<td><textarea name="textMsg" id="textMsg" cols="" rows="" style="width: 100%; height: 200px;" class="ipt"></textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
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
			<div class="right_content" style="border: 0; display: none;" id="a2">
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
									<li><a class="checkmail" href="javascript:;" rel="联系人姓名&lt;276106549@qq.com&gt;">联系人姓名</a></li>
								</ul>
								<div class="commont_list_more">
									<a href="javascript:;">显示全部↓</a>
								</div>
							</div>
							<div class="commont_list_t">邮箱联系人</div>
							<div class="commont_fl commont_tree">
								<div class="commont_fl_btn">
									<a>朋友</a>
								</div>
								<ul>
									<s:iterator value="trendsList" var="entity" status="s">
										<li><a class="checkmail" href="javascript:;" rel="${entity.title}&lt;${entity.title}&gt;">${entity.title}</a></li>
									</s:iterator>
								</ul>
							</div>
							<div class="commont_fl commont_tree">
								<div class="commont_fl_btn">
									<a>同事</a>
								</div>
								<ul>
									<li><a class="checkmail" href="javascript:;" rel="联系人姓名&lt;276106549@qq.com&gt;">联系人姓名</a></li>
								</ul>
							</div>
							<div class="commont_fl commont_tree">
								<div class="commont_fl_btn">
									<a>同学</a>
								</div>
								<ul>
									<li><a class="checkmail" href="javascript:;" rel="联系人姓名&lt;276106549@qq.com&gt;">联系人姓名</a></li>
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
										<a href="javascript:;" class="abtn"><span>发送</span></a> <a href="javascript:;" class="abtn"><span>存草稿</span></a> <a href="javascript:;" class="abtn"><span>定时发送</span></a> <a href="javascript:;" class="abtn"><span>关闭</span></a>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td width="60" class="tr">发送群：</td>
							<td><input name="" type="text" class="ipt" id="check2" /></td>
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
							<td><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="60" class="tr"></td>
							<td>
								<div class="mail_editbtn">
									<a href="javascript:;" onclick="$('#mailFile').click()"><img src="${vix}/common/img/mail_9.png" />添加附件</a> <a href="javascript:;" onclick="$('#mailBigFile').click()"><img src="${vix}/common/img/mail_10.png" />超大附件</a> <a href="javascript:;" onclick="$('#mailImgFile').click()"><img src="${vix}/common/img/mail_11.png" />图片</a> <a
										href="javascript:;"><img src="${vix}/common/img/mail_13.png" />截屏</a> <a href="javascript:;"><img src="${vix}/common/img/mail_16.png" />网盘</a> <a href="javascript:;"><img src="${vix}/common/img/mail_12.png" />表情</a> <a href="javascript:;"><img src="${vix}/common/img/mail_14.png" />音乐</a> <a href="javascript:;"><img
										src="${vix}/common/img/mail_15.png" />文字格式↑</a>
								</div> <input type="file" id="mailFile" style="display: none;" /> <input type="file" id="mailBigFile" style="display: none;" /> <input type="file" id="mailImgFile" style="display: none;" />
							</td>
						</tr>
						<tr>
							<td width="60" class="tr">正文：</td>
							<td><div class="areabox">
									<textarea name="" id="area2" cols="" rows="" style="width: 100%; height: 200px;"></textarea>
								</div> <script>
									$(function() {
										$('#area2').width($('.areabox:eq(0)').width());
										new nicEditor().panelInstance('area2');

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
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>