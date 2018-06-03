<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<li><a href="#"><i class="fa fa-lg fa-fw fa-weixin"></i> <span class="menu-item-parent">微信管理</span></a>
	<ul>
		<li><a href="#" onclick="loadContent('sys_wxpWeixinSite','${nvix}/nvixnt/wxpWeixinSiteAction!goList.action');">公众号管理</a></li>
		<li><a href="#">微信菜单管理</a>
			<ul>
				<li><a href="#" onclick="loadContent('sys_wxpWeixinSite','${nvix}/nvixnt/wx/wxCustomizeMenuAction!goList.action');">自定义菜单</a></li>
			</ul></li>
		<li><a href="#">微信消息管理</a>
			<ul>
				<li><a href="#">自动回复</a>
					<ul>
						<li><a href="#" onclick="loadContent('sys_wxpWeixinSite','${nvix}/nvixnt/wx/wxMessageAction!goList.action');">关注|消息自动回复</a></li>
						<li><a href="#" onclick="loadContent('sys_wxpWeixinSite','${nvix}/nvixnt/wx/wxMessageAction!goAntistopList.action');">关键词自动回复</a></li>
					</ul></li>
				<li><a href="#" onclick="loadContent('sys_wxpWeixinSite','${nvix}/nvixnt/wx/wxFormIdAction!goList.action');">模板消息</a></li>
				<li><a href="#" onclick="loadContent('sys_wxpWeixinSite','${nvix}/nvixnt/wx/wxFansMessageAction!goList.action');">粉丝消息</a></li>
			</ul></li>
		<li><a href="#">微信粉丝管理</a>
			<ul>
				<li><a href="#" onclick="loadContent('sys_wxpWeixinSite','${nvix}/nvixnt/wx/wxFansAction!goList.action');">微信粉丝</a></li>
				<li><a href="#" onclick="loadContent('sys_wxpWeixinSite','${nvix}/nvixnt/wx/wxTagsAction!goList.action');">微信标签</a></li>
				<li><a href="#" onclick="loadContent('sys_wxpWeixinSite','${nvix}/nvixnt/wx/wxFansAction!goSynchronousList.action');">同步微信粉丝</a></li>
			</ul></li>
		<li><a href="#">微信素材管理</a>
			<ul>
				<li><a href="#" onclick="loadContent('sys_wxpWeixinSite','${nvix}/nvixnt/wx/wxMaterialAction!goImageText.action');">图文素材</a></li>
				<li><a href="#" onclick="loadContent('sys_wxpWeixinSite','${nvix}/nvixnt/wx/wxMaterialAction!goList.action');">图片素材</a></li>
				<li><a href="#" onclick="loadContent('sys_wxpWeixinSite','${nvix}/nvixnt/wx/wxMaterialAction!goSaveOrUpdate.action');">微信素材同步</a></li>
			</ul></li>
		<li><a href="#" onclick="loadContent('sys_wxpWeixinSite','${nvix}/nvixnt/wx/wxPaycenterAction!goList.action');">微信支付配置</a></li>
	</ul></li>