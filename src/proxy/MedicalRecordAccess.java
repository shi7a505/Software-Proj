package proxy;

/**
 * Proxy Pattern: Medical Record Access Interface
 * واجهة الوصول للسجلات الطبية - نمط البروكسي
 */
public interface MedicalRecordAccess {
    String viewRecord(String recordId);
    String getAccessLog();
}