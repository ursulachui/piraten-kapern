package pk;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Log {
    private static final Logger logger = LogManager.getLogger(Log.class.getName());

    public boolean checkTrace(String[] commandArgs) {
        //change after
        if (commandArgs.length > 3) {
            if (commandArgs[3].equals("trace")) {
                return true;
            }
        }
        return false;
    }

    public void header(Object message) {
        logger.info(message);
    }

    public void tracer(boolean trace, Object message) {
        if (trace) {
            logger.trace(message);
        }
    }
}
