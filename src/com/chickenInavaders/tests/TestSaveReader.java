package src.com.chickenInavaders.tests;

import junit.framework.TestCase;
import org.junit.Test;
import src.com.chickenInavaders.controllers.settings.SaveReader;

public class TestSaveReader extends TestCase {

    private SaveReader saveReader;
    String name = "test";
    int score = 200;
    int level = 1;
    String date = "01/01/1970";
    int indexOfReturned;

    protected void setUp() {
        saveReader = new SaveReader();
    }

    protected void tearDown() {
    }

    @Test
    /* testAddRecord
     * testing adding new record to Save.Json
     * */
    public void testAddRecord() {
        indexOfReturned = saveReader.addRecord(name, score, level, date);
        SaveReader.save saveObj = new SaveReader.save(indexOfReturned, name, score, level, date);
        SaveReader.save afterAdded = saveReader.LoadsList.get(indexOfReturned);
        assertEquals("True : two objects have the same values", saveObj.toString(), afterAdded.toString());
    }

    @Test
    /* testUpdateRecord
     * testing update exist record in Save.Json
     * */
    public void testUpdateRecord() {
        // to init indexOfReturned with current item index
        testAddRecord();

        score = 300;
        level = 3;
        name = "test1";

        saveReader.updateRecord(indexOfReturned, "name", name);
        assertEquals("True : name field updated successfully", name, saveReader.LoadsList.get(indexOfReturned).name);

        saveReader.updateRecord(indexOfReturned, "score", score);
        assertEquals("True : score field updated successfully", score, saveReader.LoadsList.get(indexOfReturned).score);

        saveReader.updateRecord(indexOfReturned, "level", level);
        assertEquals("True : level field updated successfully", level, saveReader.LoadsList.get(indexOfReturned).level);
    }

}
