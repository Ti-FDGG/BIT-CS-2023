// g++ expr4-1.cpp CMyString.cpp -o expr4-1.exe && expr4-1

#include "CMyString.h"

int main(void)
{
    CMyString s1("BIT"), s2;
    s2 = "love";
    s2[0] = 'L';
    cout << "I " + s2 + " " << s1 << endl; // The output is: I Love BIT
    int pos = s1.Find('T'); // pos is 2, or -1 if 'T' is not found.
    cout << "The position of 'T' in BIT is: " << pos << endl; // The output is: The position of 'T' in BIT is: 2
    pos = s1.Find("IT"); // pos is 1, or -1 if "IT" is not found.
    cout << "The position of 'IT' in BIT is: " << pos << endl; // The output is: The position of 'IT' in BIT is: 1
    int startPos = 3, len = 4;
    s2 = "Welcome";
    // In Mid, startPos represents starting position, len represents the length of substring.
    CMyString myStr = s2.Mid(startPos, len); // myStr is "come"
    cout << "The substring of " << s2 << " from position " << startPos << " with length " << len << " is: " << myStr << endl; // The output is: The substring of Welcome from position 3 with length 4 is: come
    return 0;
}
