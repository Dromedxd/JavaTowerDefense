package sounds;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;

public class CargadorRecursos {
	// con esto nos aeguramos que la imagen de entrada sea compatible para que a
	// java no le de un paro
	// este lo usamos para cargar imagenes que no tengan opacas y el otro para las
	// transparentes
	public static BufferedImage cargarImagenCompatibleOpaca(final String ruta) {
		Image imagen = null;
		// al cargar una imagen es una operacion arriegada que la rodeamos de try catch
		try {
			imagen = ImageIO.read(new File(ruta));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// con esto obtendremos la configuracion grafica del monitor que estemos usando
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();
		// con esto cargamos una imagen compatible de las dimensiones de la propia
		// imagen
		BufferedImage imagenAcelerada = gc.createCompatibleImage(imagen.getWidth(null), imagen.getHeight(null),
				Transparency.OPAQUE);
		// creamos un objeto grafico que tendra las caracterisicas del monitor
		Graphics g = imagenAcelerada.getGraphics();
		// pegamos la imagen en el objeto grafico y luego borramos la imagen que no
		// sirve
		g.drawImage(imagen, 0, 0, null);
		g.dispose();

		return imagenAcelerada;
		// esto viene bien porque si la imagen tiene unos requisitos que indique java en
		// vez de cargarse en la ram lo carga a la tarjeta grafica aumentando el
		// rendimiento del juego
	}

	public static BufferedImage cargarImagenCompatibleTranslucida(final String ruta) {
		Image imagen = null;

		try {
			// imagen = ImageIO.read(ClassLoader.class.getResource(ruta));
			imagen = ImageIO.read(new File(ruta));
		} catch (IOException e) {
			e.printStackTrace();
		}

		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();

		BufferedImage imagenAcelerada = gc.createCompatibleImage(imagen.getWidth(null), imagen.getHeight(null),
				Transparency.TRANSLUCENT);

		Graphics g = imagenAcelerada.getGraphics();

		g.drawImage(imagen, 0, 0, null);
		g.dispose();

		return imagenAcelerada;
	}

	public static String leerArchivoTexto(final String ruta) {
		// usaremos el string para guardar todo el archivo
		String contenido = "";
		String linea;

		InputStream entradaBytes = null;
		BufferedReader lector = null;
		try {
			// va a leer en forma de flujo de bytes la ruta que le demos
			entradaBytes = new FileInputStream(ruta);
			// inputStreamReader recibe la ruta y se la pasa a BufferedReader que la va a
			// interpretar de bytes a texto(De esta forma el texto fluye hacia Java)
			lector = new BufferedReader(new InputStreamReader(entradaBytes));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			while ((linea = lector.readLine()) != null) {
				contenido += linea;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// esto lo usamos para cerrar los recursos que hemos utilizado
				// independientemente de si falla o no en su lectura
				if (entradaBytes != null) {
					entradaBytes.close();
				}
				if (lector != null) {
					lector.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return contenido;
	}

	public static Font cargarFuente(final String ruta) {
		Font fuente = null;
		InputStream entradaBytes = null;
		try {
			entradaBytes = new FileInputStream(ruta);
			try {
				fuente = Font.createFont(Font.TRUETYPE_FONT, entradaBytes);
			} catch (FontFormatException e) {
				// que la fuente no sea del formato
				e.printStackTrace();
			} catch (IOException e) {
				// problema de la entrada
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		// cambiamos aqui el tamaño de la fuenta reconvirtiendo el objeto
		fuente = fuente.deriveFont(12f);
		return fuente;
	}

	public static Clip cargarSonido(final String ruta) {
		Clip clip = null;

		try {
			// cargamos el fichero como un flujo de datos
			InputStream is = new FileInputStream(ruta);
			// cargamos el sonido con un input para sonidos y usamos un buffer para cargar
			// el audio poco a poco
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
			// esto le da cual es el tipo de formato de entrada del audio
			DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
			// esto reserva la memoria necesaria para sonido
			clip = (Clip) AudioSystem.getLine(info);
			// cargamos el audio en memoria
			clip.open(ais);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-35);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return clip;
	}
}
