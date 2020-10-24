package com.dxl.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.*;
import com.dxl.model.Course;
import com.dxl.model.User;

import java.util.Map;
import java.util.Set;

public class FastJSONUtil {

    private static final SerializeConfig config;

    static {
        config = new SerializeConfig();
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
    }

    private static final SerializerFeature[] features = {
//            SerializerFeature.WriteDateUseDateFormat,
            SerializerFeature.WriteMapNullValue, // 输出空置字段
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty,// 字符类型字段如果为null，输出为""，而不是null
            SerializerFeature.DisableCircularReferenceDetect
    };


    public static String toJSONString(Object object) {
        return JSON.toJSONString(object, config, features);
    }

    public static String toJSONNoFeatures(Object object) {
        return JSON.toJSONString(object, config);
    }

    public static String toJSONString(Object object,PropertyFilter profilter) {
        String json = JSON.toJSONString(object, profilter, features);
        return json;
    }

}
