<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-desktop"></i> 办公 <span>&gt; 公告通知</span> <span>&nbsp; &gt; 公告通知详情</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<c:if test="${syncTag == 'notice'}">
					<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/noticeAction!goList.action');">
						<i class="fa fa-rotate-left"></i>&nbsp;返回
					</button>
				</c:if>
				<c:if test="${syncTag == 'home'}">
					<button class="btn btn-default" type="button" onclick="loadContent('mid_index','${nvix}/nvixnt/vixNtIndexAction!goListContent.action');">
						<i class="fa fa-rotate-left"></i> 返回
					</button>
				</c:if>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-9">
			<div class="well padding-10">
				<div class="row">
					<div class="col-md-12 padding-left-0">
						<h3 class="margin-top-0">
							<a href="javascript:void(0);" onclick="loadContent('notice','${nvix}/nvixnt/noticeAction!goList.action');">${announcementNotification.title}</a>
						</h3>
						<p class="note margin-bottom-10">
							<a href="javascript:void(0);"><i class="fa fa-calendar"></i> <fmt:formatDate value="${announcementNotification.activeStartDate}" type="both" pattern="yyyy-MM-dd" />&nbsp;&nbsp; </a> <a href="javascript:void(0);"><i class="fa fa-calendar"></i> <fmt:formatDate
									value="${announcementNotification.activeEndDate}" type="both" pattern="yyyy-MM-dd" />&nbsp;&nbsp; </a> <a href="javascript:void(0);"><i class="fa fa-child"></i> ${announcementNotification.uploadPersonName}&nbsp;&nbsp;</a> <a href="javascript:void(0);"><i class="fa fa-comments"></i>
								${announcementNotification.readTimes}条阅读记录&nbsp;&nbsp;</a> <a href="javascript:void(0);">发布对象:&nbsp;${announcementNotification.pubNames}&nbsp;&nbsp;</a> <a href="javascript:void(0);"><i class="fa fa-caret-down"></i> <s:if test="%{announcementNotification.bulletinType == 0}">公告</s:if> <s:elseif
									test="%{announcementNotification.bulletinType == 1}">通知</s:elseif> </a>
						</p>
						<div class="chat-body no-padding profile-message">${announcementNotification.content }</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-3">
			<div class="well padding-10">
				<h5 class="margin-top-0">
					<i class="fa fa-tags"></i> 关键字：
				</h5>
				<div class="row">
					<div class="col-lg-6">
						<ul class="list-unstyled">
							<li><a href=""><span class="badge badge-info">${announcementNotification.keywords}</span></a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="well padding-10">
				<h5 class="margin-top-0">
					<i class="fa fa-thumbs-o-up"></i> 内容简介:
				</h5>
				<div class="row">
					<div class="col-lg-6">
						<ul class="list-unstyled">
							<li><a href=""><span class="badge badge-info">${announcementNotification.plotSummary}</span></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>