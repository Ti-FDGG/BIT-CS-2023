package myowniterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

class SequenceIterator implements Iterator<Integer> {
    //数据源
    private NumberSequence sequence;
    //向外界返回下一个值
    private int nextValue;
    
    public SequenceIterator(NumberSequence sequence) {
        this.sequence = sequence;
        this.nextValue = sequence.start;
    }

    @Override
    public boolean hasNext() {
        return this.nextValue <= this.sequence.limit;
    }

    @Override
    public Integer next() {
        if (this.sequence.limit < this.nextValue) {
            throw new NoSuchElementException("No such element");
        }
        int rv = this.nextValue;
        //生成下一个值
        this.nextValue += this.sequence.increment;
        return rv;
    }
}

//一个自定义的，支持foreach迭代的数字序列生成器类
public class NumberSequence implements Iterable<Integer> {
    final int start, increment, limit;

    public NumberSequence(int start, int increment, int limit) {
        this.start = start;
        this.increment = increment;
        this.limit = limit;
    }

    @Override
    public Iterator<Integer> iterator() {
        //返回一个自定义的迭代器给外界
        return new SequenceIterator(this);
    }
}
