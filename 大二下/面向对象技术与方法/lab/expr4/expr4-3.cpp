// g++ expr4-3.cpp CMyString.cpp -o expr4-3.exe && expr4-3
// 为什么Debit的取款费率不扣余额？
// 为什么Debit的消费不扣余额？

#include <iostream>
#include <windows.h>

#include "CMyString.h"

using namespace std;

class CAccount
{
    protected:
        int account; // 账号
        CMyString password; // 密码
        double balance; // 余额
    public:
        CAccount(int account, CMyString password, double balance) : account(account), password(password), balance(balance) {}
        virtual ~CAccount() {}
        virtual void Withdraw(double amount) = 0; // 取现
        virtual void Spending(double amount) = 0; // 消费
        friend ostream& operator<<(ostream& os, const CAccount& account) {
            os << "账户余额：" << account.balance;
            return os;
        }
        virtual void SetPassword(CMyString password) = 0; // 设置密码
};

// 借记卡（实际上题目给错了，Credit 应该是贷记卡/信用卡）
class CCreditAccount : public CAccount
{     
    public:
        CCreditAccount(int account, CMyString password, double balance) : CAccount(account, password, balance) {}
        void Withdraw(double amount) override;
        void Spending(double amount) override;
        void SetPassword(CMyString password) override; // 设置密码
};

void CCreditAccount::Withdraw(double amount) {
    if (amount > balance) {
        cout << "Fail to withdraw, insufficient balance!" << endl;
    } else {
        balance -= amount;
    }
}

void CCreditAccount::Spending(double amount) {
    if (amount > balance) {
        cout << "Fail to spend, insufficient balance!" << endl;
    } else {
        balance -= amount;
    }
}

void CCreditAccount::SetPassword(CMyString password) {
    password = password; // 设置密码
}

// 贷记卡/信用卡（实际上题目给错了，Debit 应该是借记卡），这里我将他理解为一个可以透支一定额度的可以存钱的卡，可能与现实不符
class CDebitAccount : public CAccount
{
    private:
        double overdraft = 10000; // 透支额度，假定为 10000 元
        double rate = 0.01; // 取现费率，假定为 1%
        double minWithdraw = 2; // 最低取现金额，假定为 2 元
    public:
        CDebitAccount(int account, CMyString password, double balance) : CAccount(account, password, balance) {}
        void Withdraw(double amount) override;
        void Spending(double amount) override;
        void SetPassword(CMyString password) override; // 设置密码
};

void CDebitAccount::Withdraw(double amount) {
    if (amount < minWithdraw) {
        cout << "Fail to withdraw, amount is less than minimum!" << endl;
        return;
    } 
    double totalAmount = amount + amount * rate; // 取现总额 = 取现金额 + 取现手续费
    if (totalAmount > balance + overdraft) {
        cout << "Fail to withdraw, insufficient balance!" << endl;
    } else {
        balance -= totalAmount; // 扣除取现总额
    }
}

void CDebitAccount::Spending(double amount) {
    if (amount > balance + overdraft) {
        cout << "Fail to spend, insufficient balance!" << endl;
    } else {
        balance -= amount;
    }
}

void CDebitAccount::SetPassword(CMyString password) {
    password = password; // 设置密码
}

int main()
{
    // 设置控制台输出编码为 UTF-8
    SetConsoleOutputCP(CP_UTF8);

    CCreditAccount credit(1061, "123456", 500); // 借记卡：账号、密码（字符串）、余额
    CDebitAccount debit(9032, "654321", 3000); // 贷记卡：账号、密码（字符串）、透支额度
    credit.Withdraw(200); // 取现
    cout << credit << endl; // 账户余额：300
    debit.Withdraw(200); // 取现，费率：1%，最低 2 元 / 笔
    debit.Spending(200); // 消费
    cout << debit << endl; // 账户余额：2800

    return 0;
}
