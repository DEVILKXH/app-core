package com.devilkxh.app.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.devilkxh.app.model.entity.AppGameScore;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface AppGameScoreMapper extends BaseMapper<AppGameScore> {

    @Select(" select u.USER_NAME userName, u.USER_LOGO userLogo, case when t.score is null then 0 else t.score end score from APP_USER u " +
            " left join (" +
            " select OPEN_ID, sum(score) score FROM APP_GAME_SCORE " +
            " group by OPEN_ID) t on u.open_id = t.OPEN_ID" +
            " order by t.score desc")
    List<Map<String, String>> getRankList();
}
