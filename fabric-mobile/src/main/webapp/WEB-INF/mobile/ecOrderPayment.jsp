<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/mobilecommon/page/head.jsp"%>
	<%@ include file="/mobilecommon/page/resource_css.jsp"%>
	<title>贝垦-订单支付</title>
</head>
<body>
<header>
    <a href="${beiken}/beiken/beikenShoppingCartAction!goIndex.action?key=shoppingcart" class="b-header-code b-header-return"></a>订单支付
</header>
<section class="ordPay">
    <div class="pay-num b_b plr10 mb10">请支付：<span class="c_red">￥<i>${requireGoodsOrder.amount}</i></span></div>
    <div class="ordPay_list1">
        <ul>
            <li><span>订单号：</span><strong>${requireGoodsOrder.code}</strong></li>
            <li><span>应付金额：</span><strong>￥${requireGoodsOrder.amount}</strong></li>
            <li>
            	<span>支付方式：</span>
            	<strong><c:if test="${requireGoodsOrder.payType == 'wx'}">微信支付</c:if></strong>
            </li>
        </ul>
    </div>
    <!-- <div class="pay-method b_t mb15">
        <h1 class="b_b">选择支付方式</h1>
        <div class="m-item plr10 check" data-val="WX_JSAPI">
            <span class="m-item-img weChat"></span>
            <p>微信支付</p>
            <span class="check-icon bnj-sprite-icon"></span>
        </div>
        <div class="m-item plr10" data-val="ALI_WAP">
            <span class="m-item-img alipay"></span>
            <p>支付宝支付</p>
            <span class="check-icon bnj-sprite-icon"></span>
        </div>
        <div class="m-item plr10" data-val="UN_WEB">
            <span class="m-item-img unionpay"></span>
            <p>银联支付</p>
            <span class="check-icon bnj-sprite-icon"></span>
        </div>
    </div> -->
    <div class="btn"><a href="javascript:void(0)" onclick="weixinPay('${requireGoodsOrder.id}')">确认付款</a></div>
</section>
<%@ include file="/mobilecommon/page/foot.jsp"%>
</body>
</html>
<%@ include file="/mobilecommon/page/resource_js.jsp"%>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js" type="text/javascript" ></script>
<input type="hidden" id="qiyeCorpId" value="${qiyeCorpId}">
<input type="hidden" id="timestamp" value="${timestamp}">
<input type="hidden" id="nonceStr" value="${nonceStr}">
<input type="hidden" id="signature" value="${signature}">
<input type="hidden" id="openId" value="${openId}">
<script type="text/javascript">
	//微信初始化
	wx.config({
	    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	    appId: $("#qiyeCorpId").val(), // 必填，公众号的唯一标识
	    timestamp: $("#timestamp").val(), // 必填，生成签名的时间戳
	    nonceStr: $("#nonceStr").val(), // 必填，生成签名的随机串
	    signature: $("#signature").val(),// 必填，签名，见附录1
	    jsApiList: ['chooseWXPay'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});
	wx.ready(function(){
		wx.hideOptionMenu();//隐藏右边的一些菜单
	});
	wx.checkJsApi({
	    jsApiList: ['chooseWXPay'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
	    success: function(res) {
	        // 以键值对的形式返回，可用的api值true，不可用为false
	        // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
	    }
	});
	
	function weixinPay(id){
		if(id){
			$.ajax({ url: '${beiken}/beiken/beikenPaymentAction!weixinJsPayPrepare.action'
				,data:{"id" : id}
	    		,dataType:"json"
	       		,type:"post"
	    		,success:function(json){
	    			if(json){
	    				wx.chooseWXPay({
	    				    timestamp: json.timeStamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
	    				    nonceStr: json.nonceStr, // 支付签名随机串，不长于 32 位
	    				    package: json.package, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
	    				    signType: json.signType, // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
	    				    paySign: json.paySign, // 支付签名
	    				    success: function (res) {
	    				    	// 支付成功后的回调函数
	    				    	var url = '${beiken}/beiken/beikenOrderAction!goOrderPaymentSuccess.action?id='+id;
	        		    		document.location.href=url;
	    				    }
	    				});
	    			}else{
	    				$.toptip('网络加载失败，请重试');
	    				return;
	    			}
	        }});
		}else{
			$.toptip('订单不存在');
			return;
		}
	}
</script>

