package net.tlalka.android.fiszki.services;

import android.content.Context;
import android.content.res.AssetManager;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

public class AssetsHelper {

    private final AssetManager assetManager;
    private final Gson gson;

    public AssetsHelper() {
        this.assetManager = AppHelper.getContext().getAssets();
        this.gson = new Gson();
    }

    public AssetsHelper(Context context) {
        this.assetManager = context.getAssets();
        this.gson = new Gson();
    }

    public List<String> getJsonList(String assetPath) throws IOException {
        List<String> fileList = new LinkedList<>();
        for (String fileName : assetManager.list(assetPath)) {
            fileList.add(assetPath + File.separator + fileName);
        }
        return fileList;
    }

    public <E> E getJson(String assetPath, Class<E> jsonDtoClass) throws IOException {
        return this.getJson(assetManager.open(assetPath), jsonDtoClass);
    }

    private <E> E getJson(InputStream stream, Class<E> jsonDtoClass) throws IOException {
        try {
            return this.getJson(new InputStreamReader(stream, "UTF-8"), jsonDtoClass);
        } finally {
            stream.close();
        }
    }

    private <E> E getJson(Reader reader, Class<E> jsonDtoClass) throws IOException {
        try {
            return this.gson.fromJson(reader, jsonDtoClass);
        } finally {
            reader.close();
        }
    }
}
