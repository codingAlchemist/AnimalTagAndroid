package jasondebottis.animaltagandroid.Services;


import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;
import java.util.List;

import jasondebottis.animaltagandroid.BuildConfig;
import jasondebottis.animaltagandroid.Models.AnimalModel;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;


public class RetrofitService {

    private enum AnimalType {
        kBird,
        kReptile,
        kInsect
    }

    public RetrofitService() {

    }


    public static AnimalService GetService() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BuildConfig.baseUrl);
        builder.addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC).create()));
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(AnimalService.class);
    }


    public static void GetAnimals(AnimalType inAnimalType, Callback<List<AnimalModel>> inCallback) {
        switch (inAnimalType) {
            case kBird:
                GetService().GetBirds().enqueue(inCallback);
                break;
            case kReptile:
                GetService().GetReptiles().enqueue(inCallback);
                break;
            case kInsect:
                GetService().GetInsects().enqueue(inCallback);
                break;
        }

    }

    public static void GetAnimals(Callback<List<AnimalModel>> inCallback) {
        GetService().GetAnimals().enqueue(inCallback);
    }

    public static void GetAnimal(String inTag, Callback<AnimalModel> inCallback) {
        GetService().GetAnimalByTag(inTag).enqueue(inCallback);
    }

    public interface AnimalService {
        @GET("birds")
        Call<List<AnimalModel>> GetBirds();

        @GET("reptiles")
        Call<List<AnimalModel>> GetReptiles();

        @GET("insects")
        Call<List<AnimalModel>> GetInsects();

        @GET("animals")
        Call<List<AnimalModel>> GetAnimals();

        @GET("animals/{tag}")
        Call<AnimalModel> GetAnimalByTag(@Path("tag") String inTag);
    }


}
