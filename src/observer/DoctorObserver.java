package observer;

/**
 * Observer Pattern: Doctor Observer
 * مراقب الطبيب
 */
public class DoctorObserver implements Observer {
    @Override
    public void update(String message) {
        // This will be used by the GUI
    }
    
    public String getNotification(String message) {
        return "Doctor Notification: " + message;
    }
}
