package observer;

/**
 * Observer Pattern: Receptionist Observer
 * مراقب موظف الاستقبال
 */
public class ReceptionistObserver implements Observer {
    @Override
    public void update(String message) {
        // This will be used by the GUI
    }
    
    public String getNotification(String message) {
        return "Receptionist Notification: " + message;
    }
}
