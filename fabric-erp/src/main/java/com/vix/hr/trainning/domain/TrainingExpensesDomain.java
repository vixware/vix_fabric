package com.vix.hr.trainning.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.trainning.entity.TrainingExpenses;
import com.vix.hr.trainning.service.ITrainingExpensesService;

@Transactional
@Component("trainingexpensesdomain")
public class TrainingExpensesDomain {
	@Autowired
	private ITrainingExpensesService iTrainingExpensesService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iTrainingExpensesService.findPagerByHqlConditions(pager, TrainingExpenses.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iTrainingExpensesService.findPagerByOrHqlConditions(pager, TrainingExpenses.class, params);
		return p;
	}

	public TrainingExpenses findEntityById(String id) throws Exception {
		return iTrainingExpensesService.findEntityById(TrainingExpenses.class, id);
	}

	public TrainingExpenses merge(TrainingExpenses trainingExpenses) throws Exception {
		return iTrainingExpensesService.merge(trainingExpenses);
	}

	public void deleteByEntity(TrainingExpenses trainingExpenses) throws Exception {
		iTrainingExpensesService.deleteByEntity(trainingExpenses);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		iTrainingExpensesService.batchDelete(TrainingExpenses.class, ids);
	}

	/** 索引对象列表 */
	public List<TrainingExpenses> findTrainingExpensesIndex() throws Exception {
		return iTrainingExpensesService.findAllByConditions(TrainingExpenses.class, null);
	}

}
