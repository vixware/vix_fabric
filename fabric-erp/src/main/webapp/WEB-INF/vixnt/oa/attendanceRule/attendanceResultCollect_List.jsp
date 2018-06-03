<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-tasks"></i> <a onclick="">考勤管理</a> <span>> 报表管理 </span> <span>> 月考勤汇总表 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="dealAttendance();">
						<i class="fa fa-th"></i>&nbsp;查询计算
					</button>
				</a><a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="goSaveOrUpdate('');">
						<i class="fa fa-share-square-o"></i>&nbsp;
						<s:text name="导出考勤汇总" />
					</button>
				</a>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>月考勤汇总表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div>
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										姓名: <input type="text" value="" class="form-control" id="empName">
									</div>
									<button onclick="dailyStatisticsTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#empName').val('');dailyStatisticsTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="dailyStatisticsTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	pageSetUp();
	var dailyStatisticsColumns = [ {
	"width" : "8%",
	"title" : "姓名",
	"data" : function(data) {
		return data.empName;
	}
	}, {
	"width" : "8%",
	"title" : "月份",
	"data" : function(data) {
		return data.dayAndMonth;
	}
	}, {
	"orderable" : false,
	"title" : "应到天数(天)",
	"data" : function(data) {
		return data.workDays;
	}
	}, {
	"orderable" : false,
	"title" : "实到天数(天)",
	"data" : function(data) {
		return data.cardNum;
	}
	}, {
	"orderable" : false,
	"title" : "事假(天)",
	"data" : function(data) {
		if (data.affairAbsence != null) {
			return "<span class='label label-primary'>" + data.affairAbsence + "</span>";
		}
		return "0";
	}
	}, {
	"orderable" : false,
	"title" : "病假(天)",
	"data" : function(data) {
		if (data.sickLeave != null) {
			return "<span class='label label-default'>" + data.sickLeave + "</span>";
		}
		return "0";
	}
	}, {
	"orderable" : false,
	"title" : "旷工(天)",
	"data" : function(data) {
		if (data.absenteeismNum != null) {
			return "<span class='label label-danger'>" + data.absenteeismNum + "</span>";
		}
		return "0";
	}
	}, {
	"orderable" : false,
	"title" : "市内出差(天)",
	"data" : function(data) {
		if (data.cityTravel != null) {
			return "<span class='label label-warning'>" + data.cityTravel + "</span>";
		}
		return "0";
	}
	}, {
	"orderable" : false,
	"title" : "外地出差(天)",
	"data" : function(data) {
		if (data.townonBusiness != null) {
			return "<span class='label label-warning'>" + data.townonBusiness + "</span>";
		}
		return "0";
	}
	}, {
	"orderable" : false,
	"title" : "换休(天)",
	"data" : function(data) {
		if (data.changeTheDayOff != null) {
			return "<span class='label label-warning'>" + data.changeTheDayOff + "</span>";
		}
		return "0";
	}
	}, {
	"orderable" : false,
	"title" : "婚假(天)",
	"data" : function(data) {
		if (data.marriageleave != null) {
			return "<span class='label label-warning'>" + data.marriageleave + "</span>";
		}
		return "0";
	}
	}, {
	"orderable" : false,
	"title" : "丧假(天)",
	"data" : function(data) {
		if (data.funeralLeave != null) {
			return "<span class='label label-warning'>" + data.funeralLeave + "</span>";
		}
		return "0";
	}
	}, {
	"orderable" : false,
	"title" : "产假(天)",
	"data" : function(data) {
		if (data.maternityLeave != null) {
			return "<span class='label label-warning'>" + data.maternityLeave + "</span>";
		}
		return "0";
	}
	}, {
	"orderable" : false,
	"title" : "哺乳假(天)",
	"data" : function(data) {
		if (data.breastfeedingLeave != null) {
			return "<span class='label label-warning'>" + data.breastfeedingLeave + "</span>";
		}
		return "0";
	}
	}, {
	"orderable" : false,
	"title" : "产后长假(天)",
	"data" : function(data) {
		if (data.postPartum != null) {
			return "<span class='label label-warning'>" + data.postPartum + "</span>";
		}
		return "0";
	}
	}, {
	"orderable" : false,
	"title" : "计划生育假(天)",
	"data" : function(data) {
		if (data.familyPlanning != null) {
			return "<span class='label label-warning'>" + data.familyPlanning + "</span>";
		}
		return "0";
	}
	}, {
	"orderable" : false,
	"title" : "护理假(天)",
	"data" : function(data) {
		if (data.nursingLeave != null) {
			return "<span class='label label-warning'>" + data.nursingLeave + "</span>";
		}
		return "0";
	}
	}, {
	"orderable" : false,
	"title" : "探亲假(天)",
	"data" : function(data) {
		if (data.homeLeave != null) {
			return "<span class='label label-warning'>" + data.homeLeave + "</span>";
		}
		return "0";
	}
	}, {
	"orderable" : false,
	"title" : "年休假(天)",
	"data" : function(data) {
		if (data.annualLeave != null) {
			return "<span class='label label-warning'>" + data.annualLeave + "</span>";
		}
		return "0";
	}
	}, {
	"orderable" : false,
	"title" : "补休假(天)",
	"data" : function(data) {
		if (data.takedeferredholiDays != null) {
			return "<span class='label label-warning'>" + data.takedeferredholiDays + "</span>";
		}
		return "0";
	}
	}, {
	"orderable" : false,
	"title" : "产前假(天)",
	"data" : function(data) {
		if (data.antenatal != null) {
			return "<span class='label label-warning'>" + data.antenatal + "</span>";
		}
		return "0";
	}
	}, {
	"orderable" : false,
	"title" : "加班(天)",
	"data" : function(data) {
		if (data.overtime != null) {
			return "<span class='label label-warning'>" + data.overtime + "</span>";
		}
		return "0";
	}
	}, {
	"orderable" : false,
	"width" : "5%",
	"title" : "操作",
	"data" : function(data) {
		return "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
	}
	} ];

	var dailyStatisticsTable = initDataTable("dailyStatisticsTableId", "${nvix}/nvixnt/attendanceResultCollectAction!goMonthlyStatisticsList.action", dailyStatisticsColumns, function(page, pageSize, orderField, orderBy) {
		var empName = $("#empName").val();
		empName = Url.encode(empName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"empName" : empName
		};
	}, 10, "1", "0");

	function dealAttendance() {
		$.ajax({
		url : '${nvix}/nvixnt/attendanceResultCollectAction!dealEmpMonthCardRecords.action',
		cache : false,
		success : function() {
			dailyStatisticsTable.ajax.reload();
		}
		});
	}
</script>