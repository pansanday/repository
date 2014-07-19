package model;

public class Book {

	private Long auto_id;
	private String title;
	private String author;
	private String publish;
	private double price;

	public Long getAuto_id() {
		return auto_id;
	}

	public void setAuto_id(Long auto_id) {
		this.auto_id = auto_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublish() {
		return publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
