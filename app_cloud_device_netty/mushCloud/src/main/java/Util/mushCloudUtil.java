package Util;

import javax.servlet.http.HttpServletResponse;

public  class mushCloudUtil {
	private static String status;
	private static HttpServletResponse resp;
	private static boolean orderSentFlag;
	public static boolean isOrderSentFlag() {
		return orderSentFlag;
	}

	public static void setOrderSentFlag(boolean orderSentFlag) {
		mushCloudUtil.orderSentFlag = orderSentFlag;
	}

	public static HttpServletResponse getResp() {
		return resp;
	}

	public static void setResp(HttpServletResponse resp) {
		mushCloudUtil.resp = resp;
	}

	public static String getStatus() {
		return status;
	}

	public static void setStatus(String status) {
		mushCloudUtil.status = status;
	}
}
