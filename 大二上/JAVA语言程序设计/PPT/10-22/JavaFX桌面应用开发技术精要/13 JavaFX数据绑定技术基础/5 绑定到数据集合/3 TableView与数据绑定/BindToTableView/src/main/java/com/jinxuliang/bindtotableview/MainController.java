package com.jinxuliang.bindtotableview;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import com.jinxuliang.bindtotableview.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MainController implements Initializable {
    //绑定数据源
    private ObservableList<Person> personList =
            FXCollections.observableArrayList();
    @FXML
    private TableView<Person> tableView;
    @FXML
    private TableColumn<Person, String> nameColumn;
    @FXML
    private TableColumn<Person, Integer> ageColumn;

    private Random random = new Random();
    private PersonApp main;
    private Stage primaryStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //设定两个列从Person对象的哪个属性中提取数据
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<Person, String>("name"));
        ageColumn.setCellValueFactory(
                new PropertyValueFactory<Person, Integer>("age"));
    }

    //此方法给绑定数据源添加初始化示例数据
    public void setTableData() {
        personList.add(new Person("张三", 45));
        personList.add(new Person("李四", 46));
    }

    //此方法由主类调用，完成数据绑定任务
    public void setMain(PersonApp main, Stage primaryStage) {
        this.main = main;
        this.primaryStage = primaryStage;
        setTableData(); //装载初始化数据
        tableView.setItems(personList); //绑定数据源
        tableView.getSelectionModel().select(0);
    }

    public void close() {
        primaryStage.close();
    }

    //新增用户（注意，使用数据绑定机制更新UI，支持多线程方式）
    public void add() {
        new Thread(() -> {
            Person person = new Person("用户" + (personList.size() + 1),
                    random.nextInt(100));
            personList.add(person);
        }).start();
    }

    //删除用户
    public void remove() {
        int index = tableView.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            personList.remove(index);
        }
    }

    public void addAge() {
        int index = tableView.getSelectionModel().getSelectedIndex();
        Person person = personList.get(index);
        //age值一改，TableView会自动更新显示
        person.setAge(person.getAge() + 1);
    }


}
