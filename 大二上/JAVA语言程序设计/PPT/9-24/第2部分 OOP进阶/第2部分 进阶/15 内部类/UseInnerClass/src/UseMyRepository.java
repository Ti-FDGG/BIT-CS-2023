
public class UseMyRepository {
    public static void main(String[] args) {

        //我想使用内存作为数据存储
        var memRepo = new MyRepository("Memory");
        //输出：MyRepository$MemoryStorage
        System.out.println(memRepo.getRealStorageInfo());

        //我想使用数据库作为数据存储
        var dbRepo = new MyRepository("Database");
        //输出：MyRepository$DatabaseStorage
        System.out.println(dbRepo.getRealStorageInfo());
    }
}

class MyRepository {
    private interface IStorage {
        //一种存储介质需要实现的功能
    }

    //使用内存作为数据存储
    private class MemoryStorage implements IStorage {
    }

    //使用数据库进行数据存储
    private class DatabaseStorage implements IStorage {
    }

    //真正使用的数据存储
    private IStorage realStorage;

    public MyRepository(String storageType) {
        //依据构造方法传入的数值，实例化不同的数据存储介质
        realStorage = switch (storageType.toUpperCase()) {
            case "MEMMORY" -> new MemoryStorage();
            case "DATABASE" -> new DatabaseStorage();
            default -> new MemoryStorage();
        };
    }

    public String getRealStorageInfo() {
        //供外界查询，到底使用的是哪种数据存储
        return realStorage.getClass().getName();
    }

}