package modulos.produto;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.*;
import telas.LoginTela;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@DisplayName("Testes Mobile do Módulo de Produto")
public class ProdutoTestRefatorado {
    private WebDriver app;

    @BeforeEach
    public void beforeEach() throws MalformedURLException {
        // Abrir o App
        DesiredCapabilities capacidades = new DesiredCapabilities();
        capacidades.setCapability("deviceName","LDPlayer");
        // Inicie o emulador android e no cmd do pc, digite adb devices e copie o identificador apresentado
        capacidades.setCapability("platformName", "Android");
        capacidades.setCapability("udid", "emulator-5554");
        //capacidades.setCapability("automationName", "UiAutomator2");
        capacidades.setCapability("appPackage", "com.lojinha");
        capacidades.setCapability("appActivity", "com.lojinha.ui.MainActivity");
        // Instala o arquivo da lojinha.apk
        capacidades.setCapability("app", "C:\\Users\\Rogerio\\Desktop\\Lojinha+Android+Nativa\\lojinha-nativa.apk");

        // Isso fará a var app ser executado no dispostivo apontado (emulator-5554)
       this.app = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capacidades);

        // Se não achar algum elemento, tente 5 seg. até quebrar o teste. Serve para lentidão no app.
        this.app.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // Aqui somente para didática faz this.app, mas não precisa.
    }

    //Fazer Login
    @Test
    @DisplayName("Não é permitido registrar um produto com valor igual a zero")
    public void TestNaoEPermitidoRegistrarProdutoComValorMaiorQue7Mil() {
        String mensagemApresentada = new LoginTela(app)
                .informarOUsuario("usuario")
                .informarASenha("senha")
                .submeterFormularioDeLogin()
                .clicarNoBotaoNovoProduto()
                .preencherNomeProduto("teste mobile")
                .preencherValorProduto("700001")
                .preencherCoresProduto("azul, vermelho")
                .clicarBotaoSalvarComErro()
                .capturarMensagemApresentada();
        // Validar que a mensagem de valor inválido foi apresentada
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @AfterEach
    public void afterEach() {
        // Fechar o app
        app.quit();
    }


}
