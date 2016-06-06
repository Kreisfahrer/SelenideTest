package helpers.listeners;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.logevents.EventsCollector;
import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.SimpleReport;
import com.google.common.base.Joiner;
import helpers.Helpers;

import java.util.Collections;
import java.util.logging.Logger;


public class CustomReport extends SimpleReport {
    private static final Logger log = Logger.getLogger(SimpleReport.class.getName());

    private EventsCollector logEventListener;

    public void start() {
        SelenideLogger.addListener(logEventListener = new EventsCollector());
    }

    public void finish(String title) {
        SelenideLogger.removeListener(logEventListener);

        StringBuilder sb = new StringBuilder();
        sb.append("Report for ").append(title).append('\n');

        String delimiter = '+' + Joiner.on('+').join(line(20), line(70), line(10), line(10)) + "+\n";

        sb.append(delimiter);
        sb.append(String.format("|%-20s|%-70s|%-10s|%-10s|%n", "Element", "Subject", "Status", "ms."));
        sb.append(delimiter);

        for (LogEvent e : logEventListener.events()) {
            sb.append(String.format("|%-20s|%-70s|%-10s|%-10s|%n", e.getElement(), e.getSubject(),
                    e.getStatus(), e.getDuration()));
        }
        sb.append(delimiter);
        log.info(sb.toString());
        Helpers.attachText(sb.toString());
    }

    private String line(int count) {
        return Joiner.on("").join(Collections.nCopies(count, "-"));
    }
}

