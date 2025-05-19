#include "expr3-3.cpp"
#include <sstream>
#include <cctype>

using namespace std;

class CExpression {
private:
    string expression;  // 存储表达式字符串
    CMyStack* operatorStack;  // 运算符栈
    CMyStack* numberStack;    // 数字栈
    
    // 辅助函数
    bool isOperator(char ch) const;  // 判断是否为运算符
    int getPriority(char op) const;  // 获取运算符优先级
    double calculate(double a, double b, char op) const;  // 执行运算
    void processOperator(char op);  // 处理运算符
    double getNumber(const string& expr, int& pos);  // 从字符串中提取数字

public:
    CExpression(const string& expr = "");  // 构造函数
    ~CExpression();  // 析构函数
    
    void SetExpression(const string& expr);  // 设置表达式
    double Value();  // 计算表达式值
    
    // 输出运算符重载
    friend ostream& operator<<(ostream& os, const CExpression& expr);
};

// 构造函数
CExpression::CExpression(const string& expr) {
    operatorStack = new CMyStack(100);  // 假设表达式不会超过100个运算符
    numberStack = new CMyStack(100);    // 假设表达式不会超过100个数字
    SetExpression(expr);
}

// 析构函数
CExpression::~CExpression() {
    delete operatorStack;
    delete numberStack;
}

// 设置表达式
void CExpression::SetExpression(const string& expr) {
    expression = expr;
}

// 判断是否为运算符
bool CExpression::isOperator(char ch) const {
    return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '(' || ch == ')';
}

// 获取运算符优先级
int CExpression::getPriority(char op) const {
    switch (op) {
        case '+': case '-': return 1;
        case '*': case '/': return 2;
        case '(': case ')': return 0;
        default: return -1;
    }
}

// 执行运算
double CExpression::calculate(double a, double b, char op) const {
    switch (op) {
        case '+': return b + a;
        case '-': return b - a;
        case '*': return b * a;
        case '/': return b / a;
        default: return 0;
    }
}

// 从字符串中提取数字
double CExpression::getNumber(const string& expr, int& pos) {
    string num;
    while (pos < expr.length() && (isdigit(expr[pos]) || expr[pos] == '.')) {
        num += expr[pos++];
    }
    return stod(num);
}

// 处理运算符
void CExpression::processOperator(char op) {
    if (op == '(') {
        operatorStack->Push(op);
        return;
    }
    
    if (op == ')') {
        while (!operatorStack->isEmpty() && operatorStack->Peek() != '(') {
            char currOp = operatorStack->Pop();
            double a = stod(string(1, numberStack->Pop()));
            double b = stod(string(1, numberStack->Pop()));
            double result = calculate(a, b, currOp);
            numberStack->Push(to_string(result)[0]);
        }
        if (!operatorStack->isEmpty()) {
            operatorStack->Pop();  // 弹出 '('
        }
        return;
    }
    
    while (!operatorStack->isEmpty() && 
           getPriority(operatorStack->Peek()) >= getPriority(op)) {
        char currOp = operatorStack->Pop();
        double a = stod(string(1, numberStack->Pop()));
        double b = stod(string(1, numberStack->Pop()));
        double result = calculate(a, b, currOp);
        numberStack->Push(to_string(result)[0]);
    }
    operatorStack->Push(op);
}

// 计算表达式值
double CExpression::Value() {
    // 清空栈
    operatorStack->Clear();
    numberStack->Clear();
    
    for (int i = 0; i < expression.length(); i++) {
        if (isspace(expression[i])) continue;
        
        if (isdigit(expression[i]) || expression[i] == '.') {
            double num = getNumber(expression, i);
            numberStack->Push(to_string(num)[0]);
            continue;
        }
        
        if (isOperator(expression[i])) {
            processOperator(expression[i]);
        }
    }
    
    // 处理剩余的运算符
    while (!operatorStack->isEmpty()) {
        char op = operatorStack->Pop();
        double a = stod(string(1, numberStack->Pop()));
        double b = stod(string(1, numberStack->Pop()));
        double result = calculate(a, b, op);
        numberStack->Push(to_string(result)[0]);
    }
    
    return stod(string(1, numberStack->Pop()));
}

// 输出运算符重载
ostream& operator<<(ostream& os, const CExpression& expr) {
    os << expr.expression;
    return os;
}

// 测试代码
int main() {
    CExpression expr("50.3-20.12+8*8/2");
    cout << expr << " = " << expr.Value() << endl;      // 50.3-20.12+8*8/2 = 62.18
    
    expr.SetExpression("55.99-88.11+77.12");
    cout << expr << " = " << expr.Value() << endl;      // 55.99-88.11+77.12 = 45
    
    expr.SetExpression("(39+11)*30+10/5");
    cout << expr << " = " << expr.Value() << endl;     // (39+11)*30+10/5 = 1502
    
    expr.SetExpression("39+12*(47+33)");
    cout << expr << " = " << expr.Value() << endl;     // 39+12*(47+33) = 999
    
    expr.SetExpression("20/(112-(10*1.2))/10-1.01");
    cout << expr << " = " << expr.Value() << endl;     // 20/(112-(10*1.2))/10-1.01 = -0.99
    
    cout << "ENDING..." << endl;
    return 0;
}
