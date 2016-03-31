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
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

/**
 * The Class EnderecoTeste.
 *
 * @author William
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EnderecoTeste {

    /** The endereco. */
    private Endereco endereco;

    /** The thrown. */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /** The global timeout. */
    public Timeout globalTimeout = Timeout.seconds(1);

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
        this.endereco = Fixture.from(Endereco.class).gimme("endereco_valido");
    }

    /**
     * Tear down.
     */
    @After
    public void tearDown() {
        endereco = null;
    }

    /**
     * Tear down after class.
     */
    @AfterClass
    public static void tearDownAfterClass() {
        System.out.println("Teste de Endereco terminado.");
    }

    // Testes

    /**
     * Deve_aceitar_cep_valido.
     */
    @Test
    public void deve_aceitar_cep_valido() {
        assertThat(hasErrors(endereco, null), is(false));
    }

    /**
     * Nao_deve_aceitar_cep_nulo.
     */
    @Test
    public void nao_deve_aceitar_cep_nulo() {
        endereco.setCep(null);
        assertTrue(hasErrors(endereco, "O CEP deve ser preenchido."));
    }

    /**
     * Nao_deve_aceitar_cep_vazio.
     */
    @Test
    public void nao_deve_aceitar_cep_vazio() {
        endereco.setCep("");
        assertTrue(hasErrors(endereco, "O CEP não pode ficar vazio."));
    }

    /**
     * Nao_deve_aceitar_cep_em_branco.
     */
    @Test
    public void nao_deve_aceitar_cep_em_branco() {
        endereco.setCep(" ");
        assertTrue(hasErrors(endereco, "O CEP não pode ficar vazio."));
    }

    /**
     * Nao_deve_aceitar_cep_menor_que_limite.
     */
    @Test
    public void nao_deve_aceitar_cep_menor_que_limite() {
        endereco.setCep("1234567");
        assertTrue(hasErrors(endereco, "O CEP deve conter 8 dígitos."));
    }

    /**
     * Nao_deve_aceitar_cep_maior_que_limite.
     */
    @Test
    public void nao_deve_aceitar_cep_maior_que_limite() {
        endereco.setCep("123456789");
        assertTrue(hasErrors(endereco, "O CEP deve conter 8 dígitos."));
    }

    /**
     * Nao_deve_aceitar_cep_com_letras.
     */
    @Test
    public void nao_deve_aceitar_cep_com_letras() {
        endereco.setCep("abcdefgh");
        assertTrue(hasErrors(endereco, "O CEP deve conter 8 dígitos."));
    }

    /**
     * Deve_aceitar_numero endereco_valido.
     */
    @Test
    public void deve_aceitar_numeroEndereco_valido() {
        assertThat(hasErrors(endereco, null), is(false));
    }

    /**
     * Nao_deve_aceitar_numero endereco_menor_que_limite.
     */
    @Test
    public void nao_deve_aceitar_numeroEndereco_menor_que_limite() {
        endereco.setNumeroEndereco(0);
        assertTrue(hasErrors(endereco, "O número de endereço deve ser, no mínimo, 1."));
    }

    /**
     * Nao_deve_aceitar_numero endereco_maior_que_limite.
     */
    @Test
    public void nao_deve_aceitar_numeroEndereco_maior_que_limite() {
        endereco.setNumeroEndereco(10000);
        assertTrue(hasErrors(endereco, "O número de endereço deve ser, no máximo, 9999."));
    }

    /**
     * Nao_deve_aceitar_numero endereco_negativo.
     */
    @Test
    public void nao_deve_aceitar_numeroEndereco_negativo() {
        endereco.setNumeroEndereco(-1);
        assertTrue(hasErrors(endereco, "O número de endereço deve ser, no mínimo, 1."));
    }

    /**
     * Deve_aceitar_nome logradouro_valido.
     */
    @Test
    public void deve_aceitar_nomeLogradouro_valido() {
        assertThat(hasErrors(endereco, null), is(false));
    }

    /**
     * Nao_deve_aceitar_nome logradouro_nulo.
     */
    @Test
    public void nao_deve_aceitar_nomeLogradouro_nulo() {
        endereco.setNomeLogradouro(null);
        assertTrue(hasErrors(endereco, "O nome de logradouro deve ser preenchido."));
    }

    /**
     * Nao_deve_aceitar_nome logradouro_vazio.
     */
    @Test
    public void nao_deve_aceitar_nomeLogradouro_vazio() {
        endereco.setNomeLogradouro("");
        assertTrue(hasErrors(endereco, "O nome de logradouro não pode ficar vazio."));
    }

    /**
     * Nao_deve_aceitar_nome logradouro_em_branco.
     */
    @Test
    public void nao_deve_aceitar_nomeLogradouro_em_branco() {
        endereco.setNomeLogradouro(" ");
        assertTrue(hasErrors(endereco, "O nome de logradouro não pode ficar vazio."));
    }

    /**
     * Nao_deve_aceitar_nome logradouro_maior_que_limite.
     */
    @Test
    public void nao_deve_aceitar_nomeLogradouro_maior_que_limite() {
        endereco.setNomeLogradouro("1234567891123456789212345678931");
        assertTrue(hasErrors(endereco, "O nome de logradouro deve conter entre 1 e 30 caracteres."));
    }

    /**
     * Nao_deve_aceitar_nome logradouro_com_caracter_especial.
     */
    @Test
    public void nao_deve_aceitar_nomeLogradouro_com_caracter_especial() {
        endereco.setNomeLogradouro("teste_exemplo");
        assertTrue(hasErrors(endereco, "O nome de logradouro deve conter apenas caracteres alfanuméricos."));
    }

    /**
     * Deve_aceitar_tipo logradouro_valido.
     */
    @Test
    public void deve_aceitar_tipoLogradouro_valido() {
        assertThat(hasErrors(endereco, null), is(false));
    }

    /**
     * Nao_deve_aceitar_tipo logradouro_nulo.
     */
    @Test
    public void nao_deve_aceitar_tipoLogradouro_nulo() {
        endereco.setTipoLogradouro(null);
        assertTrue(hasErrors(endereco, "O tipo de logradouro deve ser preenchido."));
    }

    /**
     * Nao_deve_aceitar_tipo logradouro_vazio.
     */
    @Test
    public void nao_deve_aceitar_tipoLogradouro_vazio() {
        endereco.setTipoLogradouro("");
        assertTrue(hasErrors(endereco, "O tipo de logradouro não pode ficar vazio."));
    }

    /**
     * Nao_deve_aceitar_tipo logradouro_em_branco.
     */
    @Test
    public void nao_deve_aceitar_tipoLogradouro_em_branco() {
        endereco.setTipoLogradouro(" ");
        assertTrue(hasErrors(endereco, "O tipo de logradouro não pode ficar vazio."));
    }

    /**
     * Nao_deve_aceitar_tipo logradouro_menor_que_minimo.
     */
    @Test
    public void nao_deve_aceitar_tipoLogradouro_menor_que_minimo() {
        endereco.setTipoLogradouro("ex");
        assertTrue(hasErrors(endereco, "O tipo de logradouro deve conter entre 3 e 10 caracteres, sem números."));
    }

    /**
     * Nao_deve_aceitar_tipo logradouro_maior_que_maximo.
     */
    @Test
    public void nao_deve_aceitar_tipoLogradouro_maior_que_maximo() {
        endereco.setTipoLogradouro("exemploexemplo");
        assertTrue(hasErrors(endereco, "O tipo de logradouro deve conter entre 3 e 10 caracteres, sem números."));
    }

    /**
     * Nao_deve_aceitar_tipo logradouro_invalido.
     */
    @Test
    public void nao_deve_aceitar_tipoLogradouro_invalido() {
        endereco.setTipoLogradouro("Ru12");
        assertTrue(hasErrors(endereco, "O tipo de logradouro deve conter entre 3 e 10 caracteres, sem números."));
    }

    /**
     * Nao_deve_aceitar_tipo endereco_nulo.
     */
    @Test
    public void nao_deve_aceitar_tipoEndereco_nulo() {
        endereco.setTipoEndereco(null);
        assertTrue(hasErrors(endereco, "O tipo de endereço deve ser preenchido."));
    }
}
