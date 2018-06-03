package com.vix.oa.share.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
* @ClassName: Commentss
* @Description: 评论新闻的评论
* @author bobchen
* @date 2016年3月23日 下午2:33:31
*
 */
public class Commentss extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 评论内容 */
	private String commentscontent;
	/** 评论条数 */
	public Long commentsnumber;
	/** 点赞 */
	public Long likes;
	/** 评论时间 */
	public Date createtime;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;
	/** 评论 */
	private Comments comments;

	public Commentss() {
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


	public Long getLikes() {
		return likes;
	}

	public void setLikes(Long likes) {
		this.likes = likes;
	}

	public Comments getComments() {
		return comments;
	}

	public void setComments(Comments comments) {
		this.comments = comments;
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

}
