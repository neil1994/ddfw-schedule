package com.dxhy.dispatch.utils;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;
import org.slf4j.LoggerFactory;

import java.net.URL;

import static com.dxhy.dispatch.utils.StringUtils.getBefore;

/**
 * 
 * <p>logback启动触发监听，定义项目名称和端口号</p>
 *
 * @author 赵睿
 * @version 1.0 Created on 2016年9月28日 下午2:40:33
 */
public class LoggerStartupListener extends ContextAwareBase implements LoggerContextListener, LifeCycle {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(LoggerStartupListener.class);

	private boolean started = false;

	@Override
	public void start() {
		if (started)
			return;
		String path = getContextpath();

		if (logger == null) {
			System.out.println("获得的项目名称为:" + path);
		} else {
			System.out.println("获得的项目名称为:" + path);
		}
		Context context = getContext();
		String contextName = "contextName";
		context.putProperty(contextName, path);
		started = true;
	}

	

	/**
	 * 
	 * <p>
	 * 获得当前项目的在运行中的项目名称
	 * </p>
	 *
	 * @return String
	 * @author 赵睿 2016年8月2日上午9:12:30
	 */
	public String getContextpath() {
		URL url = this.getClass().getResource("/");
		String path = url.getPath();

		String target = "target";
		String web = "WEB-INF";
		String bin = "bin";
		String split = "/";
		if (!org.apache.commons.lang.StringUtils.isNotBlank(path)) {
			String defaultPath = "dxhy";
			path = defaultPath;
		} else if (path.contains(web)) {
			path = getBefore(path, web, split);
			path=path+"-"+WebUtils.getPort();
		} else if (path.contains(target)) {
			path = getBefore(path, target, split);
		} else if (path.contains(bin)) {
			path = getBefore(path, bin, split);
		}
		return path;
	}

	@Override
	public void stop() {
	}

	@Override
	public boolean isStarted() {
		return started;
	}

	@Override
	public boolean isResetResistant() {
		return true;
	}

	@Override
	public void onStart(LoggerContext context) {
	}

	@Override
	public void onReset(LoggerContext context) {
	}

	@Override
	public void onStop(LoggerContext context) {
	}

	@Override
	public void onLevelChange(Logger logger, Level level) {
	}

}
