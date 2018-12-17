package org.sang.mapper;

import org.apache.ibatis.annotations.Param;
import org.sang.bean.Position;

import java.util.List;

/**
 * @author sang
 * @date 2018/1/10
 */
public interface PositionMapper {

    int addPos(@Param("pos") Position pos);

    Position getPosByName(String name);

    /**
     * 获取所有有效职位
     *
     * @return 职位列表
     */
    List<Position> getAllPos();

    int deletePosById(@Param("pids") String[] pids);

    int updatePosById(@Param("pos") Position position);
}
