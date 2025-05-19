// g++ expr4-2.cpp CMyString.cpp -o expr4-2.exe && expr4-2

#include <iostream>
#include "CMyString.h"

using namespace std;

class CInternetURL : public CMyString
{
private:
    CMyString m_strDomain; // 域名
    CMyString m_strDomainCountry; // 域名国家
    CMyString m_strDomainType; // 域名类型
    CMyString m_strHomePage; // 首页

    // 解析URL
    void ParseURL();
public:
    CInternetURL(const char* str = "") : CMyString(str) {
        ParseURL(); // 解析URL
    }
    ~CInternetURL() {}

    CMyString GetDomain() const { return m_strDomain; }
    CMyString GetDomainCountry() const { return m_strDomainCountry; }
    CMyString GetDomainType() const { return m_strDomainType; }
    CMyString GetHomePage() const { return m_strHomePage; }
};

void CInternetURL::ParseURL() {
    int posScheme = Find("://"); // 用于定位协议部分
    int posDomain = Find("/", posScheme + 3); // 用于定位域名部分和首页部分
    CMyString strDomain = Mid(posScheme + 3, posDomain - posScheme - 3); // 提取域名部分
    int posFirstDot = strDomain.Find("."); // 用于定位域名中第一个点的位置
    int posLastDot = strDomain.rfind("."); // 用于定位域名中最后一个点的位置
    int posLast2ndDot = strDomain.rfind(".", posLastDot - 1); // 用于定位域名中倒数第二个点的位置

    m_strDomain = "www." + strDomain.Mid(posFirstDot + 1, strDomain.strlen() - posFirstDot - 1); // 提取域名
    m_strDomainCountry = strDomain.Mid(posLastDot + 1, strDomain.strlen() - posLastDot - 1); // 提取域名类型
    m_strDomainType = strDomain.Mid(posLast2ndDot + 1, posLastDot - posLast2ndDot - 1); // 提取域名国家
    m_strHomePage = Mid(posDomain + 1, strlen() - posDomain - 1); // 提取首页部分
    if (m_strHomePage == "") {
        m_strHomePage = "index.htm"; // 如果没有首页部分，则默认首页为index.htm
    }
}

int main()
{
    CInternetURL URL("https://jwc.bit.edu.cn/index.htm");
    cout << URL.GetDomain() << endl; // The result is: www.bit.edu.cn
    cout << URL.GetDomainCountry() << endl; // The result is: cn
    cout << URL.GetDomainType() << endl; // The result is: edu
    cout << URL.GetHomePage() << endl; // The result is: index.htm

    return 0;
}
