
public class app {
  public static void main(String[] args) {

    Goods car = new CreateGoods()
    .setId(265780)
    .setName("Машина")
    .setPrice(250000)
    .setDescription("BMW x5")
    .Create();
    
    Goods microwave = new Goods();

    Goods notebook = new CreateGoods()
    .setId(789560)
    .setName("Ноутбук")
    .setPrice(75000)
    .setDescription("asus zenbook 13 oled")
    .Create();

    microwave.print();
    car.print();
    notebook.print();
  }
}
