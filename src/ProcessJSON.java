/**
 * Created with IntelliJ IDEA.
 * User: simonz
 * Date: 2015.03.18.
 * Time: 13:19
 * To change this template use File | Settings | File Templates.
 */

import com.google.gson.stream.JsonToken;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.stream.JsonReader;

/**
 * Created by Zoli on 2015.02.25..
 */
public class ProcessJSON{

    private String data_holder;
    private ArrayList<String> world_list;
    private ArrayList<String> item_list;

    private int switch_case;
    private String temp;
    private String url;
    boolean good;
    InputStream inputStream;


    public ProcessJSON (InputStream inputStream){
        this.inputStream=inputStream;
    }

    public ArrayList<String> getWorld_list() {
        return world_list;
    }

    public String getUrl() {
        return url;
    }




    public List createFromJSON(Integer integer) throws IOException {
        String temp;

        String temp_integer;
        List list = new ArrayList();
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        JsonReader jsonReader = null;
        inputStreamReader = new InputStreamReader(this.inputStream);
        bufferedReader = new BufferedReader(inputStreamReader);
        jsonReader = new JsonReader(inputStreamReader);
        String char_name;

        switch (integer){

            case 1:
                jsonReader.beginObject();
                while( jsonReader.hasNext() ){
                    final String name = jsonReader.nextName();

                    if( name.equals( "realms" ) && jsonReader.peek()!= JsonToken.NULL ) {

                        jsonReader.beginArray();
                        while( jsonReader.hasNext() ) {

                            jsonReader.beginObject();
                            while( jsonReader.hasNext() ) {

                                final String innerInnerName = jsonReader.nextName();
                                if( innerInnerName.equals( "name" )&& jsonReader.peek()!= JsonToken.NULL) {

                                    temp=(jsonReader.nextString());
                                    list.add(temp);
                                }
                                else {
                                    jsonReader.skipValue();
                                }
                            }
                            jsonReader.endObject();
                        }
                        jsonReader.endArray();
                    }
                    else
                        jsonReader.skipValue();
                }
                jsonReader.endObject();
                break;
            case 2:
                jsonReader.beginObject();
                while( jsonReader.hasNext() ){
                    final String name = jsonReader.nextName();

                    if( name.equals( "files" ) && jsonReader.peek()!= JsonToken.NULL ) {

                        jsonReader.beginArray();
                        while( jsonReader.hasNext() ) {

                            jsonReader.beginObject();
                            while( jsonReader.hasNext() ) {

                                final String innerInnerName = jsonReader.nextName();
                                if( innerInnerName.equals( "url" )&& jsonReader.peek()!= JsonToken.NULL) {

                                    temp=(jsonReader.nextString());
                                    list.add(temp);
                                }
                                else {
                                    jsonReader.skipValue();
                                }
                            }
                            jsonReader.endObject();
                        }
                        jsonReader.endArray();
                    }
                    else
                        jsonReader.skipValue();
                }
                jsonReader.endObject();
                break;
            case 3:
                jsonReader.beginObject();
                while( jsonReader.hasNext() ){
                    final String name = jsonReader.nextName();

                    if( name.equals( "auctions" ) && jsonReader.peek()!= JsonToken.NULL ) {

                        jsonReader.beginObject();
                        while( jsonReader.hasNext() ) {

                            final String innerName = jsonReader.nextName();
                            if( innerName.equals( "auctions" )&& jsonReader.peek()!= JsonToken.NULL) {

                                jsonReader.beginArray();
                                while( jsonReader.hasNext() ) {
                                    jsonReader.beginObject();
                                    while( jsonReader.hasNext() ) {
                                        final String innerInnerName = jsonReader.nextName();
                                        if( innerInnerName.equals( "item" )&& jsonReader.peek()!= JsonToken.NULL) {

                                            temp_integer=(jsonReader.nextString());
                                            jsonReader.nextName();
                                            char_name=(jsonReader.nextString());
                                            if (char_name.equals("Enrupt")){
                                                list.add(temp_integer);

                                            }
                                        }
                                        else {
                                            jsonReader.skipValue();
                                        }

                                    }jsonReader.endObject();

                                }jsonReader.endArray();
                            }
                            else {
                                jsonReader.skipValue();
                            }
                        }
                        jsonReader.endObject();

                    }
                    else
                        jsonReader.skipValue();
                }
                jsonReader.endObject();

        }
        return list;
    }




}

