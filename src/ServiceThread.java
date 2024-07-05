import javax.swing.*;

public class ServiceThread implements Runnable {
    
    private Patient patient;
    private Doctor doctor;
    private int serviceTime;
    private ClinicGUI gui;
    private ServiceCallback callback;

    public ServiceThread(Patient patient, Doctor doctor, int serviceTime, ClinicGUI gui, ServiceCallback callback) {
        this.patient = patient;
        this.doctor = doctor;
        this.serviceTime = serviceTime;
        this.gui = gui;
        this.callback = callback;
    }

    @Override
    public void run() {
        // doctor.setAvailable(false);
        System.out.println("Melayani pasien " + patient.getName() + " dengan " + doctor.getName());
        try {
            Thread.sleep(serviceTime);
        } catch (InterruptedException e) {
            JOptionPane.showMessageDialog(gui.frame, "Interupsi pada pasien " + patient.getName(), "warning", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        } finally {
            doctor.setAvailable(true);
        }
        if(callback != null) {
            callback.onServiceCompleted();
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(gui.getFrame(), "Pasien " + patient.getName() + " selesai dilayani.", "info", JOptionPane.INFORMATION_MESSAGE);
                gui.updateQueuePanel();
            });
        }
    }
}
