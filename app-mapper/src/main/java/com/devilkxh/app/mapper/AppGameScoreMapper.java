package com.devilkxh.app.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.devilkxh.app.model.entity.AppGameScore;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface AppGameScoreMapper extends BaseMapper<AppGameScore> {

    @Select(" SELECT  u.USER_NAME userName, u.USER_LOGO userLogo, " +
            " CASE WHEN t.score IS NULL THEN 0 ELSE t.score END score " +
            " FROM APP_USER u LEFT JOIN " +
            " (SELECT t1.OPEN_ID, SUM(t1.score) score FROM " +
            " (SELECT  OPEN_ID, point, MAX(score) score FROM " +
            " APP_GAME_SCORE GROUP BY OPEN_ID , point) t1 " +
            " GROUP BY t1.OPEN_ID) t ON u.open_id = t.OPEN_ID " +
            " ORDER BY t.score DESC")
    List<Map<String, String>> getRankList();

    @Select("select ifnull(max(score), 0) score from APP_GAME_SCORE where OPEN_ID = #{openId} and POINT = #{point}")
    int getMaxScore(AppGameScore score);

    @Select("select ifnull(sum(t.score), 0) from (" +
            "select  POINT, MAX(SCORE) score from APP_GAME_SCORE where OPEN_ID = #{openId}  and point != #{point} " +
            "group by point ) t")
    int getAllScore(AppGameScore score);
}
