package com.devilkxh.app.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.devilkxh.app.model.entity.AppGameShareInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface AppGameShareInfoMapper extends BaseMapper<AppGameShareInfo> {
    @Select(" select t.USER_NAME userName, t.USER_LOGO userLogo, s.share_date shareDate from APP_USER t" +
            " left join APP_GAME_SHARE_INFO s on t.open_id = s.share_open_id" +
            " where s.share_type = #{shareType} and s.open_id = #{openId} and s.SHARE_OPEN_ID is not null order by s.share_date desc")
    List<Map<String,String>> getShareInfo(AppGameShareInfo shareInfo);

    @Select("select share_type type, count(0) count from APP_GAME_SHARE_INFO where open_id = #{openId} group by share_type")
    List<Map<String, Object>> countGameByType(String openId);
}
