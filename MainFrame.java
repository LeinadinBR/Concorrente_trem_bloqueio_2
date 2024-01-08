import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Autor: Daniel Nogueira
Matricula: 201911910
Inicio...: 14 de Abril de 2021
Alteracao: 09 de Maio de 2021
Nome.....: MainFrame
Funcao...: Serve para criar a tela grafica do programa. Herda de 'JFrame'
=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
public class MainFrame extends JFrame {

  /**
   *
   */
  private static final long serialVersionUID = 1L;   //numero serial vindo da classe JFrame
  private JPanel painelSuperior;          //objeto 'JPanel' para colocar a tela de Display
  private PainelInferior painelInferior;    //objeto de 'PainelInferior'
  private Display display;              //objeto de 'Display'
  private boolean iniciado = false;   //boolean que serve para verificar se os trens foram iniciados
  private Trem trem1, trem2;          //objetos dos trens
  private Pista pista;                //objeto de 'Pista'
  private Sprite imagemPista, imagemTunel, imagemSombraEsq, imagemSombraDir;  //objetos sprites das imagens usadas no programa
  private Sprite[] imagemSinaleira = new Sprite[2];   //array de sprites das sinaleiras
  private BufferedImage imagem = new BufferedImage(1080, 450, BufferedImage.TYPE_INT_BGR);  //imagem na qual um objeto 'Graphic' pode 'desenhar'
  private Bandeira[] bandeiras = new Bandeira[4];
  private FrameInicial frameInicial;
  private boolean iniciarTeste = false;

  /* *********************
  * Metodo: MainFrame
  * Funcao: Construtor de MainFrame
  * Parametros: nenhum
  ********************* */
  public MainFrame(){
    inicializar();

    painelSuperior.add(display.getTela());

    this.add(painelSuperior, BorderLayout.NORTH);
    this.add(painelInferior, BorderLayout.SOUTH);

    this.setSize(new Dimension(1130, 450));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    this.setVisible(true);
    this.setTitle("Problema dos Trens");

    //adiciona acao ao slider1
    painelInferior.getTrem1().addChangeListener(e -> {
      trem1.setVelocidade(painelInferior.getTrem1().getValue());
    });

    //adiciona acao ao slider2
    painelInferior.getTrem2().addChangeListener(e -> {
      trem2.setVelocidade(painelInferior.getTrem2().getValue());
    });

    pista.setTipoDeProtocolo("bandeiras");

    JFrame frameSecundario = new JFrame();
    frameSecundario.add(frameInicial);
    frameSecundario.pack();
    frameSecundario.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    frameSecundario.setResizable(false);
    frameSecundario.setLocationRelativeTo(null);
    frameSecundario.setTitle("Selecionar protocolo");
    frameSecundario.setVisible(true);
    frameInicial.acaoDosBotoes(pista, frameSecundario);

    //chama a funcao 'renderizar' que roda em um loop eterno
    while (frameSecundario.isVisible()) {
      try {
        Thread.sleep(1000);
      }catch (Exception e){
      }
    }
    while (true){
      renderizar();
    }
  }

  /* *********************
  * Metodo: inicializar
  * Funcao: inicializar objetos
  * Parametros: nenhum
  * Retorno: void
  ********************* */
  private void inicializar(){
    frameInicial = new FrameInicial();

    painelInferior = new PainelInferior();
    
    painelSuperior = new JPanel();

    display = new Display();

    pista = new Pista();

    bandeiras[0] = new Bandeira(200, 160);
    bandeiras[1] = new Bandeira(340, 160);
    bandeiras[2] = new Bandeira(635, 160);
    bandeiras[3] = new Bandeira(775, 160);

    trem1 = new Trem1(0, 155, pista, bandeiras);
    trem2 = new Trem2(1080, 300, pista, bandeiras);

    imagemPista = new Sprite("res/Pista.png");

    imagemTunel = new Sprite("res/Tunel.png");

    imagemSombraEsq = new Sprite("res/sombra_esquerda.png");
    imagemSombraDir = new Sprite("res/sombra_direita.png");

    imagemSinaleira[0] = new Sprite("res/sinaleira_vermelho.png");
    imagemSinaleira[1] = new Sprite("res/sinaleira_verde.png");
  }

