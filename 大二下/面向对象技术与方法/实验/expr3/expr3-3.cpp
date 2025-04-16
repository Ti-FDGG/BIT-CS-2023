#include <iostream>
using namespace std;

class CMyStack {
private:
    char* m_pTop;      // 栈顶指针
    int m_iSize;       // 实际元素数量
    int m_iCapacity;   // 栈的容量

public:
    CMyStack(int size);
    ~CMyStack();
    char Pop();
    char Peek();
    bool Push(char ch);
    bool isEmpty();
    bool isFull();
    int GetSize();
    void Clear();
};

// 构造函数实现
CMyStack::CMyStack(int size) {
    m_iCapacity = size;
    m_iSize = 0;
    m_pTop = new char[size];  // 分配内存空间
}

// 析构函数实现
CMyStack::~CMyStack() {
    if (m_pTop) {
        delete[] m_pTop;
        m_pTop = nullptr;
    }
}

// 弹出栈顶元素
char CMyStack::Pop() {
    if (isEmpty()) {
        throw runtime_error("Stack is empty");
    }
    m_iSize--;
    return m_pTop[m_iSize];
}

// 查看栈顶元素但不弹出
char CMyStack::Peek() {
    if (isEmpty()) {
        throw runtime_error("Stack is empty");
    }
    return m_pTop[m_iSize - 1];
}

// 压入新元素
bool CMyStack::Push(char ch) {
    if (isFull()) {
        return false;
    }
    m_pTop[m_iSize] = ch;
    m_iSize++;
    return true;
}

// 检查栈是否为空
bool CMyStack::isEmpty() {
    return m_iSize == 0;
}

// 检查栈是否已满
bool CMyStack::isFull() {
    return m_iSize >= m_iCapacity;
}

// 获取当前元素数量
int CMyStack::GetSize() {
    return m_iSize;
}

// 清空栈
void CMyStack::Clear() {
    m_iSize = 0;
}

#ifdef MAIN_FILE
// 测试代码
int main() {
    CMyStack stack(5);  // 创建一个容量为5的栈
    
    // 测试Push操作
    cout << "Pushing elements: A, B, C" << endl;
    stack.Push('A');
    stack.Push('B');
    stack.Push('C');
    
    // 测试GetSize
    cout << "Current size: " << stack.GetSize() << endl;
    
    // 测试Peek
    cout << "Top element: " << stack.Peek() << endl;
    
    // 测试Pop
    cout << "Popping elements: ";
    while (!stack.isEmpty()) {
        cout << stack.Pop() << " ";
    }
    cout << endl;
    
    // 测试Clear
    stack.Push('X');
    stack.Push('Y');
    stack.Clear();
    cout << "After Clear, size: " << stack.GetSize() << endl;
    
    return 0;
}
# endif // MAIN_FILE