package org.sang.mapper;

import org.sang.bean.Menu;

import java.util.List;

/**
 * @author sang
 * @date 2018/1/26
 */
public interface MenuMapper {
    List<Menu> getAllMenu();

    List<Menu> getMenusByHrId(Long hrId);

    List<Menu> menuTree();

    List<Long> getMenusByRid(Long rid);
}
