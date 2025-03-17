import java.util.*;

//手工实现JDK中ArrayList的主要功能
//通过在内部封装一个数组实现
public class MySimpleArrayList<E> implements List<E> {
    //使用数组封装数据
    private E[] data = (E[])(new Object[10]);
    //当前己保存的数据元素个数
    private int count = 0;
    //包容多少个元素？
    @Override
    public int size() {
        return count;
    }
    //是否为空？
    @Override
    public boolean isEmpty() {
        return count != 0;
    }
    //判断是否可以保证有这个最小的容量
    private void ensureCapacity(int minCapacity) {
        if (data.length < minCapacity) {
            //扩充数组
            data = Arrays.copyOf(data, minCapacity);
        }
    }
    @Override
    public boolean add(E e) {
        //确保有足够的容量，如果不够，就扩充底层的数组
        ensureCapacity(count + 1);
        data[count++] = e;
        return true;
    }
    //在指定的位置添加元素
    @Override
    public void add(int index, E element) {
        if (index > count || index < 0) {
            throw new IndexOutOfBoundsException(index + "不是一个有效的位置索引");
        }
        //在尾部追加
        if (index == count) {
            add(element);
            return;
        }
        ensureCapacity(count + 1);
        //将插入点后面的元素后移一个位置
        System.arraycopy(data, index, data, index + 1, count - index);
        //将新元素放置到空出的位置上
        data[index] = element;
        count++;
    }

    //按索引提取元素
    @Override
    public E get(int index) {
        //检查索引的有效性
        if (index < count && index >= 0) {
            return data[index];
        } else {
            throw new IndexOutOfBoundsException(index + "不是一个有效的位置索引");
        }
    }

    //修改特定位置的元素
    @Override
    public E set(int index, E element) {
        if (index < count && index >= 0) {
            E rv = data[index];
            data[index] = element;
            return rv;
        } else {
            throw new IndexOutOfBoundsException(index + "不是一个有效的位置索引");
        }
    }

    @Override
    public void clear() {
        //将所有数组元素全部置为null
        Arrays.fill(data, 0, count, null);
        count = 0;
    }


    @Override
    public E remove(int index) {
        E rv = get(index);
        //被删除元素后面的所有元素，全部向前移动一个位置
        System.arraycopy(data, index + 1, data, index, count - index - 1);
        //最后一个位置设置为null
        data[count--] = null;
        return rv;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MySimpleArrayList [");
        for (int i = 0; i < count; i++) {
            sb.append(data[i]).append(", ");
        }
        if (count > 0) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");
        return sb.toString();
    }

    //自定义迭代器
    private class MyArrayListIterator implements Iterator<E> {
        private int progress = 0;
        @Override
        public boolean hasNext() {
            return progress < count;
        }
        @Override
        public E next() {
            return data[progress++];
        }
    }
    @Override
    public Iterator<E> iterator() {
       return new MyArrayListIterator();
    }


    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
