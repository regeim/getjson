/**
 * Created with IntelliJ IDEA.
 * User: simonz
 * Date: 2015.03.18.
 * Time: 10:40
 * To change this template use File | Settings | File Templates.
 */
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.google.gson.stream.JsonReader;

public class DownloadJSON {


    public static void main(String[] args) throws IOException {
        ProcessJSON main_json;
        HttpConnection main_Http;
        String url="http://eu.battle.net/api/wow/auction/data/outland";
        List<String> auction_url=null;
        String fileName = new SimpleDateFormat("yyyyMMddhhmm'.txt'").format(new Date());

        File file = new File("c:\\hello\\results_outland.txt");
         //The file that you want to download there9

        InputStream inputStream;

        main_Http=new HttpConnection(url);
        main_Http.OpenStream();


        if (main_Http.getStream()!=null){

            inputStream=main_Http.getStream();
            main_json=new ProcessJSON (inputStream);
            try {
                auction_url=main_json.createFromJSON(2);

            } catch (IOException e) {
                e.printStackTrace();
            }
            main_Http.CloseStream();

        }


        URL link = new URL(auction_url.get(0).toString());
        //Code to download
        InputStream in = new BufferedInputStream(link.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        while (-1!=(n=in.read(buf)))
        {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        byte[] response = out.toByteArray();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(response);
        fos.close();
        //End download code

        System.out.println("Finished");

    }
}
