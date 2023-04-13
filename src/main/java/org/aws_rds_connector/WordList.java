package org.aws_rds_connector;

import com.google.gson.Gson;
import org.aws_rds_connector.data.Word;

import java.sql.*;
import java.util.ArrayList;

public class WordList {
    private ArrayList<String> translationWords;
    private final DataSourceProperties db;
    public WordList() {
        this.db = new DataSourceProperties();
    }
    public WordList(DataSourceProperties db) {
        this.db = db;
    }
    /*
    We are creating a custom request handler and not using the defaults ones which means when configuring
    the Lambda function we will need to mention class and name. Ex: packageName.className::methodName.
    If the default handlers were implemented (RequestHandler or RequestStreamHandler), the handler identification
    would be packageName.className.
     */
    public ArrayList<String> handleRequest() {
        translationWords = new ArrayList<String>();

        try(Connection dbConnection = DriverManager.getConnection(db.getJdbcURL(), db.getUsername(), db.getPassword())){
            if(!dbConnection.isValid(0)) {
                System.out.println("Unable to connect through JDBC: " + db.getJdbcURL());
                System.exit(0);
            }

            PreparedStatement selectWordsQuery = dbConnection.prepareStatement("SELECT * FROM dictionary");
            ResultSet dbWords = selectWordsQuery.executeQuery();

            while(dbWords.next()){
                Word dbWord = new Word();
                Gson jsonWord = new Gson();
                // Get db data and place it into a word
                dbWord.setEsWord(dbWords.getString("es_word"));
                dbWord.setZpWord(dbWords.getString("zp_word"));
                dbWord.setEsExample(dbWords.getString("es_ex"));
                dbWord.setZpExample(dbWords.getString("zp_ex"));
                dbWord.setIcon(dbWords.getString("icon"));
                dbWord.setMeta(dbWords.getString("meta"));

                //Convert Word data to JSON
                translationWords.add(jsonWord.toJson(dbWord));
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(translationWords);
        return translationWords;
    }
}
