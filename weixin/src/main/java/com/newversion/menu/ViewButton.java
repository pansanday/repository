package com.newversion.menu;

/**
 * View类型的按钮
 */
public class ViewButton extends Button {

	private String type;
	// 网页链接，用户点击菜单可打开链接，不超过1024字节
	private String url;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
