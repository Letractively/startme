/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.common.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.turbine.util.RunData;

/**
 * 【类说明】从HttpServletRequest中取得参数的类
 * @author yehongyu.org
 * @version 1.0 Dec 5, 2007 12:02:17 AM
 */
public class ParamUtils {

	/**
	 * 【函数功能】取得提交的参数字符串,不作任何转码。如果没有取到参数,返回""
	 * @param data turbine rundata对象
	 * @param name 参数名称
	 * @return 参数值
	 */
    public static String getParameter(RunData data, String name) {
        return filtSqlStr(data.getParameters().getString(name, ""));
    }
	    
    /**
     * 【函数功能】Gets a parameter as a string.
     * @param request The HttpServletRequest object, known as "request" in a JSP page.
     * @param name The name of the parameter you want to get
     * @return The value of the parameter or "" if the parameter was not found.
     */
    public static String getParameter(HttpServletRequest request, String name) {
        return getParameter(request, name, "");
    }

    /**
     * 【函数功能】Gets a parameter as a string.
     * @param request The HttpServletRequest object, known as "request" in a JSP page.
     * @param name The name of the parameter you want to get
     * @param defaultVal String defaultVal
     * @return The value of the parameter or "" if the parameter was not found.
     */
    public static String getParameter(HttpServletRequest request, String name,
			String defaultVal) {
		String temp = request.getParameter(name);
		return temp!=null?filtSqlStr(temp):defaultVal;
	}
    
    /**
     * 【函数功能】Gets a list of string parameters.
     * @param request The HttpServletRequest object, known as "request" in a JSP page.
     * @param name The name of the parameter you want to get
     * @return The "" value of a parameter, if the parameter can't be
     *         converted into an string.
     */
    public static String[] getParameters(HttpServletRequest request, String name) {
        return getParameters(request, name, "");
    }
    
    /**
     * 【函数功能】Gets a list of string parameters.
     * @param request The HttpServletRequest object, known as "request" in a JSP page.
     * @param name The name of the parameter you want to get
     * @param defaultVal string default value
     * @return The default value of a parameter, if the parameter can't be
     *         converted into an string.
     */
	public static String[] getParameters(HttpServletRequest request,
			String name, String defaultVal) {
		String[] paramValues = request.getParameterValues(name);
		if (paramValues == null) {
			return null;
		}
		if (paramValues.length < 1) {
			return new String[0];
		}
		String[] values = new String[paramValues.length];
		for (int i = 0; i < paramValues.length; i++) {
			values[i] = paramValues[i] != null ? filtSqlStr(paramValues[i])
					: defaultVal;
		}
		return values;
	}

    /**
	 * 【函数功能】Gets a parameter as a boolean.
	 * @param request The HttpServletRequest object, known as "request" in a JSP page.
	 * @param name The name of the parameter you want to get
	 * @return True if the value of the parameter was "true", false otherwise.
	 */
    public static boolean getBooleanParameter(HttpServletRequest request,
            String name) {
        return getBooleanParameter(request, name, false);
    }

   /**
    * 【函数功能】Gets a parameter as a boolean.
    * @param request The HttpServletRequest object, known as "request" in a JSP page.
    * @param name The name of the parameter you want to get
    * @param defaultVal boolean defaultVal
    * @return True if the value of the parameter was "true", false otherwise
    */
    public static boolean getBooleanParameter(HttpServletRequest request,
            String name, boolean defaultVal) {
        String temp = request.getParameter(name);
        if ("true".equals(temp) || "on".equals(temp)) {
            return true;
        } else if ("false".equals(temp) || "off".equals(temp)) {
            return false;
        } else {
            return defaultVal;
        }
    }
    
    /**
     * 【函数功能】Gets a list of boolean parameters.
     * @param request The HttpServletRequest object, known as "request" in a JSP page.
     * @param name The name of the parameter you want to get
     * @return The false value of a parameter, if the parameter can't be
     *         converted into an boolean.
     */
    public static boolean[] getBooleanParameters(HttpServletRequest request,
            String name) {
        return getBooleanParameters(request, name, false);
    }

