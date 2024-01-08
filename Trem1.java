/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
  Autor: Daniel Nogueira
  Matricula: 201911910
  Inicio...: 14 de Abril de 2021
  Alteracao: 09 de Maio de 2021
  Nome.....: Trem1
  Funcao...: Uma classe que herda da classe 'Trem' e representa o trem que vai da esquerda para a direita
  =-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
public class Trem1 extends Trem {
  private Pista pista;      //objeto referencia para 'Pista'
  private Animacao animacao;    //objeto de 'Animacao'
  private Bandeira[] bandeiras = new Bandeira[4];

  /* *********************
  * Metodo: Trem1
  * Funcao: Construtor de Trem1
  * Parametros: int x, int y, Pista pista
  ********************* */
  public Trem1(int x, int y, Pista pista, Bandeira[] bandeiras) {
    super(x, y);
    this.velocidade = 1;
    this.pista = pista;
    this.bandeiras = bandeiras;

    animacao = new Animacao("res/Trem1.png", "res/Trem1_2.png", "res/Trem1_3.png", this);
    this.setImagem(animacao.getImagem());
  }

  /* *********************
  * Metodo: run
  * Funcao: realiza o algoritmo que o trem1 tem que fazer
  * Parametros: nenhum
  * Retorno: void
  ********************* */
  @Override
  public void run(){
    switch (pista.getTipoDeProtocolo()){
      case "bandeiras":
        protocoloBandeiras();
        break;
      case "sinal":
        variavelTravamento();
        break;
      case "alternancia":
        estritaAlternacia();
        break;
      case "peterson":
        solucaoPeterson();
        break;
    }
  }

  /* *********************
  * Metodo: protocoloBandeiras
  * Funcao: realiza o algoritmo do protocolo de bandeiras
  * Parametros: nenhum
  * Retorno: void
  ********************* */
  public void protocoloBandeiras(){
    pista.setDistancia1(0); //coloca a distancia do trem 2 para o limite direito da tela
    while (true){
      if (!animacao.isRunning()) //inicai a animacao de troca de sprites do trem2
        animacao.ligarTickAnimacao();

      if (x>=1080){  //caso ele atinja o limite volra para o inicio do seu percurso
        x = 0;
        pista.setDistancia1(0);
      }

      if (velocidade>0){ //movimenta o trem de acordo com a velocidade
        this.x += 1;
        pista.setDistancia1(pista.getDistancia1()+1);
        esperar();
      }
      else { //caso a velocidade seja 0 ele espera no mesmo lugar
        esperar();
      }

      //caso o ponto critico 1 ja tenha um trem dentro, ele espera no final do trecho 1
      while (pista.getDistancia1()+90 == pista.getTrecho1().getFim() && bandeiras[1].getValorImagemAtual()!=0){
        esperar();
      } 

      //₢aso o trem1 esteja no ponto critico 1
      if(pista.getTrecho2().estaDentro(pista.getDistancia1()+90)){
        bandeiras[0].mudarEstado(2);
        pista.setTremNoCritico1("1");
      } //qunado o trem1 sai do ponto critico 1 (+90 serve para consertar o espacamento)
      else if (pista.getDistancia1() == pista.getTrecho3().getInicio()){
        pista.setTremNoCritico1("");
        bandeiras[0].mudarEstado(0);
        if (bandeiras[1].getImagemAtual()==bandeiras[1].getImagens()[2])
          bandeiras[1].mudarEstado(1);
      }

      //caso o ponto critico 2 ja tenha um trem dentro, ele espera no final do trecho 3
      while (pista.getDistancia1()+90 == pista.getTrecho3().getFim() && bandeiras[3].getValorImagemAtual()!=0){
        esperar();
      } 

      //caso o trem 2 esteja no ponto critico 1
      if(pista.getTrecho4().estaDentro(pista.getDistancia1()+90)){
        pista.setTremNoCritico1("1");
        bandeiras[2].mudarEstado(2);
      } //quando o trem 2 sai do ponto critico 2 (+90 serve para consertar o espacamento)
      else if (pista.getDistancia1() == pista.getTrecho5().getInicio()){
        pista.setTremNoCritico1("");
        bandeiras[2].mudarEstado(0);
        if (bandeiras[3].getImagemAtual()==bandeiras[3].getImagens()[2])
          bandeiras[3].mudarEstado(1);
      }
    }
  }

  /* *********************
  * Metodo: variavleTravamento
  * Funcao: realiza o algoritmo com variavel de travamento
  * Parametros: nenhum
  * Retorno: void
  ********************* */
  public void variavelTravamento(){
    pista.setDistancia1(0);  //coloca a distancia do trem1 para 0
    while (true){
      if (!animacao.isRunning())
        animacao.ligarTickAnimacao();  //inicia a animacao de troca de sprites do trem1

      if (x>1080){  //caso ele atinja o limite volta para o inicio
        x = 0;
        pista.setDistancia1(0);
      }

      if (velocidade>0){  //movimenta o trem de acordo com a velocidade
        this.x += 1;
        pista.setDistancia1(pista.getDistancia1()+1);
        esperar();
      }
      else {  //caso a velocidade seja 0, ele apenas espera no mesmo lugar
        esperar();
      }

      //caso o ponto critico1 ja tenha um trem dentro, ele espera no final do trecho 1 (+90 eh para consertar o espacamento)
      while (pista.getDistancia1()+90 == pista.getTrecho1().getFim() && pista.isPontoCritico1()){ 
        esperar();
      } 

      //caso o trem1 esteja no ponto critico1
      if(pista.getTrecho2().estaDentro(pista.getDistancia1()+90)){
        pista.setPontoCritico1(true);
        pista.setTremNoCritico1("1");
      } //quando o trem1 sai do ponto critico1
      else if (pista.getDistancia1() == pista.getTrecho3().getInicio()){
        pista.setPontoCritico1(false);
        pista.setTremNoCritico1("");
      }

      //caso o ponto critico2 ja tenha um trem dentro, ele espera no final do trecho3
      while (pista.getDistancia1()+90 == pista.getTrecho3().getFim() && pista.isPontoCritico2()){ 
        esperar();
      } 

      //caso o trem1 esteja no ponto critico2
      if(pista.getTrecho4().estaDentro(pista.getDistancia1()+90)){
        pista.setPontoCritico2(true);
        pista.setTremNoCrititco2("1");
      } //quando o trem1 sai do ponto critico2
      else if (pista.getDistancia1() == pista.getTrecho5().getInicio()){
        pista.setPontoCritico2(false);
        pista.setTremNoCrititco2("");
      }
    }
  }

