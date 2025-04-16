#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

class CLINT {
private:
    string number;  // 存储大整数的字符串表示
    
    // 辅助函数
    string addStrings(const string& num1, const string& num2) const;  // 字符串加法
    void removeLeadingZeros();  // 移除前导零

public:
    // 构造函数
    CLINT(const string& num = "0") {
        number = num;
        removeLeadingZeros();
    }
    
    // 加法运算符重载
    CLINT operator+(const CLINT& other) const;
    
    // 输出运算符重载
    friend ostream& operator<<(ostream& os, const CLINT& num);
};

// 移除前导零
void CLINT::removeLeadingZeros() {
    while (number.length() > 1 && number[0] == '0') {
        number.erase(0, 1);
    }
}

// 字符串加法实现
string CLINT::addStrings(const string& num1, const string& num2) const {
    string result;
    int carry = 0;
    int i = num1.length() - 1;
    int j = num2.length() - 1;
    
    // 从右向左逐位相加
    while (i >= 0 || j >= 0 || carry) {
        int sum = carry;
        if (i >= 0) sum += num1[i--] - '0';
        if (j >= 0) sum += num2[j--] - '0';
        
        carry = sum / 10;
        result.push_back(sum % 10 + '0');
    }
    
    // 反转结果字符串
    reverse(result.begin(), result.end());
    return result;
}

// 加法运算符重载实现
CLINT CLINT::operator+(const CLINT& other) const {
    CLINT result;
    result.number = addStrings(this->number, other.number);
    return result;
}

// 输出运算符重载实现
ostream& operator<<(ostream& os, const CLINT& num) {
    os << num.number;
    return os;
}

// 测试代码
int main() {
    CLINT L1("12345678900987654321"), L2("9876543210"), L3;
    L3 = L1 + L2;
    cout << L3 << endl;    // 12345678910864197531
    
    // 额外测试用例
    CLINT A("99999999999999999999"), B("1"), C;
    C = A + B;
    cout << C << endl;     // 100000000000000000000
    
    CLINT D("123"), E("456"), F;
    F = D + E;
    cout << F << endl;     // 579
    
    return 0;
}
