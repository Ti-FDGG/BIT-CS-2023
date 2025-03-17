module ModuleB {
    exports cn.edu.bit.cs.moduleb;
    //声明对ModuleC模块的依赖是可以传递的
    requires transitive ModuleC;
    //requires ModuleC;
}

