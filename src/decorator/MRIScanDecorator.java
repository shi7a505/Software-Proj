package decorator;

/**
 * Decorator Pattern: MRI Scan Decorator
 * مُزخرف فحص الرنين المغناطيسي
 */
public class MRIScanDecorator extends AppointmentDecorator {
    public MRIScanDecorator(Appointment appointment) {
        super(appointment);
    }
    
    @Override
    public double getCost() {
        return appointment.getCost() + 500.0;
    }
    
    @Override
    public String getDescription() {
        return appointment.getDescription() + " + MRI Scan";
    }
}