  /* *********************
  * Metodo: renderizar
  * Funcao: "desenhar" as imagens no Canvas
  * Parametros: nenhum
  * Retorno: void
  ********************* */
  public void renderizar(){
    //verifica se os trens foram inicializados
    if (!iniciado){
      trem1.start();
      trem2.start();
      iniciado = true;
    }
    //cria e configura os objetos para "desenhar"
    display.criarBufferStrategy();
    BufferStrategy bs = display.getTela().getBufferStrategy();
    if (bs == null) {
			display.criarBufferStrategy();
			bs = display.getTela().getBufferStrategy();
		}
    Graphics g = imagem.getGraphics();
    g = bs.getDrawGraphics();

    //desenha as imagens (trem1 e trem2 tem suas posicoes de acordo com o x e y deles)
    g.drawImage(imagemPista.getSprite(0, 0, 1080, 450), 0, 0, null);
    g.drawImage(trem1.getImagem().getSprite(), trem1.getX(), trem1.getY(), 90, 45, null);
    g.drawImage(trem2.getImagem().getSprite(), trem2.getX(), trem2.getY(), 90, 45, null);
    g.drawImage(imagemTunel.getSprite(), 135, 40, 390, 385, null);
    g.drawImage(imagemTunel.getSprite(), 570, 40, 390, 385, null);
    g.drawImage(imagemSombraEsq.getSprite(), 0, 0, null);
    g.drawImage(imagemSombraDir.getSprite(), 990, 0, null);
    
    switch(pista.getTipoDeProtocolo()){
      case "bandeiras":
        renderizarBandeiras(g);
        break;
      case "sinal":
        renderizarSinal(g);
        break;
      case "alternancia":
        renderizarAlternancia(g);
        break;
      case "peterson":
        renderizarPeterson(g);
        break;
      default:
        renderizarSinal(g);
        break;
    }
    g.dispose();  //acaba com o objeto g tirando sua referencia
    bs.show();    //torna o proximo buffer visivel
  }

  public void renderizarBandeiras(Graphics g){
    bandeiras[0].renderizar(g);
    bandeiras[1].renderizar(g);
    bandeiras[2].renderizar(g);
    bandeiras[3].renderizar(g);
  }

  public void renderizarSinal(Graphics g){
    //desenha bandeira esquerda do tunel 1
    if (!pista.isPontoCritico1())
      g.drawImage(imagemSinaleira[1].getSprite(), 210, 160, 70, 93, null);
    else if (pista.getTremNoCritico1()=="2") {
      g.drawImage(imagemSinaleira[0].getSprite(), 210, 160, 70, 93, null);
      g.drawImage(imagemSinaleira[1].getSprite(), 360, 160, 70, 93, null);
    }

    //desenha bandeira direita do tunel 1
    if (!pista.isPontoCritico1())
      g.drawImage(imagemSinaleira[1].getSprite(), 360, 160, 70, 93, null);
    else if (pista.getTremNoCritico1()=="1") {
      g.drawImage(imagemSinaleira[0].getSprite(), 360, 160, 70, 93, null);
      g.drawImage(imagemSinaleira[1].getSprite(), 210, 160, 70, 93, null);
    }

    //desenha bandeira esquerda do tunel 2
    if (!pista.isPontoCritico2())
      g.drawImage(imagemSinaleira[1].getSprite(), 650, 160, 70, 93, null);
    else if (pista.getTremNoCrititco2()=="2") {
      g.drawImage(imagemSinaleira[0].getSprite(), 650, 160, 70, 93, null);
      g.drawImage(imagemSinaleira[1].getSprite(), 800, 160, 70, 93, null);
    }

    //desenha bandeira direita do tunel 2
    if (!pista.isPontoCritico2())
      g.drawImage(imagemSinaleira[1].getSprite(), 800, 160, 70, 93, null);
    else if (pista.getTremNoCrititco2()=="1") {
      g.drawImage(imagemSinaleira[0].getSprite(), 800, 160, 70, 93, null);
      g.drawImage(imagemSinaleira[1].getSprite(), 650, 160, 70, 93, null);
    }
  }

