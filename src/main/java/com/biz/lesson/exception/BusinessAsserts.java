package com.biz.lesson.exception;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class BusinessAsserts {

    private BusinessAsserts() {
    }

    public static void exists(Object object, Object id) {
        if (object == null) {
            throw new ObjectNotExistsException(id);
        }
    }
    public static void notBlank(String str,String msg) {
        if (StringUtils.isBlank(str)) {
            throw new ObjectNotExistsException(msg);
        }
    }

    public static void notNull(Object object,String msg) {
        if (object == null) {
            throw new ObjectNotExistsException(msg);
        }
    }

    public static void notNull(Object object) {
        if (object == null) {
            throw new ObjectNotExistsException("");
        }
    }


    public static void empty(@SuppressWarnings("rawtypes") Collection collection) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new CollectionEmptyException();
        }
    }
    
    public static void empty(Object[] array) {
        if (ArrayUtils.isEmpty(array)) {
            throw new ArrayEmptyException();
        }
    }
    
    
}
