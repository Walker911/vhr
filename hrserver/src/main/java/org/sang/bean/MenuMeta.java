package org.sang.bean;

import java.io.Serializable;

/**
 * @author sang
 * @date 2018/1/26
 */
public class MenuMeta implements Serializable {

    private boolean keepAlive;
    private boolean requireAuth;

    public boolean isKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public boolean isRequireAuth() {
        return requireAuth;
    }

    public void setRequireAuth(boolean requireAuth) {
        this.requireAuth = requireAuth;
    }
}
