<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 底部代码 -->
<div class="weui-tabbar">
    <a href="${ws}/front/fabricIndexAction!goIndex.action?key=home" class="weui-tabbar__item weui-bar__item_on">
        <i class="iconfont icon-home"></i>
        <p class="weui-tabbar__label">首页</p>
    </a>
    <a href="${ws}/front/fabricContractAction!goContractList.action?id=" class="weui-tabbar__item  ">
        <i class="iconfont icon-heyue"></i>
        <p class="weui-tabbar__label">合约</p>
    </a>
    <a href="${ws}/front/fabricAssetAction!goAssetList.action?id=" class="weui-tabbar__item">
        <i class="iconfont icon-zichanxinxi"></i>
        <p class="weui-tabbar__label">资产</p>
    </a>
    <a href="${ws}/front/fabricUserCenterAction!goIndex.action?id=" class="weui-tabbar__item ">
        <i class="iconfont icon-wode"></i>
        <p class="weui-tabbar__label">我的</p>
    </a>
</div>
<script type="text/javascript">
$(function(){
	$('.weui-tabbar__item').on('click', function() {
		$(this).addClass('weui-bar__item_on').siblings('.weui-bar__item_on').removeClass('weui-bar__item_on');
	});
});
</script>