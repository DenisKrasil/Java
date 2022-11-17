class CreateGoods {

    Goods temp;

    public CreateGoods() {
        temp = new Goods();
        temp.id = 0;
        temp.name=" ";
        temp.price= 0;
        temp.description="";
    }

    public CreateGoods setId(int id) {
        temp.id = id;
        return this;
    }

    public CreateGoods setName(String name) {
        temp.name = name;
        return this;
    }

    public CreateGoods setPrice(double price) {
        temp.price = price;
        return this;
    }

    public CreateGoods setDescription(String description) {
        temp.description = description;
        return this;
    }

    public Goods Create() {
        return temp;
    }
}

//     class ProxyName {
//     String val;

//     public ProxyName(String v) {
//         this.val = v != null ? v : "EMPTY";

//         if (v != null)
//         this.val = v;
//         else
//         this.val = "EMPTY";
//     }

//     public String getVal() {
//         return val;
//     }
//     }

//     public CreateGoods(String name) {
//     this.name = new ProxyName(name).getVal();
//     }

//     public CreateGoods(String name, double price) {
//     this.name = name;
//     this.price = price;
//     }

//     public CreateGoods(int id, String name) {
//     this.id = id;
//     this.name = name;
//     }

//     public CreateGoods(int id, String name, double price) {
//     this.id = id;
//     this.name = name;
//     this.price = price;
//     }

    
    
// }
