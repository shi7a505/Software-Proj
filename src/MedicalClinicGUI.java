import javax.swing.*;
import java.awt.*;
import models.Patient;
import singleton.PatientDatabaseManager;
import factory.MedicalRecord;
import factory.MedicalRecordFactory;
import decorator.*;
import observer.*;
import adapter.*;
import proxy.*;

/**
 * Medical Clinic Management System - Main GUI
 * نظام إدارة العيادة الطبية
 * 
 * Demonstrates 6 Design Patterns:
 * 1. Singleton Pattern
 * 2. Factory Pattern
 * 3. Decorator Pattern
 * 4. Observer Pattern
 * 5. Adapter Pattern
 * 6. Proxy Pattern
 */
public class MedicalClinicGUI extends JFrame {
    
    private JTabbedPane tabbedPane;
    
    public MedicalClinicGUI() {
        setTitle("Medical Clinic Management System - نظام إدارة العيادة");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        tabbedPane = new JTabbedPane();
        
        // Add 6 tabs
        tabbedPane.addTab("Singleton", createSingletonTab());
        tabbedPane.addTab("Factory", createFactoryTab());
        tabbedPane.addTab("Decorator", createDecoratorTab());
        tabbedPane.addTab("Observer", createObserverTab());
        tabbedPane.addTab("Adapter", createAdapterTab());
        tabbedPane.addTab("Proxy", createProxyTab());
        
        add(tabbedPane);
    }
    
    /**
     * Tab 1: Singleton Pattern - Patient Database Manager
     */
    private JPanel createSingletonTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        
        JLabel nameLabel = new JLabel("Patient Name:");
        JTextField nameField = new JTextField();
        
        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField();
        
        JButton addButton = new JButton("Add Patient");
        JButton showButton = new JButton("Show All Patients");
        
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(ageLabel);
        inputPanel.add(ageField);
        inputPanel.add(addButton);
        inputPanel.add(showButton);
        
        // Output area
        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        
        // Add action listeners
        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String ageStr = ageField.getText().trim();
            
            if (name.isEmpty() || ageStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            try {
                int age = Integer.parseInt(ageStr);
                String id = "P" + System.currentTimeMillis();
                Patient patient = new Patient(id, name, age);
                
                PatientDatabaseManager.getInstance().addPatient(patient);
                
                outputArea.append("Patient added successfully!\n");
                outputArea.append(patient.toString() + "\n\n");
                
                nameField.setText("");
                ageField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Age must be a number!", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        showButton.addActionListener(e -> {
            outputArea.setText("");
            PatientDatabaseManager manager = PatientDatabaseManager.getInstance();
            outputArea.append("=== All Patients in Database ===\n");
            outputArea.append("Total Patients: " + manager.getPatientCount() + "\n\n");
            
            for (Patient patient : manager.getAllPatients()) {
                outputArea.append(patient.toString() + "\n");
            }
        });
        
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Tab 2: Factory Pattern - Medical Records
     */
    private JPanel createFactoryTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Input panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        JLabel typeLabel = new JLabel("Select Record Type:");
        String[] recordTypes = {"Prescription", "Lab Result", "Patient History"};
        JComboBox<String> recordTypeCombo = new JComboBox<>(recordTypes);
        JButton createButton = new JButton("Create Record");
        
        inputPanel.add(typeLabel);
        inputPanel.add(recordTypeCombo);
        inputPanel.add(createButton);
        
        // Output area
        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        
        // Add action listener
        createButton.addActionListener(e -> {
            String selectedType = (String) recordTypeCombo.getSelectedItem();
            MedicalRecord record = MedicalRecordFactory.createRecord(selectedType);
            
            if (record != null) {
                outputArea.setText("");
                outputArea.append("=== Medical Record Created ===\n\n");
                outputArea.append(record.getDetails());
                outputArea.append("\n\n=== Factory Pattern in Action ===\n");
                outputArea.append("Record created using MedicalRecordFactory.createRecord(\"" 
                    + selectedType + "\")");
            }
        });
        
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Tab 3: Decorator Pattern - Appointment Services
     */
    private JPanel createDecoratorTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Options panel
        JPanel optionsPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        
        JLabel basicLabel = new JLabel("Basic Consultation: $100");
        basicLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        JCheckBox labTestCheck = new JCheckBox("Lab Test +$50");
        JCheckBox xrayCheck = new JCheckBox("X-Ray +$150");
        JCheckBox mriCheck = new JCheckBox("MRI Scan +$500");
        
