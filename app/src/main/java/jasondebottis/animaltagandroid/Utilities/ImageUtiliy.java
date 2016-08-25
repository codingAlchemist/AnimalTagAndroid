package jasondebottis.animaltagandroid.Utilities;

import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import jasondebottis.animaltagandroid.MainActivity;



public class ImageUtiliy {

    public static void LoadImage(String inStringUri, ImageView inImageView) {
        Picasso.with(MainActivity.GetContext())
                .load(Uri.parse(inStringUri))
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .noFade()
                .into(inImageView);
    }
}
