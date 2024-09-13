-- Create Users Table
CREATE TABLE IF NOT EXISTS users (
                       user_id SERIAL PRIMARY KEY,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       first_name VARCHAR(255),
                       last_name VARCHAR(255),
                       phone_number VARCHAR(15),
                       address TEXT,
                       role VARCHAR(50),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Categories Table
CREATE TABLE IF NOT EXISTS categories (
                            category_id SERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            description TEXT,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Products Table
CREATE TABLE IF NOT EXISTS products (
                          product_id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          price DECIMAL(10,2) NOT NULL,
                          quantity INT NOT NULL,
                          category_id INT REFERENCES categories(category_id),
                          brand_id INT,  -- Assuming there's a separate table for brands
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Orders Table with deferrable constraint
CREATE TABLE IF NOT EXISTS orders (
                        order_id SERIAL PRIMARY KEY,
                        user_id INT,
                        order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        total_amount DECIMAL(10,2) NOT NULL,
                        status VARCHAR(50),
                        shipping_address TEXT,
                        payment_id INT,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);

-- Create Payments Table with deferrable constraint
CREATE TABLE IF NOT EXISTS payments (
                          payment_id SERIAL PRIMARY KEY,
                          order_id INT,
                          payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          amount DECIMAL(10,2) NOT NULL,
                          payment_method VARCHAR(50),
                          status VARCHAR(50),
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);



-- Create Order Items Table
CREATE TABLE IF NOT EXISTS order_items (
                             order_item_id SERIAL PRIMARY KEY,
                             order_id INT REFERENCES orders(order_id),
                             product_id INT REFERENCES products(product_id),
                             quantity INT NOT NULL,
                             price DECIMAL(10,2) NOT NULL,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



-- Create Reviews Table
CREATE TABLE IF NOT EXISTS reviews (
                         review_id SERIAL PRIMARY KEY,
                         user_id INT REFERENCES users(user_id),
                         product_id INT REFERENCES products(product_id),
                         rating INT CHECK (rating >= 1 AND rating <= 5),
                         comment TEXT,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Cart Table
CREATE TABLE IF NOT EXISTS cart (
                      cart_id SERIAL PRIMARY KEY,
                      user_id INT REFERENCES users(user_id),
                      product_id INT REFERENCES products(product_id),
                      quantity INT NOT NULL,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Wishlist Table
CREATE TABLE IF NOT EXISTS wishlist (
                          wishlist_id SERIAL PRIMARY KEY,
                          user_id INT REFERENCES users(user_id),
                          product_id INT REFERENCES products(product_id),
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Addresses Table
CREATE TABLE IF NOT EXISTS addresses (
                           address_id SERIAL PRIMARY KEY,
                           user_id INT REFERENCES users(user_id),
                           address TEXT NOT NULL,
                           city VARCHAR(255) NOT NULL,
                           state VARCHAR(255),
                           postal_code VARCHAR(20),
                           country VARCHAR(255) NOT NULL,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Add foreign key constraint to Orders Table
ALTER TABLE orders
    ADD CONSTRAINT fk_orders_payments
        FOREIGN KEY (payment_id) REFERENCES payments(payment_id) DEFERRABLE INITIALLY DEFERRED;

-- Add foreign key constraint to Payments Table
ALTER TABLE payments
    ADD CONSTRAINT fk_payments_orders
        FOREIGN KEY (order_id) REFERENCES orders(order_id) DEFERRABLE INITIALLY DEFERRED;
