package com.jinxuliang.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class DeviceInfo {
    //网络设备IP
    private String ip;
    //网络设备监听的端口（用于接收信息）
    private int port;
    //网络设备信息
    private String info;
}
