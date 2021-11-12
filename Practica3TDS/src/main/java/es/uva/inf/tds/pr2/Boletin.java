package es.uva.inf.tds.pr2;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Implementacion de un boletin de noticias.
 * 
 * @author aleniet
 * @author davmele
 */
public class Boletin {
	
	private ArrayList<Noticia> noticias;
	
	/**
	 * Inicializador de un boletin vacio
	 */
	public Boletin() {
		noticias = new ArrayList<>();
	}
	
	/**
	 * Inicializador de un boletin a partir de una lista de noticias.
	 * Si alguna noticia de la lista es null no se añadira al boletin.
	 * Las noticias se añadiran al boletin en el mismo orden en que aparecen en la lista
	 * 
	 * @param noticias la lista de noticias
	 * @throws IllegalArgumentException si hay alguna noticia repetida,
	 * 									si hay alguna noticia igual a null  o
	 * 									si la lista de noticias es null
	 */
	public Boletin(Noticia[] noticias) {
		if(noticias == null) {
			throw new IllegalArgumentException("Lista de noticias igual a null");
		}
		for(int i = 0; i < noticias.length; i++) {
			if(noticias[i] == null) {
				throw new IllegalArgumentException("Noticia igual a null");
			}
		}
		
		for(int i = 0; i < noticias.length; i++) {
			for(int j = 0; j < noticias.length; j++) {
				if(j != i && noticias[i].equals(noticias[j])) {
					throw new IllegalArgumentException("Noticia repetida");
				}
			}
		}
		
		this.noticias = new ArrayList<>();
		
		for(int i = 0; i < noticias.length; i++) {
			addNoticia(noticias[i]);
		}
	}

	/**
	 * Añade una noticia al boletin
	 * 
	 * @param noticia la noticia a añadir
	 * @throws IllegalArgumentException si la noticia ya esta en el boletin o
	 * 									si la noticia es null
	 */
	public void addNoticia(Noticia noticia) {
		if(noticia == null) {
			throw new IllegalArgumentException("Noticia igual a null");
		}

		for(int i = 0; i < noticias.size(); i++) {
			
			if(noticias.get(i).equals(noticia)) {
				throw new IllegalArgumentException("Noticia repetida");
			}
		}
		if(getNumNoticias() == 0) {
			noticias.add(noticia);
		} else {
			boolean noticiaAnadida = false;
			for(int i = 0; i < getNumNoticias() && !noticiaAnadida; i++) {
				if(noticias.get(i).getFecha().isAfter(noticia.getFecha())) {
					noticias.add(i, noticia);
					noticiaAnadida = true;
				} else {
					if(i == getNumNoticias()-1) {
						noticias.add(noticia);
						noticiaAnadida = true;
					}
				}
			}
		}
	}

	/**
	 * Devuelve el numero de noticias del boletin
	 * 
	 * @return un int con el numero de noticias
	 */
	public int getNumNoticias() {
		return noticias.size();
	}

	/**
	 * Devuelve la fecha de las noticias mas recientes del boletin
	 * 
	 * @return un LocalDate con la fecha
	 * @throws IllegalStateException si el boletin esta vacio
	 */
	public LocalDate getFechaNoticiasMasRecientes() {
		if(getNumNoticias() == 0) {
			throw new IllegalStateException("Boletin vacio");
		}
		return noticias.get(getNumNoticias()-1).getFecha();
	}

	/**
	 * Devuelve la fecha de las noticias mas antiguas del boletin
	 * 
	 * @return un LocalDate con la fecha
	 * @throws IllegalStateException si el boletin esta vacio
	 */
	public LocalDate getFechaNoticiasMasAntiguas() {
		if(getNumNoticias() == 0) {
			throw new IllegalStateException("Boletin vacio");
		}
		return noticias.get(0).getFecha();
	}

	/**
	 * Devuelve las noticias del boletin ordenadas cronologicamente de anterior a posterior.
	 * Si coincide la fecha de publicacion de dos noticias se considerara anterior
	 * la que primero se añadio al boletin
	 * 
	 * @return un array de noticias con las noticias
	 * @throws IllegalStateException si el boletin esta vacio
	 */
	public Noticia[] getNoticiasOrdenCronologico() {
		if(getNumNoticias() == 0) {
			throw new IllegalStateException("Boletin vacio");
		}
		return noticias.toArray(new Noticia[0]);
	}

