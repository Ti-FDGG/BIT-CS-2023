package com.jinxuliang.chatclientjavafx;

import com.jinxuliang.ChatMessage;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

//控制器实现了IShowMessage接口
public class MainController
        implements Initializable, IShowMessage {

    //region 控件变量说明
    @FXML
    private TextArea txtMessage;

    @FXML
    private Button btnSend;

    @FXML
    private Button btnConnect;

    @FXML
    private Button btnQuit;

    @FXML
    private TextField txtIP;

    @FXML
    private Label lblStatus;

    @FXML
    private TableView<ChatMessage> tbMessage;

    @FXML
    private TableColumn<ChatMessage, String> tcUser;

    @FXML
    private TableColumn<ChatMessage, String> tcMessage;

    @FXML
    private TextField txtUserName;

    @FXML
    private Button btnRename;

    //endregion

    private ChatClient chatClient = null;

    private String userName;
    //用于保存聊天消息，它们将显示在TableView中
    ObservableList<ChatMessage> messages =
            FXCollections.observableArrayList();

    private void disableControlsBeforeConnect(){
        btnConnect.setDisable(false);
        btnQuit.setDisable(true);
        btnSend.setDisable(true);
        txtMessage.setDisable(true);
        tbMessage.setDisable(true);
    }

    private void enableControlsAfterConnect(){
        //禁用连接按钮
        btnConnect.setDisable(true);
        btnSend.setDisable(false);
        btnQuit.setDisable(false);
        txtMessage.setDisable(false);
        tbMessage.setDisable(false);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        disableControlsBeforeConnect();

        userName = UUID.randomUUID().toString();
        txtUserName.setText(userName);

        btnRename.setOnAction(e->{
            String userChangeName=txtUserName.getText();
            if(!userChangeName.isEmpty()){
                userName=userChangeName;
                showStatus("用户名已经改为："+userChangeName);
            }
        });

        tcUser.setCellValueFactory(new PropertyValueFactory<ChatMessage, String>("userName"));
        tcMessage.setCellValueFactory(new PropertyValueFactory<ChatMessage, String>("message"));
        tbMessage.setItems(messages);


        btnConnect.setOnAction(e -> {
            String ip = txtIP.getText();
            if (!ip.isEmpty()) {
                new Thread(() -> {
                    //实例化一个ChatClient，用于接收和发送消息
                    chatClient = new ChatClient(txtIP.getText(),
                            this, userName);
                    chatClient.start();
                }).start();
                enableControlsAfterConnect();
                txtMessage.requestFocus();
            }
        });

        btnSend.setOnAction(e -> {
            if (chatClient == null) {
                return;
            }
            String message = txtMessage.getText();
            if (!message.isEmpty()) {
                new Thread(() -> {
                    try {
                        //将用户名与消息序列化为Json,发送出去
                        ChatMessage messageToBeSent = ChatMessage.builder()
                                .userName(userName)
                                .message(message)
                                .build();
                        chatClient.send(ChatMessage.toJson(messageToBeSent));
                        Platform.runLater(() -> {
                            this.lblStatus.setText("消息已经发送");
                            this.txtMessage.selectAll();
                            this.txtMessage.requestFocus();
                        });
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }).start();
            }
        });

        btnQuit.setOnAction(e -> {
            quit();
        });
    }

    public void quit() {
        if (chatClient != null) {
            chatClient.close();
        }
        disableControlsBeforeConnect();
    }

    @Override
    public void showMessage(ChatMessage message) {
        //利用数据绑定显示聊天消息
        messages.add(ChatMessage.builder().
                userName(message.getUserName())
                .message(message.getMessage())
                .build());
    }

    @Override
    public void showStatus(String message) {
        //跨线程访问UI控件，更新状态信息
        Platform.runLater(() -> {
            this.lblStatus.setText(message);
        });
    }
}