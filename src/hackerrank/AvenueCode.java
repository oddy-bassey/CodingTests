package hackerrank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AvenueCode {
    private static final String BASE_URL = "https://jsonmock.hackerrank.com/api/universities?page=";
    private static int maxCount = 0;
    private static String maxUniversity = "";
    private static StringBuilder urlBuilder;
    private static Map<String, Integer> universityCount = new HashMap<>();
    /*
     * Complete the 'highestInternationalStudents' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING firstCity
     *  2. STRING secondCity
     * Base URL: https://jsonmock.hackerrank.com/api/universities?page=
     */

    public static String highestInternationalStudents(String firstCity, String secondCity) {
        queryUniversities(firstCity, secondCity);

        return maxUniversity;
    }

    public static void queryUniversities(String firstCity, String secondCity){
        ArrayList<String> responses = new ArrayList<>();

        for (int i=1; i<=20; i++) {
            try {
                // setting up the http GET request
                urlBuilder = new StringBuilder(BASE_URL);
                URL url = new URL(urlBuilder.append(i).toString());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection ();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json");

                // executing the request
                int respondCode = connection.getResponseCode();

                if (respondCode == 200) {
                    // reading the response
                    BufferedReader inputReader = new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuffer content = new StringBuffer();
                    while ((inputLine = inputReader.readLine()) != null) {
                        content.append(inputLine);
                    }
                    inputReader.close();

                    computeMaxUniversityOccurrenceByCities(firstCity, secondCity, content.toString());
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void computeMaxUniversityOccurrenceByCities(String firstCity, String secondCity, String respoonse) {

        final String regex = "\"([^\"]+)\":[\"]*([^,^\\}^\"]+)";

        final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(respoonse);

        String university = "";
        String city = "";

        boolean jsonPairFlah = false;

        while (matcher.find()) {
            String match = matcher.group(0).replaceAll("[\"]", "");
            String[] keySet = match.split(":");

            if(keySet[0].equals("university")) {
                university = keySet[1];
            }

            if(keySet[0].equals("city")) {
                city = keySet[1];
            }

            if (!university.equals("") && !city.equals("")){

                if (city.equals(firstCity) || city.equals(secondCity)) {

                    if (universityCount.get(university) != null) {
                        Integer count = universityCount.get(university);
                        universityCount.put(university, count+=1);

                        setMaxUniversity(university, count);
                    } else {
                        Integer count = 1;
                        universityCount.put(university, count);
                        setMaxUniversity(university, count);
                    }
                }
                university = "";
                city = "";
            }
        }
    }

    public static void setMaxUniversity(String university, Integer count) {
        if (count > maxCount){
            maxCount = count;
            maxUniversity = university;
        }
    }

    public static void main (String[] args) {
        try {
            highestInternationalStudents("Singapore", "New York City");
            //computeMaxUniversityOccurrenceByCities("Singapore", "New York City", string);
            System.out.println("max xount: "+maxCount+" university: "+maxUniversity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
