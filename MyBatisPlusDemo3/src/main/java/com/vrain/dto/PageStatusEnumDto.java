package com.vrain.dto;

public enum PageStatusEnumDto {
	OFFLINE(-1, "非法"),CHECK(0, "审核中"),PASS(1, "通过认证"),  
	SUCCESS(200, "操作成功"), INNER_ERROR(-1001, "操作失败"), 
	EMPTY(-1002, "区域信息为空"),NO_POWER_TO(-1003,"没有权限访问"),
	INNER_EMPTY(-1004, "查询空数据");
	
	private int state;
	private String stateInfo;
	
	private PageStatusEnumDto(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static PageStatusEnumDto stateOf(int index) {
		for (PageStatusEnumDto state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
	
}
