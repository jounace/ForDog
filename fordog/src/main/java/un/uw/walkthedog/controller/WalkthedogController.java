package un.uw.walkthedog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import un.uw.walkthedog.operation.OperationService;
import un.uw.walkthedog.operation.pojo.DogInfo;
import un.uw.walkthedog.operation.pojo.Operationresult;
import un.uw.walkthedog.operation.pojo.Order;

import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Controller
@RequestMapping("/walkthedog")
public class WalkthedogController {

    @Autowired
    private Environment env;

    @Autowired
    private OperationService operationService;

    @RequestMapping("/adddog")
    @POST
    public Operationresult adddog(@ModelAttribute DogInfo dogInfo){
        return operationService.adddog(dogInfo);
    }

    @RequestMapping("/getdog/{ownerid}")
    @POST
    public Operationresult getdog(@PathParam("ownerid") String ownerid){
        return operationService.getdog(ownerid);
    }

    @RequestMapping("/getorder")
    @POST
    public Operationresult getorder(){
        return operationService.getorder();
    }

    @RequestMapping("/addorder")
    @POST
    public Operationresult addorder(@ModelAttribute Order order){
        return operationService.addorder(order);
    }

    @RequestMapping("/takeorder")
    @POST
    public Operationresult takeorder(@QueryParam("id") String id, @QueryParam("workerid") String workerid){
        Operationresult operationresult = new Operationresult();
        operationService.takeorder(id,workerid);
        return operationresult;
    }
}
