package com.durbo.simData.country;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    String apiUrl = "https://restcountries.com/v3.1/";

    public ArrayList<Country> getCountriesByRegion(String region) {
        ArrayList<Country> countries = new ArrayList<>();
        try {
            URL url = new URL(apiUrl + "region/" + region + "?fields=name,cca3,region");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            int status = con.getResponseCode();

            if (status == HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONArray jsonArray = new JSONArray(response.toString());
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String name = jsonObject.getJSONObject("name").getString("common");
                    String cca3 = jsonObject.getString("cca3");
                    String region1 = jsonObject.getString("region");
                    Country c = new Country(name, cca3, region1);
                    countries.add(c);
                }
                return countries;
            }
        } catch (IOException | JSONException e) {
            return null;
        }
        return countries;
    }





}
