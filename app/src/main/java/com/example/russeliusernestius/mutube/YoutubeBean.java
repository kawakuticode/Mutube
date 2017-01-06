package com.example.russeliusernestius.mutube;

import android.graphics.Bitmap;


/**
 * Created by russeliusernestius on 05/01/17.
 */

public class YoutubeBean {
    private String title;
    private String id;
    private Bitmap thumbnails;

    public YoutubeBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Bitmap getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(Bitmap thumbnails) {
        this.thumbnails = thumbnails;
    }
}
