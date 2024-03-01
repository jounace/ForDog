package un.uw.walkthedog.operation;

import un.uw.walkthedog.operation.pojo.DogInfo;
import un.uw.walkthedog.operation.pojo.Operationresult;
import un.uw.walkthedog.operation.pojo.Order;

public interface OperationService {
    public Operationresult adddog(DogInfo dogInfo);

    public Operationresult getdog(String ownerid);

    public Operationresult addorder(Order order);

    public Operationresult getorder();

    public Operationresult takeorder(String id,String worker);
}
