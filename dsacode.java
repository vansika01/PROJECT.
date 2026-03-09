import java.util.*;
class Node {
    String currency;
    double rate;
    Node next;

    Node(String currency, double rate) {
        this.currency = currency;
        this.rate = rate;
        this.next = null;
    }
}


class CurrencyLinkedList {
    Node head;

   
    void insert(String currency, double rate) {
        Node newNode = new Node(currency, rate);

        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null)
                temp = temp.next;

            temp.next = newNode;
        }
    }


    double search(String currency) {
        Node temp = head;

        while (temp != null) {
            if (temp.currency.equals(currency))
                return temp.rate;

            temp = temp.next;
        }
        return -1;
    }
}

public class CurrencyConverterDSA {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        HashMap<String, Double> rates = new HashMap<>();
        rates.put("USD", 1.0);
        rates.put("INR", 83.0);
        rates.put("EUR", 0.92);
        rates.put("GBP", 0.78);

       
        CurrencyLinkedList list = new CurrencyLinkedList();
        list.insert("USD", 1.0);
        list.insert("INR", 83.0);
        list.insert("EUR", 0.92);
        list.insert("GBP", 0.78);


        Stack<String> historyStack = new Stack<>();

      
        Queue<String> requestQueue = new LinkedList<>();

        System.out.println("Currency Converter");

        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();

        System.out.print("From Currency (USD/INR/EUR/GBP): ");
        String from = sc.next().toUpperCase();

        System.out.print("To Currency (USD/INR/EUR/GBP): ");
        String to = sc.next().toUpperCase();

       
        requestQueue.add(from + " to " + to);

    
        double fromRate = list.search(from);
        double toRate = list.search(to);

        if (fromRate == -1 || toRate == -1) {
            System.out.println("Invalid Currency!");
            return;
        }


        double usdAmount = amount / fromRate;
        double converted = usdAmount * toRate;

        System.out.println("Converted Amount: " + converted + " " + to);

      
        historyStack.push(amount + " " + from + " -> " + converted + " " + to);

        System.out.println("\nConversion History (Stack):");
        while (!historyStack.isEmpty()) {
            System.out.println(historyStack.pop());
        }
        System.out.println("\nProcessed Requests (Queue):");
        while (!requestQueue.isEmpty()) {
            System.out.println(requestQueue.poll());
        }

        sc.close();
    }
}