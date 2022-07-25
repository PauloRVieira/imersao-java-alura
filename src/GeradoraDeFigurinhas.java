import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
    
    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {
        // leitura da imagem
        //BufferedImage imagemOriginal = ImageIO.read(new File("entrada/Filme.jpg"));
        
        //InputStream inputStream = new URL("").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // criar nova imagem em memória com transparência e com novo tamanho
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;

        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);
        
        // configurar a fonte
        Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setFont(fonte);
        graphics.setColor(Color.YELLOW);
        
        // escrever uma frase na nova imagem
        graphics.drawString("TOPZERA", 50, novaAltura - 100);

        // escrever a nova imagem em um arquivo
        //ImageIO.write(novaImagem, "png", new File("saida/figura.png"));
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));
    }

    public static void main(String[] args) {

    }

}