	/**
	 * Devuelve las noticias del boletin ordenadas por categoria.
	 * El orden de aparicion de las categorias es: nacional, internacional, 
	 * sociedad, economia, deporte y cultura.
	 * Dentro de cada categoria las noticias apareceran ordenadas por 
	 * orden cronologico de anterior a posterior.
	 * 
	 * @return un array con las noticias
	 * @throws IllegalStateException si el boletin esta vacio
	 */
	public Noticia[] getNoticiasOrdenCategoria() {
		if(getNumNoticias() == 0) {
			throw new IllegalStateException("El boletin esta vacio");
		}
		Noticia[] noticiasOrdenCategoria = new Noticia[getNumNoticias()];
		int numeroNoticia = 0;
		Categoria[] categorias = {Categoria.NACIONAL, Categoria.INTERNACIONAL, Categoria.SOCIEDAD, 
				Categoria.ECONOMIA, Categoria.DEPORTE, Categoria.CULTURA};
	
		for(int j = 0; j < categorias.length; j++) {
			for(int i = 0; i < getNumNoticias(); i++) {
				if(noticias.get(i).getCategoria().equals(categorias[j])) {
					noticiasOrdenCategoria[numeroNoticia] = noticias.get(i);
					numeroNoticia++;
				}
			}
		}
		return noticiasOrdenCategoria;
	}

	/**
	 * Devuelve las noticias del boletin similares a la noticia especificada
	 * 
	 * @param noticia la noticia especificada
	 * @return un array con las noticias similares del boletin; null si ninguna es similar
	 * @throws IllegalArgumentException si la noticia es null
	 */
	public Noticia[] getNoticiasSimilares(Noticia noticia) {
		if(noticia == null) {
			throw new IllegalArgumentException("Noticia igual a null");
		}
		ArrayList<Noticia> noticiasSimilares = new ArrayList<>();
		for(int i = 0; i < getNumNoticias(); i++) {
			if(noticias.get(i).similarTo(noticia)) {
				noticiasSimilares.add(noticias.get(i));
			}
		}
		return noticiasSimilares.isEmpty() ? null : noticiasSimilares.toArray(new Noticia[0]);
	}

	/**
	 * Devuelve un nuevo boletin con todas las noticias del boletin publicadas
	 * en la fecha especificada
	 * 
	 * @param fecha la fecha especificada
	 * @return un Boletin con las noticias
	 * @throws IllegalArgumentException si la fecha dada es igual a null
	 */
	public Boletin getBoletinPorFecha(LocalDate fecha) {
		if(fecha == null) {
			throw new IllegalArgumentException("Noticia igual a null");
		}
		
		Boletin boletinNoticiasFecha = new Boletin();
		Noticia noticia;
		
		for(int i = 0; i < getNumNoticias(); i++) {
			noticia = noticias.get(i);
			if(noticia.getFecha().equals(fecha)) {
				boletinNoticiasFecha.addNoticia(noticia);
			}
		}
		return boletinNoticiasFecha;
	}

	/**
	 * Devuelve un nuevo boletin con las noticias del boletin publicadas entre
	 * las dos fechas especificadas
	 * 
	 * @param fecha1 la primera fecha
	 * @param fecha2 la segunda fecha
	 * @return un Boletin con las noticias
	 * @throws IllegalArgumentException si alguna de las fechas es igual a null o
	 *          						si la segunda fecha es anterior a la primera
	 */
	public Boletin getBoletinIntervaloFechas(LocalDate fecha1, LocalDate fecha2) {
		if(fecha1 == null || fecha2 == null) {
			throw new IllegalArgumentException("Fecha igual a null");
		}
		if(fecha2.isBefore(fecha1)) {
			throw new IllegalArgumentException("Segunda fecha anterior a la primera");
		}
		
		Boletin boletinNoticiasIntervaloFechas = new Boletin();
		Noticia noticia;		
		for(int i = 0; i < getNumNoticias(); i++) {
			noticia = noticias.get(i);
			if(noticia.getFecha().isAfter(fecha1.minusDays(1)) && noticia.getFecha().isBefore(fecha2.plusDays(1))) {
				boletinNoticiasIntervaloFechas.addNoticia(noticia);
			}
		}
		return boletinNoticiasIntervaloFechas;
	}

