package com.bumppo109.firmaciv_firmacompat.addon;

import com.bumppo109.firmaciv_firmacompat.ModWatercraftMaterial;

public class NatureSpiritCompatLoader {
    public static void init() {
        ModWatercraftMaterial.addMaterials(NatureSpiritWatercraftMaterial.values());
    }
}
