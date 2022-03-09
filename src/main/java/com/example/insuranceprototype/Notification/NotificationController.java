package com.example.insuranceprototype.Notification;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@RestController
@CrossOrigin
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/getall")
    public List<Notification> getAllNotification(){
        return notificationService.getAllNotification();
    }



    @GetMapping("/employee/{id}")
    public List<Notification> getbyEMployee(@PathVariable Long id){
        return notificationService.getByEmployee(id);
    }

//	@PostMapping("/addNotification")
//	public String addNotification(@PathVariable(value = "notification_type") String notification_type, @PathVariable(value = "notification_priority") String notification_priority,@PathVariable(value = "employee_id") Integer employee_id, @PathVariable(value = "agent_id") Integer agent_id, @PathVariable(value = "notification_text") String notification_text){
//		notificationService.addNotification(notification_type, notification_priority, notification_text, employee_id,agent_id );
//
//		return "Notification is added successfully ";
//	}


}
