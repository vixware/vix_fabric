<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="css/service.css" type="text/css" rel="stylesheet">
<script src="js/asyncbox.v1.5.beta.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$ (function (){
		var chart;
		$ (document).ready (function (){
			// Build the chart
			$ ('#container').highcharts ({
			chart : {
			plotBackgroundColor : null ,
			plotBorderWidth : null ,
			plotShadow : false
			} ,
			title : {
				text : 'Browser market shares at a specific website, 2010'
			} ,
			tooltip : {
				pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
			} ,
			plotOptions : {
				pie : {
				allowPointSelect : true ,
				cursor : 'pointer' ,
				dataLabels : {
					enabled : false
				} ,
				showInLegend : true
				}
			} ,
			series : [
				{
				type : 'pie' ,
				name : 'Browser share' ,
				data : [
				[
				'Firefox' , 45.0
				] , [
				'IE' , 26.8
				] , {
				name : 'Chrome' ,
				y : 12.8 ,
				sliced : true ,
				selected : true
				} , [
				'Safari' , 8.5
				] , [
				'Opera' , 6.2
				] , [
				'Others' , 0.7
				]
				]
				}
			]
			});
			$ ('#container2').highcharts ({
			chart : {
			plotBackgroundColor : null ,
			plotBorderWidth : null ,
			plotShadow : false
			} ,
			title : {
				text : 'Browser market shares at a specific website, 2010'
			} ,
			tooltip : {
				pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
			} ,
			plotOptions : {
				pie : {
				allowPointSelect : true ,
				cursor : 'pointer' ,
				dataLabels : {
					enabled : false
				} ,
				showInLegend : true
				}
			} ,
			series : [
				{
				type : 'pie' ,
				name : 'Browser share' ,
				data : [
				[
				'Firefox' , 45.0
				] , [
				'IE' , 26.8
				] , {
				name : 'Chrome' ,
				y : 12.8 ,
				sliced : true ,
				selected : true
				} , [
				'Safari' , 8.5
				] , [
				'Opera' , 6.2
				] , [
				'Others' , 0.7
				]
				]
				}
			]
			});
		});
	});
</script>
<script src="js/highcharts.js"></script>
<script src="js/modules/exporting.js"></script>
<!-- head -->
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="img/icon_14.gif" alt="" />打印</a> <a href="#" id="help"><img src="img/icon_15.gif" alt="" />帮助</a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/pm_project.png" alt="" />
					<s:text name="supplyChain" /></a></li>
				<li><a href="#"><s:text name="项目管理" /></a></li>
				<li><a href="#"><s:text name="项目一览" /></a></li>
				<li><a href="#"><s:text name="进度一览" /></a></li>
			</ul>
		</div>
	</h2>
