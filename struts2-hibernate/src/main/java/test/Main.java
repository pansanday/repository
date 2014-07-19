package test;

import model.Book;
import dao.BookDao;

public class Main {
	public static void main(String[] args) {
		BookDao dao = new BookDao();
		Book book = new Book();
		book.setAuto_id(17);
		book.setAuthor("作者");
		book.setPrice(100);
		book.setPublish("北京大学出版社");
		book.setTitle("屌丝加油");
//		dao.saveBook(book);
		dao.updateBook(book);
	}
}
