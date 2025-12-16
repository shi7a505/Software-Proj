package adapter;

/**
 * Adapter Pattern: Insurance Adapter
 * محول التأمين
 */
public class InsuranceAdapter implements InsuranceService {
    private LegacyInsuranceSystem legacySystem;
    
    public InsuranceAdapter() {
        this.legacySystem = new LegacyInsuranceSystem();
    }
    
    @Override
    public InsuranceCoverage getCoverage(String patientId) {
        // Get data from legacy system
        String legacyData = legacySystem.checkPatientInsurance(patientId);
        
        // Parse the legacy format: "PatientID|CoveragePercent|MaxLimit"
        String[] parts = legacyData.split("\\|");
        
        if (parts.length == 3) {
            String id = parts[0];
            double percentage = Double.parseDouble(parts[1]);
            double limit = Double.parseDouble(parts[2]);
            
            return new InsuranceCoverage(id, percentage, limit);
        }
        
        // Return default coverage if parsing fails
        return new InsuranceCoverage(patientId, 0, 0);
    }
}
