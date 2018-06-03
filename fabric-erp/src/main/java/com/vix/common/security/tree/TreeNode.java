package com.vix.common.security.tree;

import java.io.Serializable;
import java.util.TreeSet;

public class TreeNode implements Serializable, Comparable<TreeNode> {

	private static final long serialVersionUID = 1275713386338960803L;

	private String id;

    private String parentId;

    private TreeNode parent;

    private TreeSet<TreeNode> children;

    private String name;

    private String displayName;
    
    /** 权限编码 */
    private String authorityCode;

    private Integer sortSeq;

    private String menuUrl;
    
    private String iconClass;

    //private boolean isAsc;

    public TreeNode() {
        super();
    }

/*
    public TreeNode(String id, String parentId,  String name, String displayName, String sortSeq,
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

    public TreeNode(String id, String parentId,  String name, String displayName,String authorityCode, Integer sortSeq,
            String menuUrl,String iconClass) {
        super();
        this.id = id;
        this.parentId = parentId;
       // this.parent = parent;
        this.name = name;
        this.displayName = displayName;
        this.authorityCode = authorityCode;
        this.sortSeq = sortSeq;
        this.menuUrl = menuUrl;
        this.iconClass = iconClass;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getIconClass() {
		return iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
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
    public int compareTo(TreeNode arg0) {
        TreeNode obj = arg0;
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
        if(this.getId()==obj.getId()){
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		TreeNode other = (TreeNode) obj;
		
		if (id == null) {
			if (other.id != null)
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

	

    
    
}
