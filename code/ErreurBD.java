package code;

public  class ErreurBD  extends Exception {

	/** Lever d'exception lorsque la liaison à la base de donnée ne se fait pas correctement
	 * @param s
	 */
	public ErreurBD (String s) {
       super(s) ;
   }
}
