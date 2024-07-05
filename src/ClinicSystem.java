import java.util.*;
import java.util.concurrent.*;

public class ClinicSystem {

    private PatientQueue patientQueue, donePatientQueue;
    private LinkedList<Doctor> doctors;
    private ExecutorService executorService;
    private Queue<Patient> inServiceQueue;

    public ClinicSystem() {
        patientQueue = new PatientQueue();
        donePatientQueue = new PatientQueue();
        doctors = new LinkedList<>();
        executorService = Executors.newFixedThreadPool(5);
        inServiceQueue = new LinkedList<>();
    }

    public void registerPatient(String name, int age, String category, String description) {
        Patient newPatient = new Patient(name, age, category, description);
        patientQueue.enqueue(newPatient);
    }

    public void addDoctor(String name, String speciality) {
        Doctor newDoctor = new Doctor(name, speciality);
        doctors.add(newDoctor);
    }

    private int getServiceTime(String speciality) {
        switch (speciality) {
            case "Umum":
                return 35000;
            case "KIA dan KB":
                return 50000;
            case "Gigi dan Mulut":
                return 40000;
            case "Gizi":
                return 35000;
            case "Psikologi":
                return 45000;
            case "Fisioterapi":
                return 55000;
            default:
                return 30000;
        }
    }

    private Doctor findAvailableDoctor(String speciality) {
        for (Doctor doctor : doctors) {
            if (doctor.getSpeciality().equals(speciality) && doctor.isAvailable()) {
                return doctor;
            }
        }
        return null;
    }

    public LinkedList<Doctor> getDoctorList() {
        return doctors;
    }

    public PatientQueue getPatientQueue() {
        return patientQueue;
    }

    public void servePatient(ClinicGUI gui) {
        if(patientQueue.isEmpty()) return;
        QueueNode current = patientQueue.getFront();
        while (current != null && current.patient.isServe) {
            current = current.next;
        }
        if(current == null) return;
        Patient patient = current.patient;
        Doctor doctor = findAvailableDoctor(patient.getCategory());
        if (doctor != null) {
            patient.isServe = true;
            doctor.setAvailable(false);
            int serviceTime = getServiceTime(doctor.getSpeciality());
            ServiceThread serviceThread = new ServiceThread(patient, doctor, serviceTime, gui, new ServiceCallback() {
                @Override
                public void onServiceCompleted() {
                    System.out.println("Pasien " + patient.getName() + " selesai dilayani.");
                    doctor.setAvailable(true);
                    patientQueue.dequeue();
                    serviceComplete(patient, gui);
                }
            });
            executorService.submit(serviceThread);
        } else {
            System.out.println("Tidak ada dokter tersedia untuk pasien " + patient.getName());
        }
    }

    public void serviceComplete(Patient patient, ClinicGUI gui) {
        inServiceQueue.remove(patient);
        donePatientQueue.enqueue(patient);
        gui.updateQueuePanel();
        gui.updateHistoryPanel(donePatientQueue.getCurrentOrder());
        checkAndServePatient(gui);
    }

    public void shutDownService() {
        executorService.shutdown();
    }

    private void checkAndServePatient(ClinicGUI gui) {
        for (Doctor doctor : doctors) {
            if (doctor.isAvailable()) {
                servePatient(gui);
                break;
            }
        }
    }

    public PatientQueue getDonePatients() {
        return donePatientQueue;
    }

}