        JButton calculateButton = new JButton("Calculate Total");
        JLabel totalCostLabel = new JLabel("Total Cost: $100");
        totalCostLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalCostLabel.setForeground(new Color(0, 128, 0));
        
        optionsPanel.add(basicLabel);
        optionsPanel.add(labTestCheck);
        optionsPanel.add(xrayCheck);
        optionsPanel.add(mriCheck);
        optionsPanel.add(calculateButton);
        optionsPanel.add(totalCostLabel);
        
        // Description area
        JTextArea descArea = new JTextArea();
        descArea.setEditable(false);
        descArea.setText("Select services and click 'Calculate Total' to see the final cost.");
        JScrollPane scrollPane = new JScrollPane(descArea);
        
        // Add action listener
        calculateButton.addActionListener(e -> {
            Appointment appointment = new BasicAppointment();
            
            if (labTestCheck.isSelected()) {
                appointment = new LabTestDecorator(appointment);
            }
            if (xrayCheck.isSelected()) {
                appointment = new XRayDecorator(appointment);
            }
            if (mriCheck.isSelected()) {
                appointment = new MRIScanDecorator(appointment);
            }
            
            double totalCost = appointment.getCost();
            String description = appointment.getDescription();
            
            totalCostLabel.setText("Total Cost: $" + totalCost);
            descArea.setText("=== Appointment Details ===\n\n");
            descArea.append("Services: " + description + "\n");
            descArea.append("Total Cost: $" + totalCost + "\n\n");
            descArea.append("=== Decorator Pattern in Action ===\n");
            descArea.append("Starting with BasicAppointment ($100)\n");
            
            if (labTestCheck.isSelected()) {
                descArea.append("+ Decorated with LabTestDecorator (+$50)\n");
            }
            if (xrayCheck.isSelected()) {
                descArea.append("+ Decorated with XRayDecorator (+$150)\n");
            }
            if (mriCheck.isSelected()) {
                descArea.append("+ Decorated with MRIScanDecorator (+$500)\n");
            }
        });
        
