package dev.app.enak.Model;

import android.net.Uri;

public class UriImageSource {
    String image_uri;
    Uri uriImage;

    public Uri getUriImage() {
        return uriImage;
    }

    public void setUriImage(Uri uriImage) {
        this.uriImage = uriImage;
    }

    public UriImageSource(String image_uri,Uri uriImage){
        this.image_uri = image_uri;
        this.uriImage = uriImage;
    }

    public String getImage_uri() {
        return image_uri;
    }

    public void setImage_uri(String image_uri) {
        this.image_uri = image_uri;
    }
}
