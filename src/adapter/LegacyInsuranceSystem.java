package adapter;

/**
 * Adapter Pattern: Legacy Insurance System
 * النظام القديم للتأمين
 */
public class LegacyInsuranceSystem {
    public String checkPatientInsurance(String patientId) {
        // Simulate legacy system returning data in old format
        // Format: "PatientID|CoveragePercent|MaxLimit"
        
        // Sample data based on patient ID
        if (patientId != null && !patientId.isEmpty()) {
            int idHash = patientId.hashCode() % 100;
            double coverage = 70 + (idHash % 30);
            double limit = 5000 + (idHash % 20) * 1000;
            return patientId + "|" + coverage + "|" + limit;
        }
        
        return "UNKNOWN|0|0";
    }
}
