package com.jinxuliang.model;

//用于广播的消息

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BroadcastInfo {
    //设备控制中心IP地址
    private String deviceCenterIp;
    //设备控制中心监听的端口（当前仅用于UDP）
    private int deviceCenterListenPort;
}
