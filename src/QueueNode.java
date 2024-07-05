class QueueNode {
    public Patient patient;
    public QueueNode next;

    public QueueNode(Patient patient) {
        this.patient = patient;
        this.next = null;
    }
}