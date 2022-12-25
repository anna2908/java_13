import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {


    @Test
    public void initBookTest() {
        Book book = new Book(1, "Словарь", 500, "Петров");
        book.setId(2);
        int expectedId = 2;
        int actualId = book.getId();
        Assertions.assertEquals(expectedId, actualId);

        book.setName("Программирование JAVA");
        String expectedName = "Программирование JAVA";
        String actualName = book.getName();
        Assertions.assertEquals(expectedName, actualName);

        book.setPrice(400);
        int expectedPrice = 400;
        int actualPrice = book.getPrice();
        Assertions.assertEquals(expectedPrice, actualPrice);

        book.setAuthor("Иванов");
        String expectedAuthor = "Иванов";
        String actualAuthor = book.getAuthor();
        Assertions.assertEquals(expectedAuthor, actualAuthor);
    }

    @Test
    public void initSmartphoneTest() {
        Smartphone smart = new Smartphone(2, "Samsung A53", 25000, "Samsung");
        smart.setId(1);
        int expectedId = 1;
        int actualId = smart.getId();
        Assertions.assertEquals(expectedId, actualId);

        smart.setName("Samsung A51");
        String expectedName = "Samsung A51";
        String actualName = smart.getName();
        Assertions.assertEquals(expectedName, actualName);

        smart.setPrice(20000);
        int expectedPrice = 20000;
        int actualPrice = smart.getPrice();
        Assertions.assertEquals(expectedPrice, actualPrice);

        smart.setManufacturer("Samsung");
        String expectedManufacturer = "Samsung";
        String actualManufacturer = smart.getManufacturer();
        Assertions.assertEquals(expectedManufacturer, actualManufacturer);

    }


    @Test
    public void addTest() {
        ProductRepository repository = new ProductRepository();
        Product book = new Book(1, "Словарь", 500, "Петров");
        Product smart = new Smartphone(2, "Samsung A53", 25000, "Samsung");

        ProductManager manager = new ProductManager(repository);
        manager.add(book);
        manager.add(smart);

        Product[] expected = new Product[2];
        expected[0] = book;
        expected[1] = smart;
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByMoreThanOneTest() {
        ProductRepository repository = new ProductRepository();
        Product book1 = new Book(1, "Словарь", 500, "Петров");
        Product book2 = new Book(2, "Словарь", 400, "Иванов");
        Product smart = new Smartphone(3, "Samsung A53", 25000, "Samsung");

        ProductManager manager = new ProductManager(repository);
        manager.add(book1);
        manager.add(book2);
        manager.add(smart);

        Product[] expected = new Product[2];
        expected[0] = book1;
        expected[1] = book2;
        Product[] actual = manager.searchBy("Словарь");

        Assertions.assertArrayEquals(expected, actual);
    }


    public void searchByOneTest() {
        ProductRepository repository = new ProductRepository();
        Product book1 = new Book(1, "Словарь", 500, "Петров");
        Product smart = new Smartphone(3, "Samsung A53", 25000, "Samsung");

        ProductManager manager = new ProductManager(repository);
        manager.add(book1);
        manager.add(smart);

        Product[] expected = new Product[1];
        expected[0] = book1;
        Product[] actual = manager.searchBy("Словарь");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByZeroTest() {
        ProductRepository repository = new ProductRepository();
        Product book1 = new Book(1, "Словарь", 500, "Петров");
        Product smart = new Smartphone(3, "Samsung A53", 25000, "Samsung");

        ProductManager manager = new ProductManager(repository);
        manager.add(book1);
        manager.add(smart);

        Product[] expected = new Product[0];
        Product[] actual = manager.searchBy("Стол");

        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    public void removeByIdTest() {
        ProductRepository repository = new ProductRepository();
        Product book = new Book(1, "Словарь", 500, "Петров");
        Product smart = new Smartphone(2, "Samsung A53", 25000, "Samsung");
        repository.save(book);
        repository.save(smart);
        repository.removeById(1);
        Product[] expected = new Product[1];
        expected[0] = smart;
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void removeByIdExceptionTest() {
        ProductRepository repository = new ProductRepository();
        Product book = new Book(1, "Словарь", 500, "Петров");
        Product smart = new Smartphone(2, "Samsung A53", 25000, "Samsung");
        repository.save(book);
        repository.save(smart);
        Assertions.assertThrows(NotFoundException.class, () -> {
            repository.removeById(3);
        });
    }


    @Test
    public void saveTest() {
        ProductRepository repository = new ProductRepository();
        Product book = new Book(1, "Словарь", 500, "Петров");
        Product smart = new Smartphone(2, "Samsung A53", 25000, "Samsung");
        repository.save(book);
        repository.save(smart);
        Product[] expected = new Product[2];
        expected[0] = book;
        expected[1] = smart;
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    public void saveExceptionTest() {
        ProductRepository repository = new ProductRepository();
        Product book = new Book(1, "Словарь", 500, "Петров");
        Product smart = new Smartphone(1, "Samsung A53", 25000, "Samsung");
        repository.save(book);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repository.save(smart);
        });
    }
}
