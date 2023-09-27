package telas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*
    principios de page objects
    -tenha um atributo da classe que seja web driver
    -tenha um construtor que pegue uma variável e jogue dentro do atributo
    -ter métodos de interação com cada elemento
 */
public class LoginTela extends BaseTela{
    public LoginTela(WebDriver app) {
        super(app);
    }

    public LoginTela informarOUsuario(String usuario) {
        app.findElement(By.id("com.lojinha:id/user")).click();
        app.findElement(By.id("com.lojinha:id/user")).findElement(By.id("com.lojinha:id/editText")).sendKeys(usuario);

        return this; /* retorna a própria classe ou instância do objeto pois vai permanecer na
        mesma página permitindo encadear chamadas de métodos em uma única instancia do objeto sem precisar
        criar nova instância cada vez.*/
    }

    public LoginTela informarASenha(String senha) {
        app.findElement(By.id("com.lojinha:id/password")).click();
        app.findElement(By.id("com.lojinha:id/password")).findElement(By.id("com.lojinha:id/editText")).sendKeys(senha);

        return this;
    }

    public ListaDeProdutosTela submeterFormularioDeLogin() {
        app.findElement(By.id("com.lojinha:id/loginButton")).click();

        return new ListaDeProdutosTela(app);
    }

}
