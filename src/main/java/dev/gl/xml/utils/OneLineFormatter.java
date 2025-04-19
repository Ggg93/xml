package dev.gl.xml.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 *
 * @author gl
 */
public class OneLineFormatter extends Formatter {

    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);

    @Override
    public String format(LogRecord record) {
        String dateTime = LocalDateTime.now().format(formatter);
        String threadNumber = String.valueOf(record.getLongThreadID());
        String level = record.getLevel().getLocalizedName();
        String source = getSource(record);
        String message = record.getMessage();
        String throwable = getThrowable(record);

        return new StringBuilder()
                .append(dateTime)
                .append(" [")
                .append(threadNumber)
                .append("] ")
                .append(level)
                .append(". ")
                .append(source)
                .append(": ")
                .append(message)
                .append(throwable)
                .append(System.lineSeparator())
                .toString();
    }

    private String getSource(LogRecord record) {

        String source;
        if (record.getSourceClassName() != null) {
            source = record.getSourceClassName();
            if (record.getSourceMethodName() != null) {
                source += "." + record.getSourceMethodName();
            }
        } else {
            source = record.getLoggerName();
        }

        return source;
    }

    private String getThrowable(LogRecord record) {

        String throwable = "";
        if (record.getThrown() != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            pw.println();
            record.getThrown().printStackTrace(pw);
            pw.close();
            throwable = sw.toString();
        }

        return throwable;
    }

}
