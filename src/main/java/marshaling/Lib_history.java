package marshaling;

import ru.bobrova.models.pojo.History;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanusha on 22/02/2017.
 */
@XmlType(propOrder = { "histories", "history" }, name = "library")
@XmlRootElement
public class Lib_history {
    private List<History> history = new ArrayList<History>();
    private String histories;

    public List<History> getHistory() {
        return history;
    }

    public String getHistories() {
        return histories;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }

    public void setHistories(String histories) {
        this.histories = histories;
    }
}
