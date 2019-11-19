package com.example.momo.network.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;

public class MyResult extends ResponseBody {
    @Override
    public long contentLength() {
        return 0;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return null;
    }

    @NotNull
    @Override
    public BufferedSource source() {
        return null;
    }
}
