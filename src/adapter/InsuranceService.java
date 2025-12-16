package adapter;

/**
 * Adapter Pattern: Insurance Service Interface
 * واجهة خدمة التأمين - نمط المحول
 */
public interface InsuranceService {
    InsuranceCoverage getCoverage(String patientId);
}
