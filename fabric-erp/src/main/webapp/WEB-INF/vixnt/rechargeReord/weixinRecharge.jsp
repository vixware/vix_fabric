<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<input type="hidden" id="urlCode" value="${rechargeRecord.qrCodePath}">
<input type="hidden" id="docOrderId" value="${rechargeRecord.id}">
<input type="hidden" id="customerAccountId" value="${customerAccount.id}">
<input type="hidden" id="customerAccountClipId" value="${customerAccountClip.id}">
<div class="jarviswidget" style="margin: 0 0 8px;">
	<div id="code"></div>
</div>
<script type="text/javascript" src="${nvix}/vixntcommon/base/js/jquery.qrcode.min.js"></script>
<script type="text/javascript">
	$(function(){
		var str = $("#urlCode").val();
		str = toUtf8(str);
		/* $('#code').qrcode(str); */
		
		$("#code").qrcode({
			render: "table",
			width: 300,
			height:300,
			text: str
		});
	})

	function toUtf8(str) {   
	    var out, i, len, c;   
	    out = "";   
	    len = str.length;   
	    for(i = 0; i < len; i++) {   
	    	c = str.charCodeAt(i);
	    	if ((c >= 0x0001) && (c <= 0x007F)) {   
	        	out += str.charAt(i);   
	    	} else if (c > 0x07FF) {   
	        	out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));   
	        	out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));   
	        	out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));   
	    	} else {   
	        	out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));   
	        	out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));   
	    	}   
	    }   
	   
	    return out;   
	}  
</script>