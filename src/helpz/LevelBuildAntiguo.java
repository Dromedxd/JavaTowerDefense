//package helpz;
//
//import java.util.Random;
//
//public class LevelBuildAntiguo {
//	
//	private static Random random;
//
//	
//	public static int [][] getLevelData(){
//		
//		
//		
//		
//		//Crea un array de 0 dimensiones
//		//Donde acada valor es un "SLOT" del nivel
//		//aqui hacemos los mapas
//		
//		int[][] lvl = {
//	
////			{  |1|| 2|  |3, 4|   | 5, 6 | |7, 8 | | 9, 0 | |11, 12| |13, 14| |15  16|  |17 18| |9   10| |21  22|								 22 		
//			{  r(),r(), r(),r(),   5,  5, r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r() },
//			{  r(),r(), r(),r(),   5,r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r() },
//			{  r(),r(), r(),r(),   5,r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r() },
//			{  r(),r(), r(),r(),   5,r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r() },
//			{  r(),r(), r(),r(),   5,r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r() },
//			{  r(),r(), r(),r(),   7,  6,   6,  6,  6 ,  6,  6 ,  6,   6,  6,   6,  6,   6,  9, r(),r(), r(),r() },
//			{  r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),  5, r(),r(), r(),r() },
//			{  r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),	 5, r(),r(), r(),r() },
//			{  r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),  5, r(),r(), r(),r() },
//			{  r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),  5, r(),r(), r(),r() },
//			{  r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),  5, r(),r(), r(),r() },
//			{  r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),  5, r(),r(), r(),r() },
//			{  r(),r(), 10,  6,   6,  6,   6,  6,   6,  6,  6 ,  6,   6,  6 ,  6,   6,  6,   8, r(), r(),r(), r(),r() },
//			{  r(),r(), 5,r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r() },
//			{  r(),r(), 5,r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r() },
//			{  r(),r(), 5,r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r() },
//			{  r(),r(), 5,r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r() },
//			{  r(),r(), 5,r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r() },
//			{  r(),r(), 5,r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r() },
//			{  r(),r(), 5,r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r(), r(),r() }
//			
//			// |1|| 2|  |3, 4|   | 5, 6 | |7, 8 | | 9, 0 | |11, 12| |13, 14| |15  16|  |17 18| |9   10| |21  22|	
//			
//			
//		};
//		return lvl;
//	}
//	
//	private static int r() { //relleno random
//		random = new Random();
//		return random.nextInt(0,4);
//	}
//	
//}
