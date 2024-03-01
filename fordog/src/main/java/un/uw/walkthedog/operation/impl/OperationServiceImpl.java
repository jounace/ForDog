package un.uw.walkthedog.operation.impl;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import un.uw.walkthedog.operation.OperationService;
import un.uw.walkthedog.operation.dao.IDao;
import un.uw.walkthedog.operation.pojo.DogInfo;
import un.uw.walkthedog.operation.pojo.Operationresult;
import un.uw.walkthedog.operation.pojo.Order;

import java.util.List;
import java.util.Map;

@Component
public class OperationServiceImpl implements OperationService {

    @Autowired
    private IDao dao;

    @Override
    public Operationresult adddog(DogInfo dogInfo) {
        Operationresult operationresult = new Operationresult();
        int i = dao.adddog(dogInfo.getId(), dogInfo.getName(), dogInfo.getType(), dogInfo.getAge(), dogInfo.getColor(), dogInfo.getSex(), dogInfo.getSize(), dogInfo.getOwnerid());
        if (i > 0) {
            operationresult.setStatus("success");
        } else {
            operationresult.setStatus("fail");
            operationresult.setResultstring("add dog fail");
        }
        return operationresult;
    }

    @Override
    public Operationresult getdog(String ownerid) {
        List<Map<String, String>> result = dao.getdog(ownerid);
        Operationresult operationresult = new Operationresult();
        operationresult.setResultstring(JSON.toJSONString(result));
        return operationresult;
    }

    @Override
    public Operationresult addorder(Order order) {
        int i = dao.addorder(order.getDogid(), order.getOwnerid(), order.getPrice(), order.getStart_time(), order.getEnd_time());
        Operationresult operationresult = new Operationresult();
        if (i > 0) {
            operationresult.setStatus("success");
        } else {
            operationresult.setStatus("false");
            operationresult.setResultstring("add order fail");
        }
        return operationresult;
    }

    @Override
    public Operationresult getorder() {
        List<Map<String, String>> result = dao.getorder();
        Operationresult operationresult = new Operationresult();
        operationresult.setResultstring(JSON.toJSONString(result));
        return operationresult;
    }

    @Override
    public Operationresult takeorder(String id,String workerid) {
        int i = dao.takeorder(id,workerid);
        Operationresult operationresult = new Operationresult();
        if (i>0){
            operationresult.setStatus("success");
        }else {
            operationresult.setStatus("fail");
            operationresult.setStatus("take order fail");
        }
        return operationresult;
    }
}
