package com.jinxuliang.javafxusejdbc.controller;

import com.jinxuliang.javafxusejdbc.entity.OrderClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditClientController implements Initializable {
    //被新增或修改的对象
    private OrderClient orderClient = null;

    //引用主控制器，以便回调它的方法
    private MainController mainController;

    //region FXML控件声明
    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAddress;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnOk;
    //endregion

    //region getter and setter

    public void setOrderClient(OrderClient orderClient) {
        this.orderClient = orderClient;
        initDataBind();
    }

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnCancel.setOnAction(e -> {
            mainController.onCancelEdit();
        });
        btnOk.setOnAction(e -> {
            mainController.onAddOrEditClient(
                    orderClient.getId()==0, orderClient);
        });
    }

    //初始化数据绑定
    private void initDataBind() {
        if (orderClient != null) {
            txtName.textProperty().bindBidirectional(orderClient.nameProperty());
            txtAddress.textProperty().bindBidirectional(orderClient.addressProperty());
        }

    }
}
