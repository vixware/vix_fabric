<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-desktop"></i> 办公 <span>&gt; 公告通知</span> <span>&nbsp; &gt; 培训需求征集通知</span>
			</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-9">
			<div class="well padding-10">
				<div class="row">
					<div class="col-md-12 padding-left-0">
						<h3 class="margin-top-0">
							<a href="javascript:void(0);" onclick="loadContent('notice','${nvix}/nvixnt/noticeAction!goList.action');">${calendars.scheduleName}</a>
						</h3>
						<div class="chat-body no-padding profile-message">${calendars.calendarsContent }</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-3">
			<div class="well padding-10">
				<h5 class="margin-top-0"><a href="#" onclick="loadContent('','${nvix}/nvixnt/hr/nvixNeedAction!goSaveOrUpdateNeedApply.action');">
					  <i class="fa fa-tags"></i> 回复：
					  </a>
				</h5>
			</div>
		</div>
	</div>
</div>