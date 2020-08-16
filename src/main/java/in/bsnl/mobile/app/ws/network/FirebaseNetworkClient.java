package in.bsnl.mobile.app.ws.network;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;


public class FirebaseNetworkClient {
     public static FirebaseUser getUserByEmail(String email) {
         DefaultHttpClient httpClient = new DefaultHttpClient();
         FirebaseUser firebaseUser = new FirebaseUser();
         try {
             HttpGet getRequest  = new HttpGet("http://10.196.215.24:8080/"+email);
             getRequest.addHeader("accept","application/json");
             HttpResponse response = httpClient.execute(getRequest);

             int statusCode = response.getStatusLine().getStatusCode();
             if(statusCode != 200) {
                 throw new RuntimeException("Failed with HTTP error code :"+statusCode);
             }

             HttpEntity httpEntity = response.getEntity();
             String apiOut  = EntityUtils.toString(httpEntity);

             ObjectMapper mapper = new ObjectMapper();
             firebaseUser = mapper.readValue(apiOut,FirebaseUser.class);
//             JAXBContext jaxbContext = JAXBContext.newInstance(FirebaseUser.class);
//             Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//             firebaseUser = (FirebaseUser) jaxbUnmarshaller.unmarshal(new StringReader(apiOut));


             System.out.println(apiOut);
         } catch (ClientProtocolException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
         return  firebaseUser;
     }
}