</div>
<!-- sub menu -->
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_head -->
	<div class="npcontent clearfix">
		<div class="np_left_title">
			<h2>项目进度</h2>
		</div>
		<div class="np_l_l">
			<div class="tx">
				<img src="img/face.jpg" width="140" height="140" />
			</div>
			<p>
				<a href="#">编辑我的个人资料</a>
			</p>
			<div class="member_list">
				<div class="members_title">我的项目成员</div>
				<ul class="clearfix">
					<li><a href="#"><img src="img/face_2.jpg" width="40" height="40" />Members Name</a></li>
					<li><a href="#"><img src="img/face_2.jpg" width="40" height="40" />Members Name</a></li>
					<li><a href="#"><img src="img/face_2.jpg" width="40" height="40" />Members Name</a></li>
					<li><a href="#"><img src="img/face_2.jpg" width="40" height="40" />Members Name</a></li>
					<li><a href="#"><img src="img/face_2.jpg" width="40" height="40" />Members Name</a></li>
					<li><a href="#"><img src="img/face_2.jpg" width="40" height="40" />Members Name</a></li>
					<li><a href="#"><img src="img/face_2.jpg" width="40" height="40" />Members Name</a></li>
					<li><a href="#"><img src="img/face_2.jpg" width="40" height="40" />Members Name</a></li>
					<li><a href="#"><img src="img/face_2.jpg" width="40" height="40" />Members Name</a></li>
				</ul>
			</div>
		</div>
		<div class="np_l_r clearfix">
			<div class="inputbox">
				<form onsubmit="" method="POST">
					<input type="text" name="" />
				</form>
			</div>
			<ul class="np_tab">
				<li class="current"><a href="javascript:;" onclick="javascript:tab(3,1,'np',event)">最新活动</a></li>
				<li><a href="javascript:;" onclick="javascript:tab(3,2,'np',event)">用户状态</a></li>
				<li><a href="javascript:;" onclick="javascript:tab(3,3,'np',event)">公告</a></li>
			</ul>
			<div class="np_clist" id="np1">
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
					<tbody>
						<tr>
							<td valign="top" nowrap="nowrap" colspan="7" class="txtSmallBlack11 pt6 h30"><div class="dashdate w70">
									<b>今天的</b>
								</div></td>
						</tr>
						<tr>
							<td width="50" valign="top" align="right" class="pt3 bdrbtm4 pb3"><div class="zpstatus" id="stylediv_1">状态</div></td>
							<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">What's this?</a></td>
							<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">张贴者</td>
							<td valign="top" class="pl5 pt6 pr3 bdrbtm4 userminmax3"><span><a class="projlinksmall" href="#">ritchiechu</a></span></td>
							<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">06:51 </span></td>
						</tr>
						<tr>
							<td valign="top" nowrap="nowrap" colspan="7" class="txtSmallBlack11 pt6 h30"><div class="dashdate w70">
									<b>09-13</b>
								</div></td>
						</tr>
						<tr>
							<td width="50" valign="top" align="right" class="bdrbtm4 pt3 pb3"><div class="zptask" id="stylediv_2">任务</div></td>
							<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">测试</a></td>
							<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">上传者</td>
							<td valign="top" class="dashstatusmsg pl5 pt6 bdrbtm4"><a class="projlinksmall" href="#"><i>ritchiechu</i></a></td>
							<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">08:00 </span></td>
						</tr>
						<tr>
							<td valign="top" nowrap="nowrap" colspan="7" class="txtSmallBlack11 pt6 h30"><div class="dashdate w70">
									<b>09-12</b>
								</div></td>
						</tr>
						<tr>
							<td width="50" valign="top" align="right" class="bdrbtm4 pt3 pb3"><div class="zptask" id="stylediv_3">任务</div></td>
							<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">test</a></td>
							<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">张贴者</td>
							<td valign="top" class="dashstatusmsg pl5 pt6 bdrbtm4"><a class="projlinksmall" href="#"><i>ritchiechu</i></a></td>
							<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">06:45 </span></td>
						</tr>
						<tr>
							<td valign="top" nowrap="nowrap" colspan="7" class="txtSmallBlack11 pt6 h30"><div class="dashdate w70">
									<b>09-11</b>
								</div></td>
						</tr>
						<tr>
							<td width="50" valign="top" align="right" class="bdrbtm4 pt3 pb3"><div class="zptask" id="stylediv_4">任务</div></td>
							<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">aeffvvrv</a></td>
							<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">张贴者</td>
							<td valign="top" class="dashstatusmsg pl5 pt6 bdrbtm4"><a class="projlinksmall" href="#"><i>ritchiechu</i></a></td>
							<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">10:42 </span></td>
						</tr>
						<tr>
							<td width="50" valign="top" align="right" class="pt3 bdrbtm4 pb3"><div class="zptask" id="stylediv_5">任务 list</div></td>
							<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">adf</a></td>
							<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">张贴者</td>
							<td valign="top" class="pl5 pt6 pr3 bdrbtm4 userminmax3"><span><a class="projlinksmall" href="#">ritchiechu</a></span></td>
							<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">10:42 </span></td>
						</tr>
						<tr>
							<td width="50" valign="top" align="right" class="pt3 bdrbtm4 pb3"><div class="zptask" id="stylediv_6">任务</div></td>
							<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">阿苏大发送</a></td>
							<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">上传者</td>
							<td valign="top" class="pl5 pt6 pr3 bdrbtm4 userminmax3"><span><a class="projlinksmall" href="#">ritchiechu</a></span></td>
							<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">10:41 </span></td>
						</tr>
						<tr>
							<td width="50" valign="top" align="right" class="pt3 bdrbtm4 pb3"><div class="zptask" id="stylediv_7">任务</div></td>
							<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">阿苏大发送</a></td>
							<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">上传者</td>
							<td valign="top" class="pl5 pt6 pr3 bdrbtm4 userminmax3"><span><a class="projlinksmall" href="#">ritchiechu</a></span></td>
							<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">10:41 </span></td>
						</tr>
						<tr>
							<td valign="top" nowrap="nowrap" colspan="7" class="txtSmallBlack11 pt6 h30"><div class="dashdate w70">
									<b>09-07</b>
								</div></td>
						</tr>
						<tr>
							<td width="50" valign="top" align="right" class="bdrbtm4 pt3 pb3"><div class="zptask" id="stylediv_8">任务</div></td>
							<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">阿苏大发送</a></td>
							<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">张贴者</td>
							<td valign="top" class="dashstatusmsg pl5 pt6 bdrbtm4"><a class="projlinksmall" href="#"><i>ritchiechu</i></a></td>
							<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">10:27 </span></td>
						</tr>
						<tr>
							<td width="50" valign="top" align="right" class="pt3 bdrbtm4 pb3"><div class="zptask" id="stylediv_9">任务表</div></td>
							<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">New 任务</a></td>
							<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">张贴者</td>
							<td valign="top" class="pl5 pt6 pr3 bdrbtm4 userminmax3"><span><a class="projlinksmall" href="#">ritchiechu</a></span></td>
							<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">10:26 </span></td>
						</tr>
						<tr>
							<td width="50" valign="top" align="right" class="pt3 bdrbtm4 pb3"><div class="zpproject" id="stylediv_10">项目</div></td>
							<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">Test</a></td>
							<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">张贴者</td>
							<td valign="top" class="pl5 pt6 pr3 bdrbtm4 userminmax3"><span><a class="projlinksmall" href="#">ritchiechu</a></span></td>
							<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">10:26 </span></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="np_clist" id="np2" style="display: none;">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="msgtable">
					<tbody>
						<tr>
							<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"></span></td>
							<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
								<div class="plbox hide">
									<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
										<tbody>
											<tr>
												<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
														<input type="text" value="" class="addformtextField h30 w100per">
													</form></td>
											</tr>
										</tbody>
									</table>
								</div></td>
							<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
									<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
								</div></td>
						</tr>
						<tr>
							<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"></span></td>
							<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
								<div class="plbox hide">
									<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
										<tbody>
											<tr>
												<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
														<input type="text" value="" class="addformtextField h30 w100per">
													</form></td>
											</tr>
										</tbody>
									</table>
								</div></td>
							<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
									<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
								</div></td>
						</tr>
						<tr>
							<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"></span></td>
							<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
								<div class="plbox hide">
									<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
										<tbody>
											<tr>
												<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
														<input type="text" value="" class="addformtextField h30 w100per">
													</form></td>
											</tr>
										</tbody>
									</table>
								</div></td>
							<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
									<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
								</div></td>
						</tr>
						<tr>
							<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"></span></td>
							<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
								<div class="plbox hide">
									<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
										<tbody>
											<tr>
												<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
														<input type="text" value="" class="addformtextField h30 w100per">
													</form></td>
											</tr>
										</tbody>
									</table>
								</div></td>
							<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
									<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
								</div></td>
						</tr>
						<tr>
							<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"></span></td>
							<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
								<div class="plbox hide">
									<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
										<tbody>
											<tr>
												<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
														<input type="text" value="" class="addformtextField h30 w100per">
													</form></td>
											</tr>
										</tbody>
									</table>
								</div></td>
							<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
									<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
								</div></td>
						</tr>
						<tr>
							<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"></span></td>
							<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
								<div class="plbox hide">
									<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
										<tbody>
											<tr>
												<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
														<input type="text" value="" class="addformtextField h30 w100per">
													</form></td>
											</tr>
										</tbody>
									</table>
								</div></td>
							<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
									<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
								</div></td>
						</tr>
						<tr>
							<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"></span></td>
							<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
								<div class="plbox hide">
									<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
										<tbody>
											<tr>
												<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
														<input type="text" value="" class="addformtextField h30 w100per">
													</form></td>
											</tr>
										</tbody>
									</table>
								</div></td>
							<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
									<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
								</div></td>
						</tr>
						<tr>
							<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"></span></td>
							<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
								<div class="plbox hide">
									<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
										<tbody>
											<tr>
												<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
														<input type="text" value="" class="addformtextField h30 w100per">
													</form></td>
											</tr>
										</tbody>
									</table>
								</div></td>
							<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
									<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
								</div></td>
						</tr>
						<tr>
							<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"></span></td>
							<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
								<div class="plbox hide">
									<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
										<tbody>
											<tr>
												<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
														<input type="text" value="" class="addformtextField h30 w100per">
													</form></td>
											</tr>
										</tbody>
									</table>
								</div></td>
							<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
									<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
								</div></td>
						</tr>
						<tr>
							<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"></span></td>
							<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
								<div class="plbox hide">
									<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
										<tbody>
											<tr>
												<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
														<input type="text" value="" class="addformtextField h30 w100per">
													</form></td>
											</tr>
										</tbody>
									</table>
								</div></td>
							<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
									<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
								</div></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="np_clist" id="np3" style="display: none;">
				<div id="projcontent">
					<div id="announce" class="fr">
						<a onclick="javascript:$('#newprojannounce').show();" class="navLinkSmall" href="javascript:;"><u>新建公告</u></a>
					</div>
					<div class="hide" id="newprojannounce">
						<form class="formparam" onsubmit="javascript:$('#newprojannounce').hide(); return false;" method="POST" name="">
							<div class="txtSmall pl5 pb3 pT20">
								<img border="0" align="absmiddle" class="zpsort_desc" src="img/spacer.gif">&nbsp;<b>新建公告</b>
							</div>
							<table width="100%" cellspacing="0" cellpadding="0" border="0" align="center" class="txtSmall addformdiv">
								<tbody>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td width="25%" valign="top" align="right" class="addformlbl">公告标题 :</td>
										<td valign="top" class="addformlbl"><input type="text" class="addformtextField w95per" name=""></td>
									</tr>
									<tr>
										<td valign="top" align="right" class="addformlbl pt10">描述 :</td>
										<td valign="top" class="pb3 pt10 addformlbl"><textarea class="addformtextField w95per h100" name=""></textarea></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td class="addformlbl pb3"><span class="txtBold">备注:</span><span class="p5 txtDisabled">只有最新的公告或更新的公告在仪表板显示。</span></td>
									</tr>
								</tbody>
							</table>
							<table width="100%" cellspacing="0" cellpadding="0" border="0">
								<tbody>
									<tr>
										<td width="25%">&nbsp;</td>
										<td width="75%" align="left" class=" pt5"><input type="submit" class="buttonNew w100px" name="" value="提交"> &nbsp; <input type="button" onclick="$('#newprojannounce').hide();" class="buttonCancel w70" value="取消"></td>
									</tr>
								</tbody>
							</table>
						</form>
					</div>
					<br>
					<div id="projbody">
						<div class="showannounce">
							<div name="" class="projannouncementtitle">adsfasdf</div>
							<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
							<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
							<br>
							<div>
								<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a></span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
							</div>
							<div class="bdrbtm mb10 pb5"></div>
						</div>
						<div class="showannounce">
							<div name="" class="projannouncementtitle">adsfasdf</div>
							<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
							<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
							<br>
							<div>
								<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a></span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
							</div>
							<div class="bdrbtm mb10 pb5"></div>
						</div>
						<div class="showannounce">
							<div name="" class="projannouncementtitle">adsfasdf</div>
							<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
							<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
							<br>
							<div>
								<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a></span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
							</div>
							<div class="bdrbtm mb10 pb5"></div>
						</div>
						<div class="showannounce">
							<div name="" class="projannouncementtitle">adsfasdf</div>
							<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
							<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
							<br>
							<div>
								<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a></span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
							</div>
							<div class="bdrbtm mb10 pb5"></div>
						</div>
						<div class="showannounce">
							<div name="" class="projannouncementtitle">adsfasdf</div>
							<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
							<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
							<br>
							<div>
								<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a></span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
							</div>
							<div class="bdrbtm mb10 pb5"></div>
						</div>

						<div class="showannounce">
							<div name="" class="projannouncementtitle">adsfasdf</div>
							<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
							<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
							<br>
							<div>
								<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a></span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
							</div>
							<div class="bdrbtm mb10 pb5"></div>
						</div>
						<div class="showannounce">
							<div name="" class="projannouncementtitle">adsfasdf</div>
							<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
							<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
							<br>
							<div>
								<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a></span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
							</div>
							<div class="bdrbtm mb10 pb5"></div>
						</div>
						<div class="showannounce">
							<div name="" class="projannouncementtitle">adsfasdf</div>
							<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
							<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
							<br>
							<div>
								<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a></span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
							</div>
							<div class="bdrbtm mb10 pb5"></div>
						</div>
						<div class="showannounce">
							<div name="" class="projannouncementtitle">adsfasdf</div>
							<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
							<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
							<br>
							<div>
								<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a></span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
							</div>
							<div class="bdrbtm mb10 pb5"></div>
						</div>
						<div class="showannounce">
							<div name="" class="projannouncementtitle">adsfasdf</div>
							<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
							<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
							<br>
							<div>
								<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a></span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
							</div>
							<div class="bdrbtm mb10 pb5"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>