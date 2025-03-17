package com.jinxuliang.protocol;

//在这里，集中定义指令集，可以定义最多256个指令
public interface Command {
    //登录请求指令id
    Byte LOGIN_REQUEST = 1;
    //登录响应id
    Byte LOGIN_RESPONSE = 2;

    //依据指令id，获取具体指令实现类型
    static Class<? extends Packet> getRequestType(byte command){
        if(command == LOGIN_REQUEST){
            return LoginRequestPacket.class;
        }
        if(command == LOGIN_RESPONSE){
            return LoginResponsePacket.class;
        }
        return null;
    }
}
