package funtzioak;

import modelo.Memoria;

public class Metodoak {
	public static void hasieratuArray(Memoria memoria, boolean arrayHasieratuak) {
		if (!arrayHasieratuak) {
			memoria.gehituAeroportua(null);
			
			System.out.println("Arrayak Hasieratua");
		}
	}
}
