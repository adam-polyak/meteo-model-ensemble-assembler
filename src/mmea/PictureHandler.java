package mmea;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * PictureHandler class used for getting each picture given in the URL extended with the Run parameter
 * and save it into the local drive
 * @author Adam Polyak <adam.polyak.email at gmail.com>
 */
public class PictureHandler {    
    // base forecast URL: "http://www.wetterzentrale.de/ens_image.php?geoid=55612&var=201&model=gfs&member=ENS&bw=&run=00"
    private static final String FORECAST_URL_FRAME = "http://www.wetterzentrale.de/ens_image.php?geoid=55612&var=201&model=gfs&member=ENS&bw=0&run=";
    private static final String PICTURE_LOCATION = "./src/pics/";
    
    // calls savePic for all four kind of pictures at once
    public void savePics() {
        // use String for run options as leading zero is expected value
        List<String> runOptions = new ArrayList<>();
        runOptions.add("00");
        runOptions.add("06");
        runOptions.add("12");
        runOptions.add("18");
        
        for (String run : runOptions) {
            this.savePic(run);
        }
    }
    
    // saves one picture with the given "run" timeframe
    public void savePic(String run) {
        URL url;
        InputStream ins;
        try {
            url = new URL(FORECAST_URL_FRAME + run);
            ins = new BufferedInputStream(url.openStream());
            ByteArrayOutputStream baout = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1 != (n = ins.read(buf))) {
                baout.write(buf, 0, n);
            }
            baout.close();
            ins.close();
            byte[] response = baout.toByteArray();
            
            String filename = getDate() + "_" + run + "_image.jpg";
            FileOutputStream fos = new FileOutputStream(PICTURE_LOCATION + filename);
            fos.write(response);
            fos.close();
        } catch (MalformedURLException ex) {
            System.out.println("MalformedURLException: " + ex);
        } catch (IOException ex) {
            System.out.println("IOException: " + ex);
        }
    }
    
    private String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
	Date date = new Date();
        return dateFormat.format(date);
    }
}
