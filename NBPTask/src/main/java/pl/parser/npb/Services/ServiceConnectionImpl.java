package pl.parser.npb.Services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import pl.parser.npb.Constants.*;

public class ServiceConnectionImpl implements ServiceConnection {
	static HttpURLConnection connection;
	static URL url;
	static List<String> filenames;
	static String xmlContent;
	@Override
	public  List<String> getYearlyFileNames(String Year) {
        try {
            url = new URL(Constants.BaseURL + "dir"+(Year == String.valueOf(LocalDateTime.now().getYear()) ? "" : Year)+".txt");
            connection = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            filenames = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                filenames.add(line);
             }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return filenames;
	}
	@Override
	public String getxmlContent(String fileName)  {
        try {
            url = new URL(Constants.BaseURL + fileName + ".xml");
            connection = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            StringBuffer sb = new StringBuffer();

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            xmlContent = sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
        	xmlContent = "FileNotFound";
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return xmlContent;
	}
}
