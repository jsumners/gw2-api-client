package com.jrfom.gw2.api.model.items;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.jsontype.NamedType;

/**
 * No usage at the moment. But could be useful in the future?
 */
public class ItemClassRegistry {
  private static ItemClassRegistry itemClassRegistry;
  private final HashMap<String, Class<?>> registry;

  private ItemClassRegistry() {
    this.registry = new HashMap<>(0);

    ObjectMapper mapper = new ObjectMapper();
    AnnotationIntrospector ai = mapper.getDeserializationConfig().getAnnotationIntrospector();
    AnnotatedClass ac = AnnotatedClass.constructWithoutSuperTypes(
      GenericItem.class,
      ai,mapper.getDeserializationConfig()
    );

    List<NamedType> subtypes = ai.findSubtypes(ac);
    if (subtypes != null) {
      for (NamedType subtype : subtypes) {
        this.registry.put(
          subtype.getType().getSimpleName().toLowerCase(),
          subtype.getType()
        );
      }
    }
  }

  public static ItemClassRegistry getInstance() {
    if (itemClassRegistry == null) {
      itemClassRegistry = new ItemClassRegistry();
    }

    return itemClassRegistry;
  }

  public Class<?> getClassForType(String type) {
    return this.registry.get(type.toLowerCase());
  }
}