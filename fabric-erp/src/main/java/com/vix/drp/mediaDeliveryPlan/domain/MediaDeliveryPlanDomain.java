package com.vix.drp.mediaDeliveryPlan.domain;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.mediaDeliveryPlan.entity.MediaPlan;
import com.vix.drp.mediaDeliveryPlan.entity.NewsPaperMedia;
import com.vix.drp.mediaDeliveryPlan.entity.RadioMedia;
import com.vix.drp.mediaDeliveryPlan.entity.TelevisionMedia;

@Component("mediaDeliveryPlanDomain")
@Transactional
public class MediaDeliveryPlanDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, MediaPlan.class, params);
		return p;
	}

	public MediaPlan findMediaPlanById(String id) throws Exception {
		return baseHibernateService.findEntityById(MediaPlan.class, id);
	}

	public TelevisionMedia findTelevisionMediaById(String id) throws Exception {
		return baseHibernateService.findEntityById(TelevisionMedia.class, id);
	}

	public NewsPaperMedia findNewsPaperMediaById(String id) throws Exception {
		return baseHibernateService.findEntityById(NewsPaperMedia.class, id);
	}

	public RadioMedia findRadioMediaById(String id) throws Exception {
		return baseHibernateService.findEntityById(RadioMedia.class, id);
	}

	public MediaPlan saveOrUpdateMediaPlan(MediaPlan mediaPlan) throws Exception {
		return baseHibernateService.merge(mediaPlan);
	}

	public TelevisionMedia saveOrUpdateTelevisionMedia(TelevisionMedia televisionMedia) throws Exception {
		return baseHibernateService.merge(televisionMedia);
	}

	public NewsPaperMedia saveOrUpdateNewsPaperMedia(NewsPaperMedia newsPaperMedia) throws Exception {
		return baseHibernateService.merge(newsPaperMedia);
	}
	public RadioMedia saveOrUpdateRadioMedia(RadioMedia radioMedia) throws Exception {
		return baseHibernateService.merge(radioMedia);
	}

}
