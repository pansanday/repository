package dao;

import java.util.List;
import model.Book;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class BookDao {

	private Session session = null;

	public BookDao() {
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		this.session = sessionFactory.openSession();
	}

	public void saveBook(Book book) {
		Transaction tran = session.beginTransaction();
		this.session.save(book);
		tran.commit();
		this.session.close();
	}

	public void delBook(Book book) {
		Transaction tran = session.beginTransaction();
		this.session.delete(book);
		tran.commit();
		session.close();
	}

	public void delBookById(String ids) {
		Transaction tran = session.beginTransaction();
		String hql = "DELETE BOOK WHERE auto_id in(?)";
		Query query = session.createQuery(hql);
		query.setString(0, ids);
		query.executeUpdate();
		tran.commit();
		session.close();
	}

	public void updateBook(Book book) {
		Transaction tran = session.beginTransaction();
		System.out.println("auto_id is: "+book.getAuto_id());
		session.update(book);
		tran.commit();
		session.close();
	}

	public List getBookList() {
		List bookList = null;
		String hql = "FROM BOOK AS B";
		Query query = session.createQuery(hql);
		bookList = query.list();
		return bookList;
	}

	public Book getBook(String auto_id) {
		Book book = new Book();
		String hql = "FROM Book as b where b.auto_id=:auto_id";
		Query query = session.createQuery(hql);
		query.setString("auto_id", auto_id);
		book = (Book) query.uniqueResult();
		return book;
	}

}
