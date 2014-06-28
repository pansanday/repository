package com.git.util;

import java.util.concurrent.ThreadFactory;

/**
 * 这与普通的ThreadFactory的唯一差异就是它将后台状态全部设置为了true
 */
public class DaemonThreadFactory implements ThreadFactory {
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setDaemon(true);
		return t;
	}
}
