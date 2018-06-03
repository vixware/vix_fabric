<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<s:iterator value="projectContactPerson" var="projectContactPerson">
	<td style="text-align: center;" align="center">
		<p align="center">
			<img border="0" src="${vix}/common/img/n2.png"><br /> <a href="#"><s:property value="#projectContactPerson.personName" /></a><br /> <b><s:property value="#projectContactPerson.mobile" /></b> <img border="0" align="Baseline" src="${vix}/common/img/sphone.png"><br />
		</p>
	</td>
</s:iterator>
