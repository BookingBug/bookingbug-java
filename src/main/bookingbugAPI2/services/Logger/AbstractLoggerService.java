package bookingbugAPI2.services.logger;

/**
 * Created by sebi on 05.07.2016.
 */
public abstract class AbstractLoggerService {

    public abstract Logger getLogger(String TAG);

    public interface Logger {
        /**
         * Log a verbose message
         * @param message the message to log. Can contain formatting like {0} {1} which are replaced by {@code args}
         * @param args Additional objects to print inside the message
         */
        void v(String message, Object... args);

        /**
         * Log a verbose exception and a message
         * @param t the throwable
         * @param message the message to log. Can contain formatting like {0} {1} which are replaced by {@code args}
         * @param args Additional objects to print inside the message
         */
        void v(Throwable t, String message, Object... args);

        /**
         * Log a debug message
         * @param message the message to log. Can contain formatting like {0} {1} which are replaced by {@code args}
         * @param args Additional objects to print inside the message
         */
        void d(String message, Object... args);

        /**
         * Log a debug exception and a message
         * @param t the throwable
         * @param message the message to log. Can contain formatting like {0} {1} which are replaced by {@code args}
         * @param args Additional objects to print inside the message
         */
        void d(Throwable t, String message, Object... args);

        /**
         * Log an info message
         * @param message the message to log. Can contain formatting like {0} {1} which are replaced by {@code args}
         * @param args Additional objects to print inside the message
         */
        void i(String message, Object... args);

        /**
         * Log an info exception and a message
         * @param t the throwable
         * @param message the message to log. Can contain formatting like {0} {1} which are replaced by {@code args}
         * @param args Additional objects to print inside the message
         */
        void i(Throwable t, String message, Object... args);

        /**
         * Log a warning message
         * @param message the message to log. Can contain formatting like {0} {1} which are replaced by {@code args}
         * @param args Additional objects to print inside the message
         */
        void w(String message, Object... args);

        /**
         * Log a warning exception and a message
         * @param t the throwable
         * @param message the message to log. Can contain formatting like {0} {1} which are replaced by {@code args}
         * @param args Additional objects to print inside the message
         */
        void w(Throwable t, String message, Object... args);

        /**
         * Log an error message
         * @param message the message to log. Can contain formatting like {0} {1} which are replaced by {@code args}
         * @param args Additional objects to print inside the message
         */
        void e(String message, Object... args);

        /**
         * Log an error exception and a message
         * @param t the throwable
         * @param message the message to log. Can contain formatting like {0} {1} which are replaced by {@code args}
         * @param args Additional objects to print inside the message
         */
        void e(Throwable t, String message, Object... args);
    }

    public static abstract class AbstractLogger implements Logger {
        final String TAG;

        public AbstractLogger(String TAG) {
            this.TAG = TAG;
        }
    }

}
