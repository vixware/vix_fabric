<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-user"></i> 会员营销<span>> 促销规则 </span>
			</h1>
		</div>
		<div class="col-lg-8 text-align-right">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-12 text-align-right">
				<button class="btn btn-primary" type="button" onclick="saveOrUpdate('','新增');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i>
			</span>
			<h2>促销规则列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							名称: <input type="text" value="" placeholder="名称" class="form-control" id="searchName">
						</div>

						<button onclick="promotionRuleTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="clearSearchCondition('searchName',promotionRuleTable);" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="promotionRule" class="table table-striped table-bordered table-hover" width="100%">
					<thead>
						<tr>
							<th width="10%">编号</th>
							<th width="13%">促销策略</th>
							<th width="15%">名称</th>
							<th width="15%">开始时间</th>
							<th width="15%">结束时间</th>
							<th width="10%">状态</th>
							<th width="15%">备注</th>
							<th width="10%">操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var promotionRuleColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return "";
	}
	}, {
	"name" : "promotionType",
	"data" : function(data) {
		if (data.promotionType == 'PROMOTION_PRICE') {
			return "促销价(单品)";
		}
		if (data.promotionType == 'PROMOTION_DISCOUNT') {
			return "价格折扣(单品)";
		}
		if (data.promotionType == 'PROMOTION_SECONDSALE') {
			return "秒杀(单品)";
		}
		if (data.promotionType == 'PROMOTION_SALEGIFT') {
			return "买赠(单品)";
		}
		if (data.promotionType == 'PROMOTION_FULLREDUCE') {
			return "满减(整单)";
		}
		if (data.promotionType == 'PROMOTION_FULLGIFT') {
			return "满赠(整单)";
		}
		if (data.promotionType == 'PROMOTION_DOUBLEINTEGRAL') {
			return "积分翻倍(整单)";
		}
		if (data.promotionType == 'PROMOTION_FREEFREIGHT') {
			return "免运费(整单)";
		}
		if (data.promotionType == 'PROMOTION_COUPON') {
			return "优惠券(整单)";
		}
		if (data.promotionType == 'PROMOTION_DISCOUNTCB') {
			return "价格折扣(全场)";
		}
		return '';
	}
	}, {
	"name" : "name",
	"data" : function(data) {
		return "<a href='javascript:void(0);' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'>"+data.name+"</a>";
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.startTimeStr;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.endTimeStr;
	}
	}, {
	"name" : "status",
	"data" : function(data) {
		if (data.status == '0') {
			return "禁用";
		}
		if (data.status == '1') {
			return "启用";
		}
		if (data.status == '2') {
			return "活动结束";
		}
		return '';
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.memo;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		var update = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];
	var promotionRuleTable = initDataTable("promotionRule", "${nvix}/nvixnt/nvixntPromotionRuleAction!goSingleList.action", promotionRuleColumns, function(page, pageSize, orderField, orderBy) {
		var name = $("#searchName").val();
		name = Url.encode(name);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"name" : name
		};
	});

	function saveOrUpdate(id, title) {
		$.ajax({
		url : '${nvix}/nvixnt/nvixntPromotionRuleAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#content").html(html);
		}
		});
	};

	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixntPromotionRuleAction!deleteById.action?id=' + id, '是否删除促销规则?', promotionRuleTable);
	}

	/** 页面加载完调用 */
	pageSetUp();
</script>