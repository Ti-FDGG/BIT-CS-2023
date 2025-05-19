// 没写完，懒得写了

#include <iostream>
#include "CMyString.h"

using namespace std;

class CSqlStatement : public CMyString {
private:
    CMyString m_sql;
public:
    CSqlStatement() : m_sql("") {}
    CSqlStatement(const char* sql) : m_sql(sql) {}
    CSqlStatement(CMyString sql) : m_sql(sql) {}

    void SetAttribute(CMyString index, CMyString value)
    {
        if (index == nullptr || value == nullptr)
            throw "Errors in setting attribution";
        cout << "SetAttribute: " << index << " = " << value << endl;
    }
    void ExecuteSql()
    {
        cout << "Executing SQL: " << m_sql << endl;
    }
};

int main()
{
    CMyString str = "Hello, World!";
    // 注意下面一行是初始化语句，因此需要一个自定义的构造函数而不是赋值运算符
    CSqlStatement sql = "select ?, ? from student where SID = ?"; //假定：这条 sql 语句没有错误
    sql.SetAttribute("1", "Name");
    sql.SetAttribute("2", "Age");
    sql.SetAttribute("3", "2020007"); // 如果：sql.SetAttribute("3", "abc || 2023 == 2023");
    // 这时，成员函数应抛出异常：Errors in setting attribution
    sql.ExecuteSql( );
    return 0;
}