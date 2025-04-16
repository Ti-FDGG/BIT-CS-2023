#include <iostream>

using namespace std;

class Stash {
    private:
    int size;      // Size of each space
    int quantity;  // Number of storage spaces
    int next;      // Next empty space
    
    unsigned char* storage; // Dynamically allocated array of bytes. Here, unsigned char is used to store byte data

    public:
    // Constructor and Destructor
    Stash(int size) : size(size), quantity(0), next(0), storage(0) {}
    ~Stash() { 
        if(storage != 0) 
            cout << "freeing storage" << endl; 
            delete []storage; 
    }

    // Other Member Functions
    // 相比于课本中的对应部分，这里不需要initialize和cleanup函数，因为我们使用了class的构造函数和析构函数
    int add(const void* element);
    void* fetch(int index);
    int count();
    void inflate(int increase);

    void Remove(int index);
    void Contract();
};

int Stash::add(const void* element) {
/*
description:
    Add an element to the stash
parameters:
    element: the element to be added
return:
    the index number of the element
*/
if(next >= quantity)
    inflate(size);

// Copy element into storage, starting at next empty space
int startBytes = next * size;
unsigned char* e = (unsigned char*)element;
for(int i = 0; i < size; i++)
    storage[startBytes + i] = e[i];
    next++;
    return (next - 1);
}

void* Stash::fetch(int index) {
    /*
    description:
        Fetch an element from the stash
    parameters:
        index: the index number of the element to be fetched
    return:
        the pointer to the element
    */
    // Check index out of bounds
    if(index >= next)
        return 0; // To indicate the end
    return &(storage[index * size]); // Pointer to the desired element
}

int Stash::count() {
    /*
    description:
        Return the number of elements in the stash
    return:
        the number of elements in the stash
    */
    return next; // Number of elements in CStash
}

void Stash::inflate(int increase) {
    /*
    description:
        Increase the storage space
    parameters:
        increase: the number of elements to be increased
    */
    if(increase == 0)
        return;
    
    int newQuantity = quantity + increase;
    int newBytes = newQuantity * size;
    int oldBytes = quantity * size;
    unsigned char* b = new unsigned char[newBytes];
    for(int i = 0; i < oldBytes; i++)
        b[i] = storage[i];
    delete []storage; // Release old storage
    storage = b; // Point to new memory
    quantity = newQuantity;
}

void Stash::Remove(int index) {
    /*
    description:
        Delete an element at specified position
    parameters:
        index: the index number of the element to be deleted
    */
    if (index >= next) {
        cout << "index out of bounds" << endl;
        return;
    }

    // Move the elements after the index one position forward
    for (int i = index; i < next - 1; i++) {
        unsigned char* current = storage + i * size;
        unsigned char* next = storage + (i + 1) * size;
        for (int j = 0; j < size; j++) {
            current[j] = next[j];
        }
    }
    next--;
}

void Stash::Contract() {
    /*
    description:
        Release the storage space. Compared to the destructor, this function is designed for the user.
    */
    if(storage != 0) {
        cout << "freeing storage commanded by user" << endl;
        delete []storage;
        storage = 0;
    }
}