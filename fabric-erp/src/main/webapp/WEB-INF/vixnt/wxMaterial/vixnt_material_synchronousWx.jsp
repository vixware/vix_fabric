<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
			<div class="row">
				<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
					<h1 class="page-title txt-color-blueDark">
						<i class="fa fa-list-alt fa-fw "></i> 微信管理 <span>> 微信素材同步</span>
					</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12 col-md-12">
					<div class="well">
						<div class="row">
							<div class="col-xs-6">
								<a href="javascript:;" class="btn btn-primary btn-sm" onclick="showDiv1()">
									<i class="fa fa-cloud"></i>&nbsp;&nbsp;同步微信素材总数
								</a>
								<div class="alert alert-block alert-info" style="margin-top:10px;">
									<strong><i class="icon-ok"></i>图文总数量</strong>&nbsp;&nbsp;2<br>
									<strong><i class="icon-ok"></i>图片总数量</strong>&nbsp;&nbsp;5<br>
									<strong><i class="icon-ok"></i>语音总数量</strong>&nbsp;&nbsp;0<br>
									<strong><i class="icon-ok"></i>视频总数量</strong>&nbsp;&nbsp;0<br>
									<strong><i class="icon-ok"></i>最后同步时间</strong>&nbsp;&nbsp;2017-11-06 15:16:18	
								</div>
							</div>
							<div class="col-xs-2"></div>
							<div class="col-xs-4 hidden-phone">
								<h5 class="header" style="border-bottom:1px solid #2c699d;padding-bottom:5px;">
									关微信素材总数
								</h5>
								<small>同步同步微信素材总数操作一般只需要在系统第一次使用时执行，将存在的微信素材总数同步到系统中</small><br>
								<small>永久素材的总数，也会计算公众平台官网素材管理中的素材</small>
								<small>图片和图文消息素材（包括单图文和多图文）的总数上限为5000，其他素材的总数上限为1000</small>
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
				<div class="dialog_appointment1_main">此前如果已经执行过同步操作，一般不需要再次执行，是否确认执行？</div>
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
};
function showDiv(){
	$(".dialog_appointment").show();
};
function hideDiv(){
	$(".dialog_appointment").hide();
}
</script>