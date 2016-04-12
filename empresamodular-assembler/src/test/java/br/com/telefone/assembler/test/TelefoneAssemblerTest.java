package br.com.telefone.assembler.test;

import static br.com.contmatic.empresawilliam.assembler.TelefoneAssembler.documentToTelefone;
import static br.com.contmatic.empresawilliam.assembler.TelefoneAssembler.listaTelefonesToDocument;
import static br.com.contmatic.empresawilliam.assembler.TelefoneAssembler.telefoneToDocument;
import static br.com.contmatic.empresawilliam.assembler.TelefoneAssembler.toTelefone;
import static br.com.six2six.fixturefactory.Fixture.from;
import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.empresawilliam.Empresa;
import br.com.contmatic.empresawilliam.Telefone;

public class TelefoneAssemblerTest {

    private Empresa empresa;
    private Set<Telefone> telefones;
    private List<Document> telefonesDoc;

    @BeforeClass
    public static void setUpBeforeClass() {
        loadTemplates("br.com.contmatic.empresawilliam.templates");
    }

    @Test
    public void deve_retornar_empresa_telefones_igual_a_doc_telefones() {
        empresa = from(Empresa.class).gimme("empresa_valida");
        telefones = new HashSet<Telefone>();
        telefones.addAll(empresa.getTelefones());
        Iterator<Telefone> itrEnd = telefones.iterator();
        while (itrEnd.hasNext()) {
            telefones.add(itrEnd.next());
        }
        telefonesDoc = listaTelefonesToDocument(telefones);
        assertEquals(empresa.getTelefones(), toTelefone(telefonesDoc));
    }

    @Test
    public void deve_tratar_tipoTelefone_null_em_telefoneToDocument() {
        assertNull(telefoneToDocument(null));
    }

    @Test
    public void deve_tratar_endereco_null_em_telefoneToDocument() {
        Telefone telefone = new Telefone();
        assertNull(telefoneToDocument(telefone));
    }

    @Test
    public void deve_tratar_null_em_listaTelefonesToDocument() {
        assertNull(listaTelefonesToDocument(null));
    }

    @Test
    public void deve_tratar_null_em_toTelefone() {
        assertNull(toTelefone(null));
    }

    @Test
    public void deve_tratar_null_em_documentToTelefone() {
        assertNull(documentToTelefone(null));
    }

    @Test
    public void deve_tratar_null_em_tipoTelefone() {
        Telefone telefone = new Telefone();
        Document telefoneDoc = telefoneToDocument(telefone);
        assertNull(documentToTelefone(telefoneDoc));
    }

}
