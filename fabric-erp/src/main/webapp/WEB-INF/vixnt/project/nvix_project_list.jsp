<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<style>
	.ibox-content .itemBox{overflow:hidden;padding:10px 0;border-top:1px solid #e4e4e4;background:#fbfbfd;}
	.ibox-content .itemBox .itemBoxList{float:left;width:25%;box-sizing:border-box;border-right:1px solid #e4e4e4;padding:0 20px;}
	.ibox-content .itemBox .itemBoxList:last-child{border-right:none;}
</style>
<div id="content">
	<div class="row">
		<div class="col-sm-12 col-md-12">
			<div class="well" style="min-height:800px">
				<div class="row">
					<c:forEach items="${projectList}" var="project">
						<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
							<div class="well padding-0">
								<div class="ibox float-e-margins">
									<div class="ibox-title" style="padding:0 20px;">
		                                <span class="label label-success pull-right"><i class="fa fa-chevron-down"></i></span>
		                                <h5>${project.projectName}</h5>
		                            </div>
		                            <hr class="simple" style="border-color: #c0c0c0;">
		                             <div class="ibox-content" style="padding:20px 0;">
		                                <h1 class="no-margins txt-color-blue text-center">任务总数<span class="txt-color-blue">${project.taskNum} </span></h1>
		                            </div>
		                            <div class="ibox-content">
		                            	<div class="itemBox">
		                            		<div class="itemBoxList text-center">
		                            			<p class="txt-color-gray">未开始任务</p>
		                            			<p class="txt-color-blueN">${project.nostartTaskNum}</p>
		                            		</div>
		                            		<div class="itemBoxList text-center">
		                            			<p class="txt-color-gray">进行中任务</p>
		                            			<p class="txt-color-orangeN">${project.progressTaskNum}</p>
		                            		</div>
		                            		<div class="itemBoxList text-center">
		                            			<p class="txt-color-gray">已完成任务</p>
		                            			<p class="txt-color-greenN">${project.finishTaskNum}</p>
		                            		</div>
		                            		<div class="itemBoxList text-center">
		                            			<p class="txt-color-gray">已超时任务</p>
		                            			<p class="txt-color-redN">${project.timeoutTaskNum}</p>
		                            		</div>
		                            	</div>
		                            </div>
		                        </div>
							</div> 
						</div>
					</c:forEach>
					<%-- <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
						<div class="well padding-20" style="background:#fbfbfd">
							<div class="additem text-center" style="margin:50px 0;">
								<img src="${nvix}/vixntcommon/base/images/additem.png">
								<br/>
								<p class="txt-color-gray margin-top-10">添加项目集</p>
							</div>
							
						</div>
					</div> --%>
				</div>
				<br>
			</div>
		</div>
	</div>
</div>