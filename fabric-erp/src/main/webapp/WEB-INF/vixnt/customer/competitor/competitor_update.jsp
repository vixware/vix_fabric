<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 客户关系管理 <span>> 售前管理 </span><span>> 竞争对手 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixCompetitorAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>竞争对手</h2>
		</header>
		<div class="widget-body">
			<form id="competitorForm" class="form-horizontal">
				<input type="hidden" id="id" name="competitor.id" value="${competitor.id}" /> 
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>公司名称:</label>
						<div class="col-md-8">
							<input id="companyName" name="competitor.companyName" value="${competitor.companyName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<%-- <label class="col-md-2 control-label"><span class="text-danger">*</span>项目:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="projectId" name="competitor.crmProject.id" value="${competitor.crmProject.id}" /> 
								<input id="projectName" name="competitor.crmProject.subject" value="${competitor.crmProject.subject}" onfocus="chooseCrmProject();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseCrmProject();">选择</span>
							</div>
						</div> --%>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>客户名称:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="customerId" name="competitor.customerAccount.id" value="${competitor.customerAccount.id}" /> 
								<input id="customerName" name="competitor.customerAccount.name" value="${competitor.customerAccount.name}" onfocus="chooseCustomer();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" style="cursor: pointer;" readonly="readonly" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseCustomer();">选择</span>
							</div>
						</div>
						<label class="col-md-2 control-label">对应机会:</label>
						<div class="col-md-3">
							<select id="saleChanceId" name="competitor.saleChance.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${saleChanceList}" var="entity">
									<option value="${entity.id}" <c:if test="${competitor.saleChance.id == entity.id}">selected="selected"</c:if>>${entity.subject}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>价格:</label>
						<div class="col-md-3">
							<input id="price" name="competitor.price" value="${competitor.price}" data-prompt-position="topLeft" class="form-control validate[required,custom[number],min[1]]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>竞争能力:</label>
						<div class="col-md-3">
							<select id="competitiveId" name="competitor.competitive.id" class="form-control validate[required]">
								<option value="">------请选择------</option>
								<c:forEach items="${competitiveList}" var="entity">
									<option value="${entity.id}" <c:if test="${competitor.competitive.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<%-- <div class="form-group">
						<label class="col-md-2 control-label">联系人:</label>
						<div class="col-md-3">
							<input id="contactPerson" name="competitor.contactPerson" value="${competitor.contactPerson}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label">联系人电话:</label>
						<div class="col-md-3">
							<input id="cpPhone" name="competitor.cpPhone" value="${competitor.cpPhone}" data-prompt-position="topLeft" class="form-control validate[custom[phone]]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">公司地址:</label>
						<div class="col-md-3">
							<input id="address" name="competitor.address" value="${competitor.address}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label">公司网站:</label>
						<div class="col-md-3">
							<input id="website" name="competitor.website" value="${competitor.website}" data-prompt-position="topLeft" class="form-control validate[custom[url]" type="text" />
						</div>
					</div> --%>
					<div class="form-group">
						<label class="col-md-2 control-label">竞争产品:</label>
						<div class="col-md-8">
							<textarea id="competeProduct" name="competitor.competeProduct" class="form-control" rows="4">${competitor.competeProduct}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">优势:</label>
						<div class="col-md-3">
							<textarea id="superiority" name="competitor.superiority" class="form-control" rows="4">${competitor.superiority}</textarea>
						</div>
						<label class="col-md-2 control-label">劣势:</label>
						<div class="col-md-3">
							<textarea id="inferior" name="competitor.inferior" class="form-control" rows="4">${competitor.inferior}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">应用策略:</label>
						<div class="col-md-3">
							<textarea id="copeStrategy" name="competitor.copeStrategy" class="form-control" rows="4">${competitor.copeStrategy}</textarea>
						</div>
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-3">
							<textarea id="memo" name="competitor.memo" class="form-control" rows="4">${competitor.memo}</textarea>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button type="button" class="btn btn-success" onclick="saveOrUpdate('');">
								<i class="fa fa-save"></i> 保存
							</button>
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixCompetitorAction!goList.action');">
								<i class="fa fa-rotate-left"></i> 返回
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	$("#competitorForm").validationEngine();

	function saveOrUpdate(id) {
		//表单验证
		if ($("#competitorForm").validationEngine('validate')) {
			$("#competitorForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/nvixCompetitorAction!saveOrUpdate.action?id=" + $("#id").val(),
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(result) {
				showMessage(result);
				loadContent('', '${nvix}/nvixnt/nvixCompetitorAction!goList.action');
			}
			});
		}
	}
	
	/** 加载机会*/
	function loadSaleChance() {
		if ($("#customerId").val() != '') {
			$.ajax({
				url : '${nvix}/nvixnt/nvixCompetitorAction!loadSaleChance.action?id=' + $("#customerId").val(),
				cache : false,
				success : function(html) {
					var saleChanceId = $("#saleChanceId").val();
					$("#saleChanceId").html(html);
					/* if (saleChanceId != '') {
						$("#saleChanceId").val(saleChanceId);
					} */
				}
			});
		}
	}
	
	function chooseCustomer() {
		chooseListData('${nvix}/nvixnt/nvixCustomerAction!goChooseCustomerAccount.action', 'single', '选择客户', 'customer',function(){
			loadSaleChance();
		});
	}
	
	function chooseCrmProject() {
		chooseListData('${nvix}/nvixnt/nvixCrmProjectAction!goChooseCrmProject.action', 'single', '选择项目', 'project');
	}
</script>