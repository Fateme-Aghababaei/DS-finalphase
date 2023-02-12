package pkg;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pkg.Forms.LoginForm;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        var path = "users2.json";
        var jsonArray = new JSONArray();
        try {
            jsonArray = (JSONArray) new JSONParser().parse(new FileReader(path));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        for (var jsonObject : jsonArray) {
            var user = (JSONObject) jsonObject;
            var id = user.get("id").toString();
            var name = user.get("name").toString();
            var dateOfBirth = user.get("dateOfBirth").toString();
            var uni = user.get("universityLocation").toString();
            var field = user.get("field").toString();
            var workplace = user.get("workplace").toString();
            var specialtiesArray = (JSONArray) user.get("specialties");
            var connectionIdArray = (JSONArray) user.get("connectionId");
            var specialties = new ArrayList<String>(specialtiesArray);
            var connectionID = new ArrayList<String>(connectionIdArray);
            for (int i = 0; i < connectionID.size(); i++) {
                connectionID.set(i, (String) connectionIdArray.get(i));
            }
            User.AllUsers.add(new User(id, name, "1234", dateOfBirth, uni, field, workplace, specialties, connectionID));
        }

        SwingUtilities.invokeLater(() -> {
            var loginForm = new LoginForm(User.AllUsers);
            loginForm.setVisible(true);
        });
    }
}
