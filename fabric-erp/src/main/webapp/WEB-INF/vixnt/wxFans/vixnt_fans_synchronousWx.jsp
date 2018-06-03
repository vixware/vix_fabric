<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
			<div class="row">
				<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
					<h1 class="page-title txt-color-blueDark">
						<i class="fa fa-list-alt fa-fw "></i> 微信管理 <span>> 同步微信粉丝</span>
					</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12 col-md-12">
					<div class="well">
						<div class="row">
							<div class="col-xs-6">
								<a href="javascript:;" class="btn btn-primary btn-sm" onclick="showDiv1()">
									<i class="fa fa-cloud"></i>&nbsp;&nbsp;同步关注粉丝
								</a>
								<div class="alert alert-block alert-info" style="margin-top:10px;">
									<strong><i class="icon-ok"></i>当前状态</strong>&nbsp;&nbsp;同步操作挂起中<br>
									<strong><i class="icon-ok"></i>关注粉丝总数</strong>&nbsp;&nbsp;从未进行同步<br>
									<strong><i class="icon-ok"></i>总共同步次数</strong>&nbsp;&nbsp;1<br>
									<strong><i class="icon-ok"></i>最后同步时间</strong>&nbsp;&nbsp;2017-11-08 11:23:28&nbsp;&nbsp;至&nbsp;&nbsp;2017-11-08 11:23:28
								</div>
							</div>
							<div class="col-xs-2"></div>
							<div class="col-xs-4 hidden-phone">
								<h5 class="header" style="border-bottom:1px solid #2c699d;padding-bottom:5px;">
									关注用户同步
								</h5>
								<small>同步微信关注用户操作一般只需要在系统第一次使用时执行，将存在的微信粉丝同步到系统中</small><br>
								<small>系统正常运行的情况下，新用户关注时会自动将该用户信息同步到系统中</small>
								<small>同步进程为后台进程，整个同步过程可能会进行较长的时间，请不要重复执行同步操作</small>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 弹出框 -->
	<div class="dialog_appointment1">
		<div class="dialog_appointment1_middle">
			<div class="dialog_appointment1_con">
				<div class="dialog_appointment1_main">此前如果已经执行过同步操作，一般不需要再次执行，受关注粉丝数量影响，此操作可能需要在后台执行较长时间，是否确认执行？</div>
				<div class="dialog_appointment1_footer">
					<button class="btn btn-sm btn-primary">保存</button>
					<button class="btn btn-sm bg-color-blueDark txt-color-white" onclick="hideDiv1()">关闭</button>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
function showDiv1(){
	$(".dialog_appointment1").show();
};
function hideDiv1(){
	$(".dialog_appointment1").hide();
}
</script>