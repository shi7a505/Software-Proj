package factory;

/**
 * Factory Pattern: Medical Record Factory
 * مصنع السجلات الطبية
 */
public class MedicalRecordFactory {
    public static MedicalRecord createRecord(String type) {
        if (type == null) {
            return null;
        }
        
        switch (type) {
            case "Prescription":
                return new Prescription();
            case "Lab Result":
                return new LabResult();
            case "Patient History":
                return new PatientHistory();
            default:
                return null;
        }
    }
}
