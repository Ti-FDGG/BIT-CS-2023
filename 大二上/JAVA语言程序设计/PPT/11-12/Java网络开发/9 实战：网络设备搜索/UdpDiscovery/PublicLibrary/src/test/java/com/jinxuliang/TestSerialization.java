package com.jinxuliang;

import com.google.gson.Gson;
import com.jinxuliang.model.BroadcastInfo;
import org.junit.jupiter.api.Test;

public class TestSerialization {
    @Test
    public void testBroadcastInfoToJson(){
        BroadcastInfo info=BroadcastInfo.builder().deviceCenterIp("191.168.1.5")
                .deviceCenterListenPort(30000).build();
        Gson gson=new Gson();
        String json=gson.toJson(info);
        System.out.println(json);
    }
}
