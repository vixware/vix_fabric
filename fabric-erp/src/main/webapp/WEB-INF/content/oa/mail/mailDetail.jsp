<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="${vix}/common/css/base/jquery.ui.all.css" rel="stylesheet">
<script type="text/javascript">
 
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
$(".mail_menu").hover(function(){
	$(this).addClass("sub_menu_hover");
	$("ul",this).stop().slideDown(200);
},function(){
	$(this).removeClass("sub_menu_hover");
	$("ul",this).stop().slideUp(200);
}); 
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="${vix}/common/img/icon_14.gif" alt="" />
			<s:text name="print" /></a> <a href="#" id="help"><img src="${vix}/common/img/icon_15.gif" alt="" />
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/oa_personal_office.png" alt="" /> <s:text name="oa_xtbg" /></a></li>
				<li><a href="#"><s:text name="oa_grbg" /></a></li>
				<li><a href="#"><s:text name="电子邮件" /></a></li>
				<li><a href="#"><s:text name="联系人" /></a></li>
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
					<li class="compose"><a href="#" onclick="loadContent('${vix}/oa/emailAction!goSingleList.action');">写信</a></li>
					<li class="check"><a href="#" onclick="loadContent('${vix}/oa/emailAction!goSaveOrUpdate.action');">收信</a></li>
					<li class="addr"><a href="#" onclick="loadContent('${vix}/oa/emailAction!goList.action');">联系人</a></li>
				</ul>
				<ul class="mail_link">
					<li class="addr"><a href="#" onclick="loadContent('${vix}/oa/emailAction!goList.action');">收件箱</a></li>
					<li><a href="#">星标邮件</a></li>
					<li><a href="#">草稿箱</a></li>
					<li><a href="#">已发送</a></li>
					<li><a href="#">已删除</a></li>
					<li><a href="#">垃圾箱</a></li>
				</ul>
				<ul class="mail_link">
					<li><a href="#">其他邮件</a></li>
				</ul>
			</div>

		</div>
		<div id="right">
			<div class="right_content" style="border: 0;">
				<div class="pagelist drop clearfix mail_tool">
					<div class="l">
						<a href="javascript:;" class="abtn"><span>返回</span></a> <a href="javascript:;" class="abtn"><span>回复</span></a> <a href="javascript:;" class="abtn"><span>全部回复</span></a> <a href="javascript:;" class="abtn"><span>转发</span></a> <a href="javascript:;" class="abtn"><span>删除</span></a> <a href="javascript:;" class="abtn"><span>彻底删除</span></a> <a
							href="javascript:;" class="abtn"><span>举报</span></a> <a href="javascript:;" class="abtn"><span>拒收</span></a>
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
						<a href="#">上一封</a>&nbsp;<a href="#">下一封</a>
					</div>
				</div>
				<div class="mail_box">
					<div class="mail_top">
						<div class="mail_title">您已成功付款</div>
						<p>
							发件人：<b>腾讯财付通</b> <span class="gray">&lt;tenpay@tencent.com&gt;</span>
						</p>
						<p>
							时 间：<span class="gray">2013年3月29日(星期五) 上午10:16</span>
						</p>
						<p>
							收件人：<span class="gray">276106549 &lt;276106549@qq.com&gt;</span>
						</p>
						<p>
							大 小：<span class="gray">2kb</span>
						</p>
					</div>
					<div class="mail_content">
						<p>付款成功</p>
						<p>付款成功</p>
						<p>付款成功</p>
						<p>付款成功</p>
						<p>付款成功</p>
						<p>付款成功</p>
						<p>付款成功</p>
						<p>付款成功</p>
						<p>付款成功</p>
						<p>付款成功</p>
						<p>付款成功</p>
						<p>付款成功</p>
						<p>付款成功</p>
						<p>付款成功</p>
						<p>付款成功</p>
						<p>付款成功</p>
						<p>&nbsp;</p>
						<div>
							<div style="color: #909090; font-family: Arial Narrow; font-size: 12px">------------------</div>
							<div style="font-size: 14px; font-family: Verdana; color: #000;">
								<p>
									<span style="color: rgb(128, 128, 128);"><span style="font-size: 10pt;">李阳 web前端<br></span></span>
								</p>
								<span style="color: rgb(128, 128, 128);"><span lang="EN-US" style="font-size: 10pt;">Mobile: 13718996400<br>E-mail:
								</span></span><span lang="EN-US" style="font-size: 10.0pt;;;;; color: black"><span style="color: rgb(128, 128, 128);"><a href="mailto:ly63@vip.qq.com" target="_blank">ly63@vip.qq.com</a><br>QQ：276106549</span><br></span><span lang="EN-US" style="font-size: 10.0pt;;;;; color: #666666"></span>
							</div>
						</div>
					</div>
					<div class="mail_file">
						<div class="mail_file_title">
							<img src="${vix}/common/img/mail_9.png" width="13" height="13" /> <b>附件</b>(1 个)
						</div>
						<div class="mail_file_item clearfix">
							<img src="${vix}/common/img/fu_rar.gif" />htm38_130118.rar <span class="gray">(10.10M)</span><br />
							<a href="#">下载</a>
						</div>
					</div>
				</div>
				<div class="pagelist drop clearfix mail_tool">
					<div class="l">
						<a href="javascript:;" class="abtn"><span>返回</span></a> <a href="javascript:;" class="abtn"><span>回复</span></a> <a href="javascript:;" class="abtn"><span>全部回复</span></a> <a href="javascript:;" class="abtn"><span>转发</span></a> <a href="javascript:;" class="abtn"><span>删除</span></a> <a href="javascript:;" class="abtn"><span>彻底删除</span></a> <a
							href="javascript:;" class="abtn"><span>举报</span></a> <a href="javascript:;" class="abtn"><span>拒收</span></a>
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
						<a href="#">上一封</a>&nbsp;<a href="#">下一封</a>
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