#include <iostream>
#include <stdexcept>
using namespace std;

class CLoopSet {
private:
    int *Data;
    int Space;
    int Size;
public:
    // Constructor and Destructor

    // 构造函数
    CLoopSet(int n = 1) {
        if (n < 1) {
            throw invalid_argument("Space must be greater than 0");
        }
        Size = 0;
        Space = n;
        Data = new int[Space];
    }
    // 析构函数
    ~CLoopSet() {
        delete[] Data;
    }
    // 拷贝构造函数
    CLoopSet(const CLoopSet &s) {
        Size = s.Size;
        Space = s.Space;
        Data = new int[Space];
        for (int i = 0; i < Size; i++) {
            Data[i] = s.Data[i];
        }
    }

    // Getter and Setter
    int *GetData() {
        return Data;
    }
    int GetSize() {
        return Size;
    }
    int GetSpace() {
        return Space;
    }
    void SetSize(int size) {
        Size = size;
    }

    // Member functions
    void print();
    bool IsExist(int x);
    bool IsEqual(CLoopSet &s2);
    CLoopSet Intersection(CLoopSet &s2);
    CLoopSet Union(CLoopSet &s2);
    int RemoveItem(int x);
    int AddItem(int x);
    int GetItem(int i);
};

// 打印集合
void CLoopSet::print() {
    cout << "{ ";
    for (int i = 0; i < Size; i++) {
        cout << Data[i];
        if (i < Size - 1) {
            cout << ", ";
        }
    }
    cout << " }" << endl;
}

// 判断元素是否存在
bool CLoopSet::IsExist(int x) {
    for (int i = 0; i < Size; i++) {
        if (Data[i] == x) {
            return true;
        }
    }
    return false;
}

// 判断两个集合是否相等
bool CLoopSet::IsEqual(CLoopSet &s2) {
    if (Size != s2.Size) {
        return false;
    }
    for (int i = 0; i < Size; i++) {
        if (Data[i] != s2.Data[i]) {
            return false;
        }
    }
    return true;
}

// 求交集
CLoopSet CLoopSet::Intersection(CLoopSet &s2) {
    CLoopSet result(Space);
    for (int i = 0; i < Size; i++) {
        if (s2.IsExist(Data[i])) {
            result.AddItem(Data[i]);
        }
    }
    return result;
}

// 求并集
CLoopSet CLoopSet::Union(CLoopSet &s2) {
    CLoopSet result(Space + s2.Space);
    for (int i = 0; i < Size; i++) {
        result.AddItem(Data[i]);
    }
    for (int i = 0; i < s2.Size; i++) {
        if (!result.IsExist(s2.Data[i])) {
            result.AddItem(s2.Data[i]);
        }
    }
    return result;
}

// 移除元素
int CLoopSet::RemoveItem(int x) {
    for (int i = 0; i < Size; i++) {
        if (Data[i] == x) {
            for (int j = i; j < Size - 1; j++) {
                Data[j] = Data[j + 1];
            }
            Size--;
            return 0;
        }
    }
    cout << "The number is not in the set." << endl;
    return -1;
}

// 添加元素
int CLoopSet::AddItem(int x) {
    if (IsExist(x)) {
        cout << "The number is already in the set." << endl;
        return -1;
    }
    if (Size + 1 > Space) {
        cout << "The space is full." << endl;
        return -1;
    }
    Data[Size++] = x;
    return 0;
}

// 获取元素（支持循环访问）
int CLoopSet::GetItem(int i) {
    return Data[i % Size];
}

// 主函数
int main() {
    CLoopSet loopSet1(5);
    loopSet1.AddItem(1);
    loopSet1.AddItem(2);
    loopSet1.AddItem(3);

    CLoopSet loopSet2(5);
    loopSet2.AddItem(3);
    loopSet2.AddItem(4);
    loopSet2.AddItem(5);

    cout << "Set 1: ";
    loopSet1.print();

    cout << "Set 2: ";
    loopSet2.print();

    CLoopSet unionSet = loopSet1.Union(loopSet2);
    cout << "Union: ";
    unionSet.print();

    CLoopSet intersectionSet = loopSet1.Intersection(loopSet2);
    cout << "Intersection: ";
    intersectionSet.print();

    return 0;
}