<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<title>付款</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
<%@ include file="/vixntcommon/page/resource_css.jsp"%>
<script src="${nvix}/vixntcommon/base/js/libs/jquery-2.1.1.min.js" type="text/javascript"></script>
<script src="${nvix}/vixntcommon/base/js/libs/jquery-ui-1.10.3.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function() {
	setTimeout('goPos()',3000);
});

function goPos(){
	window.location.href = "${nvix}/nvixnt/vixntPosAction!goList.action";
}
</script>
<body class="">
	<div id="main" role="main" style="margin-left: 0px;">
		<div id="content">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="row">
						<div class="col-sm-12">
							<div class="text-center error-box">
								<h2 class="font-xl">
									<strong>支付成功，正在跳转。。。</strong>
								</h2>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>	
</body>