#include <iostream>
#include <cmath>
#include <string>

using namespace std;

const double PI = 3.14159265358979323846;

class CCircle {
    private:
    double radius; // radius of the circle
    double x, y; // x and y coordinates of the center

    public:
    // Constructor and Destructor
    CCircle(double radius = 0, double x = 0, double y = 0) : radius(radius), x(x), y(y) {}
    ~CCircle(){}

    // Getter and Setter
    double GetRadius() { return radius; }
    double GetX() { return x; }
    double GetY() { return y; }

    void SetRadius(double radius) { this->radius = radius; }
    void SetX(double x) { this->x = x; }
    void SetY(double y) { this->y = y; }

    // Other Member Function
    double Area();
    double Circumference();
    double Distance(CCircle &c2);
    string Relationship(CCircle &c2);
};

double CCircle::Area() {
    /*
    description:
        calculate the area of the circle
    return:
        the area of the circle
    */
    return PI * radius * radius;
}

double CCircle::Circumference() {
    /*
    description:
        calculate the circumference of the circle
    return:
        the circumference of the circle
    */
    return 2 * PI * radius;
}

double CCircle::Distance(CCircle &c2) {
    /*
    description:
        calculate the distance between the two circles
    parameters:
        c2: the circle to be calculated the distance
    return:
        the distance between the two circles
    */
    return sqrt((x - c2.x) * (x - c2.x) + (y - c2.y) * (y - c2.y));
}

string CCircle::Relationship(CCircle &c2) {
    /*
    description:
        calculate the relationship between the two circles
    parameters:
        c2: the circle to be calculated the relationship
    return:
        the relationship between the two circles
    */
    double dis = Distance(c2);
    if (dis == 0 && radius == c2.radius) return "coincide";
    else if (dis == 0 && radius != c2.radius) return "concentric";
    else if (dis == radius + c2.radius) return "externally tangent";
    else if (dis == abs(radius - c2.radius)) return "internally tangent";
    else if (dis < radius + c2.radius) return "intersect";
    else return "disjoint";
}

int main(void) {
    CCircle c1(3);
    CCircle c2(2, 4, 3);
    cout << "The relationship is " << c1.Relationship(c2) << endl;
    return 0;
}

