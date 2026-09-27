package cho.info.config;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ConfigLoader {

    public static Config load() {
        try (var reader = Files.newBufferedReader(
                Path.of("resources", "config.json")
        )) {
            return new Gson().fromJson(reader, Config.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}