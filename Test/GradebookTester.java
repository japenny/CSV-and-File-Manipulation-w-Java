import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.Assert.*;

public class GradebookTester {

    public static void SystemOutPrintWrapper(String s) {
        System.out.print(s);
    }

    // Variable that will capture the output. Calling System.out.print will send data to this stream.
    private ByteArrayOutputStream captured_out = new ByteArrayOutputStream();

    // This is the original output to the stream. We need to restore it after the tests.
    private PrintStream original_out = System.out;

    // Prepares the output so we can capture it.
    public void setupTest(){
        System.setOut(new PrintStream(captured_out));
    }

    // Restores the original output to the screen after the tests are done.
    public void desetupTest() {
        System.setOut(original_out);
    }


    @Test
    public void printRow() throws IOException {
        Gradebook Strange = new Gradebook("CS/src/GradebookCopy.csv");
        Strange.printRow(22);
        assertEquals("Grades:88 98 78 99 100 100 100 Average:94.71 Name:Bruce Wayne",Strange.printrow(22) );
        System.out.println();

        Gradebook Parker = new Gradebook("CS/src/GradebookCopy1.csv");
        Parker.printRow(2);
        assertEquals("Grades:72 67 62 73 83 85 81 Average:74.71 Name:Bucky Barnes", Parker.printrow(2));
        System.out.println();

        Gradebook Stephen = new Gradebook("CS/src/GradebookCopy2.csv");
        Stephen.printRow(16);
        assertEquals("Grades:99 100 100 100 100 100 100 Average:99.86 Name:Jessica Jones", Stephen.printrow(16));

    }

    @Test
    public void averagePerAssignment() throws IOException {
        Gradebook Strange = new Gradebook("CS/src/GradebookCopy.csv");
        assertEquals("[74.67, 80.93, 75.37, 80.27, 84.3, 83.9, 81.9]",Arrays.toString(Strange.averagePerAssignment()));

        Gradebook Parker = new Gradebook("CS/src/GradebookCopy1.csv");
        assertEquals("[65.5, 67.83, 63.17, 65.7, 68.17, 69.4, 67.93]",Arrays.toString(Parker.averagePerAssignment()));

        Gradebook Stephen = new Gradebook("CS/src/GradebookCopy2.csv");
        assertEquals("[98.97, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0]",Arrays.toString(Stephen.averagePerAssignment()));

    }

    @Test
    public void largestVariation() throws IOException {
        Gradebook Strange = new Gradebook("CS/src/GradebookCopy.csv");
        assertEquals("Dinah Lance",Strange.largestVariation());

        Gradebook Parker = new Gradebook("CS/src/GradebookCopy1.csv");
        assertEquals("Ray Palmer", Parker.largestVariation());

        Gradebook Stephen = new Gradebook("CS/src/GradebookCopy2.csv");
        assertEquals("Natasha Romanoff",Stephen.largestVariation());

    }

