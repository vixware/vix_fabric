<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	WdatePicker({
	eCont : 'date1',
	dateFmt : 'yyyy-MM-dd HH:mm:ss',
	skin : 'blue',
	onpicked : function(dp) {
		chosedatabydate(dp.cal.getDateStr());
	}
	});
</script>
<div class="addleft">
	<div class="addbtn">
		<%-- <p> <img src="${vix}/common/img/address_book.png" /> 工作日志</p> --%>
		<!-- <a href="#">最新</a><a href="#">新建</a><a href="#">查询</a> -->
	</div>
	<div id="date1" class="date_box"></div>
	<div class="addbox">
		<div class="addtitle">最新点评人</div>
		<ul class="ul_list">
			<s:iterator value="pager.resultList" var="entity" status="s">
				<li><a href="javascript:;" onclick="popNews('${entity.id}');">${entity.uploadPersonName}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- <div class="addbox">
	<div class="addtitle">共享日志</div>
	<ul class="ul_list">
		<li><a href="#">系统管理员</a></li>
		<li><a href="#">系统管理员</a></li>
	</ul>
</div> -->
</div>
<div class="addright">
	<div class="addbox">
		<!-- 如果没有反馈信息显示如需下 -->
		<s:if test="pager.resultList.size()==0">
			<p>
				<span style="color: red;">该日期没有工作日志！</span>
			</p>
		</s:if>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<%-- <div class="addtitle"><div class="add_sm"><a href="#"  onclick="saveOrUpdate1('${entity.id}',0);">评论</a> 
			|<a href="javascript:;" onclick="popNews('${entity.id}');">查看全文 </a>  
			| <a href="javascript:;" onclick="saveOrUpdate('${entity.id}');">编辑</a> </div>${entity.uploadPersonName}		
			<fmt:formatDate value="${entity.logDate}" type="both" pattern="yyyy-MM-dd" /> 
			<a href="#" style="color: gray;"> 
			<s:if test="%{#entity.logType == 0}">工作日志</s:if> 
			<s:elseif test="%{#entity.logType == 1}">个人日志</s:elseif>
			</a>
		</div> --%>
			<p class="gray">
				${entity.uploadPersonName} <a href="#" style="color: gray;"> <s:if test="%{#entity.logType == 0}">工作日志</s:if> <s:elseif test="%{#entity.logType == 1}">个人日志</s:elseif>
				</a> <a href="#" onclick="saveOrUpdate1('${entity.id}',0);">评论</a> |<a href="javascript:;" onclick="popNews('${entity.id}');">查看全文 </a> |<a href="javascript:;" onclick="saveOrUpdate('${entity.id}');">编辑</a> | 时间：
				<fmt:formatDate value="${entity.logDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss" />
				<SimpleDateFormat dateFm=new SimpleDateFormat("EEEE"); String logDate=dateFm.format(new Date()); />
				| 最后修改时间：
				<fmt:formatDate value="${entity.updateTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" />
				<SimpleDateFormat dateFm=new SimpleDateFormat("EEEE"); String logDate=dateFm.format(new Date()); />
				| <a href="#">查看次数(${entity.commentsnumber})</a>
			</p>
			<p align="center" style="color: #000099; font-size: 16px;">${entity.title}</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;${entity.logContent}</p>
		</s:iterator>
	</div>
</div>