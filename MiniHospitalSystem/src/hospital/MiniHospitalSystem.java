package hospital;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Patient {
    int patientId;
    String patientName;
    int age;
    String contactNumber;
    String medicalCondition;
    VisitHistoryLinkedList visitHistory;

    public Patient(int patientId, String patientName, int age, String contactNumber, String medicalCondition) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.age = age;
        this.contactNumber = contactNumber;
        this.medicalCondition = medicalCondition;
        this.visitHistory = new VisitHistoryLinkedList();
    }

    @Override
    public String toString() {
        return "ID: " + patientId + " | Name: " + patientName + " | Age: " + age +
               " | Contact: " + contactNumber + " | Condition: " + medicalCondition;
    }
}

class BSTNode {
    Patient patient;
    BSTNode left, right;

    public BSTNode(Patient patient) {
        this.patient = patient;
        this.left = this.right = null;
    }
}

class PatientBST {
    private BSTNode root;

    public void insert(Patient patient) {
        root = insertRec(root, patient);
    }

    private BSTNode insertRec(BSTNode root, Patient patient) {
        if (root == null) {
            root = new BSTNode(patient);
            return root;
        }
        if (patient.patientId < root.patient.patientId) {
            root.left = insertRec(root.left, patient);
        } else if (patient.patientId > root.patient.patientId) {
            root.right = insertRec(root.right, patient);
        }
        return root;
    }

    public void displayInOrder() {
        inOrderRec(root);
    }

    private void inOrderRec(BSTNode root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.println(root.patient);
            inOrderRec(root.right);
        }
    }
}

class EmergencyQueue {
    private Queue<Patient> queue = new LinkedList<>();

    public void enqueue(Patient patient) {
        queue.add(patient);
        System.out.println("රෝගී " + patient.patientName + " හදිසි පෝලිමට ඇතුළත් කරන ලදී.");
    }

    public Patient dequeue() {
        if (queue.isEmpty()) return null;
        Patient p = queue.poll();
        System.out.println("හදිසි ප්‍රතිකාර ලබාදීම: " + p.patientName);
        return p;
    }

    public void displayQueue() {
        for (Patient p : queue) {
            System.out.println("- " + p.patientName + " (ID: " + p.patientId + ")");
        }
    }
}

class TreatmentRecord {
    int patientId;
    String treatmentDetails;
    String date;

    public TreatmentRecord(int patientId, String treatmentDetails, String date) {
        this.patientId = patientId;
        this.treatmentDetails = treatmentDetails;
        this.date = date;
    }

    @Override
    public String toString() {
        return "දිනය: " + date + " | Patient ID: " + patientId + " | විස්තර: " + treatmentDetails;
    }
}

class TreatmentStack {
    private Stack<TreatmentRecord> stack = new Stack<>();

    public void push(TreatmentRecord record) {
        stack.push(record);
    }

    public void displayStack() {
        for (int i = stack.size() - 1; i >= 0; i--) {
            System.out.println(stack.get(i));
        }
    }
}

class VisitNode {
    int visitId;
    String visitDate;
    String doctorName;
    String diagnosis;
    String treatment;
    VisitNode next;

    public VisitNode(int visitId, String visitDate, String doctorName, String diagnosis, String treatment) {
        this.visitId = visitId;
        this.visitDate = visitDate;
        this.doctorName = doctorName;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.next = null;
    }

    @Override
    public String toString() {
        return "Visit ID: " + visitId + " | දිනය: " + visitDate + " | දොස්තර: " + doctorName +
               " | රෝග විනිශ්චය: " + diagnosis + " | ප්‍රතිකාර: " + treatment;
    }
}

class VisitHistoryLinkedList {
    private VisitNode head;

    public void addVisit(int visitId, String visitDate, String doctorName, String diagnosis, String treatment) {
        VisitNode newVisit = new VisitNode(visitId, visitDate, doctorName, diagnosis, treatment);
        if (head == null) {
            head = newVisit;
        } else {
            VisitNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newVisit;
        }
    }

    public void displayVisits() {
        VisitNode temp = head;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

public class MiniHospitalSystem {
    public static void main(String[] args) {
        PatientBST bst = new PatientBST();
        EmergencyQueue queue = new EmergencyQueue();
        TreatmentStack stack = new TreatmentStack();

        System.out.println("--- 1. Testing BST (Patient Records) ---");
        Patient p1 = new Patient(102, "Alice Smith", 30, "0712345678", "Fever");
        Patient p2 = new Patient(101, "Bob Jones", 45, "0778765432", "Fracture");
        Patient p3 = new Patient(103, "Charlie Brown", 50, "0751122334", "Chest Pain");

        bst.insert(p1);
        bst.insert(p2);
        bst.insert(p3);
        bst.displayInOrder();

        System.out.println("\n--- 2. Testing Emergency Queue ---");
        queue.enqueue(p2);
        queue.enqueue(p3);
        queue.displayQueue();
        queue.dequeue();

        System.out.println("\n--- 3. Testing Treatment History (Stack) ---");
        stack.push(new TreatmentRecord(101, "Administered X-Ray", "2026-09-07"));
        stack.push(new TreatmentRecord(103, "ECG Performed", "2026-09-07"));
        stack.displayStack();

        System.out.println("\n--- 4. Testing Visit History (Linked List) ---");
        p1.visitHistory.addVisit(1, "2026-01-10", "Dr. Adams", "Flu", "Prescribed Rest");
        p1.visitHistory.displayVisits();
    }
}
