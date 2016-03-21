package br.com.empresa.repository.util;

import java.util.Date;

import org.bson.Document;
import org.joda.time.LocalDate;

import br.com.contmatic.empresawilliam.Empresa;

public final class EmpresaDocument {

    private EmpresaDocument() {
    }

    public static Date converteParaDate(LocalDate localDate) {
        return Date.from(localDate.toDate().toInstant());
    }

    public static Document toDocument(Empresa empresa) {
        if (empresa != null) {
            Date dataCriacao = converteParaDate(empresa.getDataDeCriacao());
            Date dataAlteracao = converteParaDate(empresa.getDataDeAlteracao());
            Document empresaDoc = new Document("cnpj", empresa.getCnpj()).append("razaoSocial", empresa.getRazaoSocial()).append("proprietario", empresa.getProprietario())
                    .append("email", empresa.getEmail()).append("site", empresa.getSite()).append("enderecos", EnderecoDocument.toDocument(empresa.getEnderecos()))
                    .append("telefones", TelefoneDocument.toDocument(empresa.getTelefones())).append("dataCriacao", dataCriacao).append("dataAlteracao", dataAlteracao);
            return empresaDoc;
        }
        return null;
    }

    public static Document toDocumentFilter(Empresa empresa) {
        if (empresa == null) {
            return null;
        }
        Document empresaDoc = new Document();
        if (empresa.getCnpj() != null) {
            empresaDoc.append("cnpj", empresa.getCnpj());
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
            empresaDoc.append("enderecos", EnderecoDocument.toDocument(empresa.getEnderecos()));
        }
        if (empresa.getTelefones() != null) {
            empresaDoc.append("telefones", TelefoneDocument.toDocument(empresa.getTelefones()));
        }
        // if (empresa.getDataDeCriacao() != null) {
        // empresaDoc.append("dataDeCriacao", empresa.getDataDeCriacao());
        // }
        // if (empresa.getDataDeAlteracao() != null) {
        // empresaDoc.append("dataDeAlteracao", empresa.getDataDeAlteracao());
        // }
        return empresaDoc;
    }
}