    /**
     * 【函数功能】Gets a list of boolean parameters.
     * @param request The HttpServletRequest object, known as "request" in a JSP page.
     * @param name The name of the parameter you want to get
     * @param defaultNum boolean default value
     * @return The default value of a parameter, if the parameter can't be
     *         converted into an boolean.
     */
	public static boolean[] getBooleanParameters(HttpServletRequest request,
			String name, boolean defaultVal) {
		String[] paramValues = request.getParameterValues(name);
		if (paramValues == null) {
			return null;
		}
		if (paramValues.length < 1) {
			return new boolean[0];
		}
		boolean[] values = new boolean[paramValues.length];
		for (int i = 0; i < paramValues.length; i++) {
			if ("true".equals(paramValues[i]) || "on".equals(paramValues[i])) {
				values[i] = true;
			} else if ("false".equals(paramValues[i])
					|| "off".equals(paramValues[i])) {
				values[i] = false;
			} else {
				values[i] = defaultVal;
			}
		}
		return values;
	}

 	/**
	 * 【函数功能】Gets a parameter as an int.
	 * @param request The HttpServletRequest object, known as "request" in a JSP page.
	 * @param name The name of the parameter you want to get
	 * @return The int value of the parameter specified or the 0 value if the
	 *         parameter is not found.
	 */
 	public static int getIntParameter(HttpServletRequest request, String name) {
         return getIntParameter(request,name,0);
	}

	/**
	 * 【函数功能】Gets a parameter as an int.
     * @param request The HttpServletRequest object, known as "request" in a JSP page.
     * @param name The name of the parameter you want to get
     * @param defaultNum int defaultNum
     * @return The int value of the parameter specified or the default value if
     *         the parameter is not found.
     */
	public static int getIntParameter(HttpServletRequest request, String name,
            int defaultNum) {
        String temp = request.getParameter(name);
        if (temp != null && !temp.equals("")) {
            int num = defaultNum;
            try {
                num = Integer.parseInt(temp);
            } catch (Exception ignored) {
                num = defaultNum;
            }
            return num;
        } else {
            return defaultNum;
        }
    }

    /**
     * 【函数功能】Gets a list of int parameters.
     * @param request The HttpServletRequest object, known as "request" in a JSP page.
     * @param name The name of the parameter you want to get
     * @return The default value of a parameter, if the parameter can't be
     *         converted into an int.
     */
    public static int[] getIntParameters(HttpServletRequest request,
            String name) {
        return getIntParameters(request,name,0);
    }

    /**
     * 【函数功能】Gets a list of int parameters.
     * @param request The HttpServletRequest object, known as "request" in a JSP page.
     * @param name The name of the parameter you want to get
     * @param defaultNum int default value
     * @return The default value of a parameter, if the parameter can't be
     *         converted into an int.
     */
    public static int[] getIntParameters(HttpServletRequest request,
            String name, int defaultNum) {
        String[] paramValues = request.getParameterValues(name);
        if (paramValues == null) {
            return null;
        }
        if (paramValues.length < 1) {
            return new int[0];
        }
        int[] values = new int[paramValues.length];
        for (int i = 0; i < paramValues.length; i++) {
            try {
                values[i] = Integer.parseInt(paramValues[i]);
            } catch (Exception e) {
                values[i] = defaultNum;
            }
        }
        return values;
    }

    /**
     * 【函数功能】Gets a parameter as a long.
     * @param request The HttpServletRequest object, known as "request" in a JSP page.
     * @param name The name of the parameter you want to get
     * @return The long value of the parameter specified or the 
     *              0 value if the parameter is not found.
     */
    public static long getLongParameter(HttpServletRequest request,
            String name) {
        return getLongParameter(request,name,0);
    }

    /**
     * 【函数功能】Gets a parameter as a long.
     * @param request The HttpServletRequest object, known as "request" in a JSP page.
     * @param name The name of the parameter you want to get
     * @param defaultNum long default value
     * @return The long value of the parameter specified or the 
     *              default value if the parameter is not found.
     */
    public static long getLongParameter(HttpServletRequest request,
            String name, long defaultNum) {
        String temp = request.getParameter(name);
        if (temp != null && !temp.equals("")) {
            long num = defaultNum;
            try {
                num = Long.parseLong(temp);
            } catch (Exception ignored) {
                num = defaultNum;
            }
            return num;
        } else {
            return defaultNum;
        }
    }

