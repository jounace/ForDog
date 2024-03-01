package un.uw.walkthedog.operation.dao;

import java.util.List;
import java.util.Map;

public interface IDao {

    public int adddog(String id,String name ,String type,int age, String color,String sex,String size,String ownerid);

    public List<Map<String,String>> getdog(String ownerid);

    public int addorder(String dogid,String ownerid,int price,String start_time,String end_time);

    public List<Map<String,String>> getorder();

    public int takeorder(String id,String workerid);
}
