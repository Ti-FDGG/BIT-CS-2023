#include <iostream>
using namespace std;

class vector {
public:
    // 构造函数
    vector(int s) {
        size = s;
        v = new int[s];
        for(int i = 0; i < size; i++) v[i] = i * 10;  // 修正：使用size而不是capacity
    }
    
    // 析构函数
    ~vector() { 
        if (v) {  // 修正：检查v是否为nullptr
            delete[] v;  
            v = nullptr; 
        } 
    }
    
    // 只读的operator[]声明
    const int& operator[](int index) const {
        // 检查索引是否越界
        if (index < 0 || index >= size) {
            throw out_of_range("Index out of bounds");
        }
        return v[index];  // 返回const引用，防止修改
    }

private:
    int* v;
    int size;
};

int main() {
    vector vec(5);
    
    // vec[2] = 12;   // ERROR: 不允许修改，因为operator[]返回const引用
    
    int x = vec[2];  // 正确：可以读取值
    cout << "vec[2] = " << x << endl;  // 输出应该是20
    
    return 0;
}