  public void renderizarAlternancia(Graphics g){
    //desenha bandeira esquerda do tunel 1
    if (!(pista.getFlag1(0) || pista.getFlag1(1))) {
      g.drawImage(imagemSinaleira[0].getSprite(), 210, 160, 70, 93, null);
      g.drawImage(imagemSinaleira[1].getSprite(), 360, 160, 70, 93, null);
    }
    else {
      g.drawImage(imagemSinaleira[0].getSprite(), 210, 160, 70, 93, null);
      g.drawImage(imagemSinaleira[1].getSprite(), 360, 160, 70, 93, null);
    }

    //desenha bandeira direita do tunel 1
    if (!(pista.getFlag1(0) || pista.getFlag1(1))) {
      g.drawImage(imagemSinaleira[1].getSprite(), 360, 160, 70, 93, null);
      g.drawImage(imagemSinaleira[1].getSprite(), 210, 160, 70, 93, null);
    }
    else {
      g.drawImage(imagemSinaleira[0].getSprite(), 360, 160, 70, 93, null);
      g.drawImage(imagemSinaleira[0].getSprite(), 210, 160, 70, 93, null);
    }

    //desenha bandeira esquerda do tunel 2
    if (!(pista.getFlag2(0) || pista.getFlag2(1))) {
      g.drawImage(imagemSinaleira[1].getSprite(), 650, 160, 70, 93, null);
      g.drawImage(imagemSinaleira[1].getSprite(), 800, 160, 70, 93, null);
    }
    else {
      g.drawImage(imagemSinaleira[0].getSprite(), 650, 160, 70, 93, null);
      g.drawImage(imagemSinaleira[0].getSprite(), 800, 160, 70, 93, null);
    }

    //desenha bandeira direita do tunel 2
    if (!(pista.getFlag2(0) || pista.getFlag2(1))) {
      g.drawImage(imagemSinaleira[1].getSprite(), 800, 160, 70, 93, null);
      g.drawImage(imagemSinaleira[1].getSprite(), 650, 160, 70, 93, null);
    }
    else {
      g.drawImage(imagemSinaleira[0].getSprite(), 800, 160, 70, 93, null);
      g.drawImage(imagemSinaleira[0].getSprite(), 650, 160, 70, 93, null);
    }
  }

  public void renderizarPeterson(Graphics g){
    //desenha bandeira esquerda do tunel 1
    if (!(pista.getFlag1(0) || pista.getFlag1(1))) {
      g.drawImage(imagemSinaleira[0].getSprite(), 210, 160, 70, 93, null);
      g.drawImage(imagemSinaleira[1].getSprite(), 360, 160, 70, 93, null);
    }
    else {
      g.drawImage(imagemSinaleira[0].getSprite(), 210, 160, 70, 93, null);
      g.drawImage(imagemSinaleira[1].getSprite(), 360, 160, 70, 93, null);
    }

    //desenha bandeira direita do tunel 1
    if (!(pista.getFlag1(0) || pista.getFlag1(1))) {
      g.drawImage(imagemSinaleira[1].getSprite(), 360, 160, 70, 93, null);
      g.drawImage(imagemSinaleira[1].getSprite(), 210, 160, 70, 93, null);
    }
    else {
      g.drawImage(imagemSinaleira[0].getSprite(), 360, 160, 70, 93, null);
      g.drawImage(imagemSinaleira[0].getSprite(), 210, 160, 70, 93, null);
    }

    //desenha bandeira esquerda do tunel 2
    if (!(pista.getFlag2(0) || pista.getFlag2(1))) {
      g.drawImage(imagemSinaleira[1].getSprite(), 650, 160, 70, 93, null);
      g.drawImage(imagemSinaleira[1].getSprite(), 800, 160, 70, 93, null);
    }
    else {
      g.drawImage(imagemSinaleira[0].getSprite(), 650, 160, 70, 93, null);
      g.drawImage(imagemSinaleira[0].getSprite(), 800, 160, 70, 93, null);
    }

    //desenha bandeira direita do tunel 2
    if (!(pista.getFlag2(0) || pista.getFlag2(1))) {
      g.drawImage(imagemSinaleira[1].getSprite(), 800, 160, 70, 93, null);
      g.drawImage(imagemSinaleira[1].getSprite(), 650, 160, 70, 93, null);
    }
    else {
      g.drawImage(imagemSinaleira[0].getSprite(), 800, 160, 70, 93, null);
      g.drawImage(imagemSinaleira[0].getSprite(), 650, 160, 70, 93, null);
    }
  }
}
