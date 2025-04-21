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

    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss XXX";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);

    @Override
    public String format(LogRecord record) {
        String dateTime = LocalDateTime.now()
                .atZone(ZoneId.systemDefault())
                .toOffsetDateTime()
                .format(formatter);
        String threadNumber = String.valueOf(record.getLongThreadID());
        String level = record.getLevel().getLocalizedName();
        String source = getSource(record); // implementation from SimpleFormatter
        String message = checkForResourseBundleAndParameteres(record);
        String throwable = getThrowable(record); // implementation from SimpleFormatter

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

    /**
     * This is Formatter.formatMessage()
     */
    private String checkForResourseBundleAndParameteres(LogRecord record) {
        String format = record.getMessage();
        java.util.ResourceBundle catalog = record.getResourceBundle();
        if (catalog != null) {
            try {
                format = catalog.getString(format);
            } catch (java.util.MissingResourceException ex) {
                // Drop through.  Use record message as format
            }
        }
        // Do the formatting.
        try {
            Object parameters[] = record.getParameters();
            if (parameters == null || parameters.length == 0) {
                // No parameters.  Just return format string.
                return format;
            }
            int index = -1;
            int fence = format.length() - 1;
            while ((index = format.indexOf('{', index + 1)) > -1) {
                if (index >= fence) {
                    break;
                }
                char digit = format.charAt(index + 1);
                if (digit >= '0' && digit <= '9') {
                    return java.text.MessageFormat.format(format, parameters);
                }
            }
            return format;

        } catch (Exception ex) {
            // Formatting failed: use localized format string.
            return format;
        }
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
