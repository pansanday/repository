package test;

import model.Book;
import dao.BookDao;

public class Main {
	public static void main(String[] args) {
		BookDao dao = new BookDao();
		Book book = new Book();
		book.setAuthor("����");
		book.setPrice(100);
		book.setPublish("������ѧ������");
		book.setTitle("��˿����");
		dao.saveBook(book);
	}
}
