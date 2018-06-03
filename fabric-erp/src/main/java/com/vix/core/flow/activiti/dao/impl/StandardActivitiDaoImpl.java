package com.vix.core.flow.activiti.dao.impl;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.flow.activiti.dao.IStandardActivitiDao;

@Transactional(readOnly=false)
@Service
public class StandardActivitiDaoImpl implements IStandardActivitiDao{

	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private ManagementService managementService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private ProcessEngine processEngine;
	 
}
