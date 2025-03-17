package com.jinxuliang.chatclientjavafx;

import com.jinxuliang.ChatMessage;

public interface IShowMessage {
    //在表格中显示消息
    void showMessage(ChatMessage message);
    //用于显示状态信息
    void showStatus(String message);
}
