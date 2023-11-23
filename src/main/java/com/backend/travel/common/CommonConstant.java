package com.backend.travel.common;

public interface CommonConstant {

    /**
     * 升序
     */
    String SORT_ORDER_ASC = "ascend";

    /**
     * 降序
     */
    String SORT_ORDER_DESC = " descend";

    /**
     * 密钥
     */
    String APP_SECRET = "daniaozhuanzhuanzhuan";

    /**
     * jwt过期时间
     */
    Long expireTime = 1000 * 60 * 60L * 12;
}
