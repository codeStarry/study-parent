package com.jd.study.common.utils;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zk.chen
 * @date 2020/8/1
 */
public class PageUtils {

    /**
     * 逻辑分页
     *
     * @param page
     * @param pageSize
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<T> pageHelper(int page, int pageSize, List<T> list) {
        int size = CollectionUtils.isEmpty(list) ? 0 : list.size();
        if (size > 0 && page > 0 && pageSize > 0) {
            int index = (page - 1) * pageSize;
            if (index > size) {
                return new ArrayList<T>();
            }
            return list.subList(index, size - index > pageSize ? index + pageSize : size);
        }
        return list;
    }
}
