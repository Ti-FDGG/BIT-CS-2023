package com.jinxuliang.protocol.serialize;

public interface Serializer {
    //默认使用JSON序列化
    byte JSON_SERIALIZER = 1;
    Serializer DEFAULT = new JSONSerializer();

    //获取序列化算法ID
    byte getSerializerAlgorithm();
    //将Java对象序列化为字节数组
    byte[] serialize(Object object);
    //从字节数组中提取数据，反序列化为Java对象
    <T> T deserialize(Class<T> clazz,byte[] bytes);

    //依据序列化算法id，获取具体的序列化器实例
    static Serializer getSerializer(byte serializerAlgorithm) {
        if(serializerAlgorithm == JSON_SERIALIZER){
            return DEFAULT;
        }
        return null;
    }
}
