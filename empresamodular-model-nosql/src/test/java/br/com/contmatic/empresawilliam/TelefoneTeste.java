/******************************************************************************
 * Produto: Gestor de Empresas                                                *
 * Contmatic Phoenix © Desde 1986                                             *
 * Tecnologia em Softwares de Gestão Contábil, Empresarial e ERP              *
 * Todos os direitos reservados.                                              *
 *                                                                            *
 *                                                                            *
 *    Histórico:                                                              *
 *          Data        Programador              Tarefa                       *
 *          ----------  -----------------        -----------------------------*
 *   Autor  31/03/2016  william.salerno          Classe criada.        	      *
 *                                                                            *
 *   Comentários:                                                             *
 *                                                                            *
 *                                                                            *
 *                                                                            *
 *                                                                            *
 *****************************************************************************/
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
 * The Class TelefoneTeste.
 *
 * @author William
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TelefoneTeste {

    /** The fixo valido. */
    private Telefone fixoValido;

    /** The fixo invalido. */
    private Telefone fixoInvalido;

    /** The celular valido. */
    private Telefone celularValido;

    /** The celular invalido. */
    private Telefone celularInvalido;

    /** The telefone ddd menor. */
    private Telefone telefoneDDDMenor;

    /** The thrown. */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Set up before class.
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.empresawilliam.templates");
    }

    /**
     * Set up.
     */
    @Before
    public void setUp() {
        this.fixoValido = Fixture.from(Telefone.class).gimme("fixo_valido");
        this.fixoInvalido = Fixture.from(Telefone.class).gimme("fixo_invalido");
        this.celularValido = Fixture.from(Telefone.class).gimme("celular_valido");
        this.celularInvalido = Fixture.from(Telefone.class).gimme("celular_invalido");
        this.telefoneDDDMenor = Fixture.from(Telefone.class).gimme("ddd_menor_que_limite");
    }

    /**
     * Tear down.
     */
    @After
    public void tearDown() {
        fixoValido = null;
        fixoInvalido = null;
        celularValido = null;
        celularInvalido = null;
        telefoneDDDMenor = null;
    }

    /**
     * Tear down after class.
     */
    @AfterClass
    public static void tearDownAfterClass() {
        System.out.println("Teste de Telefone terminado.");
    }

    // Testes

    /**
     * Nao_deve_aceitar_tipo telefone_nulo.
     */
    @Test
    public void nao_deve_aceitar_tipoTelefone_nulo() {
        fixoValido.setTipoTelefone(null);
        assertTrue(hasErrors(fixoValido, "O tipo de telefone deve ser preenchido."));
    }

    /**
     * Deve_aceitar_ddd_valido.
     */
    @Test
    public void deve_aceitar_ddd_valido() {
        assertThat(hasErrors(fixoValido, null), is(false));
    }

    /**
     * Nao_deve_aceitar_ddd_menor_que_limite.
     */
    @Test
    public void nao_deve_aceitar_ddd_menor_que_limite() {
        assertTrue(hasErrors(telefoneDDDMenor, "O número de DDD informado deve ser entre 11 e 99."));
    }

    /**
     * Nao_deve_aceitar_ddd_maior_que_limite.
     */
    @Test
    public void nao_deve_aceitar_ddd_maior_que_limite() {
        assertTrue(hasErrors(telefoneDDDMenor, "O número de DDD informado deve ser entre 11 e 99."));
    }

    /**
     * Nao_deve_aceitar_ddd_negativo.
     */
    @Test
    public void nao_deve_aceitar_ddd_negativo() {
        assertTrue(hasErrors(telefoneDDDMenor, "O número de DDD informado deve ser entre 11 e 99."));
    }

    /**
     * Deve_aceitar_telefone fixo_valido.
     */
    @Test
    public void deve_aceitar_telefoneFixo_valido() {
        assertThat(hasErrors(fixoValido, null), is(false));
    }

    /**
     * Nao_deve_aceitar_telefone fixo_nulo.
     */
    @Test
    public void nao_deve_aceitar_telefoneFixo_nulo() {
        fixoValido.setTelefone(null);
        assertTrue(hasErrors(fixoValido, "O número de telefone não pode ser nulo."));
    }

    /**
     * Nao_deve_aceitar_telefone fixo_vazio.
     */
    @Test
    public void nao_deve_aceitar_telefoneFixo_vazio() {
        fixoValido.setTelefone("");
        assertTrue(hasErrors(fixoValido, "O número de telefone não pode ficar vazio."));
    }

    /**
     * Nao_deve_aceitar_telefone fixo_em_branco.
     */
    @Test
    public void nao_deve_aceitar_telefoneFixo_em_branco() {
        fixoValido.setTelefone(" ");
        assertTrue(hasErrors(fixoValido, "O número de telefone não pode ficar vazio."));
    }

    /**
     * Nao_deve_aceitar_telefone fixo_menor_que_limite.
     */
    @Test
    public void nao_deve_aceitar_telefoneFixo_menor_que_limite() {
        assertTrue(hasErrors(fixoInvalido, "Para telefone fixo, por favor informar 8 dígitos.", Fixo.class));
    }

    /**
     * Nao_deve_aceitar_telefone fixo_maior_que_limite.
     */
    @Test
    public void nao_deve_aceitar_telefoneFixo_maior_que_limite() {
        assertTrue(hasErrors(fixoInvalido, "Para telefone fixo, por favor informar 8 dígitos.", Fixo.class));
    }

    /**
     * Deve_aceitar_telefone celular_valido.
     */
    @Test
    public void deve_aceitar_telefoneCelular_valido() {
        assertThat(hasErrors(celularValido, null), is(false));
    }

    /**
     * Nao_deve_aceitar_telefone celular_nulo.
     */
    @Test
    public void nao_deve_aceitar_telefoneCelular_nulo() {
        celularValido.setTelefone(null);
        assertTrue(hasErrors(celularValido, "O número de telefone não pode ser nulo."));
    }

    /**
     * Nao_deve_aceitar_telefone celular_vazio.
     */
    @Test
    public void nao_deve_aceitar_telefoneCelular_vazio() {
        celularValido.setTelefone("");
        assertTrue(hasErrors(celularValido, "O número de telefone não pode ficar vazio."));
    }

    /**
     * Nao_deve_aceitar_telefone celular_em_branco.
     */
    @Test
    public void nao_deve_aceitar_telefoneCelular_em_branco() {
        celularValido.setTelefone(" ");
        assertTrue(hasErrors(celularValido, "O número de telefone não pode ficar vazio."));
    }

    /**
     * Nao_deve_aceitar_telefone celular_menor_que_limite.
     */
    @Test
    public void nao_deve_aceitar_telefoneCelular_menor_que_limite() {
        assertTrue(hasErrors(celularInvalido, "Para telefone celular, por favor informar 9 dígitos.", Celular.class));

    }

    /**
     * Nao_deve_aceitar_telefone celular_maior_que_limite.
     */
    @Test
    public void nao_deve_aceitar_telefoneCelular_maior_que_limite() {
        assertTrue(hasErrors(celularInvalido, "Para telefone celular, por favor informar 9 dígitos.", Celular.class));
    }
}
