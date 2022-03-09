package com.example.insuranceprototype.Notification;



import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getAllNotification(){
        return notificationRepository.findAll();
    }

    public Optional<Notification> getNotificationById(Long id){
        return notificationRepository.findById(id);
    }

    public List<Notification> getByEmployee(Long id){
        return notificationRepository.findByEmployeeId(id);
    }

	public void addNotification(String notification_type, String notification_priority, String notification_text, Long employee_id, Long agent_id) {
		Notification n = new Notification(notification_type, notification_priority,  notification_text , employee_id, agent_id);
		notificationRepository.save(n);
	}

//    public String addNotification(Notification notification) {
//        notificationRepository.save(notification);
//        return "Notification saved successfully ";
//    }
}
