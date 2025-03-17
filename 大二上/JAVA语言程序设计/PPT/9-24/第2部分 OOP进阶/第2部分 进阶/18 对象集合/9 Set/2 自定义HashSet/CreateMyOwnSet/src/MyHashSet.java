

public class MyHashSet<E> {
    //此HashSet最多可以保存10个元素
    private static final int STORE_SIZE = 10;
    //每个槽最多个保存4个元素（这些元素的hash值相同)
    private static final int SLOT_SIZE = 4;
    //使用二维数组来保存元素
    private Object[][] store = new Object[STORE_SIZE][SLOT_SIZE];

    //获取集合的容量
    public int getCapacity() {
        return STORE_SIZE;
    }

    //插入元素
    public boolean insert(E elem) throws Exception {
        Object[] target = store[storeLocation(elem)];
        int idx = 0;
        //在行中查找“空位”
        while (target[idx] != null && idx < SLOT_SIZE) {
            //如果己有这个元素，则不追加它到集合中
            if(target[idx].equals(elem)){
                return false;
            }
            idx++;
        }
        if (idx == SLOT_SIZE) {
            throw new Exception("槽已经满了");
        }
        target[idx] = elem;
        return true;
    }

    //是否己经包容有特定的元素
    public boolean contains(E elem) {
        //计算Hash值，按照此值找到存储的行
        Object[] target = store[storeLocation(elem)];
        //在行中查找是否此元素己经存在
        int idx = 0;
        while (target[idx] != null) {
            if (elem.equals(target[idx])) {
                return true;
            }
            idx++;
        }
        return false;
    }

    //计算hash值，它决定了真实的存储位置
    private int storeLocation(E elem) {
        return Math.abs(elem.hashCode()) % STORE_SIZE;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object[] row : store) {
            int idx = 0;
            while (row[idx] != null && idx < SLOT_SIZE) {
                sb.append(row[idx]).append(", ");
                idx++;
            }
        }
        if (sb.length() >= 2) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }
}
