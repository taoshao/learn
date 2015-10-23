package Util;

public class DeviceUtil {
	private static String status;
	private static boolean getDataCloudFlag;
	public static boolean isGetDataCloudFlag() {
		return getDataCloudFlag;
	}

	public static void setGetDataCloudFlag(boolean getDataCloudFlag) {
		DeviceUtil.getDataCloudFlag = getDataCloudFlag;
	}

	public static String getStatus() {
		return status;
	}

	public static void setStatus(String status) {
		DeviceUtil.status = status;
	}
	
}
