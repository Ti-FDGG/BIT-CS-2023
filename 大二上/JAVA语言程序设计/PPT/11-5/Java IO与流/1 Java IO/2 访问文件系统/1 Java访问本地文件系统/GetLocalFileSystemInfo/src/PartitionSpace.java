import java.io.File;
//获取各个分区的可用空间
public class PartitionSpace {
    public static void main(String[] args) {
        File[] roots = File.listRoots();
        for (File root : roots) {
            System.out.println("Partition: " + root);
            System.out.println("自由空间（Free Space） = " +
                    root.getFreeSpace());
            //getUsableSpace()方法会考虑到操作系统的特性，给出更精确的结果
            System.out.println("可用空间（Usable Space） = " +
                    root.getUsableSpace());
            System.out.println("总容量 = " +
                    root.getTotalSpace());
            System.out.println("***");
        }
    }
}