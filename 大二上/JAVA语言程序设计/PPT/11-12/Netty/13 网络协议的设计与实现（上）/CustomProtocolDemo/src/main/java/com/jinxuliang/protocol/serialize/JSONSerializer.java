package com.jinxuliang.protocol.serialize;

import com.alibaba.fastjson2.JSON;

//使用阿里巴巴的FastJson2进行序列化
public class JSONSerializer implements Serializer {
   //序列化算法名称，采用字节来表示
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
