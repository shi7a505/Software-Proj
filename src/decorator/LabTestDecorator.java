package decorator;

/**
 * Decorator Pattern: Lab Test Decorator
 * مُزخرف فحص المختبر
 */
public class LabTestDecorator extends AppointmentDecorator {
    public LabTestDecorator(Appointment appointment) {
        super(appointment);
    }
    
    @Override
    public double getCost() {
        return appointment.getCost() + 50.0;
    }
    
    @Override
    public String getDescription() {
        return appointment.getDescription() + " + Lab Test";
    }
}
