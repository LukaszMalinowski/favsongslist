package com.herokuapp.favsongslist;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Test {

    public static final String accessToken = "ABvYa45O8eb-imO1dnRdjlkczDkhnu3Mt6ZdAupSFVxMyUdVd-zh6AMLMZxVWvaX";
    public static final String searchULR = "https://api.genius.com/search?q=";
    public static final String songsURL = "https://api.genius.com/songs/";
//    public static final String songsURL = "https://api.genius.com/songs/436083?access_token=R6DSKN6yasvTRjKo61BQhfDgwwzjy2gtxCkdXs5R9XTDg6h91jCCDO3XahmjAymf";

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("ABvYa45O8eb-imO1dnRdjlkczDkhnu3Mt6ZdAupSFVxMyUdVd-zh6AMLMZxVWvaX");

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        ResponseEntity<String> response = restTemplate.exchange(searchULR + "The+Weeknd+Adaptation", HttpMethod.GET,
                                                                entity, String.class);

        JSONObject json = new JSONObject(response.getBody());

        JSONArray jsonArray = json.getJSONObject("response").getJSONArray("hits");
        int id = jsonArray.getJSONObject(0).getJSONObject("result").getInt("id");

        System.out.println(id);

        ResponseEntity<String> response2 = restTemplate.exchange(songsURL + id, HttpMethod.GET, entity, String.class);

        System.out.println(response);
        System.out.println(response2);
    }
}
