import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    //引用本机SQL Server数据库服务器，并打开MyDB数据库
    private final static String MsSqlServer=
            "jdbc:sqlserver://localhost:1433;database=MyDB;";
    //指定使用集成身份验证模式，让本机登录账号直接访问数据库
    private final static String IntegratedSecurity=
            "IntegratedSecurity=true;encrypt=true;trustServerCertificate=true;";

    public static void main(String[] args) throws SQLException {
        //connectUseIntegratedSecurity();
       connectUseUserNameAndPassword();
    }

    //使用集成身份验证模式访问本机SQL Server
    static void connectUseIntegratedSecurity() throws SQLException {
        try(var connection= DriverManager.getConnection(
                MsSqlServer+IntegratedSecurity)){
            System.out.println("使用集成身份验证模式，成功连接SQL Server数据库");
        }catch (SQLException se){
            System.out.println(se.getMessage());
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    private final static String userName="test";
    private final static String password="abcdefg";
    //提供用户名与密码，连接数据库
    static void connectUseUserNameAndPassword(){
        try(var connection= DriverManager.getConnection(
                MsSqlServer,userName,password)){
            System.out.println("使用用户名与密码，成功连接SQL Server数据库");
        }catch (SQLException se){
            System.out.println(se.getMessage());
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
