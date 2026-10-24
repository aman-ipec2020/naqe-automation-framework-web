package utils;

import com.google.gson.Gson;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class DataUtilityProvider {

    @DataProvider(name="jsonData")
    public static Object[][] getJsonDataToMap(String filePath) throws IOException {
        String jsonContent = new String(Files.readAllBytes(
                Paths.get(System.getProperty("user.dir") + filePath)));

        Type type = new TypeToken<List<HashMap<String, String>>>() {}.getType();

        List<HashMap<String, String>> list = new Gson().fromJson(jsonContent, type);

        Object[][] table = new Object[list.size()][1];
        for(int i=0; i<list.size();i++)
            table[i][0]= list.get(i);

        return table;
    }


    @Test(dataProvider = "jsonData")
    public void testFillForm2(HashMap<String, String> data)  {

        System.out.println(data.get("email"));
        System.out.println(data.get("password"));
    }


/*
    @DataProvider(name = "BasicData1")
    public Object[][] basicData1()  {

        return new Object[][] {{"user1@yahoo.com", "password1"}, {"user2@gmail.com", "password2"}};
    }

    @DataProvider(name = "BasicData2")
    public Object[][] basicData2()  {

        HashMap<String, String> userData1 = new HashMap<>();

        userData1.put("email", "user1@yahoo.com");
        userData1.put("password", "password1");

        HashMap<String, String> userData2 = new HashMap<>();

        userData2.put("email", "user2@gmail.com");
        userData2.put("password", "password2");

        return new Object[][] {{userData1}, {userData1}};
    }
*/
}
