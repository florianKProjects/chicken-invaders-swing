package src.com.chickenInavaders.tests;

import junit.framework.TestCase;
import org.junit.Test;
import src.com.chickenInavaders.controllers.settings.SettingsController;


public class TestSettingsController extends TestCase {

    private SettingsController settingsController;

    public void TestSettingsController() {
    }

    protected void setUp() {
        settingsController = SettingsController.getInstance();
    }

    protected void tearDown() {
    }

    @Test
    /* testSaveSettings
     * testing save settings to Settings.Json
     * */
    public void testSaveSettings() {
        assertTrue("True : save settings succeed", settingsController.saveSettings());
    }

    @Test
    /* testLoadSettings
     * testing load settings from Settings.Json
     * */
    public void testLoadSettings() {
        assertTrue("True : load settings succeed", settingsController.loadSettings());
    }


}
