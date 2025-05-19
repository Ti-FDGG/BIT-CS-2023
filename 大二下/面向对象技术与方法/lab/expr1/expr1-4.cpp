#include <iostream>
// #include <string>

using namespace std;

class CDate {
    private:
    int year, month, day; // year, month, and day of the date
    public:
    // Constructor and Destructor
    CDate(int year = -1, int month = -1, int day = -1) : year(year), month(month), day(day) {}
    ~CDate(){}

    // Getter and Setter
    int GetYear() { return year; }
    int GetMonth() { return month; }
    int GetDay() { return day; }
    
    void SetYear(int year) { this->year = year; }
    void SetMonth(int month) { this->month = month; }
    void SetDay(int day) { this->day = day; }

    // Other Member Function
    string toString();
    int dateToDays();
    int Span(CDate &date2);
};

namespace DateUtils {
    bool isLeapYear(int year);
    int daysInMonth(int year, int month);
}

bool DateUtils::isLeapYear(int year) {
    /*
    description:
        check if the year is a leap year
    parameters:
        year: the year to be checked
    return:
        true if the year is a leap year, false otherwise
    */
    return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
}

int DateUtils::daysInMonth(int year, int month) {
    /*
    description:
        get the number of days in the month
    parameters:
        year: the year of the month
        month: the month to be checked
    return:
        the number of days in the month
    */
    static const int days[12] = {31, 28, 31, 30, 31, 30, 
                                31, 31, 30, 31, 30, 31};
    return days[month - 1] + (month == 2 && isLeapYear(year));
}


string CDate::toString() {
    /*
    description:
        get the string representation of the date
    return:
    the string representation of the date in the format "yyyy-mm-dd"
    */
   return to_string(year) + "-" + to_string(month) + "-" + to_string(day);
}

int CDate::dateToDays() {
    /*
    description:
        get the number of days from 0001-01-01 to the date
    return:
        the number of days from 0001-01-01 to the date
    */
    int days = 0;
    // Add the days of the past years
    days += (year - 1) * 365 + (year - 1) / 4 - 
        (year - 1) / 100 + (year - 1) / 400;
    // Add the days of the months of this year
    for (int i = 1; i < month; i++) {
        days += DateUtils::daysInMonth(year, i);
    }
    // Add the days of this month
    days += day;
    return days;
}

int CDate::Span(CDate &date2) {
    /*
    description:
        get the number of days between the two dates
    parameters:
        date2: the second date
    return:
        the number of days between the two dates
    */
    return abs(dateToDays() - date2.dateToDays());
}

int main(void) {
    CDate date1(2025, 2, 24), date2(2025, 3, 12);
    int spanDays = date1.Span(date2);
    cout << "The number of days between " << date1.toString() << " and " << date2.toString() << " is " << spanDays << endl;
    return 0;
}