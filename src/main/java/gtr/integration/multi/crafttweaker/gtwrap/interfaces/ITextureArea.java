package gtr.integration.multi.crafttweaker.gtwrap.interfaces;

import crafttweaker.annotations.ZenRegister;
import gtr.api.gui.resources.TextureArea;
import gtr.integration.multi.crafttweaker.gtwrap.impl.MCTextureArea;
import net.minecraft.util.ResourceLocation;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import javax.annotation.Nonnull;

/**
 * Can be used for defining a custom recipe arrow.
 */
@ZenClass("mods.gregtech.render.ITextureArea")
@ZenRegister
public interface ITextureArea {

    @Nonnull
    TextureArea getInternal();

    /**
     * Get the full image at a location.
     *
     * You will most likely wish to define this in a script with {@code #loader preinit}, so the texture actually gets loaded.
     *
     * @param imageLocation The full location of the image.
     * @return An {@link ITextureArea} of the given image.
     */
    @ZenMethod
    static ITextureArea fullImage(String imageLocation) {
        return new MCTextureArea(new TextureArea(new ResourceLocation(imageLocation), 0, 0, 1, 1));
    }

    /**
     * Get an area of an image at a location.
     *
     * You will most likely wish to define this in a script with {@code #loader preinit}, so the texture actually gets loaded.
     *
     * @param imageLocation The full location of the image.
     * @return An {@link ITextureArea} of the area of the given image.
     */
    @ZenMethod
    static ITextureArea areaOfImage(String imageLocation, int imageSizeX, int imageSizeY, int u, int v, int width, int height) {
        return new MCTextureArea(TextureArea.areaOfImage(imageLocation, imageSizeX, imageSizeY, u, v, width, height));
    }

    /**
     * Get an area of the current {@link ITextureArea}.
     *
     * @param offsetX The offsetX relative to this image.
     * @param offsetY The offsetY relative to this image.
     * @param width The width of the image.
     * @param height The height of the image.
     * @return The new {@link ITextureArea}.
     */
    @ZenMethod
    ITextureArea getSubArea(double offsetX, double offsetY, double width, double height);

}
