<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>


<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-fw fa-user"></i> 会员营销 <span>&gt; 智能营销</span> <span>&gt; 营销流程监控</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-default" type="button" onclick="loadContent('drp_distributionsystem','${nvix}/nvixnt/vixntMarketingAutomationProcessAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-sm-12 col-md-12">
			<div class="well">
				<!-- 头部会员信息内容部分 -->
				<div class="row">
					<div class="col-xs-12">
						<div id="" class="jarviswidget">
							<header>
								<h2>流程信息</h2>
							</header>
							<div>
								<div class="widget-body no-padding">
									<div id="contentDiv"></div>
									<script>
									   var report = new Rreport({
										contentDiv : 'contentDiv',
										isEdit : true,
										});
										$('#vreporttoolbar').attr({ style : 'display:none'});
										report.loadData(${activityFlow});
										report.monitorNode(${executingProcessData});
							      </script>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div class="jarviswidget">
							<header>
								<span class="widget-icon"> <i class="fa fa-table"></i>
								</span>
								<h2>流程日志</h2>
							</header>
							<div>
								<div class="widget-body no-padding">
									<div id="">
										<form role="search" class="navbar-form navbar-left">
											<div class="form-group">
												主题: <input type="text" value="" class="form-control" id="selectName">
											</div>
											<button onclick="processLogTable.ajax.reload();" type="button" class="btn btn-info">
												<i class="glyphicon glyphicon-search"></i> 检索
											</button>
											<button onclick="$('#selectName').val('');processLogTable.ajax.reload();" type="button" class="btn btn-default">
												<i class="glyphicon glyphicon-repeat"></i> 清空
											</button>
										</form>
									</div>
									<table id="processLogTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	
	pageSetUp();
	
	var processLogColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "内容",
	"data" : function(data) {
		return data.logContent;
	}
	}, {
	"title" : "生成日期",
	"width" : "15%",
	"data" : function(data) {
		return data.createTimeTimeStr;
	}
	}];

	var processLogTable = initDataTable("processLogTableId", "${nvix}/nvixnt/vixntMarketingAutomationProcessAction!goNodesLogList.action", processLogColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize
		};
	}, 10, '0');

</script>