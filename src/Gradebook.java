import java.io.*;
import java.text.DecimalFormat;
import java.util.Arrays;

public class Gradebook {
    public static void main(String[] args) throws IOException {
        Gradebook g = new Gradebook("CS/src/PastLabs.Gradebook.csv");
        //g.sortStudentsByAverage();
        //g.sortStudentsByName();
        //System.out.println(Arrays.toString(g.averagePerAssignment()));
        //System.out.println(g.largestVariation());
        //g.printRow(0);
        //System.out.println(Arrays.deepToString(g.Grades));
    }

    /* ATTRIBUTES *******************************************************/
    private String[] Roster;
    private String[] Assignments;
    private int[][] Grades;

    /* CONSTRUCTORS *****************************************************/
    /* Default constructor */
    public Gradebook() {}

    /* Constructor based on data from file */
    public Gradebook(String filename) throws FileNotFoundException, IOException {
        int numStudents = numLines(filename) - 1;
        String[] line;

        FileReader fr = new FileReader(filename);
        BufferedReader textReader = new BufferedReader(fr);

        Assignments = textReader.readLine().substring(21).split(",");
        Grades = new int[numStudents][Assignments.length];
        Roster = new String[numStudents];

        /* for each student, we fill out:
         *  	- the student's name in Roster
         *  	- the student's grades in the corresponding row of Grades
         */
        for (int i = 1; i <= numStudents; i++) {
            line = textReader.readLine().split(",");
            Roster[i-1] = line[0] + " " + line[1];
            for (int j=0; j<Assignments.length; j++) {
                Grades[i-1][j] = Integer.valueOf(line[j+2]);
            }
        }
        textReader.close();
    }

    public int numLines(String filename) throws FileNotFoundException, IOException {
        int lines = 0;
        FileReader fr = new FileReader(filename);
        BufferedReader textReader = new BufferedReader(fr);

        while (textReader.ready()) {
            textReader.readLine();
            lines++;
        }
        textReader.close();
        return lines;
    }

    /* GETTERS AND SETTERS ***********************************************/
    /**
     * @return the roster
     */
    public String[] getRoster() {
        return Roster;
    }

    /**
     * @return the assignments
     */
    public String[] getAssignments() {
        return Assignments;
    }

    /**
     * @return the grades
     */
    public int[][] getGrades() {
        return Grades;
    }

    /**
     * @param roster the roster to set
     */
    public void setRoster(String[] roster) {
        Roster = roster;
    }

    /**
     * @param assignments the assignments to set
     */
    public void setAssignments(String[] assignments) {
        Assignments = assignments;
    }

    /**
     * @param grades the grades to set
     */
    public void setGrades(int[][] grades) {
        Grades = grades;
    }

    /* Method 1: named printRow
     * 		• Inputs: an int value n that corresponds to a given row index
     * 			in Grades and an index in Roster.
     * 		• Output: nothing
     * 		• What it does: it prints out a given row: student’s name followed
     * 			by this student’s grades, and finally this student’s average grade
     */

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

    public void printRow(int n) {

        String [] theRoster = getRoster();
        int [][] theGrades = getGrades();
        Double total = 0.0;

        System.out.print("Grades:");
        //for loop to print grades and add to total
        for(int gr: theGrades[n]){
            System.out.print(gr + " ");
            total += gr;
        }
        //print average and name of student
        System.out.printf("Average:" + "%.2f" + " Name:"  +theRoster[n], total/Assignments.length);
    }



    public String printrow(int n){
        String [] theRoster = getRoster();
        int [][] theGrades = getGrades();
        Double total = 0.0;

        //for loop to add grades to a string and to get total of all grades
        String strGrades = "";
        for(int gr: theGrades[n]){
            strGrades +=  gr + " ";
            total += gr;
        }
        DecimalFormat df = new DecimalFormat("#.00");
        total = Double.valueOf(df.format(total/Assignments.length));
        //return string of grades, average and name of student
        return "Grades:" + strGrades + "Average:"+ total + " Name:" + theRoster[n] ;
    }

    /* Method 2: named averagePerAssignment
     * 		•	Inputs: nothing
     * 		•	Output: a 1D array of doubles of size Assignments.length
     * 		•	What it does: it computes and returns an array of the average
     * 				grades of all assignments, one average grade per assignment.
     */
    public double[] averagePerAssignment() {
        double[] result = new double[Assignments.length];
        int [][] theGrades = getGrades();
        int i = 0;
        Double sum = 0.0;
        DecimalFormat Dec = new DecimalFormat(".##");

        //Goes through each assignment and retrieves average
        while(i < Assignments.length){
            //gets sum of certain column(grades)
            for(int k = 0; k < theGrades.length; k++){
                sum += theGrades[k][i];
            }
            //calculates average of an assignment and uses DecimalFormat to get rid of unnecessary decimals
            result[i] = Double.parseDouble(Dec.format(sum / Grades.length));
            sum = 0.0;
            i++;
        }

        return result;
    }

