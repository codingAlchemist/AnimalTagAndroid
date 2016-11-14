package jasondebottis.animaltagandroid;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;

/**
 * Created by jasondebottis on 8/1/16.
 */

public class MainApplication extends com.activeandroid.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Configuration dbConfig = new Configuration.Builder(this).setDatabaseName("Animals.db").create();
        ActiveAndroid.initialize(dbConfig);
    }
}
