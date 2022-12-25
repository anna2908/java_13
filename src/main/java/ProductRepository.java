public class ProductRepository {
    private Product[] items = new Product[0];


    public void save(Product item) {
        if (this.findById(item.getId()) != null) {
            throw new AlreadyExistsException(
                    item.getId() + " уже существует!"
            );
        }
        Product[] temp = new Product[items.length + 1];
        for (int i = 0; i < items.length; i++) {
            temp[i] = items[i];
        }
        temp[temp.length - 1] = item;
        items = temp;
    }

    public void removeById(int id) {
        if (this.findById(id) == null) {
            throw new NotFoundException(
                    "ID " + id + " не найден!"
            );
        }
        Product[] tmp = new Product[items.length - 1];
        int copyToIndex = 0;
        for (Product item : items) {
            if (item.getId() != id) {
                tmp[copyToIndex] = item;
                copyToIndex++;
            }
        }
        items = tmp;
    }


    public Product[] findAll() {
        return items;
    }


    public Product findById(int id) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].id == id) {
                return items[i];
            }
        }
        return null;
    }
}
