/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.bo.manage.permission;

/**
 * 【类说明】用户列表页面参数对象
 * @author yehongyu.org
 * @version 1.0 2007-11-28 下午04:24:38
 */
public class UserParam implements Cloneable {

    private String username;

    private String name;

    private String depname;

    private String admin;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    /**
     * @return Returns the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return Returns the admin.
     */
    public String getAdmin() {
        return admin;
    }

    /**
     * @param admin
     *            The admin to set.
     */
    public void setAdmin(String admin) {
        this.admin = admin;
    }
    
    // 重写Object的clone
    public UserParam clone() throws CloneNotSupportedException {
         return (UserParam)super.clone();
     }

}
