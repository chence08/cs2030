import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numRecords = sc.nextInt();
        Roster roster = new Roster("");

        for (int i = 0; i < numRecords; i++) {
            // roster is immutable!
            roster = roster.add(sc.next(), sc.next(), sc.next(), sc.next());
        }

        while (sc.hasNext()) {
            System.out.println(roster.getGrade(sc.next(), sc.next(), sc.next()));
        }
        sc.close(); // good habit but not compulsory for this module.
    }
}
