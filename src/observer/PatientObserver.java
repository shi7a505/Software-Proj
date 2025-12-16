package observer;

/**
 * Observer Pattern: Patient Observer
 * مراقب المريض
 */
public class PatientObserver implements Observer {
    @Override
    public void update(String message) {
        // This will be used by the GUI
    }
    
    public String getNotification(String message) {
        return "Patient Notification: " + message;
    }
}