    /**
     * 【函数功能】Gets a list of long parameters
     * @param request The HttpServletRequest object, known as "request" in a JSP page.
     * @param name The name of the parameter you want to get
     * @return The default value of a parameter, 
     *         if the parameter can't be converted into a long.
     */
    public static long[] getLongParameters(HttpServletRequest request,
            String name) {
        return getLongParameters(request,name,0);
    }

    /**
     * 【函数功能】Gets a list of long parameters
     * @param request The HttpServletRequest object, known as "request" in a JSP page.
     * @param name The name of the parameter you want to get
     * @param defaultNum long default value
     * @return The default value of a parameter, 
     *         if the parameter can't be converted into a long.
     */
    public static long[] getLongParameters(HttpServletRequest request,
            String name, long defaultNum) {
        String[] paramValues = request.getParameterValues(name);
        if (paramValues == null) {
            return null;
        }
        if (paramValues.length < 1) {
            return new long[0];
        }
        long[] values = new long[paramValues.length];
        for (int i = 0; i < paramValues.length; i++) {
            try {
                values[i] = Long.parseLong(paramValues[i]);
            } catch (Exception e) {
                values[i] = defaultNum;
            }
        }
        return values;
    }

    /**
     * 【函数功能】Gets a parameter as a double.
     * @param request The HttpServletRequest object, known as "request" in a JSP page. 
     * @param name The name of the parameter you want to get
     * @return The double value of the parameter specified or the 
     *                0 value if the parameter is not found.
     */
    public static double getDoubleParameter(HttpServletRequest request,
            String name) {
        return getDoubleParameter(request,name,0);
    }

    /**
     * 【函数功能】Gets a parameter as a double.
     * @param request The HttpServletRequest object, known as "request" in a JSP page. 
     * @param name The name of the parameter you want to get
     * @param defaultNum double default value
     * @return The double value of the parameter specified or the 
     *                default value if the parameter is not found.
     */
    public static double getDoubleParameter(HttpServletRequest request,
            String name, double defaultNum) {
        String temp = request.getParameter(name);
        if (temp != null && !temp.equals("")) {
            double num = defaultNum;
            try {
                num = Double.parseDouble(temp);
            } catch (Exception ignored) {
            }
            return num;
        } else {
            return defaultNum;
        }
    }
    
    /**
     * 【函数功能】Gets a list of double parameters.
     * @param request The HttpServletRequest object, known as "request" in a JSP page. 
     * @param name The name of the parameter you want to get
     * @return The double value of a parameter, 
    *         if the parameter can't be converted into a double.
     */
    public static double[] getDoubleParameters(HttpServletRequest request,
            String name) {
    	return getDoubleParameters(request,name);
    }

    /**
     * 【函数功能】Gets a list of double parameters.
     * @param request The HttpServletRequest object, known as "request" in a JSP page. 
     * @param name The name of the parameter you want to get
     * @param defaultNum double default value
     * @return The default value of a parameter, 
    *         if the parameter can't be converted into a double.
     */
    public static double[] getDoubleParameters(HttpServletRequest request,
            String name, double defaultNum) {
    	String[] paramValues = request.getParameterValues(name);
        if (paramValues == null) {
            return null;
        }
        if (paramValues.length < 1) {
            return new double[0];
        }
        double[] values = new double[paramValues.length];
        for (int i = 0; i < paramValues.length; i++) {
            try {
                values[i] = Double.parseDouble(paramValues[i]);
            } catch (Exception e) {
                values[i] = defaultNum;
            }
        }
        return values;
    }

   /**
    * 【函数功能】Gets a attribute as a string.
    * @param request The HttpServletRequest object, known as "request" in a JSP page.
    * @param name The name of the attribute you want to get
    * @return String The value of the parameter or null if the parameter 
    *                was not found or if the parameter is a zero-length string.
    */
    public static String getAttribute(HttpServletRequest request, String name) {
        return getAttribute(request, name, "");
    }

    /**
     * 【函数功能】Gets an attribute as a string.
     * @param request The HttpServletRequest object, known as "request" in a JSP page.
     * @param name The name of the attribute you want to get
     * @param defaultVal boolean default value
     * @return String if the value is not null and "". 
     */
    public static String getAttribute(HttpServletRequest request, String name,
            String defaultVal) {
        String temp = (String) request.getAttribute(name);
        if (temp != null) {
        	return temp;
        } else {
            return defaultVal;
        }
    }
    

