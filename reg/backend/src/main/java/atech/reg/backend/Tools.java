package atech.reg.backend;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Tools {

    public Date stringToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = null;
        System.out.println(dateString);
        try {
            date = dateFormat.parse(dateString);
            System.out.println("SUCCESS: " + date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
