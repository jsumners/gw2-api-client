package com.jrfom.gw2.api.model.items;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.jackson.deserializers.ItemIdListDeserializer;
import com.jrfom.gw2.jackson.serializers.ItemIdListSerializer;

@JsonDeserialize(using = ItemIdListDeserializer.class)
@JsonSerialize(using = ItemIdListSerializer.class)
@Gw2ApiVersion("v1")
public class ItemIdList extends ArrayList<Number> {
}