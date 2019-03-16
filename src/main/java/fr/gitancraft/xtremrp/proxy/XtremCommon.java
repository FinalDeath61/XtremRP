package fr.gitancraft.xtremrp.proxy;


import java.io.File;

import fr.gitancraft.xtremrp.Util.RegistryHandler;

public class XtremCommon {

	public void preinit(File configFile) {
		RegistryHandler.preInitRegistries();
	}

	public void init() {

	}

}
