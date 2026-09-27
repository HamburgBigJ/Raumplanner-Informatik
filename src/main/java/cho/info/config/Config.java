package cho.info.config;

import com.google.gson.annotations.SerializedName;

public record Config(
        @SerializedName("App") AppConfig app
) {
    public record AppConfig(
            @SerializedName("Name") String name,
            int width,
            int height,
            int fpsTarget,
            @SerializedName("BackgroundColor") String backgroundColor
    ) {}
}