    @Test
    public void sortStudentsByAverage() throws IOException {
        Gradebook Strange = new Gradebook("CS/src/GradebookCopy.csv");
        Strange.sortStudentsByAverage();
        assertEquals("[[66, 68, 68, 80, 72, 61, 84], [65, 62, 82, 74, 61, 95, 62], [67, 75, 67, 86, 64, 87, 64], [62, 96, 62, 62, 99, 67, 71], [72, 67, 62, 73, 83, 85, 81], [91, 80, 64, 68, 83, 69, 68], [68, 77, 73, 74, 91, 79, 66], [89, 74, 87, 74, 61, 77, 67], [70, 64, 60, 97, 80, 85, 84], [91, 74, 66, 70, 78, 95, 69], [75, 99, 78, 61, 72, 74, 84], [68, 98, 72, 79, 83, 72, 80], [73, 63, 76, 91, 90, 84, 76], [61, 73, 64, 86, 93, 99, 78], [89, 79, 92, 97, 67, 61, 70], [88, 60, 78, 79, 72, 94, 88], [79, 67, 78, 61, 84, 94, 98], [71, 95, 94, 84, 63, 94, 64], [72, 83, 76, 88, 86, 81, 85], [62, 99, 95, 80, 70, 70, 96], [99, 84, 76, 71, 99, 66, 82], [88, 89, 88, 96, 83, 80, 62], [85, 91, 63, 74, 97, 94, 88], [81, 96, 75, 61, 100, 88, 95], [50, 80, 76, 91, 100, 100, 100], [51, 86, 94, 70, 100, 100, 100], [95, 94, 63, 97, 98, 66, 95], [63, 81, 69, 95, 100, 100, 100], [61, 76, 85, 90, 100, 100, 100], [88, 98, 78, 99, 100, 100, 100]][Wanda Maximoff, Barry Allen, Stephen1 Murdock, Peter Parker, Bucky Barnes, James Rhodes, Tony Stark, Steve Rogers, Rachel Roth, Peter Quill, Jessica Jones, Garfield Logan, Victor Stone, Arthur Parker, Natasha Romanoff, Stephen2 Strange, Scott Lang, Ray Palmer, Luke Cage, Bruce Banner, Carol Denvers, Oliver Queen, Nick Fury, Clint Barton, Dinah Lance, Stephen3 Strange, Sam Strange, Hal Jordan, Clarke Kent, Bruce Wayne]\n",Strange.stringAll());

        Gradebook Parker = new Gradebook("CS/src/GradebookCopy1.csv");
        Parker.sortStudentsByAverage();
        assertEquals("[[0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 100, 0], [66, 68, 68, 80, 72, 61, 84], [65, 62, 82, 74, 61, 95, 62], [67, 75, 67, 86, 64, 87, 64], [62, 96, 62, 62, 99, 67, 71], [72, 67, 62, 73, 83, 85, 81], [91, 80, 64, 68, 83, 69, 68], [68, 77, 73, 74, 91, 79, 66], [61, 76, 85, 90, 65, 62, 89], [89, 74, 87, 74, 61, 77, 67], [91, 74, 66, 70, 78, 95, 69], [75, 99, 78, 61, 72, 74, 84], [89, 79, 92, 97, 67, 61, 70], [88, 60, 78, 79, 72, 94, 88], [79, 67, 78, 61, 84, 94, 98], [72, 83, 76, 88, 86, 81, 85], [62, 99, 95, 80, 70, 70, 96], [63, 81, 69, 95, 89, 92, 86], [99, 84, 76, 71, 99, 66, 82], [88, 89, 88, 96, 83, 80, 62], [85, 91, 63, 74, 97, 94, 88], [78, 80, 76, 91, 89, 79, 100], [81, 96, 75, 61, 100, 88, 95], [91, 86, 94, 70, 87, 86, 89], [95, 94, 63, 97, 98, 66, 95], [88, 98, 78, 99, 95, 80, 99]][Arthur Parker, Victor Stone, Garfield Logan, Rachel Roth, Ray Palmer, Wanda Maximoff, Barry Allen, Stephen Murdock, Peter Parker, Bucky Barnes, James Rhodes, Tony Stark, Clarke Kent, Steve Rogers, Peter Quill, Stephen3 Strange, Natasha Romanoff, Stephen1 Strange, Scott Lang, Stephen4 Strange, Bruce Banner, Hal Jordan, Carol Denvers, Stephen5 Strange, Nick Fury, Stephen6 Strange, Clint Barton, Stephen7 Strange, Stephen2 Strange, Bruce Wayne]\n", Parker.stringAll());

        Gradebook Stephen = new Gradebook("CS/src/GradebookCopy2.csv");
        Stephen.sortStudentsByAverage();
        assertEquals("[[98, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100]][Natasha Romanoff, Peter1 Parker, Peter2 Parker, Bucky Barnes, Clint Barton, Steve Rogers, Stephen Murdock, Bruce Banner, Carol Denvers, Nick Fury, Scott Lang, Wanda Maximoff, Peter Quill, James Rhodes, Stephen Strange, Sam Stone, Jessica Jones, Luke Cage, Oliver Queen, Dinah Lance, Some One, Hal Jordan, Bruce Wayne, Clarke Kent, Barry Allen, Ray Palmer, Arthur Parker, Victor Stone, Peter3 Parker, Peter4 Parker]\n",Stephen.stringAll());

    }

