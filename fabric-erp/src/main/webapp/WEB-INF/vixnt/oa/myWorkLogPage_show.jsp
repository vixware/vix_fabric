<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<s:iterator value="pager.resultList" var="entity" status="s">
	<h4 class="margin-bottom-10">
		<a href="#" onclick="viewLog('${entity.id}','my');"> ${entity.title} </a>
	</h4>
	<div class="margin-bottom-10">
		<p class="note margin-bottom-10">
			<a href="javascript:void(0);"><i class="fa fa-user"></i>&nbsp;${entity.employee.name}&nbsp;</a> <a href="javascript:void(0);"><i class="fa fa-calendar"></i> ${entity.startTimeTimeStr}&nbsp; </a> <a href="javascript:void(0);"><i class="fa fa-eye"></i>&nbsp;查看次数：${entity.readTimes}&nbsp;</a> <a href="javascript:void(0);" style="color: #3276b1;"
				onclick="loadContent('','${nvix}/nvixnt/dailyJournalAction!goWorkLogAttachment.action?id=${entity.id }');"><i class="fa fa-file"></i>&nbsp;附件( ${entity.uploaderNum} )</a> <a href="javascript:void(0);" style="color: #3276b1;" onclick="saveOrUpdate('${entity.id }', '修改');"><i class="fa fa-edit"></i>&nbsp;修改</a> <a href="javascript:void(0);"
				style="color: #E63F00;" onclick="deleteById('${entity.id }');"><i class="fa fa-trash-o"></i>&nbsp;删除</a>
		</p>
		<p class="description margin-bottom-10">日志内容：${entity.logContent}</p>
	</div>
</s:iterator>