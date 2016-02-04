package com.example.tangshisongci.model.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by bigwen on 2015/12/26.
 */
public class MyAnnotion {
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public  @interface DBField{};

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface DBPrimaryKey{};


    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface DBName{
        String value();
    };
}
