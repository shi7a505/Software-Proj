package decorator;

/**
 * Decorator Pattern: Base Appointment Decorator
 * المُزخرف الأساسي للمواعيد
 */
public abstract class AppointmentDecorator implements Appointment {
    protected Appointment appointment;
    
    public AppointmentDecorator(Appointment appointment) {
        this.appointment = appointment;
    }
    
    @Override
    public double getCost() {
        return appointment.getCost();
    }
    
    @Override
    public String getDescription() {
        return appointment.getDescription();
    }
}
