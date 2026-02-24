package ru.netology.javaqa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {

    @Test
    public void shouldRemoveExistingProduct() {
        ShopRepository repo = new ShopRepository();
        Product p1 = new Product(1, "Книга", 100);
        Product p2 = new Product(2, "Ноутбук", 20_000);
        Product p3 = new Product(3, "Телевизор", 30_000);

        repo.add(p1);
        repo.add(p2);
        repo.add(p3);

        repo.removeById(2);

        Product[] expected = {p1, p3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowWhenRemoveNotExisting() {
        ShopRepository repo = new ShopRepository();
        Product p1 = new Product(1, "Книга", 100);
        Product p2 = new Product(2, "Ноутбук", 20_000);

        repo.add(p1);
        repo.add(p2);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(999);
        });
    }

    @Test
    public void shouldAddProductIfIdNotExists() {
        ShopRepository repo = new ShopRepository();
        Product p1 = new Product(1, "Книга", 100);
        Product p2 = new Product(2, "Ноутбук", 20_000);

        repo.add(p1);
        repo.add(p2);

        Product[] expected = {p1, p2};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowWhenAddProductWithExistingId() {
        ShopRepository repo = new ShopRepository();
        Product p1 = new Product(1, "Книга1", 100);
        Product p2 = new Product(2, "Ноутбук", 20_000);
        Product p3 = new Product(1, "Книга2", 150);

        repo.add(p1);
        repo.add(p2);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(p3);
        });
    }
}

