package com.bumppo109.firmaciv_firmacompat.addon;

import com.bumppo109.firmaciv_firmacompat.ModWatercraftMaterial;

public class RUCompatLoader {
    public static void init() {
        ModWatercraftMaterial.addMaterials(RUWatercraftMaterial.values());
    }
}
