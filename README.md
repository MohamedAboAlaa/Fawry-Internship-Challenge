# 🛒 E-Commerce System

A comprehensive Java-based e-commerce system demonstrating object-oriented programming principles, inheritance, polymorphism, and interface implementation.

## 📋 Table of Contents

- [🎯 Project Overview](#-project-overview)
- [🏗️ Project Structure](#️-project-structure)
- [✨ Key Features](#-key-features)
- [🚀 Getting Started](#-getting-started)
- [📦 Product Types](#-product-types)
- [👥 Customer Management](#-customer-management)
- [🛍️ Shopping Cart System](#️-shopping-cart-system)
- [💳 Checkout Process](#-checkout-process)
- [🚚 Shipping System](#-shipping-system)
- [🧪 Test Cases](#-test-cases)
- [📊 Usage Examples](#-usage-examples)

## 🎯 Project Overview

This e-commerce system supports multiple product types, customer management, shopping cart functionality, and comprehensive checkout processing with shipping calculations.

## 🏗️ Project Structure

```
📁 E-Commerce-System/
├── 📁 src/
│   ├── 📁 main/
│   │   └── 📄 Main.java                    # 🚀 Comprehensive demo application
│   ├── 📁 models/
│   │   ├── 📄 Product.java                 # 📦 Base product class
│   │   ├── 📄 ShippableProduct.java        # 🚚 Products requiring shipping
│   │   ├── 📄 ExpirableProduct.java        # ⏰ Products with expiration dates
│   │   └── 📄 Customer.java                # 👤 Customer with balance management
│   ├── 📁 cart/
│   │   └── 📄 Cart.java                    # 🛒 Shopping cart functionality
│   ├── 📁 services/
│   │   ├── 📄 CheckoutService.java         # 💳 Checkout processing logic
│   │   └── 📄 ShippingService.java         # 📮 Shipping calculations
│   ├── 📁 interfaces/
│   │   └── 📄 Shippable.java               # 🏷️ Interface for shippable items
│   └── 📁 tests/
│       ├── 📄 test1.java                   # ✅ Successful checkout test
│       ├── 📄 test2.java                   # ❌ Empty cart test
│       ├── 📄 test3.java                   # 💰 Insufficient balance test
│       ├── 📄 test4.java                   # 📦 Out of stock test
│       ├── 📄 test5.java                   # ⏰ Expired products test
│       ├── 📄 test6.java                   # 💻 Non-shippable items test
│       ├── 📄 test7.java                   # 🔄 Complex scenarios test
│       └── 📄 test8.java                   # 🎯 Exact challenge example
└── 📄 README.md                            # 📖 This file
```

## ✨ Key Features

### 🛍️ Multi-Product Support
- **📦 Base Products**: Digital items (no shipping required)
- **🚚 Shippable Products**: Physical items with weight-based shipping
- **⏰ Expirable Products**: Items with expiration dates
- **🔄 Combined Products**: Shippable + Expirable items

### 👥 Customer Management
- **💰 Balance Management**: Track customer spending power
- **🔒 Payment Validation**: Ensure sufficient funds before checkout
- **📊 Transaction History**: Monitor customer purchases

### 🛒 Shopping Cart Features
- **➕ Add Items**: With quantity validation
- **❌ Remove Items**: Individual item removal
- **🧹 Clear Cart**: Empty entire cart
- **📈 Calculations**: Subtotal and item count tracking

### 💳 Checkout System
- **✅ Validation**: Stock, expiration, and balance checks
- **🚚 Shipping**: Automatic calculation for physical items
- **📋 Receipts**: Detailed transaction summaries
- **🔄 Inventory**: Automatic stock updates

## 🚀 Getting Started

### Running the Application

1. **📁 Clone the repository**
   ```bash
   git clone https://github.com/MohamedAboAlaa/Fawry-Internship-Challenge.git
   cd ecommerce-system-task
   ```

2. **▶️ Run the main demo**
   ```bash
   javac src/main/Main.java
   java src.main.Main
   ```

3. **🧪 Run individual tests**
   ```bash
   javac src/tests/test1.java
   java src.tests.test1
   ```

## 📦 Product Types

### 📱 Digital Products
```java
Product scratchCard = new Product("Mobile Scratch Card", 50.0, 10);
```
- No shipping required
- Instant delivery
- Examples: Software, gift cards, credits

### 📦 Physical Products
```java
ShippableProduct tv = new ShippableProduct("TV", 266.67, 5, 5.0);
```
- Requires shipping
- Weight-based shipping costs
- Examples: Electronics, furniture, books

### 🥛 Perishable Products
```java
ShippableProduct cheese = new ShippableProduct("Cheese", 100.0, 10, 0.4, 
                                               LocalDate.now().plusDays(7));
```
- Has expiration date
- Automatic expiration checking
- Examples: Food, medicines, flowers

## 👥 Customer Management

```java
Customer customer = new Customer("Abo Alaa", 1500.0);
```

### 💰 Balance Operations
- Check affordability: `customer.canAfford(amount)`
- Deduct payment: `customer.deductBalance(amount)`
- Update balance: `customer.setBalance(newBalance)`

## 🛍️ Shopping Cart System

```java
Cart cart = new Cart();
cart.add(product, quantity);    // ➕ Add items
cart.remove(product);           // ❌ Remove items
cart.clear();                   // 🧹 Clear cart
cart.getSubtotal();            // 💰 Calculate total
```

### 🔒 Validation Features
- **📦 Stock Checking**: Prevents overselling
- **🚫 Null Protection**: Validates product existence
- **🔢 Quantity Validation**: Ensures positive quantities

## 💳 Checkout Process

```java
CheckoutService.checkout(customer, cart);
```

### 📋 Checkout Steps
1. **✅ Validation**: Cart, stock, expiration, balance
2. **🧮 Calculation**: Subtotal + shipping fees
3. **💰 Payment**: Balance deduction
4. **📦 Inventory**: Stock updates
5. **🚚 Shipping**: Shipment processing
6. **📋 Receipt**: Transaction summary

## 🚚 Shipping System

### 💰 Shipping Costs
```java
// Base fee + (weight × rate)
double shippingFee = 10.0 + (totalWeight * 20.0);
```

### 📊 Shipping Rates
- **🏠 Base Fee**: $10.00
- **⚖️ Weight Rate**: $20.00 per kg
- **📦 Free Shipping**: For non-physical items

### 📮 Shipment Processing
- Weight calculations
- Package summaries
- Shipping notifications

## 🧪 Test Cases

| Test | 📝 Description | 🎯 Purpose |
|------|----------------|------------|
| **test1** | ✅ Successful checkout | Normal transaction flow |
| **test2** | ❌ Empty cart | Error handling |
| **test3** | 💰 Insufficient balance | Payment validation |
| **test4** | 📦 Out of stock | Inventory management |
| **test5** | ⏰ Expired products | Product validation |
| **test6** | 💻 Non-shippable only | Digital products |
| **test7** | 🔄 Complex scenarios | Edge cases |
| **test8** | 🎯 Exact challenge | Reference implementation |

## 📊 Usage Examples

### 🛒 Basic Shopping Flow
```java
// Create products
ShippableProduct cheese = new ShippableProduct("Cheese", 100.0, 10, 0.4, 
                                               LocalDate.now().plusDays(7));
Product giftCard = new Product("Gift Card", 50.0, 20);

// Create customer
Customer customer = new Customer("John Doe", 500.0);

// Shopping
Cart cart = new Cart();
cart.add(cheese, 2);
cart.add(giftCard, 1);

// Checkout
CheckoutService.checkout(customer, cart);
```

### 🎯 Expected Output
```
----------------------
** Checkout receipt **
2x Cheese 200
1x Gift Card 50
----------------------
Subtotal 250
Shipping 26
Amount 276
Customer balance after payment: 224.00
----------------------

----------------------
** Shipment notice **
2x Cheese 400g
Total package weight 0.8kg
```

## 🎉 Features Demonstrated

- **🏗️ Object-Oriented Design**: Inheritance, polymorphism, encapsulation
- **🔌 Interface Implementation**: Shippable interface
- **🚫 Error Handling**: Comprehensive validation
- **📊 Business Logic**: Real-world e-commerce scenarios
- **🧪 Testing**: Comprehensive test coverage
- **📋 Documentation**: Clear code comments and structure
---

**🎯 Built with Java • 💻 Object-Oriented Programming • 🛒 E-Commerce Logic**
