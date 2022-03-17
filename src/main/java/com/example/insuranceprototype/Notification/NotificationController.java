package com.example.insuranceprototype.Notification;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public Optional<Notification> getEvent(@PathVariable Integer id){
        return notificationService.getNotificationById(id);
    }

    @GetMapping("/employee/{id}")
    public List<Notification> getbyEMployee(@PathVariable Long id){
        return notificationService.getByEmployee(id);
    }

    @PostMapping("/add")
    public void addNotification(@RequestBody Notification event){
        notificationService.addEvent(event);
    }

    @PatchMapping("/{id}")
    public void updatenotification(@PathVariable Integer id, @RequestBody Notification event){
        notificationService.updateEvent(id, event);
    }

    @DeleteMapping("{id}")
    public void deleteevent(@PathVariable Integer id){
        notificationService.deleteEvent(id);
    }

}
