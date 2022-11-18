class Goods {

    public int id;
    public String name;
    public double price;
    public String description;
    
    public void print() {
    //     System.out.println(
    //         String.format(
    //             "%d %d %s %s %s",
    //             name,
    //             price,
    //             id,
    //             description));
    // }
    System.out.println("Товар - " + this.name);
    System.out.println("ID - "+ this.id);
    System.out.println("Цена - " + this.price + " рублей");
    System.out.println("Описание - " + this.description);
    }
}