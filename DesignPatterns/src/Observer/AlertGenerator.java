package Observer;

import java.util.List;

public class AlertGenerator {

    private List<AlertListener> listeners = null;

    public AlertGenerator(List<AlertListener> listeners){
        this.listeners = listeners;
        addListener();
    }

    public void addListener(){
        listeners.add(new AdminListener());
    }

    public void publish(Stock stock) {
        listeners.forEach(t -> t.generateAlert(stock));
    }
}
