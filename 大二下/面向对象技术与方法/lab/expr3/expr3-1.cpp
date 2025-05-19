#include <iostream>
using namespace std;

class Singleton {
private:
    static Singleton* s;  // 静态成员变量，用于存储唯一实例
    Singleton() { cout << "Constructor" << endl; }  // 私有构造函数，防止外部创建实例

public:
    static Singleton* GetInstance() {
        if (s == nullptr) {
            s = new Singleton();
        }
        return s;
    }
    
    // 修正后的析构函数
    ~Singleton() {
        cout << "Destructor called" << endl;
        // 不再在析构函数中删除s，避免递归删除
    }
    
    // 添加静态方法用于清理资源
    static void ReleaseInstance() {
        /*
        提供一个显式的接口，用于安全地释放单例对象的内存。
        避免在析构函数中直接删除静态成员变量引发的递归调用问题。
        */
        if (s != nullptr) {
            delete s;
            s = nullptr;
            cout << "Release the static s." << endl;
        }
    }
};

// 静态成员变量的定义
Singleton* Singleton::s = nullptr;

int main() {
    Singleton* ps = Singleton::GetInstance();
    cout << "Singleton instance address: " << ps << endl;
    
    // 使用ReleaseInstance来正确清理资源
    Singleton::ReleaseInstance();
    return 0;
}
