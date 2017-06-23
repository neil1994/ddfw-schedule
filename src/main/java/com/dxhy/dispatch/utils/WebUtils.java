package com.dxhy.dispatch.utils;


import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.Query;
import java.lang.management.ManagementFactory;
import java.util.Set;

/**
 * 
 * <p>对web服务的工具类</p>
 *
 * @author 赵睿
 * @version 1.0 Created on 2016年9月28日 下午2:41:33
 */
public class WebUtils {
	/**
	 * 
	 * <p>获得当前运行服务器的端口号</p>
	 * 
	 * @return String
	 * @author 赵睿  2016年8月2日上午10:33:10
	 */
	public static String getPort() {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		Set<ObjectName> objs;
		String port = null;
		try {
			objs = mbs.queryNames(new ObjectName("*:type=Connector,*"),
					Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));

			ObjectName[] names = objs.toArray(new ObjectName[objs.size()]);
			for (ObjectName name : names) {
				port = name.getKeyProperty("port");
				System.out.println("获得的端口号为：" + port);
			}

		} catch (Exception e) {
			System.out.println("获取当前服务端口出错！");
			e.printStackTrace();
		}
		return port;
	}

}
