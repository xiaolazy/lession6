package com.biz.lesson.config;

import com.biz.lesson.util.CryptoUtil;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * @program: lession6
 * @description: 对数据库账号密码进行加解密
 * @author: lyy
 * @generate: 2020-05-25 14:30
 **/
public class DatasourcePropertyConfigurer extends PropertyPlaceholderConfigurer {
    private String[] encryptPropNames = {"jdbc.username", "jdbc.password"};

    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if (isEncryptProp(propertyName))
        {
            String decryptValue = CryptoUtil.decrypt(propertyValue);
            System.out.println(decryptValue);
            return decryptValue;
        }else {
            return propertyValue;
        }
    }

    private boolean isEncryptProp(String propertyName)
    {
        for (String encryptName : encryptPropNames)
        {
            if (encryptName.equals(propertyName))
            {
                return true;
            }
        }
        return false;
    }
}
