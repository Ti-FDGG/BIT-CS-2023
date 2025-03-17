package com.jinxuliang.javafxmvvmdemo;

import com.jinxuliang.javafxmvvmdemo.model.LoginManager;
import com.jinxuliang.javafxmvvmdemo.model.LoginViewModel;
import com.jinxuliang.javafxmvvmdemo.model.LoginResult;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnOK;

    @FXML
    private Button btnCancel;

    private final LoginManager loginManager = new LoginManager();

    private final LoginViewModel loginViewModel = new LoginViewModel();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //为按钮挂接事件响应代码
        btnOK.setOnAction(this::login);
        btnCancel.setOnAction(this::cancel);

        //建立View与ViewModel间的数据绑定关系
        txtName.textProperty().bindBidirectional(loginViewModel.nameProperty());
        txtPassword.textProperty().bindBidirectional(loginViewModel.passwordProperty());
    }

    private void cancel(ActionEvent actionEvent) {
        //取消时，重置所有输入控件
        loginViewModel.setName("");
        loginViewModel.setPassword("");
        //焦点回到用户名文本框
        txtName.requestFocus();
    }

    private void login(ActionEvent actionEvent) {
        if (loginViewModel.getName().trim().length() > 0 &&
                loginViewModel.getPassword().trim().length() > 0) {
            //调用业务组件，实现”登录“这一业务功能
            var result = loginManager.login(loginViewModel.getName(),
                    loginViewModel.getPassword());
            //依据处理结果进行不同的响应
            if (result == LoginResult.SUCCESS) {
                new Alert(Alert.AlertType.INFORMATION, result.toString()).show();
            } else {
                new Alert(Alert.AlertType.ERROR, result.toString()).show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "用户名及密码不能为空",
                    ButtonType.OK).show();
        }
    }
}