# Car Rental System

## Overview

The **Car Rental System** is a simple Java-based application that allows users to rent and return cars. It simulates a car rental service where customers can choose a car, specify the rental duration, and calculate the total rental price. The system also provides an option to return the rented cars.

This project uses **AWT (Abstract Window Toolkit)** to create a graphical user interface (GUI) for interacting with the system.

---

## Features

- **Rent a Car**: Customers can rent a car by entering their name, the car ID, and the rental duration. The system checks availability and calculates the total rental price.
- **Return a Car**: Customers can return a rented car by entering the car ID.
- **Car Management**: Keeps track of car availability and customer details.
- **Dynamic GUI**: Built with Java's AWT for an intuitive and interactive experience.

---

## Technologies Used

- **Programming Language**: Java
- **GUI Library**: Java AWT (Abstract Window Toolkit)

---

## Classes and Their Functions

### 1. **Car**
- Represents a car available for rent.
- Attributes:
  - `carId` (String): Unique identifier for the car.
  - `brand` (String): Brand of the car.
  - `model` (String): Model of the car.
  - `basePricePerDay` (double): Daily rental price.
  - `isAvailable` (boolean): Indicates if the car is available for rent.
- Methods:
  - `calculatePrice(int days)`: Calculates the total price for renting the car.
  - `rent()`: Marks the car as rented.
  - `returnCar()`: Marks the car as available.

### 2. **Customer**
- Represents a customer who rents a car.
- Attributes:
  - `customerId` (String): Unique identifier for the customer.
  - `name` (String): Name of the customer.

### 3. **Rental**
- Represents a rental transaction between a car and a customer.
- Attributes:
  - `car` (Car): The rented car.
  - `customer` (Customer): The renting customer.
  - `days` (int): Rental duration.

### 4. **CarRentalSystem**
- Manages the overall rental process.
- Features:
  - Add cars and customers to the system.
  - Rent and return cars.
  - Displays an interactive menu with options for renting and returning cars.

<details>
<summary>Click to view video</summary>

![Watch Demo](WorkingModel.mp4)

</details>
