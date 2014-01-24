package com.jrfom.gw2.api.model.items;

import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents properties that are specific to a
 * {@link com.jrfom.gw2.api.model.items.GizmoItem}. At this time, the only
 * known extra property is the "type". For details, see
 * {@link ItemProperties#getType()}.
 */
@Gw2ApiVersion("v1")
public class GizmoItemProperties extends ItemProperties {
}