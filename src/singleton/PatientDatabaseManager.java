package singleton;

import models.Patient;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton Pattern: Patient Database Manager
 * مدير قاعدة بيانات المرضى - نمط السينجلتون
 */
public class PatientDatabaseManager {
    private static PatientDatabaseManager instance;
    private List<Patient> patients;
    
    private PatientDatabaseManager() {
        patients = new ArrayList<>();
    }
    
    public static PatientDatabaseManager getInstance() {
        if (instance == null) {
            instance = new PatientDatabaseManager();
        }
        return instance;
    }
    
    public void addPatient(Patient patient) {
        patients.add(patient);
    }
    
    public List<Patient> getAllPatients() {
        return new ArrayList<>(patients);
    }
    
    public int getPatientCount() {
        return patients.size();
    }
}
