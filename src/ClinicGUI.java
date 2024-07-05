import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.ArrayList;

public class ClinicGUI {

    // all component
    ClinicSystem clinic;
    JFrame frame;
    JPanel side, mainPanel, queuePanel, addPatientPanel, addDoctorPanel, showHistoryPanel;
    JPanel displayPanel, registPanel, listPanel, historyPanel, sortPanel;
    JButton queue, addPatient, addDoctor, showHistory;
    JButton addPatientButton, cancelButton, sortButton, resetButton;
    JLabel title, header1, header2, header3, header4, noData, noData2;
    JLabel nameLabel, ageLabel, categoryLabel, descLabel;
    JLabel nameData, categoryData, timeData, nameData2, categoryData2, timeData2;
    JLabel nameDoctor, specialityDoctor;
    JTextField namePatientField, agePatientField;
    JComboBox<String> categoryBox;
    JTextArea descPatientField;
    CardLayout cardLayout;
    GridBagConstraints gbc;
    JScrollPane scroll, scroll2;
    PatientQueue defaultQueue, currentQueue;

    // all font
    Font titleFont = new Font("Poppins", Font.BOLD, 20);
    Font funcBtnFont = new Font("Poppins", Font.BOLD, 15);
    Font headerFont = new Font("Poppins", Font.BOLD, 18);
    Font labelFont = new Font("Poppins", Font.BOLD, 13);
    Font fieldFont = new Font("Poppins", Font.PLAIN, 13);
    Font categoryFont = new Font("Poppins", Font.PLAIN, 18);

    // dimension
    Dimension textField = new Dimension(300, 25);
    Dimension labelText = new Dimension(150, 25);
    Dimension sideButton = new Dimension(160, 32);
    Dimension actionButton = new Dimension(160, 25);
    Dimension displayData = new Dimension(580, 50);
    
