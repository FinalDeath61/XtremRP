package fr.gitancraft.xtremrp.Util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;

public class ToByteUtil {
	

	public static byte[] objToBytes(Object obj) throws IOException {
		byte[] bytes = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null;
		try {
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray();
		} finally {
			if (oos != null)
				oos.close();
			if (bos != null)
				bos.close();
		}
		return bytes;
	}

	public static Object bytesToObj(byte[] bytes) throws IOException, ClassNotFoundException {
		Object obj = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			bis = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bis);
			obj = ois.readObject();
		} finally {
			if (ois != null)
				ois.close();
			if (bis != null)
				bis.close();
		}
		return obj;
	}
	
	
}
