package com.jinxuliang.protocol;

import lombok.Data;

//这条指令，用于实现用户登录
@Data
public class LoginRequestPacket extends Packet {
    private Integer userId;
    private String userName;
    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
