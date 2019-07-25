package net.arsenalnetwork.arcanearteries.client.modfixes.thaumcraft;

import net.arsenalnetwork.arcanearteries.client.modfixes.ModFixBase;

public class TestFix extends ModFixBase
{
    public TestFix()
    {
        super("TestFix");
    }

    @Override
    public boolean needsForgeEventBus() {
        return true;
    }

    @Override
    public boolean needsFMLEventBus() {
        return true;
    }

    @Override
    public boolean getIsActive() {
        return true;
    }

    @Override
    public boolean init() {
        return true;
    }
}