    /* Method 3: named largestVariation
     * 		•	Inputs: nothing
     * 		•	Output: a string that corresponds to the name of a students
     * 		•	What it does: it checks, for each student, the variation between
     * 				their lowest and highest grade, and returns the name of the student
     * 				with the largest such variation.
     */
    public String largestVariation() {
        String result = "";
        int theGrades [][] = getGrades();
        String theRoster[] = getRoster();
        int ct = 0;
        int gap = 0;
        int student = -1;

        //for loop to get grades of all students
        for(int [] i: theGrades){
            int high = 0; int low = 100;

            //for loops to get high and low of student(i) grades
            //retrieves min
            for(int j: i){
                if(j < low){
                    low = j;
                }
            }
            //retrieves max
            for(int j: i){
                if(j > high){
                    high = j;
                }
            }

            //calculates if new gap between high and low are greater than the previous gap
            if(high - low >= gap){
                gap = high - low;
                student = ct;//used to mark which student has the largest gap
            }
            ct++;
        }

        //result is the student with the largest gap between high and low
        result = theRoster[student];
        return result;
    }

    /* Method 4: named sortStudentsByAverage
     * 		•	Inputs: nothing
     * 		•	Output: nothing
     * 		•	What it does: it sorts the students by highest to lowest average
     * 				of their grades. The execution of this method results in a reordering
     * 				of the Roster array as well as a corresponding reordering of the Grades
     * 				array, so that the rows of Grades still correspond to the correct student.
     */
    public void sortStudentsByAverage() {
        int [][] theGrades = getGrades();
        String [] theRoster = getRoster();
        Double avg [] = new Double [theGrades.length];
        int ct = 0;

        //iterates through each student's grades
        for(int [] i: theGrades){
            Double tempSum = 0.0;

            //gets sum of student(i) grades
            for(int j: i){
                tempSum += j;
            }
            //gets average of student and adds to avg, ct++ to keep track of student
            avg[ct] = tempSum / Assignments.length;
            ct++;
        }

        //insertion sort algorithm
        //Goes through each student
        for (int i = 1; i < Grades.length; ++i) {
            int j = i;

            //iterates through avg till j is 0 or if avg[j] > avg[j-1]
            while (j > 0 && avg[j] < avg[j - 1]) {
                //sorts averages
                Double temp = avg[j];
                avg[j] = avg[j - 1];
                avg[j - 1] = temp;

                //sorts grades
                int[] tempG = theGrades[j];
                theGrades[j] = theGrades[j-1];
                theGrades[j-1] = tempG;

                //sorts roster
                String tempR = theRoster[j];
                theRoster[j] = theRoster[j-1];
                theRoster[j-1] = tempR;

                j--;
            }
        }

        //System.out.println(Arrays.toString(avg));
        //System.out.println(Arrays.deepToString(theGrades));
        //System.out.println(Arrays.toString(theRoster));

        setGrades(theGrades);
        setRoster(theRoster);
    }

    /* Method 5: named sortStudentsByName
     * 		•	Inputs: nothing
     * 		•	Output: nothing
     * 		•	What it does: it sorts the students by alphabetical order of their last names
     * 				then first names (like a phone book or a dictionary). The execution of
     * 				this method results in a reordering of the Roster array as well as a
     * 				corresponding reordering of the Grades array, so that the rows of Grades
     * 				still correspond to the correct student.
     */
    public void sortStudentsByName() {
        String [] theRoster = getRoster();
        int [][] theGrades = getGrades();
        String [][] splitNames = new String[theRoster.length][2];

        int ct = 0;
        //splits the first and last name of student(i), used to track last name
        for(String i: theRoster){
            splitNames[ct] = i.split(" "); ct++;
        }

        //selection sort algorithm
        //goes through each name on roster
        for (int i = 0; i < theRoster.length - 1; ++i) {
            int indexSmallest = i;
            //for loop to "travel" through each name
            for (int j = i + 1; j < theRoster.length; ++j) {
                //determines which letter is smallest
                //[j] / [indexSmallest], which gets a specified student
                //[1], gets the last name of student
                //charAt(0) get first letter of last name then compares
                if ( splitNames[j][1].charAt(0) < splitNames[indexSmallest][1].charAt(0) ) {
                    indexSmallest = j;
                }
            }

            //sorts split names
            String [] temp = splitNames[i];
            splitNames[i] = splitNames[indexSmallest];
            splitNames[indexSmallest] = temp;
            //sorts roster names
            String tempR = theRoster[i];
            theRoster[i] = theRoster[indexSmallest];
            theRoster[indexSmallest] = tempR;
            //sorts grades
            int[] tempG = theGrades[i];
            theGrades[i] = theGrades[indexSmallest];
            theGrades[indexSmallest] = tempG;

        }

        //System.out.println(Arrays.deepToString(theGrades));
        //System.out.println(Arrays.toString(theRoster));

        setGrades(theGrades);
        setRoster(theRoster);
    }

    public void printAll(){
        System.out.print(Arrays.deepToString(Grades));
        System.out.print(Arrays.deepToString(Roster));

    }
    public String stringAll(){
        return Arrays.deepToString(Grades) + "" +  Arrays.deepToString(Roster) + "\n";
    }

}
