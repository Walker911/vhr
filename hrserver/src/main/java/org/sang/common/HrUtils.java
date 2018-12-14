package org.sang.common;

import org.sang.bean.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author sang
 * @date 2017/12/30
 */
public class HrUtils {
    public static Hr getCurrentHr() {
        return (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
