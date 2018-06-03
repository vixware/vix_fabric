<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/mobilecommon/page/head.jsp"%>
	<%@ include file="/mobilecommon/page/resource_css.jsp"%>
	<title>贝垦-查询库存</title>
</head>
<body>
<header>
    <a href="javascript:history.back();" class="b-header-code b-header-return"></a>
    <div class="b-header-search">
        <span class="b-header-search-icon bnj-sprite-icon"></span>
        <div class="b-search-form-input">
            <input id="itemcode" type="text" maxlength="20" autocomplete="off" name="keyword" value="" placeholder="输入商品名称或编码查询库存">
        </div>
    </div>
    <div class="b-header-text search" onclick="searchStock();">查询</div>
</header>
<%@ include file="/mobilecommon/page/foot.jsp"%>
</body>
</html>
<%@ include file="/mobilecommon/page/resource_js.jsp"%>
<script type="text/javascript">
	function searchStock(){
		var itemcode = $("#itemcode").val();
		if (!itemcode) {
			$.toptip('请您输入查询商品的编码');
			return;
		}else{
			itemcode = Url.encode(itemcode);
			document.location.href = '${beiken}/beiken/beikenUserCenterAction!searchStockByItemCode.action?itemcode='+itemcode;
		}
	}
</script>
