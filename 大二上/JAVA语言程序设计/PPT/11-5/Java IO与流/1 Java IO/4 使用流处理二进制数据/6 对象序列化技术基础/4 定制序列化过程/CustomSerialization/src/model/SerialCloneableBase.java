package model;

import java.io.*;


public class SerialCloneableBase implements Cloneable, Serializable {
    public Object clone() throws CloneNotSupportedException {
        Object clone = super.clone();
        try (var bout = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bout);
        ) {
            out.writeObject(this); //将自身状态序列化到流中
            try (var bin = new ByteArrayInputStream(bout.toByteArray());
                 ObjectInputStream in = new ObjectInputStream(bin)) {
                //基于流反序列化，得到一个克隆对象
                clone= in.readObject();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        //向外界返回克隆结果
        return clone;
    }
}
