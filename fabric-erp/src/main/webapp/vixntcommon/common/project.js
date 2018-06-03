/**
 * 保存项目
 */
function saveOrUpdateProject() {
	$.post('${nvix}/nvixnt/nvixProjectAction!saveOrUpdate.action', {
	'project.id' : $("#projectId").val(),
	'project.projectCode' : $("#projectCode").val(),
	'project.projectName' : $("#projectName").val()
	}, function(result) {
		// showMessage(result);
		// setTimeout("", 1000);
		loadContent('${nvix}/nvixnt/nvixProjectAction!goListProjectDetails.action');
	});
}
/**
 * 保存任务
 */
function saveOrUpdate() {
	$.post('${nvix}/nvixnt/nvixProjectAction!saveOrUpdate.action', {
	'taskDefinition.id' : $("#taskDefinitionId").val(),
	'taskDefinition.code' : $("#taskDefinitionCode").val(),
	'taskDefinition.name' : $("#taskDefinitionName").val(),
	'taskDefinition.taskWeights' : $("#taskWeights").val()
	}, function(result) {
		// showMessage(result);
		// setTimeout("", 1000);
		loadContent('${nvix}/nvixnt/nvixProjectAction!goList.action');
	});
};