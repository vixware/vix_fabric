<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
												
<table class="table table-striped table-forum">
	<thead>
			<th colspan="5" style="text-align: center;">${titleDate }客户消费排行 TOP10</th>
	</thead>
	<tbody>
		<tr>
			<td class="text-center" style="width: 60px;">排名</td>
			<td class="text-center">姓名</td>
			<td class="text-center hidden-xs hidden-sm">会员卡号</td>
			<td class="text-center hidden-xs hidden-sm">金额(元)</td>
		</tr>
			<s:if test="null==tBeanEArr || tBeanEArr.isEmpty()">  
				<tr>
				  <td colspan="5" style="text-align: center;color:red;"> 暂无数据 </td>
				</tr>
			</s:if>
			<s:else>
				<s:iterator value="tBeanEArr" var="tBean" status="vs">	  
					 <tr>
						<td class="text-center" style="width: 60px;">${vs.index+1 }</td>
						<td class="text-center">${tBean.colA} </td>
						<td class="text-center hidden-xs hidden-sm"> ${tBean.colB} </td>
						<td class="text-center hidden-xs hidden-sm">  ${tBean.colC} </td>
					</tr>		
				</s:iterator>
			</s:else>
	</tbody>
</table>									
