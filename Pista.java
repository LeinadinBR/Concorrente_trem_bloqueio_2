/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Autor: Daniel Nogueira
Matricula: 201911910
Inicio...: 14 de Abril de 2021
Alteracao: 09 de Maio de 2021
Nome.....: Pista
Funcao...: Essa classe serve para simular o 'back-end' da pista, contendo os trechos e posicoes dos trens 
=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
public class Pista {
  private int distancia1;   //int que mede a distancia do trem1 na pista
  private int distancia2;   //int que mede a distancia do trem2 na pista

  private String tremNoCritico1="";   //String que identifica qual trem esta no ponto critico 1
  private String tremNoCrititco2="";    //String que identifica qual trem esta no ponto critico 2

  private boolean pontoCritico1 = false;  //boolean que diz se o tunel 1 esta ocupado (false = desocupado, true = ocupado)
  private boolean pontoCritico2 = false;  //boolean que diz se o tunel 2 esta ocupado (false = desocupado, true = ocupado)

  private boolean[] flagPC1 = new boolean[2];
  private boolean[] flagPC2 = new boolean[2];

  private int turno;

  private String tipoDeProtocolo = "";

  //objetos dos trechos
  private final Trecho trecho1 = new Trecho(0, 204, false);   
  private final Trecho trecho2 = new Trecho(205, 459, true);
  private final Trecho trecho3 = new Trecho(460, 644, false);
  private final Trecho trecho4 = new Trecho(645, 869, true);
  private final Trecho trecho5 = new Trecho(870, 1080, false);

  //getters e setters dos atributos de pista----------------------//
  public int getDistancia1() {
    return distancia1;
  }
  public void setDistancia1(int distancia1) {
    this.distancia1 = distancia1;
  }
  public int getDistancia2() {
    return distancia2;
  }
  public void setDistancia2(int distancia2) {
    this.distancia2 = distancia2;
  }
  public boolean isPontoCritico1() {
    return pontoCritico1;
  }
  public void setPontoCritico1(boolean pontoCritico1) {
    this.pontoCritico1 = pontoCritico1;
  }
  public boolean isPontoCritico2() {
    return pontoCritico2;
  }
  public void setPontoCritico2(boolean pontoCritico2) {
    this.pontoCritico2 = pontoCritico2;
  }
  public Trecho getTrecho1() {
    return trecho1;
  }
  public Trecho getTrecho2() {
    return trecho2;
  }
  public Trecho getTrecho3() {
    return trecho3;
  }
  public Trecho getTrecho4() {
    return trecho4;
  }
  public Trecho getTrecho5() {
    return trecho5;
  }
  public String getTremNoCritico1() {
    return tremNoCritico1;
  }
  public void setTremNoCritico1(String tremNoCritico1) {
    this.tremNoCritico1 = tremNoCritico1;
  }
  public String getTremNoCrititco2() {
    return tremNoCrititco2;
  }
  public void setTremNoCrititco2(String tremNoCrititco2) {
    this.tremNoCrititco2 = tremNoCrititco2;
  }
  public boolean getFlag1(int i){
    return flagPC1[i];
  }
  public void setFlag1(int i, boolean b){
    this.flagPC1[i] = b;
  }
  public boolean getFlag2(int i){
    return flagPC2[i];
  }
  public void setFlag2(int i, boolean b){
    this.flagPC2[i] = b;
  }
  public int getTurno(){
    return turno;
  }
  public void setTurno(int i){
    this.turno = i;
  }
  public String getTipoDeProtocolo(){
    return this.tipoDeProtocolo;
  }
  public void setTipoDeProtocolo(String s){
    this.tipoDeProtocolo = s;
  }
  //------------------------------------------------------------//

  /*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
  Autor: Daniel Nogueira
  Matricula: 201911910
  Inicio...: 15 de Abril de 2021
  Alteracao: 18 de Abril de 2021
  Nome.....: Trecho
  Funcao...: Serve para criar os trechos criticos e nao-criticos da pista
  =-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
  class Trecho {
    private int inicio;  //int que define inicio do trecho
    private int fim;      //int que define fim do trecho
    private boolean ehCritico;  //boolean que define se o trecho eh critico ou nao

    /* *********************
    * Metodo: Trecho
    * Funcao: Construtor de Trecho
    * Parametros: int inicio, int fim, boolena ehCritico
    ********************* */
    public Trecho(int inicio, int fim, boolean ehCritico){
      this.inicio = inicio;
      this.fim = fim;
      this.ehCritico = ehCritico;
    }

    /* *********************
    * Metodo: estaDentro
    * Funcao: Serve para verificar se dado int esta dentro do escopo do trecho. Eh usado junto ao atributo de distancia (1 ou 2) de Pista
    * Parametros: int index
    * Retorno: boolean
    ********************* */
    public boolean estaDentro(int index){
      return (index>=inicio && index<=fim);
    }

    //getters e setters de Trecho--------------------//
    public int getInicio() {
      return inicio;
    }
    public int getFim() {
      return fim;
    }
    public boolean isEhCritico() {
      return ehCritico;
    }
    public void setEhCritico(boolean ehCritico) {
      this.ehCritico = ehCritico;
    }
    //----------------------------------------------//
  }
}
