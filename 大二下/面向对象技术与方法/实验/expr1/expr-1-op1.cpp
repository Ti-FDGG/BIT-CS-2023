#include <iostream>
#include <fstream>
#include <cctype>
#include <codecvt>
#include <windows.h>
#include <string>
#include <map>
#include <vector>

using namespace std;

int countCharInFile(const string& filename) {
    /*
    description: 
        统计文件中的字符数
    parameter:
        filename: 文件名
    return:
        -1: 文件打开失败
        >=0: 文件中的字符数
    */
    wifstream file(filename); // 使用宽字符流
    file.imbue(locale(file.getloc(), new codecvt_utf8<wchar_t>)); // 设置 UTF-8 编码

    if (!file.is_open()) {
        cerr << "Can't open file: " << filename << endl;
        return -1;
    }

    wchar_t ch;
    int charCount = 0;
    while (file.get(ch)) {
        if (!iswspace(ch)) {
            charCount++;
        }
    }

    file.close();
    return charCount;
}

int countWordFrequencyInFile(const string& filename, string word) {
    /*
    description: 
        统计文件中某个单词的频率
    parameter:
        filename: 文件名
        word: 待统计的单词
    return:
        -1: 文件打开失败
        >=0: 单词的频率
    */
    ifstream file(filename, ios::in);
    if (!file.is_open()) {
        cerr << "Can't open file: " << filename << endl;
        return -1;
    }

    string line;
    int wordCount = 0;
    while (getline(file, line)) {
        size_t pos = 0; // 设置pos并在每次循环更新pos，而不是每次都从0开始而导致重复（find的参数pos的默认值为0）
        while ((pos = line.find(word, pos)) != string::npos) {
            wordCount++;
            pos += word.length();
        }
    }

    file.close();
    return wordCount;
}

int main() {
    // 设置控制台输出编码为 UTF-8
    SetConsoleOutputCP(CP_UTF8);

    string filename = "Chapter5InJourneyToWest.txt";
    int charCount = countCharInFile(filename);
    if (charCount != -1) {
        cout << "The number of words in the file: " << charCount << endl;
    }

    vector<string> words = {"大王","玉帝","七仙女","大圣"};
    string strwords = "{大王,玉帝,七仙女,大圣}";
    int maxFrequency = 0;
    string maxWord;
    for (auto word : words) {
        int count = countWordFrequencyInFile(filename, word);
        if (count != -1 ) {
            cout << "The frequency of " << word << " in the file: " << count << endl;
            if (count > maxFrequency) {
                maxFrequency = count;
                maxWord = word;
            }
        }
    }
    cout << "The very important person in " << strwords << " is: " << maxWord << endl;
    return 0;
}
