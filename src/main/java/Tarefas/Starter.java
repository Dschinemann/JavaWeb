package Tarefas;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.*;
import servicos.ClientesXML;

import java.time.LocalDateTime;

@Startup
@Singleton
public class Starter{

    @Resource
    TimerService timerService;

    @PostConstruct
    public void init(){
        timerService.createCalendarTimer(
                new ScheduleExpression().dayOfMonth(1).dayOfWeek("Mon,Tue,Wed,Thu,Fri").hour("1").minute("0")
                //new ScheduleExpression().second("*/10").minute("*").hour("*")
        );
    }

    @Timeout
    public void processingList(){
        try{
            ClientesXML.ocupacao();
            //ClientesXML.teste();
            System.out.println("excutando" + LocalDateTime.now());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
