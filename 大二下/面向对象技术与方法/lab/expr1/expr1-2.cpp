#include <iostream>

using namespace std;

void DrawTriangle(int row)
/*
    description: 
        draw a triangle with row rows
    parameters: 
        row: numbers of triangle's row
*/
{
    for (int i = 0; i < row; i++)
    {
        for (int j = 0; j < row - i - 1; j++)
        {
            cout << " ";
        }

        for (int j = 0; j < 2 * i + 1; j++)
        {
            cout << "*";
        }
        cout << endl;
    }
}

void DrawSquare(int row)
/*
    description: 
        draw a square with row rows
    parameters: 
        row: numbers of square's row
*/
{
    for (int i = 0; i < row; i++)
    {
        cout << "+";
    }
    cout << endl;

    for (int i = 0; i < row - 2; i++)
    {
        cout << "+";
        for (int j = 0; j < row - 2; j++)
        {
            cout << " ";
        }
        cout << "+";
        cout << endl;
    }

    for (int i = 0; i < row; i++)
    {
        cout << "+";
    }
    cout << endl;
}

void Draw(void (*DrawShape)(int), int row)
/*
    description: 
        draw a shape with row rows
    parameters: 
        DrawShape: a function pointer to draw a shape
        row: numbers of shape's row
*/
{
    DrawShape(row);
}

int main(void)
{
    int row;    // numbers of shape's row
    cin >> row;
    Draw(DrawTriangle, row);
    cout << endl << endl;
    Draw(DrawSquare, row);
    return 0;
}