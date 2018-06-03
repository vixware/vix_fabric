package com.vix.wechat.base.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CardEntity;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.pm.common.entity.Project;
import com.vix.oa.adminMg.conference.entity.ApplicationMg;
import com.vix.oa.bulletin.entity.AnnouncementNotification;
import com.vix.oa.personaloffice.entity.TripRecord;
import com.vix.oa.personaloffice.entity.WorkLog;
import com.vix.oa.personaloffice.wab.entity.Wab;
import com.vix.oa.share.entity.Trends;
import com.vix.oa.task.taskDefinition.entity.VixTask;

/**
 * 图片
 * 
 * @ClassFullName com.vix.wechat.base.entity.WxpQyPicture
 *
 * @author bjitzhang
 *
 * @date 2016年3月27日
 *
 */
public class WxpQyPicture extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String pictureUrl;

	private VixTask vixTask;
	private WorkLog workLog;
	private Wab wab;
	private AnnouncementNotification announcementNotification;
	private ApplicationMg applicationMg;
	private CardEntity cardEntity;
	/**
	 * 请假记录
	 */
	private TripRecord tripRecord;
	/**
	 * 新闻
	 */
	private Trends trends;
	/**
	 * 活动
	 */
	private WxpActivity wxpActivity;
	/**
	 * 项目
	 */
	private Project project;
	/**
	 * 人员
	 */
	private Employee employee;
	/**
	 * 商品
	 */
	private Item item;

	/**
	 * @return the pictureUrl
	 */
	public String getPictureUrl() {
		return pictureUrl;
	}

	/**
	 * @param pictureUrl
	 *            the pictureUrl to set
	 */
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	/**
	 * @return the wxpActivity
	 */
	public WxpActivity getWxpActivity() {
		return wxpActivity;
	}

	/**
	 * @param wxpActivity
	 *            the wxpActivity to set
	 */
	public void setWxpActivity(WxpActivity wxpActivity) {
		this.wxpActivity = wxpActivity;
	}

	/**
	 * @return the vixTask
	 */
	public VixTask getVixTask() {
		return vixTask;
	}

	/**
	 * @param vixTask
	 *            the vixTask to set
	 */
	public void setVixTask(VixTask vixTask) {
		this.vixTask = vixTask;
	}

	/**
	 * @return the applicationMg
	 */
	public ApplicationMg getApplicationMg() {
		return applicationMg;
	}

	/**
	 * @param applicationMg
	 *            the applicationMg to set
	 */
	public void setApplicationMg(ApplicationMg applicationMg) {
		this.applicationMg = applicationMg;
	}

	/**
	 * @return the workLog
	 */
	public WorkLog getWorkLog() {
		return workLog;
	}

	/**
	 * @param workLog
	 *            the workLog to set
	 */
	public void setWorkLog(WorkLog workLog) {
		this.workLog = workLog;
	}

	/**
	 * @return the trends
	 */
	public Trends getTrends() {
		return trends;
	}

	/**
	 * @param trends
	 *            the trends to set
	 */
	public void setTrends(Trends trends) {
		this.trends = trends;
	}

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee
	 *            the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * @param project
	 *            the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * @return the tripRecord
	 */
	public TripRecord getTripRecord() {
		return tripRecord;
	}

	/**
	 * @param tripRecord
	 *            the tripRecord to set
	 */
	public void setTripRecord(TripRecord tripRecord) {
		this.tripRecord = tripRecord;
	}

	public Wab getWab() {
		return wab;
	}

	public void setWab(Wab wab) {
		this.wab = wab;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * @return the announcementNotification
	 */
	public AnnouncementNotification getAnnouncementNotification() {
		return announcementNotification;
	}

	/**
	 * @param announcementNotification
	 *            the announcementNotification to set
	 */
	public void setAnnouncementNotification(AnnouncementNotification announcementNotification) {
		this.announcementNotification = announcementNotification;
	}

	public CardEntity getCardEntity() {
		return cardEntity;
	}

	public void setCardEntity(CardEntity cardEntity) {
		this.cardEntity = cardEntity;
	}

}
