#include <iostream>
#include <fstream>
#include <vector>
#include <string> 
// 由于fstream不支持CMyString，所以这里被迫使用STL中的string，相关的line变量，file.open()、getline()、ParseCSVLine()等函数也使用STL中的string

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

CMyString& CMyString::operator+=(const CMyString& str) {
    *this = *this + str;
    return *this;
}

CMyString& CMyString::operator+=(char c) {
    CMyString temp(c);
    *this = *this + temp;
    return *this;
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

int CMyString::toInt() const {
    int result = 0;
    for (int i = 0; i < m_len; i++) {
        if (m_str[i] >= '0' && m_str[i] <= '9') {
            result = result * 10 + (m_str[i] - '0');
        } else {
            return -1; // 非数字字符，返回-1
        }
    }
    return result;
}   

// 前向声明
class Student;
class Teacher;
class Course;
class Score;

template <typename T>
class Table {
    private:
        vector<T> data;

        void ParseCSVLine(string line, vector<CMyString>& iniList); // 解析CSV文件的每一行
    public:
        Table() {} // 默认构造函数
        Table(string csvAddress) { // 从CSV的构造函数
            int result = InitializeByCSV(csvAddress);
            if (result != 0) {
                cerr << "Error initializing table: " << result << endl;
            }
        }
        ~Table() {}

        // 迭代器
        class iterator {
            private:
                typename vector<T>::iterator it;
            public:
                iterator(typename vector<T>::iterator it) : it(it) {}
                iterator& operator++() { ++it; return *this; }
                iterator operator++(int) { iterator temp = *this; ++it; return temp; }
                T& operator*() { return *it; }
                bool operator!=(const iterator& other) const { return it != other.it; }
                bool operator==(const iterator& other) const { return it == other.it; }
        };

        iterator begin() { return iterator(data.begin()); }
        iterator end() { return iterator(data.end()); }

        // 运算符重载

        T& operator[](int index) { return data[index]; } // 下标运算符重载

        // 成员函数

        int InitializeByCSV(string csvAddress); // 用CSV初始化表格数据
};

// 解析CSV文件的每一行
template <typename T>
void Table<T>::ParseCSVLine(string line, vector<CMyString>& iniList) {
    CMyString cell;
    for (int i = 0; i < line.length(); i++) {
        if (line[i] == ',') {
            iniList.push_back(cell);
            cell = "";
        } else {
            cell += line[i];
        }
    }
    iniList.push_back(cell); // 添加最后一个值
}

template <typename T>
int Table<T>::InitializeByCSV(string csvAddress) {
    fstream file;
    try
    {
        file.open(csvAddress, ios::in); // 以只读方式打开文件
    } catch (const std::exception& e) {
        cerr << e.what() << '\n';
        return -1; // 文件打开失败
    }
    string line;
    getline(file, line); // 读取第一行，跳过表头
    while (getline(file, line)) {
        vector<CMyString> iniList;
        ParseCSVLine(line, iniList);
        T item;
        try
        {
            item = T(iniList);
            data.push_back(item);
        }
        catch(const std::exception& e)
        {
            cerr << e.what() << '\n';
            return -2; // 元组初始化失败
        }
         
    }
    file.close();
    
    return 0; // 成功
}

class Person {
    protected: // 设置成 protected 以便子类访问
        CMyString name;
        CMyString gender;
        CMyString birth_Y_M;
    public:
        Person() {}
        Person(CMyString name, CMyString g, CMyString birth_Y_M) 
            : name(name), gender(g), birth_Y_M(birth_Y_M) {}
};

class Student : public Person {
    private:
        CMyString SID; // 主键
    public:
        Student() {}
        Student(CMyString name, CMyString g, CMyString birth_Y_M, int SID) 
            : Person(name, g, birth_Y_M), SID(SID) {}
        // 从vector<CMyString> iniList中初始化
        Student(vector<CMyString> iniList) 
            : Person(iniList[0], iniList[1], iniList[2]), SID(iniList[3]) {}
        ~Student() {}

        bool operator==(const Student& other) const {
            return SID == other.SID; // 比较主键（学号）是否相同
        }

        // 成员函数

        CMyString GetSID() const { return SID; } // 获取学号

        CMyString GetTeacherByCourse(Table<Course>& courseTable, Course& course);
        int GetScoreByCourse(Table<Score>& scoreTable, Course& course);
};

class Teacher : public Person {
    private:
        CMyString TID; // 主键
    public:
        Teacher() {}
        Teacher(CMyString name, CMyString g, CMyString birth_Y_M, CMyString TID) 
            : Person(name, g, birth_Y_M), TID(TID) {}
        // 从vector<CMyString> iniList中初始化
        Teacher(vector<CMyString> iniList) 
            : Person(iniList[0], iniList[1], iniList[2]), TID(iniList[3]) {}
        ~Teacher() {}

        bool operator==(const Teacher& other) const {
            return TID == other.TID; // 比较主键（教师 ID）是否相同
        }

        // 成员函数

        CMyString GetTID() const { return TID; } // 获取教师 ID

        Student GetStudInfoBySID(Table<Student>& studentTable, CMyString SID);
        void SetScore2Course(Table<Score>& scoreTable, Student& student, Course& course, int score);
};

class Course {
    private:
        CMyString Cno; // 主键
        CMyString Cname;
        CMyString TID;
    public:
        Course() {}
        Course(CMyString Cno, CMyString Cname, CMyString TID) : Cno(Cno), Cname(Cname), TID(TID) {}
        // 从vector<CMyString> iniList中初始化
        Course(vector<CMyString> iniList) 
            : Cno(iniList[0]), Cname(iniList[1]), TID(iniList[2]) {}
        ~Course() {}

        // 运算符重载

        bool operator==(const Course& other) const {
            return Cno == other.Cno; // 比较主键（课程 ID）是否相同
        }

        // 成员函数
        CMyString GetCno() const { return Cno; } // 获取课程 ID
        CMyString GetTID() const { return TID; } // 获取教师 ID
};

class Score {
    private:
        CMyString SID;
        CMyString Cno;
        int score;
    public:
        Score() {}
        Score(CMyString SID, CMyString Cno, int score) : SID(SID), Cno(Cno), score(score) {}
        // 从vector<CMyString> iniList中初始化
        Score(vector<CMyString> iniList) 
            : SID(iniList[0]), Cno(iniList[1]), score(iniList[2].toInt()) {}
        ~Score() {}

        // 成员函数

        CMyString GetSID() const { return SID; } // 获取学号
        CMyString GetCno() const { return Cno; } // 获取课程 ID
        int GetScore() const { return score; } // 获取成绩

        void SetScore(int newScore) { score = newScore; } // 设置成绩
};

// 学生根据课程名称，查询授课教师
// 返回值为教师 ID（课程表格外键，教师表格主键）
CMyString Student::GetTeacherByCourse(Table<Course>& courseTable, Course& course) {
    CMyString teacherID;
    for (auto& c : courseTable) {
        if (c == course) {
            teacherID = c.GetTID();
            return teacherID;
        }
    }
    throw runtime_error("Course not found"); // 如果未找到课程，抛出异常
}

// 学生根据课程名称和学号，查询课程成绩
// 返回值为课程成绩
int Student::GetScoreByCourse(Table<Score>& scoreTable, Course& course) {
    for (auto& s : scoreTable) {
        if (s.GetCno() == course.GetCno() && s.GetSID() == this->SID) {
            int score = s.GetScore();
            return score;
        }
    }
    throw runtime_error("Student's course not found"); // 如果未找到成绩，抛出异常
}

// 教师通过学号 SID，查询学生信息（姓名、性别和年龄）
// 返回值为学生对象
Student Teacher::GetStudInfoBySID(Table<Student>& studentTable, CMyString SID) {
    for (auto& s : studentTable) {
        if (s.GetSID() == SID) {
            return s; // 返回学生对象
        }
    }
    throw runtime_error("Student not found"); // 如果未找到学生，抛出异常
}

// 教师根据课程 ID，给出课程的学生成绩
void Teacher::SetScore2Course(Table<Score>& scoreTable, Student& student, Course& course, int score) {
    for (auto& s : scoreTable) {
        if (s.GetCno() == course.GetCno() && s.GetSID() == student.GetSID()) {
            s.SetScore(score); // 设置成绩
            return;
        }
    }
    throw runtime_error("Student's course not found"); // 如果未找到成绩，抛出异常
}


int main(void) {
    // 初始化表格数据
    Table<Student> studentTable("data/students.csv");
    Table<Teacher> teacherTable("data/teachers.csv");
    Table<Course> courseTable("data/courses.csv");
    Table<Score> scoreTable("data/scores.csv");

    // 测试
    Student s0 = studentTable[0];
    Teacher t0 = teacherTable[0];
    Course c0 = courseTable[0];

    CMyString teacherID = s0.GetTeacherByCourse(courseTable, c0); // 学生根据课程名称，查询授课教师
    cout << "Student " << s0.GetSID() << " teacher ID in course " << c0.GetCno() << ": " << teacherID << endl;

    int s0score = s0.GetScoreByCourse(scoreTable, c0); // 学生根据课程名称和学号，查询课程成绩
    cout << "Student " << s0.GetSID() << " score in course " << c0.GetCno() << ": " << s0score << endl;

    Student t0s0 = t0.GetStudInfoBySID(studentTable, s0.GetSID()); // 教师通过学号 SID，查询学生信息（姓名、性别和年龄）
    cout << "Teacher " << t0.GetTID() << " get student info by SID " << s0.GetSID() << ": " 
         << t0s0.GetSID() << ", " << t0s0.GetTeacherByCourse(courseTable, c0) << ", " 
         << t0s0.GetScoreByCourse(scoreTable, c0) << endl;
        
    t0.SetScore2Course(scoreTable, t0s0, c0, 100); // 教师根据课程 ID，给出课程的学生成绩
    cout << "Teacher " << t0.GetTID() << " set score for student " << t0s0.GetSID() << " in course " << c0.GetCno() << ": 100" << endl;
    cout << "Student " << t0s0.GetSID() << " score in course " << c0.GetCno() << " now is: " 
         << t0s0.GetScoreByCourse(scoreTable, c0) << endl;
    
    return 0;
}