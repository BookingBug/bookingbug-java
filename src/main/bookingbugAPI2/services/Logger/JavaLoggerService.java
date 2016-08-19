package bookingbugAPI2.services.logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sebi on 06.07.2016.
 */
public class JavaLoggerService extends AbstractLoggerService {

    HashMap<String, Logger> loggers = new HashMap<>();

    @Override
    public Logger getLogger(String TAG) {
        //Get cached logger
        if(loggers.containsKey(TAG))
            return loggers.get(TAG);

        Logger logger = new JavaLogger(TAG);
        loggers.put(TAG, logger);
        return logger;
    }

    public static class JavaLogger extends AbstractLogger implements AbstractLoggerService.Logger {

        //Custom log levels (INFO is higher than DEBUG)
        public static final Level DEBUG = new Level("DEBUG", Level.INFO.intValue() + 1){};
        public static final Level INFO = new Level("INFO", Level.INFO.intValue() + 2){};

        //Custom Formatter to display correct Class and Method name
        public static final Formatter formatter = new Formatter() {
            private final String format = "%1$tb %1$td, %1$tY %1$tl:%1$tM:%1$tS %1$Tp %2$s%n%4$s: %5$s%6$s%n";
            private final Date dat = new Date();

            @Override
            public String format(LogRecord record) {
                dat.setTime(record.getMillis());
                String source;

                inferCaller(record);

                if (record.getSourceClassName() != null) {
                    source = record.getSourceClassName();
                    if (record.getSourceMethodName() != null) {
                        source += " " + record.getSourceMethodName();
                    }
                } else {
                    source = record.getLoggerName();
                }
                String message = formatMessage(record);
                String throwable = "";
                if (record.getThrown() != null) {
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    pw.println();
                    record.getThrown().printStackTrace(pw);
                    pw.close();
                    throwable = sw.toString();
                }
                return String.format(format,
                        dat,
                        source,
                        record.getLoggerName(),
                        record.getLevel(),
                        message,
                        throwable);
            }

            // Private method to infer the caller's class and method names
            // The isLoggerImplFrame is different and uses StackTrace
            private void inferCaller(LogRecord record) {
                Throwable throwable = new Throwable();
                StackTraceElement[] stackTrace = throwable.getStackTrace();

                int depth = stackTrace.length;
                boolean lookingForLogger = true;
                for (int ix = 0; ix < depth; ix++) {
                    StackTraceElement frame = stackTrace[ix];
                    String cname = frame.getClassName();
                    boolean isLoggerImpl = isLoggerImplFrame(cname);
                    if (lookingForLogger) {
                        // Skip all frames until we have found the first logger frame.
                        if (isLoggerImpl) {
                            lookingForLogger = false;
                        }
                    } else {
                        if (!isLoggerImpl) {
                            // skip reflection call
                            if (!cname.startsWith("java.lang.reflect.") && !cname.startsWith("sun.reflect.")) {
                                // We've found the relevant frame.
                                record.setSourceClassName(cname);
                                record.setSourceMethodName(frame.getMethodName());
                                return;
                            }
                        }
                    }
                }
            }

            /**
             * Overridden from SimpleFormatter to provide the correct logger class
             * @param cname The class name
             * @return
             */
            private boolean isLoggerImplFrame(String cname) {
                // the log record could be created for a platform logger
                return (cname.equals("java.util.logging.Logger") ||
                        cname.startsWith("java.util.logging.LoggingProxyImpl") ||
                        cname.startsWith("sun.util.logging.") ||
                        cname.equals(JavaLogger.class.getName()));
            }
        };


        final java.util.logging.Logger logger;

        public JavaLogger(String TAG) {
            super(TAG);
            logger = java.util.logging.Logger.getLogger(TAG);

            //ConsoleHandler with custom formatter
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(formatter);

            logger.setUseParentHandlers(false);
            Handler[] handlers = logger.getHandlers();
            for(Handler handler : handlers)
                if(handler.getClass() == ConsoleHandler.class)
                    logger.removeHandler(handler);

            logger.addHandler(consoleHandler);
        }

        @Override
        public void v(String message, Object... args) {
            log(Level.FINER, null, message, args);
        }

        @Override
        public void v(Throwable t, String message, Object... args) {
            log(Level.FINER, t, message, args);
        }

        @Override
        public void d(String message, Object... args) {
            log(DEBUG, null, message, args);
        }

        @Override
        public void d(Throwable t, String message, Object... args) {
            log(DEBUG, t, message, args);
        }

        @Override
        public void i(String message, Object... args) {
            log(INFO, null, message, args);
        }

        @Override
        public void i(Throwable t, String message, Object... args) {
            log(INFO, t, message, args);
        }

        @Override
        public void w(String message, Object... args) {
            log(Level.WARNING, null, message, args);
        }

        @Override
        public void w(Throwable t, String message, Object... args) {
            log(Level.WARNING, t, message, args);
        }

        @Override
        public void e(String message, Object... args) {
            log(Level.SEVERE, null, message, args);
        }

        @Override
        public void e(Throwable t, String message, Object... args) {
            log(Level.SEVERE, t, message, args);
        }

        private void log(java.util.logging.Level level, Throwable t, String message, Object... args) {
            if(message == null || message.length() == 0) {
                if(t != null)
                    message = t.toString();
                else return; //Swallow error
            } else if(t != null)
                message += "\n" + t.toString();

            if(args.length > 0) {
                //Format the message
                StringBuffer stringBuffer = new StringBuffer();
                Matcher matcher = Pattern.compile("\\{\\d\\}").matcher(message);

                while (matcher.find()) {
                    String value = matcher.group();
                    value = value.substring(1, value.length() - 1);
                    int pos = Integer.parseInt(value);
                    if(pos < args.length && args[pos] != null)
                        matcher.appendReplacement(stringBuffer, args[pos].toString());
                }
                matcher.appendTail(stringBuffer);
                message = stringBuffer.toString();
            }

            logger.log(level, message);
        }
    }
}
