package net.arsenalnetwork.arcanearteries.client.modfixes;

import net.arsenalnetwork.arcanearteries.utilities.ModReference;

import java.util.HashMap;

/**
 * Taken from https://github.com/GTNewHorizons/NewHorizonsCoreMod/tree/master/src/main/java/com/dreammaster/modfixes on July 25, 2019
 * This file is licenced under GNU General Public License v3.0 License
 * See https://github.com/GTNewHorizons/NewHorizonsCoreMod/blob/master/LICENSE for more details
 * The only changes that were made is that MainRegistry.Logger was changes to ModReference.LOGGER
 */

public final class ModFixesMaster
{
    private static boolean mEnabled = false;
    private static HashMap<String, IModFix> mModFixes = new HashMap<>();

    private ModFixesMaster() {}

    public static void registerModFix( IModFix pConstructedModFix )
    {
        if( mEnabled )
        {
            ModReference.LOGGER.error( String.format( "ModFix %s tried to register after enable phase. This mod-fix will be ignored", pConstructedModFix.getModFixName() ) );
            return;
        }

        if( !mModFixes.containsKey( pConstructedModFix.getModFixName() ) ) {
            mModFixes.put(pConstructedModFix.getModFixName(), pConstructedModFix);
        } else {
            ModReference.LOGGER.error(String.format("ModFix [%s] is already registered! Did you forget to change the name?", pConstructedModFix.getModFixName()));
        }
    }

    public static void enableModFixes()
    {
        if( mEnabled )
        {
            ModReference.LOGGER.error( "ModFixesMaster::enableModFixes() was called more than once" );
            return;
        }

        for( IModFix tModFix : mModFixes.values() )
        {
            if( !tModFix.init() ) {
                ModReference.LOGGER.error(String.format("ModFix [%s] could not be initialized", tModFix.getModFixName()));
            } else {
                ModReference.LOGGER.info(String.format("ModFix [%s] initialized and enabled", tModFix.getModFixName()));
            }
        }
        mEnabled = true;
    }

    public static IModFix getModFixInstance( String modFixName )
    {
        return mModFixes.get( modFixName );
    }
}
