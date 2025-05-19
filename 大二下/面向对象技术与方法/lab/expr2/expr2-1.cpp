#include <iostream>
#include <stdexcept>

using namespace std;

class CSet {
private:
    int *Data;
    int Space;
    int Size;
public:
    // Constructor and Destructor
    CSet(int n = 1) { 
        if (n < 1) {
            throw invalid_argument("Space must be greater than 0");
        }
        Size = 0; 
        Space = n;
        Data = new int[Space]; 
    }
    ~CSet() { delete[] Data; }

    // Copy constructor
    CSet(const CSet &s) {
        Size = s.Size;
        Space = s.Space;
        Data = new int[Space];
        for (int i = 0; i < Size; i++) {
            Data[i] = s.Data[i];
        }
    }

    // Getter and Setter
    int *GetData() { return Data; }
    int GetSize() { return Size; }
    int GetSpace() { return Space; }

    void SetSize(int size) { Size = size; }

    // Member functions
    void print();
    bool IsExist(int x);
    bool IsEqual(CSet &s2);
    CSet Intersection(CSet &s2);
    CSet Union(CSet &s2);
    int RemoveItem(int x);
    int AddItem(int x);
    int GetItem(int i);
};

void CSet::print() {
    cout << "{ ";
    for (int i = 0; i < Size; i++) {
        cout << Data[i];
        if (i < Size - 1) {
            cout << ", ";
        }
    }
    cout << " }" << endl; 
}

bool CSet::IsExist(int x) {
    /*
    description: 
        To judge if an integer is a member of a set or not
    param:
        x: the number to check
    return:
        true if x exists in the set, false otherwise
    */
    for (int i = 0; i < Size; i++) {
        if (Data[i] == x) {
            return true;
        }
    }
    return false;
}

bool CSet::IsEqual(CSet &s2) {
    /*
    description: 
        To judge if two sets are equal or not
    param:
        s2: the set to compare
    return:
        true if the set is equal to s2, false otherwise
    */
    if (Size != s2.Size) {
        return false;
    }
    for (int i = 0; i < Size; i++) {
        if (Data[i] != s2.Data[i]) {
            return false;
        }
    }
    return true;
}

CSet CSet::Intersection(CSet &s2) {
    /*
    description: 
        To get intersection with another set
    param:
        s2: the set to get the intersection
    return:
        the intersection of the set and s2
    */
    CSet result(Space);
    int resultSize = 0;
    for (int i = 0; i < Size; i++) {
        if (s2.IsExist(Data[i])) {
            result.AddItem(Data[i]);
            resultSize++;
        }
    }
    result.SetSize(resultSize);
    return result;
}

CSet CSet::Union(CSet &s2) {
    /*
    description: 
        To get union with another set
    param:
        s2: the set to get the union
    return:
        the union of the set and s2
    */
    CSet result(Space + s2.Space);
    int resultSize = 0;
    for (int i = 0; i < Size; i++) {
        result.AddItem(Data[i]);
        resultSize++;
    }
    for (int i = 0; i < s2.Size; i++) {
        if (!result.IsExist(s2.Data[i])) {
            result.AddItem(s2.Data[i]);
            resultSize++;
        }
    }
    result.SetSize(resultSize);
    return result;
}

int CSet::RemoveItem(int x) {
    /*
    description: 
        To delete an integer from the set
    param:
        x: the number to remove
    return:
        0 if x is removed successfully, -1 otherwise
    */
    for (int i = 0; i < Size; i++) {
        if (Data[i] == x) {
            for (int j = i; j < Size - 1; j++) {
                Data[j] = Data[j + 1];
            }
            Size--;
            return 0;
        }
    }
    cout << "The number is not in the set." << endl;
    return -1;
}

int CSet::AddItem(int x) {
    /*
    description: 
        To add an integer to a set.In this function adds an integer successfully when this integer is NOT in the set and there are enough space to save it in the set;  
    param:
        x: the number to add
    return:
        0 if x is added successfully, -1 otherwise
    */
    if (IsExist(x)) {
        cout << "The number is already in the set." << endl;
        return -1;
    }
    if (Size + 1 > Space) {
        cout << "The space is full." << endl;
        return -1;
    }
    Data[Size++] = x;
    return 0;
}

int CSet::GetItem(int i) {
    /*
    description: 
        To get an integer according to specified position
    param:
        i: the index of the element
    return:
        the i-th element of the set
    */
    return Data[i];
}
