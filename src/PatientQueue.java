import java.util.ArrayList;

public class PatientQueue {

    private QueueNode front;
    private QueueNode rear;
    private ArrayList<Patient> originalOrder;

    public PatientQueue() {
        this.front = null;
        this.rear = null;
    }

    public void enqueue(Patient patient) {
        QueueNode newNode = new QueueNode(patient);
        if (rear == null) {
            front = newNode;
            rear = newNode;
            return;
        }
        rear.next = newNode;
        rear = newNode;
        // if (originalOrder == null) {
        //     originalOrder = new ArrayList<>();
        //     originalOrder.add(patient);
        // } else originalOrder.add(patient);
    }

    public void dequeue() {
        if(front == null) return;
        front = front.next;
        if(front == null) rear = null;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public QueueNode getFront() {
        return front;
    }

    public ArrayList<Patient> getOriginalOrder() {
        originalOrder = getCurrentOrder();
        return originalOrder;
    }

    public ArrayList<Patient> getCurrentOrder() {
        ArrayList<Patient> currentList = new ArrayList<>();
        QueueNode currentNode = front;
        while (currentNode != null) {
            currentList.add(currentNode.patient);
            currentNode = currentNode.next;
        }
        return currentList;
    }

}
