package com.jinxuliang.protocol;

import lombok.Data;
//登录响应数据包
@Data
public class LoginResponsePacket extends Packet {
    private Boolean isSuccess;
    private String message;
    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
