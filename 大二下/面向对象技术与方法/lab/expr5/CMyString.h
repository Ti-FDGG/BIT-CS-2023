#ifndef CMYSTRING_H
#define CMYSTRING_H

#include <iostream>
using namespace std;

class CMyString
{
private:
    char* m_str; // 指向字符串的指针
    int m_len;
public:
    // 构造函数和析构函数
    CMyString(char c);
    CMyString(const char* str = "");
    ~CMyString();

    // 拷贝构造函数
    CMyString(const CMyString& str);

    // 迭代器
    class iterator {
    private:
        char* m_ptr; // 指向当前字符的指针
    public:
        iterator(char* ptr) : m_ptr(ptr) {}

        iterator& operator++() {
            m_ptr++;
            return *this;
        }
        iterator operator++(int) {
            iterator temp = *this;
            m_ptr++;
            return temp;
        }
        iterator& operator--() {
            m_ptr--;
            return *this;
        }
        iterator operator--(int) {
            iterator temp = *this;
            m_ptr--;
            return temp;
        }
        char& operator*() {
            return *m_ptr;
        }
        bool operator!=(const iterator& it) const {
            return m_ptr != it.m_ptr;
        }
        bool operator==(const iterator& it) const {
            return m_ptr == it.m_ptr;
        }
    };

    iterator begin() {
        return iterator(m_str);
    }
    iterator end() {
        return iterator(m_str + m_len);
    }

    const iterator begin() const {
        return iterator(m_str);
    }
    const iterator end() const {
        return iterator(m_str + m_len);
    }

    int test() const {
        return m_len;
    }
    // 运算符重载
    CMyString& operator=(const CMyString& str);
    char& operator[](int index);
    friend CMyString operator+(const CMyString& str1, const CMyString& str2);
    CMyString& operator+=(const CMyString& str);
    CMyString& operator+=(char c);
    friend ostream& operator<<(ostream& os, const CMyString& str);
    bool operator==(const CMyString& str) const;


    // 其他成员函数
    int strlen() const;
    int Find(CMyString str, int startPos = 0);
    int rfind(CMyString str, int startPos = -1);
    CMyString Mid(int startPos, int len);
    int toInt() const;
};

#endif // CMYSTRING_H
