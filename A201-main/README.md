# Restaurant CLI (Java)

## Run
    mkdir -p out
    javac -d out Main.java $(find src -name "*.java")
    java -cp out Main

## Flow
Tables -> Menu -> Orders -> Payments
1. Orders > New Order: pick a FREE table (table becomes occupied), add menu items
2. Orders > View Order / Bill: see items and total
3. Payments > Pay Order Bill: choose Cash/Card/UPI (amount = order total)
   -> order marked PAID, table freed automatically
4. Orders > Cancel Order: order cancelled, table freed

## Structure
models/       Table, MenuItem, Order, OrderItem, Payment
repository/   in-memory storage
service/      business logic (OrderService links Table + Menu; PaymentService links Order)
payment/      PaymentMethod interface + Cash/Card/UPI (polymorphism)
CLI/          Input (shared Scanner), MainCli, TableCli, MenuCli, OrderCli, PaymentCli
