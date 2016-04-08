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
package br.com.empresa.assembler.test;

import static br.com.contmatic.empresawilliam.assembler.EmpresaAssembler.converteParaDate;
import static br.com.contmatic.empresawilliam.assembler.EmpresaAssembler.findToDocumentFilter;
import static br.com.contmatic.empresawilliam.assembler.EmpresaAssembler.toDocument;
import static br.com.contmatic.empresawilliam.assembler.EmpresaAssembler.updateToDocumentFilter;
import static br.com.six2six.fixturefactory.Fixture.from;
import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.bson.Document;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.empresawilliam.Empresa;

/**
 * The Class EmpresaDocumentTest.
 *
 * @author William
 */
public class EmpresaAssemblerTest {

    private Empresa empresa;
    private Empresa empresaVazia;
    private Document empresaDoc;

    @BeforeClass
    public static void setUpBeforeClass() {
        loadTemplates("br.com.contmatic.empresawilliam.templates");
    }

    @Before
    public void setUp() {
        empresa = from(Empresa.class).gimme("empresa_valida");
        empresaVazia = new Empresa();
        empresaDoc = toDocument(empresa);
    }

    @Test
    public void deve_retornar_empresa_cnpj_igual_a_doc_cnpj() {
        assertEquals(empresa.getCnpj(), empresaDoc.get("_id"));
    }

    @Test
    public void deve_retornar_empresa_razaoSocial_igual_a_doc_razaoSocial() {
        assertEquals(empresa.getRazaoSocial(), empresaDoc.get("razaoSocial"));
    }

    @Test
    public void deve_retornar_empresa_proprietario_igual_a_doc_proprietario() {
        assertEquals(empresa.getProprietario(), empresaDoc.get("proprietario"));
    }

    @Test
    public void deve_retornar_empresa_email_igual_a_doc_email() {
        assertEquals(empresa.getEmail(), empresaDoc.get("email"));
    }

    @Test
    public void deve_retornar_empresa_site_igual_a_doc_site() {
        assertEquals(empresa.getSite(), empresaDoc.get("site"));
    }

    @Test
    public void deve_tratar_null_em_toDocument() {
        assertNull(toDocument(null));
    }

    @Test
    public void deve_retornar_updateToDocumentFilter_vazio() {
        assertTrue(updateToDocumentFilter(empresaVazia).isEmpty());
    }

    @Test
    public void deve_retornar_updateToDocumentFilter_correto() {
        assertFalse(updateToDocumentFilter(empresa).isEmpty());
    }

    @Test
    public void deve_tratar_null_em_updateToDocumentFilter() {
        assertNull(updateToDocumentFilter(null));
    }

    @Test
    public void deve_retornar_findToDocumentFilter_vazio() {
        assertTrue(findToDocumentFilter(empresaVazia).isEmpty());
    }

    @Test
    public void deve_retornar_findToDocumentFilter_correto() {
        assertFalse(findToDocumentFilter(empresa).isEmpty());
    }

    @Test
    public void deve_tratar_null_em_findToDocumentFilter() {
        assertNull(findToDocumentFilter(null));
    }

    @Test
    public void deve_tratar_null_em_converteParaDate() {
        assertNull(converteParaDate(empresaVazia.getDataDeCriacao()));
    }

}
