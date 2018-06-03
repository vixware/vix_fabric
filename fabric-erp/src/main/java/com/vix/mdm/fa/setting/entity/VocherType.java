package com.vix.mdm.fa.setting.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.fa.certificates.entity.Vocher;

/**
 * @Description: 凭证类型
 * @author ivan
 * @date 2014-01-10
 */
public class VocherType extends BaseEntity {

	private static final long serialVersionUID = -3752644718324921589L;
	/** 限制类型 */
	private String restrictionType;
	/** 父分类 */
	private VocherType vocherType;
	/** 子分类 */
	private Set<VocherType> vocherTypes = new HashSet<VocherType>();
	/** 凭证 */
	private Set<Vocher> vochers = new HashSet<Vocher>();

	public String getRestrictionType() {
		return restrictionType;
	}

	public void setRestrictionType(String restrictionType) {
		this.restrictionType = restrictionType;
	}

	public VocherType getVocherType() {
		return vocherType;
	}

	public void setVocherType(VocherType vocherType) {
		this.vocherType = vocherType;
	}

	public Set<VocherType> getVocherTypes() {
		return vocherTypes;
	}

	public void setVocherTypes(Set<VocherType> vocherTypes) {
		this.vocherTypes = vocherTypes;
	}

	public Set<Vocher> getVochers() {
		return vochers;
	}

	public void setVochers(Set<Vocher> vochers) {
		this.vochers = vochers;
	}

}
