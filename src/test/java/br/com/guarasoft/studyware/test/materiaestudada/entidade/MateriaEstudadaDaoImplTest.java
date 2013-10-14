/**
 * 
 */
package br.com.guarasoft.studyware.test.materiaestudada.entidade;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.joda.time.Duration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.guarasoft.concursos.infra.dao.AbstractDao;
import br.com.guarasoft.concursos.infra.dao.Entidade;
import br.com.guarasoft.conteudoprogramatico.banca.persistence.Banca;
import br.com.guarasoft.conteudoprogramatico.concurso.entidade.Concurso;
import br.com.guarasoft.conteudoprogramatico.concurso.persistence.ConcursoRepository;
import br.com.guarasoft.conteudoprogramatico.concursomateria.entidade.ConcursoMateria;
import br.com.guarasoft.conteudoprogramatico.concursomateria.persistence.ConcursoMateriaRepository;
import br.com.guarasoft.conteudoprogramatico.concursomateriaestudada.ConcursoMateriaEstudada;
import br.com.guarasoft.conteudoprogramatico.concursomateriaestudada.ConcursoMateriaEstudadaRepository;
import br.com.guarasoft.conteudoprogramatico.materia.persistence.Materia;
import br.com.guarasoft.conteudoprogramatico.materiaestudada.entidade.MateriaEstudada;
import br.com.guarasoft.conteudoprogramatico.materiaestudada.persistence.MateriaEstudadaDao;
import br.com.guarasoft.conteudoprogramatico.materiaestudada.persistence.MateriaEstudadaDaoImpl;
import br.com.guarasoft.conteudoprogramatico.orgao.persistence.Orgao;
import br.com.guarasoft.conteudoprogramatico.orgao.persistence.OrgaoRepository;

/**
 * @author guara
 * 
 */
@RunWith(Arquillian.class)
public class MateriaEstudadaDaoImplTest {
	
	private final Logger logger = LoggerFactory.getLogger(MateriaEstudadaDaoImplTest.class);

	@Deployment
	public static Archive<?> createDeployment() {
		MavenDependencyResolver resolver = DependencyResolvers.use(
				MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");

		WebArchive war = ShrinkWrap
				.create(WebArchive.class, "studyware.war")
				.addAsLibraries(
						resolver.artifact("joda-time:joda-time:2.2")
								.resolveAsFiles())
				.addAsLibraries(
						resolver.artifact("postgresql:postgresql:9.1-901-1.jdbc4")
								.resolveAsFiles())
				.addClass(AbstractDao.class)
				.addClass(Entidade.class)
				.addClass(MateriaEstudada.class)
				.addClass(ConcursoMateria.class)
				.addClass(ConcursoMateriaRepository.class)
				.addClass(ConcursoMateriaEstudada.class)
				.addClass(ConcursoMateriaEstudadaRepository.class)
				.addClass(ConcursoRepository.class)
				.addClass(Materia.class)
				.addClass(Orgao.class)
				.addClass(OrgaoRepository.class)
				.addClass(Banca.class)
				.addClass(Concurso.class)
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml")
				// .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"))
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		System.out.println(war.toString(true));
		return war;
	}

	@Inject
	private MateriaEstudadaDao materiaEstudadaDao;
	@Resource
	private UserTransaction userTransaction;

	/**
	 * Test method for {@link MateriaEstudadaDaoImpl#persist(MateriaEstudada)}.
	 */
	@Test
	public void testPersist() {
		ConcursoMateria concursoMateria = new ConcursoMateria();
		concursoMateria.setId(1001L);
		MateriaEstudada materiaEstudada = new MateriaEstudada();
		materiaEstudada.setConcursoMateria(concursoMateria);
		materiaEstudada.setDataHoraEstudo(new Date());
		materiaEstudada.setTempoEstudado(new Duration(1000L));
		materiaEstudada.setObservacao("Teste");

		try {
			userTransaction.begin();
			materiaEstudadaDao.persist(materiaEstudada);
			userTransaction.commit();
		} catch (SecurityException | IllegalStateException
				| NotSupportedException | SystemException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			e.printStackTrace();
		}
		logger.debug("\n\n\n\n" + materiaEstudada.getId() + "\n\n\n\n");
		assertNotNull(materiaEstudada.getId());
	}

}
