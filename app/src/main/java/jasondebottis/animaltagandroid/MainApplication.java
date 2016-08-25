package jasondebottis.animaltagandroid;

import com.activeandroid.ActiveAndroid;

/**
 * Created by jasondebottis on 8/1/16.
 */

public class MainApplication extends com.activeandroid.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
