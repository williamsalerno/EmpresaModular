package br.com.endereco.assembler.test;

import static br.com.contmatic.empresawilliam.assembler.EnderecoAssembler.documentToEndereco;
import static br.com.contmatic.empresawilliam.assembler.EnderecoAssembler.enderecoToDocument;
import static br.com.contmatic.empresawilliam.assembler.EnderecoAssembler.listaEnderecosToDocument;
import static br.com.contmatic.empresawilliam.assembler.EnderecoAssembler.toEndereco;
import static br.com.six2six.fixturefactory.Fixture.from;
import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.empresawilliam.Empresa;
import br.com.contmatic.empresawilliam.Endereco;

public class EnderecoAssemblerTest {
    private Empresa empresa;
    private Set<Endereco> enderecos;
    private List<Document> enderecosDoc;

    @BeforeClass
    public static void setUpBeforeClass() {
        loadTemplates("br.com.contmatic.empresawilliam.templates");
    }

    @Before
    public void setUp() {
        empresa = from(Empresa.class).gimme("empresa_valida");
    }

    @Test
    public void deve_retornar_empresa_enderecos_igual_a_doc_enderecos() {
        enderecos = new HashSet<Endereco>();
        enderecos.addAll(empresa.getEnderecos());
        Iterator<Endereco> itrEnd = enderecos.iterator();
        while (itrEnd.hasNext()) {
            enderecos.add(itrEnd.next());
        }
        enderecosDoc = listaEnderecosToDocument(enderecos);
        assertEquals(empresa.getEnderecos(), toEndereco(enderecosDoc));
    }

    @Test
    public void deve_tratar_tipoEndereco_null_em_enderecoToDocument() {
        assertNull(enderecoToDocument(null));
    }

    @Test
    public void deve_tratar_endereco_null_em_enderecoToDocument() {
        Endereco endereco = new Endereco();
        assertNull(enderecoToDocument(endereco));
    }

    @Test
    public void deve_tratar_null_em_listaEnderecosToDocument() {
        assertNull(listaEnderecosToDocument(null));
    }

    @Test
    public void deve_tratar_null_em_toEndereco() {
        assertNull(toEndereco(null));
    }

    @Test
    public void deve_tratar_null_em_documentToEndereco() {
        assertNull(documentToEndereco(null));
    }

    @Test
    public void deve_tratar_null_em_tipoEndereco() {
        Endereco endereco = new Endereco();
        endereco.setTipoEndereco(null);
        Document enderecoDoc = enderecoToDocument(endereco);
        assertNull(documentToEndereco(enderecoDoc));
    }

}
