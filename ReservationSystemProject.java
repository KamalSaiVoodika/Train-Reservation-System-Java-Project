import java.util.*;

public class ReservationSystemProject {
    public static void main(String args[]) {
        int userPin = 0000;
        String userId = "kamal143";
        List<TrainTicket> ticketsList = new ArrayList<>();
        List<Train> trainsList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        trainsList.add(new Train("01234", "Visakha Express"));
        trainsList.add(new Train("12345", "Mumbai Express"));
        trainsList.add(new Train("23456", "Chennai Express"));
        trainsList.add(new Train("34567", "Delhi Express"));
        

        while (true) {
            System.out.println("\n\t <<>> Welcome To IRCTC - Login Form <<>>\n");

            System.out.print("Please Enter UserId :: ");

            String enteredUserId = sc.next();

            System.out.print("Please Enter UserPin :: ");

            int enteredUserPin = sc.nextInt();

            if (enteredUserId.trim().equals(userId.trim()) && enteredUserPin == userPin) {

                System.out.println("\n>>> Login Successful!!\n");

                System.out.println("Choose 1 for Train Reservation Form");
                System.out.println("Choose 2 for Ticket Cancellation Form");

                System.out.println("Choose Any Other Number for EXIT");
                System.out.print("\nChoose the operation you want to perform :: ");

                int choice = sc.nextInt();
                // if (choice >= 1 && choice <= 3) {
                switch (choice) {
                    case 1:

                        System.out.println("\n\t <<>> Train Reservation Form <<>>\n");

                        System.out.print("Enter Your Name :: ");

                        String userName = sc.next();

                        System.out.print("Enter Gender :: ");

                        String gender = sc.next();

                        boolean proceed = true;

                        while (proceed) {

                            System.out.print("Enter Train Number :: ");

                            String trainNumber = sc.next();

                            boolean hasTrain = false;

                            for (Train train : trainsList) {
                                if (trainNumber.trim().equals(train.trainNumber)) {

                                    hasTrain = true;

                                    System.out.printf("\n>>> Train Name :: %s\n", train.trainName);

                                    System.out.print("\nEnter Class Type :: ");

                                    String classType = sc.next();

                                    System.out.print("Enter Date of Journey (YYYY/MM/DD) :: ");

                                    String dateOfJoining = sc.next();

                                    System.out.print("Enter From Place :: ");

                                    String fromPlace = sc.next();

                                    System.out.print("Enter To Place :: ");

                                    String toPlace = sc.next();

                                    System.out.println("\n>>> All Fields Are Filled!!\n");

                                    System.out.println("Choose 1 for Submit");
                                    System.out.println("Choose Any Other Number for Exit");

                                    System.out.print("\nChoose the operation you want to perform :: ");

                                    int mainChoice = sc.nextInt();

                                    if (mainChoice == 1) {

                                        Random rn = new Random();

                                        while (true) {
                                            int sevenDigitRandomNumber = rn.nextInt(9000000) + 1000000;

                                            int threeDigitRandomNumber = rn.nextInt(900) + 100;

                                            String pnrNumber = threeDigitRandomNumber + "-"
                                                    + sevenDigitRandomNumber;

                                            if (ticketsList.stream()
                                                    .filter(t -> t.pnrNumber.trim().equals(pnrNumber)).toList()
                                                    .isEmpty()) {
                                                ticketsList.add(new TrainTicket(userName, trainNumber, pnrNumber,
                                                        train.trainName, toPlace, fromPlace, dateOfJoining, classType,
                                                        gender));

                                                System.out.printf(
                                                        "\n>>> Train Reservation Successful!!, PNR Number -> %s\n",
                                                        pnrNumber);
                                                break;
                                            }

                                        }

                                    }

                                    proceed = false;

                                    break;

                                }
                            }
                            if (!hasTrain)
                                System.out.println("\n>>> Train Not Found!!\n");

                        }

                        break;

                    case 2:

                        System.out.println("\n\t <<>> Ticket Cancellation Form <<>>\n");

                        System.out.print("Enter PNR Number :: ");

                        String pnrNumber = sc.next();

                        boolean hasPnr = false;

                        for (int i = 0; i < ticketsList.size(); i++) {

                            TrainTicket ticket = ticketsList.get(i);

                            if (ticket.pnrNumber.trim().equals(pnrNumber.trim())) {
                                hasPnr = true;

                                System.out.printf(
                                        "\n>>> Ticket Details Fetched Successfully, of PNR Number -> %s\n",
                                        pnrNumber);

                                System.out.printf("\n>>> Name :: %s\n", ticket.name);

                                System.out.printf("\n>>> Gender :: %s\n", ticket.gender);

                                System.out.printf("\n>>> Train Number :: %s\n", ticket.trainNumber);

                                System.out.printf("\n>>> Train Name :: %s\n", ticket.trainName);

                                System.out.printf("\n>>> Date Of Journey :: %s\n", ticket.dateOfJoining);

                                System.out.printf("\n>>> From Place :: %s\n", ticket.fromPlace);

                                System.out.printf("\n>>> To Place :: %s\n", ticket.toPlace);

                                System.out.printf("\n>>> Class Type :: %s\n", ticket.classType);

                                System.out.println("\nChoose 1 for Cancel Ticket");
                                System.out.println("Choose Any Other Number for Exit");

                                System.out.print("\nChoose the operation you want to perform :: ");

                                int mainChoice = sc.nextInt();

                                if (mainChoice == 1) {
                                    ticketsList.remove(i);
                                    System.out.println("\n>>> Ticket Cancelled Successful!!\n");
                                }

                                break;
                            }

                        }

                        if (!hasPnr)
                            System.out.println("\n>>> Ticket Not Found!!");

                        break;

                    // default:
                    // // exit from the menu
                    // System.exit(0);
                }
                // } else {
                // System.out.println("\n>>> Please Enter Valid Choice!!\n");
                // }
            } else {
                System.out.println("\n>>> Please Enter Valid UserId And UserPin!!\n");
            }

        }
    }
}

class TrainTicket {
    String name, trainNumber, pnrNumber, trainName, toPlace, fromPlace, dateOfJoining, classType, gender;

    public TrainTicket(String name, String trainNumber, String pnrNumber, String trainName, String toPlace,
            String fromPlace, String dateOfJoining, String classType, String gender) {
        this.name = name;
        this.trainNumber = trainNumber;
        this.pnrNumber = pnrNumber;
        this.trainName = trainName;
        this.toPlace = toPlace;
        this.fromPlace = fromPlace;
        this.dateOfJoining = dateOfJoining;
        this.classType = classType;
        this.gender = gender;
    }
}

class Train {
    String trainNumber, trainName;

    public Train(String trainNumber, String trainName) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
    }
}