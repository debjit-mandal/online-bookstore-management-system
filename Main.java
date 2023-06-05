import java.io.*;
import java.util.*;
class Book {
  private String title;
  private String author;
  private double price;

  public Book(String title, String author, double price) {
    this.title = title;
    this.author = author;
    this.price = price;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
}

class Bookstore {
  private List<Book> books;

  public Bookstore() {
    books = new ArrayList<>();
  }

  public void addBook(Book book) {
    books.add(book);
  }

  public void removeBook(Book book) {
    books.remove(book);
  }

  public void displayBooks() {
    if (books.isEmpty()) {
      System.out.println("No books available in the bookstore.");
    } else {
      for (Book book : books) {
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Price: ₹" + book.getPrice());
        System.out.println("-------------------------");
      }
    }
  }

  public Book findBookByTitle(String title) {
    for (Book book : books) {
      if (book.getTitle().equalsIgnoreCase(title)) {
        return book;
      }
    }
    return null;
  }

  public void sortBooksByTitle() {
    Collections.sort(books, Comparator.comparing(Book::getTitle));
  }

  public void sortBooksByAuthor() {
    Collections.sort(books, Comparator.comparing(Book::getAuthor));
  }

  public void sortBooksByPrice() {
    Collections.sort(books, Comparator.comparingDouble(Book::getPrice));
  }

  public void exportBooksToCSV(String filename) {
    try (FileWriter writer = new FileWriter(filename)) {
      writer.write("Title,Author,Price\n");
      for (Book book : books) {
        writer.write(book.getTitle() + "," + book.getAuthor() + "," + book.getPrice() + "\n");
      }
      System.out.println("Book data exported to " + filename + " successfully!");
    } catch (IOException e) {
      System.out.println("An error occurred while exporting the book data to " + filename + ".");
    }
  }
}

public class Main {
  public static void main(String[] args) {
    Bookstore bookstore = new Bookstore();
    Scanner scanner = new Scanner(System.in);
    int choice = 0;

    while (choice != 7) {
      System.out.println("===== Online Bookstore Management System =====");
      System.out.println("1. Add a Book");
      System.out.println("2. Remove a Book");
      System.out.println("3. Search for a Book");
      System.out.println("4. Update Book Price");
      System.out.println("5. Sort Books");
      System.out.println("6. Export Books to CSV");
      System.out.println("7. Exit");
      System.out.print("Enter your choice: ");

      try {
        choice = scanner.nextInt();

        switch (choice) {
          case 1:
            System.out.print("Enter book title: ");
            scanner.nextLine(); // Consume newline
            String title = scanner.nextLine();
            System.out.print("Enter author name: ");
            String author = scanner.nextLine();
            System.out.print("Enter book price: ");
            double price = scanner.nextDouble();
            Book newBook = new Book(title, author, price);
            bookstore.addBook(newBook);
            System.out.println("Book added successfully!");
            break;
          case 2:
            System.out.print("Enter book title to remove: ");
            scanner.nextLine(); // Consume newline
            String bookTitle = scanner.nextLine();
            Book bookToRemove = bookstore.findBookByTitle(bookTitle);
            if (bookToRemove != null) {
              bookstore.removeBook(bookToRemove);
              System.out.println("Book removed successfully!");
            } else {
              System.out.println("Book not found!");
            }
            break;
          case 3:
            System.out.print("Enter book title to search: ");
            scanner.nextLine(); // Consume newline
            String searchTitle = scanner.nextLine();
            Book foundBook = bookstore.findBookByTitle(searchTitle);
            if (foundBook != null) {
              System.out.println("Book found!");
              System.out.println("Title: " + foundBook.getTitle());
              System.out.println("Author: " + foundBook.getAuthor());
              System.out.println("Price: ₹" + foundBook.getPrice());
            } else {
              System.out.println("Book not found!");
            }
            break;
          case 4:
            System.out.print("Enter book title to update price: ");
            scanner.nextLine(); // Consume newline
            String bookTitleToUpdate = scanner.nextLine();
            Book bookToUpdate = bookstore.findBookByTitle(bookTitleToUpdate);
            if (bookToUpdate != null) {
              System.out.print("Enter new price: ");
              double newPrice = scanner.nextDouble();
              bookToUpdate.setPrice(newPrice);
              System.out.println("Book price updated successfully!");
            } else {
              System.out.println("Book not found!");
            }
            break;
          case 5:
            System.out.println("===== Sort Books =====");
            System.out.println("1. Sort by Title");
            System.out.println("2. Sort by Author");
            System.out.println("3. Sort by Price");
            System.out.print("Enter your choice: ");
            int sortChoice = scanner.nextInt();
            switch (sortChoice) {
              case 1:
                bookstore.sortBooksByTitle();
                System.out.println("Books sorted by Title.");
                break;
              case 2:
                bookstore.sortBooksByAuthor();
                System.out.println("Books sorted by Author.");
                break;
              case 3:
                bookstore.sortBooksByPrice();
                System.out.println("Books sorted by Price.");
                break;
              default:
                System.out.println("Invalid choice! Sorting cancelled.");
            }
            break;
          case 6:
            System.out.print("Enter filename to export book data (CSV format): ");
            scanner.nextLine(); // Consume newline
            String filename = scanner.nextLine();
            bookstore.exportBooksToCSV(filename);
            break;
          case 7:
            System.out.println("Exiting... Thank you!");
            break;
          default:
            System.out.println("Invalid choice! Please try again.");
        }
      } catch (NumberFormatException e) {
        System.out.println("Invalid input! Please enter a valid number.");
        scanner.nextLine(); // Clear the input
      } catch (Exception e) {
        System.out.println("An error occurred. Please try again.");
        scanner.nextLine(); // Clear the input
      }

      System.out.println();
    }

    scanner.close();
  }
}
