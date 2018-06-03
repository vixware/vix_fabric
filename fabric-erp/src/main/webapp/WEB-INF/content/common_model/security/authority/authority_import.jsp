<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
<!--
function importXslUpload(){
	var tip = $("#mailTip");
	tip.show();
	$.ajaxFileUpload({
		url : '${vix}/common/security/resourceAction!importFile.action',//用于文件上传的服务器端请求地址
		secureuri : true,//是否安全提交,设置为true;设置为false，则出现乱码
		fileElementId : 'fileToUpload',//文件上传空间的id属性  <input type="file" id="file" name="file" />
		dataType : 'text',//返回值类型 ,可以使xml、text、json、html
		success : function(data, status){ //服务器成功响应处理函数
			tip.hide();
			var data = $.parseJSON(data);
			if(data.success=='1'){
				showMessage(data.msg);
				setTimeout("", 1000);
			}else{
				if(typeof(data.error) != 'undefined'){
					if(data.error != ''){
						showErrorMessage(data.error);
						setTimeout("", 1000);
					}else{
						alert(data.msg);
					}
				}
			}
         },
         error : function(data, status, e){//服务器响应失败处理函数
        	 showErrorMessage("上传错误!");
			setTimeout("", 1000);
         }
 	});
}

function brandList(){
	$("#brandSub").css({"display":""});
	$("#dimensionSub").css({"display":"none"});
}
//-->
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<form action="" id="authorityImportForm" name="authorityImportForm">
			<table>
				<tr>
					<td align="right">文件:&nbsp;</td>
					<td colspan="3"><input type="text" id="authorityNameDetail" name="authorityNameDetail" readonly="readonly" value="${entity.parentAuthority.displayName}" /> <input class="btn" type="button" value="选择" onclick="chooseParentAuthority();" /></td>
				</tr>
				<tr>
					<td colspan="4">
						<div id="mailTip" style="display: none">文件正在上传...</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
<script src="${vix}/common/js/jtip.js" type="text/javascript"></script>
<script type="text/javascript">
<!--
//提示
if($('input.sweet-tooltip').length){
	$('input.sweet-tooltip').focus(function() {
		tooltip				= $(this);
		tooltipText 		= tooltip.attr('data-text-tooltip');
		tooltipClassName	= 'tooltip';
		tooltipClass		= '.' + tooltipClassName;
		
		if(tooltip.hasClass('showed-tooltip')) return false;
		
		tooltip.addClass('showed-tooltip')
				 .after('<div class="'+tooltipClassName+'"><div class="tooltip_l"></div><div class="tooltip_r"></div><div class="tooltip_x">'+tooltipText+'</div><div class="tooltip_b"></div></div>');
		tooltipPosTop 	= tooltip.position().top - $(tooltipClass).outerHeight() - 10;
		tooltipPosLeft = tooltip.position().left;
		tooltipPosLeft = tooltipPosLeft - (($(tooltipClass).outerWidth()/2) - tooltip.outerWidth()/2);
		$(tooltipClass).css({ 'left': tooltipPosLeft, 'top': tooltipPosTop }).animate({'opacity':'1', 'marginTop':'0'}, 500);
	}).focusout(function() {
		$(tooltipClass).animate({'opacity':'0', 'marginTop':'-10px'}, 500, function() {
			$(this).remove();
			tooltip.removeClass('showed-tooltip');
				
		});
	});
}
//-->
</script>