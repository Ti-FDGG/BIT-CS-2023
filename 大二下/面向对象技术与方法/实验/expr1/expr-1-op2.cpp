#include <iostream>

using namespace std;

const int LEN = 10;

void myMemset(void *p, int val, size_t size) {
    /*
    description:
        自定义 memset 函数
    parameter:
        p: 待设置的内存地址
        val: 设置的值
        size: 设置的字节数
    */
    char *pchar = static_cast<char *>(p);
    for (size_t i = 0; i < size; i++) {
        pchar[i] = val;
    }
}

int main(void) {
    int arr[LEN] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    for (int i = 0; i < LEN; i++) {
        cout << arr[i] << " ";
    }
    cout << endl;

    void *parr = static_cast<void *>(arr);
    myMemset(parr, -1, sizeof(arr));
    for (int i = 0; i < LEN; i++) {
        cout << arr[i] << " ";
    }
    cout << endl;
    return 0;
}