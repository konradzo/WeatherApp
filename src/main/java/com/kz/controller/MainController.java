package com.kz.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private final static String surl = "http://api.apixu.com/v1/current.json?key=d578c483ed8b494bad8170534161511&q=Warsaw";
    private final static String hotImage = "https://bloximages.newyork1.vip.townnews.com/northwestgeorgianews.com/content/tncms/assets/v3/editorial/9/cf/9cf0d394-2352-11e3-9d30-001a4bcf6878/565f5a4629c30.image.jpg";
    private final static String coldImage = "https://www.beautifulonraw.com/sites/default/files/field/image/Shivering.jpg";

    @FXML
    private ImageView image;

    @FXML
    private Label temperature;

    public void initialize(URL location, ResourceBundle resources) {
        temperature.setText("Pobieranie danych pogody");
        try {
            URL url = new URL(surl);
            JSONObject json = new JSONObject(IOUtils.toString(url));

            JSONObject json2 = (JSONObject) json.get("current");
            String sstemp = json2.get("temp_c").toString();
            Double temp = Double.parseDouble(sstemp);
            Image im;
            if (temp < 15) {
                im = new Image(coldImage);
            } else {
                im = new Image(hotImage);
            }

            if (im != null) {
                image.setImage(im);
            }

            temperature.setText("Srednia temperatura: " + temp);

            System.out.println(json.toString());
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
}
