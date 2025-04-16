// g++ expr5-op1.cpp CMyString.cpp -o expr5-op1.exe && expr5-op1

#include <iostream>
#include "CMyString.h"

using namespace std;

int main()
{
    CMyString str("I Love BIT");
    cout << "Use iterator to print a string with FOR statement." << endl;
    for (char ch : str)
        cout << ch << ' ';
    cout << endl;
    return 0;
}