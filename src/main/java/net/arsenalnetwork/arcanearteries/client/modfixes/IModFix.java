package net.arsenalnetwork.arcanearteries.client.modfixes;

/**
 * Taken from https://github.com/GTNewHorizons/NewHorizonsCoreMod/tree/master/src/main/java/com/dreammaster/modfixes on July 25, 2019
 * This file is licenced under GNU General Public License v3.0 License
 * See https://github.com/GTNewHorizons/NewHorizonsCoreMod/blob/master/LICENSE for more details
 * NO Changes were made.
 */

public interface IModFix
{
    boolean needsForgeEventBus();

    boolean needsFMLEventBus();

    String getModFixName();

    boolean getIsActive();

    boolean init();
}
