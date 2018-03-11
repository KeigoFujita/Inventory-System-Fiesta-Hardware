package com.hardware.fiesta;

import com.hardware.fiesta.LoaderUI.UILoader;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {



    @Override
    public void start(Stage primaryStage){

        UILoader uiLoader = new UILoader();
        uiLoader.getLoginFormController().setUiLoader(uiLoader);
        uiLoader.setLoginStage(primaryStage);
        primaryStage.show();

    }
    public static void main(String[] args){


        launch(args);


//
//
//        final String USER_AGENT = "Mozilla/5.0";
//
//        String url = "https://smsgateway.me/api/v3/contacts/create";
//
//        URL obj = new URL(url);
//        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
//
//        //add reuqest header
//        con.setRequestMethod("POST");
//        con.setRequestProperty("User-Agent", USER_AGENT);
//        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
//
//        String urlParameters = "email=keigofujita19@gmail.com&password=jklrsgang26&name=keigo&number=09126126901";
//
//        // Send post request
//        con.setDoOutput(true);
//        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//        wr.writeBytes(urlParameters);
//        wr.flush();
//        wr.close();
//
//        int responseCode = con.getResponseCode();
//        System.out.println("\nSending 'POST' request to URL : " + url);
//        System.out.println("Post parameters : " + urlParameters);
//        System.out.println("Response Code : " + responseCode);
//
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuffer response = new StringBuffer();
//
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        in.close();
//
//        //print result
//        System.out.println(response.toString());
//
//
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        XSSFSheet sheet = workbook.createSheet("Paksheet");
//        XSSFRow row = sheet.createRow(0);
//        XSSFCell cell = row.createCell(0);
//        cell.setCellValue("Paksheet");
//        workbook.write( new FileOutputStream(System.getProperty("user.home") + "\\Desktop\\"+"excel.xlsx"));
//        workbook.close();
//
//        System.exit(0);



    }

}
