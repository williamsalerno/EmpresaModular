package br.com.contmatic.empresawilliam;

import static br.com.contmatic.empresawilliam.util.ValidationUtil.hasErrors;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

/**
 * @author William
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TelefoneTeste {

    private Telefone fixoValido;
    private Telefone fixoInvalido;
    private Telefone celularValido;
    private Telefone celularInvalido;
    private Telefone telefoneDDDMenor;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void setUpBeforeClass() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.empresawilliam.templates");
    }

    @Before
    public void setUp() {
        this.fixoValido = Fixture.from(Telefone.class).gimme("fixo_valido");
        this.fixoInvalido = Fixture.from(Telefone.class).gimme("fixo_invalido");
        this.celularValido = Fixture.from(Telefone.class).gimme("celular_valido");
        this.celularInvalido = Fixture.from(Telefone.class).gimme("celular_invalido");
        this.telefoneDDDMenor = Fixture.from(Telefone.class).gimme("ddd_menor_que_limite");
    }

    @After
    public void tearDown() {
        fixoValido = null;
        fixoInvalido = null;
        celularValido = null;
        celularInvalido = null;
        telefoneDDDMenor = null;
    }

    @AfterClass
    public static void tearDownAfterClass() {
        System.out.println("Teste de Telefone terminado.");
    }

    // Testes

    @Test
    public void nao_deve_aceitar_tipoTelefone_nulo() {
        fixoValido.setTipoTelefone(null);
        assertTrue(hasErrors(fixoValido, "O tipo de telefone deve ser preenchido."));
    }

    @Test
    public void deve_aceitar_ddd_valido() {
        assertThat(hasErrors(fixoValido, null), is(false));
    }

    @Test
    public void nao_deve_aceitar_ddd_menor_que_limite() {
        assertTrue(hasErrors(telefoneDDDMenor, "O número de DDD informado deve ser entre 11 e 99."));
    }

    @Test
    public void nao_deve_aceitar_ddd_maior_que_limite() {
        assertTrue(hasErrors(telefoneDDDMenor, "O número de DDD informado deve ser entre 11 e 99."));
    }

    @Test
    public void nao_deve_aceitar_ddd_negativo() {
        assertTrue(hasErrors(telefoneDDDMenor, "O número de DDD informado deve ser entre 11 e 99."));
    }

    @Test
    public void deve_aceitar_telefoneFixo_valido() {
        assertThat(hasErrors(fixoValido, null), is(false));
    }

    @Test
    public void nao_deve_aceitar_telefoneFixo_nulo() {
        fixoValido.setTelefone(null);
        assertTrue(hasErrors(fixoValido, "O número de telefone não pode ser nulo."));
    }

    @Test
    public void nao_deve_aceitar_telefoneFixo_vazio() {
        fixoValido.setTelefone("");
        assertTrue(hasErrors(fixoValido, "O número de telefone não pode ficar vazio."));
    }

    @Test
    public void nao_deve_aceitar_telefoneFixo_em_branco() {
        fixoValido.setTelefone(" ");
        assertTrue(hasErrors(fixoValido, "O número de telefone não pode ficar vazio."));
    }

    @Test
    public void nao_deve_aceitar_telefoneFixo_menor_que_limite() {
        assertTrue(hasErrors(fixoInvalido, "Para telefone fixo, por favor informar 8 dígitos.", Fixo.class));
    }

    @Test
    public void nao_deve_aceitar_telefoneFixo_maior_que_limite() {
        assertTrue(hasErrors(fixoInvalido, "Para telefone fixo, por favor informar 8 dígitos.", Fixo.class));
    }

    @Test
    public void deve_aceitar_telefoneCelular_valido() {
        assertThat(hasErrors(celularValido, null), is(false));
    }

    @Test
    public void nao_deve_aceitar_telefoneCelular_nulo() {
        celularValido.setTelefone(null);
        assertTrue(hasErrors(celularValido, "O número de telefone não pode ser nulo."));
    }

    @Test
    public void nao_deve_aceitar_telefoneCelular_vazio() {
        celularValido.setTelefone("");
        assertTrue(hasErrors(celularValido, "O número de telefone não pode ficar vazio."));
    }

    @Test
    public void nao_deve_aceitar_telefoneCelular_em_branco() {
        celularValido.setTelefone(" ");
        assertTrue(hasErrors(celularValido, "O número de telefone não pode ficar vazio."));
    }

    @Test
    public void nao_deve_aceitar_telefoneCelular_menor_que_limite() {
        assertTrue(hasErrors(celularInvalido, "Para telefone celular, por favor informar 9 dígitos.", Celular.class));

    }

    @Test
    public void nao_deve_aceitar_telefoneCelular_maior_que_limite() {
        assertTrue(hasErrors(celularInvalido, "Para telefone celular, por favor informar 9 dígitos.", Celular.class));
    }
}
