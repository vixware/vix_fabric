package com.vix.common.bulletin.entity;

import java.util.Date;
import java.util.Set;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.security.entity.Role;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;


/**
 * @ClassName: Bulletin
 * @Description: 公告通知
 * @author wangmingchen
 * @date 2012-7-19 下午2:30:59
 *
 */
public class Bulletin extends BaseEntity {

    /**
	 *
	 */
	private static final long serialVersionUID = 1294480704127561120L;
	/** 公告分类  字典key */
    public String catalogy;
    /** 标题 */
    public String title;
    /** 内容 */
    private String content;
    
    /** 有效期开始时间 */
    private Date activeStartDate;
    /** 有效期结束时间 */
    private Date activeEndDate;
    
    /** 插入事务提醒   0 否  1 是*/
    private Boolean sendMsg ;
    /** 置顶 */
    /** 是否置顶 0 否  1 是*/
    private Boolean onTop ;
    
    /** 天后结束置顶，0表示一直置顶 */
    private Integer onTopDay;
    
    
    
    /**
     * 发布人员类型
     * "O\":\"部门\",\"R\":\"角色\",\"E\":\"人员
     */
    private String pubType;
    /**
     * 按部门发布
     */
    private Set<OrganizationUnit> organizationUnits;
    /**
     * 按角色发布
     */
    private Set<Role> roles;
    /**
     * 按人员发布
     */
    private Set<Employee> employees;
    
    /** 发布对象的id集合 */
    private String pubIds;
    /** 发布对象的名称集合 */
    private String pubNames;
    
    
    
    
    
    
    /** 开始时间和结束时间参见父类 */
    
    /** 数据状态
     *  0 创建
     *  1 审核中
     *  2 审核通过
     *  3 审核没通过
     *  
     **/
    public Integer bulletinStatus;
    
    /** 阅读次数 */
    public Long readTimes;
    
    /** 失效日期 */
    public Date invalidDate;
    

    /** 创建人 */
    public UserAccount createUser;
    
    /** 审核人 */
    public UserAccount auditUser;
    
    /** 是否审核  1 是 0 否 */
    public Integer isAudit;
    /** 审核时间 */
    public Date auditDate;
    /** 是否发布 */
    public Integer isPublish;
    /** 是否发送消息 */
    public Integer isSendMsg;
    
    
    public Bulletin() {
        super();
    }
    
    public Bulletin(String id) {
        super();
        setId(id);
    }
    
    public String getCatalogy() {
        return catalogy;
    }
    public void setCatalogy(String catalogy) {
        this.catalogy = catalogy;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Long getReadTimes() {
        return readTimes;
    }
    public void setReadTimes(Long readTimes) {
        this.readTimes = readTimes;
    }
    public Date getInvalidDate() {
        return invalidDate;
    }
    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }
    
    public Integer getIsAudit() {
        return isAudit;
    }
    public void setIsAudit(Integer isAudit) {
        this.isAudit = isAudit;
    }
    public Date getAuditDate() {
        return auditDate;
    }
    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }
    public Integer getIsPublish() {
        return isPublish;
    }
    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }

    public Integer getIsSendMsg() {
        return isSendMsg;
    }

    public void setIsSendMsg(Integer isSendMsg) {
        this.isSendMsg = isSendMsg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	public UserAccount getCreateUser() {
		return createUser;
	}

	public void setCreateUser(UserAccount createUser) {
		this.createUser = createUser;
	}

	public UserAccount getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(UserAccount auditUser) {
		this.auditUser = auditUser;
	}

	public Integer getBulletinStatus() {
		return bulletinStatus;
	}

	public void setBulletinStatus(Integer bulletinStatus) {
		this.bulletinStatus = bulletinStatus;
	}

	public Date getActiveStartDate() {
		return activeStartDate;
	}

	public void setActiveStartDate(Date activeStartDate) {
		this.activeStartDate = activeStartDate;
	}

	public Date getActiveEndDate() {
		return activeEndDate;
	}

	public void setActiveEndDate(Date activeEndDate) {
		this.activeEndDate = activeEndDate;
	}

	public Boolean getSendMsg() {
		return sendMsg;
	}

	public void setSendMsg(Boolean sendMsg) {
		this.sendMsg = sendMsg;
	}

	public Boolean getOnTop() {
		return onTop;
	}

	public void setOnTop(Boolean onTop) {
		this.onTop = onTop;
	}

	public Integer getOnTopDay() {
		return onTopDay;
	}

	public void setOnTopDay(Integer onTopDay) {
		this.onTopDay = onTopDay;
	}

	public String getPubType() {
		return pubType;
	}

	public void setPubType(String pubType) {
		this.pubType = pubType;
	}

	public Set<OrganizationUnit> getOrganizationUnits() {
		return organizationUnits;
	}

	public void setOrganizationUnits(Set<OrganizationUnit> organizationUnits) {
		this.organizationUnits = organizationUnits;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public String getPubIds() {
		return pubIds;
	}

	public void setPubIds(String pubIds) {
		this.pubIds = pubIds;
	}

	public String getPubNames() {
		return pubNames;
	}

	public void setPubNames(String pubNames) {
		this.pubNames = pubNames;
	}
    
}
