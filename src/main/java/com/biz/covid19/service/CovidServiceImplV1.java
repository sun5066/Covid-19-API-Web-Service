package com.biz.covid19.service;

import com.biz.covid19.domain.CovidVO;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("covidServiceImpl")
public class CovidServiceImplV1 implements CovidService {

    public CovidServiceImplV1() {

    }

    public List<CovidVO> getCovidList() {
        JSONParser jsonParser = new JSONParser();
        Object object = null;
        try {
            object = jsonParser.parse(getDocument());
            JSONObject jsonObject = (JSONObject) object;
            List<CovidVO> covidVOList = new ArrayList<>();
            JSONObject jsonObj = null;
            String strKey = "";

            for (Object key : jsonObject.keySet()) {
                if (key.toString().equals("resultCode") || key.toString().equals("resultMessage")) {
                    continue;
                }
                strKey = key.toString();
                jsonObj = (JSONObject) jsonObject.get(strKey);

                CovidVO covidVO = CovidVO.builder()
                        .id(strKey)
                        .countryName(jsonObj.get("countryName").toString())
                        .newCase(jsonObj.get("newCase").toString())
                        .totalCase(jsonObj.get("totalCase").toString())
                        .recovered(jsonObj.get("recovered").toString())
                        .death(jsonObj.get("death").toString())
                        .percentage(jsonObj.get("percentage").toString())
                        .newFcase(jsonObj.get("newFcase").toString())
                        .newCcase(jsonObj.get("newCcase").toString())
                        .build();
                covidVOList.add(covidVO);
            }
            return covidVOList;
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private String getDocument() {
        try {
            URL url = new URL("https://api.corona-19.kr/korea/country/new/?serviceKey=931894787783b592835283b2e1ec05492");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            String inputLine = "";
            StringBuffer reponse = new StringBuffer();

            while ((inputLine = bufferedReader.readLine()) != null) {
                reponse.append(inputLine);
            }
            bufferedReader.close();

            return reponse.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
