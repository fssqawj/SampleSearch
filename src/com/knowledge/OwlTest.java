package com.knowledge;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLOntologyDocumentSource;
import org.semanticweb.owlapi.io.OWLOntologyDocumentTarget;
import org.semanticweb.owlapi.io.OWLParserFactory;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.ImpendingOWLOntologyChangeListener;
import org.semanticweb.owlapi.model.MissingImportListener;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLDocumentFormat;
import org.semanticweb.owlapi.model.OWLImportsDeclaration;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyChange;
import org.semanticweb.owlapi.model.OWLOntologyChangeBroadcastStrategy;
import org.semanticweb.owlapi.model.OWLOntologyChangeListener;
import org.semanticweb.owlapi.model.OWLOntologyChangeProgressListener;
import org.semanticweb.owlapi.model.OWLOntologyChangesVetoedListener;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyFactory;
import org.semanticweb.owlapi.model.OWLOntologyID;
import org.semanticweb.owlapi.model.OWLOntologyIRIMapper;
import org.semanticweb.owlapi.model.OWLOntologyLoaderConfiguration;
import org.semanticweb.owlapi.model.OWLOntologyLoaderListener;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.OWLStorerFactory;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.model.parameters.ChangeApplied;
import org.semanticweb.owlapi.model.parameters.OntologyCopy;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.util.PriorityCollection;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

