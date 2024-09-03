package pl.dom.model;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.reactive.GenericReactiveTransaction;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@Component  // sessionFactory hibernate z xml psuje jdbc stad wylacznie beana
public class OsobaServiceTx {

	@Autowired
	OsobaRepo osobaRepo;
	
	private final TransactionTemplate tx;
	
	public OsobaServiceTx(PlatformTransactionManager transactionManager) {
		this.tx= new TransactionTemplate( transactionManager);
		
		this.tx.setIsolationLevel(TransactionDefinition.ISOLATION_READ_UNCOMMITTED);
		this.tx.setPropagationBehavior(0);
		this.tx.setTimeout(30);
	}

	public void serviceProgramaticallyTransactionmanager() {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("mojaTranzakcaj");
//		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = tx.getTransactionManager().getTransaction(def); 
		try {
			Osoba oso = new Osoba();
			oso.setId(61);
			oso.setImie(null);
			oso.setNazwisko("hei");
			oso.setNumer(55555);
			osobaRepo.save(oso);
//			throw new Exception();
			tx.getTransactionManager().commit(status);
		}catch(Exception err) {
			tx.getTransactionManager().rollback(status);
			System.out.println("error called  -> rollback !!");
		}
	}
	
	public Object serviceMethod() {
		Object obj =  tx.execute(new TransactionCallback<Object>() {
			public Object doInTransaction(TransactionStatus status) {
				
				Optional<Osoba> osoba = osobaRepo.findById(2);
				System.out.println("dziala!! " + osoba.get().getNazwisko());
				Osoba oso = new Osoba();
				oso.setId(58);
				oso.setImie(null);
				oso.setNazwisko("hei");
				oso.setNumer(55555);
				try {
					osobaRepo.save(oso);
				throw new Error();
				} catch(Error er) {
					status.setRollbackOnly(); // ustawia do rollbacka tranzakcje w Transactioncallback
				}
				
//				test try/catch
//				try {  
//					TransactionStatus ts = tx.getTransactionManager().getTransaction(tx);
//					tx.getTransactionManager().rollback(ts);
//				}
//				catch (UnexpectedRollbackException er ) {
//					System.out.println(er.getRootCause());
//				}
				
				return osoba.get();
			}
		});
		

		return obj;
	}
	
}
