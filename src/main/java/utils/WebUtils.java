//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class WebUtils {

    private WebUtils() {
    }

    public static void setFileDownloadHeader(HttpServletRequest request, HttpServletResponse response, String fileName) {
        try {
            response.setContentType("application/x-download; charset=UTF-8");
            String e = encodingFileName(fileName, request, response);
            response.setHeader("Content-Disposition", "attachment; " + e);
        } catch (UnsupportedEncodingException var4) {
            throw new RuntimeException("设置响应头异常", var4);
        }
    }

    private static String encodingFileName(String filename, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String userAgent = request.getHeader("User-Agent").toLowerCase();
        return StringUtils.isEmpty(userAgent) ? "filename=" + URLEncoder.encode(filename, "UTF-8") : (StringUtils.containsIgnoreCase(userAgent, "msie") ? "filename=" + URLEncoder.encode(filename, "UTF-8") : (StringUtils.contains(userAgent, "trident") ? "filename=" + URLEncoder.encode(filename, "UTF-8") : (StringUtils.containsIgnoreCase(userAgent, "opera") ? "filename*=UTF-8\'\'" + URLEncoder.encode(filename, "UTF-8") : "filename=\"" + new String(filename.getBytes("UTF-8"), "ISO8859-1") + "\"")));
    }

    public static Map<String, Object> getParametersStartingWith(HttpServletRequest request, String prefix) {
        HashMap map = new HashMap();
        Enumeration names = request.getParameterNames();

        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            String[] param = name.split("\\.");
            if (param.length >= 2 && StringUtils.equals(param[0], prefix)) {
                String[] key = new String[param.length - 1];
                System.arraycopy(param, 1, key, 0, key.length);
                map.put(StringUtils.join(key, "."), request.getParameter(name));
            }
        }

        return map;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static Map<String, Object> getParametersStartingAndEncodeUTF8With(HttpServletRequest request, String prefix) {
        return getParametersStartingWith(request, prefix);
    }

    public static Map<String, Object> getQueryParameter(HttpServletRequest request) {
        return getParametersStartingWith(request, "queryParameter");
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static Map<String, Object> getQueryParameterEncodeUTF8(HttpServletRequest request) {
        return getParametersStartingAndEncodeUTF8With(request, "queryParameter");
    }


    private static List<String> enumToList(Enumeration e) {
        ArrayList list = new ArrayList();
        Enumeration names = e;

        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            list.add(name);
        }

        return list;
    }

    private static int getIndex(String arrayString, String prefix) {
        String regex = prefix + "(?is)\\[(.*?)\\]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(arrayString);
        if (matcher.find()) {
            String s = matcher.group();
            s = s.substring(s.indexOf(91) + 1, s.length() - 1);
            int index = Integer.parseInt(s);
            return index;
        } else {
            return -1;
        }
    }

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }


}
