package com.javaguru.shoppinglist;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ShoppingListApplication {

    public static void main(String[] args) {
        Map<Long, Product> productRepository = new HashMap<>();
        Long productIdSequence = 0L;
        Scanner scanner = new Scanner(System.in); //no need to start every time in the cycle inside
        while (true) {
            try {
                System.out.println("1. Create product");
                System.out.println("2. Find product by id");
                System.out.println("3. Exit");
                Integer userInput = Integer.valueOf(scanner.nextLine());
                switch (userInput) {
                    case 1:
                        System.out.println("Enter product category: "); //using ' ' after ':'
                        String category = scanner.nextLine();

                        System.out.println("Enter product name: ");
                        String name = scanner.nextLine();
                        if (name.length() < 3 | name.length() > 32) {
                            System.out.println("Input Error! Name should be in diapason from 3 to 32 symbols");
                            scanner.close();
                            return;
                        }

                        System.out.println("Enter product price: ");
                        BigDecimal price = new BigDecimal(scanner.nextLine());
                        if (price.compareTo(BigDecimal.ZERO) < 0) {
                            System.out.println("Input Error! Price should not be less than 0.");
                            scanner.close();
                            return;
                        }

                        System.out.println("Enter product discount: ");
                        BigDecimal discountForPrice = new BigDecimal(scanner.nextLine());
                        if (discountForPrice.compareTo(price) > 0) {
                            System.out.println("Discount should not be bigger than price.");
                            scanner.close();
                            return;
                        }

                        System.out.println("Enter product description: ");
                        String description = scanner.nextLine();

                        Product product = new Product();
                        product.setCategory(category);
                        product.setName(name);
                        product.setPrice(price);
                        product.setDiscountForPrice(discountForPrice);
                        product.setDescription(description);
                        product.setId(productIdSequence);

                        productRepository.put(productIdSequence, product);
                        productIdSequence++;
                        System.out.println("Result: " + product.getId());
                        break;
                    case 2:
                        System.out.println("Enter product id: ");
                        long id = scanner.nextLong();
                        Product findProductResult = productRepository.get(id);
                        System.out.println(findProductResult);
                        break;
                    case 3:
                        return;
                }
            } catch (Exception e) {
                System.out.println("Error! Please try again.");
                e.printStackTrace();
            }
        }
    }
}