	/**
	 * Devuelve un nuevo boletin con las noticias del boletin de la categoria especificada
	 * 
	 * @param categoria la categoria especificada
	 * @return un Boletin con las noticias
	 * @throws IllegalArgumentException si la categoria es igual a null
	 */
	public Boletin getBoletinPorCategoria(Categoria categoria) {
		if(categoria == null) {
			throw new IllegalArgumentException("Categoria igual a null");
		}
		
		Boletin boletinNoticiasCategoria = new Boletin();
		Noticia noticia;
		
		for(int i = 0; i < getNumNoticias(); i++) {
			noticia = noticias.get(i);
			if(noticia.getCategoria().equals(categoria)) {
				boletinNoticiasCategoria.addNoticia(noticia);
			}
		}
		return boletinNoticiasCategoria;
	}

	/**
	 * Devuelve un nuevo boletin con las noticias del boletin actual 
	 * publicadas en la fecha dada y asociadas a la categoria especificada
	 * 
	 * @param fecha la fecha de publicacion
	 * @param categoria la categoria de las noticias
	 * @return un Boletin con las noticias
	 * @throws IllegalArgumentException si algun argumento es null
	 */
	public Boletin getBoletinPorFechaYCategoria(LocalDate fecha, Categoria categoria) {
		if(categoria == null) {
			throw new IllegalArgumentException("Categoria igual a null");
		}
		if(fecha == null) {
			throw new IllegalArgumentException("Fecha igual a null");
		}
		return getBoletinPorFecha(fecha).getBoletinPorCategoria(categoria);
	}

	/**
	 * Devuelve un nuevo boletin con las noticias del boletin comprendidas
	 * entre las fechas dadas y asociadas a la categoria especificada
	 * 
	 * @param fecha1 la primera fecha
	 * @param fecha2 la segunda fecha
	 * @param categoria la categoria de las noticias
	 * @return un Boletin con las noticias
	 * @throws IllegalArgumentException si algun argumento es null o
	 * 									si la segunda fecha es anterior a la primera 
	 */
	public Boletin getBoletinPorIntervaloYCategoria(LocalDate fecha1, LocalDate fecha2, Categoria categoria) {
		if(fecha1 == null || fecha2 == null) {
			throw new IllegalArgumentException("Fecha igual a null");
		}
		if(categoria == null) {
			throw new IllegalArgumentException("Categoria igual a null");
		}
		if(fecha2.isBefore(fecha1)) {
			throw new IllegalArgumentException("Segunda fecha anterior a la primera");
		}
		return getBoletinIntervaloFechas(fecha1, fecha2).getBoletinPorCategoria(categoria);
	}
	
	/**
	 * Verifica si this es igual al objeto especificado
	 * Dos boletines se consideran iguales si todas sus noticias son iguales.
	 * Dos boletines vacios se consideran iguales.
	 * 
	 * @param obj el objeto especificado
	 * @return true si son iguales; false en caso contrario
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Boletin)) {
			return false;
		}
		return ((Boletin) obj).getNoticias().equals(getNoticias());
	}
	
	/* Devuelve la lista de noticias del boletin */
	private ArrayList<Noticia> getNoticias(){
		return noticias;
	}
	
	/**
	 * Devuelve el porcentaje de similitud entre el boletin actual y el dado.
	 * Un boletin tiene un porcentaje de similitud del 100% con otro si todas sus 
	 * noticias son similares a al menos una de las del otro boletin.
	 * 
	 * @param boletin el boletin
	 * @return un double con el porcentaje de cuantas noticias de this son similares a noticias del boletin dado
	 * @throws IllegalArgumentException si el boletin es null
	 */
	public double getPorcentajeSimilitud(Boletin boletin) {
		if(boletin == null) {
			throw new IllegalArgumentException("Boletin igual a null");
		}
		int numNoticiasSimilares = 0;
		for(int i = 0; i < getNumNoticias(); i++) {
			for(int j = 0; j < boletin.getNumNoticias(); j++) {
				if(noticias.get(i).similarTo(boletin.getNoticias().get(j))) {
					numNoticiasSimilares++;
					break;
				}
			}
		}
		return getNumNoticias() == 0 ? 0 : numNoticiasSimilares*100.0/getNumNoticias();
	}
}
