import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BusBookingSystemApp {
    private static final int TOTAL_SEATS = 20;
    private static boolean[] seatAvailability = new boolean[TOTAL_SEATS];

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Bus Booking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

        JButton displaySeatsButton = new JButton("Display Available Seats");
        displaySeatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAvailableSeats();
            }
        });

        JButton bookSeatButton = new JButton("Book a Seat");
        bookSeatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookSeat();
            }
        });

        JButton checkStatusButton = new JButton("Check Booking Status");
        checkStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBookingStatus();
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(displaySeatsButton);
        panel.add(bookSeatButton);
        panel.add(checkStatusButton);
        panel.add(exitButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void displayAvailableSeats() {
        StringBuilder availableSeats = new StringBuilder("Available Seats:\n");

        for (int i = 0; i < TOTAL_SEATS; i++) {
            if (!seatAvailability[i]) {
                availableSeats.append((i + 1)).append(" ");
            }
        }

        JOptionPane.showMessageDialog(null, availableSeats.toString(), "Available Seats", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void bookSeat() {
        String seatNumberString = JOptionPane.showInputDialog("Enter Seat Number to Book:");

        try {
            int seatNumber = Integer.parseInt(seatNumberString);

            if (isValidSeat(seatNumber) && !seatAvailability[seatNumber - 1]) {
                seatAvailability[seatNumber - 1] = true;
                JOptionPane.showMessageDialog(null, "Booking Successful! Seat " + seatNumber + " booked.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid seat number or seat already booked. Please choose another seat.", "Booking Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid seat number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void checkBookingStatus() {
        String seatNumberString = JOptionPane.showInputDialog("Enter Seat Number to Check Booking Status:");

        try {
            int seatNumber = Integer.parseInt(seatNumberString);

            if (isValidSeat(seatNumber)) {
                if (seatAvailability[seatNumber - 1]) {
                    JOptionPane.showMessageDialog(null, "Seat " + seatNumber + " is booked.", "Booking Status", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Seat " + seatNumber + " is available.", "Booking Status", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a valid seat number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid seat number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static boolean isValidSeat(int seatNumber) {
        return seatNumber >= 1 && seatNumber <= TOTAL_SEATS;
    }
}
