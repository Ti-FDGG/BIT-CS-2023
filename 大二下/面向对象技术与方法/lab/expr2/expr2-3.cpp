#include <iostream>
#include <string>
#include <bitset>

using namespace std;

class CIntChar {
private:
    int intchar = 0; // 用整数存储最多4个字符

public:
    // Constructor
    CIntChar(string s) {
        for (size_t i = 0; i < 4; ++i) {
            intchar <<= 8; // 左移 8 位
            if (i < s.size()) {
                intchar |= static_cast<unsigned char>(s[i]); // 按位或以存储字符
            }
        }
    }

    // Other Member Functions
    void ASC_Print();
    void Binary_Print();
    void Int_Print();
    char At(int index);
    string str();
};

void CIntChar::ASC_Print() {
    /*
    description: 
        Print the string stored in intchar
    */
    for (int i = 3; i >= 0; --i) {
        char c = static_cast<char>((intchar >> (i * 8)) & 0xFF);
        if (c != '\0') cout << c; // 输出非空字符，同时兼容长度不足4位的情况
    }
    cout << endl;
}

void CIntChar::Binary_Print() {
    /*
    description:
        Print the binary representation of intchar
    */
    bitset<32> bin(intchar);
    string binStr = bin.to_string();
    for (size_t i = 0; i < binStr.size(); i += 8) {
        cout << binStr.substr(i, 8) << " ";
    }
    cout << endl;
}

void CIntChar::Int_Print() {
    /*
    description:
        Print the integer value of intchar
    */
    cout << intchar << endl;
}

char CIntChar::At(int index) {
    /*
    description:
        Get the character at the specified index
    param:
        index: the index of the character
    return: 
        the character at the specified index
    */
    if (index < 0 || index >= 4) return '\0';
    return static_cast<char>((intchar >> ((3 - index) * 8)) & 0xFF);
}

string CIntChar::str() {
    /*
    description:
        Get the string stored in intchar
    return:
        the string stored in intchar
    */
    string result;
    for (int i = 3; i >= 0; --i) {
        char c = static_cast<char>((intchar >> (i * 8)) & 0xFF);
        if (c != '\0') result += c;
    }
    return result;
}

int main() {
    CIntChar IC("Love");
    IC.ASC_Print();        // 输出: Love
    IC.Binary_Print();     // 输出: 01001100 01101111 01110110 01100101
    IC.Int_Print();        // 输出: 1282373221
    cout << IC.At(3) << endl;  // 输出: v
    cout << IC.str() << endl;  // 输出: Love
    return 0;
}
