<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="${vix}/common/css/switch.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${vix}/common/js/switch.js"></script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="img/icon_14.gif" alt="" />打印</a> <a href="#" id="help"><img src="img/icon_15.gif" alt="" />帮助</a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/hr_staff.png" class="png" alt="" width="26" height="26" />&nbsp;人力资源</a></li>
				<li><a href="#">员工自助</a></li>
				<a href="#">员工自助平台</a>
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
	<div class="npcontent clearfix" id="onresizeBox">

		<!--左右翻页#418eb8-->
		<!-- <span class="pre">&lt;</span><span class="next">&gt;</span>  -->

		<!--DEMO start-->
		<div style="width: 850px; height: 98%; margin: 0 auto; overflow: hidden;">
			<div style="height: 15%; padding-top: 15px;">
				<!-- 弹性高度 -->
				<!-- 人员搜索 -->
				<div class="search">
					<div class="logo"></div>
					<div class="inner">
						<input type="text" name="" class="text" /> <input type="submit" value=" 查询 " class="btn" />
					</div>
				</div>
			</div>
			<div class="ibox" style="height: 80%; margin: 0px;">
				<!-- 弹性高度 -->
				<div class="showbox">
					<ul>
						<li>
							<div style="width: 850px;">
								<div class="con_inner">
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/hr/responsibilitiesAction!goList.action');" onfocus="this.blur()" class="icon_3_1">岗位职责</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/employeeQueryAction!goList.action');" onfocus="this.blur()" class="icon_3_2">员工信息</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/insuranceAction!goList.action');" onfocus="this.blur()" class="icon_3_3">保险信息</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/jobPerformanceAction!goList.action');" onfocus="this.blur()" class="icon_3_4">工作绩效</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/salaryInformationAction!goList.action');" onfocus="this.blur()" class="icon_3_5">工资信息</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/applyregistrationAction!goList.action');" onfocus="this.blur()" class="icon_3_6">应聘报名</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/trainDemAction!goSaveOrUpdate.action');" onfocus="this.blur()" class="icon_3_7">培训需求</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/demandNoticeAction!goSaveOrUpdate.action');" onfocus="this.blur()" class="icon_3_8">培训通知</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/trainDemAction!goSaveOrUpdate.action');" onfocus="this.blur()" class="icon_3_9">培训登记</a></li>
									</ul>
								</div>
							</div>
						</li>
					</ul>
				</div>
				<!-- <div class="links">
					<ul>
						<li><a href="#" class="icon_txl">通讯录</a></li>
						<li><a href="#" class="icon_gzbz">工作布置</a></li>
						<li><a href="#" class="icon_gzzd">规章制度</a></li>
					</ul>
				</div> -->
				<div class="num">
					<ul>
						<li>&nbsp;</li>
						<li class="numcur">员工自助平台</li>
						<li>&nbsp;</li>
					</ul>
				</div>
			</div>
		</div>
		<!--DEMO end-->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>