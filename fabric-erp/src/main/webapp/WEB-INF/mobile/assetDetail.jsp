<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/mobilecommon/page/head.jsp"%>
<%@ include file="/mobilecommon/page/resource_js.jsp"%>
<%@ include file="/mobilecommon/page/resource_css.jsp"%>
<title>联盟链-资产详情</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<script charset="utf-8"
	src="http://map.qq.com/api/js?v=2.exp&key=4ZQBZ-COJ3F-T6EJJ-JZJPH-NMQ62-A2B44"></script>
<script charset="utf-8"
	src="http://map.qq.com/api/js?v=2.exp&key=4ZQBZ-COJ3F-T6EJJ-JZJPH-NMQ62-A2B44&libraries=drawing,geometry,autocomplete,convertor"></script>
<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}

body, button, input, select, textarea {
	font: 12px/16px Verdana, Helvetica, Arial, sans-serif;
}

#info {
	width: 603px;
	padding-top: 3px;
	overflow: hidden;
}

.btn {
	width: 190px;
}

#container {
	min-height: 200px;
}
</style>
</head>
<body onload="init()">
	<header> <a href="javascript:history.back();"
		class="header_return"><i class="iconfont icon-icon--"></i></a>
	<p class="coupons">资产详情</p>
	<a href="#" class="header_r_txt"></a> </header>
	<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp"></script>
	<section class="mt_45 mb_53">
	<div class="weui-cells">
		<div class="weui-cell">
			<div class="weui-cell__bd">
				<p>
					<font color="red">*</font>资产编码
				</p>
			</div>
			<div class="weui-cell__ft">${fabricAsset.assetCode}</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__bd">
				<p>
					<font color="red">*</font>资产名称
				</p>
			</div>
			<div class="weui-cell__ft">${fabricAsset.assetName}</div>
		</div>
	</div>
	<div class="weui-cells">
		<div class="weui-cell">
			<div class="weui-cell__bd">
				<p>
					<font color="red">*</font>所在地点
				</p>
			</div>
			<div class="weui-cell__ft">${fabricAsset.site}</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__bd">
				<p>
					<font color="red">*</font>面积
				</p>
			</div>
			<div class="weui-cell__ft">${fabricAsset.plantingArea}</div>
		</div>
		<a class="weui-cell">
			<div class="weui-cell__bd">
				<p>
					<font color="red">*</font>取得使用权日期
				</p>
			</div>
			<div class="weui-cell__ft">${fabricAsset.dateOfAccessStr}</div>
		</a>
		<div class="weui-cell">
			<div class="weui-cell__bd">
				<p>
					<font color="red">*</font>账面原值
				</p>
			</div>
			<div class="weui-cell__ft">${fabricAsset.originalValue}</div>
		</div>
		<a class="weui-cell " href="javascript:;">
			<div class="weui-cell__bd">
				<p>
					<font color="red">*</font>使用类型
				</p>
			</div>
			<div class="weui-cell__ft">${fabricAsset.useType}</div>
		</a> <a class="weui-cell" href="javascript:;">
			<div class="weui-cell__bd">
				<p>
					<font color="red">*</font>非转经或闲置起始日期
				</p>
			</div>
			<div class="weui-cell__ft">${fabricAsset.inidleTime}</div>
		</a>
		<%-- <a class="weui-cell weui-cell_access" href="javascript:;">
            <div class="weui-cell__bd">
                <p><font color="red">*</font>有无协议或合同</p>
            </div>
            <div class="weui-cell__ft">${fabricAsset.hsContract}
            </div>
        </a> --%>
		<a class="weui-cell " href="javascript:;">
			<div class="weui-cell__bd">
				<p>
					<font color="red">*</font>非转经年收益
				</p>
			</div>
			<div class="weui-cell__ft">${fabricAsset.annualEarnings}</div>
		</a>
	</div>
	<div class="weui-cells">
		<div class="weui-cell">
			<div class="weui-cell__bd">
				<p>
					<font color="red">*</font>经营单位或个人名称
				</p>
			</div>
			<div class="weui-cell__ft">${fabricAsset.operation}</div>
		</div>
	</div>
	<div class="weui-cells">
		<div id="container" class="weui-cell"></div>
	</div>
	</section>
	<!-- 底部代码 -->
	<div class="weui-tabbar">
		<a href="${ws}/front/fabricIndexAction!goIndex.action?key=home"
			class="weui-tabbar__item "> <i class="iconfont icon-home"></i>
			<p class="weui-tabbar__label">首页</p>
		</a> <a href="${ws}/front/fabricContractAction!goContractList.action?id="
			class="weui-tabbar__item  "> <i class="iconfont icon-heyue"></i>
			<p class="weui-tabbar__label">合约</p>
		</a> <a href="${ws}/front/fabricAssetAction!goAssetList.action?id="
			class="weui-tabbar__item weui-bar__item_on"> <i
			class="iconfont icon-zichanxinxi"></i>
			<p class="weui-tabbar__label">资产</p>
		</a> <a href="${ws}/front/fabricUserCenterAction!goIndex.action?id="
			class="weui-tabbar__item "> <i class="iconfont icon-wode"></i>
			<p class="weui-tabbar__label">我的</p>
		</a>
	</div>
</body>
<script type="text/javascript">
	var init = function() {
		var path1 = [];
		var pointsStr = eval('(' + '${pointsStr}' + ')');
		alert(pointsStr);
		var path2 = pointsStr.points;
		if (pointsStr && path2 != "") {
			for ( var i in path2) {
				path1.push(new qq.maps.LatLng(path2[i].lat, path2[i].lng));
			}
		}
		var center = path1[0];
		var map = new qq.maps.Map(document.getElementById('container'), {
			center : center,
			zoom : 13
		});
		var polygon = new qq.maps.Polygon({
			path : path1,
			strokeColor : '#000000',
			strokeWeight : 1,
			map : map
		});
	}
</script>
</html>