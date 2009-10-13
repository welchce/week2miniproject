/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package miniproject;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Raymond Cox <rj.cox101 at gmail.com>
 * @version 1.0
 *
 *
 */
public class Logger {

    //the name of the file taht we are printing the log to.
    String _fileName;

    /*
     * The default constructor for the Logger class, creates a log file.
    */
    public Logger() {
        final String DATE_FORMAT_NOW = "MM-dd-yy HH.mm.ss";
        Date curTime = Calendar.getInstance().getTime();
        _fileName = new SimpleDateFormat(DATE_FORMAT_NOW).format(curTime)+".log";
    }
    /*
     * Constructor that takes a filename as a parameter.
    */
    public Logger(String fileName) {
        _fileName = fileName;
    }
    /*
     * returns the filename of the log file.
    */
    public String getFileName() { return _fileName; }

    /*
     * writes the passed String st into the log file.
    */
    public void write(String st) throws java.io.IOException {
        FileWriter outFile = new FileWriter(_fileName,true);
        PrintWriter logWriter = new PrintWriter(outFile);
        logWriter.write(st);
        logWriter.close();
    }

}
