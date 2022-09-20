package com.yuxuan66.admin.support.json;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.ObjectDeserializer;

import java.lang.reflect.Type;

/**
 * 反序列化枚举
 *
 * @author Sir丶雨轩
 * @since 2022/9/20
 */
public class FastJsonEnumDeserializer implements ObjectDeserializer {
    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object o) {

        return null;
    }


}
