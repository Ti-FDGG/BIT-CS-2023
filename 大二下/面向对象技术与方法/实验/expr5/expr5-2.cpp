// g++ expr5-2.cpp CMyString.cpp -o expr5-2.exe && expr5-2

#include <iostream>
#include "CMyString.h"

using namespace std;

class CStudent {
	private:
		CMyString name;
		int age;
	public:
		CStudent() : name(""), age(0) {} // 默认构造函数，需要手动添加（原有的被覆盖了）
		CStudent(CMyString name, int age) : name(name), age(age) {}
		~CStudent(){}
		
		CStudent(const CStudent& s) {
			name = s.name;
			age = s.age;
		}
		
		bool operator==(CStudent& stu2) {
			// 根据题目要求，这里的比较仅仅根据年龄
			return age == stu2.age;
		}
};

template <typename T>
class CList {
	private:
		T *p;
		int capacity;
		int num; // 改名字
	public:
		CList(int capacity = 50){
			p = new T[capacity];
			capacity = capacity;
		}
		~CList(){
			if (p != nullptr) {
				delete[] p;
			}
		}
		CList(CList& l) {
			p = l.p;
			capacity = l.capacity;
		}
		
		T& operator[](int i) {
			return p[i];
		}
		
		// 其他成员函数
		int Add(T& t);
		int Remove(int index); 
};

// 在列表末尾添加一个元素
template <typename T>
int CList<T>::Add(T& t) {
	p[num] = t;
	num++;
	return 1;
}

// 删除指定索引的元素
template <typename T>
int CList<T>::Remove(int index) {
	for (int i = index; i < num; i++) {
		p[i] = p[i+1];
	}
	num--;
	return 1;
}

int main() 
{
	CStudent s1("Joan", 22), s2("John", 19), s3("Joe", 22); 
	CList<CStudent> listStudent;
	listStudent.Add(s1); 
	listStudent.Add(s2); 
	listStudent.Add(s3); 
	listStudent.Remove(1); // 1 is the 2rd element index of listStudent 
	if (listStudent[0] == listStudent[1]) // If two students have same age. 
		cout << "Equal." << endl; 
	else 
		cout << "Not equal." << endl; 
	return 0; 
}
