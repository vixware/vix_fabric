<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 办公 <span onclick="loadContent('notice','${nvix}/nvixnt/noticeAction!goPersonalAnnouncement.action');">&gt; 公告通知</span> <span onclick="loadContent('notice','${nvix}/nvixnt/noticeAction!goPersonalAnnouncement.action');">&gt; 个人公告通知</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<div class="btn-group">
					<button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
						个人公告通知 <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<li><a href="javascript:void(0);" onclick="loadContent('notice','${nvix}/nvixnt/noticeAction!goList.action');">公告通知管理</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<ul id="myTab1" class="nav nav-tabs bordered">
				<li class="active"><a href="#s1" data-toggle="tab">查看公告通知 <i class="fa fa-caret-down"></i></a></li>
			</ul>

			<div id="myTabContent1" class="tab-content bg-color-white padding-10">
				<div class="tab-pane fade in active" id="s1">
					<div class="input-group input-group-lg hidden-mobile">
						<div class="input-group-btn">
							<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">公告通知标题</button>
						</div>
						<input class="form-control input-lg" " type="text" name="announcementNotification.title" placeholder="填写公告通知标题..." id="searchproject">
						<div class="input-group-btn">
							<button onclick="SearchAnnouncement()" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#searchproject').val('');" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</div>
					</div>
					<div class="search-results clearfix" id="notices">
						<s:if test="announcementNotificationList.size > 0">
							<s:iterator value="announcementNotificationList" var="entity" status="s">
								<h4 class="margin-bottom-10">
									<a href="#" onclick="viewNotice('${entity.id}');"> ${entity.title} </a>
								</h4>
								<div class="margin-bottom-10">
									<p class="note margin-bottom-10">
										<a href="javascript:void(0);"><i class="fa fa-calendar"></i> <fmt:formatDate value="${entity.activeStartDate}" type="both" pattern="yyyy-MM-dd" />&nbsp;&nbsp; </a> <a href="javascript:void(0);"><i class="fa fa-calendar"></i> <fmt:formatDate value="${entity.activeEndDate}" type="both" pattern="yyyy-MM-dd" />&nbsp;&nbsp; </a> <a
											href="javascript:void(0);"><i class="fa fa-child"></i> ${entity.uploadPersonName}&nbsp;&nbsp;</a> <a href="javascript:void(0);"><i class="fa fa-comments"></i> ${entity.readTimes}条阅读记录&nbsp;&nbsp;</a> <a href="javascript:void(0);" style="color: #E63F00;"><i class="fa fa-caret-down"></i> <s:if test="%{#entity.bulletinType == 0}">公告</s:if>
											<s:elseif test="%{#entity.bulletinType == 1}">通知</s:elseif> </a>
									</p>
									<p class="description">&nbsp;&nbsp;&nbsp;&nbsp;${entity.content}</p>
								</div>
							</s:iterator>
						</s:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
function viewNotice(id) {
	$.ajax({
	url : '${nvix}/nvixnt/noticeAction!goViewNotice.action?id=' + id,
	cache : false,
	success : function(html) {
		$("#mainContent").html(html);
	}
	});
};

//查看页面搜索公告通知
function SearchAnnouncement(){
	var searchproject=$("#searchproject").val();
		searchproject=Url.encode(searchproject);
	$.ajax({
		url : '${nvix}/nvixnt/noticeAction!goNotices.action?title=' + searchproject,
		cache : false,
		success : function(obj){
		$("#notices").html(obj);
		}
	});
};
</script>