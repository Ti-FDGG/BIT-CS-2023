from libs.Round import round_by_tolerence
from libs.MathExpr import MathExpression
from libs.Integral.NewtonCotesFormula import *
from libs.Integral.CompositeFormula import *
from libs.Integral.RombergMethod import *
from libs.Integral.GaussianFormula import *
from libs.Integral.MutipleIntegral import *

def demo():
    t = input("请输入数字：一重积分(1)，二重积分(2)：")
    f = MathExpression(input("请输入被积函数："))
    print("被积函数：", f.to_string())
    if (t == "1"):
        a = float(input("请输入积分下限："))
        b = float(input("请输入积分上限："))
        tolerence = float(input("请输入积分结果精度（如1e-6）："))
        method = input("请输入积分方法：Newton_Cotes(1)，Composite(2)，Romberg(3)，Gauss(4)：")
        if (method == "1"):
            method2 = input("请输入积分方法：Trapezoid(1)，Simpson(2)，Three_Divide_Eight(3)，Cotes(4), General_Newton_Cotes(5)：")
            if (method2 == "1"):
                I, R = trapezoid_formula(f, a, b)
            elif (method2 == "2"):
                I, R = simpson_formula(f, a, b)
            elif (method2 == "3"):
                I, R = three_divide_eight_formula(f, a, b)
            elif (method2 == "4"):
                I, R = cotes_formula(f, a, b)
            elif (method2 == "5"):
                nn = int(input("请输入n："))
                I, R = general_newton_cotes_formula(f, a, b, nn)
        elif (method == "2"):
            M = int(input("请输入分割数："))
            method2 = input("请输入积分方法：Trapezoid(1)，Simpson(2)，Three_Divide_Eight(3)，Cotes(4)：")
            if (method2 == "1"):
                I, R = composite_trapezoid_formula(f, a, b, M)
            elif (method2 == "2"):
                I, R = composite_simpson_formula(f, a, b, M)
            elif (method2 == "3"):
                I, R = composite_three_divide_eight_formula(f, a, b, M)
            elif (method2 == "4"):
                I, R = composite_cotes_formula(f, a, b, M)
        elif (method == "3"):
            I = romberg_method(f, a, b, tolerence)
            R = "龙贝格法暂无方法误差公式"
        elif (method == "4"):
            n = input("请输入n（2到7之间）：")
            I, R = gauss_formula(f, a, b, n)
        else:
            print("输入错误")
            return
        print("积分结果：", round_by_tolerence(I, tolerence))
        print("方法误差：", R)
    elif (t == "2"):
        a1 = float(input("请输入积分下限1："))
        b1 = float(input("请输入积分上限1："))
        a2 = float(input("请输入积分下限2："))
        b2 = float(input("请输入积分上限2："))
        bounds = [(a1, b1), (a2, b2)]
        m = int(input("请输入x方向的分段数："))
        n = int(input("请输入y方向的分段数："))
        tolerence = float(input("请输入积分结果精度（如1e-6）："))
        method = input("请输入积分方法：Mutiple_Composite_Simpson(1), Mutiple_Gauss(2)：")
        if (method == "1"):
            I = double_composite_simpson_formula(f, bounds, m, n)
            R = "重积分暂无方法误差公式"
        elif (method == "2"):
            I = double_gauss_formula(f, bounds, m, n)
            R = "重积分暂无方法误差公式"
        else:
            print("输入错误")
            return
        
        print("积分结果：", round_by_tolerence(I, tolerence))
        print("方法误差：", R)
    else:
        print("输入错误")
        return

if __name__ == '__main__':
    demo()

