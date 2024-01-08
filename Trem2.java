/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
  Autor: Daniel Nogueira
  Matricula: 201911910
  Inicio...: 14 de Abril de 2021
  Alteracao: 09 de Maio de 2021
  Nome.....: Trem2
  Funcao...: Uma classe que herda da classe 'Trem' e representa o trem que vai da direita para a esquerda
  =-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
public class Trem2 extends Trem {

  private Pista pista;    //objeto para referencia de pista
  private Animacao animacao;  //objeto de 'Animacao'
  private Bandeira[] bandeiras = new Bandeira[4];

  /* *********************
  * Metodo: Trem2
  * Funcao: Construtor de Trem2
  * Parametros: int x, int y, Pista pista
  ********************* */
  public Trem2(int x, int y, Pista pista, Bandeira[] bandeiras) {
    super(x, y);
    this.velocidade = 1;
    this.pista = pista;
    this.bandeiras = bandeiras;

    animacao = new Animacao("res/Trem2.png", "res/Trem2_2.png", "res/Trem2_3.png", this);
    this.setImagem(animacao.getImagem());
  }

  /* *********************
  * Metodo: run
  * Funcao: realiza o algoritmo que o trem2 tem que fazer
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
    pista.setDistancia2(1080); //coloca a distancia do trem 2 para o limite direito da tela
    while (true){
      if (!animacao.isRunning()) //inicai a animacao de troca de sprites do trem2
        animacao.ligarTickAnimacao();

      if (x<=0){  //caso ele atinja o limite volra para o inicio do seu percurso
        x = 1080;
        pista.setDistancia2(1080);
      }

      if (velocidade>0){ //movimenta o trem de acordo com a velocidade
        this.x -= 1;
        pista.setDistancia2(pista.getDistancia2()-1);
        esperar();
      }
      else { //caso a velocidade seja 0 ele espera no mesmo lugar
        esperar();
      }

      //caso o ponto critico 2 ja tenha um trem dentro, ele espera no inicio do trecho 5
      while (pista.getDistancia2() == pista.getTrecho5().getInicio() && bandeiras[2].getValorImagemAtual()!=0){
        esperar();
      } 

      //₢aso o trem2 esteja no ponto critico 2
      if(pista.getTrecho4().estaDentro(pista.getDistancia2())){
        bandeiras[3].mudarEstado(2);
        pista.setTremNoCrititco2("2");
      } //qunado o trem2 sai do ponto critico 2 (+90 serve para consertar o espacamento)
      else if (pista.getDistancia2()+90 == pista.getTrecho3().getFim()){
        bandeiras[3].mudarEstado(0);
        if (bandeiras[2].getImagemAtual()==bandeiras[2].getImagens()[2])
          bandeiras[2].mudarEstado(1);
        pista.setTremNoCrititco2("");
      }

      //caso o ponto critico 1 ja tenha um trem dentro, ele espera no inicio do trecho 3
      while (pista.getDistancia2() == pista.getTrecho3().getInicio() && bandeiras[0].getValorImagemAtual()!=0){
        esperar();
      } 

      //caso o trem 2 esteja no ponto critico 1
      if(pista.getTrecho2().estaDentro(pista.getDistancia2())){
        bandeiras[1].mudarEstado(2);
        pista.setTremNoCritico1("2");
      } //quando o trem 2 sai do ponto critico 2 (+90 serve para consertar o espacamento)
      else if (pista.getDistancia2()+90 == pista.getTrecho1().getFim()){
        bandeiras[1].mudarEstado(0);
        if (bandeiras[0].getImagemAtual()==bandeiras[0].getImagens()[2])
          bandeiras[0].mudarEstado(1);
        pista.setTremNoCritico1("");
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
    pista.setDistancia2(1080); //coloca a distancia do trem 2 para o limite direito da tela
    while (true){
      if (!animacao.isRunning()) //inicai a animacao de troca de sprites do trem2
        animacao.ligarTickAnimacao();

      if (x<=0){  //caso ele atinja o limite volra para o inicio do seu percurso
        x = 1080;
        pista.setDistancia2(1080);
      }

      if (velocidade>0){ //movimenta o trem de acordo com a velocidade
        this.x -= 1;
        pista.setDistancia2(pista.getDistancia2()-1);
        esperar();
      }
      else { //caso a velocidade seja 0 ele espera no mesmo lugar
        esperar();
      }

      //caso o ponto critico 2 ja tenha um trem dentro, ele espera no inicio do trecho 5
      if (pista.getDistancia2() == pista.getTrecho5().getInicio() && pista.isPontoCritico2()){
        esperar();
      } 

      //₢aso o trem2 esteja no ponto critico 2
      if(pista.getTrecho4().estaDentro(pista.getDistancia2())){
        pista.setPontoCritico2(true);
        pista.setTremNoCrititco2("2");
      } //qunado o trem2 sai do ponto critico 2 (+90 serve para consertar o espacamento)
      else if (pista.getDistancia2()+90 == pista.getTrecho3().getFim()){
        pista.setPontoCritico2(false);
        pista.setTremNoCrititco2("");
      }

      //caso o ponto critico 1 ja tenha um trem dentro, ele espera no inicio do trecho 3
      while (pista.getDistancia2() == pista.getTrecho3().getInicio() && pista.isPontoCritico1()){
        esperar();
      } 

      //caso o trem 2 esteja no ponto critico 1
      if(pista.getTrecho2().estaDentro(pista.getDistancia2())){
        pista.setPontoCritico1(true);
        pista.setTremNoCritico1("2");
      } //quando o trem 2 sai do ponto critico 2 (+90 serve para consertar o espacamento)
      else if (pista.getDistancia2()+90 == pista.getTrecho1().getFim()){
        pista.setPontoCritico1(false);
        pista.setTremNoCritico1("");
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
    pista.setDistancia2(1080); //coloca a distancia do trem 2 para o limite direito da tela
    while (true){
      if (!animacao.isRunning()) //inicai a animacao de troca de sprites do trem2
        animacao.ligarTickAnimacao();

      if (x<=0){  //caso ele atinja o limite volra para o inicio do seu percurso
        x = 1080;
        pista.setDistancia2(1080);
      }

      if (velocidade>0){ //movimenta o trem de acordo com a velocidade
        this.x -= 1;
        pista.setDistancia2(pista.getDistancia2()-1);
        esperar();
      }
      else { //caso a velocidade seja 0 ele espera no mesmo lugar
        esperar();
      }

      //caso o ponto critico 2 ja tenha um trem dentro, ele espera no inicio do trecho 5
      if (pista.getDistancia2() == pista.getTrecho5().getInicio()){
        pista.setFlag2(1, true);
        while (pista.getFlag2(0))
          esperar();
      } 

      //₢aso o trem2 esteja no ponto critico 2
      if(pista.getTrecho4().estaDentro(pista.getDistancia2())){
        pista.setTremNoCrititco2("2");
      } //qunado o trem2 sai do ponto critico 2 (+90 serve para consertar o espacamento)
      else if (pista.getDistancia2()+90 == pista.getTrecho3().getFim()){
        pista.setFlag1(1, false);
        pista.setTremNoCrititco2("");
      }

      //caso o ponto critico 1 ja tenha um trem dentro, ele espera no inicio do trecho 3
      if (pista.getDistancia2() == pista.getTrecho3().getInicio()){
        pista.setFlag1(1, true);
        while (pista.getFlag1(0))
          esperar();
      } 

      //caso o trem 2 esteja no ponto critico 1
      if(pista.getTrecho2().estaDentro(pista.getDistancia2())){
        pista.setTremNoCritico1("2");
      } //quando o trem 2 sai do ponto critico 2 (+90 serve para consertar o espacamento)
      else if (pista.getDistancia2()+90 == pista.getTrecho1().getFim()){
        pista.setFlag1(1, false);
        pista.setTremNoCritico1("");
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
    pista.setDistancia2(1080); //coloca a distancia do trem 2 para o limite direito da tela
    while (true){
      if (!animacao.isRunning()) //inicai a animacao de troca de sprites do trem2
        animacao.ligarTickAnimacao();

      if (x<=0){  //caso ele atinja o limite volra para o inicio do seu percurso
        x = 1080;
        pista.setDistancia2(1080);
      }

      if (velocidade>0){ //movimenta o trem de acordo com a velocidade
        this.x -= 1;
        pista.setDistancia2(pista.getDistancia2()-1);
        esperar();
      }
      else { //caso a velocidade seja 0 ele espera no mesmo lugar
        esperar();
      }

      //caso o ponto critico 2 ja tenha um trem dentro, ele espera no inicio do trecho 5
      if (pista.getDistancia2() == pista.getTrecho5().getInicio()){
        pista.setFlag2(1, true);
        pista.setTurno(0);
        while (pista.getFlag2(0) && pista.getTurno() == 0)
          esperar();
      } 

      //₢aso o trem2 esteja no ponto critico 2
      if(pista.getTrecho4().estaDentro(pista.getDistancia2())){
        pista.setTremNoCrititco2("2");
      } //qunado o trem2 sai do ponto critico 2 (+90 serve para consertar o espacamento)
      else if (pista.getDistancia2()+90 == pista.getTrecho3().getFim()){
        pista.setFlag2(1, false);
        pista.setTremNoCrititco2("");
      }

      //caso o ponto critico 1 ja tenha um trem dentro, ele espera no inicio do trecho 3
      if (pista.getDistancia2() == pista.getTrecho3().getInicio()){
        pista.setFlag1(1, true);
        pista.setTurno(0);
        while (pista.getFlag1(0) && pista.getTurno() == 0)
          esperar();
      } 

      //caso o trem 2 esteja no ponto critico 1
      if(pista.getTrecho2().estaDentro(pista.getDistancia2())){
        pista.setTremNoCritico1("2");
      } //quando o trem 2 sai do ponto critico 2 (+90 serve para consertar o espacamento)
      else if (pista.getDistancia2()+90 == pista.getTrecho1().getFim()){
        pista.setFlag1(1, false);
        pista.setTremNoCritico1("");
      }
    }
  }
}
