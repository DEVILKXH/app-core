package com.devilkxh.app.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.devilkxh.app.model.entity.AppGameLog;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface AppGameLogMapper extends BaseMapper<AppGameLog> {
    @Select("select type, count(0) count from APP_GAME_LOG where open_id = #{openId} group by type")
    List<Map<String, Object>> countGameByType(String openId);
}