    /**
     * 【函数功能】Gets an attribute as a boolean.
     * @param request The HttpServletRequest object, known as "request" in a JSP page.
     * @param name The name of the attribute you want to get
     * @return boolean True if the value of the attribute is "true", false otherwise.
     */
    public static boolean getBooleanAttribute(HttpServletRequest request,
			String name) {
    	return getBooleanAttribute(request,name,false);
	}

    /**
     * 【函数功能】Gets an attribute as a boolean.
     * @param request The HttpServletRequest object, known as "request" in a JSP page.
     * @param name The name of the attribute you want to get
     * @param defaultVal boolean default value
     * @return boolean True if the value of the attribute is "true", false otherwise.
     */
    public static boolean getBooleanAttribute(HttpServletRequest request,
			String name,boolean defaultVal) {
		String temp = (String) request.getAttribute(name);
		if (temp != null && temp.equals("true")) {
			return true;
		} else {
			return defaultVal;
		}
	}

    /**
     * 【函数功能】Gets an attribute as a int.
     * @param request The HttpServletRequest object, known as "request" in a JSP page.
	 * @param name The name of the attribute you want to get
	 * @return int The int value of the attribute or the 0 value if the
	 *         attribute is not found or is a zero length string.
	 */
    public static int getIntAttribute(HttpServletRequest request, String name) {
        return getIntAttribute(request,name,0);
    }

    /**
     * 【函数功能】Gets an attribute as a int.
     * @param request The HttpServletRequest object, known as "request" in a JSP page.
	 * @param name The name of the attribute you want to get
	 * @param defaultNum int default value
	 * @return int The int value of the attribute or the default value if the
	 *         attribute is not found or is a zero length string.
	 */
    public static int getIntAttribute(HttpServletRequest request, String name,
            int defaultNum) {
        String temp = (String) request.getAttribute(name);
        if (temp != null && !temp.equals("")) {
            int num = defaultNum;
            try {
                num = Integer.parseInt(temp);
            } catch (Exception ignored) {
            }
            return num;
        } else {
            return defaultNum;
        }
    }
    
    /**
     * 【函数功能】Gets an attribute as a long.
     * @param request The HttpServletRequest object, known as "request" in a JSP page.
     * @param name The name of the attribute you want to get
     * @return long The long value of the attribute or the 0 value if the
     *              attribute is not found or is a zero length string.
     */
    public static long getLongAttribute(HttpServletRequest request,
			String name) {
		return getLongAttribute(request,name,0);
	}

    /**
     * 【函数功能】Gets an attribute as a long.
     * @param request The HttpServletRequest object, known as "request" in a JSP page.
     * @param name The name of the attribute you want to get
     * @param defaultNum long default value
     * @return long The long value of the attribute or the default value if the
     *              attribute is not found or is a zero length string.
     */
    public static long getLongAttribute(HttpServletRequest request,
			String name, long defaultNum) {
		String temp = (String) request.getAttribute(name);
		if (temp != null && !temp.equals("")) {
			long num = defaultNum;
			try {
				num = Long.parseLong(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}

    /**
	 * 【函数功能】对输入字符串进行过滤
	 * @param inPutStr 输入的字符串
	 * @return 经过过滤后的字符串
	 */
    public static String filtSqlStr(String inPutStr) {
        if (inPutStr.indexOf("select ") >= 0
                || inPutStr.indexOf("insert ") >= 0
                || inPutStr.indexOf("delete from") >= 0
                || inPutStr.indexOf("count(") >= 0
                || inPutStr.indexOf("drop table") >= 0
                || inPutStr.indexOf("update ") >= 0
                || inPutStr.indexOf("truncate ") >= 0
                || inPutStr.indexOf("asc(") >= 0
                || inPutStr.indexOf("mid(") >= 0
                || inPutStr.indexOf("char(") >= 0
                || inPutStr.indexOf("xp_cmdshell") >= 0
                || inPutStr.indexOf("exec master") >= 0
                || inPutStr.indexOf("net localgroup administrators") >= 0
                || inPutStr.indexOf(" and ") >= 0
                || inPutStr.indexOf("net user") >= 0
                || inPutStr.indexOf(" or ") >= 0) {
            return "";
        } else {
            return inPutStr;
        }
    }
}
