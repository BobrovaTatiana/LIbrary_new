package marshaling;

/**
 * Created by Tanusha on 20/02/2017.
 */

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class marsh_book implements Runnable{
    private String path;
    private Object object;
    //private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public marsh_book(String path, Object object) {
        this.path = path;
        this.object = object;
    }

    @Override
    public void run() {
        try {
            File file = new File(path);
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(object, file);
            marshaller.marshal(object, System.out);
        } catch (JAXBException exception) {
            Logger.getLogger(marsh_book.class.getName()).
                    log(Level.SEVERE, "marshalling " + object.getClass(), exception);
        }
    }
}
