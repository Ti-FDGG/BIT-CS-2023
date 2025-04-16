#include "CMyString.h"
#include <iostream>

using namespace std;

// 构造函数和析构函数
CMyString::CMyString(char c) {
    m_len = 1; // 单个字符长度为1
    m_str = new char[m_len + 1];
    m_str[0] = c;
    m_str[1] = '\0';
}

CMyString::CMyString(const char* str) {
    if (str) {
        m_len = 0;
        while (str[m_len] != '\0') {
            m_len++;
        }
        m_str = new char[m_len + 1];
        for (int i = 0; i < m_len; i++) {
            m_str[i] = str[i];
        }
        m_str[m_len] = '\0';
    } else {
        m_len = 0;
        m_str = new char[1];
        m_str[0] = '\0';
    }
}

CMyString::~CMyString() {
    delete[] m_str;
}

// 拷贝构造函数
CMyString::CMyString(const CMyString& str) {
    m_len = str.m_len;
    m_str = new char[m_len + 1];
    for (int i = 0; i < m_len; i++) {
        m_str[i] = str.m_str[i];
    }
    m_str[m_len] = '\0';
}

// 运算符重载
CMyString& CMyString::operator=(const CMyString& str) {
    if (this != &str) {
        delete[] m_str; // Free the old memory
        m_len = str.m_len;
        m_str = new char[m_len + 1]; // Allocate new memory
        for (int i = 0; i < m_len; i++) {
            m_str[i] = str.m_str[i];
        }
        m_str[m_len] = '\0';
    }
    return *this;
}

char& CMyString::operator[](int index) {
    return m_str[index];
}

CMyString operator+(const CMyString& str1, const CMyString& str2) {
    int len1 = str1.m_len;
    int len2 = str2.m_len;

    char* newStr = new char[len1 + len2 + 1];
    for (int i = 0; i < len1; i++) {
        newStr[i] = str1.m_str[i];
    }
    for (int i = 0; i < len2; i++) {
        newStr[len1 + i] = str2.m_str[i];
    }
    newStr[len1 + len2] = '\0';

    return CMyString(newStr);
}

ostream& operator<<(ostream& os, const CMyString& str) {
    os << str.m_str;
    return os;
}

bool CMyString::operator==(const CMyString& str) const {
    if (m_len != str.m_len) {
        return false; // 长度不同，直接返回false
    }
    for (int i = 0; i < m_len; i++) {
        if (m_str[i] != str.m_str[i]) {
            return false; // 有字符不同，返回false
        }
    }
    return true; // 所有字符都相同，返回true
}

// 其他成员函数

// 计算字符串长度
// 返回字符串长度，不包括结束符'\0'
// str: 要计算长度的字符串
int CMyString::strlen() const {
    return m_len; // 直接返回 m_len
}

// 从指定位置开始查找子串
// 返回子串在字符串中的起始位置，未找到返回-1
// str: 要查找的子串
// startPos: 起始位置，默认值为0，表示从字符串开头开始查找
int CMyString::Find(CMyString str, int startPos) {
    if (startPos < 0 || startPos >= m_len) {
        return -1; // 起始位置越界
    }
    int len2 = str.m_len;
    for (int i = startPos; i <= m_len - len2; i++) {
        int j;
        for (j = 0; j < len2; j++) {
            if (m_str[i + j] != str.m_str[j]) {
                break;
            }
        }
        if (j == len2) {
            return i; // 返回找到的起始位置
        }
    }
    return -1; // 未找到
}

// 从指定位置向前查找子串
// 返回子串在字符串中的起始位置，未找到返回-1
// str: 要查找的子串
// startPos: 起始位置。若为负值，则表示从倒数第 |startPos| 个字符开始查找，若为正值，则表示从第 startPos 个字符开始查找。默认值为-1，表示从字符串末尾开始查找
int CMyString::rfind(CMyString str, int startPos) {
    if (startPos >= m_len) {
        return -1; // 起始位置越界
    }
    if (startPos < 0) {
        startPos = m_len + startPos; // 将负值转换为正值
    }
    int len2 = str.m_len;
    for (int i = startPos; i >= 0; i--) {
        int j;
        for (j = 0; j < len2; j++) {
            if (i + j >= m_len || m_str[i + j] != str.m_str[j]) {
                break;
            }
        }
        if (j == len2) {
            return i; // 返回找到的起始位置
        }
    }
    return -1; // 未找到
}

// 从指定位置开始截取字符串
// 返回截取的字符串
// 如果起始位置越界或长度为负数，则返回空字符串
// startPos: 起始位置
// len: 截取的长度
CMyString CMyString::Mid(int startPos, int len) {
    if (startPos < 0 || startPos >= m_len || len <= 0) {
        return CMyString(); // 返回空字符串
    }
    if (startPos + len > m_len) {
        len = m_len - startPos; // 调整长度以避免越界
    }
    char* newStr = new char[len + 1];
    for (int i = 0; i < len; i++) {
        newStr[i] = m_str[startPos + i];
    }
    newStr[len] = '\0'; // 添加字符串结束符

    return CMyString(newStr);
}