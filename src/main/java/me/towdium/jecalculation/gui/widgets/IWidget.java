package me.towdium.jecalculation.gui.widgets;

import mcp.MethodsReturnNonnullByDefault;
import me.towdium.jecalculation.gui.JecaGui;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

/**
 * Author: towdium
 * Date:   8/14/17.
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public interface IWidget {  // TODO unify listener behavior when manually set and pass instance to listener
    void onDraw(JecaGui gui, int xMouse, int yMouse);

    default boolean onTooltip(JecaGui gui, int xMouse, int yMouse, List<String> tooltip) {
        return false;
    }

    default boolean onClicked(JecaGui gui, int xMouse, int yMouse, int button) {
        return false;
    }

    default boolean onKey(JecaGui gui, char ch, int code) {
        return false;
    }

    default boolean onScroll(JecaGui gui, int xMouse, int yMouse, int diff) {
        return false;
    }
}