    @Test
    public void sortStudentsByName() throws IOException {
        // use nameOfObject.printAll() to print roster and grades
        Gradebook Strange = new Gradebook("CS/src/GradebookCopy.csv");
        Strange.sortStudentsByName();
        assertEquals("[[65, 62, 82, 74, 61, 95, 62], [72, 67, 62, 73, 83, 85, 81], [81, 96, 75, 61, 100, 88, 95], [62, 99, 95, 80, 70, 70, 96], [72, 83, 76, 88, 86, 81, 85], [99, 84, 76, 71, 99, 66, 82], [85, 91, 63, 74, 97, 94, 88], [75, 99, 78, 61, 72, 74, 84], [63, 81, 69, 95, 100, 100, 100], [61, 76, 85, 90, 100, 100, 100], [50, 80, 76, 91, 100, 100, 100], [79, 67, 78, 61, 84, 94, 98], [68, 98, 72, 79, 83, 72, 80], [67, 75, 67, 86, 64, 87, 64], [66, 68, 68, 80, 72, 61, 84], [62, 96, 62, 62, 99, 67, 71], [71, 95, 94, 84, 63, 94, 64], [61, 73, 64, 86, 93, 99, 78], [88, 89, 88, 96, 83, 80, 62], [91, 74, 66, 70, 78, 95, 69], [89, 79, 92, 97, 67, 61, 70], [89, 74, 87, 74, 61, 77, 67], [91, 80, 64, 68, 83, 69, 68], [70, 64, 60, 97, 80, 85, 84], [95, 94, 63, 97, 98, 66, 95], [51, 86, 94, 70, 100, 100, 100], [68, 77, 73, 74, 91, 79, 66], [73, 63, 76, 91, 90, 84, 76], [88, 60, 78, 79, 72, 94, 88], [88, 98, 78, 99, 100, 100, 100]][Barry Allen, Bucky Barnes, Clint Barton, Bruce Banner, Luke Cage, Carol Denvers, Nick Fury, Jessica Jones, Hal Jordan, Clarke Kent, Dinah Lance, Scott Lang, Garfield Logan, Stephen1 Murdock, Wanda Maximoff, Peter Parker, Ray Palmer, Arthur Parker, Oliver Queen, Peter Quill, Natasha Romanoff, Steve Rogers, James Rhodes, Rachel Roth, Sam Strange, Stephen3 Strange, Tony Stark, Victor Stone, Stephen2 Strange, Bruce Wayne]\n", Strange.stringAll());

        Gradebook Parker = new Gradebook("CS/src/GradebookCopy1.csv");
        Parker.sortStudentsByName();
        assertEquals("[[65, 62, 82, 74, 61, 95, 62], [72, 67, 62, 73, 83, 85, 81], [81, 96, 75, 61, 100, 88, 95], [62, 99, 95, 80, 70, 70, 96], [99, 84, 76, 71, 99, 66, 82], [85, 91, 63, 74, 97, 94, 88], [63, 81, 69, 95, 89, 92, 86], [61, 76, 85, 90, 65, 62, 89], [79, 67, 78, 61, 84, 94, 98], [0, 0, 0, 0, 0, 0, 0], [66, 68, 68, 80, 72, 61, 84], [67, 75, 67, 86, 64, 87, 64], [62, 96, 62, 62, 99, 67, 71], [0, 0, 0, 0, 0, 100, 0], [0, 0, 0, 0, 0, 0, 0], [91, 74, 66, 70, 78, 95, 69], [89, 74, 87, 74, 61, 77, 67], [91, 80, 64, 68, 83, 69, 68], [89, 79, 92, 97, 67, 61, 70], [0, 0, 0, 0, 0, 0, 0], [91, 86, 94, 70, 87, 86, 89], [68, 77, 73, 74, 91, 79, 66], [75, 99, 78, 61, 72, 74, 84], [72, 83, 76, 88, 86, 81, 85], [88, 89, 88, 96, 83, 80, 62], [88, 60, 78, 79, 72, 94, 88], [0, 0, 0, 0, 0, 0, 0], [95, 94, 63, 97, 98, 66, 95], [78, 80, 76, 91, 89, 79, 100], [88, 98, 78, 99, 95, 80, 99]][Barry Allen, Bucky Barnes, Clint Barton, Bruce Banner, Carol Denvers, Nick Fury, Hal Jordan, Clarke Kent, Scott Lang, Garfield Logan, Wanda Maximoff, Stephen Murdock, Peter Parker, Ray Palmer, Arthur Parker, Peter Quill, Steve Rogers, James Rhodes, Natasha Romanoff, Rachel Roth, Stephen7 Strange, Tony Stark, Stephen3 Strange, Stephen4 Strange, Stephen5 Strange, Stephen1 Strange, Victor Stone, Stephen2 Strange, Stephen6 Strange, Bruce Wayne]\n",Parker.stringAll());

        Gradebook Stephen = new Gradebook("CS/src/GradebookCopy2.csv");
        Stephen.sortStudentsByName();
        assertEquals("[[99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [98, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100], [99, 100, 100, 100, 100, 100, 100]][Barry Allen, Bucky Barnes, Clint Barton, Bruce Banner, Luke Cage, Carol Denvers, Nick Fury, Jessica Jones, Hal Jordan, Clarke Kent, Dinah Lance, Scott Lang, Stephen Murdock, Wanda Maximoff, Some One, Peter2 Parker, Peter1 Parker, Ray Palmer, Arthur Parker, Peter3 Parker, Peter4 Parker, Peter Quill, Oliver Queen, James Rhodes, Steve Rogers, Natasha Romanoff, Victor Stone, Sam Stone, Stephen Strange, Bruce Wayne]\n",Stephen.stringAll());

    }
}