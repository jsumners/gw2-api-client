package com.jrfom.gw2.jackson.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.base.Optional;
import com.jrfom.gw2.api.model.colors.Color;
import com.jrfom.gw2.api.model.colors.ColorsList;
import com.jrfom.gw2.api.model.colors.MaterialColor;
import com.jrfom.gw2.api.model.colors.RGB;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ColorsListDeserializerTest {
  private static final Logger log = LoggerFactory.getLogger(ColorsListDeserializerTest.class);

  private String json;

/*  @Before
  public void setup() throws IOException {
    InputStream jsonStream = this.getClass().getResourceAsStream("/json/ColorsList.json");
    BufferedReader reader = new BufferedReader(new InputStreamReader(jsonStream));
    StringBuilder builder = new StringBuilder();

    String line;
    while ( (line = reader.readLine()) != null ) {
      builder.append(line);
    }

    json = builder.toString();
    reader.close();
  }*/

  @Test
  public void test() throws IOException {
    ColorsListDeserializer deserializer = new ColorsListDeserializer();
    ObjectMapper mapper = new ObjectMapper();
    SimpleModule simpleModule = new SimpleModule(
      "my_module",
      new Version(1, 0, 0, null, "com.jrfom", "foo")
    );
    simpleModule.addDeserializer(ColorsList.class, deserializer);
    mapper.registerModule(simpleModule);

    ColorsList colorsList = mapper.readValue(
      this.getClass().getResourceAsStream("/json/ColorsList.json"),
      ColorsList.class
    );

    assertTrue(colorsList.size() > 0);

    Optional<Color> result = colorsList.getColorWithName("Pink Ice");
    assertTrue(result.isPresent());

    Color color = result.get();
    assertTrue(color.getName().equals("Pink Ice"));

    RGB baseRGB = color.getBaseRgb();
    assertNotNull(baseRGB);
    assertTrue(baseRGB.getRed() == 128);
    assertTrue(baseRGB.getGreen() == 26);
    assertTrue(baseRGB.getBlue() == 26);

    MaterialColor cloth = color.getCloth();
    assertNotNull(cloth);
    assertTrue(cloth.getBrightness() == 50);
    assertTrue(cloth.getContrast() == 1.36719);
    assertTrue(cloth.getHue() == 8);
    assertTrue(cloth.getSaturation() == 0.351563);
    assertTrue(cloth.getLightness() == 1.36719);
    RGB clothRGB = cloth.getRgb();
    assertNotNull(clothRGB);
    assertTrue(clothRGB.getRed() == 216);
    assertTrue(clothRGB.getGreen() == 172);
    assertTrue(clothRGB.getBlue() == 164);

    // That should be good enough.
  }
}