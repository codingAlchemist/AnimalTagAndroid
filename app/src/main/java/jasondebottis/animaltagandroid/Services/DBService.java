package jasondebottis.animaltagandroid.Services;

import com.activeandroid.query.Select;

import java.util.List;

import jasondebottis.animaltagandroid.Models.AnimalDataHolder;

/**
 * Created by jasondebottis on 8/5/16.
 */

public class DBService {

    public static List<AnimalDataHolder> GetAllSavedAnimals() {
        return new Select().from(AnimalDataHolder.class).execute();
    }
}