  /* *********************
  * Metodo: estritaAlternacia
  * Funcao: realiza o algoritmo com estrita alternancia
  * Parametros: nenhum
  * Retorno: void
  ********************* */
  public void estritaAlternacia(){
    pista.setDistancia1(0);  //coloca a distancia do trem1 para 0
    while (true){
      if (!animacao.isRunning())
        animacao.ligarTickAnimacao();  //inicia a animacao de troca de sprites do trem1

      if (x>1080){  //caso ele atinja o limite volta para o inicio
        x = 0;
        pista.setDistancia1(0);
      }

      if (velocidade>0){  //movimenta o trem de acordo com a velocidade
        this.x += 1;
        pista.setDistancia1(pista.getDistancia1()+1);
        esperar();
      }
      else {  //caso a velocidade seja 0, ele apenas espera no mesmo lugar
        esperar();
      }

      //caso o ponto critico1 ja tenha um trem dentro, ele espera no final do trecho 1 (+90 eh para consertar o espacamento)
      if (pista.getDistancia1()+90 == pista.getTrecho1().getFim()){
        pista.setFlag1(0, true);
        while(pista.getFlag1(1)){
          esperar();
        }
      }

      //caso o trem1 esteja no ponto critico1
      if(pista.getTrecho2().estaDentro(pista.getDistancia1()+90)){
        pista.setTremNoCritico1("1");
      } //quando o trem1 sai do ponto critico1
      else if (pista.getDistancia1() == pista.getTrecho3().getInicio()){
        pista.setFlag1(0, false);
        pista.setTremNoCritico1("");
      }

      //caso o ponto critico2 ja tenha um trem dentro, ele espera no final do trecho3
      if (pista.getDistancia1()+90 == pista.getTrecho3().getFim()){ 
        pista.setFlag2(0, true);
        while (pista.getFlag2(1)){
          esperar();
        }
      } 

      //caso o trem1 esteja no ponto critico2
      if(pista.getTrecho4().estaDentro(pista.getDistancia1()+90)){
        pista.setTremNoCrititco2("1");
      } //quando o trem1 sai do ponto critico2
      else if (pista.getDistancia1() == pista.getTrecho5().getInicio()){
        pista.setFlag2(0, false);
        pista.setTremNoCrititco2("");
      }
    }
  }

  /* *********************
  * Metodo: solucaoPeterson
  * Funcao: realiza o algoritmo com a solucao de Petersons
  * Parametros: nenhum
  * Retorno: void
  ********************* */
  public void solucaoPeterson(){
    pista.setDistancia1(0);  //coloca a distancia do trem1 para 0
    while (true){
      if (!animacao.isRunning())
        animacao.ligarTickAnimacao();  //inicia a animacao de troca de sprites do trem1

      if (x>1080){  //caso ele atinja o limite volta para o inicio
        x = 0;
        pista.setDistancia1(0);
      }

      if (velocidade>0){  //movimenta o trem de acordo com a velocidade
        this.x += 1;
        pista.setDistancia1(pista.getDistancia1()+1);
        esperar();
      }
      else {  //caso a velocidade seja 0, ele apenas espera no mesmo lugar
        esperar();
      }

      //caso o ponto critico1 ja tenha um trem dentro, ele espera no final do trecho 1 (+90 eh para consertar o espacamento)
      if (pista.getDistancia1()+90 == pista.getTrecho1().getFim()){ 
        pista.setFlag1(0, true);
        pista.setTurno(1);
        while (pista.getFlag1(1) && pista.getTurno() == 1){
          esperar();
        }
      } 

      //caso o trem1 esteja no ponto critico1
      if(pista.getTrecho2().estaDentro(pista.getDistancia1()+90)){
        pista.setTremNoCritico1("1");
      } //quando o trem1 sai do ponto critico1
      else if (pista.getDistancia1() == pista.getTrecho3().getInicio()){
        pista.setFlag1(0, false);
        pista.setTremNoCritico1("");
      }

      //caso o ponto critico2 ja tenha um trem dentro, ele espera no final do trecho3
      if (pista.getDistancia1()+90 == pista.getTrecho3().getFim()){ 
        pista.setFlag2(0, true);
        pista.setTurno(1);
        while (pista.getFlag2(1) && pista.getTurno() == 1){
          esperar();
        }
      } 

      //caso o trem1 esteja no ponto critico2
      if(pista.getTrecho4().estaDentro(pista.getDistancia1()+90)){
        pista.setTremNoCrititco2("1");
      } //quando o trem1 sai do ponto critico2
      else if (pista.getDistancia1() == pista.getTrecho5().getInicio()){
        pista.setFlag2(0, false);
        pista.setTremNoCrititco2("");
      }
    }
  }
}
