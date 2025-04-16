#ifndef CMYSTRING_H
#define CMYSTRING_H

#include <iostream>
using namespace std;

class CMyString
{
private:
    char* m_str;
    int m_len;
public:
    // 构造函数和析构函数
    CMyString(char c);
    CMyString(const char* str = "");
    ~CMyString();

    // 拷贝构造函数
    CMyString(const CMyString& str);

    // 运算符重载
    CMyString& operator=(const CMyString& str);
    char& operator[](int index);
    friend CMyString operator+(const CMyString& str1, const CMyString& str2);
    friend ostream& operator<<(ostream& os, const CMyString& str);
    bool operator==(const CMyString& str) const;

    // 其他成员函数
    int strlen() const;
    int Find(CMyString str, int startPos = 0);
    int rfind(CMyString str, int startPos = -1);
    CMyString Mid(int startPos, int len);
};

#endif // CMYSTRING_H
