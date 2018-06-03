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

$(".sub_menu .drop>ul>li").hover(function() // sub_menu菜单项的鼠标移入操作 
{
	$(this).addClass("sub_menu_hover");
},function() // sub_menu菜单项的鼠标移出操作 
{
	$(this).removeClass("sub_menu_hover");
}); 
 
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
loadMenuContent('${vix}/oa/main/oaMainAction!goMenuContent.action');
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
				<li><a href="#"><s:text name="写信" /></a></li>
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
			<div class="right_content" style="border: none;">
				<div class="mail_title">
					收件箱<span class="gray">（共1123封）</span>
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
						<span><a class="start"></a></span> <span><a class="previous"></a></span> <em>(1-20 到 29)</em> <span><a class="next"></a></span> <span><a class="end"></a></span>
					</div>
				</div>
				<div class="table mail_table mail_box">
					<table class="list">
						<tr>
							<th width="50">
								<div class="list_check">
									<div class="drop">
										<label><input name="" type="checkbox" value="" /></label>
										<ul>
											<li><a href="#">所有</a></li>
											<li><a href="#">其他</a></li>
											<li><a href="#">式样</a></li>
											<li><a href="#">关闭</a></li>
										</ul>
									</div>
								</div>
							</th>
							<th width="25"><img src="${vix}/common/img/mail_8.png" /></th>
							<th width="110">发件人<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
							<th>主题<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
							<th width="100">时间<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
							<th width="100">大小<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
							<th width="20"></th>
						</tr>
						<tr>
							<td colspan="7"><div class="bd talk">
									<b>今天</b> <span class="gray">(10 封)</span>
								</div></td>
						</tr>
						<tr>
							<td><input name="" type="checkbox" value="" /></td>
							<td><img src="${vix}/common/img/mail_4.png" /></td>
							<td><div class="nowrap">
									<a href="#"><b>Tortoise Corp</b></a>
								</div></td>
							<td><div class="nowrap">
									<a href="#"><b>web版前端js调用的json接口</b><span class="gray"> - hi,all 消息（message）已经部署好了，接口详见wiki</span></a>
								</div></td>
							<td>3月28日</td>
							<td>74K</td>
							<td><img src="${vix}/common/img/mail_7.png" /></td>
						</tr>
						<tr>
							<td><input name="" type="checkbox" value="" /></td>
							<td><img src="${vix}/common/img/mail_5.png" /></td>
							<td><div class="nowrap">
									<a href="#">Tortoise Corp</a>
								</div></td>
							<td><a href="#"><strong>web版前端js调用的json接口</strong><span class="gray"> - hi,all 消息（message）已经部署好了，接口详见wiki</span></a></td>
							<td>3月28日</td>
							<td>74K</td>
							<td><img src="${vix}/common/img/mail_6.png" /></td>
						</tr>
						<tr>
							<td colspan="7"><div class="bd talk">
									<b>昨天</b> <span class="gray">(10 封)</span>
								</div></td>
						</tr>
						<tr>
							<td><input name="" type="checkbox" value="" /></td>
							<td><img src="${vix}/common/img/mail_4.png" /></td>
							<td><div class="nowrap">
									<a href="#"><b>Tortoise Corp</b></a>
								</div></td>
							<td><div class="nowrap">
									<a href="#"><b>web版前端js调用的json接口</b><span class="gray"> - hi,all 消息（message）已经部署好了，接口详见wiki</span></a>
								</div></td>
							<td>3月28日</td>
							<td>74K</td>
							<td><img src="${vix}/common/img/mail_7.png" /></td>
						</tr>
						<tr>
							<td><input name="" type="checkbox" value="" /></td>
							<td><img src="${vix}/common/img/mail_5.png" /></td>
							<td><div class="nowrap">
									<a href="#">Tortoise Corp</a>
								</div></td>
							<td><a href="#"><strong>web版前端js调用的json接口</strong><span class="gray"> - hi,all 消息（message）已经部署好了，接口详见wiki</span></a></td>
							<td>3月28日</td>
							<td>74K</td>
							<td><img src="${vix}/common/img/mail_6.png" /></td>
						</tr>
						<tr>
							<td colspan="7"><div class="bd talk">
									<b>前天</b> <span class="gray">(10 封)</span>
								</div></td>
						</tr>
						<tr>
							<td><input name="" type="checkbox" value="" /></td>
							<td><img src="${vix}/common/img/mail_4.png" /></td>
							<td><div class="nowrap">
									<a href="#"><b>Tortoise Corp</b></a>
								</div></td>
							<td><div class="nowrap">
									<a href="#"><b>web版前端js调用的json接口</b><span class="gray"> - hi,all 消息（message）已经部署好了，接口详见wiki</span></a>
								</div></td>
							<td>3月28日</td>
							<td>74K</td>
							<td><img src="${vix}/common/img/mail_7.png" /></td>
						</tr>
						<tr>
							<td><input name="" type="checkbox" value="" /></td>
							<td><img src="${vix}/common/img/mail_5.png" /></td>
							<td><div class="nowrap">
									<a href="#">Tortoise Corp</a>
								</div></td>
							<td><a href="#"><strong>web版前端js调用的json接口</strong><span class="gray"> - hi,all 消息（message）已经部署好了，接口详见wiki</span></a></td>
							<td>3月28日</td>
							<td>74K</td>
							<td><img src="${vix}/common/img/mail_6.png" /></td>
						</tr>
						<tr>
							<td><input name="" type="checkbox" value="" /></td>
							<td><img src="${vix}/common/img/mail_4.png" /></td>
							<td><div class="nowrap">
									<a href="#"><b>Tortoise Corp</b></a>
								</div></td>
							<td><div class="nowrap">
									<a href="#"><b>web版前端js调用的json接口</b><span class="gray"> - hi,all 消息（message）已经部署好了，接口详见wiki</span></a>
								</div></td>
							<td>3月28日</td>
							<td>74K</td>
							<td><img src="${vix}/common/img/mail_7.png" /></td>
						</tr>
						<tr>
							<td colspan="7"><div class="bd talk">
									<b>上周</b> <span class="gray">(10 封)</span>
								</div></td>
						</tr>
						<tr>
							<td><input name="" type="checkbox" value="" /></td>
							<td><img src="${vix}/common/img/mail_5.png" /></td>
							<td><div class="nowrap">
									<a href="#">Tortoise Corp</a>
								</div></td>
							<td><a href="#"><strong>web版前端js调用的json接口</strong><span class="gray"> - hi,all 消息（message）已经部署好了，接口详见wiki</span></a></td>
							<td>3月28日</td>
							<td>74K</td>
							<td><img src="${vix}/common/img/mail_6.png" /></td>
						</tr>
						<tr>
							<td><input name="" type="checkbox" value="" /></td>
							<td><img src="${vix}/common/img/mail_4.png" /></td>
							<td><div class="nowrap">
									<a href="#"><b>Tortoise Corp</b></a>
								</div></td>
							<td><div class="nowrap">
									<a href="#"><b>web版前端js调用的json接口</b><span class="gray"> - hi,all 消息（message）已经部署好了，接口详见wiki</span></a>
								</div></td>
							<td>3月28日</td>
							<td>74K</td>
							<td><img src="${vix}/common/img/mail_7.png" /></td>
						</tr>
						<tr>
							<td colspan="7"><div class="bd talk">
									<b>更早</b> <span class="gray">(10 封)</span>
								</div></td>
						</tr>
						<tr>
							<td><input name="" type="checkbox" value="" /></td>
							<td><img src="${vix}/common/img/mail_5.png" /></td>
							<td><div class="nowrap">
									<a href="#">Tortoise Corp</a>
								</div></td>
							<td><a href="#"><strong>web版前端js调用的json接口</strong><span class="gray"> - hi,all 消息（message）已经部署好了，接口详见wiki</span></a></td>
							<td>3月28日</td>
							<td>74K</td>
							<td><img src="${vix}/common/img/mail_6.png" /></td>
						</tr>
					</table>
				</div>
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
						<span><a class="start"></a></span> <span><a class="previous"></a></span> <em>(1-20 到 29)</em> <span><a class="next"></a></span> <span><a class="end"></a></span>
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