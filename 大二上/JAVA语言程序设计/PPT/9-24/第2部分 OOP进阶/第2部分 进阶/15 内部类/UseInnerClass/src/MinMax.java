public class MinMax {
    public static void main(String[] args) {
        //使用随机数填充数组
        double[] d = new double[20];
        for (int i = 0; i < d.length; i++)
            d[i] = 100 * Math.random();
        //一次性获取数组的最大值和最小值
        ArrayWithInnerClass.Pair p = ArrayWithInnerClass.minmax(d);
        System.out.println("min = " + p.getFirst());
        System.out.println("max = " + p.getSecond());
    }
}

class ArrayWithInnerClass {
    //用于封装一个数值对
    public static class Pair {
        public Pair(double f, double s) {
            first = f;
            second = s;
        }
        public double getFirst() {
            return first;
        }
        public double getSecond() {
            return second;
        }
        private double first;
        private double second;
    }
    //获取数组的最大值与最小值
    public static Pair minmax(double[] values) {
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        for (double v : values) {
            if (min > v) min = v;
            if (max < v) max = v;
        }
        return new Pair(min, max);
    }
}
