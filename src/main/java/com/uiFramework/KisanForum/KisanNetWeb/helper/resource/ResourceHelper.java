package com.uiFramework.KisanForum.KisanNetWeb.helper.resource;


public class ResourceHelper {

	public static String getResourcePath(String path) {
        String basePath = System.getProperty("user.dir");
        System.out.println("BAse path is " +basePath);
        System.out.println(basePath +"\\"+ path);
		return basePath +"\\"+ path;
	}
}