import com.google.inject.Provider;
import com.google.inject.spi.StaticInjectionRequest;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class OwlTest {
		static String base = "http://www.semanticweb.org/fssqawj/ontologies/2015/2/untitled-ontology-2";
		
		static Map<String, Map<String, String>> wi = new HashMap<String,Map<String, String>>();
		
		static Map<String, String>cMap = new HashMap<String, String>();
		
		static OWLOntologyManager m = OWLManager.createOWLOntologyManager();
		
		/*static OWLDataFactory dataFactory = m.getOWLDataFactory();
		
		static IRI ontologyIRI = IRI.create("http://www.semanticweb.org/wiki/ontologies/2015/2/untitled-ontology-2");
		
		
		
		static PrefixManager pm = new DefaultPrefixManager(null, null,
				ontologyIRI.toString());
		
		static OWLClass provinceClass = dataFactory.getOWLClass(IRI.create(base + "#province"));
		
		static OWLClass cityClass = dataFactory.getOWLClass(IRI.create(base + "#city"));
		
		static OWLClass siteClass = dataFactory.getOWLClass(IRI.create(base + "#site"));
		
		static OWLClass typeClass = dataFactory.getOWLClass(IRI.create(base + "#type"));
		
		
		
		static OWLObjectProperty locateProvince = dataFactory.getOWLObjectProperty(IRI.create(base + "#locateProvince"));
		
		static OWLObjectProperty locateCity = dataFactory.getOWLObjectProperty(IRI.create(base + "#locateCity"));
		
		static OWLObjectProperty cityInpro = dataFactory.getOWLObjectProperty(IRI.create(base + "#cityInpro"));
		
		static OWLObjectProperty sitehastype = dataFactory.getOWLObjectProperty(IRI.create(base + "#sitehastype"));
		
		static OWLDataProperty hasName = dataFactory.getOWLDataProperty(IRI.create(base + "#hasName"));
		
		static OWLDataProperty hasScore = dataFactory.getOWLDataProperty(IRI.create(base + "#hasScore"));
		
		static OWLDataProperty hasAddr = dataFactory.getOWLDataProperty(IRI.create(base + "#hasAddr"));
		
		static OWLDataProperty hasSummary = dataFactory.getOWLDataProperty(IRI.create(base + "#hasSummary"));
		
		static OWLDataProperty hasURL = dataFactory.getOWLDataProperty(IRI.create(base + "#hasURL"));
		
		static OWLDataProperty hasTest = dataFactory.getOWLDataProperty(IRI.create(base + "#hasTest"));
		
		static OWLDataProperty hasWD = dataFactory.getOWLDataProperty(IRI.create(base + "#hasWD"));
		
		static OWLDataProperty hasJD = dataFactory.getOWLDataProperty(IRI.create(base + "#hasJD"));
		
		static OWLDataProperty hasMint = dataFactory.getOWLDataProperty(IRI.create(base + "#hasMint"));
		
		static OWLDataProperty hasMaxt = dataFactory.getOWLDataProperty(IRI.create(base + "#hasMaxt"));
		
		static OWLDataProperty hasTel = dataFactory.getOWLDataProperty(IRI.create(base + "#hasTel"));
		
		static OWLDataProperty hasWebsite = dataFactory.getOWLDataProperty(IRI.create(base + "#hasWebsite"));
		
		static OWLDataProperty hasOpentime = dataFactory.getOWLDataProperty(IRI.create(base + "#hasOpentime"));
		
		static OWLDataProperty hasTicket = dataFactory.getOWLDataProperty(IRI.create(base + "#hasTicket"));
		
		
		static OWLDatatype stringDatatype = dataFactory.getOWLDatatype(OWL2Datatype.XSD_STRING.getIRI());
		
		static OWLDatatype doubleDatatype = dataFactory.getOWLDatatype(OWL2Datatype.XSD_DOUBLE.getIRI());
		
		static ReadoneFile reof = new ReadoneFile();
		*/
		/*
		public static void main(String[] args) throws OWLOntologyCreationException, OWLOntologyStorageException, IOException{
			//OWLOntology ont = m.createOntology(ontologyIRI);
			String path = "/home/fssqawj/下载/information/";
			File file = new File(path);
			
			String[] filelist = file.list();
			
			//Map<String, String>cMap = new HashMap<String, String>();
			
			File outfile = new File("/home/fssqawj/sitexlwiki");
    		BufferedWriter writer = null;
    		writer = new BufferedWriter(new FileWriter(outfile));
    		
    		getInfo();
    		writer.write("实体名,所属分类,中文名,评分,地址,简介,URL,纬度,经度,省份,城市,类型,电话,官方网站,开放时间,门票信息,游玩时间,");
    		int cnt = 0;
    		System.out.println("cmapsize:" + cMap.size());
    		for(String key : cMap.keySet()){
    			cnt ++;
    			System.out.println(key);
    			writer.write(key + (cnt == cMap.size() ? "\n" : ","));
    		}
    		cnt = 0;
			for(String key : filelist){
				File readfile = new File(path + "/" + key);
				String[] flist = readfile.list();
				for(String fname : flist){
					
					//solve(m, ont, readfile.getPath() + "/" + fname);
					//String[] tem = fname.split(".");
					//if(tem.length > 0)writer.write(tem[0] + "\n");
					//writer.write(key + " " + fname.substring(0, fname.indexOf(".")) + "\n");
					Map<String, String> tem = reof.read(readfile.getPath() + "/" + fname);
					writer.write(tem.get("ChName") + "," + "景点,");
					if(tem.get("ChName") != null)writer.write(tem.get("ChName").replace(",", "，").trim() + ",");
					if(tem.get("score") != null)writer.write(tem.get("score").trim() + ",");
					if(tem.get("addr") != null)writer.write(tem.get("addr").replace(",", "，").trim() + ",");
					if(tem.get("summary") != null)writer.write(tem.get("summary").replace(",", "，").trim() + ",");
					if(tem.get("URL") != null)writer.write(tem.get("URL").replace(",", "，").trim() + ",");
					if(tem.get("X") != null)writer.write(tem.get("X").trim() + ",");
					if(tem.get("Y") != null)writer.write(tem.get("Y").trim() + ",");
					if(tem.get("Country") != null)writer.write(tem.get("Country").replace(",", "，").trim() + ",");
					if(tem.get("City") != null)	writer.write(tem.get("City").replace(",", "，").trim() + ",");
					if(tem.get("类型") != null)writer.write(tem.get("类型").replace(",", "，").trim() + ",");
					else writer.write("null,");
					if(tem.get("电话") != null)writer.write(tem.get("电话").replace(",", "，").trim() + ",");
					else writer.write("null,");
					if(tem.get("官方网站") != null)writer.write(tem.get("官方网站").replace(",", "，").trim() + ",");
					else writer.write("null,");
					if(tem.get("开放时间") != null)writer.write(tem.get("开放时间").replace(",", "，").trim() + ",");
					else writer.write("null,");
					if(tem.get("门票信息") != null)writer.write(tem.get("门票信息").replace(",", "，").trim() + ",");
					else writer.write("null,");
					if(tem.get("游玩时间") != null)writer.write(tem.get("游玩时间").replace(",", "，").trim());
					else writer.write("null");
					System.out.println(cnt ++);
					if(!wi.containsKey(tem.get("ChName"))){
						for(String fur : cMap.keySet()){
							writer.write(",null");
						}
					}
					else {
						Map<String, String> t = wi.get(tem.get("ChName"));
						for(String fur : cMap.keySet()){
							if(t.containsKey(fur)){
								writer.write("," + t.get(fur));
							}
							else writer.write(",null");
						}
					}
					writer.write("\n");
				}
			}
			writer.close();
			//m.saveOntology(ont,IRI.create("file:/home/fssqawj/example.owl"));
			
			System.out.println("done!");
		}
		*/
		/*
		public static void main(String[] args) throws OWLOntologyCreationException, OWLOntologyStorageException, IOException{
			String path = "/home/fssqawj/webmagic/";
			OWLOntology ont = m.loadOntologyFromOntologyDocument(new File("/home/fssqawj/example.owl"));
			
			File file = new File(path);
			
			String[] filelist = file.list();
			
			Map<String, String>cMap = new HashMap<String, String>();
			
			//File outfile = new File("/home/fssqawj/sitexl");
    		//BufferedWriter writer = null;
    		//writer = new BufferedWriter(new FileWriter(outfile));
			System.out.println("pm" + pm.getDefaultPrefix());
    		for(String key : filelist){
				File readfile = new File(path + "/" + key);
				String[] flist = readfile.list();
				for(String fname : flist){
					solve(m, ont, readfile.getPath() + "/" + fname);
					//String[] tem = fname.split(".");
					//if(tem.length > 0)writer.write(tem[0] + "\n");
					//writer.write(key + " " + fname.substring(0, fname.indexOf(".")) + "\n");
				}
    		}
    		//writer.close();
    		System.out.println("here!");
			m.saveOntology(ont,IRI.create("file:/home/fssqawj/sitewiki.owl"));
			
			System.out.println("done!");
		}
		*/
		/*
		public static void getInfo() throws OWLOntologyCreationException, OWLOntologyStorageException, IOException{
			String path = "/home/fssqawj/webmagic/";
			//OWLOntology ont = m.loadOntologyFromOntologyDocument(new File("/home/fssqawj/example.owl"));
			
			File file = new File(path);
			
			String[] filelist = file.list();
			
			
			
			
			//File outfile = new File("/home/fssqawj/sitexl");
    		//BufferedWriter writer = null;
    		//writer = new BufferedWriter(new FileWriter(outfile));
			//System.out.println("pm" + pm.getDefaultPrefix());
    		for(String key : filelist){
				File readfile = new File(path + "/" + key);
				String[] flist = readfile.list();
				for(String fname : flist){
					//solve(m, ont, readfile.getPath() + "/" + fname);
					//String[] tem = fname.split(".");
					//if(tem.length > 0)writer.write(tem[0] + "\n");
					//writer.write(key + " " + fname.substring(0, fname.indexOf(".")) + "\n");
					Map<String, String> tem = reof.readx(readfile.getPath() + "/" + fname);
					wi.put(getSiteName(tem.get("url")), tem);
					for(String fur : tem.keySet()){
						if(fur == "" || fur == "\n")continue;
						if(fur.contains("（"))continue;
						if(fur.contains(" "))continue;
						if(fur.contains("*"))continue;
						cMap.put(fur, "true");
					}
					
				}
    		}
    		System.out.println("size:" + cMap.size());
    		//for(String key : cMap.keySet())System.out.println(key);
    		//writer.close();
    		//System.out.println("here!");
			//m.saveOntology(ont,IRI.create("file:/home/fssqawj/sitewiki.owl"));
			
			System.out.println("done!");
		}
		
		*/
		public static List<String> doQuery(String queryString) throws OWLOntologyCreationException, OWLOntologyStorageException, IOException{
			OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);  
			  
	       
			//if(queryString == null)return null;
			queryString = GetSparql.get(queryString);
			if(queryString == null)return null;
			System.out.println("SPARQL:" + queryString);
	        ontModel.read("file:/home/fssqawj/sitewiki_small.owl");  
	        //String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + 
	        //	"PREFIX owl: <http://www.w3.org/2002/07/owl#>" + 
	        //		"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" + 
	        //		"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" + 
	        //		"PREFIX my: <http://www.semanticweb.org/fssqawj/ontologies/2015/2/untitled-ontology-2#>" + 
	        //		"SELECT ?subject WHERE { ?subject my:locateCity ?o. my:一线天 my:locateCity ?o.} limit 10";  
	  
	        Query query = QueryFactory.create(queryString);  
	        QueryExecution qe = QueryExecutionFactory.create(query, ontModel);  
	        ResultSet results = qe.execSelect();  
	        List<String> res = new ArrayList<String>();
	        while (results.hasNext()) {  
	            QuerySolution qs = results.next();  
	            res.add(qs.get("subject").toString());
	            System.out.println(qs.get("subject")); 
	            //System.out.println(qs.get("s")); 
	        }  
	        
	        // ResultSetFormatter.out(System.out, results, query);  
	        qe.close();  
	        System.out.println("done");
	        return res;
	        
	    }  
		/*
		public static void solve(OWLOntologyManager m,OWLOntology ont,String filename){
				Map<String, String> tem = reof.read(filename);

				String siteName = getSiteName(tem.get("url"));
				if(siteName == null || siteName == "")return;
				System.out.println("siteName:"+siteName);
				OWLNamedIndividual site = dataFactory.getOWLNamedIndividual(IRI.create(base + "#" + siteName));
				*/
				
				/*
				// Create a typed literal. We type the literal "51" with the datatype
				OWLLiteral literal = dataFactory.getOWLLiteral(tem.get("ChName"), stringDatatype);
				// Create the property assertion and add it to the ontology
				OWLAxiom ax = dataFactory.getOWLDataPropertyAssertionAxiom(hasName, site,literal);
				m.addAxiom(ont, ax);
				
				
				// Create a typed literal. We type the literal "51" with the datatype
				OWLLiteral literalsrc = dataFactory.getOWLLiteral(tem.get("score"), stringDatatype);
				// Create the property assertion and add it to the ontology
				OWLAxiom axsrc = dataFactory.getOWLDataPropertyAssertionAxiom(hasScore, site,literalsrc);
				m.addAxiom(ont, axsrc);
				
				OWLLiteral literaladdr = dataFactory.getOWLLiteral(tem.get("addr"), stringDatatype);
				OWLAxiom axaddr = dataFactory.getOWLDataPropertyAssertionAxiom(hasAddr, site,literaladdr);
				m.addAxiom(ont, axaddr);
				
				OWLLiteral literalsummary = dataFactory.getOWLLiteral(tem.get("summary"), stringDatatype);
				OWLAxiom axsummary = dataFactory.getOWLDataPropertyAssertionAxiom(hasSummary, site,literalsummary);
				m.addAxiom(ont, axsummary);
				
				OWLLiteral literalurl = dataFactory.getOWLLiteral(tem.get("URL"), stringDatatype);
				OWLAxiom axurl = dataFactory.getOWLDataPropertyAssertionAxiom(hasURL, site,literalurl);
				m.addAxiom(ont, axurl);
				
				OWLLiteral literalwd = dataFactory.getOWLLiteral(tem.get("X"), doubleDatatype);
				OWLAxiom axwd = dataFactory.getOWLDataPropertyAssertionAxiom(hasWD, site,literalwd);
				m.addAxiom(ont, axwd);
				
				OWLLiteral literaljd = dataFactory.getOWLLiteral(tem.get("Y"), doubleDatatype);
				OWLAxiom axujd = dataFactory.getOWLDataPropertyAssertionAxiom(hasJD, site,literaljd);
				m.addAxiom(ont, axujd);
				
				OWLLiteral literaltel = dataFactory.getOWLLiteral(tem.get("电话"), stringDatatype);
				OWLAxiom axutel = dataFactory.getOWLDataPropertyAssertionAxiom(hasTel, site,literaltel);
				m.addAxiom(ont, axutel);
				*/
				
				/*
				if(tem.get("ChName") != null)adddatapro(m,ont,tem.get("ChName"),hasName,site);
				if(tem.get("score") != null)adddatapro(m,ont,tem.get("score"),hasScore,site);
				if(tem.get("addr") != null)adddatapro(m,ont,tem.get("addr"),hasAddr,site);
				if(tem.get("summary") != null)adddatapro(m,ont,tem.get("summary"),hasSummary,site);
				if(tem.get("URL") != null)adddatapro(m,ont,tem.get("URL"),hasURL,site);
				if(tem.get("X") != null)adddatapro(m,ont,tem.get("X"),hasWD,site);
				if(tem.get("Y") != null)adddatapro(m,ont,tem.get("Y"),hasJD,site);
				if(tem.get("电话") != null)adddatapro(m,ont,tem.get("电话"),hasTel,site);
				if(tem.get("官方网站") != null)adddatapro(m,ont,tem.get("官方网站"),hasWebsite,site);
				if(tem.get("开放时间") != null)adddatapro(m,ont,tem.get("开放时间"),hasOpentime,site);
				if(tem.get("门票信息") != null)adddatapro(m,ont,tem.get("门票信息"),hasTicket,site);
				if(tem.get("游玩时间") != null)adddatapro(m,ont,getmint(tem.get("游玩时间")),hasMint,site);
				if(tem.get("游玩时间") != null)adddatapro(m,ont,getmaxt(tem.get("游玩时间")),hasMaxt,site);
				*/
				
				//OWLDataProperty t = dataFactory.getOWLDataProperty(,)
				
		
		/*
				for(String key : tem.keySet()){
					if(key == null || key.contains("addr")||key.contains("url")||tem.get(key) == ""||tem.get(key) == null||key == "")continue;
					//System.out.println("key:" + key);
					if(key.contains("（"))continue;
					if(key.contains(" "))continue;
					if(key.contains("*"))continue;
					key.replace("*", "");
					hasTest = dataFactory.getOWLDataProperty(IRI.create(base + "#" + key));
					//if(hasTest.getIRI().toString() == "http://www.semanticweb.org/fssqawj/ontologies/2015/2/untitled-ontology-2#")continue;
					String[] t = hasTest.getIRI().toString().split("#");
					if(t.length > 1 && t[1] != ""){
						System.out.println("keyrel:"+hasTest.getIRI().toString());
						adddatapro(m,ont,tem.get(key),hasTest,site);
					}
				}
				*/
				
				/*
				 * 
				 * 
				OWLIndividual province = dataFactory.getOWLNamedIndividual(IRI.create(base+ "#" + tem.get("Country")));
				OWLIndividual city = dataFactory.getOWLNamedIndividual(IRI.create(base+ "#" + tem.get("City")));
				OWLIndividual type = dataFactory.getOWLNamedIndividual(IRI.create(base+ "#" + tem.get("类型")));
				
				OWLObjectPropertyAssertionAxiom assertionpro = dataFactory.getOWLObjectPropertyAssertionAxiom(locateProvince, site, province);
				AddAxiom addAxiomChangepro = new AddAxiom(ont, assertionpro);
				m.applyChange(addAxiomChangepro);
				
				OWLObjectPropertyAssertionAxiom assertiontype = dataFactory.getOWLObjectPropertyAssertionAxiom(sitehastype, site, type);
				AddAxiom addAxiomChangetype = new AddAxiom(ont, assertiontype);
				m.applyChange(addAxiomChangetype);
				
				OWLObjectPropertyAssertionAxiom assertioncity = dataFactory.getOWLObjectPropertyAssertionAxiom(locateCity, site, city);
				AddAxiom addAxiomChangecity = new AddAxiom(ont, assertioncity);
				m.applyChange(addAxiomChangecity);
				
				OWLObjectPropertyAssertionAxiom assertioncp = dataFactory.getOWLObjectPropertyAssertionAxiom(cityInpro, city, province);
				AddAxiom addAxiomChangecp = new AddAxiom(ont, assertioncp);
				m.applyChange(addAxiomChangecp);
				*/
				
				
				/*
				
				OWLClassAssertionAxiom px = dataFactory.getOWLClassAssertionAxiom(provinceClass, matthew);
						// Add this axiom to our ontology. We can use a short cut method -
						// instead of creating the AddAxiom change ourselves, it will be created
						// automatically and the change applied
				m.addAxiom(ont, px);
				
				OWLClassAssertionAxiom cx = dataFactory.getOWLClassAssertionAxiom(cityClass, matthew);
				// Add this axiom to our ontology. We can use a short cut method -
				// instead of creating the AddAxiom change ourselves, it will be created
				// automatically and the change applied
				m.addAxiom(ont, cx);
				*/
				
				
				/*
				 * 
				 * 
				 * 

				OWLClassAssertionAxiom sx = dataFactory.getOWLClassAssertionAxiom(siteClass, site);
				// Add this axiom to our ontology. We can use a short cut method -
				// instead of creating the AddAxiom change ourselves, it will be created
				// automatically and the change applied
				m.addAxiom(ont, sx);
			
				OWLClassAssertionAxiom px = dataFactory.getOWLClassAssertionAxiom(provinceClass, province);
				m.addAxiom(ont, px);
				
				OWLClassAssertionAxiom cx = dataFactory.getOWLClassAssertionAxiom(cityClass, city);
				m.addAxiom(ont, cx);
				
				OWLClassAssertionAxiom tx = dataFactory.getOWLClassAssertionAxiom(typeClass, type);
				m.addAxiom(ont, tx);
				*/
			//}
			
		//}
		/*
		public static String getSiteName(String str){
			String[] tem = str.split("/");
			System.out.println(str);
			return tem[tem.length - 1];
		}
		
		public static void adddatapro(OWLOntologyManager m,OWLOntology ont,String value,OWLDataProperty ODP,OWLNamedIndividual site){
			OWLLiteral literal = dataFactory.getOWLLiteral(value, stringDatatype);
			// Create the property assertion and add it to the ontology
			OWLAxiom ax = dataFactory.getOWLDataPropertyAssertionAxiom(ODP, site,literal);
			m.addAxiom(ont, ax);
		}
		public static void adddataprox(OWLOntologyManager m,OWLOntology ont,String value,OWLDataProperty ODP,OWLNamedIndividual site){
			OWLLiteral literal = dataFactory.getOWLLiteral(value, doubleDatatype);
			// Create the property assertion and add it to the ontology
			OWLAxiom ax = dataFactory.getOWLDataPropertyAssertionAxiom(ODP, site,literal);
			m.addAxiom(ont, ax);
		}
		public static String getmint(String str){
			String res = "";
			if(str.contains("分钟")){
				return "0.1";
			}
			if(str.contains("-")){
				for(int i = 0;i < str.length();i ++){
					if(str.charAt(i) == '-')break;
					if(judge(str.charAt(i))){
						res += str.charAt(i);
					}	
				}
				return res;
			}
			else {
				for(int i = 0;i < str.length();i ++){
					if(judge(str.charAt(i))){
						res += str.charAt(i);
					}	
				}
				return res;
			}
		}
		public static String getmaxt(String str){
			String res = "";
			if(str.contains("分钟")){
				return "0.8";
			}
			if(str.contains("-")){
				int t = 0;
				for(int i = 0;i < str.length();i ++){
					if(str.charAt(i) == '-')t = 1;
					if(t == 1 && judge(str.charAt(i))){
						res += str.charAt(i);
					}	
				}
				return res;
			}
			else {
				for(int i = 0;i < str.length();i ++){
					if(judge(str.charAt(i))){
						res += str.charAt(i);
					}	
				}
				return res;
			}
		}
		public static Boolean judge(char c){
			return c == '.' || c >= '0' && c <= '9';
		}
		*/
}
