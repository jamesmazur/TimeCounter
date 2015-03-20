package code;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class Driver {
    public static Date programLaunchDate;
    public static Date startDate;

    public static void main(String[] args) {
        // Begin by setting the program launch date appropriately.
        programLaunchDate = new Date();

        // Next, perform a sanity check on the command line arguments.
        if (args.length > 2 || (args.length == 2 && !args[0].equals("-f"))) {
            System.out.println("Argument must either be a date, or else '-f' followed by the name "
                    + "of a file containing a date. You may also choose not to enter any "
                    + "arguments, in which case the start date will be the program launch date.");
            System.exit(0);
        }
        
        // Now get the start date.
        if (args.length == 0) {
            startDate = programLaunchDate;
        } else if (args.length == 1) {
            try {
                startDate = (new SimpleDateFormat("MMMM d, yyyy - HH:mm")).parse(args[0]);
            } catch (ParseException e) {
                System.out.println("Parsing the start date string failed.");
                System.exit(1);
            }
        } else { // Arguments are -f followed by a file name.
            try {
                BufferedReader dateFileReader = new BufferedReader(new FileReader(args[1]));
                String dateString = null; // Only initialized to make Eclipse be quiet.
                try {
                    dateString = dateFileReader.readLine();
                    dateFileReader.close();
                } catch (IOException e) {
                    System.out.println("There was an I/O error when trying to read the specified "
                            + "date file.");
                    System.exit(1);
                }
                try {
                    startDate = (new SimpleDateFormat("MMMM d, yyyy - HH:mm")).parse(dateString);
                } catch (ParseException e) {
                    System.out.println("Parsing the start date string from the file failed.");
                    System.exit(1);
                } catch (NullPointerException e) {
                    System.out.println("Parsing the start date string from the file failed.");
                    System.exit(1);
                }
            } catch (FileNotFoundException e) {
                System.out.println("The date file specified could not be found.");
                System.exit(1);
            }
        }
        
        // Now, create myFrame.
        JFrame myFrame = new JFrame("Progress");
        myFrame.setSize(520, 80);
        myFrame.setLocationRelativeTo(null); // Center myFrame on screen.
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set up the progress bar for myFrame.
        JProgressBar timeProgressBar = new JProgressBar();
        timeProgressBar.setMaximum(1000000);
        timeProgressBar.setUI(new BasicProgressBarUI() { // Make the font color
                                                         // black.
                    protected Color getSelectionBackground() {
                        return Color.black;
                    }

                    protected Color getSelectionForeground() {
                        return Color.black;
                    }
                });
        timeProgressBar.setStringPainted(true); // Show text on the progress
                                                // bar.
        timeProgressBar.setFont(new Font("SansSerif", Font.BOLD, 24));
        myFrame.add(timeProgressBar);

        // Set up the timer to update the progress bar, show the frame, and then
        // start the timer.
        ProgressTimerAndListener myProgressTimer = new ProgressTimerAndListener(timeProgressBar);
        myFrame.setVisible(true);
        myProgressTimer.start();
    }
}