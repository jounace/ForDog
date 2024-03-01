package un.uw.walkthedog.operation.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.relational.core.sql.SQL;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OperationServiceDao implements IDao {

    @Autowired
    @Qualifier("mysqljdbctemple")
    private JdbcTemplate mysqlJdbcTemple;

    @Override
    public int adddog(String id, String name, String type, int age, String color, String sex, String size, String ownerid) {
        id = name + type + age + color + sex + size + ownerid;
        String sql = "insert into DOG (id,name,type,age,color,sex,size,ownerid) values (?,?,?,?,?,?,?,?)";
        return mysqlJdbcTemple.update(sql, id.hashCode(), name, type, age, color, sex, size, ownerid);
    }

    @Override
    public List<Map<String, String>> getdog(String ownerid) {
        String sql = "select name,type,age,color,sex,size from DOG where ownerid = ?";
        List<Map<String, Object>> rows = mysqlJdbcTemple.queryForList(sql, ownerid);
        List<Map<String, String>> dogs = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            Map<String, String> dog = new HashMap<>();
            dog.put("name", row.get("name").toString());
            dog.put("type", row.get("type").toString());
            dog.put("age", row.get("age").toString());
            dog.put("color", row.get("color").toString());
            dog.put("sex", row.get("sex").toString());
            dog.put("size", row.get("size").toString());
            dogs.add(dog);
        }
        return dogs;
    }

    @Override
    public int addorder(String dogid, String ownerid, int price, String start_time, String end_time) {
        String sql = "insert into order (dogid,ownerid,status,price,start_time,end_time) values (?,?,'0',?,?,?)";
        return mysqlJdbcTemple.update(sql, dogid, ownerid, price, start_time, end_time);
    }

    @Override
    public List<Map<String, String>> getorder() {
        String sql = "select t.id as id ,b.name as dogname,c.name as ownername,e.name as workername,t.status as status,t.price as price,t.start_time as start_time,t.end_time as end_time from order t left join DOGOWNER c on t.ownerid = c.id left join DOG b on t.dogid = b.id left join WORKER e on t.worker=e.id";
        List<Map<String, Object>> rows = mysqlJdbcTemple.queryForList(sql);
        List<Map<String,String>> orderinfo = new ArrayList<>();
        for (Map<String,Object> row : rows){
            Map<String,String> order = new HashMap<>();
            order.put("id",row.get("id").toString());
            order.put("dogname",row.get("dogname").toString());
            order.put("ownername",row.get("ownername").toString());
            order.put("workername",row.get("wokername").toString());
            order.put("status",row.get("status").toString());
            order.put("price",row.get("price").toString());
            order.put("start_time",row.get("start_time").toString());
            order.put("end_time",row.get("end_time").toString());
            orderinfo.add(order);
        }
        return orderinfo;
    }

    @Override
    public int takeorder(String id,String workerid) {
        String sql = "update order set worker = ? , status = '1' where id = ?";
        return mysqlJdbcTemple.update(sql,workerid,id);
    }
}
