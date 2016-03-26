package br.com.empresa.repository.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.Document;
import org.joda.time.LocalDate;

import br.com.contmatic.empresawilliam.Empresa;

public final class EmpresaAssembler {

	private EmpresaAssembler() {
	}

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

	public static Document toDocument(Empresa empresa) {
		if (empresa != null) {
			Date dataCriacao = converteParaDate(empresa.getDataDeCriacao());
			Date dataAlteracao = converteParaDate(empresa.getDataDeAlteracao());
			Document empresaDoc = new Document("_id", empresa.getCnpj()).append("razaoSocial", empresa.getRazaoSocial())
					.append("proprietario", empresa.getProprietario()).append("email", empresa.getEmail())
					.append("site", empresa.getSite())
					.append("enderecos", EnderecoAssembler.toDocument(empresa.getEnderecos()))
					.append("telefones", TelefoneAssembler.toDocument(empresa.getTelefones()))
					.append("dataCriacao", dataCriacao).append("dataAlteracao", dataAlteracao);
			return empresaDoc;
		}
		return null;
	}

	public static Document updateToDocumentFilter(Empresa empresa) {
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
			empresaDoc.append("enderecos", EnderecoAssembler.toDocument(empresa.getEnderecos()));
		}
		if (empresa.getTelefones() != null) {
			empresaDoc.append("telefones", TelefoneAssembler.toDocument(empresa.getTelefones()));
		}
		if (empresa.getDataDeAlteracao() != null) {
			Date dataAlteracao = converteParaDate(empresa.getDataDeAlteracao());
			empresaDoc.append("dataAlteracao", dataAlteracao);
		}
		return empresaDoc;
	}

	public static Document findToDocumentFilter(Empresa empresa) {
		if (empresa == null) {
			return null;
		}
		Document empresaDoc = new Document();
		if (empresa.getCnpj() != null) {
			empresaDoc.append("_id", empresa.getCnpj());
		} else {
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
				empresaDoc.append("enderecos", EnderecoAssembler.toDocument(empresa.getEnderecos()));
			}
			if (empresa.getTelefones() != null) {
				empresaDoc.append("telefones", TelefoneAssembler.toDocument(empresa.getTelefones()));
			}
			if (empresa.getDataDeCriacao() != null) {
				Date dataCriacao = converteParaDate(empresa.getDataDeCriacao());
				empresaDoc.append("dataCriacao", dataCriacao);
			}
			if (empresa.getDataDeAlteracao() != null) {
				Date dataAlteracao = converteParaDate(empresa.getDataDeAlteracao());
				empresaDoc.append("dataAlteracao", dataAlteracao);
			}
		}
		return empresaDoc;
	}
}
