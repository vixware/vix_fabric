package com.vix.oa.share.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @ClassName: Comments
 * @Description: 新闻评论 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-4-1 下午3:36:00
 */
public class Comments extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 评论内容 */
	private String commentscontent;
	/** 评论条数 */
	public Long commentsnumber;
	/** 评论时间 */
	public Date createtime;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;
	/** 新闻 */
	private Trends trends;
	/** 评论新闻的评论*/
	private Set<Commentss> commentss = new HashSet<Commentss>();

	public Comments() {
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public String getCommentscontent() {
		return commentscontent;
	}

	public void setCommentscontent(String commentscontent) {
		this.commentscontent = commentscontent;
	}

	public Long getCommentsnumber() {
		return commentsnumber;
	}

	public void setCommentsnumber(Long commentsnumber) {
		this.commentsnumber = commentsnumber;
	}

	public Trends getTrends() {
		return trends;
	}

	public void setTrends(Trends trends) {
		this.trends = trends;
	}

	public String getUploadPersonId() {
		return uploadPersonId;
	}

	public void setUploadPersonId(String uploadPersonId) {
		this.uploadPersonId = uploadPersonId;
	}

	public String getUploadPerson() {
		return uploadPerson;
	}

	public void setUploadPerson(String uploadPerson) {
		this.uploadPerson = uploadPerson;
	}

	public String getUploadPersonName() {
		return uploadPersonName;
	}

	public void setUploadPersonName(String uploadPersonName) {
		this.uploadPersonName = uploadPersonName;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Set<Commentss> getCommentss() {
		return commentss;
	}

	public void setCommentss(Set<Commentss> commentss) {
		this.commentss = commentss;
	}

}
