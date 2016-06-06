package helpers.listeners;

import com.codeborne.selenide.logevents.ErrorsCollector;
import com.codeborne.selenide.logevents.LogEvent;
import helpers.Helpers;

import java.util.ArrayList;
import java.util.List;

 public class CustomErrorsCollector extends ErrorsCollector {
     private final List<Throwable> errors = new ArrayList();

     @Override
     public void onEvent(LogEvent event) {
         if(event.getStatus() == LogEvent.EventStatus.FAIL) {
             this.errors.add(event.getError());
             Helpers.takeScreenshot();
         }
     }
 }
