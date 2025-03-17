package com.jinxuliang.javafxusejdbc.controller;

import com.jinxuliang.javafxusejdbc.JavaFXDbApplication;
import com.jinxuliang.javafxusejdbc.entity.OrderClient;
import com.jinxuliang.javafxusejdbc.repo.OrderClientRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    //region FXML控件声明
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnModify;

    @FXML
    private TableView tableClient;

    @FXML
    private TableColumn<OrderClient, Integer> id;

    @FXML
    private TableColumn<OrderClient, String> name;

    @FXML

    private TableColumn<OrderClient, String> address;

//endregion

    //绑定数据源（具备数据更改通知特性）
    private ObservableList<OrderClient> clients = null;
    //实现数据的CRUD
    private OrderClientRepository repository = null;
    //用于引用新增或修改窗体
    private Stage newStage = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //提取所有数据，放到表格中显示
        repository = new OrderClientRepository();

        clients = FXCollections.observableList(repository.getAllClients());

        //建立数据绑定
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        //设置TableView的数据源
        tableClient.setItems(clients);

        btnAdd.setOnAction(e -> {
            addOrModifyClient(true);
        });
        btnModify.setOnAction(e -> {
            addOrModifyClient(false);
        });
        btnDelete.setOnAction(e -> {
            var selectedIndex = tableClient.getSelectionModel().getSelectedIndex();
            var selectedOrderClient = clients.get(selectedIndex);
            repository.deleteClient(selectedOrderClient.getId());
            clients.remove(selectedIndex);
        });
    }

    private void addOrModifyClient(boolean isAddNew) {
        //加载布局文件
        var fxmlLoader = new FXMLLoader(
                JavaFXDbApplication.class.getResource("client-edit.fxml"));
        try {
            GridPane root = fxmlLoader.load();
            //装载“新增（或修改）窗体”控制器
            var controller = (EditClientController) fxmlLoader.getController();
            //将主控制器引用传给“新增（或修改）窗体”控制器
            controller.setMainController(this);

            if (isAddNew) {
                var newClient = new OrderClient(); //新增，创建一个空白数据对象
                controller.setOrderClient(newClient);
            } else {
                //修改，取出当前选中的行所对应的对象
                OrderClient client =
                        (OrderClient) tableClient.getSelectionModel().getSelectedItem();
                if (client == null) return;
                //克隆一份传给对话框窗体控制器
                controller.setOrderClient((OrderClient) client.clone());
            }

            //显示对话框窗体
            var scene = new Scene(root);
            newStage = new Stage();
            if (isAddNew) {
                newStage.setTitle("新增记录");
            } else {
                newStage.setTitle("修改记录");
            }
            newStage.setScene(scene);
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.showAndWait();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    //主窗体控制器提供将被对话窗体口“回调”的方法
    public void onAddOrEditClient(boolean isAddNew, OrderClient client) {
        repository.insertOrUpdateClients(client);
        if (isAddNew) {
            //将新对象加入到Clients集合中
            clients.add(client);
        } else {
            OrderClient currentClient =
                    (OrderClient) tableClient.getSelectionModel().getSelectedItem();
            //更新当前选中行所对应的数据对象
            currentClient.setName(client.getName());
            currentClient.setAddress(client.getAddress());
        }
        if (newStage != null) {
            newStage.close();
        }
    }

    public void onCancelEdit() {
        newStage.close();
    }
}