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
 */
public class Logger {
    String _fileName;

    public Logger() {
        final String DATE_FORMAT_NOW = "MM-dd-yy HH.mm.ss";
        Date curTime = Calendar.getInstance().getTime();
        _fileName = new SimpleDateFormat(DATE_FORMAT_NOW).format(curTime)+".log";
    }

    public Logger(String fileName) {
        _fileName = fileName;
    }

    public String getFileName() { return _fileName; }

    public void write(String st) throws java.io.IOException {
        FileWriter outFile = new FileWriter(_fileName,true);
        PrintWriter logWriter = new PrintWriter(outFile);
        logWriter.write(st);
        logWriter.close();
    }

}
