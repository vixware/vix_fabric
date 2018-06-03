package com.vix.common.security.tree;

import java.io.Serializable;
import java.util.TreeSet;

public class TreeCodeNode implements Serializable, Comparable<TreeCodeNode> {

	private static final long serialVersionUID = 1275713386338960803L;

	private String code;

    private String parentCode;

    private TreeNode parent;

    private TreeSet<TreeNode> children;

    private String name;

    private String displayName;
    
    /** 权限编码 */
    private String authorityCode;

    private Integer sortSeq;

    private String menuUrl;

    //private boolean isAsc;

    public TreeCodeNode() {
        super();
    }

/*
    public TreeNode(Long id, Long parentId,  String name, String displayName, Long sortSeq,
            String menuUrl) {
        super();
        this.id = id;
        this.parentId = parentId;
       // this.parent = parent;
        this.name = name;
        this.displayName = displayName;
        this.sortSeq = sortSeq;
        this.menuUrl = menuUrl;
    }
*/

    public TreeCodeNode(String code, String parentCode,  String name, String displayName,String authorityCode, Integer sortSeq,
            String menuUrl) {
        super();
        this.code = code;
        this.parentCode = parentCode;
       // this.parent = parent;
        this.name = name;
        this.displayName = displayName;
        this.sortSeq = sortSeq;
        this.menuUrl = menuUrl;
    }

    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeSet<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(TreeSet<TreeNode> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getSortSeq() {
        return sortSeq;
    }

    public void setSortSeq(Integer sortSeq) {
        this.sortSeq = sortSeq;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public String getAuthorityCode() {
		return authorityCode;
	}


	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
	}


	public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public void initChildList() {
        if (children == null)
            children = new TreeSet<TreeNode>();
    }
    
    public void addChildren(TreeNode child){
        initChildList();
        this.children.add(child);
    }

    public boolean isLeaf() {
        if (children == null) {
            return true;
        } else {
            if (children.isEmpty()) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public int compareTo(TreeCodeNode arg0) {
    	TreeCodeNode obj = arg0;
        Integer myObj = this.sortSeq;
        Integer otherObj = obj.sortSeq;
        if(myObj==null || otherObj==null){
            return -1;
        }
        
        if(myObj<otherObj){
            return -1;
        }else if(myObj>otherObj){
            return 1;
        }
        
        //if id is same can alow add the same
        if(this.getCode()==obj.getCode()){
        	return 0;
        }
       
        //if return 0 then can't add
        return 1;
        //return 0;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parentCode == null) ? 0 : parentCode.hashCode());
		result = prime * result + ((sortSeq == null) ? 0 : sortSeq.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TreeCodeNode other = (TreeCodeNode) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parentCode == null) {
			if (other.parentCode != null)
				return false;
		} else if (!parentCode.equals(other.parentCode))
			return false;
		if (sortSeq == null) {
			if (other.sortSeq != null)
				return false;
		} else if (!sortSeq.equals(other.sortSeq))
			return false;
		return true;
	}

	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result	+ ((authorityCode == null) ? 0 : authorityCode.hashCode());
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TreeCodeNode other = (TreeCodeNode) obj;
		
		if (StringUtils.isEmpty(code)) {
			if (StringUtils.isNotEmpty(other.code))
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		if (authorityCode == null) {
			if (other.authorityCode != null)
				return false;
		} else if (!authorityCode.equals(other.authorityCode))
			return false;
		return true;
	}

	*/

    
    
}