        panel.add(optionsPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Tab 4: Observer Pattern - Notification System
     */
    private JPanel createObserverTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        
        JLabel patientLabel = new JLabel("Patient Name:");
        JTextField patientNameField = new JTextField();
        
        JLabel doctorLabel = new JLabel("Doctor Name:");
        JTextField doctorNameField = new JTextField();
        
        JLabel dateLabel = new JLabel("Date:");
        JTextField dateField = new JTextField();
        
        JButton bookButton = new JButton("Book Appointment");
        JLabel spacer = new JLabel("");
        
        inputPanel.add(patientLabel);
        inputPanel.add(patientNameField);
        inputPanel.add(doctorLabel);
        inputPanel.add(doctorNameField);
        inputPanel.add(dateLabel);
        inputPanel.add(dateField);
        inputPanel.add(bookButton);
        inputPanel.add(spacer);
        
        // Notifications area
        JTextArea notificationsArea = new JTextArea();
        notificationsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(notificationsArea);
        
        // Create observers
        PatientObserver patientObserver = new PatientObserver();
        DoctorObserver doctorObserver = new DoctorObserver();
        ReceptionistObserver receptionistObserver = new ReceptionistObserver();
        
        // Add action listener
        bookButton.addActionListener(e -> {
            String patientName = patientNameField.getText().trim();
            String doctorName = doctorNameField.getText().trim();
            String date = dateField.getText().trim();
            
            if (patientName.isEmpty() || doctorName.isEmpty() || date.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Create subject and attach observers
            AppointmentSubject subject = new AppointmentSubject();
            subject.attach(patientObserver);
            subject.attach(doctorObserver);
            subject.attach(receptionistObserver);
            
            String message = "Appointment booked for " + patientName + 
                           " with Dr. " + doctorName + " on " + date;
            
            notificationsArea.setText("");
            notificationsArea.append("=== Appointment Booked Successfully ===\n\n");
            notificationsArea.append("Details: " + message + "\n\n");
            notificationsArea.append("=== Notifications Sent to All Observers ===\n\n");
            
            // Notify and display notifications
            notificationsArea.append(patientObserver.getNotification(message) + "\n");
            notificationsArea.append(doctorObserver.getNotification(message) + "\n");
            notificationsArea.append(receptionistObserver.getNotification(message) + "\n");
            
            subject.notifyObservers(message);
            
            // Clear fields
            patientNameField.setText("");
            doctorNameField.setText("");
            dateField.setText("");
        });
        
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Tab 5: Adapter Pattern - Insurance Check
     */
    private JPanel createAdapterTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Input panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        JLabel idLabel = new JLabel("Patient ID:");
        JTextField patientIdField = new JTextField(20);
        JButton checkButton = new JButton("Check Coverage");
        
        inputPanel.add(idLabel);
        inputPanel.add(patientIdField);
        inputPanel.add(checkButton);
        
        // Coverage area
        JTextArea coverageArea = new JTextArea();
        coverageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(coverageArea);
        
        // Add action listener
        checkButton.addActionListener(e -> {
            String patientId = patientIdField.getText().trim();
            
            if (patientId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a patient ID!", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Use adapter to get coverage
            InsuranceService insuranceService = new InsuranceAdapter();
            InsuranceCoverage coverage = insuranceService.getCoverage(patientId);
            
            coverageArea.setText("");
            coverageArea.append("=== Insurance Coverage Information ===\n\n");
            coverageArea.append(coverage.toString());
            coverageArea.append("\n\n=== Adapter Pattern in Action ===\n");
            coverageArea.append("Legacy system data converted to modern format\n");
            coverageArea.append("using InsuranceAdapter\n");
        });
        
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Tab 6: Proxy Pattern - Medical Record Access
     */
    private JPanel createProxyTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        
        JLabel userLabel = new JLabel("User Name:");
        JTextField userNameField = new JTextField();
        
        JLabel recordLabel = new JLabel("Record ID:");
        String[] recordIds = {"REC001", "REC002", "REC003"};
        JComboBox<String> recordCombo = new JComboBox<>(recordIds);
        
        JButton viewButton = new JButton("View Record");
        JButton showLogButton = new JButton("Show Access Log");
        
        inputPanel.add(userLabel);
        inputPanel.add(userNameField);
        inputPanel.add(recordLabel);
        inputPanel.add(recordCombo);
        inputPanel.add(viewButton);
        inputPanel.add(showLogButton);
        
        // Output area
        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        
        // Create proxy (will be recreated when user changes)
        final MedicalRecordProxy[] proxyHolder = new MedicalRecordProxy[1];
        
        // Add action listeners
        viewButton.addActionListener(e -> {
            String userName = userNameField.getText().trim();
            String recordId = (String) recordCombo.getSelectedItem();
            
            if (userName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a user name!", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Create or reuse proxy
            if (proxyHolder[0] == null || !userName.equals(userNameField.getText())) {
                proxyHolder[0] = new MedicalRecordProxy(userName);
            }
            
            String record = proxyHolder[0].viewRecord(recordId);
            
            outputArea.setText("");
            outputArea.append("=== Medical Record ===\n\n");
            outputArea.append(record);
            outputArea.append("\n\n=== Proxy Pattern in Action ===\n");
            outputArea.append("Access controlled and logged by MedicalRecordProxy\n");
            outputArea.append("User: " + userName + "\n");
            outputArea.append("Record ID: " + recordId + "\n");
        });
        
        showLogButton.addActionListener(e -> {
            if (proxyHolder[0] == null) {
                outputArea.setText("No access log yet. Please view a record first.");
                return;
            }
            
            outputArea.setText(proxyHolder[0].getAccessLog());
            outputArea.append("\n=== Proxy Pattern in Action ===\n");
            outputArea.append("All access attempts are logged by the proxy\n");
        });
        
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Main method to launch the application
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MedicalClinicGUI().setVisible(true);
        });
    }
}
