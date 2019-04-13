package ascential.challenge;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public abstract class AbstractNumberFinder implements NumberFinder {

  private final Gson gson = new Gson();

  @Override
  public List<CustomNumberEntity> readFromFile(final String file) {

    List<CustomNumberEntity> list = null;
    try {
      list = readJsonStream(this.getClass().getClassLoader().getResourceAsStream(file));
    } catch (IOException e) {
      e.printStackTrace();
    }

    return list;
  }

  protected List<CustomNumberEntity> readJsonStream(final InputStream in) throws IOException {
    JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
    CustomNumberEntity[] data = gson.fromJson(reader, CustomNumberEntity[].class);

    return Arrays.asList(data);
  }
}
