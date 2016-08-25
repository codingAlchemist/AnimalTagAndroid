package jasondebottis.animaltagandroid.Services;

import com.activeandroid.query.Select;

import java.util.List;

import jasondebottis.animaltagandroid.Models.AnimalModel;

/**
 * Created by jasondebottis on 8/5/16.
 */

public class DBService {

    public static List<AnimalModel> GetAllSavedAnimals() {
        return new Select().from(AnimalModel.class).execute();
    }
}
