CREATE TABLE Usserrs(

user_id INT AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(255) NOT NULL,
last_name VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL UNIQUE,
password_hash VARCHAR(255) NOT NULL,
phone_number VARCHAR(15) NOT NULL,
create_on timestamp default current_timestamp,
update_on timestamp default current_timestamp on update current_timestamp
);

CREATE TABLE categories(
category_id INT AUTO_INCREMENT PRIMARY KEY,
description text
);

CREATE TABLE Productss(
product_id INT AUTO_INCREMENT PRIMARY KEY,
title VARCHAR(255) NOT NULL,
description text,
price decimal(10,2) NOT NULL,
stock INT NOT NULL DEFAULT 0,
category_id INT,
images text,
created_on timestamp default current_timestamp,
updated_on timestamp default current_timestamp on update current_timestamp,
foreign key(category_id) references categories (category_id) on delete set null
);
CREATE TABLE carts(
cart_id INT AUTO_INCREMENT PRIMARY KEY,
user_id INT,
foreign key(user_id) references Usserrs(user_id) 
);
CREATE TABLE Cart_items(

card_item_id INT AUTO_INCREMENT PRIMARY KEY,
cart_id INT NOT NULL,
product_id INT NOT NULL,
quantity INT NOT NULL,
created_on timestamp default current_timestamp,
updated_on timestamp default current_timestamp on update current_timestamp,
foreign key(cart_id) references carts(cart_id) on delete cascade,
foreign key(product_id) references Productss(product_id) on delete cascade

);

create table wishlists(
wishlist_id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255),
created_on timestamp default current_timestamp,
updated_on timestamp default current_timestamp on update current_timestamp
);

CREATE TABLE wishlist_items (
    wishlist_item_id INT AUTO_INCREMENT PRIMARY KEY,
    wishlist_id INT NOT NULL,
    product_id INT NOT NULL,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (wishlist_id) REFERENCES wishlists(wishlist_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES Productss(product_id) ON DELETE CASCADE
);

create table addresses(

address_id INT auto_increment primary key,
user_id int not null,
address_line1 varchar(255),
address_line2 varchar(255),
city varchar(255),
state varchar(255),
pincode varchar(255),
country varchar(100),
latitude decimal(9,6),
longitude decimal(9,6),
is_default boolean default false,
address_type enum('HOME','Shipping','Billing','Office0,','Others'),
  created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES usserrs(user_id) ON DELETE CASCADE
);
CREATE TABLE Orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    total_amount DECIMAL(10,2),
    order_status ENUM('Pending', 'Processing', 'Completed', 'Cancelled'),
    billing_address_id INT,
    shipping_address_id INT,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Usserrs(user_id) ON DELETE CASCADE,
    FOREIGN KEY (billing_address_id) REFERENCES addresses(address_id) ON DELETE CASCADE,
    FOREIGN KEY (shipping_address_id) REFERENCES addresses(address_id) ON DELETE CASCADE
);

CREATE TABLE Order_items (
    Order_item_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    price DECIMAL(10,2),
    quantity INT,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (order_id) REFERENCES Orders(order_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES Productss(product_id) ON DELETE CASCADE
);
CREATE TABLE coupons (
    coupon_id INT AUTO_INCREMENT PRIMARY KEY,
    coupon_code VARCHAR(25) NOT NULL,
    offer_type ENUM('Value', 'Percentage') NOT NULL,
    offer_value INT NOT NULL,
    valid_till DATETIME NOT NULL,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

create table reviews(

review_id int auto_increment primary key,
user_id int,
product_id int,
order_id int,
review_text varchar(255),
images_path varchar(255),
videos_path varchar(255),
is_verified boolean default false,
is_deleted boolean default false,
created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (order_id) REFERENCES Orders(order_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES Productss(product_id) ON DELETE CASCADE,
 FOREIGN KEY (user_id) REFERENCES Usserrs(user_id) ON DELETE CASCADE




);

