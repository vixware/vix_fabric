<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<input type="hidden" id="chooseType" value="${chooseType}" />
<div class="jarviswidget">
	<header style="height: 1px; border-color: #ddd !important"></header>
	<div>
		<div class="widget-body no-padding">
			<div style="margin: 5px;">
				<div class="form-group" style="margin: 0 5px;">
					<div class="col-md-4">
						<div id="treeMenu" class="input-group col-md-12">
							<input type="hidden" id="chooseOrgId" value="" /> 
							<input id="chooseOrgName" type="text" onfocus="showMenu(); return false;" value="" placeholder="部门" class="form-control" />
							<div id="menuContent" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
								<ul id="chooseOrgZtree" class="ztree"></ul>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<input type="text" value="" placeholder="名称" class="form-control" id="selectName">
					</div>
					<button onclick="chooseEmployeeTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button type="button" class="btn btn-default" onclick="$('#selectName').val('');$('#chooseOrgId').val('');$('#chooseOrgName').val('');chooseEmployeeTable.ajax.reload();">
						<i class="glyphicon glyphicon-repeat"></i> 清空
					</button>
				</div>
			</div>
			<table id="chooseEmployeeTableId" class="table table-striped table-bordered table-hover" width="100%">
				<thead>
					<tr>
						<th width="10%"><s:if test="chooseType == 'multi'">
								<div class="btn-group">
									<button data-toggle="dropdown" class="btn dropdown-toggle btn-xs btn-default" aria-expanded="false">
										选择 <i class="fa fa-caret-down"></i>
									</button>
									<ul class="dropdown-menu js-status-update pull-left">
										<li><a id="all" onclick="changeDataTableSelect('chooseEmployeeTableId','all');" href="javascript:void(0);">全选</a></li>
										<li><a id="reverse" onclick="changeDataTableSelect('chooseEmployeeTableId','reverse');" href="javascript:void(0);">反选</a></li>
										<li><a id="cancle" onclick="changeDataTableSelect('chooseEmployeeTableId','cancle');" href="javascript:void(0);">取消</a></li>
									</ul>
								</div>
							</s:if> <s:else>选择</s:else></th>
						<th width="35%">名称</th>
						<th width="45%">职位</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
	var chooseType = $("#chooseType").val();
	chooseMap.setColumnName("name");

	var chooseEmployeeColumns = [ {
		"data" : function(data) {
			if (chooseType == 'multi') {
				return "<input id='entityId' name='entityId' value='" + data.id + "' type='checkBox' onchange=\"checkBoxChange('${entityName}',this,'" + data.id + "','" + data.name + "');\"/>";
			}
			if (chooseType == 'single') {
				return "<input id='entityId' name='entityId' value='" + data.id + "' type='radio' onchange=\"radioChange('${entityName}','" + data.id + "','" + data.name + "');\"/>";
			}
			return "";
		}
	}, {
		"data" : function(data) {
			// return "<div class='project-members'><a href='javascript:void(0)'><img src='"+data.headImgUrl +"'> " + data.name + "</a></div> ";
			return data.name;
		}
	}, {
		"data" : function(data) {
			return data.position;
		}
	} ];
	var selectType = chooseType == "multi" ? "2" : "1";
	var isGenerateIndex = chooseType == "multi" ? "1" : "0";
	var chooseEmployeeTable = initDataTable("chooseEmployeeTableId", "${nvix}/nvixnt/nvixntUserAccountAction!goEmployeeList.action", chooseEmployeeColumns, function(page, pageSize, orderField, orderBy) {
		var employeeName = $("#selectName").val();
		employeeName = Url.encode(employeeName);
		var orgId = $("#chooseOrgId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"employeeName" : employeeName,
		"orgId" : orgId
		};
	}, 6, selectType, isGenerateIndex);
	/** 初始化分类下拉列表树 */
	var showMenu = initDropListTree("treeMenu", "${nvix}/nvixnt/wxpEmployeeAction!findOrgAndUnitTreeToJson.action", "chooseOrgId", "chooseOrgName", "chooseOrgZtree", "menuContent");
</script>