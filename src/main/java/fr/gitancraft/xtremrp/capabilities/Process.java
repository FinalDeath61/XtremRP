package fr.gitancraft.xtremrp.capabilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class Process implements IProcess, INBTSerializable<NBTTagCompound> {

    private int cooldown;
    private int maxCooldown;
    private boolean reversed = false;

    private Runnable doProcess, processDone;

    public Process(int maxCooldown, Runnable doProcess, Runnable processDone) {
        cooldown = 0;
        this.maxCooldown = maxCooldown;
        this.doProcess = doProcess;
        this.processDone = processDone;
    }

    public void setMaxCooldown(int maxCooldown) {
        this.maxCooldown = maxCooldown;
    }

    public int getMaxCooldown() {
        return maxCooldown;
    }

    public boolean isReversed() {
        return reversed;
    }

    public void setReversed(boolean reversed) {
        this.reversed = reversed;
    }

    @Override
    public int getProcessDone() {
        return cooldown;
    }

    @Override
    public void doProcess() {
        if (reversed)
            cooldown--;
        else
            cooldown++;
        doProcess.run();
        if (this.cooldown == maxCooldown)
            processDone();
        if(maxCooldown != 0)
            cooldown %= maxCooldown;

        if(cooldown < 0)
            cooldown = 0;
    }

    @Override
    public void processDone() {
        processDone.run();
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger("cooldown", cooldown);
        tag.setInteger("maxCooldown", maxCooldown);
        return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        this.cooldown = nbt.getInteger("cooldown");
        this.maxCooldown = nbt.getInteger("maxCooldown");
    }
}
