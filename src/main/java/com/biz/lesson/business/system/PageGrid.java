package com.biz.lesson.business.system;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * @program: lession6
 * @description:
 * @author: lyy
 * @generate: 2020-05-28 18:11
 **/
@Data
public class PageGrid implements Serializable {
    private static final long serialVersionUID = -1682349799092177474L;

    private Integer rows = 0;
    private Integer page = 10;
    private String sidx;
    private String sord;

    public Integer getCurrentPage() {
        return page -1 < 0 ? 0 : page -1;
    }

    public PageRequest getPageRequest(){
        Sort sort = new Sort(this.sidx);
        return new PageRequest(getCurrentPage(),this.rows,sort);
    }
}
