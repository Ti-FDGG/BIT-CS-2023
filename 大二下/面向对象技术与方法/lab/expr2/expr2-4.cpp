#include <iostream>
#include <ctime>
#include <iomanip> 
#include <thread>
#include <chrono>
#include <windows.h>

using namespace std;

class CDateTime {
private:
    tm timeInfo;
    // 私有构造函数，防止直接创建对象
    CDateTime(tm t) : timeInfo(t) {}

public:
    static CDateTime Now();
    void ShowTime12() const;
    void ShowTime24() const;
    void ShowDate() const;
    void ShowTimeDynamically();

};

// 静态方法获取当前时间
CDateTime CDateTime::Now(){
    time_t t = time(nullptr);
    tm localTime = *localtime(&t);
    return CDateTime(localTime);
}

// 12 小时制时间显示（AM/PM）
void CDateTime::ShowTime12() const {
    int hour = timeInfo.tm_hour;
    string period = (hour >= 12) ? "pm" : "am";
    hour = (hour == 0) ? 12 : (hour > 12 ? hour - 12 : hour);

    cout << "当前时间（12小时制）："
                << hour << ":"
                << setw(2) << setfill('0') << timeInfo.tm_min << ":"
                << setw(2) << setfill('0') << timeInfo.tm_sec
                << " " << period << endl;
}

// 24 小时制时间显示
void CDateTime::ShowTime24() const {
    cout << "当前时间（24小时制）："
                << setw(2) << setfill('0') << timeInfo.tm_hour << ":"
                << setw(2) << setfill('0') << timeInfo.tm_min << ":"
                << setw(2) << setfill('0') << timeInfo.tm_sec
                << endl;
}

// 显示日期和星期
void CDateTime::ShowDate() const {
    const char* weekDays[] = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    cout << "当前日期："
                << (timeInfo.tm_year + 1900) << "年"
                << (timeInfo.tm_mon + 1) << "月"
                << timeInfo.tm_mday << "日，"
                << weekDays[timeInfo.tm_wday] << endl;
}

// 动态显示时间（每秒刷新）
void CDateTime::ShowTimeDynamically() {
    while (true) {
        // 获取当前时间
        CDateTime current = CDateTime::Now();

        // 清空控制台
        system("cls");

        // 显示当前时间
        cout << "当前时间（动态显示，24小时制）：" << endl;
        current.ShowTime24();

        // Windows 下暂停 1 秒（单位是毫秒）
        Sleep(1000);
    }
}

int main() {
    // 设置控制台为UTF-8编码
    SetConsoleOutputCP(CP_UTF8);

    CDateTime dt = CDateTime::Now();

    dt.ShowTime12();
    dt.ShowTime24();
    dt.ShowDate();
    cout << endl;
    cout << "动态显示时间（24小时制）：" << endl;
    dt.ShowTimeDynamically(); // 动态显示时间

    return 0;
}