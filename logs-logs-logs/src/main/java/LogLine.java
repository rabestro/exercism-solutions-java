public class LogLine {

    private final String message;

    public LogLine(String logLine) {
        message = logLine;
    }

    public LogLevel getLogLevel() {
        return switch (message.substring(1, 4)) {
            case "INF" -> LogLevel.INFO;
            case "TRC" -> LogLevel.TRACE;
            case "DBG" -> LogLevel.DEBUG;
            case "WRN" -> LogLevel.WARNING;
            case "ERR" -> LogLevel.ERROR;
            case "FTL" -> LogLevel.FATAL;
            default -> LogLevel.UNKNOWN;
        };
    }

    public String getOutputForShortLog() {
        return getLogLevel().getLevel() + ":" + message.substring(7);
    }
}
