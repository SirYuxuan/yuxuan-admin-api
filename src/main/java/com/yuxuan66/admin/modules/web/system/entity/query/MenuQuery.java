package com.yuxuan66.admin.modules.web.system.entity.query;

import com.yuxuan66.admin.modules.web.system.entity.Menu;
import com.yuxuan66.admin.support.base.BaseQuery;
import lombok.Data;

/**
 * @author Sir丶雨轩
 * @since 2022/9/19
 */
@Data
public class MenuQuery extends BaseQuery<Menu> {

    private Long pid = 0L;

    private Boolean all = false;
}
