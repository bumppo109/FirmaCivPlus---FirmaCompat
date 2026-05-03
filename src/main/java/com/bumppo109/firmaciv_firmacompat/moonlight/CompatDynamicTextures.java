// File: CompatDynamicTextures.java
package com.bumppo109.firmaciv_firmacompat.moonlight;

import com.bumppo109.firmaciv_firmacompat.FirmaCivFirmaCompat;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicTexturePack;
import net.minecraft.resources.ResourceLocation;

public class CompatDynamicTextures extends DynamicTexturePack {

    public static final CompatDynamicTextures INSTANCE = new CompatDynamicTextures();

    private CompatDynamicTextures() {
        super(ResourceLocation.fromNamespaceAndPath(FirmaCivFirmaCompat.MOD_ID, "dynamic_textures"));
    }

    // Public method to register the pack
    public void register() {
        this.registerPack();        // now accessible because we're inside the subclass
        //this.addPackLogo();
    }
}