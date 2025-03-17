package com.jinxuliang.udpnetworkdevicecenterjavafxapp;


import com.google.gson.Gson;
import com.jinxuliang.model.BroadcastInfo;
import com.jinxuliang.model.DeviceInfo;
import com.jinxuliang.model.UDPConstants;
import com.jinxuliang.UdpMessageHelper;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class MainController implements Initializable {
    //region "界面控件相关字段"
    @FXML
    private Button btnSend;
    @FXML
    private TableView<DeviceInfo> tableDevices;
    @FXML
    private TableColumn<DeviceInfo, String> ip;
    @FXML
    private TableColumn<DeviceInfo, Integer> port;
    @FXML
    private TableColumn<DeviceInfo, String> info;
    @FXML
    private Label lblInfo;
    //endregion

    //用于保存在线设备的列表
    private ObservableList<DeviceInfo> deviceInfos =
            FXCollections.observableList(new ArrayList<>());
    //用于实现UDP数据的发送与接收
    DatagramSocket ds = null;
    //当主窗体关闭时，结束后台线程
    Disposable schedularTask = null;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //设定TableView绑定“在线设备集合”
        ip.setCellValueFactory(new PropertyValueFactory<>("ip"));
        port.setCellValueFactory(new PropertyValueFactory<>("port"));
        info.setCellValueFactory(new PropertyValueFactory<>("info"));
        tableDevices.setItems(deviceInfos);

        ds = new DatagramSocket(UDPConstants.MANAGER_LISTEN_PORT);

        btnSend.setOnAction(e -> {
            //点击按钮，立即发送广播消息
            sendBroadcastMessage();
        });
        //使用RxJava，每隔5秒广播一次消息
        schedularTask = Observable.interval(0, 5, TimeUnit.SECONDS)
                .subscribe((counter) -> {
                    showInfo(LocalTime.now() + "：广播消息……");
                    sendBroadcastMessage();
                });
        //等待设备"汇报消息"
        waitForResponse();
    }

    //广播消息的发送
    private void sendBroadcastMessage() {
        new Thread(() -> {
            InetAddress address = null;
            try {
                address = InetAddress.getLocalHost();
                var deviceCenterInfo = BroadcastInfo.builder()
                        .deviceCenterIp(address.getHostAddress())
                        .deviceCenterListenPort(UDPConstants.MANAGER_LISTEN_PORT);
                var json = new Gson().toJson(deviceCenterInfo);
                sendMessage(json);
            } catch (IOException ex) {
                showInfo(ex.getMessage());
            }
        }).start();
    }

    //等待网络设备回应
    private void waitForResponse() {
        var thread = new Thread(() -> {
            try {
                while (true) {
                    //收到设备回发的信息之后，将其加入到在线设备集合中
                    var json = UdpMessageHelper.receiveMessage(ds, false);
                    var deviceInfo = new Gson().fromJson(json, DeviceInfo.class);
                    //重复的不再加入集合
                    if (!deviceInfos.contains(deviceInfo)) {
                        deviceInfos.add(deviceInfo);
                    }
                }
            } catch (IOException e) {
                showInfo(e.getMessage());
            }
        });
        thread.setDaemon(true); //设置为背景线程，以便自动终结
        thread.start();
    }

    //使用UDP发送消息
    private void sendMessage(String message)
            throws IOException {
        //设置为广播地址
        UdpMessageHelper.sendMessageTo(message,
                "255.255.255.255",
                UDPConstants.DEVICE_LISTEN_PORT,
                "");
    }

    //可以跨线程安全调用的信息显示方法，使用标签显示信息
    private void showInfo(String info) {
        if (!info.isEmpty()) {
            Platform.runLater(() -> {
                lblInfo.setText(info);
            });
        }
    }

    public void close() {
        if (schedularTask != null) {
            schedularTask.dispose();
        }
    }

}