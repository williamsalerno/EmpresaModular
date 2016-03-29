package br.com.contmatic.empresawilliam.assembler;

import static br.com.contmatic.empresawilliam.assembler.EnderecoAssembler.toEndereco;
import static br.com.contmatic.empresawilliam.assembler.TelefoneAssembler.toTelefone;

import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.joda.time.LocalDate;

import br.com.contmatic.empresawilliam.Empresa;

/**
 * The Class EmpresaObject.
 *
 * @author William
 */
public class EmpresaObject {

    /**
     * Instantiates a new empresa object.
     */
    private EmpresaObject() {

    }

    /**
     * Converte para local date.
     *
     * @param document the document
     * @param key the key
     * @return the local date
     */
    public static LocalDate converteParaLocalDate(Document document, String key) {
        if (document != null) {
            Date date = document.getDate(key);
            if (date != null) {
                return LocalDate.fromDateFields(date);
            }
        }
        return null;
    }

    /**
     * Empresa to object.
     *
     * @param document the document
     * @return the empresa
     */
    public static Empresa empresaToObject(Document document) {
        if (document == null) {
            return null;
        } else {
            Empresa empresa = new Empresa();
            empresa.setCnpj(document.getString("_id"));
            empresa.setProprietario(document.getString("proprietario"));
            empresa.setRazaoSocial(document.getString("razaoSocial"));
            empresa.setEmail(document.getString("email"));
            empresa.setSite(document.getString("site"));
            empresa.setTelefones(toTelefone((List<Document>) document.get("telefones")));
            empresa.setEnderecos(toEndereco((List<Document>) document.get("enderecos")));
            empresa.isPesquisa();
            empresa.setDataDeCriacao(converteParaLocalDate(document, "dataCriacao"));
            if (document.get("dataAlteracao") != null) {
                empresa.setDataDeAlteracao(converteParaLocalDate(document, "dataAlteracao"));
            }
            empresa.isPesquisaOff();
            return empresa;
        }

    }
}
