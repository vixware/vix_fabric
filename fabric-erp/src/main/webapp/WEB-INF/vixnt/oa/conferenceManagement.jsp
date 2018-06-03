<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-group"></i> 会议室管理
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<div class="btn-group">
					<button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
						会议室视图 <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<li><a href="javascript:void(0);" onclick="loadContent('','${nvix}/nvixnt/conferenceManagementAction!goConferenceRoomManagement.action');">会议室管理</a></li>
						<li><a href="javascript:void(0);" onclick="loadContent('','${nvix}/nvixnt/conferenceManagementAction!goApplyApplicationMg.action');">会议管理</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div class="row">
			<s:if test="meetingRequestList.size > 0">
				<s:iterator value="meetingRequestList" var="entity" status="s">
					<div class="col-sm-6" href="javascript:void(0);">
						<s:if test="%{#entity.meetingRoomStatus == 0}">
							<div class="well well-sm bg-color-teal txt-color-white text-center">
								<p>
									<span class="txt-color-bluee pull-right"> <i class="fa fa-check-circle-o"></i> 已启用
									</span>
								</p>
								<h5>
									<div class="btn-group">
										<button class="btn btn-primary" onclick="saveOrUpdate('${entity.id}');">${entity.meetingName}</button>
										<button class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
											<span class="caret"></span>
										</button>
										<ul class="dropdown-menu">
											<li><a href="javascript:void(0);" onclick="saveOrUpdate('${entity.id}');">申请会议</a></li>
											<li class="divider"></li>
											<li><a href="javascript:void(0);" onclick="viewRoom('${entity.id}');">查看详情</a></li>
										</ul>
									</div>
								</h5>
								<br>
								<h5>
									<ul class="list-inline padding-10">
										<li>可容纳人数： ${entity.capacity}</li>
										<li>管理员： ${entity.creator}</li>
										<li>设备情况： ${entity.equipment}</li>
									</ul>
								</h5>
							</div>
						</s:if>
						<s:elseif test="%{#entity.meetingRoomStatus == 1}">
							<div class="well well-sm bg-color-pinkDark txt-color-white text-center">
								<p>
									<span class="txt-color-red pull-right"> <i class="fa fa-ban"></i> 已禁用
									</span>
								</p>
								<h5>
									<div class="btn-group">
										<button class="btn btn-primary" onclick="viewRooms('${entity.id}');">${entity.meetingName}</button>
									</div>
								</h5>
								<br>
								<h5>
									<ul class="list-inline padding-10">
										<li>禁用原因： ${entity.name}</li>
										<li>禁用： <fmt:formatDate value="${entity.allowedStartTime}" type="both" pattern="yyyy-MM-dd HH:mm" />
										</li>
										<li>启用： <fmt:formatDate value="${entity.allowedEndTime}" type="both" pattern="yyyy-MM-dd HH:mm" />
										</li>
									</ul>
								</h5>
							</div>
						</s:elseif>
					</div>
				</s:iterator>
			</s:if>
		</div>
	</section>
</div>
<script type="text/javascript">
	//查看会议室的使用情况并申请会议
	function saveOrUpdate(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/conferenceManagementAction!goApplyRoom.action?id=' + id, "applyRoomForm", "申请会议", 1100, 500);
	}

	//查看会议室的使用情况
	function viewRoom(id) {
		openWindowForShow('${nvix}/nvixnt/conferenceManagementAction!goViewRoom.action?id=' + id, "查看会议室使用详情", 1100, 500);
	}

	//查看会议室
	function viewRooms(id) {
		openWindowForShow('${nvix}/nvixnt/conferenceManagementAction!goViewRooms.action?id=' + id, "查看会议室信息", 800, 400);
	}
</script>
