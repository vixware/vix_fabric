<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/system/page/taglibs.jsp"%>
<script type="text/javascript">
function loadContent(span,url){
	$("div[id='dic'] li").each(function(){
		$(this).attr("style","");
	});
	$(span).parent().attr("style","background-color:#d0d0d0;");
	$.ajax({
		  url:url,
		  cache: false,
		  success: function(html){
			  $("#dictionary").html(html);
			  bindSwitch();
		  }
	});
}
$("#ibc").click();
$("#dic").css({"height":$("#right").height()-8});
</script>
<style>
#dic ul {
	margin-right: 10px;
	padding-bottom: 20px;
	color: #000;
}

#dic ul li {
	margin-left: 15px;
	font-size: 12px;
	height: 25px;
	line-height: 25px;
}

#dic ul li a {
	color: #000;
}
</style>
<div id="switch"></div>
<div id="box">
	<div id="box_left">
		<div id="dic" style="width: 96%; overflow-x: auto; overflow-y: auto; border: 1px solid gray; border-radius: 4px 4px 4px 4px;">
			<ul>
				<hr />
				<p style="font-size: 14px; margin-left: 6px; color: #606060;">信用字典</p>
				<li><span id="ibc" style="cursor: pointer;" onclick="loadContent(this,'${vix}/sales/credit/creditDictionaryAction/goList.action?dicType=riskLevel&dicCount=8');"> 风险等级 </span></li>
				<li><span id="ibc" style="cursor: pointer;" onclick="loadContent(this,'${vix}/sales/credit/creditDictionaryAction/goList.action?dicType=arrearLevel&dicCount=8');"> 拖欠等级 </span></li>
				<li><span id="ibc" style="cursor: pointer;" onclick="loadContent(this,'${vix}/sales/credit/creditDictionaryAction/goList.action?dicType=creditLine&dicCount=8');"> 信用额度 </span></li>
				<li><span id="ibc" style="cursor: pointer;" onclick="loadContent(this,'${vix}/sales/credit/creditDictionaryAction/goList.action?dicType=maxVoucher&dicCount=8');"> 允许最大凭证值 </span></li>
				<li><span id="ibc" style="cursor: pointer;" onclick="loadContent(this,'${vix}/sales/credit/creditDictionaryAction/goList.action?dicType=overdue&dicCount=8');"> 逾期天数 </span></li>

			</ul>
		</div>
	</div>
	<!-- box-left -->
	<div id="box_right" style="overflow-x: hidden; overflow-y: auto;">
		<div id="dictionary"></div>
	</div>
	<!-- box-right -->
</div>
<script type="text/javascript">
$("#tree").css({"height": $("#right").height()-10});
$("#box_right").css({"height": $("#right").height()-10});
</script>