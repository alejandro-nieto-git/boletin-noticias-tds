package es.uva.inf.tds.pr2;

import java.time.LocalDate;
import java.time.Period;

/**
 * Implementacion de una noticia. 
 * Una noticia esta caracterizada por su titular, 
 * la fuente de la que ha sido extraida, 
 * la URL donde reside su contenido y su 
 * categoria
 * 
 * @author aleniet
 * @author davmele
 */
public class Noticia {

    private String titular;
    private LocalDate fecha;
    private String fuente;
    private String url;
    private Categoria categoria;
    
    /**
     * Inicializador
     * 
     * @param titular el titular de la noticia
     * @param fecha la fecha de publicacion de la noticia
     * @param fuente la fuente de la noticia
     * @param url la URL al contenido de la noticia
     * @param categoria la categoria de la noticia
     * 
     * @throws IllegalArgumentException si algun argumento es igual a null
     * @throws IllegalArgumentException si el titular esta vacio o tiene mas 
     *            de 12 palabras
     */
    public Noticia(String titular, LocalDate fecha, String fuente, String url, Categoria categoria) {
        if(titular == null) {
            throw new IllegalArgumentException("Titular de noticia igual a null");
        }
        if(fecha == null) {
            throw new IllegalArgumentException("Fecha de noticia igual a null");
        }
        if(fuente == null) {
            throw new IllegalArgumentException("Fuente de noticia igual a null");
        }
        if(url == null) {
            throw new IllegalArgumentException("URL de noticia igual a null");
        }
        if(categoria == null) {
            throw new IllegalArgumentException("Categoria de noticia igual a null");
        }
        if(titular.equals("")) {
            throw new IllegalArgumentException("Titular de noticia vacio");
        }
        if(titular.split(" ").length > 12) {
            throw new IllegalArgumentException("Titular de noticia con mas de 12 palabras");
        }
        this.titular = titular;
        this.fecha = fecha;
        this.fuente = fuente;
        this.url = url;
        this.categoria = categoria;
    }

    /**
     * Compara this y la noticia especificada 
     * con respecto a su fecha de publicacion
     * 
     * @param noticia la noticia a comparar
     * @return -1 si this es anterior; 0 si tienen la misma fecha;
     *             1 si this es posterior
     * @throws IllegalArgumentException si la noticia especificada es null
     */
    public int compareTo(Noticia noticia) {
        if(noticia == null) {
            throw new IllegalArgumentException("Noticia igual a null");
        }
        if(noticia.getFecha().equals(getFecha())) {
            return 0;
        }
        if(noticia.getFecha().isAfter(getFecha())) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * Devuelve el titular de la noticia
     * 
     * @return un String con el titular
     */
    public String getTitular() {
        return titular;
    }

    /**
     * Devuelve la URL de la noticia
     * 
     * @return un String con la URL
     */
    public String getURL() {
        return url;
    }

    /**
     * Devuelve la fuente de la noticia
     * 
     * @return un String con la fuente
     */
    public String getFuente() {
        return fuente;
    }

    /**
     * Devuelve la fecha de publicacion de la noticia
     * 
     * @return un LocalDate con la fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Devuelve la categoria de la noticia
     * 
     * @return una Categoria con la categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Verifica si this es similar a la noticia especificada.
     * Dos noticias son similares si tienen el mismo titular y la 
     * misma categoria y ademas sus fechas de publicacion se diferencian como mucho en 2 dias
     * 
     * @param noticia la noticia especificada
     * @return true si son similares; false en caso contrario
     * @throws IllegalArgumentException si la noticia es null
     */
    public boolean similarTo(Noticia noticia) {
        if(noticia == null) {
            throw new IllegalArgumentException("Noticia igual a null");
}
        Period diferenciaFechas = Period.between(noticia.getFecha(), getFecha());
        if(noticia.getTitular().equals(getTitular()) && noticia.getCategoria().equals(getCategoria()) && diferenciaFechas.getDays() < 3 && diferenciaFechas.getDays() > -3) {
            return true;
        }
        return false;
    }
    
    /**
     * Verifica si this es igual al objeto especificado. 
     * Una noticia es igual a otra si tienen el mismo titular, 
     * fecha, fuente, url y categoria
     * 
     * @param obj el objeto especificado
     * @return true si son iguales; false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Noticia)) {
            return false;
        }
        Noticia noticia = (Noticia) obj;
        return noticia.getTitular().equals(getTitular()) && noticia.getFuente().equals(getFuente()) && noticia.getURL().equals(getURL()) && noticia.getFecha().equals(getFecha()) && noticia.getCategoria().equals(getCategoria());
    }
}