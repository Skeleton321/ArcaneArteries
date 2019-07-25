package net.arsenalnetwork.arcanearteries.client.modfixes;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

/**
 * Taken from https://github.com/GTNewHorizons/NewHorizonsCoreMod/tree/master/src/main/java/com/dreammaster/modfixes on July 25, 2019
 * This file is licenced under GNU General Public License v3.0 License
 * See https://github.com/GTNewHorizons/NewHorizonsCoreMod/blob/master/LICENSE for more details
 * NO Changes were made.
 */

public abstract class ModFixBase implements IModFix
{
    private String _mModFixName;

    protected ModFixBase(String pModFixName)
    {
        _mModFixName = pModFixName;

        if( needsForgeEventBus() ) {
            MinecraftForge.EVENT_BUS.register(this);
        }
        if( needsFMLEventBus() ) {
            FMLCommonHandler.instance().bus().register(this);
        }
    }

    @Override
    public final String getModFixName()
    {
        return _mModFixName;
    }
}
