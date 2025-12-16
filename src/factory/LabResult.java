package factory;

/**
 * Factory Pattern: Lab Result Record
 * سجل نتائج المختبر
 */
public class LabResult implements MedicalRecord {
    @Override
    public String getRecordType() {
        return "Lab Result";
    }
    
    @Override
    public String getDetails() {
        return "Record Type: Lab Result\n" +
               "Description: Laboratory test results\n" +
               "Contains: Blood tests, urine analysis, and diagnostic findings\n" +
               "Status: Results available";
    }
}
