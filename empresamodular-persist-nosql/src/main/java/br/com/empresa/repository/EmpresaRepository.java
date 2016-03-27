package br.com.empresa.repository;

import static br.com.contmatic.empresawilliam.util.ValidationUtil.hasErrors;
import static br.com.empresa.repository.util.EmpresaAssembler.findToDocumentFilter;
import static br.com.empresa.repository.util.EmpresaAssembler.toDocument;
import static br.com.empresa.repository.util.EmpresaAssembler.updateToDocumentFilter;
import static br.com.empresa.repository.util.EmpresaObject.empresaToObject;
import static br.com.empresa.repository.util.MongoClientDate.codecDate;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.com.contmatic.empresawilliam.Empresa;
import br.com.contmatic.empresawilliam.util.ValidationUtil;
import br.com.empresa.repository.util.MongoClientDate;

/**
 * @author William
 *
 */
public class EmpresaRepository {

	private String host;
	private int port;
	private String db;
	public static final String COLLECTION = "empresa";
	private MongoClient mongoClient;

	public EmpresaRepository(String host, int port, String db) {
		this.host = host;
		this.port = port;
		this.db = db;
	}

	public void saveEmpresa(Empresa empresa) {
		try {
			this.mongoClient = new MongoClient(this.host + ":" + this.port, MongoClientDate.codecDate());
			if (hasErrors(empresa)) {
				throw new Exception("Foi encontrado erro em Empresa.");
			}
			MongoDatabase database = mongoClient.getDatabase(this.db);
			MongoCollection<Document> collection = database.getCollection(COLLECTION);
			collection.insertOne(toDocument(empresa));
		} catch (MongoWriteException e) {
			throw new IllegalStateException("Empresa já existe.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mongoClient.close();
		}
	}

	public void updateEmpresa(Empresa empresaFiltro, Empresa empresa) {
		try {
			if (ValidationUtil.hasErrors(empresa)) {
				throw new Exception("Foi encontrado erro em Empresa.");
			}
			this.mongoClient = new MongoClient(this.host + ":" + this.port, MongoClientDate.codecDate());
			MongoDatabase database = mongoClient.getDatabase(this.db);
			MongoCollection<Document> collection = database.getCollection(COLLECTION);
			collection.updateOne(findToDocumentFilter(empresaFiltro),
					new Document("$set", updateToDocumentFilter(empresa)));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mongoClient.close();
		}
	}

	public void updateEmpresas(Empresa empresaFiltro, Empresa empresa) {
		try {
			if (ValidationUtil.hasErrors(empresa)) {
				throw new Exception("Foi encontrado erro em Empresa.");
			}
			this.mongoClient = new MongoClient(this.host + ":" + this.port, MongoClientDate.codecDate());
			MongoDatabase database = mongoClient.getDatabase(this.db);
			MongoCollection<Document> collection = database.getCollection(COLLECTION);
			empresa.setRazaoSocial("asdhuahsd");
			collection.updateMany(findToDocumentFilter(empresaFiltro),
					new Document("$set", updateToDocumentFilter(empresa)));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mongoClient.close();
		}
	}

	public void removeEmpresa(Empresa empresaFiltro) {
		try {
			this.mongoClient = new MongoClient(this.host, this.port);
			MongoDatabase database = mongoClient.getDatabase(this.db);
			MongoCollection<Document> collection = database.getCollection(COLLECTION);
			collection.deleteOne(new Document("_id", empresaFiltro.getCnpj()));
		} finally {
			mongoClient.close();
		}
	}

	public void removeEmpresas(Empresa empresaFiltro) {
		try {
			this.mongoClient = new MongoClient(this.host, this.port);
			MongoDatabase database = mongoClient.getDatabase(this.db);
			MongoCollection<Document> collection = database.getCollection(COLLECTION);
			collection.deleteMany(findToDocumentFilter(empresaFiltro));
		} finally {
			mongoClient.close();
		}
	}

	public List<Empresa> buscaEmpresa(Empresa empresa) {
		try {
			this.mongoClient = new MongoClient(this.host + ":" + this.port, MongoClientDate.codecDate());
			MongoDatabase database = mongoClient.getDatabase(this.db);
			FindIterable<Document> collection = database.getCollection(COLLECTION).find();
			final List<Empresa> empresas = new ArrayList<>();
			collection.forEach(new Block<Document>() {
				public void apply(final Document document) {
					empresas.add(empresaToObject(document));
				}
			});
			return empresas;
		} finally {
			mongoClient.close();
		}

	}

	public Map<String, String> buscaEmpresaPor(List<String> key) {
		try {
			this.mongoClient = new MongoClient(this.host + ":" + this.port, MongoClientDate.codecDate());
			MongoDatabase database = mongoClient.getDatabase(this.db);
			Map<String, String> mapEmpresas = new HashMap<>();
			FindIterable<Document> collection;
			List<Integer> is = new ArrayList<>();
			for (int i = 0; i < key.size(); i++) {
				is.add(i);
			}
			for (final int i : is) {
				collection = database.getCollection(COLLECTION).find().projection(fields(include(key), include("_id")));
				collection.forEach(new Block<Document>() {
					public void apply(final Document document) {
						mapEmpresas.put(key.get(i), document.getString(key.get(i)));
					}
				});
			}
			return mapEmpresas;
		} finally {
			mongoClient.close();
		}
	}

	public List<Empresa> buscaEmpresaPor(Empresa empresaFind) {
		try {
			List<Empresa> empresas = new ArrayList<>();
			Document doc = new Document(updateToDocumentFilter(empresaFind));
			this.mongoClient = new MongoClient(this.host + ":" + this.port, codecDate());
			MongoDatabase database = mongoClient.getDatabase(this.db);
			Set<String> setKeys = doc.keySet();
			List<String> listKeys = new ArrayList<>();
			Iterator<String> itr = setKeys.iterator();
			while (itr.hasNext()) {
				listKeys.add(itr.next());
			}
			FindIterable<Document> collection;
			collection = database.getCollection(COLLECTION).find()
					.projection(fields(include("_id"), include(listKeys)));
			collection.forEach(new Block<Document>() {
				public void apply(final Document document) {
					empresas.add(empresaToObject(document));
				}
			});
			return empresas;
		} finally

		{
			mongoClient.close();
		}
	}
}