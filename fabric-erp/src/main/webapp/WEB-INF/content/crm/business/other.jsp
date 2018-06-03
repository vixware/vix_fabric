<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/page/taglibs.jsp"%>


<link href="${vix}/common/js/vreport/vreport/css/vreport.css" rel="stylesheet" type="text/css" />
<link href="${vix}/common/js/vreport/jquery/css/jquery-ui-1.9.1.custom.css" rel="stylesheet" type="text/css" />
<script src="${vix}/common/js/vreport/vprint.js" type="text/javascript"></script>
<script src="${vix}/common/js/vreport/jquery/jquery-1.8.2.js" type="text/javascript"></script>
<script src="${vix}/common/js/vreport/jquery/jquery-ui-1.9.1.custom.min.js" type="text/javascript"></script>
<script src="${vix}/common/js/raphael-min.js" type="text/javascript"></script>
<script src="${vix}/common/js/vreport/flowtask.js" type="text/javascript"></script>



<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/inv_transfer.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">会员交互管理 </a></li>
				<li><a href="#">营销中心 </a></li>
				<li><a href="#">智能营销 </a></li>
				<li><a href="#">会员细分管理 </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop"></div>
</div>

<div class="content">
	<!-- c_head -->
	<!-- sub menu -->
	<div class="box">
		<div id="contentDiv"></div>
		<!-- content -->

		<script>
			var report = new Rreport({
			contentDiv : 'contentDiv',
			isEdit : true,
			saveFun : function(data) {
				document.write(data);
			}
			});

			report.loadData({
			"viewType" : "w",
			"width" : "100%",
			"height" : 450,
			"link" : {
			"link1" : {
			"id" : "link1",
			"from" : "node1",
			"to" : [ {
			"targetId" : "node2",
			"condition" : ""
			} ],
			"isMul" : "0",
			"length" : 50
			},
			"link2" : {
			"id" : "link2",
			"from" : "node2",
			"to" : [ {
			"targetId" : "node3",
			"condition" : ""
			}, {
			"targetId" : "node5",
			"condition" : ""
			} ],
			"isMul" : "0",
			"length" : 50
			},
			"link3" : {
			"id" : "link3",
			"from" : "node3",
			"to" : [ {
			"targetId" : "node4",
			"condition" : ""
			} ],
			"isMul" : "0",
			"length" : 50
			},
			"link4" : {
			"id" : "link4",
			"from" : "node5",
			"to" : [ {
			"targetId" : "node6",
			"condition" : ""
			} ],
			"isMul" : "0",
			"length" : 50
			}
			},
			"node" : {
			"node1" : {
			"x" : 58,
			"icon" : "start.png",
			"type" : "start",
			"y" : 72,
			"text" : "标题",
			"id" : "node1",
			"value" : "quartz://report?cron=*/5 * * * * ?&stateful=true",
			"param" : "",
			"ext" : ""
			},
			"node2" : {
			"x" : 213,
			"y" : 163,
			"type" : "delay",
			"icon" : "timer.png",
			"text" : "标题",
			"id" : "node2",
			"value" : "1000",
			"param" : "",
			"ext" : ""
			},
			"node3" : {
			"x" : 535,
			"y" : 348,
			"type" : "process",
			"icon" : "human.png",
			"text" : "标题",
			"id" : "node3",
			"value" : "com.e6soft.test.p.AProcesser",
			"param" : "",
			"ext" : ""
			},
			"node4" : {
			"x" : 1141,
			"y" : 348,
			"type" : "process",
			"icon" : "email.png",
			"text" : "标题",
			"id" : "node4",
			"value" : "com.e6soft.test.p.BProcesser",
			"param" : "",
			"ext" : ""
			},
			"node5" : {
			"x" : 582,
			"y" : 27,
			"type" : "process",
			"icon" : "human.png",
			"text" : "标题",
			"id" : "node5"
			},
			"node6" : {
			"x" : 1045,
			"y" : 27,
			"type" : "process",
			"icon" : "email.png",
			"text" : "标题",
			"id" : "node6"
			}
			}
			});
		</script>

	</div>
	<!-- dialog -->
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>