package at.allergico.allergico.database.Manager;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Michael on 23/05/2015.
 */
public class DBManager
{
    private URL url;
    private OutputStreamWriter wr;

    /******** SINGLETON START ********/
    private static DBManager _instance;

    public static DBManager getInstance() {
        if (_instance == null) {
            _instance = new DBManager();
        }
        return _instance;
    }

    private DBManager() {}
    /******** SINGLETON END *********/

    public boolean addUser(String jsonString)
    {
        try
        {
            url = new URL("http://sadler.or.at/allergico/service.php");
            URLConnection conn = url.openConnection();
            String data  = URLEncoder.encode("InsertUser", "UTF-8")
                    + "=" + URLEncoder.encode(jsonString, "UTF-8");
            wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            return true;
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
            return false;
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            return false;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
