public class App {
    public static void main(String[] args) {
        // Configure Spark
        port(4567);
        staticFiles.location("/public");
        
        // Initialize DAO
        ProductDAO productDAO = new ProductDAO();
        
        // Initialize Gson
        Gson gson = new Gson();
        
        // Routes
        get("/", (req, res) -> {
            res.redirect("/form.html");
            return null;
        });
        
        // Get all products
        get("/products", (req, res) -> {
            res.type("application/json");
            return gson.toJson(productDAO.getAllProducts());
        }, gson::toJson);
        
        // Get product by ID
        get("/products/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Product product = productDAO.getProduct(id);
            if (product != null) {
                res.type("application/json");
                return product;
            } else {
                res.status(404);
                return "Product not found";
            }
        }, gson::toJson);
        
        // Create new product
        post("/products", (req, res) -> {
            Product product = gson.fromJson(req.body(), Product.class);
            boolean created = productDAO.addProduct(product);
            if (created) {
                res.status(201);
                return "Product created successfully";
            } else {
                res.status(500);
                return "Error creating product";
            }
        });
        
        // Update product
        put("/products/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Product product = gson.fromJson(req.body(), Product.class);
            product.setId(id);
            boolean updated = productDAO.updateProduct(product);
            if (updated) {
                return "Product updated successfully";
            } else {
                res.status(404);
                return "Product not found";
            }
        });
        
        // Delete product
        delete("/products/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            boolean deleted = productDAO.deleteProduct(id);
            if (deleted) {
                return "Product deleted successfully";
            } else {
                res.status(404);
                return "Product not found";
            }
        });
    }
}
