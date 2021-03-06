package com.vladsch.flexmark.core.test.util.renderer;

import com.vladsch.flexmark.test.util.spec.ResourceLocation;
import org.jetbrains.annotations.NotNull;

final public class FullOrigSpecCoreTest extends OrigSpecCoreTest {
    static final String SPEC_RESOURCE = "/spec.txt";
    public static final @NotNull ResourceLocation RESOURCE_LOCATION = ResourceLocation.of(SPEC_RESOURCE);

    public FullOrigSpecCoreTest() {
        super(null);
    }
}
