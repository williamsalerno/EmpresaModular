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
package br.com.contmatic.empresawilliam.assembler;

import static br.com.contmatic.empresawilliam.assembler.EnderecoAssembler.enderecoToDocument;
import static br.com.contmatic.empresawilliam.assembler.TelefoneAssembler.telefoneToDocument;

import java.util.Date;

import org.bson.Document;
import org.joda.time.LocalDate;

import br.com.contmatic.empresawilliam.Empresa;

/**
 * The Class EmpresaAssembler.
 *
 * @author William
 */
public final class EmpresaAssembler {

    /**
     * Instantiates a new empresa assembler.
     */
    private EmpresaAssembler() {
    }

    /**
     * Converte para date.
     *
     * @param localDate the local date
     * @return the date
     */
    public static Date converteParaDate(LocalDate localDate) {
        if (localDate != null) {
            return Date.from(localDate.toDate().toInstant());
        }
        return null;
    }

    // public static List<Object> valuesEmpresa(Empresa empresaFind, Document
    // doc) {
    // Set<String> keys = new HashSet<>();
    // keys = doc.keySet();
    // List<Object> values = new ArrayList<>();
    // Iterator<String> itr = keys.iterator();
    // while (itr.hasNext()) {
    // values.add(doc.get(itr.next()));
    // }
    // return values;
    // }

    /**
     * To document.
     *
     * @param empresa the empresa
     * @return the document
     */
    public static Document toDocument(Empresa empresa) {
        if (empresa != null) {
            Date dataCriacao = converteParaDate(empresa.getDataDeCriacao());
            Date dataAlteracao = converteParaDate(empresa.getDataDeAlteracao());
            Document empresaDoc = new Document();
            empresaDoc.append("_id", empresa.getCnpj())
            .append("razaoSocial", empresa.getRazaoSocial())
            .append("proprietario", empresa.getProprietario())
            .append("email", empresa.getEmail())
            .append("site", empresa.getSite())
            .append("enderecos", enderecoToDocument(empresa.getEnderecos()))
            .append("telefones", telefoneToDocument(empresa.getTelefones()))
            .append("dataCriacao", dataCriacao)
            .append("dataAlteracao", dataAlteracao);
            return empresaDoc;
        }
        return null;
    }

    /**
     * Update to document filter.
     *
     * @param empresa the empresa
     * @return the document
     */
    public static Document updateToDocumentFilter(Empresa empresa) {
        if (empresa == null) {
            return null;
        }
        Document empresaDoc = new Document();
        if (empresa.getRazaoSocial() != null) {
            empresaDoc.append("razaoSocial", empresa.getRazaoSocial());
        }
        if (empresa.getProprietario() != null) {
            empresaDoc.append("proprietario", empresa.getProprietario());
        }
        if (empresa.getSite() != null) {
            empresaDoc.append("site", empresa.getSite());
        }
        if (empresa.getEmail() != null) {
            empresaDoc.append("email", empresa.getEmail());
        }
        if (empresa.getEnderecos() != null) {
            empresaDoc.append("enderecos", enderecoToDocument(empresa.getEnderecos()));
        }
        if (empresa.getTelefones() != null) {
            empresaDoc.append("telefones", telefoneToDocument(empresa.getTelefones()));
        }
        if (empresa.getDataDeAlteracao() != null) {
            Date dataAlteracao = converteParaDate(empresa.getDataDeAlteracao());
            empresaDoc.append("dataAlteracao", dataAlteracao);
        }
        return empresaDoc;
    }

    /**
     * Find to document filter.
     *
     * @param empresa the empresa
     * @return the document
     */
    public static Document findToDocumentFilter(Empresa empresa) {
        if (empresa == null) {
            return null;
        }
        Document empresaDoc = new Document();
        if (empresa.getCnpj() != null) {
            empresaDoc.append("_id", empresa.getCnpj());
        }
        if (empresa.getProprietario() != null) {
            empresaDoc.append("proprietario", empresa.getProprietario());
        }
        if (empresa.getRazaoSocial() != null) {
            empresaDoc.append("razaoSocial", empresa.getRazaoSocial());
        }
        if (empresa.getSite() != null) {
            empresaDoc.append("site", empresa.getSite());
        }
        if (empresa.getEmail() != null) {
            empresaDoc.append("email", empresa.getEmail());
        }
        if (empresa.getEnderecos() != null) {
            empresaDoc.append("enderecos", enderecoToDocument(empresa.getEnderecos()));
        }
        if (empresa.getTelefones() != null) {
            empresaDoc.append("telefones", telefoneToDocument(empresa.getTelefones()));
        }
        if (empresa.getDataDeCriacao() != null) {
            Date dataCriacao = converteParaDate(empresa.getDataDeCriacao());
            empresaDoc.append("dataCriacao", dataCriacao);
        }
        if (empresa.getDataDeAlteracao() != null) {
            Date dataAlteracao = converteParaDate(empresa.getDataDeAlteracao());
            empresaDoc.append("dataAlteracao", dataAlteracao);
        }
        return empresaDoc;
    }
}
