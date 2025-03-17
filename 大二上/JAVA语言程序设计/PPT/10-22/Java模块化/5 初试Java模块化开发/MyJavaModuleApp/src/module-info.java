//模块的名字，其实就是IntelliJ IDEA的Project名
//因为IntelliJ中的项目，至少都有一个模块（称为默认模块）
module MyJavaModuleApp {
    //声明本模块需要调用MyLibModule模块中的代码
    requires MyLibModule;
}
