package sounds;

import javax.sound.sampled.Clip;

import sounds.CargadorRecursos;

public class Sonido {

	final private Clip sonido;

	public Sonido(final String ruta) {
		sonido = CargadorRecursos.cargarSonido(ruta);
	}

	public void reproducir() {
		sonido.stop();
		// liberamos la memoria que usa el sonido
		sonido.flush();
		// rebobina el sonido
		sonido.setMicrosecondPosition(0);
		// inicia el sonido en el punto que sea
		sonido.start();
	}

	public void repetir() {
		sonido.stop();
		sonido.flush();
		sonido.setMicrosecondPosition(0);
		// repite el sonido en bucle
		sonido.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public long obtenerDuracion() {
		return sonido.getMicrosecondLength();
	}
}
