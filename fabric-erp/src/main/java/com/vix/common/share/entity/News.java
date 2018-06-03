package com.vix.common.share.entity;

import java.util.Date;

import com.vix.common.security.entity.UserAccount;

/**
 * @ClassName: News
 * @Description: 新闻
 * @author wangmingchen
 * @date 2012-7-19 下午4:31:01
 *
 */
public class News extends BaseEntity {

    /** 部门 */
   // private Organization org;
    /** 新闻分类 */
    private NewsCatalogy newsCatalogy;
    
    /** 创建用户和创建时间参见父类 */
    
    /** 标题 */
    public String title;
    /** 附标题 */
    public String subtitle;
    /** 关键词 */
    public String keywords;
    /** 内容 */
    private String content;
    
    /** 新闻来源 */
    private String sourceFrom;
    /** 新闻来源url */
    private String sourceFromUrl;
    
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
    
    /** 阅读次数 */
    public Long readTimes;
    
    /** 新闻类型
     * 0 文本新闻
     * 1 图片新闻
     *  */
    public Integer newsType;
    /** 图片新闻的路径 */
    public String imgPath;
    
    public News() {
        super();
    }

    public NewsCatalogy getNewsCatalogy() {
        return newsCatalogy;
    }

    public void setNewsCatalogy(NewsCatalogy newsCatalogy) {
        this.newsCatalogy = newsCatalogy;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom;
    }

    public String getSourceFromUrl() {
        return sourceFromUrl;
    }

    public void setSourceFromUrl(String sourceFromUrl) {
        this.sourceFromUrl = sourceFromUrl;
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

    public Long getReadTimes() {
        return readTimes;
    }

    public void setReadTimes(Long readTimes) {
        this.readTimes = readTimes;
    }

    public Integer getNewsType() {
        return newsType;
    }

    public void setNewsType(Integer newsType) {
        this.newsType = newsType;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
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
}
