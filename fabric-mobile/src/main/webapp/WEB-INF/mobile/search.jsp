<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/mobilecommon/page/head.jsp"%>
	<%@ include file="/mobilecommon/page/resource_css.jsp"%>
	<title>贝垦-搜索</title>
</head>
<body style="background: #fff;">
<header>
    <a href="javascript:history.back();" class="b-header-code b-header-return"></a>
    <div class="b-header-search">
        <span class="b-header-search-icon bnj-sprite-icon"></span>
        <div class="b-search-form-input">
            <input id="searchName" type="text" maxlength="20" autocomplete="off" name="keyword" value="" placeholder="搜索医疗器械">
        </div>
    </div>
    <div class="b-header-text search" onclick="searchProduct();">搜索</div>
</header>
<section class="search mt_44">
    <!-- <div class="view">
        <div class="hot-search">
            <h3 class="his-tit b_b plr10">热门搜索</h3>
            <div class="his-hot-list">
                <a href="javascript:;" class="his-hot-item search-item">Protégé RX支架</a>
                <a href="javascript:;" class="his-hot-item search-item">Spider保护伞</a>
                <a href="javascript:;" class="his-hot-item search-item">Apollo支架</a>
                <a href="javascript:;" class="his-hot-item search-item">Maverick球囊</a>
            </div>
        </div>
        <div class="his-search">
            <h3 class="his-tit b_b plr10">历史搜索</h3>
            <ul class="his-list mb15">
                <li class="b_b his-item"><a href="javascript:;" class="search-item">Apollo支架</a></li>
            </ul>
            <div class="t_c"><a href="javascript:;" class="clearHis">清空搜索记录</a></div>
        </div>
    </div> -->
</section>
<%@ include file="/mobilecommon/page/foot.jsp"%>
</body>
</html>
<%@ include file="/mobilecommon/page/resource_js.jsp"%>
<script type="text/javascript">
	function searchProduct(){
		var searchName = $("#searchName").val();
		if (!searchName) {
			$.toptip('请您输入查询商品的名称');
			return;
		}else{
			searchName = Url.encode(searchName);
			document.location.href = '${beiken}/beiken/beikenIndexAction!searchProduct.action?searchName='+searchName;
		}
	}
</script>
