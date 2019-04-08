package com.vrain.dto;

import java.io.Serializable;
import java.util.List;

public class PageResultDto<E> implements Serializable{
	private static final long serialVersionUID = 1L;
	// 结果状态
	private int state;
	// 状态标识
	private String stateInfo;
	// 数量
	private long count;
	// 操作的entity
	private E info;
	// 获取的entity列表
	private List<E> infoList;
	
	public PageResultDto() {
	}

	// 失败的构造器
	public PageResultDto(PageStatusEnumDto stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 成功的构造器
	public PageResultDto(PageStatusEnumDto stateEnum, E info) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.info = info;
	}

	// 成功的构造器
	public PageResultDto(PageStatusEnumDto stateEnum, List<E> infoList, long count) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.infoList = infoList;
		this.count=count;
	}

	// 成功的构造器
	public PageResultDto(PageStatusEnumDto stateEnum, List<E> infoList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.infoList = infoList;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public E getInfo() {
		return info;
	}

	public void setInfo(E info) {
		this.info = info;
	}

	public List<E> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<E> infoList) {
		this.infoList = infoList;
	}

}
