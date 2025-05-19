#include <iostream>

using namespace std;

class CSmart 
{
private:
    inline static int count = 0; // C++17支持通过inline static在class内初始化静态成员变量
    bool destroyed; // 标志位，表示析构函数是否被手动调用过
public:
    CSmart() : destroyed(false) {
        count++;
        Alert();
    };
    ~CSmart() {
        if (!destroyed) {
            destroyed = true;
            count--;
            Alert();
        }
    };

    void Alert() {
        cout << count;
        if (count == 1) {
            cout << " object" << endl;
        }
        else {
            cout << " objects" << endl;
        }
    }
};  
void DoSomething() 
{ 
    CSmart s; 
} 

CSmart s1; 

int main()  { 
    CSmart s2;  
    DoSomething();   
    CSmart *s3 = new CSmart; 
    delete s3; 
    s2.~CSmart();   
    return 0; 
} 