    public ClinicGUI() {

        // settings
        clinic = new ClinicSystem();
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // side panel
        side = new JPanel();
        side.setBackground(new Color(0x03125E));
        side.setBounds(0, 0, 200,400);
        side.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));

        // title logo
        title = new JLabel("KLINIK UDAYANA");
        title.setFont(titleFont);
        title.setForeground(new Color(0xFFFFFF));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(null);
        title.setBounds(0, 20, 200, 60);
        side.add(title);
        
        // side menu button
        queue = new JButton("Antrian Pasien");
        queue.setFont(funcBtnFont);
        queue.setForeground(new Color(0x03125E));
        queue.setHorizontalAlignment(JLabel.CENTER);
        queue.setPreferredSize(sideButton);
        side.add(queue);
        addPatient = new JButton("Daftar Pasien");
        addPatient.setFont(funcBtnFont);
        addPatient.setForeground(new Color(0x03125E));
        addPatient.setHorizontalAlignment(JLabel.CENTER);
        addPatient.setPreferredSize(sideButton);
        side.add(addPatient);
        addDoctor = new JButton("Data Dokter");
        addDoctor.setFont(funcBtnFont);
        addDoctor.setForeground(new Color(0x03125E));
        addDoctor.setHorizontalAlignment(JLabel.CENTER);
        addDoctor.setPreferredSize(sideButton);
        side.add(addDoctor);
        showHistory = new JButton("Riwayat");
        showHistory.setFont(funcBtnFont);
        showHistory.setForeground(new Color(0x03125E));
        showHistory.setHorizontalAlignment(JLabel.CENTER);
        showHistory.setPreferredSize(sideButton);
        side.add(showHistory);

        // Queue Panel
        queuePanel = new JPanel(new BorderLayout());
        queuePanel.setBackground(new Color(0xFFFFFF));
        queuePanel.setBounds(200, 0, 600,400);
        // display queue
        displayPanel = new JPanel();
        scroll = new JScrollPane(displayPanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        updateQueuePanel();
        queuePanel.add(displayPanel, BorderLayout.CENTER);
        // Queue header
        header1 = new JLabel("Antrian Pasien");
        header1.setFont(headerFont);
        header1.setForeground(new Color(0x03125E));
        header1.setBackground(new Color(0xB5EDFF));
        header1.setHorizontalTextPosition(JLabel.LEFT);
        header1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        queuePanel.add(header1, BorderLayout.NORTH);

        // add Patient Panel
        addPatientPanel = new JPanel(new BorderLayout());
        addPatientPanel.setBackground(new Color(0xFFFFFF));
        addPatientPanel.setBounds(200, 0, 600,400);
        // patient registration
        registPanel = new JPanel(new GridBagLayout());
        // patient name label
        nameLabel = new JLabel("Nama                :");
        nameLabel.setFont(labelFont);
        nameLabel.setPreferredSize(labelText);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        registPanel.add(nameLabel, gbc);
        // patient name textfield
        namePatientField = new JTextField(15);
        namePatientField.setFont(fieldFont);
        namePatientField.setPreferredSize(textField);
        namePatientField.setBorder(null);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        registPanel.add(namePatientField, gbc);
        // patient age label
        ageLabel = new JLabel("Umur                 :");
        ageLabel.setFont(labelFont);
        ageLabel.setPreferredSize(labelText);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        registPanel.add(ageLabel, gbc);
        // patient age textfield
        agePatientField = new JTextField(15);
        agePatientField.setFont(fieldFont);
        agePatientField.setPreferredSize(textField);
        agePatientField.setBorder(null);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        registPanel.add(agePatientField, gbc);
        // patient category label
        categoryLabel = new JLabel("Kategori        :");
        categoryLabel.setFont(labelFont);
        categoryLabel.setPreferredSize(labelText);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        registPanel.add(categoryLabel, gbc);
        // patient category textfield
        String[] category = {"Umum", "KIA dan KB", "THT", "Gigi dan Mulut", "Gizi", "Psikologi", "Fisioterapi"};
        categoryBox = new JComboBox<>(category);
        categoryBox.setFont(fieldFont);
        categoryBox.setPreferredSize(textField);
        categoryBox.setBorder(null);
        categoryBox.setSelectedIndex(-1);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        registPanel.add(categoryBox, gbc);
        // patient description label
        descLabel = new JLabel("Keluhan         :");
        descLabel.setFont(labelFont);
        descLabel.setPreferredSize(labelText);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        registPanel.add(descLabel, gbc);
        // patient description textfield
        descPatientField = new JTextArea();
        descPatientField.setFont(fieldFont);
        descPatientField.setPreferredSize(new Dimension(300, 100));
        descPatientField.setBorder(null);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        registPanel.add(descPatientField, gbc);
        // cancel button
        cancelButton = new JButton("Batalkan");
        cancelButton.setFont(funcBtnFont);
        cancelButton.setForeground(new Color(0x03125E));
        cancelButton.setHorizontalAlignment(JLabel.CENTER);
        cancelButton.setPreferredSize(actionButton);
        gbc.gridx = 0;
        gbc.gridy = 4;
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                namePatientField.setText("");
                agePatientField.setText("");
                categoryBox.setSelectedIndex(-1);
                descPatientField.setText("");
            }
        });
        registPanel.add(cancelButton, gbc);
        // add data button
        addPatientButton = new JButton("Tambah Data");
        addPatientButton.setFont(funcBtnFont);
        addPatientButton.setForeground(new Color(0x03125E));
        addPatientButton.setHorizontalAlignment(JLabel.CENTER);
        addPatientButton.setPreferredSize(actionButton);
        gbc.gridx = 1;
        gbc.gridy = 4;
        addPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = namePatientField.getText();
                int age = Integer.parseInt(agePatientField.getText());
                String category = (String) categoryBox.getSelectedItem();
                String description = descPatientField.getText();
                clinic.registerPatient(name, age, category, description);
                JOptionPane.showMessageDialog(frame, "Pasien Berhasil Didaftarkan.", "info", JOptionPane.INFORMATION_MESSAGE);
                updateQueuePanel();
                startService();
                cardLayout.show(mainPanel, "Queue Panel");
            }
        });
        registPanel.add(addPatientButton, gbc);
        // patient header
        header2 = new JLabel("Daftar Pasien");
        header2.setFont(headerFont);
        header2.setForeground(new Color(0x03125E));
        header2.setBackground(new Color(0xB5EDFF));
        header2.setHorizontalTextPosition(JLabel.LEFT);
        header2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // add component to patient panel
        addPatientPanel.add(registPanel);
        addPatientPanel.add(header2, BorderLayout.NORTH);

        // add Doctor Panel
        addDoctorPanel = new JPanel(new BorderLayout());
        addDoctorPanel.setBackground(new Color(0xFFFFFF));
        addDoctorPanel.setBounds(200, 0, 600, 400);
        // add doctor list
        clinic.addDoctor("Dr. Wayan", "Umum");
        clinic.addDoctor("Dr. Putu", "KIA dan KB");
        clinic.addDoctor("Dr. Made", "THT");
        clinic.addDoctor("Dr. Gede", "Gizi");
        clinic.addDoctor("Dr. Nyoman", "Psikologi");
        clinic.addDoctor("Dr. Ketut", "Fisioterapi");
        clinic.addDoctor("Dr. Nengah", "Gigi dan Mulut");
        // show doctor list
        listPanel = new JPanel();
        doctorPanel(clinic.getDoctorList());
        addDoctorPanel.add(listPanel, BorderLayout.CENTER);
        // doctor header
        header3 = new JLabel("Data Dokter");
        header3.setFont(headerFont);
        header3.setForeground(new Color(0x03125E));
        header3.setBackground(new Color(0xB5EDFF));
        header3.setHorizontalTextPosition(JLabel.LEFT);
        header3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        addDoctorPanel.add(header3, BorderLayout.NORTH);

        // History Panel
        showHistoryPanel = new JPanel(new BorderLayout());
        showHistoryPanel.setBackground(new Color(0xFFFFFF));
        showHistoryPanel.setBounds(200, 0, 600, 400);
        // display history panel
        historyPanel = new JPanel();
        scroll2 = new JScrollPane(historyPanel);
        scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        updateHistoryPanel(clinic.getDonePatients().getOriginalOrder());
        showHistoryPanel.add(historyPanel, BorderLayout.CENTER);
        // sort panel
        sortPanel = new JPanel(new GridBagLayout());
        sortPanel.setPreferredSize(new Dimension(580, 60));
        // sort dan reset button
        sortButton = new JButton("Sort by Name");
        sortButton.setFont(funcBtnFont);
        sortButton.setForeground(new Color(0x03125E));
        sortButton.setHorizontalAlignment(JLabel.CENTER);
        sortButton.setPreferredSize(actionButton);
        gbc.insets = new Insets(5, 30, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        sortPanel.add(sortButton, gbc);
        resetButton = new JButton("Reset Order");
        resetButton.setFont(funcBtnFont);
        resetButton.setForeground(new Color(0x03125E));
        resetButton.setHorizontalAlignment(JLabel.CENTER);
        resetButton.setPreferredSize(actionButton);
        gbc.insets = new Insets(5, 5, 5, 30);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 1;
        gbc.gridy = 0;
        sortPanel.add(resetButton, gbc);
        // set sort panel
        sortPanel.setBackground(Color.LIGHT_GRAY);
        showHistoryPanel.add(sortPanel, BorderLayout.SOUTH);
        // history header
        header4 = new JLabel("Riwayat Pemeriksaan");
        header4.setFont(headerFont);
        header4.setForeground(new Color(0x03125E));
        header4.setBackground(new Color(0xB5EDFF));
        header4.setHorizontalTextPosition(JLabel.LEFT);
        header4.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        showHistoryPanel.add(header4, BorderLayout.NORTH);

        // card layout dan main panel
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBounds(200, 0, 600,400);
        mainPanel.add(queuePanel, "Queue Panel");
        mainPanel.add(addPatientPanel, "Patient Panel");
        mainPanel.add(addDoctorPanel, "Doctor Panel");
        mainPanel.add(showHistoryPanel, "History Panel");

        // actionlistener pada side button 
        queue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateQueuePanel();
                cardLayout.show(mainPanel, "Queue Panel");
            }
        });
        addPatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                namePatientField.setText("");
                agePatientField.setText("");
                categoryBox.setSelectedIndex(-1);
                descPatientField.setText("");
                cardLayout.show(mainPanel, "Patient Panel");
            }
        });
        addDoctor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(mainPanel, "Doctor Panel");
            }
        });
        showHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                updateHistoryPanel(clinic.getDonePatients().getCurrentOrder());
                cardLayout.show(mainPanel, "History Panel");
            }
        });

        // actionlistener pada sort dan reset button
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Patient> patients = clinic.getDonePatients().getCurrentOrder();
                selectionSort(patients);
                updateHistoryPanel(patients);
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Patient> originalPatients = clinic.getDonePatients().getOriginalOrder();
                updateHistoryPanel(originalPatients);
            }
        });

        // full frame
        frame = new JFrame();
        frame.setLayout(null);
        frame.setTitle("Sistem Manajemen Klinik");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(815, 435);
        frame.getContentPane().setBackground(new Color(0xFFFFFF));
        frame.setLocationRelativeTo(null);
        frame.add(side, BorderLayout.WEST);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setResizable(false);

        startService();
    }

    void updateHistoryPanel(ArrayList<Patient> patients) {
        // gridbag
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        // setup ulang history panel
        historyPanel.removeAll();
        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS));
        if (patients == null) {
            // beritahu tidak ada pasien
            noData2 = new JLabel("Tidak ada pasien yang telah dilayani.");
            noData2.setFont(fieldFont);
            JPanel noDataHPanel = new JPanel(new GridBagLayout());
            noDataHPanel.add(noData2, gbc);
            historyPanel.add(noDataHPanel);
            patients = new ArrayList<>();
        } else {
            // tambahkan panel pada setiap patient
            for (Patient patient : patients) {
                JPanel patientHPanel = createHistoryPanel(patient);
                historyPanel.add(patientHPanel, gbc);
            }
        }
        // atur history panel
        historyPanel.revalidate();
        historyPanel.repaint();
    }

    void updateQueuePanel() {
        // gridbag
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        // setup ulang display panel
        displayPanel.removeAll();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        // ambil nilai current node dari front
        QueueNode currentNode = clinic.getPatientQueue().getFront();
        if (currentNode == null) {
            // beritahu tidak ada pasien
            noData = new JLabel("Tidak ada pasien dalam antrian.");
            noData.setFont(fieldFont);
            JPanel noDataPanel = new JPanel(new GridBagLayout());
            noDataPanel.add(noData, gbc);
            displayPanel.add(noDataPanel);
        } else {
            // tambahkan panel pada setiap patient
            while (currentNode != null) {
                Patient patient = currentNode.patient;
                JPanel patientPanel = createPatientPanel(patient);
                displayPanel.add(patientPanel);
                currentNode = currentNode.next;
            }
        }
        // atur display panel
        displayPanel.revalidate();
        displayPanel.repaint();
    }

    // create panel baru pada setiap riwayat pemeriksaan
    private JPanel createHistoryPanel(Patient patient) {
        // gridbag
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        // atur panel pembungkus luar
        JPanel hPanel = new JPanel(new BorderLayout());
        hPanel.setMaximumSize(new Dimension(590, 60));
        hPanel.setBackground(new Color(0xF0F0F0));
        // panel pembungkus dalam
        JPanel dataHPanel = new JPanel(new GridBagLayout());
        dataHPanel.setPreferredSize(displayData);
        dataHPanel.setBackground(new Color(0xB5EDFF));
        // tampilkan data nama
        nameData2 = new JLabel(patient.getName());
        nameData2.setFont(headerFont);
        gbc.gridx = 0;
        dataHPanel.add(nameData2, gbc);
        // tampilkan data kategori
        categoryData2 = new JLabel(patient.getCategory());
        categoryData2.setFont(fieldFont);
        gbc.gridx = 1;
        dataHPanel.add(categoryData2, gbc);
        // tampilkan data waktu
        timeData2 = new JLabel(patient.getRegistTime());
        timeData2.setFont(fieldFont);
        gbc.gridx = 2;
        dataHPanel.add(timeData2, gbc);
        // add dan return
        hPanel.add(dataHPanel, BorderLayout.NORTH);
        return hPanel;
    }

    // create panel baru pada setiap antrian pasien
    private JPanel createPatientPanel(Patient patient) {
        // gridbag
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        // atur panel pembungkus luar
        JPanel panel = new JPanel(new BorderLayout());
        panel.setMaximumSize(new Dimension(590, 60));
        panel.setBackground(new Color(0xF0F0F0));
        // panel pembungkus dalam
        JPanel dataPanel = new JPanel(new GridBagLayout());
        dataPanel.setPreferredSize(displayData);
        dataPanel.setBackground(new Color(0xB5EDFF));
        // tampilkan data nama
        nameData = new JLabel(patient.getName());
        nameData.setFont(headerFont);
        gbc.gridx = 0;
        dataPanel.add(nameData, gbc);
        // tampilkan data kategori
        categoryData = new JLabel(patient.getCategory());
        categoryData.setFont(fieldFont);
        gbc.gridx = 1;
        dataPanel.add(categoryData, gbc);
        // tampilkan data waktu
        timeData = new JLabel(patient.getRegistTime());
        timeData.setFont(fieldFont);
        gbc.gridx = 2;
        dataPanel.add(timeData, gbc);
        // add dan return
        panel.add(dataPanel, BorderLayout.NORTH);
        return panel;
    }

    private void doctorPanel(LinkedList<Doctor> doctors) {
        // gridbag
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 10, 2, 10);
        // setup ulang list panel
        listPanel.removeAll();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        // tambahkan data setiap dokter
        int number = 1;
        for(Doctor doctor : doctors) {
            // atur gridbag
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weightx = 1.0;
            // atur panel pembungkus luar
            JPanel dpanel = new JPanel(new BorderLayout());
            dpanel.setMaximumSize(new Dimension(590, 45));
            dpanel.setBackground(new Color(0xF0F0F0));
            // panel pembungkus dalam
            JPanel dataDPanel = new JPanel(new GridBagLayout());
            dataDPanel.setPreferredSize(displayData);
            dataDPanel.setBackground(new Color(0xB5EDFF));
            // tampilkan data nama dan speciality
            nameDoctor = new JLabel(number + ". " + doctor.getName() + " : Spesialitas Dokter " + doctor.getSpeciality());
            nameDoctor.setFont(funcBtnFont);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            dataDPanel.add(nameDoctor, gbc);
            // add component
            dpanel.add(dataDPanel, BorderLayout.NORTH);
            listPanel.add(dpanel);
            listPanel.add(Box.createRigidArea(new Dimension(0, 3)));
            number++;
        }
        // atur list panel
        listPanel.revalidate();
        listPanel.repaint();
    }

    public void selectionSort(ArrayList<Patient> patients) {
        int n = patients.size();
        for (int i = 0; i < n-1; i++) {
            int minIndex = i;
            for (int j = i+1; j < n; j++) {
                if (patients.get(j).getName().compareToIgnoreCase(patients.get(minIndex).getName()) < 0) {
                    minIndex = j;
                }
            }
            Patient temp = patients.get(minIndex);
            patients.set(minIndex, patients.get(i));
            patients.set(i, temp);
        }
    }

    public void startService() {
        if(!clinic.getPatientQueue().isEmpty()) {
            clinic.servePatient(this);
        }
    }

    public JFrame getFrame() {
        return frame;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClinicGUI();
            }
        });
    }

}