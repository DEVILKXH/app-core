package com.devilkxh.app.common.wrapper;

import com.baomidou.mybatisplus.mapper.Wrapper;
import org.apache.commons.lang3.StringUtils;

/**
 * @author kexiaohong
 * @since 2020/6/4
 */
public class QueryWrapper extends Wrapper {
    @Override
    public String getSqlSegment() {
        String sqlWhere = sql.toString();
        if (StringUtils.isBlank(sqlWhere)) {
            return null;
        }
        return isWhere != null ? (isWhere ? sqlWhere : sqlWhere.replaceFirst("WHERE", AND_OR)) : sqlWhere.replaceFirst("WHERE", AND_OR);

    }
}
