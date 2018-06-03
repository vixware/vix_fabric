<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="workDayForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/nvixContactPersonAction!saveOrUpdate.action" method="post" enctype="multipart/form-data">
	<input id="id" name="contactPerson.id" value="${contactPerson.id }" type="hidden" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"></label>
			<div class="col-md-2">
				<div data-toggle="buttons" class="btn-group"></div>
			</div>
			<label class="col-md-1 control-label"></label>
			<div class="col-md-2">
				<div class="input-group">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;休息开始时间</div>
			</div>
			<label class="col-md-1 control-label"></label>
			<div class="col-md-2">
				<div class="input-group">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;休息结束时间</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">星期日:</label>
			<div class="col-md-2">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="contactPerson.sex == 1">active</s:if>"> <input type="radio" value="1" id="sex" name="contactPerson.sex" <s:if test="contactPerson.sex == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="contactPerson.sex == 0">active</s:if>"> <input type="radio" value="0" id="sex" name="contactPerson.sex" <s:if test="contactPerson.sex == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-1 control-label"></label>
			<div class="col-md-2">
				<div class="input-group">
					<input id="lastName" name="contactPerson.lastName" value="${contactPerson.lastName}" placeholder="00:00" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
				</div>
			</div>
			<label class="col-md-1 control-label"></label>
			<div class="col-md-2">
				<div class="input-group">
					<input id="lastName" name="contactPerson.lastName" value="${contactPerson.lastName}" placeholder="00:00" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">星期一:</label>
			<div class="col-md-2">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="contactPerson.sex == 1">active</s:if>"> <input type="radio" value="1" id="sex" name="contactPerson.sex" <s:if test="contactPerson.sex == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="contactPerson.sex == 0">active</s:if>"> <input type="radio" value="0" id="sex" name="contactPerson.sex" <s:if test="contactPerson.sex == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-1 control-label"></label>
			<div class="col-md-2">
				<div class="input-group">
					<input id="lastName" name="contactPerson.lastName" value="${contactPerson.lastName}" placeholder="00:00" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
				</div>
			</div>
			<label class="col-md-1 control-label"></label>
			<div class="col-md-2">
				<div class="input-group">
					<input id="lastName" name="contactPerson.lastName" value="${contactPerson.lastName}" placeholder="00:00" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">星期二:</label>
			<div class="col-md-2">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="contactPerson.sex == 1">active</s:if>"> <input type="radio" value="1" id="sex" name="contactPerson.sex" <s:if test="contactPerson.sex == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="contactPerson.sex == 0">active</s:if>"> <input type="radio" value="0" id="sex" name="contactPerson.sex" <s:if test="contactPerson.sex == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-1 control-label"></label>
			<div class="col-md-2">
				<div class="input-group">
					<input id="lastName" name="contactPerson.lastName" value="${contactPerson.lastName}" placeholder="00:00" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
				</div>
			</div>
			<label class="col-md-1 control-label"></label>
			<div class="col-md-2">
				<div class="input-group">
					<input id="lastName" name="contactPerson.lastName" value="${contactPerson.lastName}" placeholder="00:00" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">星期三:</label>
			<div class="col-md-2">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="contactPerson.sex == 1">active</s:if>"> <input type="radio" value="1" id="sex" name="contactPerson.sex" <s:if test="contactPerson.sex == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="contactPerson.sex == 0">active</s:if>"> <input type="radio" value="0" id="sex" name="contactPerson.sex" <s:if test="contactPerson.sex == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-1 control-label"></label>
			<div class="col-md-2">
				<div class="input-group">
					<input id="lastName" name="contactPerson.lastName" value="${contactPerson.lastName}" placeholder="00:00" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
				</div>
			</div>
			<label class="col-md-1 control-label"></label>
			<div class="col-md-2">
				<div class="input-group">
					<input id="lastName" name="contactPerson.lastName" value="${contactPerson.lastName}" placeholder="00:00" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">星期四:</label>
			<div class="col-md-2">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="contactPerson.sex == 1">active</s:if>"> <input type="radio" value="1" id="sex" name="contactPerson.sex" <s:if test="contactPerson.sex == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="contactPerson.sex == 0">active</s:if>"> <input type="radio" value="0" id="sex" name="contactPerson.sex" <s:if test="contactPerson.sex == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-1 control-label"></label>
			<div class="col-md-2">
				<div class="input-group">
					<input id="lastName" name="contactPerson.lastName" value="${contactPerson.lastName}" placeholder="00:00" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
				</div>
			</div>
			<label class="col-md-1 control-label"></label>
			<div class="col-md-2">
				<div class="input-group">
					<input id="lastName" name="contactPerson.lastName" value="${contactPerson.lastName}" placeholder="00:00" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">星期五:</label>
			<div class="col-md-2">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="contactPerson.sex == 1">active</s:if>"> <input type="radio" value="1" id="sex" name="contactPerson.sex" <s:if test="contactPerson.sex == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="contactPerson.sex == 0">active</s:if>"> <input type="radio" value="0" id="sex" name="contactPerson.sex" <s:if test="contactPerson.sex == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-1 control-label"></label>
			<div class="col-md-2">
				<div class="input-group">
					<input id="lastName" name="contactPerson.lastName" value="${contactPerson.lastName}" placeholder="00:00" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
				</div>
			</div>
			<label class="col-md-1 control-label"></label>
			<div class="col-md-2">
				<div class="input-group">
					<input id="lastName" name="contactPerson.lastName" value="${contactPerson.lastName}" placeholder="00:00" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">星期六:</label>
			<div class="col-md-2">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="contactPerson.sex == 1">active</s:if>"> <input type="radio" value="1" id="sex" name="contactPerson.sex" <s:if test="contactPerson.sex == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="contactPerson.sex == 0">active</s:if>"> <input type="radio" value="0" id="sex" name="contactPerson.sex" <s:if test="contactPerson.sex == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-1 control-label"></label>
			<div class="col-md-2">
				<div class="input-group">
					<input id="lastName" name="contactPerson.lastName" value="${contactPerson.lastName}" placeholder="00:00" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
				</div>
			</div>
			<label class="col-md-1 control-label"></label>
			<div class="col-md-2">
				<div class="input-group">
					<input id="lastName" name="contactPerson.lastName" value="${contactPerson.lastName}" placeholder="00:00" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
				</div>
			</div>
		</div>
		<br />
		<div class="form-group">
			<label class="col-md-2 control-label">按日期设定休息日:</label>
			<div class="col-md-2">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="contactPerson.sex == 1">active</s:if>"> <input type="radio" value="1" id="sex" name="contactPerson.sex" <s:if test="contactPerson.sex == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="contactPerson.sex == 0">active</s:if>"> <input type="radio" value="0" id="sex" name="contactPerson.sex" <s:if test="contactPerson.sex == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">开始时间:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="lastName" name="contactPerson.lastName" value="${contactPerson.lastName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">00:00</i></span>
				</div>
			</div>
			<label class="col-md-3 control-label">结束时间:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="lastName" name="contactPerson.lastName" value="${contactPerson.lastName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">00:00</i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">每天工作时数:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="lastName" name="contactPerson.lastName" value="${contactPerson.lastName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">小&nbsp;&nbsp;时</i></span>
				</div>
			</div>
			<label class="col-md-3 control-label">每天工时大于:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="lastName" name="contactPerson.lastName" value="${contactPerson.lastName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">小&nbsp;&nbsp;时</i></span>
				</div>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	//表单校验
	$("#workDayForm").validationEngine();
</script>