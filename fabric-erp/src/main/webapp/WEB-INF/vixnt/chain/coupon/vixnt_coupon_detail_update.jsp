<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="couponForm" class="form-horizontal" style="padding:40px 40px;" action="${snow}/ec/activity/couponAction!saveOrUpdate.action" >
	<fieldset>
		<%-- <div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
			<div class="col-md-4">
				<input id="code" name="coupon.code" value="${coupon.code}" type="text" data-prompt-position="topLeft" class="form-control validate[required]" />
			</div>
			<label class="col-md-2 control-label">密码:</label>
			<div class="col-md-4">
				<input id="password" name="coupon.password" value="${coupon.password}" type="password" class="form-control" />
			</div>
		</div> --%>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>开始时间:</label>
			<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<input id="startTime" name="coupon.startTime" value="${coupon.startTimeStr}" type="text" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
					<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'startTime'});"><i class="fa fa-calendar"></i></span>
				</div>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>结束时间:</label>
			<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<input id="endTime" name="coupon.endTime" value="${coupon.endTimeStr}" type="text" data-prompt-position="topLeft" onblur="dateTimeRange();"  class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
					<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'endTime'});"><i class="fa fa-calendar"></i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>面值:</label>
			<div class="col-md-4">
				<input name="" value="" type="text" class="form-control" />
			</div>
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-4">
				<input id="memo" name="coupon.memo" value="${coupon.memo}" type="text" class="form-control" />
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#couponForm").validationEngine();
	
</script>