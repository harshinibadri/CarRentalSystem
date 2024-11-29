package Harshini;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class Car {
    private String carId;
    private String brand;
    private String model;
    private double basePricePerDay;
    private boolean isAvailable;

    public Car(String carId, String brand, String model, double basePricePerDay) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.basePricePerDay = basePricePerDay;
        this.isAvailable = true;
    }

    public String getCarId() {
        return carId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double calculatePrice(int rentalDays) {
        return basePricePerDay * rentalDays;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void rent() {
        isAvailable = false;
    }

    public void returnCar() {
        isAvailable = true;
    }
}

class Customer {
    private String customerId;
    private String name;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }
}

class Rental {
    private Car car;
    private Customer customer;
    private int days;

    public Rental(Car car, Customer customer, int days) {
        this.car = car;
        this.customer = customer;
        this.days = days;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDays() {
        return days;
    }
}

class CarRentalSystem {
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public CarRentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void rentCar(Car car, Customer customer, int days) {
        if (car.isAvailable()) {
            car.rent();
            rentals.add(new Rental(car, customer, days));
            System.out.println("Car rented successfully.");
        } else {
            System.out.println("Car is not available for rent.");
        }
    }

    public void returnCar(Car car) {
        car.returnCar();
        Rental rentalToRemove = null;
        for (Rental rental : rentals) {
            if (rental.getCar() == car) {
                rentalToRemove = rental;
                break;
            }
        }
        if (rentalToRemove != null) {
            rentals.remove(rentalToRemove);
            System.out.println("Car returned successfully.");
        } else {
            System.out.println("Car was not rented.");
        }
    }

    public void menu() {
        Frame frame = new Frame("Car Rental System");
        frame.setSize(400, 200);
        frame.setLayout(new FlowLayout());

        Button rentButton = new Button("Rent a Car");
        Button returnButton = new Button("Return a Car");
        Button exitButton = new Button("Exit");
        rentButton.setBackground(Color.PINK);
        returnButton.setBackground(Color.YELLOW);
        exitButton.setBackground(Color.GREEN);
        frame.add(rentButton);
        frame.add(returnButton);
        frame.add(exitButton);

        rentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Frame rentFrame = new Frame("Rent a Car");
                rentFrame.setSize(300, 200);
                rentFrame.setLayout(new GridLayout(4, 2));

                Label nameLabel = new Label("Enter your name:");
                TextField nameField = new TextField();

                Label carIdLabel = new Label("Enter car ID:");
                TextField carIdField = new TextField();
                


                Label daysLabel = new Label("Enter rental days:");
                TextField daysField = new TextField();

                Button confirmButton = new Button("Confirm");

                confirmButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String customerName = nameField.getText();
                        String carId = carIdField.getText();
                        int rentalDays = Integer.parseInt(daysField.getText());

                        Customer newCustomer = new Customer("CUS" + (customers.size() + 1), customerName);
                        addCustomer(newCustomer);

                        Car selectedCar = null;
                        for (Car car : cars) {
                            if (car.getCarId().equals(carId) && car.isAvailable()) {
                                selectedCar = car;
                                break;
                            }
                        }

                        if (selectedCar != null) {
                            double totalPrice = selectedCar.calculatePrice(rentalDays);
                            System.out.println("== Rental Information ==");
                            System.out.println("Customer ID: " + newCustomer.getCustomerId());
                            System.out.println("Customer Name: " + newCustomer.getName());
                            System.out.println("Car: " + selectedCar.getBrand() + " " + selectedCar.getModel());
                            System.out.println("Rental Days: " + rentalDays);
                            System.out.printf("Total Price: $%.2f%n", totalPrice);
                            rentCar(selectedCar, newCustomer, rentalDays);
                            rentFrame.dispose();
                        } else {
                            System.out.println("Invalid car selection or car not available for rent.");
                        }
                    }
                });

                rentFrame.add(nameLabel);
                rentFrame.add(nameField);
                rentFrame.add(carIdLabel);
                rentFrame.add(carIdField);
                rentFrame.add(daysLabel);
                rentFrame.add(daysField);
                rentFrame.add(new Label(""));
                rentFrame.add(confirmButton);

                rentFrame.setVisible(true);
            }
        });

        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Frame returnFrame = new Frame("Return a Car");
                returnFrame.setSize(300, 100);
                returnFrame.setLayout(new FlowLayout());

                Label carIdLabel = new Label("Enter car ID:");
                TextField carIdField = new TextField();

                Button confirmButton = new Button("Confirm");

                confirmButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String carId = carIdField.getText();
                        Car carToReturn = null;
                        for (Car car : cars) {
                            if (car.getCarId().equals(carId) && !car.isAvailable()) {
                                carToReturn = car;
                                break;
                            }
                        }

                        if (carToReturn != null) {
                            returnCar(carToReturn);
                            returnFrame.dispose();
                        } else {
                            System.out.println("Invalid car ID or car is not rented.");
                        }
                    }
                });

                returnFrame.add(carIdLabel);
                returnFrame.add(carIdField);
                returnFrame.add(confirmButton);

                returnFrame.setVisible(true);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.add(rentButton);
        frame.add(returnButton);
        frame.add(exitButton);

        frame.setVisible(true);
    }
}

public class CarApp {
    public static void main(String[] args) {
        CarRentalSystem rentalSystem = new CarRentalSystem();

        Car car1 = new Car("C001", "Toyota", "Camry", 60.0); 
        Car car2 = new Car("C002", "Honda", "Accord", 70.0);
        Car car3 = new Car("C003", "Mahindra", "Thar", 150.0);
        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.addCar(car3);
        rentalSystem.menu();
    }
}
