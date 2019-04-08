package com.vrain.dto;

import java.io.Serializable;
import java.util.List;

public class EasyUITreeNode implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5329402199632612106L;
	
	private Long id;//节点的 id，它对于加载远程数据很重要
	private String text;//要显示的节点文本
	private String state;//节点状态，'open' 或 'closed'，默认是 'open'。当设置为 'closed' 时，该节点有子节点，并且将从远程站点加载它们。
	private List children;//定义了一些子节点的节点数组
	private List attributes;//给一个节点添加的自定义属性。
	private boolean checked;//指示节点是否被选中。
	
	public List getAttributes() {
		return attributes;
	}

	public void setAttributes(List attributes) {
		this.attributes = attributes;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public List getChildren() {
		return children;
	}

	public void setChildren(List children) {
		this.children = children;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "EasyUITreeNode [id=" + id + ", text=" + text + ", state=" + state + ", children=" + children
				+ ", attributes=" + attributes + ", checked=" + checked + "]";
	}

}
